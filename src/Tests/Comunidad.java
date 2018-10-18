package Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.html.impl.SelectableTextInput;

import Pages.BasePage;
import Pages.setConexion;

public class Comunidad extends TestBase {
	
	private WebDriver driver;
	
	//Befores & Afters
	@BeforeClass(alwaysRun=true)
	public void init() {
		driver = setConexion.setupEze();
		sleep(5000);
		loginCommunity(driver);
		driver.switchTo().defaultContent();
		sleep(3000);
		
	}
	
	//@AfterMethod(alwaysRun=true)
	public void backToTheInicio() throws Exception {
		driver.findElement(By.className("slds-container_fluid")).click();
		sleep(10000);
	}

	//@AfterClass(alwaysRun=true)
	public void quit() throws IOException {
		driver.quit();
		sleep(5000);
	}
	
	@Test (groups = {"Communities","E2E"})
	public void CRM_PRE_Community_Desktop_Pagina_Servicios_Assets(){
		List <WebElement> gest = driver.findElements(By.cssSelector(".via-slds.ta-community-services"));
			boolean aa = false;
			for(WebElement g : gest){
				if(g.getText().toLowerCase().equals("mis gestiones")){
					g.isDisplayed();
					aa = true;
				}
			}
	}
	
	@Test (groups = {"Communities","E2E"})
	public void CRM_PRE_Community_Desktop_Mis_gestiones_Filtro_Tipo(){
	BasePage cambioFrameByID=new BasePage();
	driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".vlocity.via-slds")));
	sleep (8000);
	buscarYClick(driver.findElements(By.cssSelector(".slds-col.slds-size--1-of-1")), "equals", "plan con tarjeta repro");
	sleep (8000);
	driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("containergestiones")));
	((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("containergestiones")).getLocation().y+")");
	buscarYClick(driver.findElements(By.className("slds-grid")),"equals", "mis gestiones");
	driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".via-slds.slds-m-around--small.ng-scope")));
	driver.findElement(By.id("text-input-03")).click();
	driver.findElement(By.cssSelector(".slds-dropdown.slds-dropdown--left.resize-dropdowns")).findElements(By.tagName("li")).get(1).click();
	WebElement gestiones =driver.findElement(By.xpath("/html/body/div[1]/div[1]/ng-include/div/div/div[2]/div[4]/div[2]")).findElement(By.tagName("button"));
	gestiones.click();
	Assert.assertTrue(gestiones.isDisplayed());
	
		}
	
	
	}
	

