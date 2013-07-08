/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.mulesoft.cloudhub.client;

import java.util.Date;
import java.util.Map;

public class Notification
{

    public enum Priority
    {
        INFO, ERROR, WARN
    }

    private String id;
    private String message;
    private String domain;
    private Priority priority;
    private Date createdAt;
    private Date dismissedOn;
    private String href;
    private String username;
    private Date readOn;
    private String tenantId;
    private String transactionId;
    private Map<String, String> customProperties;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getDomain()
    {
        return domain;
    }

    public void setDomain(String domain)
    {
        this.domain = domain;
    }

    public Priority getPriority()
    {
        return priority;
    }

    public void setPriority(Priority priority)
    {
        this.priority = priority;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getDismissedOn()
    {
        return dismissedOn;
    }

    public void setDismissedOn(Date dismissedOn)
    {
        this.dismissedOn = dismissedOn;
    }

    public String getHref()
    {
        return href;
    }

    public void setHref(String href)
    {
        this.href = href;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Date getReadOn()
    {
        return readOn;
    }

    public void setReadOn(Date readOn)
    {
        this.readOn = readOn;
    }

    public Map<String, String> getCustomProperties()
    {
        return customProperties;
    }

    public void setCustomProperties(Map<String, String> customProperties)
    {
        this.customProperties = customProperties;
    }

    public String getTenantId()
    {
        return tenantId;
    }

    public void setTenantId(String tenantId)
    {
        this.tenantId = tenantId;
    }

    public String getTransactionId()
    {
        return transactionId;
    }

    public void setTransactionId(String transactionId)
    {
        this.transactionId = transactionId;
    }

    @Override
    public String toString()
    {
        return "Notification{" +
               "id='" + id + '\'' +
               ", message='" + message + '\'' +
               ", domain='" + domain + '\'' +
               ", priority=" + priority +
               ", createdAt=" + createdAt +
               ", dismissedOn=" + dismissedOn +
               ", href='" + href + '\'' +
               ", username='" + username + '\'' +
               ", readOn=" + readOn +
               ", tenantId='" + tenantId + '\'' +
               ", transactionId='" + transactionId + '\'' +
               ", customProperties=" + customProperties +
               '}';
    }
}
