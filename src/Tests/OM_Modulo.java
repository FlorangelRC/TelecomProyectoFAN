package Tests;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.OM;
import Pages.OMQPage;

import Pages.SCP;
import Pages.setConexion;


public class OM_Modulo extends TestBase {
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
		SCP pageSCP= new SCP(driver);
		pageSCP.goToMenu("Ventas");
		
		//click +
		sleep(5000);
		OM pageOm=new OM(driver);
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

@Test(groups="OM")
public void TS51856_CRM_OM_Ordenes_Cliente_Nuevo_Alta_de_linea_Sin_delivery_Sin_VAS_Paso_1 () throws InterruptedException {
	OM pageOm=new OM(driver);
	OMQPage OM=new OMQPage (driver);
	pageOm.crearOrden("LineasPlanConTarjeta");
	assertTrue(driver.findElement(By.cssSelector(".noSecondHeader.pageType")).isDisplayed());
	OM.getCPQ().click();
	sleep(5000);
	OM.colocarPlan("Plan con Tarjeta");
	OM.configuracion();
	sleep(5000);
	driver.findElement(By.name("ta_submit_order")).click();
	sleep(25000);
	pageOm.cambiarVentanaNavegador(1);
	sleep(2000);
	driver.findElement(By.id("idlist")).click();
	sleep(5000);
	pageOm.cambiarVentanaNavegador(0);
	sleep(12000);
	pageOm.completarFlujoOrquestacion();

	
		
	
		}
}