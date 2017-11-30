package Tests;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.CustomerCare;
import Pages.customerInformation;
import Pages.setConexion;


public class PanelServiceActive extends TestBase {
	
	private WebDriver driver;
	
	
	//@AfterClass
	public void tearDown2() {
		driver.close();	
	}
	
	//@AfterMethod//(groups= "Fase2")
		public void alert (){
			CustomerCare page = new CustomerCare(driver);
			page.cerrarultimapestaña();
		

			try{
				Alert alert = driver.switchTo().alert();
				System.out.println(alert.getText());
			}catch(org.openqa.selenium.NoAlertPresentException e){}
		}

		@BeforeClass//(groups= "Fase2")
		public void init() throws Exception
		
		{
			CustomerCare page = new CustomerCare(driver);
			page.cerrarultimapestaña();
			this.driver = setConexion.setupEze();
			login(driver);
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			String a = driver.findElement(By.id("tsidLabel")).getText();
			driver.findElement(By.id("tsidLabel")).click();
			if(a.equals("Ventas"))
			{
				driver.findElement(By.xpath("//a[@href=\'/console?tsid=02uc0000000D6Hd\']")).click();
			}else
			{			driver.findElement(By.xpath("//a[@href=\'/home/home.jsp?tsid=02u41000000QWha\']")).click();
				try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
				driver.findElement(By.id("tsidLabel")).click();
				driver.findElement(By.xpath("//a[@href=\'/console?tsid=02uc0000000D6Hd\']")).click();
			}
		}	
		@BeforeMethod
		public void setup(){
			try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			CustomerCare page = new CustomerCare(driver);
			page.cerrarultimapestaña();
		}
		
	
	@Test
	public void TS7130_filterFuncionality(){
		CustomerCare page = new CustomerCare(driver);
		page.elegircuenta("Fernando Care");
		 BasePage cambioFrameByID=new BasePage();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.xpath("/html/body/span/div/div/ng-include/div/form/div/input")));
		driver.findElement(By.xpath("/html/body/span/div/div/ng-include/div/form/div/input")).sendKeys("inter");
		assertEquals (driver.findElement(By.xpath("/html/body/span/div/div/ng-include/div/div[2]/div[1]/ng-include/section/div[1]/div/h2")).getText(),"INTERNET");
		driver.switchTo().defaultContent();
		
	}
	
	@Test
	public void TS7129_showFilter()
	{
		CustomerCare page = new CustomerCare(driver);
		page.elegircuenta("Fernando Care");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

		 BasePage cambioFrameByID=new BasePage();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.xpath("/html/body/span/div/div/ng-include/div/form/div/input")));
		driver.findElement(By.xpath("/html/body/span/div/div/ng-include/div/form/div/input"));
		driver.switchTo().defaultContent();
	}
	
	@Test
	public void TS7131_showCardService()
	{
		CustomerCare page = new CustomerCare(driver);
		page.elegircuenta("Fernando Care");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

		 BasePage cambioFrameByID=new BasePage();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.xpath("/html/body/span/div/div/ng-include/div/form/div/input")));
		assertNotSame(driver.findElement(By.xpath("/html/body/span/div/div/ng-include/div/div[2]/div[1]/ng-include/section/div[1]/div/h2")).getText(), "NULL");
		driver.switchTo().defaultContent();
	}
	
	@Test
	public void TS7133_validationScroll(){
		CustomerCare page = new CustomerCare(driver);
		page.elegircuenta("Fernando Care");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page.usarpanelcentral("Detalles");
		 BasePage cambioFrameByID=new BasePage();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.xpath("//*[@id='contactHeaderRow']/div[2]")));
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		Boolean VertscrollStatus = (Boolean) javascript.executeScript("return document.documentElement.scrollHeight>document.documentElement.clientHeight;");
		assertTrue(VertscrollStatus);
		driver.switchTo().defaultContent();
	}
}
	
	


