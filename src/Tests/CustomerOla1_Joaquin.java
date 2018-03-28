package Tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.CustomerCare;
import Pages.setConexion;

public class CustomerOla1_Joaquin extends TestBase {
	
	private WebDriver driver;
	protected CustomerCare Page;
	
	
	@BeforeClass(alwaysRun = true, groups = {"CustomerCare", "Ola1", "AjustesYEscalamiento", "SuspensionYRehabilitacion"})
	public void init() {
		driver = setConexion.setupEze();
		sleep(5000);
		Page = new CustomerCare(driver);
		Page.login("SIT");
		Page.cajonDeAplicaciones("Consola FAN");
	}
	
	@AfterClass(alwaysRun = true, groups = {"CustomerCare", "Ola1", "AjustesYEscalamiento", "SuspensionYRehabilitacion"})
	public void exit() {
		driver.quit();
		sleep(5000);
	}
	
	@BeforeMethod(alwaysRun = true, groups = {"CustomerCare", "Ola1", "AjustesYEscalamiento", "SuspensionYRehabilitacion"})
	public void before() {
		Page.cerrarTodasLasPestañas();
	}
	
	
	@Test(groups = {"CustomerCare", "Ola1", "AjustesYEscalamiento"}, priority = 3)
	public void TS90443_Adjustments_and_Esccalations_Adjustments_and_Escalations_Configurar_Ajuste_Formato_monto_con_2_decimales() throws IOException {
		Page.elegirCuenta(buscarCampoExcel(2, "cuenta activa c/serv activo", 1));
		Page.irAGestion("Ajustes");
		Page.avanzarAConfigurarAjuste();
		Page.unidad("Credito");
		
		WebElement monto = Page.obtenerCampo("CantidadMonto");
		monto.sendKeys("55511");
		String valorCampo = obtenerValorDelCampo(monto);
		
		Assert.assertTrue(valorCampo.contentEquals("555.11"));
	}
	
	@Test(groups = {"CustomerCare", "Ola1", "AjustesYEscalamiento"}, priority = 3)
	public void TS90446_Adjustments_and_Esccalations_Adjustments_and_Escalations_Configurar_Ajuste_Tipos_Unidades_VOZ_HH_MM_SS() throws IOException {
		Page.elegirCuenta(buscarCampoExcel(2, "cuenta activa c/serv activo", 1));
		Page.irAGestion("Ajustes");
		Page.avanzarAConfigurarAjuste();
		Page.unidad("Voz");
		
		WebElement cantidad = Page.obtenerCampo("CantidadVoz");
		cantidad.sendKeys("042050");
		String valorCampo = obtenerValorDelCampo(cantidad);
		
		Assert.assertTrue(valorCampo.contentEquals("04:20:50"));
	}
	
	@Test(groups = {"CustomerCare", "Ola1", "AjustesYEscalamiento"}, priority = 2)
	public void TS90454_Adjustments_and_Esccalations_Adjustments_and_Escalations_UX_Visualizacion_Ajustes_y_Casos_Relacionados_Visualizar_botón_siguiente_OS() throws IOException {
		Page.elegirCuenta(buscarCampoExcel(2, "cuenta activa c/serv activo", 1));
		Page.irAGestion("Ajustes");
		
		Assert.assertTrue(Page.botonSiguiente().isDisplayed());
	}
}