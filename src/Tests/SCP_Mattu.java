package Tests;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.omg.Messaging.SyncScopeHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
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
	@AfterClass(groups = "SCP")
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
	public void TS112725_Mosaico_de_Relacionamiento_por_Oportunidad_Triangulo_Ordenador() throws ParseException {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 3);
		Assert.assertTrue(prueba.Triangulo_Ordenador_Validador("//*[@id=\"mainTable\"]/thead/tr", "//*[@id=\"mainTable\"]/tbody", 4, 2));
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
	public void TS112563_Asignaci�n_de_Value_Drivers_a_Oportunidades_M�S() {
		SCP prueba = new SCP(driver); 
		prueba.moveToElementOnAccAndClick("tercerTitulo", 1);
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.xpath("//*[@id=\"a0l3F0000005ipnQAA\"]/td[2]/span[2]/button")).getLocation().y+")");
		driver.findElement(By.cssSelector(".btn.btn-default.btn-xs.showMore")).click();
		
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("j_id0:j_id112:j_id451:0:j_id540")).getLocation().y+")");
		WebElement wBody = driver.findElement(By.xpath("//*[@id=\"mainTable\"]/tbody"));
		List<WebElement> wElementos = wBody.findElements(By.tagName("td"));
		List<WebElement> wSubElementos = wElementos.get(1).findElements(By.className("moreSpan"));
		List<WebElement> wSubSubElementos = wSubElementos.get(0).findElements(By.tagName("span"));
		Assert.assertTrue(wSubSubElementos.get(2).isDisplayed());
	}
	
	//------------------------------------------------------------------------------------------------- 
	//TCC = 7
	@Test(groups = "SCP")
	public void TS112565_Asignaci�n_de_Value_Drivers_a_Oportunidades_Oportunidades() {
		SCP prueba = new SCP(driver); 
		prueba.moveToElementOnAccAndClick("tercerTitulo", 1);
		driver.findElement(By.xpath("//*[@id=\"0063F000002UbLjQAK\"]/td[1]/a")).click();
	}
	
	//------------------------------------------------------------------------------------------------- 
	//TCC = 8
	@Test(groups = "SCP")
	public void TS112566_Asignaci�n_de_Value_Drivers_a_Oportunidades_Ordenar_por_columnas() throws ParseException {
		SCP prueba = new SCP(driver); 
		prueba.moveToElementOnAccAndClick("tercerTitulo", 1);
		
		driver.findElement(By.xpath("//*[@id=\"mainOppsTable\"]/thead/tr/th[1]"));
		Assert.assertTrue(prueba.Triangulo_Ordenador_Validador("//*[@id=\"mainOppsTable\"]/thead/tr", "//*[@id=\"mainOppsTable\"]/tbody", 6, 2));
		Assert.assertTrue(prueba.Triangulo_Ordenador_Validador("//*[@id=\"mainOppsTable\"]/thead/tr", "//*[@id=\"mainOppsTable\"]/tbody", 6, 3));
		Assert.assertTrue(prueba.Triangulo_Ordenador_Validador("//*[@id=\"mainOppsTable\"]/thead/tr", "//*[@id=\"mainOppsTable\"]/tbody", 6, 4));
		Assert.assertTrue(prueba.Triangulo_Ordenador_Validador("//*[@id=\"mainOppsTable\"]/thead/tr", "//*[@id=\"mainOppsTable\"]/tbody", 6, 5));
		Assert.assertTrue(prueba.Triangulo_Ordenador_Validador("//*[@id=\"mainOppsTable\"]/thead/tr", "//*[@id=\"mainOppsTable\"]/tbody", 6, 6));
	}
	
	//------------------------------------------------------------------------------------------------- 
	//TCC = 9
	@Test(groups = "SCP")
	public void TS112772_Panel_de_control_Busqueda_Anterior_pagina() {
		WebElement wNavBar = driver.findElement(By.cssSelector(".zen-inlineList.zen-tabMenu"));
		List<WebElement> wMenu = wNavBar.findElements(By.tagName("li"));
		wMenu.get(4).click();
		WebElement wMenuSiguiente = driver.findElement(By.cssSelector(".btn-group.pull-right"));
		List<WebElement> wMenu2 = wMenuSiguiente.findElements(By.tagName("input"));
		wMenu2.get(2).click();
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement wMenuAnterior = driver.findElement(By.cssSelector(".btn-group.pull-right"));
		List<WebElement> wMenu3 = wMenuAnterior.findElements(By.tagName("input"));
		wMenu3.get(1).click();
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement wMenuSiguiente2 = driver.findElement(By.cssSelector(".btn-group.pull-right"));
		Assert.assertTrue(wMenuSiguiente2.findElement(By.tagName("b")).getText().contains("P�gina: 1"));
	}
	
	//------------------------------------------------------------------------------------------------- 
	//TCC = 10
	/*@Test(groups = "SCP")
	public void TS112773_Panel_de_control_Busqueda_Con_varios_resultados() {
		WebElement wNavBar = driver.findElement(By.cssSelector(".zen-inlineList.zen-tabMenu"));
		List<WebElement> wMenu = wNavBar.findElements(By.tagName("li"));
		wMenu.get(4).click();
		WebElement wFiltro = driver.findElement(By.id("filterDiv"));
		wFiltro.findElement(By.tagName("input")).sendKeys("APER\n");
		SCP prueba = new SCP(driver);
		boolean bContains = true;
		TestBase TB = new TestBase();
		TB.waitFor(driver, By.cssSelector(".table.table-striped.table-bordered.table-condensed.dataTablePrincipal"));
		List <String> sColumna = prueba.TraerColumna("//*[@id=\"mainOppsTable\"]/tbody", 3, 1);
		for (String sNombreDeLaCuenta:sColumna) {
			if (!sNombreDeLaCuenta.contains("APER")) {
				bContains = false;
				break;
			}
		}
		WebElement wMenuSiguiente = driver.findElement(By.cssSelector(".btn-group.pull-right"));
		List<WebElement> wMenu2 = wMenuSiguiente.findElements(By.tagName("input"));
		boolean bSiguiente = wMenu2.get(2).isEnabled();
		if(bSiguiente)wMenu2.get(2).click();
		while (bSiguiente) { 
			System.out.println("Is enable");
			TB.waitFor(driver, By.cssSelector(".table.table-striped.table-bordered.table-condensed.dataTablePrincipal"));
			sColumna = prueba.TraerColumna("//*[@id=\"mainOppsTable\"]/tbody", 3, 1);
			System.out.println("Paso 1 vez");
			for (String sNombreDeLaCuenta:sColumna) {
				if (!sNombreDeLaCuenta.contains("APER")) {
					bContains = false;
					break;
				}
			}
			wMenuSiguiente = driver.findElement(By.cssSelector(".btn-group.pull-right"));
			wMenu = wMenuSiguiente.findElements(By.tagName("input"));
			bSiguiente = wMenu.get(2).isEnabled();
			if(bSiguiente)wMenu2.get(2).click();
		}
		System.out.println(bContains);
	}*/
	
}