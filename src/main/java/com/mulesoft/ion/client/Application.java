/**
 * This software is licensed under the Apache 2 license, quoted below.
 *
 * Copyright 2010 Julien Eluard
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *     [http://www.apache.org/licenses/LICENSE-2.0]
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.mulesoft.ion.client;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Maps application JSON type.
 *
 * @see http://www.mulesoft.org/documentation/display/ION/Get+An+Application
 */
public class Application {

    public enum Status {
        WAITING_FOR_SERVER,
        DEPLOYING,
        UNDEPLOYING,
        STARTED,
        UNDEPLOYED,
        DEPLOY_FAILED
    }

    public static class WorkerStatus {

        private String id;
        private String host;
        private int port;
        private Status status;

        public String getId() {
            return this.id;
        }

        public void setId(final String id) {
            this.id = id;
        }

        public String getHost() {
            return this.host;
        }

        public void setHost(final String host) {
            this.host = host;
        }

        public int getPort() {
            return this.port;
        }

        public void setPort(final int port) {
            this.port = port;
        }

        public Status getStatus() {
            return this.status;
        }

        public void setStatus(final Status status) {
            this.status = status;
        }

    }

    private String description;
    private String domain;
    private String fullDomain;
    private Status status;
    private boolean hasFile;
    private String filename;
    private Date lastUpdateTime;
    private int workers;
    private int remainingWorkerCount;
    private List<WorkerStatus> workerStatuses;
    private String muleVersion;
    private List<String> supportedVersions;
    private Map<String, String> properties;
    private String href;
    private Status deploymentUpdateStatus;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(final String domain) {
        this.domain = domain;
    }

    public String getFullDomain() {
        return this.fullDomain;
    }

    public void setFullDomain(final String fullDomain) {
        this.fullDomain = fullDomain;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public boolean isHasFile() {
        return this.hasFile;
    }

    public void setHasFile(final boolean hasFile) {
        this.hasFile = hasFile;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(final String filename) {
        this.filename = filename;
    }

    public Date getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setLastUpdateTime(final Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public int getWorkers() {
        return this.workers;
    }

    public void setWorkers(final int workers) {
        this.workers = workers;
    }

    public int getRemainingWorkerCount() {
        return this.remainingWorkerCount;
    }

    public void setRemainingWorkerCount(final int remainingWorkerCount) {
        this.remainingWorkerCount = remainingWorkerCount;
    }

    public List<WorkerStatus> getWorkerStatuses() {
        return this.workerStatuses;
    }

    public void setWorkerStatuses(final List<WorkerStatus> workerStatuses) {
        this.workerStatuses = workerStatuses;
    }

    public String getMuleVersion() {
        return this.muleVersion;
    }

    public void setMuleVersion(final String muleVersion) {
        this.muleVersion = muleVersion;
    }

    public List<String> getSupportedVersions() {
        return this.supportedVersions;
    }

    public void setSupportedVersions(final List<String> supportedVersions) {
        this.supportedVersions = supportedVersions;
    }

    public Map<String, String> getProperties() {
        return this.properties;
    }

    public void setProperties(final Map<String, String> properties) {
        this.properties = properties;
    }

    public String getHref() {
        return this.href;
    }

    public void setHref(final String href) {
        this.href = href;
    }

    public Status getDeploymentUpdateStatus() {
        return this.deploymentUpdateStatus;
    }

    public void setDeploymentUpdateStatus(final Status deploymentUpdateStatus) {
        this.deploymentUpdateStatus = deploymentUpdateStatus;
    }

}