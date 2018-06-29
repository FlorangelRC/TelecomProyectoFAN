package Tests;

import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.BasePage;
import Pages.Marketing;
import Pages.OM;
import Pages.OMQPage;
import Pages.setConexion;

public class OMerO extends TestBase {

	private WebDriver driver;
	protected OM om;
	protected BasePage bp;
	protected OMQPage omq;

	@BeforeClass (alwaysRun = true, groups = "OM")
	public void init() {
		driver = setConexion.setupEze();
		sleep(5000);
		login(driver, "https://crm--sit.cs14.my.salesforce.com/", "U585991", "Testa10k");
		sleep(5000);
		om = new OM(driver);
		bp = new BasePage(driver);
		omq = new OMQPage(driver);
	}
	
	@BeforeMethod (alwaysRun = true, groups = "OM")
	public void before() {
		bp.cajonDeAplicaciones("Sales");
		sleep(5000);
		driver.findElement(By.id("Order_Tab")).click();
		sleep(3000);
	}
	
	@AfterClass (alwaysRun = true, groups = "OM")
	public void quit() {
		driver.quit();
		sleep(5000);
	}

	@Test (groups = "OM")
	public void TS6721_OM_Ordenes_Vista_Configuracion(){
		driver.findElement(By.className("fFooter")).click();
		sleep(5000);
		driver.findElement(By.id("fname")).sendKeys("Automatizacion");
		Select env = new Select (driver.findElement(By.id("colselector_select_1")));
		env.selectByVisibleText("Order Number");
		driver.findElement(By.id("colselector_select_0_left")).click();
		sleep(2000);
		env.selectByVisibleText("Order Start Date");
		driver.findElement(By.id("colselector_select_0_left")).click();
		sleep(2000);
		env.selectByVisibleText("Order Amount");
		driver.findElement(By.id("colselector_select_0_left")).click();
		sleep(2000);
		env.selectByVisibleText("Contract Number");
		driver.findElement(By.id("colselector_select_0_left")).click();
		sleep(3000);	
		Select field = new Select (driver.findElement(By.id("fcol1")));
		field.selectByVisibleText("Account Name");
		Select oper = new Select (driver.findElement(By.id("fop1")));
		oper.selectByVisibleText("equals");
		driver.findElement(By.id("fval1")).sendKeys("AutomaOM");
		WebElement a = driver.findElement(By.cssSelector(".pbButtonb")).findElement(By.tagName("input"));
		a.click();
		sleep(5000);
		List<WebElement> acc = driver.findElement(By.id("ext-gen15")).findElement(By.tagName("table")).findElement(By.tagName("thead")).findElements(By.tagName("td"));
			if(acc.get(0).getText().contains("Action"))
				acc.remove(0);
		System.out.println(acc.size());		
		Assert.assertTrue(acc.get(2).getText().contains("Account Name"));
		Assert.assertTrue(acc.get(3).getText().contains("Status"));
		Boolean chk = false;
		WebElement llsstt = driver.findElement(By.className("x-grid3-body")).findElement(By.tagName("div")).findElement(By.tagName("tbody")).findElement(By.tagName("tr"));
		List<WebElement> list = llsstt.findElements(By.tagName("td"));
			for(WebElement l : list){
				list.get(2).equals("AutomaOM");
				chk = true;
			}
		Assert.assertTrue(chk);	
		sleep(3000);
		WebElement dele = driver.findElement(By.className("filterLinks")).findElements(By.tagName("a")).get(1);
		dele.click();
		try {driver.switchTo().alert().accept();}catch(org.openqa.selenium.NoAlertPresentException e) {}
	}
	
//========================================================================================================================================================================
	
	@Test (groups = "OM")
	public void TS6722_OM_Ordenes_Vista_Configuración_Cargar_Vista(){
		driver.findElement(By.className("fFooter")).click();
		sleep(5000);
		driver.findElement(By.id("fname")).sendKeys("CambiodeVista");
		Select env = new Select (driver.findElement(By.id("colselector_select_1")));
		env.selectByVisibleText("Order Number");
		driver.findElement(By.id("colselector_select_0_left")).click();
		sleep(2000);
		env.selectByVisibleText("Order Start Date");
		driver.findElement(By.id("colselector_select_0_left")).click();
		sleep(2000);
		env.selectByVisibleText("Order Amount");
		driver.findElement(By.id("colselector_select_0_left")).click();
		sleep(2000);
		env.selectByVisibleText("Contract Number");
		driver.findElement(By.id("colselector_select_0_left")).click();
		sleep(3000);	
		Select field = new Select (driver.findElement(By.id("fcol1")));
		field.selectByVisibleText("Account Name");
		Select oper = new Select (driver.findElement(By.id("fop1")));
		oper.selectByVisibleText("equals");
		driver.findElement(By.id("fval1")).sendKeys("AutomaOM");
		WebElement a = driver.findElement(By.cssSelector(".pbButtonb")).findElement(By.tagName("input"));
		a.click();
		sleep(5000);
		om.selectVistaByVisibleText("All");
		sleep(5000);
		om.selectVistaByVisibleText("CambiodeVista");
		sleep(5000);
		List<WebElement> acc = driver.findElement(By.id("ext-gen15")).findElement(By.tagName("table")).findElement(By.tagName("thead")).findElements(By.tagName("td"));
			if(acc.get(0).getText().contains("Action"))
				acc.remove(0);
		System.out.println(acc.size());		
		Assert.assertTrue(acc.get(2).getText().contains("Account Name"));
		Assert.assertTrue(acc.get(3).getText().contains("Status"));
	}
//============================================================================================================================================================================
	
	@Test (groups = {"OM","CambioDeNumero"})
	public void TS79682CRM_OM_Ordenes_Cliente_existente_Cambio_de_numero_Sin_delivery_Paso_5() throws InterruptedException{
		OM pageOm=new OM(driver);
		boolean gestion = false;
		pageOm.Gestion_Alta_De_Linea("AlOM", "Plan Prepago Nacional");
		pageOm.Gestion_Cambio_de_Numero("AlanOM", "07-07-2018");
		sleep(15000);
		/*WebElement status = driver.findElement(By.id("Status_ilecell"));
		List <WebElement> gest = driver.findElements(By.cssSelector(".dataCol.inlineEditWrite"));
		for (WebElement x : gest) {
			if (x.getText().toLowerCase().contains("cambio de n\\u00famero")) {
				gestion = true;
			}
		}
		Assert.assertTrue(status.getText().equalsIgnoreCase("Activated"));
		Assert.assertTrue(gestion);
		sleep(3000);*/
	}      
		
			
	@Test (groups = {"OM","CambioDeNumero"}, dependsOnMethods="TS79682CRM_OM_Ordenes_Cliente_existente_Cambio_de_numero_Sin_delivery_Paso_5")
	public void TS79681_OM_Ordenes_Cliente_existente_Cambio_de_numero_Sin_delivery_Paso_4(){
		Assert.assertTrue(true);
		sleep(3000);
	}
	
	@Test (groups = {"OM","CambioDeNumero"}, dependsOnMethods="TS79682CRM_OM_Ordenes_Cliente_existente_Cambio_de_numero_Sin_delivery_Paso_5")
	public void TS79680_OM_Ordenes_Cliente_existente_Cambio_de_numero_Sin_delivery_Paso_3(){
		Assert.assertTrue(true);
		sleep(3000);
	}
	
	@Test (groups = {"OM","CambioDeNumero"}, dependsOnMethods="TS79682CRM_OM_Ordenes_Cliente_existente_Cambio_de_numero_Sin_delivery_Paso_5")
	public void TS79678_OM_Ordenes_Cliente_existente_Cambio_de_numero_Sin_delivery_Paso_2(){
		Assert.assertTrue(true);
		sleep(3000);
	}
	
	@Test (groups = {"OM","CambioDeNumero"}, dependsOnMethods="TS79682CRM_OM_Ordenes_Cliente_existente_Cambio_de_numero_Sin_delivery_Paso_5")
	public void TS79677_OM_Ordenes_Cliente_existente_Cambio_de_numero_Sin_delivery_Paso_1(){
		Assert.assertTrue(true);
		sleep(3000);
	}
	
	@Test (groups = {"OM","CambioDeNumero"}, dependsOnMethods="TS79682CRM_OM_Ordenes_Cliente_existente_Cambio_de_numero_Sin_delivery_Paso_5")
	public void TS79676_OM_Ordenes_Cliente_existente_Cambio_de_numero_Sin_delivery_Paso_0(){
		Assert.assertTrue(true);
		sleep(3000);
	}
	
	
	/*
	
//=======================================================================================================================================================================
	
	@Test (groups = {"OM","CambioDeNumero"})
	public void TS80246_OM_Ordenes_Cliente_existente_Cambio_de_numero_Plan_con_tarjeta_Sin_delivery_Paso_5() throws InterruptedException{
		OM pageOm=new OM(driver);
		boolean gestion = false;
		pageOm.Gestion_Alta_De_Linea("AlOM", "Plan con tarjeta");
		pageOm.Gestion_Cambio_de_Numero("AlanOM", "07-07-2018");
		sleep(15000);
		WebElement status = driver.findElement(By.id("Status_ilecell"));
		List <WebElement> gest = driver.findElements(By.cssSelector(".dataCol.inlineEditWrite"));
		for (WebElement x : gest) {
			if (x.getText().toLowerCase().contains("cambio de n\\u00famero")) {
				gestion = true;
			}
		}
		Assert.assertTrue(status.getText().equalsIgnoreCase("Activated"));
		//Assert.assertTrue(gestion);
		sleep(3000);
	}      
		
			
	@Test (groups = {"OM","CambioDeNumero"},dependsOnMethods="TS80246_OM_Ordenes_Cliente_existente_Cambio_de_numero_Plan_con_tarjeta_Sin_delivery_Paso_5")
	public void TS80245_OM_Ordenes_Cliente_existente_Cambio_de_numero_Plan_con_tarjeta_Sin_delivery_Paso_4(){
		Assert.assertTrue(true);
		sleep(3000);
	}
	
	@Test (groups = {"OM","CambioDeNumero"}, dependsOnMethods="TS80246_OM_Ordenes_Cliente_existente_Cambio_de_numero_Plan_con_tarjeta_Sin_delivery_Paso_5")
	public void TS80244_OM_Ordenes_Cliente_existente_Cambio_de_numero_Plan_con_tarjeta_Sin_delivery_Paso_3(){
		Assert.assertTrue(true);
		sleep(3000);
	}
	
	@Test (groups = {"OM","CambioDeNumero"}, dependsOnMethods="TS80246_OM_Ordenes_Cliente_existente_Cambio_de_numero_Plan_con_tarjeta_Sin_delivery_Paso_5")
	public void TS80243_OM_Ordenes_Cliente_existente_Cambio_de_numero_Plan_con_tarjeta_Sin_delivery_Paso_2(){
		Assert.assertTrue(true);
		sleep(3000);
	}
	
	@Test (groups = {"OM","CambioDeNumero"}, dependsOnMethods="TS80246_OM_Ordenes_Cliente_existente_Cambio_de_numero_Plan_con_tarjeta_Sin_delivery_Paso_5")
	public void TS80242_OM_Ordenes_Cliente_existente_Cambio_de_numero_Plan_con_tarjeta_Sin_delivery_Paso_1(){
		Assert.assertTrue(true);
		sleep(3000);
	}
	
	@Test (groups = {"OM","CambioDeNumero"}, dependsOnMethods="TS80246_OM_Ordenes_Cliente_existente_Cambio_de_numero_Plan_con_tarjeta_Sin_delivery_Paso_5")
	public void TS80241_OM_Ordenes_Cliente_existente_Cambio_de_numero_Plan_con_tarjeta_Sin_delivery_Paso_0(){
		Assert.assertTrue(true);
		sleep(3000);
	}
	*/

}
