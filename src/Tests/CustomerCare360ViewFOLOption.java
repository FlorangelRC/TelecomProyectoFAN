package Tests;
import static org.testng.Assert.assertEquals;

import java.sql.Driver;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.Login;
import Pages.setConexion;


public class CustomerCare360ViewFOLOption extends TestBase {

	
private WebDriver driver;
 	
//@AfterClass
public void tearDown() {
		driver.close();
	}
	//***************************************ESTA CLASE NO VA*******************************************
//@BeforeTest
public void mainSeteup() {
	this.driver = setConexion.setupLeo();	

}

@AfterMethod
public void alert (){
	driver.get("https://cs14.salesforce.com/home/home.jsp");
	try{
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
	}catch(org.openqa.selenium.NoAlertPresentException e){}
}

@BeforeClass
public void init() throws Exception
{
	this.driver = setConexion.setupLeo();
	try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	login(driver);
	try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
}

@BeforeMethod
public void setUpTest() {
	try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	driver.findElement(By.id("tsidLabel")).click();
	try {driver.findElement(By.xpath("//a[@href=\"/home/home.jsp?tsid=02u41000000QWha\"]")).click();
	}catch (org.openqa.selenium.NoSuchElementException e){}
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
	goToLeftPanel(driver, "Cuentas");
	WebElement frame0 = driver.findElement(By.tagName("iframe"));
	driver.switchTo().frame(frame0);
	waitFor(driver, (By.name("fcf")));	
	Select field = new Select(driver.findElement(By.name("fcf")));
	field.selectByVisibleText("Todas las cuentas");
	
	waitFor(driver, (By.xpath("//*[text() = 'Adrian Tech']")));	
	try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	List<WebElement> accounts = driver.findElements(By.xpath("//*[text() ='Andres Care']"));
	accounts.get(0).click();	
	driver.switchTo().defaultContent();
}	

	 @Test
	public void TS7120_ValidationTabBillingInformation () {
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		String billingTabName = "Facturaci�n";
		boolean billingTabExist = false;
		List<WebElement> availableTabs = driver.findElements(By.className("tabText"));
		//Checks all tabs, and finds if some one matches billingTabName
		for (WebElement tab : availableTabs) {
			if (tab.getText().equals(billingTabName)) {
				billingTabExist = true;
			}
		}
		Assert.assertTrue(billingTabExist);
	}
	

	
	@Test
	public void TS7123_Visualizar_Formato_Tabla () {
	try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	driver.findElement(By.cssSelector(".ext-webkit.ext-chrome"));
	}
	
	@Test
	public void TS7122_ValidationFieldAddFOL () {
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame1 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame1.get(5));
		driver.findElement(By.className("slds-truncate"));
}
	
	
	
	
	
	
}

	
	
	

