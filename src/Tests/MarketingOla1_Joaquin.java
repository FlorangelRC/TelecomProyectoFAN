package Tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Marketing;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class MarketingOla1_Joaquin extends TestBase {
	protected Marketing Page;
	
	@BeforeClass(groups = {"Marketing", "Ola1"})
	public void init() {
		inicializarDriver();
		Page = new Marketing(driver);
		Page.login("SIT");
		Page.cajonDeAplicaciones("Consola FAN");
	}
	
	@AfterClass(groups = {"Marketing", "Ola1"})
	public void exit() {
		Page.cerrarTodasLasPestañas();
		Page.cajonDeAplicaciones("Ventas");
		cerrarTodo();
	}
	
	@BeforeMethod(groups = {"Marketing", "Ola1"})
	public void before() {
		Page.cerrarTodasLasPestañas();
	}
	
	@Test(groups = {"Marketing", "Ola1"})
	public void TS98022_Visualizar_botones_ABM_del_CP() {
		Page.elegirCuenta("Florencia Marketing");
		Page.irAGestionMarketing();
		
		List<WebElement> botones = Page.obtenerBotonesClubPersonal();
		for (WebElement boton : botones) {
			Assert.assertTrue(boton.getText().contains("Alta") || boton.getText().contains("Baja") || boton.getText().contains("Modificación"));
		}
	}
	
	@Test(groups = {"Marketing", "Ola1"})
	public void TS98025_Visualizar_error_Fraude_Alta_CP() {
		Page.elegirCuenta("aaaaCuenta Fraude");
		Page.irAGestionMarketing();
		Page.clubPersonal("alta");
		
		Assert.assertTrue(Page.verificarMensajeDeErrorCuentaFraude());
	}
	
	@Test(groups = {"Marketing", "Ola1"})
	public void TS98028_Generar_Caso_error_Fraude_Alta_CP() {
		Page.elegirCuenta("aaaaCuenta Fraude");
		Page.irAGestionMarketing();
		Page.clubPersonal("alta");
		
		String numeroCaso = null;
		if (Page.verificarMensajeDeErrorCuentaFraude()) {
			numeroCaso = Page.obtenerNumeroCasoCuentaFraude();
		}
		else Assert.assertTrue(false);
		
		Page.cerrarTodasLasPestañas();
		Page.irACasos();
		Assert.assertTrue(Page.obtenerEstadoDelCaso(numeroCaso).contentEquals("Closed"));
	}
	
	@Test(groups = {"Marketing", "Ola1"})
	public void TS98029_Visualizar_cuentas_Customer_Alta_CP() {
		Page.elegirCuenta("Florencia Marketing");
		Page.irAGestionMarketing();
		Page.clubPersonal("alta");
		
		Assert.assertTrue(Page.visualizarCuentasConsumerUsuarioCP());
	}
	
	@Test(groups = {"Marketing", "Ola1"})
	public void TS98030_Visualizar_cuentas_Business_Alta_CP() {
		Page.elegirCuenta("Florencia Marketing");
		Page.irAGestionMarketing();
		Page.clubPersonal("alta");
		
		Assert.assertTrue(Page.visualizarCuentasBusinessUsuarioCP());
	}
	
	@Test(groups = {"Marketing", "Ola1"})
	public void TS98039_Visualizacion_de_cuentas_seleccionadas_Alta_CP() {
		Page.elegirCuenta("Florencia Marketing");
		Page.irAGestionMarketing();
		Page.clubPersonal("alta");
		Page.seleccionarCuenta("consumerAccounts");
		Page.botonSiguiente().click();

		Assert.assertTrue(Page.visualizarCuentasSeleccionadasConsumerCP());
	}
	
	@Test(groups = {"Marketing", "Ola1"})
	public void TS98040_No_visualizacion_de_cuentas_sin_seleccionar_Alta_CP() {
		Page.elegirCuenta("Florencia Marketing");
		Page.irAGestionMarketing();
		Page.clubPersonal("alta");
		Page.seleccionarCuenta("consumerAccounts");
		Page.botonSiguiente().click();
		
		Assert.assertTrue(!Page.visualizarCuentasSeleccionadasBusinessCP());
	}
	

}
