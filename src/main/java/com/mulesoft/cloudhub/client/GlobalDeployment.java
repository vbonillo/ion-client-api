package com.mulesoft.cloudhub.client;

public class GlobalDeployment
{

    private Boolean enabled;
    private String defaultRegion;

    public Boolean getEnabled()
    {
        return enabled;
    }

    public void setEnabled(Boolean enabled)
    {
        this.enabled = enabled;
    }

    public String getDefaultRegion()
    {
        return defaultRegion;
    }

    public void setDefaultRegion(String defaultRegion)
    {
        this.defaultRegion = defaultRegion;
    }

    @Override
    public String toString()
    {
        return "GlobalDeployment{" +
               "enabled=" + enabled +
               ", defaultRegion='" + defaultRegion + '\'' +
               '}';
    }
}