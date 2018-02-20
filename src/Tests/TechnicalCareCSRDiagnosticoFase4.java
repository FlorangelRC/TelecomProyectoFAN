package Tests;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.hamcrest.core.Is;
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

import Pages.AccountType;
import Pages.Accounts;
import Pages.BasePage;
import Pages.CustomerCare;
import Pages.HomeBase;
import Pages.TechCare_Ola1;
import Pages.TechnicalCareCSRAutogestionPage;
import Pages.TechnicalCareCSRDiagnosticoPage;
import Pages.setConexion;


public class TechnicalCareCSRDiagnosticoFase4 extends TestBase{
	
private WebDriver driver;
	
 	@BeforeClass(groups= {"TechnicalCare", "SVA", "Ola1"})
 	public void init() throws InterruptedException{
	
	this.driver = setConexion.setupEze();
    sleep(5000);
    login(driver);
    sleep(5000);
    HomeBase homePage = new HomeBase(driver);
    Accounts accountPage = new Accounts(driver);
       if(driver.findElement(By.id("tsidLabel")).getText().equals("Consola FAN")) {
         homePage.switchAppsMenu();
         sleep(2000);
         homePage.selectAppFromMenuByName("Ventas");
         sleep(5000);
       }
       homePage.switchAppsMenu();
       sleep(2000);
       homePage.selectAppFromMenuByName("Consola FAN");
       sleep(5000);
	   goToLeftPanel2(driver, "Cuentas");
	   sleep(2000);  
	   driver.switchTo().defaultContent();
		 driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".topNav.primaryPalette")));
		 Select field = new Select(driver.findElement(By.name("fcf")));
		 try {field.selectByVisibleText("Todas Las cuentas");}
		 catch (org.openqa.selenium.NoSuchElementException ExM) {field.selectByVisibleText("Todas las cuentas");}
	

 	 CustomerCare cerrar = new CustomerCare(driver);
 	 cerrar.cerrarultimapestaña();		
 	 sleep(4000);
 	
	
 	}
 	
 	@BeforeMethod(groups= {"TechnicalCare", "SVA", "Ola1"}) 
	public void setUp() throws Exception {
	 Accounts accountPage = new Accounts(driver);
     //Selecciono Vista Tech
     driver.switchTo().defaultContent();
     accountPage.accountSelect("Vista Tech");
     sleep(8000);
     accountPage.selectAccountByName("Adrian Tech");
 	}

	

	@Test (groups= {"TechnicalCare", "SVA", "Ola1"})//listo
	public void TS94226_CRM_Ola_1_Technical_Care_CSR_SVA_Actualización_de_matriz_Servicio_Transferencia_de_llamadas_inconveniente_No_funciona_transferencia_de_llamadas_No_funciona_transferencia_de_llamadas_No_puede_configurar() throws Exception {
	TechnicalCareCSRDiagnosticoPage tech = new TechnicalCareCSRDiagnosticoPage(driver);
	sleep (4000);
	driver.switchTo().defaultContent();
    tech.clickOpcionEnAsset("1100000075", "mis servicios");
    tech.verDetalles();
    tech.clickDiagnosticarServicio("Transferencia de Llamadas");
    tech.selectionInconvenient("No puede configurar");
    assertTrue(tech.validarInconveniente("No puede configurar"));
	}
	
	@Test (groups= {"TechnicalCare", "SVA", "Ola1"})
	public void TS94368_CRM_Ola_1_Technical_Care_CSR_SVA_Visualizacion_de_lista_de_servicios_AGRUPADOR() throws Exception {
	TechnicalCareCSRDiagnosticoPage tech = new TechnicalCareCSRDiagnosticoPage(driver);
	sleep (4000);
	driver.switchTo().defaultContent();
    tech.clickOpcionEnAsset("1100000075", "mis servicios");
    tech.verDetalles();
    tech.clickDiagnosticarServicio("sms", "SMS Entrante", false);
    assertTrue(tech.validarOpcionesXSubServicio("SMS Entrante"));
    
	}
	
	@Test (groups= {"TechnicalCare", "SVA", "Ola1"}) //listo
	public void TS94439_CRM_Ola_1_Technical_Care_CSR_SVA_Visualizacion_de_Servicio_Transferencia_de_llamadas_e_inconveniente_No_funciona_transferencia_de_llamadas_No_puede_configurar() throws Exception {
	TechnicalCareCSRDiagnosticoPage tech = new TechnicalCareCSRDiagnosticoPage(driver);
	sleep (4000);
	driver.switchTo().defaultContent();
    tech.clickOpcionEnAsset("1100000075", "mis servicios");
    tech.verDetalles();
    tech.clickDiagnosticarServicio("Transferencia de Llamadas");
    assertTrue(tech.validarInconveniente("Transferencia de Llamadas"));
    
	}
	
	@Test (groups= {"TechnicalCare", "SVA", "Ola1"})//listo
	public void TS94440_CRM_Ola_1_Technical_Care_CSR_SVA_Visualizacion_de_Servicio_Llamada_en_espera_e_inconveniente_No_funciona_llamada_en_espera() throws Exception {
	TechnicalCareCSRDiagnosticoPage tech = new TechnicalCareCSRDiagnosticoPage(driver);
	sleep (4000);
	driver.switchTo().defaultContent();
    tech.clickOpcionEnAsset("1100000075", "mis servicios");
    tech.verDetalles();
    tech.clickDiagnosticarServicio("Llamada en espera");
    assertTrue(tech.validarInconveniente("No funciona llamada en espera"));
    
	}
	
	@Test (groups= {"TechnicalCare", "SVA", "Ola1"})//listo
	public void TS94441_CRM_Ola_1_Technical_Care_CSR_SVA_Visualizacion_de_Servicio_Llamada_tripartita_e_inconveniente_No_funciona_Llamada_tripartita() throws Exception {
	TechnicalCareCSRDiagnosticoPage tech = new TechnicalCareCSRDiagnosticoPage(driver);
	sleep (4000);
	driver.switchTo().defaultContent();
    tech.clickOpcionEnAsset("1100000075", "mis servicios");
    tech.verDetalles();
    tech.clickDiagnosticarServicio("Llamada Tripartita");
    tech.selectionInconvenient("No funciona Llamada tripartita");
    assertTrue(tech.validarInconveniente("No funciona Llamada tripartita"));
    
	}
	
	@Test (groups= {"TechnicalCare", "SVA", "Ola1"})//listo
	public void TS94459_CRM_Ola_1_Technical_Care_CSR_SVA_Visualizacion_de_Servicio_Conferencia_tripartita_e_inconveniente_No_funciona_Conferencia_Tripartita() throws Exception {
	TechnicalCareCSRDiagnosticoPage tech = new TechnicalCareCSRDiagnosticoPage(driver);
	sleep (4000);
    tech.clickOpcionEnAsset("1100000075", "mis servicios");
    tech.verDetalles();
    tech.clickDiagnosticarServicio("Llamada Tripartita");
    tech.selectionInconvenient("No funciona Llamada tripartita");
    assertTrue(tech.validarInconveniente("No funciona Llamada tripartita"));
	}
	
	@Test(groups= {"TechnicalCare", "SVA", "Ola1"}) //Listo
	public void TS94464_CRM_Ola_1_Technical_Care_CSR_SVA_Visualizacion_de_Servicio_Mensajes_Multimedia_Personal_MMS_e_inconveniente_MMS_Emisión_Cliente_informa_que_no_puede_enviar_Archivo_Imagen_Audio() throws Exception {
	TechnicalCareCSRDiagnosticoPage tech = new TechnicalCareCSRDiagnosticoPage(driver);
	sleep (4000);
    tech.clickOpcionEnAsset("1100000075", "mis servicios");
    tech.verDetalles();
    tech.clickDiagnosticarServicio("MMS");
    tech.selectionInconvenient("MMS Emisión Cliente informa que no puede enviar Imagen");
    assertTrue(tech.validarInconveniente("MMS Emisión Cliente informa que no puede enviar Imagen"));
    
	}
	
	@Test (groups= {"TechnicalCare", "SVA", "Ola1"})
	public void TS94467_CRM_Ola_1_Technical_Care_CSR_SVA_Visualizacion_de_Servicio_SMS_saliente_e_inconveniente_SMS_Emisión_a_algun_destino_en_particular() throws Exception {
	TechnicalCareCSRDiagnosticoPage tech = new TechnicalCareCSRDiagnosticoPage(driver);
	sleep (4000);
    tech.clickOpcionEnAsset("1100000075", "mis servicios");
    tech.verDetalles();
    tech.clickDiagnosticarServicio("sms", "SMS Saliente", false);
    assertTrue(tech.validarOpcionesXSubServicio("SMS Saliente"));

    
    }
	@Test (groups= {"TechnicalCare", "SVA", "Ola1"}) //Listo
	public void TS94276_CRM_Ola_1_Technical_Care_CSR_SVA_Validacion_SMS_entrante_no_recibe_ningun_numero() throws Exception {
	TechnicalCareCSRDiagnosticoPage tech = new TechnicalCareCSRDiagnosticoPage(driver);
	sleep (4000);
    tech.clickOpcionEnAsset("1100000075", "mis servicios");
    tech.verDetalles();
    tech.clickDiagnosticarServicio("sms", "SMS Entrante", true);
    tech.selectionInconvenient("No recibe de un número particular");
    assertTrue(tech.validarInconveniente("No recibe de un número particular"));
	
	}
	
	@Test (groups= {"TechnicalCare", "SVA", "Ola1"}) //Listo
	public void TS94277_CRM_Ola_1_Technical_Care_CSR_SVA_Validacion_SMS_saliente_no_emite_a_ningun_numero() throws Exception {
	TechnicalCareCSRDiagnosticoPage tech = new TechnicalCareCSRDiagnosticoPage(driver);
	sleep (4000);
    tech.clickOpcionEnAsset("1100000075", "mis servicios");
    tech.verDetalles();
    tech.clickDiagnosticarServicio("sms", "SMS Saliente", true);
    tech.selectionInconvenient("SMS a fijo");
    assertTrue(tech.validarInconveniente("SMS a fijo"));
	
	}
	
	@Test (groups= {"TechnicalCare", "SVA", "Ola1"}) //Listo
	public void TS94278_CRM_Ola_1_Technical_Care_CSR_SVA_Validacion_SMS_saliente_no_emite_a_algun_destino() throws Exception {
	TechnicalCareCSRDiagnosticoPage tech = new TechnicalCareCSRDiagnosticoPage(driver);
	sleep (4000);
    tech.clickOpcionEnAsset("1100000075", "mis servicios");
    tech.verDetalles();
    tech.clickDiagnosticarServicio("sms", "SMS Saliente", true);
    tech.selectionInconvenient("SMS Emisión a algún destino en particular");
    assertTrue(tech.validarInconveniente("SMS Emisión a algún destino en particular"));
	
	}
	
	@Test (groups= {"TechnicalCare", "SVA", "Ola1"}) //Listo
	public void TS94309_CRM_Ola_1_Technical_Care_CSR_SVA_Visualizacion_de_buscador_para_servicios_agrupados() throws Exception {
	TechnicalCareCSRDiagnosticoPage tech = new TechnicalCareCSRDiagnosticoPage(driver);
	sleep (4000);
    tech.clickOpcionEnAsset("1100000075", "mis servicios");
    tech.verDetalles();
    tech.buscarServicio("SMS");
  
	
	}
	
	
}