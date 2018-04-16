package Tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Marketing;

import java.io.IOException;
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
		Page.cerrarTodasLasPesta�as();
		Page.cajonDeAplicaciones("Ventas");
		cerrarTodo();
	}
	
	@BeforeMethod(groups = {"Marketing", "Ola1"})
	public void before() {
		Page.cerrarTodasLasPesta�as();
	}
	
	@Test(groups = {"Marketing", "Ola1", "GestionDelSocioDeClubPersonal"}, dataProvider="MarketingCuentaSinServicio")
	public void TS90242_Sucripcion_CP_No_Validacion_de_Mail(String nombreCuenta) {
		Page.elegirCuenta(nombreCuenta);
		Page.irAGestionMarketing();
		Page.clubPersonal("alta");
		
		Assert.assertTrue(Page.verificarMensajeDeErrorEmail());
	}
	
	@Test(groups = {"Marketing", "Ola1", "GestionDelSocioDeClubPersonal"}, dataProvider="MarketingCuentaNormal")
	public void TS98022_Visualizar_botones_ABM_del_CP(String nombreCuenta) {
		Page.elegirCuenta(nombreCuenta);
		Page.irAGestionMarketing();
		
		List<WebElement> botones = Page.obtenerBotonesClubPersonal();
		for (WebElement boton : botones) {
			Assert.assertTrue(boton.getText().contains("Alta") || boton.getText().contains("Baja") || boton.getText().contains("Modificaci�n"));
		}
	}
	
	@Test(groups = {"Marketing", "Ola1", "GestionDelSocioDeClubPersonal"}, dataProvider="MarketingCuentaConFraude")
	public void TS98025_Visualizar_error_Fraude_Alta_CP(String nombreCuenta) {
		Page.elegirCuenta(nombreCuenta);
		Page.irAGestionMarketing();
		Page.clubPersonal("alta");
		
		Assert.assertTrue(Page.verificarMensajeDeErrorCuentaFraude());
	}
	
	@Test(groups = {"Marketing", "Ola1", "GestionDelSocioDeClubPersonal"}, dataProvider="MarketingCuentaConFraude")
	public void TS98028_Generar_Caso_error_Fraude_Alta_CP(String nombreCuenta) {
		Page.elegirCuenta(nombreCuenta);
		Page.irAGestionMarketing();
		Page.clubPersonal("alta");
		
		String numeroCaso = null;
		if (Page.verificarMensajeDeErrorCuentaFraude()) {
			numeroCaso = Page.obtenerNumeroCasoCuentaFraude();
		}
		else Assert.assertTrue(false);
		
		Page.cerrarTodasLasPesta�as();
		Page.irACasos();
		Assert.assertTrue(Page.obtenerEstadoDelCaso(numeroCaso).contentEquals("Closed"));
	}
	
	@Test(groups = {"Marketing", "Ola1", "GestionDelSocioDeClubPersonal"}, dataProvider="MarketingCuentaNormal")
	public void TS98029_Visualizar_cuentas_Customer_Alta_CP(String nombreCuenta) {
		Page.elegirCuenta(nombreCuenta);
		Page.irAGestionMarketing();
		Page.clubPersonal("alta");
		
		Assert.assertTrue(Page.visualizarCuentasConsumerUsuarioCP());
	}
	
	@Test(groups = {"Marketing", "Ola1", "GestionDelSocioDeClubPersonal"}, dataProvider="MarketingCuentaNormal")
	public void TS98030_Visualizar_cuentas_Business_Alta_CP(String nombreCuenta) {
		Page.elegirCuenta(nombreCuenta);
		Page.irAGestionMarketing();
		Page.clubPersonal("alta");
		
		Assert.assertTrue(Page.visualizarCuentasBusinessUsuarioCP());
	}
	
	@Test(groups = {"Marketing", "Ola1", "GestionDelSocioDeClubPersonal"}, dataProvider="MarketingCuentaNormal")
	public void TS98039_Visualizacion_de_cuentas_seleccionadas_Alta_CP(String nombreCuenta) {
		Page.elegirCuenta(nombreCuenta);
		Page.irAGestionMarketing();
		Page.estadoAltaBaja("alta");
		Page.seleccionarCuenta("consumerAccounts");
		Page.botonSiguiente().click();

		Assert.assertTrue(Page.visualizarCuentasSeleccionadasConsumerCP());
	}
	
	@Test(groups = {"Marketing", "Ola1", "GestionDelSocioDeClubPersonal"}, dataProvider="MarketingCuentaNormal")
	public void TS98040_No_visualizacion_de_cuentas_sin_seleccionar_Alta_CP(String nombreCuenta) {
		Page.elegirCuenta(nombreCuenta);
		Page.irAGestionMarketing();
		Page.estadoAltaBaja("alta");
		Page.seleccionarCuenta("consumerAccounts");
		Page.botonSiguiente().click();
		
		Assert.assertTrue(!Page.visualizarCuentasSeleccionadasBusinessCP());
	}
	
	@Test(groups = {"Marketing", "Ola1", "GestionDelSocioDeClubPersonal"}, dataProvider="MarketingCuentaNormal")
	public void TS98063_Verificar_creacion_de_caso_cerrado_Notificacion_Baja_CP(String nombreCuenta) {
		Page.elegirCuenta(nombreCuenta);
		Page.irAGestionMarketing();
		Page.estadoAltaBaja("baja");
		Page.seleccionarCuenta("businessAccounts");
		Page.seleccionarMotivo(1);
		Page.botonSiguiente().click();
		sleep(1000);
		Page.botonSiguiente().click();
		sleep(1500);
		
	    String numeroCaso = Page.obtenerNumeroCasoAltaOBaja(); 
	    Page.cerrarTodasLasPesta�as();
		Page.irACasos();
		
		Assert.assertTrue(Page.obtenerEstadoDelCaso(numeroCaso).contentEquals("Closed"));
	}
	
	@Test(groups = {"Marketing", "Ola1", "GestionDelSocioDeClubPersonal"}, dataProvider="MarketingCuentaNormal")
	public void TS98064_Visualizar_mensaje_al_cerrar_el_caso_Notificacion_Baja_CP(String nombreCuenta) {
		Page.elegirCuenta(nombreCuenta);
		Page.irAGestionMarketing();
		Page.estadoAltaBaja("baja");
		Page.seleccionarCuenta("businessAccounts");
		Page.seleccionarMotivo(1);
		Page.botonSiguiente().click();
		sleep(1000);
		Page.botonSiguiente().click();
		sleep(1500);
		
		Assert.assertTrue(Page.obtenerTextoCasoGeneradoAltaOBaja().contains("Su gesti�n ha sido procesada, el n�mero es:"));
	}
}
