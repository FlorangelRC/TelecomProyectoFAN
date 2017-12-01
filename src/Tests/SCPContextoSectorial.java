package Tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.SCP;
import Pages.setConexion;

public class SCPContextoSectorial extends TestBase {

private WebDriver driver;
	
	@BeforeClass(groups = "Fase2")
	  public void Init() throws Exception
	  {
	    this.driver = setConexion.setupEze();
	    loginSCPAdmin(driver);
	    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	  }
	
	@BeforeMethod(groups = "Fase2")
	  public void setUp() throws Exception {
	    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    SCP prueba= new SCP(driver);
	    prueba.goToMenu("SCP");
	    prueba.clickOnTabByName("cuentas");
	    prueba.clickOnFirstAccRe();
	    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
	}
	
	@Test
	public void TS112613_Cronograma_de_cuenta_Agregar_Vencimiento_Contrato_del_Servicio() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("cuartoTitulo", 1);
		List <WebElement> checkbox = driver.findElements(By.className("checkboxFiltroTimeLine"));
		checkbox.get(1).click();
		driver.findElement(By.id("j_id0:j_id89:j_id109")).click();
		Assert.assertTrue(driver.findElement(By.className("tl-message-full")).isDisplayed());		
	}
	
	@Test
	public void TS112626_Cronograma_de_cuenta_Filtros_Vencimiento_Contrato_del_Servicio(){
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("cuartoTitulo", 1);
		List <WebElement> checkbox = driver.findElements(By.className("checkboxFiltroTimeLine"));
		checkbox.get(1).click();
		driver.findElement(By.id("j_id0:j_id89:j_id109")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector(".tl-timemarker-content.tl-timemarker-content-small")).isDisplayed());
	}
	
	@Test
	public void TS112627_Cronograma_de_Cuenta_Ingreso_Desde_el_contacto() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("cuartoTitulo", 1);
		List <WebElement> checkbox = driver.findElements(By.className("checkboxFiltroTimeLine"));
		checkbox.get(1).click();
		driver.findElement(By.id("j_id0:j_id89:j_id109")).click();
		Assert.assertTrue(driver.findElement(By.className("tl-message-full")).isDisplayed() 
						  && driver.findElement(By.cssSelector(".tl-timemarker-content.tl-timemarker-content-small")).isDisplayed());
	}
	
	@Test
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
	
	@Test
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
	
	@Test
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
	
	@Test
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
	
	@Test
	public void TS112742_Negocio_del_Cliente_Exportar_a_Excel() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 2);
		driver.findElement(By.id("j_id0:Form:j_id274")).findElement(By.cssSelector(".btn.btn-default.btn-sm")).click();	
	}
	
	@Test
	public void TS112744_Negocio_del_cliente_Ingreso_Desde_el_contacto() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 2);
		Assert.assertTrue(driver.findElement(By.id("hidden-descripcionCliente")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("hidden-strategicContext")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("hidden-strategicIniciative")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("hidden-mainCompetitors")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("hidden-csat")).isDisplayed());
	}
	
	@Test
	public void TS112745_Negocio_del_Cliente_Principales_competidores_del_cliente() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 2);
		driver.findElement(By.id("hidden-mainCompetitors")).click();
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(driver.findElement(By.cssSelector(".hiddenTable.hidden-mainCompetitors")).isDisplayed());
	}
	
	@Test
	public void TS112802_Share_of_Wallet_Exportar_a_Excel() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("segundoTitulo", 1);
		List<WebElement> servicioList = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		  for (WebElement UnS : servicioList) {
			  if (UnS.getText().toLowerCase().contains("export to excel")) {
				  	UnS.click();
				  	break;
			  }
		  }	
	}
	
	@Test
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
	
	@Test
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
	
	@Test
	public void TS112720_Mosaico_de_Relacionamiento_por_Oportunidad_Enviar() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("segundoTitulo", 4);
		driver.findElement(By.cssSelector(".btn.btnPrimary.publishersharebutton.btn.btn-default.btn-sm")).click();
	}
	
	@Test
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
	
	@Test
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
	
	@Test
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
	
	@Test
	public void TS112756_Opportunity_Snapshot_Nombre_de_la_oportunidad() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("tercerTitulo", 4);
		driver.findElement(By.xpath("//*[@id=\"mainTable\"]/tbody/tr[1]/td[2]/a")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(driver.findElement(By.id("bodyCell")).isDisplayed());
	}
	
	@Test
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
	
	@Test
	public void TS112766_Organigrama_y_mapa_de_Influencia_Ingreso_Desde_el_contacto() {
		SCP prueba = new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 3);
		Assert.assertTrue(driver.findElement(By.id("j_id0:Form:pageContent")).findElement(By.cssSelector(".table.table-striped.table-bordered.table-condensed")).isDisplayed());	
	}
	
	@Test
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
	
	@Test
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
}