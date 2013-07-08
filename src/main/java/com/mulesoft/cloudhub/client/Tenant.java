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
    private Date created;
    private String companyName;
    private String contactName;
    private String contactEmail;
    private boolean enabled;
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

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getContactName()
    {
        return contactName;
    }

    public void setContactName(String contactName)
    {
        this.contactName = contactName;
    }

    public String getContactEmail()
    {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail)
    {
        this.contactEmail = contactEmail;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
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
               "id='" + id + '\'' +
               ", href='" + href + '\'' +
               ", created=" + created +
               ", companyName='" + companyName + '\'' +
               ", contactName='" + contactName + '\'' +
               ", contactEmail='" + contactEmail + '\'' +
               ", enabled=" + enabled +
               ", configuration=" + configuration +
               '}';
    }
}
