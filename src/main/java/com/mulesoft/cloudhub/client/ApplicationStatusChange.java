package com.mulesoft.cloudhub.client;

public class ApplicationStatusChange
{

    private ApplicationStatus status;

    public ApplicationStatusChange()
    {
        super();
    }

    public ApplicationStatusChange(ApplicationStatus status)
    {
        this.status = status;
    }

    public ApplicationStatus getStatus()
    {
        return status;
    }

    public void setStatus(ApplicationStatus status)
    {
        this.status = status;
    }

    public static enum ApplicationStatus
    {
        start, stop
    }

    @Override
    public String toString()
    {
        return "ApplicationStatusChange{" +
               "status=" + status +
               '}';
    }
}