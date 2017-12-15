package Tests;

import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.SCP;
import Pages.setConexion;

public class SCP_Joaquin extends TestBase{
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
	
	@AfterClass(groups = "SCP")
	public void teardown() {
		driver.quit();
		sleep(5000);
	}
	
	/*@Test(groups = "SCP")
	public void TS112727_Negocio_del_cliente_Chatter_contextualizado_Escribir_comentario() {
		SCP prueba= new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 2);
		WebElement chatter = driver.findElement(By.xpath("//textarea[@id='publishereditablearea']"));
		chatter.click();
		sleep(8000);
		driver.findElement(By.xpath("//div[@class='publisherTextAreaInner']")).findElement(By.tagName("input")).sendKeys("Comentario para TS112727");
	}*/
	
	private boolean isFileDownloaded_Ext(String dirPath, String ext){
		boolean flag=false;
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        flag = false;
	    }
	    
	    for (int i = 1; i < files.length; i++) {
	    	if(files[i].getName().contains(ext)) {
	    		flag=true;
	    	}
	    }
	    return flag;
	}
	
	@Test(groups = "SCP")
	public void TS112735_Negocio_del_cliente_Descripción_del_Cliente() {
		SCP prueba= new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 2);
		WebElement label = driver.findElements(By.cssSelector(".botones")).get(0);
		label.click();
		sleep(2000);
		
		Assert.assertTrue(label.getText().contains("Descripción del Cliente"));
	}
	
	@Test(groups = "SCP")
	public void TS112742_Negocio_del_cliente_Exportar_a_Excel() {
		SCP prueba= new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 2);
		driver.findElements(By.cssSelector(".botones")).get(4).click();
		sleep(2000);
		driver.findElements(By.xpath("//button[@class='btn btn-default btn-sm']")).get(1).click();
		sleep(3000);
		
		Assert.assertTrue(isFileDownloaded_Ext("C:\\Users\\Joaquin\\Downloads\\", ".xls"));
	}
	
	@Test(groups = "SCP")
	public void TS112743_Negocio_del_cliente_Guardar() {
		SCP prueba= new SCP(driver);
		prueba.moveToElementOnAccAndClick("primerTitulo", 2);
		driver.findElements(By.cssSelector(".botones")).get(4).click();
		sleep(2000);
		int i = 0;
		List<WebElement> tablas = driver.findElements(By.cssSelector(".table.table-striped.table-bordered.table-condensed"));
		for (WebElement t : tablas) {
			if (t.isDisplayed()) {
				WebElement tbody = t.findElement(By.tagName("tbody"));
				WebElement celda = tbody.findElement(By.tagName("span")).findElement(By.tagName("span"));
				Actions builder = new Actions(driver);
				builder.moveToElement(celda).doubleClick().build().perform();
				celda.findElement(By.tagName("input")).sendKeys("test");
				driver.findElement(By.xpath("//button[@class='btn btn-default btn-sm']")).click();
				sleep(2000);
				celda.findElement(By.tagName("input")).submit();
				break;
			}
			i++;
		}
		driver.findElements(By.cssSelector(".botones")).get(4).click();
		sleep(2000);
		List<WebElement> tablas2 = driver.findElements(By.cssSelector(".table.table-striped.table-bordered.table-condensed"));
		WebElement tbody = tablas2.get(i).findElement(By.tagName("tbody"));
		WebElement celda = tbody.findElement(By.tagName("span")).findElement(By.tagName("span"));
		
		Assert.assertTrue(celda.getText().equalsIgnoreCase("test"));
	}
}
