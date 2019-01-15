package Tests;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.BeFan;
import Pages.ContactSearch;
import Pages.Marketing;
import Pages.setConexion;

public class BeFANConfigurador extends TestBase {
	
	private WebDriver driver;
	private Pages.BeFan pbf;
	
	private void irA(String sMenu,String sOpcion) {
		sleep(3000);
		List<WebElement> wMenu = driver.findElement(By.className("tpt-nav")).findElements(By.className("dropdown"));
		for (WebElement wAux : wMenu) {
			if (wAux.findElement(By.className("dropdown-toggle")).getText().toLowerCase().contains(sMenu.toLowerCase())) {
				wAux.click();
			}
		}
		
		
		
		List<WebElement> wOptions = driver.findElement(By.cssSelector(".dropdown.open")).findElement(By.className("multi-column-dropdown")).findElements(By.tagName("li"));
		for (WebElement wAux2 : wOptions) {
			if (wAux2.findElement(By.tagName("a")).getText().toLowerCase().contains(sOpcion.toLowerCase())) {
				wAux2.click();
				sleep(3000);
				break;
			}
		}
	}
	

	@BeforeClass (alwaysRun = true)
	public void init() {
		driver = setConexion.setupEze();
		loginBeFANConfigurador(driver);
	}
	
	//@AfterMethod (alwaysRun = true)
	public void after() {
		driver.get(TestBase.urlBeFAN);
		sleep(3000);
	}
	
	//@AfterClass (alwaysRun = true)
	public void quit() {
		driver.quit();
	}
	
	@Test (groups = "BeFAN", dataProvider="GestionRegionesCreacion")
	public void TS126620_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Busqueda_Busqueda_especifica(String sRegion) {
		irA("Regiones", "Gesti\u00f3n");
		pbf = new Pages.BeFan(driver);
		
		Assert.assertTrue(pbf.buscarRegion(sRegion));
	}
	
	@Test (groups = "BeFAN")
	public void TS126661_BeFan_Movil_REPRO_Preactivacion_repro_Busqueda_de_archivos_Usuario_TPA_Archivos_de_otros_agentes() {
		boolean match = false;
		irA("sims", "gesti\u00f3n");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "Procesado");
		selectByText(driver.findElements(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).get(0), "VJP");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");		
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(5000);
		String agente = driver.findElement(By.id("exportarTabla")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).getText();
		List<WebElement> tabla = driver.findElement(By.id("exportarTabla")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		for (int i=0; i<9; i++) {
			if (!tabla.get(i).findElements(By.tagName("td")).get(4).getText().equals(agente))
				match = true;
			i++;
		}
		Assert.assertTrue(match);
	}
	
	@Test (groups = "BeFAN")
	public void TS126618_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Pantalla_de_inicio() {
		irA("regiones", "gesti\u00f3n");
		List<WebElement> agrupadores = driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope"));
		Assert.assertTrue(agrupadores.size() >= 1);
		if (driver.findElements(By.className("collapsed")).size() >= 1)
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
	}
	
	@Test (groups = "BeFAN")
	public void TS126621_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Busqueda_Busqueda_vacia() {
		irA("Regiones", "Gesti\u00f3n");
		sleep(3000);
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys("asd");
		driver.findElement(By.xpath("//*[@type='search']")).clear();
		sleep(3000);
		WebElement wBody = driver.findElement(By.className("panel-data"));
		List<WebElement> wList = wBody.findElements(By.xpath("//*[@class='panel-group'] //*[@class='collapsed'] //*[@class='ng-binding']"));
		
		boolean bAssert = false;
		String sAgrupador = wList.get(0).getText().toLowerCase();
		for (WebElement wAux : wList) {
			if (!wAux.getText().toLowerCase().equalsIgnoreCase(sAgrupador)) {
				bAssert = true;
				break;
			}
		}
		
		Assert.assertTrue(bAssert && wList.size() > 1);
	}
	@Test (groups = "BeFAN")
	public void TS126592_BeFan_Movil_REPRO_Preactivacion_repro_Cantidad_inexistente() {
	boolean Mensaje = false;
	irA("Cupos", "Importaci\u00f3n");
	driver.findElement(By.name("INPUT-ArchivoCupos")).sendKeys("C:\\Users\\Quelys\\Documents\\1.txt");
	sleep(3000);
	driver.findElement(By.name("BTN-Importar")).click();
	sleep(3000);
	driver.findElement(By.className("modal-header")).getText().equalsIgnoreCase("El archivo que desea importar est\u00e1 vac\u00edo.");
	Mensaje=true;
	Assert.assertTrue(Mensaje);
	
	}
	
	@Test (groups = "BeFAN")
	public void TS135630_BeFan_Movil_Repro_Preactivacion_Gestion_de_cupos_Busqueda_Sin_fecha_desde_y_sin_fecha_hasta() {
	boolean Mensaje = false;
	irA("Cupos", "Gesti\u00f3n");
	selectByText(driver.findElement(By.name("agentes")), "VJP");
	selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
	selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "Vigente");
	driver.findElement(By.id("dataPickerDesde")).click();
	driver.findElement(By.id("dataPickerDesde")).clear();
	driver.findElement(By.id("dataPickerHasta")).click();
	driver.findElement(By.id("dataPickerHasta")).clear();
	driver.findElement(By.name("buscar")).click();
	driver.findElement(By.className("modal-header")).getText().equalsIgnoreCase("Debe ingresar fecha desde y fecha hasta.");
	Mensaje=true;
	Assert.assertTrue(Mensaje);
	}
	
	@Test (groups = "BeFAN")
	public void TS135629_BeFan_Movil_Repro_Preactivacion_Gestion_de_cupos_Busqueda_Sin_fecha_hasta() {
	boolean Mensaje = false;
	irA("Cupos", "Gesti\u00f3n");
	selectByText(driver.findElement(By.name("agentes")), "VJP");
	selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
	selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "Vigente");
	driver.findElement(By.id("dataPickerDesde")).click();
	((JavascriptExecutor) driver).executeScript("document.getElementById('dataPickerDesde').value='12/12/2018'");
	driver.findElement(By.id("dataPickerHasta")).click();
	driver.findElement(By.id("dataPickerHasta")).clear();
	driver.findElement(By.name("buscar")).click();
	driver.findElement(By.className("modal-header")).getText().equalsIgnoreCase("Debe ingresar fecha desde y fecha hasta.");
	Mensaje=true;
	Assert.assertTrue(Mensaje);
	}
	
	@Test (groups = "BeFAN")
	public void TS135628_BeFan_Movil_Repro_Preactivacion_Gestion_de_cupos_Busqueda_Sin_fecha_desde() {
	boolean Mensaje = false;
	irA("Cupos", "Gesti\u00f3n");
	selectByText(driver.findElement(By.name("agentes")), "VJP");
	selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
	selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "Vigente");
	driver.findElement(By.id("dataPickerDesde")).click();
	driver.findElement(By.id("dataPickerDesde")).clear();
	driver.findElement(By.id("dataPickerHasta"));
	((JavascriptExecutor) driver).executeScript("document.getElementById('dataPickerHasta').value='28/12/2018'");
	driver.findElement(By.name("buscar")).click();
	driver.findElement(By.className("modal-header")).getText().equalsIgnoreCase("Debe ingresar fecha desde y fecha hasta.");
	Mensaje=true;
	Assert.assertTrue(Mensaje);
	}
	
	@Test (groups = "BeFAN")
	public void TS135624_BeFan_Movil_Repro_Preactivacion_Gestion_de_cupos_Valores_por_default() {
	irA("Cupos", "Gesti\u00f3n");
	boolean valores = driver.findElement(By.className("tpt-body")).findElement(By.tagName("div")).isDisplayed();
	Assert.assertTrue(valores);
		
	}
	
	@Test (groups = "BeFAN")
	public void TS135625_BeFan_Movil_Repro_Preactivacion_Gestion_de_cupos_Busqueda_Fecha_desde_Formato_erroneo() {
	irA("Cupos", "Gesti\u00f3n");
	selectByText(driver.findElement(By.name("agentes")), "VJP");
	selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
	selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "Vigente");
	driver.findElement(By.id("dataPickerDesde"));
	((JavascriptExecutor) driver).executeScript("document.getElementById('dataPickerDesde').value='12122018'");
	sleep(3000);
	driver.findElement(By.id("dataPickerHasta"));
	((JavascriptExecutor) driver).executeScript("document.getElementById('dataPickerHasta').value='28/12/2018'");
	driver.findElement(By.name("buscar")).click();
	Assert.assertTrue(driver.findElement(By.className("modal-header")).getText().equalsIgnoreCase("El formato de Fecha debe ser DD/MM/AAAA."));
	}
	
	@Test (groups = "BeFAN")
	public void TS135626_BeFan_Movil_Repro_Preactivacion_Gestion_de_cupos_Busqueda_Fecha_hasta_Formato_erroneo() {
	irA("Cupos", "Gesti\u00f3n");
	selectByText(driver.findElement(By.name("agentes")), "VJP");
	selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
	selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "Vigente");
	driver.findElement(By.id("dataPickerDesde"));
	((JavascriptExecutor) driver).executeScript("document.getElementById('dataPickerDesde').value='12/12/2018'");
	sleep(3000);
	driver.findElement(By.id("dataPickerHasta"));
	((JavascriptExecutor) driver).executeScript("document.getElementById('dataPickerHasta').value='28122018'");
	driver.findElement(By.name("buscar")).click();
	Assert.assertTrue(driver.findElement(By.className("modal-header")).getText().equalsIgnoreCase("El formato de Fecha debe ser DD/MM/AAAA."));
	}
	
	@Test (groups = "BeFAN")
	public void TS135627_BeFan_Movil_Repro_Preactivacion_Gestion_de_cupos_Busqueda_Fecha_mayor_a_90_dias() throws ParseException {
	BeFan fechas= new BeFan (driver);
	SimpleDateFormat formatoDelTexto = new SimpleDateFormat ("dd/MM/yyyy");
	String desde ="01/05/2018";
	String hasta = "25/12/2018";
	Date fechaDesde = formatoDelTexto.parse(desde);
	Date fechaHasta =formatoDelTexto.parse(hasta);
	irA("Cupos", "Gesti\u00f3n");
	selectByText(driver.findElement(By.name("agentes")), "VJP");
	selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
	selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "Vigente");
	driver.findElement(By.id("dataPickerDesde"));
	((JavascriptExecutor) driver).executeScript("document.getElementById('dataPickerDesde').value='"+desde+"'");
	sleep(3000);
	driver.findElement(By.id("dataPickerHasta"));
	((JavascriptExecutor) driver).executeScript("document.getElementById('dataPickerHasta').value='"+hasta+"'");
	int dias = fechas.numeroDiasEntreDosFechas(fechaDesde, fechaHasta);
	System.out.println("Hay " + dias + " dias, " +"Supera los 90 dias comprendidos");
	driver.findElement(By.cssSelector(".btn.btn-primary")).click();
	sleep(3000);
	driver.switchTo().defaultContent();
	Assert.assertFalse(driver.findElement(By.className("modal-content")).getText().equalsIgnoreCase("No se encontraron cupos para el filtro aplicado."));
	//Assert.assertTrue(driver.findElement(By.className("modal-header")).getText().equalsIgnoreCase("El per\u00eddo debe comprender menos de 90 d\u00edas."));
	}
	
	@Test (groups = "BeFAN")
	public void TS126633_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Busqueda_Eliminacion_de_agrupadores_No() {
		boolean cancelar = false;
		irA("regiones", "gesti\u00f3n");
		driver.findElement(By.cssSelector(".panel-group.panel-group-alternative.ng-scope")).click();
		driver.findElement(By.cssSelector(".actions.text-center")).findElement(By.cssSelector(".btn.btn-link")).click();
		sleep(3000);
		if (driver.findElement(By.className("pull-right")).findElement(By.cssSelector(".btn.btn-link")).getText().equalsIgnoreCase("Cancelar")) {
			driver.findElement(By.className("pull-right")).findElement(By.cssSelector(".btn.btn-link")).click();
			cancelar = true;
		}
		Assert.assertTrue(cancelar);
	}
	
	@Test (groups = "BeFAN")
	public void TS126631_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Busqueda_Eliminacion_de_agrupadores_Mensaje() {
		boolean mensaje = false;
		irA("regiones", "gesti\u00f3n");
		driver.findElement(By.cssSelector(".panel-group.panel-group-alternative.ng-scope")).click();
		driver.findElement(By.cssSelector(".actions.text-center")).findElement(By.cssSelector(".btn.btn-link")).click();
		sleep(3000);
		if (driver.findElement(By.cssSelector(".text-center.ng-binding")).getText().contains("�Esta seguro que desea eliminarlo ?"))
			mensaje = true;
		Assert.assertTrue(mensaje);
	}
	
	@Test (groups = "BeFAN")
	public void TS135644_BeFan_Movil_Repro_Preactivacion_Gestion_de_cupos_Busqueda_Modificacion_de_cupo_Con_total_de_cupos_invalidos() {
		irA("cupos", "gesti\u00f3n");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "VJP");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "Vigente");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(3000);
		driver.findElement(By.name("modificarGuardar")).click();
		driver.findElement(By.name("cantidadTotal")).clear();
		driver.findElement(By.name("cantidadTotal")).sendKeys("asd");
		driver.findElement(By.name("modificarGuardar")).click();
		sleep(3000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".text-center.ng-binding")).getText().equalsIgnoreCase("Cantidad de cupo inv\u00e1lida"));
		driver.findElement(By.className("pull-right")).findElement(By.cssSelector(".btn.btn-link")).click();
		sleep(3000);
		driver.findElement(By.name("modificarGuardar")).click();
		driver.findElement(By.name("cantidadTotal")).clear();
		driver.findElement(By.name("cantidadTotal")).sendKeys("$");
		driver.findElement(By.name("modificarGuardar")).click();
		sleep(3000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".text-center.ng-binding")).getText().equalsIgnoreCase("Cantidad de cupo inv\u00e1lida"));
		driver.findElement(By.className("pull-right")).findElement(By.cssSelector(".btn.btn-link")).click();
		sleep(3000);
		driver.findElement(By.name("modificarGuardar")).click();
		driver.findElement(By.name("cantidadTotal")).clear();
		driver.findElement(By.name("cantidadTotal")).sendKeys("1111111");
		driver.findElement(By.name("modificarGuardar")).click();
		sleep(3000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".text-center.ng-binding")).getText().equalsIgnoreCase("Cantidad de cupo inv\u00e1lida"));
		driver.findElement(By.className("pull-right")).findElement(By.cssSelector(".btn.btn-link")).click();
	}
	
	@Test (groups = "BeFAN")
	public void TS135640_BeFan_Movil_Repro_Preactivacion_Gestion_de_cupos_Busqueda_Modificacion_de_cupo_Mensaje() {
		irA("cupos", "gesti\u00f3n");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "VJP");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "Vigente");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(3000);
		driver.findElement(By.name("modificarGuardar")).click();
		driver.findElement(By.name("cantidadTotal")).clear();
		driver.findElement(By.name("cantidadTotal")).sendKeys("99998");
		driver.findElement(By.name("modificarGuardar")).click();
		sleep(3000);
		Assert.assertTrue(driver.findElements(By.cssSelector(".text-center.ng-binding")).get(1).getText().equalsIgnoreCase("Est\u00e1 seguro que desea modificar el registro seleccionado?"));
	}
	
	@Test (groups = "BeFAN")
	public void TS135641_BeFan_Movil_Repro_Preactivacion_Gestion_de_cupos_Busqueda_Modificacion_de_cupo_Mensaje_Confirmacion() {
		irA("cupos", "gesti\u00f3n");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "VJP");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "Vigente");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(3000);
		driver.findElement(By.name("modificarGuardar")).click();
		String cantAnterior = driver.findElement(By.name("cantidadTotal")).getAttribute("value");
		driver.findElement(By.name("cantidadTotal")).clear();
		driver.findElement(By.name("cantidadTotal")).sendKeys("99998");
		driver.findElement(By.name("modificarGuardar")).click();
		sleep(3000);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "aceptar");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-link")), "equals", "aceptar");
		driver.findElement(By.name("modificarGuardar")).click();
		Assert.assertTrue(driver.findElement(By.name("cantidadTotal")).getAttribute("value").equals("99998"));
		driver.findElement(By.name("cantidadTotal")).clear();
		driver.findElement(By.name("cantidadTotal")).sendKeys(cantAnterior);
		driver.findElement(By.name("modificarGuardar")).click();
		sleep(3000);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "aceptar");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-link")), "equals", "aceptar");
	}
	@Test (groups = "BeFAN", dataProvider="GestionRegionesCreacion")
	public void TS126619_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Creacion_de_agrupador_exitosa(String sRegion) {
		irA("Regiones", "Gesti\u00f3n");
		sleep(3000);
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(3000);
		driver.findElement(By.cssSelector(".form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys(sRegion);
		driver.findElement(By.xpath("//*[@class='btn btn-primary' and contains(text(), 'Agregar')]")).click();
		sleep(5000);
		pbf = new Pages.BeFan(driver);
		Assert.assertTrue(pbf.verificarMensajeExitoso());
		//Ask about confirmation message
		driver.findElement(By.xpath("//*[@ng-show='mensajeAgregarRegionCtrl.container.showSuccess']//*[@class='btn btn-primary']")).click();
		TS126620_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Busqueda_Busqueda_especifica(sRegion);
	}
	
	@Test (groups = "BeFAN", dataProvider="GestionRegionesCreacion")
	public void TS126623_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Busqueda_Modificacion_de_agrupadores_Asignacion_de_prefijos_a_agrupador_existente_Guardando(String sRegion) {
		irA("Regiones", "Gesti\u00f3n");
		pbf = new Pages.BeFan(driver);
		List<String> sPrefijos = pbf.agregarPrefijos(sRegion);
		
		WebElement wBody = driver.findElement(By.xpath("//*[@class='panel-collapse in collapse'] //table[@class='table table-top-fixed table-striped table-primary ng-scope']"));
		Marketing mM = new Marketing(driver);
		List<WebElement> wPrefijosWeb = mM.traerColumnaElement(wBody, 3, 2);
		boolean bAssert = false;
		for (String sAux3 : sPrefijos) {
			bAssert = false;
			for (WebElement wAux4 : wPrefijosWeb) {
				if (sAux3.equals(wAux4.getText())) {
					bAssert = true;
				}
			}
			if (!bAssert) {
				break;
			}
		}
		Assert.assertTrue(bAssert);
	}
	
	@Test (groups = "BeFAN", dataProvider="GestionRegionesCreacion")
	public void TS126623_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Busqueda_Modificacion_de_agrupadores_Eliminacion_de_prefijos_en_agrupador_existente_Guardando(String sRegion) {
		irA("Regiones", "Gesti\u00f3n");
		pbf = new Pages.BeFan(driver);
		pbf.buscarYAbrirRegion(sRegion);
		
		WebElement wBody = driver.findElement(By.xpath("//*[@class='panel-collapse in collapse'] //table[@class='table table-top-fixed table-striped table-primary ng-scope']"));
		Marketing mM = new Marketing(driver);
		List<WebElement> wRegiones = mM.traerColumnaElement(wBody, 3, 1);
		driver.findElement(By.xpath("//*[@ng-repeat='prefijo in displayedCollection'] [1] //button")).click();
		driver.findElement(By.xpath("//*[@ng-show='mensajeEliminarCtrl.container.showConfirmation'] //button[@class='btn btn-primary']")).click();
		sleep(3000);
		driver.findElement(By.xpath("//*[@ng-show='mensajeEliminarCtrl.container.showSuccess'] //button[@class='btn btn-primary']")).click();
		driver.navigate().refresh();
		
		pbf.buscarYAbrirRegion(sRegion);
		List<WebElement> wRegionesActualizadas = mM.traerColumnaElement(wBody, 3, 1);
		boolean bAssert= true;
		for (WebElement wAux : wRegionesActualizadas) {
			if (wAux.getText().equalsIgnoreCase(wRegiones.get(0).getText())) {
				bAssert = false;
				break;
			}
		}
		
		Assert.assertTrue(bAssert);
	}
	
	@Test (groups = "BeFAN")
	public void TS97663_BeFan_Movil_REPRO_Localidad_inexistente() {
		irA("regiones", "gesti\u00f3n");
		buscarYClick(driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope")), "contains", "mar del tuy\u00fa");
		driver.findElement(By.cssSelector(".panel-collapse.in.collapse")).findElement(By.cssSelector(".btn.btn-link")).click();
		sleep(3000);
		for (WebElement x : driver.findElements(By.cssSelector(".compatibility.custom-check.ng-scope"))) {
			if (!(x.getText().equalsIgnoreCase("3275")))
				Assert.assertTrue(false);
		}
	}
	
	@Test (groups = "BeFAN")
	public void TS97660_BeFan_Movil_REPRO_Seriales_con_estado_EN_PROCESO() {
		boolean enProceso = false;
		irA("sims", "gesti\u00f3n");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "En Proceso");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "VJP");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(5000);
		List<WebElement> tabla = driver.findElement(By.id("exportarTabla")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		for (int i=0; i<5; i++) {
			if (tabla.iterator().next().findElements(By.tagName("td")).get(6).getText().equalsIgnoreCase("En Proceso"))
				enProceso = true;
			i++;
		}
		Assert.assertTrue(enProceso);
	}
	
	@Test (groups = "BeFAN")
	public void TS97661_BeFan_Movil_REPRO_Seriales_con_estado_VALIDADO() {
		boolean procesado = false;
		irA("sims", "gesti\u00f3n");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "Procesado");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "VJP");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(5000);
		List<WebElement> tabla = driver.findElement(By.id("exportarTabla")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		for (int i=0; i<5; i++) {
			if (tabla.iterator().next().findElements(By.tagName("td")).get(6).getText().equalsIgnoreCase("Procesado"))
				procesado = true;
			i++;
		}
		Assert.assertTrue(procesado);
	}
	
	@Test (groups = "BeFAN")
	public void TS112021_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Busqueda_Eliminacion_de_agrupadores_No() {
		boolean noEliminar = false;
		irA("regiones", "gesti\u00f3n");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		driver.findElement(By.cssSelector(".form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys("Pinamar");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "cerrar");
		buscarYClick(driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope")), "contains", "pinamar");
		driver.findElement(By.cssSelector(".panel-collapse.in.collapse")).findElement(By.cssSelector(".btn.btn-link")).click();
		sleep(3000);
		driver.findElement(By.className("check-filter-on")).click();
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "agregar");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "cerrar");
		sleep(5000);
		for (WebElement x : driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope"))) {
			if (x.getText().toLowerCase().contains("pinamar"))
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + x.getLocation().y + ")");
		}
		buscarYClick(driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope")), "contains", "pinamar");
		for (WebElement x : driver.findElements(By.className("panel-group"))) {
			if (x.getText().contains("3532"))
				x.findElement(By.tagName("tbody")).findElement(By.tagName("button")).click();
		}
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "eliminar");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "cerrar");
		String msj = driver.findElement(By.className("modal-header")).getText();
		if (msj.contains("Tambien desea eliminar la Region Pinamar")) {
			buscarYClick(driver.findElements(By.cssSelector(".btn.btn-link")), "equals", "cancelar");
			noEliminar = true;
		}
		for (WebElement x : driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope"))) {
			if (x.getText().toLowerCase().contains("pinamar"))
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + x.getLocation().y + ")");
		}
		buscarYClick(driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope")), "contains", "pinamar");
		driver.findElement(By.cssSelector(".panel-collapse.in.collapse")).findElement(By.cssSelector(".btn.btn-link")).click();
		sleep(3000);
		driver.findElement(By.className("check-filter-on")).click();
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "agregar");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "cerrar");
		sleep(5000);
		for (WebElement x : driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope"))) {
			if (x.getText().toLowerCase().contains("pinamar"))
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + x.getLocation().y + ")");
		}
		buscarYClick(driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope")), "contains", "pinamar");
		for (WebElement x : driver.findElements(By.className("panel-group"))) {
			if (x.getText().contains("3532"))
				x.findElement(By.tagName("tbody")).findElement(By.tagName("button")).click();
		}
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "eliminar");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "cerrar");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "eliminar");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "cerrar");
		Assert.assertTrue(noEliminar);
	}
	@Test (groups = "BeFan")
	public void TS135605_BeFan_Movil_Repro_Preactivacion_Importacion_de_cupos_Formato_invalido() {
		String text = "debe importar un archivo .txt";
		irA("Cupos", "Importaci\u00f3n");
		sleep(3000);
		driver.findElement(By.id("fileinput")).sendKeys("C:\\Users\\xappiens\\Documents\\Word\\cupos1.docx");
		sleep(3000);
		driver.findElement(By.name("BTN-Importar")).click();
		boolean a = false;
		List <WebElement> formato = driver.findElements(By.className("modal-header"));
		for(WebElement x : formato) {
			if(x.getText().toLowerCase().contains(text)) {
				a = true;
				System.out.println("Se debe seleccionar un archivo con formato .txt");
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = "BeFan")
	public void TS135631_BeFan_Movil_Repro_Preactivacion_Gestion_de_cupos_Busqueda_Formato() throws ParseException{
		BeFan fechas= new BeFan (driver);
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat ("dd/MM/yyyy");
		String desde ="28/09/2018";
		String hasta = "25/12/2018";
		Date fechaDesde = formatoDelTexto.parse(desde);
		Date fechaHasta =formatoDelTexto.parse(hasta);
		irA("Cupos", "Gesti\u00f3n");
		sleep(3000);
		selectByText(driver.findElement(By.name("estados")), "No Vigente");
		driver.findElement(By.id("dataPickerDesde"));
		((JavascriptExecutor) driver).executeScript("document.getElementById('dataPickerDesde').value='"+desde+"'");
		sleep(3000);
		driver.findElement(By.id("dataPickerHasta"));
		((JavascriptExecutor) driver).executeScript("document.getElementById('dataPickerHasta').value='"+hasta+"'");
		int dias = fechas.numeroDiasEntreDosFechas(fechaDesde, fechaHasta);
		System.out.println("Hay " + dias + " dias, " +"No supera los 90 dias comprendidos");
		driver.findElement(By.name("buscar")).click();
		sleep(8000);
		boolean razonS = false , region = false , puntoDeVenta = false, fechaD = false, fechaH = false, estado = false, cantidadTotal = false, disponibles = false, activados = false, reservados = false;
		List <WebElement> colum = driver.findElements(By.className("text-center"));
		for(WebElement x : colum) {
			if(x.getText().contains("Raz\u00f3n Social")) 
				razonS = true;
			if(x.getText().contains("Regi\u00f3n")) 
				region = true;
			if(x.getText().contains("Punto de Venta")) 
				puntoDeVenta = true;
			if(x.getText().contains("Fecha Desde")) 
				fechaD = true;
			if(x.getText().contains("Fecha Hasta"))
				fechaH = true;
			if(x.getText().contains("Estado")) 
				estado = true;
			if(x.getText().contains("Cantidad Total"))
				cantidadTotal= true;
			if(x.getText().contains("Disponibles"))
				disponibles = true;
			if(x.getText().contains("Activados"))
				activados = true;
			if(x.getText().contains("Reservados"))
				reservados = true;
		}
		Assert.assertTrue(razonS && region && puntoDeVenta && fechaD && fechaH && estado && cantidadTotal && disponibles && activados && reservados);
	}
	
	@Test (groups = "BeFan")
	public void TS135632_BeFan_Movil_Repro_Preactivacion_Gestion_de_cupos_Busqueda_Exportacion() throws ParseException{
		BeFan fechas= new BeFan (driver);
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat ("dd/MM/yyyy");
		String desde ="27/09/2018";
		String hasta = "25/12/2018";
		Date fechaDesde = formatoDelTexto.parse(desde);
		Date fechaHasta =formatoDelTexto.parse(hasta);
		irA("Cupos", "Gesti\u00f3n");
		sleep(3000);
		selectByText(driver.findElement(By.name("estados")), "No Vigente");
		driver.findElement(By.id("dataPickerDesde"));
		((JavascriptExecutor) driver).executeScript("document.getElementById('dataPickerDesde').value='"+desde+"'");
		sleep(3000);
		driver.findElement(By.id("dataPickerHasta"));
		((JavascriptExecutor) driver).executeScript("document.getElementById('dataPickerHasta').value='"+hasta+"'");
		int dias = fechas.numeroDiasEntreDosFechas(fechaDesde, fechaHasta);
		System.out.println("Hay " + dias + " dias, " +"No supera los 90 dias comprendidos");
		driver.findElement(By.name("buscar")).click();
		sleep(8000);
		WebElement boton = driver.findElement(By.name("exportar"));
		boton.click();
		Assert.assertTrue(boton.isDisplayed());
	}
	
	@Test (groups = "BeFan")
	public void TS126634_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Busqueda_Eliminacion_de_agrupadores_Si_Preactivando() throws InterruptedException {
		irA("Regiones", "Gesti\u00f3n");
		sleep(3000);
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(3000);
		String Region="CORDOBA";
		driver.findElement(By.cssSelector(".form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys(Region);
		driver.findElement(By.xpath("//*[@class='btn btn-primary' and contains(text(), 'Agregar')]")).click();
//		String region= "";
//		if(region=driver.findElement(By.cssSelector(".alert.alert-dismissable.alert-danger")) {
//			System.out.println(driver.findElement(By.cssSelector(".alert.alert-dismissable.alert-danger")).getText());
//			driver.findElement(By.className("pull-right")).findElement(By.cssSelector(".btn.btn-link")).getText().equalsIgnoreCase("Cancelar");
//			driver.findElement(By.className("pull-right")).findElement(By.cssSelector(".btn.btn-link")).click();
//		//pbf.region();
//		}
//		else {
		sleep(5000);
		driver.findElement(By.xpath("//*[@class='btn btn-primary' and contains(text(), 'Agregar')]")).click();
		sleep(5000);
		pbf = new Pages.BeFan(driver);
		Assert.assertTrue(pbf.verificarMensajeExitoso());
		//Ask about confirmation message
		driver.findElement(By.xpath("//*[@ng-show='mensajeAgregarRegionCtrl.container.showSuccess']//*[@class='btn btn-primary']")).click();
		pbf.buscarR("cordoba");
		sleep(3000);
		driver.findElement(By.className("col-lg-2")).findElement(By.cssSelector(".btn.btn-link")).click();
		driver.findElement(By.className("modal-content")).findElement(By.className("modal-body")).findElement(By.cssSelector(".col-lg-4.dropdown-filters"));
		driver.findElement(By.id("compatibility")).findElement(By.tagName("input")).click();
		driver.findElement(By.xpath("//*[@class='btn btn-primary' and contains(text(), 'CERRAR')]")).click();
		sleep(5000);
		pbf = new Pages.BeFan(driver);
		Assert.assertTrue(pbf.verificarMensajeExitoso());
		driver.findElement(By.cssSelector(".actions.text-center")).findElement(By.cssSelector(".btn.btn-link")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector(".text-center.ng-binding")).getText().contains("�Esta seguro que desea eliminarlo ?"));
		pbf.eliminar();
		//driver.findElement(By.xpath("//*[@class='btn btn-primary' and contains(text(), 'ELIMINAR')]")).click();
		Assert.assertTrue(pbf.verificarMensajeExitoso());
		//driver.findElement(By.xpath("//*[@class='btn btn-primary' and contains(text(), 'CERRAR')]")).click();
		pbf.cerrar();
		driver.findElement(By.cssSelector(".text-center.ng-binding")).getText().contains("�Tambien desea eliminar la Region CORDOBA ?");
		//driver.findElement(By.xpath("//*[@class='btn btn-primary' and contains(text(), 'ELIMINAR')]")).click();
		pbf.eliminar();
		Assert.assertTrue(pbf.verificarMensajeExitoso());
		//driver.findElement(By.xpath("//*[@class='btn btn-primary' and contains(text(), 'CERRAR')]")).click();
		pbf.cerrar();
	}
	

	
	@Test (groups = "BeFan")
	public void TS112020_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Busqueda_Eliminacion_de_agrupadores_Si_Sin_preactivar() {
		boolean eliminar = false;
		irA("regiones", "gesti\u00f3n");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		driver.findElement(By.cssSelector(".form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys("Pinamar");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "cerrar");
		buscarYClick(driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope")), "contains", "pinamar");
		driver.findElement(By.cssSelector(".panel-collapse.in.collapse")).findElement(By.cssSelector(".btn.btn-link")).click();
		sleep(3000);
		driver.findElement(By.className("check-filter-on")).click();
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "agregar");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "cerrar");
		sleep(5000);
		for (WebElement x : driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope"))) {
			if (x.getText().toLowerCase().contains("pinamar"))
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + x.getLocation().y + ")");
		}
		buscarYClick(driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope")), "contains", "pinamar");
		for (WebElement x : driver.findElements(By.className("panel-group"))) {
			if (x.getText().contains("3532"))
				x.findElement(By.tagName("tbody")).findElement(By.tagName("button")).click();
		}
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "eliminar");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "cerrar");
		String msj = driver.findElement(By.className("modal-header")).getText();
		if (msj.contains("Tambien desea eliminar la Region Pinamar")) {
			buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "eliminar");
			buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "cerrar");
			eliminar = true;
		}
		Assert.assertTrue(eliminar);
	}
	
	@Test (groups = "BeFan")
	public void TS112019_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Busqueda_Eliminacion_de_agrupadores_Mensaje() {
		boolean mensaje = false;
		irA("regiones", "gesti\u00f3n");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		driver.findElement(By.cssSelector(".form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys("Tierra Del Fuego");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "cerrar");
		buscarYClick(driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope")), "contains", "tierra del fuego");
		driver.findElement(By.cssSelector(".panel-collapse.in.collapse")).findElement(By.cssSelector(".btn.btn-link")).click();
		sleep(3000);
		driver.findElement(By.className("check-filter-on")).click();
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "agregar");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "cerrar");
		sleep(5000);
		for (WebElement x : driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope"))) {
			if (x.getText().toLowerCase().contains("tierra del fuego"))
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + x.getLocation().y + ")");
		}
		buscarYClick(driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope")), "contains", "tierra del fuego");
		for (WebElement x : driver.findElements(By.className("panel-group"))) {
			if (x.getText().contains("3532"))
				x.findElement(By.tagName("tbody")).findElement(By.tagName("button")).click();
		}
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "eliminar");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "cerrar");
		String msj = driver.findElement(By.className("modal-header")).getText();
		if (msj.contains("Tambien desea eliminar la Region Tierra Del Fuego")) {
			buscarYClick(driver.findElements(By.cssSelector(".btn.btn-link")), "equals", "cancelar");
			mensaje = true;
		}
		for (WebElement x : driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope"))) {
			if (x.getText().toLowerCase().contains("tierra del fuego"))
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + x.getLocation().y + ")");
		}
		buscarYClick(driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope")), "contains", "tierra del fuego");
		driver.findElement(By.cssSelector(".panel-collapse.in.collapse")).findElement(By.cssSelector(".btn.btn-link")).click();
		sleep(3000);
		driver.findElement(By.className("check-filter-on")).click();
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "agregar");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "cerrar");
		sleep(5000);
		for (WebElement x : driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope"))) {
			if (x.getText().toLowerCase().contains("tierra del fuego"))
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + x.getLocation().y + ")");
		}
		buscarYClick(driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope")), "contains", "tierra del fuego");
		for (WebElement x : driver.findElements(By.className("panel-group"))) {
			if (x.getText().contains("3532"))
				x.findElement(By.tagName("tbody")).findElement(By.tagName("button")).click();
		}
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "eliminar");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "cerrar");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "eliminar");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "cerrar");
		Assert.assertTrue(mensaje);
	}
	
	@Test (groups = "BeFan")
	public void TS112016_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Busqueda_Modificacion_de_agrupadores_Eliminacion_de_prefijos_en_agrupador_existente_Logeo() {
		irA("regiones", "gesti\u00f3n");
		boolean eliminarPrefijo = false;
		buscarYClick(driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope")), "contains", "la plata");
		for (WebElement x : driver.findElements(By.className("panel-group"))) {
			try {
				if (x.getText().toLowerCase().contains("la plata"))
					x.findElement(By.cssSelector(".glyphicon.glyphicon-plus")).click();
			} catch(Exception e) {}
		}
		sleep(3000);
		driver.findElement(By.cssSelector(".compatibility.custom-check.ng-scope")).findElement(By.tagName("input")).click();
		String nroPrefijo = driver.findElement(By.cssSelector(".compatibility.custom-check.ng-scope")).findElement(By.tagName("label")).getText();
		System.out.println(nroPrefijo);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "agregar");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "cerrar");
		driver.navigate().refresh();
		sleep(3000);
		buscarYClick(driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope")), "contains", "la plata");
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + driver.findElement(By.cssSelector(".panel.ng-scope.ng-isolate-scope.panel-default.panel-open")).getLocation().y + ")");
		for (WebElement x : driver.findElement(By.cssSelector(".panel.ng-scope.ng-isolate-scope.panel-default.panel-open")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"))) {
			if (x.getText().contains(nroPrefijo)) {
				x.findElement(By.cssSelector(".btn.btn-link")).click();
				buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "eliminar");
				buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "cerrar");
				eliminarPrefijo = true;
			}
		}
		Assert.assertTrue(eliminarPrefijo);
	}
	
	
	@Test (groups = "BeFan")
	public void TS135633_BeFan_Movil_Repro_Preactivacion_Gestion_de_cupos_Busqueda_Eliminacion_de_cupo_Cupo_no_vigente_sin_fecha_de_baja() throws ParseException {
		BeFan fechas= new BeFan (driver);
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat ("dd/MM/yyyy");
		String desde ="27/09/2018";
		String hasta = "25/12/2018";
		Date fechaDesde = formatoDelTexto.parse(desde);
		Date fechaHasta =formatoDelTexto.parse(hasta);
		irA("Cupos", "Gesti\u00f3n");
		sleep(3000);
		selectByText(driver.findElement(By.name("estados")), "No Vigente");
		driver.findElement(By.id("dataPickerDesde"));
		((JavascriptExecutor) driver).executeScript("document.getElementById('dataPickerDesde').value='"+desde+"'");
		sleep(3000);
		driver.findElement(By.id("dataPickerHasta"));
		((JavascriptExecutor) driver).executeScript("document.getElementById('dataPickerHasta').value='"+hasta+"'");
		int dias = fechas.numeroDiasEntreDosFechas(fechaDesde, fechaHasta);
		System.out.println("Hay " + dias + " dias, " +"No supera los 90 dias comprendidos");
		driver.findElement(By.name("buscar")).click();
		sleep(3000);
		WebElement eliminacion = driver.findElement(By.name("eliminar"));
		Assert.assertTrue(eliminacion.isEnabled());
		System.out.println("No se puede eliminar un cupo no vigente");
	}
	
	@Test (groups = "BeFan")
	public void TS135634_BeFan_Movil_Repro_Preactivacion_Gestion_de_cupos_Busqueda_Eliminacion_de_cupo_Cupo_vigente_con_fecha_de_baja() throws ParseException {
		BeFan fechas= new BeFan (driver);
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat ("dd/MM/yyyy");
		String desde ="27/09/2018";
		String hasta = "25/12/2018";
		Date fechaDesde = formatoDelTexto.parse(desde);
		Date fechaHasta =formatoDelTexto.parse(hasta);
		irA("Cupos", "Gesti\u00f3n");
		sleep(3000);
		selectByText(driver.findElement(By.name("estados")), "Vigente");
		driver.findElement(By.id("dataPickerDesde"));
		((JavascriptExecutor) driver).executeScript("document.getElementById('dataPickerDesde').value='"+desde+"'");
		sleep(3000);
		driver.findElement(By.id("dataPickerHasta"));
		((JavascriptExecutor) driver).executeScript("document.getElementById('dataPickerHasta').value='"+hasta+"'");
		int dias = fechas.numeroDiasEntreDosFechas(fechaDesde, fechaHasta);
		System.out.println("Hay " + dias + " dias, " +"No supera los 90 dias comprendidos");
		driver.findElement(By.name("buscar")).click();
		//Se necesita un cupo con fecha de bajar
	}
	
	@Test (groups = "BeFan")
	public void TS135636_BeFan_Movil_Repro_Preactivacion_Gestion_de_cupos_Busqueda_Eliminacion_de_cupo_Mensaje_Confirmacion() throws ParseException{
		BeFan fechas= new BeFan (driver);
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat ("dd/MM/yyyy");
		String pregunta = "�est\u00e1 seguro que desea dar de baja el registro seleccionado?";
		String desde ="27/09/2018";
		String hasta = "25/12/2018";
		Date fechaDesde = formatoDelTexto.parse(desde);
		Date fechaHasta =formatoDelTexto.parse(hasta);
		irA("Cupos", "Gesti\u00f3n");
		sleep(3000);
		selectByText(driver.findElement(By.name("estados")), "Vigente");
		driver.findElement(By.id("dataPickerDesde"));
		((JavascriptExecutor) driver).executeScript("document.getElementById('dataPickerDesde').value='"+desde+"'");
		sleep(3000);
		driver.findElement(By.id("dataPickerHasta"));
		((JavascriptExecutor) driver).executeScript("document.getElementById('dataPickerHasta').value='"+hasta+"'");
		int dias = fechas.numeroDiasEntreDosFechas(fechaDesde, fechaHasta);
		System.out.println("Hay " + dias + " dias, " +"No supera los 90 dias comprendidos");
		driver.findElement(By.name("buscar")).click();
		sleep(3000);
		WebElement eliminacion = driver.findElement(By.name("eliminar"));
		Assert.assertTrue(eliminacion.isDisplayed());
		eliminacion.click();
		boolean a = false;
		for(WebElement x : driver.findElements(By.className("modal-header"))) {
			if(x.getText().toLowerCase().contains(pregunta)) {
				a = true;
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = "BeFan")
	public void TS135638_BeFan_Movil_Repro_Preactivacion_Gestion_de_cupos_Busqueda_Modificacion_de_cupo_Cupo_no_vigente_sin_fecha_de_baja() throws ParseException {
		BeFan fechas= new BeFan (driver);
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat ("dd/MM/yyyy");
		String desde ="27/09/2018";
		String hasta = "25/12/2018";
		Date fechaDesde = formatoDelTexto.parse(desde);
		Date fechaHasta =formatoDelTexto.parse(hasta);
		irA("Cupos", "Gesti\u00f3n");
		sleep(3000);
		selectByText(driver.findElement(By.name("estados")), "No Vigente");
		driver.findElement(By.id("dataPickerDesde"));
		((JavascriptExecutor) driver).executeScript("document.getElementById('dataPickerDesde').value='"+desde+"'");
		sleep(3000);
		driver.findElement(By.id("dataPickerHasta"));
		((JavascriptExecutor) driver).executeScript("document.getElementById('dataPickerHasta').value='"+hasta+"'");
		int dias = fechas.numeroDiasEntreDosFechas(fechaDesde, fechaHasta);
		System.out.println("Hay " + dias + " dias, " +"No supera los 90 dias comprendidos");
		driver.findElement(By.name("buscar")).click();
		sleep(3000);
		WebElement modificacion = driver.findElement(By.name("modificarGuardar"));
		Assert.assertTrue(modificacion.isEnabled());
		System.out.println("No se puede modificar un cupo no vigente");
	}
	
	@Test (groups = "BeFan")
	public void TS112006_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Pantalla_de_inicio() {
		irA("Regiones", "Gesti\u00f3n");
		WebElement panelDeBusqueda = driver.findElement(By.className("panel-heading"));
		WebElement boton = driver.findElement(By.cssSelector(".btn.btn-primary"));
		Assert.assertTrue(panelDeBusqueda.isDisplayed() && boton.isDisplayed());
		WebElement panel = driver.findElement(By.className("panel-data"));
		Assert.assertTrue(panel.isDisplayed());
		if(panel.getText().toLowerCase().contains("fort\u00edn olavarr\u00eda") || panel.getText().toLowerCase().contains("la plata")) {
			driver.findElements(By.className("panel-heading")).get(0).click();
		}
		WebElement tabla = driver.findElement(By.cssSelector(".table.table-top-fixed.table-striped.table-primary.ng-scope"));
		Assert.assertTrue(tabla.getText().toLowerCase().contains("descripci\u00f3n") && tabla.getText().toLowerCase().contains("codigo prefijo") && tabla.getText().toLowerCase().contains("acci\u00f3n"));
	}
	
	@Test (groups = "BeFan")
	public void TS112007_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Creacion_de_agrupador_exitosa() {
		irA("Regiones", "Gesti\u00f3n");
		boolean eliminarPrefijo = false;
		sleep(2000);
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(2000);
		driver.findElement(By.cssSelector(".form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys("Moreno");
		driver.findElement(By.xpath("//*[@class='btn btn-primary' and contains(text(), 'Agregar')]")).click();
		sleep(2000);
		driver.findElement(By.xpath("//*[@class='btn btn-primary' and contains(text(), 'Cerrar')]")).click();
		sleep(2000);
		WebElement panel = driver.findElement(By.className("panel-data"));
		Assert.assertTrue(panel.getText().toLowerCase().contains("moreno"));
		//Se borra el agrupador para volver a correr el caso
		buscarYClick(driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope")), "contains", "moreno");
		for (WebElement x : driver.findElements(By.className("panel-group"))) {
			try {
				if (x.getText().toLowerCase().contains("moreno"))
					x.findElement(By.cssSelector(".glyphicon.glyphicon-plus")).click();
			} catch(Exception e) {}
		}
		sleep(3000);
		driver.findElement(By.cssSelector(".compatibility.custom-check.ng-scope")).findElement(By.tagName("input")).click();
		String nroPrefijo = driver.findElement(By.cssSelector(".compatibility.custom-check.ng-scope")).findElement(By.tagName("label")).getText();
		System.out.println(nroPrefijo);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "agregar");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "cerrar");
		driver.navigate().refresh();
		sleep(3000);
		buscarYClick(driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope")), "contains", "moreno");
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + driver.findElement(By.cssSelector(".panel.ng-scope.ng-isolate-scope.panel-default.panel-open")).getLocation().y + ")");
		for (WebElement x : driver.findElement(By.cssSelector(".panel.ng-scope.ng-isolate-scope.panel-default.panel-open")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"))) {
			if (x.getText().contains(nroPrefijo)) {
				x.findElement(By.cssSelector(".btn.btn-link")).click();
				buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "eliminar");
				buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "cerrar");
				eliminarPrefijo = true;
			}
		}
		Assert.assertTrue(eliminarPrefijo);
		boolean eliminarRegion = false;
		String mensaje = driver.findElement(By.className("modal-header")).getText();
		if (mensaje.contains("Tambien desea eliminar la Region Moreno")) {
			buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "eliminar");
			buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "cerrar");
			eliminarRegion = true;
		}
		Assert.assertTrue(eliminarRegion);
	}
	
	@Test (groups = "BeFan")
	public void TS112011_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Busqueda_Modificacion_de_agrupadores_Asignacion_de_prefijos_a_agrupador_existente_Guardando(){
		irA("Regiones", "Gesti\u00f3n");
		boolean prefijo = false;
		buscarYClick(driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope")), "contains", "cordoba");
		for (WebElement x : driver.findElements(By.className("panel-group"))) {
			try {
				if (x.getText().toLowerCase().contains("cordoba"))
					x.findElement(By.cssSelector(".glyphicon.glyphicon-plus")).click();
			} catch(Exception e) {}
		}
		sleep(3000);
		driver.findElement(By.cssSelector(".compatibility.custom-check.ng-scope")).findElement(By.tagName("input")).click();
		String nroPrefijo = driver.findElement(By.cssSelector(".compatibility.custom-check.ng-scope")).findElement(By.tagName("label")).getText();
		System.out.println(nroPrefijo);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "agregar");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "cerrar");
		driver.navigate().refresh();
		sleep(3000);
		buscarYClick(driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope")), "contains", "cordoba");
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + driver.findElement(By.cssSelector(".panel.ng-scope.ng-isolate-scope.panel-default.panel-open")).getLocation().y + ")");
		for (WebElement x : driver.findElement(By.cssSelector(".panel.ng-scope.ng-isolate-scope.panel-default.panel-open")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"))) {
			if (x.getText().contains(nroPrefijo)) {
				prefijo = true;
			}
		}
		Assert.assertTrue(prefijo);
		
	}
	
	@Test (groups = "BeFan")
	public void TS112014_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Busqueda_Modificacion_de_agrupadores_Eliminacion_de_prefijos_en_agrupador_existente_No_guardando() {
		irA("regiones", "gesti\u00f3n");
		boolean noEliminar = false;
		buscarYClick(driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope")), "contains", "bas-vjp-bahia blanca");
		driver.findElement(By.cssSelector(".panel-collapse.in.collapse")).findElement(By.cssSelector(".btn.btn-link")).click();
		String msj = driver.findElement(By.className("modal-header")).getText();
		if (msj.contains("Esta seguro que desea eliminarlo")) {
			if (driver.findElement(By.className("modal-footer")).findElement(By.cssSelector(".btn.btn-link")).getText().equals("Cancelar")) {
				driver.findElement(By.className("modal-footer")).findElement(By.cssSelector(".btn.btn-link")).click();
				noEliminar = true;
			}
		}
		Assert.assertTrue(noEliminar);
	}
	
	@Test (groups = "BeFan")
	public void TS112013_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Busqueda_Modificacion_de_agrupadores_Eliminacion_de_prefijos_en_agrupador_existente_Guardando() {
		irA("regiones", "gesti\u00f3n");
		boolean eliminar = false;
		buscarYClick(driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope")), "contains", "la plata");
		for (WebElement x : driver.findElements(By.className("panel-group"))) {
			try {
				if (x.getText().contains("3583")) {
					x.findElement(By.tagName("tbody")).findElement(By.tagName("button")).click();
					buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "eliminar");
					buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "cerrar");
					eliminar = true;
				}
			} catch(Exception e) {}
		}
		for (WebElement x : driver.findElements(By.className("panel-group"))) {
			try {
				if (x.getText().toLowerCase().contains("la plata"))
					x.findElement(By.cssSelector(".glyphicon.glyphicon-plus")).click();
				sleep(5000);
				for (WebElement a : driver.findElements(By.id("compatibility"))) {
					if (a.getText().contains("3583")) {
						a.findElement(By.tagName("input")).click();
						buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "agregar");
						buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")), "equals", "cerrar");
					}
				}
			} catch(Exception e) {}
		}
		Assert.assertTrue(eliminar);
	}
	
	@Test (groups = "BeFan")
	public void TS112012_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Busqueda_Modificacion_de_agrupadores_Asignacion_de_prefijos_a_agrupador_existente_No_guardando() {
		irA("regiones", "gesti\u00f3n");
		boolean btnCancelar = false;
		buscarYClick(driver.findElements(By.cssSelector(".panel-group.panel-group-alternative.ng-scope")), "contains", "la plata");
		for (WebElement x : driver.findElements(By.className("panel-group"))) {
			try {
				if (x.getText().toLowerCase().contains("la plata"))
					x.findElement(By.cssSelector(".glyphicon.glyphicon-plus")).click();
			} catch(Exception e) {}
		}
		sleep(3000);
		driver.findElement(By.id("compatibility")).findElement(By.tagName("input")).click();
		if (driver.findElement(By.cssSelector(".ng-scope.block-ui.block-ui-anim-fade")).findElement(By.cssSelector(".btn.btn-link")).getText().equals("Cancelar"))
			btnCancelar = true;
		Assert.assertTrue(btnCancelar);
	}
}