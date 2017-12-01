package Tests;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Pages.CustomerCare;
import Pages.setConexion;

public class CustomerCare360Joaquin extends TestBase {
	
	private By btn_VerDetalles = By.cssSelector(".slds-button.slds-button--brand");
	private By btn_GestionesEncontradas = By.xpath("//button[@class='slds-button slds-button--neutral slds-truncate']");
	private By tarjetaServicios360 = By.cssSelector(".console-card.active");
	private By fechaDesde = By.id("text-input-id-1");
	private By fechaHasta = By.id("text-input-id-2");
	private By iconoDesplegable = By.cssSelector(".slds-input__icon--left.slds-icon.slds-icon--x-small.slds-input__icon");
	private By campos_TarjetaHistorial = By.cssSelector(".slds-truncate.slds-th__action");
	private By detalleRegistro = By.xpath("//div[@class='slds-box--small']");
	private By tablaTarjetaHistorial = By.cssSelector(".slds-table.slds-table--bordered.slds-table--resizable-cols.slds-table--fixed-layout.via-slds-table-pinned-header");

	
	@BeforeClass(groups= "CustomerCare")
	public void init() {
		driver = setConexion.setupEze();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

		login();
		ConsolaFAN();
	}
	
	@AfterClass(groups= "CustomerCare")
	public void quit() {
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//span[@id='tsidLabel']")).click();
		driver.findElement(By.xpath("//a[contains(.,'Ventas')]")).click();
		driver.quit();
	}
	
	@BeforeMethod(groups= "CustomerCare")
	public void before() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.elegirCuenta("Fernando Care");
		Customer.limpiarTodo();
	}
	
	@Test(groups= "CustomerCare")
	public void TS38068_Consumption_Details_Definicion_de_Filtros_sobre_Calendario_Fecha_Desde_No_se_puede_ingresar_una_fecha_posterior_a_día_de_consulta() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.irADetalleDeConsumos();
		
		driver.findElement(By.xpath("//input[@ng-model='ptc.filterOption']")).click();
		driver.findElements(By.cssSelector(".slds-picklist.slds-dropdown-trigger.slds-dropdown-trigger--click.slds-is-open .slds-truncate")).get(2).click();
		driver.findElement(fechaDesde).click();
		List<WebElement> diasCalendario = driver.findElements(By.className("slds-day"));
		diasCalendario.get(diasCalendario.size()-1).click(); //Último día visible del calendario
		
		Assert.assertTrue(driver.findElement(fechaDesde).getAttribute("value").equals(""));
	}
	
	@Test(groups="CustomerCare")
	public void TS38164_360_View_UX_360_Card_Historiales_Visualizar_HISTORIAL_DE_PACKS() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.buscarGestion("Historial de Packs");
		
		List<WebElement> gestionesEncontradas = driver.findElements(btn_GestionesEncontradas);
		
		Assert.assertTrue(gestionesEncontradas.get(0).getText().contains("Historial de Packs"));
	}
	
	@Test(groups="CustomerCare")
	public void TS38166_360_View_UX_360_Card_Historiales_Visualizar_HISTORIAL_DE_AJUSTES() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.irAHistoriales();
		WebElement tarjeta = Customer.obtenerTarjetaHistorial("Historial de Ajustes");
		
		Assert.assertTrue(tarjeta.getText().toLowerCase().contains("historial de ajustes"));
	}
	
	@Test(groups="CustomerCare")
	public void TS38169_360_View_UX_360_Card_Historiales_Visualizar_botón_Ver_Detalle_HISTORIAL_DE_RECARGAS_SOS() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.irAHistoriales();
		WebElement tarjeta = Customer.obtenerTarjetaHistorial("Historial de Recargas S.O.S");
		
		Assert.assertTrue(tarjeta.findElement(btn_VerDetalles).isDisplayed());
	}
	
	@Test(groups="CustomerCare")
	public void TS38170_360_View_UX_360_Card_Historiales_Visualizar_botón_Ver_Detalle_HISTORIAL_DE_AJUSTES() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.irAHistoriales();
		WebElement tarjeta = Customer.obtenerTarjetaHistorial("Historial de Ajustes");
		
		Assert.assertTrue(tarjeta.findElement(btn_VerDetalles).isDisplayed());
	}
	
	@Test(groups="CustomerCare")
	public void TS38172_360_View_UX_360_Card_Historiales_Campos_Historial_de_Packs() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.irAGestion("Historial de Packs");
		
		List<String> campos = new ArrayList<String>();
		campos.add("FECHA");
		campos.add("VENCIMIENTO");
		campos.add("NOMBRE DEL PACK");
		campos.add("MONTO");

		driver.findElement(By.xpath("//button[contains(.,'Consultar')]")).click();
		List<WebElement> listaElementos = driver.findElements(By.cssSelector(".slds-truncate.slds-th__action"));
		List<String> textElementos = new ArrayList<String>();
		for (WebElement elem : listaElementos) {
			textElementos.add(elem.getText());
		}
		
		Assert.assertTrue(textElementos.containsAll(campos));
	}
	
	@Test(groups="CustomerCare")
	public void TS38174_360_View_UX_360_Card_Historiales_Campos_Historial_de_Ajustes() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.irAHistoriales();
		WebElement tarjeta = Customer.obtenerTarjetaHistorial("Historial de Ajustes");
		
		List<String> campos = new ArrayList<String>();
		campos.add("FECHA");
		campos.add("MOTIVO");
		campos.add("MONTO");
		List<WebElement> listaElementos = tarjeta.findElements(By.cssSelector(".slds-truncate.slds-th__action"));
		List<String> textElementos = new ArrayList<String>();
		for (WebElement elem : listaElementos) {
			textElementos.add(elem.getText());
		}
		
		Assert.assertTrue(textElementos.contains("FECHA"));
	}

	@Test(groups="CustomerCare")
	public void TS38185_360_View_360_View_Historial_de_Packs_Desplegable_nombre_Historial_Packs() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.irAGestion("Historial de Packs");

		Assert.assertTrue(driver.findElement(By.id("text-input-03")).isDisplayed());
	}
	
	@Test(groups="CustomerCare")
	public void TS38186_360_View_360_View_Historial_de_Packs_Fecha_Desde_y_Hasta_no_superan_los_30_dias() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.irAGestion("Historial de Packs");

		String actual = driver.findElement(fechaHasta).getAttribute("value");
		driver.findElement(fechaHasta).click();
		List<WebElement> diasCalendario = driver.findElements(By.className("slds-day"));
		diasCalendario.get(diasCalendario.size()-1).click(); //Último día visible del calendario
		
		Assert.assertTrue(driver.findElement(fechaHasta).getAttribute("value").equals(actual));
	}
	
	@Test(groups="CustomerCare")
	public void TS38187_360_View_360_View_Historial_de_Packs_Detalle_Aperturar_registro_Detalle() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.irAGestion("Historial de Packs");

		driver.findElement(btn_VerDetalles).click();
		driver.findElement(By.cssSelector(".slds-input__icon--left.slds-icon.slds-icon--x-small.slds-input__icon")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='slds-box--small']")).isDisplayed());
	}
	
	
	@Test(groups="CustomerCare")
	public void TS38188_360_View_360_View_Historial_de_Packs_Detalle_Ordenamiento_columna_cierra_registros() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.irAGestion("Historial de Packs");

		driver.findElement(btn_VerDetalles).click();
		driver.findElement(iconoDesplegable).click();
		driver.findElements(campos_TarjetaHistorial).get(2).click();
		
		try {
			driver.findElement(detalleRegistro);
		} catch (NoSuchElementException e) {
			Assert.assertTrue(true);
			return;
		}
		Assert.assertTrue(false);
	}
	
	@Test(groups="CustomerCare")
	public void TS38189_360_View_Historial_de_Recargas_Pre_pago_Visualización_de_registros_y_criterios_de_ordenamiento_Ordenamiento_columna() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.irAHistoriales();
		Customer.irAHistorialDeRecargas();

		driver.findElement(iconoDesplegable).click();
		driver.findElements(campos_TarjetaHistorial).get(2).click();
		try {
			driver.findElement(detalleRegistro);
		} catch (NoSuchElementException e) {
			Assert.assertTrue(true);
			return;
		}
		
		Assert.assertTrue(false);
	}
	
	@Test(groups="CustomerCare")
	public void TS38416_360_View_360_card_servicio_prepago_Header_Visualizar_campos() {
		WebElement elementoIzq = driver.findElement(tarjetaServicios360).findElement(By.className("header-left"));
		WebElement elementoDer = driver.findElement(tarjetaServicios360).findElement(By.className("header-right"));
		
		Assert.assertTrue(elementoIzq.getText().toLowerCase().contains("plan") && elementoIzq.getText().toLowerCase().contains("fecha de activación"));
		Assert.assertTrue(elementoDer.getText().toLowerCase().contains("línea"));
	}
	
	@Test(groups="CustomerCare")
	public void TS38417_360_View_360_card_servicio_prepago_Información_de_la_card_Visualizar_campos() {
		List<String> campos = new ArrayList<String>();
		campos.add("Estado");
		campos.add("Crédito recarga");
		campos.add("Crédito promocional");
		campos.add("Internet disponible");
		List<WebElement> elementos = driver.findElement(tarjetaServicios360).findElements(By.cssSelector(".slds-text-body_regular.detail-label"));
		List<String> textElementos = new ArrayList<String>();
		for (WebElement elem : elementos) {
			textElementos.add(elem.getText());
		}
		
		Assert.assertTrue(textElementos.containsAll(campos));
	}
	
	@Test(groups="CustomerCare")
	public void TS38418_360_View_360_card_servicio_prepago_Acciones_Detalle_de_consumos() {
		CustomerCare Customer = new CustomerCare(driver);
		WebElement accion = Customer.obtenerAccionLineaPrepago("Detalle de Consumos");
		
		Assert.assertTrue(accion.getText().toLowerCase().contains("detalle de consumos"));
	}
	
	@Test(groups="CustomerCare")
	public void TS38419_360_View_360_card_servicio_prepago_Acciones_Historial_de_Recargas() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.irAHistoriales();
		WebElement tarjeta = Customer.obtenerTarjetaHistorial("Historial de Recargas");
		
		Assert.assertTrue(tarjeta.getText().toLowerCase().contains("historial de recargas"));
	}
	
	@Test(groups="CustomerCare")
	public void TS38421_360_View_360_card_servicio_prepago_Mis_Servicios() {
		CustomerCare Customer = new CustomerCare(driver);
		WebElement accion = Customer.obtenerAccionLineaPrepago("Mis Servicios");
		
		Assert.assertTrue(accion.getText().toLowerCase().contains("mis servicios"));
	}
	
	@Test(groups="CustomerCare")
	public void TS38471_360_View_360_card_servicio_prepago_Persistencia_Visualizar_Nombre_del_producto() {
		WebElement elementoIzq = driver.findElement(tarjetaServicios360).findElement(By.className("header-left"));
		
		Assert.assertTrue(elementoIzq.findElement(By.xpath("//h2[@class='slds-text-heading_medium']")).getText().length() > 0);
	}
	
	@Test(groups="CustomerCare")
	public void TS38472_360_View_360_card_servicio_prepago_Persistencia_Visualizar_Fecha_de_activación() {
		WebElement elementoIzq = driver.findElement(tarjetaServicios360).findElement(By.className("header-left"));
		Assert.assertTrue(elementoIzq.findElement(By.className("slds-text-body_regular")).getText().length() > 0); 
	}
	
	@Test(groups="CustomerCare")
	public void TS38473_360_View_360_card_servicio_prepago_Persistencia_Visualizar_Estado() {
		List<WebElement> elementos = driver.findElement(tarjetaServicios360).findElements(By.cssSelector(".slds-text-body_regular.detail-label"));
		
		for (WebElement e : elementos) {
			if (e.getText().toLowerCase().contains("estado")) {
				Assert.assertTrue(true);
				return;
			}
		}
		Assert.assertTrue(false);
	}
	
	@Test(groups="CustomerCare")
	public void TS38474_360_View_360_card_servicio_prepago_Persistencia_Visualizar_Numero_de_línea() {
		WebElement elementoDer = driver.findElement(tarjetaServicios360).findElement(By.className("header-right"));
		
		Assert.assertTrue(elementoDer.getText().length() > 0);
	}
	
	@Test(groups="CustomerCare")
	public void TS38475_360_View_360_card_servicio_prepago_Persistencia_Visualizar_Crédito_de_Recarga() {
		List<WebElement> elementos = driver.findElement(tarjetaServicios360).findElements(By.cssSelector(".slds-text-body_regular.detail-label"));
		
		for (WebElement e : elementos) {
			if (e.getText().toLowerCase().contains("crédito recarga")) {
				Assert.assertTrue(true);
				return;
			}
		}
		Assert.assertTrue(false);
	}
	
	@Test(groups="CustomerCare")
	public void TS38476_360_View_360_card_servicio_prepago_Persistencia_Visualizar_Internet_Disponible() {
		List<WebElement> elementos = driver.findElement(tarjetaServicios360).findElements(By.cssSelector(".slds-text-body_regular.detail-label"));
		
		for (WebElement e : elementos) {
			if (e.getText().toLowerCase().contains("internet disponible")) {
				Assert.assertTrue(true);
				return;
			}
		}
		Assert.assertTrue(false);
	}
	
	@Test(groups="CustomerCare")
	public void TS38477_360_View_360_card_servicio_prepago_Persistencia_Visualizar_Acciones_Detalle_de_consumos() {
		CustomerCare Customer = new CustomerCare(driver);
		WebElement accion = Customer.obtenerAccionLineaPrepago("Detalle de Consumos");
		
		Assert.assertTrue(accion.getText().toLowerCase().contains("detalle de consumos"));
	}
	
	@Test(groups="CustomerCare")
	public void TS38479_360_View_360_card_servicio_prepago_Persistencia_Visualizar_Acciones_Ahorrá() {
		CustomerCare Customer = new CustomerCare(driver);
		WebElement accion = Customer.obtenerAccionLineaPrepago("Ahorrá");
		
		Assert.assertTrue(accion.getText().toLowerCase().contains("ahorrá"));
	}
	
	@Test(groups="CustomerCare")
	public void TS38480_360_View_360_card_servicio_prepago_Persistencia_Visualizar_Acciones_Mis_Servicios() {
		CustomerCare Customer = new CustomerCare(driver);
		WebElement accion = Customer.obtenerAccionLineaPrepago("Mis servicios");
		
		Assert.assertTrue(accion.getText().toLowerCase().contains("mis servicios"));
	}
	
	@Test(groups="CustomerCare")
	public void TS38537_Problems_with_Refills_Problemas_con_Recargas_Medio_de_recarga_Selección_simple() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.irAProblemasConRecargas();
		
		List<WebElement> elementos = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
		dynamicWait().until(ExpectedConditions.visibilityOfAllElements(elementos));
		
		for (WebElement e : elementos) {
			if (!e.getAttribute("class").contains("itemSelected")) {
				e.click();
				Assert.assertTrue(e.getAttribute("class").contains("itemSelected"));
				return;
			}
		}
		Assert.assertTrue(false);
	}
	
	@Test(groups="CustomerCare")
	public void TS38538_Problems_with_Refills_Problemas_con_Recargas_Medio_de_recarga_Selección_Múltiple() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.irAProblemasConRecargas();
		
		List<WebElement> elementos = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
		dynamicWait().until(ExpectedConditions.visibilityOfAllElements(elementos));
		for (WebElement e : elementos) {
			if (!e.getAttribute("class").contains("itemSelected")) {
				e.click();
				break;
			}
		}
		sleep(1000);
		
		for (WebElement e : elementos) {
			if (!e.getAttribute("class").contains("itemSelected")) {
				Assert.assertTrue(!e.getAttribute("class").contains("itemSelected"));
				return;
			}	
		}
		Assert.assertTrue(false);
	}
	
	@Test(groups="CustomerCare")
	public void TS38541_Problems_with_Refills_Problemas_con_Recargas_Medio_de_recarga_Seleccionar_Tarjeta_Pre_Paga_PIN_Visible_Lote_activo() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.irAProblemasConRecargas();
		
		List<WebElement> elementos = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
		dynamicWait().until(ExpectedConditions.visibilityOfAllElements(elementos));
		for (WebElement e : elementos) {
			if (e.getText().contains("Tarjeta Prepaga")) {
				e.click();
				break;
			}
		}
		driver.findElement(By.xpath("//div[@id='stepChooseMethod_nextBtn']")).findElement(By.tagName("p")).click();
		driver.findElement(By.id("lotNumber")).sendKeys("2222222222222222");
		driver.findElement(By.xpath("//div[@id='stepPrepaidCardData_nextBtn']")).findElement(By.tagName("p")).click();
		dynamicWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='slds-radio--faux ng-scope']")));
		driver.findElement(By.xpath("//span[@class='slds-radio--faux ng-scope']")).click();
		driver.findElement(By.cssSelector(".vlc-slds-transparent .slds-input.ng-pristine.ng-valid.ng-empty")).sendKeys("C:\\Intel\\Logs\\dptf.log");
		driver.findElement(By.xpath("//div[@id='stepAttachDocuments_nextBtn']")).findElement(By.tagName("p")).click();
		driver.findElement(By.xpath("//div[@id='stepSummary_nextBtn']")).findElement(By.tagName("p")).click();
		try {
			WebElement popup = driver.findElement(By.cssSelector(".slds-box.vlc-slds-remote-action__container.ng-scope"));
			popup.findElement(By.xpath("//button[contains(.,'Continue')]")).click();
		} catch (NoSuchElementException e) {}

		Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(.,'Confirmación')]")) != null);
	}
	
	@Test(groups="CustomerCare")
	public void TS38549_Problems_with_Refills_Problemas_con_Recargas_Medio_de_recarga_Lote_Ingresa_15_dígitos() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.irAProblemasConRecargas();
		
		List<WebElement> elementos = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
		dynamicWait().until(ExpectedConditions.visibilityOfAllElements(elementos));
		for (WebElement e : elementos) {
			if (e.getText().contains("Tarjeta Prepaga")) {
				e.click();
				break;
			}
		}
		driver.findElement(By.xpath("//div[@id='stepChooseMethod_nextBtn']")).findElement(By.tagName("p")).click();
		driver.findElement(By.id("lotNumber")).sendKeys("123456789012345");
		
		Assert.assertTrue(driver.findElement(By.id("lotNumber")).getAttribute("class").contains("ng-invalid-minlength"));
	}
	
	@Test(groups="CustomerCare")
	public void TS38550_Problems_with_Refills_Problemas_con_Recargas_Medio_de_recarga_Lote_Ingresa_16_dígitos() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.irAProblemasConRecargas();
		
		List<WebElement> elementos = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
		dynamicWait().until(ExpectedConditions.visibilityOfAllElements(elementos));
		for (WebElement e : elementos) {
			if (e.getText().contains("Tarjeta Prepaga")) {
				e.click();
				break;
			}
		}
		driver.findElement(By.xpath("//div[@id='stepChooseMethod_nextBtn']")).findElement(By.tagName("p")).click();
		driver.findElement(By.id("lotNumber")).sendKeys("1234567890123456");
		
		Assert.assertTrue(driver.findElement(By.id("lotNumber")).getAttribute("class").contains("ng-valid-minlength"));
		Assert.assertTrue(driver.findElement(By.id("lotNumber")).getAttribute("class").contains("ng-valid-maxlength"));
	}
	
	@Test(groups="CustomerCare")
	public void TS38551_Problems_with_Refills_Problemas_con_Recargas_Medio_de_recarga_Lote_Ingresa_17_dígitos() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.irAProblemasConRecargas();
		
		List<WebElement> elementos = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
		dynamicWait().until(ExpectedConditions.visibilityOfAllElements(elementos));
		for (WebElement e : elementos) {
			if (e.getText().contains("Tarjeta Prepaga")) {
				e.click();
				break;
			}
		}
		driver.findElement(By.xpath("//div[@id='stepChooseMethod_nextBtn']")).findElement(By.tagName("p")).click();
		driver.findElement(By.id("lotNumber")).sendKeys("12345678901234567");
		
		Assert.assertTrue(driver.findElement(By.id("lotNumber")).getAttribute("class").contains("ng-invalid-maxlength"));
	}
	
	@Test(groups="CustomerCare")
	public void TS38552_Problems_with_Refills_Problemas_con_Recargas_Medio_de_recarga_Lote_Ingresa_letras() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.irAProblemasConRecargas();

		List<WebElement> elementos = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
		dynamicWait().until(ExpectedConditions.visibilityOfAllElements(elementos));
		for (WebElement e : elementos) {
			if (e.getText().contains("Tarjeta Prepaga")) {
				e.click();
				break;
			}
		}
		driver.findElement(By.xpath("//div[@id='stepChooseMethod_nextBtn']")).findElement(By.tagName("p")).click();
		driver.findElement(By.id("lotNumber")).sendKeys("abcde");
		
		Assert.assertTrue(driver.findElement(By.id("lotNumber")).getAttribute("class").contains("ng-invalid-pattern"));
	}
	
	@Test(groups="CustomerCare")
	public void TS38628_360_View_360_View_Card_Pre_pago_Acción_sobre_Historiales_Visualizar_Ultimas_5_recargas_desde_el_dia_de_la_fecha() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.irAHistoriales();
		WebElement tarjeta = Customer.obtenerTarjetaHistorial("Historial de Recargas");
		
		WebElement tabla = tarjeta.findElement(tablaTarjetaHistorial);
		List<WebElement> lineas = tabla.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		
		Assert.assertTrue(lineas.size() <= 5);
		for (WebElement l : lineas) {
			Assert.assertTrue(l.getText().length() != 0);
		}
	}
	
	@Test(groups="CustomerCare")
	public void TS38629_360_View_360_View_Card_Pre_pago_Acción_sobre_Historiales_Visualizar_Ultimas_5_recargas_SOS_desde_el_dia_de_la_fecha() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.irAHistoriales();
		WebElement tarjeta = Customer.obtenerTarjetaHistorial("Historial de Recargas S.O.S");
		
		WebElement tabla = tarjeta.findElement(tablaTarjetaHistorial);
		List<WebElement> lineas = tabla.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		
		Assert.assertTrue(lineas.size() <= 5);
		for (WebElement l : lineas) {
			Assert.assertTrue(l.getText().length() != 0);
		}
	}
	
	@Test(groups="CustomerCare")
	public void TS38630_360_View_360_View_Card_Pre_pago_Acción_sobre_Historiales_Visualizar_Ultimas_5_compras_de_Packs_desde_el_dia_de_la_fecha() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.irAHistoriales();
		WebElement tarjeta = Customer.obtenerTarjetaHistorial("Historial de Packs");
		
		WebElement tabla = tarjeta.findElement(tablaTarjetaHistorial);
		List<WebElement> lineas = tabla.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		
		Assert.assertTrue(lineas.size() <= 5);
		for (WebElement l : lineas) {
			Assert.assertTrue(l.getText().length() != 0);
		}
	}
	
	@Test(groups="CustomerCare")
	public void TS38631_360_View_360_View_Card_Pre_pago_Acción_sobre_Historiales_Visualizar_Ultimos_5_ajustes_desde_el_dia_de_la_fecha() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.irAHistoriales();
		WebElement tarjeta = Customer.obtenerTarjetaHistorial("Historial de Ajustes");
		
		WebElement tabla = tarjeta.findElement(tablaTarjetaHistorial);
		List<WebElement> lineas = tabla.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		
		Assert.assertTrue(lineas.size() <= 5);
		for (WebElement l : lineas) {
			Assert.assertTrue(l.getText().length() != 0);
		}
	}
	
	@Test(groups="CustomerCare")
	public void TS38637_360_View_360_View_Card_Pre_pago_Acción_sobre_Historiales_Ordenar_ajustes_por_Monto() {
		CustomerCare Customer = new CustomerCare(driver);
		Customer.irAHistoriales();
		WebElement tarjeta = Customer.obtenerTarjetaHistorial("Historial de Ajustes");
		
		List<WebElement> camposOrdenables = tarjeta.findElements(campos_TarjetaHistorial);
		WebElement campo = null;
		for (WebElement c : camposOrdenables) {
			if (c.getText().contains("MONTO")) {
				campo = c;
				c.click();
				break;
			}
		}
		
		Assert.assertTrue(campo.findElement(By.cssSelector(".slds-icon.slds-icon--x-small.slds-icon-text-default.slds-is-sortable__icon")).isDisplayed());
	}
	
}
