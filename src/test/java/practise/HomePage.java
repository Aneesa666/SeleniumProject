package practise;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.ForgotPassword;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.base;

public class HomePage extends base {
	public WebDriver driver;
	
	public static Logger log =LogManager.getLogger(base.class.getName());
	@BeforeTest
	public void initialize() throws IOException
	{
		 driver =initializeDriver();

	}
	
	@Test(dataProvider="getData")
	public void basePageNavigation(String Username, String Password, String text) throws IOException
	{
		driver.get(prop.getProperty("url"));
		LandingPage l=new LandingPage(driver);
		LoginPage lp=l.getLogin(); 
		lp.getEmail().sendKeys(Username);
		lp.getPassword().sendKeys(Password);

		log.info(text);
		
		lp.getLogin().click();
		ForgotPassword fp= lp.forgotPassword();
		fp.getEmail().sendKeys("xxx");
		fp.getNewPassword().click();		
	}

	@AfterTest
	public void teardown()
	{
		driver.close();		
	}

	@DataProvider
	public Object[][] getData()
	{
		//User name and Passwords for login check
		Object[][] data=new Object[2][3];
		
		data[0][0]="user1@example.com";
		data[0][1]="123456";
		data[0][2]="Logging as User One";
		
		data[1][0]="user2@example.com";
		data[1][1]="456788";
		data[1][2]= "Logging as User Two";
		
		return data;	
	}
}