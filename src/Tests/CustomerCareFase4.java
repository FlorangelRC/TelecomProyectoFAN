package Tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.BasePage;
import Pages.CustomerCare;
import Pages.setConexion;
import Tests.TestBase.IrA;

public class CustomerCareFase4 extends TestBase{

	CustomerCare page;

	
	@BeforeClass (groups = {"CustomerCare", "Vista360Layout", "DetalleDeConsumos"})
	public void init() {
		inicializarDriver();
		page = new CustomerCare(driver);
		login();
		IrA.CajonDeAplicaciones.ConsolaFAN();
	}
	
	//@AfterClass (groups = {"CustomerCare", "Vista360Layout", "DetalleDeConsumos"})
	public void quit() {
		page.cerrarTodasLasPestañas();
		IrA.CajonDeAplicaciones.Ventas();
		cerrarTodo();
	}
	
	@BeforeMethod (groups = {"CustomerCare", "Vista360Layout", "DetalleDeConsumos"})
	public void after() {
		page.cerrarTodasLasPestañas();
	}
	
	
	@Test (groups = {"CustomerCare", "Vista360Layout"})
	public void TS69033_360_View_360_View_Historiales_Formulario_Historiales_Ver_detalle_Historial_de_Recargas_Verificar_nombre_Historico() {
		page.elegirCuenta("aaaaFernando Care");
		page.irAHistoriales();		
		List <WebElement> hist = driver.findElements(By.cssSelector(".slds-p-around--large.slds-text-body--regular.labelFont"));
		for (WebElement x : hist) {
			if (x.getText().toLowerCase().contains("historial de recargas")) {
				x.click();
				break;
			}
		}
		WebElement element = page.obtenerPestañaActiva();
		Assert.assertTrue(element.getText().equals("Historiales"));
	}
	
	@Test (groups = {"CustomerCare", "Vista360Layout"})
	public void TS69034_360_View_360_View_Historiales_Formulario_Historiales_Ver_detalle_Historial_de_Packs_Verificar_nombre_Historico() {
		page.elegirCuenta("aaaaFernando Care");
		page.irAHistoriales();		
		List <WebElement> hist = driver.findElements(By.cssSelector(".slds-p-around--large.slds-text-body--regular.labelFont"));
		for (WebElement x : hist) {
			if (x.getText().toLowerCase().contains("historial de packs")) {
				x.click();
				break;
			}
		}
		WebElement element = page.obtenerPestañaActiva();
		Assert.assertTrue(element.getText().equals("Historiales"));
	}
	
	@Test (groups = {"CustomerCare", "DetalleDeConsumos"})
	public void TS69098_Consumption_Details_Detalle_de_Consumo_Modificacion_de_Tabla_Nuevas_Columnas_Visualizar_encabezado() {
		page.elegirCuenta("aaaaFernando Care");
		page.irAGestion("detalle de consu");
		WebElement title = driver.findElement(By.className("big_title"));
		Assert.assertTrue(title.getText().equals("Detalle de Consumos"));
	}
	
	@Test (groups = {"CustomerCare", "DetalleDeConsumos"})
	public void TS69104_Consumption_Details_Detalle_de_Consumo_Modificacion_de_Tabla_Nuevas_Columnas_Visualizar_calendario_en_filtro_Inicio() {
		page.elegirCuenta("aaaaFernando Care");
		page.irAGestion("detalle de consu");
		driver.findElement(By.id("text-input-02")).click();
		driver.findElement(By.xpath("//*[text() = 'Un rango personalizado']")).click();
		driver.findElement(By.id("text-input-id-1")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-datepicker.slds-dropdown.slds-dropdown--left")).isDisplayed());
	}
	
	@Test (groups = {"CustomerCare", "DetalleDeConsumos"})
	public void TS69106_Consumption_Details_Detalle_de_Consumo_Modificacion_de_Tabla_Nuevas_Columnas_Visualizar_calendario_en_filtro_Fin() {
		TestBase TB = new TestBase();
		page.elegirCuenta("aaaaFernando Care");
		page.irAGestion("detalle de consu");
		driver.findElement(By.id("text-input-02")).click();
		driver.findElement(By.xpath("//*[text() = 'Un rango personalizado']")).click();
		TB.waitFor(driver, By.id("text-input-id-2"));
		driver.findElement(By.id("text-input-id-2")).click();
		TB.waitFor(driver, By.cssSelector(".slds-datepicker.slds-dropdown.slds-dropdown--left"));
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-datepicker.slds-dropdown.slds-dropdown--left")).isDisplayed());
	}
	
	@Test (groups = {"CustomerCare", "DetalleDeConsumos"})
	public void TS69107_Consumption_Details_Detalle_de_Consumo_Modificacion_de_Tabla_Nuevas_Columnas_Visualizar_boton_Consultar() {
		page.elegirCuenta("aaaaFernando Care");
		page.irAGestion("detalle de consu");
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-button.slds-button--brand")).isDisplayed());
	}
	
	@Test (groups = {"CustomerCare", "DetalleDeConsumos"})
	public void TS69108_Consumption_Details_Detalle_de_Consumo_Modificacion_de_Tabla_Nuevas_Columnas_Visualizar_desplegable_Filtros_Avanzados() {
		page.elegirCuenta("aaaaFernando Care");
		page.irAGestion("detalle de consu");
		List <WebElement> element = driver.findElements(By.className("slds-text-heading--small"));
		boolean a = false;
		for (WebElement x : element) {
			if (x.getText().equals("Filtros avanzados")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = {"CustomerCare", "DetalleDeConsumos"})
	public void TS69110_Consumption_Details_Detalle_de_Consumo_Modificacion_de_Tabla_Nuevas_Columnas_Visualizar_filtro_avanzado_Numero_de_origen_o_destino() {
		page.elegirCuenta("aaaaFernando Care");
		page.irAGestion("detalle de consu");
		List<WebElement> filtro = driver.findElements(By.className("slds-text-heading--x-small"));
		filtro.get(0).click();
		List <WebElement> element = driver.findElements(By.className("slds-text-heading--small"));
		boolean a = false;
		for (WebElement x : element) {
			if (x.getText().equals("Número de origen o destino")) {
				a = true;
			}
		}
		List <WebElement> search = driver.findElements(By.cssSelector(".slds-form-element__control.slds-input-has-icon.slds-input-has-icon--left"));
		boolean b = false;
		if (search.get(2).getAttribute("placeholder").equals("Buscar")) {
			b = true;			
		}
		
		Assert.assertTrue(a && b);
	}
	
	@Test (groups = {"CustomerCare", "DetalleDeConsumos"})
	public void TS69112_Consumption_Details_Detalle_de_Consumo_Modificacion_de_Tabla_Nuevas_Columnas_Visualizar_filtro_avanzado_Con_o_sin_cargo() {
		page.elegirCuenta("aaaaFernando Care");
		page.irAGestion("detalle de consu");
		List<WebElement> filtro = driver.findElements(By.className("slds-text-heading--x-small"));
		filtro.get(0).click();
		List <WebElement> element = driver.findElements(By.className("slds-text-heading--small"));
		boolean a = false;
		for (WebElement x : element) {
			if (x.getText().equals("Con o sin cargo")) {
				a = true;
			}
		}
		List <WebElement> checkbox = driver.findElements(By.id("text-input-01"));
		boolean b = false;
		for (WebElement x : checkbox) {
			if (x.getAttribute("placeholder").equals("Con y sin cargo")) {
				b = true;
			}
		}
		Assert.assertTrue(a && b);
	}
	
	@Test (groups = {"CustomerCare", "DetalleDeConsumos"})
	public void TS69138_Consumption_Details_Detalle_de_Consumo_Filtro_x_Producto_Visualizar_filtro_Producto_desplegado() {
		page.elegirCuenta("aaaaFernando Care");
		page.irAGestion("detalle de consu");
		List<WebElement> filtro = driver.findElements(By.className("slds-text-heading--x-small"));
		filtro.get(0).click();
		List <WebElement> element = driver.findElements(By.className("slds-text-heading--small"));
		boolean a = false;
		for (WebElement x : element) {
			if (x.getText().equals("Número de origen o destino")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = {"CustomerCare", "DetalleDeConsumos"})
	public void TS69149_Consumption_Details_Criterios_de_Filtro_Temporal_Visualizar_filtro_Fin() {
		page.elegirCuenta("aaaaFernando Care");
		page.irAGestion("detalle de consu");
		driver.findElement(By.id("text-input-02")).click();
		driver.findElement(By.xpath("//*[text() = 'Un rango personalizado']")).click();		
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-picklist.slds-dropdown-trigger.slds-dropdown-trigger--click"));
		boolean a = false;
		for (WebElement x : element) {
			if (x.getText().equals("Fin")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
	}
}
