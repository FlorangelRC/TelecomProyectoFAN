package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.setConexion;

public class BeFAN extends TestBase {
		
	private WebDriver driver;
	
	private void irA(String opcion) {
		WebElement menu = null;
		buscarYClick(driver.findElements(By.className("dropdown-toggle")), "contains", "sims");
		for (WebElement x : driver.findElements(By.className("col-sm-4"))) {
			if (x.getAttribute("ng-show").equals("headerCtrl.container.hasAccess(['sims_importacion', 'sims_gestion'])"))
				menu = x;
		}
		switch (opcion) {
		case "importacion":
			try {
				for (WebElement x : menu.findElements(By.tagName("a"))) {
					if (x.getText().toLowerCase().contains("importaci\u00f3n")) {
						x.click();
						sleep(3000);
					}
				}
			
			} catch (Exception e) {}
		case "gestion":
			try {
				for (WebElement x : menu.findElements(By.tagName("a"))) {
					if (x.getText().toLowerCase().contains("gesti\u00f3n")) {
						x.click();
						sleep(3000);
					}
				}
			
			} catch (Exception e) {}
		}
	}
	

	@BeforeClass
	public void init() {
		driver = setConexion.setupEze();
		loginBeFAN(driver);
	}
	
	@AfterMethod
	public void after() {
		driver.get(TestBase.urlBeFAN);
		sleep(3000);
	}
	
	//@AfterClass
	public void quit() {
		driver.quit();
	}
}
