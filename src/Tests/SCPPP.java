package Tests;

import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.SCP;
import Pages.setConexion;

public class SCPPP extends TestBase {
	
private WebDriver driver;
	
	@BeforeClass(groups = "SCP")
	public void init() throws Exception	{
	this.driver = setConexion.setupEze();
	driver.get("http://www.google.com");
	try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	loginSCPAdmin(driver);
	try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	@BeforeMethod(groups = "SCP")
	public void setup() {
		SCP pScp = new SCP(driver);
		//pScp.goToMenu("scp");
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		pScp.clickOnTabByName("cuentas");
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//pScp.listTypeAcc("Todas Las cuentas");
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//pScp.clickOnFirstAccRe();
		pScp.clickEnCuentaPorNombre("Florencia Di Ci");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
//	@AfterClass(groups = "SCP")
	public void teardown() {
		driver.quit();
		sleep(5000);
	}

	@Test(groups = "SCP")
	public void TS110244_Estructura_del_cliente_GGCC_Campos() {
	
		 ArrayList<String> camp1 = new ArrayList<String>();
		 ArrayList<String> txt2 = new ArrayList<String>();
		 txt2.add("CUIT");
		// txt2.add("Razón Social");
		 txt2.add("Holding");
		 txt2.add("Segmento");
		 txt2.add("Region");
		 txt2.add("Territorio");
		 txt2.add("Domicilio de recepción de notificaciones");
		 List<WebElement> campos = driver.findElements(By.className("labelCol"));
		 System.out.println(campos.size());
		 for(WebElement c: campos){
			 camp1.add(c.getText());
			 }
			 Assert.assertTrue(camp1.containsAll(txt2));
	}
	
	@Test(groups = "SCP")
	public void TS110245_Estructura_del_cliente_GGCC_Campos_Region	() {
		WebElement reg = driver.findElement(By.id("00N3F000000HaUe_ileinner"));
		Actions action = new Actions(driver);   
		action.moveToElement(reg).doubleClick().perform();
		waitFor(driver, By.id("00N3F000000HaUe"));
		Select dropdown = new Select (driver.findElement(By.id("00N3F000000HaUe")));
		dropdown.selectByVisibleText("Gobierno");	
	}
	@Test(groups = "SCP")
	public void TS110246_Estructura_del_cliente_GGCC_Campos_Territorio() {
	WebElement reg = driver.findElement(By.id("00N3F000000HaUe_ileinner"));
	Actions action = new Actions(driver);   
	action.moveToElement(reg).doubleClick().perform();
	waitFor(driver, By.id("00N3F000000HaUe"));
	Select rego = new Select (driver.findElement(By.id("00N3F000000HaUe")));
	rego.selectByVisibleText("Gobierno");	
	waitFor(driver, By.id("00N3F000000HaUj"));
	List<WebElement> region = driver.findElements(By.id("00N3F000000HaUj"));
	ArrayList<String> ter = new ArrayList<String>();
	ArrayList<String> terri = new ArrayList<String>();
	ter.add("--Ninguno--");
	ter.add("Gobierno ambaaa 1");
	ter.add("Gobierno amba 2");
	ter.add("Gobierno litoral");
	ter.add("Gobierno mediterraneo");
	sleep(5000);
	boolean f = false;
	for(WebElement r : region) {
		terri.add(r.getText());
		ter.contains(r.getAttribute("value"));
		f=true;
	}
	System.out.println(f);
	}
	
}
