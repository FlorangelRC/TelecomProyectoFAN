package Tests;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.CustomerCare;
import Pages.customerInformation;
import Pages.setConexion;

public class customerInformationUpdates extends TestBase {
	
	private WebDriver driver;


	@AfterClass(groups = {"CustomerCare", "ActualizarDatos"})
	public void tearDown2() {
		driver.close();	
	}

	@AfterMethod(groups = {"CustomerCare", "ActualizarDatos"})
	public void tearDown() {		
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BasePage cambioFrameByID=new BasePage();
	    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("alert-ok-button")));
		driver.findElement(By.id("alert-ok-button")).click();
	}
	
	@BeforeClass(groups = {"CustomerCare", "ActualizarDatos"})
	public void init() throws Exception{
		this.driver = setConexion.setupEze();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		login(driver);
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
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
	 	try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		CustomerCare page = new CustomerCare(driver);
		page.cerrarultimapestaña();
		page.elegircuenta("aaaaFernando Care");
		BasePage cambioFrameByID=new BasePage();
	    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("profile-edit")));
		List <WebElement> actualizar = driver.findElements(By.className("profile-edit"));
		for (WebElement x : actualizar) {
			if (x.getText().toLowerCase().contains("actualizar datos")) {
				x.click();
			}
		}
	 } 
	 
	 @BeforeMethod(groups = {"CustomerCare", "ActualizarDatos"})
	 public void setup(){
		 try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		 BasePage cambioFrameByID=new BasePage();
		 driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("LastName")));
	 }

	 
	@Test(groups = {"CustomerCare", "ActualizarDatos"})	
	public void TS7175_isLastNameMandatory() {
		driver.findElement(By.id("LastName")).clear();
		List <WebElement> element = driver.findElements(By.cssSelector(".error.ng-scope"));
		Assert.assertTrue(element.get(1).isDisplayed());
		driver.findElement(By.id("ClientInformation_nextBtn")).click();		
	}
	
	
	@Test(groups = {"CustomerCare", "ActualizarDatos"})	
	public void TS7174_isFirstNameMandatory() {
		driver.findElement(By.id("FirstName")).clear();
		List <WebElement> element = driver.findElements(By.cssSelector(".error.ng-scope"));
		Assert.assertTrue(element.get(0).isDisplayed());
		driver.findElement(By.id("ClientInformation_nextBtn")).click();		
	}
	
	
	@Test(groups = {"CustomerCare", "ActualizarDatos"})	
	public void TS7173_isEmailMandatory() {
		driver.findElement(By.id("Email")).clear();
		List <WebElement> element = driver.findElements(By.cssSelector(".error.ng-scope"));
		Assert.assertTrue(element.get(7).isDisplayed());
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
	}

	
	@Test(groups = {"CustomerCare", "ActualizarDatos"})	
	public void TS7170_isDocumentMandatory() {
		Assert.assertTrue(driver.findElement(By.id("DocumentNumber")).isDisplayed());
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
	}

	
	@Test(groups = {"CustomerCare", "ActualizarDatos"})	
	public void TS7171_isBirthDateMandatory() {
		driver.findElement(By.id("Birthdate")).clear();
		List <WebElement> element = driver.findElements(By.cssSelector(".error.ng-scope"));
		Assert.assertTrue(element.get(5).isDisplayed());
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
	}
	
	
	@Test(groups = {"CustomerCare", "ActualizarDatos"})	
	public void TS7169_isGenderMandatory() {
		BasePage x = new BasePage(driver);
		x.setSimpleDropdown(driver.findElement(By.id("Gender")), "-- Clear --");
		List <WebElement> element = driver.findElements(By.cssSelector(".error.ng-scope"));
		Assert.assertTrue(element.get(6).isDisplayed());
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
	}
	

	@Test(groups = {"CustomerCare", "ActualizarDatos"})
	public void TS7172_isMobilePhoneMandatory() {
		driver.findElement(By.id("MobilePhone")).clear();
		List <WebElement> element = driver.findElements(By.cssSelector(".error.ng-scope"));
		Assert.assertTrue(element.get(8).isDisplayed());
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
	}
	
	
	//@Test(groups = {"CustomerCare", "ActualizarDatos"})	//BUG EN DNI
	public void TS7149_fieldsWhichDontTriggerIdentityValidationProcess() {
		customerInformation page = new customerInformation(driver);	
		page.setDefaultValues();
		waitFor(driver, (By.className("panel-heading")));		
	}
	
	//@Test(groups = {"CustomerCare", "ActualizarDatos"})	//BUG EN DNI
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
	
	//@Test(groups = {"CustomerCare", "ActualizarDatos"})	//BUG EN DNI
	public void TS7177_modifyThreeFieldsWhichTriggerIdentityValidationProcess() {
		customerInformation page = new customerInformation(driver);
		page.setThreeFieldsWhichTriggerIdentityValidationProcess();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> text = driver.findElements(By.cssSelector(".ng-binding.ng-scope"));
		Assert.assertEquals("No se pueden modificar Género, Número de documento y Fecha de Nacimiento al mismo tiempo.", text.get(3).getText());
	}
	
	@Test(groups = {"CustomerCare", "ActualizarDatos"})
	public void TS7153_verifyBirthDateHasValidDateFormat() {
		WebElement element = driver.findElement(By.id("Birthdate"));
		String fecha = element.getAttribute("vlc-slds-model-date-format");
		Assert.assertTrue(fecha.contains("dd/MM/yyyy"));
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
	}
	
	@Test(groups = {"CustomerCare", "ActualizarDatos"})
	public void TS7155_validateBirthDateHasAYearPicker() {
		driver.findElement(By.id("Birthdate")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(driver.findElement(By.cssSelector(".datepicker.-bottom-left-.-from-bottom-")).isDisplayed());
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
	}
	
	//@Test(groups = {"CustomerCare", "ActualizarDatos"})	//NO SE PUEDE HACER POR DNI
	public void TS7183_modifyDocumentTwiceInAMonth() {
		customerInformation page = new customerInformation(driver);
		try{Assert.assertFalse(page.isDocumentModifyable());} catch (Exception e){}
		page.modifyDocument("32645423");
		waitFor(driver, (By.className("panel-heading")));		
		//List<WebElement> text = driver.findElements(By.className("panel-heading"));
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
	
	
	@Test(groups = {"CustomerCare", "ActualizarDatos"})
	public void TS7098_cancelUpdateInformation() {
		BasePage cambioFrameByID=new BasePage();
		CustomerCare page = new CustomerCare(driver);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")));
		List <WebElement> cancelar = driver.findElements(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope"));
		for (WebElement x : cancelar) {
			if (x.getText().toLowerCase().contains("cancelar")) {
				x.click();
			}
		}
	    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("alert-ok-button")));
		driver.findElement(By.id("alert-ok-button")).click();
		page.cerrarultimapestaña();
		page.elegircuenta("aaaaFernando Care");
	    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("profile-edit")));
		List <WebElement> actualizar = driver.findElements(By.className("profile-edit"));
		for (WebElement x : actualizar) {
			if (x.getText().toLowerCase().contains("actualizar datos")) {
				x.click();
			}
		}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("profile-box")));
		List <WebElement> actualizar1 = driver.findElements(By.className("profile-edit"));
		actualizar1.get(0).click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("ClientInformation_nextBtn")));
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
	}
	
	//@Test(groups = {"CustomerCare", "ActualizarDatos"})	//BUG EN celular
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
	
	//@Test(groups = {"CustomerCare", "ActualizarDatos"})	//BUG EN EMAIL
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
	
	//@Test(groups = {"CustomerCare", "ActualizarDatos"})	//BUG EN EMAIL
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
	
	//@Test(groups = {"CustomerCare", "ActualizarDatos"})	//BUG EN EMAIL
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
	
	//@Test(groups = {"CustomerCare", "ActualizarDatos"})	//BUG EN EMAIL
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
	
	//@Test(groups = {"CustomerCare", "ActualizarDatos"})	
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
	
	//@Test(groups = {"CustomerCare", "ActualizarDatos"})
	public void TS7150_verifyNumbersAreNotAllowedInFirstNameAndLastName() {
		customerInformation page = new customerInformation(driver);
		page.areNumbersAllowedInFirstNameAndLastName();
		driver.findElement(By.id("ClientInformation_nextBtn")).click();	
	}
	
	//@Test(groups = {"CustomerCare", "ActualizarDatos"})	//BUG EN EMAIL
	public void TS7182_modifyDniByTwoDigits() {
		customerInformation page = new customerInformation(driver);
		page.modifyDniBy("32645423");
		waitFor(driver, (By.className("panel-heading")));		
		List<WebElement> text = driver.findElements(By.className("panel-heading"));
		Assert.assertTrue(text.get(0).getText().contains("Confirmación"));
	}
	
	//@Test(groups = {"CustomerCare", "ActualizarDatos"})	//BUG EN EMAIL
	public void TS7186_modifyDniByOneDigits() {
		customerInformation page = new customerInformation(driver);
		page.modifyDniBy("32645422");
		waitFor(driver, (By.className("panel-heading")));		
		List<WebElement> text = driver.findElements(By.className("panel-heading"));
		Assert.assertTrue(text.get(0).getText().contains("Confirmación"));
	}
	
	@Test(groups = {"CustomerCare", "ActualizarDatos"})
	public void TS7207_verifyLettersAreNotAllowedInCuil() {
		driver.findElement(By.id("Cuil")).clear();
		driver.findElement(By.id("Cuil")).sendKeys("aaa");
		List <WebElement> element = driver.findElements(By.cssSelector(".error.ng-scope"));
		Assert.assertTrue(element.get(4).isDisplayed());
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
	}
	

	//@Test(groups = {"CustomerCare", "ActualizarDatos"})
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
		
	@Test(groups = {"CustomerCare", "ActualizarDatos"})
		public void TS7208_Profile_Changes_Cambios_En_La_Informacion_Del_Cliente_Validar_Caracteres_Campo_Email(){
		driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys("pruebapruebapruebapruebapruebapruebapruebapruebapruebapruebaprueb@telecom");
		String CM = driver.findElement(By.id("Email")).getAttribute("class");
		assertTrue(CM.equals("slds-input form-control ng-touched ng-dirty ng-invalid ng-not-empty ng-invalid-email ng-valid-required ng-invalid-remove ng-valid-email-add ng-invalid-email-remove"));
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
	}
	
	@Test(groups = {"CustomerCare", "ActualizarDatos"})
	public void TS7179_Profile_Changes_Validacion_Correo_Electronico_Creacion_De_Caso_Al_Cambiar_Correo_Electronico(){
		driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys("pruebaat@gmail.com");
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("ClientInformation_nextBtn")).getLocation().y+")");
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		CustomerCare page = new CustomerCare(driver);
		page.elegircaso();
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
		List<WebElement> camposOrden = driver.findElement(By.className("x-grid3-viewport")).findElement(By.tagName("tr")).findElements(By.tagName("td"));
		camposOrden.remove(0);
		for (WebElement UnCO : camposOrden) {
			if (UnCO.findElement(By.tagName("div")).getAttribute("title").toLowerCase().equals("due date")){
				UnCO.click();
				try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
				UnCO.click();
				try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
				break;
			}
		}
		assertTrue(driver.findElement(By.cssSelector(".x-grid3-row.x-grid3-row-first")).findElements(By.tagName("td")).get(4).findElement(By.tagName("span")).getText().toLowerCase().equals("actualización de datos"));
		assertTrue(driver.findElement(By.cssSelector(".x-grid3-row.x-grid3-row-first")).findElement(By.cssSelector(".x-grid3-cell-inner.x-grid3-col-CASES_STATUS")).getText().toLowerCase().equals("nuevo"));
	}
		
	@Test(groups = {"CustomerCare", "ActualizarDatos"})
	public void TS7205_Cambios_en_la_Informacion_del_Cliente_Validar_Caracteres_Campo_Apellido() {
		driver.findElement(By.id("LastName")).clear();
		driver.findElement(By.id("LastName")).sendKeys("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"a1zc0000003XKPMAA4-4\"]/div[1]/div/child[3]/div/ng-form/div[2]/div[2]/div/div[2]/small[8]")).isDisplayed());
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
	}
	
	@Test(groups = {"CustomerCare", "ActualizarDatos"})
	public void TS7210_Cambios_en_la_Informacion_del_Cliente_Telefono_Alternativo_No_permite_letras() {
		driver.findElement(By.id("OtherPhone")).clear();
		driver.findElement(By.id("OtherPhone")).sendKeys("aaaaaaaaaa");
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"a1zc0000003XKPMAA4-4\"]/div[1]/div/child[14]/div/ng-form/div[2]/div[2]/div/div[2]")).isDisplayed());
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
	}
	
	@Test(groups = {"CustomerCare", "ActualizarDatos"})
	public void TS7209_Cambios_en_la_Informacion_del_Cliente_Telefono_Movil_No_permite_letras() {
		driver.findElement(By.id("MobilePhone")).clear();
		driver.findElement(By.id("MobilePhone")).sendKeys("aaaaaaaaaaa");
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"a1zc0000003XKPMAA4-4\"]/div[1]/div/child[11]/div/ng-form/div[2]/div[2]/div/div[2]")).isDisplayed());
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
	}
	
	@Test(groups = {"CustomerCare", "ActualizarDatos"})
	public void TS12282_Reseteo_de_Claves_Manejo_de_la_Clave_Visualizar_Boton_Reseteo_Clave() {
		BasePage cambioFrameByID=new BasePage();
	    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("profile-edit")));
		List <WebElement> element = driver.findElements(By.className("profile-edit"));
		boolean a = false;
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("reseteo clave")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("LastName")));
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
	}
	
	/*@Test(groups = {"CustomerCare", "ActualizarDatos"}) //No reconoce los 5 digitos de codigo de area
	public void TS7161_Cambios_en_la_Informacion_del_Cliente_Validar_Teléfono_Movil_5_digitos_Codigo_de_area() {
		BasePage cambioFrameByID=new BasePage();
	    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("ClientInformation_nextBtn")));
	    Assert.assertTrue(false);
	    driver.findElement(By.id("ClientInformation_nextBtn")).click();
	}
	
	@Test(groups = {"CustomerCare", "ActualizarDatos"}) //No valida el error de los caracteres especiales
	public void TS7151_Cambios_en_la_Informacion_del_Cliente_Validar_Nombre_Apellido_Que_tengan_caracteres_especiales() {
		driver.findElement(By.id("FirstName")).clear();
	    driver.findElement(By.id("LastName")).clear();
		driver.findElement(By.id("FirstName")).sendKeys("/*!#$");
	    driver.findElement(By.id("LastName")).sendKeys("/*!#$");
	    Assert.assertTrue(false);
	    driver.findElement(By.id("ClientInformation_nextBtn")).click();
	}*/
}
