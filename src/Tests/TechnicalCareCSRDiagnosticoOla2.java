package Tests;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.BasePage;
import Pages.CustomerCare;
import Pages.HomeBase;
import Pages.TechCare_Ola1;
import Pages.TechnicalCareCSRDiagnosticoPage;
import Pages.setConexion;

public class TechnicalCareCSRDiagnosticoOla2 extends TestBase {
	
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
 		@BeforeMethod(alwaysRun=true)
 		public void setUp() throws Exception {
		//Selecciona la cuenta Adrian Tech de todas las Cuentas
 		TechCare_Ola1 page=new TechCare_Ola1(driver);
		sleep(3000);
		driver.switchTo().defaultContent();
		sleep(3000);
	//	page.selectAccount(buscarCampoExcel(3, "Cuenta Activa c/ linea y serv", 1));
		page.selectAccount ("Marco Polo");
		driver.switchTo().defaultContent();
		sleep(3000);
 	
	}
 	 	
 		//@AfterMethod(alwaysRun=true)
 		public void after() {
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent(); 
		CustomerCare cerrar = new CustomerCare(driver);
	    cerrar.cerrarultimapestaña();
	    driver.switchTo().defaultContent(); 
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
 		
 		@Test (groups= {"TechnicalCare", "SVA", "Ola2"},priority=1)
 		public void CRM_Ola_2_Technical_Care_CRM_SVA_Estado_BC_Verificación_de_cierre_de_estado_en_Resuelta_exitosa() throws Exception {
 		TechnicalCareCSRDiagnosticoPage tech = new TechnicalCareCSRDiagnosticoPage(driver);
 		sleep (4000);
 		driver.switchTo().defaultContent();
 	    //tech.clickOpcionEnAsset(buscarCampoExcel(3, "Cuenta Activa c/ linea y serv", 3), "mis servicios");
 	    tech.clickOpcionEnAsset("543416869777", "mis servicios");
 	    tech.verDetalles();
 	    tech.clickDiagnosticarServicio("Caller Id");
 	    sleep (4000);
 	    tech.selectionInconvenient("No funciona Identificación de llamadas");
 	    tech.continuar();
 	    sleep (4000);
 	    tech.categoriaRed("SI");
 	    tech.clickContinuar();
 	    sleep (4000);
 	    tech.buscarCaso();
 	    sleep (4000);
 	    assertTrue(tech.getEstado().getText().equalsIgnoreCase("Resuelta Exitosa"));

 		}
 		
 		@Test (groups= {"TechnicalCare", "SVA", "Ola2"},priority=2)
 		public void CRM_Ola_2_Technical_Care_CRM_SVA_Estado_Conciliador_Verificación_de_cierre_de_estado_en_Resuelta_exitosa() throws Exception {
 		TechnicalCareCSRDiagnosticoPage tech = new TechnicalCareCSRDiagnosticoPage(driver);
 		sleep (4000);
 		driver.switchTo().defaultContent();
 	    tech.clickOpcionEnAsset("543416869777", "mis servicios");
 	    tech.verDetalles();
 	    tech.clickDiagnosticarServicio("Llamada en espera");
 	    sleep (4000);
 	    tech.selectionInconvenient("No funciona llamada en espera");
 	    tech.continuar();
 	    sleep (4000);
 	   //tech.verificarCaso();
 	    tech.categoriaRed("NO");
 	    tech.clickContinuar();
 	    sleep (4000);
 	    tech.categoriaRed("Conciliar");
 	    sleep (5000);
 	    tech.clickContinuar();
 	   tech.CasoConciliar();
 	    sleep (4000);
 	    assertTrue(tech.getEstado().getText().equalsIgnoreCase("Resuelta Exitosa"));

 		}
 		
 		
}