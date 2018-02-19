package Tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.CustomerCare;

public class CustomerOla2_Joaquin extends TestBase {
	
	protected CustomerCare Page;
	
	@BeforeClass(groups = {"CustomerCare", "Ola2"})
	public void init() {
		inicializarDriver();
		Page = new CustomerCare(driver);
		Page.login("SIT");
		Page.cajonDeAplicaciones("Consola FAN");
	}
	
	@AfterClass(groups = {"CustomerCare", "Ola2"})
	public void exit() {
		Page.cerrarTodasLasPesta�as();
		Page.cajonDeAplicaciones("Ventas");
		cerrarTodo();
	}
	
	@BeforeMethod(groups = {"CustomerCare", "Ola2"})
	public void before() {
		Page.cerrarTodasLasPesta�as();
	}
	
	@Test(groups = {"CustomerCare", "Ola2"})
	public void TS100939_360_View_360_View_POSTPAGO_Visualizaci�n_Resumen_de_Facturaci�n_Verificaci�n_filtro_Fecha() {
		Page.elegirCuenta("aaaaFernando Care");
		Page.irAFacturacion();
		Page.irAResumenDeCuenta();
		
		WebElement fechaHasta = Page.obtenerFechaHasta();
		Assert.assertTrue(fechaHasta.getAttribute("value").contentEquals(fechaDeHoy()));
	}
	
	@Test(groups = {"CustomerCare", "Ola2"})
	public void TS100971_Marks_Management_Marcas_Session_Guiada_Bot�n_en_Iniciar_gestiones() {
		Page.elegirCuenta("aaaaFernando Care");
		Page.irAGestion("Gesti�n de Marcas");
		WebElement tab = Page.obtenerPesta�aActiva();
		
		Assert.assertTrue(tab.getText().contentEquals("Gesti�n de Marcas"));
	}
	
}