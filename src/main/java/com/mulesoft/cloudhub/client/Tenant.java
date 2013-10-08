package com.mulesoft.cloudhub.client;

import java.util.Date;
import java.util.Map;

/**
 * @author mariano.gonzalez@mulesoft.com
 */
public class Tenant
{

    private String id;
    private String href;
    private String name;
    private String email;
    private boolean enabled;
    private boolean alertsEnabled;
    private Date created;
    private Map<String, String> configuration;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getHref()
    {
        return href;
    }

    public void setHref(String href)
    {
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public boolean isAlertsEnabled() {
        return alertsEnabled;
    }

    public void setAlertsEnabled(boolean alertsEnabled) {
        this.alertsEnabled = alertsEnabled;
    }

    public Map<String, String> getConfiguration()
    {
        return configuration;
    }

    public void setConfiguration(Map<String, String> configuration)
    {
        this.configuration = configuration;
    }

    @Override
    public String toString()
    {
        return "Tenant{" +
               "id='" + getId() + '\'' +
               ", href='" + getHref() + '\'' +
               ", name='" + getName() + '\'' +
               ", email='" + getEmail() + '\'' +
               ", created=" + getCreated() +
               ", enabled=" + isEnabled() +
               ", alertsEnabled=" + isAlertsEnabled() +
               ", configuration=" + getConfiguration() +
               '}';
    }
}
