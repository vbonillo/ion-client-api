/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */


package com.mulesoft.cloudhub.client;

import java.util.Map;

/**
 * Maps application JSON type.
 *
 * @see http://www.mulesoft.org/documentation/display/ION/Get+An+Application
 */

public class ApplicationUpdateInfo
{

    private String description;
    private Integer workers;
    private String muleVersion;
    private Map<String, String> properties;
    private String filename;

    public ApplicationUpdateInfo(Application application)
    {
        setDescription(application.getDescription());
        setWorkers(application.getWorkers());
        setMuleVersion(application.getMuleVersion());
        setProperties(application.getProperties());
        setFilename(application.getFilename());
    }

    public ApplicationUpdateInfo(String p_description, Integer p_workers, String p_muleVersion, Map<String, String> p_properties, String p_filename)
    {
        setDescription(p_description);
        setWorkers(p_workers);
        setMuleVersion(p_muleVersion);
        setProperties(p_properties);
        setFilename(p_filename);
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getWorkers()
    {
        return workers;
    }

    public void setWorkers(Integer workers)
    {
        this.workers = workers;
    }

    public String getMuleVersion()
    {
        return muleVersion;
    }

    public void setMuleVersion(String muleVersion)
    {
        this.muleVersion = muleVersion;
    }

    public Map<String, String> getProperties()
    {
        return properties;
    }

    public void setProperties(Map<String, String> properties)
    {
        this.properties = properties;
    }

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    @Override
    public String toString()
    {
        return "ApplicationUpdateInfo{" +
               "description='" + description + '\'' +
               ", workers=" + workers +
               ", muleVersion='" + muleVersion + '\'' +
               ", properties=" + properties +
               ", filename='" + filename + '\'' +
               '}';
    }
}
