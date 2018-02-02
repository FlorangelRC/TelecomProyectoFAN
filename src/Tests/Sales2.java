package Tests;

import org.openqa.selenium.By;
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
import Pages.SalesBase;
import Tests.TestBase.IrA;

public class Sales2 extends TestBase{

	SalesBase sb;
	
	
	@BeforeClass
	public void init() {
		inicializarDriver();
		sb = new SalesBase(driver);
		loginAndres(driver);
		IrA.CajonDeAplicaciones.Ventas();
	}
	
	@BeforeMethod
	public void setup() throws Exception {
		sleep(5000);
		driver.findElement(By.xpath("//a[@href=\'https://crm--sit--c.cs14.visual.force.com/apex/taClientSearch']")).click();
		sleep(7000);
	}
	
	@AfterMethod
	public void deslogin() {
		sleep(3000);
		driver.get("https://crm--sit.cs14.my.salesforce.com/home/home.jsp?tsid=02u41000000QWha/");
		sleep(10000);
	}
	
	//@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	
}
