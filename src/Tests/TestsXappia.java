package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import Pages.CustomerCare;
import Pages.setConexion;

public class TestsXappia extends TestBase {

	private WebDriver driver;
	private CustomerCare cc;
	
	public TestsXappia() {
		driver = setConexion.setupEze();
		cc = new CustomerCare(driver);
	}
		
	private void loginUAT() {
		driver.get("https://telecomcrm--uat.cs53.my.salesforce.com");
		sleep(2000);
		driver.findElement(By.id("idp_section_buttons")).click();
		sleep(2000);
		driver.findElement(By.name("Ecom_User_ID")).sendKeys("uat579805");
 		driver.findElement(By.name("Ecom_Password")).sendKeys("Testa10k");
 		driver.findElement(By.id("loginButton2")).click();
 		sleep(5000);
	}
	
	private void loginSIT() {
		driver.get("https://crm--sit.cs14.my.salesforce.com/");
		sleep(2000);
		driver.findElement(By.id("idp_section_buttons")).click();
		sleep(2000);
		driver.findElement(By.name("Ecom_User_ID")).sendKeys("UAT195528");
 		driver.findElement(By.name("Ecom_Password")).sendKeys("Testa10k");
 		driver.findElement(By.id("loginButton2")).click();
 		sleep(5000);
	}
	
	private void irAConsolaFAN() {
		try {
			cc.cajonDeAplicaciones("Consola FAN");
		} catch (Exception e) {
			driver.findElement(By.id("tabBar")).findElement(By.tagName("a")).click();
			sleep(8000);
		}
	}
	
	
	@AfterClass (alwaysRun = true)
	public void quit() {
		driver.quit();
	}
	
	
	@Test (groups = "UAT")
	public void Gestiones_Del_Panel_Izquierdo_En_Consola_FAN_En_Ambiente_UAT() {
		loginUAT();
		irAConsolaFAN();
		driver.switchTo().frame(cambioFrame(driver, By.className("slds-spinner_container")));
		WebElement gestiones = driver.findElement(By.className("slds-spinner_container"));
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0," + gestiones.getLocation().y + ")");
		Assert.assertTrue(!gestiones.getText().isEmpty());
	}
	
	@Test (groups = "SIT")
	public void SmokeTest_Tiempo_De_Carga_De_Consola_FAN_En_Ambiente_SIT() {
		loginSIT();
		
	}
}