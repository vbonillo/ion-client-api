package com.mulesoft.cloudhub.client;

public class Organization
{

    private String id;
    private Billing billing;
    private String adminUsername;
    private String updated;
    private String created;
    private String name;
    private GlobalDeployment globalDeployment;
    private Multitenancy multitenancy;
    private Boolean disabled;

    public Organization()
    {
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Billing getBilling()
    {
        return billing;
    }

    public void setBilling(Billing billing)
    {
        this.billing = billing;
    }

    public String getAdminUsername()
    {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername)
    {
        this.adminUsername = adminUsername;
    }

    public String getUpdated()
    {
        return updated;
    }

    public void setUpdated(String updated)
    {
        this.updated = updated;
    }

    public String getCreated()
    {
        return created;
    }

    public void setCreated(String created)
    {
        this.created = created;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public GlobalDeployment getGlobalDeployment()
    {
        return globalDeployment;
    }

    public void setGlobalDeployment(GlobalDeployment globalDeployment)
    {
        this.globalDeployment = globalDeployment;
    }

    public Multitenancy getMultitenancy()
    {
        return multitenancy;
    }

    public void setMultitenancy(Multitenancy multitenancy)
    {
        this.multitenancy = multitenancy;
    }

    public Boolean getDisabled()
    {
        return disabled;
    }

    public void setDisabled(Boolean disabled)
    {
        this.disabled = disabled;
    }
}