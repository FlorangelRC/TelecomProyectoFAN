package Tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	
	//-------------------------------------------------------------------------------------------------
	//@Befor&After
	@BeforeClass(groups = "SCP")
	public void Init() throws Exception {
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
	
	//------------------------------------------------------------------------------------------------- 
    //TCC = 1 
	@Test(groups = "SCP")
	public void TS112722_Mosaico_de_Relacionamiento_por_Oportunidad_Ir_al_mosaico() {
		SCP prueba = new SCP(driver); 
	    prueba.moveToElementOnAccAndClick("tercerTitulo", 3); 
	    List <WebElement> wIrAlMosaico = driver.findElements(By.cssSelector(".sorting_1")); 
	    wIrAlMosaico.get(1).click(); 
	    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
	    Assert.assertTrue(driver.findElement(By.cssSelector(".row.conteinerDiv")).isDisplayed()); 
	}
	
	//------------------------------------------------------------------------------------------------- 
    //TCC = 2 
	@Test(groups = "SCP") 
	public void TS112723_Mosaico_de_Relacionamiento_por_Oportunidad_Nombre_de_la_oportunidad() { 
	  SCP prueba = new SCP(driver); 
	  prueba.moveToElementOnAccAndClick("tercerTitulo", 4); 
	  List <WebElement> wOportunity = driver.findElement(By.className("odd")).findElements(By.tagName("a")); 
	  wOportunity.get(1).click(); 
	  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
	  Assert.assertTrue(driver.findElement(By.className("pageType")).isDisplayed());
	} 
	 
	//------------------------------------------------------------------------------------------------- 
	//TCC = 3 
	@Test(groups = "SCP") 
	public void TS112724_Mosaico_de_Relacionamiento_por_Oportunidad_Search() { 
	  SCP prueba = new SCP(driver); 
	  prueba.moveToElementOnAccAndClick("tercerTitulo", 3); 
	  try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
	  driver.findElement(By.xpath("//*[@id=\"mainTable_filter\"]/label/input")).sendKeys("Oportunidad 2"); 
	  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
	
	  List<WebElement> wOportunity = driver.findElements(By.xpath("//*[@id=\"mainTable\"]/tbody/tr")); 
	  boolean bAssert = true; 
	   
	  for(WebElement wText:wOportunity) { 
	    if (!(wText.getText().toLowerCase().contains("oportunidad 2"))) { 
	      bAssert=false; 
	      break; 
	    } 
	  } 
	  Assert.assertTrue(bAssert);
	}
	
	//------------------------------------------------------------------------------------------------- 
	//TCC = 4 
	@Test(groups = "SCP") 
	public void TS112725_Mosaico_de_Relacionamiento_por_Oportunidad_Triangulo_Ordenador() {
		SCP prueba = new SCP(driver); 
		prueba.moveToElementOnAccAndClick("tercerTitulo", 3);
		Assert.assertTrue(prueba.Triangulo_Ordenador_Validador());
	}
	
	//------------------------------------------------------------------------------------------------- 
	//TCC = 5
	@Test(groups = "SCP")
	public void TS112726_Mosaico_de_Relacionamiento_por_Oportunidad_Ver_video() {
		TestBase TB = new TestBase();
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 3);
		TB.waitFor(driver, By.cssSelector(".btn.btn-xs.btn-default"));
		driver.findElement(By.cssSelector(".btn.btn-xs.btn-default")).click();
		
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		BasePage cambioFrameByID=new BasePage();
	    driver.switchTo().defaultContent();
	    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("ytp-cued-thumbnail-overlay-image")));
		TB.waitFor(driver, By.className("ytp-cued-thumbnail-overlay-image"));
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.className("ytp-cued-thumbnail-overlay-image")).getLocation().y+")");
		Assert.assertTrue(driver.findElement(By.className("ytp-cued-thumbnail-overlay-image")).isDisplayed());
		driver.close();
		driver.switchTo().window(tabs2.get(0));
	}
	
	//------------------------------------------------------------------------------------------------- 
	//TCC = 6
	@Test(groups = "SCP")
	public void TS() {
		
	}
    
}