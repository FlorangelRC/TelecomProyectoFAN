package Tests;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
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

import Pages.BasePage;
import Pages.setConexion;

public class GestionFlow extends TestBase {
	
	private WebDriver driver;
	
	//Befores & Afters
	
	@BeforeClass(alwaysRun=true)
	public void init() {
		driver = setConexion.setupEze();
		sleep(5000);
		loginflow(driver);
		driver.switchTo().defaultContent();
		sleep(3000);
		
	}
	
	//@AfterMethod(alwaysRun=true)
	public void backToTheInicio() throws Exception {
		driver.findElement(By.className("ui-tabs-anchor")).click();
		sleep(10000);
	}

	//@AfterClass(alwaysRun=true)
	public void quit() throws IOException {
		driver.quit();
		sleep(5000);
	}
	
	@Test (groups = {"Flow","E2E"})
	public void FlowServiciosActivos (String sLinea){
	WebElement consul = driver.findElement(By.cssSelector(".nav-link.dropdown-toggle.linksMenu"));
		if(consul.getText().toLowerCase().equals("consultas")){
			consul.click();
		}
	driver.findElements(By.cssSelector(".dropdown-item.navbarItemPersonalizado.itemClick")).get(0).click();
	sleep(5000);
	driver.switchTo().frame(cambioFrame(driver, By.id("frameizquierdo")));
	driver.findElement(By.name("btnConsultar")).click();
	sleep(5000);
	WebElement txt = driver.findElement(By.id("txtCampo"));
	txt.click();
	txt.sendKeys("2932449333");  
	driver.findElement(By.name("btnConsultar")).click();
	sleep(7500);
	driver.switchTo().frame(cambioFrame(driver, By.id("framederecho"))); 
	List<WebElement> box = driver.findElements(By.cssSelector(".box.efecto3"));
		for(WebElement b : box){
			if(b.getText().toLowerCase().contains("servicios activos")){
				b.click();
				break;
			}
		}
	sleep(8000);
	driver.switchTo().frame(cambioFrame(driver, By.id("laAyuda")));
	ArrayList<String> txt1 = new ArrayList<String>();
	ArrayList<String> txt2 = new ArrayList<String>();
	txt2.add("Llamada en Espera");
	txt2.add("Conferencia Multipartita");
	txt2.add("Identificaci\u00f3n de Llamadas");
	txt2.add("Transferencias de Llamadas");
	txt2.add("Notificaci\u00f3n de mensajes de voz y Recepci\u00f3n de SMS");
	txt2.add("Mensaje Personal - Env\u00edo y Recepci\u00f3n de SMS");
	txt2.add("Servicio de voz y datos - GPRS Activo");
	txt2.add("Video Call");
	txt2.add("Contestador Personal");
	txt2.add("Discado Directo Internacional con Roaming Int.(solo Con transferencia internacional)");
	txt2.add("Servicio MMS");
	//txt2.add("Servicio 4G Nunca Registrado");
	List<WebElement> serv = driver.findElement(By.id("laAyuda")).findElements(By.tagName("font"));
		for(WebElement e: serv){
			if(e.getAttribute("color").equals("#5065BC")){
				txt1.add(e.getText());
				System.out.println(e.getText());
			}
		}
	Assert.assertTrue(txt2.containsAll(txt1));
	}
	
	
	@Test (groups = {"Flow","E2E"})
	public String FlowIMSI (String sLinea){
	WebElement consul = driver.findElement(By.cssSelector(".nav-link.dropdown-toggle.linksMenu"));
		if(consul.getText().toLowerCase().equals("consultas")){
			consul.click();
		}
	driver.findElements(By.cssSelector(".dropdown-item.navbarItemPersonalizado.itemClick")).get(0).click();
	sleep(5000);
	driver.switchTo().frame(cambioFrame(driver, By.id("frameizquierdo")));
	driver.findElement(By.name("btnConsultar")).click();
	sleep(5000);
	WebElement txt = driver.findElement(By.id("txtCampo"));
	txt.click();
	txt.sendKeys(sLinea);  
	driver.findElement(By.name("btnConsultar")).click();
	sleep(7500);
	driver.switchTo().frame(cambioFrame(driver, By.id("framederecho"))); 
	List<WebElement> box = driver.findElements(By.cssSelector(".box.efecto3"));
		for(WebElement b : box){
			if(b.getText().toLowerCase().contains("imsi:")){
				b.click();
				break;
			}
		}
	sleep(8000);
	String nimsi;
	driver.switchTo().frame(cambioFrame(driver, By.id("laAyuda")));
	System.out.println(driver.findElement(By.id("laAyuda")).getText());
	nimsi = driver.findElement(By.id("laAyuda")).getText();
	nimsi = nimsi.split(" ")[2];
	System.out.println(nimsi);
	return(nimsi);
	}
	
	
}