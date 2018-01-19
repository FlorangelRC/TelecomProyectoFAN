package Tests;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.HomeBase;
import Pages.SalesBase;
import Pages.setConexion;


public class SalesMatrix extends TestBase {
	
	
	@BeforeClass (groups = {"Sales", "Ventas", "AltaDeCuenta", "AltaDeLinea"})
	public void init() throws Exception{
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
	    try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    driver.findElement(By.xpath("//a[@href=\"/home/showAllTabs.jsp\"]")).click();
	    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[3]/div[2]/table/tbody/tr[36]/td[2]/a")).click();
	    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    driver.findElement(By.name("go")).click();
	    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}    
	}
	
	@AfterMethod (groups = {"Sales", "Ventas", "AltaDeCuenta", "AltaDeLinea"})
	public void goBack(){
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		driver.findElement(By.className("ptBreadcrumb")).findElement(By.tagName("a")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}

	@AfterClass (groups = {"Sales", "Ventas", "AltaDeCuenta", "AltaDeLinea"})
	public void tearDown() {
		driver.quit();
	}
	
	
	@Test (groups = {"Sales", "Ventas"})
	public void TS38033_Ventas_Entregas_General_Verificar_Plazo_de_Envio_para_ModoRetiroSuc_ZonaAmba() {
		SalesBase sb = new SalesBase(driver);
		sb.selectMatrix("s", "shippingtimeconfiguration");	
		BasePage cambioFrameByID = new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".rowsTbody.ng-scope")));
		WebElement element = driver.findElement(By.cssSelector(".rowsTbody.ng-scope"));
		List <WebElement> fila = element.findElements(By.tagName("tr"));
		boolean a = false;
		boolean b = false;
		boolean c = false;
		if (fila.get(2).getText().toLowerCase().contains("retiro")) {
			a = true;
		}
		if (fila.get(2).getText().toLowerCase().contains("amba")) {
			b = true;
		}
		if (fila.get(2).getText().toLowerCase().contains("2")) {
			c = true;
		}
		Assert.assertTrue(a && b && c);
	}
	
	@Test (groups = {"Sales", "Ventas"})
	public void TS38034_Ventas_Entregas_General_Verificar_Plazo_de_Envio_para_ModoRetiroSuc_ZonaCiudadPrinInt() {
		SalesBase sb = new SalesBase(driver);
		sb.selectMatrix("s", "shippingtimeconfiguration");	
		BasePage cambioFrameByID = new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".rowsTbody.ng-scope")));
		WebElement element = driver.findElement(By.cssSelector(".rowsTbody.ng-scope"));
		List <WebElement> fila = element.findElements(By.tagName("tr"));
		boolean a = false;
		boolean b = false;
		boolean c = false;
		if (fila.get(3).getText().toLowerCase().contains("retiro")) {
			a = true;
		}
		if (fila.get(3).getText().toLowerCase().contains("ciudades principales")) {
			b = true;
		}
		if (fila.get(3).getText().toLowerCase().contains("3")) {
			c = true;
		}
		Assert.assertTrue(a && b && c);
	}
	
	@Test (groups = {"Sales", "Ventas"})
	public void TS38035_Ventas_Entregas_General_Verificar_Plazo_de_Envio_para_ModoEnvEst_ZonaAmba() {
		SalesBase sb = new SalesBase(driver);
		sb.selectMatrix("s", "shippingtimeconfiguration");	
		BasePage cambioFrameByID = new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".rowsTbody.ng-scope")));
		WebElement element = driver.findElement(By.cssSelector(".rowsTbody.ng-scope"));
		List <WebElement> fila = element.findElements(By.tagName("tr"));
		boolean a = false;
		boolean b = false;
		boolean c = false;
		if (fila.get(4).getText().toLowerCase().contains("standard")) {
			a = true;
		}
		if (fila.get(4).getText().toLowerCase().contains("amba")) {
			b = true;
		}
		if (fila.get(4).getText().toLowerCase().contains("5")) {
			c = true;
		}
		Assert.assertTrue(a && b && c);
	}
	
	@Test (groups = {"Sales", "Ventas"})
	public void TS38036_Ventas_Entregas_General_Verificar_Plazo_de_Envio_para_ModoEnvEst_ZonaCiudadPrinInt() {
		SalesBase sb = new SalesBase(driver);
		sb.selectMatrix("s", "shippingtimeconfiguration");	
		BasePage cambioFrameByID = new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".rowsTbody.ng-scope")));
		WebElement element = driver.findElement(By.cssSelector(".rowsTbody.ng-scope"));
		List <WebElement> fila = element.findElements(By.tagName("tr"));
		boolean a = false;
		boolean b = false;
		boolean c = false;
		if (fila.get(5).getText().toLowerCase().contains("standard")) {
			a = true;
		}
		if (fila.get(5).getText().toLowerCase().contains("ciudades principales")) {
			b = true;
		}
		if (fila.get(5).getText().toLowerCase().contains("8")) {
			c = true;
		}
		Assert.assertTrue(a && b && c);
	}
	
	@Test (groups = {"Sales", "Ventas"})
	public void TS38037_Ventas_Entregas_General_Verificar_Plazo_de_Envio_para_ModoEnvExp_ZonaAmba() {
		SalesBase sb = new SalesBase(driver);
		sb.selectMatrix("s", "shippingtimeconfiguration");	
		BasePage cambioFrameByID = new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".rowsTbody.ng-scope")));
		WebElement element = driver.findElement(By.cssSelector(".rowsTbody.ng-scope"));
		List <WebElement> fila = element.findElements(By.tagName("tr"));
		boolean a = false;
		boolean b = false;
		boolean c = false;
		if (fila.get(0).getText().toLowerCase().contains("express")) {
			a = true;
		}
		if (fila.get(0).getText().toLowerCase().contains("amba")) {
			b = true;
		}
		if (fila.get(0).getText().toLowerCase().contains("1")) {
			c = true;
		}
		Assert.assertTrue(a && b && c);
	}
	
	@Test (groups = {"Sales", "Ventas"})
	public void TS38038_Ventas_Entregas_General_Verificar_Plazo_de_Envio_para_ModoEnvExp_ZonaCiudadPrinInt() {
		SalesBase sb = new SalesBase(driver);
		sb.selectMatrix("s", "shippingtimeconfiguration");	
		BasePage cambioFrameByID = new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".rowsTbody.ng-scope")));
		WebElement element = driver.findElement(By.cssSelector(".rowsTbody.ng-scope"));
		List <WebElement> fila = element.findElements(By.tagName("tr"));
		boolean a = false;
		boolean b = false;
		boolean c = false;
		if (fila.get(1).getText().toLowerCase().contains("express")) {
			a = true;
		}
		if (fila.get(1).getText().toLowerCase().contains("ciudades principales")) {
			b = true;
		}
		if (fila.get(1).getText().toLowerCase().contains("2")) {
			c = true;
		}
		Assert.assertTrue(a && b && c);
	}
	
	@Test (groups = {"Sales", "AltaDeCuenta"})
	public void TS76129_Alta_Cuenta_Validaciones_Verificar_creacion_de_matriz_de_validacion_de_identidad() {
		SalesBase sb = new SalesBase(driver);
		sb.selectMatrix("m", "manageableidentityvalidation");
		BasePage cambioFrameByID = new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".table.pricingMatrixTable")));
		Assert.assertTrue(driver.findElement(By.cssSelector(".table.pricingMatrixTable")).isDisplayed());
	}
	
	@Test (groups = {"Sales", "AltaDeLinea"})
	public void TS76266_Ventas_Seriales_Verificar_instancia_de_Factura_en_la_matriz_StockMovementOperation() {
		SalesBase sb = new SalesBase(driver);
		sb.selectMatrix("s", "stockmovementoperation");
		BasePage cambioFrameByID = new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".table.pricingMatrixTable")));
		WebElement tabla = driver.findElement(By.id("rowsTbody"));
		List <WebElement> fila = tabla.findElements(By.tagName("tr"));
		boolean a = false;
		boolean b = false;
		boolean c = false;
		for (WebElement x : fila) {
			if (x.findElements(By.tagName("td")).get(2).getText().contains("StorePickUp")) {
				a = true;
			}
			if (x.findElements(By.tagName("td")).get(1).getText().contains("FACTURA")) {
				b = true;
			}
			if (x.findElements(By.tagName("td")).get(4).getText().contains("VICLIE")) {
				c = true;
			}
		}
		Assert.assertTrue(a && b && c);
	}
	
	@Test (groups = {"Sales", "AltaDeLinea"})
	public void TS76267_Ventas_Seriales_Verificar_instancia_de_Entrega_eliminada_en_la_matriz_StockMovementOperation() {
		SalesBase sb = new SalesBase(driver);
		sb.selectMatrix("s", "stockmovementoperation");
		BasePage cambioFrameByID = new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".table.pricingMatrixTable")));
		WebElement tabla = driver.findElement(By.id("rowsTbody"));
		List <WebElement> fila = tabla.findElements(By.tagName("tr"));
		for (WebElement x : fila) {
			if (x.findElements(By.tagName("td")).get(2).getText().contains("StorePickUp") && x.findElements(By.tagName("td")).get(1).getText().contains("ENTREGA")) {
				Assert.assertTrue(false);
			}
		}
	}
}
