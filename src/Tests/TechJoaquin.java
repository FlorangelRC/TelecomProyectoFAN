package Tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TechJoaquin extends TestBase {
	
	public void cerrarTodasLasPestañas() {
		driver.switchTo().defaultContent();
		List<WebElement> pestañasPrimarias = driver.findElements(By.cssSelector(".x-plain-header.sd_primary_tabstrip.x-unselectable .x-tab-strip-closable"));
		if (pestañasPrimarias.size() > 0) {
			for (WebElement t : pestañasPrimarias) {
					WebElement btn_cerrar = t.findElement(By.className("x-tab-strip-close"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn_cerrar);	
			}
		}
	}
	
	private void cambiarAFrameActivo(String gest) {
		driver.switchTo().defaultContent();
		sleep(3000);
		List<WebElement> panelesCentrales = driver.findElements(By.cssSelector(".sd_secondary_container.x-border-layout-ct"));
		for (WebElement t : panelesCentrales) {
			if (!t.getAttribute("class").contains("x-hide-display")) {
				driver.switchTo().frame(t.findElement(By.cssSelector("div iframe")));
			}
		}
	}
	
	public void elegirCuenta(String nombreCuenta) {
		WebElement selector = driver.findElement(By.cssSelector(".x-btn-small.x-btn-icon-small-left"));
		if  (!selector.getText().equalsIgnoreCase("Cuentas")) {
			WebElement btnSplit = selector.findElement(By.className("x-btn-split"));
			Actions builder = new Actions(driver);   
			builder.moveToElement(btnSplit, 245, 20).click().build().perform();
			List<WebElement> desplegable = driver.findElements(By.cssSelector(".x-menu-item.accountMru.standardObject.sd-nav-menu-item"));
			for (WebElement op : desplegable) {
				if (op.getText().equalsIgnoreCase("Cuentas")) op.click();
			}
		}
		
		WebElement marcoCuentas = driver.findElement(By.cssSelector(".x-plain-body.sd_nav_tabpanel_body.x-tab-panel-body-top iframe"));
		driver.switchTo().frame(marcoCuentas);
		WebElement selectCuentas = driver.findElement(By.name("fcf"));
		Select field = new Select(selectCuentas);
		if (!field.getFirstSelectedOption().getText().equalsIgnoreCase("Todas las cuentas")) {
			field.selectByVisibleText("Todas las cuentas");
		}
		
		By cuentasLocator = By.cssSelector(".x-grid3-cell-inner.x-grid3-col-ACCOUNT_NAME");
		(new WebDriverWait(driver, 3)).until(ExpectedConditions.numberOfElementsToBe(cuentasLocator, 200));
		List<WebElement> cuentas = driver.findElements(cuentasLocator);
		for (WebElement c : cuentas) {
			if (c.getText().equalsIgnoreCase(nombreCuenta)) {
				c.findElement(By.tagName("a")).click();
				break;
			}
		}
		
		driver.switchTo().defaultContent();
		By panelesLocator = By.cssSelector(".sd_secondary_container.x-border-layout-ct");
		driver.findElements(panelesLocator);
		(new WebDriverWait(driver, 7)).until(ExpectedConditions.numberOfElementsToBe(panelesLocator, 2));
		cambiarAFrameActivo("Elegir Cuenta");
	}
	
	public WebElement obtenerAsset(String numeroLinea) {
		List<WebElement> lineasActivas = driver.findElements(By.cssSelector(".console-card.active"));
		for (WebElement l : lineasActivas) {
			if (l.findElement(By.cssSelector(".card-top")).getText().contains(numeroLinea)) {
				return l;
			}
		}
		return null;
	}
	
	public void irAAccion(WebElement asset, String accion) {
		List<WebElement> acciones = asset.findElements(By.cssSelector(".actions span"));
		for (WebElement a : acciones) {
			if (a.getText().contains(accion)) {
				a.click();
				break;
			}
		}
		cambiarAFrameActivo("Mis Servicios");
	}
	
	public void irAVerMasDetalles() {
		WebElement botonVerDetalles = driver.findElement(By.cssSelector(".slds-button.slds-button--brand"));
		waitFor.elementToBeClickable(botonVerDetalles);
		botonVerDetalles.click();
		cambiarAFrameActivo("Ver más detalles");
	}
	
	// **********************************************************************************
	// **********************************************************************************
	// **********************************************************************************
	
	@BeforeClass(groups= {"TechnicalCare"})
	public void init() {
		inicializarDriver();
		login();
		IrA.CajonDeAplicaciones.ConsolaFAN();
	}
	
	@AfterClass(groups= {"TechnicalCare"})
	public void quit() {
		cerrarTodasLasPestañas();
		IrA.CajonDeAplicaciones.Ventas();
		cerrarTodo();
	}
	
	@BeforeMethod(groups= {"TechnicalCare"})
	public void after() {
		cerrarTodasLasPestañas();
	}
	
	@Test(groups= {"TechnicalCare", "MisServicios"})
	public void TS51322_CSR_Mis_Servicios_Visualizacion_mayor_informacion() {
		elegirCuenta("Adrian Tech");
		WebElement asset = obtenerAsset("1122334456");
		irAAccion(asset, "Mis servicios");
		irAVerMasDetalles();
		
		WebElement headerServiciosDeValorAgregado = driver.findElement(By.cssSelector(".via-slds-card__header.slds-card__header"));
		waitFor.visibilityOfElement(headerServiciosDeValorAgregado);
		Assert.assertTrue(headerServiciosDeValorAgregado.isDisplayed());
	}
	
	@Test(groups= {"TechnicalCare", "MisServicios"})
	public void TS51323_CSR_Mis_Servicios_Visualizacion_estado_de_los_Servicios() {
		elegirCuenta("Adrian Tech");
		WebElement asset = obtenerAsset("1122334456");
		irAAccion(asset, "Mis servicios");
		irAVerMasDetalles();
		
		List<WebElement> estadoDeCadaServicio = driver.findElements(By.xpath("//tr[@records='records']/td[@class='addedValueServices-card-td'][3]"));
		for (WebElement e : estadoDeCadaServicio) {
			Assert.assertTrue(e.isDisplayed());
		}
	}
	
	@Test(groups= {"TechnicalCare", "MisServicios"})
	public void TS51524_CSR_Mis_Servicios_Visualizacion_Servicios_Activos_Inactivos_y_Pendientes() {
		elegirCuenta("Adrian Tech");
		WebElement asset = obtenerAsset("1122334456");
		irAAccion(asset, "Mis servicios");
		irAVerMasDetalles();
		
		List<WebElement> estadoDeCadaServicio = driver.findElements(By.xpath("//tr[@records='records']/td[@class='addedValueServices-card-td'][3]"));
		List<String> estados = new ArrayList<String>();
		for (WebElement e : estadoDeCadaServicio) {
			estados.add(e.getText());
		}
		
		Assert.assertTrue(estados.contains("Inactivo") || estados.contains("En activación") || estados.contains("Activo") || estados.contains("Limitado") || estados.contains("Pendiente"));
	}
	
	@Test(groups= {"TechnicalCare", "MisServicios"})
	public void TS51327_CSR_Mis_Servicios_Visualizacion_Servicio_Fecha_Estado() {
		elegirCuenta("Adrian Tech");
		WebElement asset = obtenerAsset("1122334456");
		irAAccion(asset, "Mis servicios");
		irAVerMasDetalles();
		
		List<WebElement> columnas = driver.findElements(By.cssSelector(".slds-card__body.cards-container .slds-text-heading--label th a"));
		List<String> texto = new ArrayList<String>();
		for (WebElement c : columnas) {
			texto.add(c.getText());
		}
		
		Assert.assertTrue(texto.contains("SERVICIO"));
		Assert.assertTrue(texto.contains("FECHA"));
		Assert.assertTrue(texto.contains("ESTADO"));
	}
	

}
