package Tests;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.BasePage;
import Pages.HomeBase;
import Pages.setConexion;

public class TechnicalCareCSRDiagnostico extends TestBase{
	private WebDriver driver;

	@BeforeClass(groups = "Fase2")
	public void init() throws Exception
	{
		this.driver = setConexion.setupEze();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		login(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}

	@BeforeMethod(groups = "Fase2")
	public void setUp() throws Exception {
	 HomeBase homePage = new HomeBase(driver);
     homePage.switchAppsMenu();
     try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
     homePage.selectAppFromMenuByName("Consola FAN");
     try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}            
     goToLeftPanel2(driver, "Cuentas");
     try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
     Accounts accountPage = new Accounts(driver);
     //Selecciono Vista Tech
     driver.switchTo().defaultContent();
     accountPage.accountSelect("Vista Tech");
     try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
     accountPage.selectAccountByName("Robo Tech");
     try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}            
     if(accountPage.isTabOpened("Asistencia Técnica")) {
         System.out.println("Tab Opened.");
         accountPage.goToTab("Asistencia Técnica");
     }else {
         accountPage.findAndClickButton("Asistencia Técnica");
     }
     try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
 }
	@AfterMethod(groups = "Fase2")
	public void closeTechCareTab() {
		System.out.println("AfterMethod executed.");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Accounts accountPage = new Accounts(driver);
		if(accountPage.isTabOpened("Asistencia Técnica")) {
			accountPage.closeAccountServiceTabByName("Asistencia Técnica");	
		}
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accountPage.findAndClickButton("Asistencia Técnica");
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	
	//@AfterClass
	public void tearDown() {
		BasePage basePage = new BasePage();
		basePage.switchAppsMenu();
		basePage.selectAppFromMenuByName("Ventas");
		driver.close();
	}
	
	@Test(groups = "Fase2") 
	public void TS11596_Banda_Ancha_Fija_Lista_De_Sintomas() {
		Accounts accPage = new Accounts(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("SelectServiceStep_nextBtn")));
		driver.findElement(By.id("LookupSelectofService")).click();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.cssSelector(".slds-list--vertical.vlc-slds-list--vertical")).findElements(By.cssSelector(".slds-list__item.ng-binding.ng-scope")).get(0).click();
		WebElement BenBoton = driver.findElement(By.id("SelectServiceStep_nextBtn"));
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+BenBoton.getLocation().y+")");
		BenBoton.click();
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> Emergente= driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope"));
		Emergente.get(1).click();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("SelectedMotivesLookup")).click();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> Motivos = driver.findElements(By.cssSelector(".slds-list--vertical.vlc-slds-list--vertical")).get(1).findElements(By.cssSelector(".slds-list__item.ng-binding.ng-scope"));
		boolean modem = false; //No funciona mi modem
		boolean noInter = false; //No me funciona internet
		boolean interLen = false; //Internet Funciona lento o se cae
		for (WebElement UnMot : Motivos) {
			if(UnMot.getText().toLowerCase().equals("no funciona mi módem")) {modem=true;}
			else {
				if(UnMot.getText().toLowerCase().equals("no me funciona internet")) {noInter=true;}
				else {
					if(UnMot.getText().toLowerCase().equals("internet funciona lento o se cae")) {interLen=true;}
					}
				}
		}
		assertTrue(modem&&noInter&&interLen);
	}
	
	@Test(groups = "Fase2") 
	public void TS11599_Centrex_Lista_De_Sintomas() {
		Accounts accPage = new Accounts(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("SelectServiceStep_nextBtn")));
		driver.findElement(By.id("LookupSelectofService")).click();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.cssSelector(".slds-list--vertical.vlc-slds-list--vertical")).findElements(By.cssSelector(".slds-list__item.ng-binding.ng-scope")).get(1).click();
		WebElement BenBoton = driver.findElement(By.id("SelectServiceStep_nextBtn"));
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+BenBoton.getLocation().y+")");
		BenBoton.click();
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> Emergente= driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope"));
		Emergente.get(1).click();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("SelectedMotivesLookup")).click();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> Motivos = driver.findElements(By.cssSelector(".slds-list--vertical.vlc-slds-list--vertical")).get(1).findElements(By.cssSelector(".slds-list__item.ng-binding.ng-scope"));
		boolean central = false; //No funciona mi modem
		boolean comunic = false; //No me funciona internet
		for (WebElement UnMot : Motivos) {
			if(UnMot.getText().toLowerCase().equals("no funciona bien la central")) {central=true;}
			else {
				if(UnMot.getText().toLowerCase().equals("no me puedo comunicar con los internos")) {comunic=true;}
				
				}
		}
		assertTrue(central&&comunic);
	}
	
	@Test(groups = "Fase2") 
	public void TS11581_Banda_Ancha_Fija_Creacion_De_Ticket_Al_Ejecutar() {
		Accounts accPage = new Accounts(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("SelectServiceStep_nextBtn")));
		driver.findElement(By.id("LookupSelectofService")).click();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.cssSelector(".slds-list--vertical.vlc-slds-list--vertical")).findElements(By.cssSelector(".slds-list__item.ng-binding.ng-scope")).get(0).click();
		WebElement BenBoton = driver.findElement(By.id("SelectServiceStep_nextBtn"));
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+BenBoton.getLocation().y+")");
		BenBoton.click();
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> Emergente= driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope"));
		Emergente.get(1).click();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("SelectedMotivesLookup")).click();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> Motivos = driver.findElements(By.cssSelector(".slds-list--vertical.vlc-slds-list--vertical")).get(1).findElements(By.cssSelector(".slds-list__item.ng-binding.ng-scope"));
		for (WebElement UnMot : Motivos) {
			if(UnMot.getText().toLowerCase().equals("internet funciona lento o se cae")) {
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+UnMot.getLocation().y+")");
			      UnMot.click();
			}
			
		}
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("IntegProc_Diagnóstico")).click();
		//debe ser completado pero por ahora el sistema no anda como deberia...!!!!!!
	}
	
	
	@Test(groups = "Fase2")
	  public void TS11600_CRM_Fase_2_Technical_Care_CSR_Diagnostico_Servicio_Indiferente_Boton_ejecutar_no_disponible(){
	  driver.switchTo().defaultContent();
	  try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    BasePage pagina = new BasePage (driver);
	    driver.switchTo().frame(pagina.getFrameForElement(driver, By.id("LookupSelectofService")));
	    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    WebElement BenBoton = driver.findElement((By.id("LookupSelectofService")));
	    BenBoton.click();
	    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    JavascriptExecutor js = (JavascriptExecutor)driver;
	  js.executeScript("document.getElementsByClassName('slds-list__item ng-binding ng-scope')[0].click();");
	  WebElement Continuar = driver.findElement((By.id("SelectServiceStep_nextBtn")));
	  try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	  Continuar.click();
	  try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	  List<WebElement> Emergente= driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope"));
	  Emergente.get(1).click();
	  
	  try {
	  if(driver.findElement((By.id("IntegProc_Diagnóstico"))).isDisplayed())
	    System.out.println("Test Fail");
	  }
	  catch (org.openqa.selenium.NoSuchElementException e) {System.out.println("Test OK!"); }
	  
	  }

}
