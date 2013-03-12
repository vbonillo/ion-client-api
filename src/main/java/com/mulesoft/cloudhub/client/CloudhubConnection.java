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
}
