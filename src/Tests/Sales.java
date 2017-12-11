package Tests;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.sql.Driver;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.ContactSearch;
import Pages.CustomerCare;
import Pages.Order;
import Pages.OrdersTab;
import Pages.SalesBase;
import Pages.setConexion;


public class Sales extends TestBase {
	
	protected String perfil = "agente";
	protected WebDriver driver;
	protected  WebDriverWait wait;
	String nombre="Roberto";
	String apellido="Carlos";
	String nacimiento="15/07/1995";
	String NDNI="65987659";
	String DNI = "DNI";
	String plan="Plan con tarjeta";
	String telefono="1565987464";
	String impositiva="IVA Consumidor Final";
	String provincia="Buenos Aires" ;
	String localidad="SAN ISIDRO";
	String calle="Santa Fe";
	String local="no"; 
	String altura="123"; 
	String piso="PB";
	String dpto="B";
	String CP= "1609";
	String Email = "RobertoCarlos@gmail.com";
	String DateOfBirthdayWrong = "06/07/1890";
	String[] genero = {"masculino","femenino"};
	String[] DocValue = {"52698547","3569874563","365","ssss"};
	
	//@AfterClass
	public void tearDown() {
		driver.close();
	}
	
//	@AfterMethod(groups="Sales")
	public void deslogin(){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.get("https://crm--sit.cs14.my.salesforce.com/home/home.jsp?tsid=02u41000000QWha/");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		SalesBase SB = new SalesBase(driver);
		SB.borrarcontacto(apellido,nombre);
		SB.borrarcuenta(nombre, apellido);

	}
	
	@BeforeClass(groups="Sales")
	public void Init() throws Exception
	{
		this.driver = setConexion.setupEze();
		 wait = new WebDriverWait(driver, 10);
		//try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		 switch(perfil){
		 case "dani":
			login(driver);
			break;
		 case "agente":
			 loginAndres(driver);
			 break;
		 case "call":
			 loginElena(driver);
			 break;
		 case "venta":
			 loginFranciso(driver);
			 break;
		 case "logistica":
			 loginNicolas(driver);
			 break;
		 case "entregas":
			 loginMarcela(driver);
			 break;
		 case "fabiana":
			 loginFabiana(driver);
			 break;
		 }
	
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}

	@BeforeMethod(groups="Sales")
	public void setup() throws Exception {		
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.xpath("//a[@href=\'https://crm--SIT--c.cs14.visual.force.com/apex/taClientSearch']")).click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

	}
	
	@Test(groups="Fase1")
	public void TS6905_createdNewValidContact(){
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		contact.searchContact(DNI, DocValue[0], "femenino");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("ContactInfo_nextBtn")).click();
		
	}
	
	@Test(groups="Fase1")
	public void TS6965_Verificar_que_el_campo_Numero_de_documento_no_tenga_menos_de_7_digitos()	{
		boolean a = false;
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, "123", "femenino");
		List <WebElement> error = driver.findElements(By.cssSelector(".description.ng-binding"));
		for(WebElement e: error){
			if(e.getText().equals("Longitud Mínima De 7")){
				a=true;
				break;
			}
		}
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

	Assert.assertTrue(a);
	}
	
	@Test(groups="Fase1")
	public void TS6918_Seleccionar_Femenino_en_campo_Genero(){
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.sex("femenino");
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> genero = driver.findElements(By.id("GenderSearch"));
		System.out.println(genero.size());
		genero.get(0).isSelected();
		Assert.assertTrue(genero.get(0).isSelected());
	}
	
	@Test(groups="Fase1")
	public void TS6919_Seleccionar_Masculino_en_campo_Genero(){
		boolean a= false;
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.sex("masculino");
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> genero = driver.findElements(By.id("GenderSearch"));
		System.out.println(genero.size());
		genero.get(0).isSelected();
		Assert.assertTrue(genero.get(1).isSelected());
	}
	
	@Test(groups="Fase1")
	public void TS6964_Verificar_que_el_campo_Numero_de_documento_no_tenga_mas_de_8_digitos(){
		boolean a = false;
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, "123456789", "femenino");
		List <WebElement> error = driver.findElements(By.cssSelector(".description.ng-binding"));
		for(WebElement e: error){
			
			if(e.getText().equals("Longitud Máxima De 8")){
				a=true;
				break;
			}
		}
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

	Assert.assertTrue(a);
	}
	
	@Test(groups="Fase1")
	public void TS6967_Verificar_que_el_campo_tipo_de_documento_sea_obligatorio(){
		boolean a= false;
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact( "DNI","","");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		contact.searchContact( "-- Clear --","","");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> error = driver.findElements(By.cssSelector(".description.ng-binding"));
		for(WebElement e: error){
			if(e.getText().equals("Campo Requerido")){
				a=true;
				break;
			}
		}
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

	Assert.assertTrue(a);
	}
	
	//@Test(groups="Fase2")
	public void TS6937_Verificar_campo_Genero_obligatorio(){
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, "12345678", "");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

		System.out.println(driver.findElement(By.id("GenderSearch|0")).findElement(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-radio-control.ng-scope.ng-dirty.ng-invalid.ng-invalid-required")).isEnabled());
		
			}
	
	@Test(groups="Fase1")
	public void TS6966_Verificar_que_el_campo_Numero_de_Documento_sea_obligatorio(){
		boolean a= false;
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, "456", "femenino");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("DocumentInputSearch")).clear();
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> error = driver.findElements(By.cssSelector(".description.ng-binding"));
		for(WebElement e: error){
			if(e.getText().equals("Campo Requerido")){
				a=true;
				break;}}
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	Assert.assertTrue(a);
	}
	
	@Test(groups="Fase1")
	public void TS6914_Ingresar_mas_de_9_caracteres_en_el_campo_Pasaporte(){
		boolean a= false;
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("Pasaporte", "1234567890", "femenino");
		List <WebElement> error = driver.findElements(By.cssSelector(".description.ng-binding"));
		for(WebElement e: error){
			if(e.getText().equals("Longitud Máxima De 9")){
				a=true;
				break;}}
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(a);
	
	}
	
	@Test(groups="Fase1")
	public void TS6915_Ingresar_menos_de_9_caracteres_en_el_pasaporte(){
		boolean a= false;
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("Pasaporte", "1234567890", "femenino");
		List <WebElement> error = driver.findElements(By.cssSelector(".description.ng-binding"));
		for(WebElement e: error){
			if(e.getText().equals("Longitud Minima De 9")){
				a=true;
				break;}}
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(a);
	}
	
	@Test(groups="Fase1")
	public void TS6916_Ingresar_pasaporte_en_el_campo_Numero_de_Documento(){
		String PASA = "123456789";
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("Pasaporte", PASA, "femenino");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		String PASA2=driver.findElement(By.id("DocumentInputSearch")).getAttribute("value");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(PASA.equals(PASA2));

	}
	
	@Test(groups="Fase1")
	public void TS6912_Ingresar_DNI_en_el_campo_Numero_de_Documento(){
		String PASA = "1234567";
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, PASA, "femenino");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		String PASA2=driver.findElement(By.id("DocumentInputSearch")).getAttribute("value");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(PASA.equals(PASA2));
	}
	
	//@Test(groups="Fase2")
	public void TS6911_CUITNumber(){
		BasePage base = new BasePage();
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement type = driver.findElement(By.id("DocumentType"));
		base.setSimpleDropdown(type, "CUIT");
		driver.findElement(By.id("CuitDocument")).sendKeys("22-35689987-4");
	}
	
	@Test(groups="Fase1")
	public void TS6943_Verificar_el_ingreso_de_caracteres_alfanumericos_en_Pasaporte(){
		String PASA = "123ASD1";
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("Pasaporte", PASA, "femenino");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		String PASA2=driver.findElement(By.id("DocumentInputSearch")).getAttribute("value");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(PASA.equals(PASA2));
	
	}
	
	
	@Test(groups="Fase1")
	public void TS6945_Verificar_error_al_ingresar_CUIT_con_cero_al_inicio(){
		boolean a= false;
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("CUIT", "05698957425", "femenino");
		List <WebElement> error = driver.findElements(By.cssSelector(".description.ng-binding"));
		for(WebElement e: error){
			if(e.getText().equals("Mínimo 7 Caracteres Y Máximo 8 And El Primer Dígito No Debe Ser 0.")){
				a=true;
				break;}}
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(a);
	}
	
	@Test(groups="Fase1")
	public void TS6944_Verificar_error_al_ingresar_caracteres_alfanumericos_en_el_CUIT(){
		String PASA =  "AAAA";
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		boolean a= false;
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("CUIT",PASA, "femenino");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		String PASA2=driver.findElement(By.id("DocumentInputSearch")).getAttribute("value");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertFalse(PASA.equals(PASA2));
	}
	
	@Test(groups="Fase1")
	public void TS6935_Verificar_campo_CUIT_obligatorio(){
		boolean a= false;
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("CUIT", "456", "femenino");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("DocumentInputSearch")).clear();
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> error = driver.findElements(By.cssSelector(".description.ng-binding"));
		for(WebElement e: error){
			if(e.getText().equals("Campo Requerido")){
				a=true;
				break;}}
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	Assert.assertTrue(a);
	
}
	
	
	
	
	
	
	
	@Test(groups="Sales")
	public void TS14278_Ventas_NumeroOrden_Verificar_Orden_de_Venta_Abierta_ICCID(){
		SalesBase SB= new SalesBase(driver);
		SB.BuscarCuenta(DNI, NDNI);
		SB.crearnuevocliente(nombre, apellido, nacimiento);
		SB.agregarplan(plan);
		SB.continuar();
		SB.elegirvalidacion("DOC");
		SB.subirdoc();
		SB.error();
		SB.Crear_DatosDelCliente(impositiva);
		SB.Crear_DomicilioLegal(provincia, localidad, calle, local, altura, piso, dpto, CP);
		SB.Crear_CopiarDatosLegal();
		SB.BtnCrearNuevoCliente();
		SB.error2();
		SB.AsignarLinea();
		SB.SimulacionDeFactura();
		SB.verificarOrdenICCID();
	//	SB.borrarcuenta();
		
		
	}
	
	@Test(groups="Sales")
	
	public void TS14277_Ventas_NumeroOrden_Verificar_Orden_de_Venta_Abierta_Medio_de_Pago(){
		SalesBase SB= new SalesBase(driver);
		
		SB.borrarcuenta(nombre,apellido);
		SB.borrarcontacto(apellido,nombre);
		Assert.assertTrue(false);
		
	}
	
	@Test(groups="Sales")
	public void TS14275_Ventas_NumeroOrden_Verificar_Orden_de_Venta_Abierta_Modo_de_Entrega(){
		Assert.assertTrue(false);
		
	}
	
	@Test(groups="Sales")
	public void TS14272_Ventas_NumeroOrden_Verificar_Orden_de_Venta_Abierta_Nueva_Venta(){
		Assert.assertTrue(false);
		
	}
	
	@Test(groups="Sales")
	public void TS14274_Ventas_NumeroOrden_Verificar_Orden_de_Venta_Abierta_Seleccion_de_Linea(){
		SalesBase SB= new SalesBase(driver);
		SB.BuscarCuenta(DNI, NDNI);
		SB.crearnuevocliente(nombre, apellido, nacimiento);
		SB.agregarplan(plan);
		SB.continuar();
		SB.elegirvalidacion("DOC");
		SB.subirdoc();
		SB.error();
		SB.Crear_DatosDelCliente(impositiva);
		SB.Crear_DomicilioLegal(provincia, localidad, calle, local, altura, piso, dpto, CP);
		SB.Crear_CopiarDatosLegal();
		SB.BtnCrearNuevoCliente();
		SB.error2();
		SB.AsignarLinea();
		SB.SimulacionDeFactura();
		SB.verificarOrdenICCID();		
		Assert.assertTrue(false);

	}
	
	@Test(groups="Sales")
	public void TS14273_Ventas_NumeroOrden_Verificar_Orden_de_Venta_Abierta_Seleccionar_un_producto(){
		SalesBase SB= new SalesBase(driver);
		SB.BuscarCuenta(DNI, NDNI);
		SB.crearnuevocliente(nombre, apellido, nacimiento);
		SB.agregarplan(plan);
		Assert.assertTrue(false);

	}
	
	@Test(groups="Sales")
	public void TS14276_Ventas_NumeroOrden_Verificar_Orden_de_Venta_Abierta_Simulacion_de_Factura(){
		SalesBase SB= new SalesBase(driver);
		SB.BuscarCuenta(DNI, NDNI);
		SB.crearnuevocliente(nombre, apellido, nacimiento);
		SB.agregarplan(plan);
		SB.continuar();
		SB.elegirvalidacion("DOC");
		SB.subirdoc();
		SB.error();
		SB.Crear_DatosDelCliente(impositiva);
		SB.Crear_DomicilioLegal(provincia, localidad, calle, local, altura, piso, dpto, CP);
		SB.Crear_CopiarDatosLegal();
		SB.BtnCrearNuevoCliente();
		SB.error2();
		SB.AsignarLinea();
		SB.SimulacionDeFactura();
		Assert.assertTrue(false);

	}
	
	@Test(groups="Sales")
	public void TS14279_Ventas_NumeroOrden_Verificar_Orden_de_Venta_Abierta_Simulacion_de_Factura_Final(){
		Assert.assertTrue(false);
		
	}
	
	@Test(groups="Sales")
	public void TS14270_Ventas_NumeroOrden_Visualizar_Orden_de_Venta_Abierta_ICCID(){
		SalesBase SB= new SalesBase(driver);
		SB.BuscarCuenta(DNI, NDNI);
		SB.crearnuevocliente(nombre, apellido, nacimiento);
		SB.agregarplan(plan);
		SB.continuar();
		SB.elegirvalidacion("DOC");
		SB.subirdoc();
		SB.error();
		SB.Crear_DatosDelCliente(impositiva);
		SB.Crear_DomicilioLegal(provincia, localidad, calle, local, altura, piso, dpto, CP);
		SB.Crear_CopiarDatosLegal();
		SB.BtnCrearNuevoCliente();
		SB.error2();
		SB.AsignarLinea();
		SB.SimulacionDeFactura();
		SB.verificarOrdenICCID();
		Assert.assertTrue(false);

	}
	
	@Test(groups="Sales")
	public void TS14269_Ventas_NumeroOrden_Visualizar_Orden_de_Venta_Abierta_Medio_de_Pago(){
		Assert.assertTrue(false);
		
	}
	
	@Test(groups="Sales")
	public void TS14267_Ventas_NumeroOrden_Visualizar_Orden_de_Venta_Abierta_Modo_de_Entrega(){
		Assert.assertTrue(false);

	}
	
	@Test(groups="Sales")
	public void TS14264_Ventas_NumeroOrden_Visualizar_Orden_de_Venta_Abierta_Nueva_Venta(){
		Assert.assertTrue(false);

	}
	
	@Test
	public void TS14266_Ventas_NumeroOrden_Visualizar_Orden_de_Venta_Abierta_Seleccion_de_Linea(){
		Assert.assertTrue(false);

	}
	
	@Test(groups="Sales")
	public void TS14265_Ventas_NumeroOrden_Visualizar_Orden_de_Venta_Abierta_Seleccionar_un_producto(){
		SalesBase SB= new SalesBase(driver);
		SB.BuscarCuenta(DNI, NDNI);
		SB.crearnuevocliente(nombre, apellido, nacimiento);
		SB.agregarplan(plan);
		Assert.assertTrue(false);

	}
	
	@Test(groups="Sales")
	public void TS14268_Ventas_NumeroOrden_Visualizar_Orden_de_Venta_Abierta_Simulacion_de_Factura(){
		SalesBase SB= new SalesBase(driver);
		SB.BuscarCuenta(DNI, NDNI);
		SB.crearnuevocliente(nombre, apellido, nacimiento);
		SB.agregarplan(plan);
		SB.continuar();
		SB.elegirvalidacion("DOC");
		SB.subirdoc();
		SB.error();
		SB.Crear_DatosDelCliente(impositiva);
		SB.Crear_DomicilioLegal(provincia, localidad, calle, local, altura, piso, dpto, CP);
		SB.Crear_CopiarDatosLegal();
		SB.BtnCrearNuevoCliente();
		SB.error2();
		SB.AsignarLinea();
		SB.SimulacionDeFactura();
		Assert.assertTrue(false);

	}
	
	//************FASE 3*********************

	@Test(groups="Sales")
	public void TS38689_Alta_Contacto_Busqueda_Verificar_resultado_busqueda_cliente_activo_inactivo(){
		  SalesBase SB = new SalesBase(driver);
		  SB.BuscarCuenta("DNI", "");
		  try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		  WebElement act = driver.findElement(By.id("tab-scoped-1__item"));
		  Assert.assertTrue(act.getText().equals("Clientes Activos"));
		  WebElement ina = driver.findElement(By.id("tab-scoped-2__item"));
		  Assert.assertTrue(ina.getText().equals("Cliente Inactivos"));
		  try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}


	}
	
	@Test(groups="Sales")
	public void TS38760_Perfiles_Verificar_creacion_de_perfil_Canal_Tefonico(){
		SalesBase SB = new SalesBase(driver);
		SB.gestiondeusuarios();
		SB.validarperfil("Medina, Elena", "CC Venta y Atencion a Clientes");
		
		
			
	}
	@Test(groups="Sales")
	public void TS38761_Perfiles_Verificar_creacion_de_perfil_Oficina_Comercial(){
		SalesBase SB = new SalesBase(driver);
		SB.gestiondeusuarios();
		SB.validarperfil("Sit, Francisco", "Oficina Comercial");
		
	}
	@Test(groups="Sales")
	public void TS38762_Perfiles_Verificar_creacion_de_perfil_Oficina_Agente(){
		SalesBase SB = new SalesBase(driver);
		SB.gestiondeusuarios();
		SB.validarperfil("Borbon, Andres", "Agente Venta y Atencion a Clientes");
			
	}
	@Test(groups="Sales")
	public void TS38763_Perfiles_Verificar_creacion_de_perfil_Oficina_Logistica(){
		SalesBase SB = new SalesBase(driver);
		SB.gestiondeusuarios();
		SB.validarperfil("Sit, Nicolas", "Logistica B");
			perfil="agente";
			
	}
	@Test(groups="Sales")
	public void TS38790_Alta_Cuenta_Busqueda_Verificar_nombre_de_la_busqueda(){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		  Boolean f = false;
		  List<WebElement> gst = driver.findElements(By.cssSelector(".slds-page-header__title.vlc-slds-page-header__title.slds-truncate.ng-binding"));
		   for (WebElement e : gst){
		    System.out.println(e.getText());
		    if  (e.getText().equals("Gestión de clientes")){
		     f= true;}}
		  Assert.assertTrue(f);}
			
	
	@Test(groups="Sales")
	public void TS38791_Alta_Cuenta_Busqueda_Verificar_secciones_de_la_busqueda_de_cliente(){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		  WebElement tipodni=driver.findElement(By.id("SearchClientDocumentType"));
		  WebElement numdoc=driver.findElement(By.id("SearchClientDocumentNumber"));
		  WebElement linea=driver.findElement(By.id("PhoneNumber"));
		  Assert.assertTrue(tipodni.isEnabled());
		  Assert.assertTrue(numdoc.isEnabled());
		  Assert.assertTrue(linea.isEnabled());
		  Boolean f = false;
		  List<WebElement> busqadv=driver.findElements(By.cssSelector(".slds-form-element__label.slds-clearfix.ng-scope"));
		   for (WebElement e : busqadv){
		    if  (e.getText().equals("Búsqueda avanzada")){
		     f= true;}}
		  Assert.assertTrue(f);}
	
	@Test(groups="Sales")
	public void TS38792_Alta_Cuenta_Busqueda_Verificar_campos_de_la_busqueda_avanzada(){
		SalesBase SB = new SalesBase(driver);
		SB.BusquedaAvanzada();
		SB.validarcamposbusqueda();
			
	}
	@Test(groups="Sales")
	public void TS38793_Alta_Cuenta_Busqueda_Verificar_Nombre_y_Apellido_separado(){
		SalesBase SB = new SalesBase(driver);
		SB.BusquedaAvanzada();
		SB.BuscarAvanzada("pepe","", "", "", "");
		SB.validarespacio();
		
			
		
	}
	@Test(groups="Sales")
	public void TS38803_Ventas_General_Verificar_visualizacion_de_boton_Continuar(){
		SalesBase SB= new SalesBase(driver);
		SB.BuscarCuenta("DNI", "");;
		SB.acciondecontacto("catalogo");
		SB.agregarproductos();
		Assert.assertTrue(SB.validartxtbtn("Continuar"));
	}
	
	@Test(groups="Sales")
	public void TS39554_Ventas_General_Verificar_que_se_muestre_el_estado_de_una_OV(){
		SalesBase SB= new SalesBase(driver);
		SB.BuscarCuenta("DNI", "");;
		SB.acciondecontacto("catalogo");
		SB.agregarproductos();
		SB.continuar();
		SB.validarpasos();			
	}
	
	
	@Test(groups="Sales")
	public void TS39641_Alta_Contacto_Busqueda_Verificar_que_se_recuerden_los_datos_de_busqueda(){
		SalesBase SB= new SalesBase(driver);
		SB.BuscarCuenta(DNI, NDNI);
		SB.nuevocliente();
		SB.validarnuevocliente(DNI, NDNI);
	}
	
	@Test(groups="Sales")
	public void TS40650_Alta_Cuenta_Busqueda_Verificar_accion_boton_1(){
		SalesBase SB= new SalesBase(driver);
		SB.BuscarCuenta("DNI", "");;
		SB.acciondecontacto("catalogo");
		SB.validarentrarcatalogo();
	}
	
	@Test(groups="Sales")
	public void TS40648_Alta_Cuenta_Busqueda_Verificar_que_se_agregue_un_nivel_de_agrupamiento(){
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada("Flavia", "Sales", "", "", "");
		SB.validaragrupados();
	}
	
	@Test(groups="Sales")
	public void TS38688_Alta_Contacto_Busqueda_Verificar_resultado_busqueda_contacto_Sin_cuenta_asociada(){
		  SalesBase SB = new SalesBase(driver);
		  SB.BuscarCuenta("DNI", "");
		  try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		  WebElement cont = driver.findElement(By.id("tab-scoped-3__item"));
		   Assert.assertTrue(cont.getText().equals("Contactos"));
		 }
		  
		 
	@Test(groups="Sales")
	public void TS39798_Alta_Contacto_Busqueda_Verificar_accion_de_Crear_Cuenta(){
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada("pepe","", "", "", "");
		SB.validarcrearcuenta();
	}
	
	@Test(groups="Sales") 
	public void TS39733_Verificar_que_se_ejecuten_los_procesos_de_validacion(){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}				
		SalesBase SB = new SalesBase(driver);
		BasePage dni = new BasePage(driver);
		SB.BtnCrearNuevoCliente();
		dni.setSimpleDropdown(driver.findElement(By.id("DocumentTypeSearch")),"DNI");
		WebElement num = driver.findElement(By.id("DocumentInputSearch"));
		num.sendKeys("7323552"); 
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}				
	  List<WebElement> gen = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
	    	for(WebElement g : gen) {
	    		if(g.getText().equals("Masculino")) {
	    			g.click();}}}  

	@Test(groups="Sales")    // rOtO
	public void TS39658_Verificar_que_se_bonifique_el_costo_de_SIM_en_PlanPrepago() {
		SalesBase SB = new SalesBase(driver);
		SB.agregarplan("Plan con tarjeta");
	}

}
