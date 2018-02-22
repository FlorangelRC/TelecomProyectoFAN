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
		TechCare_Ola1 page=new TechCare_Ola1(driver);
		sleep(3000);
		page.selectAccount("Marco Polo");
		driver.switchTo().defaultContent();
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public void setUp() throws Exception {
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
		page.clickOpcionEnAsset("543416869777", "mis servicios");
		sleep(7000);
		page.clickVerDetalle();
		sleep(5000);
		page.clickDiagnosticarServicio("Transferencia de Llamadas");
		sleep(5000);
		Accounts accPage = new Accounts(driver);
	    driver.switchTo().frame(accPage.getFrameForElement(driver, By.cssSelector(".imgItemContainer.ng-scope")));
		assertEquals(driver.findElements(By.cssSelector(".imgItemContainer.ng-scope")).get(1).getText().toLowerCase(),"no puede configurar");
	}

	/**
	 * Verifica que al diagnosticar servicio "voice mail con clave" se muestre la pregunta
	 * "cómo ingreso mi clave"
	 */
	@Test(groups= {"TechnicalCare","SVA","Ola1"})
	public void TS94438_CRM_Ola1_Technical_Care_CSR_SVA_Visualizacion_de_Servicio_Voice_Mail_con_Clave_e_inconveniente_cómo_ingreso_mi_clave() {
		sleep(5000);
		TechCare_Ola1 page=new TechCare_Ola1(driver);
		page.clickOpcionEnAsset("543416869777", "mis servicios");
		sleep(7000);
		page.clickVerDetalle();
		sleep(5000);
		page.clickDiagnosticarServicio("Voice Mail con Clave");
		sleep(5000);
		Accounts accPage = new Accounts(driver);
	    driver.switchTo().frame(accPage.getFrameForElement(driver, By.cssSelector(".imgItemContainer.ng-scope")));
	    assertTrue(driver.findElement(By.cssSelector(".imgItemContainer.ng-scope")).getText().toLowerCase().contains("cómo ingreso mi clave"));
	}
	
	/**
	 * Veririca que se muestre la pregunta "no puedo llamar a un número en particular"
	 */
	@Test(groups= {"TechnicalCare","SVA","Ola1"})
	public void TS96292_CRM_Ola1_Technical_Care_CSR_SVA_Visualizacion_de_Servicio_Barrings_Configurables_por_el_usuario_e_inconveniente_No_puedo_llamar_a_un_numero_en_particular() {
		sleep(5000);
		TechCare_Ola1 page=new TechCare_Ola1(driver);
		page.clickOpcionEnAsset("543416869777", "mis servicios");
		sleep(7000);
		page.clickVerDetalle();
		sleep(5000);
		page.clickDiagnosticarServicio("Barrings Configurables por el Usuario");
		sleep(5000);
		Accounts accPage = new Accounts(driver);
	    driver.switchTo().frame(accPage.getFrameForElement(driver, By.cssSelector(".imgItemContainer.ng-scope")));
	    assertTrue(driver.findElement(By.cssSelector(".imgItemContainer.ng-scope")).getText().toLowerCase().contains("no puedo llamar a un número en particular"));
	}
	
	/**
	 * Veririca que se muestre la pregunta "el artículo ofrecido soluciona su inconveniente"
	 */
	@Test(groups= {"TechnicalCare","MisServicios","Ola1"})
	public void TS94390_CRM_Ola1_Technical_Care_CSR_Mis_Servicios_Visualizacion_de_pregunta() {
		sleep(5000);
		TechCare_Ola1 page=new TechCare_Ola1(driver);
		page.clickOpcionEnAsset("543416869777", "mis servicios");
		sleep(7000);
		page.clickVerDetalle();
		sleep(5000);
		page.clickDiagnosticarServicio("SMS","sms entrante");
		sleep(5000);
		page.seleccionarRespuesta("no recibe de un número particular");
	    page.BajaryContinuar();
	    sleep(5000);
	    boolean preguntaFinalDisponible=false;
	    List<WebElement> preguntaFinal=driver.findElements(By.cssSelector(".slds-form-element__label.vlc-slds-inline-control__label.ng-binding"));
	    for(WebElement pf:preguntaFinal) {
	    	if(pf.getText().toLowerCase().contains("el artículo ofrecido soluciona su inconveniente")&&pf.isDisplayed()) {
	    		preguntaFinalDisponible=true;
	    		break;
	    	}
	    }
	    assertTrue(preguntaFinalDisponible);
	}
	
	/**
	 * Verifica que al seleccionar NO continue a la siguienete Ventana
	 */
	@Test(groups= {"TechnicalCare","MisServicios","Ola1"})
	public void TS94392_CRM_Ola1_Technical_Care_CSR_Mis_Servicios_Visualizacion_Documento_Base_de_Conocimiento_Respuesta_NO() {
		sleep(5000);
		TechCare_Ola1 page=new TechCare_Ola1(driver);
		page.clickOpcionEnAsset("543416869777", "mis servicios");
		sleep(7000);
		page.clickVerDetalle();
		sleep(5000);
		page.clickDiagnosticarServicio("SMS","sms entrante");
		sleep(5000);
		page.seleccionarRespuesta("no recibe de un número particular");
	    page.BajaryContinuar();
	    sleep(5000);
	    page.seleccionarRespuesta("no");
	    page.BajaryContinuar();
	    sleep(4000);
	    assertTrue(driver.findElement(By.id("NetworkCategory_nextBtn")).isDisplayed());
	}
	
	/**
	 * Verifica que al seleccionar SI, se genere el ticket al final.
	 */
	@Test(groups= {"TechnicalCare","MisServicios","Ola1"})
	public void TS94391_CRM_Ola1_Technical_Care_CSR_Mis_Servicios_Visualizacion_Documento_Base_de_Conocimiento_Respuesta_SI() {
		sleep(5000);
		TechCare_Ola1 page=new TechCare_Ola1(driver);
		page.clickOpcionEnAsset("543416869777", "mis servicios");
		sleep(7000);
		page.clickVerDetalle();
		sleep(5000);
		page.clickDiagnosticarServicio("SMS","sms entrante");
		sleep(5000);
		page.seleccionarRespuesta("no recibe de un número particular");
	    page.BajaryContinuar();
	    sleep(5000);
	    page.seleccionarRespuesta("si");
	    page.BajaryContinuar();
	    sleep(6000);
	    assertTrue(driver.findElement(By.xpath("//*[@id=\"ClosedCaseKnowledgeBase\"]/div/p/p/strong/strong")).isDisplayed());
	}
	
	@Test(groups= {"TechnicalCare","SVA","Ola1"})
	public void TS94403_CRM_Ola1_Technical_Care_CSR_SVA_Validacion_Red_OK_Consulta_al_cliente_tiene_señal_NO() {
		sleep(5000);
		TechCare_Ola1 page=new TechCare_Ola1(driver);
		page.clickOpcionEnAsset("543416869777", "mis servicios");
		sleep(7000);
		page.clickVerDetalle();
		sleep(5000);
		page.clickDiagnosticarServicio("SMS","sms saliente");
		sleep(5000);
		page.seleccionarRespuesta("sms emisión a algún destino en particular");
	    page.BajaryContinuar();
	    sleep(5000);
	    page.seleccionarRespuesta("no");
	    page.BajaryContinuar();
	    sleep(4000);
	    page.BajaryContinuar();
	    sleep(4000);
	    page.buscarDireccion("Av. Congreso 3940, Buenos Aires, Argentina");
	    page.seleccionarRespuesta("no son las antenas");
	    page.BajaryContinuar();
	    sleep(4000);
	    page.seleccionarRespuesta("no");
	    page.BajaryContinuar();
	    
	}
	
	@Test(groups= {"TechnicalCare","SVA","Ola1"})
	public void TS94417_CRM_Ola1_Technical_Care_CSR_SVA_Validaciones_Visualizacion_MMS_Emisión_Cliente_informa_que_no_puede_enviar_Archivo_Imagen_Audio() {
		sleep(5000);
		TechCare_Ola1 page=new TechCare_Ola1(driver);
		page.clickOpcionEnAsset("543416869777", "mis servicios");
		sleep(7000);
		page.clickVerDetalle();
		sleep(5000);
		page.clickDiagnosticarServicio("mms");
		sleep(5000);
		Accounts accPage = new Accounts(driver);
	    driver.switchTo().frame(accPage.getFrameForElement(driver, By.cssSelector(".imgItemContainer.ng-scope")));
		assertEquals(driver.findElements(By.cssSelector(".imgItemContainer.ng-scope")).get(0).getText().toLowerCase(),"mms emisión cliente informa que no puede enviar archivo");
		assertEquals(driver.findElements(By.cssSelector(".imgItemContainer.ng-scope")).get(1).getText().toLowerCase(),"mms emisión cliente informa que no puede enviar audio");
		assertEquals(driver.findElements(By.cssSelector(".imgItemContainer.ng-scope")).get(2).getText().toLowerCase(),"mms emisión cliente informa que no puede enviar imagen");
	}

	@Test(groups= {"TechnicalCare","SVA","Ola1"})
	public void TS94351_CRM_Ola1_Technical_Care_CSR_SVA_Verificacion_de_buscar_la_posicion_en_el_mapa() {
		sleep(5000);
		TechCare_Ola1 page=new TechCare_Ola1(driver);
		page.clickOpcionEnAsset("543416869777", "mis servicios");
		sleep(7000);
		page.clickVerDetalle();
		sleep(5000);
		page.clickDiagnosticarServicio("sms","sms entrante");
		sleep(5000);
		page.seleccionarRespuesta("no recibe");
	    page.BajaryContinuar();
	    sleep(5000);
	    page.seleccionarRespuesta("no");
	    page.BajaryContinuar();
	    sleep(4000);
	    page.BajaryContinuar();
	    sleep(4000);
	    page.buscarDireccion("José Martí 1439, Argentina");
	    sleep(4000);
	    assertTrue(driver.findElement(By.xpath("//*[@id=\"busSearchMap\"]")).isDisplayed());
	}
}
	
