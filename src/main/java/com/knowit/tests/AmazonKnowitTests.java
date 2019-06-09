package com.knowit.tests;

import static org.testng.AssertJUnit.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.knowit.pages.HomePage;
import com.knowit.pages.DepartmentPage;
import com.knowit.pages.ElectronicsPage;

public class AmazonKnowitTests extends BaseTest {

	public AmazonKnowitTests() throws IOException {
		super();

	}

	@Test(description = "Launch any driver and open https://www.amazon.com")
	public void launchSite() throws InterruptedException {

		// *************PAGE INSTANTIATIONS*************
		HomePage homePage = new HomePage(driver);

		log.info(" TC# 1 > Launch any driver and open https://www.amazon.com");
		log.debug("Check Site Response Mode");
		setSiteResponseMode();
		try {
			if (isItfullSite == true) {

				log.info("verify Home Page Titile in Web mode");
				homePage.verifyHomPageLogoText();

			} else {
				log.info("verify Home Page Titile in Mobile mode");
				homePage.verifyHomPageLogoText();
			}

			log.info("Add pass entry to the excel sheet TC# 1 > Launch any driver and open https://www.amazon.com");
			testresultdata.put("2", new Object[] { 1d, "navigate to site " + siteResponsiveMode,
					"site opens successfully in " + siteResponsiveMode, "Pass" });

		} catch (Exception e) {
			log.info("Add Fail entry to the excel sheet TC# 1 > Launch any driver and open https://www.amazon.com");
			assertTrue(false);
			testresultdata.put("2", new Object[] { 1d, "navigate to site " + siteResponsiveMode,
					"site opens successfully in " + siteResponsiveMode, "Fail" });
		}

	}

	@Test(description = "Navigate to the department page")
	public void opendepartmentPage() throws InterruptedException {

		// *************PAGE INSTANTIATIONS*************
		HomePage homePage = new HomePage(driver);
		DepartmentPage departmentPage = new DepartmentPage(driver);
		log.info(" TC# 2 > Select department as Electronics");
		try {
			if (isItfullSite == true) {

				log.debug("Navigate To Elctronics Page on Web");
				departmentPage.goToElctronicsPageonWeb(department);

			} else {
				try {
					log.debug("Navigate To Elctronics Page on Mobile");
					log.info("Click on Department");
					homePage.goToDepartmentPageOnMobile();

					log.info("Click on sumenu :" + department);
					departmentPage.goToElctronicsPageonWeb(department);

				} catch (Exception e) {
					log.debug("element exception : " + e);
				}

			}
			log.info("Add pass entry to the excel sheet TC# 2 > Select department as Electronics ");
			testresultdata.put("3", new Object[] { 2d, "Select department as Electronics in " + siteResponsiveMode,
					"Page Displayed " + siteResponsiveMode, "Pass" });

		} catch (Exception e) {
			assertTrue(false);
			log.info("Add fail entry to the excel sheet TC# 2 > Select department as Electronics ");
			testresultdata.put("3", new Object[] { 2d, "Select department as Electronics in " + siteResponsiveMode,
					"Page Displayed " + siteResponsiveMode, "Fail" });
		}

	}

	@Test(description = "Search for \"Lenovo T470 Laptop\"")
	public void searchProdcut() {
		// *************PAGE INSTANTIATIONS*************
		ElectronicsPage electronicsPage = new ElectronicsPage(driver);
		log.info(" TC# 3 > Search for given item : " + searchItem);
		try {
			if (isItfullSite == true) {
				try {
					log.info("clear search text");
					electronicsPage.clearSearchTextboxinWeb().sendItemtoSearchTextboxinWeb(searchItem)
							.clickonSearchIconboxinWeb();

					String searchResult = electronicsPage.readNumberofItemtsinWeb();
					log.info("searchResult : " + searchResult);
				} catch (Exception e) {
					assertTrue(false);
					log.debug("element exception : " + e);
				}

			} else {
				try {
					// mobile code
				} catch (Exception e) {
					log.debug("element exception : " + e);
				}
			}
			// add pass entry to the excel sheet
			log.info("Add pass entry to the excel sheet  TC# 3 > Search for given item :  " + searchItem);
			testresultdata.put("4", new Object[] { 3d, "Search for Prduct" + siteResponsiveMode,
					"Search for Prduct " + siteResponsiveMode, "Pass" });

		} catch (Exception e) {
			// add fail entry to the excel sheet
			log.info("add fail entry to the excel sheet TC# 3 > Search for given item :  " + searchItem + e);
			assertTrue(false);
			testresultdata.put("4", new Object[] { 3d, "Search for Prduct " + siteResponsiveMode,
					"Search for Prduct fail " + siteResponsiveMode, "Fail" });
		}

	}

	@Test(description = "Select Avg. Customer Review as 3 stars")
	public void selectAvgRatting() throws InterruptedException {
		ElectronicsPage electronicsPage = new ElectronicsPage(driver);
		log.info(" TC# 4 Select Avg. Customer Review as 3 stars");
		try {
			if (isItfullSite == true) {
				try {
					log.debug("Extarct all customer Review list and Total No of reviews list display : "
							+ electronicsPage.customerReviewlist().size());
					for (WebElement customerReviews : electronicsPage.customerReviewlist()) {
						log.debug("Search avg Customer Review from provided data i.e. : "
								+ customerReviews.getAttribute("class"));
						log.info("isProvidedCustomerReviewVisibleinWeb" + electronicsPage
								.isProvidedCustomerReviewVisibleinWeb(customerReviews, avgCustomerReview));
						if (electronicsPage.isProvidedCustomerReviewVisibleinWeb(customerReviews, avgCustomerReview)) {
							electronicsPage.clickoncustomerReviewsinWeb(customerReviews);
							break;
						} else {
							log.info("Please provide the valid Star raiting");
						}
					}
					Thread.sleep(1000);
				} catch (Exception e) {
					log.debug("element exception : " + e);
				}
			} else {
				try {
					// mobile code
				} catch (Exception e) {
					log.debug("element exception : " + e);
				}
			}
			// add pass entry to the excel sheet
			log.info("Add pass entry to the excel sheet TC# 4 Select Avg. Customer Review as 3 stars "
					+ avgCustomerReview);
			testresultdata.put("5",
					new Object[] { 4d, "Select Avg. Customer Review " + avgCustomerReview + siteResponsiveMode,
							"Able to search Products " + siteResponsiveMode, "Pass" });

		} catch (Exception e) {
			// add fail entry to the excel sheet
			assertTrue(false);
			log.info("Add fail entry to the excel sheet TC# 4 Select Avg. Customer Review as 3 stars "
					+ avgCustomerReview + e);
			testresultdata.put("5", new Object[] { 4d, "Select Avg. Customer Review as 3 stars " + siteResponsiveMode,
					"not able to search " + siteResponsiveMode, "Fail" });
		}
	}

	@Test(description = "Print Name and Price for all the filtered products including products which displays on pages 2,3… after navigating to those pages through pagination")
	public void printNameAndPrice() throws InterruptedException {
		// *************PAGE INSTANTIATIONS*************
		ElectronicsPage electronicsPage = new ElectronicsPage(driver);
		log.info(
				" TC# 4 > ) Print Name and Price for all the filtered products including products which displays on pages 2,3… after navigating to those pages through pagination");
		try {
			if (isItfullSite == true) {
				try {
					do {
						log.debug(electronicsPage.productNameList().size());
						for (WebElement productNames : electronicsPage.productNameList()) {
							String productName = productNames.getText();
							log.info("ProductName is :" + productName);

							String productprice = electronicsPage.productPriceinWeb();
							log.info("Price Details :" + productprice);
						}
						if (electronicsPage.ispaginationinWeb()) {
							boolean isNextButtonDisable = electronicsPage.isNextButtonDisableinWeb();
							log.debug("cehck value for isNextButtonDisable: " + isNextButtonDisable);
							if (!isNextButtonDisable) {
								log.info("Click on next in pagination link");
								electronicsPage.clickonNextButtoninWeb();
							} else {
								break;
							}
						}
					} while (electronicsPage.ispaginationinWeb());

				} catch (Exception e) {
					log.debug("pagination is not available and or element issue");
				}

			} else {
				try {
					driver.findElement(By.cssSelector("mobile")).click();
					// mobile code
				} catch (Exception e) {
					log.debug("element exception : " + e);
				}
			}
			// add pass entry to the excel sheet
			log.info(
					"Add Pass entry to the excel sheet TC# 4 > ) Print Name and Price for all the filtered products including products which displays on pages 2,3… after navigating to those pages through pagination");
			testresultdata.put("6",
					new Object[] { 5d, "Print Name and Price for all the filtered products " + siteResponsiveMode,
							"Able to Print Name and Price for all the filtered products  " + siteResponsiveMode,
							"Pass" });

		} catch (Exception e) {
			// add fail entry to the excel sheet
			log.info(
					"Add Fail entry to the excel sheet TC# 4 > ) Print Name and Price for all the filtered products including products which displays on pages 2,3… after navigating to those pages through pagination "
							+ e);
			assertTrue(false);
			testresultdata.put("6",
					new Object[] { 5d, "Print Name and Price for all the filtered products  " + siteResponsiveMode,
							"not able to Print Name and Price for all the filtered products  " + siteResponsiveMode,
							"Fail" });
		}
	}

	@Test(description = "Print the total number of products ")
	// *************PAGE INSTANTIATIONS*************
	public void printTotalNumberOfProducts() throws InterruptedException {
		ElectronicsPage electronicsPage = new ElectronicsPage(driver);
		log.info(" TC# 6 Print the total number of products filtered ");
		try {
			if (isItfullSite == true) {

				log.info("Total number of Product in Search after filter :" + electronicsPage.getNumberofProductsinWeb()
						+ searchItem);
			} else {
				try {
					// mobile code
				} catch (Exception e) {
					System.out.println("element exception : " + e);
				}
			}
			// add pass entry to the excel sheet
			log.info("Add Pass entry to the excel sheet TC# 6 Print the total number of products filtered for "
					+ searchItem);
			testresultdata.put("7", new Object[] { 6d, "Print the total number of products and " + siteResponsiveMode,
					"Able to Print the total number of products " + siteResponsiveMode, "Pass" });

		} catch (Exception e) {
			log.info(
					"Add Fail entry to the excel sheet TC# 6 and 7 Print the total number of products filtered and Find the Minimum price laptop and print its name and price "
							+ e);
			assertTrue(false);
			testresultdata.put("7", new Object[] { 6d,
					"Print the total number of products and Find  the Minimum price laptop and print its name and price "
							+ siteResponsiveMode,
					"not able Print the total number of products and Find  the Minimum price laptop and print its name and price "
							+ siteResponsiveMode,
					"Fail" });
		}
	}

	@Test(description = "Find  the Minimum price laptop and print its name and price")
	// *************PAGE INSTANTIATIONS*************
	public void printMiniPriceDetails() throws InterruptedException {
		ElectronicsPage electronicsPage = new ElectronicsPage(driver);
		log.info(" TC# 7 Find the Minimum price laptop and print its name and price");
		try {
			if (isItfullSite == true) {

				log.debug("Click on filter Drop Down and then Low To High Filter");
				electronicsPage.clickonFilterDropDowninWeb().clickonLowToHighFiterinWeb();

				log.info("MinPriceProductName" + electronicsPage.getMinPriceProductNameinWeb());

				log.info("Min Price Product Details :" + electronicsPage.getMinPriceProductDetailsinWeb());

			} else {
				try {
					// mobile code
				} catch (Exception e) {
					System.out.println("element exception : " + e);
				}

			}
			// add pass entry to the excel sheet
			log.info(
					"Add Pass entry to the excel sheet TC# 7 Find the Minimum price laptop and print its name and price");
			testresultdata.put("8",
					new Object[] { 7d,
							"Find  the Minimum price laptop and print its name and price " + siteResponsiveMode,
							"Able to Find  the Minimum price laptop and print its name and price " + siteResponsiveMode,
							"Pass" });

		} catch (Exception e) {
			// add fail entry to the excel sheet
			log.info(
					"Add Fail entry to the excel sheet TC# 7 Find the Minimum price laptop and print its name and price"
							+ e);
			assertTrue(false);
			testresultdata.put("8", new Object[] { 7d,
					"Find  the Minimum price laptop and print its name and price " + siteResponsiveMode,
					"Not able to Find  the Minimum price laptop and print its name and price" + siteResponsiveMode,
					"Fail" });
		}
	}
}
