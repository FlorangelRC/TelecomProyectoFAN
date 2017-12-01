package Tests;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.sql.Driver;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.CasePage;
import Pages.CustomerCare;
import Pages.Login;
import Pages.customerInformation;
import Pages.setConexion;
import org.openqa.selenium.support.ui.Select;
public class CustomerCare360ViewInformationClient extends TestBase {



		
	private WebDriver driver;
	 	
	//@AfterClass(groups= "Fase2")
	public void tearDown() {
			driver.close();
	}
	
	@AfterMethod(groups= "Fase2")
	 public void alert (){
	  CustomerCare page = new CustomerCare(driver);
	  page.cerrarultimapestaña();
	  //driver.get("https://cs14.salesforce.com/console");
	  //login(driver);

	  try{
	   Alert alert = driver.switchTo().alert();
	   System.out.println(alert.getText());
	  }catch(org.openqa.selenium.NoAlertPresentException e){}
	 }

	@BeforeClass(groups= "Fase2")
	public void init() throws Exception
	{
		driver = setConexion.setupEze();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		System.out.println("entrando a login");
		login(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}	
	@BeforeMethod(groups= "Fase2")
	public void setUpTest() {
		driver.switchTo().defaultContent();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		if (!driver.getCurrentUrl().toString().contains("https://crm--sit.cs14.my.salesforce.com/console")){
			driver.findElement(By.id("tsidLabel")).click();
			try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.findElement(By.xpath("//a[@href=\"/console?tsid=02uc0000000D6Hd\"]")).click();}	
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		CustomerCare page = new CustomerCare(driver);
		page.cerrarultimapestaña();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		
	}
/*	
	@Test
	public void TS7137_BusinessDataPanelQuickAccessButtonsAccount() {
		CustomerCare page = new CustomerCare(driver);
		goToLeftPanel(driver, "Cuentas");
		page.elegircuenta("Andres Care");
		page.openleftpanel();		
		driver.findElements(By.className("ext-webkit.ext-chromehg"));
		driver.findElement(By.className("social"));
		
	}
	
	
		
	@Test
	public void TS7138_BusinessDataPanelPicklistCommercialDataAccount() {
		CustomerCare page = new CustomerCare(driver);
		goToLeftPanel(driver, "Cuentas");
		page.elegircuenta("Andres Care");
		page.openleftpanel();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.className("account-select"));
		
	}
		
		
	@Test(groups= "Fase2")
	public void TS16056_Sesion_Guiada_para_Cambios_de_Inicio_de_Ciclo_de_Facturacion_Funcionamiento_Boton_Sesion_Guiada() {
		CustomerCare page = new CustomerCare(driver);
		goToLeftPanel(driver, "Cuentas");
		page.elegircuenta("Andres Care");
		page.openrightpanel();
		page.SelectGestion("Cambio de ciclo");
		try {Thread.sleep(30000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page.ValidarCambioDeCiclo();
		
	}
		
		@Test(groups= "Fase2")
	public void TS16055_Sesion_Guiada_para_Cambios_de_Inicio_de_Ciclo_de_Facturacion__Boton_Sesion_Guiada() {
			CustomerCare page = new CustomerCare(driver);
			goToLeftPanel(driver, "Cuentas");
			page.elegircuenta("Andres Care");
			page.openrightpanel();
			page.ValidarBtnsGestion("Cambio de ciclo");
		}
		
		
		@Test(groups= "Fase2")
	public void TS15962_Sesion_Guiada_Para_Cambios_en_Condicion_Impositiva_Boton_de_sesion_guiada() {
			CustomerCare page = new CustomerCare(driver);
			goToLeftPanel(driver, "Cuentas");
			page.elegircuenta("Andres Care");
			page.openrightpanel();	
			page.ValidarBtnsGestion("Cambios de condición impositiva");
		}
			
		
		@Test(groups= "Fase2")
		//terminar
	public void TS16061_Line_Movements_Paso_0_Error_por_cliente_inactivo() {
			CustomerCare page = new CustomerCare(driver);
			goToLeftPanel(driver, "Cuentas");
			page.elegircuenta("Andres Care");
			page.openrightpanel();	
			page.SelectGestion("Cambio de ciclo");
			//page.validacion
			driver.switchTo().defaultContent();

		}
		
		
		@Test(groups= "Fase2")
		public void TS14567_Capacidades_de_Busqueda_Filtrar_Por_DNI() {
			CustomerCare page = new CustomerCare(driver);
			page.usarbuscadorsalesforce("30303030");
			//page.detectarframe();
			page.validarlabusqueda("Andres Care");
			driver.switchTo().defaultContent();

		}
		
		
		@Test(groups= "Fase2")
		public void TS14565_View_Capacidades_de_Busqueda_Visualizar_Filtro_Salesforce() {
			CustomerCare page = new CustomerCare(driver);
			page.validarbuscadorsalesforce();
			driver.switchTo().defaultContent();

		}
		
		

		@Test(groups= "Fase2")
		public void TS14570_Busqueda_de_Transacciones_Filtrar_Por_Numero_de_Caso() {
			CustomerCare page = new CustomerCare(driver);
			page.usarbuscadorsalesforce("00003035");
			//page.detectarframe();
			page.validarlabusqueda("00003035");
			driver.switchTo().defaultContent();

			
		}
		
		
		
		@Test(groups= "Fase2")
		public void TS12252_Billing_Group_User_Line_Movements_Paso_0_Error_por_cliente_inactivo() {
			CustomerCare page = new CustomerCare(driver);
			goToLeftPanel(driver, "Cuentas");
			page.editarcuenta("Fernando Care", "no", "inactive");
			page.elegircuenta("Fernando Care");
			page.SelectGestion("Movimientos de cuenta de facturaci");
			page.validarerrorpaso0();	
			driver.switchTo().defaultContent();
			page.cerrarultimapestaña();

		}
		
		
		
		@Test(groups= "Fase2")
		public void TS12251_Billing_Group_User_Line_Movements_Paso_0_Error_por_fraude_Cliente_inactivo() {
			CustomerCare page = new CustomerCare(driver);
			goToLeftPanel(driver, "Cuentas");
			page.editarcuenta("Fernando Care", "si", "inactive");
			page.elegircuenta("Fernando Care");
			page.SelectGestion("Movimientos de cuenta de facturaci");
			page.validarerrorpaso0();
			driver.switchTo().defaultContent();
			//page.cerrarultimapestaña();


		}
		@Test(groups= "Fase2")
		public void TS12262_Billing_Group_User_Line_Movements_Paso_1_Billing_Account_suspendida_por_fraude_No_se_visualiza() {
			CustomerCare page = new CustomerCare(driver);
			goToLeftPanel(driver, "Cuentas");
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			page.editarcuenta("Fernando Care", "no", "active");
			page.editarcuenta("Fernando Care Billing 1", "si", "active");
			page.editarcuenta("Fernando Care Billing 2", "no", "inactive");
			page.elegircuenta("Fernando Care");		
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();};
			page.SelectGestion("Movimientos de cuenta de facturacion");
			page.validarerrorpaso1("cuenta billing fraude no aparece");
			driver.switchTo().defaultContent();
			page.cerrarultimapestaña();


		}
		
		@Test(groups="Fase2")
		public void TS12261_Billing_Group_User_Line_Movements_Paso_1_Mover_Bundle_Se_mueven_todos_los_servicios() {
		CustomerCare page = new CustomerCare(driver);
		goToLeftPanel(driver, "Cuentas");
		page.editarcuenta("Fernando Care","no","active");
	//	page.editarcuenta("Fernando Care Billing 1", "no", "active");
	//  page.editarcuenta("Fernando Care Billing 2", "no", "active");
		page.elegircuenta("Fernando Care");
		page.SelectGestion("Movimientos de cuenta de facturaci");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();};
		page.serviciocambiadecuenta("Arnet 10 MB (Prueba)", "Fernando Care Billing 2");
		//page.SelectGestion("Movimientos de cuenta de facturacion");
		//page.validarerrorpaso1("servicio cambia de cuenta billing");

		
		}
		
		@Test(groups="Fase2")
		public void TS12244_Positive_Feedback_Suggestions_Generic_Interaction_No_Follow_up_Required_Creacion_de_los_Casos_Crear_Caso() {
		CustomerCare page = new CustomerCare(driver);
		CasePage page1 = new CasePage(driver);
		page.elegircuenta("Fernando Care");
		page.SelectGestion("sugerencia");
		page.crearsugerencia("Sugerencias", "Publicidad", "crear");
		page.cerrarultimapestaña();
		page.elegircaso();
		page1.validarcasocerrado("Sugerencias", "Publicidad", " ", " ");
		
		}
		
		@Test(groups="Fase2")
		public void TS12245_Positive_Feedback_Suggestions_Generic_Interaction_No_Follow_up_Required_Creacion_de_los_Casos_Crear_y_Cancelar_Gestion() {
		CustomerCare page = new CustomerCare(driver);
		CasePage page1 = new CasePage(driver);
		page.elegircuenta("Fernando Care");
		page.SelectGestion("sugerencia");
		page.crearsugerencia("Sugerencias", "Productos/Servicios", "cancel");
		page.cerrarultimapestaña();
		page.elegircaso();
		page1.validarcasocerrado("", "", " ", " ");
		
		
		}
		@Test(groups="Fase2")
		public void TS12302_Positive_Feedback_Suggestions_Generic_Interaction_No_Follow_up_Required_Detalle_de_Atributos_Feedback_Positivo_Generar_Gestion_Subcategoria_Atencion_Ejecutivos() {
		CustomerCare page = new CustomerCare(driver);
		CasePage page1 = new CasePage(driver);
		page.elegircuenta("Fernando Care");
		page.SelectGestion("sugerencia");
		page.crearsugerencia("Sugerencias", "Atención Ejecutivos", "crear");
		page.cerrarultimapestaña();
		page.elegircaso();
		page1.validarcasocerrado("Sugerencias", "Atención Ejecutivos", " ", " ");
		
		}
		
		
		@Test (groups="Fase2")
		public void TS15962_Tax_Condition_Changes_Sesion_Guiada_Para_Cambios_en_Condicion_Impositiva_Boton_de_sesion_guiada() {
			CustomerCare page = new CustomerCare (driver);
			page.elegircuenta("Fernando Care");
			page.ValidarBtnsGestion("Cambios de condi");
			page.cerrarultimapestaña();
			driver.switchTo().defaultContent();
		}
	*/	
		@Test (groups= "Fase2")
		public void TS15966_Tax_Condition_Changes_Sesion_Guiada_Para_Cambios_en_Condicion_Impositiva_Paso_1_Escenario_1() {
			CustomerCare page = new CustomerCare (driver);
			page.elegircuenta("Fernando Care");
			page.SelectGestion("cambios de condi");
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().defaultContent();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			BasePage cambioFrameByID=new BasePage();
		    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")));
		    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.clickContinueError();
		    page.clickContinueError();
		    page.validarCheckBox();
		    page.validarDniACuit();
		    driver.switchTo().defaultContent();
		}
		
		@Test (groups = "Fase2")
		public void TS15976_Tax_Condition_Changes_Sesion_Guiada_Para_Cambios_en_Condicion_Impositiva_Paso_2_Seleccion_DNI_a_CUIT(){
			CustomerCare page = new CustomerCare (driver);
			page.elegircuenta("Fernando Care");
			page.SelectGestion("cambios de condi");
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().defaultContent();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			BasePage cambioFrameByID=new BasePage();
		    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")));
		    try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.clickContinueError();
		    page.clickContinueError();
		    driver.findElement(By.cssSelector(".slds-radio--faux.ng-scope")).click();;
		    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	    
		    page.clickSiguiente(driver.findElement(By.id("Step_2_Select_Tax_Condition_To_Modify_nextBtn")));
		    page.billings(driver.findElements(By.className("slds-checkbox--faux")));;
		    page.clickSiguiente(driver.findElement(By.id("Step_3_Select_Billing_Accounts_nextBtn")));
		    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    driver.switchTo().defaultContent();		    
		}
		
		@Test (groups = "Fase2")
		public void TS15977_Tax_Condition_Changes_Sesion_Guiada_Para_Cambios_en_Condicion_Impositiva_Paso_2_Sin_seleccion_DNI_a_CUIT() {
			CustomerCare page = new CustomerCare (driver);
			page.elegircuenta("Fernando Care");
			page.SelectGestion("cambios de condi");
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().defaultContent();
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			BasePage cambioFrameByID=new BasePage();
		    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")));
		    try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.clickContinueError();
		    page.clickContinueError();
		    driver.findElement(By.cssSelector(".slds-radio--faux.ng-scope")).click();;
		    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.clickSiguiente(driver.findElement(By.id("Step_2_Select_Tax_Condition_To_Modify_nextBtn")));
		    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.clickSiguiente(driver.findElement(By.id("Step_3_Select_Billing_Accounts_nextBtn")));
		    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.validarError();
		    driver.switchTo().defaultContent();    
		}		
		
		@Test (groups = "Fase2")
		public void TS15974_Tax_Condition_ChangesSesion_Guiada_Para_Cambios_en_Condicion_Impositiva_Paso_2_Visualizar_DNI_a_CUIT() {
			CustomerCare page = new CustomerCare (driver);
			page.elegircuenta("Fernando Care");
			page.SelectGestion("cambios de condi");
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().defaultContent();
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			BasePage cambioFrameByID=new BasePage();
		    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")));
		    try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.clickContinueError();
		    page.clickContinueError();
		    driver.findElement(By.cssSelector(".slds-radio--faux.ng-scope")).click();;
		    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.clickSiguiente(driver.findElement(By.id("Step_2_Select_Tax_Condition_To_Modify_nextBtn")));
		    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    WebElement billings = driver.findElement(By.cssSelector(".slds-form-element__label.tax-condition-billing-accounts-name.ng-binding"));
		    boolean a = false;
		    if (billings.isDisplayed()) {
		    	a = true;
		    	assertTrue(a);    	
		    }
		    driver.switchTo().defaultContent();		    
		}
		
		@Test (groups = "Fase2")
		public void TS15993_Tax_Condition_Changes_Sesion_Guiada_Para_Cambios_en_Condicion_Impositiva_Paso_4_Valores_IVA_No_ejecutivo() {
			CustomerCare page = new CustomerCare (driver);
			BasePage pagina = new BasePage(driver);
			page.elegircuenta("Fernando Care");
			page.SelectGestion("cambios de condi");
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().defaultContent();
			try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			BasePage cambioFrameByID=new BasePage();
		    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")));
		    try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.clickContinueError();
		    page.clickContinueError();
		    driver.findElement(By.cssSelector(".slds-radio--faux.ng-scope")).click();;
		    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.clickSiguiente(driver.findElement(By.id("Step_2_Select_Tax_Condition_To_Modify_nextBtn")));
		    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.billings(driver.findElements(By.className("slds-checkbox--faux")));;
		    page.clickSiguiente(driver.findElement(By.id("Step_3_Select_Billing_Accounts_nextBtn")));
		    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.setSimpleDropdown((driver.findElement(By.id("NewCUITType"))), "20");
		    page.setSimpleDropdown((driver.findElement(By.id("NewCheckDigit"))), "1");
		    page.clickSiguiente(driver.findElement(By.id("Step_5_DNI_To_CUIT_nextBtn")));
		    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    driver.findElement(By.id("IVACondition")).click();;
		    try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    Select ivaSelect = new Select (driver.findElement(By.id("IVACondition")));
		    Assert.assertTrue(pagina.getSelectOptions(ivaSelect).contains("IVA Exento / No Alcanzado"));
		    Assert.assertTrue(pagina.getSelectOptions(ivaSelect).contains("IVA Responsable Inscripto"));
		    Assert.assertTrue(pagina.getSelectOptions(ivaSelect).contains("IVA Sujeto No Categorizado"));
		    Assert.assertTrue(pagina.getSelectOptions(ivaSelect).contains("IVA Monotributista"));
		    driver.switchTo().defaultContent();
		    }
		    
		@Test (groups = "Fase2")
		public void TS15999_Tax_Condition_Changes_Sesion_Guiada_Para_Cambios_en_Condicion_Impositiva_Paso_4_Visualizar_Exencion_IVA() {
			CustomerCare page = new CustomerCare (driver);
			page.elegircuenta("Fernando Care");
			page.SelectGestion("cambios de condi");
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().defaultContent();
			try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			BasePage cambioFrameByID=new BasePage();
		    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")));
		    try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.clickContinueError();
		    page.clickContinueError();
		    driver.findElement(By.cssSelector(".slds-radio--faux.ng-scope")).click();;
		    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.clickSiguiente(driver.findElement(By.id("Step_2_Select_Tax_Condition_To_Modify_nextBtn")));
		    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.billings(driver.findElements(By.className("slds-checkbox--faux")));;
		    page.clickSiguiente(driver.findElement(By.id("Step_3_Select_Billing_Accounts_nextBtn")));
		    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.setSimpleDropdown((driver.findElement(By.id("NewCUITType"))), "20");
		    page.setSimpleDropdown((driver.findElement(By.id("NewCheckDigit"))), "1");
		    page.clickSiguiente(driver.findElement(By.id("Step_5_DNI_To_CUIT_nextBtn")));
		    page.ElementPresent(driver.findElement(By.cssSelector(".taxConditionChanges")));
		    driver.switchTo().defaultContent();
		}
				
		@Test (groups = "Fase2")
		public void TS16015_Tax_Condition_Changes_Sesion_Guiada_Para_Cambios_en_Condicion_Impositiva_Paso_4_Visualizar_Percepcion_IIBB() {
		    CustomerCare page = new CustomerCare (driver);
			page.elegircuenta("Fernando Care");
			page.SelectGestion("cambios de condi");
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().defaultContent();
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			BasePage cambioFrameByID=new BasePage();
			driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")));
		    try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			page.clickContinueError();
			page.clickContinueError();
			driver.findElement(By.cssSelector(".slds-radio--faux.ng-scope")).click();;
		    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.clickSiguiente(driver.findElement(By.id("Step_2_Select_Tax_Condition_To_Modify_nextBtn")));
		    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.billings(driver.findElements(By.className("slds-checkbox--faux")));;
		    page.clickSiguiente(driver.findElement(By.id("Step_3_Select_Billing_Accounts_nextBtn")));
		    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.setSimpleDropdown((driver.findElement(By.id("NewCUITType"))), "20");
		    page.setSimpleDropdown((driver.findElement(By.id("NewCheckDigit"))), "1");
		    page.clickSiguiente(driver.findElement(By.id("Step_5_DNI_To_CUIT_nextBtn")));
		    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.ElementPresent(driver.findElement(By.id("IIBBCondition")));
		    driver.switchTo().defaultContent();
		}
		
		@Test (groups = "Fase2")
		public void TS16017_Tax_Condition_Changes_Sesion_Guiada_Para_Cambios_en_Condicion_Impositiva_Paso_4_Visualizar_Picklist_Jurisdicciones_Percepcion_IIBB() {
			CustomerCare page = new CustomerCare (driver);
			page.elegircuenta("Fernando Care");
			page.SelectGestion("cambios de condi");
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().defaultContent();
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			BasePage cambioFrameByID=new BasePage();
			driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")));
		    try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			page.clickContinueError();
			page.clickContinueError();
			driver.findElement(By.cssSelector(".slds-radio--faux.ng-scope")).click();;
		    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.clickSiguiente(driver.findElement(By.id("Step_2_Select_Tax_Condition_To_Modify_nextBtn")));
		    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.billings(driver.findElements(By.className("slds-checkbox--faux")));;
		    page.clickSiguiente(driver.findElement(By.id("Step_3_Select_Billing_Accounts_nextBtn")));
		    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.setSimpleDropdown((driver.findElement(By.id("NewCUITType"))), "20");
		    page.setSimpleDropdown((driver.findElement(By.id("NewCheckDigit"))), "1");
		    page.clickSiguiente(driver.findElement(By.id("Step_5_DNI_To_CUIT_nextBtn")));
		    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.ElementPresent(driver.findElement(By.id("IIBBExemptionJurisdiction")));
		    driver.switchTo().defaultContent();
		}
		
		@Test (groups = "Fase2")
		public void TS15965_Tax_Condition_Changes_Sesion_Guiada_Para_Cambios_en_Condicion_Impositiva_Validaciones_negativas() {
			CustomerCare page = new CustomerCare (driver);
			page.elegircuenta("Fernando Care");
			page.SelectGestion("cambios de condi");
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().defaultContent();
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			BasePage cambioFrameByID=new BasePage();
			driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")));
		    try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			page.clickContinueError();
			page.clickContinueError();
			driver.findElement(By.cssSelector(".slds-radio--faux.ng-scope")).click();;
		    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.clickSiguiente(driver.findElement(By.id("Step_2_Select_Tax_Condition_To_Modify_nextBtn")));
		    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.clickSiguiente(driver.findElement(By.id("Step_3_Select_Billing_Accounts_nextBtn")));;
		    page.validarError();
		    driver.switchTo().defaultContent();		    
		}
		
		@Test (groups = "Fase2")
		public void TS16052_Tax_Condition_Changes_Sesion_Guiada_Para_Cambios_en_Condicion_Impositiva_Paso_6_Confirmacion() {
			CustomerCare page = new CustomerCare (driver);
			page.elegircuenta("Fernando Care");
			page.SelectGestion("cambios de condi");
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().defaultContent();
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			BasePage cambioFrameByID=new BasePage();
			driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")));
		    try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			page.clickContinueError();
			page.clickContinueError();
			driver.findElement(By.cssSelector(".slds-radio--faux.ng-scope")).click();;
		    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.clickSiguiente(driver.findElement(By.id("Step_2_Select_Tax_Condition_To_Modify_nextBtn")));
		    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.billings(driver.findElements(By.className("slds-checkbox--faux")));;
		    page.clickSiguiente(driver.findElement(By.id("Step_3_Select_Billing_Accounts_nextBtn")));
		    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.setSimpleDropdown((driver.findElement(By.id("NewCUITType"))), "20");
		    page.setSimpleDropdown((driver.findElement(By.id("NewCheckDigit"))), "1");
		    page.clickSiguiente(driver.findElement(By.id("Step_5_DNI_To_CUIT_nextBtn")));
		    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.setSimpleDropdown(driver.findElement(By.id("IVACondition")), "IVA Monotributista");		    
		    driver.findElement(By.id("IVAExemptionDateTo")).sendKeys("24/10/2017");;
		    driver.findElement(By.id("IVAExemptionPercentage")).sendKeys("10");;
		    page.setSimpleDropdown(driver.findElement(By.id("IIBBCondition")), "IIBB Inscripto Local");
		    page.setSimpleDropdown(driver.findElement(By.id("IIBBExemptionJurisdiction")), "CABA");
		    driver.findElement(By.id("IIBBExemptionDateTo")).sendKeys("25/10/2017");;
		    driver.findElement(By.id("IIBBExemptionPercentage")).sendKeys("10");;		    
			try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.findElement(By.id("RespaldatoryDocumentationFile")).sendKeys("C:\\descarga.jpg");
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.clickSiguiente(driver.findElement(By.id("Step_6_Select_New_Tax_Condition_DNI_To_CUIT_nextBtn")));
		    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.clickSiguiente(driver.findElement(By.id("Step_8_Summary_nextBtn")));
		    try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("ng-binding")));
		    List <WebElement> nombre = driver.findElements(By.className("ng-binding"));
		    Assert.assertTrue(nombre.get(0).getText().contains("Los datos se actualizaron correctamente. Su número de gestión es:"));
		    driver.switchTo().defaultContent();
		}
		
		@Test (groups = "Fase2")
		public void TS16054_Tax_Condition_Changes_Sesion_Guiada_Para_Cambios_en_Condicion_Impositiva_Paso_6_Caso_creado() {
			CustomerCare page = new CustomerCare (driver);
			CasePage pagina = new CasePage(driver);
			page.elegircuenta("Fernando Care");
			page.SelectGestion("cambios de condi");
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().defaultContent();
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			BasePage cambioFrameByID=new BasePage();
			driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")));
		    try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			page.clickContinueError();
			page.clickContinueError();
			driver.findElement(By.cssSelector(".slds-radio--faux.ng-scope")).click();;
		    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.clickSiguiente(driver.findElement(By.id("Step_2_Select_Tax_Condition_To_Modify_nextBtn")));
		    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.billings(driver.findElements(By.className("slds-checkbox--faux")));;
		    page.clickSiguiente(driver.findElement(By.id("Step_3_Select_Billing_Accounts_nextBtn")));
		    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.setSimpleDropdown((driver.findElement(By.id("NewCUITType"))), "20");
		    page.setSimpleDropdown((driver.findElement(By.id("NewCheckDigit"))), "1");
		    page.clickSiguiente(driver.findElement(By.id("Step_5_DNI_To_CUIT_nextBtn")));
		    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.setSimpleDropdown(driver.findElement(By.id("IVACondition")), "IVA Monotributista");		    
		    driver.findElement(By.id("IVAExemptionDateTo")).sendKeys("24/10/2017");;
		    driver.findElement(By.id("IVAExemptionPercentage")).sendKeys("10");;
		    page.setSimpleDropdown(driver.findElement(By.id("IIBBCondition")), "IIBB Inscripto Local");
		    page.setSimpleDropdown(driver.findElement(By.id("IIBBExemptionJurisdiction")), "CABA");
		    driver.findElement(By.id("IIBBExemptionDateTo")).sendKeys("25/10/2017");;
		    driver.findElement(By.id("IIBBExemptionPercentage")).sendKeys("10");;		    
			try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.findElement(By.id("RespaldatoryDocumentationFile")).sendKeys("C:\\descarga.jpg");
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.clickSiguiente(driver.findElement(By.id("Step_6_Select_New_Tax_Condition_DNI_To_CUIT_nextBtn")));
		    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.clickSiguiente(driver.findElement(By.id("Step_8_Summary_nextBtn")));
		    try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("ng-binding")));
		    List <WebElement> nombre = driver.findElements(By.className("ng-binding"));
		    Assert.assertTrue(nombre.get(0).getText().contains("Los datos se actualizaron correctamente."));
		    page.cerrarultimapestaña();
		    page.elegircaso();
		    pagina.validarcasocerrado(" ", " ", "Cambio de condición impositiva", "nico");
		    driver.switchTo().defaultContent();
		}
		
		@Test (groups = "Fase2")
		public void TS15905_Consumption_Details_Definicion_de_Filtros_Filtro_Lista_de_Servicios_Servicio_que_lo_tiene_el_cliente(){
			CustomerCare page = new CustomerCare(driver);
			page.elegircuenta("Fernando Care");
			page.SelectGestion("detalle de consumo");
			BasePage cambioFrameByID=new BasePage();
			try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("text-input-01")));
			try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.findElement(By.id("text-input-01")).click();
		    try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    List<WebElement> servicios = driver.findElements(By.cssSelector(".slds-dropdown.slds-dropdown--left")).get(0).findElements(By.cssSelector(".slds-lookup__item-action.slds-lookup__item-action--label"));
		    //for (WebElement x : servicios) {
		    //	System.out.println(x.getText());
		    //}
			Assert.assertTrue(servicios.size() > 0);
			driver.switchTo().defaultContent();			
		}
		
		@Test (groups = "Fase2")
		public void TS15906_Consumption_Details_Definicion_de_Filtros_Filtro_Lista_de_Servicios_Servicio_que_no_tiene_el_cliente() {
			CustomerCare page = new CustomerCare(driver);
			page.elegircuenta("Fernando Care");
			page.SelectGestion("detalle de consumo");
			BasePage cambioFrameByID=new BasePage();
			try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("text-input-01")));
			try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.findElement(By.id("text-input-01")).click();
		    try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    List<WebElement> servicios = driver.findElements(By.cssSelector(".slds-dropdown.slds-dropdown--left")).get(0).findElements(By.cssSelector(".slds-lookup__item-action.slds-lookup__item-action--label"));
			Assert.assertTrue(servicios.size() != 0);
			driver.switchTo().defaultContent();			
		}
		
		@Test (groups = "Fase2")
		public void TS15964_Tax_Condition_Changes_Sesion_Guiada_Para_Cambios_en_Condicion_Impositiva_Guardar_para_Despues_Sesion_Guiada() {
			CustomerCare page = new CustomerCare (driver);
			page.elegircuenta("Fernando Care");
			page.SelectGestion("cambios de condi");
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().defaultContent();
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			BasePage cambioFrameByID=new BasePage();
			driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")));
		    try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			page.clickContinueError();
			page.clickContinueError();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("vlc-slds--tertiary-container")));
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			List <WebElement> guardar = driver.findElements(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope"));	
			guardar.get(1).click();
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.findElement(By.id("alert-ok-button")).click();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-grid.slds-box.vlc-slds-save_for_later")));
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			page.ElementPresent(driver.findElement(By.cssSelector(".slds-grid.slds-box.vlc-slds-save_for_later")));
			driver.switchTo().defaultContent();			
		}
		
		@Test (groups = "Fase2")
		public void TS15954_360_View_Ver_Equipo_Creador_en_Case_Visualizar_campo_Equipo_del_Creador() {
			CustomerCare page = new CustomerCare (driver);
			page.elegircuenta("Fernando Care");
			page.elegircaso();
			page.ElementPresent(driver.findElement(By.cssSelector(".x-grid3-hd-inner.x-grid3-hd-00Nc0000001iLah")));
			driver.switchTo().defaultContent();
		}
		
		@Test (groups = "Fase2")
		public void TS16069_Billing_Cycle_Changes_Sesion_Guiada_para_Cambios_de_Inicio_de_Ciclo_de_Facturacion_Paso_1_Seleccion_Billing_accounts() {
			CustomerCare page = new CustomerCare (driver);
			page.elegircuenta("Fernando Care");
			page.SelectGestion("cambio de ciclo");
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().defaultContent();
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			BasePage cambioFrameByID=new BasePage();
			driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")));
		    try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			page.clickContinueError();
			page.clickContinueError();
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().defaultContent();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("slds-checkbox--faux")));
		    try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.billings(driver.findElements(By.className("slds-checkbox--faux")));;
			Assert.assertTrue(page.ElementPresent(driver.findElement(By.id("BillingCycle_nextBtn"))));
			driver.switchTo().defaultContent();			
		}
		
		@Test (groups = "Fase2")
		public void TS16062_Billing_Cycle_Changes_Sesion_Guiada_para_Cambios_de_Inicio_de_Ciclo_de_Facturacion_Paso_1_Visualizar_Billing_accounts() {
			CustomerCare page = new CustomerCare (driver);
			page.elegircuenta("Fernando Care");
			page.SelectGestion("cambio de ciclo");
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().defaultContent();
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			BasePage cambioFrameByID=new BasePage();
			driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")));
		    try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			page.clickContinueError();
			page.clickContinueError();
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			List <WebElement> cuenta = driver.findElements(By.id("tree0-node1__label"));
			Assert.assertTrue(cuenta.get(0).isDisplayed());
			Assert.assertTrue(cuenta.get(2).isDisplayed());
			driver.switchTo().defaultContent();			
		}
		
		@Test (groups = "Fase2")
		public void TS16077_Billing_Cycle_Changes_Sesion_Guiada_para_Cambios_de_Inicio_de_Ciclo_de_Facturacion_Paso_2_Valores_Picklist_Ciclo_de_facturacion() {
			CustomerCare page = new CustomerCare (driver);
			BasePage pagina = new BasePage(driver);
			page.elegircuenta("Fernando Care");
			page.SelectGestion("cambio de ciclo");
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().defaultContent();
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			BasePage cambioFrameByID=new BasePage();
			driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")));
		    try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			page.clickContinueError();
			page.clickContinueError();
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().defaultContent();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("slds-checkbox--faux")));
		    try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.billings(driver.findElements(By.className("slds-checkbox--faux")));;
			driver.findElement(By.id("BillingCycle_nextBtn")).click();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.findElement(By.id("BillingCycleSelect")).click();
			Select dias = new Select (driver.findElement(By.id("BillingCycleSelect")));
			Assert.assertTrue(pagina.getSelectOptions(dias).contains("1"));
			Assert.assertTrue(pagina.getSelectOptions(dias).contains("7"));
			Assert.assertTrue(pagina.getSelectOptions(dias).contains("14"));
			Assert.assertTrue(pagina.getSelectOptions(dias).contains("21"));
			driver.switchTo().defaultContent();		
		}
		
		// No se puede hacer @Test (groups = "Fase2")
		public void TS16081_Billing_Cycle_Changes_Sesion_Guiada_para_Cambios_de_Inicio_de_Ciclo_de_Facturacion_Paso_3_Visualizar_Datos_Nuevos_Resumen() {		
			CustomerCare page = new CustomerCare(driver);
		    CasePage Cp = new CasePage(driver);
		    BasePage Bp = new BasePage();
		    page.elegircuenta("Fernando Care");
		    page.SelectGestion("ciclo");
		    page.clickContinueError();
		    page.clickContinueError();
		    driver.switchTo().defaultContent();
		    driver.switchTo().frame(Bp.getFrameForElement(driver, By.id("BillingCycle_nextBtn")));
		    page.billings(driver.findElements(By.className("slds-checkbox--faux")));;
		    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    String parcial = driver.findElement(By.id("tree0-node1")).findElements(By.cssSelector(".slds-form-element__label.slds-truncate.ng-binding")).get(1).getText();
		    parcial = parcial.substring(parcial.length()-2, parcial.length());
		    if (parcial.contains(" ")) {
		    	parcial.substring(1);
		    }
		    System.out.println("parcial"+parcial);
		    page.ciclodefacturacionpaso2();
		    if (parcial.equals("1")) {
		    	Bp.setSimpleDropdown(driver.findElement(By.id("BillingCycleSelect")), "7");
		    }else {Bp.setSimpleDropdown(driver.findElement(By.id("BillingCycleSelect")), "1");}
		    try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("BillingCycle_nextBtn")).getLocation().y+")");
		    driver.findElement(By.id("BillingCycle_nextBtn")).click();
		    
		}
} 

