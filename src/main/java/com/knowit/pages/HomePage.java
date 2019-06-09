package com.knowit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

 
public class HomePage extends BasePage {
 
    //*********Constructor*********
    public HomePage (WebDriver driver) {
        super(driver);
    }
 
    //*********Page Variables*********
    String logoAttributeClassName = "aria-label";
    String logoExpectedText = "Amazon";
    
    //*********Web Elements*********
    By mlDepartmentMenu = By.cssSelector(obj.getProperty("mlDepartmentMenu"));
    By logoTextvalue = By.cssSelector(obj.getProperty("logo"));
 
    //*********Page Methods*********
    //Read Logo Attribute Text
    private String readHomPageLogoText(String attribute)  {
         return readAttributeText(logoTextvalue, attribute);
    }
 
    //Verify Logo Attribute Text
    public HomePage verifyHomPageLogoText()  {
         assertEquals(readHomPageLogoText(logoAttributeClassName), logoExpectedText);
        return this;
    }
 
    
    //Go to Department Page on mobile
    public DepartmentPage goToDepartmentPageOnMobile (){
        click(mlDepartmentMenu);
        return new DepartmentPage(driver);
    }
}