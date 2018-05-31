package Tests;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.ArrayList;
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

@Test(groups="OM")
public void TS51856_CRM_OM_Ordenes_Cliente_Nuevo_Alta_de_linea_Sin_delivery_Sin_VAS_Paso_1 () throws InterruptedException {
	OMQPage OM=new OMQPage (driver);
	OM.CrearOrden();
	OM.getCPQ().click();
	sleep(5000);
	OM.colocarPlan("Plan Prepago Nacional");
	OM.configuracion();
	
	
		
	
		}
}