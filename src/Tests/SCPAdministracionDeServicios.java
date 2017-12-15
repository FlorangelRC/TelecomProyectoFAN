package Tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.SCP;
import Pages.setConexion;

public class SCPAdministracionDeServicios extends TestBase {
	private WebDriver driver;
	private static String downloadPath = "C:\\Users\\Florangel\\Downloads";
	
	
	
	@BeforeClass(groups = "SCP")
	public void init() throws Exception
	{
		this.driver = setConexion.setupEze();
		driver.get("http://www.google.com");
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		loginSCPAdminServices(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
	}
	
	@BeforeMethod(groups = "SCP")
	public void setup() {
		SCP pScp = new SCP(driver);
		//pScp.goToMenu("scp");
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		pScp.clickOnTabByName("cuentas");
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//pScp.listTypeAcc("Todas Las cuentas");
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//pScp.clickOnFirstAccRe();
		pScp.clickEnCuentaPorNombre("Florencia Di Ci");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	//@AfterClass(groups = "SCP")
	public void tearDown() {
		driver.quit();
	}
	
	@Test(groups = "SCP")
	public void TS110254_Estructura_De_Los_Servicios_Servicios_Nuestros() {
		SCP pcp = new SCP(driver);
		pcp.moveToElementOnAccAndClick("segundoTitulo",2);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		assertTrue(driver.findElement(By.className("NotUsed")).getAttribute("style").contains("rgb(179, 253, 159)")&&(driver.findElement(By.className("NotUsedSampleText")).getText().toLowerCase().contains("nosotros")));
		
	}
	
	@Test(groups = "SCP")
	public void TS110255_Estructura_De_Los_Servicios_Servicios_De_La_Competencia() {
		SCP pcp = new SCP(driver);
		pcp.moveToElementOnAccAndClick("segundoTitulo",2);
		assertTrue(driver.findElement(By.className("hasOpportunity")).getAttribute("style").contains("rgb(255, 158, 158)")&&(driver.findElement(By.className("hasOpportunitySampleText")).getText().toLowerCase().contains("competencia")));
		
	}
	
	@Test(groups = "SCP")
	public void TS110256_Estructura_De_Los_Servicios_Servicios_Futuros() {
		SCP pcp = new SCP(driver);
		pcp.moveToElementOnAccAndClick("segundoTitulo",2);
		assertTrue(driver.findElement(By.className("hasPotentialBussiness")).getAttribute("style").contains("rgb(255, 255, 139)")&&(driver.findElement(By.className("hasPotentialBussinessSampleText")).getText().toLowerCase().contains("futuro")));
		
	}
	
	@Test(groups = "SCP")
	public void TS112539_Administracion_de_Contexto_Sectorial_Agregar_Guardando() {
		BasePage Bp = new BasePage();
		SCP pcp = new SCP(driver);
		String sector = new String();
		List<WebElement> servicioList = driver.findElement(By.className("detailList")).findElements(By.tagName("tr"));
		servicioList.remove(0);
		for (WebElement UnS : servicioList) {
			if (UnS.findElements(By.tagName("td")).get(2).getText().toLowerCase().contains("segmento vertical")) {
				sector = UnS.findElements(By.tagName("td")).get(3).getText();
				System.out.println("Sector: "+sector);
				break;
			}	
		}
		pcp.moveToElementOnAccAndClick("quintoTitulo",1);
		Select listSelect = new Select(driver.findElement(By.className("panel-body")).findElement(By.tagName("select")));
		listSelect.selectByVisibleText(sector);
		driver.findElement(By.className("panel-body")).findElement(By.cssSelector(".btn.btn.btn-default.btn-sm")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		servicioList = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement UnS : servicioList) {
			if (UnS.getText().toLowerCase().contains("agregar")) {
				UnS.click();
				break;
			}	
		}
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.className("modal-body")).findElement(By.tagName("input")).sendKeys("Contexto sectorial automatizado");
		listSelect = new Select(driver.findElement(By.className("modal-body")).findElement(By.tagName("select")));
		listSelect.selectByVisibleText("Contexto General");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.className("modal-body")).findElement(By.tagName("textarea")).sendKeys("Contexto sectorial automatizado");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElements(By.className("modal-footer")).get(0).findElement(By.cssSelector(".btn.btn-primary")).click();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		servicioList = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement UnS : servicioList) {
			if (UnS.getText().toLowerCase().contains("guardar")) {
				UnS.click();
				break;
			}	
		}
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		SCP pScp = new SCP(driver);
		pScp.clickOnTabByName("cuentas");
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		pScp.listTypeAcc("Todas Las cuentas");
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		pScp.clickOnFirstAccRe();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		driver.switchTo().frame(Bp.getFrameForElement(driver, By.id("primerTitulo")));
		pcp.moveToElementOnAccAndClick("primerTitulo",1);
		driver.switchTo().defaultContent();
		driver.findElement(By.id("hidden-Con")).click();
		boolean enc = false;
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		servicioList = driver.findElement(By.cssSelector(".table.table-striped.table-bordered.table-condensed")).findElements(By.tagName("tr"));
		servicioList.remove(0);
		for (WebElement UnaC : servicioList) {
			if ((UnaC.findElements(By.tagName("td")).get(1).getText().toLowerCase().contains("contexto sectorial automatizado"))&&(UnaC.findElements(By.tagName("td")).get(2).getText().toLowerCase().contains("contexto sectorial automatizado"))) {
				enc = true;
			}	
		}
		assertTrue(enc);
		
	}
	
	@Test(groups = "SCP")
	public void TS112545_Administracion_de_Contexto_Sectorial_Filtros_Sector() {
		SCP pcp = new SCP(driver);
		boolean filtroSirve = false;
		
		pcp.moveToElementOnAccAndClick("quintoTitulo",1);
		Select listSelect = new Select(driver.findElement(By.className("panel-body")).findElement(By.tagName("select")));
		listSelect.selectByVisibleText("Entretenimiento");
		driver.findElement(By.className("panel-body")).findElement(By.cssSelector(".btn.btn.btn-default.btn-sm")).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> servicioList = driver.findElement(By.cssSelector(".table.table-striped.table-bordered.table-condensed")).findElements(By.tagName("tr"));
		servicioList.remove(0);
		for (WebElement UnaC : servicioList) {
			if ((UnaC.findElements(By.tagName("td")).get(2).getText().toLowerCase().contains("contexto general"))&&(UnaC.findElements(By.tagName("td")).get(3).getText().toLowerCase().contains("probando filtro sector"))&&(UnaC.findElements(By.tagName("td")).get(4).getText().toLowerCase().contains("probando filtro sector"))) {
				filtroSirve = true;
				break;
			}
		}
		assertTrue(filtroSirve);
	}
	
	@Test(groups = "SCP")
	public void TS112547_Administracion_de_Contexto_Sectorial_Ingreso_Desde_el_Contacto() {
		SCP pcp = new SCP(driver);
		pcp.moveToElementOnAccAndClick("quintoTitulo",1);
		//picklist sector
		assertTrue(driver.findElement(By.className("panel-body")).findElement(By.tagName("select")).isDisplayed());
		//boton seleccionar
		assertTrue(driver.findElement(By.className("panel-body")).findElement(By.cssSelector(".btn.btn.btn-default.btn-sm")).getAttribute("Value").equals("Seleccionar")&&driver.findElement(By.className("panel-body")).findElement(By.cssSelector(".btn.btn.btn-default.btn-sm")).getAttribute("type").equals("button"));
		List<WebElement> Itabla = driver.findElement(By.cssSelector(".table.table-striped.table-bordered.table-condensed")).findElement(By.tagName("tr")).findElements(By.tagName("th"));
		//columna borrar
		assertTrue(Itabla.get(0).isDisplayed());
		//columna id
		assertTrue(Itabla.get(1).getText().toLowerCase().equals("id"));
		//columna tipo
		assertTrue(Itabla.get(2).getText().toLowerCase().equals("tipo"));
		//columna titulo
		assertTrue(Itabla.get(3).getText().toLowerCase().equals("título"));
		//columna descripcion
		assertTrue(Itabla.get(4).getText().toLowerCase().equals("descripción"));
	}

	@Test(groups = "SCP")
	public void TS112541_Administracion_de_Contexto_Sectorial_Borrar_Guardando() {
		SCP pcp = new SCP(driver);
		BasePage Bp = new BasePage();
		String sector = new String();
		List<WebElement> servicioList = driver.findElement(By.className("detailList")).findElements(By.tagName("tr"));
		servicioList.remove(0);
		for (WebElement UnS : servicioList) {
			if (UnS.findElements(By.tagName("td")).get(2).getText().toLowerCase().contains("segmento vertical")) {
				sector = UnS.findElements(By.tagName("td")).get(3).getText();
				System.out.println("Sector: "+sector);
				break;
			}	
		}
		pcp.moveToElementOnAccAndClick("quintoTitulo",1);
		Select listSelect = new Select(driver.findElement(By.className("panel-body")).findElement(By.tagName("select")));
		listSelect.selectByVisibleText(sector);
		driver.findElement(By.className("panel-body")).findElement(By.cssSelector(".btn.btn.btn-default.btn-sm")).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		servicioList = driver.findElement(By.cssSelector(".table.table-striped.table-bordered.table-condensed")).findElements(By.tagName("tr"));
		servicioList.remove(0);
		for (WebElement UnaC : servicioList) {
			if ((UnaC.findElements(By.tagName("td")).get(2).getText().toLowerCase().contains("contexto general"))&&(UnaC.findElements(By.tagName("td")).get(3).getText().toLowerCase().contains("contexto sectorial automatizado"))&&(UnaC.findElements(By.tagName("td")).get(4).getText().toLowerCase().contains("contexto sectorial automatizado"))) {
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+UnaC.findElements(By.tagName("td")).get(0).getLocation().y+")");
				UnaC.findElements(By.tagName("td")).get(0).click();
				break;
			}	
		}
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		servicioList = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		WebElement subir = driver.findElement(By.id("userNavLabel"));
		  ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+subir.getLocation().y+")");
		for (WebElement UnS : servicioList) {
			if (UnS.getText().toLowerCase().contains("guardar")) {
				UnS.click();
				break;
			}	
		}
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		SCP pScp = new SCP(driver);
		pScp.clickOnTabByName("cuentas");
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		pScp.listTypeAcc("Todas Las cuentas");
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		pScp.clickOnFirstAccRe();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		driver.switchTo().frame(Bp.getFrameForElement(driver, By.id("primerTitulo")));
		pcp.moveToElementOnAccAndClick("primerTitulo",1);
		driver.switchTo().defaultContent();
		driver.findElement(By.id("hidden-Con")).click();
		boolean enc = true;
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		servicioList = driver.findElement(By.cssSelector(".table.table-striped.table-bordered.table-condensed")).findElements(By.tagName("tr"));
		servicioList.remove(0);
		for (WebElement UnaC : servicioList) {
			if ((UnaC.findElements(By.tagName("td")).get(1).getText().toLowerCase().contains("contexto sectorial automatizado"))&&(UnaC.findElements(By.tagName("td")).get(2).getText().toLowerCase().contains("contexto sectorial automatizado"))) {
				enc = false;
			}	
		}
		assertTrue(enc);
		
	}
	
	@Test(groups = "SCP")
	public void TS112548_Administracion_de_Servicios_Borrar_Categoria_del_Servicio() {
		SCP pcp = new SCP(driver);
		pcp.moveToElementOnAccAndClick("quintoTitulo",2);
		if (driver.findElement(By.className("panel-body")).findElement(By.tagName("h3")).getText().contains("Servicio automatizado")) {
			driver.findElement(By.className("panel-body")).findElement(By.cssSelector(".btn.btn.btn-default.btn-sm")).click();
		}
	
		boolean enc = true;
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> servicioList = driver.findElement(By.className("panel-body")).findElements(By.tagName("h3"));
		for (WebElement UnaC : servicioList) {
			if (UnaC.getText().contains("Servicio automatizado")) {
				enc = false;
			}	
		}
		assertTrue(enc);
	}
	
	@Test(groups = "SCP")
	public void TS112551_Administracion_de_Servicios_Borrar_Servicio() {
		SCP pcp = new SCP(driver);
		pcp.moveToElementOnAccAndClick("quintoTitulo",2);
		boolean enc = true;
		List<WebElement> servicioList = driver.findElement(By.cssSelector(".table.table-striped.table-bordered.table-condensed")).findElements(By.tagName("tr"));
		servicioList.remove(0);
		for (WebElement Uno : servicioList) {
			if (Uno.findElements(By.tagName("td")).get(1).getText().equals("Prueba automatizada")) {
				Uno.findElements(By.tagName("td")).get(0).click();
				break;
			}	
		}
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		servicioList = driver.findElement(By.cssSelector(".table.table-striped.table-bordered.table-condensed")).findElements(By.tagName("tr"));
		servicioList.remove(0);
		for (WebElement Uno : servicioList) {
			if (Uno.findElements(By.tagName("td")).get(1).getText().equals("Prueba automatizada")) {
				enc = false;
			}	
		}
		assertTrue(enc);
	}
	
	@Test(groups = "SCP")
	public void TS112554_Administracion_de_Servicios_Creacion_Crear_Categoria_de_Servicio() {
		SCP pcp = new SCP(driver);
		pcp.moveToElementOnAccAndClick("quintoTitulo",2);
		List<WebElement> servicioList = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement UnS : servicioList) {
			if (UnS.getText().contains("Crear Categoría de Servicio")) {
				UnS.click();
				break;
			}	
		}
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElements(By.className("modal-body")).get(0).findElement(By.tagName("input")).sendKeys("Servicio automatizado");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElements(By.className("modal-footer")).get(0).findElement(By.cssSelector(".btn.btn-primary")).click();
		boolean enc = false;
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		servicioList = driver.findElements(By.tagName("h3"));
		for (WebElement UnaC : servicioList) {
			if (UnaC.getText().contains("Servicio automatizado")) {
				enc = true;
			}	
		}
		assertTrue(enc);
		
	}
	
	@Test(groups = "SCP")
	public void TS112556_Administracion_de_Servicios_Creacion_Crear_Servicio() {
		SCP pcp = new SCP(driver);
		pcp.moveToElementOnAccAndClick("quintoTitulo",2);
		List<WebElement> servicioList = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement UnS : servicioList) {
			if (UnS.getText().contains("Nuevo Servicio")) {
				UnS.click();
				break;
			}	
		}
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElements(By.className("modal-body")).get(1).findElement(By.tagName("input")).sendKeys("Prueba automatizada");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElements(By.className("modal-footer")).get(1).findElement(By.cssSelector(".btn.btn-primary")).click();
		boolean enc = false;
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		servicioList = driver.findElement(By.cssSelector(".table.table-striped.table-bordered.table-condensed")).findElements(By.tagName("tr"));
		servicioList.remove(0);
		for (WebElement Uno : servicioList) {
			if (Uno.findElements(By.tagName("td")).get(1).getText().equals("Prueba automatizada")) {
				enc = true;
			}	
		}
		assertTrue(enc);

	}
	
	@Test(groups = "SCP")
	public void TS112557_Administracion_de_Servicios_Ingreso_Desde_El_Contacto() {
		SCP pcp = new SCP(driver);
		pcp.moveToElementOnAccAndClick("quintoTitulo",2);
		boolean enc = true;
		boolean ccs = false;
		boolean ns = false;
		boolean bbs = false;
		List<WebElement> servicioList = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement UnS : servicioList) {
			if (UnS.getText().contains("Nuevo Servicio")) {
				ns = true;
			}else {
				if (UnS.getText().contains("Crear Categoría de Servicio")) {
					ccs = true;
				}
			}
		}
		assertTrue(ns&&ccs);
		enc = driver.findElement(By.className("panel-body")).findElement(By.tagName("h3")).isDisplayed();
		assertTrue(enc);
		servicioList = driver.findElement(By.cssSelector(".table.table-striped.table-bordered.table-condensed")).findElements(By.tagName("tr"));
		enc= servicioList.get(0).findElements(By.tagName("th")).get(1).getText().contains("Servicio"); 
		assertTrue(enc);
		servicioList.remove(0);
		for (WebElement Uno : servicioList) {
			if (Uno.findElements(By.tagName("td")).get(0).isEnabled()) {
				bbs = true;
			}	
		}
		assertTrue(bbs);
	}
	
	@Test(groups = "SCP")
	public void TS112576_Configurar_Reporte_SCP_Exportar_a_Word() {
		SCP pcp = new SCP(driver);
		pcp.moveToElementOnAccAndClick("cuartoTitulo",3);
		String usuario = driver.findElements(By.cssSelector(".nav.navbar-nav.navbar-right")).get(1).findElement(By.tagName("a")).getText();
		List<WebElement> servicioList = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement UnS : servicioList) {
			if (UnS.getText().toLowerCase().contains("export to word")||UnS.getText().toLowerCase().contains("exportar a word")) {
				UnS.click();
				break;
			}
		}
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		usuario=usuario.replace(' ', '_');
		usuario=usuario.concat("-Configurar_Reporte_SCP.doc");
		assertTrue(pcp.isFileDownloaded(downloadPath, usuario), "Failed to download Expected document");
	}
	
	@Test(groups = "SCP")
	public void TS112577_Configurar_Reporte_SCP_Guardar() {
		SCP pcp = new SCP(driver);
		boolean botonG = false;
		pcp.moveToElementOnAccAndClick("cuartoTitulo",3);
		List<WebElement> servicioList = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement UnS : servicioList) {
			if (UnS.getText().toLowerCase().contains("guardar")) {
				botonG = true;
				UnS.click();
			}
		}
		assertTrue(botonG);
		System.out.println("Se debe verificar que mas hacer!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	
	@Test(groups = "SCP")
	public void TS112578_Configurar_Reporte_SCP_Ingreso_Desde_El_Contacto() {
		SCP pcp = new SCP(driver);
		boolean botonG= false;
		boolean botonW = false;
		pcp.moveToElementOnAccAndClick("cuartoTitulo",3);
		List<WebElement> servicioList = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement UnS : servicioList) {
			if (UnS.getText().toLowerCase().contains("export to word")||UnS.getText().toLowerCase().contains("exportar a word")) {
				botonW = true;
			}else {
				if (UnS.getText().toLowerCase().contains("guardar")) {
					botonG = true;
				}
			}
		}
		assertTrue(botonW&&botonG);
		servicioList = driver.findElement(By.className("panel-body")).findElements(By.className("h1"));
		List<WebElement> reportList = driver.findElement(By.className("panel-body")).findElements(By.cssSelector(".table.table-striped.table-bordered.table-condensed"));
		assertTrue(servicioList.size()<=reportList.size());
		
	}
	
	@Test(groups = "SCP")
	public void TS112579_Configurar_Reporte_SCP_Ver_Video() {
		SCP pcp = new SCP(driver);
		boolean botonG = false;
		pcp.moveToElementOnAccAndClick("cuartoTitulo",3);
		List<WebElement> botones = driver.findElements(By.cssSelector(".btn.btn-xs.btn-default"));
		for (WebElement UnS : botones) {
			if (UnS.getText().toLowerCase().contains("ver video")) {
				botonG = true;
				UnS.click();
				try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			}
		}
		assertTrue(botonG);
		ArrayList<String> allTabs = new ArrayList<String>(driver.getWindowHandles());
	    driver.switchTo().window(allTabs.get(1));
		assertTrue(driver.findElement(By.id("player-container")).isDisplayed());
		driver.close();
		driver.switchTo().window(allTabs.get(0));
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	@Test(groups = "SCP")
	public void TS112631_Estrategia_De_Crecimiento_Exportar_A_Excel() {
		SCP pcp = new SCP(driver);
		pcp.moveToElementOnAccAndClick("tercerTitulo",5);
		String usuario = driver.findElements(By.cssSelector(".nav.navbar-nav.navbar-right")).get(1).findElement(By.tagName("a")).getText();
		List<WebElement> servicioList = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement UnS : servicioList) {
			if (UnS.getText().toLowerCase().contains("export to excel")||UnS.getText().toLowerCase().contains("exportar a excel")) {
				UnS.click();
				break;
			}
		}
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		usuario=usuario.replace(' ', '_');
		usuario=usuario.concat("-Estrategia_de_Crecimiento.xls");
		assertTrue(pcp.isFileDownloaded(downloadPath, usuario), "Failed to download Expected document");
	}
	
	@Test(groups = "SCP")
	public void TS112635_Estrategia_De_Crecimiento_Search() {
		SCP pcp = new SCP(driver);
		boolean filtroBien = true;
		boolean estaBuscado = false;
		pcp.moveToElementOnAccAndClick("tercerTitulo",5);
		List<WebElement> primeros = driver.findElement(By.id("mainTable")).findElements(By.tagName("tr"));
		primeros.remove(0);
		String textoBuscar = primeros.get(0).findElement(By.tagName("td")).getText();
		driver.findElement(By.id("mainTable_filter")).findElement(By.tagName("input")).sendKeys(textoBuscar);
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		primeros = driver.findElement(By.id("mainTable")).findElements(By.tagName("tr"));
		primeros.remove(0);
		for (WebElement uno : primeros) {
			if (uno.getAttribute("class").equals("StrategicInitiativeRow DraggableRow dataRow hasPotentialBussiness odd") || (uno.getAttribute("class").equals("StrategicInitiativeRow DraggableRow dataRow hasPotentialBussiness even"))) {
				if(uno.findElement(By.tagName("td")).getText().equals(textoBuscar)) 
					estaBuscado = true;
				else 
					filtroBien = false;
			}
		}
		assertTrue(estaBuscado&&filtroBien);
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	@Test(groups = "SCP")
	public void TS112637_Estrategia_De_Crecimiento_Ver_Video() {
		SCP pcp = new SCP(driver);
		boolean botonG = false;
		pcp.moveToElementOnAccAndClick("tercerTitulo",5);
		List<WebElement> botones = driver.findElements(By.cssSelector(".btn.btn-xs.btn-default"));
		for (WebElement UnS : botones) {
			if (UnS.getText().toLowerCase().contains("ver video")) {
				botonG = true;
				UnS.click();
				try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			}
		}
		assertTrue(botonG);
		ArrayList<String> allTabs = new ArrayList<String>(driver.getWindowHandles());
	    driver.switchTo().window(allTabs.get(1));
	    driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
	    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".html5-video-player.unstarted-mode.ytp-hide-controls")).getLocation().y+")");
		assertTrue(driver.findElement(By.cssSelector(".html5-video-player.unstarted-mode.ytp-hide-controls")).isDisplayed());
		assertTrue(driver.findElement(By.cssSelector(".ytp-title-link.yt-uix-sessionlink")).getText().toLowerCase().contains("strategic client plan"));
		assertTrue(driver.findElement(By.cssSelector(".ytp-title-link.yt-uix-sessionlink")).getAttribute("href").contains("youtube"));
		driver.close();
		driver.switchTo().window(allTabs.get(0));
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	@Test(groups = "SCP")
	public void TS112788_Parque_De_Servicios_Ver_Video() {
		SCP pcp = new SCP(driver);
		boolean botonG = false;
		pcp.moveToElementOnAccAndClick("segundoTitulo",2);
		List<WebElement> botones = driver.findElements(By.cssSelector(".btn.btn-xs.btn-default"));
		for (WebElement UnS : botones) {
			if (UnS.getText().toLowerCase().contains("ver video")) {
				botonG = true;
				UnS.click();
				try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			}
		}
		assertTrue(botonG);
		ArrayList<String> allTabs = new ArrayList<String>(driver.getWindowHandles());
	    driver.switchTo().window(allTabs.get(1));
		assertTrue(driver.findElement(By.id("player-container")).isDisplayed());
		driver.close();
		driver.switchTo().window(allTabs.get(0));
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	@Test(groups = "SCP")
	public void TS112787_Parque_De_Servicios_Ingreso_Desde_El_Contacto() {
		SCP pcp = new SCP(driver);
		List<String> tdo = new ArrayList<String>();
		String[] todos = {"servicios","editar estado","proveedor actual","gastos anuales","tomar acción antes de", "compra a nivel","decision maker","otra información"};
		pcp.moveToElementOnAccAndClick("segundoTitulo",2);
		List<WebElement> servicioList = driver.findElement(By.xpath("//*[@id='tableList']/tbody/tr[2]")).findElements(By.tagName("th")); 
		for (int i = 0 ; i<=7 ; i++) {
			tdo.add(todos[i]);
		}
		assertTrue(servicioList.get(0).getText().isEmpty());
		servicioList.remove(0);
		for (WebElement UnS : servicioList) {
			if(!UnS.getText().equals("Pais")) {
				assertTrue(tdo.contains(UnS.getText().toLowerCase()));
			}
		}
	}
	
	@Test(groups = "SCP")
	public void TS112791_Plan_de_accion_Doble_Click_Para_Editar() {
		SCP pcp = new SCP(driver);
		List<String> tdo = new ArrayList<String>();
		pcp.Desloguear_Loguear_Comentar("admin", "fabiana", "Mati <3 Nico", "cuartoTitulo", 2);
		/*pcp.moveToElementOnAccAndClick("segundoTitulo",2);
		List<WebElement> servicioList = driver.findElement(By.xpath("//*[@id='tableList']/tbody/tr[2]")).findElements(By.tagName("th")); 
		for (int i = 0 ; i<=7 ; i++) {
			tdo.add(todos[i]);
		}
		assertTrue(servicioList.get(0).getText().isEmpty());
		servicioList.remove(0);
		for (WebElement UnS : servicioList) {
			if(!UnS.getText().equals("Pais")) {
				assertTrue(tdo.contains(UnS.getText().toLowerCase()));
			}
		}*/
	}
	
	
}
