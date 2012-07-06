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

public class Notification {
    public enum Priority {
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
    private boolean read;
    private Date readOn;
       

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDismissedOn() {
        return dismissedOn;
    }

    public void setDismissedOn(Date dismissedOn) {
        this.dismissedOn = dismissedOn;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public Date getReadOn() {
		return readOn;
	}

	public void setReadOn(Date readOn) {
		this.readOn = readOn;
	}

}
