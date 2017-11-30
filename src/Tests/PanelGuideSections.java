package Tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.assertTrue;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.customerInformation;
import Pages.setConexion;

public class PanelGuideSections extends TestBase {
	
private WebDriver driver;
	

	@AfterClass
	public void tearDown2() {
		//driver.close();	
	}
	
	@BeforeClass
	public void Init() throws Exception
	{
		this.driver = setConexion.setupEze();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		login(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	@BeforeMethod
	public void setUp() throws Exception {
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		if (!driver.getCurrentUrl().toString().equals("https://cs14.salesforce.com/console")){
			driver.findElement(By.id("tsidLabel")).click();
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.findElement(By.xpath("//a[@href=\"/console?tsid=02uc0000000D6Hd\"]")).click();
		}
	}
	
	@Test
	public void TS7139_predictiveSearch()
	{
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try{ for(WebElement e : driver.findElements(By.className("x-tab-strip-close"))) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
		} } catch (org.openqa.selenium.StaleElementReferenceException e) {}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		goToLeftPanel(driver, "Cuentas");
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement frame0 = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(frame0);
		Select field = new Select(driver.findElement(By.name("fcf")));
		field.selectByVisibleText("Todas las cuentas");
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> accounts = driver.findElements(By.xpath("//*[text() = 'Fernando Care']"));
		accounts.get(0).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		if(driver.findElements(By.cssSelector(".x-layout-collapsed.x-layout-collapsed-east.x-layout-cmini-east")).size() != 0) {
			driver.findElement(By.cssSelector(".x-layout-collapsed.x-layout-collapsed-east.x-layout-cmini-east")).click();
			}
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame1 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame1.get(4));
		driver.findElement(By.xpath("/html/body/div/div[1]/ng-include/div/div[1]/ng-include/div/div[2]/input")).sendKeys("actuali");
		List<WebElement> butons = driver.findElements(By.xpath("//*[text() = 'Actualizar Pago']"));
		assertTrue(butons.get(0).isDisplayed());
		driver.switchTo().defaultContent();
	}
	
	@Test
	public void TS7134_sucessSearch()
	{
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try{ for(WebElement e : driver.findElements(By.className("x-tab-strip-close"))) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
		} } catch (org.openqa.selenium.StaleElementReferenceException e) {}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		goToLeftPanel(driver, "Cuentas");
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement frame0 = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(frame0);
		Select field = new Select(driver.findElement(By.name("fcf")));
		field.selectByVisibleText("Todas las cuentas");
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> accounts = driver.findElements(By.xpath("//*[text() = 'Fernando Care']"));
		accounts.get(0).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		if(driver.findElements(By.cssSelector(".x-layout-collapsed.x-layout-collapsed-east.x-layout-cmini-east")).size() != 0) {
			driver.findElement(By.cssSelector(".x-layout-collapsed.x-layout-collapsed-east.x-layout-cmini-east")).click();
			}
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame1 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame1.get(4));
		driver.findElement(By.xpath("/html/body/div/div[1]/ng-include/div/div[1]/ng-include/div/div[2]/input")).sendKeys("pague su factura");
		List<WebElement> butons = driver.findElements(By.xpath("//*[text() = 'Pague su Factura']"));
		assertTrue(butons.get(0).isDisplayed());
		driver.switchTo().defaultContent();
	}
	
	@Test
	public void TS7135_failSearch()
	{
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try{ for(WebElement e : driver.findElements(By.className("x-tab-strip-close"))) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
		} } catch (org.openqa.selenium.StaleElementReferenceException e) {}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		goToLeftPanel(driver, "Cuentas");
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement frame0 = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(frame0);
		Select field = new Select(driver.findElement(By.name("fcf")));
		field.selectByVisibleText("Todas las cuentas");
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> accounts = driver.findElements(By.xpath("//*[text() = 'Fernando Care']"));
		accounts.get(0).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		if(driver.findElements(By.cssSelector(".x-layout-collapsed.x-layout-collapsed-east.x-layout-cmini-east")).size() != 0) {
			driver.findElement(By.cssSelector(".x-layout-collapsed.x-layout-collapsed-east.x-layout-cmini-east")).click();
			}
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame1 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame1.get(4));
		driver.findElement(By.xpath("/html/body/div/div[1]/ng-include/div/div[1]/ng-include/div/div[2]/input")).sendKeys("paa");
		List<WebElement> butons = driver.findElements(By.xpath("//*[text() = 'Pague su Factura']"));
		if (butons.size()== 0)
			assertTrue(true);
		else
			assertTrue(false);
		driver.switchTo().defaultContent();
	}
	
	@Test
	public void TS7140_placeholder()
	{
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try{ for(WebElement e : driver.findElements(By.className("x-tab-strip-close"))) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
		} } catch (org.openqa.selenium.StaleElementReferenceException e) {}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		goToLeftPanel(driver, "Cuentas");
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement frame0 = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(frame0);
		Select field = new Select(driver.findElement(By.name("fcf")));
		field.selectByVisibleText("Todas las cuentas");
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> accounts = driver.findElements(By.xpath("//*[text() = 'Fernando Care']"));
		accounts.get(0).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		if(driver.findElements(By.cssSelector(".x-layout-collapsed.x-layout-collapsed-east.x-layout-cmini-east")).size() != 0) {
			driver.findElement(By.cssSelector(".x-layout-collapsed.x-layout-collapsed-east.x-layout-cmini-east")).click();
			}
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame1 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame1.get(4));
		List<WebElement> butons = driver.findElements(By.xpath("//input[@placeholder='Buscar Gestion Ej: Cambio de Titularidad']"));
		assertTrue(butons.get(0).isDisplayed());
		driver.switchTo().defaultContent();
	}
	
	@Test
	public void TS7141_resetSearch()
	{
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try{ for(WebElement e : driver.findElements(By.className("x-tab-strip-close"))) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
		} } catch (org.openqa.selenium.StaleElementReferenceException e) {}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		goToLeftPanel(driver, "Cuentas");
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement frame0 = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(frame0);
		Select field = new Select(driver.findElement(By.name("fcf")));
		field.selectByVisibleText("Todas las cuentas");
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> accounts = driver.findElements(By.xpath("//*[text() = 'Fernando Care']"));
		accounts.get(0).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		if(driver.findElements(By.cssSelector(".x-layout-collapsed.x-layout-collapsed-east.x-layout-cmini-east")).size() != 0) {
			driver.findElement(By.cssSelector(".x-layout-collapsed.x-layout-collapsed-east.x-layout-cmini-east")).click();
			}
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame1 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame1.get(4));
		driver.findElement(By.xpath("/html/body/div/div[1]/ng-include/div/div[1]/ng-include/div/div[2]/input")).sendKeys("pague su factura");
		List<WebElement> butons = driver.findElements(By.xpath("//*[text() = 'Pague su Factura']"));
		assertTrue(butons.get(0).isDisplayed());
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> butons1 = driver.findElements(By.xpath("//*[text() = 'Actualizar Pago']"));
		if (butons1.size()== 0)
			assertTrue(true);
		else
			assertTrue(false);
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.xpath("/html/body/div/div[1]/ng-include/div/div[1]/ng-include/div/div[2]/input")).clear();
		List<WebElement> butons2 = driver.findElements(By.xpath("//*[text() = 'Actualizar Pago']"));
		assertTrue(butons2.get(0).isDisplayed());
		driver.switchTo().defaultContent();
	}

}
