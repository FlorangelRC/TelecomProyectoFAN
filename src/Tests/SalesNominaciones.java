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
		boolean a= false;
		SalesBase SB = new SalesBase(driver);
		CustomerCare CC = new CustomerCare(driver);
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("DNI", "10000018", "femenino");
		sleep(6000);
		driver.findElement(By.cssSelector(".slds-input.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).click();
		  sleep(2000);
		  driver.findElement(By.xpath("//*[@id=\"EmailSelectableItems\"]/div/ng-include/div/ng-form/div[1]/div[1]/input")).sendKeys("algo@gmail.com");
		CC.obligarclick(driver.findElement(By.id("Contact_nextBtn")));
		sleep(5000);
		driver.findElement(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")).equals("Validaci\u00f3n por Documento de Identidad");
		driver.findElement(By.id("ValidationMethod_nextBtn")).click();
		SB.subirdoc();
	}
	@Test(groups = "Sales") 
	  public void TS76150_Nominacion_Argentino_Nominar_personas_mayores_a_16_anios_cliente_mayor_de_edad_con_linea_existente_plan_repro(){
				SalesBase SB = new SalesBase(driver);
				CustomerCare CC = new CustomerCare(driver);
				ContactSearch contact = new ContactSearch(driver);
				contact.searchContact("DNI", "10000018", "femenino");
				sleep(6000);
				driver.findElement(By.cssSelector(".slds-input.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).click();
				  sleep(2000);
				  driver.findElement(By.xpath("//*[@id=\"EmailSelectableItems\"]/div/ng-include/div/ng-form/div[1]/div[1]/input")).sendKeys("algo@gmail.com");
				  CC.obligarclick(driver.findElement(By.id("Contact_nextBtn")));
				  sleep(5000);
	}
}
  	//DONDE APARECEN LAS LINEAS PREPAGAS DEL CLIENTE
	

	
	
	
		
