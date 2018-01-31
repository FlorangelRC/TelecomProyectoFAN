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
import org.openqa.selenium.support.ui.Select;
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
	
	//@AfterClass(alwaysRun=true)
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	
	//@AfterMethod(alwaysRun=true)
	public void deslogin(){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.get("https://crm--sit.cs14.my.salesforce.com/home/home.jsp?tsid=02u41000000QWha/");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	

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

	@Test(groups={"Sales", "AltaDeContacto", "Ola1"}) 
	public void TS94544_createdNewValidContact(){
		SalesBase SB = new SalesBase(driver);
		SB.BtnCrearNuevoCliente();
		String asd = driver.findElement(By.id("SearchClientDocumentNumber")).getAttribute("value");
		CustomerCare CC = new CustomerCare(driver);
		ContactSearch contact = new ContactSearch(driver);
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("FirstName")).sendKeys("yy");
		driver.findElement(By.id("LastName")).sendKeys("z");
		driver.findElement(By.id("Birthdate")).sendKeys("28/12/1999");
		contact.sex("masculino");
		driver.findElement(By.id("Contact_nextBtn")).click();
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.get("https://crm--sit--c.cs14.visual.force.com/apex/taClientSearch#/");
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		contact.searchContact(DNI, asd, "");
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep(5000);
		WebElement coldni = driver.findElement(By.id("tab-scoped-3")).findElement(By.tagName("section")).findElement(By.tagName("div")).findElement(By.tagName("table")).findElement(By.tagName("tbody")).findElements(By.tagName("td")).get(2);
		//System.out.println(coldni.getText());
		//System.out.println(asd);
		Assert.assertTrue(coldni.getText().equals(asd));	
		}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94570_Verificar_que_el_campo_Numero_de_documento_no_tenga_menos_de_7_digitos()	{
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
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94542_Seleccionar_Femenino_en_campo_Genero(){
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
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94543_Seleccionar_Masculino_en_campo_Genero(){
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
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94569_Verificar_que_el_campo_Numero_de_documento_no_tenga_mas_de_8_digitos(){
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
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"}) //no es obligatorio
	public void TS94572_Verificar_que_el_campo_tipo_de_documento_sea_obligatorio(){
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
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94552_Verificar_campo_Genero_obligatorio(){
		SalesBase SB = new SalesBase(driver);
		SB.BtnCrearNuevoCliente();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("FirstName")).sendKeys("a");
		driver.findElement(By.id("LastName")).sendKeys("b");
		driver.findElement(By.id("Birthdate")).sendKeys("28/12/1999");
		WebElement gen = driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-radio-control.ng-pristine.ng-scope.ng-invalid.ng-invalid-required")).findElement(By.id("Gender|0"));
		Assert.assertTrue(gen.isEnabled());
		
			}
	
	@Test(groups={"Sales", "AltaDeContacto", "Ola1"}) //no es obligatorio
	public void TS94571_Verificar_que_el_campo_Numero_de_Documento_sea_obligatorio(){
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
	
	@Test(groups={"Sales", "AltaDeContacto", "Ola1"})
	public void TS94539_Ingresar_mas_de_9_caracteres_en_el_campo_Pasaporte(){
		boolean a = false;
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("Pasaporte", "1234568709", "");
		List <WebElement> error = driver.findElements(By.cssSelector(".description.ng-binding"));
		for(WebElement e: error){
			if(e.getText().toLowerCase().equals("longitud m\u00e1xima de 8")){
				a=true;
				break;}}
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(a);	
	
	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94540_Ingresar_menos_de_9_caracteres_en_el_pasaporte(){
		boolean a = false;
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("Pasaporte", "59801234", "");
		List <WebElement> error = driver.findElements(By.cssSelector(".description.ng-binding"));
		for(WebElement e: error){
			if(e.getText().toLowerCase().equals("longitud m\u00ednima de 7")){
				a=true;
				break;}}
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(a);	
	
	}
	
	 @Test(groups={"Sales", "AltaDeContacto","Ola1"})   //verify
	public void TS94541_Ingresar_pasaporte_en_el_campo_Numero_de_Documento(){
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
	
	@Test(groups={"Sales", "AltaDeContacto", "Ola1"}) //verify 
	public void TS94537_Ingresar_DNI_en_el_campo_Numero_de_Documento(){
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
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94536_Numero_De_Cuit_Con_Guiones(){
		String CUIT = "22-35689987-4";
		boolean esta = false;
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta("CUIT", CUIT);
		assertTrue(driver.findElement(By.id("alert-container")).isDisplayed());
		
	}
	
					// ACA
	@Test(groups={"Sales", "AltaDeContacto", "Ola1"})
	public void TS94556_Verificar_el_ingreso_de_caracteres_alfanumericos_en_Pasaporte(){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		boolean as = false;
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("Pasaporte", "24asw", "");
		WebElement num = driver.findElement(By.id("SearchClientDocumentNumber"));
		List<WebElement> er = driver.findElements(By.cssSelector(".error.ng-scope"));
		for(WebElement e : er){
			if( e.getText().toLowerCase().equals("longitud m\u00ednima de 7 m\u00ednimo 7 caracteres y m\u00e1ximo 8 y el primer d\u00edgito no debe ser 0.")){
				e.isDisplayed();
			as=true;}
		}
		Assert.assertTrue(as);
	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})  // ALFANUMERICO
	public void TS94558_Verificar_error_al_ingresar_CUIT_con_cero_al_inicio(){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		boolean as = false;
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("CUIT", "24asw", "");
		WebElement num = driver.findElement(By.id("SearchClientDocumentNumber"));
		List<WebElement> er = driver.findElements(By.cssSelector(".error.ng-scope"));
		for(WebElement e : er){
			e.getText().toLowerCase().equals("longitud m\u00ednima de 7 m\u00ednimo 7 caracteres y m\u00e1ximo 8 y el primer d\u00edgito no debe ser 0.");
		as=true;
		}
		Assert.assertTrue(as);
	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94557_Verificar_error_al_ingresar_caracteres_alfanumericos_en_el_CUIT(){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		boolean as = false;
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("CUIT", "24asw", "");
		WebElement num = driver.findElement(By.id("SearchClientDocumentNumber"));
		List<WebElement> er = driver.findElements(By.cssSelector(".error.ng-scope"));
		for(WebElement e : er){
			e.getText().toLowerCase().equals("longitud m\u00ednima de 7 m\u00ednimo 7 caracteres y m\u00e1ximo 8 y el primer d\u00edgito no debe ser 0.");
		as=true;
		}
		Assert.assertTrue(as);
	}
	
	@Test(groups={"Sales", "AltaDeContacto", "Ola1"})
	public void TS94550_Verificar_campo_CUIT_obligatorio(){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("CUIT", "", "");
		WebElement num = driver.findElement(By.id("SearchClientDocumentNumber"));
		Assert.assertTrue(num.getAttribute("ng-required").equals("required"));

	
}
	
	/// ACA  
	
	
	
	@Test(groups={"Sales", "AltaDeContacto", "Ola1"})
	public void TS94526_Completar_los_campos_luego_de_una_busqueda_de_contacto_inexistente()	{
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, "1234657", "");
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep(4000);
		List <WebElement> cc = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for (WebElement x : cc) {
			if (x.getText().toLowerCase().contains("+ crear nuevo cliente")) {
				x.click();
				break;
			}
		}
		sleep(5000);
		ContactInformation page = new ContactInformation(driver);
		page.setContactInformation(nombre, apellido, nacimiento);
		driver.findElement(By.id("EmailSelectableItems")).findElement(By.tagName("input")).sendKeys("unmailto@gmail.com");
		assertTrue(driver.findElement(By.id("EmailSelectableItems")).findElement(By.tagName("input")).getAttribute("value").equals("unmailto@gmail.com"));
		assertTrue(driver.findElement(By.id("FirstName")).getAttribute("value").equals(nombre));
		assertTrue(driver.findElement(By.id("LastName")).getAttribute("value").equals(apellido));
		assertTrue(driver.findElement(By.id("Birthdate")).getAttribute("value").equals(nacimiento));
		
		
	}
	
	@Test(groups={"Sales", "AltaDeContacto", "Ola1"})
	public void TS94588_Seleccionar_opcion_de_validacion_de_identidad(){//la validacion de identidad no esta mas
		SalesBase SB = new SalesBase(driver);
		BasePage Bp= new BasePage();
		Random aleatorio = new Random(System.currentTimeMillis());
		aleatorio.setSeed(System.currentTimeMillis());
		int intAletorio = aleatorio.nextInt(8999999)+1000000;
		Bp.setSimpleDropdown(driver.findElement(By.id("SearchClientDocumentType")),"DNI");
		driver.findElement(By.id("SearchClientDocumentNumber")).sendKeys(Integer.toString(intAletorio));
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep(5000);
		List <WebElement> cc = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for (WebElement x : cc) {
			if (x.getText().toLowerCase().contains("+ crear nuevo cliente")) {
				x.click();
				break;
			}
		}
		sleep(5000);
		driver.findElement(By.cssSelector(".slds-radio--faux.ng-scope")).click();
		
		ContactInformation page = new ContactInformation(driver);
		page.setContactInformation(nombre, apellido, nacimiento);
		driver.findElement(By.id("Contact_nextBtn")).click();
		sleep(5000);
		SB.agregarplan(plan);
		SB.continuar();
		SB.elegirvalidacion("DOC");
		
	}
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94534_Ingresar_caracteres_numericos_en_campo_Apellido(){
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, "1234657", "");
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep(4000);
		List <WebElement> cc = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for (WebElement x : cc) {
			if (x.getText().toLowerCase().contains("+ crear nuevo cliente")) {
				x.click();
				break;
			}
		}
		sleep(5000);
		driver.findElement(By.id("LastName")).sendKeys("123");
		assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-invalid.ng-valid-minlength.ng-valid-maxlength.ng-dirty.ng-valid-parse.ng-invalid-pattern.ng-valid-required")).isDisplayed());
	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94535_Ingresar_caracteres_numericos_en_campo_Nombre()
	{	
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, "1234657", "");
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep(4000);
		List <WebElement> cc = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for (WebElement x : cc) {
			if (x.getText().toLowerCase().contains("+ crear nuevo cliente")) {
				x.click();
				break;
			}
		}
		sleep(5000);
		driver.findElement(By.id("FirstName")).sendKeys("123");
		assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-invalid.ng-valid-minlength.ng-valid-maxlength.ng-dirty.ng-valid-parse.ng-invalid-pattern.ng-valid-required")).isDisplayed());
	}
	
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94560_Verificar_Fecha_de_Nacimiento_con_ingreso_manual() {
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, "1234675", "");
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep(4000);
		List <WebElement> cc = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for (WebElement x : cc) {
			if (x.getText().toLowerCase().contains("+ crear nuevo cliente")) {
				x.click();
				break;
			}
		}
		sleep(6000);
		driver.findElement(By.id("Birthdate")).sendKeys(nacimiento);
		assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-dirty.ng-valid-parse.ng-valid-required.ng-valid.ng-valid-valid")).isDisplayed());
	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94561_Fecha_de_Nacimiento_con_ingreso_manual_Anio_con_letras_o_mas_de_5_digitos(){
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, "1234657", "");
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep(4000);
		List <WebElement> cc = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for (WebElement x : cc) {
			if (x.getText().toLowerCase().contains("+ crear nuevo cliente")) {
				x.click();
				break;
			}
		}
		sleep(5000);		
		driver.findElement(By.id("Birthdate")).sendKeys("11/04/19894");
		assertTrue(driver.findElement(By.id("Birthdate")).getAttribute("value").equals("11/04/1989"));
		sleep(1000);
		driver.findElement(By.id("Birthdate")).clear();
		driver.findElement(By.id("Birthdate")).sendKeys("11/04/198p");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-dirty.ng-valid-parse.ng-valid-required.ng-invalid.ng-invalid-valid")).isDisplayed());

	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94562_Verificar_Fecha_de_Nacimiento_con_ingreso_manual_Dia_Fuera_de_rango(){
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, "1234657", "");
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep(4000);
		List <WebElement> cc = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for (WebElement x : cc) {
			if (x.getText().toLowerCase().contains("+ crear nuevo cliente")) {
				x.click();
				break;
			}
		}
		sleep(5000);	
		driver.findElement(By.id("Birthdate")).sendKeys("32/08/1999");
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-dirty.ng-valid-parse.ng-valid-required.ng-invalid.ng-invalid-valid")).isDisplayed());
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("Birthdate")).clear();
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("Birthdate")).sendKeys("00/08/1999");
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-dirty.ng-valid-parse.ng-valid-required.ng-invalid.ng-invalid-valid")).isDisplayed());

	}
	

	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94563_Verificar_Fecha_de_Nacimiento_con_ingreso_manual_Mes_Fuera_de_rango() 
	{
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, "1234657", "");
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep(4000);
		List <WebElement> cc = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for (WebElement x : cc) {
			if (x.getText().toLowerCase().contains("+ crear nuevo cliente")) {
				x.click();
				break;
			}
		}
		sleep(5000);	
		driver.findElement(By.id("Birthdate")).sendKeys("22/13/1999");
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-dirty.ng-valid-parse.ng-valid-required.ng-invalid.ng-invalid-valid")).isDisplayed());
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("Birthdate")).clear();
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("Birthdate")).sendKeys("22/00/1999");
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-dirty.ng-valid-parse.ng-valid-required.ng-invalid.ng-invalid-valid")).isDisplayed());

	}
	
	//@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94575_Verificar_valor_del_check_de_email_por_default() {	
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
	
	//@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94555_Verificar_check_de_no_tener_email() {
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
	

	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94549_Verificar_campo_Apellido_obligatorio(){
		SalesBase SB = new SalesBase(driver);
		SB.BtnCrearNuevoCliente();
		WebElement name = driver.findElement(By.id("LastName"));
		Assert.assertTrue(name.getAttribute("required").equals("true"));
	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94551_Verificar_campo_Fecha_de_Nacimiento_obligatorio(){
		SalesBase SB = new SalesBase(driver);
		SB.BtnCrearNuevoCliente();
		WebElement name = driver.findElement(By.id("Birthdate"));
		Assert.assertTrue(name.getAttribute("required").equals("true"));
	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94553_Verificar_campo_Nombre_obligatorio(){
		SalesBase SB = new SalesBase(driver);
		SB.BtnCrearNuevoCliente();
		WebElement name = driver.findElement(By.id("FirstName"));
		Assert.assertTrue(name.getAttribute("required").equals("true"));
	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94548_calendarBirthDate(){
		SalesBase SB = new SalesBase(driver);
		SB.BtnCrearNuevoCliente();
		driver.findElement(By.id("Birthdate")).click();
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".datepicker.-bottom-left-.-from-bottom-")).isDisplayed());
	}

	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94567_Verificar_mascara_del_campo_Fecha_de_Nacimiento() {
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada("cuenta", "generica", "", "", "");
		List <WebElement> cuenta = driver.findElements(By.cssSelector(".slds-truncate.ng-binding"));
		for (WebElement x : cuenta) {
			if (x.getText().toLowerCase().contains("cuenta generica")) {
				x.click();
				break;
			}
		}
		sleep(7000);
		WebElement date = driver.findElement(By.id("Birthdate"));
		Assert.assertTrue(date.getAttribute("vlc-slds-model-date-format").equals("yyyy-MM-dd"));
	}	
	
	//************FASE 2*********************
	
	@Test(groups="Sales") //Errores en los pasos siguientes a la confirmacion del carrito
	public void TS14278_Ventas_NumeroOrden_Verificar_Orden_de_Venta_Abierta_ICCID(){
		Assert.assertTrue(false);
	}
	
	@Test(groups="Sales") //Errores en los pasos siguientes a la confirmacion del carrito	
	public void TS14277_Ventas_NumeroOrden_Verificar_Orden_de_Venta_Abierta_Medio_de_Pago(){
		Assert.assertTrue(false);	
	}
	
	@Test(groups="Sales") //Errores en los pasos siguientes a la confirmacion del carrito
	public void TS14275_Ventas_NumeroOrden_Verificar_Orden_de_Venta_Abierta_Modo_de_Entrega(){
		Assert.assertTrue(false);	
	}
	
	@Test(groups="Sales") //Errores en los pasos siguientes a la confirmacion del carrito
	public void TS14272_Ventas_NumeroOrden_Verificar_Orden_de_Venta_Abierta_Nueva_Venta(){
		Assert.assertTrue(false);	
	}
	
	@Test(groups="Sales") //Errores en los pasos siguientes a la confirmacion del carrito
	public void TS14274_Ventas_NumeroOrden_Verificar_Orden_de_Venta_Abierta_Seleccion_de_Linea(){		
		Assert.assertTrue(false);
	}
	
	@Test(groups="Sales") //Errores en los pasos siguientes a la confirmacion del carrito
	public void TS14273_Ventas_NumeroOrden_Verificar_Orden_de_Venta_Abierta_Seleccionar_un_producto(){
		Assert.assertTrue(false);
	}
	
	@Test(groups="Sales") //Errores en los pasos siguientes a la confirmacion del carrito
	public void TS14276_Ventas_NumeroOrden_Verificar_Orden_de_Venta_Abierta_Simulacion_de_Factura(){
		Assert.assertTrue(false);
	}
	
	@Test(groups="Sales") //Errores en los pasos siguientes a la confirmacion del carrito
	public void TS14279_Ventas_NumeroOrden_Verificar_Orden_de_Venta_Abierta_Simulacion_de_Factura_Final(){
		Assert.assertTrue(false);	
	}
	
	@Test(groups="Sales")
	public void TS14270_Ventas_NumeroOrden_Visualizar_Orden_de_Venta_Abierta_ICCID(){
		Assert.assertTrue(false);
	}
	
	@Test(groups="Sales") //Errores en los pasos siguientes a la confirmacion del carrito
	public void TS14269_Ventas_NumeroOrden_Visualizar_Orden_de_Venta_Abierta_Medio_de_Pago(){
		Assert.assertTrue(false);		
	}
	
	@Test(groups="Sales") //Errores en los pasos siguientes a la confirmacion del carrito
	public void TS14267_Ventas_NumeroOrden_Visualizar_Orden_de_Venta_Abierta_Modo_de_Entrega(){
		Assert.assertTrue(false);
	}
	
	@Test(groups="Sales") //Errores en los pasos siguientes a la confirmacion del carrito
	public void TS14264_Ventas_NumeroOrden_Visualizar_Orden_de_Venta_Abierta_Nueva_Venta(){
		Assert.assertTrue(false);
	}
	
	@Test(groups="Sales") //Errores en los pasos siguientes a la confirmacion del carrito
	public void TS14266_Ventas_NumeroOrden_Visualizar_Orden_de_Venta_Abierta_Seleccion_de_Linea(){
		Assert.assertTrue(false);
	}
	
	@Test(groups="Sales") //Errores en los pasos siguientes a la confirmacion del carrito
	public void TS14265_Ventas_NumeroOrden_Visualizar_Orden_de_Venta_Abierta_Seleccionar_un_producto(){
		Assert.assertTrue(false);
	}
	
	@Test(groups="Sales") //Errores en los pasos siguientes a la confirmacion del carrito
	public void TS14268_Ventas_NumeroOrden_Visualizar_Orden_de_Venta_Abierta_Simulacion_de_Factura(){
		Assert.assertTrue(false);
	}
	
	//************FASE 3*********************

	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94887_Alta_Contacto_Busqueda_Verificar_resultado_busqueda_cliente_activo_inactivo() {
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta("DNI", "");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement act = driver.findElement(By.id("tab-scoped-1__item"));
		Assert.assertTrue(act.getText().equals("Clientes Activos"));
		WebElement ina = driver.findElement(By.id("tab-scoped-2__item"));
		Assert.assertTrue(ina.getText().equals("Cliente Inactivos"));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();
		}
	}
	
	@Test(groups={"Sales", "Perfiles","Ola1"})
	public void TS94872_Perfiles_Verificar_creacion_de_perfil_Canal_Tefonico(){
		SalesBase SB = new SalesBase(driver);
		SB.gestiondeusuarios();
		SB.validarperfil("Medina, Elena", "CC Venta y Atencion a Clientes");			
	}
	
	@Test(groups={"Sales", "Perfiles","Ola1"})
	public void TS94873_Perfiles_Verificar_creacion_de_perfil_Oficina_Comercial(){
		SalesBase SB = new SalesBase(driver);
		SB.gestiondeusuarios();
		SB.validarperfil("Sit, Francisco", "TA - OFCOM Venta y Atencion a Clientes");		
	}
	
	@Test(groups={"Sales", "Perfiles","Ola1"})
	public void TS94874_Perfiles_Verificar_creacion_de_perfil_Oficina_Agente(){
		SalesBase SB = new SalesBase(driver);
		SB.gestiondeusuarios();
		SB.validarperfil("Borbon, Andres", "Agente Venta y Atencion a Clientes");			
	}
	
	@Test(groups={"Sales", "Perfiles","Ola1"})
	public void TS94875_Perfiles_Verificar_creacion_de_perfil_Oficina_Logistica(){
		SalesBase SB = new SalesBase(driver);
		SB.gestiondeusuarios();
		driver.findElements(By.className("listItemPad")).get(13).click();
		sleep(4000);
		SB.validarperfil("Sit, Nicolas", "Logistica B");
			perfil="agente";			
	}

	@Test(groups={"Sales", "AltaCuenta","Ola1"})
	public void TS94882_Alta_Cuenta_Busqueda_Verificar_nombre_de_la_busqueda() {
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Boolean f = false;
		List<WebElement> gst = driver.findElements(By.cssSelector(".slds-page-header__title.vlc-slds-page-header__title.slds-truncate.ng-binding"));
		for (WebElement e : gst) {
			if (e.getText().equals("Gesti\u00f3n de clientes")) {
				f = true;
			}
		}
		Assert.assertTrue(f);
	}
				
	@Test(groups={"Sales", "AltaCuenta","Ola1"})
	public void TS94883_Alta_Cuenta_Busqueda_Verificar_secciones_de_la_busqueda_de_cliente() {
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement tipodni = driver.findElement(By.id("SearchClientDocumentType"));
		WebElement numdoc = driver.findElement(By.id("SearchClientDocumentNumber"));
		WebElement linea = driver.findElement(By.id("PhoneNumber"));
		Assert.assertTrue(tipodni.isEnabled());
		Assert.assertTrue(numdoc.isEnabled());
		Assert.assertTrue(linea.isEnabled());
		Boolean f = false;
		List<WebElement> busqadv = driver.findElements(By.cssSelector(".slds-form-element__label.slds-clearfix.ng-scope"));
		for (WebElement e : busqadv) {
			if (e.getText().equals("B\u00fasqueda avanzada")) {
				f = true;
			}
		}
		Assert.assertTrue(f);
	}
	
	@Test(groups={"Sales", "AltaCuenta","Ola1"})
	public void TS94884_Alta_Cuenta_Busqueda_Verificar_campos_de_la_busqueda_avanzada(){
		SalesBase SB = new SalesBase(driver);
		SB.BusquedaAvanzada();
		SB.validarcamposbusqueda();
	}
	
	@Test(groups={"Sales", "AltaCuenta","Ola1"})
	public void TS94885_Alta_Cuenta_Busqueda_Verificar_Nombre_y_Apellido_separado(){
		SalesBase SB = new SalesBase(driver);
		SB.BusquedaAvanzada();
		SB.BuscarAvanzada("pepeasd","argentoasd", "", "", "");
		SB.validarespacio();
	}
	
	@Test(groups={"Sales", "Ventas","Ola1"})
	public void TS94889_Ventas_General_Verificar_visualizacion_de_boton_Continuar(){
		SalesBase SB= new SalesBase(driver);
		SB.BuscarCuenta("DNI", "");;
		SB.acciondecontacto("catalogo");
		SB.agregarproductos();
		Assert.assertTrue(SB.validartxtbtn("Continuar"));
	}
	
	@Test(groups={"Sales", "Ventas","Ola1"})
	public void TS94912_Ventas_General_Verificar_que_se_muestre_el_estado_de_una_OV(){
		SalesBase SB= new SalesBase(driver);
		SB.BuscarCuenta("DNI", "");
		SB.acciondecontacto("catalogo");
		SB.agregarproductos();
		SB.continuar();
		SB.validarpasos();			
	}
	
	@Test(groups= {"Fase2", "Sales"})
	public void TS7005_mandatoryTaxCondition()
	{
		BasePage page = new BasePage();
		SalesBase SB= new SalesBase(driver);
		SB.BuscarCuenta("DNI", "65987659");
		List <WebElement> cc = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for (WebElement x : cc) {
			if (x.getText().toLowerCase().contains("+ crear nuevo cliente")) {
				x.click();
				break;
			}
		}
		sleep(5000);
		SB.acciondecontacto("nueva cuenta");
		sleep(8000);
		Select listSelect = new Select (driver.findElement(By.id("ImpositiveCondition")));
		String[] todos = {"iva consumidor final","iva monotributista"};
		List<WebElement> motivos = listSelect.getOptions();
	    assertTrue(verificarContenidoLista(todos,motivos));
		
	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94958_Alta_Contacto_Busqueda_Verificar_que_se_recuerden_los_datos_de_busqueda(){
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
	
	@Test(groups={"Sales", "AltaCuenta","Ola1"})
	public void TS94967_Alta_Cuenta_Busqueda_Verificar_accion_boton_1(){
		SalesBase SB= new SalesBase(driver);
		SB.BuscarCuenta("DNI", "");;
		SB.acciondecontacto("catalogo");
		SB.validarentrarcatalogo();
	}
	
	@Test(groups={"Sales", "AltaCuenta","Ola1"})
	public void TS94965_Alta_Cuenta_Busqueda_Verificar_que_se_agregue_un_nivel_de_agrupamiento(){
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
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94796_Alta_Contacto_Busqueda_Verificar_resultado_busqueda_contacto_Sin_cuenta_asociada() {
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta("DNI", "");
		sleep(5000);
		WebElement cont = driver.findElement(By.id("tab-scoped-3__item"));
		Assert.assertTrue(cont.getText().equals("Contactos"));
	}
		  		 
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94878_Alta_Contacto_Busqueda_Verificar_accion_de_Crear_Cuenta(){
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
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94820_Verificar_que_se_ejecuten_los_procesos_de_validacion() {
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

	@Test(groups={"Sales", "Ventas","Ola1"})
	public void TS94975_Verificar_que_se_bonifique_el_costo_de_SIM_en_PlanPrepago() {
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
		Assert.assertTrue(a);
		Assert.assertTrue(precio.get(3).getText().contains("0,00"));
	}
	
//	============================ Fase 4 ============================= 	
	
	@Test(groups={"Sales", "AltaCuenta","Ola1"}) 
	public void TS95279_Alta_Cuenta_Consumer_Valida_alta_mayor_o_igual_16anios() {
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
	
	@Test(groups={"Sales", "AltaCuenta","Ola1"})
	public void TS95199_Alta_Cuenta_Busqueda_Verificar_DNI_inexistente_y_creacion_de_contacto() {
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

	@Test(groups={"Sales", "AltaCuenta","Ola1"})
	public void TS95197_Alta_Cuenta_Busqueda_Verificar_busqueda_combinada_DNI_con_NyAp_DNI_Existe_NyAP_No_Existe() {
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
	
	@Test(groups={"Sales", "AltaCuenta","Ola1"}) 
	public void TS95205_Alta_Cuenta_Business_Validar_nombres_de_los_campos() {
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
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS95184_Alta_Contacto_Creacion_Verificar_alta_de_contacto_con_cuenta_generica() {
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
	 	 	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	  public void TS95203_Alta_Contacto_Creacion_Verificar_descripcion_al_dejar_mail_vacio(){
	 	SalesBase SB = new SalesBase(driver);
	 	SB.BtnCrearNuevoCliente();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
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
	public void TS76117_Alta_Contacto_Creacion_Verificar_creacion_de_cliente() {
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
	 	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS95186_Alta_Contacto_Creacion_Verificar_creacion_de_cliente() {
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta(DNI, "1111111");
		SB.acciondecontacto("nueva cuenta");
		sleep(5000);
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
	
	@Test(groups = "Sales")
	 public void TS76153_Blacklist_Validacion_de_cliente_en_blacklist_Cliente_DNI(){
	  SalesBase SB = new SalesBase(driver);
	  Boolean t = false;
	  SB.BuscarCuenta(DNI, "28672141");
	  SB.acciondecontacto("catalogo");
	  List <WebElement> pp = driver.findElements(By.cssSelector(".slds-radio--faux"));
	  for(WebElement p : pp){
	   if (p.getText().toLowerCase().equals("products")){
	   p.isDisplayed();
	   t=true;
	   }
	  }
	 Assert.assertTrue(t);  
	 }
	 @Test (groups = "Sales") 
	  public void TS94734_Alta_de_Contacto_Persona_Fisica_Verificar_seleccion_de_localidad_existente(){ 
	    SalesBase SB = new SalesBase(driver); 
	    SB.BuscarCuenta(DNI, "11111111"); 
	    SB.acciondecontacto("nueva cuenta"); 
	    boolean h = false; 
	    sleep(5000); 
	    Select regio = new Select (driver.findElement(By.id("State"))); 
	    regio.selectByVisibleText("Buenos Aires");   
	    driver.findElement(By.id("CityTypeAhead")).sendKeys("a"); 
	    List<WebElement> est = driver.findElements(By.cssSelector(".slds-input.ng-scope.ng-valid-minlength.ng-valid-maxlength.ng-valid.ng-valid-required.ng-not-empty.ng-dirty.ng-animate.ng-touched-add.ng-untouched-remove.ng-touched.ng-touched-add-active.ng-untouched-remove-active")); 
	    sleep(5000); 
	    List<WebElement> loc = driver.findElements(By.cssSelector(".typeahead.dropdown-menu.ng-scope.am-fade.bottom-left")); 
	    for(WebElement l : loc){ 
	      if(l.getText().toLowerCase().contains("abasto")){ 
	      h=true; 
	      } 
	    } 
	    Assert.assertTrue(h); 
	   } 
	  @Test (groups = "Sales") 
	  public void TS94737_Alta_de_Contacto_Persona_Fisica_Verificar_seleccion_de_calle_existente(){ 
	    SalesBase SB = new SalesBase(driver); 
	    SB.BuscarCuenta(DNI, "11111111"); 
	    SB.acciondecontacto("nueva cuenta"); 
	    boolean h = false; 
	    sleep(5000); 
	    Select regio = new Select (driver.findElement(By.id("State"))); 
	    regio.selectByVisibleText("Buenos Aires");   
	    driver.findElement(By.id("CityTypeAhead")).sendKeys("VILLA LUZURIAGA"); 
	    sleep(5000);   
	    driver.findElement(By.cssSelector(".typeahead.dropdown-menu.ng-scope.am-fade.bottom-left")).click(); 
	        sleep(5000); 
	    List<WebElement> cal = driver.findElements(By.id("LegalStreetTypeAhead")); 
	    for(WebElement c : cal){ 
	      if(c.getText().toLowerCase().contains("atenas")){ 
	      h=true; 
	      } 
	    } 
	    Assert.assertTrue(h); 
	   } 
	  @Test (groups = "sales") 
	  public void TS94735_Alta_de_Contacto_Persona_Fisica_Verificar_ingreso_manual_de_localidad_inexistente(){ 
	    SalesBase SB = new SalesBase(driver); 
	    SB.BuscarCuenta(DNI, "11111111"); 
	    SB.acciondecontacto("nueva cuenta"); 
	    sleep(5000); 
	    Select regio = new Select (driver.findElement(By.id("State"))); 
	    regio.selectByVisibleText("Buenos Aires");   
	    WebElement loc = driver.findElement(By.id("CityTypeAhead")); 
	    loc.sendKeys("VILLA LUZURIAGA"); 
	    sleep(5000);   
	    driver.findElement(By.cssSelector(".typeahead.dropdown-menu.ng-scope.am-fade.bottom-left")).click(); 
	    Assert.assertTrue(loc.getAttribute("value").equals("VILLA LUZURIAGA")); 
	  } 
	  @Test(groups = "sales") 
	  public void TS94739_Alta_de_Contacto_Persona_Fisica_Verificar_ingreso_manual_de_cod_postal_inexistente(){ 
	    SalesBase SB = new SalesBase(driver); 
	    SB.BuscarCuenta(DNI, "11111111"); 
	    SB.acciondecontacto("nueva cuenta"); 
	    sleep(5000); 
	    Select regio = new Select (driver.findElement(By.id("State"))); 
	    regio.selectByVisibleText("Buenos Aires");   
	    WebElement loc = driver.findElement(By.id("CityTypeAhead")); 
	    loc.sendKeys("VILLA LUZURIAGA"); 
	    sleep(5000);   
	    driver.findElement(By.cssSelector(".typeahead.dropdown-menu.ng-scope.am-fade.bottom-left")).click(); 
	      driver.findElement(By.id("PostalCodeTypeAhead")).sendKeys("1"); 
	    sleep(4000); 
	    driver.findElement(By.cssSelector(".typeahead.dropdown-menu.ng-scope.am-fade.bottom-left")).click(); 
	    WebElement cod = driver.findElement(By.id("NewPostalCodeName")); 
	    cod.sendKeys("1765"); 
	    Assert.assertTrue(cod.getAttribute("value").equals("1765")); 
	  } 
	  @Test(groups = "sales") 
	  public void TS94736_Alta_de_Contacto_Persona_Fisica_Verificar_ingreso_manual_de_calle_inexistente(){ 
	    SalesBase SB = new SalesBase(driver); 
	    SB.BuscarCuenta(DNI, "11111111"); 
	    SB.acciondecontacto("nueva cuenta"); 
	    sleep(5000); 
	    Select regio = new Select (driver.findElement(By.id("State"))); 
	    regio.selectByVisibleText("Buenos Aires");   
	    WebElement loc = driver.findElement(By.id("CityTypeAhead")); 
	    loc.sendKeys("VILLA LUZURIAGA"); 
	    sleep(5000);   
	    driver.findElement(By.cssSelector(".typeahead.dropdown-menu.ng-scope.am-fade.bottom-left")).click(); 
	    driver.findElement(By.id("LegalStreetTypeAhead")).sendKeys("a"); 
	    sleep(5000); 
	    driver.findElement(By.cssSelector(".typeahead.dropdown-menu.ng-scope.am-fade.bottom-left")).click(); 
	    WebElement cal = driver.findElement(By.id("NewStreetName")); 
	    cal.sendKeys("ATENAS"); 
	    Assert.assertTrue(cal.getAttribute("value").equals("ATENAS")); 
	  } 
	  
	  @Test(groups = {"Sales", "Configuracion"}) 
	  public void TS94610_Configuracion_CondicionImpositiva_Verificar_categoria_frente_al_IVA_para_clientes_con_DNI_Pasaporte() {
		  SalesBase SB = new SalesBase(driver); 
		  SB.BuscarCuenta(DNI, "11111111"); 
		  SB.acciondecontacto("nueva cuenta");
		  sleep(7000);
		  driver.findElement(By.id("ImpositiveCondition")).click();
		  sleep(2000);
		  Assert.assertTrue(driver.findElement(By.xpath("//*[text() = 'IVA Consumidor Final']")).isDisplayed());
	  }
	  
	  @Test(groups = {"Sales", "Ventas"})
	  public void TS94709_Ventas_BuscarCliente_Verificar_cliente_activo_por_numero_de_linea() {
		  String tel = "1157602860";
		  driver.findElement(By.id("PhoneNumber")).sendKeys(tel);
		  driver.findElement(By.id("SearchClientsDummy")).click();
		  sleep(5000);
		  WebElement linea = driver.findElement(By.xpath("//*[@id=\"tab-scoped-1\"]/section/div/table/tbody/tr[1]/td[2]"));
		  Assert.assertTrue(linea.getText().equals(tel));		  
	  }
	  
	  @Test(groups = {"Sales", "Ventas"})
	  public void TS94715_Ventas_BuscarCliente_Verificar_linea_sin_cliente_asociado() {
		  driver.findElement(By.id("PhoneNumber")).sendKeys("1157602861");
		  driver.findElement(By.id("SearchClientsDummy")).click();
		  sleep(5000);
		  List <WebElement> cai = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		  if (cai.get(0).isDisplayed() && cai.get(1).isDisplayed()) {
			  Assert.assertTrue(false);
		  }
	  }
	  
	  @Test(groups = {"Sales", "AltaDeContacto"})
	  public void TS94887_Alta_de_Contacto_Busqueda_Verificar_boton_de_Crear_Contacto_en_cualquier_resultado_de_busqueda_con_contacto_inexistente() {
		  SalesBase SB = new SalesBase(driver); 
		  SB.BuscarCuenta(DNI, "78421962");
		  Assert.assertTrue(driver.findElement(By.cssSelector(".OSradioButton.ng-scope.only-buttom")).isDisplayed());
	  }
	  
	  @Test(groups = {"Sales", "AltaDeContacto"})
	  public void TS94945_Alta_de_Contacto_Busqueda_Verificar_boton_1_sobre_contactos() {
		  SalesBase SB = new SalesBase(driver); 
		  SB.BuscarCuenta(DNI, "11111111");
		  List <WebElement> nc = driver.findElements(By.cssSelector(".slds-button.slds-button.slds-button--icon"));
		  boolean a = false;
		  for (WebElement x : nc) {
			  if (x.getText().contains("Nueva Cuenta")) {
				  a = true;
			  }
		  }
		  Assert.assertTrue(a);
	  }
	  
	  @Test(groups = {"Sales", "AltaDeContacto"})
	  public void TS94878_Alta_de_Contacto_Busqueda_Verificar_accion_de_Crear_Cuenta() {
		  SalesBase SB = new SalesBase(driver); 
		  SB.BuscarCuenta(DNI, "11111111"); 
		  SB.acciondecontacto("nueva cuenta");
		  sleep(7000);
		  Assert.assertTrue(driver.findElement(By.id("ContactName")).isDisplayed());
	  }
	  
	  @Test(groups = {"Sales", "AltaDeContacto"})
	  public void TS94896_Alta_Contacto_Verificar_contacto_existente_sin_cuenta_asociada_muestra_datos_de_contacto() {
		  SalesBase SB = new SalesBase(driver); 
		  SB.BuscarCuenta(DNI, "11111111");
		  sleep(7000);
		  List <WebElement> cuenta = driver.findElements(By.cssSelector(".slds-truncate.ng-binding"));
		  for (WebElement x : cuenta) {
			  if (x.getText().toLowerCase().contains("adela sales")) {
				  x.click();
				  break;
			  }
		  }		  
		  sleep(5000);
		  WebElement dni = driver.findElement(By.id("DocumentNumber"));
		  WebElement name = driver.findElement(By.id("FirstName"));
		  WebElement apel = driver.findElement(By.id("LastName"));
		  Assert.assertTrue(dni.getAttribute("value").contains("11111111") && name.getAttribute("value").contains("Adela") && apel.getAttribute("value").contains("Sales"));
	  }
	  
	  @Test(groups = {"Sales", "AltaDeContacto"})
	  public void TS94795_Alta_Contacto_Busqueda_Verificar_Op1_tercer_TAB_de_visualizacion() {
		  SalesBase SB = new SalesBase(driver); 
		  SB.BuscarCuenta(DNI, "74195213");
		  sleep(3000);
		  WebElement msj = driver.findElement(By.id("txtNoRegistryMsg"));
		  Assert.assertTrue(msj.getText().contains("No hay ning\u00fan cliente con este tipo y n\u00famero de documento. Busc\u00e1 con otro dato o cre\u00e1 un nuevo cliente"));
	  }
	  
	  @Test(groups = {"Sales", "AltaDeContacto"})
	  public void TS94799_Alta_Contacto_Busqueda_Verificar_accion_Crear_Nuevo_Contacto() {
		  SalesBase SB = new SalesBase(driver); 
		  SB.BuscarCuenta(DNI, "74195213");
		  sleep(3000);
		  driver.findElement(By.cssSelector(".OSradioButton.ng-scope.only-buttom")).click();
		  sleep(7000);
		  WebElement msj = driver.findElement(By.id("TextBlock1"));
		  Assert.assertTrue(msj.getText().contains("El contacto no existe. Complet\u00e1 los datos para crear un nuevo cliente"));
	  }
	  
	  @Test(groups = {"Sales", "AltaDeContacto"})
	  public void TS94891_Alta_Contacto_Verificar_que_por_defecto_aparezca_DNI() {
		  SalesBase SB = new SalesBase(driver); 
		  SB.BuscarCuenta(DNI, "12587963");
		  sleep(3000);
		  driver.findElement(By.cssSelector(".OSradioButton.ng-scope.only-buttom")).click();
		  sleep(7000);
		  WebElement dni = driver.findElement(By.id("DocumentType"));
		  Assert.assertTrue(dni.getAttribute("value").equals("DNI"));
	  }
	  
	  @Test(groups = {"Sales", "AltaDeContacto"})
	  public void TS94892_Alta_Contacto_Verificar_que_los_datos_del_contacto_nuevo_se_soliciten_en_la_misma_pantalla() {
		  SalesBase SB = new SalesBase(driver); 
		  SB.BuscarCuenta(DNI, "12587967");
		  sleep(3000);
		  driver.findElement(By.cssSelector(".OSradioButton.ng-scope.only-buttom")).click();
		  sleep(7000);
		  Assert.assertTrue(driver.findElement(By.id("FirstName")).isDisplayed());
		  Assert.assertTrue(driver.findElement(By.id("LastName")).isDisplayed());
		  Assert.assertTrue(driver.findElement(By.id("Birthdate")).isDisplayed());
		  Assert.assertTrue(driver.findElement(By.cssSelector(".slds-picklist.slds-dropdown-trigger.slds-dropdown-trigger--click.slds-is-open.slds-col--padded.slds-size--6-of-12.ng-scope")).isDisplayed());
	  }

}