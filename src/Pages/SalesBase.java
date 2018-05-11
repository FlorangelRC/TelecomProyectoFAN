package Pages;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.synth.SynthScrollBarUI;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Tests.TestBase;
import javafx.scene.control.Accordion;

public class SalesBase extends BasePage {
	
final WebDriver driver;
	
	@FindBy (how = How.ID, using = "phSearchInput")
	private WebElement searchInput;
	
	@FindBy (how = How.ID, using = "phSearchButton")
	private WebElement searchButton;
	
	@FindBy (how = How.ID, using = "userNavButton")
	private WebElement userSIT;
	
	@FindBy (how = How.CSS, using = ".menuButtonMenuLink.firstMenuItem")
	private WebElement myProfile;
	
	@FindBy (how = How.CLASS_NAME, using = "menuButtonMenuLink")
	private List<WebElement> userSITOptions;
	
	@FindBy (how = How.CSS, using = ".debugLogLink.menuButtonMenuLink")
	private WebElement developerConsole;
	
	@FindBy (how = How.CLASS_NAME, using = "brandZeronaryFgr")
	private WebElement helpAndInformation;
	
	@FindBy (how = How.ID, using = "tsidButton")
	private WebElement sales;
	
	@FindBy (how = How.CSS, using = ".menuButtonMenuLink.firstMenuItem")
	private WebElement services;
	
	@FindBy (how = How.CLASS_NAME, using = "menuButtonMenuLink")
	private List<WebElement> salesOptions;
	
	@FindBy (how = How.ID, using = "home_Tab")
	private WebElement homeTab;
	
	@FindBy (how = How.ID, using = "Contact_Tab")
	private WebElement contactsTab;
	
	@FindBy (how = How.ID, using = "Account_Tab")
	private WebElement accountsTab;
	
	@FindBy (how = How.ID, using = "Lead_Tab")
	private WebElement candidatesTab;
	
	@FindBy (how = How.ID, using = "Opportunity_Tab")
	private WebElement opportunitiesTab;
	
	@FindBy (how = How.ID, using = "Forecasting3_Tab")
	private WebElement forecastsTab;
	
	@FindBy (how = How.ID, using = "report_Tab")
	private WebElement reportsTab;
	
	@FindBy (how = How.ID, using = "Dashboard_Tab")
	private WebElement dashboardsTab;
	
	@FindBy (how = How.ID, using = "Chatter_Tab")
	private WebElement chatterTab;
	
	@FindBy (how = How.ID, using = "File_Tab")
	private WebElement filesTab;
	
	@FindBy (how = How.ID, using = "Product2_Tab")
	private WebElement productsTab;
	
	@FindBy (how = How.ID, using = "Quote_Tab")
	private WebElement budgetsTab;
	
	@FindBy (how = How.ID, using = "Order_Tab")
	private WebElement ordersTab;
	
	@FindBy (how = How.ID, using = "01rc0000000DZDq_Tab")
	private WebElement userSITTab;
	
	@FindBy (how = How.CLASS_NAME, using = "allTabsArrow")
	private WebElement allTabs;
	
	@FindBy (how = How.ID, using = "createNewButton")
	private WebElement createNewButton;
	
	@FindBy (how = How.CSS, using = ".menuButtonMenuLink.firstMenuItem.eventMru")
	private WebElement event;
	
	@FindBy (how = How.CSS, using = ".taskMru.menuButtonMenuLink")
	private WebElement task;
	
	@FindBy (how = How.CSS, using = ".contactMru.menuButtonMenuLink")
	private WebElement contact;
	
	@FindBy (how = How.CSS, using = ".accountMru.menuButtonMenuLink")
	private WebElement account;
	
	@FindBy (how = How.CSS, using = ".leadMru.menuButtonMenuLink")
	private WebElement candidate;
	
	@FindBy (how = How.CSS, using = ".opportunityMru.menuButtonMenuLink")
	private WebElement opportunity;
	
	@FindBy (how = How.CSS, using = ".reportMru.menuButtonMenuLink")
	private WebElement report;
	
	@FindBy (how = How.CSS, using = ".menuButtonMenuLink.contentSearchMru")
	private WebElement file;
	
	@FindBy (how = How.CSS, using = ".productMru.menuButtonMenuLink")
	private WebElement product;
	
	@FindBy (how = How.CSS, using = ".quoteMru.menuButtonMenuLink")
	private WebElement budget;
	
	@FindBy (how = How.CLASS_NAME, using = "recycleText")
	private WebElement paperCan;
	@FindBy (how = How.ID, using = "ContactFirstName")
	private WebElement firstname;
	
	@FindBy (how = How.ID, using = "ContactLastName")
	private WebElement lastname;
	
	@FindBy(how=How.ID,using = "AccountName")
	private WebElement razonsocial;
	
	@FindBy(how=How.ID, using ="AccountNumber")
	private WebElement numerodecuenta;
	
	@FindBy(how=How.ID, using ="Email")
	private WebElement Email;
	
	@FindBy(how= How.ID, using ="SearchClientDocumentType")
	private WebElement DNIbuscador;
	
	@FindBy (how =How.ID,using="SearchClientsDummy")
	private WebElement btnbuscar;
	
	@FindBy(how=How.ID, using="SearchClientDocumentNumber")
	private WebElement DNI;
	
	@FindBy(css = ".x-plain-header.sd_primary_tabstrip.x-unselectable .x-tab-strip-closable")
	private List<WebElement> pestaniasPrimarias;
	
public SalesBase(WebDriver driver){
		this.driver = driver;
        PageFactory.initElements(driver, this);
}

public void BusquedaAvanzada(){
	List<WebElement> busqueda = driver.findElements(By.className("slds-form-element__control"));	
	for(WebElement e: busqueda){
		
		if(e.getText().equals("B\u00fasqueda avanzada")){
			e.click();
			e.click();
			break;}}
	try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
}

public void BuscarAvanzada(String nombre, String apellido, String razon, String cuenta, String email){
	try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	BusquedaAvanzada();
	try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	firstname.sendKeys(nombre);
	lastname.sendKeys(apellido);
	razonsocial.sendKeys(razon);
	numerodecuenta.sendKeys(cuenta);
	Email.sendKeys(email);
	try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	driver.findElement(By.id("SearchClientsDummy")).click();
	try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

}
public void validarespacio(){
	try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	List<WebElement> resultado = driver.findElements(By.cssSelector(".slds-truncate.ng-binding"));
	String dato= resultado.get(1).getText();
	System.out.println(dato);
	dato=dato.replaceAll(" ", "ESTOESUNESPACIO");
	System.out.println(dato);

	Assert.assertTrue(dato.contains("ESTOESUNESPACIO"));
}

public void validarcamposbusqueda(){
	firstname.isDisplayed();
	lastname.isDisplayed();
	razonsocial.isDisplayed();
	numerodecuenta.isDisplayed();
	Email.isDisplayed();
}

public void BuscarCuenta(String Type, String NDNI){
	try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	setSimpleDropdown(DNIbuscador, Type);
	DNI.sendKeys(NDNI);
	sleep(2000);
	btnbuscar.click();
	try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

}


public boolean btnnoexiste(String boton){
	try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}


	   List<WebElement> creusr = driver.findElements(By.cssSelector(".slds-button.slds-button.slds-button--icon")); 
	   boolean e= false;
	     for(WebElement c : creusr) {
	    	if(c.getText().toLowerCase().equals(boton)){
	       e= true;}}
	  return e;}



 public void gestiondeusuarios(){
	 driver.navigate().back();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("userNavButton")).click();
		driver.findElement(By.xpath("//a[@href=\"/ui/setup/Setup\"]")).click();
		driver.findElement(By.id("setupSearch")).sendKeys("gestion");
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.xpath("//a[@href=\"/005?isUserEntityOverride=1&retURL=%2Fui%2Fsetup%2FSetup%3Fsetupid%3DUsers&setupid=ManageUsers\"]")).click();
//buscar cuenta
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		setSimpleDropdown(driver.findElement(By.id("fcf")), "Todos los usuarios");	
 }

 public void validarperfil(String nombre, String perfil){
	 boolean a= false;
	 int rep = 0;
	 List<WebElement> filas = driver.findElements(By.cssSelector(".dataCell"));
	 for(int i=0; i<filas.size();i++){
		 if (filas.get(i).getText().equals(nombre)){
			 a = true;
			 System.out.println(filas.get(i+1).getText());
			 if (nombre.contains("Nicolas")) {
				 if(rep == 1) {
					 Assert.assertTrue(filas.get(i+1).getText().contains(perfil));
					 break;}
				 rep++;
			 }else {
				 Assert.assertTrue(filas.get(i+1).getText().contains(perfil));
				 break;
			 }
			}} 
	 Assert.assertTrue(a);}
 
 public boolean validartxtbtn(String txt){
		try {Thread.sleep(30000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	 boolean a=false;
		if(driver.findElement(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand")).getText().equals(txt)){
			a= true;}
			return a; }
 
 public void agregarproductos(){
		try {Thread.sleep(30000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> asl = driver.findElements(By.cssSelector(".slds-button.slds-button_neutral.cpq-add-button"));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

		System.out.println(asl.size());
		for (WebElement e: asl){									
			if(e.getText().equals("Agregar")){
				e.click();
			}
		}}

 
 
 public void continuar(){
		try {Thread.sleep(30000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> cont = driver.findElements(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand"));
		for(WebElement c : cont){
			c.getText().equals("Continuar");
				c.click();
		}
 }
 
 public void validarpasos(){
	try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	 ArrayList<String> txt1 = new ArrayList<String>();
	 ArrayList<String> txt2 = new ArrayList<String>();
	 txt2.add("ASIGNACI\u00d3N DE L\u00cdNEA");
	 txt2.add("SELECCI\u00d3N DE MEDIO DE PAGO");
	 txt2.add("RESUMEN DE LA ORDEN DE VENTA");
	 txt2.add("ENV\u00cdO FACTURA Y DATOS DE COBRO");
	 txt2.add("INFORMACI\u00d3N");

	 List<WebElement> pasos = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
	 System.out.println(pasos.size());
	 for(WebElement e: pasos){
		 txt1.add(e.getText());
		 }
		 Assert.assertTrue(txt1.containsAll(txt2));
	
  }
 
 public void nuevocliente(){
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	 driver.findElement(By.id("dataInput_nextBtn")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

 }
 public void validarnuevocliente(String DNIType, String DNINumber){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Select DNI = new Select( driver.findElement(By.id("DocumentType")));
		
		
	WebElement NDNI = driver.findElement(By.id("DocumentNumber"));
	String Type =DNI.getFirstSelectedOption().getText();
	String numero=NDNI.getAttribute("value");
	System.out.println(Type);
	System.out.println(numero);
	Assert.assertTrue(Type.equals(DNIType));
	Assert.assertTrue(numero.equals(DNINumber));
	
 }
 public void validarentrarcatalogo(){
		try {Thread.sleep(50000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	 Assert.assertTrue(driver.findElement(By.cssSelector(".slds-col.cpq-product-cart-items.js-cpq-cart-scroll-container.scroll")).isDisplayed());
 }
 public void validaragrupados(){
	List<WebElement> lista = driver.findElements(By.className("ng-binding"));
	
	for(WebElement e: lista){
		if(e.getText().equals("Flavia Sales")){
			e.click();
			 Assert.assertTrue(driver.findElement(By.cssSelector(".slds-hint-parent.ng-scope")).isDisplayed());
			break;}}}
 
 public void validarcrearcuenta(){
acciondecontacto("nueva cuenta");
try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
for(String winHandle:driver.getWindowHandles()){
    driver.switchTo().window(winHandle);}
driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"\t");
try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
Assert.assertTrue(driver.findElement(By.cssSelector(".slds-page-header__title.vlc-slds-page-header__title.slds-truncate.ng-binding")).getText().equals("Crear Nuevo Cliente"));
 }
 
 
 public void acciondecontacto(String accion){
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		 driver.findElement(By.id("tab-scoped-3__item")).click();
		    sleep(3000);
	List<WebElement> btns = driver.findElements(By.cssSelector(".slds-button.slds-button.slds-button--icon"));
for(WebElement e: btns){
	if(e.getText().toLowerCase().equals(accion)){ 
		e.click();
		break;}}
 } 
 
 public void crearnuevocliente(String nombre, String apellido, String nacimiento){
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("dataInput_nextBtn")).click();
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//	setSimpleDropdown(driver.findElement(By.id("DocumentTypeSearch")), "DNI");
		//	try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("FirstName")).sendKeys(nombre);
		driver.findElement(By.id("LastName")).sendKeys(apellido);
		ContactSearch contact = new ContactSearch(driver);
		contact.sex("masculino");
		driver.findElement(By.id("Birthdate")).sendKeys(nacimiento);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("Contact_nextBtn")).click();
 }
 
 public void elegirplan(String plan){
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.cssSelector(".slds-button.custom-view-dropdown-button.slds-button_neutral.slds-p-right_small.slds-picklist__label.cpq-base-header-picklist-label")).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> list = driver.findElements(By.className("slds-dropdown__item"));
		System.out.println(list.size());
		for(WebElement e: list){
			if(e.getText().equals("Telecom Price List")){
				System.out.println(e.getText());
				e.click();
				break;}}
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid")).sendKeys(plan);		
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> agregar = driver.findElements(By.cssSelector(".slds-button.slds-button_neutral.cpq-add-button")); 
		agregar.get(0).click();
}
 public void agregarplan(String plan){
		try {Thread.sleep(30000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.cssSelector(".slds-button.custom-view-dropdown-button.slds-button_neutral.slds-p-right_small.slds-picklist__label.cpq-base-header-picklist-label")).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> list = driver.findElements(By.className("slds-dropdown__item"));
		System.out.println(list.size());
		for(WebElement e: list){
			if(e.getText().equals("Telecom Price List")){
				System.out.println(e.getText());
				e.click();
				break;}}
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid")).sendKeys(plan);		
		try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> agregar = driver.findElements(By.cssSelector(".slds-button.slds-button_neutral.cpq-add-button")); 
		agregar.get(1).click();
 }
 
 public void borrarcuenta(String nombre, String apellido){
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	 driver.get("https://crm--sit.cs14.my.salesforce.com/home/home.jsp");
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("Account_Tab")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> cuentas = driver.findElements(By.cssSelector(".dataCell"));
		for(WebElement e: cuentas){

			if(e.getText().equals(nombre+" "+apellido)){
				e.findElement(By.tagName("a")).click();
				try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
				WebElement btns = driver.findElement(By.className("pbButton"));
				btns.findElement(By.name("delete")).click();
				try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
				 Alert alert = driver.switchTo().alert();
				   alert.accept();
					try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
				break;}}}
 
 
 public void borrarcontacto(String apellido, String nombre){
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	 driver.get("https://crm--sit.cs14.my.salesforce.com/home/home.jsp");
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("Contact_Tab")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> cuentas = driver.findElements(By.cssSelector(".dataCell"));
		for(WebElement e: cuentas){
			if(e.getText().equals(apellido+", "+nombre)){
				e.findElement(By.tagName("a")).click();
				try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
				WebElement btns = driver.findElement(By.className("pbButton"));
				btns.findElement(By.name("del")).click();
				try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
				 Alert alert = driver.switchTo().alert();
				   alert.accept();
					try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
				break;}}}
 
 
 public void elegirvalidacion(String validacion){
	 //DOC SMS o QA
	 CustomerCare cc = new CustomerCare(driver);
	try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	List<WebElement> valid =driver.findElements(By.xpath("//input[@id='ValidationMethodInValidContact' and @type='radio']"));
	List<WebElement> radio = driver.findElements(By.cssSelector(".slds-radio--faux.ng-scope"));
	for(int i=0; i<valid.size();i++){
		String value=valid.get(i).getAttribute("value");
		if(value.equals(validacion)){
			cc.obligarclick(radio.get(i+2));
			break;}}
	try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	 driver.findElement(By.id("MethodSelection_nextBtn")).click();
 }
 
 public void seleccionarMetodoValidacion(String validacion){
	try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	List<WebElement> valid =driver.findElements(By.id("MethodSelection"));
	List<WebElement> radio = driver.findElement(By.id("MethodSelection|0")).findElements(By.cssSelector(".slds-radio--faux.ng-scope"));
	int i = -1;
	for(WebElement UnV: valid){
		System.out.println(UnV.getAttribute("value"));
		i++;
		if(UnV.getAttribute("value").equals(validacion)){
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+radio.get(i).getLocation().y+")");
			radio.get(i).click();
			break;}}
	try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	 driver.findElement(By.id("ValidationMethod_nextBtn")).click();
 }
 
 public void validacionProcesoVenta(String validacion){
	try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	List<WebElement> valid =driver.findElements(By.id("ValidationMethodValidContact"));
	List<WebElement> radio = driver.findElement(By.id("ValidationMethodValidContact|0")).findElements(By.cssSelector(".slds-radio--faux.ng-scope"));
	int i = -1;
	for(WebElement UnV: valid){
		System.out.println(UnV.getAttribute("value"));
		i++;
		if(UnV.getAttribute("value").toLowerCase().contains(validacion)){
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+radio.get(i).getLocation().y+")");
			radio.get(i).click();
			break;}}
	try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	 driver.findElement(By.id("MethodSelection_nextBtn")).click();
	 sleep(8000);
 }
 
 public void praguntasRespuestasLino(String resp1, String resp2){
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement valid =driver.findElement(By.id("tree0-node1"));
		List<WebElement> radio = valid.findElements(By.className("slds-radio"));
		for(WebElement UnV: radio){
			System.out.println(UnV.findElement(By.cssSelector(".slds-form-element__label.ng-binding")).getText());
			if(UnV.findElement(By.cssSelector(".slds-form-element__label.ng-binding")).getText().equalsIgnoreCase(resp1)){	
				UnV.findElement(By.className("slds-radio--faux")).click();
				break;}
		}
		
		valid =driver.findElements(By.id("tree0-node1")).get(1);
		radio = valid.findElements(By.className("slds-radio"));
		for(WebElement UnV: radio){
			System.out.println(UnV.findElement(By.cssSelector(".slds-form-element__label.ng-binding")).getText());
			if(UnV.findElement(By.cssSelector(".slds-form-element__label.ng-binding")).getText().equalsIgnoreCase(resp2)){	
				UnV.findElement(By.className("slds-radio--faux")).click();
				break;}
		}
		
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		 driver.findElement(By.id("QAQuestions_nextBtn")).click();
		 sleep(6000);
	 }
 

 public void subirdoc(){
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	//driver.findElement(By.id("FileDocumentImage")).click();
	try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
try{	driver.findElement(By.id("alert-ok-button")).click();	} catch (NoSuchElementException e) {};
	try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	driver.findElement(By.id("FileDocumentImage")).click();


	try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	 driver.switchTo().activeElement().sendKeys("C:\\Users\\Sofia Chardin\\Desktop\\DNI.png");
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}


		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	 driver.findElement(By.id("DocumentMethod_nextBtn")).click();
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	    
		    
	 driver.findElement(By.id("ValidationResult_nextBtn")).click();
	
 }
 
 public void Crear_DatosDelCliente(String impositiva){
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	 setSimpleDropdown(driver.findElement(By.id("ImpositiveCondition")), impositiva);
	// driver.findElement(By.id("IdPhone")).sendKeys(telefono);;
	try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

 }
 public void Crear_DomicilioLegal(String provincia, String localidad, 
		 String calle, String local, String altura, String piso, String dpto,
		 String CP){
	
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> check = driver.findElements(By.cssSelector(".slds-checkbox--faux"));

	 setSimpleDropdown(driver.findElement(By.id("State")), provincia);

	driver.findElement(By.id("CityTypeAhead")).sendKeys(localidad);
	try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
	driver.findElement(By.id("CityTypeAhead")).sendKeys(Keys.ARROW_DOWN);
	driver.findElement(By.id("CityTypeAhead")).sendKeys(Keys.ENTER);
	driver.findElement(By.id("LegalStreetTypeAhead")).sendKeys(calle); 
	try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	driver.findElement(By.id("LegalStreetTypeAhead")).sendKeys(Keys.ARROW_DOWN);
	driver.findElement(By.id("LegalStreetTypeAhead")).sendKeys(Keys.ENTER);
	driver.findElement(By.id("NewStreetName")).sendKeys(calle);
	

	switch(local){
	case "si":
		if(!driver.findElement(By.id("Local")).isSelected()){
			check.get(0).click();}
		break;
	case "no":
		if(driver.findElement(By.id("Local")).isSelected()){
			check.get(0).click();}
		break;}
	driver.findElement(By.id("StreetNumber")).sendKeys(altura);
	driver.findElement(By.id("FloorNumber")).sendKeys(piso);
	driver.findElement(By.id("Department")).sendKeys(dpto);
	driver.findElement(By.id("PostalCodeTypeAhead")).sendKeys(CP);
	try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	driver.findElement(By.id("PostalCodeTypeAhead")).sendKeys(Keys.ARROW_DOWN);
	driver.findElement(By.id("PostalCodeTypeAhead")).sendKeys(Keys.ARROW_DOWN); 
	driver.findElement(By.id("PostalCodeTypeAhead")).sendKeys(Keys.ENTER);
	try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	driver.findElement(By.id("NewPostalCodeName")).sendKeys(CP);
	driver.findElement(By.id("btnSameAsLegalAddress")).click();
	try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	if(CP!="6666")
	driver.findElement(By.id("AccountData_nextBtn")).click();
 }
 
 
 	public void Crear_CopiarDatosLegal(){
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

 		List<WebElement> check = driver.findElements(By.cssSelector(".slds-checkbox--faux"));
 		
 		if(!driver.findElement(By.id("CopyLegalAddress")).isSelected()){
 			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+check.get(3).getLocation().y+")");
			check.get(3).click();}
 	}
 	
 	public void error(){
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
 	try{	if(driver.findElement(By.id("SaveContactValidationDOC")).isDisplayed()){
 			List<WebElement> btn = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope"));
 			for(WebElement e:btn){
 				if(e.getText().equals("Continue")){
 					e.click();
 				}}}	} catch (NoSuchElementException e) {}}
 	public void error2(){
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
 	try{	if(driver.findElement(By.id("CheckAndSaveDecisiveLine")).isDisplayed()){
 			List<WebElement> btn = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope"));
 			for(WebElement e:btn){										
 				if(e.getText().equals("Continue")){
 					e.click();
 				}}}	} catch (NoSuchElementException e) {}}
 	
	public void Crear_DomicilioFacturacion(String provincia, String localidad, 
			 String calle, String local, String altura, String piso, String dpto,
			 String CP){
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

		List<WebElement> check = driver.findElements(By.cssSelector(".slds-checkbox--faux"));
		 setSimpleDropdown(driver.findElement(By.id("BillingState")), provincia);

			driver.findElement(By.id("BillingCityTypeAhead")).sendKeys(localidad);
			driver.findElement(By.id("BillingStreetTypeAHead")).sendKeys(calle);
			switch(local){
			case "si":
				if(!driver.findElement(By.id("BillingLocal")).isSelected()){
				check.get(2).click();}
				break;
			case "no":
				if(driver.findElement(By.id("BillingLocal")).isSelected()){
					check.get(2).click();}
				break;}
			driver.findElement(By.id("BillingStreetNumber")).sendKeys(altura);
			driver.findElement(By.id("BillingFloor")).sendKeys(piso);
			driver.findElement(By.id("BillingDepartment")).sendKeys(dpto);
			driver.findElement(By.id("BillingPostalCodeTypeAhead")).sendKeys(CP);}
		
	
	public void Crear_InfoAdicional(){
			
		}
	
	public void BtnCrearNuevoCliente(){
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		boolean existe = false;
		BasePage dni = new BasePage(driver);
		do {
			Random aleatorio = new Random(System.currentTimeMillis());
			aleatorio.setSeed(System.currentTimeMillis());
			int intAleatorio = aleatorio.nextInt(8999999)+1000000;
			dni.setSimpleDropdown(driver.findElement(By.id("SearchClientDocumentType")),"DNI");
			driver.findElement(By.id("SearchClientDocumentNumber")).click();
			driver.findElement(By.id("SearchClientDocumentNumber")).sendKeys(Integer.toString(intAleatorio));
			driver.findElement(By.id("SearchClientsDummy")).click();
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			List <WebElement> cc = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
			for (WebElement x : cc) {
				if (x.getText().toLowerCase().contains("+ crear nuevo cliente")) {
					x.click();
					existe = true;
					break;
				}
			}
		}while(existe == false);
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	
	
		public void AsignarLinea(){
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			List<WebElement> bsqda = driver.findElements(By.cssSelector(".slds-form-element__label--toggleText.ng-binding"));
			System.out.println(bsqda.size());
			for(WebElement e: bsqda){
				System.out.println(e.getText());
				if(e.getText().equals("Modificar b�squeda")){
					e.click();
					break;}}
		driver.findElement(By.id("LineAssignment_nextBtn")).click();
		try {Thread.sleep(50000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
}
		public void SimulacionDeFactura(){
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.findElement(By.id("Step_Error_Huawei_S202_nextBtn")).click();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.findElement(By.id("Step_Error_Huawei_S015_nextBtn")).click();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
}
		
		public void verificarOrdenICCID(){
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

			String orden;
			WebElement NdOrden= driver.findElement(By.xpath("//*[@id='OrderStatus']/div/p/p[1]"));
			System.out.println("text "+NdOrden.getText());
			System.out.println("value "+NdOrden.getAttribute("value"));

			orden = NdOrden.getText();
			System.out.println(orden);
			
			
		}
		
		public void selectMatrix(String letra, String matrix) {
			List <WebElement> letras = driver.findElements(By.className("listItemPad"));
			for (WebElement x : letras) {
				if (x.getText().toLowerCase().equals(letra.toLowerCase())) {
					x.click();
					break;
				}
			}
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			WebElement body = driver.findElement(By.className("x-grid3-body"));
			List <WebElement> lista = body.findElements(By.tagName("tr"));
			WebElement leo= lista.get(0);
			for (WebElement x : lista) {
				//System.out.println("fffff"+x.findElements(By.tagName("td")).get(3).getText());
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+lista.get(lista.size()-1).getLocation().y+")");
				if (x.findElements(By.tagName("td")).get(3).getText().toLowerCase().contains(matrix.toLowerCase())) {
					leo = x;
				}
			}
			leo.findElements(By.tagName("td")).get(3).findElement(By.tagName("span")).click();
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		}
		
		public void volverEnNominaciones(WebDriver driver, int cant) {
			WebElement ant = driver.findElement(By.id("Contact_prevBtn"));
			CustomerCare CC = new CustomerCare(driver);
			if (cant >= 5) {
				driver.findElement(By.id("FinishProcess_prevBtn")).click();
				try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
			}
			if (cant >= 4) {
				driver.findElement(By.id("AccountData_prevBtn")).click();
				try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
			}
			if (cant >= 3) {
				driver.findElement(By.id("DocumentMethod_prevBtn")).click();
				try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
			}
			if (cant >= 2) {
				driver.findElement(By.id("ValidationMethod_prevBtn")).click();
				try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			}
			if (cant >= 1) {
				CC.obligarclick(ant);
				try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			}
			driver.findElement(By.id("DocumentInputSearch")).clear();
		}
		
		public void refrescar(WebDriver driver) {
			driver.navigate().refresh();
		}
		
		public void cerrarTodasLasPestanias() {
			driver.switchTo().defaultContent();
			if (pestaniasPrimarias.size() > 0) {
				for (WebElement t : pestaniasPrimarias) {
						WebElement btn_cerrar = t.findElement(By.className("x-tab-strip-close"));
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn_cerrar);	
				}
			}
		}
		
		public void DesloguearLoguear(String perfil) {
			sleep(5000);
			TestBase TB= new TestBase();
			driver.get("https://crm--sit.cs14.my.salesforce.com/home/home.jsp?tsid=02u41000000QWha/");
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.findElement(By.id("userNavButton")).click();
			sleep(2000);
			driver.findElement(By.id("userNav-menuItems")).findElements(By.tagName("a")).get(4).click();
			sleep(4000);
			driver.get("https://crm--sit.cs14.my.salesforce.com/");
			driver.findElement(By.id("cancel_idp_hint")).click();
			 switch(perfil){
			 case "dani":
				TB.login(driver);
				break;
			 case "agente":
				 TB.loginAndres(driver);
				 break;
			 case "call":
				 TB.loginElena(driver);  
				 break;
			 case "venta":
				 TB.loginFranciso(driver);
				 break;
			 case "logistica":
				 TB.loginNicolas(driver);
				 break;
			 case "entregas":
				 TB.loginMarcela(driver);
				 break;
			 case "fabiana":
				 TB.loginFabiana(driver);
				 break;
			 }
			 sleep(10000);
		}
 }