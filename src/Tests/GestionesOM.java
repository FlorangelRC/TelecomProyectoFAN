package Tests;

import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.BasePage;
import Pages.ContactSearch;
import Pages.HomeBase;
import Pages.OM;
import Pages.OMQPage;
import Pages.SalesBase;
import Pages.setConexion;

public class GestionesOM extends TestBase {
	
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
		OM pageOm=new OM(driver);
		pageOm.goToMenuOM();
		
		//click +
		sleep(5000);
		
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
	
	@Test(groups="OM", priority=1)
	public void TS_CRM_OM_Gestion_Alta_De_Linea() throws InterruptedException {
		OM pageOm=new OM(driver);
		OMQPage OM=new OMQPage (driver);
		pageOm.crearOrden("AutomaOM");
		assertTrue(driver.findElement(By.cssSelector(".noSecondHeader.pageType")).isDisplayed());
		pageOm.agregarGestion("Venta");
		sleep(2000);
		OM.getCPQ().click();
		sleep(5000);
		OM.colocarPlan("Plan Prepago Nacional");
		OM.configuracion();
		sleep(5000);
		driver.findElement(By.name("ta_submit_order")).click();
		sleep(35000);
		pageOm.cambiarVentanaNavegador(1);
		sleep(2000);
		driver.findElement(By.id("idlist")).click();
		sleep(5000);
		pageOm.cambiarVentanaNavegador(0);
		sleep(12000);
		pageOm.completarFlujoOrquestacion();
		sleep(5000);
		driver.findElement(By.id("accid_ileinner")).findElement(By.tagName("a")).click();
		sleep(10000);
		pageOm.irAChangeToOrder();
		
	}
	
	@Test(groups="OM", priority=1)
	public void TS_CRM_Cambio_De_SimCard() throws InterruptedException {
		//TS_CRM_OM_Gestion_Alta_De_Linea();
		OM pageOm=new OM(driver);
		OMQPage OM=new OMQPage (driver);
		//Mientras, seleccion de vista
		pageOm.selectVistaByVisibleText("LineasFlor");
		sleep(3000);
		//Selecciona la primera cuenta de la lista en la vista seleccionada
		WebElement primeraCuenta=driver.findElement(By.cssSelector(".x-grid3-col.x-grid3-cell.x-grid3-td-SALES_ACCOUNT_NAME"));
		primeraCuenta.findElement(By.tagName("div")).findElement(By.tagName("a")).click();
		sleep(5000);
		pageOm.irAChangeToOrder();	
		sleep(10000);
		Accounts accountPage = new Accounts(driver);
		driver.switchTo().defaultContent(); 
        /*DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		driver.findElement(By.id("RequestDate")).sendKeys(dateFormat.format(pageOm.fechaAvanzada()));*/
		driver.findElement(By.id("RequestDate")).sendKeys("07-12-2018");
		driver.findElement(By.cssSelector(".form-control.btn.btn-primary.ng-binding")).click();
		sleep(12000);
		OM.SimCard();
		driver.findElement(By.id("-import-btn")).click();
		sleep(8000);
		pageOm.agregarGestion("Cambio de SIM");
		sleep(5000);
		/*driver.findElement(By.name("ta_submit_order")).click();
		sleep(35000);
		pageOm.cambiarVentanaNavegador(1);
		sleep(2000);
		driver.findElement(By.id("idlist")).click();
		sleep(5000);
		pageOm.cambiarVentanaNavegador(0);
		sleep(12000);*/
	}
	
	@Test(groups="OM", priority=1, dataProvider="SalesCuentaBolsa") 
	public void TS_CRM_Nominacion(String sCuenta, String sDni, String sLinea) throws Exception {
		SalesBase sb = new SalesBase(driver);
		SalesNominaciones SN= new SalesNominaciones();
		sb.DesloguearLoguear("venta", 3);
		HomeBase homePage = new HomeBase(driver);
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    String a = driver.findElement(By.id("tsidLabel")).getText(); 
	    if (a.contains("Ventas")){}
	    else {
	    	homePage.switchAppsMenu();
	    	try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	homePage.selectAppFromMenuByName("Ventas");
	    	try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}            
	    }
	    driver.findElement(By.xpath("//a[@href=\'https://crm--sit--c.cs14.visual.force.com/apex/taClientSearch']")).click();		
	    try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}    
	    String NyA = sCuenta;
		sb.BuscarAvanzada(NyA.split(" ")[0], NyA.split(" ")[1], "", "", "");
		WebElement cli = driver.findElement(By.id("tab-scoped-1"));
		if (cli.findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElement(By.tagName("div")).getText().equals("Cliente Wholesale")) {
			cli.findElement(By.tagName("tbody")).findElement(By.tagName("tr")).click();
		}
		sleep(3000);
		WebElement cua = driver.findElement(By.id("tab-scoped-1")).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(36).findElements(By.tagName("td")).get(6).findElement(By.tagName("svg"));
		System.out.println("1: "+driver.findElement(By.id("tab-scoped-1")).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(36).findElements(By.tagName("td")).get(1).getText());
		cua.click();
		sleep(13000);
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact2("DNI", sDni, sLinea);
		try {contact.ingresarMail("asdads@gmail.com", "si");}catch (org.openqa.selenium.ElementNotVisibleException ex1) {}
		contact.tipoValidacion("documento");
		contact.subirArchivo("C:\\Users\\florangel\\Downloads\\mapache.jpg", "si");
		BasePage bp = new BasePage(driver);
		bp.setSimpleDropdown(driver.findElement(By.id("ImpositiveCondition")), "IVA Consumidor Final");
		sb.Crear_DomicilioLegal("Buenos Aires", "Vicente Lopez", "falsa", "", "1000", "", "", "1549");
		//sleep(10000);
		//contact.subirformulario("C:\\Users\\florangel\\Downloads\\form.pdf", "si");
		sleep(35000);
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope"));
		driver.findElement(By.id("FinishProcess_nextBtn")).click();
		//SN.TS95140_Nominacion_Argentino_Verificar_creacion_de_la_cuenta(sCuenta, sDni, sLinea);
		sleep(10000);
		driver.switchTo().defaultContent();
		sleep(2000);
		OM pageOm=new OM(driver);
		pageOm.goToMenuOM();
		
		//click +
		sleep(5000);
		
		pageOm.clickMore();
		sleep(3000);
		
		//click en Ordenes
		pageOm.clickOnListTabs("Pedidos");
		sleep(5000);
		pageOm.primeraOrden();
	}
	
	
	@Test(groups="GestionOM") 
	public void TS_CRM_CambioDeTitularidad() throws InterruptedException {
		
		TS_CRM_OM_Gestion_Alta_De_Linea();
		driver.switchTo().defaultContent();
		sleep(12000);
		OM pageOm=new OM(driver);
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		driver.findElement(By.id("RequestDate")).sendKeys(dateFormat.format(pageOm.fechaAvanzada()));
		//driver.findElement(By.id("RequestDate")).sendKeys("11-01-2018");
		
		//click Next
		WebElement next=driver.findElement(By.cssSelector(".form-control.btn.btn-primary.ng-binding"));
		next.click();
		sleep(30000);
		
		//Click ViewRecord
		driver.findElement(By.id("-import-btn")).click();
		sleep(7000);
		
		//click en goto list en (TA Price Book)
		WebElement goToList=driver.findElement(By.className("pShowMore")).findElements(By.tagName("a")).get(1);
		sleep(500);
		pageOm.scrollDown(driver.findElement(By.className("pShowMore")));
		sleep(500);
		goToList.click();
		sleep(7000);
		
		//Cambiar Cuenta en Servicios
		pageOm.cambioDeCuentaServicios("CambioDeTitularidad");
		
		//Click para retonar a la orden
		driver.findElement(By.className("ptBreadcrumb")).findElement(By.tagName("a")).click();
		sleep(4000);
		
		//Editamos Orden
		pageOm.cambiarCuentaYGestionEnOrden("CambioDeTitularidad","Cambio de titularidad");
		sleep(4000);
		
		//Finalizamos el proceso con TA SUBMIT ORDER
		driver.findElement(By.name("ta_submit_order")).click();
	}

}
