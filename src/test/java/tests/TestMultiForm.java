package tests;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import multiformpages.FormInterestsPage;
import multiformpages.FormPaymentPage;
import multiformpages.FormProfilePage;

public class TestMultiForm {
	
	WebDriver driver;
	
	FormInterestsPage objFormInterestsPage;
	
	FormPaymentPage objFormPaymentPage;
	
	FormProfilePage objFormProfilePage;
	

  @BeforeMethod
  @Parameters("browser")
  public void beforeMethod(String browser) throws Exception {
	  if(browser.equalsIgnoreCase("firefox")) {
		  driver = new FirefoxDriver();
	  } else if (browser.equalsIgnoreCase("chrome")) {
		  driver = new ChromeDriver();
	  }else if (browser.equalsIgnoreCase("ie")) {
		  driver = new InternetExplorerDriver();
	  }else {
		  throw new Exception("Browser is not correct");
	  }
	  
	  // Maximize the browser size
	  driver.manage().window().maximize();
	  
	  // Navigate to the http://www.globalsqa.com/angularJs-protractor/Multiform/#/form/profile
	  driver.get("http://www.globalsqa.com/angularJs-protractor/Multiform/#/form/profile");
	  
	  // Wait 10 seconds for driver
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @AfterMethod
  public void afterMethod() {
	  // Close browser
	  driver.quit();
  }

  @BeforeClass  
  @Parameters("browser")
  public void beforeClass(String browser) throws Exception {
	  if(browser.equalsIgnoreCase("firefox")) {
		  System.setProperty("webdriver.gecko.driver", "./src/test/resources/drivers/geckodriver_v0.11.1.exe");
	  }else if(browser.equalsIgnoreCase("chrome")) {
		  System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver_2.27.exe");
	  }else if(browser.equalsIgnoreCase("ie")){
		  System.setProperty("webdriver.ie.driver", "./src/test/resources/drivers/IEDriverServer_3.0.0.exe");
	  }else{
		  throw new Exception("Browser is not correct");
	  }
	  
  }
  
  @Test(invocationCount=6)
  public void testMultiForm() {
	 	  
	  // Create Profile Page object
	  objFormProfilePage = new FormProfilePage(driver);
	  
	  WebDriverWait wait = new WebDriverWait(driver, 5);
	  wait.until(ExpectedConditions.elementToBeClickable(objFormProfilePage.getNextSection()));
	  
	  // Set user name
	  objFormProfilePage.setUserName("Janek");
	  
	  // Set user email
	  objFormProfilePage.setUserEmail("Bocian");
	  
	  // Go to next section "Interests"
	  objFormProfilePage.clickNextSection();
	  
	  // Create Interests Page object
	  objFormInterestsPage = new FormInterestsPage(driver);
	  
	  wait.until(ExpectedConditions.elementToBeClickable(objFormInterestsPage.getNextSection()));
	  
	  // Click playstation button
	  objFormInterestsPage.clickPlaystation();
	  
	  // Go to next section "Payment"
	  objFormInterestsPage.clickNextSection();
	  
	  // Create Payment Page object
	  objFormPaymentPage = new FormPaymentPage(driver);
	  
	  wait.until(ExpectedConditions.elementToBeClickable(objFormPaymentPage.getSubmit()));
	  
	  // Click submit button
	  objFormPaymentPage.clickSubmit();
	  	 
	  wait.until(ExpectedConditions.alertIsPresent());
	  	  
	  // Verify alert text
	  Assert.assertTrue(objFormPaymentPage.getAlertText().contains("awesome!"));

  }

}
