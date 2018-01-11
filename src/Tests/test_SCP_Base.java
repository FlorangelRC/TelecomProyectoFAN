package Tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.Page;
import com.sun.corba.se.spi.orbutil.fsm.Action;

import Pages.Accounts;
import Pages.BasePage;
import Pages.HomeBase;
import Pages.SCP;
import Pages.SalesBase;
import Pages.setConexion;

public class test_SCP_Base extends TestBase {
	
	private static final boolean WebElement = false;
	private WebDriver driver;
	private String Cuenta;

	@BeforeClass(groups= {"SCP", "Almer", "Prueba"})
	public void Init() throws Exception
	{
		this.driver = setConexion.setupEze();
		loginSCPAdmin(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	@BeforeMethod(groups= {"SCP", "Almer", "Prueba"})
	public void setUp() throws Exception {
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		SCP page= new SCP(driver);
		page.goToMenu("SCP");
		page.clickOnTabByName("cuentas");
		page.listTypeAcc("Todas Las cuentas");
		page.clickEnCuentaPorNombre("Florencia Di Cio");
		Cuenta=driver.findElement(By.className("topName")).getText();
		page.moveToElementOnAccAndClick("tercerTitulo", 1);
		}
	
	@AfterMethod(groups= {"SCP", "Almer", "Prueba"})
	public void afterMethod() {
		driver.switchTo().defaultContent();
		SCP page= new SCP(driver);
		page.goTop();
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	@AfterClass(groups= {"SCP", "Almer", "Prueba"})
	public void tearDown() {
		driver.quit();
		sleep(4000);
	}
	
//--------------------------------------------METODOS----------------------------------------------------------------//
	public String limpiarValor(String valor) {
		//System.out.println(valor);
		valor=valor.replace("$", "");
		//System.out.println(valor);
		valor=valor.replace(".", "");
		//System.out.println(valor);
		valor=valor.replaceAll(",", ".");
		//System.out.println(valor);
		return valor;
	}
	
	/**
	 * Escribe un comentario y verifica que aparezca (Victor Pidio que lo Obviaramos)
	 * By: Almer
	 */
	@Test(retryAnalyzer = Tests.retry.class , groups= {"SCP", "Almer"})
	public void TS112559_CRM_SCP_Asignación_de_Value_Drivers_a_Oportunidades_Chatter_contextualizado_Escribir_comentario() {
		
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//WebElement BenBoton = driver.findElement(By.id("publishereditablearea"));
		//((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+BenBoton.getLocation().y+")");
		//BenBoton.click();
		//try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//BenBoton.sendKeys("Hola");
		//publisherWrapper
		//driver.findElement(By.id(("cke_1_contents"))).sendKeys("Hola Mundo");
		//driver.switchTo().defaultContent();
		//driver.switchTo().frame(1);
		//driver.switchTo().frame(driver.findElement(By.cssSelector(".cke_wysiwyg_frame.cke_reset")));
		//driver.switchTo().frame(Frame.getFrameForElement(driver, By.cssSelector(".cke_wysiwyg_frame.cke_reset")));
		//driver.findElement(By.cssSelector(".chatterPublisherRTE.cke_editable.cke_editable_themed.cke_contents_ltr.cke_show_borders.placeholder")).sendKeys("Hola Mundo");
		
		
		//.chatterPublisherRTE.cke_editable.cke_editable_themed.cke_contents_ltr.cke_show_borders.placeholder
		
		//https://crm--UAT2--scp-telecom.cs92.visual.force.com/apex/accountIndustryTrendsWindowNew?id=0013F00000300bV
	}
	
	/**
	 * Verifica que se muestren los comentarios de otras cuentas en la vista
	 * By: Almer
	 */
	@Test(retryAnalyzer = Tests.retry.class , groups= {"SCP", "Prueba"})
	public void TS112560_CRM_SCP_Asignación_de_Value_Drivers_a_Oportunidades_Chatter_contextualizado_Leer_comentario_escrito_con_otro_usuario() {
		
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
		WebElement BenBoton = driver.findElement(By.cssSelector(".feeditemcontent.cxfeeditemcontent"));
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+BenBoton.getLocation().y+")");
		//System.out.println(Cuenta);
		boolean check=false;
		List <WebElement> Nombres= driver.findElements(By.className("feeditemfirstentity"));
		for(WebElement a: Nombres){
			//System.out.println("Nombre: "+a.getText()+" ");
			//System.out.print("Comentario: "+driver.findElement(By.cssSelector(".feeditemtext.cxfeeditemtext")).findElement(By.tagName("p")).getText());
			if(!(a.getText().contains(Cuenta))) {
				assertTrue(driver.findElement(By.cssSelector(".feeditemtext.cxfeeditemtext")).findElement(By.tagName("p")).isDisplayed());
				return;}
				}
		assertTrue(check);
	}
	
	
	/**
	 * Verifica que las tablas Oportunidades y Value Drivers tengan los Componentes deseados
	 * By: Almer
	 */
	@Test(retryAnalyzer = Tests.retry.class , groups= "SCP")
	public void TS112562_CRM_SCP_Asignación_de_Value_Drivers_a_Oportunidades_Ingreso_Desde_el_contacto() {
		
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		boolean check=true;
		String[] datosOp = {"nombre de la oportunidad", "importe", "probabilidad (%)", "fecha de cierre", "etapa", "related value drivers"};
		List<String> titleTabla = new ArrayList<String>();
		WebElement oportunidad=driver.findElement(By.id("mainOppsTable")).findElement(By.className("headerRow"));
		List<WebElement> composicion= oportunidad.findElements(By.tagName("th"));
		
		for(WebElement a : composicion) {
			titleTabla.add(a.getText().toLowerCase());
			//System.out.println(a.getText());//Para Verificar que este imprimiendo el texto que buscamos
			}
		
		for(String a:datosOp) {
			if(!(titleTabla.contains(a)))
				check=false;}
		assertTrue(check);
		
		String[] datosVD = {"título", "descripción", "tipo", "origen", "oportunidades"};
		List<String> tituloTabla = new ArrayList<String>();
		WebElement valueDrivers=driver.findElement(By.id("mainTable")).findElement(By.className("headerRow"));
		List<WebElement> contenido= valueDrivers.findElements(By.tagName("th"));
		
		for(WebElement a : contenido) {
			tituloTabla.add(a.getText().toLowerCase());
			//System.out.println(a.getText()); //Para Verificar que este imprimiendo el texto que buscamos
			}
		
		for(String a:datosVD) {
			if(!(tituloTabla.contains(a)))
				check=false;}
		
		assertTrue(check);		
	}
	
	/**
	 * Se Verifica que al hacer en click en la primera oportunidad, pase a la siguiente pagina y se muestre toda la informacion de la oportunidad
	 * By: Almer
	 */
	@Test(retryAnalyzer = Tests.retry.class , groups= "SCP")
	public void TS112565_CRM_SCP_Asignación_de_Value_Drivers_a_Oportunidades_Oportunidades() {
		SCP page=new SCP(driver);
	     assertTrue(page.goToOportunity());
	}
	
	/**
	 * Verifica los campos que conforman la estructura de oportunidades. El caso especifica el orden pero Victor menciono que no es necesario.
	 * By: Almer.
	 */
	@Test(retryAnalyzer = Tests.retry.class , groups= "SCP")
	public void TS112638_CRM_SCP_Estructura_de_las_oportunidades_Bloques() {
		boolean check=true;
		SCP page=new SCP(driver);
	    if(page.goToOportunity()) {
	    	try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	String[] primerBloque= {"información adicional de ventas","valorizado de la oportunidad","estados proyectos en delta"};
	    	String[] segundoBloque= {"detalle de oportunidad","productos","contactos","funciones de contactos","actividades abiertas","competidores","historial de campos de oportunidades","notas y archivos adjuntos"};
	    	
	    	//Se verifica que el primer bloque
	    	List <WebElement> listA= driver.findElements(By.cssSelector(".brandTertiaryBrd.pbSubheader.tertiaryPalette"));
	    	List<String> titleOne = new ArrayList<String>();
	    	for(WebElement comparacion:listA) {
	    		//System.out.println(comparacion.getText().toLowerCase());
	    		titleOne.add(comparacion.getText().toLowerCase());}
	    		
	    	for(String a:primerBloque) {
	    			if(!(titleOne.contains(a)))
	    				check=false;}
	    	assertTrue(check);
	    	
	    	//Se verifica el segundo Bloque.
	    	check=true;
	    	int i=0;
	    	List <WebElement> estructuraOp2=driver.findElements(By.className("pbTitle"));
	    	List<String> titleTwo = new ArrayList<String>();
	 	    for(WebElement agregar:estructuraOp2) {
	 	    	//System.out.println(agregar.getText().toLowerCase());
	 	    	if(i==1){}
	 	    	else
	 	    		titleTwo.add(agregar.getText().toLowerCase()); i++;}
	 	    for(String comparar:segundoBloque) {
	    			if(!(titleTwo.contains(comparar)))
	    				check=false;}
	    	assertTrue(check);
	    	
	    	}
	   
	    else {System.out.println("Oportunidad no disponible, prueba no ejecutada");assertTrue(false);}	
	}
	
	/**
	 * Crea un Competidor y luego verifica que se creo segun la cantidad de competidores.
	 * @author Almer
	 */
	@Test(retryAnalyzer = Tests.retry.class , groups= "SCP")
	public void TS112641_CRM_SCP_Estructura_de_las_oportunidades_Bloques_Competidores_Creacion() {
		SCP page=new SCP(driver);
		String countBefore="", countAfter=""; //Comparadores
	    if(page.goToOportunity()) {
	    	List <WebElement> compBefore = driver.findElements(By.className("listTitle")); //Lista los Elementos de arriba
	    	for(WebElement a:compBefore) {
	    		if(a.getText().toLowerCase().startsWith("competidores")) { 
	    			countBefore=a.findElement(By.className("count")).getText();//guarda la cantidad de competidores
	    			a.click();}} //hace click en competidores para bajar
	    	
	    	//Crea un nuevo competidor
	    	try {Thread.sleep(500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	driver.findElement(By.name("newComp")).click();
	    	try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	driver.findElement(By.id("CompetitorName")).sendKeys("Almer");
	    	driver.findElement(By.id("Strengths")).sendKeys("CRACK!");
	    	try {Thread.sleep(500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	driver.findElement(By.id("bottomButtonRow")).findElement(By.name("save")).click();
	    	try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	
	    	//lista nuevamente para conocer la cantidad de competidores actual.
	    	List <WebElement> compAfter = driver.findElements(By.className("listTitle"));
	    	for(WebElement a:compAfter) {
	    		if(a.getText().toLowerCase().startsWith("competidores")) {
	    			countAfter=a.findElement(By.className("count")).getText();}}
	    	
	    	assertTrue(!(countBefore.equals(countAfter))); //compara
	    }
	    else {System.out.println("Oportunidad no disponible, prueba no ejecutada");assertTrue(false);}	
	}
	
	/**
	 * Edita un Competidor y Verifica que se mantenga la misma cantidad de competidores.
	 * By: Almer
	 */
	@Test(retryAnalyzer = Tests.retry.class , groups= "SCP")
	public void TS112642_CRM_SCP_Estructura_de_las_oportunidades_Bloques_Competidores_Edicion() {
		SCP page=new SCP(driver);
		String countBefore="", countAfter=""; //Comparadores
	    if(page.goToOportunity()) {
	    	List <WebElement> compBefore = driver.findElements(By.className("listTitle")); //Lista los Elementos de arriba
	    	for(WebElement a:compBefore) {
	    		if(a.getText().toLowerCase().startsWith("competidores")) { 
	    			countBefore=a.findElement(By.className("count")).getText();//guarda la cantidad de competidores
	    			a.click();}} //hace click en competidores para bajar
	    
	    	try {Thread.sleep(500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	
	    	driver.findElement(By.cssSelector(".listRelatedObject.opportunityCompetitorBlock")).findElement(By.cssSelector(".dataRow.odd")).findElement(By.tagName("a")).click();
	    	driver.findElement(By.id("CompetitorName")).clear();
	    	driver.findElement(By.id("CompetitorName")).sendKeys("Edicion Automatica");
	    	try {Thread.sleep(500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	driver.findElement(By.id("bottomButtonRow")).findElement(By.name("save")).click();
	    	try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	
	    	//lista nuevamente para conocer la cantidad de competidores actual.
	    	List <WebElement> compAfter = driver.findElements(By.className("listTitle"));
	    	for(WebElement a:compAfter) {
	    		if(a.getText().toLowerCase().startsWith("competidores")) {
	    			countAfter=a.findElement(By.className("count")).getText();}}
	    	
	    	assertTrue(countBefore.equals(countAfter)); //compara que sea la misma cantidad
	    }
	    else {System.out.println("Oportunidad no disponible, prueba no ejecutada");assertTrue(false);}	
	}
	
	/**
	 * Elimina un competidor y compara si fue eliminado segun la cantidad de competidores
	 * By: Almer
	 */
	@Test(retryAnalyzer = Tests.retry.class , groups= "SCP")
	public void TS112643_CRM_SCP_Estructura_de_las_oportunidades_Bloques_Competidores_Eliminacion() {
		SCP page=new SCP(driver);
		String countBefore="", countAfter=""; //Comparadores
	    if(page.goToOportunity()) {
	    	List <WebElement> compBefore = driver.findElements(By.className("listTitle")); //Lista los Elementos de arriba
	    	for(WebElement a:compBefore) {
	    		if(a.getText().toLowerCase().startsWith("competidores")) { 
	    			countBefore=a.findElement(By.className("count")).getText();//guarda la cantidad de competidores actual
	    			a.click();}} //hace click en competidores para bajar
	    
	    	try {Thread.sleep(500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	List <WebElement> Eliminar = driver.findElement(By.cssSelector(".listRelatedObject.opportunityCompetitorBlock")).findElement(By.cssSelector(".dataRow.odd")).findElements(By.tagName("a"));
	    	Eliminar.get(1).click();
	    	driver.switchTo().alert().accept();
	    	driver.switchTo().defaultContent();
	    	
	    	//lista nuevamente para conocer la cantidad de competidores actual.
	    	List <WebElement> compAfter = driver.findElements(By.className("listTitle"));
	    	for(WebElement a:compAfter) {
	    		if(a.getText().toLowerCase().startsWith("competidores")) {
	    			countAfter=a.findElement(By.className("count")).getText();}} //Guarda la cantidad de Competidores Despues de Eliminar
	    	
	    	assertTrue(!(countBefore.equals(countAfter))); //compara que sea ha eliminado el competidor segun la cantidad
	    	}
	    else {System.out.println("Oportunidad no disponible, prueba no ejecutada");assertTrue(false);}	
	}
	
	
	/**
	 * Crea una nota y verifica que ha sido creada comparando las cantidades.
	 * By: Almer
	 */
	@Test(retryAnalyzer = Tests.retry.class , groups= "SCP")
	public void TS112649_CRM_SCP_Estructura_de_las_oportunidades_Bloques_Notas_y_Archivos_Adjuntos_Adjuntar_Nota() {
		SCP page=new SCP(driver);
		String countBefore="", countAfter=""; //Comparadores
	    if(page.goToOportunity()) {
	    	List <WebElement> compBefore = driver.findElements(By.className("listTitle")); //Lista los Elementos de arriba
	    	for(WebElement a:compBefore) {
	    		if(a.getText().toLowerCase().startsWith("notas")) { 
	    			countBefore=a.findElement(By.className("count")).getText();//guarda la cantidad de competidores actual
	    			a.click();}} //hace click en competidores para bajar
	    	
	    	//Crea La nota
	    	driver.findElement(By.name("newNote")).click();
	    	try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	driver.findElement(By.id("Title")).sendKeys("Nota Automatizada");
	    	driver.findElement(By.id("Body")).sendKeys("Esta Nota ha sido creada por el Equipo de QA Autmoation");
	    	try {Thread.sleep(500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	driver.findElement(By.id("bottomButtonRow")).findElement(By.name("save")).click();
	    	
	    	//lista nuevamente para conocer la cantidad de Notas actuales
	    	try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	List <WebElement> compAfter = driver.findElements(By.className("listTitle"));
	    	for(WebElement a:compAfter) {
	    		if(a.getText().toLowerCase().startsWith("notas")) {
	    			countAfter=a.findElement(By.className("count")).getText();}} //Guarda la cantidad de notas Despues de crear
	    	
	    	assertTrue(!(countBefore.equals(countAfter))); //compara que sea ha creado la nota segun la cantidad
	    	}
	    else {System.out.println("Oportunidad no disponible, prueba no ejecutada");assertTrue(false);}	
	    }
	    
	/**
	 * Verifica que se puede visualizar las notas.
	 * By: Almer
	 */
	@Test(retryAnalyzer = Tests.retry.class , groups= "SCP")
	public void TS112650_CRM_SCP_Estructura_de_las_oportunidades_Bloques_Notas_y_Archivos_Adjuntos_Visualizar_Nota() {
		SCP page=new SCP(driver);
	    if(page.goToOportunity()) {
	    	List <WebElement> compBefore = driver.findElements(By.className("listTitle")); //Lista los Elementos de arriba
	    	for(WebElement a:compBefore) {
	    		if(a.getText().toLowerCase().startsWith("notas")) { 
	    			a.click();}} //hace click en notas para bajar
	    	driver.findElement(By.xpath("//*[@id=\"0063F000002UbLj_RelatedNoteList_body\"]/table/tbody/tr[2]/td[2]/a")).click();	
	    	try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	List <WebElement> cuerpoNota= driver.findElements(By.className("data2Col"));
	    	assertTrue(cuerpoNota.get(4).isDisplayed());
	    }
	    else {System.out.println("Oportunidad no disponible, prueba no ejecutada");assertTrue(false);}	
	}
	
	/**
	 * Cambia el Valor del Dolar Budget y verifica que se ha cambiado.
	 * By: Almer
	 */
	@Test(retryAnalyzer = Tests.retry.class , groups= "SCP")
	public void TS112665_CRM_SCP_Estructura_de_las_oportunidades_Bloques_Valorizado_de_la_oportunidad_Cotizacion_del_dolar_segun_Budget() {
		SCP page=new SCP(driver);
	    if(page.goToOportunity()) {
	   
	    	//String valorDolar= driver.findElement(By.id("00N3F000000JoWy_ileinner")).getText();
	    	//System.out.println(valorDolar);
	    	try {Thread.sleep(500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	Actions dobleClick= new Actions(driver);
	    	//List <WebElement> valorizacion= driver.findElements(By.cssSelector(".brandTertiaryBrd.pbSubheader.tertiaryPalette"));
	    	WebElement dolarBudget = driver.findElement(By.id("00N3F000000HaYj_ileinner"));
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+dolarBudget.getLocation().y+")");
	    	dobleClick.doubleClick(dolarBudget).perform();
	    	driver.findElement(By.id("00N3F000000HaYj")).clear();
	    	driver.findElement(By.id("00N3F000000HaYj")).sendKeys("10");
	    	page.goTop();
	    	driver.findElement(By.id("topButtonRow")).findElement(By.name("inlineEditSave")).click();
	    	try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	String valorDolar= driver.findElement(By.id("00N3F000000HaYj_ileinner")).getText();
	    	assertTrue(valorDolar.contains("10"));
	    	
	    }
	    else {System.out.println("Oportunidad no disponible, prueba no ejecutada");assertTrue(false);}	
	}
	
	//---------------------------------------FASE 4-------------------------------------------------------------------------------------//
	
	
	/**
	 * Se verifica que se pueda crear una nueva Tarea.
	 */
	@Test(retryAnalyzer = Tests.retry.class , groups= "SCP")
	public void TS112639_CRM_SCP_Estructura_de_las_oportunidades_Bloques_Actividades_abiertas_Tareas() {
		SCP page=new SCP(driver);
		String countBefore="", countAfter=""; //Comparadores
	    if(page.goToOportunity()) {
	    	List <WebElement> compBefore = driver.findElements(By.className("listTitle")); //Lista los Elementos de arriba
	    	for(WebElement a:compBefore) {
	    		if(a.getText().toLowerCase().startsWith("actividades abier")) { 
	    			countBefore=a.findElement(By.className("count")).getText();//guarda la cantidad
	    			a.click();}} //Click para bajar
	    	
	    	//Crea un nuevo competidor
	    	try {Thread.sleep(500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	driver.findElement(By.name("task")).click();
	    	
	    	try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	driver.findElement(By.name("tsk5")).sendKeys("Prueba Automatizada");

	    	driver.findElement(By.id("bottomButtonRow")).findElement(By.name("save")).click();
	    	try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	
	    	//lista nuevamente para conocer la cantidad de competidores actual.
	    	List <WebElement> compAfter = driver.findElements(By.className("listTitle"));
	    	for(WebElement a:compAfter) {
	    		if(a.getText().toLowerCase().startsWith("actividades abier")) {
	    			countAfter=a.findElement(By.className("count")).getText();}}
	    	
	    	assertTrue(!(countBefore.equals(countAfter))); //compara
	    }
	    else {System.out.println("Oportunidad no disponible, prueba no ejecutada");assertTrue(false);}	
	}
	
	
	/**
	 * Se verifica que dentro del bloque de detalle de la oportunidad, se encuentren todos los campos correspondientes:
	 * - Contactos de la oportunidad - Probabilidad - Grado de avance - TMI - Nombre de la oportunidad - Nombre de la cuenta
	   - Licitación - Retroactiva - Fecha probable de venta - Fecha probable de instalación - Etapa - Fecha real de venta- Fecha real Instalación
	 */
	@Test(retryAnalyzer = Tests.retry.class , groups= "SCP")
	public void TS112644_CRM_SCP_Estructura_de_las_oportunidades_Bloques_Detalle_de_la_oportunidad() {
		SCP page=new SCP(driver);
		Boolean co=false, pro=false, grado=false, tmi=false, nombreOp=false,
				nombreCu=false, lici=false, retro=false, fProventa=false, fproInst=false, etapa=false,
				fRealVen=false, fRealInst=false;
	    if(page.goToOportunity()) {
	    	List <WebElement> compBefore = driver.findElements(By.className("labelCol")); //Lista los Elementos de arriba
	    	for(WebElement a:compBefore) {
	    		System.out.println(a.getText());
	    		if(a.getText().toLowerCase().startsWith("contacto"))
	    			co=true;
	    		if(a.getText().toLowerCase().startsWith("probabilidad"))
	    			pro=true;
	    		if(a.getText().toLowerCase().startsWith("grado"))
	    			grado=true;
	    		if(a.getText().toLowerCase().startsWith("tmi"))
	    			tmi=true;
	    		if(a.getText().toLowerCase().startsWith("nombre de la oportunidad"))
	    			nombreOp=true;
	    		if(a.getText().toLowerCase().startsWith("nombre de la cuenta"))
	    			nombreCu=true;
	    		if(a.getText().toLowerCase().startsWith("licitac"))
	    			lici=true;
	    		if(a.getText().toLowerCase().startsWith("retroactiva"))
	    			retro=true;
	    		if(a.getText().toLowerCase().startsWith("fecha probable de venta"))
	    			fProventa=true;
	    		if(a.getText().toLowerCase().startsWith("fecha probable de inst"))
	    			fproInst=true;
	    		if(a.getText().toLowerCase().startsWith("etapa"))
	    			etapa=true;
	    		if(a.getText().toLowerCase().startsWith("fecha real de venta"))
	    			fRealVen=true;
	    		if(a.getText().toLowerCase().startsWith("fecha real inst"))
	    			fRealInst=true;
	    		if(co&&pro&&grado&&tmi&&nombreOp&&nombreCu&&lici&&retro&&fProventa&&fproInst&&etapa&&fRealVen&&fRealInst)
	    			break;
	    	}
	    	assertTrue(co);
	    	assertTrue(pro);
	    	assertTrue(grado);
	    	assertTrue(tmi);
	    	assertTrue(nombreOp);
	    	assertTrue(nombreCu);
	    	assertTrue(lici);
	    	assertTrue(retro);
	    	assertTrue(fProventa);
	    	assertTrue(fproInst);
	    	assertTrue(etapa);
	    	assertTrue(fRealVen);
	    	assertTrue(fRealInst);	
	    }
	    else {System.out.println("Oportunidad no disponible, prueba no ejecutada");assertTrue(false);}	
	}
	
	
	/**
	 * Modifica la oportunidad y verifica 
	 * que se haya cambiado el nombre y la fecha en el historial de campos de la oportunidad.
	 */
	@Test(retryAnalyzer = Tests.retry.class , groups= "SCP")
	public void TS112647_CRM_SCP_Estructura_de_las_oportunidades_Bloques_Historial_de_Campos() {
		SCP page=new SCP(driver);
		String fecha="";
	    if(page.goToOportunity()) {
	    	try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	driver.findElement(By.name("edit")).click();
	    	try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	List<WebElement> date=driver.findElements(By.className("dateFormat"));
	    	fecha=date.get(1).getText();
	    	fecha=fecha.substring(2, fecha.length()-2);
	    	//System.out.println(fecha);
	    	date.get(1).click();
	    	//for(WebElement imp:date)
	    		//System.out.println(imp.getText());
	    	driver.findElement(By.id("opp3")).clear();
	    	sleep(200);
	    	driver.findElement(By.id("opp3")).sendKeys("opAut");
	    	sleep(1000);
	    	driver.findElement(By.name("save")).click();
	    	WebElement modificacion=driver.findElement(By.xpath("//*[@id=\"0063F000002UbLj_RelatedEntityHistoryList_body\"]/table/tbody/tr[2]"));
	    	System.out.println(modificacion.getText());
	    	System.out.println(fecha);
	    	assertTrue(modificacion.getText().startsWith(fecha)&&modificacion.getText().endsWith("opAut."));
	    	}
	   
	    else {System.out.println("Oportunidad no disponible, prueba no ejecutada");assertTrue(false);}	
	}
	
//-------------------------------------------------Este Test  va a una oportunidad especifica que tiene un producto----------------------------------------------------------------//
	/**
	 * Verifica que en el bloque productos contenga los campos que se validan en el test
	 */
	@Test(retryAnalyzer = Tests.retry.class , groups= "SCP")
	public void TS112651_CRM_SCP_Estructura_de_las_oportunidades_Bloques_Productos_de_la_oportunidad() {
	SCP page=new SCP(driver);
	page.clickOnTabByName("oportunidades");
	sleep(3000);
	Select sOp=new Select(driver.findElement(By.id("fcf")));
	sOp.selectByVisibleText("Todas las oportunidades");
	sleep(200);
	driver.findElement(By.name("go")).click();
	sleep(2000);
	List<WebElement> lOp=driver.findElements(By.className("x-grid3-row"));
	lOp.add(driver.findElement(By.cssSelector(".x-grid3-row.x-grid3-row-first")));
	boolean flag=false;
	for(WebElement nOp: lOp) {
		List<WebElement> tagsOp=nOp.findElements(By.tagName("a"));
		for(WebElement nombre:tagsOp) {
		if(nombre.getText().toLowerCase().contains("alta sucursal entre rios")) {
			nombre.click();
			flag=true;
			break;}
			}
		if(flag)
			break;
		}
	sleep(2000);
	List <WebElement> compBefore = driver.findElements(By.className("listTitle")); //Lista los Elementos de arriba
	for(WebElement a:compBefore) {
		if(a.getText().toLowerCase().startsWith("productos")) { 
			a.click();}} //Baja hasta productos.
	sleep(1000);
	//campos a Verificar
	String[] camposaVerificar= {"Acción","Producto","Cantidad","Moneda","Precio de venta","Cargos Totales por Mes","Plazo (meses)",
			"Total Mes por Plazo","Cargo unica vez","Cargo por única vez total","Precio Total Contrato"};
	List<String> listaComparativa = new ArrayList<String>();
	boolean check=true;
	//Elemento con el campo
	List<WebElement> campos= driver.findElement(By.xpath("//*[@id=\"0063F000002UkUu_RelatedLineItemList_body\"]/table/tbody/tr[1]")).
			findElements(By.tagName("th"));
	for(WebElement a:campos) {
		System.out.println(a.getText());
		listaComparativa.add(a.getText());
		}
	for(String a:camposaVerificar) {
		if(!(listaComparativa.contains(a)))
			check=false;
		}
	assertTrue(check);
	}
	
	/**
	 * Verifica que en oportunidades, en el bloque Valorizado de la oportunidad, se muestren los campos.
	 */
	@Test(retryAnalyzer = Tests.retry.class , groups= "SCP")
	public void TS112664_CRM_SCP_Estructura_de_las_oportunidades_Bloques_Valorizado_de_la_oportunidad() {
		SCP page=new SCP(driver);
	    if(page.goToOportunity()) {
	    	sleep(3000);
	    	boolean tAP=false, tAD=false,tCuP=false,tCuD,mCP=false,mCD=false,mtc=false,impE=false,cDB=false;
	    	//List<WebElement> valorizado=driver.findElements(By.cssSelector(".brandTertiaryBrd.pbSubheader.tertiaryPalette"));
	    	List <WebElement> valorizado = driver.findElements(By.className("labelCol")); //Lista los Elementos de arriba
	    	valorizado.addAll(driver.findElements(By.cssSelector(".last.labelCol")));
	    	for(WebElement a:valorizado) {
	    		System.out.println(a.getText());
	    		if(a.getText().toLowerCase().startsWith("total abono (arg)"))
	    			tAP=true;
	    		if(a.getText().toLowerCase().startsWith("total abono (usd)"))
	    			tAD=true;
	    		if(a.getText().toLowerCase().startsWith("total cuv (arg)"))
	    			tCuP=true;
	    		if(a.getText().toLowerCase().startsWith("monto contrato (arg)"))
	    			mCP=true;
	    		if(a.getText().toLowerCase().startsWith("monto contrato (usd)"))
	    			mCD=true;
	    		if(a.getText().toLowerCase().startsWith("monto total contrato (arg)"))
	    			mtc=true;
	    		if(a.getText().toLowerCase().startsWith("impacto ejercicio actual (arg)"))
	    			impE=true;
	    		if(a.getText().toLowerCase().startsWith("dolar budget"))
	    			cDB=true;
	    		if(tAP&&tAD&&tCuP&&mCP&&mCD&&mtc&&impE&&cDB)
	    			break;
	    	}
	    	assertTrue(tAP);
	    	assertTrue(tAD);
	    	assertTrue(tCuP);
	    	assertTrue(mCP);
	    	assertTrue(mCD);
	    	assertTrue(mtc);
	    	assertTrue(impE);
	    	assertTrue(cDB);
	    }
	    else {System.out.println("Oportunidad no disponible, prueba no ejecutada");assertTrue(false);}	
	}
	
	/**
	 * Verifica que el monto total del contrato total en dolares, se igual a la formula
	 * totalcuv+(cantidad*plazo*cargopormes)
	 */
	@Test(retryAnalyzer = Tests.retry.class , groups= "SCP")
	public void TS112667_CRM_SCP_Estructura_de_las_oportunidades_Bloques_Valorizado_de_la_oportunidad_Monto_Contrato_USD() {
	SCP page=new SCP(driver);
	page.clickOnTabByName("oportunidades");
	sleep(3000);
	Select sOp=new Select(driver.findElement(By.id("fcf")));
	sOp.selectByVisibleText("Todas las oportunidades");
	sleep(200);
	driver.findElement(By.name("go")).click();
	sleep(2000);
	List<WebElement> lOp=driver.findElements(By.className("x-grid3-row"));
	lOp.add(driver.findElement(By.cssSelector(".x-grid3-row.x-grid3-row-first")));
	boolean flag=false;
	for(WebElement nOp: lOp) {
		List<WebElement> tagsOp=nOp.findElements(By.tagName("a"));
		for(WebElement nombre:tagsOp) {
		if(nombre.getText().toLowerCase().contains("alta sucursal jujuy")) {
			nombre.click();
			flag=true;
			break;}
			}
		if(flag)
			break;
		}
	sleep(3000);
		
	double totalCuvD, cantidad, plazo, cargopormes, montoContratoD;
	//WebElement temporal;
	//temporal=driver.findElement(By.xpath("//*[@id=\"0063F000002UjvW_RelatedLineItemList_body\"]/table/tbody/tr[2]/td[6]"));
	//System.out.println(temporal.getText());
	
	montoContratoD=Double.parseDouble(limpiarValor(driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[6]/table/tbody/tr[3]/td[4]")).getText()));
	
	totalCuvD=Double.parseDouble(limpiarValor(driver.findElement(By.xpath("//*[@id=\"0063F000002UjvW_RelatedLineItemList_body\"]/table/tbody/tr[2]/td[9]")).getText()));
	cantidad=Double.parseDouble(limpiarValor(driver.findElement(By.xpath("//*[@id=\"0063F000002UjvW_RelatedLineItemList_body\"]/table/tbody/tr[2]/td[2]")).getText()));
	plazo=Double.parseDouble(limpiarValor(driver.findElement(By.xpath("//*[@id=\"0063F000002UjvW_RelatedLineItemList_body\"]/table/tbody/tr[2]/td[6]")).getText()));
	cargopormes=Double.parseDouble(limpiarValor(driver.findElement(By.xpath("//*[@id=\"0063F000002UjvW_RelatedLineItemList_body\"]/table/tbody/tr[2]/td[5]")).getText()));
	
	assertTrue(((cantidad*plazo*cargopormes)+totalCuvD)==montoContratoD);
	}
	
	/**
	 * Se Verifica que el monto total del contrato en peso, se igual a la formula
	 * montocontrato en pesos + (monto contrado en dolares *dolar budget)
	 */
	@Test(retryAnalyzer = Tests.retry.class , groups= "SCP")
	public void TS112670_CRM_SCP_Estructura_de_las_oportunidades_Bloques_Valorizado_de_la_oportunidad_Total_Contrato_Pesos() {
	SCP page=new SCP(driver);
	page.clickOnTabByName("oportunidades");
	sleep(3000);
	Select sOp=new Select(driver.findElement(By.id("fcf")));
	sOp.selectByVisibleText("Todas las oportunidades");
	sleep(200);
	driver.findElement(By.name("go")).click();
	sleep(2000);
	List<WebElement> lOp=driver.findElements(By.className("x-grid3-row"));
	lOp.add(driver.findElement(By.cssSelector(".x-grid3-row.x-grid3-row-first")));
	boolean flag=false;
	for(WebElement nOp: lOp) {
		List<WebElement> tagsOp=nOp.findElements(By.tagName("a"));
		for(WebElement nombre:tagsOp) {
		if(nombre.getText().toLowerCase().contains("alta sucursal jujuy")) {
			nombre.click();
			flag=true;
			break;}
			}
		if(flag)
			break;
		}
	sleep(3000);
	double montoContratoTotalP, montoContratoP, MontoContratoD, dolar;
	
	//WebElement temporal;
	//temporal=driver.findElement(By.xpath("//*[@id=\"0063F000002UjvW_RelatedLineItemList_body\"]/table/tbody/tr[2]/td[6]"));
	//System.out.println(temporal.getText());
	
	montoContratoTotalP=Double.parseDouble(limpiarValor(driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[6]/table/tbody/tr[4]/td[2]")).getText()));
	
	montoContratoP=Double.parseDouble(limpiarValor(driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[6]/table/tbody/tr[3]/td[2]")).getText()));
	MontoContratoD=Double.parseDouble(limpiarValor(driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[6]/table/tbody/tr[3]/td[4]")).getText()));
	dolar=Double.parseDouble(limpiarValor(driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[6]/table/tbody/tr[5]/td[2]")).getText()));
	
	assertTrue(((MontoContratoD*dolar)+montoContratoP)==montoContratoTotalP);
	}
	
	/**
	 * Verifica que el monto total del contrato total en dolares, se igual a la formula
	 * totalcuv+(cantidad*plazo*cargopormes)
	 */
	@Test(retryAnalyzer = Tests.retry.class , groups= "SCP")
	public void TS112666_CRM_SCP_Estructura_de_las_oportunidades_Bloques_Valorizado_de_la_oportunidad_Monto_Contrato_Pesos() {
	SCP page=new SCP(driver);
	page.clickOnTabByName("oportunidades");
	sleep(3000);
	Select sOp=new Select(driver.findElement(By.id("fcf")));
	sOp.selectByVisibleText("Todas las oportunidades");
	sleep(200);
	driver.findElement(By.name("go")).click();
	sleep(2000);
	List<WebElement> lOp=driver.findElements(By.className("x-grid3-row"));
	lOp.add(driver.findElement(By.cssSelector(".x-grid3-row.x-grid3-row-first")));
	boolean flag=false;
	for(WebElement nOp: lOp) {
		List<WebElement> tagsOp=nOp.findElements(By.tagName("a"));
		for(WebElement nombre:tagsOp) {
		if(nombre.getText().toLowerCase().contains("alta sucursal jujuy")) {
			nombre.click();
			flag=true;
			break;}
			}
		if(flag)
			break;
		}
	sleep(3000);
		
	double totalCuvP, cantidad, plazo, cargopormes, montoContratoP;
	//WebElement temporal;
	//temporal=driver.findElement(By.xpath("//*[@id=\"0063F000002UjvW_RelatedLineItemList_body\"]/table/tbody/tr[2]/td[6]"));
	//System.out.println(temporal.getText());

	montoContratoP=Double.parseDouble(limpiarValor(driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[6]/table/tbody/tr[3]/td[2]")).getText()));

	totalCuvP=Double.parseDouble(limpiarValor(driver.findElement(By.xpath("//*[@id=\"0063F000002UjvW_RelatedLineItemList_body\"]/table/tbody/tr[3]/td[9]")).getText()));
	cantidad=Double.parseDouble(limpiarValor(driver.findElement(By.xpath("//*[@id=\"0063F000002UjvW_RelatedLineItemList_body\"]/table/tbody/tr[3]/td[2]")).getText()));
	plazo=Double.parseDouble(limpiarValor(driver.findElement(By.xpath("//*[@id=\"0063F000002UjvW_RelatedLineItemList_body\"]/table/tbody/tr[3]/td[6]")).getText()));
	cargopormes=Double.parseDouble(limpiarValor(driver.findElement(By.xpath("//*[@id=\"0063F000002UjvW_RelatedLineItemList_body\"]/table/tbody/tr[3]/td[5]")).getText()));
	
	assertTrue(((cantidad*plazo*cargopormes)+totalCuvP)==montoContratoP);
	}
	

	/**
	 * Verifica que la suma de precio de venta (abono) en PESOS que aparece en producto, sea igual al total abono en PESOS
	 * en el bloque de Valorizado de la oportunidad
	 */
	@Test(retryAnalyzer = Tests.retry.class , groups= "SCP")
	public void TS112669_CRM_SCP_Estructura_de_las_oportunidades_Bloques_Valorizado_de_la_oportunidad_Total_Abono_PESOS() {
	SCP page=new SCP(driver);
	page.clickOnTabByName("oportunidades");
	sleep(3000);
	Select sOp=new Select(driver.findElement(By.id("fcf")));
	sOp.selectByVisibleText("Todas las oportunidades");
	sleep(200);
	driver.findElement(By.name("go")).click();
	sleep(2000);
	List<WebElement> lOp=driver.findElements(By.className("x-grid3-row"));
	lOp.add(driver.findElement(By.cssSelector(".x-grid3-row.x-grid3-row-first")));
	boolean flag=false;
	for(WebElement nOp: lOp) {
		List<WebElement> tagsOp=nOp.findElements(By.tagName("a"));
		for(WebElement nombre:tagsOp) {
		if(nombre.getText().toLowerCase().contains("alta sucursal tucuman")) {
			nombre.click();
			flag=true;
			break;}
			}
		if(flag)
			break;
		}
	sleep(3000);
	double montoAbono=0.0;
	double totalAbonoP=Double.parseDouble(limpiarValor(driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[6]/table/tbody/tr[1]/td[2]")).getText()));
	List<WebElement> abono=driver.findElements(By.xpath("//*[@id=\"0063F000002Uk8Q_RelatedLineItemList_body\"]/table/tbody/tr"));
	abono.remove(0);
	for(WebElement ab:abono) {
			//System.out.println(ab.getText());
			//System.out.println(ab.findElements(By.tagName("td")).get(2).getText());
			if(ab.findElements(By.tagName("td")).get(2).getText().startsWith("ARG")) {
				//System.out.println(ab.findElements(By.tagName("td")).get(3).getText());
				montoAbono=montoAbono+Double.parseDouble(limpiarValor(ab.findElements(By.tagName("td")).get(3).getText()));
				}
		}
	//System.out.println("Suma de Montos: "+montoAbono+" TotalAbono: "+totalAbonoP);
	assertTrue(montoAbono==totalAbonoP);
	}
	
	/**
	 * Verifica que la suma de precio de venta (abono) en USD que aparece en producto, sea igual al total abono en USD
	 * en el bloque de Valorizado de la oportunidad
	 */
	@Test(retryAnalyzer = Tests.retry.class , groups= "SCP")
	public void TS112668_CRM_SCP_Estructura_de_las_oportunidades_Bloques_Valorizado_de_la_oportunidad_Total_Abono_Dolares() {
	SCP page=new SCP(driver);
	page.clickOnTabByName("oportunidades");
	sleep(3000);
	Select sOp=new Select(driver.findElement(By.id("fcf")));
	sOp.selectByVisibleText("Todas las oportunidades");
	sleep(200);
	driver.findElement(By.name("go")).click();
	sleep(2000);
	List<WebElement> lOp=driver.findElements(By.className("x-grid3-row"));
	lOp.add(driver.findElement(By.cssSelector(".x-grid3-row.x-grid3-row-first")));
	boolean flag=false;
	for(WebElement nOp: lOp) {
		List<WebElement> tagsOp=nOp.findElements(By.tagName("a"));
		for(WebElement nombre:tagsOp) {
		if(nombre.getText().toLowerCase().contains("alta sucursal tucuman")) {
			nombre.click();
			flag=true;
			break;}
			}
		if(flag)
			break;
		}
	sleep(3000);
	double montoAbono=0.0;
	double totalAbonoD=Double.parseDouble(limpiarValor(driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[6]/table/tbody/tr[1]/td[4]")).getText()));
	List<WebElement> abono=driver.findElements(By.xpath("//*[@id=\"0063F000002Uk8Q_RelatedLineItemList_body\"]/table/tbody/tr"));
	abono.remove(0);
	for(WebElement ab:abono) {
			//System.out.println(ab.getText());
			//System.out.println(ab.findElements(By.tagName("td")).get(2).getText());
			if(ab.findElements(By.tagName("td")).get(2).getText().startsWith("USD")) {
				//System.out.println(ab.findElements(By.tagName("td")).get(3).getText());
				montoAbono=montoAbono+Double.parseDouble(limpiarValor(ab.findElements(By.tagName("td")).get(3).getText()));
				}
		}
	//System.out.println("Suma de Montos: "+montoAbono+" TotalAbono: "+totalAbonoP);
	assertTrue(montoAbono==totalAbonoD);
	}

	
	/**
	 * Verifica que la sumatoria de todos los CUVS (cargos unica venta) en dolares sea igual al total CUVs del apartado
	 * Valorizado de la oportunidad.
	 */
	@Test(retryAnalyzer = Tests.retry.class , groups= "SCP")
	public void TS112671_CRM_SCP_Estructura_de_las_oportunidades_Bloques_Valorizado_de_la_oportunidad_Total_CUV_Dolares() {
	SCP page=new SCP(driver);
	page.clickOnTabByName("oportunidades");
	sleep(3000);
	Select sOp=new Select(driver.findElement(By.id("fcf")));
	sOp.selectByVisibleText("Todas las oportunidades");
	sleep(200);
	driver.findElement(By.name("go")).click();
	sleep(2000);
	List<WebElement> lOp=driver.findElements(By.className("x-grid3-row"));
	lOp.add(driver.findElement(By.cssSelector(".x-grid3-row.x-grid3-row-first")));
	boolean flag=false;
	for(WebElement nOp: lOp) {
		List<WebElement> tagsOp=nOp.findElements(By.tagName("a"));
		for(WebElement nombre:tagsOp) {
		if(nombre.getText().toLowerCase().contains("alta sucursal tucuman")) {
			nombre.click();
			flag=true;
			break;}
			}
		if(flag)
			break;
		}
	sleep(3000);
	
	double totalCuv=Double.parseDouble(limpiarValor(driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[6]/table/tbody/tr[2]/td[4]")).getText()));
	double cuvs=0.0;
	List<WebElement> abono=driver.findElements(By.xpath("//*[@id=\"0063F000002Uk8Q_RelatedLineItemList_body\"]/table/tbody/tr"));
	abono.remove(0);
	for(WebElement ab:abono) {
			//System.out.println(ab.getText());
			//System.out.println(ab.findElements(By.tagName("td")).get(2).getText());
			if(ab.findElements(By.tagName("td")).get(2).getText().startsWith("USD")) {
				//System.out.println(ab.findElements(By.tagName("td")).get(3).getText());
				cuvs=cuvs+Double.parseDouble(limpiarValor(ab.findElements(By.tagName("td")).get(8).getText()));
				}
		}
	
	assertTrue(totalCuv==cuvs);
	}

	/**
	 * Verifica que la sumatoria de todos los CUVS (cargos unica venta) en Pesos sea igual al total CUVs del apartado
	 * Valorizado de la oportunidad.
	 */
	@Test(retryAnalyzer = Tests.retry.class , groups= "SCP")
	public void TS112672_CRM_SCP_Estructura_de_las_oportunidades_Bloques_Valorizado_de_la_oportunidad_Total_CUV_Pesos() {
	SCP page=new SCP(driver);
	page.clickOnTabByName("oportunidades");
	sleep(3000);
	Select sOp=new Select(driver.findElement(By.id("fcf")));
	sOp.selectByVisibleText("Todas las oportunidades");
	sleep(200);
	driver.findElement(By.name("go")).click();
	sleep(2000);
	List<WebElement> lOp=driver.findElements(By.className("x-grid3-row"));
	lOp.add(driver.findElement(By.cssSelector(".x-grid3-row.x-grid3-row-first")));
	boolean flag=false;
	for(WebElement nOp: lOp) {
		List<WebElement> tagsOp=nOp.findElements(By.tagName("a"));
		for(WebElement nombre:tagsOp) {
		if(nombre.getText().toLowerCase().contains("alta sucursal tucuman")) {
			nombre.click();
			flag=true;
			break;}
			}
		if(flag)
			break;
		}
	sleep(3000);
	
	double totalCuv=Double.parseDouble(limpiarValor(driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[6]/table/tbody/tr[2]/td[2]")).getText()));
	double cuvs=0.0;
	List<WebElement> abono=driver.findElements(By.xpath("//*[@id=\"0063F000002Uk8Q_RelatedLineItemList_body\"]/table/tbody/tr"));
	abono.remove(0);
	for(WebElement ab:abono) {
			//System.out.println(ab.getText());
			//System.out.println(ab.findElements(By.tagName("td")).get(2).getText());
			if(ab.findElements(By.tagName("td")).get(2).getText().startsWith("ARG")) {
				//System.out.println(ab.findElements(By.tagName("td")).get(3).getText());
				cuvs=cuvs+Double.parseDouble(limpiarValor(ab.findElements(By.tagName("td")).get(8).getText()));
				}
		}
	
	assertTrue(totalCuv==cuvs);
	}
}

