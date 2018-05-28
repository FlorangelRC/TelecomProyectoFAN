package Tests;

import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.Element;
import com.gargoylesoftware.htmlunit.javascript.host.Iterator;
import com.sun.javafx.geom.transform.GeneralTransform3D;

import Pages.Accounts;
import Pages.BasePage;
import Pages.HomeBase;
import Pages.OM;
import Pages.RegistroEventoMasivo;
import Pages.SCP;
import Pages.SalesBase;
import Pages.setConexion;

import Pages.setConexion;

public class OMRuben extends TestBase {

	private WebDriver driver;

	@BeforeClass(alwaysRun = true)
	public void init() throws Exception {
		this.driver = setConexion.setupEze();
		sleep(5000);
		// Usuario Victor OM
		login(driver, "https://crm--sit.cs14.my.salesforce.com/", "U585991", "Testa10k");
		sleep(5000);
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		driver.switchTo().defaultContent();
		sleep(2000);
		SCP pageSCP = new SCP(driver);
		sleep(3000);
		pageSCP.goToMenu("Ventas");

		// click +
		sleep(5000);
		OM pageOm = new OM(driver);
		pageOm.clickMore();
		sleep(3000);

		// click en Ordenes
		pageOm.clickOnListTabs("Orders");
	}

	// @AfterClass(alwaysRun=true)
	public void tearDown() {
		sleep(2000);
		driver.quit();
		sleep(1000);
	}
	
	
	
	@Test(groups = "OM")
	public void TS6723_CRM_OM_Ordenes_Vista_Configuración_Borrar_Vista() {

		// Crear Nueva Vista
		sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]")).click();

		sleep(5000);
		
		// Completar el Formulario y Guardar
		driver.findElement(By.id("fname")).sendKeys("Vista Temporal de Ruben");
		driver.findElement(By.cssSelector(".btn.primary")).click();
		
		sleep(5000);
		
		//Seleccionar Vista
		Select vistaSelect = new Select(driver.findElement(By.name("fcf")));
		vistaSelect.selectByVisibleText("Vista Temporal de Ruben");
		sleep(2000);
		driver.findElement(By.className("filterLinks")).findElements(By.tagName("a")).get(1).click();
		
		sleep(5000);
		
//		//Borrar Vista
		try {
			driver.findElement(By.name("delID")).click();
			sleep(5000);
		} catch (UnhandledAlertException f) {
			try {
		//Aceptar Alerta para Borrar Lista
				Alert confirmDelete = driver.switchTo().alert();
				confirmDelete.accept();
			} catch (NoAlertPresentException e) {
				e.printStackTrace();
			}
		}
		
		sleep(5000);
		
		//Chequear Si La Lista Se Borró
		vistaSelect = new Select(driver.findElement(By.name("fcf")));
		
		List<WebElement> elementosVistaSelect = vistaSelect.getOptions();
		
		Boolean vistaEncontrada = false;
		
		for(WebElement e: elementosVistaSelect) {
			if(e.getText().equalsIgnoreCase("Vista Temporal de Ruben")) {
				vistaEncontrada = true;
				break;
			}
		}
		
		Assert.assertFalse(vistaEncontrada);
				
	}
	
	@Test(groups = "OM")
	public void TS6724_CRM_OM_Ordenes_Vista_Log_in_con_vista_previamente_utilizada(){
		
		//Seleccionar Una Vista Random
		sleep(3000);
		Select vistaSelect = new Select(driver.findElement(By.name("fcf")));
		List<WebElement> elementosVistaSelect = vistaSelect.getOptions();
		OM pageOm = new OM(driver);
		String textoVistaRandom = pageOm.getRandomElementFromList(elementosVistaSelect).getText();
		vistaSelect.selectByVisibleText(textoVistaRandom);
		
		//Logout
		sleep(3000);
		omLogout(driver);
		
		//Login
		sleep(2000);
		omInternalLoginWithCredentials(driver, "U585991","Testa10k");
		
		//Navegar hasta Ordenes
		sleep(5000);
		driver.switchTo().defaultContent();
		sleep(5000);
		SCP pageSCP = new SCP(driver);
		pageSCP.goToMenu("Ventas");
		sleep(2000);
		pageOm.clickMore();
		sleep(3000);
		pageOm.clickOnListTabs("Orders");
		sleep(5000);
		driver.findElement(By.id("Order_Tab")).click();
		
		//Verificar si la misma Vista esta seleccionada
		sleep(5000);
		vistaSelect = new Select(driver.findElement(By.name("fcf")));
		Boolean mismaVista = vistaSelect.getFirstSelectedOption().getText()
				.equalsIgnoreCase(textoVistaRandom); 

		Assert.assertTrue(mismaVista);
		
	}

	@Test(groups = "OM")
	public void TS6725_CRM_OM_Ordenes_Vista_Log_in_con_vista_previamente_utilizada_por_otro_usuario() {
		
		
		
	}
	
}
