package Tests;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
import Pages.ContactInformation;
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
	String[] DocValue = {"52698550","3569874563","365","ssss"};
	
	//@AfterClass(groups={"sales", "AltaDeContacto"})
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	
	//@AfterMethod(groups={"sales", "AltaDeContacto"})
	public void deslogin(){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.get("https://crm--sit.cs14.my.salesforce.com/home/home.jsp?tsid=02u41000000QWha/");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	SalesBase SB= new SalesBase(driver);
		SB.borrarcuenta(nombre,apellido);
		SB.borrarcontacto(apellido,nombre);

	}
	
	@BeforeClass(groups={"sales", "AltaDeContacto"})
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

	@BeforeMethod(groups={"sales", "AltaDeContacto"})
	public void setup() throws Exception {		
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.xpath("//a[@href=\'https://crm--sit--c.cs14.visual.force.com/apex/taClientSearch']")).click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}

	@Test(groups={"sales", "AltaDeContacto"})
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
	
	@Test(groups={"sales", "AltaDeContacto"})
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
			if(e.getText().equals("Longitud Mï¿½nima De 7")){
				a=true;
				break;
			}
		}
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

	Assert.assertTrue(a);
	}
	
	@Test(groups={"sales", "AltaDeContacto"})
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
	
	@Test(groups={"sales", "AltaDeContacto"})
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
	
	@Test(groups={"sales", "AltaDeContacto"})
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
			
			if(e.getText().equals("Longitud Mï¿½xima De 8")){
				a=true;
				break;
			}
		}
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

	Assert.assertTrue(a);
	}
	
	@Test(groups={"sales", "AltaDeContacto"})
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
	
	@Test(groups={"sales", "AltaDeContacto"})
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
	
	@Test(groups={"sales", "AltaDeContacto"})
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
			if(e.getText().equals("Longitud Mï¿½xima De 9")){
				a=true;
				break;}}
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(a);
	
	}
	
	@Test(groups={"sales", "AltaDeContacto"})
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
	
	@Test(groups={"sales", "AltaDeContacto"})
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
	
	@Test(groups={"sales", "AltaDeContacto"})
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
	
	//@Test(groups={"sales", "AltaDeContacto"})
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
	
	@Test(groups={"sales", "AltaDeContacto"})
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
	
	
	@Test(groups={"sales", "AltaDeContacto"})
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
			if(e.getText().equals("Mï¿½nimo 7 Caracteres Y Mï¿½ximo 8 And El Primer Dï¿½gito No Debe Ser 0.")){
				a=true;
				break;}}
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(a);
	}
	
	@Test(groups={"sales", "AltaDeContacto"})
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
	
	@Test(groups={"sales", "AltaDeContacto"})
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
	
	
	
	
	
	@Test(groups={"sales", "AltaDeContacto"})
	public void TS6901_Completar_los_campos_luego_de_una_busqueda_de_contacto_inexistente()	{
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, DocValue[0], "masculino");
		driver.findElement(By.id("ContactSearch_nextBtn")).click();
		try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
		ContactInformation page = new ContactInformation(driver);
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page.setContactInformation(nombre, apellido, nacimiento);
		
	}
	
	@Test(groups={"sales", "AltaDeContacto"})
	public void TS6920_Seleccionar_opcion_de_validacion_de_identidad(){
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, DocValue[0], "masculino");
		driver.findElement(By.id("ContactSearch_nextBtn")).click();
		try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
		ContactInformation page = new ContactInformation(driver);
		page.setContactInformation(nombre, apellido, nacimiento);
		driver.findElement(By.cssSelector(".slds-checkbox--faux")).click();
		driver.findElement(By.id("Contact_nextBtn")).click();
		
	}
	@Test(groups={"sales", "AltaDeContacto"})
	public void TS6909_Ingresar_caracteres_numericos_en_campo_Apellido(){
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, DocValue[0], "masculino");
		driver.findElement(By.id("ContactSearch_nextBtn")).click();
		try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
		driver.findElement(By.id("LastName")).sendKeys("123");
		driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-invalid.ng-valid-minlength.ng-valid-maxlength.ng-dirty.ng-valid-parse.ng-invalid-pattern.ng-valid-required"));
	}
	
	@Test(groups={"sales", "AltaDeContacto"})
	public void TS6910_Ingresar_caracteres_numericos_en_campo_Nombre()
	{	
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, DocValue[0], "masculino");
		driver.findElement(By.id("ContactSearch_nextBtn")).click();
		try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("FirstName")).clear();
		driver.findElement(By.id("FirstName")).sendKeys("123");
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-invalid.ng-valid-minlength.ng-valid-maxlength.ng-dirty.ng-valid-parse.ng-invalid-pattern.ng-valid-required")).isDisplayed());	
	}	
	
	@Test(groups={"sales", "AltaDeContacto"})
	public void TS6949_Verificar_Fecha_de_Nacimiento_con_ingreso_manual() {
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, DocValue[0], "masculino");
		driver.findElement(By.id("ContactSearch_nextBtn")).click();
		try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
		driver.findElement(By.id("Birthdate")).sendKeys(nacimiento);
		Assert.assertTrue(	driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-dirty.ng-valid-parse.ng-valid-required.ng-valid.ng-valid-valid")).isDisplayed());
	}
	
	@Test(groups={"sales", "AltaDeContacto"})
	public void TS6950_Fecha_de_Nacimiento_con_ingreso_manual_Anio_con_letras_o_mas_de_5_digitos(){
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, DocValue[0], "masculino");
		driver.findElement(By.id("ContactSearch_nextBtn")).click();
		try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
		driver.findElement(By.id("Birthdate")).sendKeys(DateOfBirthdayWrong +"4");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-dirty.ng-valid-parse.ng-valid-required.ng-invalid.ng-invalid-valid")).isDisplayed());
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("Birthdate")).clear();
		driver.findElement(By.id("Birthdate")).sendKeys("agosto");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-dirty.ng-valid-parse.ng-valid-required.ng-invalid.ng-invalid-valid")).isDisplayed());

	}
	

	
	@Test(groups={"sales", "AltaDeContacto"})
	public void TS6951_Verificar_Fecha_de_Nacimiento_con_ingreso_manual_Dia_Fuera_de_rango(){
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, DocValue[0], "masculino");
		driver.findElement(By.id("ContactSearch_nextBtn")).click();
		try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
		driver.findElement(By.id("Birthdate")).sendKeys("32/08/1999");
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-dirty.ng-valid-parse.ng-valid-required.ng-invalid.ng-invalid-valid")).isDisplayed());
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("Birthdate")).clear();
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("Birthdate")).sendKeys("00/08/1999");
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-dirty.ng-valid-parse.ng-valid-required.ng-invalid.ng-invalid-valid")).isDisplayed());

	}
	

	
	@Test(groups={"sales", "AltaDeContacto"})
	public void TS6952_Verificar_Fecha_de_Nacimiento_con_ingreso_manual_Mes_Fuera_de_rango() 
	{
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, DocValue[0], "masculino");
		driver.findElement(By.id("ContactSearch_nextBtn")).click();
		try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
		driver.findElement(By.id("Birthdate")).sendKeys("22/13/1999");
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-dirty.ng-valid-parse.ng-valid-required.ng-invalid.ng-invalid-valid")).isDisplayed());
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("Birthdate")).clear();
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("Birthdate")).sendKeys("22/00/1999");
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-dirty.ng-valid-parse.ng-valid-required.ng-invalid.ng-invalid-valid")).isDisplayed());

	}
	
	//@Test(groups={"sales", "AltaDeContacto"})
	public void TS6974_Verificar_valor_del_check_de_email_por_default() 
	{	
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, DocValue[0], "masculino");
		driver.findElement(By.id("ContactSearch_nextBtn")).click();
		try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
		Assert.assertTrue(!driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-pristine.ng-scope.ng-invalid.ng-invalid-required")).isSelected());
	}
	
	//@Test(groups={"sales", "AltaDeContacto"})
	public void TS6941_Verificar_check_de_no_tener_email() 
	{
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, DocValue[0], "masculino");
		driver.findElement(By.id("ContactSearch_nextBtn")).click();
		try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
		driver.findElement(By.cssSelector(".slds-checkbox--faux")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-pristine.ng-scope.ng-valid.ng-valid-required")).isSelected());
	}
	

	@Test(groups={"sales", "AltaDeContacto"})
	public void TS6934_Verificar_campo_Apellido_obligatorio(){
		boolean a= false;
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, DocValue[0], "masculino");
		driver.findElement(By.id("ContactSearch_nextBtn")).click();
		try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
	driver.findElement(By.id("LastName")).clear();
	try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	List<WebElement>asl=driver.findElements(By.cssSelector(".description.ng-binding"));
	for(WebElement e: asl){
		if(e.getText().equals("Campo Requerido")){
			a=true;}}
	Assert.assertTrue(a);
	}
	
	@Test(groups={"sales", "AltaDeContacto"})
	public void TS6936_Verificar_campo_Fecha_de_Nacimiento_obligatorio(){
		boolean a= false;
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, DocValue[0], "masculino");
		driver.findElement(By.id("ContactSearch_nextBtn")).click();
		try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
	driver.findElement(By.id("Birthdate")).clear();
	try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	List<WebElement>asl=driver.findElements(By.cssSelector(".description.ng-binding"));
	for(WebElement e: asl){
		if(e.getText().equals("Campo Requerido")){
			a=true;}}
	Assert.assertTrue(a);
	}
	
	@Test(groups={"sales", "AltaDeContacto"})
	public void TS6938_Verificar_campo_Nombre_obligatorio(){
		boolean a= false;
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, DocValue[0], "masculino");
		driver.findElement(By.id("ContactSearch_nextBtn")).click();
		try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
	driver.findElement(By.id("FirstName")).clear();
	try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	List<WebElement>asl=driver.findElements(By.cssSelector(".description.ng-binding"));
	for(WebElement e: asl){
		if(e.getText().equals("Campo Requerido")){
			a=true;}}
	Assert.assertTrue(a);	}
	
	@Test(groups={"sales", "AltaDeContacto"})
	public void TS6931_calendarBirthDate(){
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, DocValue[0], "masculino");
		driver.findElement(By.id("ContactSearch_nextBtn")).click();
		try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
		driver.findElement(By.id("Birthdate")).click();
		driver.findElement(By.cssSelector(".datepicker.-bottom-left-.-from-bottom-.active"));
	}

	@Test(groups={"sales", "AltaDeContacto"})
	public void TS6957_Verificar_mascara_del_campo_Fecha_de_Nacimiento() {

		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(nombre, apellido, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, DocValue[0], "masculino");
		driver.findElement(By.id("ContactSearch_nextBtn")).click();
		try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
		List<WebElement> profileinfo = driver.findElements(By.id("Birthdate")); 
		Assert.assertTrue(driver.findElement(By.id("Birthdate")).isDisplayed());
		 for(int i=1; i<profileinfo.size(); i+=2){
		  String b = (profileinfo.get(i).getText());  
		  String datePattern = "\\d{2}/\\d{2}/\\d{4}";
		  String date1 = b;
		  Boolean isDate1 = date1.matches(datePattern);
		 Assert.assertTrue(isDate1);
		 }

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
		    if  (e.getText().equals("Gestiï¿½n de clientes")){
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
		    if  (e.getText().equals("Bï¿½squeda avanzada")){
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
	
//	============================ Fase 4 ============================= 	
	
	@Test(groups = "Sales") 
	public void TS76235_Alta_Cuenta_Consumer_Valida_alta_mayor_o_igual_16anios() {
	SalesBase SB = new SalesBase(driver);
	BasePage dni = new BasePage(driver);
	driver.findElement(By.id("dataInput_nextBtn")).click();
	sleep(5000);	
	dni.setSimpleDropdown(driver.findElement(By.id("DocumentTypeSearch")),"DNI");
	driver.findElement(By.id("DocumentInputSearch")).click();
	driver.findElement(By.id("DocumentInputSearch")).sendKeys("1234591");
	try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}				
	List<WebElement> gen = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
    	for(WebElement g : gen) {
    		if(g.getText().equals("Masculino")) {
    			g.click();}}
    driver.findElement(By.id("ContactSearch_nextBtn")).click();
    sleep(5000);
	WebElement nac = driver.findElement(By.id("Birthdate"));
	nac.clear();
	nac.sendKeys("12/12/2005");
	boolean error = false;
	List<WebElement> cart = driver.findElements(By.cssSelector(".message.description.ng-binding.ng-scope"));
		for(WebElement c: cart) {
			if(c.getText().contains("Fecha de nacimiento invï¿½lida")) {
				c.isDisplayed();
				error= true;
				System.out.println(c.getText());
			}
		}
		Assert.assertTrue(error);
	}
	
	@Test(groups = "Sales") 
	public void TS76134_Verificar_DNI_inexistente_y_creacion_de_contacto() {
	driver.findElement(By.id("SearchClientDocumentNumber")).sendKeys("7878785");
	driver.findElement(By.id("SearchClientsDummy")).click();
	sleep(3000);
	List<WebElement> nores = driver.findElements(By.cssSelector(".ta-no-result-msg"));
	WebElement cli = driver.findElement(By.id("dataInput_nextBtn"));
	for(WebElement n : nores) {
		if(n.getText().toLowerCase().equals("la b\u00fasqueda no arroj\u00f3 resultados.")) {
			cli.click();
		}}
	try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}				
	List<WebElement> gen = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
    	for(WebElement g : gen) {
    		if(g.getText().equals("Masculino")) {
    			g.click();}}
    Boolean y=false;
	WebElement nam = driver.findElement(By.id("FirstName"));
	WebElement ape = driver.findElement(By.id("LastName"));
	WebElement cump = driver.findElement(By.id("Birthdate"));
	List<WebElement> mai = driver.findElements(By.cssSelector(".vlc-control-wrapper"));
		for(WebElement m : mai) {
			if(m.getText().equals("E-MAIL")) {
			y=true;	}}
	Assert.assertTrue(nam.isDisplayed());
	Assert.assertTrue(ape.isDisplayed());
	Assert.assertTrue(cump.isDisplayed());
	}

	@Test(groups = "Sales") 
	public void TS76132_Verificar_busqueda_combinada_DNI_con_NyAp_DNI_Existe_NyAP_No_Existe() {
		BasePage dni = new BasePage(driver);
		sleep(5000);	
		dni.setSimpleDropdown(driver.findElement(By.id("SearchClientDocumentType")),"DNI");
		driver.findElement(By.id("SearchClientDocumentNumber")).click();
		driver.findElement(By.id("SearchClientDocumentNumber")).sendKeys("17856969");	
		List<WebElement> busqueda = driver.findElements(By.className("slds-form-element__control"));	
		for(WebElement e: busqueda){
			if(e.getText().equals("Búsqueda avanzada")){
				e.click();
				e.click();
				break;}}
		sleep(5000);
		driver.findElement(By.id("ContactFirstName")).sendKeys("papa");
		driver.findElement(By.id("ContactLastName")).sendKeys("nata");
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep(5000);
		WebElement tTel = driver.findElement(By.id("tab-scoped-1")).findElement(By.tagName("tbody")).findElements(By.tagName("td")).get(3);
		Assert.assertTrue(tTel.getText().equals("17856969"));
		WebElement tNom = driver.findElement(By.id("tab-scoped-1")).findElement(By.tagName("tbody")).findElements(By.tagName("td")).get(0);
		Assert.assertFalse(tNom.getText().equals("papa" + " " +"nata"));
		}
	
	@Test(groups = "Sales") 
	public void TS76140_Validar_nombres_de_los_campos() {
		BasePage dni = new BasePage(driver);
		dni.setSimpleDropdown(driver.findElement(By.id("SearchClientDocumentType")),"DNI");
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep (3000);	
		List<WebElement> contac = driver.findElements(By.cssSelector(".slds-tabs--scoped__link"));
			for(WebElement c: contac) {
				c.getText().equals("Contactos");
				c.click();
			}
		sleep(3000);
		WebElement asdf = driver.findElement(By.id("tab-scoped-3")).findElement(By.tagName("tbody")).findElements(By.tagName("td")).get(0);
		System.out.println(asdf.getText());
		ArrayList<String> cuadro = new ArrayList<String>();
		List<WebElement> datos = driver.findElements(By.className("vloc-table-wrapper-scrollable"));
			for(WebElement c: datos){
				cuadro.add(c.getText());
			}
		SalesBase SB = new SalesBase(driver);
		SB.validarcrearcuenta();
		WebElement desc = driver.findElement(By.id("ContactName"));
		WebElement titu = driver.findElement(By.id("Owner"));
		Assert.assertTrue(desc.isDisplayed());
		Assert.assertTrue(titu.isDisplayed());
	}
	 @Test(groups = "Sales") 
	  public void TS76115_Verificar_alta_de_contacto_con_cuenta_generica() {
	    SalesBase SB = new SalesBase(driver);
	    SB.BuscarCuenta(DNI, "1112225");
	    SB.crearnuevocliente("cuenta", "generica", nacimiento);
	   sleep(15000);
	   List<WebElement> gen = driver.findElements(By.cssSelector(".slds-page-header__title.slds-m-right--small.slds-truncate.slds-align-middle"));
	    for(WebElement g:gen) {
	    	Assert.assertTrue((g.getText().toLowerCase().equals("cuenta generica")));
	    	System.out.println(g.getText());
	    	}
	    driver.get("https://crm--sit.cs14.my.salesforce.com/home/home.jsp");
	    driver.findElement(By.xpath("//a[@href=\'https://crm--sit--c.cs14.visual.force.com/apex/taClientSearch']")).click();
	    SB.BuscarAvanzada("cuenta", "generica", "", "", "");
	   List<WebElement> borra = driver.findElements(By.cssSelector(".slds-button.slds-button.slds-button--icon"));
	   for(WebElement b : borra) {
		  if(b.getText().equals("Ver Contacto")) {
			  b.click();
		  }
	   }
	   sleep(15000);
	   ArrayList<String> allTabs = new ArrayList<String>(driver.getWindowHandles());  
       driver.switchTo().window(allTabs.get(1));
			WebElement btns = driver.findElement(By.id("topButtonRow"));
			btns.findElement(By.name("del")).click();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			 Alert alert = driver.switchTo().alert();
			   alert.accept();
				try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
				driver.close();  
			      driver.switchTo().window(allTabs.get(0));
	 }
	 
	 	@Test(groups = "Sales") 
	  public void TS76116_Verificar_busqueda_modificacion_de_contacto(){
	 		SalesBase SB = new SalesBase(driver);
	 		 SB.BuscarCuenta(DNI, "1212125");
	 	    SB.crearnuevocliente("contacto", "modifica", nacimiento);
	 	   sleep(15000);
	 	  List<WebElement> gen = driver.findElements(By.cssSelector(".slds-page-header__title.slds-m-right--small.slds-truncate.slds-align-middle"));
		    for(WebElement g:gen) {
		    	Assert.assertTrue((g.getText().toLowerCase().equals("contacto, modifica")));
		    	System.out.println(g.getText());
		    	}
		    driver.get("https://crm--sit.cs14.my.salesforce.com/home/home.jsp");
		    driver.findElement(By.xpath("//a[@href=\'https://crm--sit--c.cs14.visual.force.com/apex/taClientSearch']")).click();
		    SB.BuscarAvanzada("contacto", "modifica", "", "", "");
		   List<WebElement> borra = driver.findElements(By.cssSelector(".slds-button.slds-button.slds-button--icon"));
		   for(WebElement b : borra) {
			  if(b.getText().equals("Ver Contacto")) {
				  b.click();
			  }
		   }
	 	sleep(15000);
		ArrayList<String> allTabs = new ArrayList<String>(driver.getWindowHandles());  
	    driver.switchTo().window(allTabs.get(1));
	    WebElement btns = driver.findElement(By.id("topButtonRow"));
	    btns.findElement(By.name("edit")).click();
	    sleep(10000);
	 	WebElement modi = driver.findElement(By.id("bodyCell"));
	 	WebElement name = driver.findElement(By.id("name_lastcon2"));
	 	Assert.assertTrue(modi.getText().contains("Modificación de contacto"));
	 	Assert.assertTrue(name.isEnabled());
	 	WebElement btn = driver.findElement(By.id("topButtonRow"));
	    btn.findElement(By.name("cancel")).click();
	    sleep(10000);
	    WebElement btndel = driver.findElement(By.id("topButtonRow"));
		btndel.findElement(By.name("del")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		 Alert alert = driver.switchTo().alert();
		   alert.accept();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.close();  
		      driver.switchTo().window(allTabs.get(0));
	 	}
	 	

	 	@Test(groups = "Sales") 
	  public void TS76138_Verificar_descripcion_al_dejar_mail_vacio(){
	 	SalesBase SB = new SalesBase(driver);
	 	BasePage dni = new BasePage(driver);
	 	SB.BtnCrearNuevoCliente();
	 	dni.setSimpleDropdown(driver.findElement(By.id("DocumentTypeSearch")),"DNI");
		driver.findElement(By.id("DocumentInputSearch")).click();
		driver.findElement(By.id("DocumentInputSearch")).sendKeys("1235591");
		List<WebElement> gen = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
	    	for(WebElement g : gen) {
	    		if(g.getText().equals("Masculino")) {
	    			g.click();}} 
	    WebElement sig = driver.findElement(By.id("ContactSearch_nextBtn"));
	    sig.click();
	    sleep(5000);
	 	List<WebElement> mail = driver.findElements(By.cssSelector(".slds-form-element.label.ng-binding"));
	 	for(WebElement m :mail) {
	 		if(m.equals("E-mail")) {
	 		System.out.println(m.getAttribute("value"));
	 	}}
	 	}
}
