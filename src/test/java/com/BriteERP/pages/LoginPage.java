package com.BriteERP.pages;

import com.BriteERP.utilities.BrowserUtils;
import com.BriteERP.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.events.Event;

public class LoginPage {
    public LoginPage(){
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(id = "login")
    @CacheLookup
    public WebElement username;

    @FindBy(id = "password")
    public WebElement password;

    @FindBy(css = "[type='submit']")
    public WebElement submit;

    public void login(String username,String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.submit.click();
        WebElement title = Driver.get().findElement(By.cssSelector(".o_thread_title"));
        BrowserUtils.waitForVisibility(title,5);



    }
}