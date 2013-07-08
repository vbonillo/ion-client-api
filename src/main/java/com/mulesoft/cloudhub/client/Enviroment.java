/**
 *
 */
package com.mulesoft.cloudhub.client;

public class Enviroment
{

    private String id;
    private String name;
    private boolean production;

    public Enviroment(String id, String name, boolean production)
    {
        this.id = id;
        this.name = name;
        this.production = production;
    }

    public Enviroment()
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isProduction()
    {
        return production;
    }

    public void setProduction(boolean production)
    {
        this.production = production;
    }

    @Override
    public String toString()
    {
        return "Enviroment{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", production=" + production +
               '}';
    }
}
