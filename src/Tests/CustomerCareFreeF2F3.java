package Tests;

import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class CustomerCareFreeF2F3  extends TestBase  {
	private WebDriver driver;
	
	@AfterClass(groups= "CustomerCare")
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
	     if(driver.findElement(By.id("tsidLabel")).getText().equals("Consola FAN")) {
	    	 homePage.switchAppsMenu();
	    	 try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	 homePage.selectAppFromMenuByName("Ventas");
	    	 try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}    
	     }
	     homePage.switchAppsMenu();
	     try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	     homePage.selectAppFromMenuByName("Consola FAN");
	    
	}	
	
	@BeforeMethod(groups = "CustomerCare") 
	public void setUp() throws Exception {
		 try {Thread.sleep(9000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
	     CustomerCare page = new CustomerCare(driver);
			page.cerrarultimapestaña();
	}
	
	@Test(groups= "CustomerCare")
	public void TS14601_Case_Management__Casos_Ordernados_Por_Tipos_Vista_Todos_Los_Casos_Abiertos(){
		 Accounts accountPage = new Accounts(driver);
	     driver.switchTo().defaultContent();
	     goToLeftPanel2(driver, "Casos");
	     accountPage.accountSelect("Todos Los Casos Abiertos");
	     assertTrue(driver.findElement(By.cssSelector(".x-panel.x-grid-panel")).isDisplayed());
	}
	
	@Test(groups= "CustomerCare")
	public void TS15850_Cost_For_Changes_Sesion_Guiada_Visualizar_Leyenda_Cargo_Gestion_Consumidor_Final_Costo_IVA(){
		 Accounts accountPage = new Accounts(driver);
		 goToLeftPanel2(driver, "Cuentas");
	     driver.switchTo().defaultContent();
	     driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".topNav.primaryPalette")));
	     Select field = new Select(driver.findElement(By.name("fcf")));
	     try {field.selectByVisibleText("Todas Las cuentas");}
			catch (org.openqa.selenium.NoSuchElementException ExM) {field.selectByVisibleText("Todas las cuentas");}
	     try {Thread.sleep(9000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
	     CustomerCare page = new CustomerCare(driver);
			page.cerrarultimapestaña();
			 try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
			page.elegircuenta("Fernando Care");
			 try {Thread.sleep(9000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
			 accountPage.findAndClickButton("Cambios de condición impositiva");
			 try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
			 assertTrue(false);
			 //bug reportado, arreglar cuando se pueda visualizar
	}
	
	@Test(groups= "CustomerCare")
	public void TS15851_Cost_For_Changes_Sesion_Guiada_Visualizar_Leyenda_Cargo_Gestion_Empresas_Costo_Impuestos(){
		 Accounts accountPage = new Accounts(driver);
		 goToLeftPanel2(driver, "Cuentas");
	     driver.switchTo().defaultContent();
	     driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".topNav.primaryPalette")));
	     Select field = new Select(driver.findElement(By.name("fcf")));
	     try {field.selectByVisibleText("Todas Las cuentas");}
			catch (org.openqa.selenium.NoSuchElementException ExM) {field.selectByVisibleText("Todas las cuentas");}
	     try {Thread.sleep(9000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
	     CustomerCare page = new CustomerCare(driver);
			page.cerrarultimapestaña();
			 try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
			page.elegircuenta("Empresa Care");
			 try {Thread.sleep(9000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
			 accountPage.findAndClickButton("Cambios de condición impositiva");
			 try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
			 assertTrue(false);
			 //bug reportado, arreglar cuando se pueda visualizar
	}
	
	@Test(groups= "CustomerCare")
	public void TS14569_360_View_360_View_Capacidades_De_Busqueda_Filtrar_Por_Billing_Account(){
		Accounts accountPage = new Accounts(driver);
		driver.switchTo().defaultContent();
		//driver.switchTo().frame(accountPage.getFrameForElement(driver, By.id("phSearchInput")));
		driver.findElement(By.id("phSearchInput")).sendKeys("Fernando care Billin");
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		driver.findElement(By.id("phSearchInput:group0:option0")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try {
			driver.switchTo().alert().accept();
		}catch(org.openqa.selenium.NoAlertPresentException e) {}
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.id("topButtonRow")));
		assertTrue(driver.findElement(By.className("mainTitle")).getText().equals("Detalle de Cuenta"));
		
	}
	
	@Test(groups= "CustomerCare")
	public void TS15940_Consumption_Details_Mostrar_Informacion_Sobre_El_Tiempo_De_Actualizacion_Ultima_Actualizacion_Dentro_Del_Dia(){
		 Accounts accountPage = new Accounts(driver);
		 goToLeftPanel2(driver, "Cuentas");
	     driver.switchTo().defaultContent();
	     driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".topNav.primaryPalette")));
	     Select field = new Select(driver.findElement(By.name("fcf")));
	     try {field.selectByVisibleText("Todas Las cuentas");}
			catch (org.openqa.selenium.NoSuchElementException ExM) {field.selectByVisibleText("Todas las cuentas");}
	     try {Thread.sleep(9000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
	     CustomerCare page = new CustomerCare(driver);
			page.cerrarultimapestaña();
			 try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
			page.elegircuenta("Fernando Care");
			accountPage.findAndClickButton("Detalle de Consumos");
			try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
			driver.switchTo().defaultContent();
			driver.switchTo().frame(accountPage.getFrameForElement(driver, By.id("text-input-01")));
			driver.findElement(By.id("text-input-01")).click();
			driver.findElement(By.cssSelector(".slds-dropdown.slds-dropdown--left")).findElement(By.tagName("span")).click();
			driver.findElement(By.cssSelector(".slds-button.slds-button--brand")).click();
			driver.findElement(By.cssSelector(".slds-button.slds-button--brand")).click();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
			//driver.switchTo().defaultContent();
			//driver.switchTo().frame(accountPage.getFrameForElement(driver, By.className(".slds-text-title")));
			String fecha = driver.findElements(By.className("slds-text-title")).get(2).getText();
			fecha = fecha.substring(25, fecha.length()-2);
			System.out.println("fecha: "+fecha);
			Calendar Factual = Calendar.getInstance();
			System.out.println("fecha act: "+(Integer.toString(Factual.get(Calendar.DATE))+"/"+Integer.toString(Factual.get(Calendar.MONTH))+"/"+Integer.toString(Factual.get(Calendar.YEAR))));
			assertTrue(fecha.split(" ")[0].equals(Integer.toString(Factual.get(Calendar.DATE))+"/"+Integer.toString(Factual.get(Calendar.MONTH))+"/"+Integer.toString(Factual.get(Calendar.YEAR)))); 
	}
	
	@Test(groups= "CustomerCare")
	public void TS15960_360_View_Ver_Equipo_Creador_En_Case_Usuario_Cambia_De_Equipo_No_Se_Modifica_El_Campo_Equipo_Del_Creador() throws ParseException{
		 Accounts accountPage = new Accounts(driver);
		 Date date1 = new Date();
	     driver.switchTo().defaultContent();
	     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
	     try {
			date1 = sdf.parse("24/11/2017");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     goToLeftPanel2(driver, "Casos");
	     accountPage.accountSelect("Mis Casos");
	     driver.switchTo().defaultContent();
	     driver.switchTo().frame(accountPage.getFrameForElement(driver, By.className("x-grid3-body")));
	     List<WebElement> TodosCasos = driver.findElement(By.className("x-grid3-body")).findElements(By.className("x-grid3-row"));
	     TodosCasos.remove(0);
	     for (WebElement UnC : TodosCasos) {
	    	 String fecha = UnC.findElements(By.tagName("td")).get(12).findElement(By.tagName("div")).getText();
	    	 fecha = fecha.split(" ")[0];
	    	 if (date1.compareTo(sdf.parse(fecha))>0) {
	    		 System.out.println("Equipo: "+UnC.findElements(By.tagName("td")).get(10).findElement(By.tagName("div")).getText());
	    		 assertTrue(!UnC.findElements(By.tagName("td")).get(10).findElement(By.tagName("div")).getText().equals("Cubo magico team"));
	    		 break;
	    	 }
	    	 
	     }
	     
	}
	
	@Test(groups= "CustomerCare")
	public void TS15961_360_View_Ver_Equipo_Creador_En_Case_Usuario_Cambia_De_Equipo_Nuevo_Caso_Se_Modifica_El_Campo_Equipo_Del_Creador() throws ParseException{
		 Accounts accountPage = new Accounts(driver);
		 Date date1 = new Date();
	     driver.switchTo().defaultContent();
	     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
	     try {
			date1 = sdf.parse("24/11/2017");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     goToLeftPanel2(driver, "Casos");
	     accountPage.accountSelect("Mis Casos");
	     driver.switchTo().defaultContent();
	     driver.switchTo().frame(accountPage.getFrameForElement(driver, By.className("x-grid3-body")));
	     List<WebElement> TodosCasos = driver.findElement(By.className("x-grid3-body")).findElements(By.className("x-grid3-row"));
	     TodosCasos.remove(0);
	     for (WebElement UnC : TodosCasos) {
	    	 String fecha = UnC.findElements(By.tagName("td")).get(12).findElement(By.tagName("div")).getText();
	    	 fecha = fecha.split(" ")[0];
	    	 if (date1.compareTo(sdf.parse(fecha))<=0) {
	    		 System.out.println("Equipo: "+UnC.findElements(By.tagName("td")).get(10).findElement(By.tagName("div")).getText());
	    		 assertTrue(UnC.findElements(By.tagName("td")).get(10).findElement(By.tagName("div")).getText().equals("Cubo magico team"));
	    		 break;
	    	 }
	    	 
	     }
	     
	}
	@Test(groups= "CustomerCare")
	public void TS7179_Profile_Changes_Validacion_Correo_Electronico_Creacion_De_Caso_Al_Cambiar_Correo_Electronico(){
		 Accounts accountPage = new Accounts(driver);
		 goToLeftPanel2(driver, "Cuentas");
	     driver.switchTo().defaultContent();
	     driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".topNav.primaryPalette")));
	     Select field = new Select(driver.findElement(By.name("fcf")));
	     try {field.selectByVisibleText("Todas Las cuentas");}
			catch (org.openqa.selenium.NoSuchElementException ExM) {field.selectByVisibleText("Todas las cuentas");}
	     try {Thread.sleep(9000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
	     CustomerCare page = new CustomerCare(driver);
			page.cerrarultimapestaña();
			 try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
			page.elegircuenta("Coco A");
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
			driver.switchTo().defaultContent();
			driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".slds-text-body_regular.profile-links")));
			driver.findElement(By.cssSelector(".slds-text-body_regular.profile-links")).click();
			try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
			driver.switchTo().defaultContent();
			driver.switchTo().frame(accountPage.getFrameForElement(driver, By.id("Email")));
			
			driver.findElement(By.id("Email")).clear();
			driver.findElement(By.id("Email")).sendKeys("pruebaat@gmail.com");
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("ClientInformation_nextBtn")).getLocation().y+")");
		    driver.findElement(By.id("ClientInformation_nextBtn")).click();
		    try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
		    page.elegircaso();
		    try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
			List<WebElement> camposOrden = driver.findElement(By.className("x-grid3-viewport")).findElement(By.tagName("tr")).findElements(By.tagName("td"));
			camposOrden.remove(0);
			for (WebElement UnCO : camposOrden) {
				if (UnCO.findElement(By.tagName("div")).getAttribute("title").toLowerCase().equals("due date")){
					UnCO.click();
					try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
					UnCO.click();
					try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
					break;
				}
			}
			assertTrue(driver.findElement(By.cssSelector(".x-grid3-row.x-grid3-row-first")).findElements(By.tagName("td")).get(4).findElement(By.tagName("span")).getText().toLowerCase().equals("actualización de datos"));
			assertTrue(driver.findElement(By.cssSelector(".x-grid3-row.x-grid3-row-first")).findElement(By.cssSelector(".x-grid3-cell-inner.x-grid3-col-CASES_STATUS")).getText().toLowerCase().equals("nuevo"));
	}
}