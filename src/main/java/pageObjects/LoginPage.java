package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	public WebDriver driver;
	
	private By email=By.xpath("//input[@id='email']");
	private By password=By.cssSelector("#passwd");
	private By login=By.xpath("//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/form[1]/div[1]/p[2]/button[1]/span[1]");
	private By forgotPassword = By.linkText("Forgot your password?");
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;		
	}

	public ForgotPassword forgotPassword()
	{
		driver.findElement(forgotPassword).click();
		return new ForgotPassword(driver);
	}
	
	public WebElement getEmail()
	{
		return driver.findElement(email);
	}
	
	public WebElement getPassword()
	{
		return driver.findElement(password);
	}
	
	public WebElement getLogin()
	{
		return driver.findElement(login);
	}
}