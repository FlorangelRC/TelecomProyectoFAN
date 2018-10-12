package Tests;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.ContactSearch;
import Pages.CustomerCare;
import Pages.Marketing;
import Pages.SalesBase;
import Pages.setConexion;

public class TestsXappia extends TestBase {

	private WebDriver driver;
	private CustomerCare cc;
	private SalesBase sb;
	private TestBase tb;
	/*@BeforeClass (groups = "UAT")
	public void loginUAT() {
		driver = setConexion.setupEze();
		driver.get("https://telecomcrm--uat.cs53.my.salesforce.com");
		sleep(2000);
		driver.findElement(By.id("idp_section_buttons")).click();
		sleep(2000);
		driver.findElement(By.name("Ecom_User_ID")).sendKeys("uat579805");
 		driver.findElement(By.name("Ecom_Password")).sendKeys("Testa10k");
 		driver.findElement(By.id("loginButton2")).click();
 		sleep(5000);
 		cc = new CustomerCare(driver);
		sb = new SalesBase(driver);
	}*/
	
	@BeforeClass (groups = "SIT")
	public void loginSIT() {
		driver = setConexion.setupEze();
		driver.get("https://crm--sit.cs14.my.salesforce.com/");
		sleep(2000);
		driver.findElement(By.id("idp_section_buttons")).click();
		sleep(2000);
		driver.findElement(By.name("Ecom_User_ID")).sendKeys("UAT195528");
 		driver.findElement(By.name("Ecom_Password")).sendKeys("Testa10k");
 		driver.findElement(By.id("loginButton2")).click();
 		sleep(5000);
 		cc = new CustomerCare(driver);
		sb = new SalesBase(driver);
	}
	
	private void irAConsolaFAN() {
		try {
			cc.cajonDeAplicaciones("Consola FAN");
		} catch (Exception e) {
			driver.findElement(By.id("tabBar")).findElement(By.tagName("a")).click();
			sleep(8000);
		}
	}
	
	public void carrito() {
		List <WebElement> boton = driver.findElements(By.cssSelector(".slds-button.slds-button.slds-button--icon"));
		for(WebElement x : boton) {
			if(x.getText().toLowerCase().equals("catalogo")) {
				x.click();
				break;
			}
		}
	}
	
	private void irAGestionDeClientes() {
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		boolean enc = false;
		int index = 0;
		for (WebElement frame : frames) {
			try {
				driver.switchTo().frame(frame);
				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).getText();
				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).isDisplayed();
				driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
				enc = true;
				break;
			} catch (NoSuchElementException e) {
				index++;
				driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
			}
		}
		if (enc == false)
			index = -1;
		try {
			driver.switchTo().frame(frames.get(index));
		} catch (ArrayIndexOutOfBoundsException e) {}
		buscarYClick(driver.findElements(By.tagName("button")), "equals", "gesti\u00f3n de clientes");
	}
	
	public void LoguearAgente(){
		irAConsolaFAN();
		driver.findElement(By.id("userNav")).click();
		driver.findElement(By.id("app_logout")).click();
		sleep(9000);
		driver.findElement(By.id("userDropdown")).click();
		sleep(3000);
		driver.findElement(By.id("logout")).click();
		sleep(5000);
		driver.get(urlAmbiente);
		driver.findElement(By.id("cancel_idp_hint")).click();
		sleep(7500);
		driver.findElement(By.id("idp_section_buttons")).click();
		sleep(7000);
		driver.findElement(By.name("Ecom_User_ID")).sendKeys("UAT549492");
		driver.findElement(By.name("Ecom_Password")).sendKeys("Testa10k");
		driver.findElement(By.id("loginButton2")).click();
		sleep(8000);
	}
	
	/*@BeforeMethod (groups="UAT")
	public void beforeUAT() {
		driver.get("https://telecomcrm--uat.cs53.my.salesforce.com");
	}*/
	
	/*@BeforeMethod (groups="SIT")
	public void beforeSIT() {
		driver.get("https://crm--sit.cs14.my.salesforce.com/");
	}*/
	
	/*@AfterClass (alwaysRun = true)
	public void quit() {
		driver.quit();
	}*/
	
	@Test (groups = "UAT")
	public void TXU0001_Gestiones_Del_Panel_Izquierdo_En_Consola_FAN() {
		irAConsolaFAN();
		driver.switchTo().frame(cambioFrame(driver, By.className("slds-spinner_container")));
		WebElement gestiones = driver.findElement(By.className("slds-spinner_container"));
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0," + gestiones.getLocation().y + ")");
		Assert.assertTrue(!gestiones.getText().isEmpty());
	}
	
	@Test (groups = "SIT")
	public void TXS0001_SmokeTest_Tiempo_De_Carga_De_Consola_FAN() {
		Date start = new Date();
		irAConsolaFAN();
		Date end = new Date();
		long startTime = start.getTime();
		long endTime = end.getTime();
		long tiempoTotal = endTime - startTime;
		tiempoTotal = tiempoTotal / 1000;
		Assert.assertTrue(tiempoTotal < 55);
	}
	
	@Test (groups = "UAT")
	public void TXU0002_Verificacion_De_Superposicion_De_Elementos_En_El_Carrito() {
		irAConsolaFAN();
		sb.cerrarPestaniaGestion(driver);
		irAGestionDeClientes();
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", "22222001");
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(3000);
		WebElement btnComprarInternet = null;
		List<WebElement> btn = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.slds-truncate"));
		for (int i=0; i<btn.size(); i++) {
			if (btn.get(i).getText().toLowerCase().contains("comprar internet"))
				btnComprarInternet = btn.get(i);
		}
		btnComprarInternet.click();
		sleep(40000);
		driver.switchTo().defaultContent();
		if (driver.findElements(By.cssSelector(".x-layout-mini.x-layout-mini-east.x-layout-mini-custom-logo")).size() == 0)
			driver.findElement(By.cssSelector(".x-layout-mini.x-layout-mini-east.x-layout-mini-custom-logo")).click();
		WebElement planConTarjeta = null;
		driver.switchTo().frame(cambioFrame(driver, By.className("cpq-product-cart-order")));
		List<WebElement> plan = driver.findElements(By.className("cpq-product-name"));
		for (int i=0; i<plan.size(); i++) {
			if (plan.get(i).getText().toLowerCase().contains("plan con tarjeta repro"))
				planConTarjeta = plan.get(i);
		}
		try {
			planConTarjeta.click();
		} catch (org.openqa.selenium.WebDriverException e) {
			Assert.assertTrue(false);
		}
	}
	
	@Test (groups = "UAT")
	public void TXU0003_Gestion_De_Verificacion_De_Dos_Idiomas_En_El_Carrito() {
		SalesBase sb = new SalesBase(driver);
		irAConsolaFAN();
		sb.cerrarPestaniaGestion(driver);
		irAGestionDeClientes();
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", "22222001");
		carrito();
		sleep(35000);
		List <WebElement> prod = driver.findElements(By.className("slds-radio_button__label"));
		System.out.println(prod.get(0).getText());
		sleep(3000);
		List <WebElement> agreg = driver.findElements(By.cssSelector(".slds-button.slds-button_neutral.cpq-add-button"));
		System.out.println(agreg.get(0).getText());
		boolean conf = false; 
		for(WebElement x: prod) {
			if(x.getText().toLowerCase().equals("productos") ) {
				conf = true;
			}
			Assert.assertTrue(conf);
		}
		for(WebElement y : agreg) {
			if(y.getText().toLowerCase().equals("agregar")) {
				conf = true;
			}
			Assert.assertTrue(conf);
		}
	}
	
	@Test (groups = {"UAT"}, dataProvider="NumerosAmigos")
	public void TXU0001_FF_No_Acepta_Numeros_De_Personal(String sDNI, String sLinea, String sNumeroVOZ, String sNumeroSMS) {
		irAConsolaFAN();
		sb.cerrarPestaniaGestion(driver);
		irAGestionDeClientes();
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI", sDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(25000);
		
		CustomerCare cCC = new CustomerCare(driver);
		cCC.seleccionarCardPornumeroLinea(sLinea, driver);
		sleep(3000);
		cCC.irAGestionEnCard("N\u00fameros Gratis");
		
		sleep(5000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-col--padded.slds-size--1-of-2")));
		List<WebElement> wNumerosAmigos = driver.findElements(By.cssSelector(".slds-col--padded.slds-size--1-of-2"));
		Marketing mMarketing = new Marketing(driver);
		int iIndice = mMarketing.numerosAmigos(sNumeroVOZ, sNumeroSMS);
		switch (iIndice) {
			case 0:
				wNumerosAmigos.get(0).findElement(By.tagName("input")).sendKeys(sNumeroVOZ);
				break;
			case 1:
				wNumerosAmigos.get(1).findElement(By.tagName("input")).sendKeys(sNumeroSMS);
				break;
			default:
				Assert.assertTrue(false);
		}
		sleep(5000);
		WebElement wBox = driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-tel.ng-scope.ng-dirty.ng-valid-mask.ng-valid.ng-valid-parse.ng-valid-required.ng-valid-minlength.ng-valid-maxlength")).findElement(By.className("error"));
		Assert.assertFalse(wBox.getText().equalsIgnoreCase("la linea no pertenece a Telecom, verifica el n\u00famero."));
	}
	
	@Test (groups = "UAT")
	public void TXU0002_Informacion_Internet_En_Card() {
		irAConsolaFAN();
		sb.cerrarPestaniaGestion(driver);
		irAGestionDeClientes();
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI", "22222009");
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(25000);
		
		driver.switchTo().frame(cambioFrame(driver,By.cssSelector(".console-card.active")));
		List<WebElement> wDetails = driver.findElements(By.className("detail"));
		WebElement wDetail = null;
		for (WebElement wAux : wDetails) {
			if (wAux.findElement(By.cssSelector(".slds-text-body_regular.detail-label")).getText().equalsIgnoreCase("Internet disponible")) {
				wDetail = wAux;
				break;
			}
		}
		List<WebElement> wMessages = wDetail.findElements(By.cssSelector(".slds-text-body_regular.value"));
		Assert.assertFalse(wMessages.get(1).getText().contains("Informaci\u00f3n no disponible"));
	}
	
	@Test (groups = "UAT")
	public void TXU0003_Informacion_Credito_En_Card() {
		irAConsolaFAN();
		sb.cerrarPestaniaGestion(driver);
		irAGestionDeClientes();
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI", "22222009");
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(25000);
		
		driver.switchTo().frame(cambioFrame(driver,By.cssSelector(".console-card.active")));
		List<WebElement> wDetails = driver.findElements(By.className("detail"));
		WebElement wDetail = null;
		for (WebElement wAux : wDetails) {
			if (wAux.findElement(By.cssSelector(".slds-text-body_regular.detail-label")).getText().equalsIgnoreCase("Cr\u00e9dito recarga")) {
				wDetail = wAux;
				break;
			}
		}
		List<WebElement> wMessages = wDetail.findElements(By.cssSelector(".slds-text-body_regular.value"));
		Assert.assertTrue(!wMessages.get(1).getText().isEmpty() && wMessages.get(1).getText().matches("([$][0]([,][0-9]{2}))|([$](?![0])[0-9]{0,3}([/.][0-9]{3})*([,][0-9]{2}))"));
	}
	
	@Test (groups = "SIT")
	public void TXS0002_Verificacion_De_Pestana_Detalles_En_Las_Cuentas() {
		irAConsolaFAN();
		sb.cerrarPestaniaGestion(driver);
		irAGestionDeClientes();
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", "41582129");
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().defaultContent();
		for (WebElement x : driver.findElements(By.className("x-tab-left"))) {
			if (!x.getText().equalsIgnoreCase("Detalles"))
				Assert.assertTrue(false);
		}
	}
	
	@Test (groups = "SIT")
	public void TXS0003_Busqueda_De_Productos_En_El_Carrito() {
		irAConsolaFAN();
		sb.cerrarPestaniaGestion(driver);
		irAGestionDeClientes();
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", "41582129");
		carrito();
		sleep(15000);
		driver.findElement(By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys("galaxy");
		sleep(2500);
		for (WebElement x : driver.findElements(By.cssSelector(".categoryButton.cat-icon"))) {
			if (x.getText().equalsIgnoreCase("Planes"))
				x.click();
		}
		sleep(2500);
		WebElement list = driver.findElements(By.cssSelector(".slds-tile.cpq-product-item")).get(0);
		if (list.getText().toLowerCase().contains("galaxy s8"))
			Assert.assertTrue(false);
	}
	
	@Test (groups = "SIT")
	public void TXS0009_Busqueda_De_Cliente_Inexistente_Por_Linea() {
		irAConsolaFAN();
		sb.cerrarPestaniaGestion(driver);
		irAGestionDeClientes();
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		driver.findElement(By.id("PhoneNumber")).sendKeys("2944675251");
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep(3000);
		WebElement msj = driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope"));
		Assert.assertTrue(!msj.getText().toLowerCase().contains("no hay ning\u00fan cliente con este tipo y n\u00famero de documento. busc\u00e1 con otro dato o cre\u00e1 un nuevo cliente"));
	}
	
	@Test (groups = "SIT")
	public void TXS0001_Informacion_Credito_En_Facturacion() {
		irAConsolaFAN();
		sb.cerrarPestaniaGestion(driver);
		irAGestionDeClientes();
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI", "41582129");
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(25000);
		
		try {
			cc.openleftpanel();
		}
		catch (Exception x) {
			//Always empty
		}
		cc.irAFacturacion();
		/*List<WebElement> wTitle = driver.findElements(By.className("header-right"));
		WebElement wBalance = null;
		for (WebElement wAux : wTitle) {
			if (wAux.findElement(By.cssSelector(".slds-text-body_regular.expired-title")).getText().equalsIgnoreCase("balance")) {
				wBalance = wAux;
				break;
			}
		}
		WebElement wMessage = wBalance.findElements(By.tagName("span")).get(1);
		Assert.assertTrue(!wMessage.getText().isEmpty() && wMessage.getText().matches("([$][0]([,][0-9]{2}))|([$](?![0])[0-9]{0,3}([/.][0-9]{3})*([,][0-9]{2}))"));*/
		sleep(5000);
		WebElement wMessage = driver.findElement(By.cssSelector("div[class='header-right'] span[class='slds-text-heading_medium expired-date expired-pink']"));
		Assert.assertTrue(!wMessage.getText().isEmpty() && wMessage.getText().matches("([$][0]([,][0-9]{2}))|([$](?![0])[0-9]{0,3}([/.][0-9]{3})*([,][0-9]{2}))"));
	}
	
	
	@Test (groups = "SIT")
	public void TXS_0004_CRM_Verificar_Pasos_Alta_de_Linea_Cliente_Existente_OfCom(){
		irAConsolaFAN();
		sb.cerrarPestaniaGestion(driver);
		irAGestionDeClientes();
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		sb.BuscarCuenta("DNI", "2222203");
		sleep(5000);
		carrito();
		sleep(20000);
		sb.elegirplan("Plan con Tarjeta");
		sleep(12000);
		driver.findElement(By.cssSelector(".slds-input.ng-valid.ng-not-empty.ng-dirty.ng-valid-parse.ng-touched")).clear();
		sleep(8000);
		driver.findElement(By.cssSelector(".slds-input.ng-valid.ng-dirty.ng-valid-parse.ng-touched.ng-empty")).sendKeys("Galaxy s8 - Negro");
		sleep(8000);
		List<WebElement> agregar = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.add-button")); 
			for(WebElement a : agregar){
				if(a.getText().equals("Agregar")){
					cc.obligarclick(a);
				}
			}
		sleep(5000);	
		sb.continuar();
		sleep(10000);
		ArrayList<String> txt1 = new ArrayList<String>();
		ArrayList<String> txt2 = new ArrayList<String>();
		txt2.add("ASIGNACI\u00d3N DE L\u00cdNEA");
		txt2.add("VALIDACI\u00d3N DE IDENTIDAD");
		txt2.add("SIMULACI\u00d3N DE FACTURA");
		txt2.add("SELECCI\u00d3N DE MEDIO DE PAGO");
		txt2.add("RESUMEN DE LA ORDEN DE VENTA");
		txt2.add("ENV\u00edO FACTURA Y DATOS DE COBRO");
		txt2.add("INFORMACI\u00d3N");
		List<WebElement> pasos = driver.findElements(By.cssSelector(".list-group.vertical-steps.ng-scope"));
		System.out.println(pasos.size());
			for(WebElement e: pasos){
				txt1.add(e.getText());
				System.out.println(e.getText());
			}
		Assert.assertTrue(txt1.containsAll(txt2));
		}
		
	@Test (groups = "SIT")
	public void TXS_0005_CRM_Verificar_Pasos_Alta_de_Linea_Cliente_Existente_Agente(){
	LoguearAgente();
	irAConsolaFAN();
	sb.cerrarPestaniaGestion(driver);
	irAGestionDeClientes();
	driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
	sleep(1000);
	sb.BuscarCuenta("DNI", "2222203");
	sleep(5000);
	carrito();
	sleep(20000);
	sb.elegirplan("Plan con Tarjeta");
	sleep(12000);
	driver.findElement(By.cssSelector(".slds-input.ng-valid.ng-not-empty.ng-dirty.ng-valid-parse.ng-touched")).clear();
	sleep(8000);
	driver.findElement(By.cssSelector(".slds-input.ng-valid.ng-dirty.ng-valid-parse.ng-touched.ng-empty")).sendKeys("Galaxy s8 - Negro");
	sleep(8000);
	List<WebElement> agregar = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.add-button")); 
		for(WebElement a : agregar){
			if(a.getText().equals("Agregar")){
				cc.obligarclick(a);
			}
		}
	sleep(5000);	
	sb.continuar();
	sleep(10000);
	ArrayList<String> txt1 = new ArrayList<String>();
	ArrayList<String> txt2 = new ArrayList<String>();
	txt2.add("ASIGNACI\u00d3N DE L\u00cdNEA");
	txt2.add("VALIDACI\u00d3N DE IDENTIDAD");
	txt2.add("INGRESO DE SERIAL");
	txt2.add("SIMULACI\u00d3N DE FACTURA");
	txt2.add("SELECCI\u00d3N DE MEDIO DE PAGO");
	txt2.add("RESUMEN DE LA ORDEN DE VENTA");
	txt2.add("ENV\u00edO FACTURA Y DATOS DE COBRO");
	txt2.add("INFORMACI\u00d3N");
	List<WebElement> pasos = driver.findElements(By.cssSelector(".list-group.vertical-steps.ng-scope"));
	System.out.println(pasos.size());
		for(WebElement e: pasos){
			txt1.add(e.getText());
			System.out.println(e.getText());
		}
	Assert.assertTrue(txt1.containsAll(txt2));
	}
	
	@Test (groups = "SIT")
	public void TXS_0006_CRM_Verificar_Pasos_Alta_de_Linea_Cliente_Nuevo_OfCom_StorePickUp(){
		CustomerCare cc = new CustomerCare(driver);
		SalesBase sb = new SalesBase(driver);
		Accounts accountPage = new Accounts(driver);
		irAConsolaFAN();
		sb.cerrarPestaniaGestion(driver);
		irAGestionDeClientes();
		sleep(25000);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.id("SearchClientDocumentNumber")));
		sb.BtnCrearNuevoCliente();
		ContactSearch contact = new ContactSearch(driver);
		contact.sex("Masculino");
		contact.Llenar_Contacto("Nuevo", "OfCom", "10/10/1990");
		driver.findElement(By.id("EmailSelectableItems")).findElement(By.tagName("input")).sendKeys("a@a.com");
		driver.findElement(By.id("Contact_nextBtn")).click();
		sleep(20000);
		carrito();
		sleep(20000);
		sb.ResolverEntrega(driver, "Store Pick Up", "Ciudad Aut�noma de Buenos Aires","CIUD AUTON D BUENOS AIRES");
		sleep(8000);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid.ng-empty")));
		sb.elegirplan("Plan con tarjeta");
		sleep(12000);
		driver.findElement(By.cssSelector(".slds-input.ng-valid.ng-not-empty.ng-dirty.ng-valid-parse.ng-touched")).clear();
		sleep(8000);
		driver.findElement(By.cssSelector(".slds-input.ng-valid.ng-dirty.ng-valid-parse.ng-touched.ng-empty")).sendKeys("Galaxy s8 - Negro");
		sleep(8000);
		List<WebElement> agregar = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.add-button")); 
			for(WebElement a : agregar){
				if(a.getText().equals("Agregar")){
					cc.obligarclick(a);
				}
			}
		sleep(5000);	
		sb.continuar();
		sleep(22000);
		ArrayList<String> txt1 = new ArrayList<String>();
		ArrayList<String> txt2 = new ArrayList<String>();
		txt2.add("DATOS DE LA CUENTA");
		txt2.add("ASIGNACI\u00d3N DE L\u00cdNEA");
		txt2.add("SELECCI\u00d3N DE MODO DE ENTREGA");
		txt2.add("SIMULACI\u00d3N DE FACTURA");
		txt2.add("SELECCI\u00d3N MEDIO DE PAGO");
		txt2.add("INGRESO DE SERIAL");
		//txt2.add("VALIDACI\u00d3N DE IDENTIDAD");
		txt2.add("RESUMEN DE LA ORDEN DE VENTA");
		//txt2.add("ENV\u00edO FACTURA Y DATOS DE COBRO");
		txt2.add("INFORMACI\u00d3N");
		List<WebElement> pasos = driver.findElements(By.cssSelector(".list-group.vertical-steps.ng-scope"));
		System.out.println(pasos.size());
			for(WebElement e: pasos){
				txt1.add(e.getText());
				System.out.println(e.getText());
			}
		Assert.assertTrue(txt1.containsAll(txt2));
	}
	
	@Test (groups = "SIT")
	public void TXS_0007_CRM_Verificar_Pasos_Alta_de_Linea_Cliente_Nuevo_Agente_StorePickUp(){
		CustomerCare cc = new CustomerCare(driver);
		SalesBase sb = new SalesBase(driver);
		Accounts accountPage = new Accounts(driver);
		LoguearAgente();
		irAConsolaFAN();
		sb.cerrarPestaniaGestion(driver);
		irAGestionDeClientes();
		sleep(25000);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.id("SearchClientDocumentNumber")));
		sb.BtnCrearNuevoCliente();
		ContactSearch contact = new ContactSearch(driver);
		contact.sex("Masculino");
		contact.Llenar_Contacto("Nuevo", "OfCom", "10/10/1990");
		driver.findElement(By.id("EmailSelectableItems")).findElement(By.tagName("input")).sendKeys("a@a.com");
		driver.findElement(By.id("Contact_nextBtn")).click();
		sleep(20000);
		carrito();
		sleep(20000);
		sb.ResolverEntrega(driver, "Store Pick Up", "Ciudad Aut�noma de Buenos Aires","CIUD AUTON D BUENOS AIRES");
		sleep(8000);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid.ng-empty")));
		sb.elegirplan("Plan con tarjeta");
		sleep(12000);
		driver.findElement(By.cssSelector(".slds-input.ng-valid.ng-not-empty.ng-dirty.ng-valid-parse.ng-touched")).clear();
		sleep(8000);
		driver.findElement(By.cssSelector(".slds-input.ng-valid.ng-dirty.ng-valid-parse.ng-touched.ng-empty")).sendKeys("Galaxy s8 - Negro");
		sleep(8000);
		List<WebElement> agregar = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.add-button")); 
			for(WebElement a : agregar){
				if(a.getText().equals("Agregar")){
					cc.obligarclick(a);
				}
			}
		sleep(5000);	
		sb.continuar();
		sleep(22000);
		ArrayList<String> txt1 = new ArrayList<String>();
		ArrayList<String> txt2 = new ArrayList<String>();
		txt2.add("DATOS DE LA CUENTA");
		txt2.add("ASIGNACI\u00d3N DE L\u00cdNEA");
		txt2.add("SELECCI\u00d3N DE MODO DE ENTREGA");
		txt2.add("SIMULACI\u00d3N DE FACTURA");
		txt2.add("SELECCI\u00d3N MEDIO DE PAGO");
		txt2.add("INGRESO DE SERIAL");
		//txt2.add("VALIDACI\u00d3N DE IDENTIDAD");
		txt2.add("RESUMEN DE LA ORDEN DE VENTA");
		//txt2.add("ENV\u00edO FACTURA Y DATOS DE COBRO");
		txt2.add("INFORMACI\u00d3N");
		List<WebElement> pasos = driver.findElements(By.cssSelector(".list-group.vertical-steps.ng-scope"));
		System.out.println(pasos.size());
			for(WebElement e: pasos){
				txt1.add(e.getText());
				System.out.println(e.getText());
			}
		Assert.assertTrue(txt1.containsAll(txt2));
	}
	@Test (groups = "SIT")
	public void TXS_0010_CRM_Verificar_Pasos_Alta_de_Linea_Cliente_Nuevo_OfCom_Presencial(){
		CustomerCare cc = new CustomerCare(driver);
		SalesBase sb = new SalesBase(driver);
		Accounts accountPage = new Accounts(driver);
		irAConsolaFAN();
		sb.cerrarPestaniaGestion(driver);
		irAGestionDeClientes();
		sleep(25000);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.id("SearchClientDocumentNumber")));
		sb.BtnCrearNuevoCliente();
		ContactSearch contact = new ContactSearch(driver);
		contact.sex("Masculino");
		contact.Llenar_Contacto("Nuevo", "OfCom", "10/10/1990");
		driver.findElement(By.id("EmailSelectableItems")).findElement(By.tagName("input")).sendKeys("a@a.com");
		driver.findElement(By.id("Contact_nextBtn")).click();
		sleep(20000);
		carrito();
		sleep(20000);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid.ng-empty")));
		sb.elegirplan("Plan con tarjeta");
		sleep(12000);
		driver.findElement(By.cssSelector(".slds-input.ng-valid.ng-not-empty.ng-dirty.ng-valid-parse.ng-touched")).clear();
		sleep(8000);
		driver.findElement(By.cssSelector(".slds-input.ng-valid.ng-dirty.ng-valid-parse.ng-touched.ng-empty")).sendKeys("Galaxy s8 - Negro");
		sleep(8000);
		List<WebElement> agregar = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.add-button")); 
			for(WebElement a : agregar){
				if(a.getText().equals("Agregar")){
					cc.obligarclick(a);
				}
			}
		sleep(5000);	
		sb.continuar();
		sleep(22000);
		ArrayList<String> txt1 = new ArrayList<String>();
		ArrayList<String> txt2 = new ArrayList<String>();
		txt2.add("DATOS DE LA CUENTA");
		txt2.add("ASIGNACI\u00d3N DE L\u00cdNEA");
		txt2.add("SELECCI\u00d3N DE MODO DE ENTREGA");
		txt2.add("SIMULACI\u00d3N DE FACTURA");
		txt2.add("SELECCI\u00d3N MEDIO DE PAGO");
		txt2.add("INGRESO DE SERIAL");
		//txt2.add("VALIDACI\u00d3N DE IDENTIDAD");
		txt2.add("RESUMEN DE LA ORDEN DE VENTA");
		//txt2.add("ENV\u00edO FACTURA Y DATOS DE COBRO");
		txt2.add("INFORMACI\u00d3N");
		List<WebElement> pasos = driver.findElements(By.cssSelector(".list-group.vertical-steps.ng-scope"));
		System.out.println(pasos.size());
			for(WebElement e: pasos){
				txt1.add(e.getText());
				System.out.println(e.getText());
			}
		Assert.assertTrue(txt1.containsAll(txt2));
	}
	
	@Test (groups = "SIT")
	public void TXS_0011_CRM_Verificar_Pasos_Alta_de_Linea_Cliente_Nuevo_Agente_Presencial(){
		CustomerCare cc = new CustomerCare(driver);
		SalesBase sb = new SalesBase(driver);
		Accounts accountPage = new Accounts(driver);
		LoguearAgente();
		irAConsolaFAN();
		sb.cerrarPestaniaGestion(driver);
		irAGestionDeClientes();
		sleep(25000);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.id("SearchClientDocumentNumber")));
		sb.BtnCrearNuevoCliente();
		ContactSearch contact = new ContactSearch(driver);
		contact.sex("Masculino");
		contact.Llenar_Contacto("Nuevo", "OfCom", "10/10/1990");
		driver.findElement(By.id("EmailSelectableItems")).findElement(By.tagName("input")).sendKeys("a@a.com");
		driver.findElement(By.id("Contact_nextBtn")).click();
		sleep(20000);
		carrito();
		sleep(20000);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid.ng-empty")));
		sb.elegirplan("Plan con tarjeta");
		sleep(12000);
		driver.findElement(By.cssSelector(".slds-input.ng-valid.ng-not-empty.ng-dirty.ng-valid-parse.ng-touched")).clear();
		sleep(8000);
		driver.findElement(By.cssSelector(".slds-input.ng-valid.ng-dirty.ng-valid-parse.ng-touched.ng-empty")).sendKeys("Galaxy s8 - Negro");
		sleep(8000);
		List<WebElement> agregar = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.add-button")); 
			for(WebElement a : agregar){
				if(a.getText().equals("Agregar")){
					cc.obligarclick(a);
				}
			}
		sleep(5000);	
		sb.continuar();
		sleep(22000);
		ArrayList<String> txt1 = new ArrayList<String>();
		ArrayList<String> txt2 = new ArrayList<String>();
		txt2.add("DATOS DE LA CUENTA");
		txt2.add("ASIGNACI\u00d3N DE L\u00cdNEA");
		txt2.add("SELECCI\u00d3N DE MODO DE ENTREGA");
		txt2.add("SIMULACI\u00d3N DE FACTURA");
		txt2.add("SELECCI\u00d3N MEDIO DE PAGO");
		txt2.add("INGRESO DE SERIAL");
		//txt2.add("VALIDACI\u00d3N DE IDENTIDAD");
		txt2.add("RESUMEN DE LA ORDEN DE VENTA");
		//txt2.add("ENV\u00edO FACTURA Y DATOS DE COBRO");
		txt2.add("INFORMACI\u00d3N");
		List<WebElement> pasos = driver.findElements(By.cssSelector(".list-group.vertical-steps.ng-scope"));
		System.out.println(pasos.size());
			for(WebElement e: pasos){
				txt1.add(e.getText());
				System.out.println(e.getText());
			}
		Assert.assertTrue(txt1.containsAll(txt2));
	}
}