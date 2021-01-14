package resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.lennox.automation.common.ExcelUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public WebDriver driver;

	public WebDriver initializeDriver() {
		String browserName = ExcelUtils.values.get("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().version("87.0.4280.88").setup();
			ChromeOptions chromeoptions = new ChromeOptions();
			chromeoptions.addArguments("start-maximized");
			driver = new ChromeDriver(chromeoptions);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().version("v0.9.0").setup();
			FirefoxOptions firefoxoptions = new FirefoxOptions();
			firefoxoptions.addArguments("start-maximized");
			driver = new FirefoxDriver(firefoxoptions);
		} else if (browserName.equalsIgnoreCase("IE")) {
		}
		return driver;
	}
}