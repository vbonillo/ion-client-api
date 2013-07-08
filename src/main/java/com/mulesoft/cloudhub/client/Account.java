/**
 *
 */
package com.mulesoft.cloudhub.client;

import java.util.List;

public class Account
{

    private String lastName;
    private Boolean enabled;
    private String lastModified;

    private Integer remainingWorkerCount;
    private String username;
    private Organization organization;
    private Boolean confirmed;
    private String defaultEnvironment;
    private String email;
    private String created;
    private String role;
    private String activeEnvironment;
    private String firstName;
    private List<Enviroment> environments;

    public Account()
    {
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Boolean getEnabled()
    {
        return enabled;
    }

    public void setEnabled(Boolean enabled)
    {
        this.enabled = enabled;
    }

    public String getLastModified()
    {
        return lastModified;
    }

    public void setLastModified(String lastModified)
    {
        this.lastModified = lastModified;
    }

    public Integer getRemainingWorkerCount()
    {
        return remainingWorkerCount;
    }

    public void setRemainingWorkerCount(Integer remainingWorkerCount)
    {
        this.remainingWorkerCount = remainingWorkerCount;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Organization getOrganization()
    {
        return organization;
    }

    public void setOrganization(Organization organization)
    {
        this.organization = organization;
    }

    public Boolean getConfirmed()
    {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed)
    {
        this.confirmed = confirmed;
    }

    public String getDefaultEnvironment()
    {
        return defaultEnvironment;
    }

    public void setDefaultEnvironment(String defaultEnvironment)
    {
        this.defaultEnvironment = defaultEnvironment;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getCreated()
    {
        return created;
    }

    public void setCreated(String created)
    {
        this.created = created;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public String getActiveEnvironment()
    {
        return activeEnvironment;
    }

    public void setActiveEnvironment(String activeEnvironment)
    {
        this.activeEnvironment = activeEnvironment;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public List<Enviroment> getEnvironments()
    {
        return environments;
    }

    public void setEnvironments(List<Enviroment> environments)
    {
        this.environments = environments;
    }

    @Override
    public String toString()
    {
        return "Account{" +
               "email='" + email + '\'' +
               ", username='" + username + '\'' +
               ", lastName='" + lastName + '\'' +
               ", firstName='" + firstName + '\'' +
               ", enabled=" + enabled +
               ", confirmed=" + confirmed +
               ", role='" + role + '\'' +
               ", environments=" + environments +
               '}';
    }
}
