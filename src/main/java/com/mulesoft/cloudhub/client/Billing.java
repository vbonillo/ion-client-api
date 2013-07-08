package com.mulesoft.cloudhub.client;

public class Billing
{

    private String plan;

    public String getPlan()
    {
        return plan;
    }

    public void setPlan(String plan)
    {
        this.plan = plan;
    }

    @Override
    public String toString()
    {
        return "Billing{" +
               "plan='" + plan + '\'' +
               '}';
    }
}