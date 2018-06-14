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
import Pages.ContactSearch;
import Pages.HomeBase;
import Pages.OM;
import Pages.OMQPage;
import Pages.SalesBase;
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
	public void AltaLinea() throws InterruptedException {
		OM pageOm=new OM(driver);
		pageOm.Gestion_Alta_De_Linea("FlorOM", "Plan Prepago Nacional");
	}
	
	
	@Test(groups="OM", priority=1)
	public void TS_CRM_Cambio_De_SimCard() throws InterruptedException {
		OM pageOm=new OM(driver);
		pageOm.Gestion_Alta_De_Linea("FlorOM", "Plan Con Tarjeta");
		pageOm.Cambio_De_SimCard("07/13/2018");
	}
	
	@Test(groups="OM", priority=1, dataProvider="SalesCuentaBolsa") 
	public void Gestion_Nominacion(String sCuenta, String sDni, String sLinea) throws Exception {
		SalesBase sb = new SalesBase(driver);
		sb.DesloguearLoguear("venta", 3);
		
	}
	
	
	
	@Test(groups="OM", priority=1)
	public void TS_CRM_Cambio_De_SimCard_Por_Siniestro() throws InterruptedException {
		OM pageOm=new OM(driver);
		pageOm.Gestion_Alta_De_Linea("FlorOM", "Plan Con Tarjeta");
		pageOm.Cambio_De_SimCard_Por_Siniestro("LineasFlor");
	}
	
	@Test(groups="OM", priority=1)
	public void TS_CRM_Alta_De_Servicio() throws InterruptedException {
		OM pageOm=new OM(driver);
		pageOm.Gestion_Alta_De_Linea("FlorOM", "Plan Con Tarjeta");
		pageOm.Gestion_Alta_De_Servicio("LineasFlor", "Blackberry");
	}
		

}
