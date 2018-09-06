package Tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.BasePage;
import Pages.ContactSearch;
import Pages.CustomerCare;
import Pages.SalesBase;
import Pages.setConexion;



public class AltadeLineas extends TestBase {
	String nombre="Matias";
	String apellido="Rodriguez";
	String fNacimiento="19/08/1989";
	String calle="Santa Fe";
	String CP= "1609";
	String altura="1234";
	protected WebDriver driver;
	protected  WebDriverWait wait;
	List <String> DatosOrden =new ArrayList<String>();
	String imagen;
	
	@BeforeClass(alwaysRun=true)
	public void Init2() {
		driver = setConexion.setupEze();
		//driver.manage().deleteAllCookies();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}		
		SalesBase SB = new SalesBase(driver);
		loginAgente(driver);  
		CustomerCare cc = new CustomerCare(driver);
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
		driver.findElement(By.id("tabBar")).findElement(By.tagName("a")).click();
		sleep(10000);
		try{
			SB.cerrarPestaniaGestion(driver);}
		catch(Exception ex1) {
		}
		goToLeftPanel2(driver, "Inicio");
		sleep(5000);
	}
	
	@BeforeMethod(alwaysRun=true)
	public void setup() throws Exception {
		Accounts accountPage = new Accounts(driver);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		boolean enc = false;
		int index = 0;
		for(WebElement frame : frames) {
			try {
				System.out.println("aca");
				driver.switchTo().frame(frame);

				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).getText(); //each element is in the same iframe.
				//System.out.println(index); //prints the used index.

				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).isDisplayed(); //each element is in the same iframe.
				//System.out.println(index); //prints the used index.

				driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
				enc = true;
				break;
			}catch(NoSuchElementException noSuchElemExcept) {
				index++;
				driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
			}
		}
		if(enc == false)
			index = -1;
		try {
				driver.switchTo().frame(frames.get(index));
		}catch(ArrayIndexOutOfBoundsException iobExcept) {System.out.println("Elemento no encontrado en ningun frame 2.");
			
		}
		List<WebElement> botones = driver.findElements(By.tagName("button"));
		for (WebElement UnB : botones) {
			System.out.println(UnB.getText());
			if(UnB.getText().equalsIgnoreCase("gesti\u00f3n de clientes")) {
				UnB.click();
				break;
			}
		}
		
		sleep(30000);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.id("SearchClientDocumentNumber")));
	}
	
	//@AfterMethod(alwaysRun=true)
	public void deslogin() throws IOException{
		guardarListaTxt(DatosOrden);
		DatosOrden.clear();
		tomarCaptura(driver,imagen);
		sleep(2000);
		SalesBase SB = new SalesBase(driver);
		driver.switchTo().defaultContent();
		sleep(6000);
		SB.cerrarPestaniaGestion(driver);
		
		sleep(5000);

	}
	
	//@AfterClass(alwaysRun=true)
	public void Exit() throws IOException {
		guardarListaTxt(DatosOrden);
		//driver.quit();
		sleep(2000);
	}
	
	
	@Test(groups={"Sales", "AltaLineaDatos"}, priority=1, dataProvider="DatosAltaLineaAgente")
	public void TS_CRM_Alta_de_Linea_Agente(String sDni, String sNombre, String sApellido, String sSexo, String sFNac, String sEmail, String sPlan, String sProvincia, String sLocalidad, String sCalle, String sNumCa, String sCP, String sEntrega, String sStoreProv, String sStoreLoc, String sTipoDelivery) throws IOException {
		imagen = "TS_CRM_Alta_de_Linea_Agente";
		CustomerCare cc = new CustomerCare(driver);
		SalesBase sb = new SalesBase(driver);
		sb.Crear_Cliente(sDni);
		ContactSearch contact = new ContactSearch(driver);
		contact.sex(sSexo);
		contact.Llenar_Contacto(sNombre, sApellido, sFNac);
		driver.findElement(By.id("EmailSelectableItems")).findElement(By.tagName("input")).sendKeys(sEmail);
		driver.findElement(By.id("Contact_nextBtn")).click();
		sleep(28000);
		//sb.ResolverEntrega(driver, sEntrega,sStoreProv,sStoreLoc);
		sleep(7000);
		driver.switchTo().defaultContent();
		Accounts accountPage = new Accounts(driver);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		boolean enc = false;
		int index = 0;
		for(WebElement frame : frames) {
			try {
				System.out.println("aca");
				driver.switchTo().frame(frame);

				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).getText(); //each element is in the same iframe.
				//System.out.println(index); //prints the used index.

				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).isDisplayed(); //each element is in the same iframe.
				//System.out.println(index); //prints the used index.

				driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
				enc = true;
				break;
			}catch(NoSuchElementException noSuchElemExcept) {
				index++;
				driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
			}
		}
		if(enc == false)
			index = -1;
		try {
				driver.switchTo().frame(frames.get(index));
		}catch(ArrayIndexOutOfBoundsException iobExcept) {System.out.println("Elemento no encontrado en ningun frame 2.");
			
		}
		
		sleep(14000);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid.ng-empty")));
		sb.elegirplan(sPlan);
		sb.continuar();
		sleep(22000);
		sb.Crear_DomicilioLegal(sProvincia, sLocalidad, sCalle, "", sNumCa, "", "", sCP);
		sleep(24000);
		WebElement sig = driver.findElement(By.id("LineAssignment_nextBtn"));
		cc.obligarclick(sig);
		sleep(20000);
		if (sEntrega.equalsIgnoreCase("Delivery")) {
			
		}
		if (sEntrega.equalsIgnoreCase("Store pick up")) {
			cc.obligarclick(driver.findElement(By.id("DeliveryMethodConfiguration_nextBtn")));
			sleep(20000);
		}
		if (sEntrega.equalsIgnoreCase("Presencial")) {
			String ICCID = driver.findElement(By.cssSelector(".ng-pristine.ng-untouched.ng-valid.ng-scope.ng-not-empty")).getText();
			cc.obligarclick(driver.findElement(By.id("ICCDAssignment_nextBtn")));
			sleep(30000);
		}
		try {
			cc.obligarclick(driver.findElement(By.id("InvoicePreview_nextBtn")));
		}catch(Exception ex1) {cc.obligarclick(driver.findElement(By.id("Step_Error_Huawei_S202_nextBtn")));}
		sleep(20000);
		try {
			cc.obligarclick(driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")));
			sleep(20000);
		}catch(Exception ex1) {}
		
		sb.elegirvalidacion("DOC");
		sleep(14000);
		driver.findElement(By.id("FileDocumentImage")).sendKeys("C:\\Users\\florangel\\Downloads\\mapache.jpg");
		sleep(3000);
		cc.obligarclick(driver.findElement(By.id("DocumentMethod_nextBtn")));
		sleep(35000);
		cc.obligarclick(driver.findElement(By.id("ValidationResult_nextBtn")));
		sleep(30000);
		try {
			driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")).get(1).click();
			sleep(10000);
		}catch(Exception ex1) {}
		String orden = driver.findElement(By.className("top-data")).findElement(By.className("ng-binding")).getText();
		String NCuenta = driver.findElements(By.className("top-data")).get(1).findElements(By.className("ng-binding")).get(3).getText();
		String Linea = driver.findElement(By.cssSelector(".top-data.ng-scope")).findElements(By.className("ng-binding")).get(1).getText();
		System.out.println("Orden "+orden);
		System.out.println("cuenta "+NCuenta);
		System.out.println("Linea "+Linea);
		orden = orden.substring(orden.length()-8);
		NCuenta = NCuenta.substring(NCuenta.length()-16);
		Linea = Linea.substring(Linea.length()-10);
		cc.obligarclick(driver.findElement(By.id("OrderSumary_nextBtn")));
		sleep(30000);
		try {
			cc.obligarclick(driver.findElement(By.id("Step_Error_Huawei_S029_nextBtn")));
			sleep(30000);
		}catch(Exception ex1) {
		}
		driver.findElement(By.id("SaleOrderMessages_nextBtn")).click();
		sleep(15000);
		driver.close();
		//driver.quit();
		WebDriver driver = setConexion.setupEze();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}		
		SalesBase SB = new SalesBase(driver);
		loginAgente(driver);  
		 try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
		driver.findElement(By.id("tabBar")).findElement(By.tagName("a")).click();
		sleep(10000);
		System.out.println(cc.obtenerMontoyTNparaAlta(driver, orden));
		//CBS_Mattu invoSer = new CBS_Mattu();
		//invoSer.openPage2(orden);
		sleep(2000);
		
		CambiarPerfil("logistica",driver);
		sb.completarLogistica(orden, driver);
		CambiarPerfil("entrega",driver);
		sb.completarEntrega(orden, driver);
		CambiarPerfil("agente",driver);
		
	}
	
	@Test(groups={"Sales", "AltaLineaDatos","E2E"}, priority=2, dataProvider="DatosAltaLineaOfCom")
	public void TS_CRM_Movil_PRE_Alta_Linea_Cliente_Nuevo_OfCom_Efectivo_Presencial_DNI(String sDni, String sNombre, String sApellido, String sSexo, String sFNac, String sEmail, String sPlan, String sProvincia, String sLocalidad, String sEntrega, String sStoreProv, String sStoreLoc, String sTipoDelivery) throws IOException {
		imagen = "TS_CRM_Movil_PRE_Alta_Linea_Cliente_Nuevo_OfCom_Efectivo_Presencial_DNI";
		CustomerCare cc = new CustomerCare(driver);
		SalesBase sb = new SalesBase(driver);
		sleep(8000);
		sb.BtnCrearNuevoCliente();
		sDni = driver.findElement(By.id("SearchClientDocumentNumber")).getAttribute("value");
		
		//sb.Crear_Cliente(sDni);
		ContactSearch contact = new ContactSearch(driver);
		contact.sex(sSexo);
		contact.Llenar_Contacto(sNombre, sApellido, sFNac);
		driver.findElement(By.id("EmailSelectableItems")).findElement(By.tagName("input")).sendKeys(sEmail);
		driver.findElement(By.id("Contact_nextBtn")).click();
		sleep(35000);
		sb.ResolverEntrega(driver, sEntrega,sStoreProv,sStoreLoc);
		sleep(7000);
		driver.switchTo().defaultContent();
		Accounts accountPage = new Accounts(driver);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		boolean enc = false;
		int index = 0;
		for(WebElement frame : frames) {
			try {
				System.out.println("aca");
				driver.switchTo().frame(frame);

				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).getText(); //each element is in the same iframe.
				//System.out.println(index); //prints the used index.

				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).isDisplayed(); //each element is in the same iframe.
				//System.out.println(index); //prints the used index.

				driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
				enc = true;
				break;
			}catch(NoSuchElementException noSuchElemExcept) {
				index++;
				driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
			}
		}
		if(enc == false)
			index = -1;
		try {
				driver.switchTo().frame(frames.get(index));
		}catch(ArrayIndexOutOfBoundsException iobExcept) {System.out.println("Elemento no encontrado en ningun frame 2.");
			
		}
		
		sleep(14000);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid.ng-empty")));
		sb.elegirplan(sPlan);
		sb.continuar();
		sleep(22000);
		sb.Crear_DomicilioLegal(sProvincia, sLocalidad, "falsa", "", "1000", "", "", "1549");
		sleep(24000);
		WebElement sig = driver.findElement(By.id("LineAssignment_nextBtn"));
		cc.obligarclick(sig);
		sleep(25000);
		if (sEntrega.equalsIgnoreCase("Delivery")) {
			
		}
		if (sEntrega.equalsIgnoreCase("Store pick up")) {
			cc.obligarclick(driver.findElement(By.id("DeliveryMethodConfiguration_nextBtn")));
			sleep(20000);
		}
		try {
			cc.obligarclick(driver.findElement(By.id("Step_Error_Huawei_S015_nextBtn")));
		}catch(Exception ex1) {}
		sleep(15000);
		cc.obligarclick(driver.findElement(By.id("InvoicePreview_nextBtn")));
		sleep(20000);
		try {
			cc.obligarclick(driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")));
			sleep(20000);
		}catch(Exception ex1) {}
		
		sb.elegirvalidacion("DOC");
		sleep(8000);
		driver.findElement(By.id("FileDocumentImage")).sendKeys("C:\\Users\\florangel\\Downloads\\mapache.jpg");
		sleep(3000);
		cc.obligarclick(driver.findElement(By.id("DocumentMethod_nextBtn")));
		sleep(10000);
		cc.obligarclick(driver.findElement(By.id("ValidationResult_nextBtn")));
		sleep(15000);
		try {
			driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")).get(1).click();
			sleep(15000);
		}catch(Exception ex1) {}
		String orden = driver.findElement(By.className("top-data")).findElement(By.className("ng-binding")).getText();
		String NCuenta = driver.findElements(By.className("top-data")).get(1).findElements(By.className("ng-binding")).get(3).getText();
		String Linea = driver.findElement(By.cssSelector(".top-data.ng-scope")).findElements(By.className("ng-binding")).get(1).getText();
		System.out.println("Orden "+orden);
		System.out.println("cuenta "+NCuenta);
		System.out.println("Linea "+Linea);
		orden = orden.substring(orden.length()-8);
		NCuenta = NCuenta.substring(NCuenta.length()-16);
		Linea = Linea.substring(Linea.length()-10);
		
		//00072466 9900000724810001
		cc.obligarclick(driver.findElement(By.id("OrderSumary_nextBtn")));
		sleep(20000);
		try {
			cc.obligarclick(driver.findElement(By.id("Step_Error_Huawei_S029_nextBtn")));
		}catch(Exception ex1) {
			driver.findElement(By.id("SaleOrderMessages_nextBtn")).click();
		}
		DatosOrden.add("Orden:"+orden+"-DNI:"+sDni+"-Cuenta:"+NCuenta+"-Linea"+Linea);
		sleep(15000);
		driver.navigate().refresh();
		sleep(15000);
		//try {
			System.out.println(cc.obtenerMontoyTNparaAlta(driver, orden));
		/*}catch(org.openqa.selenium.TimeoutException ex2) {
			driver.manage().deleteAllCookies();
			driver.navigate().refresh();
		}*/
		System.out.println(cc.obtenerMontoyTNparaAlta(driver, orden));
		//CBS_Mattu invoSer = new CBS_Mattu();
		//invoSer.openPage2(orden);
		sleep(2000);
		CambiarPerfil("logistica",driver);
		sb.completarLogistica(orden, driver);
		CambiarPerfil("entrega",driver);
		sb.completarEntrega(orden, driver);
		CambiarPerfil("ofcom",driver);
	}
	
	@Test(groups={"Sales", "AltaLinea"}, priority=1, dataProvider="AltaLineaNuevoAgentePresencial")
	public void TS118938_CRM_Movil_PRE_Alta_Linea_Cliente_Nuevo_Agente_Efectivo_Presencial_DNI(String sNombre, String sApellido, String sSexo, String sFNac, String sEmail, String sPlan, String sProvincia, String sLocalidad) throws IOException {
		imagen = "TS118938";
		CustomerCare cc = new CustomerCare(driver);
		SalesBase sb = new SalesBase(driver);
		sleep(5000);
		sb.BtnCrearNuevoCliente();
		ContactSearch contact = new ContactSearch(driver);
		contact.sex(sSexo);
		contact.Llenar_Contacto(sNombre, sApellido, sFNac);
		driver.findElement(By.id("EmailSelectableItems")).findElement(By.tagName("input")).sendKeys(sEmail);
		driver.findElement(By.id("Contact_nextBtn")).click();
		sleep(18000);
		sb.ResolverEntrega(driver, "Presencial", "nada", "nada");
		sleep(7000);
		driver.switchTo().defaultContent();
		Accounts accountPage = new Accounts(driver);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		boolean enc = false;
		int index = 0;
		for(WebElement frame : frames) {
			try {
				System.out.println("aca");
				driver.switchTo().frame(frame);

				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).getText(); //each element is in the same iframe.
				//System.out.println(index); //prints the used index.

				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).isDisplayed(); //each element is in the same iframe.
				//System.out.println(index); //prints the used index.

				driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
				enc = true;
				break;
			}catch(NoSuchElementException noSuchElemExcept) {
				index++;
				driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
			}
		}
		if(enc == false)
			index = -1;
		try {
				driver.switchTo().frame(frames.get(index));
		}catch(ArrayIndexOutOfBoundsException iobExcept) {System.out.println("Elemento no encontrado en ningun frame 2.");
			
		}
		
		sleep(14000);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid.ng-empty")));
		sb.elegirplan(sPlan);
		sb.continuar();
		sleep(22000);
		sb.Crear_DomicilioLegal(sProvincia, sLocalidad, "falsa", "", "1000", "", "", "1549");
		sleep(24000);
		WebElement sig = driver.findElement(By.id("LineAssignment_nextBtn"));
		cc.obligarclick(sig);
		sleep(20000);
		String ICCID = driver.findElement(By.cssSelector(".ng-pristine.ng-untouched.ng-valid.ng-scope.ng-not-empty")).getText();
		cc.obligarclick(driver.findElement(By.id("ICCDAssignment_nextBtn")));
		sleep(20000);
		
		cc.obligarclick(driver.findElement(By.id("InvoicePreview_nextBtn")));
		sleep(20000);
		try {
			cc.obligarclick(driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")));
			sleep(20000);
		}catch(Exception ex1) {}
		
		sb.elegirvalidacion("DOC");
		sleep(8000);
		driver.findElement(By.id("FileDocumentImage")).sendKeys("C:\\Users\\florangel\\Downloads\\mapache.jpg");
		sleep(3000);
		cc.obligarclick(driver.findElement(By.id("DocumentMethod_nextBtn")));
		sleep(10000);
		cc.obligarclick(driver.findElement(By.id("ValidationResult_nextBtn")));
		sleep(10000);
		try {
			driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")).get(1).click();
			sleep(10000);
		}catch(Exception ex1) {}
		cc.obligarclick(driver.findElement(By.id("OrderSumary_nextBtn")));
		sleep(20000);
		try {
			cc.obligarclick(driver.findElement(By.id("Step_Error_Huawei_S029_nextBtn")));
		}catch(Exception ex1) {
			driver.findElement(By.id("SaleOrderMessages_nextBtn")).click();
		}
		
	}
	@Test(groups={"Sales", "AltaLineaDatos","E2E"}, priority=2, dataProvider="AltaLineaExistenteOfComPresencial")
	public void TS119298_CRM_Movil_PRE_Alta_Linea_Cliente_Existente_OFCOM_Efectivo_Presencial_DNI(String sDni, String sEmail, String sPlan, String sProvincia, String sLocalidad) throws IOException {
		imagen = "TS119298";
		CustomerCare cc = new CustomerCare(driver);
		SalesBase sb = new SalesBase(driver);
		sleep(5000);
		sb.BuscarCuenta("DNI", sDni);
		sleep(5000);
		List<WebElement> btns = driver.findElements(By.cssSelector(".slds-button.slds-button.slds-button--icon"));
		for(WebElement e: btns){
			System.out.println(e.getText());
			if(e.getText().toLowerCase().equals("catalogo")){ 
				e.click();
				break;
			}
		}
		sleep(18000);
		sb.ResolverEntrega(driver, "Presencial","nada","nada");
		sleep(7000);
		driver.switchTo().defaultContent();
		Accounts accountPage = new Accounts(driver);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		boolean enc = false;
		int index = 0;
		for(WebElement frame : frames) {
			try {
				System.out.println("aca");
				driver.switchTo().frame(frame);

				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).getText(); //each element is in the same iframe.
				//System.out.println(index); //prints the used index.

				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).isDisplayed(); //each element is in the same iframe.
				//System.out.println(index); //prints the used index.

				driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
				enc = true;
				break;
			}catch(NoSuchElementException noSuchElemExcept) {
				index++;
				driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
			}
		}
		if(enc == false)
			index = -1;
		try {
				driver.switchTo().frame(frames.get(index));
		}catch(ArrayIndexOutOfBoundsException iobExcept) {System.out.println("Elemento no encontrado en ningun frame 2.");
			
		}
		
		sleep(14000);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid.ng-empty")));
		sb.elegirplan(sPlan);
		sb.continuar();
		sleep(22000);
		//sb.Crear_DomicilioLegal(sProvincia, sLocalidad, "falsa", "", "1000", "", "", "1549");
		//sleep(24000);
		WebElement sig = driver.findElement(By.id("LineAssignment_nextBtn"));
		cc.obligarclick(sig);
		sleep(20000);
		cc.obligarclick(driver.findElement(By.id("InvoicePreview_nextBtn")));
		sleep(20000);
		try {
			cc.obligarclick(driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")));
			sleep(20000);
		}catch(Exception ex1) {}
		
		sb.elegirvalidacion("DOC");
		sleep(8000);
		driver.findElement(By.id("FileDocumentImage")).sendKeys("C:\\Users\\florangel\\Downloads\\mapache.jpg");
		sleep(3000);
		cc.obligarclick(driver.findElement(By.id("DocumentMethod_nextBtn")));
		sleep(10000);
		cc.obligarclick(driver.findElement(By.id("ValidationResult_nextBtn")));
		sleep(15000);
		try {
			driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")).get(1).click();
			sleep(10000);
		}catch(Exception ex1) {}
		cc.obligarclick(driver.findElement(By.id("OrderSumary_nextBtn")));
		sleep(20000);
		try {
			cc.obligarclick(driver.findElement(By.id("Step_Error_Huawei_S029_nextBtn")));
		}catch(Exception ex1) {
			driver.findElement(By.id("SaleOrderMessages_nextBtn")).click();
		}

	}
	@Test(groups={"Sales", "AltaLineaDatos","E2E"}, priority=2, dataProvider="AltaLineaExistenteOfComTD")
	public void TS119300_CRM_Movil_PRE_Alta_Linea_Cliente_Existente_OFCOM_TD_Presencial_DNI(String sDni, String sPlan, String sBanco, String sTarjeta, String sPromo, String sNumTar, String sMes, String sAnio, String sCodSeg) throws IOException {
		imagen = "TS119300";
		CustomerCare cc = new CustomerCare(driver);
		SalesBase sb = new SalesBase(driver);
		sleep(5000);
		sb.BuscarCuenta("DNI", sDni);
		sleep(5000);
		List<WebElement> btns = driver.findElements(By.cssSelector(".slds-button.slds-button.slds-button--icon"));
		for(WebElement e: btns){
			System.out.println(e.getText());
			if(e.getText().toLowerCase().equals("catalogo")){ 
				e.click();
				break;
			}
		}
		sleep(18000);
		//sb.ResolverEntrega(driver, "Presencial","nada","nada");
		sleep(7000);
		driver.switchTo().defaultContent();
		Accounts accountPage = new Accounts(driver);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		boolean enc = false;
		int index = 0;
		for(WebElement frame : frames) {
			try {
				System.out.println("aca");
				driver.switchTo().frame(frame);

				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).getText(); //each element is in the same iframe.
				//System.out.println(index); //prints the used index.

				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).isDisplayed(); //each element is in the same iframe.
				//System.out.println(index); //prints the used index.

				driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
				enc = true;
				break;
			}catch(NoSuchElementException noSuchElemExcept) {
				index++;
				driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
			}
		}
		if(enc == false)
			index = -1;
		try {
				driver.switchTo().frame(frames.get(index));
		}catch(ArrayIndexOutOfBoundsException iobExcept) {System.out.println("Elemento no encontrado en ningun frame 2.");
			
		}
		
		sleep(14000);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid.ng-empty")));
		sb.elegirplan(sPlan);
		sb.continuar();
		sleep(22000);
		//sb.Crear_DomicilioLegal(sProvincia, sLocalidad, "falsa", "", "1000", "", "", "1549");
		//sleep(24000);
		WebElement sig = driver.findElement(By.id("LineAssignment_nextBtn"));
		cc.obligarclick(sig);
		sleep(25000);
		cc.obligarclick(driver.findElement(By.id("InvoicePreview_nextBtn")));
		sleep(20000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals", "tarjeta de d\u00e9bito");
		sleep(20000);
		driver.switchTo().frame(cambioFrame(driver, By.id("BankingEntity-0")));
		selectByText(driver.findElement(By.id("BankingEntity-0")), sBanco);
		sleep(5000);
		selectByText(driver.findElement(By.id("CardBankingEntity-0")), sTarjeta);
		sleep(5000);
		selectByText(driver.findElement(By.id("promotionsByCardsBank-0")), sPromo);
		sleep(5000);
		try {
			cc.obligarclick(driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")));
			sleep(20000);
		}catch(Exception ex1) {}
		
		sb.elegirvalidacion("DOC");
		sleep(8000);
		driver.findElement(By.id("FileDocumentImage")).sendKeys("C:\\Users\\florangel\\Downloads\\mapache.jpg");
		sleep(3000);
		cc.obligarclick(driver.findElement(By.id("DocumentMethod_nextBtn")));
		sleep(10000);
		cc.obligarclick(driver.findElement(By.id("ValidationResult_nextBtn")));
		sleep(15000);
		try {
			driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")).get(1).click();
			sleep(10000);
		}catch(Exception ex1) {}
		cc.obligarclick(driver.findElement(By.id("OrderSumary_nextBtn")));
		sleep(20000);
		try {
			cc.obligarclick(driver.findElement(By.id("Step_Error_Huawei_S029_nextBtn")));
		}catch(Exception ex1) {
			driver.findElement(By.id("SaleOrderMessages_nextBtn")).click();
		}

	}
	
	@Test(groups={"Sales", "AltaLineaDatos","E2E"}, priority=1, dataProvider="AltaLineaNuevoEquipo") //========= 31 - 8 NO HAY LINEAS
	public void TS125004_CRM_Movil_PRE_Alta_Linea_con_Equipo_Cliente_Nuevo_Presencial_AG(String sDni, String sNombre, String sApellido, String sSexo, String sFNac, String sEmail, String sPlan, String sProvincia, String sLocalidad) throws IOException {
		imagen = "TS125004";
		CustomerCare cc = new CustomerCare(driver);
		SalesBase sb = new SalesBase(driver);
		sleep(5000);
		sb.BtnCrearNuevoCliente();
		sDni = driver.findElement(By.id("SearchClientDocumentNumber")).getAttribute("value");
		ContactSearch contact = new ContactSearch(driver);
		contact.sex(sSexo);
		contact.Llenar_Contacto(sNombre, sApellido, sFNac);
		driver.findElement(By.id("EmailSelectableItems")).findElement(By.tagName("input")).sendKeys(sEmail);
		driver.findElement(By.id("Contact_nextBtn")).click();
		sleep(20000);
		List<WebElement> btns = driver.findElements(By.cssSelector(".slds-button.slds-button.slds-button--icon"));
			for(WebElement e: btns){
				if(e.getText().toLowerCase().equals("catalogo")){ 
					e.click();
					break;
				}
			}
		sleep(25000);
		sb.agregarplan(sPlan);
		sleep(12000);
		driver.findElement(By.cssSelector(".slds-input.ng-valid.ng-not-empty.ng-dirty.ng-valid-parse.ng-touched")).clear();
		sleep(3000);
		driver.findElement(By.cssSelector(".slds-input.ng-valid.ng-dirty.ng-valid-parse.ng-touched.ng-empty")).sendKeys("Galaxy S8 - Negro");
		sleep(10000);
		List<WebElement> agregar = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.add-button")); 
			for(WebElement a : agregar){
				if(a.getText().equals("Agregar")){
				a.click();
				}
			}
		sleep(5000);	
		sb.continuar();
		sleep(24000);
		sb.Crear_DomicilioLegal(sProvincia, sLocalidad, "falsa", "", "1000", "", "", "1549");
		sleep(24000);
		cc.obligarclick(driver.findElement(By.id("LineAssignment_nextBtn")));
		sleep(23000);
		cc.obligarclick(driver.findElement(By.id("ICCDAssignment_nextBtn")));
		sleep(20000);
		cc.obligarclick(driver.findElement(By.id("InvoicePreview_nextBtn")));
		sleep(20000);
		List<WebElement> medpag = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
			for(WebElement m :medpag){
				if(m.getText().equals("Efectivo")){
				cc.obligarclick(m.findElement(By.cssSelector(".slds-radio--faux")));
				}
			}
		cc.obligarclick(driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")));
		sleep(20000);
		sb.elegirvalidacion("DOC");
		sleep(8000);
		driver.findElement(By.id("FileDocumentImage")).sendKeys("C:\\Users\\Sofia Chardin\\Desktop\\DNI.jpg");
		sleep(3000);
		cc.obligarclick(driver.findElement(By.id("DocumentMethod_nextBtn")));
		sleep(10000);
		cc.obligarclick(driver.findElement(By.id("ValidationResult_nextBtn")));
		sleep(10000);
		String orden = driver.findElement(By.className("top-data")).findElement(By.className("ng-binding")).getText();
		String NCuenta = driver.findElements(By.className("top-data")).get(1).findElements(By.className("ng-binding")).get(3).getText();
		String Linea = driver.findElement(By.cssSelector(".top-data.ng-scope")).findElements(By.className("ng-binding")).get(1).getText();
		orden = orden.substring(orden.length()-8);
		NCuenta = NCuenta.substring(NCuenta.length()-16);
		Linea = Linea.substring(Linea.length()-10);
		cc.obligarclick(driver.findElement(By.id("OrderSumary_nextBtn")));
		sleep(20000);
		driver.findElement(By.id("SaleOrderMessages_nextBtn")).click();
		sleep(15000);
		CBS_Mattu invoSer = new CBS_Mattu();
		invoSer.openPage2(orden);
		sleep(5000);
		CambiarPerfil("logistica",driver);
		sb.completarLogistica(orden, driver);
		CambiarPerfil("entrega",driver);
		sb.completarEntrega(orden, driver);
		CambiarPerfil("ofcom",driver);
	}
	
	
	@Test(groups={"Sales", "AltaLineaDatos","E2E"}, priority=1, dataProvider="DatosAltaEquipoExiste") //========  31- 8 Aparece el paso de carga de datos de la cuenta
	public void TS125214_CRM_Movil_PRE_Alta_Linea_con_Equipo_Cliente_existente_Presencial_OFCOM(String sDni, String sNombre, String sApellido, String sSexo, String sFNac, String sEmail, String sPlan, String sProvincia, String sLocalidad) throws IOException {
		imagen = "TS125214";
		CustomerCare cc = new CustomerCare(driver);
		SalesBase sb = new SalesBase(driver);
		sleep(5000);
		sb.BuscarCuenta("DNI", sDni);
		sleep(5000);
		List<WebElement> btns = driver.findElements(By.cssSelector(".slds-button.slds-button.slds-button--icon"));
		for(WebElement e: btns){
			if(e.getText().toLowerCase().equals("catalogo")){ 
				e.click();
				break;
			}
		}
		sleep(25000);
		sb.elegirplan(sPlan);
		sleep(12000);
		driver.findElement(By.cssSelector(".slds-input.ng-valid.ng-not-empty.ng-dirty.ng-valid-parse.ng-touched")).clear();
		sleep(3000);
		driver.findElement(By.cssSelector(".slds-input.ng-valid.ng-dirty.ng-valid-parse.ng-touched.ng-empty")).sendKeys("Galaxy S8 - Negro");
		sleep(13000);
		List<WebElement> acept = driver.findElements(By.cssSelector(".slds-button.slds-button_neutral.cpq-add-button"));
			for(WebElement a : acept){
				System.out.println(a.getText());
				if(a.getText().equals("Agregar")){
					cc.obligarclick(a);
					break;
				}
			}
		sleep(5000);	
		sb.continuar();
		sleep(24000);
		cc.obligarclick(driver.findElement(By.id("LineAssignment_nextBtn")));
		sleep(15000);
		cc.obligarclick(driver.findElement(By.id("ICCDAssignment_nextBtn")));
		sleep(12000);
		cc.obligarclick(driver.findElement(By.id("InvoicePreview_nextBtn")));
		sleep(20000);
		List<WebElement> medpag = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
			for(WebElement m :medpag){
				if(m.getText().equals("Efectivo")){
					cc.obligarclick(m.findElement(By.cssSelector(".slds-radio--faux")));
				}
			}
		cc.obligarclick(driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")));
		sleep(20000);
		// ========================================     ACA APARECE VALIDACION POR LINEA O PREGUNTAS Y RESPUESTAS  ==================================================
		cc.obligarclick(driver.findElement(By.id("MethodSelection_nextBtn")));
		sleep(10000);
		cc.obligarclick(driver.findElement(By.id("ValidationResult_nextBtn")));
		sleep(10000);
		try {
			driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")).get(1).click();
			sleep(10000);
		}catch(Exception ex1) {}
		String orden = driver.findElement(By.className("top-data")).findElement(By.className("ng-binding")).getText();
		String NCuenta = driver.findElements(By.className("top-data")).get(1).findElements(By.className("ng-binding")).get(3).getText();
		String Linea = driver.findElement(By.cssSelector(".top-data.ng-scope")).findElements(By.className("ng-binding")).get(1).getText();
		orden = orden.substring(orden.length()-8);
		NCuenta = NCuenta.substring(NCuenta.length()-16);
		Linea = Linea.substring(Linea.length()-10);
		//00072466 9900000724810001
		cc.obligarclick(driver.findElement(By.id("OrderSumary_nextBtn")));
		sleep(20000);
		cc.obligarclick(driver.findElement(By.id("SaleOrderMessages_nextBtn")));
		sleep(15000);
		CBS_Mattu invoSer = new CBS_Mattu();
		//invoSer.openPage2(orden);
		sleep(5000);
		CambiarPerfil("logistica",driver);
		sb.completarLogistica(orden, driver);
		//CambiarPerfil("entrega",driver);
		sb.completarEntrega(orden, driver);
		CambiarPerfil("ofcom",driver);
		sb.completarEntrega(orden, driver);
	}
	@Test(groups={"Sales", "AltaLineaDatos", "E2E"}, priority=1, dataProvider="DatosAltaEquipoExiste") // ============== 31-8 no aprece el paso de ASIGNACION DE SERIALES
	public void TS_CRM_Movil_Equipo_Cliente_existente_Presencial_OFCOM(String sDni, String sNombre, String sApellido, String sSexo, String sFNac, String sEmail, String sPlan, String sProvincia, String sLocalidad) throws IOException {
		imagen = "TS_CRM_Movil_Equipo_Cliente_existente_Presencial_OFCOM";
		CustomerCare cc = new CustomerCare(driver);
		SalesBase sb = new SalesBase(driver);
		sleep(5000);
		sb.BuscarCuenta("DNI", sDni);
		sleep(5000);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		List<WebElement> btns = driver.findElements(By.cssSelector(".slds-button.slds-button.slds-button--icon"));
		for(WebElement e: btns){
			if(e.getText().toLowerCase().equals("catalogo")){ 
				e.click();
				break;
			}
		}
		sleep(20000);
		driver.findElement(By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid")).sendKeys("Galaxy");
		sleep(8000);
		List<WebElement> agregar = driver.findElements(By.cssSelector(".slds-button.slds-button_neutral.cpq-add-button")); 
			for(WebElement a : agregar){
				if(a.getText().equals("Agregar")){
					a.click();
					break;
				}
			}
		sleep(8000);	
		sb.continuar();
		sleep(22000);
		BasePage cambioFrameByID=new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("VlocityBP")));
		//System.out.println(driver.findElement(By.id("VlocityBP")).getText());
		buscarYClick(driver.findElements(By.id("InvoicePreview_nextBtn")),"equals", "Siguiente");
		System.out.println(driver.findElement(By.id("InvoicePreview_nextBtn")).getText());
		sleep(12000);
		//cc.obligarclick(driver.findElement(By.id("ICCDAssignment_nextBtn")));
		/*List<WebElement> medpag = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
		for(WebElement m :medpag){
			if(m.getText().equals("Efectivo")){
			cc.obligarclick(m.findElement(By.cssSelector(".slds-radio--faux")));
			}
		}*/
		cc.obligarclick(driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")));
		sleep(14000);
		cc.obligarclick(driver.findElement(By.id("DecisiveLineSelection_nextBtn")));
		sleep(15000);
		String oorden = driver.findElement(By.className("top-data")).findElement(By.className("ng-binding")).getText();
		String NCuenta = driver.findElements(By.className("top-data")).get(1).findElements(By.className("ng-binding")).get(3).getText();
		//String serial = driver.findElements(By.cssSelector(".top-data.ng-scope")).get(1).findElements(By.className("ng-binding")).get(1).getText();
		oorden = oorden.substring(oorden.length()-8);
		NCuenta = NCuenta.substring(NCuenta.length()-16);
		//serial = serial.substring(serial.length()-15);
		System.out.println(oorden);
		System.out.println(NCuenta);
		//System.out.println(serial);
		cc.obligarclick(driver.findElement(By.id("OrderSumary_nextBtn")));
		sleep(20000);
		try {
			cc.obligarclick(driver.findElement(By.id("Step_Error_Huawei_S029_nextBtn")));
		}catch(Exception ex1) {}
			driver.findElement(By.id("SaleOrderMessages_nextBtn")).click();
			sleep(15000);
			String orden = cc.obtenerOrdenMontoyTN(driver, "Recarga");
			System.out.println("orden = "+orden);
			DatosOrden.add("Recargas" + orden + " de cuenta "+accid+" con DNI: " + sDni);
			CBS_Mattu invoSer = new CBS_Mattu();
			invoSer.PagoEnCaja("1003", accid, "2001", orden.split("-")[2], orden.split("-")[1]);
			sleep(5000);
			CambiarPerfil("logistica",driver);
			sb.CompletarLogisticaEquipo(orden, driver);
			CambiarPerfil("entrega",driver);
			sb.completarEntrega(orden, driver);
			CambiarPerfil("ofcom",driver);
		}
	
	@Test(groups={"Sales", "AltaLineaDatos", "E2E"}, priority=1, dataProvider="AltaLineaNuevoEquipoOfCom")
	public void TS135820_CRM_Movil_Venta_Sin_Linea_Cliente_nuevo_Presencial_OFCOM_EF(String sDni, String sNombre, String sApellido, String sSexo, String sFNac, String sEmail, String sPlan, String sProvincia, String sLocalidad) throws IOException {
		imagen = "TS135820";
		CustomerCare cc = new CustomerCare(driver);
		SalesBase sb = new SalesBase(driver);
		sleep(5000);
		sb.BtnCrearNuevoCliente();
		String asd = driver.findElement(By.id("SearchClientDocumentNumber")).getAttribute("value");
		
		//sb.Crear_Cliente(sDni);
		ContactSearch contact = new ContactSearch(driver);
		contact.sex(sSexo);
		contact.Llenar_Contacto(sNombre, sApellido, sFNac);
		driver.findElement(By.id("EmailSelectableItems")).findElement(By.tagName("input")).sendKeys(sEmail);
		driver.findElement(By.id("Contact_nextBtn")).click();
		sleep(20000);
		List<WebElement> btns = driver.findElements(By.cssSelector(".slds-button.slds-button.slds-button--icon"));
			for(WebElement e: btns){
				if(e.getText().toLowerCase().equals("catalogo")){ 
					e.click();
					break;
				}
			}
		sleep(25000);
		driver.findElements(By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid")).get(0).sendKeys("Galaxy");
		sleep(10000);
		List<WebElement> agregar = driver.findElements(By.cssSelector(".slds-button.slds-button_neutral.cpq-add-button")); 
			for(WebElement a : agregar){
				if(a.getText().equals("Agregar")){
					cc.obligarclick(a);
					break;
				}
			}
		sleep(5000);	
		sb.continuar();
		sleep(24000);
		sb.Crear_DomicilioLegal(sProvincia, sLocalidad, "falsa", "", "1000", "", "", "1549");
		sleep(27000);
		cc.obligarclick(driver.findElement(By.id("InvoicePreview_nextBtn")));
		sleep(20000);
		List<WebElement> medpag = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
			for(WebElement m :medpag){
				if(m.getText().equals("Efectivo")){
				cc.obligarclick(m.findElement(By.cssSelector(".slds-radio--faux")));
				}
			}
		cc.obligarclick(driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")));
		sleep(20000);
		String oorden = driver.findElement(By.className("top-data")).findElement(By.className("ng-binding")).getText();
		String NCuenta = driver.findElements(By.className("top-data")).get(1).findElements(By.className("ng-binding")).get(3).getText();
		System.out.println(oorden);
		System.out.println(NCuenta);
		oorden = oorden.substring(oorden.length()-8);
		NCuenta = NCuenta.substring(NCuenta.length()-16);
		System.out.println(oorden);
		System.out.println(NCuenta);
		cc.obligarclick(driver.findElement(By.id("OrderSumary_nextBtn")));
		sleep(20000);
		try {
			cc.obligarclick(driver.findElement(By.id("Step_Error_Huawei_S029_nextBtn")));
		}catch(Exception ex1) {}
			driver.findElement(By.id("SaleOrderMessages_nextBtn")).click();
		sleep(15000);
		String orden = cc.obtenerMontoyTNparaAlta(driver, oorden);
		System.out.println("orden = "+orden);
		DatosOrden.add("Recargas" + orden + " de cuenta "+NCuenta+" con DNI: " + sDni);
		CBS_Mattu invoSer = new CBS_Mattu();
		invoSer.PagoEnCaja("1006", NCuenta, "1001", orden.split("-")[1], orden.split("-")[0]);
		sleep(5000);
		CambiarPerfil("logistica",driver);
		sb.CompletarLogisticaEquipo(orden, driver);    //00080481
		CambiarPerfil("entrega",driver);
		sb.completarEntrega(orden, driver);
		CambiarPerfil("ofcom",driver);
		
		
	}
	
	@Test(groups={"Sales", "AltaLineaDatos"}, priority=1, dataProvider="DatosAltaAgenteCredito")
	public void TS135761_CRM_Movil_PRE_Alta_Linea_Cliente_Nuevo_Agente_TC_Presencial_DNI_Punta_Alta(String sDni, String sNombre, String sApellido, String sSexo, String sFNac, String sEmail, String sPlan, String sProvincia, String sLocalidad, String sCalle, String sNumCa, String sCP, String cBanco, String cTarjeta, String cPromo, String cCuotas, String cNumTarjeta, String cVenceMes, String cVenceAno, String cCodSeg) throws IOException {
		imagen = "TS135761";
		CustomerCare cc = new CustomerCare(driver);
		SalesBase sb = new SalesBase(driver);
		sb.BtnCrearNuevoCliente();
		sDni = driver.findElement(By.id("SearchClientDocumentNumber")).getAttribute("value");
		
		//sb.Crear_Cliente(sDni);
		ContactSearch contact = new ContactSearch(driver);
		contact.sex(sSexo);
		contact.Llenar_Contacto(sNombre, sApellido, sFNac);
		driver.findElement(By.id("EmailSelectableItems")).findElement(By.tagName("input")).sendKeys(sEmail);
		driver.findElement(By.id("Contact_nextBtn")).click();
		sleep(28000);
		sb.ResolverEntrega(driver, "Presencial","","");
		sleep(7000);
		driver.switchTo().defaultContent();
		Accounts accountPage = new Accounts(driver);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		boolean enc = false;
		int index = 0;
		for(WebElement frame : frames) {
			try {
				System.out.println("aca");
				driver.switchTo().frame(frame);

				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).getText(); //each element is in the same iframe.
				//System.out.println(index); //prints the used index.

				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).isDisplayed(); //each element is in the same iframe.
				//System.out.println(index); //prints the used index.

				driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
				enc = true;
				break;
			}catch(NoSuchElementException noSuchElemExcept) {
				index++;
				driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
			}
		}
		if(enc == false)
			index = -1;
		try {
				driver.switchTo().frame(frames.get(index));
		}catch(ArrayIndexOutOfBoundsException iobExcept) {System.out.println("Elemento no encontrado en ningun frame 2.");
			
		}
		
		sleep(14000);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid.ng-empty")));
		sb.elegirplan(sPlan);
		sb.continuar();
		sleep(22000);
		sb.Crear_DomicilioLegal(sProvincia, sLocalidad, sCalle, "", sNumCa, "", "", sCP);
		sleep(24000);
		WebElement sig = driver.findElement(By.id("LineAssignment_nextBtn"));
		cc.obligarclick(sig);
		sleep(20000);
		String ICCID = driver.findElement(By.cssSelector(".ng-pristine.ng-untouched.ng-valid.ng-scope.ng-not-empty")).getText();
		cc.obligarclick(driver.findElement(By.id("ICCDAssignment_nextBtn")));
		sleep(30000);
		try {
			cc.obligarclick(driver.findElement(By.id("InvoicePreview_nextBtn")));
			buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals", "tarjeta de credito");
			selectByText(driver.findElement(By.id("BankingEntity-0")), cBanco);
			selectByText(driver.findElement(By.id("CardBankingEntity-0")), cTarjeta);
			selectByText(driver.findElement(By.id("promotionsByCardsBank-0")), cPromo);
			sleep(5000);
			selectByText(driver.findElement(By.id("Installment-0")), cCuotas);
			driver.findElement(By.id("CardNumber-0")).sendKeys(cNumTarjeta);
			selectByText(driver.findElement(By.id("expirationMonth-0")), cVenceMes);
			selectByText(driver.findElement(By.id("expirationYear-0")), cVenceAno);
			driver.findElement(By.id("securityCode-0")).sendKeys(cCodSeg);
			selectByText(driver.findElement(By.id("documentType-0")), "DNI");
			driver.findElement(By.id("documentNumber-0")).sendKeys(sDni);
			driver.findElement(By.id("cardHolder-0")).sendKeys(sNombre+" "+sApellido);			
		}catch(Exception ex1) {cc.obligarclick(driver.findElement(By.id("Step_Error_Huawei_S202_nextBtn")));}
		sleep(20000);
		try {
			cc.obligarclick(driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")));
			sleep(20000);
		}catch(Exception ex1) {}
		
		sb.elegirvalidacion("DOC");
		sleep(14000);
		driver.findElement(By.id("FileDocumentImage")).sendKeys("C:\\Users\\florangel\\Downloads\\mapache.jpg");
		sleep(3000);
		cc.obligarclick(driver.findElement(By.id("DocumentMethod_nextBtn")));
		sleep(35000);
		cc.obligarclick(driver.findElement(By.id("ValidationResult_nextBtn")));
		sleep(30000);
		try {
			driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")).get(1).click();
			sleep(10000);
		}catch(Exception ex1) {}
		String orden = driver.findElement(By.className("top-data")).findElement(By.className("ng-binding")).getText();
		String NCuenta = driver.findElements(By.className("top-data")).get(1).findElements(By.className("ng-binding")).get(3).getText();
		String Linea = driver.findElement(By.cssSelector(".top-data.ng-scope")).findElements(By.className("ng-binding")).get(1).getText();
		System.out.println("Orden "+orden);
		System.out.println("cuenta "+NCuenta);
		System.out.println("Linea "+Linea);
		orden = orden.substring(orden.length()-8);
		NCuenta = NCuenta.substring(NCuenta.length()-16);
		Linea = Linea.substring(Linea.length()-10);
		cc.obligarclick(driver.findElement(By.id("OrderSumary_nextBtn")));
		sleep(30000);
		try {
			cc.obligarclick(driver.findElement(By.id("Step_Error_Huawei_S029_nextBtn")));
			sleep(30000);
		}catch(Exception ex1) {
		}
		driver.findElement(By.id("SaleOrderMessages_nextBtn")).click();
		sleep(15000);
		driver.close();
		//driver.quit();
		WebDriver driver = setConexion.setupEze();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}		
		SalesBase SB = new SalesBase(driver);
		loginAgente(driver);  
		 try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
		driver.findElement(By.id("tabBar")).findElement(By.tagName("a")).click();
		sleep(10000);
		System.out.println(cc.obtenerMontoyTNparaAlta(driver, orden));
		//CBS_Mattu invoSer = new CBS_Mattu();
		//invoSer.openPage2(orden);
		sleep(2000);
		
		CambiarPerfil("logistica",driver);
		sb.completarLogistica(orden, driver);
		CambiarPerfil("entrega",driver);
		sb.completarEntrega(orden, driver);
		CambiarPerfil("agente",driver);
	}

	@Test(groups={"Sales", "AltaLineaDatos","E2E"}, priority=1, dataProvider="AltaLineaNuevoEquipoTC")
	public void TS135824_CRM_Movil_Venta_Sin_Linea_Cliente_nuevo_SPU_AG_TC(String sNombre, String sApellido, String sSexo, String sFNac, String sEmail, String sState, String sCity, String sStore, String sEquipo, String sProvincia, String sLocalidad, String sCalle, String sAltura, String sCPostal, String cBanco, String cTarjeta, String cPromo, String cCuotas, String cNumTarjeta, String cVenceMes, String cVenceAno, String cCodSeg) throws IOException {
		CustomerCare cc = new CustomerCare(driver);
		SalesBase sb = new SalesBase(driver);
		sleep(5000);
		Random aleatorio = new Random(System.currentTimeMillis());
		aleatorio.setSeed(System.currentTimeMillis());
		int intAleatorio = aleatorio.nextInt(89999999)+10000000;
		sb.Crear_Cliente(Integer.toString(intAleatorio));
		String sDni = driver.findElement(By.id("DNI")).getText();
		System.out.println(sDni);
		ContactSearch contact = new ContactSearch(driver);
		contact.sex(sSexo);
		contact.Llenar_Contacto(sNombre, sApellido, sFNac);
		driver.findElement(By.id("EmailSelectableItems")).findElement(By.tagName("input")).sendKeys(sEmail);
		driver.findElement(By.id("Contact_nextBtn")).click();
		sleep(20000);
		List<WebElement> botones = driver.findElements(By.cssSelector(".slds-m-left--x-small.slds-button.slds-button--brand")); //
		for(WebElement UnB : botones) {
			System.out.println("UnBoton= "+UnB.getText());
			if(UnB.getText().equalsIgnoreCase("cambiar")) {
				UnB.click();
				break;
			}
		}
		sleep(15000);
		List<WebElement> frame2 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame2.get(0));
		Select env = new Select (driver.findElement(By.id("DeliveryMethod")));
		env.selectByVisibleText("Store Pick Up");
		sleep(4000);
		Select prov = new Select (driver.findElement(By.id("State")));
		prov.selectByVisibleText(sState);
		sleep(4000);
		Select city = new Select (driver.findElement(By.id("City")));
		city.selectByVisibleText(sCity);
		sleep(4000);
		driver.findElement(By.id("Store")).click();
		sleep(4000);
		driver.findElement(By.cssSelector(".slds-list__item.ng-binding.ng-scope")).click();
		sleep(4000);
		driver.findElement(By.id("SalesChannelConfiguration_nextBtn")).click();
		sleep(7000);
		driver.switchTo().defaultContent();
		Accounts accountPage = new Accounts(driver);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		boolean enc = false;
		int index = 0;
		for(WebElement frame : frames) {
			try {
				System.out.println("aca");
				driver.switchTo().frame(frame);

				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).getText(); //each element is in the same iframe.
				//System.out.println(index); //prints the used index.

				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).isDisplayed(); //each element is in the same iframe.
				//System.out.println(index); //prints the used index.

				driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
				enc = true;
				break;
			}catch(NoSuchElementException noSuchElemExcept) {
				index++;
				driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
			}
		}
		if(enc == false)
			index = -1;
		try {
				driver.switchTo().frame(frames.get(index));
		}catch(ArrayIndexOutOfBoundsException iobExcept) {System.out.println("Elemento no encontrado en ningun frame 2.");
				
		}
		
		sleep(14000);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid.ng-empty")));
		try {
			driver.findElement(By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid")).sendKeys(sEquipo);	}
		catch(Exception ex1) {driver.findElement(By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys(sEquipo);}
		try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> agregar = driver.findElements(By.cssSelector(".slds-button.slds-button_neutral.cpq-add-button")); 
		agregar.get(0).click();
		sb.continuar();
		sleep(22000);
		sb.Crear_DomicilioLegal(sProvincia, sLocalidad, sCalle, "", sAltura, "", "", sCPostal);
		sleep(24000);
		cc.obligarclick(driver.findElement(By.id("DeliveryMethodConfiguration_nextBtn")));
		sleep(12000);
		try { // ========================================   ACA   QUEDO   ==============================================
			cc.obligarclick(driver.findElement(By.id("InvoicePreview_nextBtn")));       
			buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals", "tarjeta de credito");
			selectByText(driver.findElement(By.id("BankingEntity-0")), cBanco);
			selectByText(driver.findElement(By.id("CardBankingEntity-0")), cTarjeta);
			selectByText(driver.findElement(By.id("promotionsByCardsBank-0")), cPromo);
			sleep(5000);
			selectByText(driver.findElement(By.id("Installment-0")), cCuotas);
			driver.findElement(By.id("CardNumber-0")).sendKeys(cNumTarjeta);
			selectByText(driver.findElement(By.id("expirationMonth-0")), cVenceMes);
			selectByText(driver.findElement(By.id("expirationYear-0")), cVenceAno);
			driver.findElement(By.id("securityCode-0")).sendKeys(cCodSeg);
			selectByText(driver.findElement(By.id("documentType-0")), "DNI");
			//driver.findElement(By.id("documentNumber-0")).sendKeys(sDni);
			driver.findElement(By.id("cardHolder-0")).sendKeys(sNombre+" "+sApellido);			
		}catch(Exception ex1) {cc.obligarclick(driver.findElement(By.id("Step_Error_Huawei_S202_nextBtn")));}
		sleep(20000);
		try {
			cc.obligarclick(driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")));
			sleep(20000);
		}catch(Exception ex1) {}
		
		/*sb.elegirvalidacion("DOC");
		sleep(14000);
		driver.findElement(By.id("FileDocumentImage")).sendKeys("C:\\Users\\florangel\\Downloads\\mapache.jpg");
		sleep(3000);
		cc.obligarclick(driver.findElement(By.id("DocumentMethod_nextBtn")));
		sleep(35000);
		cc.obligarclick(driver.findElement(By.id("ValidationResult_nextBtn")));
		sleep(30000);*/
		try {
			driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")).get(1).click();
			sleep(10000);
		}catch(Exception ex1) {}
		String orden = driver.findElement(By.className("top-data")).findElement(By.className("ng-binding")).getText();
		String NCuenta = driver.findElements(By.className("top-data")).get(1).findElements(By.className("ng-binding")).get(3).getText();
		String Linea = driver.findElement(By.cssSelector(".top-data.ng-scope")).findElements(By.className("ng-binding")).get(1).getText();
		System.out.println("Orden "+orden);
		System.out.println("cuenta "+NCuenta);
		//System.out.println("Linea "+Linea);
		orden = orden.substring(orden.length()-8);
		NCuenta = NCuenta.substring(NCuenta.length()-16);
		//Linea = Linea.substring(Linea.length()-10);
		cc.obligarclick(driver.findElement(By.id("OrderSumary_nextBtn")));
		sleep(30000);
		try {
			cc.obligarclick(driver.findElement(By.id("Step_Error_Huawei_S029_nextBtn")));
			sleep(30000);
		}catch(Exception ex1) {
		}
		driver.findElement(By.id("SaleOrderMessages_nextBtn")).click();
		sleep(15000);
		driver.close();
		//driver.quit();
		WebDriver driver = setConexion.setupEze();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}		
		SalesBase SB = new SalesBase(driver);
		loginAgente(driver);  
		 try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
		driver.findElement(By.id("tabBar")).findElement(By.tagName("a")).click();
		sleep(10000);
		System.out.println(cc.obtenerMontoyTNparaAlta(driver, orden));
		//CBS_Mattu invoSer = new CBS_Mattu();
		//invoSer.openPage2(orden);
		sleep(2000);
		
		CambiarPerfil("logistica",driver);
		sb.completarLogistica(orden, driver);
		CambiarPerfil("entrega",driver);
		sb.completarEntrega(orden, driver);
		CambiarPerfil("agente",driver);
	}	
	
	@Test(groups={"Sales", "AltaLineaDatos","E2E"}, priority=1, dataProvider="AltaLineaExisteEquipoOfComTD")
	public void TS135810_CRM_Movil_Venta_Sin_Linea_Cliente_existente_Presencial_AG_TD(String sDni, String sEquipo, String cBanco, String cTarjeta, String cPromo, String cNumTarjeta, String cVenceMes, String cVenceAno, String cCodSeg) throws IOException {
		CustomerCare cc = new CustomerCare(driver);
		SalesBase sb = new SalesBase(driver);
		sleep(5000);
		sb.BuscarCuenta("DNI", sDni);
		sleep(5000);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		List<WebElement> btns = driver.findElements(By.cssSelector(".slds-button.slds-button.slds-button--icon"));
		for(WebElement e: btns){
			if(e.getText().toLowerCase().equals("catalogo")){ 
				e.click();
				break;
			}
		}
		sleep(20000);
		try {
			driver.findElement(By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid")).sendKeys(sEquipo);	}
		catch(Exception ex1) {driver.findElement(By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys(sEquipo);}
		try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> agregar = driver.findElements(By.cssSelector(".slds-button.slds-button_neutral.cpq-add-button")); 
		agregar.get(0).click();
		sb.continuar();
		sleep(22000);
		cc.obligarclick(driver.findElement(By.id("InvoicePreview_nextBtn")));
		sleep(10000);
		cc.obligarclick(driver.findElement(By.id("InvoicePreview_nextBtn")));       
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals", "tarjeta de credito");
		selectByText(driver.findElement(By.id("BankingEntity-0")), cBanco);
		selectByText(driver.findElement(By.id("CardBankingEntity-0")), cTarjeta);
		selectByText(driver.findElement(By.id("promotionsByCardsBank-0")), cPromo);
		sleep(5000);
		driver.findElement(By.id("CardNumber-0")).sendKeys(cNumTarjeta);
		selectByText(driver.findElement(By.id("expirationMonth-0")), cVenceMes);
		selectByText(driver.findElement(By.id("expirationYear-0")), cVenceAno);
		driver.findElement(By.id("securityCode-0")).sendKeys(cCodSeg);
		selectByText(driver.findElement(By.id("documentType-0")), "DNI");
	}
	@Test(groups={"Sales", "AltaLineaDatos","E2E"}, priority=1, dataProvider="AltaEquipoExisteSPU") 
	public void TS125211_CRM_Movil_PRE_Alta_Linea_Con_Equipo_Cliente_Existente_Store_PickUp_OFCOM(String sDni, String sPlan, String sEquipo, String sStoreProv, String sStoreLoc) throws IOException {
		imagen = "TS125211";
		CustomerCare cc = new CustomerCare(driver);
		SalesBase sb = new SalesBase(driver);
		sleep(5000);
		sb.BuscarCuenta("DNI", sDni);
		sleep(5000);
		List<WebElement> btns = driver.findElements(By.cssSelector(".slds-button.slds-button.slds-button--icon"));
		for(WebElement e: btns){
			if(e.getText().toLowerCase().equals("catalogo")){ 
				e.click();
				break;
			}
		}
		sleep(25000);
		sb.ResolverEntrega(driver, "Store Pick Up",sStoreProv,sStoreLoc);
		sb.elegirplan(sPlan);
		sleep(12000);
		driver.findElement(By.cssSelector(".slds-input.ng-valid.ng-not-empty.ng-dirty.ng-valid-parse.ng-touched")).clear();
		sleep(3000);
		driver.findElement(By.cssSelector(".slds-input.ng-valid.ng-dirty.ng-valid-parse.ng-touched.ng-empty")).sendKeys("Galaxy S8 - Negro");
		sleep(13000);
		List<WebElement> acept = driver.findElements(By.cssSelector(".slds-button.slds-button_neutral.cpq-add-button"));
			for(WebElement a : acept){
				System.out.println(a.getText());
				if(a.getText().equals("Agregar")){
					cc.obligarclick(a);
					break;
				}
			}
		sleep(5000);	
		sb.continuar();
		sleep(24000);
		cc.obligarclick(driver.findElement(By.id("LineAssignment_nextBtn")));
		sleep(15000);
		cc.obligarclick(driver.findElement(By.id("ICCDAssignment_nextBtn")));
		sleep(12000);
		cc.obligarclick(driver.findElement(By.id("InvoicePreview_nextBtn")));
		sleep(20000);
		List<WebElement> medpag = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
			for(WebElement m :medpag){
				if(m.getText().equals("Efectivo")){
					cc.obligarclick(m.findElement(By.cssSelector(".slds-radio--faux")));
				}
			}
		cc.obligarclick(driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")));
		sleep(20000);
		// ========================================     ACA APARECE VALIDACION POR LINEA O PREGUNTAS Y RESPUESTAS  ==================================================
		cc.obligarclick(driver.findElement(By.id("MethodSelection_nextBtn")));
		sleep(10000);
		cc.obligarclick(driver.findElement(By.id("ValidationResult_nextBtn")));
		sleep(10000);
		try {
			driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")).get(1).click();
			sleep(10000);
		}catch(Exception ex1) {}
		String orden = driver.findElement(By.className("top-data")).findElement(By.className("ng-binding")).getText();
		String NCuenta = driver.findElements(By.className("top-data")).get(1).findElements(By.className("ng-binding")).get(3).getText();
		String Linea = driver.findElement(By.cssSelector(".top-data.ng-scope")).findElements(By.className("ng-binding")).get(1).getText();
		orden = orden.substring(orden.length()-8);
		NCuenta = NCuenta.substring(NCuenta.length()-16);
		Linea = Linea.substring(Linea.length()-10);
		//00072466 9900000724810001
		cc.obligarclick(driver.findElement(By.id("OrderSumary_nextBtn")));
		sleep(20000);
		cc.obligarclick(driver.findElement(By.id("SaleOrderMessages_nextBtn")));
		sleep(15000);
		CBS_Mattu invoSer = new CBS_Mattu();
		//invoSer.openPage2(orden);
		sleep(5000);
		CambiarPerfil("logistica",driver);
		sb.completarLogistica(orden, driver);
		//CambiarPerfil("entrega",driver);
		sb.completarEntrega(orden, driver);
		CambiarPerfil("ofcom",driver);
		sb.completarEntrega(orden, driver);
	}
}



