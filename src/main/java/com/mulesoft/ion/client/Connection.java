/**
 * This software is licensed under the Apache 2 license, quoted below.
 *
 * Copyright 2010 Julien Eluard
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *     [http://www.apache.org/licenses/LICENSE-2.0]
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.mulesoft.ion.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.Base64;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 * Base class for ION interaction.
 */
public class Connection {

    public static final String DEFAULT_URL = "https://muleion.com/";
    private final Client client;
    private final String url;
    private final String username;
    private final String password;

    public Connection(final String url, final String username, final String password) {
        if (url == null) {
            throw new IllegalArgumentException("null url");
        }
        if (username == null) {
            throw new IllegalArgumentException("null username");
        }
        if (password == null) {
            throw new IllegalArgumentException("null password");
        }

        //TODO add URL validation
        this.url = url;
        this.username = username;
        this.password = password;

        //Ensure we have all required parameters
        final ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        this.client = Client.create(clientConfig);
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

    protected final String getIONApplicationsResource() {
        return this.url+"api/applications/";
    }

    protected final WebResource.Builder createBuilder(final String path) {
        final WebResource webResource = this.client.resource(getIONApplicationsResource());
        return webResource.path(path).header(HttpHeaders.AUTHORIZATION, "Basic "+ new String(Base64.encode(this.username+":"+this.password), Charset.forName("ASCII")));
    }

    protected final String extractFailureReason(final ClientResponse response) {
        if (response.getType() != null && response.getType().isCompatible(MediaType.APPLICATION_JSON_TYPE)) {
            return response.getEntity(Map.class).get("message").toString();
        } else {
            return response.getEntity(String.class);
        }
    }

    /**
     * @return true if provided connection details allow to connect; false otherwise
     */
    public final boolean test() {
        try {
            createBuilder("").get(Object.class);
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }

    /**
     * @return all existing applications
     */
    public final List<Application> list() {
        return createBuilder("").type(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class).getEntity(new GenericType<List<Application>>(){});
    }

}