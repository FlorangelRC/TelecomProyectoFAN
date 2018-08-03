package Tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.BasePage;
import Pages.CustomerCare;
import Pages.Marketing;
import Pages.SalesBase;
import Pages.setConexion;

public class GestionesPerfilOficina extends TestBase {

	private WebDriver driver;
	
	@BeforeClass(alwaysRun=true)
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
				//System.out.println("aca");
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
	}

	//@AfterMethod(alwaysRun=true)
	public void after() {
		SalesBase sb = new SalesBase(driver);
		sb.cerrarPestaniaGestion(driver);
	}

	//@AfterClass(alwaysRun=true)
	public void quit() {
		driver.quit();
		sleep(5000);
	}
	
	@Test (groups = {"GestionesPerfilOficina"}/*, dataProvider="NumeroAmigo"*/)
	public void OpenPage(/*String sNumeroAmigoVOZ, String sNumeroAmigoSMS*/) {
		BasePage cambioFrameByID=new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI", "46534534");
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(3000);
		
		CustomerCare cCC = new CustomerCare(driver);
		cCC.irAGesiton("Números Gratis");
		
		WebElement wNumeros = driver.findElement(By.cssSelector(".slds-grid.slds-grid--pull-padded"));
		List<WebElement> wNumerosAmigos = wNumeros.findElements(By.cssSelector(".slds-col--padded.slds-size--1-of-2"));
		//wNumerosAmigos.get(0).findElement(By.tagName("input")).sendKeys(sNumeroAmigoVOZ);
		//wNumerosAmigos.get(1).findElement(By.tagName("input")).sendKeys(sNumeroAmigoSMS);
		wNumerosAmigos.get(0).findElement(By.tagName("input")).sendKeys("1161135555");
		wNumerosAmigos.get(1).findElement(By.tagName("input")).sendKeys("");
		sleep(3000);
		driver.findElement(By.cssSelector(".OSradioButton.ng-scope.only-buttom")).click();
		
		sleep(5000);
		driver.navigate().refresh();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("story-container")));
		
		String sOrder = "";
		List<WebElement> wStoryContainer = driver.findElements(By.className("story-container"));
		for (WebElement wAux : wStoryContainer) {
			if (wAux.findElement(By.cssSelector(".slds-text-body.regular.story-title")).getText().equalsIgnoreCase("Numero Gratis")) {
				List<WebElement> wStoryField = wAux.findElements(By.cssSelector(".slds-text-body.regular.story-field"));
				sOrder = wStoryField.get(0).getText();
			}
		}
		
		System.out.println("Order: " + sOrder + " Fin");
		//Assert.assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope")).getText().contains("¡La orden se realizó con éxito!"));
	}
	
}