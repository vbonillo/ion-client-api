package com.mulesoft.cloudhub.client;

public class Multitenancy
{

    private Boolean enabled;
    private Integer maxTenants;

    public Multitenancy()
    {
    }

    public Boolean getEnabled()
    {
        return enabled;
    }

    public void setEnabled(Boolean enabled)
    {
        this.enabled = enabled;
    }

    public Integer getMaxTenants()
    {
        return maxTenants;
    }

    public void setMaxTenants(Integer maxTenants)
    {
        this.maxTenants = maxTenants;
    }

    @Override
    public String toString()
    {
        return "Multitenancy{" +
               "enabled=" + enabled +
               ", maxTenants=" + maxTenants +
               '}';
    }
}