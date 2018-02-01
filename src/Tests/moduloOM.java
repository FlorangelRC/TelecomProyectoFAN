package Tests;

import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.Iterator;

import Pages.Accounts;
import Pages.BasePage;
import Pages.HomeBase;
import Pages.RegistroEventoMasivo;
import Pages.SCP;
import Pages.setConexion;

import Pages.setConexion;

public class moduloOM extends TestBase {
	
	private WebDriver driver;
	
	@BeforeClass(alwaysRun=true)
	public void init() throws Exception
	{
		this.driver = setConexion.setupEze();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		login(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//Entrar en Ventas
		
	}

	@BeforeMethod(alwaysRun=true)
	public void setUp() throws Exception {
		driver.switchTo().defaultContent();
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		SCP page= new SCP(driver);
		page.goToMenu("Ventas");
		
		//click +
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.xpath("//a[@href=\"/home/showAllTabs.jsp\"]")).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
		//click en Pedidos

		List<WebElement> optns= driver.findElements(By.cssSelector(".dataCol.orderBlock"));
		for (WebElement option : optns) {
			if(option.getText().toLowerCase().equals("Pedidos".toLowerCase())){
					WebElement BenBoton = option.findElement(By.tagName("a"));
						((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+BenBoton.getLocation().y+")");
							try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
							BenBoton.click();
				break;}
			}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDown() {
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.quit();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	
	@Test(groups="OM")
	public void TS8231_CRM_OM_Ordenes_Panel_principal_Crear_una_Orden() {
		//Click Nuevo
		driver.findElement(By.name("new")).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
				
		//Llena los campos
		driver.findElement(By.id("accid")).sendKeys("Buda OM");
		driver.findElement(By.className("dateFormat")).click();
		Select Estado= new Select(driver.findElement(By.id("Status")));
		Estado.selectByVisibleText("Draft");
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.name("save")).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		assertTrue(driver.findElement(By.cssSelector(".noSecondHeader.pageType")).isDisplayed());
		//--------------LLega Hasta Aqui-----------------------//
		/*driver.findElement(By.name("vlocity_cmt__cpq")).click();
		try {Thread.sleep(12000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.className("slds-media__body")).findElement(By.id("cpq-custom-view-button")).click();
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.xpath("//*[text()='Telecom Price List']")).click();
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid.ng-empty")).click();
		driver.findElement(By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys("Plan Prepago Nacional");
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> Agregar=driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.add-button"));
		for(WebElement add:Agregar) {
			if(add.getText().contains("Agregar"))
				add.click();
		}*/
		
	}
	
	//Si falla revisar los Xpath //*[@id=\"CF00Nc0000001pSu8_ilecell\"] y //*[@id=\"lookupa27c0000005JPh600Nc0000001pSu8\"]
	@Test(groups="OM")
	public void TS6727_CRM_OM_Ordenes_Order_Detail_Visualización_del_flujo_de_orquestación() {
		Select allOrder=new Select(driver.findElement(By.id("fcf")));
		allOrder.selectByVisibleText("All Orders VICTOR OM");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try {
		driver.findElement(By.name("go")).click();}
		catch(Exception e) {};
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> nPedidos=driver.findElement(By.className("x-grid3-scroller")).findElement(By.className("x-grid3-body"))
				.findElements(By.cssSelector(".x-grid3-col.x-grid3-cell.x-grid3-td-ORDERS_ORDER_NUMBER"));
		for(WebElement p:nPedidos) {
			//System.out.println(p.getText());
			if(p.getText().endsWith("3879")) {
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+p.getLocation().y+")");
				try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
				p.findElement(By.tagName("a")).click();
				break;}	
			}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.xpath("//*[@id=\"CF00Nc0000001pSu8_ilecell\"]")).getLocation().y+")");
		try {Thread.sleep(500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.xpath("//*[@id=\"lookupa27c0000005JPh600Nc0000001pSu8\"]")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BasePage frame=new BasePage(driver);
		driver.switchTo().frame(frame.getFrameForElement(driver, By.id("canvas")));
		try {Thread.sleep(500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		assertTrue(driver.findElement(By.id("canvas")).isDisplayed());
		
	}
	
	//No tenemos el Codigo del test
	@Test(groups="OM")
	public void TS_CRM_OM_Interfaces_Cliente_Nuevo_PP_CN_Sin_delivery_Sin_VAS_Paso_1_S203_createSubscriber_Huawei_Verificacion_de_request_response() {
		Select allOrder=new Select(driver.findElement(By.id("fcf")));
		allOrder.selectByVisibleText("All Orders VICTOR OM");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try {
		driver.findElement(By.name("go")).click();}
		catch(Exception e) {};
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> nPedidos=driver.findElement(By.className("x-grid3-scroller")).findElement(By.className("x-grid3-body"))
				.findElements(By.cssSelector(".x-grid3-col.x-grid3-cell.x-grid3-td-ORDERS_ORDER_NUMBER"));
		for(WebElement p:nPedidos) {
			//System.out.println(p.getText());
			if(p.getText().endsWith("3879")) {
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+p.getLocation().y+")");
				try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
				p.findElement(By.tagName("a")).click();
				break;}	
			}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.xpath("//*[@id=\"CF00Nc0000001pSu8_ilecell\"]")).getLocation().y+")");
		try {Thread.sleep(500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.xpath("//*[@id=\"lookupa27c0000005JPh600Nc0000001pSu8\"]")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BasePage frame=new BasePage(driver);
		driver.switchTo().frame(frame.getFrameForElement(driver, By.id("canvas")));
		try {Thread.sleep(500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//assertTrue(driver.findElement(By.id("canvas")).isDisplayed());
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.xpath("//*[text()='Create subscriber']")).getLocation().y+")");
		driver.findElement(By.xpath("//*[text()='Create subscriber']")).click();
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTab.get(1));
		
		driver.findElement(By.className("listTitle")).click();
		assertTrue(driver.findElement(By.xpath("//*[text()='Request']")).isDisplayed()&&driver.findElement(By.xpath("//*[text()='Response']")).isDisplayed());
	}
	
	
	
	
}//Fin Clase
