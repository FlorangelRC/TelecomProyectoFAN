package Tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.setConexion;

public class BeFANConfigurador extends TestBase {
	
	private WebDriver driver;
	
	private void irA(String sMenu,String sOpcion) {
		sleep(3000);
		List<WebElement> wMenu = driver.findElement(By.className("tpt-nav")).findElements(By.className("dropdown"));
		for (WebElement wAux : wMenu) {
			if (wAux.findElement(By.className("dropdown-toggle")).getText().toLowerCase().contains(sMenu.toLowerCase())) {
				wAux.click();
			}
		}
		
		
		
		List<WebElement> wOptions = driver.findElement(By.cssSelector(".dropdown.open")).findElement(By.className("multi-column-dropdown")).findElements(By.tagName("li"));
		for (WebElement wAux2 : wOptions) {
			if (wAux2.findElement(By.tagName("a")).getText().toLowerCase().contains(sOpcion.toLowerCase())) {
				wAux2.click();
				sleep(3000);
				break;
			}
		}
	}
	

	@BeforeClass (alwaysRun = true)
	public void init() {
		driver = setConexion.setupEze();
		loginBeFANConfigurador(driver);
	}
	
	//@AfterMethod (alwaysRun = true)
	public void after() {
		driver.get(TestBase.urlBeFAN);
		sleep(3000);
	}
	
	//@AfterClass (alwaysRun = true)
	public void quit() {
		driver.quit();
	}
	
	@Test (groups = "BeFAN")
	public void TS126620_BeFan_Movil_REPRO_Preactivacion_repro_Gestion_de_agrupadores_Busqueda_Busqueda_especifica() {
		irA("Regiones", "Gesti\u00f3n");
		sleep(3000);
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys("Bahia");
		
	}
}