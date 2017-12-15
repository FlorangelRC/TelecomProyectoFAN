package Tests;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.SCP;
import Pages.setConexion;

public class SCP_Mattu extends TestBase {

private WebDriver driver;
	
	@BeforeClass(groups = "SCP")
	  public void Init() throws Exception
	  {
	    this.driver = setConexion.setupEze();
	    loginSCPAdmin(driver);
	    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	  }
	
	@BeforeMethod(groups = "SCP")
	  public void setUp() throws Exception {
	    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    SCP prueba= new SCP(driver);
	    prueba.goToMenu("SCP");
	    prueba.clickOnTabByName("cuentas");
	    prueba.clickOnFirstAccRe();
	    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
	}
	
	//@AfterClass(groups = "SCP")
	public void teardown() {
		driver.quit();
		sleep(5000);
	}
	
	@Test(groups = "SCP")
	public void TS112613_Cronograma_de_cuenta_Agregar_Vencimiento_Contrato_del_Servicio() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("cuartoTitulo", 1);
		List <WebElement> checkbox = driver.findElements(By.className("checkboxFiltroTimeLine"));
		checkbox.get(1).click();
		driver.findElement(By.id("j_id0:j_id91:j_id111")).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(driver.findElement(By.className("tl-message-full")).isDisplayed());		
	}
	
	@Test(groups = "SCP")
	public void TS() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 3);
		List <WebElement> wIrAlMosaico = driver.findElements(By.cssSelector(".sorting_1"));
		wIrAlMosaico.get(1).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(driver.findElement(By.cssSelector(".col-md-4_col-md-offset-1")).isDisplayed());
	}
}