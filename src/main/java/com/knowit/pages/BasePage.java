package com.knowit.pages;
 
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

 
public class BasePage {
    public WebDriver driver;
    public JavascriptExecutor jse;
    //public WebDriverWait wait;
    public Properties obj = new Properties();
	    
    //Constructor
    public BasePage (WebDriver driver) {
        this.driver = driver;
        jse = (JavascriptExecutor) driver;
        try {
        	FileInputStream objfile = new FileInputStream(System.getProperty("user.dir") + "\\or.properties");
			obj.load(objfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    //Wait Wrapper Method
    public void waitVisibility(By elementBy) {
		for (int second = 0;; second++) {
			if (second >= 60)
				Assert.fail("timeout");
			try {
				if (isElementPresent(elementBy))
					break;
			} catch (Exception e) {
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
 
    //Click Method
    public void click (By elementBy) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).click();
    }
    
  //Submit Method
    public void submit (By elementBy) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).submit();
    }
 
  //clear Text
    public void clearText (By elementBy) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).clear();
    }
    
    //Write Text
    public void writeText (By elementBy, String text) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).sendKeys(text);
    }
 
    //Read Text
    public String readText (By elementBy) {
        waitVisibility(elementBy);
        return driver.findElement(elementBy).getText();
    }
    
    //Read Attribute Text
    public String readAttributeText (By elementBy, String attribute) {
        waitVisibility(elementBy);
        return driver.findElement(elementBy).getAttribute(attribute);
    }
    
    //Assert
    public void assertEquals (By elementBy, String expectedText) {
        waitVisibility(elementBy);
        Assert.assertEquals(readText(elementBy), expectedText);
 
    }
    
    //Assert with Strings
    public void assertEquals (String actualText, String expectedText) {
        Assert.assertEquals(actualText, expectedText);
    }
    
    //isElementPresent
    public boolean isElementPresent(By elementBy) {
		try {
			driver.findElement(elementBy);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

    //ElementPresent
    public void ElementPresent(By elementBy) throws InterruptedException {
		for (int second = 0;; second++) {
			if (second >= 60)
				Assert.fail("timeout");
			try {
				if (isElementPresent(elementBy))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
	}
 
}