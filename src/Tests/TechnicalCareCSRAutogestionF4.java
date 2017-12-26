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

import Pages.BasePage;
import Pages.CustomerCare;
import Pages.HomeBase;
import Pages.setConexion;

public class TechnicalCareCSRAutogestionF4 extends TestBase{
	
	private WebDriver driver;
	
	@BeforeClass(groups= {"Fase4","TechnicalCare", "Autogestion"})
	public void init() throws Exception
	{
		this.driver = setConexion.setupEze();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		loginMarcela(driver);
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
       sleep(4000);
	}
	
	
	@BeforeMethod(groups= {"Fase4","TechnicalCare", "Autogestion"})
	public void setUp() throws Exception {
		//Selecciona la cuenta Adrian Tech de todas las Cuentas
		seleccionCuentaPorNombre(driver, "Adrian Techh");
		sleep(3000);
		driver.switchTo().defaultContent();
		//selecciona el campo donde esta la busquedad escribe y busca
		searchAndClick(driver, "Diagnóstico de Autogestión");
		sleep(1000);
	}
	
	@AfterMethod(groups= {"Fase4","TechnicalCare", "Autogestion"})
	public void after() {
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent(); 
		CustomerCare cerrar = new CustomerCare(driver);
	    cerrar.cerrarultimapestaña();
	    driver.switchTo().defaultContent(); 
	}
	
	@AfterClass(groups= {"Fase4","TechnicalCare", "Autogestion"})
	public void tearDown() {
		HomeBase homePage = new HomeBase(driver);
		sleep(1000);
		homePage.selectAppFromMenuByName("Ventas");
		sleep(2000);
		driver.quit();
		sleep(2000);
	}
//--------------------------------------Metodo Seleccion-------------------------------------------------------------//
	
	public void elegirOpciones(String Canal, String Servicio, String MotivoDelIncoveniente) {
		sleep(5000);
		BasePage cambioFrameByID=new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("ChannelSelection")));
		Select sCanal=new Select(driver.findElement(By.id("ChannelSelection")));
		sCanal.selectByVisibleText(Canal);
		Select sServicio=new Select(driver.findElement(By.id("ServiceSelection")));
		sServicio.selectByVisibleText(Servicio);
		Select sMotivo=new Select(driver.findElement(By.id("MotiveSelection")));
		sMotivo.selectByVisibleText(MotivoDelIncoveniente);
		sleep(2000);
	}
	
	public void continuar() {
		try {Thread.sleep(500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement BenBoton = driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope"));
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+BenBoton.getLocation().y+")");
		sleep(500);
		driver.findElement(By.id("SelfManagementStep_nextBtn")).click();
		sleep(3000);
	}
	
	public boolean verificar() {
		sleep(3000);
		if(driver.findElement(By.xpath("//*[@id=\"SimilCaseInformation\"]/div/p/p[3]/strong[1]")).getText()!=null)
			return true;
		else
			return false;
	}
	
	
//------------------------------------- Autogestion Fase4------------------------------------------------------------//
	
	//Revisar
	@Test(groups= {"Fase4","TechnicalCare", "Autogestion"})
	public void TS73819_CRM_Fase_4_Technical_Care_CSR_Autogestion_Verificacion_de_que_la_autogestion_del_servicio_asterisco_533_Pauta_On_Line_corresponde_a_TP() {
		
		elegirOpciones("Asteriscos TP","*533 (Pauta On Line)","Inconv Recarga delivery");
		continuar();
		assertTrue(verificar());	
	}
	
	//Revisar
	@Test(groups= {"Fase4","TechnicalCare", "Autogestion"})
	public void TS73822_CRM_Fase_4_Technical_Care_CSR_Autogestion_Verificacion_de_que_la_autogestion_del_servicio_asterisco_567_SMS_mas_Push_corresponde_a_TP() {
		
		elegirOpciones("Asteriscos TP","*567 (SMS + Push)","Inconv Recarga delivery");
		continuar();
		assertTrue(verificar());	
	}
	
	//Revisar
	@Test(groups= {"Fase4","TechnicalCare", "Autogestion"})
	public void TS73815_CRM_Fase_4_Technical_Care_CSR_Autogestion_Verificacion_de_que_la_autogestion_del_servicio_asterisco_762_ROAMERS_corresponde_a_TP() {
		
		elegirOpciones("Asteriscos TP","*762 (ROAMERS)","Inconv Recarga delivery");
		continuar();
		assertTrue(verificar());	
	}
	
	//Revisar
	@Test(groups= {"Fase4","TechnicalCare", "Autogestion"})
	public void TS73870_CRM_Fase_4_Technical_Care_CSR_Autogestion_Verificacion_de_que_la_autogestion_del_Canal_0800_0800_888_7382_Repro_corresponde_a_un_servicio_de_Telecom() {
		
		elegirOpciones("0800","0800-888-7382 (Repro)","");
		continuar();
		assertTrue(verificar());	
	}
	
}

