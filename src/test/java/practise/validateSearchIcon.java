package practise;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.LandingPage;
import resources.base;

public class validateSearchIcon extends base{
	public WebDriver driver;
	
	public static Logger log =LogManager.getLogger(base.class.getName());
	 
	@BeforeTest
	public void initialize() throws IOException {
		driver =initializeDriver();
		driver.get(prop.getProperty("url"));
	}
	
	@Test
	public void validateSearch() throws IOException {
		LandingPage l=new LandingPage(driver);
	    Assert.assertTrue(l.getSearchIcon().isDisplayed());
	    log.info("Search Icon is displayed");
		System.out.println("Search Icon Test completed.");
	}
	
	@AfterTest
	public void teardown()
	{		
		driver.close();
	}
}