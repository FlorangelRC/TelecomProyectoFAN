package Tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.CustomerCare;
import Pages.SalesBase;
import Pages.setConexion;

public class GestionesPerfilBackOffice extends TestBase {

	private WebDriver driver;
	private SalesBase sb;
	private CustomerCare cc;
	String imagen;
	
	
	@BeforeClass (alwaysRun = true)
	public void init() {
		driver = setConexion.setupEze();
		sleep(5000);
		sb = new SalesBase(driver);
		cc = new CustomerCare(driver);
		loginBackOffice(driver);
		sleep(22000);
		driver.findElement(By.id("tabBar")).findElement(By.tagName("a")).click();
		sleep(18000);
		driver.switchTo().defaultContent();
		sleep(3000);
		goToLeftPanel2(driver, "Inicio");
		sleep(18000);
		try {
			sb.cerrarPestaniaGestion(driver);
		} catch (Exception ex1) {}
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		List <WebElement> frames = driver.findElements(By.tagName("iframe"));
		boolean enc = false;
		int index = 0;
		for(WebElement frame : frames) {
			try {
				driver.switchTo().frame(frame);
				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).getText();
				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).isDisplayed();
				driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
				enc = true;
				break;
			} catch(NoSuchElementException noSuchElemExcept) {
				index++;
				driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
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
	
	@AfterMethod (alwaysRun = true)
	public void after() {
		sb.cerrarPestaniaGestion(driver);
	}

	@AfterClass (alwaysRun = true)
	public void quit() {
		driver.quit();
		sleep(5000);
	}
	
	@Test (groups = {"GestionesPerfilBackOffice", "Ajustes", "E2E"},  dataProvider = "CuentaAjustesPRE")
	public void TS121329_CRM_Movil_PRE_Ajuste_Backoffice_modifica_cantidades(String cDNI) {
		imagen = "TS121329";
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		cc.irAGestion("inconvenientes");
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step-TipodeAjuste_nextBtn")));
		selectByText(driver.findElement(By.id("CboConcepto")), "CREDITO PREPAGO");
		selectByText(driver.findElement(By.id("CboItem")), "Consumos de datos");
		selectByText(driver.findElement(By.id("CboMotivo")), "Informacion incorrecta");
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "no");
		driver.findElement(By.id("Step-TipodeAjuste_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "contains", "plan con tarjeta");
		driver.findElement(By.id("Step-AssetSelection_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si, ajustar");
		driver.findElement(By.id("Step-VerifyPreviousAdjustments_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("Desde")).sendKeys("04-07-2018");
		driver.findElement(By.id("Hasta")).sendKeys("24-07-2018");
		selectByText(driver.findElement(By.id("Unidad")), "Credito");
		driver.findElement(By.id("CantidadMonto")).sendKeys("60000");
		driver.findElement(By.id("Step-AjusteNivelLinea_nextBtn")).click();
		sleep(10000);
		driver.findElement(By.id("Step-Summary_nextBtn")).click();
		sleep(7000);
		String nroCaso = driver.findElement(By.cssSelector(".vlc-slds-inline-control__label.ng-binding")).getText();
		nroCaso = nroCaso.substring(nroCaso.indexOf("0"), nroCaso.length());
		CambiarPerfil("backoffice", driver);
		driver.findElement(By.id("tabBar")).findElement(By.tagName("a")).click();
		sleep(18000);
		cc.cerrarTodasLasPestanas();
		cc.buscarCaso(nroCaso);
		driver.switchTo().frame(cambioFrame(driver, By.id("cas1_ileinner")));
		driver.findElement(By.id("cas1_ileinner")).findElements(By.tagName("a")).get(1).click();		
		sleep(5000);
		selectByText(driver.findElement(By.id("newOwn_mlktp")), "Usuario");
		driver.findElement(By.id("newOwn")).sendKeys("Cintia Tschernikoff");
		driver.findElement(By.name("save")).click();
		sleep(5000);
		driver.findElements(By.className("actionLink")).get(2).click();
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.name("goNext")));
		driver.findElement(By.name("goNext")).click();
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.className("extraStatusDiv_A")));
		Assert.assertTrue(driver.findElement(By.className("extraStatusDiv_A")).getText().equalsIgnoreCase("Aprobado"));
	}
}