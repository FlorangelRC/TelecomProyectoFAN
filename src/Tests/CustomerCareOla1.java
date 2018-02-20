package Tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.CustomerCare;

public class CustomerCareOla1 extends TestBase {

	CustomerCare cc;
	
	
	@BeforeClass (alwaysRun = true)
	public void init() {
		inicializarDriver();
		cc = new CustomerCare(driver);
		login();
		IrA.CajonDeAplicaciones.ConsolaFAN();
	}
	
<<<<<<< Updated upstream
	//@AfterClass (alwaysRun = true)
=======
	@AfterClass (alwaysRun = true)
>>>>>>> Stashed changes
	public void quit() {
		driver.quit();
	}
	
	@BeforeMethod (alwaysRun = true)
	public void after() {
		cc.cerrarTodasLasPestañas();
	}
	
	
	@Test (groups = {"CustomerCare", "AjustesYEscalamiento", "Ola1"})  //popUp en el 4° paso no deja terminar el caso
	public void TS90442_Adjustments_and_Escalations_Configurar_Ajuste_Formato_dd_mm_yyyy_fecha_hasta_desde() {
		cc.elegirCuenta("aaaaFernando Care");
		cc.irAGestion("ajuste");
		List <WebElement> serv = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : serv) {
			if (x.getText().toLowerCase().contains("un servicio")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step-ApplyAdjustToAccountOrService_nextBtn")).click();
		sleep(5000);
		driver.findElement(By.id("Step-AssetSelection_nextBtn")).click();
		sleep(5000);
		driver.findElement(By.id("CboConcepto")).click();
		driver.findElement(By.xpath("//*[text() = 'CARGOS AUN NO FACTURADOS']")).click();
		driver.findElement(By.id("CboTipo")).click();
		driver.findElement(By.xpath("//*[text() = 'Otros cargos no facturados']")).click();
		driver.findElement(By.id("CboItem")).click();
		driver.findElement(By.xpath("//*[text() = 'Cargo de reconexión']")).click();
		driver.findElement(By.id("CboMotivo")).click();
		driver.findElement(By.xpath("//*[text() = 'Error/omisión/demora gestión']")).click();
		driver.findElement(By.id("Step-TipodeAjuste_nextBtn")).click();
		sleep(5000);
		Assert.assertTrue(false);
		//LO SIGUIENTE ES LO QUE SE NECESITA PARA FINALIZAR EL CASO
		/*List <WebElement> si = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : si) {
			if (x.getText().toLowerCase().contains("ajustar")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step-HistoricalAdjustments_nextBtn")).click();
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.id("Desde")).getAttribute("vlc-slds-model-date-format").contains("dd-MM-yyyy"));
		Assert.assertTrue(driver.findElement(By.id("Hasta")).getAttribute("vlc-slds-model-date-format").contains("dd-MM-yyyy"));*/
	}
	
	@Test (groups = {"CustomerCare", "AjustesYEscalamiento", "Ola1"})  //popUp en el 4° paso no deja terminar el caso
	public void TS90444_Adjustments_and_Escalations_Configurar_Ajuste_Tipos_Unidades_a_Ajustar() {
		cc.elegirCuenta("aaaaFernando Care");
		cc.irAGestion("ajuste");
		List <WebElement> serv = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : serv) {
			if (x.getText().toLowerCase().contains("un servicio")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step-ApplyAdjustToAccountOrService_nextBtn")).click();
		sleep(5000);
		driver.findElement(By.id("Step-AssetSelection_nextBtn")).click();
		sleep(5000);
		driver.findElement(By.id("CboConcepto")).click();
		driver.findElement(By.xpath("//*[text() = 'CARGOS AUN NO FACTURADOS']")).click();
		driver.findElement(By.id("CboTipo")).click();
		driver.findElement(By.xpath("//*[text() = 'Otros cargos no facturados']")).click();
		driver.findElement(By.id("CboItem")).click();
		driver.findElement(By.xpath("//*[text() = 'Cargo de reconexión']")).click();
		driver.findElement(By.id("CboMotivo")).click();
		driver.findElement(By.xpath("//*[text() = 'Error/omisión/demora gestión']")).click();
		driver.findElement(By.id("Step-TipodeAjuste_nextBtn")).click();
		sleep(5000);
		Assert.assertTrue(false);
		//LO SIGUIENTE ES LO QUE SE NECESITA PARA FINALIZAR EL CASO
		/*List <WebElement> si = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : si) {
			if (x.getText().toLowerCase().contains("ajustar")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step-HistoricalAdjustments_nextBtn")).click();
		sleep(5000);
		driver.findElement(By.id("Unidad")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[text() = 'Datos (Mb)']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//*[text() = 'Voz']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//*[text() = 'SMS']")).isDisplayed());*/
	}
	
	@Test (groups = {"CustomerCare", "AjustesYEscalamiento", "Ola1"})  //popUp en el 4° paso no deja terminar el caso
	public void TS90447_Adjustments_and_Escalations_Configurar_Ajuste_Tipos_Unidades_SMS() {
		Assert.assertTrue(false);
	}
	
	@Test (groups = {"CustomerCare", "AjustesYEscalamiento", "Ola1"})  //popUp en el 4° paso no deja terminar el caso
	public void TS90448_Adjustments_and_Escalations_Configurar_Ajuste_Tipos_Unidades_Datos_adaptar_campo() {
		Assert.assertTrue(false);
	}
	
	@Test (groups = {"CustomerCare", "AjustesYEscalamiento", "Ola1"})
	public void TS90461_Adjustments_and_Escalations_Sesión_guiada_Visualizar_Gestion_Ajustes() {
		cc.elegirCuenta("aaaaFernando Care");
		cc.buscarGestion("ajustes");
		List <WebElement> list = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.slds-truncate"));
		Assert.assertTrue(list.get(0).getText().toLowerCase().contains("ajustes"));
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion(Online)", "Ola1"})
	public void TS90462_360_VIEW_Suspensiones_and_Reconexiones_Visualizar_pantalla_para_seleccionar_el_tipo_de_acción_a_realizar_Suspensión_Rehabilitación() {
		cc.elegirCuenta("aaaaFernando Care");
		cc.irAGestion("suspensiones");
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
	
	@Test (groups = {"CustomerCare", "AjustesYEscalamiento", "Ola1"})
	public void TS90469_360_VIEW_Ajustes_y_Escalaciones_Selección_de_Concepto_Tipo_de_Cargo_Item_Motivo_Visualizar_parametro_Concepto() {
		cc.elegirCuenta("aaaaFernando Care");
		cc.irAGestion("ajuste");
		List <WebElement> serv = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : serv) {
			if (x.getText().toLowerCase().contains("un servicio")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("Step-ApplyAdjustToAccountOrService_nextBtn")).click();
		sleep(5000);
		driver.findElement(By.id("Step-AssetSelection_nextBtn")).click();
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.id("CboConcepto")));
		Assert.assertTrue(driver.findElement(By.id("CboConcepto")).getAttribute("required").equals("true"));
	}
	
	@Test (groups = {"CustomerCare", "AjustesYEscalamiento", "Ola1"})
	public void TS90481_360_VIEW_Adjustments_and_scalations_Visualizacion_Ajustes_y_Casos_Relacionados_Visualizar_un_botón_Siguiente_que_me_permita_avanzar_al_siguiente_paso_del_proceso() {
		cc.elegirCuenta("aaaaFernando Care");
		cc.irAGestion("ajuste");
		boolean a = false;
		if (driver.findElement(By.id("Step-ApplyAdjustToAccountOrService_nextBtn")).isDisplayed()) {
			a = true;
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion(Online)", "Ola1"})
	public void TS90498_360_VIEW_Suspensiones_and_Reconexiones_Session_Guiada_Visualizar_la_opción_Suspensión_en_el_panel_de_gestiones() {
		cc.elegirCuenta("aaaaFernando Care");
		cc.buscarGestion("suspensiones");
		List <WebElement> list = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.slds-truncate"));
		Assert.assertTrue(list.get(0).getText().toLowerCase().contains("suspensiones y reconexion"));
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion(Online)", "Ola1"})
	public void TS90499_360_VIEW_Suspensiones_and_Reconexiones_Session_Guiada_Visualizar_la_opción_Habilitación_en_el_panel_de_gestiones() {
		cc.elegirCuenta("aaaaFernando Care");
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
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion(Online)", "Ola1"})
	public void TS95647_Suspensiones_and_Reconexiones_Creación_del_Caso_Creación_caso_habilitacion_Líneas_y_o_equipos_seleccionados() {
		cc.elegirCuenta("aaaaFernando Care");
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
		System.out.println(caso);
		cc.buscarCaso(caso);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".feeditemaux.cxfeeditemaux.CreateRecordAuxBody")));
		WebElement vc = driver.findElement(By.cssSelector(".feeditemaux.cxfeeditemaux.CreateRecordAuxBody"));
		Assert.assertTrue(vc.getText().toLowerCase().contains("suspensiones & reconexiones"));
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion(Online)", "Ola1"})
	public void TS95927_360_VIEW_Suspensiones_and_Reconexiones_Seleccionar_tipo_Siniestro_Back_Office_Verificar_que_si_selecciono_Suspension_pueda_ser_de_DNI_CUIT() {
		cc.elegirCuenta("aaaaFernando Care");
		cc.irAGestion("suspensiones y reconexion back");
		List <WebElement> sus = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : sus) {
			if (x.getText().toLowerCase().contains("suspensi\u00f3n")) {
				x.click();
				break;
			}
		}
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
	
	@Test (groups = {"CustomerCare", "AjustesYEscalamiento", "Ola1"})  //popUp en el 4° paso no deja terminar el caso
	public void TS96020_Adjustments_and_Escalations_Consulta_de_Ajuste_Historicos_Visualizar_Ajuste_historico_de_la_cuenta() {
		Assert.assertTrue(false);
	}
	
	@Test (groups = {"CustomerCare", "SuspensionYRehabilitacion(Online)", "Ola1"})
	public void TS96074_360_VIEW_Suspensiones_and_Reconexiones_Seleccionar_tipo_Siniestro_Visualizar_opcion_Tipo_de_Siniestro() {
		cc.elegirCuenta("aaaaFernando Care");
		cc.irAGestion("suspensiones");
		List <WebElement> sus = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : sus) {
			if (x.getText().toLowerCase().contains("suspensión")) {
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
		driver.findElement(By.xpath("//*[@id=\"AssetsM0\"]/div/fieldset/div/span[2]/label/span[1]")).click();
		driver.findElement(By.id("Step3-AvailableAssetsSelection_nextBtn")).click();
		sleep(5000);
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		boolean a = false;
		boolean b = false;
		boolean c = false;
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
}
