package Tests;


import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.CasePage;
import Pages.CustomerCasesManager;
import Pages.SelectCaseRegisterType;
import Pages.setConexion;

public class CustomerCareCaseResolutionTime extends TestBase {

	private WebDriver driver;

	/*@BeforeClass
	public void init() throws Exception
	{
		this.driver = setConexion.setupEze();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		login(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}*/
	
	@BeforeMethod
	public void mainSetup() {
		this.driver = setConexion.setupEze();	
		login(driver);
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		if (!driver.getCurrentUrl().toString().equals("https://cs14.salesforce.com/console")){
			driver.findElement(By.id("tsidLabel")).click();
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.findElement(By.xpath("//a[@href=\"/console?tsid=02uc0000000D6Hd\"]")).click();
		}
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> mainTabs = driver.findElements(By.className("x-tab-strip-close"));
		for (WebElement e : mainTabs) {
		try {((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);} catch (org.openqa.selenium.StaleElementReferenceException b) {}
		}
		List<WebElement> mainTabs1 = driver.findElements(By.className("x-tab-strip-close"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", mainTabs1.get(1));
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
		goToLeftPanel2(driver, "Case Resolution Time");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	

		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		for (WebElement currentFrame : frames) {
			try {
				driver.switchTo().frame(currentFrame);
				driver.findElement(By.className("x-grid3-row-table"));
				break;
			}catch(NoSuchElementException noSuchElemExcept) {
				driver.switchTo().defaultContent();
				continue;
			}
		}
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
	
	
	@Test
	public void TS7094_checkCaseResolutionTimeExistence(){
		driver.findElement(By.className("x-grid3-row-table")); //Only CRT have this className in this frame.
	}
	
	@Test
	public void TS7095_CaseResolTimeAdminConfig(){
		WebElement modifyButton = null;
		for (WebElement a : driver.findElement(By.id("00Bc0000001K9hh_filterLinks")).findElements(By.tagName("a"))){
			System.out.println(a.getText());
			if (a.getText().equals("Modificar")) {
				modifyButton = a;
				break;
			}
		}
		Assert.assertTrue(modifyButton != null);
		modifyButton.click();
	}
	
}