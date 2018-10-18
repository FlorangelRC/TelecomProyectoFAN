package Tests;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
	@Test (groups = {"Communities","Desktop","E2E"})
	public void CRM_PRE_Community_Desktop_Pagina_Servicios_Assets(){
		List <WebElement> gest = driver.findElements(By.cssSelector(".via-slds.slds-p-bottom--xx-large.ta-community-services"));
			boolean aa = false;
			for(WebElement g : gest){
				if(g.getText().toLowerCase().equals("servicios")){
					g.isDisplayed();
					aa = true;
				}
			}
	}
	
	@Test (groups = {"Communities","Desktop","E2E"})
	public void CRM_PRE_Community_Desktop_Menu(){
		driver.findElement(By.className("profileName")).click();
		sleep(5000);
		System.out.println(driver.findElement(By.cssSelector(".home.uiMenuItem")).getText());
		Assert.assertTrue(driver.findElement(By.cssSelector(".home.uiMenuItem")).isDisplayed());
		System.out.println(driver.findElement(By.cssSelector(".profile.uiMenuItem")).getText());
		Assert.assertTrue(driver.findElement(By.cssSelector(".profile.uiMenuItem")).isDisplayed());
		System.out.println(driver.findElement(By.cssSelector(".logOut.uiMenuItem")).getText());
		Assert.assertTrue(driver.findElement(By.cssSelector(".logOut.uiMenuItem")).isDisplayed());
	}
	
	@Test (groups = {"Communities","Mobile","E2E"})
	public void CRM_PRE_Community_Mobile_Menu(){
		driver.findElement(By.className("profileName")).click();
		sleep(5000);
		System.out.println(driver.findElement(By.cssSelector(".home.uiMenuItem")).getText());
		Assert.assertTrue(driver.findElement(By.cssSelector(".home.uiMenuItem")).isDisplayed());
		System.out.println(driver.findElement(By.cssSelector(".profile.uiMenuItem")).getText());
		Assert.assertTrue(driver.findElement(By.cssSelector(".profile.uiMenuItem")).isDisplayed());
		System.out.println(driver.findElement(By.cssSelector(".logOut.uiMenuItem")).getText());
		Assert.assertTrue(driver.findElement(By.cssSelector(".logOut.uiMenuItem")).isDisplayed());
	}
	
	@Test (groups = {"Communities","Mobile","E2E"})
	public void CRM_PRE_Community_Mobile_Modificar_datos_cliente(){
		driver.findElement(By.className("profileName")).click();
		sleep(3000);
		driver.findElement(By.cssSelector(".profile.uiMenuItem")).click();
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-col.slds-text-align--left")));
		List<WebElement> datos = driver.findElements(By.cssSelector(".slds-col.slds-text-align--left"));
			for(WebElement d : datos){
				if(d.getText().toLowerCase().contains("cambiar datos personales")){
					d.click();
				}
			}
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.id("FirstName")));
		Assert.assertTrue(driver.findElement(By.id("FirstName")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("LastName")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("DocumentNumber")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("Birthdate")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("Cuil")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("Gender")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("Email")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("MobilePhone")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("OtherPhone")).isEnabled());
		driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).click();
		driver.switchTo().frame(cambioFrame(driver, By.id("alert-ok-button")));
		driver.findElement(By.id("alert-ok-button")).click();
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-col.slds-text-align--left")));
		List<WebElement> pass = driver.findElements(By.cssSelector(".slds-col.slds-text-align--left"));
			for(WebElement p : pass){
				if(p.getText().toLowerCase().contains("cambiar contrase\u00f1a")){
					p.click();
				}
			}
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.className("topPanel")));
		Assert.assertTrue(driver.findElement(By.id("changePassword:theForm:oldpsw")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("changePassword:theForm:psw")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("changePassword:theForm:vpsw")).isEnabled());
		}
	
	@Test (groups = {"Communities","Desktop","E2E"})
	public void CRM_PRE_Community_Desktop_Modificar_datos_cliente(){
		driver.findElement(By.className("profileName")).click();
		sleep(3000);
		driver.findElement(By.cssSelector(".profile.uiMenuItem")).click();
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-col.slds-text-align--left")));
		List<WebElement> datos = driver.findElements(By.cssSelector(".slds-col.slds-text-align--left"));
			for(WebElement d : datos){
				if(d.getText().toLowerCase().contains("cambiar datos personales")){
					d.click();
				}
			}
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.id("FirstName")));
		Assert.assertTrue(driver.findElement(By.id("FirstName")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("LastName")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("DocumentNumber")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("Birthdate")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("Cuil")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("Gender")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("Email")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("MobilePhone")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("OtherPhone")).isEnabled());
		driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).click();
		driver.switchTo().frame(cambioFrame(driver, By.id("alert-ok-button")));
		driver.findElement(By.id("alert-ok-button")).click();
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-col.slds-text-align--left")));
		List<WebElement> pass = driver.findElements(By.cssSelector(".slds-col.slds-text-align--left"));
			for(WebElement p : pass){
				if(p.getText().toLowerCase().contains("cambiar contrase\u00f1a")){
					p.click();
				}
			}
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.className("topPanel")));
		Assert.assertTrue(driver.findElement(By.id("changePassword:theForm:oldpsw")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("changePassword:theForm:psw")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("changePassword:theForm:vpsw")).isEnabled());
		}
	
	@Test (groups = {"Communities","Mobile","E2E"})
	public void CRM_PRE_Community_Mobile_Mis_gestiones_Filtro_Tipo(){
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".vlocity.via-slds")));
		driver.findElement(By.cssSelector(".slds-grid.respon")).findElement(By.cssSelector(".slds-grid.slds-wrap")).click();
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.id("containergestiones")));
		List<WebElement> gestion = driver.findElements(By.cssSelector(".slds-grid"));
			for(WebElement g : gestion){
				if(g.getText().toLowerCase().equals("mis gestiones")){
					g.click();
				}
			}
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.id("text-input-03")));
		driver.findElement(By.id("text-input-03")).click();
		driver.findElement(By.cssSelector(".slds-dropdown.slds-dropdown--left.resize-dropdowns")).findElements(By.tagName("li")).get(1).click();;
		driver.findElement(By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small")).click();
		sleep(5000);
		System.out.println(driver.findElement(By.cssSelector(".slds-table.slds-table--bordered.slds-table--resizable-cols.slds-table--fixed-layout.via-slds-table-pinned-header")).getText());
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-table.slds-table--bordered.slds-table--resizable-cols.slds-table--fixed-layout.via-slds-table-pinned-header")).isDisplayed());
	}
	
}	
