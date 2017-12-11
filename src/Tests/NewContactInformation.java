	package Tests;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import Pages.ContactInformation;
import Pages.ContactSearch;
import Pages.CustomerCare;
import Pages.Login;
import Pages.SalesBase;
import Pages.setConexion;


public class NewContactInformation extends TestBase {
	
	protected String perfil = "agente";

	private WebDriver driver;
	String Name = "Roberto";
	String LastName = "Carlos";
	String Email = "RobertoCarlos@gmail.com";
	String DateOfBirthday = "06/07/1990";
	String DateOfBirthdayWrong = "06/07/1890";
	String[] genero = {"masculino","femenino"};
	String DNI = "DNI";
	String[] DocValue = {"52698547","3569874563","365","ssss"};
	
	
	@AfterMethod(groups="Fase2")
	public void deslogin(){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.get("https://crm--sit.cs14.my.salesforce.com/home/home.jsp?tsid=02u41000000QWha/");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

	}
	
	@BeforeClass(groups="Fase2")
	public void Init() throws Exception
	{
		this.driver = setConexion.setupEze();
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
		 }
	
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}

	@BeforeMethod(groups="Fase2")
	public void setup() throws Exception {		
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.xpath("//a[@href=\'https://crm--SIT--c.cs14.visual.force.com/apex/taClientSearch']")).click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactInformation page = new ContactInformation(driver);
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada(Name, LastName, "", "", "");
		CustomerCare CC = new CustomerCare(driver);
		CC.obligarclick(driver.findElement(By.id("dataInput_nextBtn")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, DocValue[0], "masculino");
		driver.findElement(By.id("ContactSearch_nextBtn")).click();
		try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

	}
	
	
	@Test(groups="Fase1")
	public void TS6901_Completar_los_campos_luego_de_una_busqueda_de_contacto_inexistente()	{
		ContactInformation page = new ContactInformation(driver);
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page.setContactInformation(Name, LastName, DateOfBirthday);
		
	}
	
	@Test(groups="Fase1")
	public void TS6920_Seleccionar_opcion_de_validacion_de_identidad(){
		ContactInformation page = new ContactInformation(driver);
		page.setContactInformation(Name, LastName, DateOfBirthday);
		driver.findElement(By.cssSelector(".slds-checkbox--faux")).click();
		driver.findElement(By.id("Contact_nextBtn")).click();
		
	}
	@Test(groups="Fase1")
	public void TS6909_Ingresar_caracteres_numericos_en_campo_Apellido(){
		driver.findElement(By.id("LastName")).sendKeys("123");
		driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-invalid.ng-valid-minlength.ng-valid-maxlength.ng-dirty.ng-valid-parse.ng-invalid-pattern.ng-valid-required"));
	}
	
	@Test(groups="Fase1")
	public void TS6910_Ingresar_caracteres_numericos_en_campo_Nombre()
	{	driver.findElement(By.id("FirstName")).clear();
		driver.findElement(By.id("FirstName")).sendKeys("123");
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-invalid.ng-valid-minlength.ng-valid-maxlength.ng-dirty.ng-valid-parse.ng-invalid-pattern.ng-valid-required")).isDisplayed());	
	}	
	
	@Test(groups="Fase1")
	public void TS6949_Verificar_Fecha_de_Nacimiento_con_ingreso_manual() {
		driver.findElement(By.id("Birthdate")).sendKeys(DateOfBirthday);
		Assert.assertTrue(	driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-dirty.ng-valid-parse.ng-valid-required.ng-valid.ng-valid-valid")).isDisplayed());
	}
	
	@Test(groups="Fase1")
	public void TS6950_Fecha_de_Nacimiento_con_ingreso_manual_Anio_con_letras_o_mas_de_5_digitos(){
		driver.findElement(By.id("Birthdate")).sendKeys(DateOfBirthdayWrong +"4");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-dirty.ng-valid-parse.ng-valid-required.ng-invalid.ng-invalid-valid")).isDisplayed());
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("Birthdate")).clear();
		driver.findElement(By.id("Birthdate")).sendKeys("agosto");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-dirty.ng-valid-parse.ng-valid-required.ng-invalid.ng-invalid-valid")).isDisplayed());

	}
	

	
	@Test(groups="Fase1")
	public void TS6951_Verificar_Fecha_de_Nacimiento_con_ingreso_manual_Dia_Fuera_de_rango(){
		driver.findElement(By.id("Birthdate")).sendKeys("32/08/1999");
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-dirty.ng-valid-parse.ng-valid-required.ng-invalid.ng-invalid-valid")).isDisplayed());
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("Birthdate")).clear();
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("Birthdate")).sendKeys("00/08/1999");
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-dirty.ng-valid-parse.ng-valid-required.ng-invalid.ng-invalid-valid")).isDisplayed());

	}
	

	
	@Test(groups="Fase1")
	public void TS6952_Verificar_Fecha_de_Nacimiento_con_ingreso_manual_Mes_Fuera_de_rango() 
	{
		driver.findElement(By.id("Birthdate")).sendKeys("22/13/1999");
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-dirty.ng-valid-parse.ng-valid-required.ng-invalid.ng-invalid-valid")).isDisplayed());
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("Birthdate")).clear();
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("Birthdate")).sendKeys("22/00/1999");
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-dirty.ng-valid-parse.ng-valid-required.ng-invalid.ng-invalid-valid")).isDisplayed());

	}
	
	@Test(groups="Fase1")
	public void TS6974_Verificar_valor_del_check_de_email_por_default() 
	{	
		Assert.assertTrue(!driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-pristine.ng-scope.ng-invalid.ng-invalid-required")).isSelected());
	}
	
	@Test(groups="Fase1")
	public void TS6941_Verificar_check_de_no_tener_email() 
	{
		driver.findElement(By.cssSelector(".slds-checkbox--faux")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-pristine.ng-scope.ng-valid.ng-valid-required")).isSelected());
	}
	

	@Test(groups="Fase1")
	public void TS6934_Verificar_campo_Apellido_obligatorio(){
		boolean a= false;
	driver.findElement(By.id("LastName")).clear();
	try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	List<WebElement>asl=driver.findElements(By.cssSelector(".description.ng-binding"));
	for(WebElement e: asl){
		if(e.getText().equals("Campo Requerido")){
			a=true;}}
	Assert.assertTrue(a);
	}
	
	@Test(groups="Fase1")
	public void TS6936_Verificar_campo_Fecha_de_Nacimiento_obligatorio(){
		boolean a= false;
	driver.findElement(By.id("Birthdate")).clear();
	try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	List<WebElement>asl=driver.findElements(By.cssSelector(".description.ng-binding"));
	for(WebElement e: asl){
		if(e.getText().equals("Campo Requerido")){
			a=true;}}
	Assert.assertTrue(a);
	}
	
	@Test(groups="Fase1")
	public void TS6938_Verificar_campo_Nombre_obligatorio(){
		boolean a= false;
	driver.findElement(By.id("FirstName")).clear();
	try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	List<WebElement>asl=driver.findElements(By.cssSelector(".description.ng-binding"));
	for(WebElement e: asl){
		if(e.getText().equals("Campo Requerido")){
			a=true;}}
	Assert.assertTrue(a);	}
	
	@Test(groups="Fase1")
	public void TS6931_calendarBirthDate(){
		driver.findElement(By.id("Birthdate")).click();
		driver.findElement(By.cssSelector(".datepicker.-bottom-left-.-from-bottom-.active"));
	}

	@Test(groups="Fase1")
	public void TS6957_Verificar_mascara_del_campo_Fecha_de_Nacimiento() {

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
}

