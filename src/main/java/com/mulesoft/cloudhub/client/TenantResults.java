package com.mulesoft.cloudhub.client;

import java.util.List;

/**
 * @author mariano.gonzalez@mulesoft.com
 */
public class TenantResults
{

    private Long total;
    private List<Tenant> data;

    public Long getTotal()
    {
        return total;
    }

    public void setTotal(Long total)
    {
        this.total = total;
    }

    public List<Tenant> getData()
    {
        return data;
    }

    public void setData(List<Tenant> data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "TenantResults{" +
               "total=" + total +
               ", data=" + data +
               '}';
    }
}
