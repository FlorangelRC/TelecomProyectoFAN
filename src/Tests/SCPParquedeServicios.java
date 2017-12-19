package Tests;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.Login;
import Pages.SCP;
import Pages.setConexion;

import static org.testng.Assert.assertTrue;

import java.awt.Button;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
public class SCPParquedeServicios extends TestBase{
	private WebDriver driver;
	String categoria = "Servicio automatizado";
	String servicio = "Prueba automatizada";
	String color = "Rojo";
	@BeforeClass(groups= "SCP")
	public void init() throws Exception
	{
		this.driver = setConexion.setupEze();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	loginSCPAdmin(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
		
	@BeforeMethod(groups= "SCP")
	public void setup(){
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		String a = driver.findElement(By.id("tsidLabel")).getText();
		driver.findElement(By.id("tsidLabel")).click();
		
		List <WebElement> mdls = driver.findElements(By.cssSelector(".menuButtonMenuLink"));

		if(a.equals("Ventas"))
		{			try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

			for(WebElement e: mdls){
				if(e.getText().toLowerCase().equals("scp")){
					e.click();
					}break;}
		}else{
			for(WebElement e: mdls){
				if(e.getText().toLowerCase().equals("ventas")){
					e.click();
					}break;}
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.findElement(By.id("tsidLabel")).click();
			for(WebElement e: mdls){
				if(e.getText().toLowerCase().equals("scp")){
					e.click();
					}break;}
		}
	}
	
//	@AfterMethod(groups= "SCP")
	public void after(){
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("home_Tab")).getLocation().y+")");
		driver.findElement(By.id("home_Tab")).click();
	}
	
	//@AfterClass(groups= "SCP")
	public void tearDown() {
		driver.quit();
		sleep(4000);
	}
	
	@Test(groups= "SCP")
	public void TS112781_Parque_de_Servicios_Agregar_Nuevo_Servicio(){
	
		SCP page = new SCP(driver);
		page.clickOnTabByName("cuentas");
		page.clickOnFirstAccRe();
		page.moveToElementOnAccAndClick("segundoTitulo", "//*[@id='segundoTitulo']/div/ul/li[2]/a");
		page.nuevoservicio(categoria, servicio, color);
		Assert.assertTrue(page.validarservicionuevo(categoria, servicio, color));
		
	}
	
	@Test(groups= "SCP")
	public void TS112782_Parque_de_Servicios_Borrar(){
	
		SCP page = new SCP(driver);
		page.clickOnTabByName("cuentas");
		page.clickOnFirstAccRe();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page.moveToElementOnAccAndClick("segundoTitulo", "//*[@id='segundoTitulo']/div/ul/li[2]/a");
		page.nuevoservicioEspecifico(categoria, servicio, color);
		Assert.assertTrue(page.validarservicioborrado(categoria, servicio, color));
	}
	
		
	@Test(groups= "SCP")
	public void TS112785_Parque_de_Servicios_Exportar_a_Excel(){
		
		SCP page = new SCP(driver);
		page.clickOnTabByName("cuentas");
		page.clickOnFirstAccRe();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page.moveToElementOnAccAndClick("segundoTitulo", "//*[@id='segundoTitulo']/div/ul/li[2]/a");
		page.servicioexportarexcel();
	}
		@Test(groups= "SCP")
		public void TS112786_Parque_de_Servicios_Guardar(){
			SCP page = new SCP(driver);
			page.clickOnTabByName("cuentas");
			page.clickOnFirstAccRe();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			page.moveToElementOnAccAndClick("segundoTitulo", "//*[@id='segundoTitulo']/div/ul/li[2]/a");
			page.servicioguardar();
		}
		
	@Test(groups= "SCP")
	public void TS112783_Parque_de_Servicios_Chatter_contextualizado_Escribir_comentario(){
			SCP page = new SCP(driver);
			page.clickOnTabByName("cuentas");
			page.clickOnFirstAccRe();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			page.moveToElementOnAccAndClick("segundoTitulo", "//*[@id='segundoTitulo']/div/ul/li[2]/a");
			page.comentarycompartir("Esto es un comentario");
			page.validarcomentario("Esto es un comentario");
	}
	@Test(groups= "SCP")
	public void TS112789_Plan_de_Acción_Chatter_contextualizado_Escribir_comentario(){
		SCP page = new SCP(driver);
		page.clickOnTabByName("cuentas");
		page.clickOnFirstAccRe();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page.moveToElementOnAccAndClick("cuartoTitulo", "//*[@id='cuartoTitulo']/div/ul/li[2]/a");
		page.comentarycompartir("Esto es un comentario");
		page.validarcomentario("Esto es un comentario");
	}
	@Test(groups= "SCP")
	public void TS112727_Negocio_del_cliente_Chatter_contextualizado_Escribir_comentario(){
		SCP page = new SCP(driver);
		page.clickOnTabByName("cuentas");
		page.clickOnFirstAccRe();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page.moveToElementOnAccAndClick("primerTitulo", "//*[@id='primerTitulo']/div/ul/li[2]/a");
		page.comentarycompartir("Esto es un comentario");
		page.validarcomentario("Esto es un comentario");
	}
	
	@Test(groups= "SCP")
	public void TS112587_Contexto_ectorial_Chatter_contextualizado_Escribir_comentario(){
		SCP page = new SCP(driver);
		page.clickOnTabByName("cuentas");
		page.clickOnFirstAccRe();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page.moveToElementOnAccAndClick("primerTitulo", "//*[@id='primerTitulo']/div/ul/li[1]/a");
		page.comentarycompartir("Esto es un comentario");
		page.validarcomentario("Esto es un comentario");
	}
	
	@Test(groups= "SCP")
	public void TS112718_Mosaico_de_Relacionamiento_por_Oportunidad_Chatter_contextualizado_Escribir_comentario(){
		SCP page = new SCP(driver);
		page.clickOnTabByName("cuentas");
		page.clickOnFirstAccRe();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page.moveToElementOnAccAndClick("tercerTitulo", "//*[@id='tercerTitulo']/div/ul/li[3]/a");
		page.comentarycompartir("Esto es un comentario");
		page.validarcomentario("Esto es un comentario");
	}
	
	@Test(groups= "SCP")
	public void TS112683_Matriz_de_Criterios_de_Decisión_Chatter_contextualizado_Escribir_comentario(){
		SCP page = new SCP(driver);
		page.clickOnTabByName("cuentas");
		page.clickOnFirstAccRe();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page.moveToElementOnAccAndClick("tercerTitulo", "//*[@id='tercerTitulo']/div/ul/li[2]/a");
		page.comentarycompartir("Esto es un comentario");
		page.validarcomentario("Esto es un comentario");
	}
	@Test(groups= "SCP")
	public void TS112614_Cronograma_de_Cuenta_Chatter_contextualizado_Escribir_comentario(){
		SCP page = new SCP(driver);
		page.clickOnTabByName("cuentas");
		page.clickOnFirstAccRe();//*[@id="tercerTitulo"]/div/ul/li[2]/a
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page.moveToElementOnAccAndClick("tercerTitulo", 1);
		page.comentarycompartir("Esto es un comentario");
		page.validarcomentario("Esto es un comentario");
	}
	
	@Test(groups= "SCP")
	public void TS112698_Mosaico_de_Relacionamiento_General_Chatter_contextualizado_Escribir_comentario(){
		SCP page = new SCP(driver);
		page.clickOnTabByName("cuentas");
		page.clickOnFirstAccRe();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page.moveToElementOnAccAndClick("tercerTitulo", 4);
		page.comentarycompartir("Esto es un comentario");
		page.validarcomentario("Esto es un comentario");
	}
	
	@Test(groups = "SCP") 
    public void TS112630_Estrategia_de_Crecimiento_Chatter_Contextualizado_Leer_Comentario_Escrito_Con_Otro_Usuario() { 
      SCP pcp = new SCP(driver); 
      java.util.Date fecha = new Date();
      System.out.println (fecha);
      pcp.Desloguear_Loguear_Comentar("admin", "fabiana", fecha.toString(), "tercerTitulo", 5); 
      try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   pcp.clickOnTabByName("cuentas");
   try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   pcp.clickEnCuentaPorNombre("Florencia Di Ci");
   try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   pcp.moveToElementOnAccAndClick("tercerTitulo", 5);
      pcp.validarcomentarioajeno(fecha.toString());
 }
	
	@Test(groups = "SCP") 
    public void TS112615_Cronograma_de_Cuenta_Chatter_Contextualizado_Leer_Comentario_Escrito_Con_Otro_Usuario() { 
      SCP pcp = new SCP(driver); 
      java.util.Date fecha = new Date();
      System.out.println (fecha);
      pcp.Desloguear_Loguear_Comentar("admin", "fabiana", fecha.toString(), "cuartoTitulo", 1); 
      try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   pcp.clickOnTabByName("cuentas");
   try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   pcp.clickEnCuentaPorNombre("Florencia Di Ci");
   try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   pcp.moveToElementOnAccAndClick("cuartoTitulo", 1);
      pcp.validarcomentarioajeno(fecha.toString());
 }
	
	@Test(groups = "SCP") 
    public void TS112675_Hitos_Relevantes_Chatter_Contextualizado_Leer_Comentario_Escrito_Con_Otro_Usuario() { 
      SCP pcp = new SCP(driver); 
      java.util.Date fecha = new Date();
      System.out.println (fecha);
      pcp.Desloguear_Loguear_Comentar("admin", "fabiana", fecha.toString(), "segundoTitulo", 3); 
      try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   pcp.clickOnTabByName("cuentas");
   try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   pcp.clickEnCuentaPorNombre("Florencia Di Ci");
   try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   pcp.moveToElementOnAccAndClick("segundoTitulo", 3);
      pcp.validarcomentarioajeno(fecha.toString());
 }
	
	@Test(groups = "SCP") 
    public void TS112684_Matriz_de_Criterios_de_Decisión_Chatter_Contextualizado_Leer_Comentario_Escrito_Con_Otro_Usuario() { 
      SCP pcp = new SCP(driver); 
      java.util.Date fecha = new Date();
      System.out.println (fecha);
      pcp.Desloguear_Loguear_Comentar("admin", "fabiana", fecha.toString(), "tercerTitulo", 2); 
      try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   pcp.clickOnTabByName("cuentas");
   try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   pcp.clickEnCuentaPorNombre("Florencia Di Ci");
   try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   pcp.moveToElementOnAccAndClick("tercerTitulo", 2);
      pcp.validarcomentarioajeno(fecha.toString());
 }
	
	@Test(groups = "SCP") 
    public void TS112699_Mosaico_de_Relacionamiento_General_Chatter_Contextualizado_Leer_Comentario_Escrito_Con_Otro_Usuario() { 
      SCP pcp = new SCP(driver); 
      java.util.Date fecha = new Date();
      System.out.println (fecha);
      pcp.Desloguear_Loguear_Comentar("admin", "fabiana", fecha.toString(), "segundoTitulo", 4); 
      try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   pcp.clickOnTabByName("cuentas");
   try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   pcp.clickEnCuentaPorNombre("Florencia Di Ci");
   try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   pcp.moveToElementOnAccAndClick("segundoTitulo", 4);
      pcp.validarcomentarioajeno(fecha.toString());
 }
	
	@Test(groups = "SCP") 
    public void TS112719_Mosaico_de_Relacionamiento_por_Oportunidad_Chatter_Contextualizado_Leer_Comentario_Escrito_Con_Otro_Usuario() { 
      SCP pcp = new SCP(driver); 
      java.util.Date fecha = new Date();
      System.out.println (fecha);
      pcp.Desloguear_Loguear_Comentar("admin", "fabiana", fecha.toString(), "tercerTitulo", 3); 
      try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   pcp.clickOnTabByName("cuentas");
   try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   pcp.clickEnCuentaPorNombre("Florencia Di Ci");
   try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   pcp.moveToElementOnAccAndClick("tercerTitulo", 3);
      pcp.validarcomentarioajeno(fecha.toString());
 }
	
	@Test(groups = "SCP") 
    public void TS112728_Negocio_del_cliente_Chatter_Contextualizado_Leer_Comentario_Escrito_Con_Otro_Usuario() { 
      SCP pcp = new SCP(driver); 
      java.util.Date fecha = new Date();
      System.out.println (fecha);
      pcp.Desloguear_Loguear_Comentar("admin", "fabiana", fecha.toString(), "primerTitulo", 2); 
      try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   pcp.clickOnTabByName("cuentas");
   try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   pcp.clickEnCuentaPorNombre("Florencia Di Ci");
   try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
   pcp.moveToElementOnAccAndClick("primerTitulo", 2);
      pcp.validarcomentarioajeno(fecha.toString());
 }
}
