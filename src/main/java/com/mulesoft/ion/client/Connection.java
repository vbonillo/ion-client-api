/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package com.mulesoft.ion.client;

import com.mulesoft.ion.client.Notification.Priority;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.core.util.Base64;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * Base class for ION interaction.
 */
public class Connection {

    public static final String DEFAULT_URL = "https://muleion.com/";
    private final Client client;
    private final String url;
    private final String username;
    private final String password;
    private final String apiToken;

    public Connection(final String url, final String username, final String password) {
        if (url == null) {
            throw new IllegalArgumentException("null url");
        }
        
        apiToken = System.getProperty("ion.api.token");
        if (apiToken == null) {
            if (username == null) {
                throw new IllegalArgumentException("null username");
            }
            if (password == null) {
                throw new IllegalArgumentException("null password");
            }
        }

        //TODO add URL validation
        if (!url.endsWith("/")) { 
            this.url = url + "/";
        } else {
            this.url = url;
        }
        this.username = username;
        this.password = password;

        //Ensure we have all required parameters
        final ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getClasses().add(JacksonJsonProvider.class);
        JacksonJsonProvider jsonProvider = new JacksonJsonProvider(new ObjectMapper());
        clientConfig.getSingletons().add(jsonProvider);
        this.client = Client.create(clientConfig);
        this.client.addFilter(new LoggingFilter());
    }

    protected final String getUrl() {
        return this.url;
    }

    protected final String getUsername() {
        return this.username;
    }

    protected final String getPassword() {
        return this.password;
    }

    public DomainConnection on(final String domain) {
        return new DomainConnection(this, domain);
    }

    protected final String getAPIURL() {
        return this.url+"api/";
    }

    protected final WebResource.Builder createBuilder(final String path) {
        WebResource pathResource = this.client.resource(getAPIURL()).path(path);
        
        if (apiToken == null) {
            return pathResource.header(HttpHeaders.AUTHORIZATION, "Basic "+ new String(Base64.encode(this.username+":"+this.password), Charset.forName("ASCII")));
        } else {
            return pathResource.header("X-ION-Authenticate", apiToken);
        }
    }

    /**
     * @return true if provided connection details allow to connect; false otherwise
     */
    public final boolean test() {
        try {
            createBuilder("applications/").get(Object.class);
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }

    /**
     * @return all existing applications
     */
    public final List<Application> list() {
        ClientResponse response = createBuilder("applications/").type(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
        handleErrors(response);
        return response.getEntity(new GenericType<List<Application>>(){});
    }

    public void createNotification(String text, Priority priority, String domain) {
        Notification notification = new Notification();
        notification.setMessage(text);
        notification.setDomain(domain);
        notification.setPriority(priority);
        
        ClientResponse response = createBuilder("notifications/")
            .type(MediaType.APPLICATION_JSON_TYPE)
            .post(ClientResponse.class, notification);
        
        handleErrors(response);
    }

    protected void handleErrors(ClientResponse response) {
        if (response.getStatus() == 404) {
            throw new IONException("That domain was not found.");
        } else if (response.getStatus() == 401) {
            throw new IONException("Invalid username or password.");
        } else if (response.getStatus() == 403) {
            throw new IONException("You do not have access to perform that action.");
        } else if (response.getStatus() >= 400) {
            if (response.getType() != null && response.getType().isCompatible(MediaType.APPLICATION_JSON_TYPE)) {
                Map responseProps = response.getEntity(Map.class);
                System.out.println(responseProps);
                throw new IONException((String)responseProps.get("message"));
            } else {
                String text = response.getEntity(String.class);
                throw new IONException("Error " + response.getStatus() + ". " + text);
            }
        }
    }

    public static class ObjectMapper extends org.codehaus.jackson.map.ObjectMapper {

        public ObjectMapper() {
            super();
            getSerializationConfig().setSerializationInclusion(Inclusion.NON_NULL);
        }

    }
}