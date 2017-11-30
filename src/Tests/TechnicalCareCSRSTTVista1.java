package Tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import Pages.Accounts;
import Pages.BasePage;
import Pages.CustomerCare;
import Pages.HomeBase;
import Pages.setConexion;


public class TechnicalCareCSRSTTVista1  extends TestBase {
	private WebDriver driver;
	private String validIMEI = "545229703256596";
	private String sinTniE = "356514072350581";
	
	@BeforeClass
	public void init() throws Exception
	{
		this.driver = setConexion.setupPablo();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		login(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}

	@BeforeMethod
	public void setUp() throws Exception {
		HomeBase homePage = new HomeBase(driver);
	       if(driver.findElement(By.id("tsidLabel")).getText().equals("Consola FAN")) {
	        homePage.switchAppsMenu();
	        try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	        homePage.selectAppFromMenuByName("Ventas");
	        try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}    
	       }
	       homePage.switchAppsMenu();
	       try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	       homePage.selectAppFromMenuByName("Consola FAN");
	  
	       CustomerCare cerrar = new CustomerCare(driver);
			cerrar.cerrarultimapestaña();
			
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
     //accountPage.selectAccountByName("Robo Tech");
     
     List<WebElement> accounts = driver.findElements(By.xpath("//*[text() = 'Adrian Tech']"));
		accounts.get(0).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
     
     try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}            
     /*if(accountPage.isTabOpened("Servicio Técnico")) {
         System.out.println("Tab Opened.");
         accountPage.goToTab("Servicio Técnico");
     }else {
         accountPage.findAndClickButton("Servicio Técnico");
     }*/
     
   //selecciona el campo donde esta la busquedad del imput y Escribe
   		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   		BasePage sImput=new BasePage();
   		List<WebElement> frame1 = driver.findElements(By.tagName("iframe"));
   		int indexFrame = sImput.getIndexFrame(driver, By.xpath("/html/body/div/div[1]/ng-include/div/div[1]/ng-include/div/div[2]/input"));
   		driver.switchTo().frame(frame1.get(indexFrame));
   		WebElement elemento = driver.findElement(By.xpath("/html/body/div/div[1]/ng-include/div/div[1]/ng-include/div/div[2]/input"));
   		elemento.sendKeys("servicio t");
   		//Click en el resultado Eventos Masivos
   		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   		WebElement eMasivos= driver.findElement(By.xpath("//*[text() = 'Servicio Técnico']"));
   		eMasivos.click();
     
     
     try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
 }
	
	@AfterClass
	public void tearDown() {
		driver.switchTo().defaultContent();
		   driver.findElement(By.id("tsidButton")).click();
		   List<WebElement> options = driver.findElement(By.id("tsid-menuItems")).findElements(By.tagName("a"));

		   for (WebElement option : options) {
		    if(option.getText().toLowerCase().equals("Ventas".toLowerCase())){
		     option.click();
		     return;
		    }
		   }
		   try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.quit();
	}
	
	@AfterMethod
	public void closeTechCareTab() {
		BasePage bP = new BasePage(driver);
		driver.switchTo().defaultContent();
		List<WebElement> ctas = driver.findElement(By.cssSelector(".x-tab-strip.x-tab-strip-top")).findElements(By.tagName("li"));
		ctas.remove(0);
		for (WebElement cta : ctas) {
			if (cta.findElement(By.className("tabText")).getText().equals("Adrian Tech")) {
				Actions action = new Actions(driver);
				action.moveToElement(cta);
				action.moveToElement(cta.findElement(By.className("x-tab-strip-close"))).click().build().perform();
				break;
			}	
		}
	}
	
	@Test(groups = "Fase2") 
	public void TS16173_STT_Vista0_Invalido_Caracteres_Especiales() {
		Accounts accPage = new Accounts(driver);
		accPage.fillIMEI("120*+3%-");
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromImeiInput();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		assertTrue(driver.findElement(By.id("alert-container")).isDisplayed());

	}
	
	@Test(groups = "Fase2")
	public void TS16344_STT_Ingreso() {
		Accounts accPage = new Accounts(driver);
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("ImeiCode")));
		assertTrue(driver.findElement(By.id("ImeiCode")).isDisplayed());
	}
	
	@Test(groups = "Fase2") 
	public void TS16178_STT_Vista_3_Opcion_1_verificacion_Obligatorio() {
		Accounts accPage = new Accounts(driver);
		accPage.fillIMEI(validIMEI);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromImeiInput();
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromClientInfo();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.selectOperationType("Consulta");
		accPage.selectSymptomByIndex(2);
		accPage.continueFromSymptoms();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("TicketCreation_prevBtn")));
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElements(By.cssSelector(".slds-radio.ng-scope")).get(0).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("TicketCreation_nextBtn")).getLocation().y+")");
	    driver.findElement(By.id("TicketCreation_nextBtn")).click();
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		assertTrue(driver.findElement(By.id("alert-container")).isDisplayed());
	}
	
	@Test(groups = "Fase2") 
	public void TS16181_STT_Vista_3_Opcion_2_NO_Obligatorio() {
		Accounts accPage = new Accounts(driver);
		accPage.fillIMEI(validIMEI);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromImeiInput();
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromClientInfo();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.selectOperationType("Consulta");
		accPage.selectSymptomByIndex(2);
		accPage.continueFromSymptoms();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("TicketCreation_prevBtn")));
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElements(By.cssSelector(".slds-radio.ng-scope")).get(1).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("TicketCreation_nextBtn")).getLocation().y+")");
	    driver.findElement(By.id("TicketCreation_nextBtn")).click();
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("TicketSummaryTechnician_nextBtn")));
		assertTrue(driver.findElement(By.id("TicketSummaryTechnician_nextBtn")).isDisplayed());
	}
	
	@Test(groups = "Fase2") 
	public void TS16184_STT_Vista_3_Opcion_3_NO_Obligatorio() {
		Accounts accPage = new Accounts(driver);
		accPage.fillIMEI(validIMEI);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromImeiInput();
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromClientInfo();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.selectOperationType("Consulta");
		accPage.selectSymptomByIndex(2);
		accPage.continueFromSymptoms();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("TicketCreation_prevBtn")));
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElements(By.cssSelector(".slds-radio.ng-scope")).get(2).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("TicketCreation_nextBtn")).getLocation().y+")");
	    driver.findElement(By.id("TicketCreation_nextBtn")).click();
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("DerivatedTicketText")));
		assertTrue(driver.findElement(By.id("DerivatedTicketText")).getText().contains("ha sido derivada al servicio técnico."));
	}
	
	
	@Test(groups = "Fase2") 
	public void TS16350_Vista1_Garantia() {
		Accounts accPage = new Accounts(driver);
		accPage.fillIMEI(validIMEI);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromImeiInput();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("ClientInformation_nextBtn"))); 
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
		List<WebElement> campos= driver.findElement(By.id("SelectableItemsMobile")).findElements(By.cssSelector(".slds-tile__detail.slds-text-heading--small"));
		//Garantia Venta(SI/NO) 
		assertTrue(campos.get(4).getText().equals("SI") || campos.get(4).getText().equals("NO"));
		//fecha desde hasta con formato dd/mm/aa
		String datePattern = "\\d{2}/\\d{2}/\\d{4}";
		if (campos.get(4).getText().equals("SI")){
			assertTrue(campos.get(5).findElements(By.className("ng-binding")).get(0).getText().contains("Desde"));
			assertTrue(campos.get(5).findElements(By.className("ng-binding")).get(0).getText().split(" ")[1].matches(datePattern));
			assertTrue(campos.get(5).findElements(By.className("ng-binding")).get(1).getText().contains("Hasta"));
			assertTrue(campos.get(5).findElements(By.className("ng-binding")).get(1).getText().split(" ")[1].matches(datePattern));
		}
			//Garantia Service (SI/NO)
		assertTrue(campos.get(6).getText().equals("SI") || campos.get(6).getText().equals("NO"));
		//fecha desde hasta ocn formato dd/mm/aa
		if (campos.get(6).getText().equals("SI")){
			assertTrue(campos.get(7).findElements(By.className("ng-binding")).get(0).getText().contains("Desde"));
			assertTrue(campos.get(7).findElements(By.className("ng-binding")).get(0).getText().split(" ")[1].matches(datePattern));
			assertTrue(campos.get(7).findElements(By.className("ng-binding")).get(1).getText().contains("Hasta"));
			assertTrue(campos.get(7).findElements(By.className("ng-binding")).get(1).getText().split(" ")[1].matches(datePattern));
		}
			
		
	}

		

	/*@Test(groups = "Fase2") 
	public void TS16169_STT_Terminal() {
		Accounts accPage = new Accounts(driver);
		accPage.fillIMEI(validIMEI);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromImeiInput();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("ClientInformation_nextBtn"))); 
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
		List<WebElement> campos= driver.findElement(By.id("SelectableItemsMobile")).findElements(By.cssSelector(".slds-tile__detail.slds-text-heading--small"));
		//imei
		assertTrue(!campos.get(0).getText().isEmpty());
		//nmu
		assertTrue(!campos.get(1).getText().isEmpty());
		//Descripcion y fecha de venta existen en comentarios, pero no estan implementados por lo tanto no se visualizas ni se pueden probar
		//Garantia Venta(SI/NO) 
		assertTrue(campos.get(4).getText().equals("SI") || campos.get(4).getText().equals("NO"));
		//fecha desde hasta con formato dd/mm/aa
		String datePattern = "\\d{2}/\\d{2}/\\d{4}";
		if (campos.get(4).getText().equals("SI")){
			assertTrue(campos.get(5).findElements(By.className("ng-binding")).get(0).getText().contains("Desde"));
			assertTrue(campos.get(5).findElements(By.className("ng-binding")).get(0).getText().split(" ")[1].matches(datePattern));
			assertTrue(campos.get(5).findElements(By.className("ng-binding")).get(1).getText().contains("Hasta"));
			assertTrue(campos.get(5).findElements(By.className("ng-binding")).get(1).getText().split(" ")[1].matches(datePattern));
		}
			//Garantia Service (SI/NO)
		assertTrue(campos.get(6).getText().equals("SI") || campos.get(6).getText().equals("NO"));
		//fecha desde hasta ocn formato dd/mm/aa
		if (campos.get(6).getText().equals("SI")){
			assertTrue(campos.get(7).findElements(By.className("ng-binding")).get(0).getText().contains("Desde"));
			assertTrue(campos.get(7).findElements(By.className("ng-binding")).get(0).getText().split(" ")[1].matches(datePattern));
			assertTrue(campos.get(7).findElements(By.className("ng-binding")).get(1).getText().contains("Hasta"));
			assertTrue(campos.get(7).findElements(By.className("ng-binding")).get(1).getText().split(" ")[1].matches(datePattern));
		}
			//Marca
		assertTrue(!campos.get(2).getText().isEmpty());
		//modelo
		assertTrue(!campos.get(3).getText().isEmpty());
		//stock de uso
		assertTrue(!campos.get(8).getText().isEmpty());
		//partida
		assertTrue(!campos.get(9).getText().isEmpty());
		//Boton para consultar el historial de Reparaciones
		//no existe el boton de consultar historial de reparaciones
		//Proteccion. (El cual contendrá como valor, si tiene protección el TIPO DE PROTECCIÓN, si no posee mostrará la leyenda "NO POSEE")
		assertTrue(campos.get(10).getText().equals("NO POSEE") || !campos.get(10).getText().isEmpty());
	}*/
		
	/*@Test(groups = "Fase2") 
	public void TS16170_STT_Cliente() {
		Accounts accPage = new Accounts(driver);
		accPage.fillIMEI(validIMEI);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromImeiInput();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("ClientInformation_nextBtn"))); 
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//verificar si debo validar nombre y apellido por separado
		assertTrue(!driver.findElement(By.cssSelector(".slds-text-heading--label.taOScard-title.ng-binding")).getText().isEmpty());
		//[0] tipo de doc, [1] numero de doc
		assertTrue(!driver.findElement(By.id("SelectableItemsClient")).findElement(By.cssSelector(".slds-tile__detail.slds-text-heading--small")).findElement(By.className("ng-binding")).getText().split(" ")[0].isEmpty());
		assertTrue(!driver.findElement(By.id("SelectableItemsClient")).findElement(By.cssSelector(".slds-tile__detail.slds-text-heading--small")).findElement(By.className("ng-binding")).getText().split(" ")[1].isEmpty());  
		//razon social
		assertTrue(!driver.findElement(By.id("SelectableItemsClient")).findElements(By.cssSelector(".slds-tile.slds-p-bottom--medium.slds-col--padded.slds-size--1-of-2")).get(1).findElement(By.tagName("span")).getText().isEmpty());
		
		List<WebElement> campos = driver.findElement(By.id("SelectableItemsClient")).findElement(By.cssSelector(".slds-grid.slds-wrap.slds-theme--default.taOScard-content")).findElements(By.cssSelector(".slds-tile__detail.slds-text-heading--small"));
		//email
		assertTrue(!campos.get(0).getText().isEmpty());
		//telefono de contacto
		assertTrue(!campos.get(1).getText().isEmpty());
		//mercado
		assertTrue(!campos.get(3).getText().isEmpty());
	}*/
		

	
	@Test(groups = "Fase2")
	public void TS11625_SST_Servicio_Indiferente_Adjuntar_Dos_Archivos() {
		Accounts accPage = new Accounts(driver);
		String filePath = "C:\\Users\\Florangel\\Downloads\\nosignal.jpg";
		String filePath2 = "C:\\Users\\Florangel\\Downloads\\No-signal.jpg";
		accPage.fillIMEI(validIMEI);
		accPage.continueFromImeiInput();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}			
		accPage.continueFromClientInfo();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.selectOperationType("Consulta");
		accPage.selectSymptomByIndex(2);
		accPage.attachFile(filePath);
		accPage.attachFile(filePath2);
		accPage.continueFromSymptoms();
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("TicketCreation_prevBtn")));
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		assertTrue(driver.findElement(By.id("TicketConfirmationText")).isDisplayed());
}
	
	@Test(groups = "Fase2") 
	public void TS16208_STT_Vista_Historial_De_Visitas() {
		String datePattern = "\\d{2}/\\d{2}/\\d{4}";
		Accounts accPage = new Accounts(driver);
		accPage.fillIMEI(validIMEI);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromImeiInput();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("ClientInformation_nextBtn")));
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement BenBoton = driver.findElement(By.id("ExtractRepairHistory"));
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+BenBoton.getLocation().y+")");
		BenBoton.click();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
		WebElement rep = driver.findElement(By.className("techCare-RepairHistory-tbody"));
		List<WebElement> DethRep = rep.findElements(By.tagName("th"));
		List<WebElement> DetdRep = rep.findElements(By.tagName("td"));
		//Fecha (formato dd/mm/yyyy)
		assertTrue(DetdRep.get(0).getText().matches(datePattern));
		//Tipo de operacion
		assertTrue(DethRep.get(1).isDisplayed());
		//Soluciones
		assertTrue(DethRep.get(0).isDisplayed());
		//Gestión
		assertTrue(DetdRep.get(1).isDisplayed());
		//Estado de la gestion
		assertTrue(DethRep.get(2).isDisplayed());
	}

	@Test(groups = "Fase2") 
	public void TS16198_STT_Telefono_Alternativo_Valido() {
		Accounts accPage = new Accounts(driver);
		accPage.fillIMEI(validIMEI);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromImeiInput();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("ClientInformation_nextBtn")));
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("AlternativePhone")).sendKeys("1125116113");
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-tel.ng-scope.ng-valid-minlength.ng-valid-maxlength.ng-valid-required.ng-dirty.ng-valid-parse.ng-valid.ng-valid-pattern"));
	//verificar si ajuro debo presionar continuar
	}
	
	
	@Test(groups = "Fase2") 
	public void TS16204_STT_Telefono_Alternativo_Invalido_Caracter_Especial() {
		Accounts accPage = new Accounts(driver);
		accPage.fillIMEI(validIMEI);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromImeiInput();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("ClientInformation_nextBtn")));
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("AlternativePhone")).sendKeys("12-4875*");
		accPage.continueFromClientInfo();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		assertTrue(driver.findElement(By.id("alert-container")).isDisplayed());
	}
	
	@Test(groups = "Fase2") 
	public void TS16202_STT_Telefono_Alternativo_Vacio_No_Obligatorio() {
		Accounts accPage = new Accounts(driver);
		accPage.fillIMEI(validIMEI);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromImeiInput();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("ClientInformation_nextBtn")));
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromClientInfo();
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		assertTrue(accPage.getFrameForElement(driver, By.id("ExtractPriceList")).isDisplayed());
	}
	
	@Test(groups = "Fase2") 
	public void TS16203_STT_Telefono_Alternativo_Invalido_Letras() {
		Accounts accPage = new Accounts(driver);
		accPage.fillIMEI(validIMEI);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromImeiInput();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("ClientInformation_nextBtn")));
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("AlternativePhone")).sendKeys("564897ABC");
		accPage.continueFromClientInfo();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		assertTrue(driver.findElement(By.id("alert-container")).isDisplayed());
	}
	
	@Test(groups = "Fase2")
	 public void TS11619_SST_Servicio_Indiferente_Comentario_valido() {
	  Accounts accPage = new Accounts(driver);
	  accPage.fillIMEI(validIMEI);
	  accPage.continueFromImeiInput();
	  try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}   
	  accPage.continueFromClientInfo();
	  try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	  accPage.selectOperationType("Consulta");
	  accPage.selectSymptomByIndex(2);
	  driver.findElement(By.id("TextAreaNotes")).sendKeys("problemas con el servicio");
	  accPage.continueFromSymptoms();
	  try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	  driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("TicketCreation_prevBtn")));
	  assertTrue(driver.findElement(By.id("TicketCreation_prevBtn")).isDisplayed());
	 }
	
	@Test(groups = "Fase2") 
	public void TS16196_STT_Telefono_Alternativo_Vista() {
		Accounts accPage = new Accounts(driver);
		accPage.fillIMEI(validIMEI);
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromImeiInput();
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("ClientInformation_nextBtn")));
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		assertTrue(driver.findElement(By.id("AlternativePhone")).isDisplayed());
	}
	
	@Test(groups = "Fase2") 
	public void TS16189_STT_Mail_Alternativo_Vista() {
		Accounts accPage = new Accounts(driver);
		accPage.fillIMEI(validIMEI);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromImeiInput();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("ClientInformation_nextBtn")));
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		assertTrue(driver.findElement(By.id("AlternativeEmail")).isDisplayed());
	}
	
	@Test(groups = "Fase2") 
	public void TS16190_STT_Mail_Valido() {
		Accounts accPage = new Accounts(driver);
		accPage.fillIMEI(validIMEI);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromImeiInput();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("ClientInformation_nextBtn")));
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("AlternativeEmail")).sendKeys("roboAlt@algo.com");
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-valid-required.ng-dirty.ng-valid.ng-valid-email"));
	}
	
	@Test(groups = "Fase2") 
	public void TS16191_STT_Mail_Invalido_Sin_Dominio() {
		Accounts accPage = new Accounts(driver);
		accPage.fillIMEI(validIMEI);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromImeiInput();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("ClientInformation_nextBtn")));
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("AlternativeEmail")).sendKeys("roboAlt");
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromClientInfo();
		assertTrue(driver.findElement(By.id("alert-container")).isDisplayed());
	}
	
	@Test(groups = "Fase2") 
	public void TS16192_STT_Mail_Invalido_Con_Dominio() {
		Accounts accPage = new Accounts(driver);
		accPage.fillIMEI(validIMEI);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromImeiInput();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("ClientInformation_nextBtn")));
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("AlternativeEmail")).sendKeys("*@gmail.com");
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromClientInfo();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		assertTrue(driver.findElement(By.id("alert-container")).isDisplayed());
	}
	
	@Test(groups = "Fase2") 
	public void TS16200_STT_No_Agrega_Telefono_Alternativo() {
		Accounts accPage = new Accounts(driver);
		accPage.fillIMEI(sinTniE);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromImeiInput();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("RemoteActionDeviceWarranty")));
		driver.findElement(By.id("RemoteActionDeviceWarranty")).findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")).get(1).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("ClientInformation_nextBtn")));
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("AlternativeEmail")).sendKeys("unoAlternativo@alternativo.com");
		driver.findElement(By.id("AlternativePhone")).sendKeys("1125116113");
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromClientInfo();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.selectOperationType("Consulta");
		accPage.selectSymptomByIndex(2);
		accPage.continueFromSymptoms();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("TicketCreation_prevBtn")));
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElements(By.cssSelector(".slds-radio.ng-scope")).get(1).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("TicketCreation_nextBtn")).getLocation().y+")");
		driver.findElement(By.id("TicketCreation_nextBtn")).click();
	}
	
	@Test(groups = "Fase2") 
	public void TS16199_STT_No_Agrega_Mail_Alternativo() {
		Accounts accPage = new Accounts(driver);
		accPage.fillIMEI(sinTniE);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromImeiInput();
		driver.switchTo().defaultContent();
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("RemoteActionDeviceWarranty")));
		driver.findElement(By.id("RemoteActionDeviceWarranty")).findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")).get(1).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("ClientInformation_nextBtn")));
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("AlternativeEmail")).sendKeys("unoAlternativo@alternativo.com");
		driver.findElement(By.id("AlternativePhone")).sendKeys("1125116113");
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.continueFromClientInfo();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.selectOperationType("Consulta");
		accPage.selectSymptomByIndex(2);
		accPage.continueFromSymptoms();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("TicketCreation_prevBtn")));
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElements(By.cssSelector(".slds-radio.ng-scope")).get(1).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("TicketCreation_nextBtn")).getLocation().y+")");
		driver.findElement(By.id("TicketCreation_nextBtn")).click();
	}
	
	@Test(groups = "Fase2")
	public void TS11618_SST_Servicio_Indiferente_Comentario_Error() {
		Accounts accPage = new Accounts(driver);
		accPage.fillIMEI(validIMEI);
		accPage.continueFromImeiInput();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}			
		accPage.continueFromClientInfo();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accPage.selectOperationType("Consulta");
		accPage.selectSymptomByIndex(2);
		driver.findElement(By.id("TextAreaNotes")).sendKeys("Roberto is a veteran who is characterised by orderliness and a firm belief in the value of control. He runs his own hardware store accordingly. If a supplier sells him boxes with 100 screws each, he counts all the screws and files a complaint if just a single one is missing. He feels that the world around his isle of neatness has gone mad.");
		assertTrue(driver.findElements(By.cssSelector(".vlc-slds-error-block.ng-scope")).get(5).findElements(By.tagName("small")).get(2).getText().equals("Longitud Máxima De 255"));
	}


	

}