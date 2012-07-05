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

import java.util.List;

public class NotificationResults {
    private long unreadCount;
    private List<Notification> data;

    public long getTotal() {
        if (data == null) {
        	return 0;
        }
        else {
        	return data.size();
        }
    }

    public List<Notification> getData() {
        return data;
    }

    public void setData(List<Notification> notifications) {
        this.data = notifications;
    }

	public long getUnreadCount() {
		return unreadCount;
	}

	public void setUnreadCount(long unreadCount) {
		this.unreadCount = unreadCount;
	}

}
