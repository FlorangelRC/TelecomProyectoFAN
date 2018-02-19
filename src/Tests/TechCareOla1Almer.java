package Tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.CustomerCare;
import Pages.HomeBase;
import Pages.TechCare_Ola1;
import Pages.setConexion;
import Tests.TestBase.IrA;


public class TechCareOla1Almer extends TestBase {
	
	private WebDriver driver;
	
	
	@BeforeClass(alwaysRun=true)
	public void init() throws Exception
	{
		this.driver = setConexion.setupEze();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		login(driver);
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		goInitToConsolaFanF3(driver);
	    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	     //Alerta Aparece Ocasionalmente
	       try {
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
			}catch(org.openqa.selenium.NoAlertPresentException e) {}

       CustomerCare cerrar = new CustomerCare(driver);
       cerrar.cerrarultimapestaña();
		
		//Selecciona Cuentas
		sleep(4000);
		//TechCare_Ola1 page=new TechCare_Ola1(driver);
		//page.seleccionarCualquierCuenta(driver, "Todas las cuentas", "Adrian Tech");
		System.out.println("Cuentas");
		goToLeftPanel2(driver, "Cuentas"	);
		Accounts accPage = new Accounts(driver);
		accPage.accountSelect("Vista Tech");
		System.out.println("Vista Tech");
	    sleep(4000);
	    accPage.selectAccountByName("Adrian Tech");
	    System.out.println("Adrian Tech");
		sleep(3000);
		driver.switchTo().defaultContent();
		System.out.println("fin");
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public void setUp() throws Exception {
		System.out.println("a");
		//Selecciona la cuenta Adrian Tech de todas las Cuentas
				//seleccionCuentaPorNombre(driver, "Adrian Techh");
				sleep(3000);
		
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void after() {
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
	}
	
	//@AfterClass(alwaysRun=true)
	public void tearDown() {
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		CustomerCare cerrar = new CustomerCare(driver);
		cerrar.cerrarultimapestaña();
		HomeBase homePage = new HomeBase(driver);
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		homePage.selectAppFromMenuByName("Ventas");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.quit();
	}
	
	/**
	 * Verifica que aparezca el inconveniente "no puede configurar" luego de diagnosticar el servicio.
	 */
	@Test(groups= {"TechnicalCare","SVA","Ola1"})
	public void TS94226_CRM_Ola1_Technical_Care_CSR_SVA_Actualización_de_matriz_Servicio_Transferencia_de_llamadas_inconveniente_No_funciona_transferencia_de_llamadas_No_funciona_transferencia_de_llamadas_No_puede_configurar() {
		sleep(5000);
		TechCare_Ola1 page=new TechCare_Ola1(driver);
		page.clickOpcionEnAsset("1100000075", "mis servicios");
		sleep(7000);
		page.clickVerDetalle();
		sleep(5000);
		page.clickDiagnosticarServicio("Transferencia de Llamadas");
		sleep(5000);
		Accounts accPage = new Accounts(driver);
	    driver.switchTo().frame(accPage.getFrameForElement(driver, By.cssSelector(".imgItemContainer.ng-scope")));
		assertEquals(driver.findElements(By.cssSelector(".imgItemContainer.ng-scope")).get(1).getText().toLowerCase(),"no puede configurar");
	}

	@Test(groups= {"TechnicalCare","SVA","Ola1"})
	public void TS94226_CRM_Ola1_Technical_Care_CSR_SVA_Visualizacion_de_Servicio_Llamada_tripartita_e_inconveniente_No_funciona_Llamada_tripartita() {
		sleep(5000);
		TechCare_Ola1 page=new TechCare_Ola1(driver);
		page.clickOpcionEnAsset("1100000075", "mis servicios");
		sleep(7000);
		page.clickVerDetalle();
		sleep(5000);
		page.clickDiagnosticarServicio("Transferencia de Llamadas");
		sleep(5000);
		Accounts accPage = new Accounts(driver);
	    driver.switchTo().frame(accPage.getFrameForElement(driver, By.cssSelector(".imgItemContainer.ng-scope")));
		
	}
}
