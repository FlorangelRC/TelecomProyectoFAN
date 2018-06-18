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
import Pages.RegistroEventoMasivo;
import Pages.SCP;
import Pages.setConexion;

import Pages.setConexion;

public class moduloOM extends TestBase {
	
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
	public void TS8231_CRM_OM_Ordenes_Panel_principal_Crear_una_Orden() {
		OM pageOm=new OM(driver);
		pageOm.crearOrden("AutomaAll");
		assertTrue(driver.findElement(By.cssSelector(".noSecondHeader.pageType")).isDisplayed());
		//assertTrue(pageOm.crearVistaOM("Almer","AutomaAll"));
		
	}
	
	
	@Test(groups="OM")
	public void TS6727_CRM_OM_Ordenes_Order_Detail_Visualización_del_flujo_de_orquestación() {
		OM pageOm=new OM(driver);
		//pageOm.crearVistaOM("AutOrders","AutomaOM");
		Select allOrder=new Select(driver.findElement(By.id("fcf")));
		allOrder.selectByVisibleText("AUTPO");
		sleep(2000);
		try {
		driver.findElement(By.name("go")).click();
		sleep(2000);
		}catch(Exception e) {};
		
		WebElement primeraOrden=driver.findElement(By.cssSelector(".x-grid3-row.x-grid3-row-first"));
		primeraOrden.findElement(By.xpath("//table/tbody/tr/td[3]")).click();
		
		List<WebElement> nPedidos=driver.findElement(By.className("x-grid3-scroller")).findElement(By.className("x-grid3-body"))
				.findElements(By.cssSelector(".x-grid3-col.x-grid3-cell.x-grid3-td-ORDERS_ORDER_NUMBER"));
		nPedidos.get(0).click();
		/*
		for(WebElement p:nPedidos) {
			//System.out.println(p.getText());
			//if(p.getText().endsWith("3879")) {
			if(p.getText().endsWith("17832")) {
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+p.getLocation().y+")");
				try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
				p.findElement(By.tagName("a")).click();
				break;}	
			}*/
		sleep(5000);
		
		
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.xpath("//*[@id=\"CF00Nc0000001pSu8_ilecell\"]")).getLocation().y+")");
		try {Thread.sleep(500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.xpath("//*[@id=\"lookupa27c0000005JPh600Nc0000001pSu8\"]")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BasePage frame=new BasePage(driver);
		driver.switchTo().frame(frame.getFrameForElement(driver, By.id("canvas")));
		try {Thread.sleep(500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		assertTrue(driver.findElement(By.id("canvas")).isDisplayed());
		
	}
	
	//No tenemos el Codigo del test
	@Test(groups="OM")
	public void TS_CRM_OM_Interfaces_Cliente_Nuevo_PP_CN_Sin_delivery_Sin_VAS_Paso_1_S203_createSubscriber_Huawei_Verificacion_de_request_response() {
		Select allOrder=new Select(driver.findElement(By.id("fcf")));
		allOrder.selectByVisibleText("All Orders VICTOR OM");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try {
		driver.findElement(By.name("go")).click();}
		catch(Exception e) {};
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> nPedidos=driver.findElement(By.className("x-grid3-scroller")).findElement(By.className("x-grid3-body"))
				.findElements(By.cssSelector(".x-grid3-col.x-grid3-cell.x-grid3-td-ORDERS_ORDER_NUMBER"));
		for(WebElement p:nPedidos) {
			//System.out.println(p.getText());
			if(p.getText().endsWith("3879")) {
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+p.getLocation().y+")");
				try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
				p.findElement(By.tagName("a")).click();
				break;}	
			}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.xpath("//*[@id=\"CF00Nc0000001pSu8_ilecell\"]")).getLocation().y+")");
		try {Thread.sleep(500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.xpath("//*[@id=\"lookupa27c0000005JPh600Nc0000001pSu8\"]")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BasePage frame=new BasePage(driver);
		driver.switchTo().frame(frame.getFrameForElement(driver, By.id("canvas")));
		try {Thread.sleep(500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//assertTrue(driver.findElement(By.id("canvas")).isDisplayed());
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.xpath("//*[text()='Create subscriber']")).getLocation().y+")");
		driver.findElement(By.xpath("//*[text()='Create subscriber']")).click();
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTab.get(1));
		
		driver.findElement(By.className("listTitle")).click();
		assertTrue(driver.findElement(By.xpath("//*[text()='Request']")).isDisplayed()&&driver.findElement(By.xpath("//*[text()='Response']")).isDisplayed());
	}
	
	@Test(groups="OM")
	public void TS6716_CRM_OM_Ordenes_Panel_principal_Ingreso() {
		Select allOrder=new Select(driver.findElement(By.id("fcf")));
		allOrder.selectByVisibleText("All Orders VICTOR OM");
		sleep(1000);
		try {driver.findElement(By.name("go")).click();}catch(org.openqa.selenium.NoSuchElementException e) {}
		sleep(3000);
		List<WebElement> Orders=driver.findElement(By.className("x-grid3-scroller")).findElement(By.className("x-grid3-body"))
				.findElements(By.cssSelector(".x-grid3-col.x-grid3-cell.x-grid3-td-ORDERS_ORDER_NUMBER"));
		assertTrue(Orders.get(0).isDisplayed());
		//System.out.println(Orders.get(0).getText());
	}
	
	
	@Test(groups="OM")
	public void TS6718_CRM_OM_Ordenes_Panel_principal_Cantidad_de_ordenes_Mas_ordenes_que_el_valor_de_paginado() {
		Select allOrder=new Select(driver.findElement(By.id("fcf")));
		allOrder.selectByVisibleText("All Orders VICTOR OM");
		sleep(1000);
		try {driver.findElement(By.name("go")).click();}catch(org.openqa.selenium.NoSuchElementException e) {}
		sleep(3000);
		WebElement nDePagina=driver.findElement(By.className("pageInput"));
		int pTotal=Integer.parseInt(nDePagina.getAttribute("Maxlength"));
		int pActual=Integer.parseInt(nDePagina.getAttribute("value"));
		OM pageOM=new OM(driver);
		//Si hay una sola Pagina
		if(pActual==pTotal) {
			List<WebElement> nOrders=driver.findElement(By.className("x-grid3-scroller")).findElement(By.className("x-grid3-body"))
			.findElements(By.cssSelector(".x-grid3-col.x-grid3-cell.x-grid3-td-ORDERS_ORDER_NUMBER"));
			WebElement ultimaOrden=nOrders.get(nOrders.size()-1);
			assertTrue(pageOM.scrollDownInAView(ultimaOrden));
		}
		//SI hay mas Paginas
		else {
			while(pActual<pTotal) {
				sleep(3000);
				try{
				WebElement next=driver.findElement(By.className("next"));
				next.click();
				}catch(org.openqa.selenium.NoSuchElementException e) {}
				pActual++;
			}
			List<WebElement> nOrders=driver.findElement(By.className("x-grid3-scroller")).findElement(By.className("x-grid3-body"))
					.findElements(By.cssSelector(".x-grid3-col.x-grid3-cell.x-grid3-td-ORDERS_ORDER_NUMBER"));
					WebElement ultimaOrden=nOrders.get(nOrders.size()-1);
					assertTrue(pageOM.scrollDownInAView(ultimaOrden));
		}
	
	}
	
	@Test
	public void cambioDeTitularidad() {
		
	
		OM pageOm=new OM(driver);
		pageOm.selectVistaByVisibleText("AlmerOM");

		//Selecciona la primera cuenta de la lista en la vista seleccionada
		WebElement primeraCuenta=driver.findElement(By.cssSelector(".x-grid3-col.x-grid3-cell.x-grid3-td-SALES_ACCOUNT_NAME"));
		primeraCuenta.findElement(By.tagName("div")).findElement(By.tagName("a")).click();
		sleep(5000);
		
		BasePage frame=new BasePage(driver);
		driver.switchTo().frame(frame.getFrameForElement(driver, By.cssSelector(".panel.panel-default.panel-assets")));
		
		//Selecciona el ultimo asset
		List <WebElement> assets= driver.findElement(By.cssSelector(".panel.panel-default.panel-assets")).findElements(By.cssSelector(".root-asset.ng-scope"));
		assets.get(assets.size()-1).findElement(By.className("p-check")).click();
		
		//click en boton
		WebElement changeToOrder=driver.findElement(By.className("asset-action")).findElement(By.xpath("//button[2]"));
		changeToOrder.click();
		sleep(10000);
		driver.switchTo().defaultContent();
		
		//Selecciono fecha
		//driver.switchTo().frame(frame.getFrameForElement(driver, By.id("RequestDate")));
		//OM pageOm=new OM(driver);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		//driver.findElement(By.id("RequestDate")).sendKeys(dateFormat.format(pageOm.fechaAvanzada()));*/
		
		
		
		driver.findElement(By.id("RequestDate")).sendKeys("10-29-2018");
		
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
		List<WebElement> listadoDeServicios=driver.findElement(By.className("pbBody")).findElement(By.className("list")).findElements(By.tagName("tr"));
		if(listadoDeServicios.get(0).getAttribute("class").equalsIgnoreCase("headerRow"))
			listadoDeServicios.remove(0);
		int i=1;
		//System.out.println("aqui: "+listadoDeServicios.get(0).getText());
		while(i<listadoDeServicios.size()+1){
			driver.switchTo().defaultContent();
			sleep(1000);
			
			WebElement servicio=driver.findElement(By.className("pbBody")).findElement(By.className("list")).findElements(By.tagName("tr")).get(i);
			servicio.findElement(By.className("actionColumn")).findElements(By.tagName("a")).get(0).click();
			sleep(3000);
			
			Select action=new Select(driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[3]/table/tbody/tr[6]/td[2]")).findElement(By.tagName("select")));
			action.selectByVisibleText("Change");
			
			WebElement billingAccount=driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[3]/table/tbody/tr[11]/td[2]/span")).findElement(By.tagName("input"));
			billingAccount.click();
			billingAccount.clear();
			billingAccount.sendKeys("CambioDeTitularidad");
			sleep(300);
			
			//FALTA SERVICE ACCOUNT
			WebElement serviceAccount=driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[3]/table/tbody/tr[35]/td[4]/span")).findElement(By.tagName("input"));
			serviceAccount.click();
			serviceAccount.clear();
			serviceAccount.sendKeys("CambioDeTitularidad");
			
			sleep(500);
			//Guardar
			driver.findElement(By.name("save")).click();
			sleep(3000);
			i++;
		}
		
		//Click para retonar a la orden
		driver.findElement(By.className("ptBreadcrumb")).findElement(By.tagName("a")).click();
		sleep(4000);
		
		
		//Editamos Orden
		driver.findElement(By.name("edit")).click();
		sleep(4000);
		WebElement accountName=driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[3]/table/tbody/tr[3]/td[2]/div/span")).findElement(By.tagName("input"));
		accountName.click();
		accountName.clear();
		accountName.sendKeys("CambioDeTitularidad");
		sleep(1000);
		Select gestion=new Select(driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[3]/table/tbody/tr[14]/td[4]/span")).findElement(By.tagName("select")));
		gestion.selectByValue("Cambio de titularidad");
		//Guardamos
		//System.out.println("Select: "+gestion.getAllSelectedOptions().get(5).getText());
		//gestion.selectByIndex(5);
		driver.findElement(By.name("save")).click();

		
		sleep(4000);
		
		//Finalizamos el proceso con TA SUBMIT ORDER
		driver.findElement(By.name("ta_submit_order")).click();
		//Aqui Termina
		
		
		//Falta Verificacion
	

	}
	
	
	@Test
	public void EliminarOrdersDeVista() {
		OM page=new OM(driver);
		page.deleteOrdersNoActivated("AlmerOM");
	}
	
}//Fin Clase
