package Tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.Marketing;
import Pages.SalesBase;
import Pages.setConexion;

public class GestionesPerfilOficina extends TestBase {

	private WebDriver driver;
	
	@BeforeClass
	public void init() {
		driver = setConexion.setupEze();
		sleep(5000);
		loginOfCom(driver);
		sleep(22000);
		driver.findElement(By.id("tabBar")).findElement(By.tagName("a")).click();
		sleep(18000);
		SalesBase sb = new SalesBase(driver);
		driver.switchTo().defaultContent();
		sleep(3000);
		goToLeftPanel2(driver, "Inicio");
		sleep(18000);
		try {
			sb.cerrarPestaniaGestion(driver);
		} catch (Exception ex1) {}
		Accounts ac = new Accounts(driver);
		driver.switchTo().frame(ac.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		List <WebElement> frames = driver.findElements(By.tagName("iframe"));
		boolean enc = false;
		int index = 0;
		for(WebElement frame : frames) {
			try {
				System.out.println("aca");
				driver.switchTo().frame(frame);
				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).getText(); //each element is in the same iframe.
				//System.out.println(index); //prints the used index.
				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).isDisplayed(); //each element is in the same iframe.
				//System.out.println(index); //prints the used index.
				driver.switchTo().frame(ac.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
				enc = true;
				break;
			} catch(NoSuchElementException noSuchElemExcept) {
				index++;
				driver.switchTo().frame(ac.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
			}
		}
		if(enc == false)
			index = -1;
		try {
			driver.switchTo().frame(frames.get(index));
		} catch(ArrayIndexOutOfBoundsException iobExcept) {
			System.out.println("Elemento no encontrado en ningun frame 2.");			
		}
		List <WebElement> botones = driver.findElements(By.tagName("button"));
		for (WebElement UnB : botones) {
			System.out.println(UnB.getText());
			if(UnB.getText().equalsIgnoreCase("gesti\u00f3n de clientes")) {
				UnB.click();
				break;
			}
		}	
		sleep(14000);
		driver.switchTo().frame(ac.getFrameForElement(driver, By.id("SearchClientDocumentNumber")));
		driver.findElement(By.cssSelector(".slds-icon.slds-icon--small.vlc-slds-collapse.slds-button__icon.slds-button__icon--large")).click();
		sleep(2000);
		driver.findElement(By.id("ContactFirstName")).sendKeys("Tom");
		driver.findElement(By.id("ContactLastName")).sendKeys("Riddle");
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep(7000);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(3000);
	}
	
	@BeforeMethod
	public void before() {
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".console-flyout.active.flyout")).getLocation().y+")");
		sleep(2000);
	}

	@AfterMethod
	public void after() {
		Marketing mk = new Marketing(driver);
		mk.closeActiveTab();
	}

	//@AfterClass
	public void quit() {
		driver.quit();
		sleep(5000);
	}
}