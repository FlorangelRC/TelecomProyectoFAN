package Tests;

import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import Pages.OM;
import Pages.OMQPage;
import Pages.RegistroEventoMasivo;
import Pages.SCP;
import Pages.setConexion;

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
	public void TS102205_CRM_OM_Ola_2_Ordenes_Cliente_existente_Alta_de_linea_con_1_pack_Plan_con_tarjeta_Sin_delivery_Sin_VAS_Paso_0() throws InterruptedException, MalformedURLException {
	String Url;
	OM pageOm=new OM(driver);
	OMQPage OM=new OMQPage (driver);
	//pageOm.Gestion_Alta_De_Linea("QuelysOM", "Plan con tarjeta");
	OM.Gestion_Alta_De_Linea("QuelysOM", "Plan con tarjeta");
	sleep(5000);
	pageOm.irAChangeToOrder();
	sleep(12000);
	driver.switchTo().defaultContent();
	
	//fecha avanzada
	OM.fechaAv("06-26-2018");
	sleep(12000);
	
	//agregar Pack
	OM.agregarPack("Packs Opcionales"," Packs de Datos", "Pack Internet x 7 dias","Pack 1GB de dia + 3GB de Noche,","Pack 500Mb + WhasApp x 3 d�as");
				
	//Click ViewRecord
	sleep(8000);	
	driver.findElement(By.id("-import-btn")).click();
	sleep(7000);
	
	//agregar gestion
	pageOm.agregarGestion("Alta producto gen\u00e9rico");
	Url = driver.getCurrentUrl();
	pageOm.clickTab("Product2_Tab");
	OM.sincroProducto("Llamada en espera CFS");
	driver.get(Url);
	
	//Orquestacion
	driver.findElement(By.name("ta_submit_order")).click();
	sleep(35000);
	pageOm.cambiarVentanaNavegador(1);
	sleep(2000);
	driver.findElement(By.id("idlist")).click();
	sleep(5000);
	pageOm.cambiarVentanaNavegador(0);
	sleep(12000);
	pageOm.completarFlujoOrquestacion();
			
	}
	
	@Test(groups="OM")
	public void TS102212_CRM_OM_Ola_2_Ordenes_Cliente_existente_Alta_de_linea_con_1_pack_Plan_prepago_nacional_Sin_delivery_Sin_VAS_Paso_0() throws InterruptedException, MalformedURLException {
	String Url;
	OM pageOm=new OM(driver);
	OMQPage OM=new OMQPage (driver);
	Select allOrder=new Select(driver.findElement(By.id("fcf"))); 
    allOrder.selectByVisibleText("Quelys"); 
    sleep(1000); 
    try {driver.findElement(By.name("go")).click();}catch(org.openqa.selenium.NoSuchElementException e) {} 
    sleep(3000); 
//  //Selecciona la primera cuenta de la lista en la vista seleccionada 
    WebElement primeraCuenta=driver.findElement(By.cssSelector(".x-grid3-col.x-grid3-cell.x-grid3-td-SALES_ACCOUNT_NAME")); 
    primeraCuenta.findElement(By.tagName("div")).findElement(By.tagName("a")).click(); 
    sleep(8000); 
	//pageOm.Gestion_Alta_De_Linea("QuelysOM", "Plan Prepago Nacional");
	sleep(5000);
	pageOm.irAChangeToOrder();
	sleep(12000);
	driver.switchTo().defaultContent();
	
	//fecha avanzada
	OM.fechaAv("07-01-2018");
	sleep(12000);
	
	//agregar Pack
	OM.agregarPack("Packs Opcionales","Packs de Datos", "Pack 500MB de dia + 1,5GB de Noche","Pack 1GB de dia + 3GB de Noche","Pack 2Gb + WhasApp x 30 d�as");
				
	//Click ViewRecord
	sleep(8000);	
	driver.findElement(By.id("-import-btn")).click();
	sleep(7000);
	
	//agregar gestion
	pageOm.agregarGestion("Alta producto gen\u00e9rico");
	Url = driver.getCurrentUrl();
	pageOm.clickTab("Product2_Tab");
	OM.sincroProducto("Datos CFS");
	driver.get(Url);
	
	//Orquestacion
	driver.findElement(By.name("ta_submit_order")).click();
	sleep(35000);
	pageOm.cambiarVentanaNavegador(1);
	sleep(2000);
	driver.findElement(By.id("idlist")).click();
	sleep(5000);
	pageOm.cambiarVentanaNavegador(0);
	sleep(12000);
	pageOm.completarFlujoOrquestacion();
			
	}
}