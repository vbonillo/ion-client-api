package com.mulesoft.cloudhub.client;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *     Interface of the cloudhub connector
 * </p>
 *
 * @since 1.4
 */
public interface CloudhubConnection {

    /**
     * @param domain
     * <p>
     *     The domain of the required connection
     * </p>
     * @return
     * <p>
     *     A connection for a specific domain
     * </p>
     */
    DomainConnection on(String domain);

    /**
     * @return true if provided connection details allow to connect; false otherwise
     */
    boolean test();

    /**
     * @return all existing applications
     */
    List<Application> listApplications();


    /**
     * Returns the Account information of the logged user
     * @return The account information.
     */
    Account getAccount();


    /**
     * Use {@link #create(Notification)} instead
     */
    @Deprecated
    Notification createNotification(String text, Notification.Priority priority, String domain, Map<String, String> customProperties);

    /**
     * <p>
     *     Connects to CH to create a notification
     * </p>
     * @param notification
     * <p>
     *     The notification information that needs to be created in CH
     * </p>
     * @return
     * <p>
     *     The created notification
     * </p>
     *
     * @throws
     * <p>
     *     CloudHub exceptions based on the http response.
     * </p>
     * @since 1.4
     */
    Notification create(Notification notification);

    /**
     * <p>
     *    Lists all the notifications of an app.
     * </p>
     *
     * <p>
     *     In the case of a multitenant application those notifications will be filtered by
     *     tenant id.
     * </p>
     *
     * @param offset
     * <p>
     *     Since which notification. For pagination.
     * </p>
     * @param maxResults
     * <p>
     *     Maximum number of results. For pagination.
     * </p>
     * @param tenantId
     * <p>
     *   Filter by tenant id. If null then don't apply the filter
     * </p>
     *
     * @return <p>
     *     all existing applications
     * </p>
     */
    NotificationResults listNotifications(Integer maxResults, Integer offset, String tenantId);

    /**
     * <p>
     *     Removes the notification form the read queue
     * </p>
     * @param href
     * <p>
     *     href id of the notification that needs to be dismissed
     * </p>
     */
    void dismissNotification(String href);

    /**
     * <p>
     *     Does what {@link #dismissNotification(String)} to all the queued notifications in CH
     * </p>
     */
    void dismissAllNotifications();

    /**
     * <p>
     * 	Lists tenants for a given domain
     * </p>
     *
     * @param domain the domain owning the tenants
     * @param limit The maximum number of results to return by default. Maximum of 100.
     * @param offset The offset to start searching at
     * @param query The company name, contact name, and email of the tenant to search form. Performs a case insensitive match to any part of the tenant name.
     * @return an instance of {@link TenantResults}
     */
    public TenantResults listTenants(String domain, Integer limit, Integer offset, String query);

    /**
     * <p>
     * 	Returns an specific tenant
     * </p>
     *
     * @param domain the domain owning the tenants
     * @param tenantId the id of the tenant you want
     * @return an instance of {@link Tenant}
     */
    public Tenant getTenant(String domain, String tenantId);

    /**
     * <p>
     * 	Creates a tenant
     * </p>
     *
     * @param tenant an instance of {@link Tenant} representing the tenant
     * @param domain the domain that will own the tenant
     * @return an instance of {@link Tenant} carrying the state of the newly created tenant
     */
    public Tenant create(Tenant tenant, String domain);

    /**
     * <p>
     * 	Updates a tenant
     * </p>
     *
     * @param tenant an instance of {@link Tenant} with the tenant's new state
     * @param domain the domain that will own the tenant
     * @return an instance of {@link Tenant} carrying the tenant's updated state
     */
    public Tenant update(Tenant tenant, String domain);

    /**
     * <p>
     * 	Deletes a given tenant
     * </p>
     *
     * @param tenantId the id of the tenant to be deleted
     * @param domain the domain that owns the tenant to be deleted
     */
    public void delete(String tenantId, String domain);

    /**
     * <p>
     * 	Deletes the tenants matching one of many given ids
     * </p>
     *
     * @param domain the domain you want to clear of tenants
     * @param tenantIds a list with tenant ids to be deleted
     */
    public void deleteTenants(String domain, List<String> tenantIds);


}
