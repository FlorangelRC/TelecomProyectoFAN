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

import Pages.setConexion;

public class BeFANConfigurador extends TestBase {
	
	private WebDriver driver;
	
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
	
	@Test (groups = "BeFAN")
	public void TS126620_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Busqueda_Busqueda_especifica() {
		irA("Regiones", "Gesti\u00f3n");
		sleep(3000);
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys("Olavarr");
		sleep(3000);
		WebElement wBody = driver.findElement(By.className("panel-data"));
		List<WebElement> wList = wBody.findElements(By.xpath("//*[@class='panel-group'] //*[@class='collapsed'] //*[@class='ng-binding']"));
		
		boolean bAssert = true;
		for (WebElement wAux : wList) {
			if (!wAux.getText().toLowerCase().contains("Olavarr".toLowerCase())) {
				bAssert = false;
				break;
			}
		}
		
		Assert.assertTrue(bAssert);
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
		if (driver.findElement(By.cssSelector(".text-center.ng-binding")).getText().contains("¿Esta seguro que desea eliminarlo ?"))
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
		Assert.assertTrue(driver.findElements(By.cssSelector(".text-center.ng-binding")).get(1).getText().equalsIgnoreCase("¿Est\u00e1 seguro que desea modificar el registro seleccionado?"));
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
}