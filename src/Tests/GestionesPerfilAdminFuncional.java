package Tests;


import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.BasePage;
import Pages.CBS;
import Pages.ContactSearch;
import Pages.CustomerCare;
import Pages.Marketing;
import Pages.PagePerfilTelefonico;
import Pages.SalesBase;
import Pages.TechCare_Ola1;
import Pages.TechnicalCareCSRAutogestionPage;
import Pages.TechnicalCareCSRDiagnosticoPage;
import Pages.setConexion;

public class GestionesPerfilAdminFuncional extends TestBase{

	private WebDriver driver;
	private SalesBase sb;
	private CustomerCare cc;
	private Marketing mk;
	List <String> datosOrden =new ArrayList<String>();
	PagePerfilTelefonico ppt;
	String imagen;
	String detalles;
	
	@BeforeClass(alwaysRun=true)
	public void init() {
		driver = setConexion.setupEze();
		sleep(5000);
		cc = new CustomerCare(driver);
		sb = new SalesBase(driver);
		mk = new Marketing(driver);
		loginAdminFuncional(driver);
		sleep(22000);
		try {
			cc.cajonDeAplicaciones("Consola FAN");
		} catch(Exception e) {
			sleep(3000);
			driver.findElement(By.id("tabBar")).findElement(By.tagName("a")).click();
			sleep(6000);
		}
		driver.switchTo().defaultContent();
		sleep(3000);
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public void setup() throws Exception {
		sleep(10000);
		goToLeftPanel2(driver, "Inicio");
		sleep(15000);
		try {
			sb.cerrarPestaniaGestion(driver);
		} catch (Exception ex1) {}
		Accounts accountPage = new Accounts(driver);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
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

				driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
				enc = true;
				break;
			}catch(NoSuchElementException noSuchElemExcept) {
				index++;
				driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
			}
		}
		if(enc == false)
			index = -1;
		try {
				driver.switchTo().frame(frames.get(index));
		}catch(ArrayIndexOutOfBoundsException iobExcept) {System.out.println("Elemento no encontrado en ningun frame 2.");
			
		}
		List<WebElement> botones = driver.findElements(By.tagName("button"));
		for (WebElement UnB : botones) {
			System.out.println(UnB.getText());
			if(UnB.getText().equalsIgnoreCase("gesti\u00f3n de clientes")) {
				UnB.click();
				break;
			}
		}
		
		sleep(15000);
	}
	
	//@AfterMethod(alwaysRun=true)
	public void after() throws IOException {
		datosOrden.add(detalles);
		guardarListaTxt(datosOrden);
		datosOrden.clear();
		tomarCaptura(driver,imagen);
	}
	
	//@AfterClass(alwaysRun=true)
	public void quit() throws IOException {
		//guardarListaTxt(datosOrden);
		driver.quit();
		sleep(5000);
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Diagnostico/Inconvenientes", }, dataProvider = "Diagnostico")
	public void TS105437_CRM_Movil_Repro_Autogestion_WEB_Inconveniente_con_Informe_de_pago_Resuelto(String cDNI, String cLinea) throws InterruptedException {
		imagen = "TS105437";
		detalles = null;
		detalles = imagen + "- Autogestion - DNI: "+cDNI;
		TechnicalCareCSRAutogestionPage tech = new TechnicalCareCSRAutogestionPage(driver);
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		searchAndClick(driver, "Diagn\u00f3stico de Autogesti\u00f3n");
		tech.listadoDeSeleccion("WEB", "Otro", "Inconveniente con Informe de pago");
		sleep(4000);
		tech.verificarNumDeGestion();
		Assert.assertTrue(tech.cerrarCaso("Resuelta exitosa", "Consulta"));
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Diagnostico/Inconvenientes", }, dataProvider = "Diagnostico")
	public void TS105441_CRM_Movil_Repro_Autogestion_WEB_Informacion_Incompleta_Resuelto(String cDNI, String cLinea) throws InterruptedException {
		imagen = "TS105441";
		detalles = null;
		detalles = imagen + "- Autogestion - DNI: "+cDNI;
		TechnicalCareCSRAutogestionPage tech = new TechnicalCareCSRAutogestionPage(driver);
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		searchAndClick(driver, "Diagn\u00f3stico de Autogesti\u00f3n");
		tech.listadoDeSeleccion("WEB", "Otro", "Informaci\u00f3n Incompleta");
		sleep(4000);
		tech.verificarNumDeGestion();
		Assert.assertTrue(tech.cerrarCaso("Resuelta exitosa", "Consulta"));
	}
	
}
