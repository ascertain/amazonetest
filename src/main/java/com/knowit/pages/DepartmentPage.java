package com.knowit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

 
public class DepartmentPage extends BasePage{
 
    //*********Constructor*********
    public DepartmentPage(WebDriver driver) {
        super(driver);
    }
 
    //*********Web Elements*********
    By wlDepartmentMenu = By.cssSelector(obj.getProperty("wlDepartmentMenu"));
 
    //*********Page Methods*********    
    //Navigate to ElctronicsPage on web
    public ElectronicsPage goToElctronicsPageonWeb (String departmentText){
    	WebElement menu = driver.findElement(wlDepartmentMenu);
		//log.debug("Find submenu Electronics");
		String subwMenuXpath = obj.getProperty("wlSubMenuXpath1") + departmentText
				+ obj.getProperty("wlSubMenuXpath2");
		WebElement subMenu = driver.findElement(By.xpath(subwMenuXpath));

		Actions action = new Actions(driver);

		action.moveToElement(menu).perform();

		action.click(subMenu).perform();
        return new ElectronicsPage(driver);
    }
 
    //Go to Electronics Page on mobile
    public ElectronicsPage goToDepartmentPagennMobile (String departmentText){
    	String submMenuXpath = obj.getProperty("mlSubMenuXpath1") + departmentText
				+ obj.getProperty("mlSubMenuXpath2");
		
        click(By.xpath(submMenuXpath));
        return new ElectronicsPage(driver);
    }
    
}