package Tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.ContactSearch;
import Pages.CustomerCare;
import Pages.HomeBase;
import Pages.SalesBase;
import Pages.setConexion;

public class SalesNominaciones extends TestBase{

	@BeforeClass
	public void Init() {
		this.driver = setConexion.setupEze();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		loginFranciso(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		HomeBase homePage = new HomeBase(driver);
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    String a = driver.findElement(By.id("tsidLabel")).getText();
	    if (a.contains("Ventas")){}
	    else {
	    	homePage.switchAppsMenu();
	    	try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	homePage.selectAppFromMenuByName("Ventas");
	    	try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}            
	    }
	    driver.findElement(By.xpath("//a[@href=\'https://crm--sit--c.cs14.visual.force.com/apex/taClientSearch']")).click();		
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada("Cliente", "Wholesale", "", "", "");
		WebElement cli = driver.findElement(By.id("tab-scoped-2"));
		if (cli.getText().contains("Cliente Wholesale")) {
			cli.click();
		}
		sleep(3000);
		WebElement cua = driver.findElement(By.id("tab-scoped-2")).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(6).findElement(By.tagName("svg"));
		cua.click();
		sleep(10000);
		
	}

	//@AfterMethod
	public void IceB() {
		driver.navigate().refresh();
	}
	
	//@AfterClass
	public void Exit() {
		driver.quit();
		sleep(2000);
	}
	
	@Test(groups = "Sales") 
	  public void TS76150_Nominacion_Argentino_Nominar_personas_mayores_a_16_anios_cliente_mayor_de_edad_con_linea_existente_plan_repro(){
		CustomerCare CC = new CustomerCare(driver);
		ContactSearch contact = new ContactSearch(driver);
		boolean b = false;
		contact.searchContact("DNI", "10000018", "femenino");
		sleep(6000);
		driver.findElement(By.cssSelector(".slds-input.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys("algoaqui@yahoo.com.ar");
		CC.obligarclick(driver.findElement(By.id("Contact_nextBtn")));
		sleep(3000);
		List<WebElement> vali = driver.findElements(By.cssSelector(".slds-page-header__title.vlc-slds-page-header__title.slds-truncate.ng-binding"));
		for(WebElement v : vali){
			if(v.getText().toLowerCase().contains("validaci\u00f3n de identidad")){
				b = true;
				System.out.println(v.getText());
			}
		}
		Assert.assertTrue(b);
	}
	
	@Test(groups = "Sales")
	public void TS76149_Nominacion_Argentino_Nominar_personas_mayores_a_16_anios_cliente_menor_de_edad_sin_linea_existente_plan_repro(){
		CustomerCare CC = new CustomerCare(driver);
		ContactSearch contact = new ContactSearch(driver);
		boolean b = false;
		contact.searchContact("DNI", "1600000", "femenino");
		sleep(6000);
		//driver.findElement(By.cssSelector(".slds-input.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys("menoredad@gmail.com");
		CC.obligarclick(driver.findElement(By.id("Contact_nextBtn")));
		sleep(3000);
		List<WebElement> vali = driver.findElements(By.cssSelector(".slds-page-header__title.vlc-slds-page-header__title.slds-truncate.ng-binding"));
		for(WebElement v : vali){
			if(v.getText().toLowerCase().contains("validaci\u00f3n de identidad")){
				b = true;
				System.out.println(v.getText());
			}
		}
		Assert.assertTrue(b);
	}
	@Test(groups = "Sales")
	public void TS75962_Nominacion_Argentino_Verificar_que_se_solicite_adjuntar_img_del_Doc_de_identidad(){
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("DNI", "10000019", "masculino");
		sleep(5000);
		driver.findElement(By.cssSelector(".slds-input.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).click();
		sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"EmailSelectableItems\"]/div/ng-include/div/ng-form/div[1]/div[1]/input")).sendKeys("asdasd@gmail.com");
		driver.findElement(By.id("Contact_nextBtn")).click();
		sleep(7000);
		List <WebElement> valdni = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : valdni) {
			if (x.getText().toLowerCase().contains("validaci\u00f3n por documento de identidad")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("ValidationMethod_nextBtn")).click();
		sleep(7000);
		driver.findElement(By.id("FileDocumentImage")).sendKeys("C:\\Users\\Sofia Chardin\\Desktop\\DNI.png");
		driver.findElement(By.id("DocumentMethod_nextBtn")).click();
		sleep(7000);
		boolean b = false;
		List<WebElement> vali = driver.findElements(By.cssSelector(".slds-page-header__title.vlc-slds-page-header__title.slds-truncate.ng-binding"));
		for(WebElement v : vali){
			if(v.getText().toLowerCase().contains("datos de la cuenta")){
				b = true;
				System.out.println(v.getText());
			}
		}
		Assert.assertTrue(b);
	}
	
	@Test(groups = "Sales")
	public void TS76061_SalesCPQ_Nominacion_Argentino_Verificar_Formulario_De_Documentacion(){
		boolean a= false;
		SalesBase SB = new SalesBase(driver);
		CustomerCare CC = new CustomerCare(driver);
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("DNI", "10000018", "femenino");
		sleep(6000);
		driver.findElement(By.cssSelector(".slds-input.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys("algoaqui@yahoo.com.ar");
		CC.obligarclick(driver.findElement(By.id("Contact_nextBtn")));
		sleep(5000);
		SB.seleccionarMetodoValidacion("DOC");
	}
	
	@Test(groups = "Sales")
	public void TS95135_Nominacion_Argentino_Verificar_solicitud_de_datos_para_la_nominacion() {
		sleep(5000);
		boolean a = false;
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-form-element__label.vlc-slds-inline-control__label.ng-binding"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("g\u00e9nero")) {
				a = true;
			}
		}
		Assert.assertTrue(driver.findElement(By.id("DocumentTypeSearch")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("DocumentInputSearch")).isEnabled());
		Assert.assertTrue(a);
	}
	
	@Test(groups = "Sales")
	public void TS95140_Nominacion_Argentino_Verificar_creacion_de_la_cuenta() {
		ContactSearch contact = new ContactSearch(driver);
		SalesBase SB = new SalesBase(driver);
		contact.searchContact("DNI", "10000019", "masculino");
		contact.ingresarMail("asdads@gmail.com", "si");
		List<WebElement> valdni = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : valdni) {
			if (x.getText().toLowerCase().contains("por documento de identidad")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("ValidationMethod_nextBtn")).click();
		sleep(7000);
		driver.findElement(By.id("FileDocumentImage")).sendKeys("C:\\Users\\Nicolas\\Desktop\\descarga.jpg");
		sleep(3000);
		driver.findElement(By.id("DocumentMethod_nextBtn")).click();
		sleep(7000);
		BasePage bp = new BasePage(driver);
		bp.setSimpleDropdown(driver.findElement(By.id("ImpositiveCondition")), "IVA Consumidor Final");
		SB.Crear_DomicilioLegal("Buenos Aires", "aba", "falsa", "", "1000", "", "", "1549");
		sleep(20000);
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope"));
		boolean a = false;
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("¡nominaci\u00f3n exitosa!")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
	}
}
  	//DONDE APARECEN LAS LINEAS PREPAGAS DEL CLIENTE
	

	
	
	
		
