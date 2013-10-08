package com.mulesoft.cloudhub.client;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.ws.rs.core.MediaType;
import java.util.*;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: vbonillo
 * Date: 10/8/13
 * Time: 11:32 AM
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({WebResource.class})
public class TenantConnectionTest {
    private static final String TENANT_ID = "tenantId";
    private static final String TENANT_HREF = "https://cloudhub.io/api/applications/myApp/tenants/tid";
    private static final String TENANT_NAME = "tenantName";
    private static final boolean TENANT_ENABLED = true;
    private static final boolean TENANT_ALERTS_ENABLED = false;
    private static final String TENANT_EMAIL = "derpina@derp.com";
    private static final Date TENANT_CREATED = new Date();
    private static final Map<String, String> TENANT_CONFIGURATION = Collections.EMPTY_MAP;
    private static final String DOMAIN = "domain";
    private static final List<String> TENANT_IDS = Arrays.asList("someTenantId", "someOtherTenantId");

    @Mock
    Connection connection;

    @Test
    public void testGetTenant() {
        //SET UP
        WebResource applicationsWebResource = mock(WebResource.class);
        when(connection.createResource("applications")).thenReturn(applicationsWebResource);
        WebResource domainWebResource = mock(WebResource.class);
        when(applicationsWebResource.path(DOMAIN)).thenReturn(domainWebResource);
        WebResource tenantsWebResource = mock(WebResource.class);
        when(domainWebResource.path("tenants")).thenReturn(tenantsWebResource);
        WebResource tenantIdWebResource = mock(WebResource.class);
        when(tenantsWebResource.path(TENANT_ID)).thenReturn(tenantIdWebResource);


        WebResource.Builder authorizedBuilder = mock(WebResource.Builder.class);
        when(connection.authorizeResource(tenantIdWebResource)).thenReturn(authorizedBuilder);
        WebResource.Builder jsonBuilder = mock(WebResource.Builder.class);
        when(authorizedBuilder.type(MediaType.APPLICATION_JSON_TYPE)).thenReturn(jsonBuilder);
        ClientResponse clientResponse = mock(ClientResponse.class);
        when(jsonBuilder.get(ClientResponse.class)).thenReturn(clientResponse);

        doNothing().when(connection).handleErrors(clientResponse);

        Tenant expectedTenant = buildTenant();
        when(clientResponse.getEntity(Tenant.class)).thenReturn(expectedTenant);

        //DO WORK
        Tenant actualTenant = connection.getTenant(DOMAIN, TENANT_ID);

        //VERIFY
        verify(connection, times(1)).createResource("applications");
        verify(applicationsWebResource, times(1)).path(DOMAIN);
        verify(domainWebResource, times(1)).path("tenants");
        verify(tenantsWebResource, times(1)).path(TENANT_ID);

        verify(connection, times(1)).authorizeResource(tenantIdWebResource);
        verify(authorizedBuilder, times(1)).type(MediaType.APPLICATION_JSON_TYPE);
        verify(jsonBuilder, times(1)).get(ClientResponse.class);

        verify(connection, times(1)).handleErrors(clientResponse);

        verify(clientResponse, times(1)).getEntity(Tenant.class);

        assertSame(expectedTenant, actualTenant);
    }

    @Test
    public void testCreateTenant() {
        //SET UP
        Tenant requestedTenant = buildTenant();

        WebResource applicationsWebResource = mock(WebResource.class);
        when(connection.createResource("applications")).thenReturn(applicationsWebResource);
        WebResource domainWebResource = mock(WebResource.class);
        when(applicationsWebResource.path(DOMAIN)).thenReturn(domainWebResource);
        WebResource tenantsWebResource = mock(WebResource.class);
        when(domainWebResource.path("tenants")).thenReturn(tenantsWebResource);

        WebResource.Builder authorizedBuilder = mock(WebResource.Builder.class);
        when(connection.authorizeResource(tenantsWebResource)).thenReturn(authorizedBuilder);
        WebResource.Builder entityBuilder = mock(WebResource.Builder.class);
        when(authorizedBuilder.entity(requestedTenant)).thenReturn(entityBuilder);
        WebResource.Builder jsonBuilder = mock(WebResource.Builder.class);
        when(entityBuilder.type(MediaType.APPLICATION_JSON_TYPE)).thenReturn(jsonBuilder);
        ClientResponse clientResponse = mock(ClientResponse.class);
        when(jsonBuilder.post(ClientResponse.class)).thenReturn(clientResponse);

        doNothing().when(connection).handleErrors(clientResponse);

        Tenant expectedTenant = requestedTenant;
        when(clientResponse.getEntity(Tenant.class)).thenReturn(requestedTenant);

        //DO WORK
        Tenant actualTenant = connection.create(requestedTenant, DOMAIN);

        //VERIFY
        verify(connection, times(1)).createResource("applications");
        verify(applicationsWebResource, times(1)).path(DOMAIN);
        verify(domainWebResource, times(1)).path("tenants");

        verify(connection, times(1)).authorizeResource(tenantsWebResource);
        verify(authorizedBuilder, times(1)).entity(requestedTenant);
        verify(entityBuilder, times(1)).type(MediaType.APPLICATION_JSON_TYPE);
        verify(jsonBuilder, times(1)).post(ClientResponse.class);

        verify(connection, times(1)).handleErrors(clientResponse);

        verify(clientResponse, times(1)).getEntity(Tenant.class);

        assertSame(expectedTenant, actualTenant);
    }

    @Test
    public void testUpdateTenant() {
        //SET UP
        Tenant requestedTenant = buildTenant();

        WebResource applicationsWebResource = mock(WebResource.class);
        when(connection.createResource("applications")).thenReturn(applicationsWebResource);
        WebResource domainWebResource = mock(WebResource.class);
        when(applicationsWebResource.path(DOMAIN)).thenReturn(domainWebResource);
        WebResource tenantsWebResource = mock(WebResource.class);
        when(domainWebResource.path("tenants")).thenReturn(tenantsWebResource);
        WebResource tenantIdWebResource = mock(WebResource.class);
        when(tenantsWebResource.path(requestedTenant.getId())).thenReturn(tenantIdWebResource);

        WebResource.Builder authorizedBuilder = mock(WebResource.Builder.class);
        when(connection.authorizeResource(tenantIdWebResource)).thenReturn(authorizedBuilder);
        WebResource.Builder entityBuilder = mock(WebResource.Builder.class);
        when(authorizedBuilder.entity(requestedTenant)).thenReturn(entityBuilder);
        WebResource.Builder jsonBuilder = mock(WebResource.Builder.class);
        when(entityBuilder.type(MediaType.APPLICATION_JSON_TYPE)).thenReturn(jsonBuilder);
        ClientResponse clientResponse = mock(ClientResponse.class);
        when(jsonBuilder.put(ClientResponse.class)).thenReturn(clientResponse);

        doNothing().when(connection).handleErrors(clientResponse);

        Tenant expectedTenant = requestedTenant;
        when(clientResponse.getEntity(Tenant.class)).thenReturn(requestedTenant);

        //DO WORK
        Tenant actualTenant = connection.update(requestedTenant, DOMAIN);

        //VERIFY
        verify(connection, times(1)).createResource("applications");
        verify(applicationsWebResource, times(1)).path(DOMAIN);
        verify(domainWebResource, times(1)).path("tenants");
        verify(tenantsWebResource, times(1)).path(requestedTenant.getId());

        verify(connection, times(1)).authorizeResource(tenantIdWebResource);
        verify(authorizedBuilder, times(1)).entity(requestedTenant);
        verify(entityBuilder, times(1)).type(MediaType.APPLICATION_JSON_TYPE);
        verify(jsonBuilder, times(1)).put(ClientResponse.class);

        verify(connection, times(1)).handleErrors(clientResponse);

        verify(clientResponse, times(1)).getEntity(Tenant.class);

        assertSame(expectedTenant, actualTenant);
    }

    @Test
    public void testDeleteTenant() {
        //SET UP
        WebResource applicationsWebResource = mock(WebResource.class);
        when(connection.createResource("applications")).thenReturn(applicationsWebResource);
        WebResource domainWebResource = mock(WebResource.class);
        when(applicationsWebResource.path(DOMAIN)).thenReturn(domainWebResource);
        WebResource tenantsWebResource = mock(WebResource.class);
        when(domainWebResource.path("tenants")).thenReturn(tenantsWebResource);
        WebResource tenantIdWebResource = mock(WebResource.class);
        when(tenantsWebResource.path(TENANT_ID)).thenReturn(tenantIdWebResource);

        WebResource.Builder authorizedBuilder = mock(WebResource.Builder.class);
        when(connection.authorizeResource(tenantIdWebResource)).thenReturn(authorizedBuilder);
        WebResource.Builder jsonBuilder = mock(WebResource.Builder.class);
        when(authorizedBuilder.type(MediaType.APPLICATION_JSON_TYPE)).thenReturn(jsonBuilder);
        ClientResponse clientResponse = mock(ClientResponse.class);
        when(jsonBuilder.delete(ClientResponse.class)).thenReturn(clientResponse);

        doNothing().when(connection).handleErrors(clientResponse);

        //DO WORK
        connection.delete(TENANT_ID, DOMAIN);

        //VERIFY
        verify(connection, times(1)).createResource("applications");
        verify(applicationsWebResource, times(1)).path(DOMAIN);
        verify(domainWebResource, times(1)).path("tenants");
        verify(tenantsWebResource, times(1)).path(TENANT_ID);

        verify(connection, times(1)).authorizeResource(tenantIdWebResource);
        verify(authorizedBuilder, times(1)).type(MediaType.APPLICATION_JSON_TYPE);
        verify(jsonBuilder, times(1)).delete(ClientResponse.class);

        verify(connection, times(1)).handleErrors(clientResponse);
    }

    private Tenant buildTenant() {
        Tenant tenant = new Tenant();
        tenant.setId(TENANT_ID);
        tenant.setHref(TENANT_HREF);
        tenant.setName(TENANT_NAME);
        tenant.setEnabled(TENANT_ENABLED);
        tenant.setAlertsEnabled(TENANT_ALERTS_ENABLED);
        tenant.setEmail(TENANT_EMAIL);
        tenant.setCreated(TENANT_CREATED);
        tenant.setConfiguration(TENANT_CONFIGURATION);
        return tenant;
    }
}
