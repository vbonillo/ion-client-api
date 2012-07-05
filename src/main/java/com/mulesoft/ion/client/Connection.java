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
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.core.util.Base64;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.management.remote.NotificationResult;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for ION interaction.
 */
public class Connection {
    private Logger logger = LoggerFactory.getLogger(Connection.class);
    
    public static final String DEFAULT_URL = "https://muleion.com/";
    private final Client client;
    private final String url;
    private final String username;
    private final String password;
    private String apiToken;
    
    public Connection(final String url, final String username, final String password) {
        this(url, username, password, false);
    }
    
    public Connection(final String url, final String username, final String password, boolean debug) {
        if (url == null) {
            throw new IllegalArgumentException("null url");
        }
        
        if (username == null || "".equals(username)) {
            // only use the apiToken if username isn't set
            apiToken = System.getProperty("cloudhub.api.token");
            
            if (apiToken == null) {
            	apiToken = System.getProperty("ion.api.token");
            }
            logger.debug("Using iON token authentication.");
        } else {
            logger.debug("Using iON username/password authentication because the username is set.");
        }
        
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
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JacksonJsonProvider jsonProvider = new JacksonJsonProvider(mapper);
        clientConfig.getSingletons().add(jsonProvider);
        this.client = Client.create(clientConfig);
        
        if (debug) {
            this.client.addFilter(new LoggingFilter());
        }
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

    protected final Builder createBuilder(final String path) {
        WebResource resource = createResource(path);
        
        return authorizeResource(resource);
    }

    private Builder authorizeResource(WebResource pathResource) {
        if (apiToken == null) {
            return pathResource.header(HttpHeaders.AUTHORIZATION, "Basic "+ new String(Base64.encode(this.username+":"+this.password), Charset.forName("ASCII")));
        } else {
            return pathResource.header("X-ION-Authenticate", apiToken);
        }
    }

    private WebResource createResource(final String path) {
        return this.client.resource(getAPIURL()).path(path);
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
    public final List<Application> listApplications() {
        ClientResponse response = createBuilder("applications/").type(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
        handleErrors(response);
        return response.getEntity(new GenericType<List<Application>>(){});
    }

    public Notification createNotification(String text, Priority priority, String domain) {
        Notification notification = new Notification();
        notification.setMessage(text);
        notification.setDomain(domain);
        notification.setPriority(priority);
        
        ClientResponse response = createBuilder("notifications/")
            .type(MediaType.APPLICATION_JSON_TYPE)
            .post(ClientResponse.class, notification);
        
        handleErrors(response);
        
        // we got the location, ow fetch the resource
        Builder notificationResource = authorizeResource(client.resource(response.getLocation()));
        response = notificationResource.get(ClientResponse.class);
        
        handleErrors(response);
        
        return response.getEntity(Notification.class);
    }


    /**
     * @param includeDismissed 
     * @param offset 
     * @param maxResults 
     * @return all existing applications
     */
    public final NotificationResults listNotifications(Integer maxResults, Integer offset) {
        WebResource resource = createResource("/notifications");
        
        if (maxResults != null) {
            resource.queryParam("maxResults", maxResults.toString());
        }

        if (offset != null) {
            resource.queryParam("offset", offset.toString());
        }

      
        ClientResponse response = authorizeResource(resource).type(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
        handleErrors(response);
        return response.getEntity(NotificationResults.class);
    }
    
    public final void dismissNotification(String href) {

        Builder resource =  authorizeResource(this.client.resource(href));
        
        ClientResponse response = resource.type(MediaType.APPLICATION_JSON_TYPE).delete(ClientResponse.class);
        handleErrors(response);
    };
    

    public final void dismissAllNotifications() {
        Builder resource = createBuilder("notifications/");
        
        ClientResponse response = resource.type(MediaType.APPLICATION_JSON_TYPE).delete(ClientResponse.class);
        handleErrors(response);
    };
    
    protected void handleErrors(ClientResponse response) {
        if (response.getStatus() == 404) {
            throw new IonException("That resource was not found.");
        } else if (response.getStatus() == 401) {
            throw new IonException("Invalid username or password.");
        } else if (response.getStatus() == 403) {
            throw new IonException("You do not have access to perform that action.");
        } else if (response.getStatus() >= 400) {
            if (response.getType() != null && response.getType().isCompatible(MediaType.APPLICATION_JSON_TYPE)) {
                Map responseProps = response.getEntity(Map.class);
                System.out.println(responseProps);
                throw new IonException((String)responseProps.get("message"));
            } else {
                String text = response.getEntity(String.class);
                throw new IonException("Error " + response.getStatus() + ". " + text);
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