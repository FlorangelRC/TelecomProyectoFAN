package Tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.SCP;
import Pages.setConexion;

public class SCPPrioritarios extends TestBase{
	private WebDriver driver;
	
	@BeforeClass(groups = "SCP")
	public void init() throws Exception
	{
		this.driver = setConexion.setupEze();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		loginSCPAdmin(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
	}
	
	@BeforeMethod(groups = "SCP")
	public void setup() {
		SCP pScp = new SCP(driver);
		pScp.goToMenu("scp");
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		pScp.clickOnTabByName("cuentas");
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	@AfterClass(groups = "SCP")
	public void tearDown() {
		driver.quit();
	}
	

		@Test(groups = "SCP")
		 public void TS116024_SCP_Crear_Cuenta() { 
		  SCP prueba = new SCP(driver);
		
		          driver.findElement(By.className("pbButton")).findElement(By.className("btn")).click();
		  		  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
		  		List<WebElement> campos = driver.findElement(By.className("pbSubsection")).findElements(By.tagName("tr"));
		  		  //nombre de la cuenta
		  		campos.get(2).findElements(By.tagName("td")).get(1).findElement(By.tagName("input")).sendKeys("APEREZ APEREZ");
		  		  //driver.findElement(By.id("acc2")).
		  		//razon social
		  		campos.get(2).findElements(By.tagName("td")).get(3).findElement(By.tagName("input")).sendKeys("APEREZ APEREZ");
		  		  //driver.findElement(By.id("00N3F000000JoSt")).sendKeys("APEREZ APEREZ");
		  		  //CUIT
		  		campos.get(3).findElements(By.tagName("td")).get(3).findElement(By.tagName("input")).sendKeys("30538068892");
		  		  //driver.findElement(By.id("00N3F000000JoSZ")).sendKeys("30538068899");
		  		  //numero de cliente
		  		campos.get(7).findElements(By.tagName("td")).get(1).findElement(By.tagName("input")).sendKeys("0000096392");
		  		  //driver.findElement(By.id("00N3F000000JoSf")).sendKeys("0000096399");
		  		  try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		  		  driver.findElement(By.name("save")).click();
		  		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
				prueba.clickOnTabByName("cuentas");
				try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		  	
		}
		
		/*@Test(groups = "Fase2")
		 public void TS_SCP_Crear_Cuenta() { 
		  SCP prueba = new SCP(driver);
		  String[] separado;
		  File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	      try {
	         archivo = new File ("C:\\Users\\Florangel\\Downloads\\clientes.txt");
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         // Lectura del fichero
	         String linea;
	         while((linea=br.readLine())!=null) {
		          separado = linea.split(",");
		          
		          driver.findElement(By.className("pbButton")).findElement(By.className("btn")).click();
		  		  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
		  		  //nombre de la cuenta
		  		  driver.findElement(By.id("acc2")).sendKeys(separado[0]);
		  		//razon social
		  		  driver.findElement(By.id("00N3F000000JoSt")).sendKeys(separado[1]);
		  		  //CUIT
		  		  driver.findElement(By.id("00N3F000000JoSZ")).sendKeys(separado[2]);
		  		  //numero de cliente
		  		  driver.findElement(By.id("00N3F000000JoSf")).sendKeys(separado[3]);
		  		  try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		  		  driver.findElement(By.name("save")).click();
		  		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
				prueba.clickOnTabByName("cuentas");
				try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		  		  
	         }
	      }
	      catch(Exception e){
	         e.printStackTrace();		  
		 }
		}*/
	
}
