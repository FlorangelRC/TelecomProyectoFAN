package Pages;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.BasePage;
import Pages.Marketing;
import Pages.OM;
import Pages.SalesBase;
import Pages.setConexion;
import Tests.TestBase;

public class compraPackPerfilTelefonico extends TestBase{
	
	
	
	public compraPackPerfilTelefonico(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void buscarAssert() {
	sleep(8000);
	driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
	sleep(20000);
	driver.switchTo().frame(cambioFrame(driver, By.className("card-top"))); 
	sleep(20000);
	driver.findElement(By.className("card-top")).click(); 
	sleep(12000); 
	}
	
	
	public void comprarPack(String pack) {
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".items-card.ng-not-empty.ng-valid")));
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".slds-grid.community-flyout-content")).getLocation().y+")");
		List<WebElement> comprar = driver.findElements(By.className("community-flyout-grid-items-card"));
		for (WebElement comp : comprar) {
			if (comp.getText().toLowerCase().contains(pack)) {
				comp.findElement(By.tagName("button")).click();
				sleep(45000);
				break;
			}
		}		

	}
	
	public void agregarPack(String Pack1) {
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-button.cpq-item-has-children")));
		OMQPage OM = new OMQPage(driver);
		OM.agregarPack("Packs Opcionales", "Packs de Datos", Pack1, "", "");
		driver.findElement(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand")).click();
		sleep(45000);
		try{ 
		      driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")).get(1).click(); 
		      sleep(8000); 
		    }catch(Exception ex1){} 
		sleep(12000); 
	}
	
	public void tipoDePago(String tipodepago) {
	List<WebElement> tipodePago = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
	for (WebElement pago : tipodePago) {
		System.out.print(pago.getText().toLowerCase());
		if (pago.getText().toLowerCase().contains(tipodepago)) {
			pago.findElement(By.tagName("span")).click();
			sleep(8000);
			break;
		}
	}
	((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("SetPaymentType_nextBtn")).getLocation().y+")");
	driver.findElement(By.id("SetPaymentType_nextBtn")).click();
	sleep(10000);
	((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("SaleOrderMessages_nextBtn")).getLocation().y+")");
	sleep(15000);
	driver.findElement(By.id("SaleOrderMessages_nextBtn")).click();
		
	}
}