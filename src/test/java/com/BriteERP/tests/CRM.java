package com.BriteERP.tests;

import com.BriteERP.pages.CRMPage;
import com.BriteERP.pages.DashboardPage;
import com.BriteERP.pages.LoginPage;
import com.BriteERP.utilities.BrowserUtils;
import com.BriteERP.utilities.ConfigurationReader;
import com.BriteERP.utilities.Driver;
import com.BriteERP.utilities.TestBase;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CRM extends TestBase {
    Faker faker = new Faker();
    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();
    CRMPage crmPage = new CRMPage();





    @Test
    public void test1(){
        extentLogger = report.createTest("Verifying that second opportunity’ Expected Revenue value on the Pivot " +
                "                                   board should be the same as the Expected revenue column value on the list board.");
        String username = ConfigurationReader.get("SalesManagerUsername");
        String password = ConfigurationReader.get("SalesManagerPassword");
        String url = ConfigurationReader.get("url");
        extentLogger.info("Entering login page");
        Driver.get().get(url);
        extentLogger.info("Entering Sales Manager credentials");
        loginPage.login(username,password);
        dashboardPage.dashboardLine("CRM").click();
        extentLogger.info("Creating three opportunities with different names, book names, and prices");
        crmPage.createOpportunities(3,"Very High");
        BrowserUtils.waitForClickablility(crmPage.switchButton("pivot"),5);
        crmPage.switchButton("pivot").click();
        BrowserUtils.waitFor(2);
        crmPage.pipelineTableBody(2,1).click();
        crmPage.newButtonDropDown("Opportunity").click();
        BrowserUtils.waitFor(1);
        extentLogger.info("After created opportunities, we are collecting expected book price");
        String  expectedBookPrice = crmPage.getBookPrice(1,2,"Book");
        BrowserUtils.waitForClickablility(crmPage.switchButton("list"),5);
        crmPage.switchButton("list").click();
        extentLogger.info("Comparing expected book price with price appeared on the pipeline table");
        BrowserUtils.waitForPageToLoad(2);
        String actualBookPrice = crmPage.pipelineTableBody(1,9).getText();
        System.out.println("Expected book price: "+expectedBookPrice);
        System.out.println("Actual book price: "+actualBookPrice);
        Assert.assertEquals(expectedBookPrice,actualBookPrice,"Don't match");
        extentLogger.info("TEST PASSED");

    }

}
