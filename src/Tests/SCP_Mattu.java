package Tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.SCP;
import Pages.setConexion;

public class SCP_Mattu extends TestBase {

private WebDriver driver;
	
	@BeforeClass(groups = "SCP")
	  public void Init() throws Exception
	  {
	    this.driver = setConexion.setupEze();
	    loginSCPAdmin(driver);
	    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	  }
	
	@BeforeMethod(groups = "SCP")
	  public void setUp() throws Exception {
	    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    SCP prueba= new SCP(driver);
	    prueba.goToMenu("SCP");
	    prueba.clickOnTabByName("cuentas");
	    prueba.clickOnFirstAccRe();
	    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
	}
	
	//@AfterClass(groups = "SCP")
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
		List <WebElement> element = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("exportar a excel")) {
				x.click();
				break;
			}
		}
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
		List<WebElement> servicioList = driver.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
		  for (WebElement UnS : servicioList) {
			  if (UnS.getText().toLowerCase().contains("export to excel")) {
				  	UnS.click();
				  	break;
			  }
		  }	
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
}