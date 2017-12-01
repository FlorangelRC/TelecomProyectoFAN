package Tests;


import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.BasePage;
import Pages.CustomerCare;
import Pages.HomeBase;
import Pages.setConexion;


public class TechnicalCareCSRMuletoA extends TestBase{
	
private WebDriver driver;
	
	@BeforeClass(groups= "TechnicalCare")
	public void init() throws Exception
	{
		this.driver = setConexion.setupEze();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		login(driver);
		goInitToConsolaFanF3(driver);
		     
	    CustomerCare cerrar = new CustomerCare(driver);
	    cerrar.cerrarultimapestaña();
		
		//Selecciona Cuentas
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		goToLeftPanel2(driver, "Cuentas");
		
		   
	}
	
	
	@BeforeMethod(groups= "TechnicalCare")
	public void setUp() throws Exception {
		//Selecciona la cuenta Adrian Tech de todas las Cuentas
				seleccionCuentaPorNombre(driver, "Adrian Techh");
		//selecciona el campo donde esta la busquedad del imput y Escribe
				searchAndClick(driver, "Gestionar Muleto");
	}
	
	
	@AfterMethod(groups= "TechnicalCare")
	public void after() {
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		CustomerCare cerrar = new CustomerCare(driver);
		cerrar.cerrarultimapestaña();
	}
	
	@AfterClass(groups= "TechnicalCare")
	public void tearDown() {
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		CustomerCare cerrar = new CustomerCare(driver);
		//cerrar.cerrarultimapestaña();
		HomeBase homePage = new HomeBase(driver);
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		homePage.selectAppFromMenuByName("Ventas");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.close();
	}
	
	
	public void clickContinuar() {
		try {Thread.sleep(500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement BenBoton = driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope"));
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+BenBoton.getLocation().y+")");
		WebElement continuar= driver.findElement(By.xpath("//*[text() = 'Continuar']"));
		try {Thread.sleep(500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try {
		continuar.click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		}
		catch(org.openqa.selenium.ElementNotVisibleException a) {
			List <WebElement> continuar2=driver.findElements(By.className("ng-binding"));
			for(WebElement c:continuar2) {
				if(c.getText().contains("Continuar")) {
					c.click();
					break;}}}		
	}
	
	
	public void llenarDatos() {
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Select sGenero=new Select(driver.findElement(By.id("Gender")));
		sGenero.selectByVisibleText("Masculino");
		Select sDocumento=new Select(driver.findElement(By.id("DocumentType")));
		sDocumento.selectByVisibleText("DNI");
		driver.findElement(By.id("DocumentNumber")).sendKeys("36686926");
		try {Thread.sleep(500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	
	/**En el campo accesorio escribe y luego verifica que se haya escrito
	 * @author Almer
	 * 
	 */
	@Test(groups= "TechnicalCare")
	public void TS51111_CRM_Fase_3_Technical_Care_CSR_Muleto_Verificacion_contenido_campo_Accesorios() {
	
	//Cambio al Frame
		Accounts accPage = new Accounts(driver);
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.className("borderOverlay")));
	//Click Entrega de Muleto
		driver.findElements(By.className("borderOverlay")).get(0).click();
		clickContinuar();
	llenarDatos();
		clickContinuar();
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		clickContinuar();
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> listadoDeTerminales=driver.findElements(By.className("slds-radio--faux"));
		listadoDeTerminales.get(1).click();
		clickContinuar();
		
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("AccesoriesField")).sendKeys("Esta es una prueba automatizada");
		clickContinuar();
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		clickContinuar();
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
		boolean check=false;
		WebElement campoAccesorios=driver.findElement(By.id("MuletoDelivered")).
				findElement(By.className("slds-form-element__control")).
				findElement(By.className("ng-binding")).
				findElement(By.tagName("p"));
			//System.out.println(campoAccesorios.getText());
		if(campoAccesorios.getText().contains("Esta es una prueba automatizada"))
				check=true;
		assertTrue(check);
	}
	
	/**
	 * Verifica que al introducir un numero de documento (que no tenga muleto para devolucion), despues de hacerle click en continuar
	 * apareza el mensaje  "No Posee Terminal Para Devolución"
	 * @author Almer
	 */
	@Test(groups= "TechnicalCare")
	public void TS51117_CRM_Fase_3_Technical_Care_CSR_Muleto_Visualizacion_mensaje_en_caso_de_No_poseer_Muleto() {
		
		Accounts accPage = new Accounts(driver);
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.className("borderOverlay")));
	//Click Devolucion de Muleto
		driver.findElements(By.className("borderOverlay")).get(1).click();
		clickContinuar();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	//Click Numero de Documento
		driver.findElements(By.className("borderOverlay")).get(2).click();
		llenarDatos();
		clickContinuar();
		
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("alert-ok-button")).click();
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		boolean check=false;
		List<WebElement> a=driver.findElements(By.className("error"));
		for(WebElement b:a)
		//System.out.println(b.getText());
			if(b.getText().contains("No Posee Terminal Para Devolución."))
				check=true;
		
		assertTrue(check);
	}
	
	/**
	 * Ingresa a la vista 360 va a entrega de muleto, llena los campos, selecciona el muleto, y en el campo accesorio no escriber.
	 * se verifica que se pueda continuar sin escribir en el campo accesorio
	 * @author Almer
	 */
	@Test(groups= "TechnicalCare")
	public void TS51109_CRM_Fase_3_Technical_Care_CSR_Muleto_Verificacion_si_no_se_ingresa_un_texto_puede_continuar_con_la_gestion() {
	
	//Cambio al Frame
		Accounts accPage = new Accounts(driver);
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.className("borderOverlay")));
	//Click Entrega de Muleto
		driver.findElements(By.className("borderOverlay")).get(0).click();
		clickContinuar();
	llenarDatos();
		clickContinuar();
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		clickContinuar();
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> listadoDeTerminales=driver.findElements(By.className("slds-radio--faux"));
		listadoDeTerminales.get(1).click();
		clickContinuar();
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//driver.findElement(By.id("AccesoriesField")).sendKeys("Esta es una prueba automatizada");
		clickContinuar();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try {
			assertTrue(driver.findElement(By.cssSelector(".slds-button.slds-button--neutral.TechCare-DownloadPDF-Btn")).isDisplayed());}
		catch(org.openqa.selenium.ElementNotVisibleException e){assertTrue(false);}		
	}

	/**
	 *Se Verifica que en Entrega de muleto se pueda seleccionar un  muleto(Dispositivos) de la lista.
	 * @author Almer
	 */
	@Test(groups= "TechnicalCare")
	public void TS51097_CRM_Fase_3_Technical_Care_CSR_Muleto_Verificacion_seleccion_de_uNO_del_listado() {
	
	//Cambio al Frame
		Accounts accPage = new Accounts(driver);
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.className("borderOverlay")));
	//Click Entrega de Muleto
		driver.findElements(By.className("borderOverlay")).get(0).click();
		clickContinuar();
	llenarDatos();
		clickContinuar();
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		clickContinuar();
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> listadoDeTerminales=driver.findElements(By.className("slds-radio--faux"));
		
		try {
			listadoDeTerminales.get(1).click();
			assertTrue(true);
		}
		catch(Exception e) {assertTrue(false);}
	}
	
	/**
	 * Se verifica que al seleccionar "Numero de Caso" se pueda ingresar el numero de caso.
	 */
	@Test(groups= "TechnicalCare")
	public void TS51114_CRM_Fase_3_Technical_Care_CSR_Muleto_Verificacion_ingreso_del_NuMERO_de_Caso_para_Devolucion_de_muleto() {
		
		Accounts accPage = new Accounts(driver);
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.className("borderOverlay")));
	//Click Devolucion de Muleto
		driver.findElements(By.className("borderOverlay")).get(1).click();
		clickContinuar();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	//Click Numero de caso
		driver.findElements(By.className("borderOverlay")).get(3).click();
		try{
		driver.findElement(By.id("InputCase")).sendKeys("00006139");
		assertTrue(true);}
		catch(Exception e) {assertTrue(false);}
	}
	
	/**
	 * Se verifica que al ingresar un dni que NO este en blacklist, se pueda continuar con la gestion.
	 */
	@Test(groups= "TechnicalCare")
	public void TS51088_CRM_Fase_3_Technical_Care_CSR_Muleto_Verificacion_Blacklist_negativo() {
	
	//Cambio al Frame
		Accounts accPage = new Accounts(driver);
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.className("borderOverlay")));
	//Click Entrega de Muleto
		driver.findElements(By.className("borderOverlay")).get(0).click();
		clickContinuar();
	llenarDatos();
		clickContinuar();
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try {
			clickContinuar();
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			clickContinuar();
			assertTrue(true);
			}
		catch(Exception e) {assertTrue(false);}
	}
	

	@Test(groups= "TechnicalCare")
	public void TS51110_CRM_Fase_3_Technical_Care_CSR_Muleto_Verificacion_si_no_se_ingresa_un_texto_puede_continuar_con_la_gestion() {
	
	//Cambio al Frame
		Accounts accPage = new Accounts(driver);
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.className("borderOverlay")));
	//Click Entrega de Muleto
		driver.findElements(By.className("borderOverlay")).get(0).click();
		clickContinuar();
	llenarDatos();
		clickContinuar();
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		clickContinuar();
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> listadoDeTerminales=driver.findElements(By.className("slds-radio--faux"));
		listadoDeTerminales.get(1).click();
		clickContinuar();
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		clickContinuar();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try {
			assertTrue(driver.findElement(By.cssSelector(".slds-button.slds-button--neutral.TechCare-DownloadPDF-Btn")).isDisplayed());}
		catch(org.openqa.selenium.ElementNotVisibleException e){assertTrue(false);}		
	}
	
}
