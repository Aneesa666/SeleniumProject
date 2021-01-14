package com.lennox.automation.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.lennox.automation.common.ExcelUtils;
import com.lennox.automation.pageObjects.AddLeadPage;
import com.lennox.automation.pageObjects.HomePage;
import com.lennox.automation.pageObjects.LandingPage;
import com.lennox.automation.pageObjects.LoginPage;
import com.lennox.automation.pageObjects.SearchLeadPage;

import junit.framework.Assert;
import resources.Base;

public class SaveLead extends Base {
	public WebDriver driver;
	private Robot robot;
	private Select select;
	private Actions actions;
	private static final String TEST_DATA = "TestData";

	public static final Logger log = LogManager.getLogger(Base.class.getName());

	@BeforeTest
	public void initialize() throws IOException {
		ExcelUtils.locatorProps();
		ExcelUtils.readBrowserProps();
		ExcelUtils.readEnvProps();
		ExcelUtils.testDataProps();
		driver = initializeDriver();
	}

	@Test
	public void navigateToBaseUrl() throws IOException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(ExcelUtils.values.get("url"));
		LandingPage landingPage = new LandingPage(driver);
		LoginPage loginPage = landingPage.getLogin();
		loginPage.getEmail().sendKeys(ExcelUtils.values.get("userid"));
		loginPage.getPassword().sendKeys(ExcelUtils.values.get("password"));
		loginPage.clickSignIn().click();
		log.info("Successfully navigated to Home Page after login.");
		Assert.assertEquals(driver.getTitle(), "Homepage | LennoxPROs.com");
	}

	@Test
	public void navigateToSearchLeadPage() throws IOException, InterruptedException {
		actions = new Actions(driver);
		HomePage homePage = new HomePage(driver);
		homePage.clickMenu().click();
		homePage.clicksalesTool().click();
		homePage.clickbuildAProposal().click();
		Thread.sleep(5000);
		actions.moveToElement(homePage.clickselectLead()).click().build().perform();
	}

	@Test
	public void saveLead() throws IOException, InterruptedException, AWTException {
		robot = new Robot();
		actions = new Actions(driver);
		AddLeadPage addLeadPage = new AddLeadPage(driver);
		SearchLeadPage searchLeadPage = new SearchLeadPage(driver);
		Assert.assertTrue(searchLeadPage.checkSearchBar().isDisplayed());
		log.info("Successfully navigated to Search Leads Page.");
		searchLeadPage.clickAddLead().click();
		for (int i = 0; i < ExcelUtils.testDataValues.size(); i++) {
			addLeadPage.getFirstName().sendKeys(ExcelUtils.testDataValues.get(TEST_DATA + i).getFirstName());
			addLeadPage.getlastName().sendKeys(ExcelUtils.testDataValues.get(TEST_DATA + i).getLastName());
			addLeadPage.getEmailID().sendKeys(ExcelUtils.testDataValues.get(TEST_DATA + i).getEmail());
			addLeadPage.getPhoneNo().sendKeys(ExcelUtils.testDataValues.get(TEST_DATA + i).getPhone());
			addLeadPage.getSchedulingReqDate().click();
			performDateSelectFunction(i, addLeadPage);
			Select dropTime = new Select(addLeadPage.getSchedulingReqTime());
			dropTime.selectByVisibleText(ExcelUtils.testDataValues.get(TEST_DATA + i).getRequestTime());
			if (StringUtils.isNotEmpty(ExcelUtils.testDataValues.get(TEST_DATA + i).getDocumentPath())) {
				addLeadPage.clickAddDocument().click();
				Thread.sleep(5000);
				select = new Select(addLeadPage.clickSelectOption());
				select.selectByVisibleText(ExcelUtils.testDataValues.get(TEST_DATA + i).getOption());
				actions.moveToElement(addLeadPage.clickSelectFile()).click().build().perform();
				robot.setAutoDelay(2000);
				StringSelection data = new StringSelection(
						ExcelUtils.testDataValues.get("TestData" + i).getDocumentPath());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(data, null);
				performRobotOperation(robot);
				actions.moveToElement(addLeadPage.clickaddToLead()).click().build().perform();
			}
			if (StringUtils.isNotEmpty(ExcelUtils.testDataValues.get(TEST_DATA + i).getImagePath())) {
				actions.moveToElement(addLeadPage.clickAddImage()).click().build().perform();
				StringSelection data = new StringSelection(ExcelUtils.testDataValues.get(TEST_DATA + i).getImagePath());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(data, null);
				performRobotOperation(robot);
			}

			addLeadPage.getSaveLead().click();
			Assert.assertEquals(addLeadPage.getSuccessMsg().getText(), "Lead Saved Successfully");
			log.info("Lead got saved successfully.");
			if (i < ExcelUtils.testDataValues.size() - 1) {
				addLeadPage.getNavLink().click();
				searchLeadPage.clickAddLead().click();
			}
		}
	}

	private void performRobotOperation(Robot robot2) {
		robot.setAutoDelay(1000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.setAutoDelay(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	private void performDateSelectFunction(Integer i, AddLeadPage addLeadPage) {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		String reqDate = ExcelUtils.testDataValues.get(TEST_DATA + i).getRequestDate();
		String reqDay = reqDate.split("/")[0];
		int reqMonth = Integer.parseInt(reqDate.split("/")[1]);
		int reqYear = Integer.parseInt(reqDate.split("/")[2]);
		int totalClick = (reqYear - currentYear) * 12 + (reqMonth - currentMonth);
		for (int j = 0; j < totalClick; j++) {
			addLeadPage.getSchedulingDateNav().click();
		}
		List<WebElement> dateList = addLeadPage.getSchedulingDatePick();
		for (int k = 0; k < dateList.size(); k++) {
			if (dateList.get(k).getText().equals(reqDay)) {
				dateList.get(k).click();
			}
		}
	}

	@AfterTest
	public void teardown() {
		driver.close();
	}
}