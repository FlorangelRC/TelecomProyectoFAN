package Tests;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
		SB.BtnCrearNuevoCliente();
		String asd = driver.findElement(By.id("SearchClientDocumentNumber")).getAttribute("value");
		CustomerCare CC = new CustomerCare(driver);
		ContactSearch contact = new ContactSearch(driver);
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("FirstName")).sendKeys("aaa");
		driver.findElement(By.id("LastName")).sendKeys("bbb");
		driver.findElement(By.id("Birthdate")).sendKeys("28/12/1999");
		contact.sex("masculino");
		driver.findElement(By.id("Contact_nextBtn")).click();
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.get("https://crm--sit--c.cs14.visual.force.com/apex/taClientSearch#/");
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		contact.searchContact(DNI, asd, "");
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep(5000);
		WebElement coldni = driver.findElement(By.id("tab-scoped-2")).findElement(By.tagName("section")).findElement(By.tagName("tbody")).findElements(By.tagName("td")).get(3);
		System.out.println(coldni.getText());
		Assert.assertTrue(coldni.getText().equals(asd));	
		}
	
	@Test(groups={"sales", "AltaDeContacto"})
	public void TS6965_Verificar_que_el_campo_Numero_de_documento_no_tenga_menos_de_7_digitos()	{
		boolean a = false;
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, "123", "");
		List <WebElement> error = driver.findElements(By.cssSelector(".description.ng-binding"));
		for(WebElement e: error){
			if(e.getText().toLowerCase().equals("longitud m\u00ednima de 7")){
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
		CustomerCare CC = new CustomerCare(driver);
		SB.BtnCrearNuevoCliente();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.sex("femenino");
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> genero = driver.findElements(By.id("Gender"));
		System.out.println(genero.size());
		genero.get(0).isSelected();
		Assert.assertTrue(genero.get(0).isSelected());
	}
	
	@Test(groups={"sales", "AltaDeContacto"})
	public void TS6919_Seleccionar_Masculino_en_campo_Genero(){
		SalesBase SB = new SalesBase(driver);
		CustomerCare CC = new CustomerCare(driver);
		SB.BtnCrearNuevoCliente();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.sex("masculino");
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> genero = driver.findElements(By.id("Gender"));
		System.out.println(genero.size());
		genero.get(0).isSelected();
		Assert.assertTrue(genero.get(1).isSelected());
	}
	
	@Test(groups={"sales", "AltaDeContacto"})
	public void TS6964_Verificar_que_el_campo_Numero_de_documento_no_tenga_mas_de_8_digitos(){
		boolean a = false;
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, "123456789", "");
		List <WebElement> error = driver.findElements(By.cssSelector(".description.ng-binding"));
		for(WebElement e: error){
			
			if(e.getText().toLowerCase().equals("longitud m\u00e1xima de 8")){
				a=true;
				break;
			}
		}
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

	Assert.assertTrue(a);
	}
	
//	@Test(groups={"sales", "AltaDeContacto"}) //no es obligatorio
	public void TS6967_Verificar_que_el_campo_tipo_de_documento_sea_obligatorio(){
		boolean a= false;
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
	
	@Test(groups="Fase2")
	public void TS6937_Verificar_campo_Genero_obligatorio(){
		SalesBase SB = new SalesBase(driver);
		SB.BtnCrearNuevoCliente();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("FirstName")).sendKeys("a");
		driver.findElement(By.id("LastName")).sendKeys("b");
		driver.findElement(By.id("Birthdate")).sendKeys("28/12/1999");
		WebElement gen = driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-radio-control.ng-pristine.ng-scope.ng-invalid.ng-invalid-required")).findElement(By.id("Gender|0"));
		Assert.assertTrue(gen.isEnabled());
		
			}
	
	@Test(groups={"sales", "AltaDeContacto"}) //no es obligatorio
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
		boolean a = false;
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("Pasaporte", "1234567890", "");
		List <WebElement> error = driver.findElements(By.cssSelector(".description.ng-binding"));
		for(WebElement e: error){
			if(e.getText().toLowerCase().equals("longitud m\u00e1xima de 9")){
				a=true;
				break;}}
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(a);	
	
	}
	
	@Test(groups={"sales", "AltaDeContacto"})
	public void TS6915_Ingresar_menos_de_9_caracteres_en_el_pasaporte(){
		boolean a = false;
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("Pasaporte", "1234567890", "");
		List <WebElement> error = driver.findElements(By.cssSelector(".description.ng-binding"));
		for(WebElement e: error){
			if(e.getText().toLowerCase().equals("longitud minima de 9")){
				a=true;
				break;}}
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(a);	
	
	}
	
	 @Test(groups={"sales", "AltaDeContacto"})   //verify
	public void TS6916_Ingresar_pasaporte_en_el_campo_Numero_de_Documento(){
		String PASA = "4651327";
		boolean esta = false;
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta("Pasaporte", PASA);
		assertTrue(driver.findElement(By.id("SearchClientDocumentNumber")).getAttribute("value").equals(PASA));
		List <WebElement> cc = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for (WebElement x : cc) {
			if (x.getText().toLowerCase().contains("+ crear nuevo cliente")) {
				esta = true;
				break;
			}
		}
		assertTrue(esta);
	}
	
	@Test(groups={"sales", "AltaDeContacto"}) //verify 
	public void TS6912_Ingresar_DNI_en_el_campo_Numero_de_Documento(){
		String DNI = "4651327";
		boolean esta = false;
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta("DNI", DNI);
		assertTrue(driver.findElement(By.id("SearchClientDocumentNumber")).getAttribute("value").equals(DNI));
		List <WebElement> cc = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for (WebElement x : cc) {
			if (x.getText().toLowerCase().contains("+ crear nuevo cliente")) {
				esta = true;
				break;
			}
		}
		assertTrue(esta);
	}
	
	@Test(groups={"sales", "AltaDeContacto"})
	public void TS6911_Numero_De_Cuit_Con_Guiones(){
		String CUIT = "22-35689987-4";
		boolean esta = false;
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta("CUIT", CUIT);
		driver.findElement(By.id("alert-container")).isDisplayed();
		
	}
	
	@Test(groups={"sales", "AltaDeContacto"})
	public void TS6943_Verificar_el_ingreso_de_caracteres_alfanumericos_en_Pasaporte(){
		String PASA = "123ASD1";
		SalesBase SB = new SalesBase(driver);
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("Pasaporte", PASA, "");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		String PASA2=driver.findElement(By.id("DocumentInputSearch")).getAttribute("value");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(PASA.equals(PASA2));
	
	}
	
	
	@Test(groups={"sales", "AltaDeContacto"})  // no da error
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
			if(e.getText().equals("M\u00ednimo 7 Caracteres Y M\u00e1ximo 8 And El Primer D\u00edgito No Debe Ser 0.")){
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
		driver.findElements(By.className("listItemPad")).get(13).click();
		sleep(4000);
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
		    if  (e.getText().equals("Gesti\u00f3n de clientes")){
		     f= true;}}
		  Assert.assertTrue(f);
	}
			
	
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
		    if  (e.getText().equals("B\u00fasqueda avanzada")){
		     f= true;
		     }
		    }
		  Assert.assertTrue(f);
		  }
	
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
		SB.BuscarAvanzada("pepeasd","argentoasd", "", "", "");
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
		SB.BuscarCuenta(DNI, "5423156");
		List <WebElement> cc = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for (WebElement x : cc) {
			if (x.getText().toLowerCase().contains("+ crear nuevo cliente")) {
				x.click();
				break;
			}
		}
		SB.validarnuevocliente(DNI, "5423156");
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
		SB.BuscarAvanzada("cuenta", "generica", "", "", "");
		List <WebElement> lista = driver.findElements(By.cssSelector(".slds-truncate.ng-binding"));
		int a = 0;
		for (WebElement x : lista) {
			if (x.getText().toLowerCase().contains("cuenta generica")) {
				a++;
			}
		}
		Assert.assertTrue(a >= 2);
	}
	
	@Test(groups = "Sales")
	public void TS38688_Alta_Contacto_Busqueda_Verificar_resultado_busqueda_contacto_Sin_cuenta_asociada() {
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta("DNI", "");
		sleep(5000);
		WebElement cont = driver.findElement(By.id("tab-scoped-3__item"));
		Assert.assertTrue(cont.getText().equals("Contactos"));
	}
		  		 
	@Test(groups="Sales")
	public void TS39798_Alta_Contacto_Busqueda_Verificar_accion_de_Crear_Cuenta(){
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada("cuenta", "generica", "", "", "");
		List <WebElement> nc = driver.findElements(By.cssSelector(".slds-button.slds-button.slds-button--icon"));
		for (WebElement x : nc) {
			if (x.getText().toLowerCase().contains("nueva cuenta")) {
				x.click();
				break;
			}
		}
		sleep(7000);
		Assert.assertTrue(driver.findElement(By.id("AccountData_nextBtn")).isDisplayed());
	}
	
	@Test(groups = "Sales")
	public void TS39733_Verificar_que_se_ejecuten_los_procesos_de_validacion() {
		BasePage dni = new BasePage(driver);
		String a = "1112225";
		dni.setSimpleDropdown(driver.findElement(By.id("SearchClientDocumentType")), "DNI");
		driver.findElement(By.id("SearchClientDocumentNumber")).sendKeys(a);
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"tab-scoped-3\"]/section/div/table/tbody/tr/td[1]/a")).click();
		sleep(7000);
		WebElement numdni = driver.findElement(By.id("DocumentNumber"));
		WebElement nomb = driver.findElement(By.id("FirstName"));
		WebElement apel = driver.findElement(By.id("LastName"));
		Assert.assertTrue(numdni.getAttribute("value").equals(a));
		Assert.assertTrue(nomb.getAttribute("value").equals("Cuenta"));
		Assert.assertTrue(apel.getAttribute("value").equals("Generica"));
	}

	@Test(groups="Sales")    // rOtO
	public void TS39658_Verificar_que_se_bonifique_el_costo_de_SIM_en_PlanPrepago() {
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta(DNI, "");
		SB.acciondecontacto("catalogo");
		SB.agregarplan("Plan con tarjeta");
		sleep(20000);
		List <WebElement> plan = driver.findElements(By.cssSelector(".slds-button.cpq-item-has-children"));
		boolean a = false;
		for (WebElement x : plan) {
			if (x.getText().toLowerCase().contains("plan con tarjeta")) {
				a = true;
			}
		}
		List <WebElement> precio = driver.findElements(By.cssSelector(".slds-col.slds-shrink.slds-text-align--center"));
		Assert.assertTrue(precio.get(3).getText().contains("0,00"));
	}
	
//	============================ Fase 4 ============================= 	
	
	@Test(groups = "Sales") 
	public void TS76235_Alta_Cuenta_Consumer_Valida_alta_mayor_o_igual_16anios() {
		Random aleatorio = new Random(System.currentTimeMillis());
		aleatorio.setSeed(System.currentTimeMillis());
		int intAleatorio = aleatorio.nextInt(8999999) + 1000000;
		driver.findElement(By.id("SearchClientDocumentNumber")).sendKeys(Integer.toString(intAleatorio));
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep(3000);
		List<WebElement> cc = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for (WebElement x : cc) {
			if (x.getText().toLowerCase().contains("+ crear nuevo cliente")) {
				x.click();
				break;
			}
		}
		sleep(5000);
		driver.findElement(By.id("Birthdate")).sendKeys("12/12/2005");
		sleep(2000);
		WebElement error = driver.findElement(By.cssSelector(".message.description.ng-binding.ng-scope"));
		Assert.assertTrue(error.getText().toLowerCase().contains("fecha de nacimiento inv\u00e1lida"));
	}
	
	@Test(groups = "Sales")
	public void TS76134_Alta_Cuenta_Busqueda_Verificar_DNI_inexistente_y_creacion_de_contacto() {
		Random aleatorio = new Random(System.currentTimeMillis());
		aleatorio.setSeed(System.currentTimeMillis());
		int intAleatorio = aleatorio.nextInt(8999999) + 1000000;
		driver.findElement(By.id("SearchClientDocumentNumber")).sendKeys(Integer.toString(intAleatorio));
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep(3000);
		WebElement msj = driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope"));
		boolean a = false;
		if (msj.getText().toLowerCase().contains("no hay ning\u00fan cliente con este tipo y n\u00famero de documento. busc\u00e1 con otro dato o cre\u00e1 un nuevo cliente")) {
			a = true;
		}
		List<WebElement> cc = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for (WebElement x : cc) {
			if (x.getText().toLowerCase().contains("+ crear nuevo cliente")) {
				x.click();
				break;
			}
		}
		sleep(5000);
		WebElement nam = driver.findElement(By.id("FirstName"));
		WebElement ape = driver.findElement(By.id("LastName"));
		WebElement cump = driver.findElement(By.id("Birthdate"));
		List<WebElement> mai = driver.findElements(By.cssSelector(".vlc-control-wrapper"));
		Boolean y = false;
		for (WebElement m : mai) {
			if (m.getText().equals("E-MAIL")) {
				y = true;
			}
		}
		Assert.assertTrue(a && y);
		Assert.assertTrue(nam.isDisplayed());
		Assert.assertTrue(ape.isDisplayed());
		Assert.assertTrue(cump.isDisplayed());
	}

	@Test(groups = "Sales")
	public void TS76132_Alta_Cuenta_Busqueda_Verificar_busqueda_combinada_DNI_con_NyAp_DNI_Existe_NyAP_No_Existe() {
		BasePage dni = new BasePage(driver);
		dni.setSimpleDropdown(driver.findElement(By.id("SearchClientDocumentType")), "DNI");
		driver.findElement(By.id("SearchClientDocumentNumber")).click();
		driver.findElement(By.id("SearchClientDocumentNumber")).sendKeys("17856969");
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada("papa", "nata", "", "", "");
		WebElement tTel = driver.findElement(By.id("tab-scoped-1")).findElement(By.tagName("tbody")).findElements(By.tagName("td")).get(3);
		Assert.assertTrue(tTel.getText().equals("17856969"));
		WebElement tNom = driver.findElement(By.id("tab-scoped-1")).findElement(By.tagName("tbody")).findElements(By.tagName("td")).get(0);
		Assert.assertFalse(tNom.getText().equals("papa" + " " + "nata"));
	}
	
	@Test(groups = "Sales") 
	public void TS76140_Alta_Cuenta_Business_Validar_nombres_de_los_campos() {
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada("", "generica", "", "", "");
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep (3000);	
		WebElement asdf = driver.findElement(By.id("tab-scoped-3")).findElement(By.tagName("tbody")).findElements(By.tagName("td")).get(0);
		System.out.println(asdf.getText());
		List <WebElement> nc = driver.findElements(By.cssSelector(".slds-button.slds-button.slds-button--icon"));
		for (WebElement x : nc) {
			if (x.getText().toLowerCase().contains("nueva cuenta")) {
				x.click();
				break;
			}
		}
		sleep(7000);
		Assert.assertTrue(driver.findElement(By.id("ContactName")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("Owner")).isDisplayed());
	}
	
	@Test(groups = "Sales")
	public void TS76115_Alta_Contacto_Creacion_Verificar_alta_de_contacto_con_cuenta_generica() {
		SalesBase SB = new SalesBase(driver);
		SB.BtnCrearNuevoCliente();
		ContactSearch contact = new ContactSearch(driver);
		contact.sex("masculino");
		driver.findElement(By.id("FirstName")).sendKeys("Cuenta");
		driver.findElement(By.id("LastName")).sendKeys("Generica");
		driver.findElement(By.id("Birthdate")).sendKeys("15/07/1980");
		sleep(7000);
		driver.get("https://crm--sit.cs14.my.salesforce.com/home/home.jsp");
		driver.findElement(By.xpath("//a[@href=\'https://crm--sit--c.cs14.visual.force.com/apex/taClientSearch']")).click();
		SB.BuscarAvanzada("cuenta", "generica", "", "", "");
		List<WebElement> cont = driver.findElements(By.cssSelector(".slds-button.slds-button.slds-button--icon"));
		for (WebElement x : cont) {
			if (x.getText().equals("Ver Contacto")) {
				x.click();
				break;
			}
		}
		sleep(7000);
		WebElement element = driver.findElement(By.className("textBlock"));
		Assert.assertTrue(element.getText().contains("Cuenta Generica"));
	}
	 
	 	@Test(groups = "Sales") 
	  public void TS76116_Alta_Contacto_Creacion_Verificar_busqueda_modificacion_de_contacto(){
	 		SalesBase SB = new SalesBase(driver);
	 		 SB.BuscarCuenta(DNI, "1212125");
	 	   // SB.crearnuevocliente("contacto", "modifica", nacimiento);
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
	 	Assert.assertTrue(modi.getText().contains("Modificaci�n de contacto"));
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
	  public void TS76138_Alta_Contacto_Creacion_Verificar_descripcion_al_dejar_mail_vacio(){
	 	SalesBase SB = new SalesBase(driver);
	 	BasePage dni = new BasePage(driver);
	 	SB.BtnCrearNuevoCliente();
	 	/*dni.setSimpleDropdown(driver.findElement(By.id("DocumentTypeSearch")),"DNI");
		driver.findElement(By.id("DocumentInputSearch")).click();
		driver.findElement(By.id("DocumentInputSearch")).sendKeys("1235591");
		List<WebElement> gen = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
		for (WebElement g : gen) {
			if (g.getText().equals("Masculino")) {
				g.click();
			}
		}
		WebElement mail = driver.findElements(By.cssSelector(".slds-form-element__control.slds-input-has-icon.slds-input-has-icon--right")).get(2).findElement(By.tagName("label"));
		WebElement in = driver.findElements(By.cssSelector(".slds-form-element__control.slds-input-has-icon.slds-input-has-icon--right")).get(2).findElement(By.tagName("input"));
		sleep(5000);
		Assert.assertTrue(mail.getText().toLowerCase().equals("e-mail"));
		Assert.assertTrue(in.getAttribute("value").isEmpty());
	}
	 	
	@Test(groups = "Sales")
	public void TS76116_Alta_Contacto_Creacion_Verificar_creacion_de_cliente() {
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta(DNI, "1111111");
		SB.acciondecontacto("nueva cuenta");
		sleep(5000);
		List<WebElement> dat = driver.findElements(By.cssSelector(".slds-page-header__title.vlc-slds-page-header__title.slds-truncate.ng-binding"));
		boolean as = false;
		for (WebElement d : dat) {
			if (d.getText().toLowerCase().contains("datos de la cuenta")) {
				as = true;
			}
		}
		Assert.assertTrue(as);
	}
	 	
	@Test(groups = "Sales")
	public void TS76117_Alta_Contacto_Creacion_Verificar_creacion_de_cliente() {
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta(DNI, "1111111");
		SB.acciondecontacto("nueva cuenta");
		WebElement telalt = driver.findElement(By.id("AlternativePhone"));
		sleep(3000);
		Assert.assertTrue(telalt.isDisplayed());
		List<WebElement> sdf = driver.findElements(By.cssSelector(".slds-form-element__control"));
		for (WebElement s : sdf) {
			if (s.getText().contains("El cliente quiere ser contactado por")) {
				Assert.assertTrue(s.isDisplayed());
			}
		}
	}
}