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


	@BeforeClass (alwaysRun = true, groups = "OM")
	public void init() {
		driver = setConexion.setupEze();
		sleep(5000);
		login(driver, "https://crm--sit.cs14.my.salesforce.com/", "U585991", "Testa10k");
		sleep(5000);
		om = new OM(driver);
		bp = new BasePage(driver);
	}
	
	@BeforeMethod (alwaysRun = true, groups = "OM")
	public void before() {
		bp.cajonDeAplicaciones("Sales");
		sleep(5000);
		driver.findElement(By.id("Order_Tab")).click();
		sleep(3000);
	}
	
	//@AfterClass (alwaysRun = true, groups = "OM")
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
	
//=======================================================================================================================================================================
	
	@Test (groups = "OM")
	public void TS_OM_Cambio_de_Numero(){
		Date date = new Date();
		
	//Mientras, seleccion de vista
		Select allOrder=new Select(driver.findElement(By.id("fcf")));
		allOrder.selectByVisibleText("AlanOM");
		sleep(1000);
		try {driver.findElement(By.name("go")).click();}catch(org.openqa.selenium.NoSuchElementException e) {}
		sleep(3000);
	//Selecciona la primera cuenta de la lista en la vista seleccionada
		WebElement primeraCuenta=driver.findElement(By.cssSelector(".x-grid3-col.x-grid3-cell.x-grid3-td-SALES_ACCOUNT_NAME"));
		primeraCuenta.findElement(By.tagName("div")).findElement(By.tagName("a")).click();
		sleep(8000);
	//Seleccion del ultimo Asset
		om.irAChangeToOrder();	
		sleep(8000);
	//Ingreso de fecha avanzada
		Accounts accountPage = new Accounts(driver);
		/*DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		driver.findElement(By.id("RequestDate")).sendKeys(dateFormat.format(om.fechaAvanzada()));*/
		driver.findElement(By.id("RequestDate")).sendKeys("08-08-2018");
		driver.findElement(By.cssSelector(".form-control.btn.btn-primary.ng-binding")).click();
		sleep(15000);
	//SIM
		driver.findElement(By.cssSelector(".slds-button.cpq-item-has-children")).click();
		sleep(3000);
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath(".//*[@id='tab-default-1']/div[1]/ng-include/div/div/div/div[4]/div[2]/div/ng-include/div/div[2]/ng-include/div/div[1]/div/div[2]/div[11]")).click();
		sleep(3000);
		driver.findElement(By.xpath(".//*[@id='tab-default-1']/div[1]/ng-include/div/div/div/div[4]/div[2]/div/ng-include/div/div[2]/ng-include/div/div[1]/div/div[2]/div[11]/div[2]/div/ul/li[3]/a")).click();
		sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.className("slds-section")).getLocation().y+" )");
		WebElement msi = driver.findElement(By.xpath("//*[@id='js-cpq-product-cart-config-form']/div[1]/div/form/div[17]/div[1]/input"));
		Random r = new Random();
		msi.clear();
		msi.sendKeys("11" + r.nextInt(200000000) );
		msi.submit();
		sleep(30000);
		driver.findElement(By.id("-import-btn")).click();
		sleep(5000);
	//Gestion
		om.agregarGestion("Cambio de n\u00famero");
		driver.findElements(By.id("topButtonRow")).get(0);
		sleep(7000);
		driver.findElement(By.name("ta_submit_order")).click();
		sleep(35000);
		om.cambiarVentanaNavegador(1);
		sleep(2000);
		driver.findElement(By.id("idlist")).click();
		sleep(5000);
		om.cambiarVentanaNavegador(0);
		sleep(12000);
		om.completarFlujoOrquestacion();
		
		}
			
}
