package com.BriteERP.pages;

import com.BriteERP.utilities.BrowserUtils;
import com.BriteERP.utilities.Driver;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.List;

public class CRMPage {

    Faker faker = new Faker();
    int count = 0;


    public CRMPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(css = "[accesskey='c']")
    @CacheLookup
    public WebElement createButton;


    //TODO creating opportunities
    public void createOpportunities(int num, String priorityTxt) {
        for (int i = 1; i <= num; i++) {
            count++;
            if (count == 3) {
                BrowserUtils.waitForClickablility(createButton, 5);
                BrowserUtils.clickWithJS(createButton);
                WebElement opportunityTitle = Driver.get().findElement(By.xpath("//table/tbody/tr//input"));
                opportunityTitle.sendKeys("Book");
                WebElement customer = Driver.get().findElement(By.xpath("//table/tbody/tr[2]//td[2]//input"));
                customer.sendKeys(faker.name().fullName());
                customer.sendKeys(Keys.ENTER);
                WebElement expectedRevenue = Driver.get().findElement(By.xpath("//table/tbody/tr[3]//td[2]//input"));
                expectedRevenue.clear();
                expectedRevenue.sendKeys("500.00");
                WebElement priority = Driver.get().findElement(By.cssSelector(".modal-content a[href='#'][title='" + priorityTxt + "']"));
                priority.click();
                WebElement create = Driver.get().findElement(By.cssSelector(".modal-footer button[name='close_dialog']"));
                BrowserUtils.waitForClickablility(create, 5);
                create.click();
                BrowserUtils.waitFor(2);
            } else {
                BrowserUtils.clickWithJS(createButton);
                WebElement opportunityTitle = Driver.get().findElement(By.xpath("//table/tbody/tr//input"));
                opportunityTitle.sendKeys(faker.book().title());
                WebElement customer = Driver.get().findElement(By.xpath("//table/tbody/tr[2]//td[2]//input"));
                customer.sendKeys(faker.name().fullName());
                customer.sendKeys(Keys.ENTER);
                WebElement expectedRevenue = Driver.get().findElement(By.xpath("//table/tbody/tr[3]//td[2]//input"));
                expectedRevenue.clear();
                expectedRevenue.sendKeys(faker.commerce().price());
                WebElement priority = Driver.get().findElement(By.cssSelector(".modal-content a[href='#'][title='" + priorityTxt + "']"));
                priority.click();
                WebElement create = Driver.get().findElement(By.cssSelector(".modal-footer button[name='close_dialog']"));
                BrowserUtils.waitForClickablility(create, 5);
                create.click();
                BrowserUtils.waitFor(2);
            }
        }
    }

    //TODO switch buttons right  up corner:kanban,list,pivot..
    public WebElement switchButton(String name) {
        WebElement button = Driver.get().findElement(By.xpath("//button[contains(@data-view-type,'" + name + "')]"));
        return button;
    }

    //TODO choose an element from table body
    public WebElement pipelineTableBody(int num1, int num2) {
        WebElement elementFromTable = Driver.get().findElement(By.xpath("//table/tbody/tr[" + num1 + "]/td[" + num2 + "]"));
        return elementFromTable;
    }


    //TODO new button> drop down
    public WebElement newButtonDropDown(String name) {
        WebElement dropDown = Driver.get().findElement(By.xpath("//a[@href='#' and contains(text(),'" + name + "')]"));
        return dropDown;
    }


    //TODO choose the number of column:
    public List<WebElement> column(String idx) {
        List<WebElement> el = Driver.get().findElements(By.xpath("//table/tbody/tr/td[" + idx + "]"));
        return el;
    }

    //TODO get book particular value from the table method
    public String getBookPrice(int column) {
        String book = "";
        String bookPrice = "";
        int count = 0;
        List<WebElement> bookName = Driver.get().findElements(By.xpath("//table//tbody//tr//td["+column+"]"));
        for (WebElement el : bookName) {
            count++;
            if (el.getText().equals("Book")){
                book = el.getText();
                break;
            }
        }
       bookPrice = pipelineTableBody(count,2).getText();
        return bookPrice;
    }
}
