package Tests;

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
		Page.cerrarTodasLasPestañas();
		Page.cajonDeAplicaciones("Ventas");
		cerrarTodo();
	}
	
	@BeforeMethod(groups = {"CustomerCare", "Ola2"})
	public void before() {
		Page.cerrarTodasLasPestañas();
	}
	
	@Test(groups = {"CustomerCare", "Ola2"})
	public void TS100939_360_View_360_View_POSTPAGO_Visualización_Resumen_de_Facturación_Verificación_filtro_Fecha() {
		Page.elegirCuenta("aaaaFernando Care");
		Page.irAFacturacion();
		Page.irAResumenDeCuenta();
		
		WebElement fechaHasta = Page.obtenerFechaHasta();
		Assert.assertTrue(fechaHasta.getAttribute("value").contentEquals(fechaDeHoy()));
	}
	
	@Test(groups = {"CustomerCare", "Ola2", "Marcas"})
	public void TS100963_Marks_Management_Marks_Management_Base_Conocimiento_Acceso_a_base_de_conocimiento(){
		Page.elegirCuenta("aaaaFernando Care");
		Page.irAGestion("Gestión de Marcas");
		
		Assert.assertTrue(Page.verificarBaseConocimientoMarcas());
	}
	
	@Test(groups = {"CustomerCare", "Ola2", "Marcas"})
	public void TS100967_Marks_Management_Marks_Management_Escenario_de_Casos_Existentes_Ingresar_comentarios() {
		Page.elegirCuenta("aaaaFernando Care");
		Page.irAGestion("Gestión de Marcas");
		Page.SeleccionarClienteOCuenta("Cliente");
		Page.botonSiguiente().click();
		sleep(1000);
		
		Page.seleccionarMarca(1);
		Page.botonConsultar().click();
		
		Assert.assertTrue(Page.campoComentarios().isDisplayed());
	}
	
	@Test(groups = {"CustomerCare", "Ola2", "Marcas"})
	public void TS100971_Marks_Management_Marcas_Session_Guiada_Botón_en_Iniciar_gestiones() {
		Page.elegirCuenta("aaaaFernando Care");
		Page.irAGestion("Gestión de Marcas");
		WebElement tab = Page.obtenerPestañaActiva();
		
		Assert.assertTrue(tab.getText().contentEquals("Gestión de Marcas"));
	}
	
}