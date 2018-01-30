package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

import java.awt.Button;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;

import Pages.BasePage;
import Pages.Bill;
import Pages.BillSimulation;
import Pages.DeliveryMethod;
import Pages.LineAssignment;
import Pages.Login;
import Pages.Order;
import Pages.OrdersTab;
import Pages.PaymentMethod;
import Pages.SalesBase;
import Pages.SerialInput;
import Pages.Ta_CPQ;
import Pages.Ta_CPQ.RightPanel;
import Pages.Ta_CPQ_Validate;
import Pages.setConexion;

import org.testng.Assert;

public class ta_CPQ extends TestBase {
	
	private String province = "Buenos Aires";
	private String locality = "BALCARCE";
	private String street = "Av. Gonzalez Chavez";
	private String streetNumber = "485";
	private String postalCode = "7620";
	private String  typeZone = "Urbano";
	
	protected WebDriver driver;
	protected  WebDriverWait wait;

	//@AfterClass(groups = {"Fase2"})
	public void tearDown() {
		driver.close();
	}
	
	@BeforeClass(groups = {"Fase2"})
	public void Init() throws Exception
	{
		this.driver = setConexion.setupEze();
		 wait = new WebDriverWait(driver, 10);
		//try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		 loginFranciso(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
		if (!driver.findElement(By.id("tsidLabel")).getText().equals("Ventas")){
			driver.findElement(By.id("tsidLabel")).click();
			driver.findElement(By.xpath("//a[@href=\"/home/home.jsp?tsid=02u41000000QWha\"]")).click();
		}
		
		//driver.findElement(By.xpath("//a[@href=\'https://crm--SIT--c.cs14.visual.force.com/apex/taClientSearch']")).click();
		//driver.findElement(By.xpath("//a[@href=\'https://crm--sit--c.cs14.visual.force.com/apex/taClientSearch']")).click();
	}

	@BeforeMethod
	public void setup() throws Exception {		
		/*try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		if (!driver.findElement(By.id("tsidLabel")).getText().equals("Ventas")){
			driver.findElement(By.id("tsidLabel")).click();
			driver.findElement(By.xpath("//a[@href=\"/home/home.jsp?tsid=02u41000000QWha\"]")).click();
		}
		
		//driver.findElement(By.xpath("//a[@href=\'https://crm--SIT--c.cs14.visual.force.com/apex/taClientSearch']")).click();*/
		
		driver.get("https://crm--sit--c.cs14.visual.force.com/apex/taClientSearch");
		
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta("DNI", "32323232");
		SB.acciondecontacto("catalogo");
		try {Thread.sleep(30000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

	}
	
	//@AfterMethod(groups = {"Fase2"})
	public void returnToSales() {
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.get("https://crm--sit.cs14.my.salesforce.com/801c0000000Ec64");
	      }
	    
	       
	//No reparado
	@Test
	public void TS6816_checkSimCardAssignment() {
		System.out.println("Empiezan el caso");
		sleep(30000);
		System.out.println("Pasaron 30 Segundos.");
		Ta_CPQ page3 = new Ta_CPQ(driver);
		try { for(WebElement e : driver.findElements(By.className("cpq-product-name"))) {
			page3.clickOnDelete();
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		} } catch (java.lang.IndexOutOfBoundsException e) {}
		page3.addPlan();
		System.out.println("Ya agrego el plan");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		System.out.println("pasaron 10 segundos");
		//BasePage frame=new BasePage(driver);
		//driver.switchTo().frame(frame.getFrameForElement(driver, By.cssSelector(".slds-button__icon.slds-button__icon--small.slds-button__icon--left.fix-slds-close-switch")));

		
		//driver.findElement(By.cssSelector(".slds-button__icon.slds-button__icon--small.slds-button__icon--left.fix-slds-close-switch")).click();
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertEquals("1", page3.getSimCardValue()); 	
	}
	
	//Verificando
	@Test(groups={"Sales", "AltaLinea", "Ola1"})
	public void TS94480_checkPaperCanIsPresent() {
		System.out.println("Empiezan el caso");
		sleep(30000);
		System.out.println("Pasaron 30 Segundos.");
		
		Ta_CPQ page3 = new Ta_CPQ(driver);
		try { for(WebElement e : driver.findElements(By.className("cpq-product-name"))) {
			page3.clickOnDelete();
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		} } catch (java.lang.IndexOutOfBoundsException e) {}
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

		page3.addPlan();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(page3.isPaperCanPresent());
	}
	
	@Test(groups={"Sales", "AltaLinea", "Ola1"})
	public void TS94479_checkPlanIsDeleted() {
		Ta_CPQ page3 = new Ta_CPQ(driver);
		try { for(WebElement e : driver.findElements(By.className("cpq-product-name"))) {
			page3.clickOnDelete();
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		} } catch (java.lang.IndexOutOfBoundsException e) {}
		page3.addPlan();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page3.clickOnDelete();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertFalse(page3.isPlanPresent());
	}
	
	@Test(groups={"Sales", "AltaLinea", "Ola1"})
	public void TS94481_checkPaperCanLabel() {
		Ta_CPQ page3 = new Ta_CPQ(driver);
		try { for(WebElement e : driver.findElements(By.className("cpq-product-name"))) {
			page3.clickOnDelete();
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		} } catch (java.lang.IndexOutOfBoundsException e) {}
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page3.addPlan();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertEquals("Quitar el producto del carrito", page3.getPaperCanLabel());
	}
	
	//Listo 246-01-18 Falla por privilegios
	@Test(groups={"Sales", "AltaLinea", "Ola1"})
	public void TS94495_checkNoLineAvailableMessageAndCancelPlan() {
		Ta_CPQ page3 = new Ta_CPQ(driver);
		try { for(WebElement e : driver.findElements(By.className("cpq-product-name"))) {
			page3.clickOnDelete();
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		} } catch (java.lang.IndexOutOfBoundsException e) {}
		page3.addPlan();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page3.clickOnSalesConfig();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		LineAssignment page4 = new LineAssignment(driver);
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertEquals("No se encontro linea disponible", page4.getNoLineMessage());
		page4.cancelLineAssignment();
	}
	
	@Test(groups={"Sales", "AltaLinea", "Ola1"})
	public void TS94496_checkPlanInformation() {
		Ta_CPQ page3 = new Ta_CPQ(driver);
		try { for(WebElement e : driver.findElements(By.className("cpq-product-name"))) {
			page3.clickOnDelete();
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		} } catch (java.lang.IndexOutOfBoundsException e) {}
		page3.addPlan();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page3.openArrow();
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(page3.getPlanInformation());
	}

	
	@Test(groups={"Sales", "AltaLinea", "Ola1"})
	public void TS94486_wrongICCDFormat() {
		Ta_CPQ page3 = new Ta_CPQ(driver);
		try { for(WebElement e : driver.findElements(By.className("cpq-product-name"))) {
			page3.clickOnDelete();
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		} } catch (java.lang.IndexOutOfBoundsException e) {}
		page3.addPlan();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page3.clickOnSalesConfig();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		LineAssignment page4 = new LineAssignment(driver);
		page4.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		DeliveryMethod page5 = new DeliveryMethod(driver);
		page5.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BillSimulation page6 = new BillSimulation(driver);
		page6.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		PaymentMethod page7 = new PaymentMethod(driver);
		page7.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		SerialInput page8 = new SerialInput(driver);
		page8.setICCD("123456");
		page8.clickOnValidateICCD();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertEquals("Error de formato", page8.getValidationMessage("wrong"));
	}
	
	@Test(groups={"Sales", "AltaLinea", "Ola1"})
	public void TS94488_rightICCDFormat() {
		Ta_CPQ page3 = new Ta_CPQ(driver);
		/*
		try { for(WebElement e : driver.findElements(By.className("cpq-product-name"))) {
			page3.clickOnDelete();
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		} } catch (java.lang.IndexOutOfBoundsException e) {}
		*/
		page3.deleteAddedProducts();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page3.addPlan();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page3.clickOnSalesConfig();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		LineAssignment page4 = new LineAssignment(driver);
		page4.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		DeliveryMethod page5 = new DeliveryMethod(driver);
		page5.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//BillSimulation page6 = new BillSimulation(driver);
		//page6.clickOnNext();
		//try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		PaymentMethod page7 = new PaymentMethod(driver);
		page7.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		SerialInput page8 = new SerialInput(driver);
		page8.setICCD("1234567891");
		page8.clickOnValidateICCD();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertEquals("El ICCID fue asignado", page8.getValidationMessage("right"));
	}

    
	@Test(groups={"Sales", "AltaLinea", "Ola1"})
	public void TS94491_checkAssignButtonIsAvailable() {
		Ta_CPQ page3 = new Ta_CPQ(driver);
		try { for(WebElement e : driver.findElements(By.className("cpq-product-name"))) {
			page3.clickOnDelete();
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		} } catch (java.lang.IndexOutOfBoundsException e) {}
		page3.addPlan();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page3.clickOnSalesConfig();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		LineAssignment page4 = new LineAssignment(driver);
		page4.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		DeliveryMethod page5 = new DeliveryMethod(driver);
		page5.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BillSimulation page6 = new BillSimulation(driver);
		page6.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		PaymentMethod page7 = new PaymentMethod(driver);
		page7.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		SerialInput page8 = new SerialInput(driver);
		page8.setICCD("1234567890");
		Assert.assertEquals("Asignar", page8.getAssignButtonLabel());
	}
	
	@Test(groups={"Sales", "AltaLinea", "Ola1"})
	public void TS94487_checkOrderStatusIsPending() {
		Ta_CPQ page3 = new Ta_CPQ(driver);
		try { for(WebElement e : driver.findElements(By.className("cpq-product-name"))) {
			page3.clickOnDelete();
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		} } catch (java.lang.IndexOutOfBoundsException e) {}
		page3.addPlan();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page3.clickOnSalesConfig();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		LineAssignment page4 = new LineAssignment(driver);
		page4.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		DeliveryMethod page5 = new DeliveryMethod(driver);
		page5.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BillSimulation page6 = new BillSimulation(driver);
		page6.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		PaymentMethod page7 = new PaymentMethod(driver);
		page7.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		SerialInput page8 = new SerialInput(driver);
		page8.setICCD("1234567890");
		page8.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Bill page9 = new Bill(driver);
		Assert.assertTrue(page9.checkOrderStatusDisplays());
		Assert.assertEquals("Estado de la orden : Pendiente de pago", page9.getOrderStatus());
	}
	
	//Listo  26-01-18
	@Test(groups={"Sales", "AltaLinea", "Ola1"})
	public void TS94482_deleteAllPlans() {
		Ta_CPQ page3 = new Ta_CPQ(driver);
		try { for(WebElement e : driver.findElements(By.className("cpq-product-name"))) {
			page3.clickOnDelete();
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		} } catch (java.lang.IndexOutOfBoundsException e) {}
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page3.addPlan();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page3.addPlan();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page3.addPlan();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page3.addPlan();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page3.clickOnDelete();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page3.clickOnDelete();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page3.clickOnDelete();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page3.clickOnDelete();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertEquals("Cart is empty.", page3.getEmptyCartMessage());
	}
	
	@Test
	public void TS7007_checkCancelOrder() {
		Ta_CPQ page3 = new Ta_CPQ(driver);
		try { for(WebElement e : driver.findElements(By.className("cpq-product-name"))) {
			page3.clickOnDelete();
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		} } catch (java.lang.IndexOutOfBoundsException e) {}
		page3.addPlan();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page3.clickOnSalesConfig();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		LineAssignment page4 = new LineAssignment(driver);
		page4.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		DeliveryMethod page5 = new DeliveryMethod(driver);
		page5.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BillSimulation page6 = new BillSimulation(driver);
		page6.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		PaymentMethod page7 = new PaymentMethod(driver);
		page7.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		SerialInput page8 = new SerialInput(driver);
		page8.setICCD("1234567890");
		page8.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Bill page9 = new Bill(driver);
		page9.cancelLineAssignment();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertEquals("Ventas", driver.findElement(By.id("tsidLabel")).getText());
	}
	
	@Test
	public void TS6890_checkCashAsValueForPaymentMethod() {
		Ta_CPQ page3 = new Ta_CPQ(driver);
		try { for(WebElement e : driver.findElements(By.className("cpq-product-name"))) {
			page3.clickOnDelete();
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		} } catch (java.lang.IndexOutOfBoundsException e) {}
		page3.addPlan();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page3.clickOnSalesConfig();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		LineAssignment page4 = new LineAssignment(driver);
		page4.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		DeliveryMethod page5 = new DeliveryMethod(driver);
		page5.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BillSimulation page6 = new BillSimulation(driver);
		page6.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		PaymentMethod page7 = new PaymentMethod(driver);
		Assert.assertTrue(page7.getPaymentMethod());
	}
	
	@Test
	public void TS6889_checkPaymentMethodIsPresent() {
		Ta_CPQ page3 = new Ta_CPQ(driver);
		try { for(WebElement e : driver.findElements(By.className("cpq-product-name"))) {
			page3.clickOnDelete();
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		} } catch (java.lang.IndexOutOfBoundsException e) {}
		page3.addPlan();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page3.clickOnSalesConfig();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		LineAssignment page4 = new LineAssignment(driver);
		page4.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		DeliveryMethod page5 = new DeliveryMethod(driver);
		page5.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		/*BillSimulation page6 = new BillSimulation(driver);
		page6.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}*/
		PaymentMethod page7 = new PaymentMethod(driver);
		Assert.assertTrue(page7.isPaymentMethodPresent());
	}
	
	@Test
	public void TS6895_checkDefaultValueForDeliveryMethod() {
		Ta_CPQ page3 = new Ta_CPQ(driver);
		try { for(WebElement e : driver.findElements(By.className("cpq-product-name"))) {
			page3.clickOnDelete();
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		} } catch (java.lang.IndexOutOfBoundsException e) {}
		page3.addPlan();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page3.clickOnSalesConfig();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		LineAssignment page4 = new LineAssignment(driver);
		page4.clickOnNext();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		DeliveryMethod page5 = new DeliveryMethod(driver);
		Assert.assertEquals("Presencial", page5.getCurrentValueForDeliveryMethod());
	}
	
	@Test(groups={"Sales", "AltaLinea", "Ola1"})
	public void TS94617_CRM_Fase_1_SalesCPQ_Alta_Linea_Buscar_Cliente_Buscar_por_Nombre_del_plan_V360() {
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
		WebElement inputSearch = driver.findElement(By.xpath("//input[@placeholder=\"Search\"]"));
		inputSearch.sendKeys("Plan Prepago Nacional");
		
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
		WebElement result = driver.findElement(By.xpath(".//p"));
		Assert.assertEquals(result.getText(), "Plan Prepago Nacional");
			
	}
	
	@Test
	public void TS6826_CRM_Fase_1_SalesCPQ_Alta_Linea_Carrito_Verificar_selección_de_productos() {
		Ta_CPQ cart = new Ta_CPQ (driver);
		String productName ="productName"; //productName
		String productNameAdded = "true"; //productNameAdded
		
		cart.deleteAddedProducts();
		for (WebElement div: cart.getDivsProducts()) {
			if (!cart.requiresPrefactibility(div)) {
				WebElement addToCartButton = div.findElement(By.cssSelector(".slds-button.slds-button--neutral.add-button"));
				addToCartButton.click();
				try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
				productName = div.findElement(By.cssSelector(".slds-tile__title.slds-truncate.product-name")).getText();
				try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
				productNameAdded = driver.findElement(By.xpath(".//div[@class=\"cpq-item-no-children\"]/span")).getText();
				break;
			}
		}		
		Assert.assertEquals (productNameAdded, productName);
	}


	@Test
	public void TS6827_CRM_Fase_1_SalesCPQ_Alta_Linea_Configurar_Nueva_Linea_Boton_Siguiente() {
		Ta_CPQ cart = new Ta_CPQ(driver);
		cart.addAnyProductToCart();
		Assert.assertNotEquals(cart.getCartStatus(),"Incomplete");
	}
	//Listo 26-01-18
	@Test
	public void TS6835_CRM_Fase_SalesCPQ_Alta_Linea_Configurar_Nueva_Linea_Lista_de_cuentas_del_cliente() {
		Ta_CPQ cart = new Ta_CPQ(driver);
		Assert.assertTrue(cart.getAccountSelector() != null);
	}
	//Listo 26-01-18
	@Test
	public void TS6836_CRM_Fase_1_SalesCPQ_Alta_Linea_Configurar_Nueva_Linea_Nueva_Cuenta() {
		Ta_CPQ cart = new Ta_CPQ(driver);
		Assert.assertTrue(cart.getButtonNewAccount() != null);
	}
	//Listo 26-01-18
	@Test(groups={"Sales", "AltaLinea", "Ola1"})
	public void TS94522_CRM_Fase_1_SalesCPQ_Alta_Linea_Carrito_Verificar_el_mensaje_al_vaciar_el_carrito_XX() {
		Ta_CPQ cart = new Ta_CPQ(driver);
		//cart.addAnyProductToCart();
		//cart.deleteAddedProducts();
		cart.addPlan();
		sleep(5000);
		cart.clickOnDelete();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//WebElement messageEmptyCart = driver.findElement(By.xpath(".//div[@class=\"slds-grid slds-grid--vertical-align-center slds-grid--align-center cpq-no-cart-items-msg\"]"));
		//Assert.assertEquals(messageEmptyCart.getText().trim(), "Cart is empty.");
		Assert.assertEquals("Cart is empty.", cart.getEmptyCartMessage());
	}
	
	//Listo 26-01-18 falta darle continuar, pero al darle continuar no hay privilegios.
	@Test(groups={"Sales", "AltaLinea", "Ola1"})
	public void TS94494_CRM_Fase_1_SalesCPQ_Alta_Linea_Configurar_Nueva_Linea_Buscar_nuevo_lote_de_lineas_pre_asignadas() {
		Ta_CPQ cart = new Ta_CPQ(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> buttonsRightPanel = driver.findElements(By.xpath("//a[@ng-class=\"{'cpq-category-item-selected' : isSelectedCategory(category.catalogName), 'cat-icon': !isSelectedCategory(category.catalogName)}\"]"));
		WebElement buttonShowPlans = buttonsRightPanel.get(1);
		buttonShowPlans.click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
		cart.addAnyProductToCart();
		sleep(5000);
		cart.getButtonNext().click();
		
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
		//Debería mostrarse una vista en la que se muestran los planes preasignados, pero hay cargado un bug dado que no se está mostrando esa descripción de los planes.
	}
	
	@Test(groups={"Sales", "AltaLinea", "Ola1"})
	public void TS94498_CRM_Fase_1_SalesCPQ_Alta_Linea_Configurar_Nueva_Linea_Modificar_linea_pre_asignada_ultimos_cuatro_digitos() {
		//Mismo bug que el TS6845. Se pueden tomar ese test  como base para automatizar éste hasta el Step 4 inclusive.
		assertTrue(false);
	}
	
	@Test(groups={"Sales", "AltaLinea", "Ola1"})
	public void TS94499_CRM_Fase_1_SalesCPQ_Alta_Linea_Configurar_Nueva_Linea_Presionar_el_boton_Buscar() {
		//Ídem TS6849
		assertTrue(false);
	}
	
	@Test(groups={"Sales", "AltaLinea", "Ola1"})
	public void TS94501_CRM_Fase_1_SalesCPQ_Alta_Linea_Configurar_Nueva_Linea_Visualizar_filtros_de_localidad_y_provincia_al_modificar_linea_XX(){
		//Ídem TS6849		
	}
	
	@Test(groups={"Sales", "AltaLinea", "Ola1"})
	public void TS94504_CRM_Fase_1_SalesCPQ_Alta_Linea_Configurar_Nueva_Linea_Visualizar_mensaje_y_opciones_de_lineas_no_disponibles() {
		//Ídem TS6849
		assertTrue(false);
	}
	
	@Test(groups={"Sales", "AltaLinea", "Ola1"})
	public void TS94506_CRM_Fase_1_SalesCPQ_Alta_Linea_Configurar_Nueva_Linea_Visualizar_una_descripcion_por_varios_productos_iguales() {
		//Ídem TS6849
		assertTrue(false);
	}
	
	@Test
	public void TS6858_CRM_Fase_1_SalesCPQ_Alta_Linea_Asignar_SIMCARD_Visualizar_mensaje_al_asignar_un_ICCID_No_disponible(){
		assertTrue(false);
	}
	
	//Listo 26-01-18 no hay costo
	@Test(groups={"Sales", "AltaLinea", "Ola1"})
	public void TS94515_CRM_Fase_1_SalesCPQ_Alta_Linea_Costo_Operacion_Validar_formato_del_monto() {
		Ta_CPQ cart = new Ta_CPQ(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		cart.selectFromRightPanel(RightPanel.DISPOSITIVOS);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement linkMore = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".product-link.slds-text-body--small.slds-float--right"))); 
		linkMore.click();
		
		WebElement waiter = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".slds-item--detail.slds-truncate")));
		List<WebElement> values = driver.findElements(By.cssSelector(".slds-item--detail.slds-truncate"));
		String[] precissionCounter = values.get(3).getText().split(",");
		
		Assert.assertEquals(precissionCounter[1].length(), 2);
	}
	
	//Listo 26-01-18 
	@Test
	public void TS6883_CRM_Fase_1_SalesCPQ_Alta_Linea_Costo_Operacion_Verificar_el_detalle_de_los_impuestos_aplicados_a_la_venta() {
		Ta_CPQ cart = new Ta_CPQ(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		cart.selectFromRightPanel(RightPanel.DISPOSITIVOS);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		cart.addAnyProductToCart();
		sleep(7000);
		cart.getButtonNext().click();
		sleep(10000);
		
		//WebElement waiter = wait.until(ExpectedConditions.elementToBeClickable(By.id("LineAssignment_nextBtn")));
		//BillSimulation bill = new BillSimulation (driver);
		//presiono Siguiente 3 veces para llegar al paso "Simulación de Factura"
		
		driver.findElement(By.id("LineAssignment_nextBtn")).click();
		sleep(7000);
		driver.findElement(By.xpath("//*[@id=\"DeliveryMethodConfiguration_nextBtn\"]/p")).click();
		sleep(7000);
		/*for (int i=0; i<1; i++) {
			bill.clickOnNext();
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		}*/
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"Invoice\"]/div/ng-include/div/div[2]/div[2]/div[2]")).isDisplayed());
		//verificar impuestos
		
	}
	//LIsto 26-01-18
	/*
	 * TODO: el assert debería verificar que el dropdown con id "DeliveryMethod" ofrezca varios métodos de entrega.
	 * Actualmente el botón está bloqueado, y no se puede ver qué opciones contiene.
	 * */
	@Test(groups={"Sales", "AltaLinea", "Ola1"})
	public void TS94518_CRM_Fase_1_SalesCPQ_Alta_Linea_Costo_Operacion_Verificar_opciones_del_carrito_Boton_Siguiente() {
		Ta_CPQ cart = new Ta_CPQ(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		cart.selectFromRightPanel(RightPanel.DISPOSITIVOS);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		cart.addAnyProductToCart();
		sleep(4000);
		cart.getButtonNext().click();
		sleep(7000);
		//WebElement waiter = wait.until(ExpectedConditions.elementToBeClickable(By.className("ng-binding")));
		//BillSimulation bill = new BillSimulation (driver);
		//presiono Siguiente 1 vezpara llegar al paso "Modo de Entrega"
		//bill.clickOnNext();
		driver.findElement(By.id("LineAssignment_nextBtn")).click();
		sleep(5000);
		List<WebElement> inputDeliveryMethod = driver.findElements(By.id("DeliveryMethod"));
		Assert.assertTrue(inputDeliveryMethod.size() > 0);
	}
	
	@Test(groups={"Sales", "AltaLinea", "Ola1"})
	public void TS94520_CRM_Fase_1_SalesCPQ_Alta_Linea_Costo_Operacion_Visualizar_costo_cero_en_modo_de_entrega() {
		assertTrue(false);
	}
	
	@Test
	public void TS6893_CRM_Fase_1_SalesCPQ_Alta_Linea_Modo_de_Entrega_Seleccionar_modo_de_entrega_presencial_Producto_Tangible () {
		Ta_CPQ cart = new Ta_CPQ(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		cart.selectFromRightPanel(RightPanel.DISPOSITIVOS);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		cart.addAnyProductToCart();
		sleep(7000);
		cart.getButtonNext().click();
		sleep(7000);
		//WebElement waiter = wait.until(ExpectedConditions.elementToBeClickable(By.className("ng-binding")));
		//BillSimulation bill = new BillSimulation (driver);
		//presiono Siguiente 1 vezpara llegar al paso "Modo de Entrega"
		//bill.clickOnNext();
		driver.findElement(By.id("LineAssignment_nextBtn")).click();
		sleep(7000);
		//driver.findElement(By.xpath("//*[@id=\"DeliveryMethodConfiguration_nextBtn\"]/p")).click();
		//sleep(7000);
		WebElement selectDeliveryMethod = driver.findElement(By.id("DeliveryMethod"));
		Select deliveryMethod = new Select(selectDeliveryMethod);
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<String> optionsDeliveryMethod = new ArrayList<String>();
		for (WebElement option: deliveryMethod.getOptions()) {
			optionsDeliveryMethod.add(option.getText());
		}
		
		Assert.assertTrue(optionsDeliveryMethod.contains("Presencial"));
	}
	
	@Test//(groups = {"Fase2-1"})
	public void TS11950_CRM_Fase_2_SalesCPQ_Alta_Linea_Nueva_Venta_Verificar_campo_desplegable_con_cuentas_activas() {
		TS6835_CRM_Fase_SalesCPQ_Alta_Linea_Configurar_Nueva_Linea_Lista_de_cuentas_del_cliente();
	}
	
	@Test //(groups = {"Fase2-1"})
	public void TS11951_CRM_Fase_2_SalesCPQ_Alta_Linea_Nueva_Venta_Verificar_que_se_habilite_la_opcion_de_creacion_de_cuenta_nueva() {
		TS6836_CRM_Fase_1_SalesCPQ_Alta_Linea_Configurar_Nueva_Linea_Nueva_Cuenta();
	}
	
	/**Se verifica que el sistema muestra disponibles los ciclos de facturacion 1, 7, 14 y 21*
	 * 
	 */
	@Test(groups={"Sales", "NuevaVenta", "Ola1"})
	public void TS94708_CRM_Fase_2_SalesCPQ_Nueva_Venta_Orden_Venta_Verficar_ciclos_de_facturacion_disponibles(){
		Ta_CPQ cart = new Ta_CPQ(driver);
		cart.deleteAddedProducts();
		cart.addAnyProductToCart();	
		
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		cart.getButtonNext().click();
		
		LineAssignment lineAssignment = new LineAssignment (driver);
		lineAssignment.clickOnNext();
		BillSimulation billSimulation = new BillSimulation (driver);
		billSimulation.clickOnNext();
		DeliveryMethod deliveryMethod = new DeliveryMethod (driver);
		
		Assert.assertTrue(deliveryMethod.getBillingCycleOptions().contains("1"));
		Assert.assertTrue(deliveryMethod.getBillingCycleOptions().contains("7"));
		Assert.assertTrue(deliveryMethod.getBillingCycleOptions().contains("14"));
		Assert.assertTrue(deliveryMethod.getBillingCycleOptions().contains("21"));
	}
	
	@Test(groups={"Sales", "NuevaVenta", "Ola1"})
	public void TS94707_CRM_Fase_2_SalesCPQ_Nueva_Venta_Orden_Venta_Verficar_que_se_puede_modificar_el_ciclo_de_facturacion() {
		/*Se verifica que el sistema permite modificar el ciclo de facturacion*/
		
		Ta_CPQ cart = new Ta_CPQ(driver);
		cart.deleteAddedProducts();
		cart.addAnyProductToCart();	
		
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		cart.getButtonNext().click();
		
		LineAssignment lineAssignment = new LineAssignment (driver);
		lineAssignment.clickOnNext();
		BillSimulation billSimulation = new BillSimulation (driver);
		billSimulation.clickOnNext();
		DeliveryMethod deliveryMethod = new DeliveryMethod (driver);
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Select billingCycleSelect = new Select (deliveryMethod.getBillingCycle());
		billingCycleSelect.selectByValue("7");
		Assert.assertEquals(billingCycleSelect.getFirstSelectedOption().getText(), "7");
	}
	
	
	/**
	 * Se verifica que, cuando se selecciona un producto para linea movil 
	 * del Bundle Convergente, se agrega a la vista previa del carrito 
	 * y se habilita el boton Siguiente
	 */
	@Test(groups = {"Fase2-1"})
	public void TS15424_CRM_Fase_2_SalesCPQ_Nueva_Venta_Seleccion_Dispositivos_Verificar_boton_siguiente_habilitado() {
		Ta_CPQ cart = new Ta_CPQ(driver);
		cart.deleteAddedProducts();
		cart.selectFromRightPanel(RightPanel.BUNDLES);
		cart.addAnyProductToCartThatNeedsPrefactibility();
		
		Assert.assertNotEquals(cart.getCartStatus(),"Incomplete");
	}
	
	/**
	 * Se verifica que, cuando no se selecciona un producto para linea movil del 
	 * Bundle Convergente, no se agrega a la vista previa del carrito, no se encuentra habilitado el boton Siguiente
	 */
	@Test(groups = {"Fase2-1"})
	public void TS15423_CRM_Fase_2_SalesCPQ_Nueva_Venta_Seleccion_Dispositivos_Verificar_boton_siguiente_inhabilitado() {
		Ta_CPQ cart = new Ta_CPQ(driver);
		cart.deleteAddedProducts();
		cart.addAnyProductToCart();
		Assert.assertEquals(cart.getCartStatus(), "Incomplete");
		Assert.assertFalse(cart.getButtonNext().isEnabled());
	}
	
	/**
	 * Se visualiza que el producto movil se incorpora en forma automatica 
	 * dentro de la familia Dispositivos cuando se agrega a la vista previa del carrito
	 */
	@Test(groups = {"Fase2-1"})
	public void TS15422_CRM_Fase_2_SalesCPQ_Nueva_Venta_Seleccion_Dispositivos_Verificar_producto_incorporado_Autom_Familia_Dispositivos() {
		Ta_CPQ cart = new Ta_CPQ(driver);
		cart.deleteAddedProducts();
		cart.selectFromRightPanel(RightPanel.DISPOSITIVOS);
		cart.addAnyProductToCart();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
		
		WebElement leftProductDiv = cart.getAddableDivProduct(1);
		leftProductDiv = leftProductDiv.findElement(By.cssSelector(".slds-tile__title.slds-truncate.product-name"));
		String leftProductName = leftProductDiv.getText().trim();
		
		Assert.assertTrue(cart.getAddedProducts().contains(leftProductName));
	}
	
 	//Almer:listo. detalles:faltan planes para comparar
	@Test(groups = {"Fase2-1"})
	public void TS14361_CRM_Fase_2_SalesCPQ_Carrito_CreacionCategorias_Verificar_contenido_de_la_categoria_Planes(){
		Ta_CPQ cart = new Ta_CPQ(driver);
		  cart.deleteAddedProducts();
		  cart.selectFromRightPanel(RightPanel.PLANES);
		  try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		 //Falta completar por Bug del Sistema planes no disponibles.
		  boolean disponible=false;
		  try {
		  if(driver.findElement(By.cssSelector(".slds-tile__title slds-truncate.product-name")).isDisplayed()) {
			 if(cart.verificarSiContenido("plan movil")&&cart.verificarSiContenido("plan datos"))
				 disponible=true;
			 //aqui van los planes que se van a comparar
		  }
		  else
			  disponible=false;}
		  catch(org.openqa.selenium.NoSuchElementException e) {disponible=false;}
		  assertTrue(disponible);
	}
	
	//Almer:listo. Faltan los datos a comparar
	@Test(groups = {"Fase2-1"})
	public void TS14362_CRM_Fase_2_SalesCPQ_Carrito_CreacionCategorias_Verificar_contenido_de_la_categoria_TV(){
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Ta_CPQ cart = new Ta_CPQ(driver);
		  cart.deleteAddedProducts();
		  cart.selectFromRightPanel(RightPanel.TV);
		  try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		  //La palabra "combo" debe ser remplazada por los datos a comparar
		  assertTrue(cart.verificarSiContenido("Combo"));  
	}
	
	//Almer:listo
	@Test(groups = {"Fase2-1"})
	public void TS14357_CRM_Fase_2_SalesCPQ_Carrito_CreacionCategorias_Verificar_visualizacion_de_productos_y_servicios_agrupados_por_categoria() {
		Ta_CPQ cart = new Ta_CPQ(driver);
		  cart.deleteAddedProducts();
		  try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		  assertTrue(cart.categoriasDisponibles());
	}
	
	//Almer: listo
	@Test(groups={"Fase2-1"})
	public void TS14280_CRM_Fase_2_SalesCPQ_Carrito_Productos_Verificar_Orden_de_Familias_v360() {
		Ta_CPQ cart = new Ta_CPQ(driver);
		  cart.deleteAddedProducts();
		  try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		 assertTrue(cart.categoriasDisponiblesEnOrden());
	}
	
	//Almer:listo
	@Test(groups={"Fase2-1"})
	public void TS14282_CRM_Fase_2_SalesCPQ_Carrito_Productos_Verificar_Req_domicilio_de_instalacion_Producto(){
		Ta_CPQ cart = new Ta_CPQ(driver);
		  cart.deleteAddedProducts();
		  try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		  assertTrue(cart.CheckAnyProductRequiresPrefactibility());
	}
	
	//Almer:listo
	@Test(groups={"Fase2-1"})
	public void TS14284_CRM_Fase_2_SalesCPQ_Carrito_Productos_Verificar_Req_domicilio_de_instalacion_Familia(){
		Ta_CPQ cart = new Ta_CPQ(driver);
		  cart.deleteAddedProducts();
		  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		  cart.selectFromRightPanel(RightPanel.BUNDLES);
		  try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		  assertTrue(cart.CheckAnyProductRequiresPrefactibility());
	}
	
	//Almer:listo
	@Test(groups={"Fase2-1"})
	public void TS15331_CRM_Fase_2_SalesCPQ_Prefactibilidad_v360_Visualizar_boton_Prefactibilidad_en_v360(){
		Ta_CPQ cart = new Ta_CPQ(driver);
		  cart.deleteAddedProducts();
		  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		  WebElement boton = driver.findElement(By.cssSelector(".slds-list--horizontal.slds-col.slds-no-flex.slds-align-top")).findElement(By.className("slds-button-group")).findElement(By.cssSelector(".slds-button.slds-button--neutral"));
		  //boton.click();
		  assertTrue(boton.isDisplayed());
	}
	
	//Almer:listo
	@Test(groups={"Fase2-1"})
	public void TS14331_CRM_Fase_2_SalesCPQ_Ventas_ProductosEPC_Verificar_producto_incorporado_Automaticamente() {
		Ta_CPQ cart = new Ta_CPQ(driver);
		cart.deleteAddedProducts();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		cart.addAnyProductToCart();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
		WebElement leftProductDiv = cart.getAddableDivProduct(1);
		leftProductDiv = leftProductDiv.findElement(By.cssSelector(".slds-tile__title.slds-truncate.product-name"));
		String leftProductName = leftProductDiv.getText().trim();
		
		Assert.assertTrue(cart.getAddedProducts().contains(leftProductName));
	}
	
	//Ultimo de Nacho
	@Test(groups = {"Sales", "NuevaVenta", "Ola1"})
	public void TS94706_CRM_Fase_2_SalesCPQ_Nueva_Venta_Orden_Venta_Verficar_ciclo_de_facturacion_asignado_por_default() {
		Ta_CPQ cart = new Ta_CPQ(driver);
		cart.deleteAddedProducts();
		cart.addAnyProductToCart();
		WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(cart.getButtonNext()));
		nextButton.click();
		LineAssignment lineAssignmentPage = new LineAssignment(driver);
		lineAssignmentPage.clickOnNext();
		BillSimulation billSimulationPage = new BillSimulation(driver);
		billSimulationPage.clickOnNext();
		DeliveryMethod deliveryMethodPage = new DeliveryMethod (driver);
		Select billingCycleSelect = new Select(deliveryMethodPage.getBillingCycle());
		Assert.assertEquals(billingCycleSelect.getFirstSelectedOption().getText(), "21");
	}
}



	
	

