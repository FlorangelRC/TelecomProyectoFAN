package Tests;

import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.CustomerCare;
import Pages.SalesBase;
import Pages.setConexion;

public class TestsXappia extends TestBase {

	private WebDriver driver;
	private CustomerCare cc;
	private SalesBase sb;
		
	private void loginUAT() {
		driver.get("https://telecomcrm--uat.cs53.my.salesforce.com");
		sleep(2000);
		driver.findElement(By.id("idp_section_buttons")).click();
		sleep(2000);
		driver.findElement(By.name("Ecom_User_ID")).sendKeys("uat579805");
 		driver.findElement(By.name("Ecom_Password")).sendKeys("Testa10k");
 		driver.findElement(By.id("loginButton2")).click();
 		sleep(5000);
	}
	
	private void loginSIT() {
		driver.get("https://crm--sit.cs14.my.salesforce.com/");
		sleep(2000);
		driver.findElement(By.id("idp_section_buttons")).click();
		sleep(2000);
		driver.findElement(By.name("Ecom_User_ID")).sendKeys("UAT195528");
 		driver.findElement(By.name("Ecom_Password")).sendKeys("Testa10k");
 		driver.findElement(By.id("loginButton2")).click();
 		sleep(5000);
	}
	
	private void irAConsolaFAN() {
		try {
			cc.cajonDeAplicaciones("Consola FAN");
		} catch (Exception e) {
			driver.findElement(By.id("tabBar")).findElement(By.tagName("a")).click();
			sleep(8000);
		}
	}
	
	private void irAGestionDeClientes() {
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		boolean enc = false;
		int index = 0;
		for (WebElement frame : frames) {
			try {
				driver.switchTo().frame(frame);
				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).getText();
				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).isDisplayed();
				driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
				enc = true;
				break;
			} catch (NoSuchElementException e) {
				index++;
				driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
			}
		}
		if (enc == false)
			index = -1;
		try {
			driver.switchTo().frame(frames.get(index));
		} catch (ArrayIndexOutOfBoundsException e) {}
		buscarYClick(driver.findElements(By.tagName("button")), "equals", "gesti\u00f3n de clientes");
	}
	
	
	@BeforeMethod (alwaysRun = true)
	public void before() {
		driver = setConexion.setupEze();
		cc = new CustomerCare(driver);
		sb = new SalesBase(driver);
	}
	
	//@AfterMethod (alwaysRun = true)
	public void quit() {
		driver.quit();
	}
	
	
	@Test (groups = "UAT")
	public void Gestiones_Del_Panel_Izquierdo_En_Consola_FAN_En_Ambiente_UAT() {
		loginUAT();
		irAConsolaFAN();
		driver.switchTo().frame(cambioFrame(driver, By.className("slds-spinner_container")));
		WebElement gestiones = driver.findElement(By.className("slds-spinner_container"));
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0," + gestiones.getLocation().y + ")");
		Assert.assertTrue(!gestiones.getText().isEmpty());
	}
	
	@Test (groups = "SIT")
	public void SmokeTest_Tiempo_De_Carga_De_Consola_FAN_En_Ambiente_SIT() {
		Date start = new Date();
		loginSIT();
		irAConsolaFAN();
		Date end = new Date();
		long startTime = start.getTime();
		long endTime = end.getTime();
		long tiempoTotal = endTime - startTime;
		tiempoTotal = tiempoTotal / 1000;
		Assert.assertTrue(tiempoTotal < 55);
	}
	
	@Test (groups = "UAT")
	public void superposicion() {
		loginUAT();
		irAConsolaFAN();
		sb.cerrarPestaniaGestion(driver);
		irAGestionDeClientes();
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", "22222000");
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(10000);
	}
	@Test (groups = "UAT")
	public void Gestion_De_Verificacion_De_Dos_Idiomas_En_El_Carrito() {
		SalesBase sb = new SalesBase(driver);
		loginUAT();
		irAConsolaFAN();
		sb.cerrarPestaniaGestion(driver);
		irAGestionDeClientes();
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", "22222001");
		List <WebElement> boton = driver.findElements(By.cssSelector(".slds-button.slds-button.slds-button--icon"));
		for(WebElement x : boton) {
			if(x.getText().toLowerCase().equals("catalogo")) {
				x.click();
				break;
			}
		}
	}
}