package com.BriteERP.pages;

import com.BriteERP.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
    public DashboardPage(){
        PageFactory.initElements(Driver.get(),this);
    }

    public WebElement dashboardLine(String txt){
        WebElement module = Driver.get().findElement(By.xpath("//span[@class='oe_menu_text' and contains(text(),'"+txt+"')]"));
        return module;
    }






}
