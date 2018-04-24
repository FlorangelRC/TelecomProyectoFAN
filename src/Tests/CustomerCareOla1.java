package Tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.CustomerCare;
import Pages.setConexion;

public class CustomerCareOla1 extends TestBase {

	private WebDriver driver;
	protected CustomerCare cc;
	
	
	@BeforeClass (alwaysRun = true, groups = {"CustomerCare", "AjustesYEscalamiento", "SuspensionYRehabilitacion", "Ola1"})
	public void init() {
		driver = setConexion.setupEze();
		sleep(5000);
		cc = new CustomerCare(driver);
		cc.login("SIT");
		cc.cajonDeAplicaciones("Consola FAN");
	}
	
	@AfterClass (alwaysRun = true, groups = {"CustomerCare", "AjustesYEscalamiento", "SuspensionYRehabilitacion", "Ola1"})
	public void quit() {
		driver.quit();
		sleep(5000);
	}
	
	@BeforeMethod (alwaysRun = true, groups = {"CustomerCare", "AjustesYEscalamiento", "SuspensionYRehabilitacion", "Ola1"})
	public void before() {
		cc.cerrarTodasLasPestañas();
	}
	
	
	@Test (groups = {"CustomerCare", "AjustesYEscalamiento", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 3)
	public void TS90442_Adjustments_and_Escalations_Configurar_Ajuste_Formato_dd_mm_yyyy_fecha_hasta_desde(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("inconvenientes");
		cc.flujoInconvenientes();
		Assert.assertTrue(driver.findElement(By.id("Desde")).getAttribute("model-date-format").contains("dd-MM-yyyy"));
		Assert.assertTrue(driver.findElement(By.id("Hasta")).getAttribute("model-date-format").contains("dd-MM-yyyy"));
	}
	
	@Test (groups = {"CustomerCare", "AjustesYEscalamiento", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 3)
	public void TS90444_Adjustments_and_Escalations_Configurar_Ajuste_Tipos_Unidades_a_Ajustar(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("inconvenientes");
		cc.flujoInconvenientes();
		driver.findElement(By.id("Unidad")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[text() = 'Datos (Mb)']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//*[text() = 'Voz']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//*[text() = 'SMS']")).isDisplayed());
	}
	
	@Test (groups = {"CustomerCare", "AjustesYEscalamiento", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 3)
	public void TS90447_Adjustments_and_Escalations_Configurar_Ajuste_Tipos_Unidades_SMS(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("inconvenientes");
		cc.flujoInconvenientes();
		driver.findElement(By.id("Unidad")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[text() = 'SMS']")).isDisplayed());
	}
	
	@Test (groups = {"CustomerCare", "AjustesYEscalamiento", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 3)
	public void TS90448_Adjustments_and_Escalations_Configurar_Ajuste_Tipos_Unidades_Datos_adaptar_campo(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("inconvenientes");
		cc.flujoInconvenientes();
		driver.findElement(By.id("Unidad")).click();
		driver.findElement(By.xpath("//*[text() = 'Datos (Mb)']")).click();
		Assert.assertTrue(driver.findElement(By.id("CantidadDatosms")).isDisplayed());
	}
	
	@Test (groups = {"CustomerCare", "AjustesYEscalamiento", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 0)
	public void TS90461_Adjustments_and_Escalations_Sesión_guiada_Visualizar_Gestion_Ajustes(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.buscarGestion("inconvenientes con cargos tasados y facturados");
		List <WebElement> list = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.slds-truncate"));
		Assert.assertTrue(list.get(0).getText().toLowerCase().contains("inconvenientes con cargos tasados y facturados"));
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 1)
	public void TS90462_360_VIEW_Suspensiones_and_Reconexiones_Visualizar_pantalla_para_seleccionar_el_tipo_de_acción_a_realizar_Suspensión_Rehabilitación(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("suspensiones");
		List <WebElement> gest = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		boolean a = false, b = false;
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
	
	@Test (groups = {"CustomerCare", "AjustesYEscalamiento", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 1)
	public void TS90469_360_VIEW_Ajustes_y_Escalaciones_Selección_de_Concepto_Tipo_de_Cargo_Item_Motivo_Visualizar_parametro_Concepto(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("inconvenientes");
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.id("CboConcepto")).getAttribute("required").equals("true"));
	}
	
	@Test (groups = {"CustomerCare", "AjustesYEscalamiento", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 2)
	public void TS90481_360_VIEW_Adjustments_and_scalations_Visualizacion_Ajustes_y_Casos_Relacionados_Visualizar_un_botón_Siguiente_que_me_permita_avanzar_al_siguiente_paso_del_proceso(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("inconvenientes");
		sleep(5000);
		boolean a = false;
		if (driver.findElement(By.id("Step-TipodeAjuste_nextBtn")).isDisplayed()) {
			a = true;
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 0)
	public void TS90498_360_VIEW_Suspensiones_and_Reconexiones_Session_Guiada_Visualizar_la_opción_Suspensión_en_el_panel_de_gestiones(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.buscarGestion("suspensiones");
		List <WebElement> list = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.slds-truncate"));
		Assert.assertTrue(list.get(0).getText().toLowerCase().contains("suspensiones y reconexion"));
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 1)
	public void TS90499_360_VIEW_Suspensiones_and_Reconexiones_Session_Guiada_Visualizar_la_opción_Habilitación_en_el_panel_de_gestiones(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("suspensiones");
		List <WebElement> hab = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		boolean a = false;
		for (WebElement x : hab) {
			if (x.getText().toLowerCase().contains("habilitaci\u00f3n")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 6)
	public void TS95637_Suspensiones_and_Reconexiones_Creación_del_Caso_Back_office_Creación_caso_comentario_de_resolucion_La_gestion_ha_sido_realizada_exitosamente(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("suspensiones y reconexion back");
		List <WebElement> hab = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : hab) {
			if (x.getText().toLowerCase().contains("habilitaci\u00f3n")) {
				x.click();
				break;
			}
		}
		sleep(3000);
		driver.findElement(By.id("Step1SelectSuspensionOrReconnection_nextBtn")).click();
		sleep(3000);
		List <WebElement> dni = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : dni) {
			if (x.getText().toLowerCase().contains("dni/cuit")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step2-SelectAssetOrDocument_nextBtn")).click();
		sleep(3000);
		driver.findElement(By.id("Step3_nextBtn")).click();
		sleep(3000);
		driver.findElement(By.id("Step4_nextBtn")).click();
		sleep(3000);
		driver.findElement(By.id("StepSummary_nextBtn")).click();
		sleep(7000);
		List <WebElement> gest = driver.findElements(By.className("ta-care-omniscript-done"));
		boolean a = false;
		for (WebElement x : gest) {
			if (x.getText().toLowerCase().contains("tu gesti\u00f3n se realiz\u00f3 con \u00e9xito")) {
				a = true;
			}
		}
		String msg = driver.findElement(By.cssSelector(".vlc-slds-inline-control__label.ng-binding")).getText();
		int i = 0;
		while(msg.charAt(i++) != '0') {	}
		String caso = msg.substring(i-1, msg.length());
		cc.buscarCaso(caso);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".feeditemaux.cxfeeditemaux.CreateRecordAuxBody")));
		WebElement vc = driver.findElement(By.cssSelector(".feeditemaux.cxfeeditemaux.CreateRecordAuxBody"));
		Assert.assertTrue(vc.getText().toLowerCase().contains("habilitaci\u00f3n administrativa"));
		Assert.assertTrue(a);
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 4)
	public void TS95641_Suspensiones_and_Reconexiones_Creación_del_Caso_Creación_caso_habilitacion_status(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("suspensiones");
		List <WebElement> hab = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : hab) {
			if (x.getText().toLowerCase().contains("habilitaci\u00f3n")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step1-SuspensionOrReconnection_nextBtn")).click();
		sleep(3000);
		List <WebElement> eq = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : eq) {
			if (x.getText().toLowerCase().equals("equipo")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step2-AssetTypeSelection_nextBtn")).click();
		sleep(3000);
		List <WebElement> num = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for (WebElement x : num) {
			if (x.getText().toLowerCase().contains("543343344409154")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step3.5B-DeviceForLine_nextBtn")).click();
		sleep(5000);
		driver.findElement(By.id("Step6-Summary_nextBtn")).click();
		sleep(3000);
		driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")).get(1).click();
		sleep(3000);
		List <WebElement> msj = driver.findElements(By.className("ta-care-omniscript-done"));
		boolean a = false;
		for (WebElement x : msj) {
			if (x.getText().toLowerCase().contains("tu gesti\u00f3n se realiz\u00f3 con \u00e9xito")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 4)
	public void TS95647_Suspensiones_and_Reconexiones_Creación_del_Caso_Creación_caso_habilitacion_Líneas_y_o_equipos_seleccionados(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("suspensiones");
		List <WebElement> hab = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : hab) {
			if (x.getText().toLowerCase().contains("habilitaci\u00f3n")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step1-SuspensionOrReconnection_nextBtn")).click();
		sleep(3000);
		List <WebElement> eq = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : eq) {
			if (x.getText().toLowerCase().equals("equipo")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step2-AssetTypeSelection_nextBtn")).click();
		sleep(3000);
		List <WebElement> num = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for (WebElement x : num) {
			if (x.getText().toLowerCase().contains("543343344409154")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step3.5B-DeviceForLine_nextBtn")).click();
		sleep(5000);
		driver.findElement(By.id("Step6-Summary_nextBtn")).click();
		sleep(3000);
		driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")).get(1).click();
		sleep(3000);
		String msg = driver.findElement(By.cssSelector(".vlc-slds-inline-control__label.ng-binding")).getText();
		int i = 0;
		while(msg.charAt(i++) != '0') {	}
		String caso = msg.substring(i-1, msg.length());
		cc.buscarCaso(caso);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".feeditemaux.cxfeeditemaux.CreateRecordAuxBody")));
		WebElement vc = driver.findElement(By.cssSelector(".feeditemaux.cxfeeditemaux.CreateRecordAuxBody"));
		Assert.assertTrue(vc.getText().toLowerCase().contains("suspensiones & reconexiones"));
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 4)
	public void TS95651_Suspensiones_and_Reconexiones_Creación_del_Caso_Suspensión_Nivel_cuenta_campo_pais(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("suspensiones");
		List <WebElement> hab = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : hab) {
			if (x.getText().toLowerCase().contains("suspensi\u00f3n")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step1-SuspensionOrReconnection_nextBtn")).click();
		sleep(3000);
		List <WebElement> eq = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : eq) {
			if (x.getText().toLowerCase().equals("equipo")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step2-AssetTypeSelection_nextBtn")).click();
		sleep(3000);
		List <WebElement> num = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for (WebElement x : num) {
			if (x.getText().toLowerCase().contains("equipo facturado")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step3.5A-DeviceForLine_nextBtn")).click();
		sleep(3000);
		List <WebElement> tds = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : tds) {
			if (x.getText().toLowerCase().contains("robo")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step4-SuspensionReason_nextBtn")).click();
		sleep(3000);
		List <WebElement> pais = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		boolean a = false, b = false;
		for (WebElement x : pais) {
			if (x.getText().toLowerCase().contains("argentina")) {
				a = true;
			}
			if (x.getText().toLowerCase().contains("exterior del pa\u00eds")) {
				b = true;
			}
		}
		Assert.assertTrue(a && b);
		Assert.assertTrue(driver.findElement(By.id("State")).isEnabled() && driver.findElement(By.id("State")).getAttribute("required").equals("true"));
		Assert.assertTrue(driver.findElement(By.id("Partido")).isEnabled() && driver.findElement(By.id("Partido")).getAttribute("required").equals("true"));
		Assert.assertTrue(driver.findElement(By.id("CityTypeAhead")).isEnabled() && driver.findElement(By.id("CityTypeAhead")).getAttribute("required").equals("true"));
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 6)
	public void TS95927_360_VIEW_Suspensiones_and_Reconexiones_Seleccionar_tipo_Siniestro_Back_Office_Verificar_que_si_selecciono_Suspension_pueda_ser_de_DNI_CUIT(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("suspensiones y reconexion back");
		List <WebElement> sus = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : sus) {
			if (x.getText().toLowerCase().contains("suspensi\u00f3n")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step1SelectSuspensionOrReconnection_nextBtn")).click();
		sleep(3000);
		List <WebElement> dni = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		boolean a = false;
		for (WebElement x : dni) {
			if (x.getText().toLowerCase().contains("dni/cuit")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 6)
	public void TS95928_Suspensiones_and_Reconexiones_Seleccionar_tipo_Siniestro_Back_Office_Verificar_que_si_selecciono_Suspension_pueda_ser_de_CUENTA_DE_FACTURACION(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("suspensiones y reconexion back");
		List <WebElement> sus = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : sus) {
			if (x.getText().toLowerCase().contains("suspensi\u00f3n")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step1SelectSuspensionOrReconnection_nextBtn")).click();
		sleep(3000);
		List <WebElement> dni = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		boolean a = false, b = false;
		for (WebElement x : dni) {
			if (x.getText().toLowerCase().contains("cuenta de facturacion")) {
				a = true;
			}
			if (x.getText().toLowerCase().contains("linea")) {
				b = true;
			}
		}
		Assert.assertTrue(a && b);
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 5)
	public void TS95965_Suspensiones_and_Reconexiones_Configurar_el_tipo_de_Siniestro_Seleccionar_Solicitante_No_titular_habilita_para_completar_Apellido(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("suspensiones");
		List <WebElement> hab = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : hab) {
			if (x.getText().toLowerCase().contains("suspensi\u00f3n")) {
				x.click();
				break;
			}
		}
		sleep(2000);
		driver.findElement(By.id("Step1-SuspensionOrReconnection_nextBtn")).click();
		sleep(3000);
		List <WebElement> eq = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : eq) {
			if (x.getText().toLowerCase().equals("equipo")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step2-AssetTypeSelection_nextBtn")).click();
		sleep(3000);
		List <WebElement> num = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for (WebElement x : num) {
			if (x.getText().toLowerCase().contains("equipo facturado")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step3.5A-DeviceForLine_nextBtn")).click();
		sleep(3000);
		List <WebElement> tds = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : tds) {
			if (x.getText().toLowerCase().contains("robo")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step4-SuspensionReason_nextBtn")).click();
		sleep(7000);
		List <WebElement> no = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : no) {
			if (x.getText().toLowerCase().equals("no")) {
				x.click();
				break;
			}
		}
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.id("LastName")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("DNI")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("FirstName")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("Phone")).isEnabled());
	}
	
	@Test (groups = {"CustomerCare", "AjustesYEscalamiento", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 2)
	public void TS96020_Adjustments_and_Escalations_Consulta_de_Ajuste_Historicos_Visualizar_Ajuste_historico_de_la_cuenta(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.className("story-container")));
		List <WebElement> list = driver.findElements(By.className("story-container"));
		boolean a = false, b = false;
		for (WebElement x : list) {
			if (x.getText().toLowerCase().contains("t\u00edtulo")) {
				a = true;
			}
			if (x.getText().toLowerCase().contains("inconvenientes con cargos tasados y facturados")) {
				b = true;
			}
		}
		Assert.assertTrue(a && b);
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 6)
	public void TS96046_Suspensiones_and_Reconexiones_Creacion_del_Caso_Back_office_Creacion_caso_Subject_Suspencion_Administrativa(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("suspensiones y reconexion back");
		List <WebElement> sus = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : sus) {
			if (x.getText().toLowerCase().contains("suspensi\u00f3n")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step1SelectSuspensionOrReconnection_nextBtn")).click();
		sleep(3000);
		List <WebElement> dni = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : dni) {
			if (x.getText().toLowerCase().contains("dni/cuit")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step2-SelectAssetOrDocument_nextBtn")).click();
		sleep(3000);
		driver.findElement(By.id("Step3_nextBtn")).click();
		sleep(3000);
		Select tsus = new Select (driver.findElement(By.id("SelectFraud")));  
		tsus.selectByVisibleText("Comercial");
		Select sub = new Select (driver.findElement(By.id("SelectSubFraud")));  
		sub.selectByVisibleText("Fraude por suscripción");
		driver.findElement(By.id("Step4_nextBtn")).click();
		sleep(3000);
		driver.findElement(By.id("StepSummary_nextBtn")).click();
		sleep(3000);
		List <WebElement> msj = driver.findElements(By.className("ta-care-omniscript-done"));
		boolean a = false;
		for (WebElement x : msj) {
			if (x.getText().toLowerCase().contains("tu gesti\u00f3n se realiz\u00f3 con \u00e9xito")) {
				a = true;
			}
		}
		String msg = driver.findElement(By.cssSelector(".vlc-slds-inline-control__label.ng-binding")).getText();
		int i = 0;
		while(msg.charAt(i++) != '0') {	}
		String caso = msg.substring(i-1, msg.length());
		cc.buscarCaso(caso);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".feeditemaux.cxfeeditemaux.CreateRecordAuxBody")));
		WebElement vc = driver.findElement(By.cssSelector(".feeditemaux.cxfeeditemaux.CreateRecordAuxBody"));
		Assert.assertTrue(a);
		Assert.assertTrue(vc.getText().toLowerCase().contains("suspension administrativa"));
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 5)
	public void TS96074_360_VIEW_Suspensiones_and_Reconexiones_Seleccionar_tipo_Siniestro_Visualizar_opcion_Tipo_de_Siniestro(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("suspensiones");
		List <WebElement> sus = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : sus) {
			if (x.getText().toLowerCase().contains("suspensi\u00f3n")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step1-SuspensionOrReconnection_nextBtn")).click();
		sleep(5000);
		List <WebElement> linea = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : linea) {
			if (x.getText().toLowerCase().contains("linea")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step2-AssetTypeSelection_nextBtn")).click();
		sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"AssetsM0\"]/div/fieldset/div/span/label/span[2]")).click();
		driver.findElement(By.id("Step3-AvailableAssetsSelection_nextBtn")).click();
		sleep(5000);
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		boolean a = false, b = false, c = false;
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("robo")) {
				a = true;
			}
			if (x.getText().toLowerCase().contains("hurto")) {
				b = true;
			}
			if (x.getText().toLowerCase().contains("extrav\u00edo")) {
				c = true;
			}
		}
		Assert.assertTrue(a && b && c);
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 5)
	public void TS96075_Suspensiones_and_Reconexiones_Seleccionar_tipo_Siniestro_Verificar_que_la_opcion_Tipo_de_Siniestro_se_de_seleccion_unica(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("suspensiones");
		List <WebElement> hab = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : hab) {
			if (x.getText().toLowerCase().contains("suspensi\u00f3n")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step1-SuspensionOrReconnection_nextBtn")).click();
		sleep(3000);
		List <WebElement> eq = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : eq) {
			if (x.getText().toLowerCase().equals("equipo")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step2-AssetTypeSelection_nextBtn")).click();
		sleep(3000);
		List <WebElement> num = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for (WebElement x : num) {
			if (x.getText().toLowerCase().contains("equipo facturado")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step3.5A-DeviceForLine_nextBtn")).click();
		sleep(3000);
		List <WebElement> ro = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : ro) {
			if (x.getText().toLowerCase().contains("robo")) {
				x.click();
				break;
			}
		}
		Assert.assertTrue(driver.findElement(By.cssSelector(".ng-not-empty.ng-dirty.ng-valid.ng-valid-required.ng-touched.ng-valid-parse")).isSelected());		
		List <WebElement> hur = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : hur) {
			if (x.getText().toLowerCase().contains("hurto")) {
				x.click();
				break;
			}
		}
		Assert.assertTrue(!(driver.findElement(By.xpath("//*[@id=\"Radio3-ReasonSuspension|0\"]/div/div[1]/label[1]/span[1]")).isSelected()));
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 2)
	public void TS96078_Suspensiones_and_Reconexiones_Seleccionar_Tipo_de_gestion_Suspension_Reconexion_Verficiar_que_al_seleccionar_Suspension_se_muestren_las_opciones_Linea_Linea__Equipo_Equipo(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("suspensiones");
		List <WebElement> sus = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : sus) {
			if (x.getText().toLowerCase().contains("suspensi\u00f3n")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step1-SuspensionOrReconnection_nextBtn")).click();
		sleep(5000);
		List <WebElement> linea = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		boolean a = false, b = false, c = false;
		for (WebElement x : linea) {
			if (x.getText().toLowerCase().contains("linea")) {
				a = true;
			}
			if (x.getText().toLowerCase().contains("linea + equipo")) {
				b = true;
			}
			if (x.getText().toLowerCase().contains("equipo")) {
				c = true;
			}
		}
		Assert.assertTrue(a && b && c);
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 2)
	public void TS96080_Suspensiones_and_Reconexiones_Seleccionar_Tipo_de_gestion_Suspension_Reconexion_Seleccionar_Habilitacion_para_workplace_personalizada_se_muestren_las_opciones_Linea_Linea___Equipo_Equipo(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("suspensiones");
		List <WebElement> sus = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : sus) {
			if (x.getText().toLowerCase().contains("habilitaci\u00f3n")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step1-SuspensionOrReconnection_nextBtn")).click();
		sleep(5000);
		List <WebElement> linea = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		boolean a = false, b = false, c = false;
		for (WebElement x : linea) {
			if (x.getText().toLowerCase().contains("linea")) {
				a = true;
			}
			if (x.getText().toLowerCase().contains("linea + equipo")) {
				b = true;
			}
			if (x.getText().toLowerCase().contains("equipo")) {
				c = true;
			}
		}
		Assert.assertTrue(a && b && c);
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 3)
	public void TS96104_Suspensiones_and_Reconexiones_Visualizar_Lineas_Habilitacion_Verificar_que_sean_campos_de_seleccion_unica(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("suspensiones");
		List <WebElement> sus = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : sus) {
			if (x.getText().toLowerCase().contains("habilitaci\u00f3n")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step1-SuspensionOrReconnection_nextBtn")).click();
		sleep(5000);
		List <WebElement> lin = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : lin) {
			if (x.getText().toLowerCase().contains("linea")) {
				x.click();
				break;
			}
		}
		sleep(3000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".ng-not-empty.ng-dirty.ng-valid.ng-valid-required.ng-touched.ng-valid-parse")).isSelected());
		List <WebElement> eq = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : eq) {
			if (x.getText().toLowerCase().contains("equipo")) {
				x.click();
				break;
			}
		}
		sleep(3000);
		Assert.assertTrue(!(driver.findElement(By.cssSelector(".ng-not-empty.ng-dirty.ng-valid.ng-valid-required.ng-touched")).isSelected()));
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 6)
	public void TS96111_Suspensiones_and_Reconexiones_Seleccionar_tipo_Siniestro_Back_Office_Verificar_que_si_selecciono_Reconexion_pueda_ser_de_DNI_CUIT(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("suspensiones y reconexion back");
		List <WebElement> sus = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : sus) {
			if (x.getText().toLowerCase().contains("habilitaci\u00f3n")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step1SelectSuspensionOrReconnection_nextBtn")).click();
		sleep(3000);
		List <WebElement> dni = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		boolean a = false, b = false;
		for (WebElement x : dni) {
			if (x.getText().toLowerCase().contains("dni/cuit")) {
				a = true;
			}
			if (x.getText().toLowerCase().contains("cuenta de facturacion")) {
				b = true;
			}
		}
		Assert.assertTrue(a && b);
	}
	
	@Test(groups = {"CustomerCare", "Ola1", "AjustesYEscalamiento"}, dataProvider = "CustomerCuentaActiva", priority = 3)
	public void TS90443_Adjustments_and_Esccalations_Adjustments_and_Escalations_Configurar_Ajuste_Formato_monto_con_2_decimales(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("inconvenientes");
		cc.flujoInconvenientes();
		driver.findElement(By.id("Unidad")).click();
		driver.findElement(By.xpath("//*[text() = 'Credito']")).click();
		sleep(2000);
		WebElement monto = cc.obtenerCampo("CantidadMonto");
		monto.sendKeys("55511");
		String valorCampo = obtenerValorDelCampo(monto);
		Assert.assertTrue(valorCampo.contentEquals("555.11"));
	}
	
	@Test(groups = {"CustomerCare", "Ola1", "AjustesYEscalamiento"}, dataProvider = "CustomerCuentaActiva", priority = 3)
	public void TS90446_Adjustments_and_Esccalations_Adjustments_and_Escalations_Configurar_Ajuste_Tipos_Unidades_VOZ_HH_MM_SS(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("inconvenientes");
		cc.flujoInconvenientes();
		driver.findElement(By.id("Unidad")).click();
		driver.findElement(By.xpath("//*[text() = 'Voz']")).click();
		sleep(2000);
		WebElement cantidad = cc.obtenerCampo("CantidadVoz");
		cantidad.sendKeys("042050");
		String valorCampo = obtenerValorDelCampo(cantidad);		
		Assert.assertTrue(valorCampo.contentEquals("04:20:50"));
	}
	
	@Test(groups = {"CustomerCare", "Ola1", "AjustesYEscalamiento"}, dataProvider = "CustomerCuentaActiva", priority = 2)
	public void TS90454_Adjustments_and_Esccalations_Adjustments_and_Escalations_UX_Visualizacion_Ajustes_y_Casos_Relacionados_Visualizar_botón_siguiente_OS(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("inconvenientes");
		sleep(5000);
		driver.findElement(By.id("CboConcepto")).click();
		driver.findElement(By.xpath("//*[text() = 'CREDITO PREPAGO']")).click();
		driver.findElement(By.id("CboItem")).click();
		driver.findElement(By.xpath("//*[text() = 'Consumos de datos']")).click();
		driver.findElement(By.id("CboMotivo")).click();
		driver.findElement(By.xpath("//*[text() = 'Error/omisión/demora gestión']")).click();
		List <WebElement> si = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : si) {
			if (x.getText().toLowerCase().equals("si")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step-TipodeAjuste_nextBtn")).click();
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.id("Step-AssetSelection_nextBtn")).isDisplayed());
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 2)
	public void TS95964_360_VIEW_Suspensiones_and_Reconexiones_Configurar_el_tipo_de_Siniestro_Solicitante_No_titular_habilita_para_completar_Nombre_Apellido_DNI_telefono_de_contacto(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("suspensiones");
		List <WebElement> sus = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : sus) {
			if (x.getText().toLowerCase().contains("suspensi\u00f3n")) {
				x.click();
				break;
			}
		}
		sleep(2000);
		driver.findElement(By.id("Step1-SuspensionOrReconnection_nextBtn")).click();
		sleep(5000);
		List <WebElement> linea = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : linea) {
			if (x.getText().toLowerCase().contains("linea")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step2-AssetTypeSelection_nextBtn")).click();
		sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"AssetsM0\"]/div/fieldset/div/span/label/span[2]")).click();
		driver.findElement(By.id("Step3-AvailableAssetsSelection_nextBtn")).click();
		sleep(5000);
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("robo")) {
				x.click();
				break;
			}
		}
		sleep(3000);
		driver.findElement(By.id("Step4-SuspensionReason_nextBtn")).click();
		sleep(3000);
		List <WebElement> no = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : no) {
			if (x.getText().toLowerCase().contains("no")) {
				x.click();
				break;
			}
		}
		sleep(3000);
		Assert.assertTrue(driver.findElement(By.id("DNI")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("FirstName")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("LastName")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("Phone")).isDisplayed());
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 1)
	public void TS95934_360_VIEW_Suspensiones_and_Reconexiones_Seleccionar_tipo_Siniestro_Back_Office_Reconexion_pueda_ser_de_Linea(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("suspensiones y reconexion back");
		List <WebElement> sus = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : sus) {
			if (x.getText().toLowerCase().contains("habilitaci\u00f3n")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step1SelectSuspensionOrReconnection_nextBtn")).click();
		sleep(3000);
		boolean a = false;
		List <WebElement> linea = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : linea) {
			if (x.getText().toLowerCase().contains("linea")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 2)
	public void TS95929_360_VIEW_Suspensiones_and_Reconexiones_Seleccionar_tipo_Siniestro_Back_Office_Verificar_que_si_selecciono_Suspension_pueda_ser_de_Linea(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("suspensiones y reconexion back");
		List <WebElement> sus = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : sus) {
			if (x.getText().toLowerCase().contains("suspensi\u00f3n")) {
				x.click();
				break;
			}
		}
		sleep(2000);
		driver.findElement(By.id("Step1SelectSuspensionOrReconnection_nextBtn")).click();
		sleep(3000);
		boolean a = false;
		List <WebElement> linea = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : linea) {
			if (x.getText().toLowerCase().contains("linea")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 3)
	public void TS95973_360_VIEW_Suspensiones_and_Reconexiones_Configurar_el_tipo_de_Siniestro_Direccion_del_Siniestro_y_Exterior_del_Pais_habilita_un_campo_para_ingresar_el_pais(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("suspensiones");
		List <WebElement> sus = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : sus) {
			if (x.getText().toLowerCase().contains("suspensi\u00f3n")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step1-SuspensionOrReconnection_nextBtn")).click();
		sleep(5000);
		List <WebElement> linea = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : linea) {
			if (x.getText().toLowerCase().contains("linea")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step2-AssetTypeSelection_nextBtn")).click();
		sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"AssetsM0\"]/div/fieldset/div/span/label/span[2]")).click();
		driver.findElement(By.id("Step3-AvailableAssetsSelection_nextBtn")).click();
		sleep(5000);
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("robo")) {
				x.click();
				break;
			}
		}
		sleep(3000);
		driver.findElement(By.id("Step4-SuspensionReason_nextBtn")).click();
		sleep(3000);
		List <WebElement> ext = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : ext) {
			if (x.getText().toLowerCase().contains("exterior del pa\u00eds")) {
				x.click();
				break;
			}
		}
		Assert.assertTrue(driver.findElement(By.id("Country")).isDisplayed());
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 1)
	public void TS96112_360_VIEW_Suspensiones_and_Reconexiones_Seleccionar_tipo_Siniestro_Back_Office_Verificar_que_si_selecciono_Reconexion_pueda_ser_de_CUENTA_DE_FACTURACION(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("suspensiones y reconexion back");
		List <WebElement> sus = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : sus) {
			if (x.getText().toLowerCase().contains("habilitaci\u00f3n")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step1SelectSuspensionOrReconnection_nextBtn")).click();
		sleep(3000);
		boolean a = false;
		List <WebElement> linea = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : linea) {
			if (x.getText().toLowerCase().contains("cuenta de facturacion")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = {"CustomerCare", "AjustesYEscalamiento", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 4)
	public void TS90472_360_VIEW_Ajustes_y_Escalaciones_Seleccion_de_Concepto_Tipo_de_Cargo_Item_Motivo_Validar_Concepto_solo_se_puede_permitir_1_valor_de_la_lista_indicada_del_parametro_Tipo_de_Cargo(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("inconvenientes");
		sleep(5000);
		driver.findElement(By.id("CboConcepto")).click();
		driver.findElement(By.xpath("//*[text() = 'CARGOS AUN NO FACTURADOS']")).click();
		driver.findElement(By.id("CboTipo")).click();
		driver.findElement(By.xpath("//*[text() = 'Otros cargos no facturados']")).click();
		String a = driver.findElement(By.id("CboTipo")).getAttribute("value");
		driver.findElement(By.id("CboTipo")).click();
		driver.findElement(By.xpath("//*[text() = 'Cargos fijos no facturados']")).click();
		Assert.assertTrue(!driver.findElement(By.id("CboTipo")).getAttribute("value").equals(a));
	}
	
	@Test (groups = {"CustomerCare", "AjustesYEscalamiento", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 4)
	public void TS90476_360_VIEW_Ajustes_y_Escalaciones_Seleccion_de_Concepto_Tipo_de_Cargo_Item_Motivo_Validar_que_se_puede_seleccionar_un_Motivo_solo_si_se_han_completado_los_valores_anteriores(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("inconvenientes");
		sleep(5000);
		driver.findElement(By.id("CboMotivo")).click();
		driver.findElement(By.xpath("//*[text() = 'Error/omisi\u00f3n/demora gesti\u00f3n']")).click();
		String a = driver.findElement(By.id("CboMotivo")).getAttribute("value");
		driver.findElement(By.id("CboMotivo")).click();
		driver.findElement(By.xpath("//*[text() = 'Informacion incorrecta']")).click();
		Assert.assertTrue(!driver.findElement(By.id("CboMotivo")).getAttribute("value").equals(a));
	}
	
	@Test (groups = {"CustomerCare", "AjustesYEscalamiento", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 4)
	public void TS90470_360_VIEW_Ajustes_y_Escalaciones_Seleccion_de_Concepto_Tipo_de_Cargo_Item_Motivo_Validar_que_solo_se_puede_permitir_1_valor_de_la_lista_indicada_del_parametro_Conceptos(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("inconvenientes");
		sleep(5000);
		driver.findElement(By.id("CboConcepto")).click();
		driver.findElement(By.xpath("//*[text() = 'CREDITO PREPAGO']")).click();
		String a = driver.findElement(By.id("CboConcepto")).getAttribute("value");
		driver.findElement(By.id("CboConcepto")).click();
		driver.findElement(By.xpath("//*[text() = 'CREDITO POSPAGO']")).click();
		Assert.assertTrue(!driver.findElement(By.id("CboMotivo")).getAttribute("value").equals(a));
	}
	
	@Test (groups = {"CustomerCare", "AjustesYEscalamiento", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 3)
	public void TS96011_360_VIEW_Ajustes_y_Escalaciones_Seleccion_de_Concepto_Tipo_de_Cargo_Item_Motivo_Visualizar_parametro_item(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("inconvenientes");
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.id("CboItem")).isEnabled());
	}
	
	@Test (groups = {"CustomerCare", "AjustesYEscalamiento", "Ola1"}, dataProvider = "CustomerCuentaActiva", priority = 2)
	public void TS95996_Adjustments_and_Escalations_Cierre_Caso_Gestion_Exitosa_Rechazada_Ampliar_detalles_Cerrar_caso_exitoso_comentario(String nCuenta) {
		cc.elegirCuenta(nCuenta);
		cc.irAGestion("inconvenientes");
		cc.flujoInconvenientes();
		List <WebElement> no = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : no) {
			if (x.getText().toLowerCase().equals("no")) {
				x.click();
				break;
			}
		}
		sleep(2000);
		driver.findElement(By.id("Step-AjusteNivelLinea_nextBtn")).click();
		sleep(5000);
		driver.findElement(By.id("SummaryDerivateToBO_nextBtn")).click();
		sleep(3000);
		WebElement gest = driver.findElement(By.cssSelector(".slds-box.ng-scope"));
		boolean a = false, b = false;
		if (gest.getText().toLowerCase().contains("la gesti\u00f3n se deriv\u00f3 al area")) {
			a = true;
		}
		if (gest.getText().toLowerCase().contains("el n\u00famero de caso es")) {
			b = true;
		}
		Assert.assertTrue(a && b);
	}
}