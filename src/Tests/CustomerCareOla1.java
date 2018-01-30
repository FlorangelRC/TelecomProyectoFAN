package Tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.CustomerCare;
import Tests.TestBase.IrA;

public class CustomerCareOla1 extends TestBase {

	CustomerCare page;
	
	
	@BeforeClass (groups = "CustomerCare")
	public void init() {
		inicializarDriver();
		page = new CustomerCare(driver);
		login();
		IrA.CajonDeAplicaciones.ConsolaFAN();
	}
	
	//@AfterClass (groups = "CustomerCare")
	public void quit() {
		page.cerrarTodasLasPestañas();
		IrA.CajonDeAplicaciones.Ventas();
		cerrarTodo();
	}
	
	@BeforeMethod (groups = "CustomerCare")
	public void after() {
		page.cerrarTodasLasPestañas();
	}
	
	
	@Test
	public void TS90461_Adjustments_and_Esccalations_Adjustments_and_Escalations_Sesión_guiada_Visualizar_Gestion_Ajustes() {
		page.elegirCuenta("aaaaFernando Care");
		page.buscarGestion("ajustes");
		List <WebElement> list = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.slds-truncate"));
		Assert.assertTrue(list.get(0).getText().toLowerCase().contains("ajustes"));
	}
	
	@Test (groups = {"CustomerCare", "360View"})
	public void TS90462_360_VIEW_Suspensiones_and_Reconexiones_Visualizar_pantalla_para_seleccionar_el_tipo_de_acción_a_realizar_Suspensión_Rehabilitación() {
		page.elegirCuenta("aaaaFernando Care");
		page.irAGestion("suspensiones");
		List <WebElement> gest = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		boolean a = false;
		boolean b = false;
		for (WebElement x : gest) {
			if (x.getText().contains("Suspensi\u00f3n")) {
				a = true;
			}
			if (x.getText().contains("Habilitaci\u00f3n")) {
				b = true;
			}
		}
		Assert.assertTrue(a && b);
	}
	
	@Test
	public void TS90481_360_VIEW_Adjustments_and_scalations_Visualizacion_Ajustes_y_Casos_Relacionados_Visualizar_un_botón_Siguiente_que_me_permita_avanzar_al_siguiente_paso_del_proceso() {
		page.elegirCuenta("aaaaFernando Care");
		page.irAGestion("ajuste");
		boolean a = false;
		if (driver.findElement(By.id("Step-ApplyAdjustToAccountOrService_nextBtn")).isDisplayed()) {
			a = true;
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = {"CustomerCare", "360View"})
	public void TS90498_360_VIEW_Suspensiones_and_Reconexiones_Session_Guiada_Visualizar_la_opción_Suspensión_en_el_panel_de_gestiones() {
		page.elegirCuenta("aaaaFernando Care");
		page.buscarGestion("suspensiones");
		List <WebElement> list = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.slds-truncate"));
		Assert.assertTrue(list.get(0).getText().toLowerCase().contains("suspensiones y reconexion"));
	}
}
