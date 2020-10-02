package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPassword {
	public WebDriver driver;
	
	private By email=By.cssSelector("#email");
	private By newPassword=By.xpath("//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/div[1]/form[1]/fieldset[1]/p[1]/button[1]/span[1]");
	
	public ForgotPassword(WebDriver driver) {
		this.driver=driver;	
	}

	public WebElement getEmail()
	{
		return driver.findElement(email);
	}
	
	public WebElement getNewPassword()
	{
		return driver.findElement(newPassword);
	}
}