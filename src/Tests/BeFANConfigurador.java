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

import Pages.BeFan;
import Pages.ContactSearch;
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
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys("Bahia");
		
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
}