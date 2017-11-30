package Tests;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.ContactSearch;
import Pages.CustomerCare;
import Pages.SalesBase;
import Pages.setConexion;
import Pages.BasePage;
import Pages.ContactInformation;


public class createdContact extends TestBase {


	
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
	
	@AfterClass(groups="Fase1")
	public void tearDown() {
		driver.close();
	}
	@AfterMethod(groups="Fase1")
		public void deslogin(){
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.get("https://crm--sit.cs14.my.salesforce.com/home/home.jsp?tsid=02u41000000QWha/");
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

		}
	@BeforeClass(groups="Fase1")
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

	@BeforeMethod(groups="Fase1")
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


	}
	
	BasePage base = new BasePage();
	
	@Test(groups="Fase1")
	public void TS6905_createdNewValidContact()
	{
		ContactSearch contact = new ContactSearch(driver);
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		contact.searchContact(DNI, DocValue[0], "femenino");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("ContactInfo_nextBtn")).click();
		
	}
	
	@Test(groups="Fase1")
	public void TS6965_Verificar_que_el_campo_Numero_de_documento_no_tenga_menos_de_7_digitos()	{
		boolean a = false;
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
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, "12345678", "");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

		System.out.println(driver.findElement(By.id("GenderSearch|0")).findElement(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-radio-control.ng-scope.ng-dirty.ng-invalid.ng-invalid-required")).isEnabled());
		
			}
	
	@Test(groups="Fase1")
	public void TS6966_Verificar_que_el_campo_Numero_de_Documento_sea_obligatorio(){
		boolean a= false;
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
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact(DNI, PASA, "femenino");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		String PASA2=driver.findElement(By.id("DocumentInputSearch")).getAttribute("value");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(PASA.equals(PASA2));
	}
	
	//@Test(groups="Fase2")
	public void TS6911_CUITNumber()
	{
		WebElement type = driver.findElement(By.id("DocumentType"));
		base.setSimpleDropdown(type, "CUIT");
		driver.findElement(By.id("CuitDocument")).sendKeys("22-35689987-4");
	}
	
	@Test(groups="Fase1")
	public void TS6943_Verificar_el_ingreso_de_caracteres_alfanumericos_en_Pasaporte(){
		String PASA = "123ASD1";
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
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

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
}
