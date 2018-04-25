package Tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class BeFanTest {
	
	private WebDriver driver;
	
	@BeforeClass
	public void init() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("start-maximized");
	    driver = new ChromeDriver(options);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.get("http://befantest.personal.corp/#/signin");
	    driver.findElement(By.name("username")).sendKeys("BEF585991");
	    driver.findElement(By.name("txtPass")).sendKeys("Telecom02!");
	    driver.findElement(By.name("btnIngresar")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[3]/div[2]/div/div/ul/li[6]/a/b")).click();
	    driver.findElement(By.linkText("Importación")).click();
	    //driver.findElement(By.id("fileinput")).sendKeys("C:\\Pruebaautomatizacion.txt");
	    Thread.sleep(9000);
	    Select prefijo=new Select(driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-not-empty")));
	    prefijo.selectByVisibleText("351");
	    
	    driver.findElement(By.cssSelector(".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys("2");
	    List<WebElement> botones=driver.findElements(By.cssSelector(".btn.btn-primary"));
	    for(WebElement opcion:botones)
	    	if(opcion.getText().equalsIgnoreCase("Importar"))
	    		opcion.click();
	    
	    //Click aceptar
	    Thread.sleep(3000);
	    driver.findElement(By.linkText("Aceptar"));
	}
	@Test
	public void TS112047_BeFan_Movil_REPRO_Preactivacion_repro_Busqueda_de_archivos_Agente_Formato() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[3]/div[2]/div/div/ul/li[6]/a/b")).click();
	    driver.findElement(By.linkText("Gestión")).click();
	    Select estado=new Select(driver.findElement(By.cssSelector(".text.form-control.ng-valid.ng-not-empty.ng-dirty.ng-valid-parse.ng-touched")));
	   estado.selectByVisibleText("Procesado");
	}
	
	
}
