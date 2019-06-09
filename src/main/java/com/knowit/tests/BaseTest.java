package com.knowit.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

	public WebDriver driver;
	private String baseUrl;
	protected String department;
	protected String searchItem;
	protected String avgCustomerReview;
	protected Boolean isItfullSite;
	protected String siteResponsiveMode;
	public Properties obj = new Properties();
	public JavascriptExecutor js;
	final static Logger log = Logger.getLogger(BaseTest.class);
	// define an Excel Work Book
	HSSFWorkbook workbook;
	// define an Excel Work sheet
	HSSFSheet sheet;
	// define a test result data object
	Map<String, Object[]> testresultdata;

	public BaseTest() throws IOException {
		// Set Logger configuration
		PropertyConfigurator.configure(System.getProperty("user.dir") + "\\resource\\log4j.properties");
		org.apache.log4j.Logger.getRootLogger().setLevel(Level.INFO);

		// Load the properties File
		FileInputStream objfile = new FileInputStream(System.getProperty("user.dir") + "\\or.properties");
		obj.load(objfile);
	}

	@BeforeClass(alwaysRun = true)
	public void setupBeforeSuite(ITestContext context) {
		String strCurrentLine = null;
		try {
			String filePath = "resource/Knowit_TestData.txt";
			strCurrentLine = readLineByLine(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			strCurrentLine = strCurrentLine.replaceAll("<.*?>", "");
			strCurrentLine = strCurrentLine.replaceAll("<.*?>|\u00a0", "");
			log.debug(strCurrentLine);
			JSONObject obj = new JSONObject(strCurrentLine);
			baseUrl = obj.getString("url");
			log.debug(baseUrl);
			department = obj.getJSONObject("data").getString("department");
			log.debug(department);
			searchItem = obj.getJSONObject("data").getString("item");
			log.debug(searchItem);
			avgCustomerReview = obj.getJSONObject("data").getString("avgCustomerReview");
			log.debug(avgCustomerReview);
			// log.info(avgCustomerReview.substring(0,1));
		} catch (Exception err) {
			log.debug("Error" + err.toString());
		}

		// create a new work book
		workbook = new HSSFWorkbook();
		// create a new work sheet
		sheet = workbook.createSheet("Test Result");
		testresultdata = new LinkedHashMap<String, Object[]>();
		// add test result excel file column header
		// write the header in the first row
		testresultdata.put("1", new Object[] { "Test Step Id", "Action", "Expected Result", "Actual Result" });

		try {
			System.setProperty("webdriver.gecko.driver", "resource/geckodriver.exe");
			// Chrome driver
			System.setProperty("webdriver.chrome.driver", "resource/chromedriver.exe");
			// IE driver
			System.setProperty("webdriver.ie.driver", "resource/IEDriverServer.exe");

			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(baseUrl + "/");
			js = (JavascriptExecutor) driver;
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			throw new IllegalStateException("Can't start Web Driver", e);
		}

	}

	@AfterClass
	public void setupAfterSuite() {
		// write excel file and file name is TestResult.xls
		Set<String> keyset = testresultdata.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = testresultdata.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof Date)
					cell.setCellValue((Date) obj);
				else if (obj instanceof Boolean)
					cell.setCellValue((Boolean) obj);
				else if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Double)
					cell.setCellValue((Double) obj);
			}
		}
		try {
			FileOutputStream out = new FileOutputStream(new File("TestResult.xls"));
			workbook.write(out);
			out.close();
			System.out.println("Excel written successfully..");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// close the browser
		driver.close();
		driver.quit();
	}

	// Created in void method : writing the test results with responsive information
	// script run in Full site or in Mobile Site
	protected void setSiteResponseMode() throws InterruptedException {
		try {
			if (isElementPresent(By.xpath(obj.getProperty("wlfullSiteDropdown")))) {
				isItfullSite = true;
				siteResponsiveMode = "FullSite";
			} else {
				isItfullSite = false;
				siteResponsiveMode = "MobileSite";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	protected void ElementPresent(By by) throws InterruptedException {
		for (int second = 0;; second++) {
			if (second >= 60)
				Assert.fail("timeout");
			try {
				if (isElementPresent(by))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
	}

	private static String readLineByLine(String filePath) {
		StringBuilder contentBuilder = new StringBuilder();

		try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contentBuilder.toString();
	}

}