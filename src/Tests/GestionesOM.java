package Tests;

import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.BasePage;
import Pages.OM;
import Pages.OMQPage;
import Pages.setConexion;

public class GestionesOM extends TestBase {
	
	private WebDriver driver;
	
	@BeforeClass(alwaysRun=true)
	public void init() throws Exception
	{
		this.driver = setConexion.setupEze();
		sleep(5000);
		//Usuario Victor OM
		login(driver, "https://crm--sit.cs14.my.salesforce.com/", "U585991", "Testa10k");
		sleep(5000);	
	}

	@BeforeMethod(alwaysRun=true)
	public void setUp() throws Exception {
		driver.switchTo().defaultContent();
		sleep(2000);
		OM pageOm=new OM(driver);
		pageOm.goToMenuOM();
		
		//click +
		sleep(5000);
		
		pageOm.clickMore();
		sleep(3000);
		
		//click en Ordenes
		pageOm.clickOnListTabs("Orders");
	}
	
	//@AfterClass(alwaysRun=true)
	public void tearDown() {
		sleep(2000);
		driver.quit();
		sleep(1000);
	}
	
	@Test(groups="OM", priority=1)
	public void TS_CRM_OM_Gestion_Alta_De_Linea() throws InterruptedException {
		OM pageOm=new OM(driver);
		OMQPage OM=new OMQPage (driver);
		pageOm.crearOrden("AutomaOM");
		assertTrue(driver.findElement(By.cssSelector(".noSecondHeader.pageType")).isDisplayed());
		pageOm.agregarGestion("Venta");
		sleep(2000);
		OM.getCPQ().click();
		sleep(5000);
		OM.colocarPlan("Plan Prepago Nacional");
		OM.configuracion();
		sleep(5000);
		driver.findElement(By.name("ta_submit_order")).click();
		sleep(35000);
		pageOm.cambiarVentanaNavegador(1);
		sleep(2000);
		driver.findElement(By.id("idlist")).click();
		sleep(5000);
		pageOm.cambiarVentanaNavegador(0);
		sleep(12000);
		pageOm.completarFlujoOrquestacion();
		sleep(5000);
		driver.findElement(By.id("accid_ileinner")).findElement(By.tagName("a")).click();
		sleep(10000);
		pageOm.irAChangeToOrder();
		
	}
	
	@Test(groups="OM", priority=1)
	public void TS_CRM_Cambio_De_SimCard() throws InterruptedException {
		//TS_CRM_OM_Gestion_Alta_De_Linea();
		OM pageOm=new OM(driver);
		OMQPage OM=new OMQPage (driver);
		//Mientras, seleccion de vista
		pageOm.selectVistaByVisibleText("LineasFlor");
		sleep(3000);
		//Selecciona la primera cuenta de la lista en la vista seleccionada
		WebElement primeraCuenta=driver.findElement(By.cssSelector(".x-grid3-col.x-grid3-cell.x-grid3-td-SALES_ACCOUNT_NAME"));
		primeraCuenta.findElement(By.tagName("div")).findElement(By.tagName("a")).click();
		sleep(5000);
		pageOm.irAChangeToOrder();	
		sleep(10000);
		Accounts accountPage = new Accounts(driver);
		driver.switchTo().defaultContent(); 
        /*DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		driver.findElement(By.id("RequestDate")).sendKeys(dateFormat.format(pageOm.fechaAvanzada()));*/
		driver.findElement(By.id("RequestDate")).sendKeys("07-10-2018");
		driver.findElement(By.cssSelector(".form-control.btn.btn-primary.ng-binding")).click();
		sleep(12000);
		OM.SimCard();
		driver.findElement(By.id("-import-btn")).click();
		sleep(8000);
		pageOm.agregarGestion("Cambio de SIM");
		sleep(5000);
		driver.findElement(By.name("ta_submit_order")).click();
		sleep(35000);
		pageOm.cambiarVentanaNavegador(1);
		sleep(2000);
		driver.findElement(By.id("idlist")).click();
		sleep(5000);
		pageOm.cambiarVentanaNavegador(0);
		sleep(12000);
	}
	
	//=========================================================CAMBIO DE NUMERO==================================================================================
	
		@Test (groups = "OM")
		public void TS_OM_Cambio_de_Numero(){
			Date date = new Date();
			OM om = new OM(driver);
		//Mientras, seleccion de vista
			Select allOrder=new Select(driver.findElement(By.id("fcf")));
			allOrder.selectByVisibleText("AlanOM");
			sleep(1000);
			try {driver.findElement(By.name("go")).click();}catch(org.openqa.selenium.NoSuchElementException e) {}
			sleep(3000);
		//Selecciona la primera cuenta de la lista en la vista seleccionada
			WebElement primeraCuenta=driver.findElement(By.cssSelector(".x-grid3-col.x-grid3-cell.x-grid3-td-SALES_ACCOUNT_NAME"));
			primeraCuenta.findElement(By.tagName("div")).findElement(By.tagName("a")).click();
			sleep(8000);
		//Seleccion del ultimo Asset
			om.irAChangeToOrder();	
			sleep(8000);
		//Ingreso de fecha avanzada
			Accounts accountPage = new Accounts(driver);
			/*DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			driver.findElement(By.id("RequestDate")).sendKeys(dateFormat.format(om.fechaAvanzada()));*/
			driver.findElement(By.id("RequestDate")).sendKeys("08-10-2018");
			driver.findElement(By.cssSelector(".form-control.btn.btn-primary.ng-binding")).click();
			sleep(15000);
		//SIM
			driver.findElement(By.cssSelector(".slds-button.cpq-item-has-children")).click();
			sleep(3000);
			driver.switchTo().defaultContent();
			driver.findElement(By.xpath(".//*[@id='tab-default-1']/div[1]/ng-include/div/div/div/div[4]/div[2]/div/ng-include/div/div[2]/ng-include/div/div[1]/div/div[2]/div[11]")).click();
			sleep(3000);
			driver.findElement(By.xpath(".//*[@id='tab-default-1']/div[1]/ng-include/div/div/div/div[4]/div[2]/div/ng-include/div/div[2]/ng-include/div/div[1]/div/div[2]/div[11]/div[2]/div/ul/li[3]/a")).click();
			sleep(5000);
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.className("slds-section")).getLocation().y+" )");
			WebElement msi = driver.findElement(By.xpath("//*[@id='js-cpq-product-cart-config-form']/div[1]/div/form/div[17]/div[1]/input"));
			Random r = new Random();
			msi.clear();
			msi.sendKeys("11" + r.nextInt(200000000) );
			msi.submit();
			sleep(30000);
			driver.findElement(By.id("-import-btn")).click();
			sleep(5000);
		//Gestion
			om.agregarGestion("Cambio de n\u00famero");
			driver.findElements(By.id("topButtonRow")).get(0);
			sleep(7000);
			driver.findElement(By.name("ta_submit_order")).click();
			sleep(35000);
			om.cambiarVentanaNavegador(1);
			sleep(2000);
			driver.findElement(By.id("idlist")).click();
			sleep(5000);
			om.cambiarVentanaNavegador(0);
			sleep(12000);
			om.completarFlujoOrquestacion();
			
			}

}
