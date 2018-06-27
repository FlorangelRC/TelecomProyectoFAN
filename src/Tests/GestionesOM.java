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
	
	@Test(groups="OM", priority=1, dataProvider="OMAltaLinea")
	public void AltaLinea_Datos(String sCuenta, String sPlan, String sLinea, String sIccid, String sImsi, String sKi) throws InterruptedException {
		OM pageOm=new OM(driver);
		pageOm.Gestion_Alta_De_Linea_Parametros(sCuenta, sPlan, sLinea, sIccid, sImsi, sKi);
	}
	
	@Test(groups="OM", priority=1, dataProvider="OMCambioSim")
	public void TS_CRM_Cambio_De_SimCard_Datos(String sCuenta, String sPlan, String sLinea, String sIccid, String sImsi, String sKi, String sIccid2, String sImsi2, String sKi2) throws InterruptedException {
		OM pageOm=new OM(driver);
		pageOm.Gestion_Alta_De_Linea_Parametros(sCuenta, sPlan, sLinea, sIccid, sImsi, sKi);
		pageOm.Cambio_De_SimCard_Parametros(sIccid2,sImsi2,sKi2);
	}
	
	@Test(groups="OM", priority=1, dataProvider="OMCambioSimSiniestro")
	public void TS_CRM_Cambio_De_SimCard_Por_Siniestro_Datos(String sCuenta, String sPlan, String sLinea, String sIccid, String sImsi, String sKi, String sIccid2, String sImsi2, String sKi2) throws InterruptedException {
		OM pageOm=new OM(driver);
		pageOm.Gestion_Alta_De_Linea_Parametros(sCuenta, sPlan, sLinea, sIccid, sImsi, sKi);
		pageOm.Cambio_De_SimCard_Por_Siniestro_Parametros(sIccid2,sImsi2,sKi2);
	}
	
	@Test(groups="OM", priority=1, dataProvider="OMCambioDeNumero")
	public void TS_CRM_Cambio_De_Numero_Datos(String sCuenta, String sPlan, String sLinea, String sIccid, String sImsi, String sKi,String sMsisdn) throws InterruptedException {
		OM pageOm=new OM(driver);
		boolean gestion = false;
		pageOm.Gestion_Alta_De_Linea_Parametros(sCuenta, sPlan, sLinea, sIccid, sImsi, sKi);
		pageOm.Gestion_Cambio_de_Numero_Parametros(sMsisdn);
		sleep(5000);
		WebElement status = driver.findElement(By.id("Status_ilecell"));
		List <WebElement> gest = driver.findElements(By.cssSelector(".dataCol.inlineEditWrite"));
		for (WebElement x : gest) {
			if (x.getText().equalsIgnoreCase("Cambio de n\\u00famero")) {
				gestion = true;
			}
		}
		Assert.assertTrue(status.getText().equalsIgnoreCase("Activated"));
		Assert.assertTrue(gestion);
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
		pageOm.Cambio_De_SimCard("07-13-2018");
	}
	
	@Test(groups="OM", priority=1, dataProvider="OMNominacion") 
	public void TS_CRM_Gestion_Nominacion(String sCuenta, String sDni, String sLinea) throws Exception {
		OM pageOm=new OM(driver);
		SalesBase sb = new SalesBase(driver);
		sb.DesloguearLoguear("venta", 3);
		pageOm.Gestion_Nominacion(sCuenta, sDni, sLinea);
		sb.DesloguearLoguear("OM", 4);
	}
	
	
	@Test(groups="GestionOM") 
	public void TS_CRM_CambioDeTitularidad() throws InterruptedException {
		
		OM pageOm=new OM(driver);
		pageOm.Gestion_Alta_De_Linea("AutomaOM", "Plan Prepago Nacional");
		pageOm.Gestion_Cambio_De_Titularidad("CambioDeTitularidad");
//		driver.switchTo().defaultContent();
//		sleep(12000);
//		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
//		driver.findElement(By.id("RequestDate")).sendKeys(dateFormat.format(pageOm.fechaAvanzada()));
//		//driver.findElement(By.id("RequestDate")).sendKeys("06-15-2018");
//		
//		//click Next
//		WebElement next=driver.findElement(By.cssSelector(".form-control.btn.btn-primary.ng-binding"));
//		next.click();
//		sleep(30000);
//		
//		//Click ViewRecord
//		driver.findElement(By.id("-import-btn")).click();
//		sleep(7000);
//		
//		//click en goto list en (TA Price Book)
//		WebElement goToList=driver.findElement(By.className("pShowMore")).findElements(By.tagName("a")).get(1);
//		sleep(500);
//		pageOm.scrollDown(driver.findElement(By.className("pShowMore")));
//		sleep(500);
//		goToList.click();
//		sleep(7000);
//		
//		//Cambiar Cuenta en Servicios
//		pageOm.cambioDeCuentaServicios("CambioDeTitularidad");
//		
//		//Click para retonar a la orden
//		driver.findElement(By.className("ptBreadcrumb")).findElement(By.tagName("a")).click();
//		sleep(4000);
//		
//		//Editamos Orden
//		pageOm.cambiarCuentaYGestionEnOrden("CambioDeTitularidad","Cambio de titularidad");
//		sleep(4000);
//		
//		//Finalizamos el proceso con TA SUBMIT ORDER
//		driver.findElement(By.name("ta_submit_order")).click();
	}
	
	@Test(groups="OM", priority=1)
	public void TS_CRM_Alta_De_Servicio() throws InterruptedException {
		OM pageOm=new OM(driver);
		pageOm.Gestion_Alta_De_Linea("FlorOM", "Plan Con Tarjeta");
		pageOm.Gestion_Alta_De_Servicio("LineasFlor", "Blackberry");
	}
	
	@Test(groups="OM", priority=1)
	public void TS_CRM_Cambio_De_SimCard_Por_Siniestro() throws InterruptedException {
		OM pageOm=new OM(driver);
		boolean gestion = false;
		pageOm.Gestion_Alta_De_Linea("FlorOM", "Plan Con Tarjeta");
		pageOm.Cambio_De_SimCard_Por_Siniestro("LineasFlor");
		sleep(5000);
		WebElement status = driver.findElement(By.id("Status_ilecell"));
		List <WebElement> gest = driver.findElements(By.cssSelector(".dataCol.inlineEditWrite"));
		for (WebElement x : gest) {
			if (x.getText().equalsIgnoreCase("Cambio de SIM por siniestro")) {
				gestion = true;
			}
		}
		Assert.assertTrue(status.getText().equalsIgnoreCase("Activated"));
		Assert.assertTrue(gestion);
	}
	
	@Test(groups="OM", priority=1)
	public void AltaDeServicio() throws InterruptedException {
		boolean gestion = false;
		OM om = new OM(driver);
		om.Gestion_Alta_De_Linea("FlorOM", "Plan Prepago Nacional");
		om.irAChangeToOrder();
		sleep(15000);
		driver.findElement(By.id("RequestDate")).sendKeys("12-09-2019");
		driver.findElement(By.cssSelector(".form-control.btn.btn-primary.ng-binding")).click();
		sleep(10000);
		driver.findElement(By.xpath("//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[3]/div[1]/div[1]/button/span[2]")).click();
		sleep(10000);
		driver.findElements(By.cssSelector(".slds-button.slds-button_icon-small")).get(1).click();
		sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[4]/div[2]/div/ng-include/div/div[2]/ng-include/div/div[3]/div/div[3]/div/div/ng-include/div/div[2]/ng-include/div/div[3]/div/div[2]/div[11]/button")).click();
		sleep(7000);
		driver.findElement(By.xpath("//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[4]/div[2]/div/ng-include/div/div[2]/ng-include/div/div[3]/div/div[3]/div/div/ng-include/div/div[2]/ng-include/div/div[5]/div/div[2]/div[11]/button")).click();
		sleep(7000);
		driver.findElement(By.xpath("//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[4]/div[2]/div/ng-include/div/div[2]/ng-include/div/div[3]/div/div[3]/div/div/ng-include/div/div[2]/ng-include/div/div[7]/div/div[2]/div[11]/button")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-button.slds-button_neutral")), "contains", "view record");
		sleep(5000);
		om.agregarGestion("Alta o Baja SVA");
		sleep(3000);
		driver.findElement(By.name("ta_submit_order")).click();
		sleep(10000);
		om.completarFlujoOrquestacion();
		sleep(10000);
		WebElement status = driver.findElement(By.id("Status_ilecell"));
		List <WebElement> gest = driver.findElements(By.cssSelector(".dataCol.inlineEditWrite"));
		for (WebElement x : gest) {
			if (x.getText().equalsIgnoreCase("Alta o Baja SVA")) {
				gestion = true;
			}
		}
		Assert.assertTrue(status.getText().equalsIgnoreCase("Activated"));
		Assert.assertTrue(gestion);
	}
	
	
	@Test(groups="OM", priority=1)
	public void TS_CRM_Cambio_De_Numero() throws InterruptedException {
		OM pageOm=new OM(driver);
		boolean gestion = false;
		pageOm.Gestion_Alta_De_Linea("AlOM", "Plan Con Tarjeta");
		pageOm.Gestion_Cambio_de_Numero("AlanOM", "07-07-2018");
		sleep(5000);
		WebElement status = driver.findElement(By.id("Status_ilecell"));
		List <WebElement> gest = driver.findElements(By.cssSelector(".dataCol.inlineEditWrite"));
		for (WebElement x : gest) {
			if (x.getText().equalsIgnoreCase("Cambio de n\\u00famero")) {
				gestion = true;
			}
		}
		Assert.assertTrue(status.getText().equalsIgnoreCase("Activated"));
		Assert.assertTrue(gestion);
	}
	
}


