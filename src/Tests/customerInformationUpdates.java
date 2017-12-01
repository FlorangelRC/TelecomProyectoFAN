package Tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

import Pages.AccountType;
import Pages.BasePage;
import Pages.CustomerCare;
import Pages.NewAccount;
import Pages.customerInformation;
import Pages.setConexion;

public class customerInformationUpdates extends TestBase {
	
	private WebDriver driver;
	String accountName = "Aaa Aaa";



	//@AfterClass(groups= "CustomerCare")
	public void tearDown2() {
		driver.close();	
	}

	//@AfterMethod(groups= "CustomerCare")
	public void tearDown() {		
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BasePage cambioFrameByID=new BasePage();
	    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("alert-ok-button")));
		driver.findElement(By.id("alert-ok-button")).click();
	}
	
	@BeforeClass(groups= "CustomerCare")
	public void init() throws Exception{
		this.driver = setConexion.setupEze();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		login(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	 	String a = driver.findElement(By.id("tsidLabel")).getText();
	 	driver.findElement(By.id("tsidLabel")).click();
	 	if(a.equals("Ventas")){
	 		driver.findElement(By.xpath("//a[@href=\'/console?tsid=02uc0000000D6Hd\']")).click();
	 	}else{   
	 		driver.findElement(By.xpath("//a[@href=\'/home/home.jsp?tsid=02u41000000QWha\']")).click();
	 		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	 		driver.findElement(By.id("tsidLabel")).click();
	 		driver.findElement(By.xpath("//a[@href=\'/console?tsid=02uc0000000D6Hd\']")).click();	 		
	 	}
	 	try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		CustomerCare page = new CustomerCare(driver);
		page.elegircuenta("Fernando Care");
		BasePage cambioFrameByID=new BasePage();
	    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("profile-edit")));
		List <WebElement> actualizar = driver.findElements(By.className("profile-edit"));
		for (WebElement x : actualizar) {
			if (x.getText().toLowerCase().contains("actualizar datos")) {
				x.click();
			}
		}
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	 } 
	 
	 @BeforeMethod(groups= "CustomerCare")
	 public void setup(){
		 try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		 BasePage cambioFrameByID=new BasePage();
		 driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("LastName")));
	 }

	 
	@Test(groups= "CustomerCare")	
	public void TS7175_isLastNameMandatory() {
		driver.findElement(By.id("LastName")).clear();
		List <WebElement> element = driver.findElements(By.cssSelector(".error.ng-scope"));
		Assert.assertTrue(element.get(1).isDisplayed());
		driver.findElement(By.id("ClientInformation_nextBtn")).click();		
	}
	
	
	@Test(groups= "CustomerCare")	
	public void TS7174_isFirstNameMandatory() {
		driver.findElement(By.id("FirstName")).clear();
		List <WebElement> element = driver.findElements(By.cssSelector(".error.ng-scope"));
		Assert.assertTrue(element.get(0).isDisplayed());
		driver.findElement(By.id("ClientInformation_nextBtn")).click();		
	}
	
	
	@Test(groups= "CustomerCare")	
	public void TS7173_isEmailMandatory() {
		driver.findElement(By.id("Email")).clear();
		List <WebElement> element = driver.findElements(By.cssSelector(".error.ng-scope"));
		Assert.assertTrue(element.get(7).isDisplayed());
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
	}

	
	@Test(groups= "CustomerCare")	
	public void TS7170_isDocumentMandatory() {
		Assert.assertTrue(driver.findElement(By.id("DocumentNumber")).isDisplayed());
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
	}

	
	@Test(groups= "CustomerCare")	
	public void TS7171_isBirthDateMandatory() {
		driver.findElement(By.id("Birthdate")).clear();
		List <WebElement> element = driver.findElements(By.cssSelector(".error.ng-scope"));
		Assert.assertTrue(element.get(5).isDisplayed());
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
	}
	
	
	@Test(groups= "CustomerCare")	
	public void TS7169_isGenderMandatory() {
		BasePage x = new BasePage(driver);
		x.setSimpleDropdown(driver.findElement(By.id("Gender")), "-- Clear --");
		List <WebElement> element = driver.findElements(By.cssSelector(".error.ng-scope"));
		Assert.assertTrue(element.get(6).isDisplayed());
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
	}
	

	@Test(groups= "CustomerCare")
	public void TS7172_isMobilePhoneMandatory() {
		driver.findElement(By.id("MobilePhone")).clear();
		List <WebElement> element = driver.findElements(By.cssSelector(".error.ng-scope"));
		Assert.assertTrue(element.get(8).isDisplayed());
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
	}
	
	
	//@Test(groups= "CustomerCare")	//BUG EN DNI
	public void TS7149_fieldsWhichDontTriggerIdentityValidationProcess() {
		customerInformation page = new customerInformation(driver);	
		page.setDefaultValues();
		waitFor(driver, (By.className("panel-heading")));		
	}
	
	//@Test(groups= "CustomerCare")	//BUG EN DNI
	public void TS7176_modifyTwoFieldsWhichDontTriggerIdentityValidationProcess() {
		customerInformation page = new customerInformation(driver);
		page.setTwoFieldsWhichDontTriggerIdentityValidationProcess();
		waitFor(driver, (By.className("panel-heading")));		
		List<WebElement> text = driver.findElements(By.className("panel-heading"));
		Assert.assertTrue(text.get(0).getText().contains("Confirmación"));
		driver.switchTo().defaultContent();
		List<WebElement> tabs = driver.findElements(By.className("x-tab-strip-close"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", tabs.get(6));
		List<WebElement> frame4 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame4.get(5));
		List<WebElement> profileEdit1 = driver.findElements(By.className("profile-edit"));
		profileEdit1.get(0).click();
		driver.switchTo().defaultContent();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame5 = driver.findElements(By.tagName("iframe"));		
		driver.switchTo().frame(frame5.get(4));
		waitFor(driver, (By.id("FirstName")));		
		page.setDefaultValues();
		waitFor(driver, (By.className("panel-heading")));		
	}
	
	//@Test(groups= "CustomerCare")	//BUG EN DNI
	public void TS7177_modifyThreeFieldsWhichTriggerIdentityValidationProcess() {
		customerInformation page = new customerInformation(driver);
		page.setThreeFieldsWhichTriggerIdentityValidationProcess();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> text = driver.findElements(By.cssSelector(".ng-binding.ng-scope"));
		Assert.assertEquals("No se pueden modificar Género, Número de documento y Fecha de Nacimiento al mismo tiempo.", text.get(3).getText());
	}
	
	@Test(groups= "CustomerCare")
	public void TS7153_verifyBirthDateHasValidDateFormat() {
		CustomerCare page = new CustomerCare (driver);
		WebElement element = driver.findElement(By.id("Birthdate"));
		System.out.println(element.getAttribute("vlc-slds-model-date-format"));
		Assert.assertTrue(page.validarFecha(element.getAttribute("value"), "dd/MM/yyyy"));
	}
	
	@Test(groups= "CustomerCare")
	public void TS7155_validateBirthDateHasAYearPicker() {
		driver.findElement(By.id("Birthdate")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector(".datepicker.-bottom-left-.-from-bottom-")).isDisplayed());
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
	}
	
	//@Test(groups= "CustomerCare")	//NO SE PUEDE HACER POR DNI
	public void TS7183_modifyDocumentTwiceInAMonth() {
		customerInformation page = new customerInformation(driver);
		try{Assert.assertFalse(page.isDocumentModifyable());} catch (Exception e){}
		page.modifyDocument("32645423");
		waitFor(driver, (By.className("panel-heading")));		
		List<WebElement> text = driver.findElements(By.className("panel-heading"));
		//Assert.assertTrue(text.get(0).getText().contains("Confirmación"));
		driver.switchTo().defaultContent();
		List<WebElement> tabs = driver.findElements(By.className("x-tab-strip-close"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", tabs.get(6));
		List<WebElement> frame4 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame4.get(5));
		List<WebElement> profileEdit1 = driver.findElements(By.className("profile-edit"));
		profileEdit1.get(0).click();
		driver.switchTo().defaultContent();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame5 = driver.findElements(By.tagName("iframe"));		
		driver.switchTo().frame(frame5.get(4));
		waitFor(driver, (By.id("FirstName")));
		Assert.assertFalse(page.isDocumentModifyable()); 
	}
	
	
	@Test(groups= "CustomerCare")
	public void TS7098_cancelUpdateInformation() {
		BasePage cambioFrameByID=new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("ClientInformation_nextBtn")));
		List <WebElement> cancelar = driver.findElements(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope"));
		for (WebElement x : cancelar) {
			if (x.getText().toLowerCase().contains("cancelar")) {
				x.click();
			}
		}
	    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("alert-ok-button")));
		driver.findElement(By.id("alert-ok-button")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("profile-box")));
		List <WebElement> actualizar = driver.findElements(By.className("profile-edit"));
		actualizar.get(0).click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("ClientInformation_nextBtn")));
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
	}
	
	//@Test(groups= "CustomerCare")	//BUG EN celular
	public void TS7103_updateMobilePhone() {
		customerInformation page = new customerInformation(driver);
		page.modifyMobilePhone();
		waitFor(driver, (By.className("panel-heading")));
		List<WebElement> text = driver.findElements(By.className("panel-heading"));
		Assert.assertTrue(text.get(0).getText().contains("Confirmación"));
		driver.switchTo().defaultContent();
		List<WebElement> tabs = driver.findElements(By.className("x-tab-strip-close"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", tabs.get(6));
		List<WebElement> frame4 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame4.get(5));
		List<WebElement> profileEdit1 = driver.findElements(By.className("profile-edit"));
		profileEdit1.get(0).click();
		driver.switchTo().defaultContent();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame5 = driver.findElements(By.tagName("iframe"));		
		driver.switchTo().frame(frame5.get(4));
		waitFor(driver, (By.id("FirstName")));		
		page.setDefaultValues();
		waitFor(driver, (By.className("panel-heading")));
	}
	
	//@Test(groups= "CustomerCare")	//BUG EN EMAIL
	public void TS7102_updateOtherPhone() {
		customerInformation page = new customerInformation(driver);
		page.modifyOtherPhone();
		waitFor(driver, (By.className("panel-heading")));		
		List<WebElement> text = driver.findElements(By.className("panel-heading"));
		Assert.assertTrue(text.get(0).getText().contains("Confirmación"));
		driver.switchTo().defaultContent();
		List<WebElement> tabs = driver.findElements(By.className("x-tab-strip-close"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", tabs.get(6));
		List<WebElement> frame4 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame4.get(5));
		List<WebElement> profileEdit1 = driver.findElements(By.className("profile-edit"));
		profileEdit1.get(0).click();
		driver.switchTo().defaultContent();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame5 = driver.findElements(By.tagName("iframe"));		
		driver.switchTo().frame(frame5.get(4));
		waitFor(driver, (By.id("FirstName")));		
		page.setDefaultValues();
		waitFor(driver, (By.className("panel-heading")));
	}
	
	//@Test(groups= "CustomerCare")	//BUG EN EMAIL
	public void TS7099_updateFirstName() {
		customerInformation page = new customerInformation(driver);
		page.modifyFirstName();
		waitFor(driver, (By.className("panel-heading")));		
		List<WebElement> text = driver.findElements(By.className("panel-heading"));
		Assert.assertTrue(text.get(0).getText().contains("Confirmación"));
		driver.switchTo().defaultContent();
		List<WebElement> tabs = driver.findElements(By.className("x-tab-strip-close"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", tabs.get(6));
		List<WebElement> frame4 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame4.get(5));
		List<WebElement> profileEdit1 = driver.findElements(By.className("profile-edit"));
		profileEdit1.get(0).click();
		driver.switchTo().defaultContent();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame5 = driver.findElements(By.tagName("iframe"));		
		driver.switchTo().frame(frame5.get(4));
		waitFor(driver, (By.id("FirstName")));		
		page.setDefaultValues();
		waitFor(driver, (By.className("panel-heading")));
	}
	
	//@Test(groups= "CustomerCare")	//BUG EN EMAIL
	public void TS7104_updateBirthDate() {
		customerInformation page = new customerInformation(driver);
		page.modifyBirthDate();
		waitFor(driver, (By.className("panel-heading")));		
		List<WebElement> text = driver.findElements(By.className("panel-heading"));
		Assert.assertTrue(text.get(0).getText().contains("Confirmación"));
		driver.switchTo().defaultContent();
		List<WebElement> tabs = driver.findElements(By.className("x-tab-strip-close"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", tabs.get(6));
		List<WebElement> frame4 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame4.get(5));
		List<WebElement> profileEdit1 = driver.findElements(By.className("profile-edit"));
		profileEdit1.get(0).click();
		driver.switchTo().defaultContent();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame5 = driver.findElements(By.tagName("iframe"));		
		driver.switchTo().frame(frame5.get(4));
		waitFor(driver, (By.id("FirstName")));		
		page.setDefaultValues();
		waitFor(driver, (By.className("panel-heading")));
	}
	
	//@Test(groups= "CustomerCare")	//BUG EN EMAIL
	public void TS7101_updateEmail() {
		customerInformation page = new customerInformation(driver);
		page.modifyEmail();
		waitFor(driver, (By.className("panel-heading")));		
		List<WebElement> text = driver.findElements(By.className("panel-heading"));
		Assert.assertTrue(text.get(0).getText().contains("Confirmación"));
		driver.switchTo().defaultContent();
		List<WebElement> tabs = driver.findElements(By.className("x-tab-strip-close"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", tabs.get(6));
		List<WebElement> frame4 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame4.get(5));
		List<WebElement> profileEdit1 = driver.findElements(By.className("profile-edit"));
		profileEdit1.get(0).click();
		driver.switchTo().defaultContent();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame5 = driver.findElements(By.tagName("iframe"));		
		driver.switchTo().frame(frame5.get(4));
		waitFor(driver, (By.id("FirstName")));		
		page.setDefaultValues();
		waitFor(driver, (By.className("panel-heading")));
	}
	
	//@Test(groups= "CustomerCare")	
	public void TS7100_updateLastName() {
		customerInformation page = new customerInformation(driver);
		page.modifyLastName();
		waitFor(driver, (By.className("panel-heading")));		
		List<WebElement> text = driver.findElements(By.className("panel-heading"));
		Assert.assertTrue(text.get(0).getText().contains("Confirmación"));
		driver.switchTo().defaultContent();
		List<WebElement> tabs = driver.findElements(By.className("x-tab-strip-close"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", tabs.get(6));
		List<WebElement> frame4 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame4.get(5));
		List<WebElement> profileEdit1 = driver.findElements(By.className("profile-edit"));
		profileEdit1.get(0).click();
		driver.switchTo().defaultContent();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame5 = driver.findElements(By.tagName("iframe"));		
		driver.switchTo().frame(frame5.get(4));
		waitFor(driver, (By.id("FirstName")));		
		page.setDefaultValues();
		waitFor(driver, (By.className("panel-heading")));
	}
	
	//@Test(groups= "CustomerCare")
	public void TS7150_verifyNumbersAreNotAllowedInFirstNameAndLastName() {
		customerInformation page = new customerInformation(driver);
		page.areNumbersAllowedInFirstNameAndLastName();
		driver.findElement(By.id("ClientInformation_nextBtn")).click();	
	}
	
	//@Test(groups= "CustomerCare")	//BUG EN EMAIL
	public void TS7182_modifyDniByTwoDigits() {
		customerInformation page = new customerInformation(driver);
		page.modifyDniBy("32645423");
		waitFor(driver, (By.className("panel-heading")));		
		List<WebElement> text = driver.findElements(By.className("panel-heading"));
		Assert.assertTrue(text.get(0).getText().contains("Confirmación"));
	}
	
	//@Test(groups= "CustomerCare")	//BUG EN EMAIL
	public void TS7186_modifyDniByOneDigits() {
		customerInformation page = new customerInformation(driver);
		page.modifyDniBy("32645422");
		waitFor(driver, (By.className("panel-heading")));		
		List<WebElement> text = driver.findElements(By.className("panel-heading"));
		Assert.assertTrue(text.get(0).getText().contains("Confirmación"));
	}
	
	@Test(groups= "CustomerCare")
	public void TS7207_verifyLettersAreNotAllowedInCuil() {
		driver.findElement(By.id("Cuil")).clear();
		driver.findElement(By.id("Cuil")).sendKeys("aaa");
		List <WebElement> element = driver.findElements(By.cssSelector(".error.ng-scope"));
		Assert.assertTrue(element.get(4).isDisplayed());
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
	}
	

	//@Test(groups= "CustomerCare")
	public void TS7097_verifyNonOwnershipChange() {
		driver.switchTo().defaultContent();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame6 = driver.findElements(By.tagName("iframe"));		
		driver.switchTo().frame(frame6.get(4));
		waitFor(driver, (By.id("FirstName")));
		customerInformation page = new customerInformation(driver);
		page.modifyCuil();
		Assert.assertTrue(page.notchansgetopname());
		
	}
	/*
	@Test(groups= "CustomerCare")
	public void TS7205_Cambios_en_la_Informacion_del_Cliente_Validar_Caracteres_Campo_Apellido() {
		driver.switchTo().defaultContent();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame6 = driver.findElements(By.tagName("iframe"));		
		driver.switchTo().frame(frame6.get(4));
		waitFor(driver, (By.id("FirstName")));
		customerInformation page = new customerInformation(driver);
		Assert.assertTrue(page.validarlimitecaracterapellido());
	}
	
	@Test(groups= "CustomerCare")
	public void TS7210_Cambios_en_la_Informacion_del_Cliente_Telefono_Alternativo_No_permite_letras() {
		driver.switchTo().defaultContent();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame6 = driver.findElements(By.tagName("iframe"));		
		driver.switchTo().frame(frame6.get(4));
		waitFor(driver, (By.id("FirstName")));
		customerInformation page = new customerInformation(driver);
		Assert.assertTrue(page.validarcaractertelefonoalternativo());

	}
	@Test(groups= "CustomerCare")
	public void TS7209_Cambios_en_la_Informacion_del_Cliente_Telefono_Movil_No_permite_letras() {
		driver.switchTo().defaultContent();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame6 = driver.findElements(By.tagName("iframe"));		
		driver.switchTo().frame(frame6.get(4));
		customerInformation page = new customerInformation(driver);
		Assert.assertTrue(page.validarcaractermovilalternativo());

	}
	
	@Test(groups= "CustomerCare")
	public void TS7151_Cambios_en_la_Informacion_del_Cliente_Validar_Nombre_Apellido_Que_tengan_caracteres_especiales() {
		driver.switchTo().defaultContent();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame6 = driver.findElements(By.tagName("iframe"));		
		driver.switchTo().frame(frame6.get(4));
		customerInformation page = new customerInformation(driver);
		Assert.assertTrue(page.validarcaracterespecialesNyA());

	}
	@Test(groups= "CustomerCare")
	public void TS12282_Reseteo_de_Claves_Manejo_de_la_Clave_Visualizar_Boton_Reseteo_Clave() {
		driver.switchTo().defaultContent();
		customerInformation page = new customerInformation(driver);
		page.validacionbtnreseteodeclave();
	}
	
	@Test(groups= "CustomerCare")//noterminado
	public void TS7161_Cambios_en_la_Informacion_del_Cliente_Validar_Teléfono_Movil_5_digitos_Codigo_de_area() {
		driver.switchTo().defaultContent();
		customerInformation page = new customerInformation(driver);
		page.EntrarEditarPerfil();
		page.ValidarDigitosDelMovil();	
	}
	*/
}
