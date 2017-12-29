package Tests;


import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.BasePage;
import Pages.CustomerCare;
import Pages.HomeBase;
import Pages.setConexion;

public class CustomerCareFase3F  extends TestBase {
private WebDriver driver;
	
	//@AfterClass(groups= "CustomerCare")
	public void tearDown2() {
		driver.quit();	
	}
	
	@BeforeClass(groups= "CustomerCare")
	public void init() throws Exception
	
	{
		this.driver = setConexion.setupEze();
		login(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		HomeBase homePage = new HomeBase(driver);
		Accounts accountPage = new Accounts(driver);
	     if(driver.findElement(By.id("tsidLabel")).getText().equals("Consola FAN")) { 
	    	 homePage.switchAppsMenu();
	    	 try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	 homePage.selectAppFromMenuByName("Ventas");
	    	 try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}    
	     }
	     homePage.switchAppsMenu();
	     try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	     homePage.selectAppFromMenuByName("Consola FAN");
	    
	     try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
	     goToLeftPanel2(driver, "Cuentas");
		 driver.switchTo().defaultContent();
		 driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".topNav.primaryPalette")));
		 Select field = new Select(driver.findElement(By.name("fcf")));
		 try {field.selectByVisibleText("Todas Las cuentas");}
		 catch (org.openqa.selenium.NoSuchElementException ExM) {field.selectByVisibleText("Todas las cuentas");}
		 
	     
			
	}	
	
	@BeforeMethod(groups = "CustomerCare") 
	public void setUp() throws Exception {
		CustomerCare page = new CustomerCare(driver);
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
	     page.cerrarultimapestaña();
		 try {Thread.sleep(12000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
			 page.elegircuenta("aaaaFernando Care");
			 try {Thread.sleep(12000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
			 BasePage cambioFrameByID=new BasePage();
			 driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".console-card.active")));
		 try {Thread.sleep(9000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
		 
	}
	
	@Test(groups= "CustomerCare")
	public void TS37501_Historial_De_Recargas_PrePago_Monto_Total_De_Recargas_Visualizar_Monto_Total_De_Recargas() {
		BasePage cambioFrameByID=new BasePage(); 
		driver.switchTo().defaultContent();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".console-card.active")));
		 driver.findElement(By.className("card-info")).findElements(By.tagName("li")).get(1).click();
		 try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
		driver.switchTo().defaultContent();
		 driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".via-slds.slds-m-around--small.ng-scope")));
		 List<WebElement> historiales = driver.findElements(By.cssSelector(".via-slds.slds-m-around--small.ng-scope"));
		 for (WebElement UnH : historiales) {
			 if (UnH.findElement(By.cssSelector(".slds-text-heading--large.slds-size--1-of-1.slds-medium-size--1-of-1.slds-large-size--1-of-1.slds-m-bottom--small")).findElement(By.tagName("p")).getText().toLowerCase().equals("historial de recargas")) {
				 List<WebElement> tuplas = driver.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
				 for (WebElement UnaT : tuplas) {
					 if (UnaT.findElements(By.tagName("td")).get(3).findElement(By.tagName("b")).getText().isEmpty())
						 assertTrue(false);
				 }
				 break;
			 }
		 }
		 Accounts accountPage = new Accounts(driver);
		 accountPage.closeAccountServiceTabByName("Historiales");	
	}
	
	@Test(groups= "CustomerCare")
	public void TS37508_Historial_De_Recargas_PrePago_Ordenamiento_Y_Paginado_De_Grilla_Ordenar_Por_Beneficios() {
		boolean enc = false;
		BasePage cambioFrameByID=new BasePage();
		driver.switchTo().defaultContent();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".console-card.active")));
		 driver.findElement(By.className("card-info")).findElements(By.tagName("li")).get(1).click();
		 try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
		  driver.switchTo().defaultContent();
		 driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".via-slds.slds-m-around--small.ng-scope")));
		 List<WebElement> historiales = driver.findElements(By.cssSelector(".via-slds.slds-m-around--small.ng-scope"));
		 for (WebElement UnH : historiales) {
			 if (UnH.findElement(By.cssSelector(".slds-text-heading--large.slds-size--1-of-1.slds-medium-size--1-of-1.slds-large-size--1-of-1.slds-m-bottom--small")).findElement(By.tagName("p")).getText().toLowerCase().equals("historial de recargas")) {
				 List<WebElement> tuplas = driver.findElement(By.tagName("thead")).findElements(By.tagName("th"));
				 for (WebElement UnaT : tuplas) {
					 if (UnaT.findElement(By.tagName("a")).getText().toLowerCase().equals("beneficios")) {
						 enc = true;
						 assertTrue(UnaT.findElement(By.tagName("a")).getAttribute("ng-click").equals("sortBy(field)"));
						 break;
					 }	 
				 }
				break;
			 }
		 }
		 assertTrue(enc);
		 Accounts accountPage = new Accounts(driver);
		 accountPage.closeAccountServiceTabByName("Historiales");	
	}
	
	@Test(groups= "CustomerCare")
	public void TS37504_Historial_De_Recargas_PrePago_Ordenamiento_Y_Paginado_De_Grilla_Ordenar_Por_Canal() {
		boolean enc = false;
		BasePage cambioFrameByID=new BasePage();
		driver.switchTo().defaultContent();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".console-card.active")));
		 driver.findElement(By.className("card-info")).findElements(By.tagName("li")).get(1).click();
		 try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
		 driver.switchTo().defaultContent();
		 driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".via-slds.slds-m-around--small.ng-scope")));
		 List<WebElement> historiales = driver.findElements(By.cssSelector(".via-slds.slds-m-around--small.ng-scope"));
		 for (WebElement UnH : historiales) {
			 if (UnH.findElement(By.cssSelector(".slds-text-heading--large.slds-size--1-of-1.slds-medium-size--1-of-1.slds-large-size--1-of-1.slds-m-bottom--small")).findElement(By.tagName("p")).getText().toLowerCase().equals("historial de recargas")) {
				 List<WebElement> tuplas = driver.findElement(By.tagName("thead")).findElements(By.tagName("th"));
				 for (WebElement UnaT : tuplas) {
					 if (UnaT.findElement(By.tagName("a")).getText().toLowerCase().equals("canal")) {
						 enc = true;
						 assertTrue(UnaT.findElement(By.tagName("a")).getAttribute("ng-click").equals("sortBy(field)"));
						 break;
					 }	 
				 }
				break;
			 }
		 }
		 assertTrue(enc);
		 Accounts accountPage = new Accounts(driver);
		 accountPage.closeAccountServiceTabByName("Historiales");	
	}
	
	@Test(groups= "CustomerCare")
	public void TS37505_Historial_De_Recargas_PrePago_Ordenamiento_Y_Paginado_De_Grilla_Ordenar_Por_Descripcion() {
		boolean enc = false;
		BasePage cambioFrameByID=new BasePage();
		driver.switchTo().defaultContent();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".console-card.active")));
		 driver.findElement(By.className("card-info")).findElements(By.tagName("li")).get(1).click();
		 try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
		 driver.switchTo().defaultContent();
		 driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".via-slds.slds-m-around--small.ng-scope")));
		 List<WebElement> historiales = driver.findElements(By.cssSelector(".via-slds.slds-m-around--small.ng-scope"));
		 for (WebElement UnH : historiales) {
			 if (UnH.findElement(By.cssSelector(".slds-text-heading--large.slds-size--1-of-1.slds-medium-size--1-of-1.slds-large-size--1-of-1.slds-m-bottom--small")).findElement(By.tagName("p")).getText().toLowerCase().equals("historial de recargas")) {
				 List<WebElement> tuplas = driver.findElement(By.tagName("thead")).findElements(By.tagName("th"));
				 for (WebElement UnaT : tuplas) {
					 if (UnaT.findElement(By.tagName("a")).getText().toLowerCase().equals("descripción")) {
						 enc = true;
						 assertTrue(UnaT.findElement(By.tagName("a")).getAttribute("ng-click").equals("sortBy(field)"));
						 break;
					 }	 
				 }
				break;
			 }
		 }
		 assertTrue(enc);
		 Accounts accountPage = new Accounts(driver);
		 accountPage.closeAccountServiceTabByName("Historiales");	
	}
	
	@Test(groups= "CustomerCare")
	public void TS37503_Historial_De_Recargas_PrePago_Ordenamiento_Y_Paginado_De_Grilla_Ordenar_Por_Fecha_Y_Hora() {
		boolean enc = false;
		BasePage cambioFrameByID=new BasePage();
		driver.switchTo().defaultContent();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".console-card.active")));
		 driver.findElement(By.className("card-info")).findElements(By.tagName("li")).get(1).click();
		 try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
		 driver.switchTo().defaultContent();
		 driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".via-slds.slds-m-around--small.ng-scope")));
		 List<WebElement> historiales = driver.findElements(By.cssSelector(".via-slds.slds-m-around--small.ng-scope"));
		 for (WebElement UnH : historiales) {
			 if (UnH.findElement(By.cssSelector(".slds-text-heading--large.slds-size--1-of-1.slds-medium-size--1-of-1.slds-large-size--1-of-1.slds-m-bottom--small")).findElement(By.tagName("p")).getText().toLowerCase().equals("historial de recargas")) {
				 List<WebElement> tuplas = driver.findElement(By.tagName("thead")).findElements(By.tagName("th"));
				 for (WebElement UnaT : tuplas) {
					 if (UnaT.findElement(By.tagName("a")).getText().toLowerCase().equals("fecha")) {
						 enc = true;
						 assertTrue(UnaT.findElement(By.tagName("a")).getAttribute("ng-click").equals("sortBy(field)"));
						 break;
					 }	 
				 }
				break;
			 }
		 }
		 assertTrue(enc);
		 Accounts accountPage = new Accounts(driver);
		 accountPage.closeAccountServiceTabByName("Historiales");	
	}
	
	@Test(groups= "CustomerCare")
	public void TS37506_Historial_De_Recargas_PrePago_Ordenamiento_Y_Paginado_De_Grilla_Ordenar_Por_Monto() {
		boolean enc = false;
		BasePage cambioFrameByID=new BasePage();
		driver.switchTo().defaultContent();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".console-card.active")));
		 driver.findElement(By.className("card-info")).findElements(By.tagName("li")).get(1).click();
		 try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
		 driver.switchTo().defaultContent();
		 driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".via-slds.slds-m-around--small.ng-scope")));
		 List<WebElement> historiales = driver.findElements(By.cssSelector(".via-slds.slds-m-around--small.ng-scope"));
		 for (WebElement UnH : historiales) {
			 if (UnH.findElement(By.cssSelector(".slds-text-heading--large.slds-size--1-of-1.slds-medium-size--1-of-1.slds-large-size--1-of-1.slds-m-bottom--small")).findElement(By.tagName("p")).getText().toLowerCase().equals("historial de recargas")) {
				 List<WebElement> tuplas = driver.findElement(By.tagName("thead")).findElements(By.tagName("th"));
				 for (WebElement UnaT : tuplas) {
					 if (UnaT.findElement(By.tagName("a")).getText().toLowerCase().equals("monto")) {
						 enc = true;
						 assertTrue(UnaT.findElement(By.tagName("a")).getAttribute("ng-click").equals("sortBy(field)"));
						 break;
					 }	 
				 }
				break;
			 }
		 }
		 assertTrue(enc);
		 Accounts accountPage = new Accounts(driver);
		 accountPage.closeAccountServiceTabByName("Historiales");	
	}
	
	@Test(groups= "CustomerCare")
	public void TS37507_Historial_De_Recargas_PrePago_Ordenamiento_Y_Paginado_De_Grilla_Ordenar_Por_Vencimiento() {
		boolean enc = false;
		BasePage cambioFrameByID=new BasePage();
		driver.switchTo().defaultContent();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".console-card.active")));
		 driver.findElement(By.className("card-info")).findElements(By.tagName("li")).get(1).click();
		 try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
		 driver.switchTo().defaultContent();
		 driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".via-slds.slds-m-around--small.ng-scope")));
		 List<WebElement> historiales = driver.findElements(By.cssSelector(".via-slds.slds-m-around--small.ng-scope"));
		 for (WebElement UnH : historiales) {
			 if (UnH.findElement(By.cssSelector(".slds-text-heading--large.slds-size--1-of-1.slds-medium-size--1-of-1.slds-large-size--1-of-1.slds-m-bottom--small")).findElement(By.tagName("p")).getText().toLowerCase().equals("historial de recargas")) {
				 List<WebElement> tuplas = driver.findElement(By.tagName("thead")).findElements(By.tagName("th"));
				 for (WebElement UnaT : tuplas) {
					 if (UnaT.findElement(By.tagName("a")).getText().toLowerCase().equals("vencimiento")) {
						 enc = true;
						 assertTrue(UnaT.findElement(By.tagName("a")).getAttribute("ng-click").equals("sortBy(field)"));
						 break;
					 }	 
				 }
				break;
			 }
		 }
		 assertTrue(enc);
		 Accounts accountPage = new Accounts(driver);
		 accountPage.closeAccountServiceTabByName("Historiales");	
	}
	
	@Test(groups= "CustomerCare")
	public void TS37326_Problems_With_Refills_Tarjeta_De_Recarga_Prepaga_Verificación_Numero_De_Lote_Ingresa_15_Dígitos() {
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
	     BasePage cambioFrameByID=new BasePage();
	     driver.switchTo().defaultContent();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".console-card.active")));
		 driver.findElement(By.className("card-info")).findElement(By.className("details")).click();
	     try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	     driver.switchTo().defaultContent();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions")));
	     ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".console-flyout.active.flyout")).getLocation().y+")");
	     try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
	     driver.findElement(By.cssSelector(".console-flyout.active.flyout")).findElements(By.tagName("i")).get(1).click();
	     List<WebElement> wAsd = driver.findElements(By.id("refillMethod"));
	     for (WebElement x:wAsd) {
	      if (x.getText().toLowerCase().contains("tarjeta prepaga")) {
	       x.click();
	      }
	     }
	     try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	     driver.switchTo().defaultContent();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("stepChooseMethod_nextBtn")));
	     try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	     ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).getLocation().y+")");
	     List <WebElement> wX = driver.findElements(By.className("slds-form-element__control"));
	     for (WebElement x : wX) {
	      if (x.getText().toLowerCase().contains("siguiente")) {
	       x.click();
	       break;
	      }
	     }
	     try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		  
		 driver.findElement(By.id("lotNumber")).sendKeys("145789654212458");
		 assertTrue(driver.findElement(By.cssSelector(".vlc-slds-error-block.ng-scope")).findElement(By.cssSelector(".error.ng-scope")).findElement(By.cssSelector(".description.ng-binding")).getText().toLowerCase().equals("longitud mínima de 16"));
		 ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("stepPrepaidCardData_nextBtn")).getLocation().y+")");
		 driver.findElement(By.id("stepPrepaidCardData_nextBtn")).click();
		 try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
		 assertTrue(driver.findElement(By.cssSelector(".slds-modal__header.slds-theme--info.slds-theme--alert-texture.slds-theme--error")).isDisplayed());
		 Accounts accountPage = new Accounts(driver);
		 accountPage.closeAccountServiceTabByName("Problemas con Recargas");	
	}
	
	@Test(groups= "CustomerCare")
	public void TS37328_Problems_With_Refills_Tarjeta_De_Recarga_Prepaga_Verificación_Numero_De_Lote_Ingresa_Letras() {
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
	     BasePage cambioFrameByID=new BasePage();
	     boolean enc = true;
	     driver.switchTo().defaultContent();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".console-card.active")));
		 driver.findElement(By.className("card-info")).findElement(By.className("details")).click();
	     try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	     driver.switchTo().defaultContent();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions")));
	     ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".console-flyout.active.flyout")).getLocation().y+")");
	     try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
	     driver.findElement(By.cssSelector(".console-flyout.active.flyout")).findElements(By.tagName("i")).get(1).click();
	     List<WebElement> wAsd = driver.findElements(By.id("refillMethod"));
	     for (WebElement x:wAsd) {
	      if (x.getText().toLowerCase().contains("tarjeta prepaga")) {
	       x.click();
	      }
	     }
	     try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	     driver.switchTo().defaultContent();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("stepChooseMethod_nextBtn")));
	     try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	     ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).getLocation().y+")");
	     List <WebElement> wX = driver.findElements(By.className("slds-form-element__control"));
	     for (WebElement x : wX) {
	      if (x.getText().toLowerCase().contains("siguiente")) {
	       x.click();
	       break;
	      }
	     }
	     try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		  
		 driver.findElement(By.id("lotNumber")).sendKeys("letrasletrasletr");
		 try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
		 List<WebElement> errores = driver.findElement(By.cssSelector(".vlc-slds-error-block.ng-scope")).findElement(By.cssSelector(".error.ng-scope")).findElements(By.cssSelector(".description.ng-binding"));
		 for (WebElement UnE : errores) {
			 if (UnE.getText().toLowerCase().equals("sólo se permiten números")) {
				 enc = true;
				 break;
			 }
				 
		 }
		 assertTrue(enc);
		 
		 ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("stepPrepaidCardData_nextBtn")).getLocation().y+")");
		 driver.findElement(By.id("stepPrepaidCardData_nextBtn")).click();
		 try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
		 assertTrue(driver.findElement(By.cssSelector(".slds-modal__header.slds-theme--info.slds-theme--alert-texture.slds-theme--error")).isDisplayed());
		 Accounts accountPage = new Accounts(driver);
		 accountPage.closeAccountServiceTabByName("Problemas con Recargas");	
	}
	
	@Test(groups= "CustomerCare")
	public void TS37327_Problems_With_Refills_Tarjeta_De_Recarga_Prepaga_Verificación_Numero_De_Lote_Ingresa_17_Dígitos() {
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
	     BasePage cambioFrameByID=new BasePage();
	     boolean enc = true;
	     driver.switchTo().defaultContent();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".console-card.active")));
		 driver.findElement(By.className("card-info")).findElement(By.className("details")).click();
	     try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	     driver.switchTo().defaultContent();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions")));
	     ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".console-flyout.active.flyout")).getLocation().y+")");
	     try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
	     driver.findElement(By.cssSelector(".console-flyout.active.flyout")).findElements(By.tagName("i")).get(1).click();
	     List<WebElement> wAsd = driver.findElements(By.id("refillMethod"));
	     for (WebElement x:wAsd) {
	      if (x.getText().toLowerCase().contains("tarjeta prepaga")) {
	       x.click();
	      }
	     }
	     try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	     driver.switchTo().defaultContent();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("stepChooseMethod_nextBtn")));
	     try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	     ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).getLocation().y+")");
	     List <WebElement> wX = driver.findElements(By.className("slds-form-element__control"));
	     for (WebElement x : wX) {
	      if (x.getText().toLowerCase().contains("siguiente")) {
	       x.click();
	       break;
	      }
	     }
	     try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		 
		 driver.findElement(By.id("lotNumber")).sendKeys("12457856321457895");
		 List<WebElement> errores = driver.findElement(By.cssSelector(".vlc-slds-error-block.ng-scope")).findElement(By.cssSelector(".error.ng-scope")).findElements(By.cssSelector(".description.ng-binding"));
		 for (WebElement UnE : errores) {
			 if (UnE.getText().toLowerCase().equals("longitud máxima de 16")) {
				 enc = true;
				 break;
			 }
				 
		 }
		 assertTrue(enc);
		 ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("stepPrepaidCardData_nextBtn")).getLocation().y+")");
		 driver.findElement(By.id("stepPrepaidCardData_nextBtn")).click();
		 try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
		 assertTrue(driver.findElement(By.cssSelector(".slds-modal__header.slds-theme--info.slds-theme--alert-texture.slds-theme--error")).isDisplayed());
		 Accounts accountPage = new Accounts(driver);
		 accountPage.closeAccountServiceTabByName("Problemas con Recargas");	
	}
	
	@Test(groups= "CustomerCare")
	public void TS37325_Problems_With_Refills_Tarjeta_De_Recarga_Prepaga_Verificación_Numero_De_Lote_Ingresa_16_Dígitos() {
		
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
	     BasePage cambioFrameByID=new BasePage();
	     boolean enc = true;
	     driver.switchTo().defaultContent();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".console-card.active")));
		 driver.findElement(By.className("card-info")).findElement(By.className("details")).click();
	     try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	     driver.switchTo().defaultContent();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions")));
	     ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".console-flyout.active.flyout")).getLocation().y+")");
	     try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
	     driver.findElement(By.cssSelector(".console-flyout.active.flyout")).findElements(By.tagName("i")).get(1).click();
	     List<WebElement> wAsd = driver.findElements(By.id("refillMethod"));
	     for (WebElement x:wAsd) {
	      if (x.getText().toLowerCase().contains("tarjeta prepaga")) {
	       x.click();
	      }
	     }
	     try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	     driver.switchTo().defaultContent();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("stepChooseMethod_nextBtn")));
	     try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	     ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).getLocation().y+")");
	     List <WebElement> wX = driver.findElements(By.className("slds-form-element__control"));
	     for (WebElement x : wX) {
	      if (x.getText().toLowerCase().contains("siguiente")) {
	       x.click();
	       break;
	      }
	     }
	     try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		 driver.findElement(By.id("lotNumber")).sendKeys("1245785632145789");
		 List<WebElement> errores = driver.findElement(By.cssSelector(".vlc-slds-error-block.ng-scope")).findElement(By.cssSelector(".error.ng-scope")).findElements(By.cssSelector(".description.ng-binding"));
		 for (WebElement UnE : errores) {
			 if (!UnE.getText().isEmpty()) {
				 enc = false;
				 break;
			 }
				 
		 }
		 assertTrue(enc);
		 Accounts accountPage = new Accounts(driver);
		 accountPage.closeAccountServiceTabByName("Problemas con Recargas");	
	}
	
	@Test(groups= "CustomerCare")
	public void TS37534_Problems_With_Refills_Problemas_Con_Recargas_Medio_De_Recarga_Seleccionar_ROL() {
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
	     BasePage cambioFrameByID=new BasePage();
	     String filePath = "C:\\Users\\Florangel\\Downloads\\usuaria.png";
	     driver.switchTo().defaultContent();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".console-card.active")));
		 driver.findElement(By.className("card-info")).findElement(By.className("details")).click();
	     try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	     driver.switchTo().defaultContent();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions")));
	     ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".console-flyout.active.flyout")).getLocation().y+")");
	     try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
	     driver.findElement(By.cssSelector(".console-flyout.active.flyout")).findElements(By.tagName("i")).get(1).click();
	     try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
	     driver.switchTo().defaultContent();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("refillMethod")));
	     //List<WebElement> wAsd = driver.findElements(By.id("refillMethod"));
	     driver.findElements(By.cssSelector(".slds-radio.ng-scope")).get(1).click();
	     try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	     driver.switchTo().defaultContent();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("stepChooseMethod_nextBtn")));
	     try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	     ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).getLocation().y+")");
	     List <WebElement> wX = driver.findElements(By.className("slds-form-element__control"));
	     for (WebElement x : wX) {
	      if (x.getText().toLowerCase().contains("siguiente")) {
	       x.click();
	       break;
	      }
	     }
	     try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	     driver.switchTo().defaultContent();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("refillDate")));
		 driver.findElement(By.id("refillDate")).sendKeys("12-15-2017");
		 driver.findElement(By.id("refillAmount")).sendKeys("150");
		 driver.findElement(By.id("receiptCode")).sendKeys("150");
		 ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("stepInternetRefill_nextBtn")).getLocation().y+")");
		 driver.findElement(By.id("stepInternetRefill_nextBtn")).click();
		 try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		 wX = driver.findElements(By.id("useExistingCase"));
	     for (WebElement x:wX) {
	      if (x.getText().toLowerCase().contains("nuevo")) {
	       x.click();
	      }
	     }
	     ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("StepExistingCase_nextBtn")).getLocation().y+")");
		 driver.findElement(By.id("StepExistingCase_nextBtn")).click();
		 try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		 driver.findElement(By.id("HasVoucher")).click();
		 try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		 driver.findElement(By.id("FileAttach")).sendKeys(filePath);
		 try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		 ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("stepAttachDocuments_nextBtn")).getLocation().y+")");
		 driver.findElement(By.id("stepAttachDocuments_nextBtn")).click();
		 try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		 ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("stepSummary_nextBtn")).getLocation().y+")");
		 driver.findElement(By.id("stepSummary_nextBtn")).click();
		 try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		 assertTrue(driver.findElement(By.id("txtROLConfirmationSuccess")).findElement(By.tagName("h1")).getText().toLowerCase().contains("la recarga se realizó con éxito"));
		 Accounts accountPage = new Accounts(driver);
		 //accountPage.closeAccountServiceTabByName("Problemas con Recargas");	
	}
}
