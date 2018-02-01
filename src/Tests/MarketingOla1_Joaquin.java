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
	
	@BeforeClass(groups = "Marketing")
	public void init() {
		inicializarDriver();
		Page = new Marketing(driver);
		Page.login("SIT");
		Page.cajonDeAplicaciones("Consola FAN");
	}
	
	@AfterClass(groups = "Marketing")
	public void exit() {
		Page.cerrarTodasLasPestañas();
		Page.cajonDeAplicaciones("Ventas");
		cerrarTodo();
	}
	
	@BeforeMethod(groups = "Marketing")
	public void before() {
		Page.cerrarTodasLasPestañas();
	}
	
	@Test(groups = "Marketing")
	public void TS98022_Visualizar_botones_ABM_del_CP() {
		Page.elegirCuenta("Florencia Marketing");
		Page.irAGestionMarketing();
		
		List<WebElement> botones = Page.obtenerBotonesClubPersonal();
		for (WebElement boton : botones) {
			Assert.assertTrue(boton.getText().contains("Alta") || boton.getText().contains("Baja") || boton.getText().contains("Modificación"));
		}
	}
	
	@Test(groups = "Marketing")
	public void TS98028_Generar_Caso_error_Fraude_Alta_CP() {
		Page.elegirCuenta("aaaaCuenta Fraude");
		Page.irAGestionMarketing();
		Page.clubPersonal("alta");
		
		if (Page.verificarMensajeDeErrorCuentaFraude()) {
			System.out.println(Page.obtenerNumeroCasoCuentaFraude());
		}
	}
}
