package Tests;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import Pages.CustomerCare;
import Pages.setConexion;

public class GestionesPerfilOperativo extends TestBase {
	
	private WebDriver driver;
	private CustomerCare cc;
	String imagen;
	
	
	@BeforeClass (alwaysRun = true)
	public void init() {
		driver = setConexion.setupEze();
		sleep(5000);
		loginOperativo(driver);
		sleep(7000);
		cc = new CustomerCare(driver);
		cc.cajonDeAplicaciones("Ventas");
		sleep(5000);
		driver.findElement(By.className("wt-ManualQueue")).click();
		sleep(3000);
		WebElement inboxTecnico = null;
		for (WebElement x : driver.findElement(By.cssSelector(".bPageBlock.brandSecondaryBrd.secondaryPalette")).findElement(By.className("pbBody")).findElements(By.tagName("th"))) {
			if (x.getText().toLowerCase().contains("inbox tecnico"))
				inboxTecnico = x;
		}
		inboxTecnico.findElement(By.tagName("a")).click();
		sleep(25000);
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
	    driver.findElement(By.cssSelector(".scrollable.mt8")).click();
	    sleep(3000);
	    driver.switchTo().window(tabs.get(0));
	}
	
	//@BeforeMethod (alwaysRun = true)
	public void before() {
		sleep(3000);
		driver.switchTo().defaultContent();
		driver.findElement(By.id("home_Tab")).click();
		sleep(3000);
	}
	
	//@AfterClass (alwaysRun = true)
	public void quit() {
		driver.quit();
		sleep(5000);
	}
	
	
	@Test (groups = {"Fallout", "GestionesPerfilOperativo", "Ciclo3"})
	public void TS134430_CRM_OM_Ordenes_Inbox_tecnico_Busqueda_de_ordenes_con_error() {
		imagen = "TS134430";
		driver.findElement(By.className("wt-ManualQueue")).click();
		sleep(3000);
		WebElement inboxTecnico = null;
		for (WebElement x : driver.findElement(By.cssSelector(".bPageBlock.brandSecondaryBrd.secondaryPalette")).findElement(By.className("pbBody")).findElements(By.tagName("th"))) {
			if (x.getText().toLowerCase().contains("inbox tecnico"))
				inboxTecnico = x;
		}
		inboxTecnico.findElement(By.tagName("a")).click();
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-button.slds-section__title-action")));
		driver.findElement(By.cssSelector(".slds-button.slds-section__title-action")).click();
		selectByText(driver.findElement(By.cssSelector(".slds-select.ng-pristine.ng-untouched.ng-valid.ng-empty")), "Fatally Failed");
		buscarYClick(driver.findElements(By.cssSelector(".slds-button.slds-button--neutral")), "equals", "search");
		sleep(3000);
		WebElement fila = driver.findElement(By.cssSelector(".backreference.slds-box.slds-table.slds-table_bordered.slds-table--cell-buffer.slds-m-top--small")).findElement(By.tagName("tbody")).findElement(By.tagName("tr"));
		Assert.assertTrue(fila.findElements(By.tagName("td")).get(2).getText().equalsIgnoreCase("Fatally Failed"));
	}
	
	@Test (groups = {"Fallout", "GestionesPerfilOperativo", "Ciclo3"})
	public void TS134445_CRM_OM_Ola_1_Inbox_Tecnico_Action_Pick_Up() {
		imagen = "TS134445";
		driver.findElement(By.className("wt-ManualQueue")).click();
		sleep(3000);
		WebElement inboxTecnico = null;
		boolean assingToMe = false;
		for (WebElement x : driver.findElement(By.cssSelector(".bPageBlock.brandSecondaryBrd.secondaryPalette")).findElement(By.className("pbBody")).findElements(By.tagName("th"))) {
			if (x.getText().toLowerCase().contains("inbox tecnico"))
				inboxTecnico = x;
		}
		inboxTecnico.findElement(By.tagName("a")).click();
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-button.slds-section__title-action")));
		WebElement fila = driver.findElement(By.cssSelector(".backreference.slds-box.slds-table.slds-table_bordered.slds-table--cell-buffer.slds-m-top--small")).findElement(By.tagName("tbody")).findElement(By.tagName("tr"));
		fila.findElement(By.tagName("td")).findElement(By.tagName("span")).click();
		for (WebElement x : driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-scope"))) {
			if (x.getText().equalsIgnoreCase("Assign to Me")) {
				x.click();
				assingToMe = true;
			}
		}
		Assert.assertTrue(assingToMe);
	}
	
	@Test (groups = {"Fallout", "GestionesPerfilOperativo", "Ciclo3"})
	public void TS134446_CRM_OM_Ola_1_Inbox_Tecnico_Action_Complete() {
		imagen = "TS134446";
		driver.findElement(By.className("wt-ManualQueue")).click();
		sleep(3000);
		WebElement inboxTecnico = null;
		boolean complete = false;
		for (WebElement x : driver.findElement(By.cssSelector(".bPageBlock.brandSecondaryBrd.secondaryPalette")).findElement(By.className("pbBody")).findElements(By.tagName("th"))) {
			if (x.getText().toLowerCase().contains("inbox tecnico"))
				inboxTecnico = x;
		}
		inboxTecnico.findElement(By.tagName("a")).click();
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-button.slds-section__title-action")));
		WebElement fila = driver.findElement(By.cssSelector(".backreference.slds-box.slds-table.slds-table_bordered.slds-table--cell-buffer.slds-m-top--small")).findElement(By.tagName("tbody")).findElement(By.tagName("tr"));
		fila.findElement(By.tagName("td")).findElement(By.tagName("span")).click();
		for (WebElement x : driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-scope"))) {
			if (x.getText().equalsIgnoreCase("Complete")) {
				x.click();
				complete = true;
			}
		}
		Assert.assertTrue(complete);
	}
	
	@Test (groups = {"Fallout", "GestionesPerfilOperativo", "Ciclo3"})
	public void TS134447_CRM_OM_Ola_1_Inbox_Tecnico_Action_Retry() {
		imagen = "TS134447";
		driver.findElement(By.className("wt-ManualQueue")).click();
		sleep(3000);
		WebElement inboxTecnico = null;
		boolean retry = false;
		for (WebElement x : driver.findElement(By.cssSelector(".bPageBlock.brandSecondaryBrd.secondaryPalette")).findElement(By.className("pbBody")).findElements(By.tagName("th"))) {
			if (x.getText().toLowerCase().contains("inbox tecnico"))
				inboxTecnico = x;
		}
		inboxTecnico.findElement(By.tagName("a")).click();
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-button.slds-section__title-action")));
		WebElement fila = driver.findElement(By.cssSelector(".backreference.slds-box.slds-table.slds-table_bordered.slds-table--cell-buffer.slds-m-top--small")).findElement(By.tagName("tbody")).findElement(By.tagName("tr"));
		fila.findElement(By.tagName("td")).findElement(By.tagName("span")).click();
		for (WebElement x : driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-scope"))) {
			if (x.getText().equalsIgnoreCase("Retry")) {
				x.click();
				retry = true;
			}
		}
		Assert.assertTrue(retry);
	}
}