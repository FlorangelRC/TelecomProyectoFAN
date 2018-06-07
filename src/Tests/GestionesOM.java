package Tests;

import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
		pageOm.irAChangeToOrder();
		
	}
	
	@Test(groups="OM", priority=1)
	public void TS_CRM_Cambio_De_SimCard() throws InterruptedException {
		TS_CRM_OM_Gestion_Alta_De_Linea();
		Date date = new Date();
		Calendar cal = Calendar.getInstance(); 
        cal.setTime(date); 
        cal.add(Calendar.MONTH, +1);
        cal.add(Calendar.DATE, +1);
        date = cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		driver.findElement(By.id("RequestDate")).sendKeys(dateFormat.format(date));
	}

}
