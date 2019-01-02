package Tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
		Assert.assertTrue(driver.findElements(By.cssSelector(".text-center.ng-binding")).get(1).getText().equalsIgnoreCase("�Est\u00e1 seguro que desea modificar el registro seleccionado?"));
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
	public void TS126623_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Busqueda_Modificacion_de_agrupadores_Asignaci�n_de_prefijos_a_agrupador_existente_Guardando(String sRegion) {
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
	
}