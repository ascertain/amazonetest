package com.knowit.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElectronicsPage extends BasePage{

	public ElectronicsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

    //*********Page Variables*********
    String baseURL = "http://www.n11.com/";
    String logoAttributeClassName = "aria-label";
    String logoExpectedText = "Amazon";
    
    //*********Web Elements*********
    By wlSerchText = By.cssSelector(obj.getProperty("wlSerchText"));
    By wlSerchIcon = By.cssSelector(obj.getProperty("wlSerchIcon"));
    By wlsearchResultText = By.cssSelector(obj.getProperty("wlsearchResultText"));
    By wlcustomerReviewlist = By.cssSelector(obj.getProperty("wlcustomerReviewlist"));
    By wlPagination = By.cssSelector(obj.getProperty("wlPagination"));
    By wlProductNameList = By.cssSelector(obj.getProperty("wlProductNameList"));
    By wlProductprice = By.cssSelector(obj.getProperty("wlProductprice"));
    By wlNextButtonDisable = By.cssSelector(obj.getProperty("wlNextButtonDisable"));
    By wlNextButton = By.cssSelector(obj.getProperty("wlNextButton"));
    By wlTotalNumbProductsDetails = By.cssSelector(obj.getProperty("wlTotalNumbProductsDetails"));
    By wlFilterDropDown = By.cssSelector(obj.getProperty("wlFilterDropDown"));
    By wlLowToHighFiter = By.cssSelector(obj.getProperty("wlLowToHighFiter"));
    By wlMinPriceProductName = By.cssSelector(obj.getProperty("wlMinPriceProductName"));
    By wlMinPriceProductDetails = By.cssSelector(obj.getProperty("wlMinPriceProductDetails"));
    
 
    //*********Page Methods*********
    //clear Search Text box in Web
    public ElectronicsPage clearSearchTextboxinWeb (){
        clearText(wlSerchText);
        return this;
    }
    
    //Send keys to Search Text box in Web
    public ElectronicsPage sendItemtoSearchTextboxinWeb (String searchText){
        writeText(wlSerchText, searchText);
        return this;
    }
	
    //submit Search icon in web
    public ElectronicsPage clickonSearchIconboxinWeb (){
        submit(wlSerchIcon);
        return this;
    }
    
    // Read text after Item Searched in web
    public String readNumberofItemtsinWeb (){
        return readText(wlsearchResultText);
    }
    
    //Customer Review list in web
    public List<WebElement> customerReviewlist(){
    	isElementPresent(wlcustomerReviewlist);
    	return driver.findElements(By.cssSelector(obj.getProperty("wlcustomerReviewlist")));
    }
    
    //is pagination true in Web
    public boolean isProvidedCustomerReviewVisibleinWeb (WebElement customerReviews, String avgCustomerReview){
        return customerReviews.getAttribute("class").contains(avgCustomerReview.substring(0, 1));
    }
    
    //submit Search icon in web
    public void clickoncustomerReviewsinWeb (WebElement customerReviews){
    	jse.executeScript("arguments[0].click();", customerReviews);
    }
    
    //is pagination true in Web
    public boolean ispaginationinWeb (){
        return isElementPresent(wlPagination);
    }
    
    //product Name List in Web created a method because of element needs to enable - we can do it by property instead also :  public List<WebElement> productNameList =  driver.findElements(By.cssSelector(obj.getProperty("wlProductNameList")));
    public List<WebElement> productNameList(){
    	isElementPresent(wlProductNameList);
    	return driver.findElements(By.cssSelector(obj.getProperty("wlProductNameList")));
    }
       
    // Read text product price in web
    public String productPriceinWeb (){
        return readText(wlProductprice);
    }
    
    //is NextButton Disable in Web
    public boolean isNextButtonDisableinWeb (){
    	isElementPresent(wlNextButtonDisable);
    	return driver.findElement(By.cssSelector(obj.getProperty("wlNextButtonDisable")))
		.getAttribute("class").contains("disabled");
    }
    
    //click on Pagination Next Button in Web
    public ElectronicsPage clickonNextButtoninWeb (){
        click(wlNextButton);
        return this;
    }
    
    // Read text for total number of products after search
    public String getNumberofProductsinWeb (){
        return readText(wlTotalNumbProductsDetails);   
    }
    
    //click on Filter DropDown in Web
    public ElectronicsPage clickonFilterDropDowninWeb (){
        click(wlFilterDropDown);
        return this;
    }
    
    //click on Low To High Fiter in Web
    public ElectronicsPage clickonLowToHighFiterinWeb (){
        click(wlLowToHighFiter);
        return this;
    }
    
    // Read text for MinPrice ProductName Low To High Filter
    public String getMinPriceProductNameinWeb (){
        return readText(wlMinPriceProductName);   
    }
    
    // Read text for MinPrice Product price and details after Low To High Filter
    public String getMinPriceProductDetailsinWeb (){
        return readText(wlMinPriceProductDetails);   
    }
}
