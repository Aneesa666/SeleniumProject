package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {

	public WebDriver driver;
	
	private By signin=By.xpath("//a[contains(text(),'Sign in')]");
	private By title=By.xpath("//header/div[2]/div[1]/div[1]/nav[1]/div[2]/a[1]");
	private By searchicon=By.xpath("//header/div[3]/div[1]/div[1]/div[2]/form[1]/button[1]");
			
	public LandingPage(WebDriver driver) {
		this.driver=driver;
	}

	public LoginPage getLogin() {
		 driver.findElement(signin).click();
		 LoginPage lp=new LoginPage(driver);
		 return lp; 
	}
	
	public WebElement getSearchIcon()
	{
		return driver.findElement(searchicon);
	}
	
	public WebElement getTitle()
	{
		return driver.findElement(title);
	}	
}