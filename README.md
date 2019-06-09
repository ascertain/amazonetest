•	Created a Selenium Webdriver Maven project in Eclipse
o	Started by launching the Eclipse IDE and create a new Maven project.
o	Created a <resource> folder under the project and copy all required files (chrome driver, Knowit_TestData.txt, log4j.properties etc..)
 
•	Generate <TestNG.XML>, <POM.XML>, <log4j.properties> and <or.properties> files
o	Generate TestNG.XML as per given test case 
 
o	Added a required Maven dependencies(selenium, Testng, log4j, POI, org.json etc..) in <POM.XML>
 
o	Added log4j.properties that will help in log the information(Info, debug, warn, error)
 
o	Create or.properties : use the properties file to identify webelements using locator values
 
•	Define multiple <@Test> methods to perform the given tasks 
o	Setup the precondition by using Before annotation <@BeforeClass>
o	Define multiple methods to perform the given tasks by using Test annotation  <@Test> 
o	Write a method by using After annotation <@AfterClass>  to save Selenium WebDriver TestNG report to Excel file
•	Execute test suite
o	Via Executable Batch file
o	Via Maven
o	Via TestNG
 
 

•	Results:
o	After execution, when you would refresh the project/directory, you’ll see the <TestResult.xls> file appearing in the project folder. This file contains all the steps and their pass/fail status.
 
o	TestNG generated web report
 
Used Page Object Model test automation framework pattern. 
The POM framework consists of 2 basic abstraction layer:
Tests: It comprises of our test classes.
Pages: It comprises of page classes.
 
Let’s start with the BaseTest class.
BaseTest class contains all common functionalities and variables of test classes and all test classes extend this BaseTest class. This is one of the main features of Object Oriented Design (OOD) “Inheritance“. 
In this class, the driver variable declared. This is used by all test classes. 
Also, @BeforeClass: setup method 
o	-create a ChromeDriver and maximize the browser.
o	-read data from Knowit_TestData.txt and use org.json to extract the data
and @AfterClass: a  code that save Selenium WebDriver TestNG report to Excel file and : teardown method . Again, all test classes use these methods.
In test classes:
Instantiate the required Page classes and Use the appropriate page methods, Do the assertions and implement scenarios 
 
Now, in the BasePage class. BasePage class contains the common methods of all page classes such as click, writeText, readText, assertEquals etc
 

Then create different page classes. Below points declare in class as per requirement
o	Constructor 
o	Page Variables
o	Web Elements
o	Page Methods
We will have methods, one of them opens the Current page and the other one Navigate to the Other page.
 

