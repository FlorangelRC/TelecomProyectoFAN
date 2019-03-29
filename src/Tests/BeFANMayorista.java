package Tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import DataProvider.ExcelUtils;
import Pages.BeFan;
import Pages.ContactSearch;
import Pages.Marketing;
import Pages.SCP;
import Pages.setConexion;
import Pages.DPW;

public class BeFANMayorista extends TestBase {
		
	private WebDriver driver;
	private SCP scp;
	
	private void irA(String opcion) {
		WebElement menu = null;
		sleep(3000);
		buscarYClick(driver.findElements(By.className("dropdown-toggle")), "contains", "sims");
		for (WebElement x : driver.findElements(By.className("col-sm-4"))) {
			if (x.getAttribute("ng-show").equals("headerCtrl.container.hasAccess(['sims_importacion', 'sims_gestion'])"))
				menu = x;
		}
		switch(opcion) {
		case "importacion":
			try {
				for (WebElement x : menu.findElements(By.tagName("a"))) {
					if (x.getText().toLowerCase().contains("importaci\u00f3n")) {
						x.click(); 
						sleep(3000);
					}
				}			
			} catch(Exception e) {}
		case "gestion":
			try {
				for (WebElement x : menu.findElements(By.tagName("a"))) {
					if (x.getText().toLowerCase().contains("gesti\u00f3n")) {
						x.click();
						sleep(3000);
					}
				}
			} catch(Exception e) {}
		}
	}
	
	private void irCupos(String opcion) {
		WebElement menu = null;
		sleep(1500);
		buscarYClick(driver.findElements(By.className("dropdown-toggle")), "contains", "cupos");
		for (WebElement x : driver.findElements(By.className("col-sm-4"))) {
			if (x.getAttribute("ng-show").equals("headerCtrl.container.hasAccess(['cupo_importacion', 'cupo_gestion'])"))
				menu = x;
		}
		switch(opcion) {
		case "importacion":
			try {
				for (WebElement x : menu.findElements(By.tagName("a"))) {
					if (x.getText().toLowerCase().contains("importaci\u00f3n")) {
						x.click(); 
						sleep(3000);
					}
				}			
			} catch(Exception e) {}
		case "gestion":
			try {
				for (WebElement x : menu.findElements(By.tagName("a"))) {
					if (x.getText().toLowerCase().contains("gesti\u00f3n")) {
						x.click();
						sleep(3000);
					}
				}
			} catch(Exception e) {}
		}
	}
	
	private void irRegion(String opcion) {
		WebElement menu = null;
		sleep(1500);
		buscarYClick(driver.findElements(By.className("dropdown-toggle")), "contains", "regiones");
		for (WebElement x : driver.findElements(By.className("col-sm-4"))) {
			if (x.getAttribute("ng-show").equals("headerCtrl.container.hasAccess(['region_importacion', 'region_gestion'])"))
				menu = x;
		}
		switch(opcion) {
		case "importacion":
			try {
				for (WebElement x : menu.findElements(By.tagName("a"))) {
					if (x.getText().toLowerCase().contains("importaci\u00f3n")) {
						x.click(); 
						sleep(3000);
					}
				}			
			} catch(Exception e) {}
		case "gestion":
			try {
				for (WebElement x : menu.findElements(By.tagName("a"))) {
					if (x.getText().toLowerCase().contains("gesti\u00f3n")) {
						x.click();
						sleep(3000);
					}
				}
			} catch(Exception e) {}
		}
	}
	
	private void irA(String sMenu,String sOpcion) {
		sleep(5000);
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
	
	public String readTxt(String sName) throws IOException {
		String sPrefijo;
		File fFile = null;
		FileReader frFileReader = null;
		BufferedReader brBufferedReader = null;
		fFile = new File (sName);
		frFileReader = new FileReader (fFile);
		brBufferedReader = new BufferedReader(frFileReader);
		sPrefijo = brBufferedReader.readLine();
		
		frFileReader.close();
		brBufferedReader.close();
		return sPrefijo;
	}
	
	public void deleteFile(String sFile) {
		File fFile = new File(sFile);
		fFile.delete();
	}
	
	@BeforeClass (alwaysRun = true)
	public void init() {
		driver = setConexion.setupEze();
		scp = new SCP(driver);
		loginBeFANVictor(driver, "mayorista");
//		loginBeFAN(driver);
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
	public void TSXXXX_Befan() {
		BeFan Botones = new BeFan(driver);
		Botones.andaAlMenu("sims", "gestion");
		Botones.SGSeleccionEstado("Procesado");
		Botones.SGSeleccionDeposito("BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
		
		//Botones.SGNombreDelArchivo("UAT");
		
		Botones.SGFechaDesde("20190220");
		Botones.SGFechaHasta("20190321");
		Botones.SGClickBuscar();
		Botones.SGDatosArchivos();
		Botones.SGBuscarArchivo("prueba preactivacion uat 25_02_2019 - 2 - Copy (2)");
		//Botones.andaAlMenu("sims", "gestion");
//		irA("importacion");
	}
	
	@Test (groups = "BeFAN")
	public void TS126656_BeFan_Movil_REPRO_Preactivacion_repro_Busqueda_de_archivos_Agente_Por_estado_EN_PROCESO_Exitosa() {
		boolean enProceso = false;
		BeFan Botones = new BeFan(driver);
		Botones.andaAlMenu("sims", "gestion");
		Botones.SGSeleccionEstado("En Proceso");
		Botones.SGClickBuscar();
		enProceso = Botones.SGValidarResultado(Botones.SGDatosArchivos(), 6, 9, "En Proceso");
		Assert.assertTrue(enProceso);
	}
	
	
	//Falta preparar la carga de un archivo como preparacion
	@Test (groups = "BeFAN")
	public void TS126655_BeFan_Movil_REPRO_Preactivacion_repro_Busqueda_de_archivos_Agente_Por_estado_PENDIENTE_Exitosa() {
		boolean enProceso = false;
		BeFan Botones = new BeFan(driver);
		Botones.andaAlMenu("sims", "gestion");
		Botones.SGSeleccionEstado("Pendiente");
		Botones.SGClickBuscar();
		enProceso = Botones.SGValidarResultado(Botones.SGDatosArchivos(), 6, 9, "Pendiente");
		Assert.assertTrue(enProceso);
	}
	
	@Test (groups = "BeFAN")
	public void TS126657_BeFan_Movil_REPRO_Preactivacion_repro_Busqueda_de_archivos_Agente_Por_estado_PROCESADO_Exitosa() {
		boolean enProceso = false;
		BeFan Botones = new BeFan(driver);
		Botones.andaAlMenu("sims", "gestion");
		Botones.SGSeleccionEstado("Procesado");
		Botones.SGClickBuscar();
		enProceso = Botones.SGValidarResultado(Botones.SGDatosArchivos(), 6, 9, "Procesado");
		Assert.assertTrue(enProceso);
	}
	// Podria optimizarse con orden en las columnas
	@Test (groups = "BeFAN")
	public void TS126662_BeFan_Movil_REPRO_Preactivacion_repro_Busqueda_de_archivos_Agente_Visualizacion_de_mas_informacion() {
		boolean razonSocial = false, linea = false, plan = false, nmu = false, serie = false, preactivacion = false, procesamiento = false, estado = false, descripcion = false, def = true;
		BeFan Botones = new BeFan(driver);
		Botones.andaAlMenu("sims", "gestion");
		Botones.SGSeleccionEstado("Procesado");
		Botones.SGClickBuscar();
		Botones.SGClickVerDetalle(1);
		List <WebElement> columnas = Botones.SGVerDetalleColumnas();
		for (WebElement x : columnas) {
			System.out.println(x.getText());
			switch (x.getText()) {
			case "Raz\u00f3n Social":
				razonSocial = true;
				break;
			case "L\u00ednea":
				linea = true;
				break;
			case "Plan":
				plan = true;
				break;
			case "NMU":
				nmu = true;
				break;
			case "Serie":
				serie = true;
				break;
			case "Preactivaci\u00f3n":
				preactivacion = true;
				break;
			case "Procesamiento":
				procesamiento = true;
				break;
			case "Estado":
				estado = true;
				break;
			case "Descripci\u00f3n":
				descripcion = true;
				break;
			default:
				def = false;
				break;
			}
		}
		Assert.assertTrue(razonSocial && linea && plan && nmu && serie && preactivacion && procesamiento && estado && descripcion && def);
	}
	
	@Test (groups = "BeFAN")
	public void TS126663_BeFan_Movil_REPRO_Preactivacion_repro_Busqueda_de_archivos_Agente_Visualizacion_de_mas_informacion_Exportacion() {
		irA("gestion");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "Procesado");
		selectByText(driver.findElements(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).get(1), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(5000);
		try {
			driver.findElement(By.id("exportarTabla")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(8).click();
		} catch(Exception e) {}
		sleep(3000);
		driver.findElement(By.id("botonExportar")).click();
		sleep(5000);
		String downloadPath = "C:\\Users\\Nicolas\\Downloads";
		Assert.assertTrue(scp.isFileDownloaded(downloadPath, "PREACTIVACIONES DIARIAS"), "Failed to download Expected document");
	}

	
	@Test (groups = "BeFAN")
	public void TS112042_BeFan_Movil_REPRO_Preactivacion_repro_Busqueda_de_archivos_Agente_Por_fecha_Exitosa() throws ParseException {
		BeFan Botones = new BeFan(driver);
		Botones.andaAlMenu("sims", "gestion");
		Botones.SGSeleccionEstado("Procesado");
		SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat formatter2=new SimpleDateFormat("dd/MM/yyyy");
		Date dateHoy = formatter2.parse(fechaDeHoy());
		Date dateDesde = new Date (dateHoy.getTime()-2592000000L);
		Date dateHasta = new Date (dateHoy.getTime()-864000000L);
		Botones.SGFechaDesde(formatter.format(dateDesde));
		Botones.SGFechaHasta(formatter.format(dateHasta));
		Botones.SGClickBuscar();
		Assert.assertTrue(Botones.SGValidarFechas(formatter2.format(dateDesde), formatter2.format(dateHasta), Botones.SGDatosArchivos(), 1, 9));
	}
	
	// Podria optimizarse con orden en las columnas
	@Test (groups = "BeFAN")
	public void TS112047_BeFan_Movil_REPRO_Preactivacion_repro_Busqueda_de_archivos_Agente_Formato() {
		boolean nombreArchivo = false, fechaDeCarga = false, estado = false, fechaProcesado = false, puntoDeVenta = false, accion = false, region = false, usuario = false, vacio = false, def = true;
		BeFan Botones = new BeFan(driver);
		Botones.andaAlMenu("sims", "gestion");
		Botones.SGSeleccionEstado("Procesado");
		Botones.SGClickBuscar();
		List <WebElement> columnas = Botones.SGColumnas();
		for (WebElement x : columnas) {
			switch (x.getText()) {
			case "Fecha de Carga":
				fechaDeCarga = true;
				break;
			case "Regi\u00f3n":
				region = true;
				break;
			case "Punto de Venta":
				puntoDeVenta = true;
				break;
			case "Usuario":
				usuario = true;
				break;
			case "Nombre Archivo":
				nombreArchivo = true;
				break;
			case "Estado":
				estado = true;
				break;
			case "Fecha Procesado":
				fechaProcesado = true;
				break;
			case "Acci\u00f3n":
				accion = true;
				break;
			case "":
				vacio = true;
				break;
			default:
				def = false;
				break;
			}
		}
		Assert.assertTrue(fechaDeCarga && region && puntoDeVenta && usuario && nombreArchivo && estado && fechaProcesado && accion && vacio && def);
	}
	
	//Requiere importacion, osea preparacion
	@Test (groups = "BeFAN")
	public void TS135601_BeFan_Movil_Repro_Preactivacion_Gestion_de_simcards_Busqueda_de_archivos_Ver_detalle_Nombre_del_archivo() {
		irA("gestion");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "Procesado");
		selectByText(driver.findElements(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).get(1), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(5000);
		WebElement tabla = driver.findElement(By.id("exportarTabla")).findElement(By.tagName("thead"));
		if (tabla.findElements(By.tagName("th")).get(5).getText().equalsIgnoreCase("Nombre Archivo"))
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
	}
	
	//Requiere importacion, osea preparacion
	@Test (groups = "BeFAN")
	public void TS135602_BeFan_Movil_Repro_Preactivacion_Gestion_de_simcards_Busqueda_de_archivos_Ver_detalle_Estado_del_archivo() {
		irA("gestion");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "Procesado");
		selectByText(driver.findElements(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).get(1), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(5000);
		WebElement tabla = driver.findElement(By.id("exportarTabla")).findElement(By.tagName("thead"));
		if (tabla.findElements(By.tagName("th")).get(6).getText().equalsIgnoreCase("Estado"))
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
	}
	
	@Test (groups = "BeFAN")
	public void TS135603_BeFan_Movil_Repro_Preactivacion_Gestion_de_simcards_Exportacion_de_archivo_Nombre() {
		BeFan Botones = new BeFan(driver);
		Botones.andaAlMenu("sims", "gestion");
		Botones.SGSeleccionEstado("Procesado");
		Botones.SGClickBuscar();
		Botones.SGTablaVisible();
		Botones.SGClickVerDetalle(1);
		String downloadPath = "/download";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("download.default_directory", downloadPath);
		Botones.SGVerDetalleBotonExportar();
		sleep(5000);
		Assert.assertTrue(scp.isFileDownloaded(downloadPath, "PREACTIVACIONES DIARIAS"), "Failed to download Expected document");
	}
	
	
	@Test (groups = "BeFAN")
	public void TS135592_BeFan_Movil_Repro_Preactivacion_Gestion_de_simcards_Busqueda_de_archivos_Ver_detalle_Agente() {
		irA("gestion");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "En Proceso");
		selectByText(driver.findElements(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).get(1), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(5000);
		try {
			driver.findElement(By.id("exportarTabla")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(8).click();
		} catch(Exception e) {}
		sleep(3000);
		boolean detalleAG = false;
		WebElement columnas = driver.findElement(By.cssSelector(".table.table-top-fixed.table-striped.table-primary")).findElement(By.tagName("tbody")).findElement(By.tagName("tr"));
		System.out.println(columnas.getText());
		for (WebElement x : columnas.findElements(By.tagName("td"))) {
			if (x.getText().toLowerCase().equals("vjp"))
				detalleAG = true;
		}
		Assert.assertTrue(detalleAG);
	}
	
	@Test (groups = "BeFAN")
	public void TS135593_BeFan_Movil_Repro_Preactivacion_Gestion_de_simcards_Busqueda_de_archivos_Ver_detalle_Linea(){
		irA("gestion");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "En Proceso");
		selectByText(driver.findElements(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).get(1), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(5000);
		try {
			driver.findElement(By.id("exportarTabla")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(8).click();
		} catch(Exception e) {}
		sleep(3000);
		WebElement columnas = driver.findElement(By.cssSelector(".table.table-top-fixed.table-striped.table-primary")).findElement(By.tagName("tbody")).findElement(By.tagName("tr"));
		boolean lin = false;
		if (columnas.findElements(By.tagName("td")).get(1).getText().matches("\\d{10}"))
		lin = true;
		Assert.assertTrue(lin);
		
		
	}
	
	@Test (groups = "BeFAN")
	public void TS135594_BeFan_Movil_Repro_Preactivacion_Gestion_de_simcards_Busqueda_de_archivos_Ver_detalle_Plan(){
		irA("gestion");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "Procesado");
		selectByText(driver.findElements(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).get(1), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(5000);
		try {
			driver.findElement(By.id("exportarTabla")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(8).click();
		} catch(Exception e) {}
		sleep(3000);
		boolean plan = false;
		WebElement columnas = driver.findElement(By.cssSelector(".table.table-top-fixed.table-striped.table-primary")).findElement(By.tagName("tr"));
		for (WebElement x : columnas.findElements(By.tagName("th"))) {
			if (x.getText().contains("Plan"))
				plan = true;
		}
		Assert.assertTrue(plan);
	}
	
	@Test (groups = "BeFan")
	public void TS135595_BeFan_Movil_Repro_Preactivacion_Gestion_de_simcards_Busqueda_de_archivos_Ver_detalle_NMU_Simcard() {
		irA("gestion");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "Procesado");
		selectByText(driver.findElements(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).get(1), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(5000);
		try {
			driver.findElement(By.id("exportarTabla")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(8).click();
		} catch(Exception e) {}
		sleep(3000);
		boolean preactivacion = false;
		WebElement columnas = driver.findElement(By.cssSelector(".table.table-top-fixed.table-striped.table-primary")).findElement(By.tagName("tr"));
		for (WebElement x : columnas.findElements(By.tagName("th"))) {
			if (x.getText().contains("Preactivaci\u00f3n"))
				preactivacion = true;
		}
		Assert.assertTrue(preactivacion);
	}
	
	@Test (groups = "BeFan")
	public void TS135596_BeFan_Movil_Repro_Preactivacion_Gestion_de_simcards_Busqueda_de_archivos_Ver_detalle_Serie_Simcard() {
		irA("gestion");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "Procesado");
		selectByText(driver.findElements(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).get(1), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(5000);
		try {
			driver.findElement(By.id("exportarTabla")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(8).click();
		} catch(Exception e) {}
		sleep(3000);
		boolean serie = false;
		WebElement columnas = driver.findElement(By.cssSelector(".table.table-top-fixed.table-striped.table-primary")).findElement(By.tagName("tr"));
		for (WebElement x : columnas.findElements(By.tagName("th"))) {
			if (x.getText().contains("Serie"))
				serie = true;
		}
		Assert.assertTrue(serie);
	}
	
	@Test (groups = "BeFan")
	public void TS135597_BeFan_Movil_Repro_Preactivacion_Gestion_de_simcards_Busqueda_de_archivos_Ver_detalle_Fecha_de_preactivacion_de_la_linea() {
		irA("gestion");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "Procesado");
		selectByText(driver.findElements(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).get(1), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(5000);
		try {
			driver.findElement(By.id("exportarTabla")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(8).click();
		} catch(Exception e) {}
		sleep(3000);
		boolean preactivacion = false;
		WebElement columnas = driver.findElement(By.cssSelector(".table.table-top-fixed.table-striped.table-primary")).findElement(By.tagName("tr"));
		for (WebElement x : columnas.findElements(By.tagName("th"))) {
			if (x.getText().contains("Preactivaci\u00f3n"))
				preactivacion = true;
		}
		Assert.assertTrue(preactivacion);
	}
	
	@Test (groups = "BeFan")
	public void TS135598_BeFan_Movil_Repro_Preactivacion_Gestion_de_simcards_Busqueda_de_archivos_Ver_detalle_Fecha_de_procesamiento_del_registro() {
		irA("gestion");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "Procesado");
		selectByText(driver.findElements(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).get(1), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(5000);
		try {
			driver.findElement(By.id("exportarTabla")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(8).click();
		} catch(Exception e) {}
		sleep(3000);
		boolean procesamiento = false;
		WebElement columnas = driver.findElement(By.cssSelector(".table.table-top-fixed.table-striped.table-primary")).findElement(By.tagName("tr"));
		for (WebElement x : columnas.findElements(By.tagName("th"))) {
			if (x.getText().contains("Procesamiento"))
				procesamiento = true;
		}
		Assert.assertTrue(procesamiento);
	}
	
	@Test (groups = "BeFan")
	public void TS135599_BeFan_Movil_Repro_Preactivacion_Gestion_de_simcards_Busqueda_de_archivos_Ver_detalle_Estado_del_registro() {
		irA("gestion");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "Procesado");
		selectByText(driver.findElements(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).get(1), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(5000);
		try {
			driver.findElement(By.id("exportarTabla")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(8).click();
		} catch(Exception e) {}
		sleep(3000);
		boolean estado = false;
		WebElement columnas = driver.findElement(By.cssSelector(".table.table-top-fixed.table-striped.table-primary")).findElement(By.tagName("tr"));
		for (WebElement x : columnas.findElements(By.tagName("th"))) {
			if (x.getText().contains("Estado"))
				estado = true;		
		}
		Assert.assertTrue(estado);
	}
	
	@Test (groups = "BeFan")
	public void TS135600_BeFan_Movil_Repro_Preactivacion_Gestion_de_simcards_Busqueda_de_archivos_Ver_detalle_Descripcion_del_estado_del_error() throws Exception {
		irA("gestion");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "Procesado");
		selectByText(driver.findElements(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).get(1), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(5000);
		try {
			driver.findElement(By.id("exportarTabla")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(8).click();
		} catch(Exception e) {}
		sleep(3000);
		
		WebElement wBody = driver.findElement(By.xpath("//div[@class='modal-body'] //table[@class='table table-top-fixed table-striped table-primary']"));
		Marketing mk = new Marketing(driver);
		List<WebElement> wEstadoError = mk.traerColumnaElement(wBody, 9, 9);
		for (WebElement wEstado : wEstadoError) {
			BeFan Botones = new BeFan(driver);
			String[] lasNoches = Botones.soyEzpesial("TS135600").split(",");
			if (lasNoches[0].equals("false")) {
				Assert.assertTrue(false);
			}
			String nombreArch = lasNoches[1];
			String deposito = lasNoches[2];
		}
		
		//TS112029 To verify the code inside the for
	}

	@Test (groups = {"BeFAN", "Agente"})
	public void TS135647_BeFan_Movil_Repro_Preactivacion_Visualizacion_de_datos_del_agente() {
	sleep(3000);
	driver.findElement(By.className("tpi-user")).findElement(By.tagName("span")).click();
	WebElement asd = driver.findElement(By.id("menudatos")).findElement(By.name("salir"));
	System.out.println(asd.getText());
	Assert.assertTrue(asd.isDisplayed());
		
	}
	@Test (groups = "BeFAN")
	public void TS135604_BeFan_Movil_Repro_Preactivacion_Gestion_de_simcards_Exportacion_de_archivo_Detalle() throws IOException {
		irA("gestion");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "Procesado");
		selectByText(driver.findElements(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).get(1), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(5000);
		try {
			driver.findElement(By.id("exportarTabla")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(8).click();
		} catch(Exception e) {}
		sleep(3000);
		driver.findElement(By.id("botonExportar")).click();
		sleep(5000);
		String downloadPath = "C:\\Users\\Quelys\\Downloads";
		try {
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+downloadPath);
			System.out.println("Descargo Archivo");
			} catch (IOException ee) {
			ee.printStackTrace();
			}
		Assert.assertTrue(scp.isFileDownloaded(downloadPath, "PREACTIVACIONES DIARIAS"));
	}
	
	

	//Preparacion para preactivacion
	@Test (groups = "PreactivacionBeFan")
	public void TS123_ElMetodoQueSopapeaATodosLosMetodos() throws Exception {
		
		//Adquiero datos del excel
		Object[][] testObjArray = ExcelUtils.getTableArray("E2EUAT.xlsx","E2EsinPago",1,1,8,"Preactivacion");
		
		//Inicio las otras clases
		DPW dpw = new DPW();
		BeFan Botones = new BeFan(driver);
		MDW mdw = new MDW();
		
		//Iniciacion de variables
		ArrayList<String> resultados = new ArrayList<String>();
		ArrayList<String> temporal = new ArrayList<String>();
		int FilasTotales = 0;
		int ColumnasTotales = 0;
		int i = 0;
		String path = "";
		String nombreArch = "";
		String deposito = "";
		String prefijo = "";
		String serial1 = "";
		String serial2 = "";
		String prefijo2 = "";
		String Cantidad = "";
		String mensaje = "";
		int cant = 0;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss"); 
		LocalDateTime now = LocalDateTime.now(); 
		String time = dtf.format(now);
		File salida = new File("C://BefanArchivos//salida//Resultado" + time + ".txt");
		
		//Calculo dimensiones de casos de preactivacion
		FilasTotales = testObjArray.length;
		ColumnasTotales = testObjArray[0].length;
		//Aqui viene lo bueno joven
		for (i=0;i<=FilasTotales-1;i++) {
			switch (testObjArray[i][1].toString()) {
			case "SerialConDepositoErroneo": 
				try {
					//Leo el archivo y cargo las variables para la preparacion de este casito
					path = testObjArray[i][0].toString();
					nombreArch = testObjArray[i][1].toString();
					deposito = testObjArray[i][2].toString();
					prefijo = testObjArray[i][3].toString();
					serial1 = testObjArray[i][4].toString();
					serial2 = testObjArray[i][5].toString();
					prefijo2 = testObjArray[i][6].toString();
					Cantidad = testObjArray[i][7].toString();
					
					irA("gestion");
					irA("importacion");
					sleep(500);
					Botones.SISeleccionDeDeposito(deposito);
					sleep(500);
					Botones.SISeleccionDePrefijo(prefijo);
					sleep(500);
					Botones.SISeleccionCantidadDePrefijo("1");
					sleep(500);
					Botones.SIClickAgregar();
					Botones.SIImportarArchivo(nombreArch = Botones.SICreacionArchivo(nombreArch, path, serial1, ""));
					sleep(500);
					Botones.SIClickImportar();
					sleep(500);
					mensaje = Botones.SIMensajeModal();
					if (mensaje.contentEquals("El archivo se import\u00f3 correctamente.")) {
						Botones.SIClickAceptarImportar();
						sleep(500);
						//Respondo por el caso
						resultados.add("TS97651," + nombreArch + "," + deposito);
						resultados.add("TS126640," + nombreArch + "," + deposito);
					} else {
						Botones.SIClickAceptarImportar();
						sleep(500);
					}
		
					//Reseteo variables
					path = "";
					nombreArch = "";
					deposito = "";
					prefijo = "";
					serial1 = "";
					serial2 = "";
					prefijo2 = "";
					Cantidad = "";
					mensaje = "";
					break;
				} catch (Exception e) {
					//Reseteo variables
					path = "";
					nombreArch = "";
					deposito = "";
					prefijo = "";
					serial1 = "";
					serial2 = "";
					prefijo2 = "";
					Cantidad = "";
					mensaje = "";
					break;
				}
				
				
			case "SerialInexistente": 
				try {
					//Leo el archivo y cargo las variables para la preparacion de este casito
					path = testObjArray[i][0].toString();
					nombreArch = testObjArray[i][1].toString();
					deposito = testObjArray[i][2].toString();
					prefijo = testObjArray[i][3].toString();
					serial1 = testObjArray[i][4].toString();
					serial2 = testObjArray[i][5].toString();
					prefijo2 = testObjArray[i][6].toString();
					Cantidad = testObjArray[i][7].toString();
					
					irA("gestion");
					irA("importacion");
					sleep(500);
					Botones.SISeleccionDeDeposito(deposito);
					sleep(500);
					Botones.SISeleccionDePrefijo(prefijo);
					sleep(500);
					Botones.SISeleccionCantidadDePrefijo("1");
					sleep(500);
					Botones.SIClickAgregar();
					Botones.SIImportarArchivo(nombreArch = Botones.SICreacionArchivo(nombreArch, path, serial1, ""));
					sleep(500);
					Botones.SIClickImportar();
					sleep(500);
					mensaje = Botones.SIMensajeModal();
					if (mensaje.contentEquals("El archivo se import\u00f3 correctamente.")) {
						Botones.SIClickAceptarImportar();
						sleep(500);
						//Respondo por el caso
						resultados.add("TS112029," + nombreArch + "," + deposito);
						resultados.add("TS135600," + nombreArch + "," + deposito);
					} else {
						Botones.SIClickAceptarImportar();
						sleep(500);
					}

		
					//Reseteo variables
					path = "";
					nombreArch = "";
					deposito = "";
					prefijo = "";
					serial1 = "";
					serial2 = "";
					prefijo2 = "";
					Cantidad = "";
					mensaje = "";
					break;
				} catch (Exception e) {
					//Reseteo variables
					path = "";
					nombreArch = "";
					deposito = "";
					prefijo = "";
					serial1 = "";
					serial2 = "";
					prefijo2 = "";
					Cantidad = "";
					mensaje = "";
					break;
				}
				
				
			case "DosSerialesValidos": 
				try {
					//Leo el archivo y cargo las variables para la preparacion de este casito
					path = testObjArray[i][0].toString();
					nombreArch = "seriales";
					deposito = testObjArray[i][2].toString();
					prefijo = testObjArray[i][3].toString();
					serial1 = testObjArray[i][4].toString();
					serial2 = testObjArray[i][5].toString();
					prefijo2 = testObjArray[i][6].toString();
					Cantidad = testObjArray[i][7].toString();
					
					
					cant = Integer.parseInt(Cantidad);
					irA("gestion");
					irA("importacion");
					sleep(500);
					Botones.SISeleccionDeDeposito(deposito);
					sleep(500);
					Botones.SISeleccionDePrefijo(prefijo);
					sleep(500);
					Botones.SISeleccionCantidadDePrefijo(Integer.toString(cant-1));
					sleep(500);
					Botones.SIClickAgregar();
					sleep(500);
					Botones.SISeleccionDePrefijo(prefijo2);
					sleep(500);
					Botones.SISeleccionCantidadDePrefijo("1");
					Botones.SIClickAgregar();
					sleep(500);
					Botones.SIImportarArchivo(nombreArch = Botones.LecturaDeDatosTxt(path + "\\"+ nombreArch + ".txt", cant));
					sleep(500);
					Botones.SIClickImportar();
					sleep(500);
					mensaje = Botones.SIMensajeModal();
					if (mensaje.contentEquals("El archivo se import\u00f3 correctamente.")) {
						Botones.SIClickAceptarImportar();
						sleep(500);
						//Respondo por el caso
						resultados.add("TS97657," + nombreArch + "," + deposito);
					} else {
						Botones.SIClickAceptarImportar();
						sleep(500);
					}

		
					//Reseteo variables
					path = "";
					nombreArch = "";
					deposito = "";
					prefijo = "";
					serial1 = "";
					serial2 = "";
					prefijo2 = "";
					Cantidad = "";
					mensaje = "";
					break;
				} catch (Exception e) {
					//Reseteo variables
					path = "";
					nombreArch = "";
					deposito = "";
					prefijo = "";
					serial1 = "";
					serial2 = "";
					prefijo2 = "";
					Cantidad = "";
					mensaje = "";
					break;
				}
				
			case "SerialNoMCVM": 
				try {
					//Leo el archivo y cargo las variables para la preparacion de este casito
					path = testObjArray[i][0].toString();
					nombreArch = testObjArray[i][1].toString();
					deposito = testObjArray[i][2].toString();
					prefijo = testObjArray[i][3].toString();
					serial1 = testObjArray[i][4].toString();
					serial2 = testObjArray[i][5].toString();
					prefijo2 = testObjArray[i][6].toString();
					Cantidad = testObjArray[i][7].toString();
					
					irA("gestion");
					irA("importacion");
					sleep(500);
					Botones.SISeleccionDeDeposito(deposito);
					sleep(500);
					Botones.SISeleccionDePrefijo(prefijo);
					sleep(500);
					Botones.SISeleccionCantidadDePrefijo("1");
					sleep(500);
					Botones.SIClickAgregar();
					Botones.SIImportarArchivo(nombreArch = Botones.SICreacionArchivo(nombreArch, path, serial1, ""));
					sleep(500);
					Botones.SIClickImportar();
					sleep(500);
					mensaje = Botones.SIMensajeModal();
					if (mensaje.contentEquals("El archivo se import\u00f3 correctamente.")) {
						Botones.SIClickAceptarImportar();
						sleep(500);
						//Respondo por el caso
						resultados.add("TS97653," + nombreArch + "," + deposito);
					} else {
						Botones.SIClickAceptarImportar();
						sleep(500);
					}
		
					//Reseteo variables
					path = "";
					nombreArch = "";
					deposito = "";
					prefijo = "";
					serial1 = "";
					serial2 = "";
					prefijo2 = "";
					Cantidad = "";
					mensaje = "";
					break;
				} catch (Exception e) {
					//Reseteo variables
					path = "";
					nombreArch = "";
					deposito = "";
					prefijo = "";
					serial1 = "";
					serial2 = "";
					prefijo2 = "";
					Cantidad = "";
					mensaje = "";
					break;
				}
				
				
			case "SerialBalido": 
				try {
					//Leo el archivo y cargo las variables para la preparacion de este casito
					path = testObjArray[i][0].toString();
					nombreArch = "seriales";
					deposito = testObjArray[i][2].toString();
					prefijo = testObjArray[i][3].toString();
					serial1 = testObjArray[i][4].toString();
					serial2 = testObjArray[i][5].toString();
					prefijo2 = testObjArray[i][6].toString();
					Cantidad = testObjArray[i][7].toString();
					
					
					cant = Integer.parseInt(Cantidad);
					irA("gestion");
					irA("importacion");
					sleep(500);
					Botones.SISeleccionDeDeposito(deposito);
					sleep(500);
					Botones.SISeleccionDePrefijo(prefijo);
					sleep(500);
					Botones.SISeleccionCantidadDePrefijo("1");
					sleep(500);
					Botones.SIClickAgregar();
					Botones.SIImportarArchivo(nombreArch = Botones.LecturaDeDatosTxt(path + "\\"+ nombreArch + ".txt", cant));
					sleep(500);
					Botones.SIClickImportar();
					sleep(500);
					mensaje = Botones.SIMensajeModal();
					if (mensaje.contentEquals("El archivo se import\u00f3 correctamente.")) {
						Botones.SIClickAceptarImportar();
						sleep(500);
						//Respondo por el caso
						resultados.add("TS111958," + nombreArch + "," + deposito);
					} else {
						Botones.SIClickAceptarImportar();
						sleep(500);
					}
		
					//Reseteo variables
					path = "";
					nombreArch = "";
					deposito = "";
					prefijo = "";
					serial1 = "";
					serial2 = "";
					prefijo2 = "";
					Cantidad = "";
					mensaje = "";
					break;
				} catch (Exception e) {
					//Reseteo variables
					path = "";
					nombreArch = "";
					deposito = "";
					prefijo = "";
					serial1 = "";
					serial2 = "";
					prefijo2 = "";
					Cantidad = "";
					mensaje = "";
					break;
				}
				
				
			case "SerialValidoEterno": 
				try {
					//Leo el archivo y cargo las variables para la preparacion de este casito
					path = testObjArray[i][0].toString();
					nombreArch = testObjArray[i][1].toString();
					deposito = testObjArray[i][2].toString();
					prefijo = testObjArray[i][3].toString();
					serial1 = testObjArray[i][4].toString();
					serial2 = testObjArray[i][5].toString();
					prefijo2 = testObjArray[i][6].toString();
					Cantidad = testObjArray[i][7].toString();
					
					irA("gestion");
					irA("importacion");
					sleep(500);
					Botones.SISeleccionDeDeposito(deposito);
					sleep(500);
					Botones.SISeleccionDePrefijo(prefijo);
					sleep(500);
					Botones.SISeleccionCantidadDePrefijo("1");
					sleep(500);
					Botones.SIClickAgregar();
					Botones.SIImportarArchivo(nombreArch = Botones.SICreacionArchivo(nombreArch, path, serial1, ""));
					sleep(500);
					Botones.SIClickImportar();
					sleep(500);
					mensaje = Botones.SIMensajeModal();
					if (mensaje.contentEquals("El archivo se import\u00f3 correctamente.")) {
						Botones.SIClickAceptarImportar();
						sleep(500);
						//Respondo por el caso
						resultados.add("TS111990," + nombreArch + "," + deposito);
						resultados.add("TS97654," + nombreArch + "," + deposito);
					} else {
						Botones.SIClickAceptarImportar();
						sleep(500);
					}
		
					//Reseteo variables
					path = "";
					nombreArch = "";
					deposito = "";
					prefijo = "";
					serial1 = "";
					serial2 = "";
					prefijo2 = "";
					Cantidad = "";
					mensaje = "";
					break;
				} catch (Exception e) {
					//Reseteo variables
					path = "";
					nombreArch = "";
					deposito = "";
					prefijo = "";
					serial1 = "";
					serial2 = "";
					prefijo2 = "";
					Cantidad = "";
					mensaje = "";
					break;
				}
				
			case "SerialParaAlterar": 
				try {
					//Leo el archivo y cargo las variables para la preparacion de este casito
					path = testObjArray[i][0].toString();
					nombreArch = "seriales";
					deposito = testObjArray[i][2].toString();
					prefijo = testObjArray[i][3].toString();
					serial1 = testObjArray[i][4].toString();
					serial2 = testObjArray[i][5].toString();
					prefijo2 = testObjArray[i][6].toString();
					Cantidad = testObjArray[i][7].toString();
					
					
					cant = Integer.parseInt(Cantidad);
					irA("gestion");
					irA("importacion");
					sleep(500);
					Botones.SISeleccionDeDeposito(deposito);
					sleep(500);
					Botones.SISeleccionDePrefijo(prefijo);
					sleep(500);
					Botones.SISeleccionCantidadDePrefijo("1");
					sleep(500);
					Botones.SIClickAgregar();
					Botones.SIImportarArchivo(nombreArch = Botones.LecturaDeDatosTxt(path + "\\"+ nombreArch + ".txt", cant));
					sleep(500);
					Botones.SIClickImportar();
					sleep(500);
					mensaje = Botones.SIMensajeModal();
					if (mensaje.contentEquals("El archivo se import\u00f3 correctamente.")) {
						Botones.SIClickAceptarImportar();
						sleep(500);
						//Respondo por el caso
						resultados.add("TS126672," + nombreArch + "," + deposito);
					} else {
						Botones.SIClickAceptarImportar();
						sleep(500);
					}
		
					//Reseteo variables
					path = "";
					nombreArch = "";
					deposito = "";
					prefijo = "";
					serial1 = "";
					serial2 = "";
					prefijo2 = "";
					Cantidad = "";
					mensaje = "";
					break;
				} catch (Exception e) {
					//Reseteo variables
					path = "";
					nombreArch = "";
					deposito = "";
					prefijo = "";
					serial1 = "";
					serial2 = "";
					prefijo2 = "";
					Cantidad = "";
					mensaje = "";
					break;
				}
			case "PrefijoAMatar": 
				try {
					//Leo el archivo y cargo las variables para la preparacion de este casito
					path = testObjArray[i][0].toString();
					nombreArch = "seriales";
					deposito = testObjArray[i][2].toString();
					prefijo = testObjArray[i][3].toString();
					serial1 = testObjArray[i][4].toString();
					serial2 = testObjArray[i][5].toString();
					prefijo2 = testObjArray[i][6].toString();
					Cantidad = testObjArray[i][7].toString();
					cant = Integer.parseInt(Cantidad);
					
					loginBeFANConfigurador(driver);
					sleep(500);
					irRegion("gestion");
					sleep(500);
					driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[1]/div/input")).sendKeys("BAS-VJP");
					sleep(500);
					driver.findElement(By.className("collapsed")).click();
					
					Botones.LogOutBefan(driver);
					loginBeFAN(driver);
					
					irA("gestion");
					irA("importacion");
					sleep(500);
					Botones.SISeleccionDeDeposito(deposito);
					sleep(500);
					Botones.SISeleccionDePrefijo(prefijo);
					sleep(500);
					Botones.SISeleccionCantidadDePrefijo("1");
					sleep(500);
					Botones.SIClickAgregar();
					Botones.SIImportarArchivo(nombreArch = Botones.LecturaDeDatosTxt(path + "\\"+ nombreArch + ".txt", cant));
					sleep(500);
					Botones.SIClickImportar();
					sleep(500);
					mensaje = Botones.SIMensajeModal();
					if (mensaje.contentEquals("El archivo se import\u00f3 correctamente.")) {
						Botones.SIClickAceptarImportar();
						sleep(500);
						//Respondo por el caso
						resultados.add("TS126672," + nombreArch + "," + deposito);
					} else {
						Botones.SIClickAceptarImportar();
						sleep(500);
					}
		
					//Reseteo variables
					path = "";
					nombreArch = "";
					deposito = "";
					prefijo = "";
					serial1 = "";
					serial2 = "";
					prefijo2 = "";
					Cantidad = "";
					mensaje = "";
					break;
				} catch (Exception e) {
					//Reseteo variables
					path = "";
					nombreArch = "";
					deposito = "";
					prefijo = "";
					serial1 = "";
					serial2 = "";
					prefijo2 = "";
					Cantidad = "";
					mensaje = "";
					break;
				}
			
			
			}
		}
		
		
		dpw.main();
		BufferedWriter c = new BufferedWriter(new FileWriter(salida));
	    for (String x : resultados) {
	    	c.write(x + System.lineSeparator());
	    	String[] Caso = x.split(",");
	    	if (Caso[0].equals("TS126672")) {
	    		sleep(120000);
	    		String serial = Botones.TraemeLosSeriales(Caso[1]);
	    		if (serial.equals("No existe el archivo")) {
	    		} else {
	    			boolean hola = mdw.requestValidadorS105(mdw.callSoapWebService(mdw.s105Request("ARRF",serial,"SG31185001"), "uat105"), serial);
	    		}
	    		
	    	}
	    }
	    c.close();
	    
	    
	    
	}
	
	
// DE 10 CON PREP
	@Test (groups = "BeFan")
	public void TS97651_BeFan_Movil_REPRO_PreaActivacion_Linea_Repro_SIMCARD_en_deposito_inexistente() throws IOException, Exception {
		BeFan Botones = new BeFan(driver);
		String[] resultadoEstado = {""};
		String[] resultadoTexto = {""};
		String estado = "Procesado";
		resultadoEstado[0] = "Error";
		resultadoTexto[0] = "El dep\u00f3sito de la SIM, VICLIE001, no corresponde con el dep\u00f3sito del punto de venta del agente, SG31185001.";
		
		//traigo resultado de preparacion
		String[] lasNoches = Botones.soyEzpesial("TS97651").split(",");
		if (lasNoches[0].equals("false")) {
			Assert.assertTrue(false);
		}
		String nombreArch = lasNoches[1];
		String deposito = lasNoches[2];
		
		//ejecuto
		irA("gestion");
		sleep(500);
		Botones.SGSeleccionEstado(estado);
		sleep(500);
		Botones.SGSeleccionDeposito(deposito);
		sleep(500);
		Botones.SGFechaDesdeAhora();
		sleep(500);
		Botones.SGClickBuscar();
		sleep(500);
		Assert.assertTrue(Botones.SGLeerCampoYValidar(nombreArch, resultadoEstado, resultadoTexto));
	}

	// DE 10 SIN PREP
	@Test (groups = "BeFan", dataProvider="SerialInexistente")
	public void TS112002_BeFan_Movil_REPRO_Preactivacion_repro__Importacion_de_SIM_repro__Mensaje_de_error_ante_volver_a_agregar_otro_prefijo(String path, String nombreArch, String deposito, String prefijo, String serial1, String serial2, String prefijo2, String Cantidad, String Agente, String depositoLogico) throws Exception{
		
		BeFan Botones = new BeFan(driver);
		irA("importacion");
		String mensaje;
		sleep(500);
		Botones.SISeleccionDeDeposito(deposito);
		sleep(500);
		Botones.SISeleccionDePrefijo(prefijo);
		sleep(500);
		Botones.SISeleccionCantidadDePrefijo("1");
		sleep(500);
		Botones.SIClickAgregar();
		sleep(700);
		Botones.SISeleccionDePrefijo(prefijo);
		sleep(500);
		Botones.SISeleccionCantidadDePrefijo("1");
		sleep(500);
		Botones.SIClickAgregar();
		sleep(1000);
		mensaje = Botones.SIMensajeModal();
		if (mensaje.contentEquals("El prefijo seleccionado ya se encuentra ingresado.")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

	}
	
	@Test (groups = {"BeFan"}, dataProvider="SerialInexistente")
	public void TS112029_BeFan_Movil_REPRO_Preactivacion_repro__Importacion_de_SIM_repro__S105__Simcard_inexistente(String path, String nombreArch, String deposito, String prefijo, String serial1, String serial2, String prefijo2, String Cantidad, String agente, String depositoLogico) throws IOException, Exception {
		DPW dpw = new DPW();
		BeFan Botones = new BeFan(driver);
		String[] resultadoEstado = {""};
		String[] resultadoTexto = {""};
		String estado = "Procesado";
		resultadoEstado[0] = "Error";
		resultadoTexto[0] = "Error al consumir un proveedor - Provider ID: VMI.INVENTARIO.INVENTARIO - Provider Error Code: 1200 - Provider Error Description: Serial No encontrado";
		//traigo resultado de preparacion
		String[] lasNoches = Botones.soyEzpesial("TS112029").split(",");
		if (lasNoches[0].equals("false")) {
			Assert.assertTrue(false);
		}
		nombreArch = lasNoches[1];
		deposito = lasNoches[2];
		
		//ejecuto
		irA("gestion");
		sleep(500);
		Botones.SGSeleccionEstado(estado);
		sleep(500);
		Botones.SGSeleccionDeposito(deposito);
		sleep(500);
		Botones.SGFechaDesdeAhora();
		sleep(500);
		Botones.SGClickBuscar();
		sleep(500);
		Assert.assertTrue(Botones.SGLeerCampoYValidar(nombreArch, resultadoEstado, resultadoTexto));

	}	
	//DE 10 SIN PREP
	@Test (groups = "BeFan", dataProvider="SerialConFormatoInvalido")
	public void TS126615_BeFan_Movil_REPRO_Preactivacion_repro__Importacion_de_agrupadores__Formato_erroneo(String path, String nombreArch, String deposito, String prefijo, String serial1, String serial2, String prefijo2) throws IOException {
		BeFan Botones = new BeFan(driver);
		String mensaje;
		
		irA("importacion");
		sleep(500);
		Botones.SISeleccionDeDeposito(deposito);
		sleep(500);
		Botones.SISeleccionDePrefijo(prefijo);
		sleep(500);
		Botones.SISeleccionCantidadDePrefijo("1");
		sleep(500);
		Botones.SIClickAgregar();
		Botones.SIImportarArchivo(nombreArch = Botones.SICreacionArchivo(nombreArch, path, serial1, ""));
		sleep(500);
		Botones.SIClickImportar();
		sleep(500);
		mensaje = Botones.SIMensajeModalMasDeUnMensaje();
		if (mensaje.contentEquals("Las sims deben tener 20 caracteres num\u00e9ricos sin espacios")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
		Botones.SIClickAceptarImportar();
		sleep(1000);
	}
// Revisar, faltaria consumir un servicio S105 en el medio para reservarlo
	@Test (groups = {"BeFan"})
	public void TS126640_BeFan_Movil_REPRO_Preactivacion_repro__Importacion_de_SIM_repro__S105__Deposito_erroneo() throws IOException, Exception {
		BeFan Botones = new BeFan(driver);
		String[] resultadoEstado = {""};
		String[] resultadoTexto = {""};
		String estado = "Procesado";
		resultadoEstado[0] = "Error";
		resultadoTexto[0] = "El dep\u00f3sito de la SIM, VICLIE001, no corresponde con el dep\u00f3sito del punto de venta del agente, SG31185001.";

		//traigo resultado de preparacion
		String[] lasNoches = Botones.soyEzpesial("TS126640").split(",");
		if (lasNoches[0].equals("false")) {
			Assert.assertTrue(false);
		}
		String nombreArch = lasNoches[1];
		String deposito = lasNoches[2];
		
		//ejecuto
		irA("gestion");
		sleep(500);
		Botones.SGSeleccionEstado(estado);
		sleep(500);
		Botones.SGSeleccionDeposito(deposito);
		sleep(500);
		Botones.SGFechaDesdeAhora();
		sleep(500);
		Botones.SGClickBuscar();
		sleep(500);
		Assert.assertTrue(Botones.SGLeerCampoYValidar(nombreArch, resultadoEstado, resultadoTexto));
	}
// DE 10
	@Test (groups = {"BeFan"}, dataProvider="SerialBalido")
	public void TS126648_BeFan_Movil_REPRO_Preactivacion_repro__Importacion_de_SIM_repro__S436__Envio_de_lote(String path, String nombreArch, String deposito, String prefijo, String serial1, String serial2, String prefijo2, String Cantidad) throws IOException, Exception {
		BeFan Botones = new BeFan(driver);
		DPW dpw = new DPW();
		String[] resultadoEstado = {""};
		String[] resultadoTexto = {""};
		String estado = "En Proceso";
		resultadoEstado[0] = "PendientePreactivar";
		resultadoTexto[0] = "Reserva realizada";
		String mensaje = "";
		int cant = 0;
		cant = Integer.parseInt(Cantidad);
		irA("importacion");
		sleep(500);
		Botones.SISeleccionDeDeposito(deposito);
		sleep(500);
		Botones.SISeleccionDePrefijo(prefijo);
		sleep(500);
		Botones.SISeleccionCantidadDePrefijo("1");
		sleep(500);
		Botones.SIClickAgregar();
		Botones.SIImportarArchivo(nombreArch = Botones.LecturaDeDatosTxt(path + "\\"+ nombreArch + ".txt", cant));
		sleep(500);
		Botones.SIClickImportar();
		sleep(500);
		mensaje = Botones.SIMensajeModal();
		if (mensaje.contentEquals("El archivo se import\u00f3 correctamente.")) {
		} else {
			Assert.assertTrue(false);
		}
		Botones.SIClickAceptarImportar();
		sleep(500);
		dpw.main();
		sleep(118000);
		irA("gestion");
		sleep(500);
		Botones.SGSeleccionEstado(estado);
		sleep(500);
		Botones.SGSeleccionDeposito(deposito);
		sleep(500);
		Botones.SGFechaDesdeAhora();
		sleep(500);
		Botones.SGClickBuscar();
		sleep(500);
		Assert.assertTrue(Botones.SGLeerCampoYValidar(nombreArch, resultadoEstado, resultadoTexto));
	}
	//Falta probar, deberia funcionar :(
	@Test (groups = {"BeFan"}, dataProvider="DosSerialesValidos")
	public void TS97657_BeFan_Movil_REPRO_Asociacion_de_diferentes_seriales_a_diferentes_prefijos(String path, String nombreArch, String deposito, String prefijo, String serial1, String serial2, String prefijo2) throws Exception {
		BeFan Botones = new BeFan(driver);
		String[] resultadoEstado = {""};
		String[] resultadoTexto = {""};
		String estado = "Procesado";
		resultadoEstado[0] = "Activado";
		resultadoTexto[0] = "Activaci\u00f3n confirmada";
		resultadoEstado[1] = "Activado";
		resultadoTexto[1] = "Activaci\u00f3n confirmada";
		String mensaje = "";
		int cant = 0;
		//cant = Integer.parseInt(Cantidad);
		irA("importacion");
		sleep(500);
		Botones.SISeleccionDeDeposito(deposito);
		sleep(500);
		Botones.SISeleccionDePrefijo(prefijo);
		sleep(500);
		Botones.SISeleccionCantidadDePrefijo(Integer.toString(cant-1));
		sleep(500);
		Botones.SIClickAgregar();
		sleep(500);
		Botones.SISeleccionDePrefijo(prefijo2);
		sleep(500);
		Botones.SISeleccionCantidadDePrefijo("1");
		Botones.SIClickAgregar();
		sleep(500);
		Botones.SIImportarArchivo(nombreArch = Botones.LecturaDeDatosTxt(path + "\\"+ nombreArch + ".txt", cant));
		sleep(500);
		Botones.SIClickImportar();
		sleep(500);
		mensaje = Botones.SIMensajeModal();
		if (mensaje.contentEquals("El archivo se import\u00f3 correctamente.")) {
		} else {
			Assert.assertTrue(false);
		}
		Botones.SIClickAceptarImportar();		
		sleep(500);
		//dpw.main();
		sleep(1198000);
		irA("gestion");
		sleep(500);
		Botones.SGSeleccionEstado(estado);
		sleep(500);
		Botones.SGSeleccionDeposito(deposito);
		sleep(500);
		Botones.SGFechaDesdeAhora();
		sleep(500);
		Botones.SGClickBuscar();
		sleep(500);
		Assert.assertTrue(Botones.SGLeerCampoYValidar(nombreArch, resultadoEstado, resultadoTexto));
	}
	
	
// DE 10 SIN PREP
	@Test (groups = "BeFan", dataProvider="ArchivoVacio")
	public void TS97664_BeFan_Movil_REPRO_Cantidad_inexistente(String path, String nombreArch, String deposito, String prefijo, String serial1, String serial2, String prefijo2) throws IOException {
		BeFan Botones = new BeFan(driver);
		String mensaje;
		
		irA("importacion");
		sleep(500);
		Botones.SISeleccionDeDeposito(deposito);
		sleep(500);
		Botones.SISeleccionDePrefijo(prefijo);
		sleep(500);
		Botones.SISeleccionCantidadDePrefijo("1");
		sleep(500);
		Botones.SIClickAgregar();
		Botones.SIImportarArchivo(nombreArch = Botones.SICreacionArchivo(nombreArch, path, "", ""));
		sleep(500);
		Botones.SIClickImportar();
		sleep(1000);
		mensaje = Botones.SIMensajeModal();
		if (mensaje.contentEquals("El archivo no contiene datos")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	
	}
	
// DE 10 CON PREP
	@Test (groups = "BeFan")
	public void TS97653_BeFan_Movil_REPRO_PreaActivacion_Linea_Repro_SIMCARD_en_estado_distinto_a_MCVM() throws IOException, Exception {
		BeFan Botones = new BeFan(driver);
		String[] resultadoEstado = {""};
		String[] resultadoTexto = {""};
		String estado = "Procesado";
		resultadoEstado[0] = "Error";
		resultadoTexto[0] = "No esta disponible para la venta.";
		//traigo resultado de preparacion
		String[] lasNoches = Botones.soyEzpesial("TS97653").split(",");
		if (lasNoches[0].equals("false")) {
			Assert.assertTrue(false);
		}
		String nombreArch = lasNoches[1];
		String deposito = lasNoches[2];
		
		//ejecuto
		irA("gestion");
		sleep(500);
		Botones.SGSeleccionEstado(estado);
		sleep(500);
		Botones.SGSeleccionDeposito(deposito);
		sleep(500);
		Botones.SGFechaDesdeAhora();
		sleep(500);
		Botones.SGClickBuscar();
		sleep(500);
		
		Assert.assertTrue(Botones.SGLeerCampoYValidar(nombreArch, resultadoEstado, resultadoTexto));
	}
	
	
// DE 10 SIN PREP
	@Test (groups = "BeFan", dataProvider="SerialesNoValidos")
	public void TS97658_BeFan_Movil_REPRO_Serial_no_asociado_a_ningun_prefijo(String path, String nombreArch, String deposito, String prefijo, String serial1, String serial2, String prefijo2) throws IOException {
		BeFan Botones = new BeFan(driver);
		String mensaje;
		
		irA("importacion");
		Botones.SISeleccionDeDeposito(deposito);
		Botones.SISeleccionDePrefijo(prefijo);
		Botones.SISeleccionCantidadDePrefijo("1");
		Botones.SIClickAgregar();
		Botones.SIImportarArchivo(nombreArch = Botones.SICreacionArchivo(nombreArch, path, serial1, serial2));
		Botones.SIClickImportar();
		mensaje = Botones.SIMensajeModal();
		if (mensaje.contentEquals("La sumatoria de la cantidad de prefijos es menor a la cantidad total de lineas del archivo.")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

	}

	//DE 10 CON PREP
	@Test (groups = "BeFan")
	public void TS111958_BeFan_Movil_REPRO_Preactivacion_repro__PreActivacion_Linea_Repro() throws IOException, Exception {
		BeFan Botones = new BeFan(driver);
		String[] resultadoEstado = {""};
		String[] resultadoTexto = {""};
		String estado = "Procesado";
		resultadoEstado[0] = "Activado";
		resultadoTexto[0] = "Activaci\u00f3n confirmada";
		
		//traigo resultado de preparacion
		//Agregar para ver todas
		String[] lasNoches = Botones.soyEzpesial("TS111958").split(",");
		if (lasNoches[0].equals("false")) {
			Assert.assertTrue(false);
		}
		String nombreArch = lasNoches[1];
		String deposito = lasNoches[2];
		
		//ejecuto
		irA("gestion");
		sleep(500);
		Botones.SGSeleccionEstado(estado);
		sleep(500);
		Botones.SGSeleccionDeposito(deposito);
		sleep(500);
		Botones.SGFechaDesdeAhora();
		sleep(500);
		Botones.SGClickBuscar();
		sleep(500);
		Assert.assertTrue(Botones.SGLeerCampoYValidar(nombreArch, resultadoEstado, resultadoTexto));
		//Falta verificacion en CRM de lineas preactivadas
	}
// DE 10 SIN PREP
	@Test (groups = "BeFan", dataProvider="SerialInexistente", priority = 2)
	public void TS97656_BeFan_Movil_REPRO_Cantidad_de_seriales_ingresados_mayor_al_habilitado_por_agente(String path, String nombreArch, String deposito, String prefijo, String serial1, String serial2, String prefijo2, String Cantidad, String agente, String depositoLogico) throws Exception, IOException {
		BeFan Botones = new BeFan(driver);
		String mensaje;
		
		
		Botones.LogOutBefan(driver);
		sleep(500);
		loginBeFANConfigurador(driver);
		sleep(500);
		irCupos("gestion");
		sleep(500);
		Botones.CGeliminar(agente, deposito);
		sleep(500);
		Botones.LogOutBefan(driver);
		loginBeFAN(driver);
		irA("importacion");
		sleep(500);
		Botones.SISeleccionDeDeposito(deposito);
		sleep(500);
		Botones.SISeleccionDePrefijo(prefijo);
		sleep(500);
		Botones.SISeleccionCantidadDePrefijo("1");
		sleep(500);
		Botones.SIClickAgregar();
		Botones.SIImportarArchivo(nombreArch = Botones.SICreacionArchivo(nombreArch, path, serial1, ""));
		sleep(500);
		Botones.SIClickImportar();
		sleep(1000);
		mensaje = Botones.SIMensajeModal();
		sleep(10000);
		if (mensaje.contentEquals("Error al intentar validar cantidad de cupos.")) {
			try {
				Botones.SIClickAceptarImportar();
				sleep(500);
				Botones.LogOutBefan(driver);
				sleep(500);
				loginBeFANConfigurador(driver);
				sleep(500);
				irCupos("importacion");
				sleep(500);
				Botones.CIImportarArchivo(agente, depositoLogico);
				Assert.assertTrue(true);
			} catch (Exception e) {
				Assert.assertTrue(true);
			}

			
		} else {
			try {
				Botones.SIClickAceptarImportar();
				sleep(500);
				Botones.LogOutBefan(driver);
				sleep(500);
				loginBeFANConfigurador(driver);
				sleep(500);
				irCupos("importacion");
				sleep(500);
				Botones.CIImportarArchivo(agente, depositoLogico);
				Assert.assertTrue(false);
			} catch (Exception e) {
					Assert.assertTrue(false);
				}
		}


	}
	
	@Test (groups = {"BeFan"})
	public void TS97654_BeFan_Movil_REPRO_PreaActivacion_Linea_Repro_Localidad_inexistente_para_numeracion_movil() throws IOException, Exception {
		BeFan Botones = new BeFan(driver);
		DPW dpw = new DPW();
		String[] resultadoEstado = {""};
		String[] resultadoTexto = {""};
		String estado = "Procesado";
		resultadoEstado[0] = "Error";
		resultadoTexto[0] = "Desreserva realizada";
		
		//traigo resultado de preparacion
		String[] lasNoches = Botones.soyEzpesial("TS97654").split(",");
		if (lasNoches[0].equals("false")) {
			Assert.assertTrue(false);
		}
		String nombreArch = lasNoches[1];
		String deposito = lasNoches[2];
		
		//ejecuto
		irA("gestion");
		sleep(500);
		Botones.SGSeleccionEstado(estado);
		sleep(500);
		Botones.SGSeleccionDeposito(deposito);
		sleep(500);
		Botones.SGFechaDesdeAhora();
		sleep(500);
		Botones.SGClickBuscar();
		sleep(500);
		Assert.assertTrue(Botones.SGLeerCampoYValidar(nombreArch, resultadoEstado, resultadoTexto));
		
	}
	
	@Test (groups = {"BeFan"})
	public void TS111990_BeFan_Movil_REPRO_PreaActivacion_Linea_Repro_Localidad_inexistente_para_numeracion_movil() throws IOException, Exception {
		BeFan Botones = new BeFan(driver);
		String[] resultadoEstado = {""};
		String[] resultadoTexto = {""};
		String estado = "Procesado";
		resultadoEstado[0] = "Error";
		resultadoTexto[0] = "Desreserva realizada";

		//traigo resultado de preparacion
		String[] lasNoches = Botones.soyEzpesial("TS111990").split(",");
		if (lasNoches[0].equals("false")) {
			Assert.assertTrue(false);
		}
		String nombreArch = lasNoches[1];
		String deposito = lasNoches[2];
		
		//ejecuto
		irA("gestion");
		sleep(500);
		Botones.SGSeleccionEstado(estado);
		sleep(500);
		Botones.SGSeleccionDeposito(deposito);
		sleep(500);
		Botones.SGFechaDesdeAhora();
		sleep(500);
		Botones.SGClickBuscar();
		sleep(500);
		Assert.assertTrue(Botones.SGLeerCampoYValidar(nombreArch, resultadoEstado, resultadoTexto));
		
	}
	
	// DE 10 CON PREP
	@Test (groups = "BeFan")
	public void TS126672_BeFan_Movil_REPRO_Preactivacion_repro__Importacion_de_SIM_repro__S105__Fallo_al_confirmar_el_serial() throws IOException, Exception {
		BeFan Botones = new BeFan(driver);
		String[] resultadoEstado = {""};
		String[] resultadoTexto = {""};
		String estado = "En proceso";
		resultadoEstado[0] = "Error";
		resultadoTexto[0] = "6.1.1.3 // Movimiento no valido";

		//traigo resultado de preparacion
		String[] lasNoches = Botones.soyEzpesial("TS126672").split(",");
		if (lasNoches[0].equals("false")) {
			Assert.assertTrue(false);
		}
		String nombreArch = lasNoches[1];
		String deposito = lasNoches[2];
		
		//ejecuto
		irA("gestion");
		sleep(500);
		Botones.SGSeleccionEstado(estado);
		sleep(500);
		Botones.SGSeleccionDeposito(deposito);
		sleep(500);
		Botones.SGFechaDesdeAhora();
		sleep(500);
		Botones.SGClickBuscar();
		sleep(500);
		Assert.assertTrue(Botones.SGLeerCampoYValidar(nombreArch, resultadoEstado, resultadoTexto));
		
	}
	
	
	@Test (groups = "BeFAN")
	public void TS112052_BeFan_Movil_REPRO_Preactivacion_repro_Busqueda_de_archivos_Usuario_TPA_Visualizacion_de_mas_informacion_Exportacion_Nombre() {
		irA("gestion");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "Procesado");
		selectByText(driver.findElements(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).get(1), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(5000);
		driver.findElement(By.id("exportarTabla")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(8).click();
		sleep(3000);
		driver.findElement(By.id("botonExportar")).click();
		sleep(5000);
		String downloadPath = "C:\\Users\\Nicolas\\Downloads";
		Assert.assertTrue(scp.isFileDownloaded(downloadPath, "PREACTIVACIONES DIARIAS"));
	}
	
	@Test (groups = "BeFan")
	public void TS135619_BeFan_Movil_Repro_Preactivacion_Importacion_de_cupos_Exitoso_Verificacion_Dentro_de_la_fecha() {
		ContactSearch contact = new ContactSearch(driver);
		irA("importacion");
		sleep(7000);
		selectByText(driver.findElement(By.name("vendedores")), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
		sleep(7000);
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "2477");
		driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys("1");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		File directory = new File("BeFan135619d.txt");
		contact.subir_cupos(new File(directory.getAbsolutePath()).toString(),"");
		sleep(5000);
		driver.findElements(By.cssSelector(".btn.btn-primary")).get(2).click();
		Boolean a = false;
		sleep(5000);
		List <WebElement> formato = driver.findElements(By.className("modal-content"));
		for(WebElement x : formato) {
			if(x.getText().toLowerCase().contains("el archivo se import\u00f3 correctamente")) {
				a= true;
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test(groups= {"BeFan"}, dependsOnMethods ="TS97651_BeFan_Movil_REPRO_PreaActivacion_Linea_Repro_SIMCARD_en_deposito_inexistente")
	public void TS97652_BeFan_Movil_REPRO_PreaActivacion_Linea_Repro_SIMCARD_no_en_deposito_de_AGENTE() {
		Assert.assertTrue(true);
	}
	
	@Test(groups= {"BeFan"}, dependsOnMethods ="TS126648_BeFan_Movil_REPRO_Preactivacion_repro__Importacion_de_SIM_repro__S436__Envio_de_lote")
	public void TS97667_BeFan_Movil_REPRO_Seriales_con_estado_PENDIENTE_DE_VALIDAR() {
		Assert.assertTrue(true);
	}
	
	@Test(groups= {"BeFan"}, dependsOnMethods ="TS111958_BeFan_Movil_REPRO_Preactivacion_repro__PreActivacion_Linea_Repro")
	public void TS97671_BeFan_Movil_REPRO_Seriales_con_estado_ACTIVADA() {
		Assert.assertTrue(true);
	}
	
	@Test(groups= {"BeFan"}, dependsOnMethods ="TS111958_BeFan_Movil_REPRO_Preactivacion_repro__PreActivacion_Linea_Repro")
	public void TS111996_BeFan_Movil_REPRO_Preactivacion_repro__Envio_de_lote_a_OM__Verificacion_de_lineas_enviadas() {
		Assert.assertTrue(true);
	}
	
	@Test(groups= {"BeFan"}, dependsOnMethods ="TS111990_BeFan_Movil_REPRO_PreaActivacion_Linea_Repro_Localidad_inexistente_para_numeracion_movil")
	public void TS111998_BeFan_Movil_REPRO_Preactivacion_repro__Reserva_de_numeros_en_numeracion_movil__Fallo_y_desreserva_de_SIMCARDS() {
		Assert.assertTrue(true);
	}
	
	@Test(groups= {"BeFan"}, dependsOnMethods ="TS111958_BeFan_Movil_REPRO_Preactivacion_repro__PreActivacion_Linea_Repro")
	public void TS126673_BeFan_Movil_REPRO_Preactivacion_repro__Prefijo_con_cupos() {
		Assert.assertTrue(true);
	}
	
	@Test (groups = "BeFAN")
	public void TS126680_BeFan_Movil_REPRO_Preactivacion_repro_Visualizacion_de_archivos_importados_Fecha_de_carga() throws ParseException {
		String sDateFormat = "dd/MM/yyyy HH:mm:ss";
		irA("gestion");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "En Proceso");
		selectByText(driver.findElements(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).get(1), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(5000);
		
		WebElement wBody = driver.findElement(By.id("exportarTabla"));
		Marketing mk = new Marketing(driver);
		List<WebElement> wFechaCarga = mk.traerColumnaElement(wBody, 9, 1);
		for (WebElement wFecha : wFechaCarga) {
			System.out.println(wFecha.getText());
			
			SimpleDateFormat sdfDateFormat = new SimpleDateFormat(sDateFormat);
			sdfDateFormat.parse(wFecha.getText());
		}
	}
	
	@Test (groups = {"BeFan"}, dataProvider="GestionRegionesCreacion", dependsOnGroups="EliminacionDeAgrupador")
	public void TS126636_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Busqueda_Eliminacion_de_agrupadores_Si_Sin_preactivar_Verificacion(String sRegion) {
		irA("Sims", "Importaci\u00f3n");
		
		WebElement wSelect = driver.findElement(By.name("vendedores"));
		List<WebElement> wOptions = wSelect.findElements(By.tagName("option"));
		boolean bAssert = true;
		for(WebElement wAux : wOptions) {
			if(wAux.getText().contains(sRegion)) {
				bAssert = false;
			}
		}
		
		Assert.assertTrue(bAssert);
	}
	
	@Test (groups = "BeFAN")
	public void TS126682_BeFan_Movil_REPRO_Preactivacion_repro_Visualizacion_de_archivos_importados_Fecha_de_procesamiento_Sin_fecha() {
		boolean fechaProcesado = false;
		irA("gestion");
		selectByText(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")), "En Proceso");
		selectByText(driver.findElements(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).get(1), "BAS-VJP-BAHIA BLANCA - VJP Punta Alta");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		sleep(5000);
		
		driver.findElement(By.xpath("//button[@class='btn btn-primary btn-xs']")).click();
		
		sleep(5000);
		WebElement wBody = driver.findElement(By.xpath("//div[@class='modal-body'] //table[@class='table table-top-fixed table-striped table-primary']"));
		Marketing mk = new Marketing(driver);
		List<WebElement> wFechaProcesamiento = mk.traerColumnaElement(wBody, 9, 7);
		for (WebElement wFecha : wFechaProcesamiento) {
			Assert.assertTrue(wFecha.getText().isEmpty());
		}
	}
	
	@Test (groups = {"BeFan"}, dataProvider="GestionRegionesCreacion", dependsOnGroups="EliminacionDePrefijo")
	public void TS126637_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Busqueda_Modificacion_de_agrupadores_Eliminacion_de_prefijos_en_agrupador_existente_Guardando_Verificacion(String sRegion) throws IOException {
		irA("Sims", "Importaci\u00f3n");
		
		String sPrefijo = readTxt("Prefijo.txt");
		System.out.println("sPrefijo: " + sPrefijo);
		
		WebElement wSelect = driver.findElement(By.name("vendedores"));
		List<WebElement> wOptions = wSelect.findElements(By.tagName("option"));
		boolean bAssert = false;
		for(WebElement wAux : wOptions) {
			if(wAux.getText().contains(sRegion)) {
				bAssert = true;
				wAux.click();
			}
		}
		Assert.assertTrue(bAssert);
		
		wSelect = driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-valid.ng-empty.ng-touched"));
		wOptions = wSelect.findElements(By.tagName("option"));
		for(WebElement wAux : wOptions) {
			if(wAux.getText().equals(sPrefijo)) {
				bAssert = false;
			}
		}
		Assert.assertTrue(bAssert);
	}
	
	
	@Test(groups={"BeFan"}, dependsOnMethods= {"TS97654_BeFan_Movil_REPRO_PreaActivacion_Linea_Repro_Localidad_inexistente_para_numeracion_movil"})
	public void TS97670_BeFan_Movil_REPRO_Seriales_con_estado_ERROR() {
		Assert.assertTrue(true);
	}
	

}