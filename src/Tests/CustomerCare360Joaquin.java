package Tests;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Pages.CustomerCare;
import Pages.setConexion;

public class CustomerCare360Joaquin extends TestBase {
	
	CustomerCare Customer;
	
	private By btn_VerDetalles = By.cssSelector(".slds-button.slds-button--brand");
	private By btn_GestionesEncontradas = By.xpath("//button[@class='slds-button slds-button--neutral slds-truncate']");
	private By tarjetaServicios360 = By.cssSelector(".console-card.active");
	private By fechaDesde = By.id("text-input-id-1");
	private By fechaHasta = By.id("text-input-id-2");
	private By iconoDesplegable = By.cssSelector(".slds-input__icon--left.slds-icon.slds-icon--x-small.slds-input__icon");
	private By campos_TarjetaHistorial = By.cssSelector(".slds-truncate.slds-th__action");
	private By detalleRegistro = By.xpath("//div[@class='slds-box--small']");
	private By tablaTarjetaHistorial = By.cssSelector(".slds-table.slds-table--bordered.slds-table--resizable-cols.slds-table--fixed-layout.via-slds-table-pinned-header");

	
	@BeforeClass(groups= {"CustomerCare", "Problems with Refills"})
	public void init() {
		inicializarDriver();
		Customer = new CustomerCare(driver);
		login();
		IrA.CajonDeAplicaciones.ConsolaFAN();
	}
	
	@AfterClass(groups= {"CustomerCare", "Problems with Refills"})
	public void quit() {
		IrA.CajonDeAplicaciones.Ventas();
		cerrarTodo();
	}
	
	@BeforeMethod(groups= {"CustomerCare", "Problems with Refills"})
	public void after() {
		Customer.cerrarTodasLasPesta�as();
	}
	
	@Test(groups= "CustomerCare")
	public void TS38068_Consumption_Details_Definicion_de_Filtros_sobre_Calendario_Fecha_Desde_No_se_puede_ingresar_una_fecha_posterior_a_d�a_de_consulta() {
		Customer.elegirCuenta("aaaaFernando Care");
		Customer.irADetalleDeConsumos();
		
		Customer.selectorPeriodo.click();
		for (WebElement opcion : Customer.opcionesSelectorPeriodo) {
			if (opcion.getText().contains("Un rango personalizado")) {
				opcion.click();
				break;
			}
		}

		Customer.calendarioFechaInicio.click();
		// Solo basta con intentar clickear el �ltimo d�a que figura que es posterior al d�a actual
		int ultimoDiaDelCalendario = Customer.diasCalendario.size() - 1;
		Customer.diasCalendario.get(ultimoDiaDelCalendario).click();
		
		Assert.assertTrue(Customer.calendarioFechaInicio.getAttribute("value").equals(""));
	}
	
	@Test(groups="CustomerCare")
	public void TS38164_360_View_UX_360_Card_Historiales_Visualizar_HISTORIAL_DE_PACKS() {
		Customer.elegirCuenta("aaaaFernando Care");
		Customer.buscarGestion("Historial de Packs");
		
		for (WebElement gestion : Customer.gestionesEncontradas) {
			if (gestion.getText().contains("Historial de Packs")) {
				Assert.assertTrue(true);
				return;
			}
		}
		Assert.assertTrue(false);
	}
	
	@Test(groups="CustomerCare")
	public void TS38166_360_View_UX_360_Card_Historiales_Visualizar_HISTORIAL_DE_AJUSTES() {
		Customer.elegirCuenta("aaaaFernando Care");
		Customer.irAHistoriales();
		
		WebElement tarjeta = Customer.obtenerTarjetaHistorial("Historial de Ajustes");
		
		Assert.assertTrue(tarjeta.getText().toLowerCase().contains("historial de ajustes"));
	}
	
	@Test(groups="CustomerCare")
	public void TS38169_360_View_UX_360_Card_Historiales_Visualizar_bot�n_Ver_Detalle_HISTORIAL_DE_RECARGAS_SOS() {
		Customer.elegirCuenta("aaaaFernando Care");
		Customer.irAHistoriales();
		
		WebElement tarjeta = Customer.obtenerTarjetaHistorial("Historial de Recargas S.O.S");
		
		Assert.assertTrue(tarjeta.findElement(btn_VerDetalles).isDisplayed());
	}
	
	@Test(groups="CustomerCare")
	public void TS38170_360_View_UX_360_Card_Historiales_Visualizar_bot�n_Ver_Detalle_HISTORIAL_DE_AJUSTES() {
		Customer.elegirCuenta("aaaaFernando Care");
		Customer.irAHistoriales();
		
		WebElement tarjeta = Customer.obtenerTarjetaHistorial("Historial de Ajustes");
		
		Assert.assertTrue(tarjeta.findElement(btn_VerDetalles).isDisplayed());
	}
	
	@Test(groups="CustomerCare")
	public void TS38172_360_View_UX_360_Card_Historiales_Campos_Historial_de_Packs() {
		Customer.elegirCuenta("aaaaFernando Care");
		Customer.irAGestion("Historial de Packs");

		Customer.botonConsultar.click();
		List<String> textoColumna = new ArrayList<String>();
		for (WebElement columna : Customer.columnasHistorial) {
			textoColumna.add(columna.getText());
		}
		
		Assert.assertTrue(textoColumna.contains("FECHA"));
		Assert.assertTrue(textoColumna.contains("VENCIMIENTO"));
		Assert.assertTrue(textoColumna.contains("NOMBRE DEL PACK"));
		Assert.assertTrue(textoColumna.contains("MONTO"));	
	}
	
	@Test(groups="CustomerCare")
	public void TS38174_360_View_UX_360_Card_Historiales_Campos_Historial_de_Ajustes() {
		Customer.elegirCuenta("aaaaFernando Care");
		Customer.irAHistoriales();
		
		WebElement tarjeta = Customer.obtenerTarjetaHistorial("Historial de Ajustes");
		
		List<WebElement> columnas = tarjeta.findElements(By.cssSelector(".slds-truncate.slds-th__action"));
		List<String> textoColumna = new ArrayList<String>();
		for (WebElement columna : columnas) {
			textoColumna.add(columna.getText());
		}
		
		Assert.assertTrue(textoColumna.contains("FECHA"));
		Assert.assertTrue(textoColumna.contains("MOTIVO"));
		Assert.assertTrue(textoColumna.contains("MONTO"));
	}

	@Test(groups="CustomerCare")
	public void TS38185_360_View_360_View_Historial_de_Packs_Desplegable_nombre_Historial_Packs() {
		Customer.elegirCuenta("aaaaFernando Care");

		Customer.irAGestion("Historial de Packs");

		Assert.assertTrue(driver.findElement(By.id("text-input-03")).isDisplayed());
	}
	
	@Test(groups="CustomerCare")
	public void TS38186_360_View_360_View_Historial_de_Packs_Fecha_Desde_y_Hasta_no_superan_los_30_dias() {
		Customer.elegirCuenta("aaaaFernando Care");

		Customer.irAGestion("Historial de Packs");

		String actual = driver.findElement(fechaHasta).getAttribute("value");
		driver.findElement(fechaHasta).click();
		List<WebElement> diasCalendario = driver.findElements(By.className("slds-day"));
		diasCalendario.get(diasCalendario.size()-1).click(); //�ltimo d�a visible del calendario
		
		Assert.assertTrue(driver.findElement(fechaHasta).getAttribute("value").equals(actual));
	}
	
	@Test(groups="CustomerCare")
	public void TS38187_360_View_360_View_Historial_de_Packs_Detalle_Aperturar_registro_Detalle() {
		Customer.elegirCuenta("aaaaFernando Care");

		Customer.irAGestion("Historial de Packs");

		driver.findElement(btn_VerDetalles).click();
		driver.findElement(By.cssSelector(".slds-input__icon--left.slds-icon.slds-icon--x-small.slds-input__icon")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='slds-box--small']")).isDisplayed());
	}
	
	
	@Test(groups="CustomerCare")
	public void TS38188_360_View_360_View_Historial_de_Packs_Detalle_Ordenamiento_columna_cierra_registros() {
		Customer.elegirCuenta("aaaaFernando Care");

		Customer.irAGestion("Historial de Packs");

		driver.findElement(btn_VerDetalles).click();
		driver.findElement(iconoDesplegable).click();
		driver.findElements(campos_TarjetaHistorial).get(2).click();
		
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			driver.findElement(detalleRegistro);
		} catch (NoSuchElementException e) {
			Assert.assertTrue(true);
			return;
		}
		finally {
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}
		Assert.assertTrue(false);
	}
	
	@Test(groups="CustomerCare")
	public void TS38189_360_View_Historial_de_Recargas_Pre_pago_Visualizaci�n_de_registros_y_criterios_de_ordenamiento_Ordenamiento_columna() {
		Customer.elegirCuenta("aaaaFernando Care");

		Customer.irAHistoriales();
		Customer.irAHistorialDeRecargas();

		driver.findElement(iconoDesplegable).click();
		driver.findElements(campos_TarjetaHistorial).get(2).click();
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			driver.findElement(detalleRegistro);
		} catch (NoSuchElementException e) {
			Assert.assertTrue(true);
			return;
		}
		finally {
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}
		
		Assert.assertTrue(false);
	}
	
	@Test(groups="CustomerCare")
	public void TS38205_Automatic_Debit_Subscriptions_Sesi�n_guiada_D�bito_Autom�tico_Inicial_Paso_2_Adhesi�n_Cuenta_NO_adherida_a_Aut_Deb_Que_se_vea() {
		Customer.elegirCuenta("aaaaFernando Care");

		Customer.irAGestion("D�bito autom�tico");
		
		dynamicWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("borderOverlay")));
		driver.findElements(By.className("borderOverlay")).get(0).click();
		dynamicWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//label[@class='slds-checkbox__label']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//label[@class='slds-checkbox__label']")).isDisplayed());
	}
	
	@Test(groups="CustomerCare")
	public void TS38233_Automatic_Debit_Subscriptions_Sesi�n_guiada_D�bito_Autom�tico_Inicial_Paso_2_Adhesi�n_Cuenta_activa_pero_con_servicios_inactivos() {
		Customer.elegirCuenta("aaaaCuenta Activa Serv Inact");
		Customer.irAGestion("D�bito autom�tico");
		
		sleep(1000);
		dynamicWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h1[contains(.,'Error')]")));

		Assert.assertTrue(driver.findElements(By.xpath("//h1[contains(.,'Error')]")).get(1).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block")).getText().contains("no tiene cuentas/servicios activos"));
	}
	
	@Test(groups="CustomerCare")
	public void TS38234_Automatic_Debit_Subscriptions_Sesi�n_guiada_D�bito_Autom�tico_Inicial_Paso_2_Adhesi�n_Cuenta_Inactiva() {
		Customer.elegirCuenta("aaaaAndres Care");
		Customer.irAGestion("D�bito autom�tico");
		
		sleep(1000);
		dynamicWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h1[contains(.,'Error')]")));

		Assert.assertTrue(driver.findElements(By.xpath("//h1[contains(.,'Error')]")).get(1).isDisplayed());
	}
	
	@Test(groups="CustomerCare")
	public void TS38235_Automatic_Debit_Subscriptions_Sesi�n_guiada_D�bito_Autom�tico_Inicial_Paso_2_Adhesi�n_Cuenta_sin_servicios () {
		Customer.elegirCuenta("aaaaCuenta Activa S/Serv");
		Customer.irAGestion("D�bito autom�tico");
		
		sleep(2000);
		dynamicWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h1[contains(.,'Error')]")));

		Assert.assertTrue(driver.findElements(By.xpath("//h1[contains(.,'Error')]")).get(1).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block")).getText().contains("no tiene cuentas/servicios activos"));
	}
	
	@Test(groups="CustomerCare")
	public void TS38416_360_View_360_card_servicio_prepago_Header_Visualizar_campos() {
		Customer.elegirCuenta("aaaaFernando Care");

		WebElement elementoIzq = driver.findElement(tarjetaServicios360).findElement(By.className("header-left"));
		WebElement elementoDer = driver.findElement(tarjetaServicios360).findElement(By.className("header-right"));
		
		Assert.assertTrue(elementoIzq.getText().toLowerCase().contains("plan") && elementoIzq.getText().toLowerCase().contains("fecha de activaci�n"));
		Assert.assertTrue(elementoDer.getText().toLowerCase().contains("l�nea"));
	}
	
	@Test(groups="CustomerCare")
	public void TS38417_360_View_360_card_servicio_prepago_Informaci�n_de_la_card_Visualizar_campos() {
		Customer.elegirCuenta("aaaaFernando Care");

		List<String> campos = new ArrayList<String>();
		campos.add("Estado");
		campos.add("Cr�dito recarga");
		campos.add("Cr�dito promocional");
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
		Customer.elegirCuenta("aaaaFernando Care");

		WebElement accion = Customer.obtenerAccionLineaPrepago("Detalle de Consumos");
		
		Assert.assertTrue(accion.getText().toLowerCase().contains("detalle de consumos"));
	}
	
	@Test(groups="CustomerCare")
	public void TS38419_360_View_360_card_servicio_prepago_Acciones_Historial_de_Recargas() {
		Customer.elegirCuenta("aaaaFernando Care");

		Customer.irAHistoriales();
		WebElement tarjeta = Customer.obtenerTarjetaHistorial("Historial de Recargas");
		
		Assert.assertTrue(tarjeta.getText().toLowerCase().contains("historial de recargas"));
	}
	
	@Test(groups="CustomerCare")
	public void TS38421_360_View_360_card_servicio_prepago_Mis_Servicios() {
		Customer.elegirCuenta("aaaaFernando Care");

		WebElement accion = Customer.obtenerAccionLineaPrepago("Mis Servicios");
		
		Assert.assertTrue(accion.getText().toLowerCase().contains("mis servicios"));
	}
	
	@Test(groups="CustomerCare")
	public void TS38471_360_View_360_card_servicio_prepago_Persistencia_Visualizar_Nombre_del_producto() {
		Customer.elegirCuenta("aaaaFernando Care");

		WebElement elementoIzq = driver.findElement(tarjetaServicios360).findElement(By.className("header-left"));
		
		Assert.assertTrue(elementoIzq.findElement(By.xpath("//h2[@class='slds-text-heading_medium']")).getText().length() > 0);
	}
	
	@Test(groups="CustomerCare")
	public void TS38472_360_View_360_card_servicio_prepago_Persistencia_Visualizar_Fecha_de_activaci�n() {
		Customer.elegirCuenta("aaaaFernando Care");

		WebElement elementoIzq = driver.findElement(tarjetaServicios360).findElement(By.className("header-left"));
		Assert.assertTrue(elementoIzq.findElement(By.className("slds-text-body_regular")).getText().length() > 0); 
	}
	
	@Test(groups="CustomerCare")
	public void TS38473_360_View_360_card_servicio_prepago_Persistencia_Visualizar_Estado() {
		Customer.elegirCuenta("aaaaFernando Care");

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
	public void TS38474_360_View_360_card_servicio_prepago_Persistencia_Visualizar_Numero_de_l�nea() {
		Customer.elegirCuenta("aaaaFernando Care");

		WebElement elementoDer = driver.findElement(tarjetaServicios360).findElement(By.className("header-right"));
		
		Assert.assertTrue(elementoDer.getText().length() > 0);
	}
	
	@Test(groups="CustomerCare")
	public void TS38475_360_View_360_card_servicio_prepago_Persistencia_Visualizar_Cr�dito_de_Recarga() {
		Customer.elegirCuenta("aaaaFernando Care");

		List<WebElement> elementos = driver.findElement(tarjetaServicios360).findElements(By.cssSelector(".slds-text-body_regular.detail-label"));
		
		for (WebElement e : elementos) {
			if (e.getText().toLowerCase().contains("cr�dito recarga")) {
				Assert.assertTrue(true);
				return;
			}
		}
		Assert.assertTrue(false);
	}
	
	@Test(groups="CustomerCare")
	public void TS38476_360_View_360_card_servicio_prepago_Persistencia_Visualizar_Internet_Disponible() {
		Customer.elegirCuenta("aaaaFernando Care");

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
		Customer.elegirCuenta("aaaaFernando Care");

		WebElement accion = Customer.obtenerAccionLineaPrepago("Detalle de Consumos");
		
		Assert.assertTrue(accion.getText().toLowerCase().contains("detalle de consumos"));
	}
	
	@Test(groups="CustomerCare")
	public void TS38479_360_View_360_card_servicio_prepago_Persistencia_Visualizar_Acciones_Ahorr�() {
		Customer.elegirCuenta("aaaaFernando Care");

		WebElement accion = Customer.obtenerAccionLineaPrepago("Ahorr�");
		
		Assert.assertTrue(accion.getText().toLowerCase().contains("ahorr�"));
	}
	
	@Test(groups="CustomerCare")
	public void TS38480_360_View_360_card_servicio_prepago_Persistencia_Visualizar_Acciones_Mis_Servicios() {
		Customer.elegirCuenta("aaaaFernando Care");

		WebElement accion = Customer.obtenerAccionLineaPrepago("Mis servicios");
		
		Assert.assertTrue(accion.getText().toLowerCase().contains("mis servicios"));
	}
	
	@Test(groups= {"CustomerCare","Problems with Refills"})
	public void TS38537_Problems_with_Refills_Problemas_con_Recargas_Medio_de_recarga_Selecci�n_simple() {
		Customer.elegirCuenta("aaaaFernando Care");

		Customer.irAProblemasConRecargas();
		
		List<WebElement> elementos = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
		dynamicWait().until(ExpectedConditions.visibilityOfAllElements(elementos));
		
		for (WebElement e : elementos) {
			if (!e.getAttribute("class").contains("itemSelected")) {
				e.click();
				sleep(1000);
				Assert.assertTrue(e.getAttribute("class").contains("itemSelected"));
				return;
			}
		}
		Assert.assertTrue(false);
	}
	
	@Test(groups= {"CustomerCare","Problems with Refills"})
	public void TS38538_Problems_with_Refills_Problemas_con_Recargas_Medio_de_recarga_Selecci�n_M�ltiple() {
		Customer.elegirCuenta("aaaaFernando Care");

		Customer.irAProblemasConRecargas();
		
		List<WebElement> elementos = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
		dynamicWait().until(ExpectedConditions.visibilityOfAllElements(elementos));
		for (WebElement e : elementos) {
			if (!e.getAttribute("class").contains("itemSelected")) {
				e.click();
				sleep(1000);
				break;
			}
		}
		
		for (WebElement e : elementos) {
			if (!e.getAttribute("class").contains("itemSelected")) {
				Assert.assertTrue(!e.getAttribute("class").contains("itemSelected"));
				return;
			}	
		}
		Assert.assertTrue(false);
	}
	
	@Test(groups= {"CustomerCare","Problems with Refills"})
	public void TS38541_Problems_with_Refills_Problemas_con_Recargas_Medio_de_recarga_Seleccionar_Tarjeta_Pre_Paga_PIN_Visible_Lote_activo() {
		Customer.elegirCuenta("aaaaFernando Care");

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

		Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(.,'Confirmaci�n')]")) != null);
	}
	
	@Test(groups= {"CustomerCare","Problems with Refills"})
	public void TS38549_Problems_with_Refills_Problemas_con_Recargas_Medio_de_recarga_Lote_Ingresa_15_d�gitos() {
		Customer.elegirCuenta("aaaaFernando Care");

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
	
	@Test(groups= {"CustomerCare","Problems with Refills"})
	public void TS38550_Problems_with_Refills_Problemas_con_Recargas_Medio_de_recarga_Lote_Ingresa_16_d�gitos() {
		Customer.elegirCuenta("aaaaFernando Care");

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
	
	@Test(groups= {"CustomerCare","Problems with Refills"})
	public void TS38551_Problems_with_Refills_Problemas_con_Recargas_Medio_de_recarga_Lote_Ingresa_17_d�gitos() {
		Customer.elegirCuenta("aaaaFernando Care");

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
	
	@Test(groups= {"CustomerCare","Problems with Refills"})
	public void TS38552_Problems_with_Refills_Problemas_con_Recargas_Medio_de_recarga_Lote_Ingresa_letras() {
		Customer.elegirCuenta("aaaaFernando Care");

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
	public void TS38628_360_View_360_View_Card_Pre_pago_Acci�n_sobre_Historiales_Visualizar_Ultimas_5_recargas_desde_el_dia_de_la_fecha() {
		Customer.elegirCuenta("aaaaFernando Care");

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
	public void TS38629_360_View_360_View_Card_Pre_pago_Acci�n_sobre_Historiales_Visualizar_Ultimas_5_recargas_SOS_desde_el_dia_de_la_fecha() {
		Customer.elegirCuenta("aaaaFernando Care");

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
	public void TS38630_360_View_360_View_Card_Pre_pago_Acci�n_sobre_Historiales_Visualizar_Ultimas_5_compras_de_Packs_desde_el_dia_de_la_fecha() {
		Customer.elegirCuenta("aaaaFernando Care");

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
	public void TS38631_360_View_360_View_Card_Pre_pago_Acci�n_sobre_Historiales_Visualizar_Ultimos_5_ajustes_desde_el_dia_de_la_fecha() {
		Customer.elegirCuenta("aaaaFernando Care");

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
	public void TS38637_360_View_360_View_Card_Pre_pago_Acci�n_sobre_Historiales_Ordenar_ajustes_por_Monto() {
		Customer.elegirCuenta("aaaaFernando Care");

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
