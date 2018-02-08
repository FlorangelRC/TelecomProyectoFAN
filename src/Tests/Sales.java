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
	
	@AfterClass(alwaysRun=true)
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	
	@AfterMethod(alwaysRun=true)
	public void deslogin(){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.get("https://crm--sit.cs14.my.salesforce.com/home/home.jsp?tsid=02u41000000QWha/");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	

	}
	
	@BeforeClass(alwaysRun=true)
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

	@BeforeMethod(alwaysRun=true)
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
		assertTrue(driver.findElement(By.id("Gender")).isSelected());
	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94671_Alta_De_Contacto_Persona_Fisica_Verificar_FechaNac_Futura(){
		SalesBase SB = new SalesBase(driver);
		CustomerCare CC = new CustomerCare(driver);
		SB.BtnCrearNuevoCliente();
		sleep(6000);
		driver.findElement(By.id("Birthdate")).sendKeys("11/01/2020");
		Assert.assertTrue(driver.findElement(By.cssSelector(".message.description.ng-binding.ng-scope")).getText().toLowerCase().contains("fecha de nacimiento inv\u00e1lida"));
		
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
		for(WebElement e: error)
		{	
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
	
	@Test(groups={"Sales", "AltaDeContacto", "Ola1"})
	public void TS94673_Alta_De_Contacto_Persona_Fisica_Verificar_Campo_Numero_De_Documento(){
		boolean a = false;
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		driver.findElement(By.id("SearchClientDocumentNumber")).sendKeys("875321499");
		List <WebElement> error = driver.findElements(By.cssSelector(".description.ng-binding"));
		for(WebElement e: error){
			if(e.getText().toLowerCase().equals("longitud m\u00e1xima de 8")){
				a=true;
				break;}}
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(a);	
	
	}
	
	@Test(groups={"Sales", "AltaDeContacto", "Ola1"})
	public void TS94668_Alta_De_Contacto_Persona_Fisica_Verificar_Eliminacion_Valor_CI(){
		SalesBase SB = new SalesBase(driver);
		sleep(8000);
		SB.BuscarCuenta("Cedula de Identidad", "1487569");
		List <WebElement> cc = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for (WebElement x : cc) {
			if (x.getText().toLowerCase().contains("+ crear nuevo cliente")) {
				x.click();
			}
		}
		try {
			SB.setSimpleDropdown(driver.findElement(By.id("DocumentType")),"Cedula de Identidad");
			assertTrue(false);
		}catch(org.openqa.selenium.ElementNotVisibleException Ex1) {
			assertTrue(true);
		}
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
	
	@Test(groups={"Sales", "Ventas","Ola1"})
	public void TS95146_Ventas_General_Verificar_Creacion_De_Campo_De_Subestados(){
		boolean esta = false;
		driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).click();
		driver.findElement(By.id("alert-ok-button")).click();
		sleep(8000);
		BasePage BP = new BasePage();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(BP.getFrameForElement(driver, By.cssSelector(".slds-table.slds-table--bordered.slds-no-row-hover.slds-table--cell-buffer.slds-max-medium-table--stacked-horizontal")));
		driver.findElement(By.cssSelector(".slds-table.slds-table--bordered.slds-no-row-hover.slds-table--cell-buffer.slds-max-medium-table--stacked-horizontal")).findElement(By.className("slds-truncate")).findElement(By.tagName("a")).click();
		sleep(5000);
		List<WebElement> campos = driver.findElement(By.className("detailList")).findElements(By.className("labelCol"));
		for (WebElement UnC : campos) {
			if (UnC.getText().equalsIgnoreCase("tracking status")) {
				esta = true;
				break;
			}
		}
		assertTrue(esta);
	}
	
	@Test(groups={"Sales", "Ventas","Ola1"})
	public void TS9514_Ventas_General_Verificar_LOV_Campo_Status_En_La_Orden(){
		boolean esta = false;
		String[] todos = {"draft","cancelled","activated"};
		driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).click();
		driver.findElement(By.id("alert-ok-button")).click();
		sleep(8000);
		BasePage BP = new BasePage();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(BP.getFrameForElement(driver, By.cssSelector(".slds-table.slds-table--bordered.slds-no-row-hover.slds-table--cell-buffer.slds-max-medium-table--stacked-horizontal")));
		driver.findElement(By.cssSelector(".slds-table.slds-table--bordered.slds-no-row-hover.slds-table--cell-buffer.slds-max-medium-table--stacked-horizontal")).findElement(By.className("slds-truncate")).findElement(By.tagName("a")).click();
		sleep(5000);
		List<WebElement> campos = driver.findElement(By.className("detailList")).findElements(By.tagName("td"));
		for (WebElement UnC : campos) {
			if(esta == true) {
				Actions action = new Actions(driver);
				action.moveToElement(UnC).doubleClick().build().perform();
				sleep(2000);
			    List<WebElement> motivos = new Select(driver.findElement(By.id("Status"))).getOptions();
			    assertTrue(verificarContenidoLista(todos,motivos));
				break;
			}
			if (UnC.getText().equalsIgnoreCase("estado")) {
				esta = true;
			}
		}
		assertTrue(esta);
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
		contact.searchContact("CUIT", "02458954", "");
		WebElement num = driver.findElement(By.id("SearchClientDocumentNumber"));
		List<WebElement> er = driver.findElements(By.cssSelector(".error.ng-scope"));
		for(WebElement e : er){
			if (e.getText().toLowerCase().equals("longitud m\u00ednima de 7 m\u00ednimo 7 caracteres y m\u00e1ximo 8 y el primer d\u00edgito no debe ser 0."))
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
		System.out.println(num.getAttribute("value"));
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
		sleep(3000);
	Assert.assertFalse(driver.findElement(By.cssSelector(".description.ng-binding")).isDisplayed());
	
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
	
	//******************FASE 3*********************

	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94791_Alta_Contacto_Busqueda_Verificar_resultado_busqueda_cliente_activo_inactivo() {
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta("DNI", "");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement act = driver.findElement(By.id("tab-scoped-1__item"));
		Assert.assertTrue(act.getText().equals("Clientes Activos"));
		WebElement ina = driver.findElement(By.id("tab-scoped-2__item"));
		Assert.assertTrue(ina.getText().equals("Cliente Inactivos"));
	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94877_Alta_Contacto_Busqueda_Verificar_Tabs_Del_Resultado_De_La_Busqueda() {
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta("DNI", "32323232");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> solapas = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		assertTrue(solapas.get(0).findElement(By.tagName("a")).getText().equals("Clientes Activos"));
		assertTrue(solapas.get(1).findElement(By.tagName("a")).getText().equals("Contactos"));
		assertTrue(solapas.get(2).findElement(By.tagName("a")).getText().equals("Clientes Inactivos"));
	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94792_Alta_Contacto_Busqueda_Verificar_Primer_TAB_De_Visualizacion() {
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta("DNI", "34073329");
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> solapas = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		assertTrue(solapas.get(0).findElement(By.tagName("a")).getText().equals("Clientes Activos") && solapas.get(0).isDisplayed());
	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94793_Alta_Contacto_Busqueda_Verificar_segundo_TAB_De_Visualizacion() {
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta("DNI", "");
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> solapas = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		assertTrue(solapas.get(1).findElement(By.tagName("a")).getText().equals("Clientes Inactivos"));
		assertTrue(solapas.get(1).isDisplayed());
	}
	
	@Test(groups={"Sales", "Ventas","Ola1"})
	public void TS94712_Ventas_BuscarCliente_Verificar_Que_El_Sistema_Agrupe_Los_Clientes_Inactivos() {
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta("DNI", "");
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> solapas = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		assertTrue(solapas.get(1).findElement(By.tagName("a")).getText().equals("Clientes Inactivos"));
		assertTrue(solapas.get(1).isDisplayed());
	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94794_Alta_Contacto_Busqueda_Verificar_tercer_TAB_De_Visualizacion() {
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta("DNI", "");
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> solapas = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		assertTrue(solapas.get(2).findElement(By.tagName("a")).getText().equals("Contactos"));
		assertTrue(solapas.get(2).isDisplayed());
	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94568_Alta_Contacto_Persona_Fisica_Verificar_Que_El_Campo_Numero_De_Documento_No_Inicie_Con_0() {
		SalesBase SB = new SalesBase(driver);
		driver.findElement(By.id("SearchClientDocumentNumber")).sendKeys("0145698");
		assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flexng-scope.ng-valid-pattern.ng-valid-maxlength.ng-valid-required.ng-dirty.ng-valid-parse.ng-invalid.ng-invalid-minlength")).isDisplayed());
	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94559_Alta_Contacto_Persona_Fisica_Verificar_Error_Al_Ingresar_Pasaporte_Con_Cero_Al_Inicio() {
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta("Pasaporte", "0145698");
		assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flexng-scope.ng-valid-pattern.ng-valid-maxlength.ng-valid-required.ng-dirty.ng-valid-parse.ng-invalid.ng-invalid-minlength")).isDisplayed());
	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94565_Alta_Contacto_Persona_Fisica_Verificar_LOV_Del_Campo_Tipo_De_Documento() {
		SalesBase SB = new SalesBase(driver);
		String[] todos = {"dni","cuit","pasaporte","libreta civica","libreta de enrolamiento","cedula de identidad"};
		Select listSelect = new Select(driver.findElement(By.id("SearchClientDocumentType")));
		List<WebElement> motivos = listSelect.getOptions();
	    assertTrue(verificarContenidoLista(todos,motivos));
		
	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94876_Alta_Contacto_Busqueda_Verificar_Opciones_Del_Resultado_De_La_Busqueda() {
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta("DNI", "32323232");
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> solapas = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		for (WebElement UnaS : solapas) {
			if (UnaS.findElement(By.tagName("a")).getText().equals("Contactos")) {
				UnaS.findElement(By.tagName("a")).click();
				break;
			}
		}
		sleep(2000);
		List<WebElement> btns = driver.findElement(By.id("tab-scoped-3")).findElements(By.cssSelector(".slds-button.slds-button.slds-button--icon"));
		assertTrue(btns.get(0).getText().equalsIgnoreCase("nueva cuenta")||btns.get(1).getText().equalsIgnoreCase("nueva cuenta")||btns.get(2).getText().equalsIgnoreCase("nueva cuenta"));
		assertTrue(btns.get(0).getText().equalsIgnoreCase("catalogo")||btns.get(1).getText().equalsIgnoreCase("catalogo")||btns.get(2).getText().equalsIgnoreCase("catalogo"));
		assertTrue(btns.get(0).getText().equalsIgnoreCase("ver contacto")||btns.get(1).getText().equalsIgnoreCase("ver contacto")||btns.get(2).getText().equalsIgnoreCase("ver contacto"));
	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94574_Alta_Contacto_Persona_Fisica_Verificar_Sugerencia_De_Dominio_En_Campo_Email() {
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta("DNI", "32323232");
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> solapas = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		for (WebElement UnaS : solapas) {
			if (UnaS.findElement(By.tagName("a")).getText().equals("Contactos")) {
				UnaS.findElement(By.tagName("a")).click();
				break;
			}
		}
		sleep(1000);
		driver.findElement(By.id("tab-scoped-3")).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(0).findElements(By.tagName("td")).get(0).findElement(By.tagName("a")).click();
		sleep(6000);
		WebElement email = driver.findElement(By.id("EmailSelectableItems"));
		email.findElement(By.tagName("input")).clear();
		email.findElement(By.tagName("input")).sendKeys("abc");
		solapas = driver.findElement(By.cssSelector(".slds-dropdown.slds-dropdown--left.slds-dropdown--length-5.suggestion")).findElements(By.cssSelector(".slds-lookup__item-action.slds-lookup__item-action--label"));
		List<WebElement> sugerencias = driver.findElements(By.cssSelector(".slds-dropdown.slds-dropdown--left.slds-dropdown--length-5.suggestion"));
		sugerencias.clear();
		for(WebElement UnaS : solapas) {
			sugerencias.add(UnaS.findElement(By.tagName("span")));
		}
		String[] todos = {"abc@gmail.com","abc@yahoo.com.ar","abc@hotmail.com","abc@yahoo.com","abc@outlook.com.ar","abc@arnet.com.ar","abc@live.com.ar","abc@outlook.com"};
		assertTrue(verificarContenidoLista(todos,sugerencias));
		
	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94564_Alta_Contacto_Persona_Fisica_Verificar_Formato_De_Email() {
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta("DNI", "32323232");
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> solapas = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		for (WebElement UnaS : solapas) {
			if (UnaS.findElement(By.tagName("a")).getText().equals("Contactos")) {
				UnaS.findElement(By.tagName("a")).click();
				break;
			}
		}
		sleep(1000);
		driver.findElement(By.id("tab-scoped-3")).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(0).findElements(By.tagName("td")).get(0).findElement(By.tagName("a")).click();
		sleep(6000);
		WebElement email = driver.findElement(By.id("EmailSelectableItems"));
		email.findElement(By.tagName("input")).clear();
		email.findElement(By.tagName("input")).sendKeys("abc@telecom");
		assertTrue(driver.findElement(By.cssSelector(".message.description.ng-binding.ng-scope")).getText().equalsIgnoreCase("ingresar un email v\u00e1lido"));
		email.findElement(By.tagName("input")).clear();
		email.findElement(By.tagName("input")).sendKeys("abc@telecom.co");
		try{
			assertTrue(driver.findElement(By.cssSelector(".message.description.ng-binding.ng-scope")).getText().equalsIgnoreCase("ingresar un email v\u00e1lido"));
			assertTrue(false);
		}catch(org.openqa.selenium.NoSuchElementException exp1) {assertTrue(true);}
	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94944_Alta_Contacto_Busqueda_Verificar_Botones_Sobre_Contactos() {
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta("DNI", "11111111");
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> solapas = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		for (WebElement UnaS : solapas) {
			if (UnaS.findElement(By.tagName("a")).getText().equals("Contactos")) {
				UnaS.findElement(By.tagName("a")).click();
				break;
			}
		}
		sleep(2000);
		List<WebElement> btns = driver.findElement(By.id("tab-scoped-3")).findElements(By.cssSelector(".slds-button.slds-button.slds-button--icon"));
		assertTrue(!btns.get(0).findElement(By.tagName("svg")).getAttribute("icon").isEmpty());
		assertTrue(!btns.get(1).findElement(By.tagName("svg")).getAttribute("icon").isEmpty());
		assertTrue(!btns.get(2).findElement(By.tagName("svg")).getAttribute("icon").isEmpty());
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();
		}
	}
	
	@Test(groups={"Sales", "AltaCuenta","Ola1"})
	public void TS94966_Alta_Alta_Cuenta_Busqueda_Verificar_2_Botones_De_Accion_En_Cada_Cuenta() {
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta("DNI", "34073329");
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> solapas = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		for (WebElement UnaS : solapas) {
			if (UnaS.findElement(By.tagName("a")).getText().equals("Clientes Activos")) {
				UnaS.findElement(By.tagName("a")).click();
				break;
			}
		}
		sleep(2000);
		List<WebElement> activos = driver.findElement(By.id("tab-scoped-1")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		System.out.println(activos.size());
		activos.remove(activos.size()-1);
		for(WebElement UnA: activos) {
			List<WebElement> TdUnA = UnA.findElements(By.tagName("td"));
			for (WebElement UnT:TdUnA) {
				if(UnT.getAttribute("data-label").equalsIgnoreCase("actions")) {
					System.out.println(UnT.findElements(By.cssSelector(".slds-icon.slds-icon--x-small")).size());
					assertTrue(UnT.findElements(By.cssSelector(".slds-icon.slds-icon--x-small")).size()==2);
				}
			}
		}
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94881_Alta_Contacto_Busqueda_Verificar_Solapa_Por_Defecto_En_Contacto_Sin_Cuenta() {
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta("DNI", "22222222");
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> solapas = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		for (WebElement UnaS : solapas) {
			if (UnaS.isDisplayed()) {
				assertTrue(UnaS.findElement(By.tagName("a")).getText().equals("Contactos"));
			}
		}
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();
		}
	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	public void TS94790_Alta_Contacto_Busqueda_Verificar_Resultado_Busqueda_Contacto_Sin_Cuenta_Asociada() {
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta("DNI", "22222222");
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> solapas = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		for (WebElement UnaS : solapas) {
			if (UnaS.isDisplayed()) {
				assertTrue(UnaS.findElement(By.tagName("a")).getText().equals("Contactos"));
			}
		}
		solapas = driver.findElement(By.id("tab-scoped-3")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		for (WebElement UnC : solapas) {
			assertTrue(!UnC.findElements(By.tagName("td")).get(1).getText().isEmpty());
		}
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();
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
		WebElement tTel = driver.findElement(By.id("tab-scoped-3")).findElement(By.tagName("tbody")).findElements(By.tagName("td")).get(2);
		Assert.assertTrue(tTel.getText().equals("17856969"));
		WebElement tNom = driver.findElement(By.id("tab-scoped-3")).findElement(By.tagName("tbody")).findElements(By.tagName("td")).get(0);
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
	
	 @Test(groups = {"Sales", "AltaDeContacto"}) 
	    public void TS94583_Alta_de_Contacto_Persona_Fisica_Verificar_estado_fallido_de_la_validacion_de_identidad_por_DNI_con_documentacion_invalida_XX() { 
	      Select dni = new Select (driver.findElement(By.id("SearchClientDocumentType")));  
	      dni.selectByVisibleText("DNI"); 
	      driver.findElement(By.id("SearchClientDocumentNumber")).sendKeys("1"); 
	      sleep(2000); 
	      boolean a = false; 
	      boolean b = false; 
	      List <WebElement> error1 = driver.findElements(By.cssSelector(".error.ng-scope")); 
	      for (WebElement x : error1) { 
	        if (x.getText().toLowerCase().contains("longitud m\u00ednima de 7")) { 
	          a = true; 
	        } 
	      } 
	      driver.findElement(By.id("SearchClientDocumentNumber")).clear(); 
	      driver.findElement(By.id("SearchClientDocumentNumber")).sendKeys("1111111111"); 
	      sleep(2000); 
	      List <WebElement> error2 = driver.findElements(By.cssSelector(".error.ng-scope")); 
	      for (WebElement x : error2) { 
	        if (x.getText().toLowerCase().contains("longitud m\u00e1xima de 8")) { 
	          b = true; 
	        } 
	      } 
	      Assert.assertTrue(a && b); 
	    } 
	     
	    @Test(groups = {"Sales", "AltaDeContacto"}) 
	    public void TS94528_Alta_de_Contacto_Persona_Fisica_Confirmar_creacion_de_contacto_con_un_campo_obligatorio_incompleto_47() { 
	      SalesBase SB = new SalesBase(driver); 
	      SB.BtnCrearNuevoCliente(); 
	      Select dni = new Select(driver.findElement(By.id("DocumentType"))); 
	      dni.selectByVisibleText("DNI"); 
	      driver.findElement(By.id("FirstName")).sendKeys("asdasdasd"); 
	      driver.findElement(By.id("LastName")).sendKeys("sarasa"); 
	      driver.findElement(By.id("Birthdate")).sendKeys("15/05/1982"); 
	      driver.findElements(By.cssSelector(".slds-form-element__control.slds-input-has-icon.slds-input-has-icon--right")).get(2).findElement(By.tagName("input")).sendKeys("asdasd@gmail.com"); 
	      driver.findElement(By.id("Contact_nextBtn")).click(); 
	      sleep(2000); 
	      WebElement error = driver.findElement(By.cssSelector(".slds-modal__content.slds-p-around--medium")); 
	      Assert.assertTrue(error.getText().toLowerCase().contains("error: por favor complete todos los campos requeridos")); 
	      driver.findElement(By.id("alert-ok-button")).click();   
	    } 
	     
	    @Test(groups = {"Sales", "AltaDeCuenta","Ola1"}) 
	    public void TS95278_Alta_Cuenta_Consumer_Valida_alta_menor_16anios() { 
	      SalesBase SB = new SalesBase(driver); 
	      SB.BtnCrearNuevoCliente(); 
	      driver.findElement(By.id("Birthdate")).sendKeys("07/05/2005"); 
	      sleep(2000); 
	      WebElement error = driver.findElement(By.cssSelector(".message.description.ng-binding.ng-scope")); 
	      Assert.assertTrue(error.getText().toLowerCase().contains("fecha de nacimiento inv\u00e1lida")); 
	    } 
	     
	    @Test(groups = {"Sales", "AltaDeLinea","Ola1"}) 
	    public void TS94627_Alta_Linea_Carrito_Seleccionar_producto_Agregar_cantidad() { 
	      SalesBase SB = new SalesBase(driver); 
	      SB.BuscarCuenta(DNI, "11111111"); 
	      SB.acciondecontacto("catalogo"); 
	      SB.agregarplan("plan con tarjeta"); 
	      sleep(25000); 
	      List <WebElement> mas = driver.findElements(By.cssSelector(".slds-button.slds-p-horizontal--xx-small")); 
	      boolean a = false; 
	      for (WebElement x : mas) { 
	        if (x.getText().toLowerCase().contains("clone")) { 
	          a = true; 
	        } 
	      } 
	      Assert.assertTrue(a); 
	    } 
	     
	    @Test(groups = {"Sales", "AltaDeLinea","Ola1"}) 
	    public void TS94628_Alta_Linea_Carrito_Seleccionar_producto_Restar_cantidad() { 
	      SalesBase SB = new SalesBase(driver); 
	      SB.BuscarCuenta(DNI, "11111111"); 
	      SB.acciondecontacto("catalogo"); 
	      SB.agregarplan("plan con tarjeta"); 
	      sleep(25000); 
	      List <WebElement> mas = driver.findElements(By.cssSelector(".slds-button.slds-p-horizontal--xx-small")); 
	      boolean a = false; 
	      for (WebElement x : mas) { 
	        if (x.getText().toLowerCase().contains("delete")) { 
	          a = true; 
	        } 
	      } 
	      Assert.assertTrue(a); 
	    } 
	     
	    @Test(groups = {"Sales", "AltaDeLinea","Ola1"}) 
	    public void TS94629_Alta_Linea_Configurar_Nueva_Linea_Boton_Siguiente() { 
	      SalesBase SB = new SalesBase(driver); 
	      SB.BuscarCuenta(DNI, "11111111"); 
	      SB.acciondecontacto("catalogo"); 
	      SB.agregarplan("plan con tarjeta"); 
	      sleep(15000); 
	      Assert.assertTrue(driver.findElement(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand")).getText().contains("Continuar")); 
	    } 
	     
	    @Test(groups = {"Sales", "AltaDeCuenta","Ola1"}) 
	    public void TS94969_Alta_Cuenta_Busqueda_Verificar_accion_boton_2_con_datos_heredados() { 
	      SalesBase SB = new SalesBase(driver); 
	      SB.BuscarCuenta(DNI, "11111111"); 
	      sleep(7000); 
	      String name = ""; 
	      List <WebElement> cuenta = driver.findElements(By.cssSelector(".slds-truncate.ng-binding")); 
	      for (WebElement x : cuenta) { 
	        if (x.getText().contains("Adela Sales")) { 
	          name = "Adela Sales"; 
	        }  
	      } 
	      SB.acciondecontacto("nueva cuenta"); 
	      sleep(7000); 
	      WebElement titu = driver.findElement(By.id("Owner")); 
	      Assert.assertTrue(titu.getAttribute("value").contains(name)); 
	    } 
	     
	    @Test(groups = {"Sales", "AltaDeCuenta","Ola1"}) 
	    public void TS94971_Alta_Cuenta_Busqueda_Verificar_que_no_se_completen_datos_boton_accion_cliente() { 
	      SalesBase SB = new SalesBase(driver); 
	      SB.BuscarCuenta(DNI, "11111111"); 
	      sleep(7000); 
	      String name = ""; 
	      List <WebElement> cuenta = driver.findElements(By.cssSelector(".slds-truncate.ng-binding")); 
	      for (WebElement x : cuenta) { 
	        if (x.getText().contains("Adela Sales")) { 
	          name = "Adela Sales"; 
	        }  
	      } 
	      SB.acciondecontacto("nueva cuenta"); 
	      sleep(7000); 
	      List <WebElement> prov = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")); 
	      boolean a = false; 
	      for (WebElement x : prov) { 
	        if (x.getText().toLowerCase().contains("provincia")) { 
	          a = true; 
	        } 
	      } 
	      WebElement titu = driver.findElement(By.id("Owner")); 
	      Assert.assertTrue(driver.findElement(By.id("CityTypeAhead")).getText().isEmpty()); 
	      Assert.assertTrue(titu.getAttribute("value").contains(name)); 
	      Assert.assertTrue(a);       
	    } 
	     
	    @Test(groups = {"Sales", "AltaDeContacto","Ola1"}) 
	    public void TS94527_Alta_de_Contacto_Persona_Fisica_Confirmar_creacion_de_contacto_con_mas_de_un_campo_obligatorio_incompleto_46() { 
	      SalesBase SB = new SalesBase(driver); 
	      SB.BtnCrearNuevoCliente(); 
	      driver.findElement(By.id("FirstName")).sendKeys("asdasdasd"); 
	      driver.findElement(By.id("LastName")).sendKeys("sarasa"); 
	      driver.findElement(By.id("Contact_nextBtn")).click(); 
	      sleep(2000); 
	      WebElement error = driver.findElement(By.cssSelector(".slds-modal__content.slds-p-around--medium")); 
	      Assert.assertTrue(error.getText().toLowerCase().contains("error: por favor complete todos los campos requeridos")); 
	      driver.findElement(By.id("alert-ok-button")).click();       
	    } 
	     
	    @Test(groups = {"Sales", "AltaDeContacto"}) 
	    public void TS94893_Alta_Contacto_Verificar_que_aparezca_boton_confirmar() { 
	      SalesBase SB = new SalesBase(driver); 
	      SB.BtnCrearNuevoCliente(); 
	      Assert.assertTrue(driver.findElement(By.id("Contact_nextBtn")).getText().contains("Confirmar")); 
	      Assert.assertTrue(driver.findElement(By.id("Contact_nextBtn")).isDisplayed()); 
	    } 
	     
	    @Test(groups = {"Sales", "Ventas","Ola1"}) 
	    public void TS95062_Ventas_General_Visualizar_accion_Agregar() { 
	      SalesBase SB = new SalesBase(driver); 
	      SB.BuscarCuenta(DNI, "11111111"); 
	      SB.acciondecontacto("catalogo"); 
	      SB.agregarplan("plan con tarjeta"); 
	      sleep(15000); 
	      List <WebElement> mas = driver.findElements(By.cssSelector(".slds-button.slds-p-horizontal--xx-small")); 
	      for (WebElement x : mas) { 
	        if (x.getText().toLowerCase().contains("clone")) { 
	          x.click(); 
	          break; 
	        } 
	      } 
	      sleep(15000); 
	      int a = 0; 
	      List <WebElement> plan = driver.findElements(By.cssSelector(".slds-button.cpq-item-has-children")); 
	      for (WebElement x : plan) { 
	        if (x.getText().toLowerCase().contains("plan con tarjeta")) { 
	          a++; 
	        } 
	      } 
	      Assert.assertTrue(a == 2); 
	    } 
	     
	    @Test(groups = {"Sales", "Ventas","Ola1"}) 
	    public void TS95063_Ventas_General_Visualizar_accion_Eliminar() { 
	      SalesBase SB = new SalesBase(driver); 
	      SB.BuscarCuenta(DNI, "11111111"); 
	      SB.acciondecontacto("catalogo"); 
	      SB.agregarplan("plan con tarjeta"); 
	      sleep(15000); 
	      List <WebElement> mas = driver.findElements(By.cssSelector(".slds-button.slds-p-horizontal--xx-small")); 
	      for (WebElement x : mas) { 
	        if (x.getText().toLowerCase().contains("delete")) { 
	          x.click(); 
	          break; 
	        } 
	      } 
	      sleep(15000); 
	      List <WebElement> plan = driver.findElements(By.cssSelector(".slds-button.cpq-item-has-children")); 
	      int a = 0; 
	      for (WebElement x : plan) { 
	        if (x.getText().toLowerCase().contains("plan con tarjeta")) { 
	          a++; 
	        } 
	      } 
	      Assert.assertTrue(a == 0); 
	    } 
	     
	    @Test(groups = {"Sales", "Ventas","Ola1"}) 
	    public void TS95061_Ventas_General_Visualizar_botones_Agregar_Eliminar() { 
	      SalesBase SB = new SalesBase(driver); 
	      SB.BuscarCuenta(DNI, "11111111"); 
	      SB.acciondecontacto("catalogo"); 
	      SB.agregarplan("plan con tarjeta"); 
	      sleep(15000); 
	      boolean a = false; 
	      boolean b = false; 
	      List <WebElement> mas = driver.findElements(By.cssSelector(".slds-button.slds-p-horizontal--xx-small"));    
	      for (WebElement x : mas) { 
	        if (x.getText().toLowerCase().contains("clone")) { 
	          a = true; 
	        } 
	      } 
	      List <WebElement> menos = driver.findElements(By.cssSelector(".slds-button.slds-p-horizontal--xx-small")); 
	      for (WebElement x : menos) { 
	        if (x.getText().toLowerCase().contains("delete")) { 
	          b = true; 
	        } 
	      } 
	      Assert.assertTrue(a && b); 
	    } 
	     
	    @Test(groups = {"Sales", "Ventas","Ola1"}) 
	    public void TS94888_Ventas_General_Verificar_no_visualizacion_de_boton_Crear_Cuenta() { 
	      SalesBase SB = new SalesBase(driver); 
	      SB.BuscarCuenta(DNI, "11111111"); 
	      SB.acciondecontacto("catalogo"); 
	      sleep(10000); 
	      WebElement page = driver.findElement(By.cssSelector(".vlocity.via-slds")); 
	      if (page.findElement(By.tagName("button")).getText().toLowerCase().contains("crear cuenta")) { 
	        Assert.assertTrue(false); 
	      } 
	      if (page.findElement(By.tagName("button")).getAttribute("value").toLowerCase().contains("crear cuenta")) { 
	        Assert.assertTrue(false); 
	      } 
	    } 
	     
	    @Test(groups = {"Sales", "AltaDeCuenta","Ola1"}) 
	    public void TS95513_Alta_de_Cuenta_Consumer_Verificar_Consumidor_final_por_defecto() { 
	      SalesBase SB = new SalesBase(driver); 
	      SB.BuscarCuenta(DNI, "11111111"); 
	      SB.acciondecontacto("catalogo"); 
	      SB.agregarplan("plan con tarjeta"); 
	      sleep(15000); 
	      driver.findElement(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand")).click(); 
	      sleep(7000); 
	      WebElement iva = driver.findElement(By.id("ImpositiveCondition")); 
	      Assert.assertTrue(iva.getAttribute("value").equals("IVA_CF")); 
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
	 @Test(groups = {"Sales", "AltaDeContacto","Ola1"})
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
	  @Test(groups = {"Sales", "AltaDeContacto","Ola1"})
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
	  @Test(groups = {"Sales", "AltaDeContacto","Ola1"}) 
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
	  
	  
	  @Test(groups = {"Sales", "AltaDeContacto","Ola1"}) 
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
	  @Test(groups = {"Sales", "AltaDeContacto","Ola1"})
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
	  
	  @Test(groups = {"Sales", "Configuracion","Ola1"}) 
	  public void TS94610_Configuracion_CondicionImpositiva_Verificar_categoria_frente_al_IVA_para_clientes_con_DNI_Pasaporte() {
		  SalesBase SB = new SalesBase(driver); 
		  SB.BuscarCuenta(DNI, "11111111"); 
		  SB.acciondecontacto("nueva cuenta");
		  sleep(7000);
		  driver.findElement(By.id("ImpositiveCondition")).click();
		  sleep(2000);
		  Assert.assertTrue(driver.findElement(By.xpath("//*[text() = 'IVA Consumidor Final']")).isDisplayed());
	  }
	  
	  @Test(groups = {"Sales", "Ventas","Ola1"})
	  public void TS94709_Ventas_BuscarCliente_Verificar_cliente_activo_por_numero_de_linea() {
		  String tel = "1157602860";
		  driver.findElement(By.id("PhoneNumber")).sendKeys(tel);
		  driver.findElement(By.id("SearchClientsDummy")).click();
		  sleep(5000);
		  WebElement linea = driver.findElement(By.xpath("//*[@id=\"tab-scoped-1\"]/section/div/table/tbody/tr[1]/td[2]"));
		  Assert.assertTrue(linea.getText().equals(tel));		  
	  }
	  
	  @Test(groups = {"Sales", "Ventas","Ola1"})
	  public void TS94710_Ventas_BuscarCliente_Verificar_Los_Datos_Del_Cliente_Activo() { 
		  boolean ok = false;
		  driver.findElement(By.id("PhoneNumber")).sendKeys("1158433883");
		  driver.findElement(By.id("SearchClientsDummy")).click();
		  sleep(5000);
		  List<WebElement> campos = driver.findElement(By.id("tab-scoped-1")).findElement(By.cssSelector(".slds-table.slds-table--bordered.slds-tree.slds-table--tree.table.tableCSS")).findElement(By.tagName("tr")).findElements(By.tagName("th"));
		  assertTrue(campos.get(0).getText().equalsIgnoreCase("razon social"));	  
		  assertTrue(campos.get(1).getText().equalsIgnoreCase("nombre"));	
		  assertTrue(campos.get(2).getText().equalsIgnoreCase("linea"));	
		  assertTrue(campos.get(3).getText().equalsIgnoreCase("tipo documento"));	
		  assertTrue(campos.get(4).getText().equalsIgnoreCase("documento"));	
		  assertTrue(campos.get(5).getText().equalsIgnoreCase("mail"));	
		  assertTrue(campos.get(6).getText().equalsIgnoreCase("nro de cuenta"));	
		  assertTrue(campos.get(7).getText().equalsIgnoreCase("acciones"));	
		  driver.findElement(By.id("tab-scoped-1")).click();
		  sleep(1000);
		  if (driver.findElement(By.id("tab-scoped-1")).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(2).findElement(By.tagName("span")).getAttribute("class").equals("color-row ng-hide"))
			  ok = true;
		  else if (driver.findElement(By.id("tab-scoped-1")).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(2).findElement(By.tagName("span")).getText().toLowerCase().contains("nominado"))
			  ok = true;
		  assertTrue(ok);
	  }
	  
	  @Test(groups = {"Sales", "Ventas","Ola1"})
	  public void TS94715_Ventas_BuscarCliente_Verificar_linea_sin_cliente_asociado() {
		  driver.findElement(By.id("PhoneNumber")).sendKeys("1157602861");
		  driver.findElement(By.id("SearchClientsDummy")).click();
		  sleep(5000);
		  List <WebElement> cai = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		  if (cai.get(0).isDisplayed() && cai.get(1).isDisplayed()) {
			  Assert.assertTrue(false);
		  }
	  }
	  
	  @Test(groups = {"Sales", "Ventas","Ola1"})
	  public void TS94711_Ventas_BuscarCliente_Verificar_Clientes_No_Activos() {
		  driver.findElement(By.id("PhoneNumber")).sendKeys("1157572274");
		  driver.findElement(By.id("SearchClientsDummy")).click();
		  sleep(5000);
		  List <WebElement> cai = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		  if (cai.get(0).isDisplayed() || !cai.get(1).isDisplayed()) {
			  Assert.assertTrue(false);
		  }
	  }
	  
	  @Test(groups = {"Sales", "Ventas","Ola1"})
	  public void TS94713_Ventas_BuscarCliente_Verificar_Clientes_Activos_Y_No_Activos() {
		  driver.findElement(By.id("PhoneNumber")).sendKeys("1157572274");
		  driver.findElement(By.id("SearchClientsDummy")).click();
		  sleep(5000);
		  List <WebElement> cai = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		  if (cai.get(0).isDisplayed() || !cai.get(1).isDisplayed()) {
			  Assert.assertTrue(false);
		  }
		  sleep(2000);
		  driver.findElement(By.id("PhoneNumber")).clear();
		  driver.findElement(By.id("PhoneNumber")).sendKeys("1157602860");
		  driver.findElement(By.id("SearchClientsDummy")).click();
		  sleep(5000);
		  cai = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		  if (!cai.get(0).isDisplayed() || cai.get(1).isDisplayed()) {
			  Assert.assertTrue(false);
		  }
	  }
	  
	  //1157524452 activo  
	  
	  @Test(groups = {"Sales", "Ventas","Ola1"})
	  public void TS95040_Ventas_General_Verificar_Que_Se_Visualice_FamiliaPacks_LineaMovilActiva() {
		  SalesBase SB = new SalesBase(driver); 
		  boolean enc = false;
		  SB.BuscarCuenta("DNI", "");;
			SB.acciondecontacto("catalogo");
			sleep(14000);
			List<WebElement> opcs = driver.findElement(By.className("cpq-main-categories-container")).findElements(By.tagName("a"));
			for(WebElement UnaO : opcs) {
				if(UnaO.findElement(By.tagName("span")).getText().equalsIgnoreCase("packs")) {
					enc = true;
				}
			}
			assertTrue(enc);
			//1157602860
	  }
	  
	  @Test(groups = {"Sales", "Ventas","Ola1"})
	  public void TS94724_Ventas_VentasEntregas_Verificar_Que_Se_Permita_La_Seleccion_De_La_Modalidad_De_Entrega() {
		  SalesBase SB = new SalesBase(driver); 
		  SB.BuscarCuenta("DNI", ""); 
			SB.acciondecontacto("catalogo");
			sleep(14000);
			List<WebElement> botones = driver.findElements(By.cssSelector(".slds-m-left--x-small.slds-button.slds-button--brand"));
			for(WebElement UnB : botones) {
				System.out.println("UnBoton= "+UnB.getText());
				if(UnB.getText().equalsIgnoreCase("cambiar")) {
					UnB.click();
					break;
				}
			}
			sleep(12000);
			driver.switchTo().frame(SB.getFrameForElement(driver, By.id("DeliveryMethodSelection")));
			Select MdE= new Select(driver.findElement(By.id("SalesChannelConfiguration")).findElement(By.id("DeliveryMethodSelection")));
			MdE.selectByVisibleText("Presencial");
			assertTrue(MdE.getFirstSelectedOption().getText().equalsIgnoreCase("presencial"));
			MdE.selectByVisibleText("Delivery");
			assertTrue(MdE.getFirstSelectedOption().getText().equalsIgnoreCase("delivery"));
			//1157602860
	  }
	  
	  @Test(groups = {"Sales", "Ventas","Ola1"})
	  public void TS94725_Ventas_VentasEntregas_Verificar_Que_Se_Permita_La_Seleccion_De_La_Modalidad_De_Entrega_Store_Pick_Up() {
		  SalesBase SB = new SalesBase(driver); 
		  SB.BuscarCuenta("DNI", "");;
			SB.acciondecontacto("catalogo");
			sleep(14000);
			List<WebElement> botones = driver.findElements(By.cssSelector(".slds-m-left--x-small.slds-button.slds-button--brand"));
			for(WebElement UnB : botones) {
				System.out.println("UnBoton= "+UnB.getText());
				if(UnB.getText().equalsIgnoreCase("cambiar")) {
					UnB.click();
					break;
				}
			}
			sleep(14000);
			driver.switchTo().frame(SB.getFrameForElement(driver, By.id("DeliveryMethodSelection")));
			Select MdE= new Select(driver.findElement(By.id("SalesChannelConfiguration")).findElement(By.id("DeliveryMethodSelection")));
			MdE.selectByVisibleText("Store Pick Up");
			assertTrue(MdE.getFirstSelectedOption().getText().equalsIgnoreCase("Store Pick Up"));
			//1157602860
	  }
	  
	  @Test(groups = {"Sales", "AltaDeContacto","Ola1"})
	  public void TS94887_Alta_de_Contacto_Busqueda_Verificar_boton_de_Crear_Contacto_en_cualquier_resultado_de_busqueda_con_contacto_inexistente() {
		  SalesBase SB = new SalesBase(driver); 
		  SB.BuscarCuenta(DNI, "78421962");
		  Assert.assertTrue(driver.findElement(By.cssSelector(".OSradioButton.ng-scope.only-buttom")).isDisplayed());
	  }
	  
	  @Test(groups = {"Sales", "AltaDeContacto", "Ola1"})
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
	  
	  @Test(groups = {"Sales", "AltaDeContacto", "Ola1"})
	  public void TS94878_Alta_de_Contacto_Busqueda_Verificar_accion_de_Crear_Cuenta() {
		  SalesBase SB = new SalesBase(driver); 
		  SB.BuscarCuenta(DNI, "11111111"); 
		  SB.acciondecontacto("nueva cuenta");
		  sleep(7000);
		  Assert.assertTrue(driver.findElement(By.id("ContactName")).isDisplayed());
	  }
	  
	  @Test(groups = {"Sales", "AltaDeContacto", "Ola1"})
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
	  
	  @Test(groups = {"Sales", "AltaDeContacto","Ola1"})
	  public void TS94795_Alta_Contacto_Busqueda_Verificar_Op1_tercer_TAB_de_visualizacion() {
		  SalesBase SB = new SalesBase(driver); 
		  SB.BuscarCuenta(DNI, "74195213");
		  sleep(3000);
		  WebElement msj = driver.findElement(By.id("txtNoRegistryMsg"));
		  Assert.assertTrue(msj.getText().contains("No hay ning\u00fan cliente con este tipo y n\u00famero de documento. Busc\u00e1 con otro dato o cre\u00e1 un nuevo cliente"));
	  }
	  
	  @Test(groups = {"Sales", "AltaDeContacto", "Ola1"})
	  public void TS94799_Alta_Contacto_Busqueda_Verificar_accion_Crear_Nuevo_Contacto() {
		  SalesBase SB = new SalesBase(driver); 
		  SB.BuscarCuenta(DNI, "74195213");
		  sleep(3000);
		  driver.findElement(By.cssSelector(".OSradioButton.ng-scope.only-buttom")).click();
		  sleep(7000);
		  WebElement msj = driver.findElement(By.id("TextBlock1"));
		  Assert.assertTrue(msj.getText().contains("El contacto no existe. Complet\u00e1 los datos para crear un nuevo cliente"));
	  }
	  
	  @Test(groups = {"Sales", "AltaDeContacto", "Ola1"})
	  public void TS94891_Alta_Contacto_Verificar_que_por_defecto_aparezca_DNI() {
		  SalesBase SB = new SalesBase(driver); 
		  SB.BuscarCuenta(DNI, "12587963");
		  sleep(3000);
		  driver.findElement(By.cssSelector(".OSradioButton.ng-scope.only-buttom")).click();
		  sleep(7000);
		  WebElement dni = driver.findElement(By.id("DocumentType"));
		  Assert.assertTrue(dni.getAttribute("value").equals("DNI"));
	  }
	  
	  @Test(groups = {"Sales", "AltaDeContacto","Ola1"})
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
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	  public void TS94546_Alta_de_Contacto_Persona_Fisica_Validar_que_se_muestre_la_barra_39(){
		  SalesBase SB = new SalesBase(driver);
			 SB.BtnCrearNuevoCliente();
			 WebElement birt = driver.findElement(By.id("Birthdate"));
			 birt.sendKeys("13/08/1994");
			 Assert.assertTrue(birt.getAttribute("value").contains("/"));
	  }
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	  public void TS94538_Ingresar_dominio_de_email_inexistente_32(){
		  SalesBase SB = new SalesBase(driver);
		  SB.BtnCrearNuevoCliente();
		  sleep(8000); 
		  
		  WebElement in = driver.findElements(By.cssSelector(".slds-form-element__control.slds-input-has-icon.slds-input-has-icon--right")).get(2).findElement(By.tagName("input"));
		  in.sendKeys("asd@Q");
		  Boolean y = false;
		 List<WebElement> inv = driver.findElements(By.cssSelector(".message.description.ng-binding.ng-scope"));
			for(WebElement i : inv){
				if(i.getText().toLowerCase().equals("ingresar un email v\u00e1lido")){
					i.isDisplayed();
					y=true;
				}
			}
		  Assert.assertTrue(y);
	  }
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	  public void TS94532_Alta_de_Contacto_Persona_Fisica_Ingresar_DNI_en_el_campo_Numero_de_Documento_05(){
		  SalesBase SB = new SalesBase (driver);
		  WebElement doc = driver.findElement(By.id("SearchClientDocumentNumber"));
		  doc.sendKeys("46331352");
		  Assert.assertFalse(doc.getAttribute("value").isEmpty());
		  
	  }
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	  public void TS94533_Alta_de_Contacto_Persona_Fisica_Ingresar_a_la_vista_de_alta_de_contacto_01(){
		SalesBase SB = new SalesBase(driver);
		SB.BtnCrearNuevoCliente();
		Assert.assertTrue(driver.findElement(By.id("DocumentType")).getAttribute("value").equals("DNI"));
		Boolean x = false;
		List<WebElement> mail = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for(WebElement m :mail){
			if (m.getText().equals("E-MAIL")){
				m.isDisplayed();
				x=true;
			}
		}
		List<WebElement> fech = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for(WebElement f :fech){
			if (f.getText().equals("FECHA DE NACIMIENTO")){
				f.isDisplayed();
				x=true;
			}
		}
		List<WebElement> ape = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for(WebElement a :ape){
			if (a.getText().equals("APELLIDO")){
				a.isDisplayed();
				x=true;
			}
		}
		List<WebElement> nom = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for(WebElement n :nom){
			if (n.getText().equals("NOMBRE")){
				n.isDisplayed();
				x=true;
			}
		}
		List<WebElement> docu = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for(WebElement d :docu){
			if (d.getText().equals("DOCUMENTO")){
				d.isDisplayed();
				x=true;
			}
		}
		List<WebElement> gen = driver.findElements(By.cssSelector(".vlc-slds-control-action__container"));
		for(WebElement g :gen){
			if (g.getText().equals("G\u00e9nero")){
				g.isDisplayed();
				x=true;
			}
		}
		Assert.assertTrue(x);
	  }
	  
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	  public void TS94879_Alta_de_Contacto_Busqueda_Verificar_accion_de_proceso_de_Venta(){
		  SalesBase SB = new SalesBase(driver);
		  SB.BuscarCuenta(DNI, "11111111"); 
		  SB.acciondecontacto("catalogo"); 
		  sleep(15000); 
		  WebElement asd = driver.findElement(By.className("taHeaderInfoContainer"));
		 // System.out.println(asd.getText());
		Assert.assertTrue(asd.isDisplayed());
	  }
	
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	  public void TS94822_Alta_Contacto_Busqueda_Verificar_Consumer_Account_Contacto_existente_CRM(){
		  SalesBase SB = new SalesBase(driver);
		  SB.BuscarCuenta(DNI, "11111111");
		  sleep(5000);
		WebElement nomb = driver.findElement(By.id("tab-scoped-3")).findElement(By.tagName("section")).findElement(By.tagName("div")).findElement(By.tagName("table")).findElement(By.tagName("tbody")).findElements(By.tagName("td")).get(0);
		System.out.println(nomb.getText());
		Assert.assertTrue(nomb.getText().equals("Adela Sales"));
	  }
	
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})
	  public void TS94824_Alta_Contacto_Busqueda_Verificar_Consumer_Account_Contacto_inexistente_CRM(){
	  SalesBase SB = new SalesBase(driver);
	  SB.BuscarCuenta(DNI, "14472788");
	  boolean o = false;
	  List<WebElement> nw = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
	     for(WebElement n:nw){
	    	 if(n.getText().equals("+ Crear nuevo cliente")){
	    		n.isDisplayed();
	    		o=true;
	    	 }
	     }
	Assert.assertTrue(o);
	perfil = "call";
	}
	
	@Test(groups={"Sales", "Ventas", "Ola1"})
	public void TS94830_Ventas_General_Verificar_Metodo_De_Entrega_Por_Default_Perfil_Representante_Telefonico(){
		perfil = "agente";
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta(DNI, "34073329");
		SB.acciondecontacto("catalogo");
		boolean x = false;
		sleep(15000);
		assertTrue(driver.findElement(By.cssSelector(".slds-col.taChangeDeliveryMethod.slds-text-body--small.slds-m-left--large")).findElement(By.tagName("strong")).getText().contains("Delivery"));
		List<WebElement> cam = driver.findElements(By.cssSelector(".slds-m-left--x-small.slds-button.slds-button--brand"));
		for(WebElement c : cam ){	
			if(c.getText().toLowerCase().equals("cambiar")){
				c.click();
			}
		}
		sleep(7000);	
		List<WebElement> frame2 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame2.get(0));
		Select env = new Select (driver.findElement(By.id("DeliveryMethodSelection")));
		try {
			env.selectByVisibleText("Presencial");
			assertTrue(false);
		}catch(org.openqa.selenium.NoSuchElementException ex1) {
			assertTrue(true);
		}
		
	}
	
	@Test(groups={"Sales", "Nueva Venta", "Ola1"})
	public void TS94697_Nueva_Venta_Modo_de_Entrega_Verificar_Valor_por_Default_Tipo_de_Delivery(){
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta(DNI, "34073329");
		SB.acciondecontacto("catalogo");
		boolean x = false;
		sleep(15000);
		List<WebElement> cam = driver.findElements(By.cssSelector(".slds-m-left--x-small.slds-button.slds-button--brand"));
		for(WebElement c : cam ){	
			if(c.getText().toLowerCase().equals("cambiar")){
				c.click();
			}}
		sleep(7000);	
		List<WebElement> frame2 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame2.get(0));
		Select env = new Select (driver.findElement(By.id("DeliveryMethodSelection")));
		env.selectByVisibleText("Delivery");
		driver.findElement(By.id("SalesChannelConfiguration_nextBtn")).click();
		sleep(7000);
		driver.switchTo().defaultContent();
		
		SB.elegirplan("Plan con Tarjeta Repro");
		sleep(15000);
		List<WebElement> cont = driver.findElements(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand"));
			for(WebElement c : cont){
				if(c.getText().equals("Continuar")){
					c.click();
				}
			}
		sleep(20000);			
		WebElement sig = driver.findElement(By.id("LineAssignment_nextBtn"));
		sig.click();
		sleep(5000);
		Select deliv = new Select (driver.findElement(By.id("DeliveryServiceType")));
		deliv.selectByVisibleText("Env\u00edo Est\u00e1ndar");
		Assert.assertEquals(deliv.getFirstSelectedOption().getText(),"Env\u00edo Est\u00e1ndar");
	}	
	
	
	@Test(groups={"Sales", "Nueva Venta", "Ola1"})
	public void TS94687_Nueva_Venta_Modo_de_Entrega_Verificar_Valor_por_Default_Modo_de_Entrega_Delivery(){
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta(DNI, "34073329");
		SB.acciondecontacto("catalogo");
		boolean x = false;
		sleep(15000);
		List<WebElement> cam = driver.findElements(By.cssSelector(".slds-m-left--x-small.slds-button.slds-button--brand"));
		for(WebElement c : cam ){	
			if(c.getText().toLowerCase().equals("cambiar")){
				c.click();
			}
		sleep(7000);	
		List<WebElement> frame2 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame2.get(0));
		Select env = new Select (driver.findElement(By.id("DeliveryMethodSelection")));
		env.selectByVisibleText("Delivery");
		driver.findElement(By.id("SalesChannelConfiguration_nextBtn")).click();
		sleep(7000);
		driver.switchTo().defaultContent();
		}
		SB.elegirplan("Plan con Tarjeta Repro");
		sleep(15000);
		List<WebElement> cont = driver.findElements(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand"));
			for(WebElement c : cont){
				if(c.getText().equals("Continuar")){
					c.click();
				}
			}
		sleep(20000);			
		WebElement sig = driver.findElement(By.id("LineAssignment_nextBtn"));
		sig.click();
		sleep(5000);
		WebElement deliv = driver.findElement(By.id("DeliveryMethod"));
		Assert.assertTrue(deliv.getText().equals("Delivery"));
	}		
	
	
}