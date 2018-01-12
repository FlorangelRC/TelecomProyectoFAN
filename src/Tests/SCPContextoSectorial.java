package Tests;

import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.CustomerCare;
import Pages.SCP;
import Pages.setConexion;

public class SCPContextoSectorial extends TestBase {

	private WebDriver driver;
	private static String downloadPath = "C:\\Users\\Nicolas\\Downloads";
	
	
	@BeforeClass(groups = "SCP")
	  public void Init() throws Exception {
	    this.driver = setConexion.setupEze();
	    loginSCPAdmin(driver);
	    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	  }
	
	@BeforeMethod(groups = "SCP")
	  public void setUp() throws Exception {
	    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    SCP prueba= new SCP(driver);
	    //prueba.goToMenu("SCP");
	    prueba.clickOnTabByName("cuentas");
	    prueba.clickOnFirstAccRe();
	    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
	}
	
	@AfterMethod(groups = "SCP")
	public void after(){
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("home_Tab")).getLocation().y+")");
		driver.findElement(By.id("home_Tab")).click();
	}
	
	@AfterClass(groups = "SCP")
	public void teardown() {
		driver.quit();
		sleep(5000);
	}
	
	
	@Test(groups = "SCP")
	public void TS112613_Cronograma_de_cuenta_Agregar_Vencimiento_Contrato_del_Servicio() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("cuartoTitulo", 1);
		List <WebElement> checkbox = driver.findElements(By.className("checkboxFiltroTimeLine"));
		checkbox.get(1).click();
		driver.findElement(By.id("j_id0:j_id91:j_id111")).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(driver.findElement(By.className("tl-message-full")).isDisplayed());		
	}
	
	@Test(groups = "SCP")
	public void TS112626_Cronograma_de_cuenta_Filtros_Vencimiento_Contrato_del_Servicio(){
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("cuartoTitulo", 1);
		List <WebElement> checkbox = driver.findElements(By.className("checkboxFiltroTimeLine"));
		checkbox.get(1).click();
		driver.findElement(By.id("j_id0:j_id91:j_id111")).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(driver.findElement(By.className("tl-timenav-slider-background")).isDisplayed());
	}
	
	@Test(groups = "SCP")
	public void TS112627_Cronograma_de_Cuenta_Ingreso_Desde_el_contacto() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("cuartoTitulo", 1);
		List <WebElement> checkbox = driver.findElements(By.className("checkboxFiltroTimeLine"));
		checkbox.get(1).click();
		driver.findElement(By.id("j_id0:j_id91:j_id111")).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(driver.findElement(By.className("tl-message-full")).isDisplayed() 
						  && driver.findElement(By.className("tl-timenav-slider-background")).isDisplayed());
	}
	
	@Test(groups = "SCP")
	public void TS112594_Contexto_Sectorial_Ingreso_Desde_Acerca_del_cliente() {
		SCP prueba= new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 1);
		Assert.assertTrue(driver.findElement(By.id("hidden-Con")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("hidden-Mét")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("hidden-Pla")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("hidden-Cad")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("hidden-Ten")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("hidden-Cas")).isDisplayed());
	}
	
	@Test(groups = "SCP")
	public void TS112595_Contexto_Sectorial_Ingreso_Desde_el_contacto() {
		SCP prueba= new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 1);		
		Assert.assertTrue(driver.findElement(By.id("hidden-Con")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("hidden-Mét")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("hidden-Pla")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("hidden-Cad")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("hidden-Ten")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("hidden-Cas")).isDisplayed());
	}
	
	@Test(groups = "SCP")
	public void TS112633_Estrategia_de_Crecimiento_Ingreso_Desde_el_contacto() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 5);

		boolean check=true;
	    String[] datosOp = {"título", "descripción", "tipo", "origen", "negocio potencial"};
	    List<String> titleTabla = new ArrayList<String>();
	    WebElement oportunidad = driver.findElement(By.id("mainTable")).findElement(By.className("headerRow"));
	    List<WebElement> composicion= oportunidad.findElements(By.tagName("th"));	    
	    for(WebElement a : composicion) {
	      titleTabla.add(a.getText().toLowerCase());
	      //System.out.println(a.getText());//Para Verificar que este imprimiendo el texto que buscamos
	    }	    
	    for(String a:datosOp) {
	    	if(!(titleTabla.contains(a)))
	    		check=false;
	    }
	    Assert.assertTrue(check);
	}
	
	@Test(groups = "SCP")
	public void TS112678_Hitos_Relevantes_Nuevo_Hito_Relevante() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("segundoTitulo", 3);
		WebElement element = driver.findElement(By.cssSelector(".data2Col.last")).findElement(By.cssSelector(".btn.btn-default.btn-sm"));
		element.click();
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(driver.findElement(By.className("modal-content")).isDisplayed());
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.className("modal-footer")).findElement(By.cssSelector(".btn.btn-default")).click();
		
	}
	
	@Test(groups = "SCP")
	public void TS112742_Negocio_del_Cliente_Exportar_a_Excel() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 2);
		String usuario = driver.findElement(By.cssSelector(".nav.navbar-nav.navbar-right")).findElement(By.tagName("a")).getText();
		List <WebElement> element = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("exportar a excel")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		usuario=usuario.replace(' ', '_');
		usuario=usuario.concat("-Negocio_del_Cliente.xls");
		assertTrue(prueba.isFileDownloaded(downloadPath, usuario), "Failed to download Expected document");
	}
	
	@Test(groups = "SCP")
	public void TS112744_Negocio_del_cliente_Ingreso_Desde_el_contacto() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 2);
		Assert.assertTrue(driver.findElement(By.id("hidden-descripcionCliente")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("hidden-strategicContext")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("hidden-strategicIniciative")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("hidden-mainCompetitors")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("hidden-csat")).isDisplayed());
	}
	
	@Test(groups = "SCP")
	public void TS112745_Negocio_del_Cliente_Principales_competidores_del_cliente() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 2);
		driver.findElement(By.id("hidden-mainCompetitors")).click();
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(driver.findElement(By.cssSelector(".hiddenTable.hidden-mainCompetitors")).isDisplayed());
	}
	
	@Test(groups = "SCP")
	public void TS112802_Share_of_Wallet_Exportar_a_Excel() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("segundoTitulo", 1);
		String usuario = driver.findElement(By.cssSelector(".nav.navbar-nav.navbar-right")).findElement(By.tagName("a")).getText();
		List<WebElement> element = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("exportar a excel")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		usuario=usuario.replace(' ', '_');
		usuario=usuario.concat("-Share_of_Wallet.xls");
		assertTrue(prueba.isFileDownloaded(downloadPath, usuario), "Failed to download Expected document");
	}
	
	@Test(groups = "SCP")
	public void TS112804_Share_of_Wallet_Ingreso_Desde_el_contacto() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("segundoTitulo", 1);
		
		boolean check=true;
	    String[] datosOp = {"ytd", "año anterior", "año anterior -1"};
	    List<String> titleTabla = new ArrayList<String>();
	    WebElement oportunidad = driver.findElement(By.id("j_id0:Form:pageContent")).findElement(By.cssSelector(".table.table-striped.table-bordered.table-condensed"));
	    List<WebElement> composicion= oportunidad.findElements(By.tagName("th"));	    
	    for(WebElement a : composicion) {
	      titleTabla.add(a.getText().toLowerCase());
	      //System.out.println(a.getText());//Para Verificar que este imprimiendo el texto que buscamos
	    }	    
	    for(String a:datosOp) {
	    	if(!(titleTabla.contains(a)))
	    		check=false;
	    }
	    Assert.assertTrue(check);
	}
	
	@Test(groups = "SCP")
	public void TS112703_Mosaico_de_Relacionamiento_General_Ingreso_Desde_el_contacto() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("segundoTitulo", 4);

		boolean check=true;
	    String[] datosOp = {"rol", "actitud", "autoridad", "influencia", "relacionamiento con la competencia", "generación"};
	    List<String> titleTabla = new ArrayList<String>();
	    WebElement oportunidad = driver.findElement(By.id("j_id0:j_id139")).findElement(By.cssSelector(".table.table-striped.table-bordered.table-condensed"));
	    List<WebElement> composicion= oportunidad.findElements(By.tagName("th"));	    
	    for(WebElement a : composicion) {
	      titleTabla.add(a.getText().toLowerCase());
	      //System.out.println(a.getText());//Para Verificar que este imprimiendo el texto que buscamos
	    }	    
	    for(String a:datosOp) {
	    	if(!(titleTabla.contains(a)))
	    		check=false;
	    }
	    Assert.assertTrue(check);
	}
	
	@Test(groups = "SCP")
	public void TS112720_Mosaico_de_Relacionamiento_por_Oportunidad_Enviar() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("segundoTitulo", 4);
		driver.findElement(By.cssSelector(".btn.btnPrimary.publishersharebutton.btn.btn-default.btn-sm")).click();
	}
	
	@Test(groups = "SCP")
	public void TS112721_Mosaico_de_Relacionamiento_por_Oportunidad_Ingreso_Desde_el_contacto() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 3);
		List<WebElement> element = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("ir al mosaico")) {
					Assert.assertTrue(x.isDisplayed());
			}
		}  
		boolean check=true;
	    String[] datosOp = {"nombre de la oportunidad", "importe", "probabilidad (%)"};
	    List<String> titleTabla = new ArrayList<String>();
	    WebElement oportunidad = driver.findElement(By.id("j_id0:pageContent")).findElement(By.cssSelector(".table.table-striped.table-bordered.table-condensed.dataTable"));
	    List<WebElement> composicion= oportunidad.findElements(By.tagName("th"));	    
	    for(WebElement a : composicion) {
	      titleTabla.add(a.getText().toLowerCase());
	      //System.out.println(a.getText());//Para Verificar que este imprimiendo el texto que buscamos
	    }	    
	    for(String a:datosOp) {
	    	if(!(titleTabla.contains(a)))
	    		check=false;
	    }
	    Assert.assertTrue(check);
	}
	
	@Test(groups = "SCP")
	public void TS112753_Opportunity_Snapshot_Ingreso_Desde_el_contacto() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 4);
		List<WebElement> element = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("ir al snapshot")) {
					Assert.assertTrue(x.isDisplayed());
			}
		}  
		boolean check=true;
		String[] datosOp = {"nombre de la oportunidad", "importe", "probabilidad (%)", "etapa"};
		List<String> titleTabla = new ArrayList<String>();
		WebElement oportunidad = driver.findElement(By.id("j_id0:pageContent")).findElement(By.id("mainTable_wrapper"));
		List<WebElement> composicion= oportunidad.findElements(By.tagName("th"));	    
		for(WebElement a : composicion) {
			titleTabla.add(a.getText().toLowerCase());
			//System.out.println(a.getText());//Para Verificar que este imprimiendo el texto que buscamos
		}	    
		for(String a:datosOp) {
			if(!(titleTabla.contains(a)))
				check=false;
		}
		Assert.assertTrue(check);	  
	}
	
	@Test(groups = "SCP")
	public void TS112754_Opportunity_Snapshot_Ir_al_Snapshot() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 4);
		List <WebElement> element = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("ir al snapshot")) {
					x.click();
					break;
			}
		}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement oportunidad = driver.findElement(By.className("panel-body"));
		Assert.assertTrue(oportunidad.getText().contains("Oportunidad: Oportunidad"));
	}
	
	@Test(groups = "SCP")
	public void TS112756_Opportunity_Snapshot_Nombre_de_la_oportunidad() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 4);
		driver.findElement(By.xpath("//*[@id=\"mainTable\"]/tbody/tr[1]/td[2]/a")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(driver.findElement(By.id("bodyCell")).isDisplayed());
	}
	
	@Test(groups = "SCP")
	public void TS112763_Organigrama_y_mapa_de_influencia_Descargar_Imagen() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 3);
		List<WebElement> element = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("ver organigrama / mapa de influencia")) {
					x.click();
					break;
			}
		}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> descarga = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm.generateImg"));
		for (WebElement x : descarga) {
			if (x.getText().toLowerCase().contains("descargar imagen")) {
					x.click();
					break; 
			}
		}	
	}
	
	@Test(groups = "SCP")
	public void TS112766_Organigrama_y_mapa_de_Influencia_Ingreso_Desde_el_contacto() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 3);
		Assert.assertTrue(driver.findElement(By.className("jOrgChart")).isDisplayed());	
	}
	
	@Test(groups = "SCP")
	public void TS112765_Organigrama_y_mapa_de_influencia_Guardar_cambios() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 3);
		List<WebElement> organigrama = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : organigrama) {
			if (x.getText().toLowerCase().contains("ver organigrama / mapa de influencia")) {
					x.click();
					break; 
			}
		}	
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> guardar = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm.save"));
		for (WebElement x : guardar) {
			if (x.getText().toLowerCase().contains("guardar cambios")) {
					x.click();
					break; 
			}
		}	
	}
	
	@Test(groups = "SCP")
	public void TS112764_Organigrama_y_mapa_de_influencia_Guardar() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 3);
		List<WebElement> organigrama = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : organigrama) {
			if (x.getText().toLowerCase().contains("ver organigrama / mapa de influencia")) {
					x.click();
					break; 
			}
		}	
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> guardar = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm.save"));
		for (WebElement x : guardar) {
			if (x.getText().toLowerCase().contains("guardar cambios")) {
					x.click();
					break; 
			}
		}
	}
	
	@Test(groups = "SCP")
	public void TS112592_Contexto_Sectorial_Exportar_a_Excel() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 1);
		String usuario = driver.findElement(By.cssSelector(".nav.navbar-nav.navbar-right")).findElement(By.tagName("a")).getText();
		List <WebElement> element = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("exportar a excel")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		usuario=usuario.replace(' ', '_');
		usuario=usuario.concat("-Contexto_Sectorial.xls");
		assertTrue(prueba.isFileDownloaded(downloadPath, usuario), "Failed to download Expected document");
	}
	
	@Test(groups = "SCP")
	public void TS112751_Opportunity_Snapshot_Chatter_contextualizado_Leer_comentario_escrito_con_otro_usuario() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 4);
		List <WebElement> cuentas = driver.findElements(By.cssSelector(".cxfeeditem.feeditem"));
		boolean a = false;
		for (WebElement x : cuentas) {
			if (!(x.getText().toLowerCase().contains("fabiana vaccotti"))) {
				if (x.getText().toLowerCase().contains(" ")) {
					a = true;
				}
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test(groups = "SCP")
	public void TS112755_Opportunity_Snapshot_Ir_al_Snapshot_Ingreso() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 4);
		List <WebElement> snapshot = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		snapshot.get(0).click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> element = driver.findElements(By.cssSelector(".pbSubheader.brandTertiaryBgr.first.tertiaryPalette"));
		Assert.assertTrue(element.get(0).getText().contains("Campos de la Oportunidad"));
		Assert.assertTrue(element.get(1).getText().contains("Soluciones Para el Sector"));
		Assert.assertTrue(element.get(2).getText().contains("Value Drivers"));
		Assert.assertTrue(element.get(3).getText().contains("Propuesta de Valor"));
		Assert.assertTrue(element.get(4).getText().contains("Mosaico de Relacionamiento por Oportunidad"));
		Assert.assertTrue(element.get(5).getText().contains("Criterios de Decisión por Oportunidad"));		
	}
	
	@Test(groups = "SCP")
	public void TS112757_Opportunity_Snapshot_Search() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 4);
		String a = "oportunidad";
		driver.findElement(By.xpath("//*[@id=\"mainTable_filter\"]/label/input")).sendKeys(a);
		WebElement element = driver.findElement(By.xpath("//*[@id=\"mainTable\"]/tbody/tr[1]/td[2]"));
		Assert.assertTrue(element.getText().toLowerCase().contains(a));
	}
	
	@Test(groups = "SCP")
	public void TS112759_Opportunity_Snapshot_Ver_video() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 4);
		driver.findElement(By.cssSelector(".btn.btn-xs.btn-default")).click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
		Assert.assertTrue(driver.findElement(By.cssSelector(".video-stream.html5-main-video")).isDisplayed());
		driver.close();
	    driver.switchTo().window(tabs2.get(0));
	}
	
	@Test(groups = "SCP")
	public void TS112762_Organigrama_y_mapa_de_influencia_Chatter_contextualizado_Leer_comentario_escrito_con_otro_usuario() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 3);
		List <WebElement> cuentas = driver.findElements(By.cssSelector(".cxfeeditem.feeditem"));
		boolean a = false;
		for (WebElement x : cuentas) {
			if (!(x.getText().toLowerCase().contains("fabiana vaccotti"))) {
				if (x.getText().toLowerCase().contains(" ")) {
					a = true;
				}
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test(groups = "SCP")
	public void TS112768_Organigrama_y_mapa_de_influencia_Search() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 3);
		String a = "javier";
		driver.findElement(By.xpath("//*[@id=\"mainTable_filter\"]/label/input")).sendKeys(a);
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(driver.findElement(By.className("odd")).getText().toLowerCase().contains(a));
	}
	
	@Test(groups = "SCP")
	public void TS112769_Organigrama_y_mapa_de_influencia_Ver_Video() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 3);
		driver.findElement(By.cssSelector(".btn.btn-xs.btn-default")).click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
		Assert.assertTrue(driver.findElement(By.cssSelector(".video-stream.html5-main-video")).isDisplayed());
		driver.close();
	    driver.switchTo().window(tabs2.get(0));		
	}
	
	@Test(groups = "SCP")
	public void TS112771_Organigrama_y_mapa_de_influencia_zoom() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 3);
		Assert.assertTrue(driver.findElement(By.id("zoomOut")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("zoomIn")).isDisplayed());
	}
	
	@Test(groups = "SCP")
	public void TS112588_Contexto_Sectorial_Chatter_contextualizado_Leer_comentario_escrito_con_otro_usuario() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 1);
		List <WebElement> cuentas = driver.findElements(By.cssSelector(".cxfeeditem.feeditem"));
		boolean a = false;
		for (WebElement x : cuentas) {
			if (!(x.getText().toLowerCase().contains("fabiana vaccotti"))) {
				if (x.getText().toLowerCase().contains(" ")) {
					a = true;
				}
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test(groups = "SCP")
	public void TS112801_Share_of_Wallet_Chatter_contextualizado_Leer_comentario_escrito_con_otro_usuario() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("segundoTitulo", 1);
		List <WebElement> cuentas = driver.findElements(By.cssSelector(".cxfeeditem.feeditem"));
		boolean a = false;
		for (WebElement x : cuentas) {
			if (!(x.getText().toLowerCase().contains("fabiana vaccotti"))) {
				if (x.getText().toLowerCase().contains(" ")) {
					a = true;
				}
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test(groups = "SCP")
	public void TS112805_Share_of_Wallet_Ver_Video() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("segundoTitulo", 1);
		driver.findElement(By.cssSelector(".btn.btn-xs.btn-default")).click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
		Assert.assertTrue(driver.findElement(By.cssSelector(".video-stream.html5-main-video")).isDisplayed());
		driver.close();
	    driver.switchTo().window(tabs2.get(0));
	}
	
	@Test(groups = "SCP")
	public void TS112803_Share_of_Wallet_Guardar() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("segundoTitulo", 1);
		Actions action = new Actions(driver);
		List <WebElement> element = driver.findElements(By.className("inlineEditWrite"));
		String a = "250";
		action.doubleClick(element.get(0)).perform();
		driver.findElement(By.xpath("//*[@id=\"j_id0_Form_j_id122\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"j_id0_Form_j_id122\"]")).sendKeys(a);
		driver.findElement(By.xpath("//*[@id=\"j_id0_Form_j_id122\"]")).sendKeys("\uE007");
		List <WebElement> b = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : b) {
			if (x.getText().toLowerCase().contains("guardar")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}		
		int cant = 0;
		while (cant < 2) {
			try {
				Assert.assertTrue(element.get(0).getText().contains(a));
				break;
			} catch(org.openqa.selenium.StaleElementReferenceException e) {}
			cant++;
		}
	}
	
	@Test(groups = "SCP")
	public void TS112748_Negocio_del_Cliente_Ver_Video() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 2);
		driver.findElement(By.cssSelector(".btn.btn-xs.btn-default")).click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
		Assert.assertTrue(driver.findElement(By.cssSelector(".video-stream.html5-main-video")).isDisplayed());
		driver.close();
	    driver.switchTo().window(tabs2.get(0));
	}
	
	@Test(groups = "SCP")
	public void TS112681_Hitos_Relevantes_Ver_Video() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("segundoTitulo", 3);
		driver.findElement(By.cssSelector(".btn.btn-xs.btn-default")).click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
		Assert.assertTrue(driver.findElement(By.cssSelector(".video-stream.html5-main-video")).isDisplayed());
		driver.close();
	    driver.switchTo().window(tabs2.get(0));
	}
	
	@Test(groups = "SCP")
	public void TS112679_Hitos_Relevantes_Nuevo_Hito_Relevante_Agregar() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("segundoTitulo", 3);
		List <WebElement> boton = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : boton) {
			if (x.getText().toLowerCase().contains("nuevo hito relevante")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		String c = "nuevo test";
		List <WebElement> a = driver.findElements(By.className("resetHito"));
		a.get(0).sendKeys(c);
		a.get(1).click();
		CustomerCare page = new CustomerCare(driver);
		page.setSimpleDropdown(a.get(1), "Otro");
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.className("dateFormat")).click();
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> b = driver.findElements(By.className("data2Col"));
		Assert.assertTrue(b.get(1).getText().contains(c));
	}
	
	@Test(groups = "SCP")
	public void TS112680_Hitos_Relevantes_Nuevo_Hito_Relevante_Cancelar() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("segundoTitulo", 3);
		List <WebElement> boton = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : boton) {
			if (x.getText().toLowerCase().contains("nuevo hito relevante")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		String c = "cancelacion de hito";
		List <WebElement> a = driver.findElements(By.className("resetHito"));
		a.get(0).sendKeys(c);
		a.get(1).click();
		CustomerCare page = new CustomerCare(driver);
		page.setSimpleDropdown(a.get(1), "Otro");
		driver.findElement(By.className("dateFormat")).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.xpath("//*[@id=\"myModalHito\"]/div[2]/div/div[3]/button[1]")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> b = driver.findElements(By.className("data2Col"));
		Assert.assertTrue(!(b.get(1).getText().contains(c)));
	}

	@Test(groups = "SCP")
	public void TS112677_Hitos_Relevantes_Ingreso_Desde_el_contacto() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("segundoTitulo", 3);
		boolean check=true;
	    String[] datosOp = {"Descripción", "Fecha", "Categoría"};
	    List<String> titleTabla = new ArrayList<String>();
	    WebElement oportunidad = driver.findElement(By.xpath("//*[@id=\"j_id0:j_id89:hitosRelevantes:j_id97\"]/div[2]/table/tbody/tr[2]/td/table/thead"));
	    List<WebElement> composicion= oportunidad.findElement(By.tagName("tr")).findElements(By.tagName("th"));	    
	    for(WebElement a : composicion) {
	      titleTabla.add(a.getText());
	      //System.out.println(a.getText());//Para Verificar que este imprimiendo el texto que buscamos
	    }	    
	    for(String a:datosOp) {
	    	if(!(titleTabla.contains(a)))
	    		check=false;
	    }
	    Assert.assertTrue(check);
	    Assert.assertTrue(driver.findElement(By.cssSelector(".btn.btn.btn-default.btn-sm")).getAttribute("value").equals("Borrar"));
	    
	}
	
	@Test(groups = "SCP")
	public void TS112750_Opportunity_Snapshot_Chatter_contextualizado_Escribir_comentario() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 4);
		String a = "comentario de opportunity snapshot";
		prueba.comentarycompartir(a);
		prueba.validarcomentario(a);
	}
	
	@Test(groups = "SCP")
	public void TS112752_Opportunity_Snapshot_enviar() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 4);
		WebElement element = driver.findElement(By.name("j_id0:j_id111:j_id112:FastTaskForm:j_id117"));
		String a = element.getAttribute("value");		
		String b = "asd";
		element.sendKeys(b);
		driver.findElement(By.cssSelector(".btn.btnPrimary.publishersharebutton.btn.btn-default.btn-sm")).click();
		Assert.assertTrue(!(a.equals(b)));
	}
	
	@Test(groups = "SCP")
	public void TS112800_Share_of_Wallet_Chatter_contextualizado_Escribir_comentario() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("segundoTitulo", 1);
		String a = "comentario de share of wallet";
		prueba.comentarycompartir(a);
		prueba.validarcomentario(a);
	}
	
	@Test(groups = "SCP")
	public void TS112761_Organigrama_y_mapa_de_influencia_Chatter_contextualizado_Escribir_comentario() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 3);
		String a = "comentario";
		prueba.comentarycompartir(a);
		prueba.validarcomentario(a);
	}
	
	@Test(groups = "SCP")
	public void TS112674_Hitos_Relevantes_Chatter_contextualizado_Escribir_comentario() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("segundoTitulo", 3);
		String a = "comentario de hitos relevantes";
		prueba.comentarycompartir(a);
		prueba.validarcomentario(a);
	}
	
	@Test(groups = "SCP")
	public void TS112628_Estrategia_de_Crecimiento_Agregar_Negocio_Potencial() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 5);
		List <WebElement> boton = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : boton) {
			if (x.getText().toLowerCase().contains("agregar negocio potencial")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		String a = "nuevo negocio de prueba";
		driver.findElement(By.xpath("//*[@id=\"j_id0:j_id123:j_id219\"]")).sendKeys(a);
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		List <WebElement> tabla = driver.findElements(By.className("ScrollingDiv"));
		Assert.assertTrue(tabla.get(1).getText().contains("nuevo negocio de prueba"));
	}
	
	@Test(groups = "SCP")
	public void TS112629_Estrategia_de_Crecimiento_Chatter_contextualizado_Escribir_comentario() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 5);
		String a = "comentario de estrategia de crecimiento";
		prueba.comentarycompartir(a);
		prueba.validarcomentario(a);
	}

	@Test(groups = "SCP")
	public void TS112700_Mosaico_de_Relacionamiento_General_Descargar_imagen() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("segundoTitulo", 4);
		List <WebElement> element = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("ver mosaico ordenado por rol")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> b = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		boolean a = false;
		for (WebElement x : b) {
			if (x.getText().contains("Descargar Imagen")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
	}
	
	//@Test(groups = "SCP")
	public void TS112716_Mosaico_de_Relacionamiento_General_Ver_Video() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("segundoTitulo", 4);
		driver.findElement(By.cssSelector(".btn.btn-xs.btn-default")).click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    BasePage cambioFrameByID = new BasePage();
	    try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("ytp-cued-thumbnail-overlay")));
		Assert.assertTrue(driver.findElement(By.id("player-container")).isDisplayed());
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.close();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    driver.switchTo().window(tabs2.get(0));
	}
	
	@Test(groups = "SCP")
	public void TS112707_Mosaico_de_Relacionamiento_General_Ordenar_por_Actitud() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("segundoTitulo", 4);
		List <WebElement> element = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("ver mosaico ordenado por actitud")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(driver.findElement(By.cssSelector(".tablaMosaico.tablaUser")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector(".tablaMosaico.tablaTecnico")).isDisplayed());
	}
	
	@Test(groups = "SCP")
	public void TS112711_Mosaico_de_Relacionamiento_General_Ordenar_por_ROL() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("segundoTitulo", 4);
		List <WebElement> element = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("ver mosaico ordenado por rol")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> a = driver.findElements(By.className("tablaMosaico"));
		Assert.assertTrue(a.get(0).isDisplayed() && a.get(1).isDisplayed());
	}
	
	@Test(groups = "SCP")
	public void TS112715_Mosaico_de_Relacionamiento_General_Ver_organigrama() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("segundoTitulo", 4);
		List <WebElement> a = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : a) {
			if (x.getText().toLowerCase().contains("ver mosaico ordenado por rol")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> b = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : b) {
			if (x.getText().toLowerCase().contains("ver organigrama")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
		WebElement element = driver.findElement(By.className("panel-heading"));
		Assert.assertTrue(element.getText().contains("Organigrama y Mapa de Influencia"));
		driver.close();
	    driver.switchTo().window(tabs2.get(0));
	}
	
	@Test(groups = "SCP")
	public void TS112758_Opportunity_Snapshot_Triangulo_Ordenador() throws ParseException {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 4);
		Assert.assertTrue(prueba.Triangulo_Ordenador_Validador(driver, By.id("mainTable_wrapper"), 5, 2));	
	}
	
	@Test(groups = "SCP")
	public void TS112561_Asignación_de_Value_Drivers_a_Oportunidades_Exportar_a_Excel() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 1);
		String usuario = driver.findElement(By.cssSelector(".nav.navbar-nav.navbar-right")).findElement(By.tagName("a")).getText();
		List <WebElement> element = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("exportar a excel")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		usuario=usuario.replace(' ', '_');
		usuario=usuario.concat("-Asignación_de_Value_Drivers_a_Oportunidades.xls");
		assertTrue(prueba.isFileDownloaded(downloadPath, usuario), "Failed to download Expected document");
	}
	
	@Test(groups = "SCP")
	public void TS112686_Matriz_de_Criterios_de_Decisión_Exportar_a_Excel() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 2);
		List <WebElement> element = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("ir a los criterios")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		String usuario = driver.findElement(By.cssSelector(".nav.navbar-nav.navbar-right")).findElement(By.tagName("a")).getText();
		List <WebElement> element1 = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : element1) {
			if (x.getText().toLowerCase().contains("exportar a excel")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		usuario=usuario.replace(' ', '_');
		usuario=usuario.concat("-Criterios_de_Decisión_por_Oportunidad.xls");
		assertTrue(prueba.isFileDownloaded(downloadPath, usuario), "Failed to download Expected document");		
	}
	
	@Test(groups = "SCP")
	public void TS112687_Matriz_de_Criterios_de_Decisión_Guardar() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 2);
		List <WebElement> element = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("ir a los criterios")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> element1 = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		boolean a = false;
		for (WebElement x : element1) {
			if (x.getText().toLowerCase().contains("guardar")) {
				x.click();
				a = true;
				break;
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test(groups = "SCP")
	public void TS112688_Matriz_de_Criterios_de_Decisión_Ir_al_mosaico() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 2);
		List <WebElement> element = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("ir a los criterios")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> element1 = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : element1) {
			if (x.getText().toLowerCase().contains("ir al mosaico")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(driver.findElement(By.className("panel-heading")).getText().contains("Mosaico de Relacionamiento"));
	}
	
	@Test(groups = "SCP")
	public void TS112689_Matriz_de_Criterios_de_Decisión_Ir_al_Snapshot() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 2);
		List <WebElement> element = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("ir a los criterios")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> element1 = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : element1) {
			if (x.getText().toLowerCase().contains("ir al snapshot")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(driver.findElement(By.className("panel-heading")).getText().contains("Opportunity Snapshot"));
	}
	
	@Test(groups = "SCP")
	public void TS112690_Matriz_de_Criterios_de_Decisión_Ver_Gráficos_de_Criterio() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 2);
		List <WebElement> element = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("ir a los criterios")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> element1 = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : element1) {
			if (x.getText().toLowerCase().contains("ver gráfico de criterios")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> element2 = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		boolean a = false;
		for (WebElement x : element2) {
			if (x.getText().contains("Modificar Criterios")) {
				a = true;
			}
		}
		Assert.assertTrue(a);	
	}
	
	@Test(groups = "SCP")
	public void TS112691_Matriz_de_Criterios_de_Decisión_Ver_Video() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 2);
		driver.findElement(By.cssSelector(".btn.btn-xs.btn-default")).click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
		Assert.assertTrue(driver.findElement(By.cssSelector(".video-stream.html5-main-video")).isDisplayed());
		driver.close();
	    driver.switchTo().window(tabs2.get(0));
	}
	
	@Test(groups = "SCP")  //Rompe porque no esta la columna "Posición Competitiva de la Competencia", dice "Competidores competitivos de pie"
	public void TS112692_Matriz_de_Criterios_de_desicion_Ingreso_Desde_el_contacto() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 2);
		List <WebElement> element = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("ir a los criterios")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement boton = driver.findElement(By.xpath("//*[@id=\"j_id0:j_id128:j_id140\"]"));
		boolean b = false;
		if (boton.getAttribute("value").contains("Agregar Criterio")) {
			b = true;
		}
		WebElement eliminar = driver.findElement(By.id("j_id0:j_id143:j_id158:0:j_id174"));
		boolean c = false;
		if (eliminar.getAttribute("value").contains("Eliminar")) {
			c = true;
		}
		boolean check=true;
	    String[] datosOp = {"Criterio", "Consideración del cliente", "Nuestra posición competitiva", "Posición Competitiva de la Competencia", "Enfoque"};
	    List<String> titleTabla = new ArrayList<String>();
	    WebElement oportunidad = driver.findElement(By.id("j_id0:j_id143:j_id146"));
	    List<WebElement> composicion= oportunidad.findElement(By.tagName("tr")).findElements(By.tagName("th"));	    
	    for(WebElement a : composicion) {
	      titleTabla.add(a.getText());
	      //System.out.println(a.getText());//Para Verificar que este imprimiendo el texto que buscamos
	    }	    
	    for(String a:datosOp) {
	    	if(!(titleTabla.contains(a)))
	    		check=false;
	    }
	    Assert.assertTrue(b && c && check);
	}

	@Test(groups = "SCP")
	public void TS112685_Matriz_de_Criterios_de_desicion_Eliminar() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 2);
		List <WebElement> element = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("ir a los criterios")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> elim = driver.findElements(By.xpath("//*[@id=\"j_id0:j_id143:j_id158:0:j_id174\"]"));
		boolean a = false;
		for (WebElement x : elim) {
			if (x.getAttribute("value").toLowerCase().contains("eliminar")) {
				a = true;
				break;
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test(groups = "SCP")
	public void TS112682_Matriz_de_Criterios_de_Decisión_Agregar_Criterio() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 2);
		List <WebElement> element = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("ir a los criterios")) {
				x.click();
				break;
			}
		}
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.xpath("//*[@id=\"j_id0:j_id128:j_id140\"]")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement ventana = driver.findElement(By.className("modal-header"));
		Assert.assertTrue(ventana.getText().contains("Evaluación del Criterio"));		
		List <WebElement> cerrar = driver.findElements(By.cssSelector(".btn.btn-default"));
		for (WebElement x : cerrar) {
			if (x.getText().toLowerCase().contains("cerrar")) {
				x.click();
				break;
			}
		}
	}
}