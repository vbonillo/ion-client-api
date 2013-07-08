/**
 *
 */
package com.mulesoft.cloudhub.client;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class AccountSettingsTest
{

    @Test
    @Ignore
    public void testAccountSettings()
    {
        Account account = new Connection("https://dev.cloudhub.io/", "cloudhub-rest", "Cl0udhub", true).getAccount();
        System.out.println("account = " + account);
    }

    @Test
    public void testDomainAvailable()
    {
        boolean account = new Connection("https://dev.cloudhub.io/", "cloudhub-rest", "Cl0udhub", true).on("lalalalala").available();
        System.out.println("account = " + account);
    }


    @Test
    @Ignore
    public void testCreateDomain()
    {
        Application application = new Application();
        String domain = "lalalalala5";
        application.setDomain(domain);
        application.setDescription("test");
        application.setWorkers(1);
        boolean available = new Connection("https://dev.cloudhub.io/", "cloudhub-rest", "Cl0udhub", true).on(domain).available();
        System.out.println("available = " + available);
        Application application1 = new Connection("https://dev.cloudhub.io/", "cloudhub-rest", "Cl0udhub", true).createApplication(application);
        System.out.println("application1 = " + application1);

        available = new Connection("https://dev.cloudhub.io/", "cloudhub-rest", "Cl0udhub", true).on(domain).available();
        System.out.println("available = " + available);
    }

    @Test
    @Ignore
    public void testStatus()
    {
        Application application = new Application();
        String domain = "lalalalala2";
        application.setDomain(domain);
        application.setDescription("test");
        application.setWorkers(1);
        Application.Status available = new Connection("https://dev.cloudhub.io/", "cloudhub-rest", "Cl0udhub", true).on(domain).status();
        System.out.println("status = " + available);

    }

    @Test
    @Ignore
    public void testGetAction(){
        Application cloudHubApplication = new Connection("https://dev.cloudhub.io/", "cloudhub-rest", "Cl0udhub", true).on("test").get();
        System.out.println("cloudHubApplication = " + cloudHubApplication);
    }

    @Test
    @Ignore
    public void testSupportedMuleVersions(){
        List<String> supportedApplications = new Connection("https://dev.cloudhub.io/", "cloudhub-rest", "Cl0udhub", true).getSupportedMuleVersions();
        System.out.println("supportedApplications = " + supportedApplications);

    }
}
