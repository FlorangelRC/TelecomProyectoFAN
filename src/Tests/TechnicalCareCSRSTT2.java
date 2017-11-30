package Tests;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.BasePage;
import Pages.HomeBase;
import Pages.setConexion;

public class TechnicalCareCSRSTT2 extends TestBase {
	
		private WebDriver driver;
		private String validIMEI = "545229703256596";
		private String Ngestion;
		
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
	     //driver.switchTo().frame(driver.findElement(By.xpath("//iframe")));
	     accountPage.accountSelect("Vista Tech");
	     try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	     //select accountName "Robo Tech", currently has index 10.
	     accountPage.selectAccountByName("Robo Tech");
	     try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}            
	     if(accountPage.isTabOpened("Servicio Técnico")) {
	         System.out.println("Tab Opened.");
	         accountPage.goToTab("Servicio Técnico");
	     }else {
	         accountPage.findAndClickButton("Servicio Técnico");
	     }
	     try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			accountPage.fillIMEI(validIMEI);
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			accountPage.continueFromImeiInput();
			try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			accountPage.continueFromClientInfo();
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			accountPage.selectOperationType("Consulta");
			accountPage.selectSymptomByIndex(2);
			driver.findElement(By.id("TextAreaNotes")).sendKeys("No funciona todo el tiempo");
			accountPage.continueFromSymptoms();
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().frame(accountPage.getFrameForElement(driver, By.id("TicketCreation_prevBtn")));
			Ngestion= driver.findElement(By.id("TicketConfirmationText")).findElement(By.tagName("Strong")).getText();
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.findElements(By.cssSelector(".slds-radio.ng-scope")).get(1).click();
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("TicketCreation_nextBtn")).getLocation().y+")");
			driver.findElement(By.id("TicketCreation_nextBtn")).click();
	 }
		@AfterMethod(groups = "Fase2") 
		 public void afterMethod() {
			BasePage bP = new BasePage(driver);
			driver.switchTo().defaultContent();
			List<WebElement> ctas = driver.findElement(By.cssSelector(".x-tab-strip.x-tab-strip-top")).findElements(By.tagName("li"));
			ctas.remove(0);
			for (WebElement cta : ctas) {
				if (cta.findElement(By.className("tabText")).getText().equals("Robo Tech")) {
					Actions action = new Actions(driver);
					action.moveToElement(cta);
					action.moveToElement(cta.findElement(By.className("x-tab-strip-close"))).click().build().perform();
					break;
				}
					
			}
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			
			  }
		
		@AfterClass
		public void tearDown() {
			BasePage basePage = new BasePage();
			basePage.switchAppsMenu();
			basePage.selectAppFromMenuByName("Ventas");
			driver.close();
		}
		
		@Test(groups = "Fase2") 
		public void TS16345_CRM_Fase2_TechnicalCare_CSR_STT2_Vista1_Visualizacion_Del_Comentario_Anterior() {
			Accounts accPage = new Accounts(driver);
			driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("TicketSummaryTechnician_nextBtn"))); 
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			List<WebElement> campos= driver.findElement(By.id("TicketSummaryGrid")).findElement(By.cssSelector(".slds-grid.slds-wrap.slds-theme--default.taOScard-header")).findElements(By.cssSelector(".slds-tile__detail.slds-text-heading--small"));
			campos= driver.findElement(By.id("TicketSummaryGrid")).findElement(By.cssSelector(".slds-grid.slds-wrap.slds-theme--default.taOScard-content")).findElements(By.cssSelector(".slds-tile__detail.slds-text-heading--small"));
			//comentarios
			assertTrue(campos.get(2).getText().equals("No funciona todo el tiempo"));
		}
		
		@Test(groups = "Fase2") 
		public void TS16160_CRM_Fase2_TechnicalCare_CSR_STT2_Vista1_Mantención_de_datos() {
			Accounts accPage = new Accounts(driver);
			driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("TicketSummaryTechnician_nextBtn"))); 
			List<WebElement> campos= driver.findElement(By.id("TicketSummaryGrid")).findElement(By.cssSelector(".slds-grid.slds-wrap.slds-theme--default.taOScard-header")).findElements(By.cssSelector(".slds-tile__detail.slds-text-heading--small"));
			//imei
			assertTrue(campos.get(0).getText().equals(validIMEI));
			//nro de gestion
			assertTrue(!campos.get(1).getText().isEmpty());
			campos= driver.findElement(By.id("TicketSummaryGrid")).findElement(By.cssSelector(".slds-grid.slds-wrap.slds-theme--default.taOScard-content")).findElements(By.cssSelector(".slds-tile__detail.slds-text-heading--small"));
			//tipo de operacion
			assertTrue(campos.get(0).getText().equals("Consulta"));
			//sintoma
			assertTrue(campos.get(1).getText().equals("Baja señal"));
			//comentarios
			assertTrue(!campos.get(2).getText().isEmpty());
		}
		
		@Test(groups = "Fase2") 
		public void TS16161_CRM_Fase2_TechnicalCare_CSR_STT2_Vista1_Mantención_De_Datos_Solo_Lectura() {
			Accounts accPage = new Accounts(driver);
			driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("TicketSummaryTechnician_nextBtn"))); 
			List<WebElement> campos= driver.findElement(By.id("TicketSummaryGrid")).findElement(By.cssSelector(".slds-grid.slds-wrap.slds-theme--default.taOScard-header")).findElements(By.cssSelector(".slds-tile__detail.slds-text-heading--small"));
			//imei
			assertTrue(campos.get(0).findElement(By.className("ng-binding")).getTagName().equals("p"));
			//nro de gestion
			assertTrue(campos.get(1).findElement(By.className("ng-binding")).getTagName().equals("p"));
			campos= driver.findElement(By.id("TicketSummaryGrid")).findElement(By.cssSelector(".slds-grid.slds-wrap.slds-theme--default.taOScard-content")).findElements(By.cssSelector(".slds-tile__detail.slds-text-heading--small"));
			//tipo de operacion
			assertTrue(campos.get(0).findElement(By.className("ng-binding")).getTagName().equals("p"));
			//sintoma
			assertTrue(campos.get(1).findElement(By.className("ng-binding")).getTagName().equals("p"));	
			//comentarios
			assertTrue(campos.get(2).findElement(By.className("ng-binding")).getTagName().equals("p"));
		}
		
		@Test(groups = "Fase2") 
		public void TS16144_CRM_Fase2_TechnicalCare_CSR_STT2_Vista1_Resumen_De_Gesiton_creada() {
			Accounts accPage = new Accounts(driver);
			driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("TicketSummaryTechnician_nextBtn"))); 
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			
			List<WebElement> campos= driver.findElement(By.id("TicketSummaryGrid")).findElement(By.cssSelector(".slds-grid.slds-wrap.slds-theme--default.taOScard-header")).findElements(By.cssSelector(".slds-tile__detail.slds-text-heading--small"));
			//imei
			System.out.println("I"+campos.get(0).getText()+"F");
			assertTrue(campos.get(0).findElement(By.className("ng-binding")).getText().equals(validIMEI));
			//nro de gestion
			assertTrue(campos.get(1).getText().equals(Ngestion));
			campos= driver.findElement(By.id("TicketSummaryGrid")).findElement(By.cssSelector(".slds-grid.slds-wrap.slds-theme--default.taOScard-content")).findElements(By.cssSelector(".slds-tile__detail.slds-text-heading--small"));
			//tipo de operacion
			assertTrue(campos.get(0).getText().equals("Consulta"));
			//sintoma
			assertTrue(campos.get(1).getText().equals("Baja señal"));
			//comentarios
			assertTrue(campos.get(2).getText().equals("No funciona todo el tiempo"));
		}
		
		@Test(groups = "Fase2") 
		public void TS16141_CRM_Fase2_TechnicalCare_CSR_STT2_Vista3_Descarga() {
			Accounts accPage = new Accounts(driver);
			driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("TicketSummaryTechnician_nextBtn"))); 
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("TicketSummaryTechnician_nextBtn")).getLocation().y+")");
			driver.findElement(By.id("TicketSummaryTechnician_nextBtn")).click();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.findElement(By.id("RadioRepairType|0")).findElements(By.cssSelector(".slds-radio.ng-scope")).get(1).click();
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("DeferredRepair_nextBtn")).getLocation().y+")");
			driver.findElement(By.id("DeferredRepair_nextBtn")).click();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.findElement(By.cssSelector(".slds-button.slds-button--neutral.TechCare-DownloadPDF-Btn")).click();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().window("Servicio Técnico - Consola");
		}
		
		@Test(groups = "Fase2") 
		public void TS16150_CRM_Fase2_TechnicalCare_CSR_STT2_Vista5_Listar_Todas_Las_Reparaciones() {
			Accounts accPage = new Accounts(driver);
			driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("TicketSummaryTechnician_nextBtn"))); 
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("TicketSummaryTechnician_nextBtn")).getLocation().y+")");
			driver.findElement(By.id("TicketSummaryTechnician_nextBtn")).click();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.findElement(By.id("RadioRepairType|0")).findElements(By.cssSelector(".slds-radio.ng-scope")).get(1).click();
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("DeferredRepair_nextBtn")).getLocation().y+")");
			driver.findElement(By.id("DeferredRepair_nextBtn")).click();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("PDFTicketSummary_nextBtn")).getLocation().y+")");
			driver.findElement(By.id("PDFTicketSummary_nextBtn")).click();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			List<WebElement> campos= driver.findElement(By.cssSelector(".slds-table.slds-table--bordered.slds-table--cell-buffer.techCare-PriceListSelection.ng-scope")).findElements(By.className("slds-checkbox"));
			for (WebElement camp : campos) {
				camp.click();
			}
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("SolutionList_nextBtn")).getLocation().y+")");
			driver.findElement(By.id("SolutionList_nextBtn")).click();
			campos = driver.findElement(By.id("PriceListSelectionSummary")).findElements(By.className("ng-scope"));
			for (WebElement camp : campos) {
				assertTrue(camp.findElements(By.className("ng-binding")).get(0).isDisplayed());
				assertTrue(camp.findElements(By.className("ng-binding")).get(1).isDisplayed());
				assertTrue(camp.findElements(By.className("ng-binding")).get(2).isDisplayed());
			}
		
		}
		
		@Test(groups = "Fase2") 
		public void TS16138_CRM_Fase2_TechnicalCare_CSR_STT2_Vista5_Comentario_Error() {
			Accounts accPage = new Accounts(driver);
			driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("TicketSummaryTechnician_nextBtn"))); 
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("TicketSummaryTechnician_nextBtn")).getLocation().y+")");
			driver.findElement(By.id("TicketSummaryTechnician_nextBtn")).click();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.findElement(By.id("RadioRepairType|0")).findElements(By.cssSelector(".slds-radio.ng-scope")).get(1).click();
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("DeferredRepair_nextBtn")).getLocation().y+")");
			driver.findElement(By.id("DeferredRepair_nextBtn")).click();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("PDFTicketSummary_nextBtn")).getLocation().y+")");
			driver.findElement(By.id("PDFTicketSummary_nextBtn")).click();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("SolutionList_nextBtn")).getLocation().y+")");
			driver.findElement(By.id("SolutionList_nextBtn")).click();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.findElement(By.id("SummaryNotes")).sendKeys("Roberto is a veteran who is characterised by orderliness and a firm belief in the value of control. He runs his own hardware store accordingly. If a supplier sells him boxes with 100 screws each, he counts all the screws and files a complaint if just a single one is missing. He feels that the world around his isle of neatness has gone mad.");
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-area.ng-scope.ng-valid-minlength.ng-valid-required.ng-dirty.ng-valid-parse.ng-invalid.ng-invalid-maxlength")).findElement(By.cssSelector(".vlc-slds-error-block.ng-scope")).findElement(By.cssSelector(".error.ng-scope")).getText().toLowerCase().equals("Longitud máxima de 255".toLowerCase()));
		}
}
