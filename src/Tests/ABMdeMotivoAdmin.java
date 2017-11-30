package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.ContactMotiveManager;
import Pages.ContactMotivesManager;
import Pages.setConexion;

//page link: https://cs14.salesforce.com/a41?fcf=00Bc0000001LRma&rolodexIndex=-1&page=1

public class ABMdeMotivoAdmin extends TestBase {
	
	private WebDriver driver;
	
	private String motiveName = "motivo Nuevo para Tests"; // needed for 12587 and 12589 (ADD and DEL motive)
	private String descripcion = "Descripcion para el test.";
	private String servicio = "Llamadas ilimitadas";
	private String motivesAbmURL = "https://cs14.salesforce.com/a41?fcf=00Bc0000001LRma&rolodexIndex=-1&page=1";
	
	@BeforeClass(groups= "Fase2")
	public void init() throws Exception
	{
		this.driver = setConexion.setupEze();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		login(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}

	@AfterClass(groups= "Fase2")
	public void tearDown() {
		driver.close();
	}

	@BeforeMethod(groups= "Fase2")
	public void setUp() throws Exception {
		//TODO: add how to get to ABM de Motivo
		
		if (!driver.getCurrentUrl().toString().equals(motivesAbmURL)){
			driver.get(motivesAbmURL);
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		}
	}
	
	//priority forces the tests order execution, and groupsDependency, guarantees the other ones finished
	
	@Test(groups= "Fase2")
	public void TS12584_ABM_de_Motivo_Ingreso(){
		ContactMotivesManager cMMPage = new ContactMotivesManager(driver);
		cMMPage.getMotivesWrapper();//This should be enough.
		cMMPage.getMotiveByName("No me funciona internet");//Could Change, but this has to be a real current MotiveName.
	}
	
	@Test(groups= "Fase2")
	public void TS12587_ABM_de_Motivo_Agregar_Nuevo_Motivo() {
		driver.findElement(By.name("new")).click();
		ContactMotiveManager contactMMPage = new ContactMotiveManager(driver);
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

		contactMMPage.getContactMotiveName().sendKeys(motiveName);
		contactMMPage.getDescripcion().sendKeys(descripcion);
		contactMMPage.getActivoCheck().click();
		contactMMPage.getServicio().sendKeys(servicio);
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		contactMMPage.save();
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertEquals(driver.findElement(By.id("Name_ileinner")).getText(), motiveName); //checks name input with current.
		Assert.assertEquals(driver.findElement(By.id("00Nc0000001pWdl_ileinner")).getText(), descripcion); //checks descripcion input with current.
		driver.findElement(By.className("ptBreadcrumb")).findElement(By.tagName("a")).click(); //goes back to main page for ABM.
		try {Thread.sleep(1500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactMotivesManager contactsMMPage = new ContactMotivesManager(driver);
		//index 4 is the motiveName
		contactsMMPage.getMotiveByName(motiveName); //this should be enough.
		//Assert.assertEquals(cMMPage.getMotiveByName(motiveName).findElements(By.className("x-grid3-col")).get(4).getText(), motiveName);
		//this motive gets deleted in another test.
	}
	
	@Test(groups= "Fase2")
	public void TS12590_ABM_de_Motivo_Modificar_Motivo() {
		ContactMotivesManager contactsMMPage = new ContactMotivesManager(driver);
		contactsMMPage.modifyMotiveByName(motiveName);

		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		motiveName = "Nombre Motivo MODIFICADO.";
		descripcion = "Descripcion Motivo MODIFICADA.";
		servicio = "Arnet 2";
		//here all is modified.
		ContactMotiveManager contactMMPage = new ContactMotiveManager(driver);
		contactMMPage.clearValues(); //clears textboxes only.
		contactMMPage.getContactMotiveName().sendKeys(motiveName);
		contactMMPage.getDescripcion().sendKeys(descripcion);
		contactMMPage.getActivoCheck().click();
		contactMMPage.getAsociableAIncidente().click();
		contactMMPage.getServicio().sendKeys(servicio);
		contactMMPage.save();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		contactsMMPage.modifyMotiveByName(motiveName); //opens modify, but just will assertEquals.
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//only asserts Texts, maybe checks should be checked as well.
		
		Assert.assertEquals(contactMMPage.getContactMotiveName().getAttribute("value"), motiveName);
		Assert.assertEquals(contactMMPage.getDescripcion().getAttribute("value"), descripcion);
		Assert.assertEquals(contactMMPage.getServicio().getAttribute("value"), servicio);
		contactMMPage.cancel();
	}
	
	/*
	@Test(priority = 4, groups ="b", dependsOnGroups = "a")
	public void TS12589_ABM_de_Motivo_Asociar_Motivo_A_Incidente_Masivo() {
		//expected main page for ABM of motives.
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//The Motive Name here is: Nombre Motivo MODIFICADO.
		ContactMotivesManager cMMPage = new ContactMotivesManager(driver);
		cMMPage.openMotiveByName(motiveName);
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactMotive CMPage = new ContactMotive(driver);
		CMPage.newCase();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//Could use AccountType
		WebElement registerTypeSelect = driver.findElement(By.id("p3"));
		CMPage.setSimpleDropdown(registerTypeSelect, "Incidente Masivo");
		driver.findElement(By.name("save")).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.name("save")).click();

	}*/
	
	//@Test(priority = 5, groups ="c", dependsOnGroups = "b") //change to this when TS12589-MassiveIncident works.
	@Test(groups= "Fase2")
	public void TS12589_ABM_de_Motivo_Quitar_Motivo() {
		//expected main page for ABM of motives.
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		ContactMotivesManager cMMPage = new ContactMotivesManager(driver);
		//index 4 is the motiveName
		Assert.assertEquals(cMMPage.getMotiveByName(motiveName).findElements(By.className("x-grid3-col")).get(4).getText(), motiveName);
		cMMPage.deleteMotiveByName(motiveName); //here its deleted
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		Assert.assertEquals(cMMPage.getMotiveByName(motiveName), null); //The motive doesnt exist anymore :( 
	}	
}
