package Tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.ContactInformation;
import Pages.ContactSearch;
import Pages.CustomerCare;
import Pages.HomeBase;
import Pages.Order;
import Pages.OrdersTab;
import Pages.SalesBase;
import Pages.setConexion;
import Tests.TestBase.waitFor;

public class SalesNominaciones extends TestBase{

	@BeforeClass
	public void Init() {
		this.driver = setConexion.setupEze();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		login(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		HomeBase homePage = new HomeBase(driver);
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    String a = driver.findElement(By.id("tsidLabel")).getText();
	    if (a.contains("Ventas")){}
	    else {
	    	homePage.switchAppsMenu();
	    	try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	homePage.selectAppFromMenuByName("Ventas");
	    	try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}            
	    }
	    driver.findElement(By.xpath("//a[@href=\'https://crm--sit--c.cs14.visual.force.com/apex/taClientSearch']")).click();		
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada("Cliente", "Wholesale", "", "", "");
		WebElement cli = driver.findElement(By.id("tab-scoped-2"));
		if (cli.getText().contains("Cliente Wholesale")) {
			cli.click();
		}
		sleep(3000);
		WebElement cua = driver.findElement(By.id("tab-scoped-2")).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(6).findElement(By.tagName("svg"));
		cua.click();
		sleep(10000);
		
	}

	//@AfterMethod
	public void IceB() {
		driver.navigate().refresh();
	}
	
	//@AfterClass
	public void Exit() {
		driver.quit();
		sleep(2000);
	}
	
	@Test(groups = "Sales") 
	  public void TS75995_Nominacion_Argentino_Validar_cantidad_de_lineas(){
		BasePage dni = new BasePage(driver);
		dni.setSimpleDropdown(driver.findElement(By.id("DocumentTypeSearch")),"DNI");
		sleep (2000);
		WebElement ddd = driver.findElement(By.id("DocumentInputSearch"));
		ddd.sendKeys("10000019");
		List<WebElement> gen = driver.findElements(By.id("GenderSearch"));
  	for(WebElement g : gen) {
  		if(g.getText().equals("Masculino")) {
  			g.click();}}
	}
  	
	@Test(groups = "Sales")
	public void TS76062_SalesCPQ_Nominacion_Argentino_Verificar_Formulario_De_Documentacion_Adjunto(){
		boolean a= false;
		SalesBase SB = new SalesBase(driver);
		CustomerCare CC = new CustomerCare(driver);
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("DNI", "10000018", "femenino");
		
		sleep(6000);
		driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-pristine.ng-valid.ng-scope")).findElement(By.tagName("input")).sendKeys("algoaqui@yahoo.com.ar");
		CC.obligarclick(driver.findElement(By.id("Contact_nextBtn")));
	}
	
	@Test(groups = "Sales")
	public void TS76058_Nominacion_Argentino_Verificar_solicitud_de_datos_para_la_nominacion() {
		sleep(5000);
		boolean a = false;
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-form-element__label.vlc-slds-inline-control__label.ng-binding"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("género")) {
				a = true;
			}
		}
		Assert.assertTrue(driver.findElement(By.id("DocumentTypeSearch")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("DocumentInputSearch")).isEnabled());
		Assert.assertTrue(a);
	}
	
	@Test(groups = "Sales")
	public void TS76344_Nominacion_Argentino_Verificar_creacion_de_la_cuenta() {
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("DNI", "10000019", "masculino");
		sleep(5000);
		driver.findElement(By.cssSelector(".slds-input.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).click();
		sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"EmailSelectableItems\"]/div/ng-include/div/ng-form/div[1]/div[1]/input")).sendKeys("asdasd@gmail.com");
		driver.findElement(By.id("Contact_nextBtn")).click();
		sleep(7000);
		List <WebElement> valdni = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : valdni) {
			if (x.getText().toLowerCase().contains("validación por documento de identidad")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("ValidationMethod_nextBtn")).click();
		sleep(7000);
		driver.findElement(By.id("FileDocumentImage")).sendKeys("C:\\Users\\Nicolas\\Desktop\\descarga.jpg");
		driver.findElement(By.id("DocumentMethod_nextBtn")).click();
		sleep(7000);
		BasePage bp = new BasePage(driver);
		bp.setSimpleDropdown(driver.findElement(By.id("ImpositiveCondition")), "IVA Consumidor Final");
		bp.setSimpleDropdown(driver.findElement(By.id("State")), "Ciudad Autónoma de Buenos Aires");
		driver.findElement(By.id("CityTypeAhead")).click();
		driver.findElement(By.id("CityTypeAhead")).sendKeys("f");
		sleep(3000);
		driver.findElement(By.cssSelector(".typeahead.dropdown-menu.ng-scope.am-fade.bottom-left")).click();
		/*driver.findElement(By.id("LegalStreetTypeAhead")).click();
		driver.findElement(By.id("LegalStreetTypeAhead")).sendKeys("f");
		sleep(3000);
		driver.findElement(By.xpath("//*[text() = '+ Nueva Calle']")).click();*/
		
	}
	
}
  	//DONDE APARECEN LAS LINEAS PREPAGAS DEL CLIENTE
	

	
	
	
		
