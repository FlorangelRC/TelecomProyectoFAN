package Tests;

import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.Iterator;

import Pages.Accounts;
import Pages.BasePage;
import Pages.HomeBase;
import Pages.OM;
import Pages.OMQPage;
import Pages.RegistroEventoMasivo;
import Pages.SCP;
import Pages.setConexion;

import Pages.setConexion;



public class OM_Modulo extends TestBase {
private WebDriver driver;
	
@BeforeClass(alwaysRun=true)
public void init() throws Exception
	{
		this.driver = setConexion.setupEze();
		sleep(5000);
		//Usuario Victor OM
		login(driver, "https://crm--sit.cs14.my.salesforce.com/", "U585991", "Testa10k");
		sleep(5000);	
	}

@BeforeMethod(alwaysRun=true)
public void setUp() throws Exception {
		driver.switchTo().defaultContent();
		sleep(2000);
		SCP pageSCP= new SCP(driver);
		pageSCP.goToMenu("Ventas");
		
		//click +
		sleep(5000);
		OM pageOm=new OM(driver);
		pageOm.clickMore();
		sleep(3000);
		
		//click en Ordenes
		pageOm.clickOnListTabs("Orders");
	}
	
	//@AfterClass(alwaysRun=true)
	public void tearDown() {
		sleep(2000);
		driver.quit(); 
		sleep(1000);
	}

/*@Test(groups="OM")
public void TS102205_CRM_OM_Ola_2_Ordenes_Cliente_existente_Alta_de_linea_con_1_pack_Plan_con_tarjeta_Sin_delivery_Sin_VAS_Paso_0() throws InterruptedException {
	OM pageOm=new OM(driver);
	OMQPage OM=new OMQPage (driver);
	pageOm.crearOrden("LineasPlanConTarjeta");
	assertTrue(driver.findElement(By.cssSelector(".noSecondHeader.pageType")).isDisplayed());
	OM.getCPQ().click();
	sleep(5000);
	OM.colocarPlan("Plan con Tarjeta");
	OM.configuracion();
	sleep(5000);
	driver.findElement(By.name("ta_submit_order")).click();
	sleep(25000);
	pageOm.cambiarVentanaNavegador(1);
	sleep(2000);
	driver.findElement(By.id("idlist")).click();
	sleep(5000);
	pageOm.cambiarVentanaNavegador(0);
	sleep(12000);
	pageOm.completarFlujoOrquestacion();
	pageOm.irAChangeToOrder();

		}*/

	@Test(groups="OM")
	public void TS102205_CRM_OM_Ola_2_Ordenes_Cliente_existente_Alta_de_linea_con_1_pack_Plan_con_tarjeta_Sin_delivery_Sin_VAS_Paso_0() {
				OM pageOm=new OM(driver);
				OMQPage OM=new OMQPage (driver);
				pageOm.selectVistaByVisibleText("Quelys");
				sleep(3000);

				//Selecciona la primera cuenta de la lista en la vista seleccionada
				WebElement primeraCuenta=driver.findElement(By.cssSelector(".x-grid3-col.x-grid3-cell.x-grid3-td-SALES_ACCOUNT_NAME"));
				primeraCuenta.findElement(By.tagName("div")).findElement(By.tagName("a")).click();
				sleep(5000);
				
				BasePage frame=new BasePage(driver);
				driver.switchTo().frame(frame.getFrameForElement(driver, By.cssSelector(".panel.panel-default.panel-assets")));
				
				//Selecciona el ultimo asset
				List<WebElement> assets= driver.findElement(By.cssSelector(".panel.panel-default.panel-assets")).findElements(By.cssSelector(".root-asset.ng-scope"));
				assets.get(assets.size()-1).findElement(By.className("p-check")).click();
				
				//click en boton
				WebElement changeToOrder=driver.findElement(By.className("asset-action")).findElement(By.xpath("//button[2]"));
				changeToOrder.click();
				sleep(8000);
				driver.switchTo().defaultContent();
				
				//fecha avanzada
				pageOm.fechaAvanzada();
				//driver.findElement(By.id("RequestDate")).sendKeys(dateFormat.format(pageOm.fechaAvanzada()));
				OM.agregarPack("Pack 1Gb + WhasApp x 7 días");
				
		
	}
}