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
public class CustomerCare360ViewInformationClient extends TestBase {



		
	private WebDriver driver;
	 	
//	@AfterClass
	public void tearDown() {
			driver.close();
	}
	
	@AfterMethod(groups= "Fase2")
	public void alert (){
		CustomerCare page = new CustomerCare(driver);
		page.cerrarultimapestaña();
	

		try{
			Alert alert = driver.switchTo().alert();
			System.out.println(alert.getText());
		}catch(org.openqa.selenium.NoAlertPresentException e){}
	}

	@BeforeClass(groups= "Fase2")
	public void init() throws Exception
	{
		this.driver = setConexion.setupEze();
		login(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		String a = driver.findElement(By.id("tsidLabel")).getText();
		driver.findElement(By.id("tsidLabel")).click();
		if(a.equals("Ventas"))
		{
			driver.findElement(By.xpath("//a[@href=\'/console?tsid=02uc0000000D6Hd\']")).click();
		}else
		{			driver.findElement(By.xpath("//a[@href=\'/home/home.jsp?tsid=02u41000000QWha\']")).click();
			try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.findElement(By.id("tsidLabel")).click();
			driver.findElement(By.xpath("//a[@href=\'/console?tsid=02uc0000000D6Hd\']")).click();
		}
	}	
	@BeforeMethod
	public void setup(){
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		CustomerCare page = new CustomerCare(driver);
		page.cerrarultimapestaña();
	}
	
	@Test (groups = "Fase1")
	 public void TS7137_BusinessDataPanelQuickAccessButtonsAccount() {
	  CustomerCare page = new CustomerCare(driver);
	  goToLeftPanel(driver, "Cuentas");
	  page.elegircuenta("Andres Care");
	  page.openleftpanel();  
	  try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	  BasePage cambioFrameByID=new BasePage();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("profile-box-details")));
	  driver.findElements(By.className("profile-box-details"));
	  page.validarDatos();
	  
	 }
	 
	 
	  
	 @Test (groups = "Fase1")
	 public void TS7138_BusinessDataPanelPicklistCommercialDataAccount() {
	  CustomerCare page = new CustomerCare(driver);
	  goToLeftPanel(driver, "Cuentas");
	  page.elegircuenta("Andres Care");
	  page.openleftpanel();
	  try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	  BasePage cambioFrameByID=new BasePage();
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("profile-box-details")));
	  driver.findElement(By.className("account-select"));
	  
	 }
		
		
	@Test(groups= "Fase2")
	public void TS16056_Sesion_Guiada_para_Cambios_de_Inicio_de_Ciclo_de_Facturacion_Funcionamiento_Boton_Sesion_Guiada() {
		CustomerCare page = new CustomerCare(driver);
		page.elegircuenta("Andres Care");
		page.openrightpanel();
		page.SelectGestion("Cambio de ciclo");
		try {Thread.sleep(30000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page.clickContinueError();
		page.clickContinueError();
		page.ValidarCambioDeCiclo();
		
	}
		
		@Test(groups= "Fase2")
	public void TS16055_Sesion_Guiada_para_Cambios_de_Inicio_de_Ciclo_de_Facturacion__Boton_Sesion_Guiada() {
			CustomerCare page = new CustomerCare(driver);
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
		page.editarcuenta("Fernando Care Billing 1", "no", "active");
	    page.editarcuenta("Fernando Care Billing 2", "no", "active");
		page.elegircuenta("Fernando Care");
		page.SelectGestion("Movimientos de cuenta de facturaci");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();};
		page.serviciocambiadecuenta("Arnet 10 MB (Prueba)", "Fernando Care Billing 2");
		page.SelectGestion("Movimientos de cuenta de facturacion");
		page.validarerrorpaso1("servicio cambia de cuenta billing");

		
		}
		
		@Test(groups="Fase2")
		public void TS12244_Positive_Feedback_Suggestions_Generic_Interaction_No_Follow_up_Required_Creacion_de_los_Casos_Crear_Caso() {
		CustomerCare page = new CustomerCare(driver);
		CasePage page1 = new CasePage(driver);
		page.elegircuenta("Fernando Care");
		page.SelectGestion("sugerencia");
		page.crearsugerencia("Sugerencias", "Publicidad", "crear");
		page.cerrarultimapestaña();
		page.elegircaso("Todos los casos");
		page1.validarcasocerrado("Sugerencias", "Publicidad", "", "leo");
		
		}
		
		@Test(groups="Fase2")
		public void TS12245_Positive_Feedback_Suggestions_Generic_Interaction_No_Follow_up_Required_Creacion_de_los_Casos_Crear_y_Cancelar_Gestion() {
		CustomerCare page = new CustomerCare(driver);
		CasePage page1 = new CasePage(driver);
		page.elegircuenta("Fernando Care");
		page.SelectGestion("sugerencia");
		page.crearsugerencia("Sugerencias", "Productos/Servicios", "cancel");
		page.cerrarultimapestaña();
		page.elegircaso("Todos los casos");
		page1.validarcasocerrado("", "", "", "leo");
		
		}
		@Test(groups="Fase2")
		public void TS12302_Positive_Feedback_Suggestions_Generic_Interaction_No_Follow_up_Required_Detalle_de_Atributos_Feedback_Positivo_Generar_Gestion_Subcategoria_Atencion_Ejecutivos() {
		CustomerCare page = new CustomerCare(driver);
		CasePage page1 = new CasePage(driver);
		page.elegircuenta("Fernando Care");
		page.SelectGestion("sugerencia");
		page.crearsugerencia("Sugerencias","Atención Ejecutivos","crear");
		page.cerrarultimapestaña();
		page.elegircaso("Todos los casos");
		page1.validarcasocerrado("Sugerencias", "Atención Ejecutivos", "", "leo");
		
		}
		
		@Test(groups="Fase2")
		public void TS14571_Case_Management_Sesion_Guiada_Ver_Casos_User_Asignado_Visualizar_Pantalla_Mis_Casos_Abiertos() {
			CustomerCare page = new CustomerCare(driver);
			CasePage page1 = new CasePage(driver);
			page.elegircaso("Mis Casos Abiertos");
			page1.validarmiscasosabiertos();
			
		}

		@Test(groups="Fase2")
		public void TS14566_360_View_Capacidades_de_Busqueda_Filtrar_Por_Empresa() {
			CustomerCare page = new CustomerCare(driver);
			page.usarbuscadorsalesforce("Empresa 2X Care");
			page.validarlabusqueda("Empresa 2X Care");
			
		}
		
		@Test(groups="Fase2")
		public void TS14568_360_View_Capacidades_de_Busqueda_Filtrar_Por_número_de_linea() {
			CustomerCare page = new CustomerCare(driver);
			page.usarbuscadorsalesforce("1145325760");
			page.validarlabusqueda("1145325760");
			
		}
		
		@Test(groups="Fase2")
		public void TS14622_360_View_Estado_Servicio_Visualizar_Botones_de_Acción_Subproductos() {
		CustomerCare page = new CustomerCare(driver);
		page.elegircuenta("Fernando Care");
		page.usarpanelcentral("Servicios");
		page.validarsubproductos();
		}
		
		
		@Test(groups="Fase2")
		public void TS14593_360_View_Panel_de_los_Servicios_de_Cliente_Visualizar_borde_de_estado() {
		CustomerCare page = new CustomerCare(driver);
		page.elegircuenta("Fernando Care");
		page.usarpanelcentral("Servicios");
		page.validarestado();
		}
		
		@Test(groups="Fase2")
		public void TS14603_360_View_Basado_en_Tipo_de_Cliente_Vista_CUIT_20() {
		CustomerCare page = new CustomerCare(driver);
		page.elegircuenta("Empresa 2X Care");
		page.usarpanelcentral("Servicios");
		page.validarcard();
		}
		
		@Test(groups="Fase2")
		public void TS14604_360_View_Basado_en_Tipo_de_Cliente_Vista_CUIT_30() {
		CustomerCare page = new CustomerCare(driver);
		page.elegircuenta("Empresa 3X Care");
		page.usarpanelcentral("Servicios");
		page.validarcard();
		}
		
		@Test(groups="Fase2")
		public void TS15953_Billing_Cycle_Changes_Rastreo_de_los_Cambios_del_Inicio_del_Ciclo_de_Facturacion_Visualizar_Datos_Anteriores_Ciclo_Facturacion() {
			CustomerCare page = new CustomerCare(driver);
			page.elegircuenta("Fernando Care");
			page.usarpanelcentral("Detalles");
			page.validarhistorialdecuentas();
		}
		@Test(groups="Fase2")
		public void TS16061_Billing_Cycle_Changes_Sesion_Guiada_para_Cambios_de_Inicio_de_Ciclo_de_Facturacion_Paso_0_Caso_Cliente_activo() {
			CustomerCare page = new CustomerCare(driver);
			page.editarcuenta("Fernando Care","no", "inactive");
			page.elegircuenta("Fernando Care");
			page.SelectGestion("ciclo");
			page.clickContinueError();
			page.clickContinueError();
			page.validarerrorpaso0();
		}
		
		@Test(groups="Fase2")//*****************************
		public void TS15959_Billing_Cycle_Changes_Sesion_Guiada_para_Cambios_de_Inicio_de_Ciclo_de_Facturacion_Paso_0_Caso_fraude() {
			//bug
			CustomerCare page = new CustomerCare(driver);
			page.editarcuenta("Fernando Care","si", "active");
			page.elegircuenta("Fernando Care");
			page.SelectGestion("ciclo");
			page.clickContinueError();
			page.clickContinueError();
			page.validarerrorpaso0();
			CasePage page1 = new CasePage(driver);
			page1.validarcasocerrado("categoria", "subcategoria"," ", "leo");

			
		}
		
		@Test(groups="Fase2")
		public void TS16060_Billing_Cycle_Changes_Sesion_Guiada_para_Cambios_de_Inicio_de_Ciclo_de_Facturacion_Paso_0_Validaciones_Cliente_activo() {
			CustomerCare page = new CustomerCare(driver);
			page.editarcuenta("Fernando Care","si", "active");
			page.elegircuenta("Fernando Care");
			page.SelectGestion("ciclo");
			page.clickContinueError();
			page.clickContinueError();
			page.validarerrorpaso0();
		}
		@Test(groups="Fase2")
		public void TS16057_Billing_Cycle_Changes_Sesion_Guiada_para_Cambios_de_Inicio_de_Ciclo_de_Facturacion_Paso_0_Validaciones_correctas() {
			CustomerCare page = new CustomerCare(driver);
			page.editarcuenta("Fernando Care","no", "active");
			page.elegircuenta("Fernando Care");
			page.SelectGestion("ciclo");
			page.clickContinueError();
			page.clickContinueError();
			page.validarcorrectopaso0();
		}
		@Test(groups="Fase2")
		public void TS16065_Billing_Cycle_Changes_Sesion_Guiada_para_Cambios_de_Inicio_de_Ciclo_de_Facturacion_Paso_1_Ciclo_Billing_accounts() {
			CustomerCare page = new CustomerCare(driver);
			page.editarcuenta("Fernando Care","no", "active");
			page.elegircuenta("Fernando Care");
			page.SelectGestion("ciclo");
			page.clickContinueError();
			page.clickContinueError();
			page.validarpaso1cambiodeciclo();
		}
		@Test(groups="Fase2")
		public void TS16064_Billing_Cycle_Changes_Sesion_Guiada_para_Cambios_de_Inicio_de_Ciclo_de_Facturacion_Paso_1_Funcionamiento_Boton_Servicios_Billing_accounts() {
			CustomerCare page = new CustomerCare(driver);
				page.editarcuenta("Fernando Care","no", "active");
				page.elegircuenta("Fernando Care");
				page.SelectGestion("ciclo");
				page.clickContinueError();
				page.clickContinueError();
				page.validarcambiodecicloservicios();
		}
		@Test(groups="Fase2")
		public void TS16078_Billing_Cycle_Changes_Sesion_Guiada_para_Cambios_de_Inicio_de_Ciclo_de_Facturacion_Paso_2_Mandatorio_Picklist_Ciclo_de_facturacion() {
			CustomerCare page = new CustomerCare(driver);
			page.editarcuenta("Fernando Care","no", "active");
			page.elegircuenta("Fernando Care");
			page.SelectGestion("ciclo");
			page.clickContinueError();
			page.clickContinueError();
			page.ciclodefacturacionpaso2();
			page.validarpicklistmandatorio();
			
		}

		@Test(groups="Fase1")
	    public void TS7152_Profile_Changes_Cambios_En_La_Informacion_Del_Cliente_Validar_Nombre_Y_Apellido_Primer_Letra_Mayúscula() {
	      CustomerCare page = new CustomerCare(driver);
	      BasePage Bp = new BasePage();
	      //goToLeftPanel(driver, "Cuentas");
	      
	      page.elegircuenta("Andres Care");
	      page.openleftpanel();
	      driver.switchTo().defaultContent();
	      driver.switchTo().frame(Bp.getFrameForElement(driver, By.className("profile-box")));
	      String NC = driver.findElement(By.className("profile-box")).findElement(By.cssSelector(".slds-text-heading_large.title-card")).getText();
	      String[] ND = NC.split(" ");
	      String mayu = new String();
	      for (String uno : ND) {
	        mayu= uno.toUpperCase();
	        assertTrue(uno.charAt(0) == mayu.charAt(0));
	      }
	      
	    }
	    
	    @Test(groups="Fase2")
	    public void TS12286_Reseteo_de_Claves_Manejo_De_La_Clave_Gestion_Caso_Reseteo_Clave() {
	      CustomerCare page = new CustomerCare(driver);
	      CasePage Cp = new CasePage(driver);
	      BasePage Bp = new BasePage();
	      //goToLeftPanel(driver, "Cuentas");
	      page.elegircuenta("Andres Care");
	      page.openleftpanel();
	      driver.switchTo().defaultContent();
	      driver.switchTo().frame(Bp.getFrameForElement(driver, By.className("profile-box")));
	      driver.findElement(By.className("profile-box-details")).findElement(By.className("profile-links-wrapper")).findElements(By.tagName("a")).get(1).click();
	      driver.switchTo().defaultContent();
	      try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	      driver.switchTo().frame(Bp.getFrameForElement(driver, By.id("Step 1_nextBtn")));
	      driver.findElement(By.cssSelector(".slds-radio.ng-scope")).click();
	      try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	      ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("Step 1_nextBtn")).getLocation().y+")");
	      driver.findElement(By.id("Step 1_nextBtn")).click();
	      try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	      String todo = driver.findElement(By.className("panel-body")).findElement(By.className("ng-binding")).getText();
	      todo = todo.substring(todo.length()-9, todo.length()-1);
	      page.elegircaso("Todos los casos");
	      Cp.validarcasocerrado(" ", " ", "Ciente solicita resetear su clave de acceso", "nico");
	    }
	    
	    @Test(groups="Fase2")
	    public void TS16080_Billing_Cycle_Changes_Sesion_Guiada_para_Cambios_Inicio_Ciclo_Facturacion_Paso3_Visualizar_Datos_Antiguos_Resumen() {
	      CustomerCare page = new CustomerCare(driver);
	      CasePage Cp = new CasePage(driver);
	      BasePage Bp = new BasePage();
	      page.elegircuenta("Fernando Care");
	         page.SelectGestion("ciclo");
	         page.clickContinueError();
	         page.clickContinueError();
	       driver.switchTo().defaultContent();
	       driver.switchTo().frame(Bp.getFrameForElement(driver, By.id("BillingCycle_nextBtn")));
	       String parcial = driver.findElement(By.id("tree0-node1")).findElements(By.cssSelector(".slds-form-element__label.slds-truncate.ng-binding")).get(1).getText();
	       parcial = parcial.substring(parcial.length()-2, parcial.length());
	       if (parcial.contains(" ")) {
	         parcial.substring(1);
	       }
	       System.out.println("parcial"+parcial);
	       page.ciclodefacturacionpaso2();
	       try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	       if (parcial.equals("1")) {
	         Bp.setSimpleDropdown(driver.findElement(By.id("BillingCycleSelect")), "7");
	       }else {Bp.setSimpleDropdown(driver.findElement(By.id("BillingCycleSelect")), "1");}
	       try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("NewBillingCycle_nextBtn")).getLocation().y+")");
	 driver.findElement(By.id("NewBillingCycle_nextBtn")).click();
	        try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	        String actual = driver.findElement(By.id("SelectableItems2")).findElements(By.cssSelector("slds-text-align--left.slds-m-around--x-small.ng-binding.ng-scope")).get(1).getText();
	        actual = actual.substring(actual.length()-2, actual.length());
	         if (actual.contains(" ")) {
	           actual.substring(1);
	         }
	         System.out.println("actual"+actual);
	         assertTrue(actual.equals(parcial));
	         //error, se deben esperar 2 dias para relaizar la prueba
	    }
	 
	    @Test (groups="Fase2")
		public void TS15962_Tax_Condition_Changes_Sesion_Guiada_Para_Cambios_en_Condicion_Impositiva_Boton_de_sesion_guiada() {
			CustomerCare page = new CustomerCare (driver);
			page.elegircuenta("Fernando Care");
			page.ValidarBtnsGestion("Cambios de condi");
			page.cerrarultimapestaña();
			driver.switchTo().defaultContent();
		}
		
		@Test (groups= "Fase2")
		public void TS15966_Tax_Condition_Changes_Sesion_Guiada_Para_Cambios_en_Condicion_Impositiva_Paso_1_Escenario_1() {
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
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
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
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("slds-checkbox--faux")));
		    try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    page.billings(driver.findElements(By.className("slds-checkbox--faux")));;
			driver.findElement(By.id("BillingCycle_nextBtn")).click();
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.findElement(By.id("BillingCycleSelect")).click();
			Select dias = new Select (driver.findElement(By.id("BillingCycleSelect")));
			Assert.assertTrue(pagina.getSelectOptions(dias).contains("1"));
			Assert.assertTrue(pagina.getSelectOptions(dias).contains("7"));
			Assert.assertTrue(pagina.getSelectOptions(dias).contains("14"));
			Assert.assertTrue(pagina.getSelectOptions(dias).contains("21"));
			driver.switchTo().defaultContent();
		}
		
		//Falta terminar @Test (groups = "Fase2")
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
		
		@Test
		public void TS37423_360_View_Buscador_para_vistas_de_facturación_Buscar_por_Número_de_cuenta() {
			CustomerCare page = new CustomerCare(driver);
			  page.elegircuenta("Fernando Care");
			  page.SelectGestion("factu");
			  try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			  BasePage Bp = new BasePage();
		
			  driver.switchTo().frame(Bp.getFrameForElement(driver, By.cssSelector(".form-control.services-input-control.ng-pristine.ng-untouched.ng-valid.ng-empty")));
			  WebElement buscar = driver.findElement(By.cssSelector(".form-control.services-input-control.ng-pristine.ng-untouched.ng-valid.ng-empty"));
			  try {buscar.click();} catch ( org.openqa.selenium.WebDriverException e ) {}
			  //buscar.click();
			  //form-control services-input-control ng-pristine ng-valid ng-empty ng-touched
			  buscar.sendKeys("000");
			  
			 }
}
