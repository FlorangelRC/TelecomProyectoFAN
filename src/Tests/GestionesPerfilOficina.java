package Tests;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.BasePage;
import Pages.CBS;
import Pages.ContactSearch;
import Pages.CustomerCare;
import Pages.Marketing;
import Pages.OM;
import Pages.PagePerfilTelefonico;
import Pages.SalesBase;
import Pages.TechCare_Ola1;
import Pages.TechnicalCareCSRAutogestionPage;
import Pages.TechnicalCareCSRDiagnosticoPage;
import Pages.setConexion;

public class GestionesPerfilOficina extends TestBase {

	private WebDriver driver;
	private SalesBase sb;
	private CustomerCare cc;
	private CBS cbs;
	private CBS_Mattu cbsm;
	private Marketing mk;
	private PagePerfilTelefonico ppt;
	List<String> sOrders = new ArrayList<String>();
	String imagen;
	String detalles;
	
	@BeforeClass(alwaysRun=true)
	public void init() {
		driver = setConexion.setupEze();
		sleep(5000);
		sb = new SalesBase(driver);
		cc = new CustomerCare(driver);
		cbs = new CBS();
		cbsm = new CBS_Mattu();
		mk = new Marketing(driver);
		loginOfCom(driver);
		sleep(22000);
		try {
			cc.cajonDeAplicaciones("Consola FAN");
		} catch(Exception e) {
			sleep(3000);
			driver.findElement(By.id("tabBar")).findElement(By.tagName("a")).click();
			sleep(6000);
		}
		driver.switchTo().defaultContent();
		sleep(6000);
	}
	
	@BeforeMethod(alwaysRun=true)
	public void setup() throws Exception {
		sleep(3000);
		goToLeftPanel2(driver, "Inicio");
		sleep(13000);
		try {
			sb.cerrarPestaniaGestion(driver);
		} catch (Exception ex1) {}
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
		
		sleep(15000);
	}

	@AfterMethod(alwaysRun=true)
	public void after() throws IOException {
		guardarListaTxt(sOrders);
		sOrders.clear();
		tomarCaptura(driver,imagen);
		sleep(2000);
	}

	//@AfterClass(alwaysRun=true)
	public void quit() throws IOException {
		driver.quit();
		sleep(5000);
	}
	
	@Test (groups = {"GestionesPerfilOficina","NumerosAmigos","E2E", "Ciclo1"}, dataProvider="NumerosAmigos")
	public void TS100602_CRM_Movil_REPRO_FF_Alta_Presencial(String sDNI, String sLinea, String sNumeroVOZ, String sNumeroSMS) {
		imagen = "TS100602";
		detalles = null;
		detalles = imagen+"-Numeros Amigos-DNI:"+sDNI;
		BasePage cambioFrame=new BasePage();
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI", sDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		detalles +="-Cuenta:"+accid;
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
		driver.findElement(By.cssSelector(".OSradioButton.ng-scope.only-buttom")).click();
		sleep(15000);
		List <WebElement> wMessage = driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope")).findElement(By.className("ng-binding")).findElements(By.tagName("p"));
		boolean bAssert = wMessage.get(1).getText().contains("La orden se realiz\u00f3 con \u00e9xito!");
		Assert.assertTrue(bAssert);
		sleep(5000);
		CBS cCBS = new CBS();
		CBS_Mattu cCBSM = new CBS_Mattu();
		if (iIndice == 0)
			Assert.assertTrue(cCBS.validarNumeroAmigos(cCBSM.Servicio_QueryCustomerInfo(sLinea), "voz", sNumeroVOZ));
		else
			Assert.assertTrue(cCBS.validarNumeroAmigos(cCBSM.Servicio_QueryCustomerInfo(sLinea), "sms", sNumeroSMS));
		sOrders.add(cCC.obtenerOrden(driver, "N\u00fameros Gratis"));
		String orden = cc.obtenerOrden(driver, "Numero Gratis");
		detalles +="-Orden:"+orden;
		sOrders.add("Numeros amigos, orden numero: " + orden + " con numero de DNI: " + sDNI);
		sleep(10000);
		BasePage bBP = new BasePage();
		bBP.closeTabByName(driver, "N\u00fameros Gratis");
		cCC.seleccionarCardPornumeroLinea(sLinea, driver);
		cCC.irAGestionEnCard("N\u00fameros Gratis");
		Assert.assertTrue(mMarketing.verificarNumerosAmigos(driver, sNumeroVOZ, sNumeroSMS));
		Assert.assertTrue(cc.corroborarEstadoCaso(orden, "Activated"));
		sOrders.add("Numeros amigos, orden numero: " + orden + ", DNI: " + sDNI);
		//Verify when the page works
	}
	
	@Test (groups = {"GestionesPerfilOficina","NumerosAmigos","E2E","Ciclo1"}, dataProvider="NumerosAmigosModificacion")
	public void TS100604_CRM_Movil_REPRO_FF_Modificacion_Presencial(String sDNI, String sLinea, String sNumeroVOZ, String sNumeroSMS) {
		imagen = "TS100604";
		detalles = null;
		detalles = imagen+"-Numeros Amigos-DNI:"+sDNI;
		BasePage cambioFrame=new BasePage();
		CBS cCBS = new CBS();
		CBS_Mattu cCBSM = new CBS_Mattu();
		String sMainBalance = cCBS.ObtenerValorResponse(cCBSM.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer iMainBalance = Integer.parseInt(sMainBalance.substring(0, (sMainBalance.length()) - 1));
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI", sDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(15000);
		
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
				wNumerosAmigos.get(0).findElement(By.tagName("input")).clear();
				wNumerosAmigos.get(0).findElement(By.tagName("input")).sendKeys(sNumeroVOZ);
				break;
			case 1:
				wNumerosAmigos.get(0).findElement(By.tagName("input")).clear();
				wNumerosAmigos.get(1).findElement(By.tagName("input")).sendKeys(sNumeroSMS);
				break;
			default:
				Assert.assertTrue(false);
		}
		sleep(5000);
		driver.findElement(By.cssSelector(".OSradioButton.ng-scope.only-buttom")).click();
		sleep(5000);
		List<WebElement> opcs = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
		for(WebElement UnaO: opcs) {
			if(UnaO.getText().equalsIgnoreCase("si")) {
				UnaO.click();
				break;
			}
		}
		cCC.obligarclick(driver.findElement(By.id("ChargeConfirmation_nextBtn")));
		sleep(20000);
		List <WebElement> wMessage = driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope")).findElement(By.className("ng-binding")).findElements(By.tagName("p"));
		boolean bAssert = wMessage.get(1).getText().contains("La orden se realiz\u00f3 con \u00e9xito!");
		if (iIndice == 0)
			Assert.assertTrue(cCBS.validarNumeroAmigos(cCBSM.Servicio_QueryCustomerInfo(sLinea), "voz",sNumeroVOZ));
		else
			Assert.assertTrue(cCBS.validarNumeroAmigos(cCBSM.Servicio_QueryCustomerInfo(sLinea), "sms",sNumeroSMS));
		sOrders.add(cCC.obtenerOrden(driver, "N\u00fameros Gratis"));
		Assert.assertTrue(bAssert);
		sleep(5000);
		String orden = cc.obtenerOrden(driver, "Numero Gratis");
		sOrders.add("Numeros amigos, orden numero: " + orden + " con numero de DNI: " + sDNI);
		sleep(10000);
		BasePage bBP = new BasePage();
		bBP.closeTabByName(driver, "N\u00fameros Gratis");
		cCC.seleccionarCardPornumeroLinea(sLinea, driver);
		cCC.irAGestionEnCard("N\u00fameros Gratis");
		String uMainBalance = cCBS.ObtenerValorResponse(cCBSM.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer uiMainBalance = Integer.parseInt(uMainBalance.substring(0, (uMainBalance.length()) - 1));
		Assert.assertTrue(iMainBalance-2050000 >= uiMainBalance);
		Assert.assertTrue(mMarketing.verificarNumerosAmigos(driver, sNumeroVOZ, sNumeroSMS));
		//Verify when the page works
	}
	
	@Test (groups = {"GestionesPerfilOficina","NumerosAmigos","E2E","Ciclo1"}, dataProvider="NumerosAmigosBaja")
	public void TS100605_CRM_Movil_REPRO_FF_Baja_Presencial(String sDNI, String sLinea, String sVOZorSMS) throws AWTException {
		imagen = "TS100605";
		detalles = null;
		detalles = imagen+"-Numeros Amigos-DNI:"+sDNI;
		CBS cCBS = new CBS();
		CBS_Mattu cCBSM = new CBS_Mattu();
		String sMainBalance = cCBS.ObtenerValorResponse(cCBSM.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer iMainBalance = Integer.parseInt(sMainBalance.substring(0, (sMainBalance.length()) - 1));
		BasePage cambioFrame=new BasePage();
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI", sDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		detalles +="-Cuenta:"+accid;
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(15000);
		
		CustomerCare cCC = new CustomerCare(driver);
		cCC.seleccionarCardPornumeroLinea(sLinea, driver);
		sleep(3000);
		cCC.irAGestionEnCard("N\u00fameros Gratis");
		
		sleep(5000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-col--padded.slds-size--1-of-2")));
		List<WebElement> wNumerosAmigos = driver.findElements(By.cssSelector(".slds-col--padded.slds-size--1-of-2"));
		wNumerosAmigos.get(Integer.parseInt(sVOZorSMS)).click();
		Robot r = new Robot();    
		for(int i = 0; i<10;i++) {
			r.keyPress(KeyEvent.VK_BACK_SPACE); 
			r.keyRelease(KeyEvent.VK_BACK_SPACE);
		}
		sleep(2000);
		cCC.obligarclick(driver.findElement(By.cssSelector(".OSradioButton.ng-scope.only-buttom")));
		sleep(5000);
		List<WebElement> opcs = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
		for(WebElement UnaO: opcs) {
			if(UnaO.getText().equalsIgnoreCase("si")) {
				UnaO.click();
				break;
			}
		}
		cCC.obligarclick(driver.findElement(By.id("ChargeConfirmation_nextBtn")));
		sleep(15000);
		List <WebElement> wMessage = driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope")).findElement(By.className("ng-binding")).findElements(By.tagName("p"));
		boolean bAssert = wMessage.get(1).getText().contains("La orden se realiz\u00f3 con \u00e9xito!");
		if (Integer.parseInt(sVOZorSMS) == 0)
			Assert.assertFalse(cCBS.validarNumeroAmigos(cCBSM.Servicio_QueryCustomerInfo(sLinea), "voz",""));
		else
			Assert.assertFalse(cCBS.validarNumeroAmigos(cCBSM.Servicio_QueryCustomerInfo(sLinea), "sms",""));
		String uMainBalance = cCBS.ObtenerValorResponse(cCBSM.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer uiMainBalance = Integer.parseInt(uMainBalance.substring(0, (uMainBalance.length()) - 1));
		Assert.assertTrue(iMainBalance-2050000 >= uiMainBalance);
		sOrders.add(cCC.obtenerOrden(driver, "N\u00fameros Gratis"));
		Assert.assertTrue(bAssert);
		sleep(5000);
		String orden = cc.obtenerOrden(driver, "Numero Gratis");
		detalles +="-Orden:"+orden;
		sleep(10000);
		BasePage bBP = new BasePage();
		bBP.closeTabByName(driver, "N\u00fameros Gratis");
		sOrders.add(cCC.obtenerOrden(driver, "N\u00fameros Gratis"));
		cCC.seleccionarCardPornumeroLinea(sLinea, driver);
		cCC.irAGestionEnCard("N\u00fameros Gratis");
		wNumerosAmigos = driver.findElements(By.cssSelector(".slds-col--padded.slds-size--1-of-2"));
		Assert.assertTrue(wNumerosAmigos.get(Integer.parseInt(sVOZorSMS)).getText().isEmpty());
		//Verify when the page works
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Recargas","E2E","Ciclo1"}, dataProvider = "RecargaEfectivo")
	public void TS134318_CRM_Movil_REPRO_Recargas_Presencial_Efectivo_Ofcom(String cDNI, String cMonto, String cLinea) throws AWTException {
		sleep(6000);
		imagen = "TS134318"+cDNI;
		detalles = null;
		detalles = imagen+"-Recarga-DNI:"+cDNI;
		CBS cCBS = new CBS();
		CBS_Mattu cCBSM = new CBS_Mattu();
		String sMainBalance = cCBS.ObtenerValorResponse(cCBSM.Servicio_queryLiteBySubscriber(cLinea), "bcs:MainBalance");
		Integer iMainBalance = Integer.parseInt(sMainBalance.substring(0, (sMainBalance.length()) - 1));
		if(cMonto.length() >= 4) {
			cMonto = cMonto.substring(0, cMonto.length()-1);
		}
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(24000);
		CustomerCare cCC = new CustomerCare(driver);
		cCC.seleccionarCardPornumeroLinea(cLinea,driver);
		sleep(3000);
		cc.irAGestionEnCard("Recarga de cr\u00e9dito");
		sleep(18000);
		driver.switchTo().frame(cambioFrame(driver, By.id("RefillAmount")));
		driver.findElement(By.id("RefillAmount")).sendKeys(cMonto);
		driver.findElement(By.id("AmountSelectionStep_nextBtn")).click();
		sleep(15000);
		String sOrden = cc.obtenerOrden3(driver);
		driver.findElement(By.id("InvoicePreview_nextBtn")).click();
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals", "efectivo");
		driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")).click();
		sleep(20000);
		String msj = driver.findElement(By.cssSelector(".message.description.ng-binding.ng-scope")).getText(); 
		String check = driver.findElement(By.id("GeneralMessageDesing")).getText();
		Assert.assertTrue(msj.toLowerCase().contains("se ha enviado correctamente la factura a huawei. dirigirse a caja para realizar el pago de la misma"));
		Assert.assertTrue(check.toLowerCase().contains("la orden se realiz\u00f3 con \u00e9xito"));
		String orden = cc.obtenerTNyMonto2(driver, sOrden);
		//String orden = cc.obtenerOrdenMontoyTN(driver, "Recarga");
		System.out.println("orden = "+orden);
		sOrders.add("Recargas" + orden + ", cuenta:"+accid+", DNI: " + cDNI +", Monto:"+orden.split("-")[2]);
		
		CBS_Mattu invoSer = new CBS_Mattu();
		Assert.assertTrue(invoSer.PagoEnCaja("1006", accid, "1001", orden.split("-")[2], orden.split("-")[1],driver));
		sleep(5000);
		driver.navigate().refresh();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".hasMotif.orderTab.detailPage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		WebElement tabla = driver.findElement(By.id("ep")).findElements(By.tagName("table")).get(1);
		String datos = tabla.findElements(By.tagName("tr")).get(4).findElements(By.tagName("td")).get(1).getText();
		Assert.assertTrue(datos.equalsIgnoreCase("activada")||datos.equalsIgnoreCase("activated"));
		String uMainBalance = cCBS.ObtenerValorResponse(cCBSM.Servicio_queryLiteBySubscriber(cLinea), "bcs:MainBalance");
		System.out.println("saldo nuevo "+uMainBalance);
		Integer uiMainBalance = Integer.parseInt(uMainBalance.substring(0, (uMainBalance.length()) - 1));
		Integer monto = Integer.parseInt(orden.split("-")[2].replace(".", ""));
		monto = Integer.parseInt(monto.toString().substring(0, monto.toString().length()-1));
		System.out.println("monto inicial "+iMainBalance);
		System.out.println("monto recarga "+monto);
		System.out.println("monto uifinal "+uiMainBalance);
		monto = iMainBalance+monto;
		System.out.println("Sumatoria :"+monto);
		Assert.assertTrue(monto == uiMainBalance);

	}
	
	@Test (groups = {"GestionesPerfilOficina", "Recargas","E2E","Ciclo1"}, dataProvider = "RecargaTC")
	public void TS134330_CRM_Movil_REPRO_Recargas_Presencial_TC_Ofcom_Financiacion(String cDNI, String cMonto, String cLinea, String cBanco, String cTarjeta, String cPromo,String cCuotas,String cNumTarjeta, String cVenceMes, String cVenceAno, String cCodSeg, String cTipoDNI, String cDNITarjeta, String cTitular) throws AWTException, KeyManagementException, NoSuchAlgorithmException {
		imagen = "TS134330";
		detalles = null;
		detalles = imagen+"-Recarga - DNI:"+cDNI;
		CBS cCBS = new CBS();
		CBS_Mattu cCBSM = new CBS_Mattu();
		String sMainBalance = cCBS.ObtenerValorResponse(cCBSM.Servicio_queryLiteBySubscriber(cLinea), "bcs:MainBalance");
		Integer iMainBalance = Integer.parseInt(sMainBalance.substring(0, (sMainBalance.length()) - 1));
		System.out.println("monto "+iMainBalance);
		if(cMonto.length() >= 4) {
			cMonto = cMonto.substring(0, cMonto.length()-1);
		}
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(25000);
		cc.seleccionarCardPornumeroLinea(cLinea, driver);
		sleep(5000);
		cc.irAGestionEnCard("Recarga de cr\u00e9dito");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("RefillAmount")));
		driver.findElement(By.id("RefillAmount")).sendKeys(cMonto);
		sleep(15000);
		driver.findElement(By.id("AmountSelectionStep_nextBtn")).click();
		sleep(15000);
		String sOrden = cc.obtenerOrden3(driver);
		//driver.switchTo().frame(cambioFrame(driver, By.id("InvoicePreview_nextBtn")));
		driver.findElement(By.xpath("//*[@id=\"InvoicePreview_nextBtn\"]")).click();
		sleep(20000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals", "tarjeta de credito");
		sleep(20000);
		//driver.switchTo().frame(cambioFrame(driver, By.id("BankingEntity-0")));
		selectByText(driver.findElement(By.id("BankingEntity-0")), cBanco);
		sleep(5000);
		selectByText(driver.findElement(By.id("CardBankingEntity-0")), cTarjeta);
		sleep(5000);
		selectByText(driver.findElement(By.id("promotionsByCardsBank-0")), cPromo);
		sleep(5000);
		selectByText(driver.findElement(By.id("Installment-0")), cCuotas);
		sleep(5000);
		driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")).click();
		sleep(20000);
		//String sOrden = cc.obtenerOrden3(driver);
		buscarYClick(driver.findElements(By.id("InvoicePreview_nextBtn")), "equals", "siguiente");
		sleep(20000);
		List <WebElement> exis = driver.findElements(By.id("GeneralMessageDesing"));
		boolean a = false;
		for(WebElement x : exis) {
			if(x.getText().toLowerCase().contains("la orden se realiz\u00f3 con \u00e9xito")) {
				a = true;
			}
			Assert.assertTrue(a);
		}
		String orden = cc.obtenerTNyMonto2(driver, sOrden);
		System.out.println("orden = "+orden);
		sOrders.add("Recargas" + orden + ", cuenta:"+accid+", DNI: " + cDNI +", Monto:"+orden.split("-")[2]);
		CBS_Mattu invoSer = new CBS_Mattu();
		invoSer.PagarTCPorServicio(sOrden);
		sleep(5000);
		driver.navigate().refresh();
		sleep(10000);
		String uMainBalance = cCBS.ObtenerValorResponse(cCBSM.Servicio_queryLiteBySubscriber(cLinea), "bcs:MainBalance");
		System.out.println("saldo nuevo "+uMainBalance);
		Integer uiMainBalance = Integer.parseInt(uMainBalance.substring(0, (uMainBalance.length()) - 1));
		Integer monto = Integer.parseInt(orden.split("-")[2].replace(".", ""));
		monto = Integer.parseInt(monto.toString().substring(0, monto.toString().length()-1));
		monto = iMainBalance+monto;
		Assert.assertTrue(monto == uiMainBalance);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".hasMotif.orderTab.detailPage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		WebElement tabla = driver.findElement(By.id("ep")).findElements(By.tagName("table")).get(1);
		String datos = tabla.findElements(By.tagName("tr")).get(4).findElements(By.tagName("td")).get(1).getText();
		Assert.assertTrue(datos.equalsIgnoreCase("activada")||datos.equalsIgnoreCase("activated"));
		
	}
	
	@Test (groups = {"GestionesPerfilOficina","E2E","Ciclo3","ABMDeServicio"}, dataProvider="BajaServicios")
	public void TS134338_CRM_Movil_PRE_Baja_de_Servicio_sin_costo_DDI_con_Roaming_Internacional_Presencial(String sDNI, String sLinea){
		imagen = "TS134338";
		detalles = null;
		detalles = imagen+"-BajaServicio-DNI:"+sDNI;
		BasePage cambioFrameByID=new BasePage();
		sleep(30000);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI",sDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		detalles +="-Cuenta:"+accid;
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(18000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(5000);
		cc.irAGestionEnCard("Alta/Baja de Servicios");
		sleep(35000);
		cc.openrightpanel();
		cc.closerightpanel();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-text-body--small.slds-page-header__info.taDevider")));
		String sOrder = driver.findElement(By.cssSelector(".slds-text-body--small.slds-page-header__info.taDevider")).getText();
		sOrder = sOrder.replace("Nro. Orden:", "");
		sOrder = sOrder.replace(" ", "");
		detalles +="-Orden:"+sOrder;
		detalles +="-Servicio:DDIconRoamingInternacional";
		try {
			cc.closeleftpanel();
		}
		catch (Exception x) {
			//Always empty
		}
		try {
			driver.findElement(By.id("ext-comp-1039__scc-st-10")).click();
		}
		catch (Exception x) {
			//Always empty
		}
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.id("tab-default-1")));
		sleep(15000);
		driver.findElement(By.cssSelector(".slds-button.cpq-item-has-children")).click();
		sleep(5000);
		boolean bAssert = false;
		List<WebElement> servicios= driver.findElements(By.xpath("//*[@class='cpq-item-product-child-level-1 cpq-item-child-product-name-wrapper']"));
		for(WebElement a: servicios) {
			if (a.getText().toLowerCase().contains("servicios basicos general movil".toLowerCase())) {
					a.findElement(By.tagName("button")).click();
						sleep(8000);
						bAssert= true;
						break;
			}
		}
		Assert.assertTrue(bAssert);
		sleep(17000);
		bAssert = false;
		List <WebElement> ddi = driver.findElements(By.cssSelector(".cpq-item-product-child-level-2.cpq-item-child-product-name-wrapper"));
		for(WebElement d : ddi){
			if(d.getText().contains("DDI")){
			   cc.obligarclick(d.findElement(By.cssSelector(".slds-button.slds-button_icon-small")));
			   bAssert = true;
			   break;
			}
		}
		Assert.assertTrue(bAssert);
		sleep(10000);
		List <WebElement> roam = driver.findElements(By.cssSelector(".cpq-item-base-product"));
		for(WebElement r : roam){
			if(r.getText().contains("DDI con Roaming Internacional")){
				sleep(5000);
				cc.obligarclick(r.findElement(By.cssSelector(".slds-button.slds-button_icon-border-filled.cpq-item-actions-dropdown-button")));
				sleep(5000);
				break;
			}
		}
		List<WebElement> delet = driver.findElements(By.cssSelector(".slds-dropdown__item.cpq-item-actions-dropdown__item"));
			for(WebElement d : delet){
				if(d.equals("Delete")){
				d.click();
				sleep(4000);
				break;
			}
		}
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-button.slds-button--destructive")));
		driver.findElement(By.cssSelector(".slds-button.slds-button--destructive")).click();
		sleep(10000);
		List <WebElement> roma = driver.findElements(By.cssSelector(".cpq-item-base-product"));
		for(WebElement r : roma){
			if(r.getText().contains("DDI sin Roaming Internacional")){
				sleep(5000);
				cc.obligarclick(r.findElement(By.cssSelector(".slds-button.slds-button_neutral")));
				sleep(5000);
				break;
			}
		}
		SalesBase sb = new SalesBase(driver);
		sb.continuar();
		WebElement wMessageBox = driver.findElement(By.id("TextBlock1")).findElement(By.className("ng-binding"));
		sleep(5000);
		Assert.assertTrue(wMessageBox.getText().equalsIgnoreCase("La orden " + sOrder + " se realiz\u00d3 con \u00c9xito!"));
		Assert.assertTrue(cc.corroborarEstadoCaso(sOrder, "Activada"));
		sOrders.add("Baja de servicio, orden numero: " + sOrder + ", DNI: " + sDNI);
	}

	
	
	@Test (groups = {"GestionesPerfilOficina","E2E","Ciclo3","ABMDeServicio"}, dataProvider="BajaServicios")
	public void TS134355_CRM_Movil_PRE_Alta_Servicio_sin_costo_DDI_con_Roaming_Internacional_Presencial(String sDNI, String sLinea){
		imagen = "TS134355";
		detalles = null;
		detalles = imagen+"-BajaServicio-DNI:"+sDNI;
		BasePage cambioFrameByID=new BasePage();
		sleep(30000);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI",sDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		detalles +="-Cuenta:"+accid;
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(18000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(5000);
		cc.irAGestionEnCard("Alta/Baja de Servicios");
		sleep(35000);
		cc.openrightpanel();
		cc.closerightpanel();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-text-body--small.slds-page-header__info.taDevider")));
		String sOrder = driver.findElement(By.cssSelector(".slds-text-body--small.slds-page-header__info.taDevider")).getText();
		sOrder = sOrder.replace("Nro. Orden:", "");
		sOrder = sOrder.replace(" ", "");
		detalles +="-Orden:"+sOrder;
		detalles +="-Servicio:DDIconRoamingInternacional";
		try {
			cc.closeleftpanel();
		}
		catch (Exception x) {
			//Always empty
		}
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.id("tab-default-1")));
		sleep(10000);
		driver.findElement(By.cssSelector(".slds-button.cpq-item-has-children")).click();
		sleep(5000);
		boolean bAssert = false;
		//Not finished
		List<WebElement> servicios= driver.findElements(By.xpath("//*[@class='cpq-item-product-child-level-1 cpq-item-child-product-name-wrapper']"));
		for(WebElement a: servicios) {
			if (a.getText().toLowerCase().contains("servicios basicos general movil".toLowerCase())) {
					a.findElement(By.tagName("button")).click();
						sleep(8000);
						bAssert= true;
						break;
			}
		}
		Assert.assertTrue(bAssert);
		sleep(17000);
		bAssert = false;
		List <WebElement> ddi = driver.findElements(By.cssSelector(".cpq-item-product-child-level-2.cpq-item-child-product-name-wrapper"));
		for(WebElement d : ddi){
			if(d.getText().contains("DDI")){
			   cc.obligarclick(d.findElement(By.cssSelector(".slds-button.slds-button_icon-small")));
			   bAssert = true;
			   break;
			}
		}
		Assert.assertTrue(bAssert);
		sleep(10000);
		List <WebElement> roam = driver.findElements(By.cssSelector(".cpq-item-base-product"));
		for(WebElement r : roam){
			if(r.getText().contains("DDI con Roaming Internacional")){
				cc.obligarclick(r.findElement(By.cssSelector(".slds-button.slds-button_neutral")));
				sleep(5000);
				break;
			}
		}
		driver.findElement(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand")).click();
		sleep(10000);
		WebElement wMessageBox = driver.findElement(By.id("TextBlock1")).findElement(By.className("ng-binding"));
		sleep(5000);
		Assert.assertTrue(wMessageBox.getText().toLowerCase().contains("La orden " + sOrder + " se realiz\u00f3 con \u00e9xito!"));
		Assert.assertTrue(cc.corroborarEstadoCaso(sOrder, "Activated"));
		sOrders.add("Alta de Servicio, orden numero: " + sOrder + ", DNI: " + sDNI);
	}
	
	@Test(groups = {"Sales", "Nominacion","E2E","Ciclo1"}, dataProvider="DatosSalesNominacionNuevoOfCom") 
	public void TS_85094_CRM_Movil_REPRO_Nominatividad_Cliente_Nuevo_Presencial_DOC_OfCom(String sLinea, String sDni, String sNombre, String sApellido, String sSexo, String sFnac, String sEmail, String sProvincia, String sLocalidad, String sCalle, String sNumCa, String sCP) { 
		imagen = "85094-Nominacion"+sDni;
		detalles = null;
		detalles = imagen +"-Linea: "+sLinea;
		sleep(1000);
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		SalesBase SB = new SalesBase(driver);
		driver.findElement(By.id("PhoneNumber")).sendKeys(sLinea);
		sleep(1500);
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep(10000);
		WebElement cli = driver.findElement(By.id("tab-scoped-1")); 	
		cli.findElement(By.tagName("tbody")).findElement(By.tagName("tr")).click();
		sleep(3000);
		List<WebElement> Lineas = driver.findElement(By.id("tab-scoped-1")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		for(WebElement UnaL: Lineas) {
			if(UnaL.getText().toLowerCase().contains("plan con tarjeta repro")||UnaL.getText().toLowerCase().contains("plan prepago nacional")) {
				UnaL.findElements(By.tagName("td")).get(6).findElement(By.tagName("svg")).click();
				System.out.println("Linea Encontrada");
				break;
			}
		}
		sleep(10000);
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact2("DNI", sDni, sSexo);
		contact.Llenar_Contacto(sNombre, sApellido, sFnac);
		try {contact.ingresarMail(sEmail, "si");}catch (org.openqa.selenium.ElementNotVisibleException ex1) {}
		contact.tipoValidacion("documento");
		File directory = new File("Dni.jpg");
		contact.subirArchivo(new File(directory.getAbsolutePath()).toString(), "si");
		BasePage bp = new BasePage(driver);
		sleep(6000);
		bp.setSimpleDropdown(driver.findElement(By.id("ImpositiveCondition")), "IVA Consumidor Final");
		SB.Crear_DomicilioLegal(sProvincia, sLocalidad, sCalle, "", sNumCa, "", "", sCP);
		sleep(32000);
		CBS_Mattu invoSer = new CBS_Mattu();
		invoSer.ValidarInfoCuenta(sLinea, sNombre,sApellido, "Plan con Tarjeta Repro");
		List <WebElement> element = driver.findElement(By.id("NominacionExitosa")).findElements(By.tagName("p"));
		System.out.println("cont="+element.get(0).getText());
		boolean a = false;
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("nominaci\u00f3n exitosa!")) {
				a = true;
				System.out.println(x.getText());
			}
		}
		Assert.assertTrue(a);
		driver.findElement(By.id("FinishProcess_nextBtn")).click();
		sleep(3000);
		invoSer.ValidarInfoCuenta(sLinea, sNombre,sApellido, "Plan con Tarjeta Repro");
	}
	
	@Test (groups = {"Suspension", "GestionesPerfilOficina","E2E","Ciclo3"}, dataProvider="CuentaSuspension") 
	public void gestionSuspension(String cDNI,String cLinea, String cProvincia, String cCiudad, String cPartido) {
		imagen = "gestionSuspension";
		detalles = null;
		detalles = imagen+"- DNI:"+cDNI+" - Linea: "+cLinea;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		cc.irAGestion("suspensiones");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step1-SuspensionOrReconnection_nextBtn")));
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "contains", "suspensi\u00f3n");
		driver.findElement(By.id("Step1-SuspensionOrReconnection_nextBtn")).click();
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "contains", "linea");
		driver.findElement(By.id("Step2-AssetTypeSelection_nextBtn")).click();
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")),"contains","l\u00ednea: "+cLinea);
		driver.findElement(By.id("Step3-AvailableAssetsSelection_nextBtn")).click();
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")),"contains", "robo");
		driver.findElement(By.id("Step4-SuspensionReason_nextBtn")).click();
		sleep(10000);
		selectByText(driver.findElement(By.id("State")),cProvincia);
		sleep(10000);
		driver.findElement(By.id("CityTypeAhead")).sendKeys(cCiudad);
		sleep(10000);
		driver.findElement(By.id("Partido")).sendKeys(cPartido);
		sleep(7000);
		driver.findElement(By.id("AccountData_nextBtn")).click();
		sleep(5000);
		driver.findElement(By.id("Step6-Summary_nextBtn")).click();
		sleep(15000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")),"contains", "continue");
		sleep(15000);
		boolean a = false;
		List <WebElement> elem = driver.findElements(By.cssSelector(".slds-box.ng-scope"));
		for(WebElement x : elem) {
			if(x.getText().toLowerCase().contains("tu solicitud est\u00e1 siendo procesada.")) {
				a = true;
			}			
		}
		Assert.assertTrue(a);
		sleep(5000);
		String orden = cc.obtenerOrden(driver, "Suspensi\u00f3n de Linea");
		sOrders.add("Suspension, orden numero: " + orden + ", DNI: " + cDNI);
		//System.out.println(sOrders);
	}
	
	@Test (groups = {"ProblemaRecarga", "GestionesPerfilOficina","E2E","Ciclo3"}, dataProvider="CuentaProblemaRecarga") 
	public void problemaRecargaOnline(String sDNI, String sLinea) {
		imagen= "problemaRecargaOnline";
		detalles = null;
		detalles = imagen+" - DNI:"+sDNI+" - Linea: "+sLinea;
		CBS_Mattu verifM = new CBS_Mattu();
		CBS verif = new CBS();
		String saldo = verif.ObtenerValorResponse(verifM.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer saldo1 = Integer.parseInt(saldo.substring(0, (saldo.length()) - 1));
		imagen = "problemaRecargaOnline";
		detalles = null;
		detalles = imagen+"-Problema Con Recargas-DNI:"+sDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(8000);
		cc.irAGestionEnCard("Problemas con Recargas");
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.id("RefillMethods_nextBtn")));
		buscarYClick(driver.findElements(By.cssSelector(".imgItemContainer.ng-scope")),"contains", "recarga online");
		driver.findElement(By.id("RefillMethods_nextBtn")).click();
		sleep(8000);
		driver.findElement(By.id("RefillDate")).sendKeys("23-07-2018");
		sleep(8000);
		driver.findElement(By.id("RefillAmount")).sendKeys("123");
		sleep(8000);
		driver.findElement(By.id("ReceiptCode")).sendKeys("123");
		driver.findElement(By.id("OnlineRefillData_nextBtn")).click();
		sleep(8000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")),"contains","si");
		WebElement img = driver.findElement(By.id("FileAttach"));
		File directory = new File("Dni.jpg");
		img.sendKeys(new File(directory.getAbsolutePath()).toString());
		sleep(8000);
		driver.findElement(By.id("AttachDocuments_nextBtn")).click();
		sleep(8000);
		driver.findElement(By.id("Summary_nextBtn")).click();
		sleep(8000);
		boolean a = false;
		boolean b = false;
		List <WebElement> conf = driver.findElements(By.cssSelector(".slds-box.ng-scope"));
		for(WebElement x : conf) {
			if(x.getText().toLowerCase().contains("recarga realizada con \u00e9xito!")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
		String saldo2 = verif.ObtenerValorResponse(verifM.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer saldo3 = Integer.parseInt(saldo2.substring(0, (saldo2.length()) - 1));
		if(saldo1+123000 == saldo3) {
			b = true;
		}
		Assert.assertTrue(b);
		sleep(5000);
		String orden = cc.obtenerOrden(driver, "Problemas con Recargas");
		detalles+=" - Orden: "+orden;
		sOrders.add("Recargas, orden numero: " + orden + " con DNI: " + sDNI );
		System.out.println(sOrders);
	}
	
	@Test (groups = {"ProblemaRecarga", "GestionesPerfilOficina","E2E","Ciclo3"}, dataProvider="CuentaProblemaRecarga") //Error al intentar impactar la recarga
	public void poblemaRecargaCredito(String cDNI, String sLinea) {
		imagen = "problemaRecargaCredito";
		detalles = null;
		detalles = imagen+" - DNI: "+cDNI+" - Linea: "+sLinea;
		CBS_Mattu verifM = new CBS_Mattu();
		CBS verif = new CBS();
		String saldo = verif.ObtenerValorResponse(verifM.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer saldo1 = Integer.parseInt(saldo.substring(0, (saldo.length()) - 1));
		System.out.println(saldo1);
		imagen = "poblemaRecargaCredito";
		detalles = null;
		detalles = imagen+"-Problema Con Recargas-DNI:"+cDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(8000);
		cc.irAGestionEnCard("Problemas con Recargas");
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.id("RefillMethods_nextBtn")));
		buscarYClick(driver.findElements(By.cssSelector(".imgItemContainer.ng-scope")),"contains", "tarjeta de cr\u00e9dito");
		driver.findElement(By.id("RefillMethods_nextBtn")).click();
		sleep(8000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")),"contains", "si");
		driver.findElement(By.id("CreditCardRefillAmount")).sendKeys("123");
		driver.findElement(By.id("CreditCardRefillReceipt")).sendKeys("123");
		driver.findElement(By.id("CreditCardData_nextBtn")).click();
		sleep(8000);
		driver.findElement(By.id("Summary_nextBtn")).click();
		sleep(8000);
		boolean a = false;
		boolean b = false;
		List <WebElement> conf = driver.findElements(By.cssSelector(".slds-box.ng-scope"));
		for(WebElement x : conf) {
			if(x.getText().toLowerCase().contains("recarga realizada con \u00e9xito!")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
		String saldo2 = verif.ObtenerValorResponse(verifM.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer saldo3 = Integer.parseInt(saldo2.substring(0, (saldo2.length()) - 1));
		System.out.println(saldo3);
		if((saldo1+1230000 == saldo3)) {
			b = true;
		}
		Assert.assertTrue(b);
		sleep(5000);
		String orden = cc.obtenerOrden(driver, "Problemas con Recargas");
		detalles+=" - Orden: "+orden;
		sOrders.add("Recargas, orden numero: " + orden + " con DNI: " + cDNI );
		System.out.println(sOrders);
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Ajustes","E2E","Ciclo3"}, dataProvider = "CuentaAjustesREPRO")
	public void TS103596_CRM_Movil_REPRO_Ajuste_General_FAN_Front_OOCC(String sDNI, String sLinea) {
		imagen = "TS103596";
		detalles = null;
		detalles = imagen + " -Ajustes-DNI: " + sDNI;
		String datoViejo = cbs.ObtenerUnidadLibre(cbsm.Servicio_QueryFreeUnit(sLinea), "Datos Libres");
		Integer datosInicial = Integer.parseInt(datoViejo);
		boolean gest = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		cc.irAGestion("inconvenientes");
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step-TipodeAjuste_nextBtn")));
		selectByText(driver.findElement(By.id("CboConcepto")), "CREDITO PREPAGO");
		selectByText(driver.findElement(By.id("CboItem")), "Consumos de datos");
		selectByText(driver.findElement(By.id("CboMotivo")), "Error/omisi\u00f3n/demora gesti\u00f3n");
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("Step-TipodeAjuste_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "contains", "plan con tarjeta");
		driver.findElement(By.id("Step-AssetSelection_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si, ajustar");
		driver.findElement(By.id("Step-VerifyPreviousAdjustments_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("Desde")).sendKeys("01-07-2018");
		driver.findElement(By.id("Hasta")).sendKeys("30-07-2018");
		selectByText(driver.findElement(By.id("Unidad")), "Datos (Mb)");
		driver.findElement(By.id("CantidadDatosms")).sendKeys("123");
		driver.findElement(By.id("Step-AjusteNivelLinea_nextBtn")).click();
		sleep(7000);
		driver.findElement(By.id("Step-Summary_nextBtn")).click();
		sleep(7000);
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("tu gesti\u00f3n se realiz\u00f3 con \u00e9xito"))
				gest = true;
		}
		String datoNuevo = cbs.ObtenerUnidadLibre(cbsm.Servicio_QueryFreeUnit(sLinea), "Datos Libres");
		Integer datosFinal = Integer.parseInt(datoNuevo);
		Assert.assertTrue(datosInicial + (123 * 1024) == datosFinal);
		Assert.assertTrue(gest);
		if (TestBase.urlAmbiente.contains("sit")) {
			String orden = cc.obtenerOrden(driver, "Inconvenientes con cargos tasados y facturados");
			detalles+=" - Orden: "+orden;
			sOrders.add("Inconvenientes con cargos tasados y facturados, orden numero: " + orden + " con numero de DNI: " + sDNI);
			Assert.assertTrue(cc.verificarOrden(orden));
		} else {
			String orden = driver.findElement(By.xpath("//*[@id=\"txtSuccessConfirmation\"]/div")).findElement(By.tagName("strong")).getText();
			detalles+=" - Orden: "+orden;
			sOrders.add("Inconvenientes con cargos tasados y facturados, numero de orden: " + orden + " de cuenta con DNI: " + sDNI);
			Assert.assertTrue(cc.verificarOrdenYGestion("Inconvenientes con cargos tasados y facturados"));
		}
	}
	
	@Test (groups = {"GestionesPerfilOficina", "ModificacionDeDatos","E2E","Ciclo3"}) //No se puede modificar el DNI 2 veces en un mes
	public void GestionActualizacionDatos() {
		imagen = "GestionActualizacionDatos";
		OM om = new OM(driver);
		//boolean gest = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		driver.findElement(By.cssSelector(".slds-form-element__label--toggleText.ng-binding")).click();
		sleep(3000);
		driver.findElement(By.id("ContactFirstName")).sendKeys("nestor alberto");
		driver.findElement(By.id("ContactLastName")).sendKeys("papa");
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep(5000);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("profile-box")));
		driver.findElements(By.className("profile-edit")).get(0).click();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("DocumentNumber")));
		String nroDNI = driver.findElement(By.id("DocumentNumber")).getAttribute("value");
		String old2 = nroDNI.substring(0, nroDNI.length()-2);
		driver.findElement(By.id("DocumentNumber")).clear();
		String ultimos2 = om.getRandomNumber(2);
		driver.findElement(By.id("DocumentNumber")).sendKeys(old2 + ultimos2);
		String asd = driver.findElement(By.id("DocumentNumber")).getAttribute("value");
		System.out.println(asd);
		/*driver.findElement(By.id("ClientInformation_nextBtn")).click();
		sleep(10000);
		List <WebElement> element = driver.findElements(By.className("ta-care-omniscript-done"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("se realizaron correctamente las modificaciones")) {
				gest = true;
			}
		}
		Assert.assertTrue(gest);*/		
	}
	
	@Test (groups = {"GestionesPerfilOficina", "ProblemasConRecargas","E2E","Ciclo3"}, dataProvider = "ProblemaRecargaPrepaga")  //Se necesitan nuevos numeros de tarjeta, solo se pueden usar 1 vez
	public void GestionProblemasConRecargasTarjetaPrepaga(String cDNI,String sLinea, String cBatch, String cPin) {
		CBS_Mattu verifM = new CBS_Mattu();
		CBS verif = new CBS();
		String saldo = verif.ObtenerValorResponse(verifM.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer saldo1 = Integer.parseInt(saldo.substring(0, 5));
		System.out.println(saldo1);
		imagen = "GestionProblemasConRecargasTarjetaPrepaga";
		detalles = null;
		detalles = imagen+"-Problema Con Recargas-DNI:"+cDNI;
		boolean gest = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(20000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(5000);
		cc.irAGestionEnCard("Problemas con Recargas");
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("RefillMethods_nextBtn")));
		buscarYClick(driver.findElements(By.className("borderOverlay")), "equals", "tarjeta prepaga");
		driver.findElement(By.id("RefillMethods_nextBtn")).click();
		sleep(5000);
		driver.findElement(By.id("BatchNumber")).sendKeys(cBatch);
		driver.findElement(By.id("PIN")).sendKeys(cPin);
		driver.findElement(By.id("PrepaidCardData_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.className("borderOverlay")), "equals", "crear un caso nuevo");
		driver.findElement(By.id("ExistingCase_nextBtn")).click();
		sleep(7000);
		driver.findElement(By.id("Summary_nextBtn")).click();
		sleep(10000);
		boolean b = false;
		List <WebElement> element = driver.findElements(By.className("ta-care-omniscript-done"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("recarga realizada con \u00e9xito")) {
				gest = true;
			}
		}
		Assert.assertTrue(gest);
		String saldo2 = verif.ObtenerValorResponse(verifM.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer saldo3 = Integer.parseInt(saldo2.substring(0, (saldo2.length()) - 1));
		System.out.println(saldo3);
		if((saldo1+1230000 == saldo3)) {
			b = true;
		}
		Assert.assertTrue(b);
		String orden = cc.obtenerOrden(driver, "Problema con recarga con tarjeta prepaga");
		detalles+=" - Orden: "+orden;
		Assert.assertTrue(cc.verificarOrden(orden));
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Ajustes","E2E","Ciclo3"}, dataProvider = "CuentaAjustesPRE")
	public void Gestion_Ajustes_Credito_Pospago(String sDNI, String sLinea) {
		imagen = "Gestion_Ajustes_Credito_Pospago";
		detalles = null;
		detalles = imagen + " -Ajustes-DNI: " + sDNI;
		boolean gest = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		cc.irAGestion("inconvenientes");
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step-TipodeAjuste_nextBtn")));
		selectByText(driver.findElement(By.id("CboConcepto")), "CREDITO POSPAGO");
		selectByText(driver.findElement(By.id("CboItem")), "Minutos/SMS");
		selectByText(driver.findElement(By.id("CboMotivo")), "Error/omisi\u00f3n/demora gesti\u00f3n");
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("Step-TipodeAjuste_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "contains", "cuenta: ");
		driver.findElement(By.id("Step1-SelectBillingAccount_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si, ajustar");
		driver.findElement(By.id("Step-VerifyPreviousAdjustments_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "nota de d\u00e9bito");
		driver.findElement(By.id("MontoLibre")).sendKeys("123");
		selectByText(driver.findElement(By.id("SelectItemAjusteLibre")), "Ajuste Minutos");
		driver.findElement(By.id("Step-AjusteNivelCuenta_nextBtn")).click();
		sleep(7000);
		driver.findElement(By.id("Step-Summary_nextBtn")).click();
		sleep(7000);
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("tu gesti\u00f3n se realiz\u00f3 con \u00e9xito")) {
				gest = true;
			}
		}
		Assert.assertTrue(gest);
		if (TestBase.urlAmbiente.contains("sit")) {
			String orden = cc.obtenerOrden(driver, "Inconvenientes con cargos tasados y facturados");
			detalles+=" - Orden: "+orden;
			sOrders.add("Inconvenientes con cargos tasados y facturados, orden numero: " + orden + " con numero de DNI: " + sDNI);
			Assert.assertTrue(cc.verificarOrden(orden));		
		} else {
			String orden = driver.findElement(By.xpath("//*[@id=\"txtSuccessConfirmation\"]/div")).findElement(By.tagName("strong")).getText();
			detalles+=" - Orden: "+orden;
			sOrders.add("Inconvenientes con cargos tasados y facturados, numero de orden: " + orden + " de cuenta con DNI: " + sDNI);
			Assert.assertTrue(cc.verificarOrdenYGestion("Inconvenientes con cargos tasados y facturados"));
		}
	}
	
	@Test (groups = {"Suspension", "GestionesPerfilOficina","E2E","Ciclo3"}, dataProvider="CuentaSuspension")
	public void TS98438_CRM_Movil_REPRO_Suspension_por_Siniestro_Hurto_Linea_Titular_Presencial(String cDNI, String cLinea, String cProvincia, String cCiudad, String cPartido) {
		imagen = "TS98438";
		detalles = null;
		detalles = imagen + " - Suspension - DNI: " +cDNI+" - Linea: "+cLinea;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		cc.irAGestion("suspensiones");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step1-SuspensionOrReconnection_nextBtn")));
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "contains", "suspensi\u00f3n");
		driver.findElement(By.id("Step1-SuspensionOrReconnection_nextBtn")).click();
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "contains", "linea");
		driver.findElement(By.id("Step2-AssetTypeSelection_nextBtn")).click();
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")),"contains","l\u00ednea: "+cLinea);
		driver.findElement(By.id("Step3-AvailableAssetsSelection_nextBtn")).click();
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")),"contains", "hurto");
		driver.findElement(By.id("Step4-SuspensionReason_nextBtn")).click();
		sleep(10000);
		selectByText(driver.findElement(By.id("State")),cProvincia);
		sleep(10000);
		driver.findElement(By.id("CityTypeAhead")).sendKeys(cCiudad);
		sleep(10000);
		driver.findElement(By.id("Partido")).sendKeys(cPartido);
		sleep(10000);
		driver.findElement(By.id("AccountData_nextBtn")).click();
		sleep(10000);
		driver.findElement(By.id("Step6-Summary_nextBtn")).click();
		sleep(15000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")),"contains", "continue");
		sleep(40000);
		boolean a = false;
		List <WebElement> elem = driver.findElements(By.cssSelector(".slds-box.ng-scope"));
		for(WebElement x : elem) {
			if(x.getText().toLowerCase().contains("tu solicitud est\u00e1 siendo procesada.")) {
				a = true;
			}			
		}
		Assert.assertTrue(a);
		sleep(5000);
		String orden = cc.obtenerOrden(driver, "Suspensi\u00f3n de Linea");
		detalles+= " - Orden: "+orden;
		sOrders.add("Suspension, orden numero: " + orden + " con numero de DNI: " + cDNI);
		System.out.println(sOrders);
	}
	
	@Test (groups = {"Suspension", "GestionesPerfilOficina","E2E","Ciclo3"}, dataProvider="CuentaSuspension")
	public void TS98442_CRM_Movil_REPRO_Suspension_por_Siniestro_Extravio_Linea_Titular_Presencial(String cDNI, String cLinea, String cProvincia, String cCiudad, String cPartido) {
		imagen = "TS98442";
		detalles = null;
		detalles = imagen + " -Suspension - DNI: " + cDNI +" - Linea: "+cLinea;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		cc.irAGestion("suspensiones");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step1-SuspensionOrReconnection_nextBtn")));
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "contains", "suspensi\u00f3n");
		driver.findElement(By.id("Step1-SuspensionOrReconnection_nextBtn")).click();
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "contains", "linea");
		driver.findElement(By.id("Step2-AssetTypeSelection_nextBtn")).click();
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")),"contains","l\u00ednea: "+cLinea);
		driver.findElement(By.id("Step3-AvailableAssetsSelection_nextBtn")).click();
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")),"contains", "extrav\u00edo");
		driver.findElement(By.id("Step4-SuspensionReason_nextBtn")).click();
		sleep(10000);
		selectByText(driver.findElement(By.id("State")),cProvincia);
		sleep(10000);
		driver.findElement(By.id("CityTypeAhead")).sendKeys(cCiudad);
		sleep(10000);
		driver.findElement(By.id("Partido")).sendKeys(cPartido);
		sleep(7000);
		driver.findElement(By.id("AccountData_nextBtn")).click();
		sleep(5000);
		driver.findElement(By.id("Step6-Summary_nextBtn")).click();
		sleep(15000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")),"contains", "continue");
		sleep(15000);
		boolean a = false;
		List <WebElement> elem = driver.findElements(By.cssSelector(".slds-box.ng-scope"));
		for(WebElement x : elem) {
			if(x.getText().toLowerCase().contains("tu solicitud est\u00e1 siendo procesada.")) {
				a = true;
			}			
		}
		Assert.assertTrue(a);
		sleep(5000);
		String orden = cc.obtenerOrden(driver, "Suspensi\u00f3n de Linea");
		sOrders.add("Suspencion, orden numero: " + orden + " con numero de DNI: " + cDNI);
		System.out.println(sOrders);
		
	}	
	
	@Test (groups = {"Suspension", "GestionesPerfilOficina","E2E","Ciclo3"}, dataProvider="CuentaSuspension")
	public void TS98477_CRM_Movil_REPRO_Suspension_por_Fraude_Linea_Comercial_Desconocimiento_Administrativo(String cDNI, String cLinea, String cProvincia, String cCiudad, String cPartido) {
		imagen = "TS98477";
		detalles = null;
		detalles = imagen + " -Suspension - DNI: " + cDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		cc.irAGestion("suspensiones y reconexion back");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step1SelectSuspensionOrReconnection_nextBtn")));
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "contains", "suspensi\u00f3n");
		driver.findElement(By.id("Step1SelectSuspensionOrReconnection_nextBtn")).click();
		sleep(8000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "contains", "linea");
		driver.findElement(By.id("Step2-SelectAssetOrDocument_nextBtn")).click();
		sleep(8000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")),"contains", "nombre: plan con tarjeta repro");
		driver.findElement(By.id("Step3_nextBtn")).click();
		sleep(8000);
		selectByText(driver.findElement(By.id("SelectFraud")),"Comercial");
		selectByText(driver.findElement(By.id("SelectSubFraud")),"Desconocimiento");
		driver.findElement(By.id("Step4_nextBtn")).click();
		sleep(15000);
		driver.findElement(By.id("StepSummary_nextBtn")).click();
		sleep(15000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")),"contains", "continue");
		sleep(40000);
		boolean b = false;
		List <WebElement> prov = driver.findElements(By.cssSelector(".slds-box.ng-scope"));
		for(WebElement x : prov) {
			if(x.getText().toLowerCase().contains("tu gesti\u00f3n se realiz\u00f3 con \u00e9xito")) {
				b = true;
			}			
		}
		Assert.assertTrue(b);
		sleep(10000);
		CBS_Mattu cCBSM = new CBS_Mattu();
		Assert.assertTrue(cCBSM.obtenerStatusLinea(cLinea).equals("suspension fraude"));
		String orden = cc.obtenerOrden(driver, "Suspension administrativa");
		detalles += " - Orden: "+orden;
		sOrders.add("Suspencion, orden numero: " + orden + " con numero de DNI: " + cDNI);
		System.out.println(sOrders);
	}	
	
	@Test (groups = {"Suspension", "GestionesPerfilOficina","E2E","Ciclo3"}, dataProvider="CuentaSuspension")
	public void TS98487_CRM_Movil_REPRO_Suspension_por_Fraude_DNI_CUIT_Comercial_Irregular_Administrativo(String cDNI, String cLinea, String cProvincia, String cCiudad, String cPartido) {
		imagen = "TS98487";
		detalles = null;
		detalles = imagen + " - Suspension - DNI: " + cDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(5000);
		cc.irAGestion("suspensiones y reconexion back");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step1SelectSuspensionOrReconnection_nextBtn")));
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "contains", "suspensi\u00f3n");
		driver.findElement(By.id("Step1SelectSuspensionOrReconnection_nextBtn")).click();
		sleep(8000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "contains", "dni");
		driver.findElement(By.id("Step2-SelectAssetOrDocument_nextBtn")).click();
		sleep(8000);
		driver.findElement(By.id("Step3_nextBtn")).click();
		selectByText(driver.findElement(By.id("SelectFraud")),"Comercial");
		selectByText(driver.findElement(By.id("SelectSubFraud")),"Irregular");
		driver.findElement(By.id("Step4_nextBtn")).click();
		sleep(8000);
		driver.findElement(By.id("StepSummary_nextBtn")).click();
		sleep(8000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")),"contains", "continue");
		sleep(40000);
		boolean b = false;
		List <WebElement> prov = driver.findElements(By.cssSelector(".slds-box.ng-scope"));
		for(WebElement x : prov) {
			if(x.getText().toLowerCase().contains("tu gesti\u00f3n se realiz\u00f3 con \u00e9xito")) {
				b = true;
			}			
		}
		Assert.assertTrue(b);
		sleep(10000);
		CBS_Mattu cCBSM = new CBS_Mattu();
		Assert.assertTrue(cCBSM.obtenerStatusLinea(cLinea).equals("suspension fraude"));
		String orden = cc.obtenerOrden(driver, "Suspension administrativa");
		detalles+= " - Orden: "+orden;
		sOrders.add("Suspencion, orden numero: " + orden + " con numero de DNI: " + cDNI);
		System.out.println(sOrders);
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Ajustes","E2E","Ciclo3"}, dataProvider = "CuentaAjustesPRE")
	public void TS112434_CRM_Movil_PRE_Ajuste_Credito_Minutos_FAN_Front_OOCC(String sDNI, String sLinea) {
		imagen = "TS112434";
		detalles = null;
		detalles = imagen + " -Ajustes - DNI: " + sDNI;
		String datoViejo = cbs.ObtenerUnidadLibre(cbsm.Servicio_QueryFreeUnit(sLinea), "Segundos Libres");
		Integer datosInicial = Integer.parseInt(datoViejo);
		System.out.println(datosInicial);
		boolean gest = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		cc.irAGestion("inconvenientes");
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step-TipodeAjuste_nextBtn")));
		selectByText(driver.findElement(By.id("CboConcepto")), "CREDITO PREPAGO");
		selectByText(driver.findElement(By.id("CboItem")), "Consumos de datos");
		selectByText(driver.findElement(By.id("CboMotivo")), "Error/omisi\u00f3n/demora gesti\u00f3n");
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("Step-TipodeAjuste_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "contains", "plan con tarjeta");
		driver.findElement(By.id("Step-AssetSelection_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si, ajustar");
		driver.findElement(By.id("Step-VerifyPreviousAdjustments_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("Desde")).sendKeys("01-07-2018");
		driver.findElement(By.id("Hasta")).sendKeys("30-07-2018");
		selectByText(driver.findElement(By.id("Unidad")), "Voz");
		driver.findElement(By.id("CantidadVoz")).sendKeys("100000");
		driver.findElement(By.id("Step-AjusteNivelLinea_nextBtn")).click();
		sleep(7000);
		driver.findElement(By.id("Step-Summary_nextBtn")).click();
		sleep(10000);
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("tu gesti\u00f3n se realiz\u00f3 con \u00e9xito"))
				gest = true;
		}
		String datoNuevo = cbs.ObtenerUnidadLibre(cbsm.Servicio_QueryFreeUnit(sLinea), "Segundos Libres");
		Integer datosFinal = Integer.parseInt(datoNuevo);
		System.out.println(datosFinal);
		Assert.assertTrue(datosInicial + (10 * 3600) == datosFinal);
		System.out.println(datosFinal);
		Assert.assertTrue(gest);
		if (TestBase.urlAmbiente.contains("sit")) {
			String orden = cc.obtenerOrden(driver, "Inconvenientes con cargos tasados y facturados");
			detalles+=" - Orden: "+orden;
			sOrders.add("Inconvenientes con cargos tasados y facturados, orden numero: " + orden + " con numero de DNI: " + sDNI);
			Assert.assertTrue(cc.verificarOrden(orden));		
		} else {
			String orden = driver.findElement(By.xpath("//*[@id=\"txtSuccessConfirmation\"]/div")).findElement(By.tagName("strong")).getText();
			detalles+=" - Orden: "+orden;
			sOrders.add("Inconvenientes con cargos tasados y facturados, numero de orden: " + orden + " de cuenta con DNI: " + sDNI);
			Assert.assertTrue(cc.verificarOrdenYGestion("Inconvenientes con cargos tasados y facturados"));
		}
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Ajustes","E2E", "Ciclo3"}, dataProvider = "CuentaAjustesPRE")
	public void TS112435_CRM_Movil_PRE_Ajuste_Credito_SMS_FAN_Front_OOCC(String sDNI, String sLinea) {
		imagen = "TS112435";
		detalles = null;
		detalles = imagen + " -Ajustes-DNI: " + sDNI;
		String datoViejo = cbs.ObtenerUnidadLibre(cbsm.Servicio_QueryFreeUnit(sLinea), "SMS Libres");
		Integer datosInicial = Integer.parseInt(datoViejo);
		boolean gest = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		cc.irAGestion("inconvenientes");
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step-TipodeAjuste_nextBtn")));
		selectByText(driver.findElement(By.id("CboConcepto")), "CREDITO PREPAGO");
		selectByText(driver.findElement(By.id("CboItem")), "Consumos de datos");
		selectByText(driver.findElement(By.id("CboMotivo")), "Error/omisi\u00f3n/demora gesti\u00f3n");
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("Step-TipodeAjuste_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "contains", "plan con tarjeta");
		driver.findElement(By.id("Step-AssetSelection_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si, ajustar");
		driver.findElement(By.id("Step-VerifyPreviousAdjustments_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("Desde")).sendKeys("01-07-2018");
		driver.findElement(By.id("Hasta")).sendKeys("30-07-2018");
		selectByText(driver.findElement(By.id("Unidad")), "SMS");
		driver.findElement(By.id("CantidadDatosms")).sendKeys("123");
		driver.findElement(By.id("Step-AjusteNivelLinea_nextBtn")).click();
		sleep(7000);
		driver.findElement(By.id("Step-Summary_nextBtn")).click();
		sleep(10000);
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("tu gesti\u00f3n se realiz\u00f3 con \u00e9xito"))
				gest = true;
		}
		String datoNuevo = cbs.ObtenerUnidadLibre(cbsm.Servicio_QueryFreeUnit(sLinea), "SMS Libres");
		Integer datosFinal = Integer.parseInt(datoNuevo);
		Assert.assertTrue(datosInicial + 123 == datosFinal);
		Assert.assertTrue(gest);
		if (TestBase.urlAmbiente.contains("sit")) {
			String orden = cc.obtenerOrden(driver, "Inconvenientes con cargos tasados y facturados");
			detalles+=" - Orden: "+orden;
			sOrders.add("Inconvenientes con cargos tasados y facturados, orden numero: " + orden + " con numero de DNI: " + sDNI);
			Assert.assertTrue(cc.verificarOrden(orden));		
		} else {
			String orden = driver.findElement(By.xpath("//*[@id=\"txtSuccessConfirmation\"]/div")).findElement(By.tagName("strong")).getText();
			detalles+=" - Orden: "+orden;
			sOrders.add("Inconvenientes con cargos tasados y facturados, numero de orden: " + orden + " de cuenta con DNI: " + sDNI);
			Assert.assertTrue(cc.verificarOrdenYGestion("Inconvenientes con cargos tasados y facturados"));
		}
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Ajustes","E2E", "Ciclo3"}, dataProvider = "CuentaAjustesREPRO")
	public void TS103599_CRM_Movil_REPRO_Se_crea_caso_de_ajuste_menor_a_500_pesos_FAN_Front_OOCC(String sDNI, String sLinea) {
		imagen = "TS103599";
		detalles = null;
		detalles = imagen + " -Ajustes-DNI: " + sDNI;
		String datoViejo = cbs.ObtenerValorResponse(cbsm.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer datosInicial = Integer.parseInt(datoViejo.substring(0, 5));
		System.out.println(datosInicial);
		boolean gest = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(20000);
		cc.irAGestion("inconvenientes");
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step-TipodeAjuste_nextBtn")));
		selectByText(driver.findElement(By.id("CboConcepto")), "CREDITO PREPAGO");
		selectByText(driver.findElement(By.id("CboItem")), "Consumos de datos");
		selectByText(driver.findElement(By.id("CboMotivo")), "Error/omisi\u00f3n/demora gesti\u00f3n");
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("Step-TipodeAjuste_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "contains", "plan con tarjeta");
		driver.findElement(By.id("Step-AssetSelection_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si, ajustar");
		driver.findElement(By.id("Step-VerifyPreviousAdjustments_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("Desde")).sendKeys("01-07-2018");
		driver.findElement(By.id("Hasta")).sendKeys("30-07-2018");
		selectByText(driver.findElement(By.id("Unidad")), "Credito");
		driver.findElement(By.id("CantidadMonto")).sendKeys("49999");
		driver.findElement(By.id("Step-AjusteNivelLinea_nextBtn")).click();
		sleep(7000);
		driver.findElement(By.id("Step-Summary_nextBtn")).click();
		sleep(10000);
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("tu gesti\u00f3n se realiz\u00f3 con \u00e9xito"))
				gest = true;
		}
		Assert.assertTrue(gest);
		String datoNuevo = cbs.ObtenerValorResponse(cbsm.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer datosFinal = Integer.parseInt(datoNuevo.substring(0, 5));
		System.out.println(datosFinal);
		Assert.assertTrue(datosInicial + 4999 == datosFinal);
		if (TestBase.urlAmbiente.contains("sit")) {
			String orden = cc.obtenerOrden(driver, "Inconvenientes con cargos tasados y facturados");
			detalles+=" - Orden: "+orden;
			sOrders.add("Inconvenientes con cargos tasados y facturados, orden numero: " + orden + " con numero de DNI: " + sDNI);
			Assert.assertTrue(cc.verificarOrden(orden));		
		} else {
			String orden = driver.findElement(By.xpath("//*[@id=\"txtSuccessConfirmation\"]/div")).findElement(By.tagName("strong")).getText();
			detalles+=" - Orden: "+orden;
			sOrders.add("Inconvenientes con cargos tasados y facturados, numero de orden: " + orden + " de cuenta con DNI: " + sDNI);
			Assert.assertTrue(cc.verificarOrdenYGestion("Inconvenientes con cargos tasados y facturados"));
		}
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Ajustes","E2E", "Ciclo3"}, dataProvider = "CuentaAjustesPRE")
	public void TS112452_CRM_Movil_PRE_Ajuste_Nota_de_Credito_Derivacion_a_rango_superior_1900_FAN_Front_OOCC(String sDNI, String sLinea) {
		imagen = "TS112452";
		detalles = null;
		detalles = imagen + " -Ajustes-DNI: " + sDNI;
		boolean gest = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		cc.irAGestion("inconvenientes");
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step-TipodeAjuste_nextBtn")));
		selectByText(driver.findElement(By.id("CboConcepto")), "CREDITO PREPAGO");
		selectByText(driver.findElement(By.id("CboItem")), "Consumos de datos");
		selectByText(driver.findElement(By.id("CboMotivo")), "Error/omisi\u00f3n/demora gesti\u00f3n");
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("Step-TipodeAjuste_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "contains", "plan con tarjeta repro");
		driver.findElement(By.id("Step-AssetSelection_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si, ajustar");
		driver.findElement(By.id("Step-VerifyPreviousAdjustments_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("Desde")).sendKeys("01-07-2018");
		driver.findElement(By.id("Hasta")).sendKeys("30-07-2018");
		selectByText(driver.findElement(By.id("Unidad")), "Credito");
		driver.findElement(By.id("CantidadMonto")).sendKeys("200000");
		driver.findElement(By.id("Step-AjusteNivelLinea_nextBtn")).click();
		sleep(7000);
		driver.findElement(By.id("Step-Summary_nextBtn")).click();
		sleep(10000);
		List <WebElement> element = driver.findElements(By.className("ta-care-omniscript-done"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("el caso fue derivado para autorizaci\u00f3n"))
				gest = true;
		}
		Assert.assertTrue(gest);
		if (TestBase.urlAmbiente.contains("sit")) {
			String orden = cc.obtenerOrden(driver, "Inconvenientes con cargos tasados y facturados");
			detalles+=" - Orden: "+orden;
			sOrders.add("Inconvenientes con cargos tasados y facturados, orden numero: " + orden + " con numero de DNI: " + sDNI);
			Assert.assertTrue(cc.verificarOrden(orden));		
		} else {
			String orden = driver.findElement(By.xpath("//*[@id=\"txtSuccessConfirmation\"]/div")).findElement(By.tagName("strong")).getText();
			detalles+=" - Orden: "+orden;
			Assert.assertTrue(cc.verificarOrdenYGestion("Inconvenientes con cargos tasados y facturados"));
		}
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Ajustes","E2E", "Ciclo3"}, dataProvider = "CuentaAjustesPRE")
	public void TS135706_CRM_Movil_PRE_Ajuste_Nota_de_Credito_FAN_Front_OOCC_Punta_Alta(String sDNI, String sLinea) {
		imagen = "TS135706";
		detalles = null;
		detalles = imagen + " -Ajustes-DNI: " + sDNI;
		boolean gest = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		cc.irAGestion("inconvenientes");
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step-TipodeAjuste_nextBtn")));
		selectByText(driver.findElement(By.id("CboConcepto")), "CREDITO POSPAGO");
		selectByText(driver.findElement(By.id("CboItem")), "Minutos/SMS");
		selectByText(driver.findElement(By.id("CboMotivo")), "Error/omisi\u00f3n/demora gesti\u00f3n");
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("Step-TipodeAjuste_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "contains", "cuenta:");
		driver.findElement(By.id("Step1-SelectBillingAccount_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si, ajustar");
		driver.findElement(By.id("Step-VerifyPreviousAdjustments_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "nota de cr\u00e9dito");
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElements(By.className("slds-cell-shrink")).get(0).click();
		driver.findElement(By.id("Step-AjusteNivelCuenta_nextBtn")).click();
		sleep(7000);
		driver.findElement(By.id("Step-Summary_nextBtn")).click();
		sleep(7000);
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("tu gesti\u00f3n se realiz\u00f3 con \u00e9xito"))
				gest = true;
		}
		Assert.assertTrue(gest);
		if (TestBase.urlAmbiente.contains("sit")) {
			String orden = cc.obtenerOrden(driver, "Inconvenientes con cargos tasados y facturados");
			detalles+=" - Orden: "+orden;
			sOrders.add("Inconvenientes con cargos tasados y facturados, orden numero: " + orden + " con numero de DNI: " + sDNI);
			Assert.assertTrue(cc.verificarOrden(orden));		
		} else {
			String orden = driver.findElement(By.xpath("//*[@id=\"txtSuccessConfirmation\"]/div")).findElement(By.tagName("strong")).getText();
			detalles+=" - Orden: "+orden;
			Assert.assertTrue(cc.verificarOrdenYGestion("Inconvenientes con cargos tasados y facturados"));
		}
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Ajustes","E2E","Ciclo3"}, dataProvider = "CuentaAjustesPRE")
	public void TS135707_CRM_Movil_PRE_Ajuste_Nota_de_Debito_FAN_Front_OOCC_Bariloche(String sDNI, String sLinea) {
		imagen = "TS135707";
		detalles = null;
		detalles = imagen + " -Ajustes-DNI: " + sDNI;
		boolean gest = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		cc.irAGestion("inconvenientes");
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step-TipodeAjuste_nextBtn")));
		selectByText(driver.findElement(By.id("CboConcepto")), "CREDITO POSPAGO");
		selectByText(driver.findElement(By.id("CboItem")), "Minutos/SMS");
		selectByText(driver.findElement(By.id("CboMotivo")), "Error/omisi\u00f3n/demora gesti\u00f3n");
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("Step-TipodeAjuste_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "contains", "cuenta:");
		driver.findElement(By.id("Step1-SelectBillingAccount_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si, ajustar");
		driver.findElement(By.id("Step-VerifyPreviousAdjustments_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "nota de d\u00e9bito");
		sleep(2000);
		driver.findElement(By.id("MontoLibre")).sendKeys("123");
		selectByText(driver.findElement(By.id("SelectItemAjusteLibre")), "Abono b\u00e1sico movil");
		driver.findElement(By.id("Step-AjusteNivelCuenta_nextBtn")).click();
		sleep(7000);
		driver.findElement(By.id("Step-Summary_nextBtn")).click();
		sleep(10000);
		String nroCaso = driver.findElement(By.xpath("//*[@id=\"txtSuccessConfirmation\"]/div")).findElement(By.tagName("strong")).getText();
		detalles+=" - Caso: "+nroCaso;
		System.out.println(nroCaso);
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("tu gesti\u00f3n se realiz\u00f3 con \u00e9xito"))
				gest = true;
		}
		Assert.assertTrue(gest);
		if (TestBase.urlAmbiente.contains("sit")) {
			String orden = cc.obtenerOrden(driver, "Inconvenientes con cargos tasados y facturados");
			detalles+=" - Orden: "+orden;
			sOrders.add("Inconvenientes con cargos tasados y facturados, orden numero: " + orden + " con numero de DNI: " + sDNI);
			Assert.assertTrue(cc.verificarOrden(orden));		
		} else {
			String orden = driver.findElement(By.xpath("//*[@id=\"txtSuccessConfirmation\"]/div")).findElement(By.tagName("strong")).getText();
			detalles+=" - Orden: "+orden;
			Assert.assertTrue(cc.verificarOrdenYGestion("Inconvenientes con cargos tasados y facturados"));
		}
	}
	
	@Test (groups = {"Suspension", "GestionesPerfilOficina","E2E","Ciclo3"}, dataProvider="CuentaSuspension")
	public void TS98498_CRM_Movil_REPRO_Suspension_por_Fraude_Cuenta_de_facturacion_Comercial_Desconocimiento_Administrativo(String cDNI, String cLinea, String cProvincia, String cCiudad, String cPartido) {
		imagen = "TS98498";
		detalles = null;
		detalles = imagen + " -Suspension - DNI: " + cDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		cc.irAGestion("suspensiones y reconexion back");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step1SelectSuspensionOrReconnection_nextBtn")));
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "contains", "suspensi\u00f3n");
		driver.findElement(By.id("Step1SelectSuspensionOrReconnection_nextBtn")).click();
		sleep(8000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "contains", "cuenta de facturacion");
		driver.findElement(By.id("Step2-SelectAssetOrDocument_nextBtn")).click();
		sleep(8000);
		driver.findElement(By.xpath("//*[@id=\"BillingAccs0\"]/div/div/ul/li/label/span[1]")).click();
		driver.findElement(By.id("Step3_nextBtn")).click();
		sleep(8000);
		selectByText(driver.findElement(By.id("SelectFraud")), "Comercial");
		selectByText(driver.findElement(By.id("SelectSubFraud")), "Desconocimiento");
		driver.findElement(By.id("Step4_nextBtn")).click();
		sleep(8000);
		driver.findElement(By.id("StepSummary_nextBtn")).click();
		sleep(8000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")), "contains", "continue");
		sleep(15000);
		boolean b = false;
		List <WebElement> prov = driver.findElements(By.cssSelector(".slds-box.ng-scope"));
		for(WebElement x : prov) {
			if(x.getText().toLowerCase().contains("tu gesti\u00f3n se realiz\u00f3 con \u00e9xito")) {
				b = true;
			}			
		}
		Assert.assertTrue(b);
		sleep(8000);
		CBS_Mattu cCBSM = new CBS_Mattu();
		Assert.assertTrue(cCBSM.obtenerStatusLinea(cLinea).equals("suspension fraude"));
		String orden = cc.obtenerOrden(driver, "Suspension administrativa");
		detalles+=" - orden: "+orden;
		System.out.println(sOrders);	
	}
	
	@Test (groups = {"Habilitacion", "GestionesPerfilOficina","E2E", "Ciclo3"}, dataProvider="CuentaHabilitacion")
	public void TS98599_CRM_Movil_REPRO_Rehabilitacion_Administrativo_Fraude_DNI(String cDNI) {
		imagen = "TS98599";
		detalles = null;
		detalles = imagen + " - Rehabilitacion - DNI: " + cDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		cc.irAGestion("suspensiones y reconexion back");
		sleep(40000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step1SelectSuspensionOrReconnection_nextBtn")));
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "contains", "habilitaci\u00f3n");
		driver.findElement(By.id("Step1SelectSuspensionOrReconnection_nextBtn")).click();
		sleep(8000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "contains", "linea");
		driver.findElement(By.id("Step2-SelectAssetOrDocument_nextBtn")).click();
		sleep(8000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "contains", "nombre: ");
		driver.findElement(By.id("Step3_nextBtn")).click();
		sleep(8000);
		driver.findElement(By.id("TxtComment")).sendKeys("Fraude");
		driver.findElement(By.id("Step4_nextBtn")).click();
		sleep(8000);
		driver.findElement(By.id("StepSummary_nextBtn")).click();
		sleep(8000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")), "contains", "continue");
		sleep(15000);
		boolean b = false;
		List <WebElement> prov = driver.findElements(By.cssSelector(".slds-box.ng-scope"));
		for(WebElement x : prov) {
			if(x.getText().toLowerCase().contains("tu gesti\u00f3n se realiz\u00f3 con \u00e9xito")) {
				b = true;
			}			
		}
		Assert.assertTrue(b);
		sleep(8000);
		String orden = cc.obtenerOrden(driver, "Habilitaci\u00f3n administrativa");
		detalles+=" - Orden: "+orden;
		System.out.println(sOrders);
	}
	
	@Test (groups = {"Habilitacion", "GestionesPerfilOficina","E2E","Ciclo3"}, dataProvider="CuentaHabilitacion")
	public void TS98590_CRM_Movil_REPRO_Rehabilitacion_por_Siniestro_Presencial_Robo_Linea(String cDNI) {
		imagen = "TS98590";
		detalles = null;
		detalles = imagen + " - Rehabilitacion - DNI: " + cDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(8000);
		cc.irAGestion("suspensiones");
		sleep(20000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step1-SuspensionOrReconnection_nextBtn")));
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")),"contains", "habilitaci\u00f3n");
		driver.findElement(By.id("Step1-SuspensionOrReconnection_nextBtn")).click();
		sleep(8000);
		buscarYClick(driver.findElements(By.cssSelector(".ta-radioBtnContainer.taBorderOverlay.slds-grid.slds-grid--align-center.slds-grid--vertical-align-center")), "contains", "validaci\u00f3n por documento de identidad");
		driver.findElement(By.id("MethodSelection_nextBtn")).click();
		sleep(8000);
		File directory = new File("Dni.jpg");
		driver.findElement(By.id("FileDocumentImage")).sendKeys(new File(directory.getAbsolutePath()).toString());
		driver.findElement(By.id("DocumentMethod_nextBtn")).click();
		sleep(8000);
		driver.findElement(By.id("ValidationResult_nextBtn")).click();
		sleep(8000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")),"contains","linea");
		driver.findElement(By.id("Step2-AssetTypeSelection_nextBtn")).click();
		sleep(8000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")),"contains", "l\u00ednea: ");
		driver.findElement(By.id("Step3-AvailableAssetsSelection_nextBtn")).click();
		sleep(8000);
		driver.findElement(By.id("Step6-Summary_nextBtn")).click();
		sleep(15000);
		boolean b = false;
		List <WebElement> prov = driver.findElements(By.cssSelector(".slds-box.ng-scope"));
		for(WebElement x : prov) {
			if(x.getText().toLowerCase().contains("tu solicitud est\u00e1 siendo procesada.")) {
				b = true;
			}			
		}
		Assert.assertTrue(b);
		sleep(8000);
		String orden = cc.obtenerOrden(driver, "Reconexi\u00f3n de Linea");
		detalles+=" - Orden: "+orden;
		System.out.println(sOrders);
	}
	
	//@Test (groups = {"GestionesPerfilOficina", "Ajustes", "E2E"}, dataProvider = "CuentaAjustesPRE")  //Diferido
	public void TS112438_CRM_Movil_PRE_Ajuste_Cargos_aun_no_facturados_FAN_Front_OOCC(String cDNI) {
		imagen = "TS112438";
		detalles = null;
		detalles = imagen + " -Ajustes-DNI: " + cDNI;
		boolean gest = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		cc.irAGestion("inconvenientes");
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step-TipodeAjuste_nextBtn")));
		selectByText(driver.findElement(By.id("CboConcepto")), "CARGOS AUN NO FACTURADOS");
		selectByText(driver.findElement(By.id("CboTipo")), "Otros cargos no facturados");
		selectByText(driver.findElement(By.id("CboItem")), "Cargo de reconexi\u00f3n");
		selectByText(driver.findElement(By.id("CboMotivo")), "Error/omisi\u00f3n/demora gesti\u00f3n");
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("Step-TipodeAjuste_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si, ajustar");
		driver.findElement(By.id("Step-VerifyPreviousAdjustments_nextBtn")).click();
		sleep(7000);
		Assert.assertTrue(false);
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Ajustes", "E2E", "Ciclo3"}, dataProvider = "CuentaAjustesPRE")
	public void TS135708_CRM_Movil_REPRO_Ajuste_Credito_Minutos_FAN_Front_OOCC(String sDNI, String sLinea) {
		imagen = "TS135708";
		detalles = null;
		detalles = imagen + " -Ajustes - DNI: " + sDNI;
		String datoViejo = cbs.ObtenerUnidadLibre(cbsm.Servicio_QueryFreeUnit(sLinea), "Segundos Libres");
		Integer datosInicial = Integer.parseInt(datoViejo);
		System.out.println(datosInicial);
		boolean gest = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		cc.irAGestion("inconvenientes");
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step-TipodeAjuste_nextBtn")));
		selectByText(driver.findElement(By.id("CboConcepto")), "CREDITO PREPAGO");
		selectByText(driver.findElement(By.id("CboItem")), "Consumos de datos");
		selectByText(driver.findElement(By.id("CboMotivo")), "Error/omisi\u00f3n/demora gesti\u00f3n");
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("Step-TipodeAjuste_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "contains", "plan con tarjeta");
		driver.findElement(By.id("Step-AssetSelection_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si, ajustar");
		driver.findElement(By.id("Step-VerifyPreviousAdjustments_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("Desde")).sendKeys("01-07-2018");
		driver.findElement(By.id("Hasta")).sendKeys("30-07-2018");
		selectByText(driver.findElement(By.id("Unidad")), "Voz");
		driver.findElement(By.id("CantidadVoz")).sendKeys("100000");
		driver.findElement(By.id("Step-AjusteNivelLinea_nextBtn")).click();
		sleep(10000);
		driver.findElement(By.id("Step-Summary_nextBtn")).click();
		sleep(10000);
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("tu gesti\u00f3n se realiz\u00f3 con \u00e9xito"))
				gest = true;
		}
		String datoNuevo = cbs.ObtenerUnidadLibre(cbsm.Servicio_QueryFreeUnit(sLinea), "Segundos Libres");
		Integer datosFinal = Integer.parseInt(datoNuevo);
		System.out.println(datosFinal);
		Assert.assertTrue(datosInicial + (10 * 3600) == datosFinal);
		Assert.assertTrue(gest);
		if (TestBase.urlAmbiente.contains("sit")) {
			String orden = cc.obtenerOrden(driver, "Inconvenientes con cargos tasados y facturados");
			detalles+=" - Orden: "+orden;
			sOrders.add("Inconvenientes con cargos tasados y facturados, orden numero: " + orden + " con numero de DNI: " + sDNI);
			Assert.assertTrue(cc.verificarOrden(orden));		
		} else {
			String orden = driver.findElement(By.xpath("//*[@id=\"txtSuccessConfirmation\"]/div")).findElement(By.tagName("strong")).getText();
			detalles+=" - Orden: "+orden;
			sOrders.add("Inconvenientes con cargos tasados y facturados, numero de orden: " + orden + " de cuenta con DNI: " + sDNI);
			Assert.assertTrue(cc.verificarOrdenYGestion("Inconvenientes con cargos tasados y facturados"));
		}
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Ajustes", "E2E","Ciclo3"}, dataProvider = "CuentaAjustesREPRO")
	public void TS129317_CRM_Movil_REPRO_Ajuste_RAV_Unidades_Libres_a_Pesos_General_FAN_Front_OOCC(String sDNI, String sLinea) {
		imagen = "TS129317";
		detalles = null;
		detalles = imagen + " -Ajustes-DNI: " + sDNI;
		WebElement monto = null;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		cc.irAGestion("inconvenientes");
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step-TipodeAjuste_nextBtn")));
		selectByText(driver.findElement(By.id("CboConcepto")), "CREDITO PREPAGO");
		selectByText(driver.findElement(By.id("CboItem")), "Consumos de datos");
		selectByText(driver.findElement(By.id("CboMotivo")), "Error/omisi\u00f3n/demora gesti\u00f3n");
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("Step-TipodeAjuste_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "contains", "plan con tarjeta");
		driver.findElement(By.id("Step-AssetSelection_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si, ajustar");
		driver.findElement(By.id("Step-VerifyPreviousAdjustments_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("Desde")).sendKeys("01-07-2018");
		driver.findElement(By.id("Hasta")).sendKeys("30-07-2018");
		selectByText(driver.findElement(By.id("Unidad")), "Voz");
		driver.findElement(By.id("CantidadVoz")).sendKeys("100000");
		driver.findElement(By.id("Step-AjusteNivelLinea_nextBtn")).click();
		sleep(7000);
		WebElement fact = driver.findElement(By.cssSelector(".slds-card.ng-scope")).findElements(By.cssSelector(".slds-card__header.slds-grid")).get(1);
		List <WebElement> list = fact.findElements(By.tagName("li"));
		for (WebElement x : list)
			if (x.getText().toLowerCase().contains("monto"))
				monto = x;
		Assert.assertTrue(monto.getText().equalsIgnoreCase("Monto: 78.00"));		
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Ajustes", "E2E", "Ciclo3"},dataProvider = "CuentaAjustesPRE")
	public void TS135705_CRM_Movil_PRE_Ajuste_RAV_Unidades_Libres_a_Pesos_General_FAN_Front_OOCC(String sDNI, String sLinea) {
		imagen = "TS135705";
		detalles = null;
		detalles = imagen + " - Ajustes - DNI: " + sDNI;
		WebElement monto = null;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		cc.irAGestion("inconvenientes");
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step-TipodeAjuste_nextBtn")));
		selectByText(driver.findElement(By.id("CboConcepto")), "CREDITO PREPAGO");
		selectByText(driver.findElement(By.id("CboItem")), "Consumos de datos");
		selectByText(driver.findElement(By.id("CboMotivo")), "Error/omisi\u00f3n/demora gesti\u00f3n");
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("Step-TipodeAjuste_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "contains", "plan con tarjeta");
		driver.findElement(By.id("Step-AssetSelection_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si, ajustar");
		driver.findElement(By.id("Step-VerifyPreviousAdjustments_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("Desde")).sendKeys("01-07-2018");
		driver.findElement(By.id("Hasta")).sendKeys("30-07-2018");
		selectByText(driver.findElement(By.id("Unidad")), "Voz");
		driver.findElement(By.id("CantidadVoz")).sendKeys("100000");
		driver.findElement(By.id("Step-AjusteNivelLinea_nextBtn")).click();
		sleep(7000);
		WebElement fact = driver.findElement(By.cssSelector(".slds-card.ng-scope")).findElements(By.cssSelector(".slds-card__header.slds-grid")).get(1);
		List <WebElement> list = fact.findElements(By.tagName("li"));
		for (WebElement x : list)
			if (x.getText().toLowerCase().contains("monto")) {
				monto = x;
		}
		Assert.assertTrue(monto.getText().equalsIgnoreCase("Monto: 78.00"));
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Ajustes", "E2E","Ciclo3"},dataProvider = "CuentaAjustesREPRO")
	public void TS129320_CRM_Movil_REPRO_Escalamiento_segun_RAV_FAN_Front_OOCC(String sDNI, String sLinea) {
		imagen = "TS129320";
		detalles = null;
		detalles = imagen + " -Ajustes-DNI: " + sDNI;
		boolean gest = false;
		WebElement monto = null;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		cc.irAGestion("inconvenientes");
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step-TipodeAjuste_nextBtn")));
		selectByText(driver.findElement(By.id("CboConcepto")), "CREDITO PREPAGO");
		selectByText(driver.findElement(By.id("CboItem")), "Consumos de datos");
		selectByText(driver.findElement(By.id("CboMotivo")), "Error/omisi\u00f3n/demora gesti\u00f3n");
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("Step-TipodeAjuste_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "contains", "plan con tarjeta");
		driver.findElement(By.id("Step-AssetSelection_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si, ajustar");
		driver.findElement(By.id("Step-VerifyPreviousAdjustments_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("Desde")).sendKeys("01-07-2018");
		driver.findElement(By.id("Hasta")).sendKeys("30-07-2018");
		selectByText(driver.findElement(By.id("Unidad")), "Credito");
		driver.findElement(By.id("CantidadMonto")).sendKeys("50100");
		driver.findElement(By.id("Step-AjusteNivelLinea_nextBtn")).click();
		sleep(7000);
		WebElement fact = driver.findElement(By.cssSelector(".slds-card.ng-scope")).findElements(By.cssSelector(".slds-card__header.slds-grid")).get(1);
		List <WebElement> list = fact.findElements(By.tagName("li"));
		for (WebElement x : list) {
			if (x.getText().toLowerCase().contains("monto"))
				monto = x;
		}
		Assert.assertTrue(monto.getText().equalsIgnoreCase("Monto: 501"));
		driver.findElement(By.id("Step-Summary_nextBtn")).click();
		sleep(10000);
		List <WebElement> element = driver.findElements(By.className("ta-care-omniscript-done"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("el caso fue derivado para autorizaci\u00f3n"))
				gest = true;
		}
		Assert.assertTrue(gest);
		if (TestBase.urlAmbiente.contains("sit")) {
			String orden = cc.obtenerOrden(driver, "Inconvenientes con cargos tasados y facturados");
			detalles+=" - Orden: "+orden;
			sOrders.add("Inconvenientes con cargos tasados y facturados, orden numero: " + orden + " con numero de DNI: " + sDNI);
			Assert.assertTrue(cc.verificarOrden(orden));		
		} else {
			String orden = driver.findElement(By.xpath("//*[@id=\"txtSuccessConfirmation\"]/div")).findElement(By.tagName("strong")).getText();
			detalles+=" - Orden: "+orden;
			sOrders.add("Inconvenientes con cargos tasados y facturados, numero de orden: " + orden + " de cuenta con DNI: " + sDNI);
			Assert.assertTrue(cc.verificarOrdenYGestion("Inconvenientes con cargos tasados y facturados"));
		}
	}
	
	@Test (groups = {"GestionesPerfilOficina","RenovacionDeCuota","E2E","Ciclo1"}, dataProvider="RenovacionCuotaSinSaldo")
	public void TS135396_CRM_Movil_REPRO_Renovacion_de_cuota_Presencial_Internet_50_MB_Dia_Efectivo_sin_Credito(String sDNI, String sLinea) throws AWTException {
		imagen = "TS135396";
		//Check all
		detalles = null;
		detalles = "Renocavion de cuota: "+imagen+"- DNI: "+sDNI+" - Linea: "+sLinea;
		CBS cCBS = new CBS();
		CBS_Mattu cCBSM = new CBS_Mattu();
		String datosInicial = cCBS.ObtenerUnidadLibre(cCBSM.Servicio_QueryFreeUnit(sLinea), "Datos Libres");
		BasePage cambioFrameByID=new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI", sDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		detalles +="-Cuenta:"+accid;
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(25000);
		CustomerCare cCC = new CustomerCare(driver);
		cCC.seleccionarCardPornumeroLinea(sLinea, driver);
		sleep(3000);
		
		cCC.irAGestionEnCard("Renovacion de Datos");
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("combosMegas")));
		List<WebElement> elementos = driver.findElement(By.cssSelector(".table.slds-table.slds-table--bordered.slds-table--cell-buffer")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		for(WebElement UnE:elementos) {
			if(UnE.findElement(By.tagName("td")).getText().contains("50 MB")) {
				UnE.findElement(By.className("slds-checkbox")).click();
			}
		}
		cCC.obligarclick(driver.findElement(By.id("CombosDeMegas_nextBtn")));
		sleep(10000);
		List<WebElement> pago = driver.findElement(By.id("PaymentTypeRadio|0")).findElements(By.cssSelector(".slds-radio.ng-scope"));
		for (WebElement UnP : pago) {
			if (UnP.getText().toLowerCase().contains("factura")){
				UnP.click();
				break;
			}
		}
		cCC.obligarclick(driver.findElement(By.id("SetPaymentType_nextBtn")));
		sleep(12000);
		cCC.obligarclick(driver.findElement(By.id("InvoicePreview_nextBtn")));
		sleep(15000);
		String sOrden = cCC.obtenerOrden2(driver);
		detalles += "-orden:"+sOrden;
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals", "efectivo");
		sleep(12000);
		driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")).click();
		sleep(30000);
		String msj = driver.findElement(By.cssSelector(".message.description.ng-binding.ng-scope")).getText(); 
		String check = driver.findElement(By.id("GeneralMessageDesing")).getText();
		Assert.assertTrue(msj.toLowerCase().contains("se ha enviado correctamente la factura a huawei. dirigirse a caja para realizar el pago de la misma"));
		Assert.assertTrue(check.toLowerCase().contains("la orden se realiz\u00f3 con \u00e9xito"));
		driver.findElement(By.id("SaleOrderMessages_nextBtn")).click();
		sleep(15000);
		String orden = cc.obtenerTNyMonto2(driver, sOrden);
		System.out.println("orden = "+orden);
		detalles+=", Monto:"+orden.split("-")[2]+"Prefactura: "+orden.split("-")[1];
		CBS_Mattu invoSer = new CBS_Mattu();
		Assert.assertTrue(invoSer.PagoEnCaja("1006", accid, "1001", orden.split("-")[2], orden.split("-")[1],driver));
		sleep(5000);
		driver.navigate().refresh();
		sleep(10000);
		String datosFinal = cCBS.ObtenerUnidadLibre(cCBSM.Servicio_QueryFreeUnit(sLinea), "Datos Libres");
		Assert.assertTrue((Integer.parseInt(datosInicial)+51200)==Integer.parseInt(datosFinal));
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".hasMotif.orderTab.detailPage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		WebElement tabla = driver.findElement(By.id("ep")).findElements(By.tagName("table")).get(1);
		String datos = tabla.findElements(By.tagName("tr")).get(4).findElements(By.tagName("td")).get(1).getText();
		Assert.assertTrue(datos.equalsIgnoreCase("activada")||datos.equalsIgnoreCase("activated"));
	//Arreglar luego porque no debe ser asi
		
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Reintegros", "E2E","Ciclo4"}, dataProvider = "CuentaReintegros")
	public void TS112598_CRM_Movil_PRE_Pago_con_Tarjeta_de_debito_Reintegro_con_Efectivo_1000(String cDNI) {
		imagen = "TS112598";
		detalles = null;
		detalles = imagen + " -Reintegros - DNI: " + cDNI;
		Marketing mk = new Marketing(driver);
		boolean gest = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		mk.closeActiveTab();
		cc.irAFacturacion();
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		buscarYClick(driver.findElements(By.className("slds-text-body_regular")), "contains", "solicitud de reintegros");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("stepRefundData_nextBtn")));
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "contains", "tarjeta de d\u00e9bito");
		selectByText(driver.findElement(By.id("selectReason")), "Pago duplicado");
		driver.findElement(By.id("inputCurrencyAmount")).sendKeys("100000");
		driver.findElement(By.id("stepRefundData_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals", "efectivo");
		driver.findElement(By.id("stepRefundMethod_nextBtn")).click();
		sleep(7000);
		driver.findElement(By.id("Summary_nextBtn")).click();
		sleep(10000);
		List <WebElement> msj = driver.findElements(By.className("ta-care-omniscript-done"));
		for (WebElement x : msj) {
			if (x.getText().toLowerCase().contains("la gesti\u00f3n se deriv\u00f3 correctamente")) {
				gest = true;
			}
		}
		Assert.assertTrue(gest);
		if (TestBase.urlAmbiente.contains("sit")) {
			String orden = cc.obtenerOrden(driver, "Solicitud de Reintegros");
			detalles+=" - Orden: "+orden;
			sOrders.add("Solicitud de Reintegros, orden numero: " + orden + " con numero de DNI: " + cDNI);
			Assert.assertTrue(cc.verificarOrden(orden));		
		} else {
			String orden = driver.findElement(By.cssSelector(".vlc-slds-inline-control__label.ng-binding")).getText();
			detalles+=" - Orden: "+orden;
			orden = orden.substring(orden.lastIndexOf(" ")+1, orden.lastIndexOf("."));
			sOrders.add("Solicitud de Reintegros, numero de orden: " + orden + " de cuenta con DNI: " + cDNI);
			Assert.assertTrue(cc.verificarOrdenYGestion("Solicitud de Reintegros"));
		}
	}
	
	@Test (groups = {"ProblemaRecarga", "GestionesPerfilOficina","E2E","Ciclo3"}, dataProvider="CuentaProblemaRecargaAYD") //Lote: 11120000001688 PIN: 02222
	public void TS135714_CRM_Movil_PRE_Problemas_con_Recarga_Telefonico_Tarjeta_Scratch_Caso_Nuevo_Tarjeta_Activa_y_Disponible(String cDNI,String sLinea, String cSerie, String cPIN){
		CBS_Mattu verifM = new CBS_Mattu();
		CBS verif = new CBS();
		String saldo = verif.ObtenerValorResponse(verifM.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer saldo1 = Integer.parseInt(saldo.substring(0, 5));
		System.out.println(saldo1);
		imagen = "TS135714";
		detalles = null;
		detalles = imagen + " -Problema con recargas - DNI: " + cDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(8000);
		cc.irAGestionEnCard("Problemas con Recargas");
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.id("RefillMethods_nextBtn")));
		buscarYClick(driver.findElements(By.cssSelector(".imgItemContainer.ng-scope")), "contains", "tarjeta prepaga");
		driver.findElement(By.id("RefillMethods_nextBtn")).click();
		sleep(8000);
		driver.findElement(By.id("BatchNumber")).sendKeys(cSerie);
		driver.findElement(By.id("PIN")).sendKeys(cPIN);
		driver.findElement(By.id("PrepaidCardData_nextBtn")).click();
		sleep(15000);
		buscarYClick(driver.findElements(By.cssSelector(".imgItemContainer.ng-scope")), "contains", "crear un caso nuevo");
		driver.findElement(By.id("ExistingCase_nextBtn")).click();
		sleep(10000);
		driver.findElement(By.id("Summary_nextBtn")).click();
		sleep(10000);
		boolean b = false;
		boolean a = false;
		List <WebElement> prob = driver.findElements(By.cssSelector(".slds-box.ng-scope"));
		for(WebElement x : prob) {
			if(x.getText().toLowerCase().contains("recarga realizada con \u00e9xito")) {
				b = true;
			}
		}
		Assert.assertTrue(b);
		String saldo2 = verif.ObtenerValorResponse(verifM.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer saldo3 = Integer.parseInt(saldo2.substring(0, (saldo2.length()) - 1));
		if(saldo1+123000 == saldo3) {
			a = true;
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = {"ProblemaRecarga", "GestionesPerfilOficina","E2E","Ciclo3"}, dataProvider="CuentaProblemaRecargaQuemada")//Lote: 11120000001689 PIN: 02776
	public void TS104347_CRM_Movil_REPRO_Problemas_con_Recarga_Presencial_Tarjeta_Scratch_Caso_Nuevo_Quemada(String cDNI,String sLinea, String cSerie, String cPIN){
		CBS_Mattu verifM = new CBS_Mattu();
		CBS verif = new CBS();
		String saldo = verif.ObtenerValorResponse(verifM.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer saldo1 = Integer.parseInt(saldo.substring(0, 5));
		System.out.println(saldo1);
		imagen = "TS104347";
		detalles = null;
		detalles = imagen + " -Problema con recargas - DNI: " + cDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(8000);
		cc.irAGestionEnCard("Problemas con Recargas");
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.id("RefillMethods_nextBtn")));
		buscarYClick(driver.findElements(By.cssSelector(".imgItemContainer.ng-scope")), "contains", "tarjeta prepaga");
		driver.findElement(By.id("RefillMethods_nextBtn")).click();
		sleep(8000);
		driver.findElement(By.id("BatchNumber")).sendKeys(cSerie);
		driver.findElement(By.id("PIN")).sendKeys(cPIN);
		driver.findElement(By.id("PrepaidCardData_nextBtn")).click();
		sleep(15000);
		buscarYClick(driver.findElements(By.cssSelector(".imgItemContainer.ng-scope")), "contains", "crear un caso nuevo");
		driver.findElement(By.id("ExistingCase_nextBtn")).click();
		sleep(8000);
		driver.findElement(By.id("Summary_nextBtn")).click();
		sleep(8000);
		boolean b = false;
		boolean a  = false;
		List <WebElement> prob = driver.findElements(By.cssSelector(".slds-box.ng-scope"));
		for(WebElement x : prob) {
			if(x.getText().toLowerCase().contains("no se pudo realizar la operaci\u00f3n.")) {
				b = true;
			}
		}
		Assert.assertTrue(b);
		String saldo2 = verif.ObtenerValorResponse(verifM.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer saldo3 = Integer.parseInt(saldo2.substring(0, (saldo2.length()) - 1));
		if(saldo1+123000 == saldo3) {
			a = true;
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Reintegros", "E2E","Ciclo4"}, dataProvider = "CuentaReintegros")
	public void TS112597_CRM_Movil_PRE_Pago_con_Tarjeta_de_debito_Reintegro_con_Efectivo_Menos_de_1000(String cDNI) {
		imagen = "TS112597";
		detalles = null;
		detalles = imagen + " -Reintegros - DNI: " + cDNI;
		Marketing mk = new Marketing(driver);
		boolean gest = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		mk.closeActiveTab();
		cc.irAFacturacion();
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		buscarYClick(driver.findElements(By.className("slds-text-body_regular")), "contains", "solicitud de reintegros");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("stepRefundData_nextBtn")));
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "contains", "tarjeta de d\u00e9bito");
		selectByText(driver.findElement(By.id("selectReason")), "Pago duplicado");
		driver.findElement(By.id("inputCurrencyAmount")).sendKeys("90000");
		driver.findElement(By.id("stepRefundData_nextBtn")).click();
		sleep(7000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals", "efectivo");
		driver.findElement(By.id("stepRefundMethod_nextBtn")).click();
		sleep(7000);
		driver.findElement(By.id("Summary_nextBtn")).click();
		sleep(10000);
		List <WebElement> msj = driver.findElements(By.className("ta-care-omniscript-done"));
		for (WebElement x : msj) {
			if (x.getText().toLowerCase().contains("la gesti\u00f3n se deriv\u00f3 correctamente")) {
				gest = true;
			}
		}
		Assert.assertTrue(gest);
		if (TestBase.urlAmbiente.contains("sit")) {
			String orden = cc.obtenerOrden(driver, "Solicitud de Reintegros");
			detalles+=" - Orden: "+orden;
			sOrders.add("Solicitud de Reintegros, orden numero: " + orden + " con numero de DNI: " + cDNI);
			Assert.assertTrue(cc.verificarOrden(orden));		
		} else {
			String orden = driver.findElement(By.cssSelector(".vlc-slds-inline-control__label.ng-binding")).getText();
			detalles+=" - Orden: "+orden;
			orden = orden.substring(orden.lastIndexOf(" ")+1, orden.lastIndexOf("."));
			sOrders.add("Solicitud de Reintegros, numero de orden: " + orden + " de cuenta con DNI: " + cDNI);
			Assert.assertTrue(cc.verificarOrdenYGestion("Solicitud de Reintegros"));
		}
	}
	
	@Test (groups = {"GestionesPerfilOficina", "TriviasYSuscripciones", "E2E","Ciclo3"}, dataProvider = "CuentaTriviasYSuscripciones")
	public void TS119032_CRM_Movil_REPRO_Suscripciones_Baja_de_suscripciones_sin_BlackList_Presencial(String cDNI) {
		imagen = "TS119032";
		detalles = null;
		detalles = imagen +" -Trivias y suscripciones - DNI: " +cDNI;
		boolean gest = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(3000);
		cc.irAGestionEnCard("Suscripciones");
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-button.slds-button--brand")));
		driver.findElement(By.cssSelector(".addedValueServices-row.ng-pristine.ng-untouched.ng-valid.ng-empty")).findElement(By.className("slds-checkbox")).click();
		driver.findElements(By.cssSelector(".slds-button.slds-button--brand")).get(1).click();
		sleep(7000);
		driver.switchTo().frame(cambioFrame(driver, By.id("UnsubscriptionOptions_nextBtn")));
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "nunca me suscrib\u00ed");
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "no");
		driver.findElement(By.id("UnsubscriptionOptions_nextBtn")).click();
		sleep(10000);
		try {
			driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")));
			driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")).get(1).click();
		} catch (Exception e) {}
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("UnsubscriptionSummary_nextBtn")));
		driver.findElement(By.id("UnsubscriptionSummary_nextBtn")).click();
		sleep(10000);
		List <WebElement> msj = driver.findElements(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope"));
		for (WebElement x : msj) {
			if (x.getText().toLowerCase().contains("tu caso se resolvi\u00f3 con \u00e9xito")) {
				gest = true;
			}
		}
		Assert.assertTrue(gest);
	}
	
	@Test (groups = {"GestionesPerfilOficina", "TriviasYSuscripciones", "E2E","Ciclo3"}, dataProvider = "CuentaTriviasYSuscripciones")
	public void TS110893_CRM_Movil_REPRO_Suscripciones_Baja_de_una_suscripcion_con_BlackList_con_ajuste_Presencial(String cDNI) {
		imagen="TS110893";
		detalles = null;
		detalles = imagen + "- Trivias y suscripciones - DNI:" + cDNI;
		boolean gest = false;
		WebElement blackList = null;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(3000);
		cc.irAGestionEnCard("Suscripciones");
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-button.slds-button--brand")));
		driver.findElement(By.cssSelector(".addedValueServices-row.ng-pristine.ng-untouched.ng-valid.ng-empty")).findElement(By.className("slds-checkbox")).click();
		driver.findElements(By.cssSelector(".slds-button.slds-button--brand")).get(1).click();
		sleep(7000);
		driver.switchTo().frame(cambioFrame(driver, By.id("UnsubscriptionOptions_nextBtn")));
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "nunca me suscrib\u00ed");
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "s\u00ed");
		List <WebElement> bl = driver.findElements(By.className("slds-form-element__control"));
		for (WebElement x : bl) {
			if (x.getText().toLowerCase().contains("quer\u00e9s agregar al cliente a la blacklist")) {
				blackList = x;
			}
		}
		buscarYClick(blackList.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "s\u00ed");
		driver.findElement(By.id("UnsubscriptionOptions_nextBtn")).click();
		sleep(10000);
		try {
			driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")));
			driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")).get(1).click();
		} catch (Exception e) {}
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("UnsubscriptionSummary_nextBtn")));
		driver.findElement(By.id("UnsubscriptionSummary_nextBtn")).click();
		sleep(10000);
		List <WebElement> msj = driver.findElements(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope"));
		for (WebElement x : msj) {
			if (x.getText().toLowerCase().contains("tu caso se resolvi\u00f3 con \u00e9xito")) {
				gest = true;
			}
		}
		Assert.assertTrue(gest);
	}
	
	@Test (groups = {"GestionesPerfilOficina", "TriviasYSuscripciones", "E2E","Ciclo3"}, dataProvider = "CuentaTriviasYSuscripciones")
	public void TS110877_CRM_Movil_REPRO_Suscripciones_Baja_de_una_suscripcion_sin_BlackList_con_ajuste_Presencial(String cDNI) {
		imagen = "TS110877";
		detalles = null;
		detalles = imagen + "- Trivias y suscripciones - DNI: "+cDNI;
		boolean gest = false;
		WebElement blackList = null;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(3000);
		cc.irAGestionEnCard("Suscripciones");
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-button.slds-button--brand")));
		driver.findElement(By.cssSelector(".addedValueServices-row.ng-pristine.ng-untouched.ng-valid.ng-empty")).findElement(By.className("slds-checkbox")).click();
		driver.findElements(By.cssSelector(".slds-button.slds-button--brand")).get(1).click();
		sleep(7000);
		driver.switchTo().frame(cambioFrame(driver, By.id("UnsubscriptionOptions_nextBtn")));
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "nunca me suscrib\u00ed");
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "s\u00ed");
		List <WebElement> bl = driver.findElements(By.className("slds-form-element__control"));
		for (WebElement x : bl) {
			if (x.getText().toLowerCase().contains("quer\u00e9s agregar al cliente a la blacklist")) {
				blackList = x;
			}
		}
		buscarYClick(blackList.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "no");
		driver.findElement(By.id("UnsubscriptionOptions_nextBtn")).click();
		sleep(10000);
		try {
			driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")));
			driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")).get(1).click();
		} catch (Exception e) {}
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("UnsubscriptionSummary_nextBtn")));
		driver.switchTo().frame(cambioFrame(driver, By.id("UnsubscriptionSummary_nextBtn")));
		driver.findElement(By.id("UnsubscriptionSummary_nextBtn")).click();
		sleep(10000);
		List <WebElement> msj = driver.findElements(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope"));
		for (WebElement x : msj) {
			if (x.getText().toLowerCase().contains("tu caso se resolvi\u00f3 con \u00e9xito")) {
				gest = true;
			}
		}
		Assert.assertTrue(gest);
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Recargas","E2E"}, dataProvider = "RecargaTD")
	public void TS134320_CRM_Movil_REPRO_Recargas_Presencial_TD_Ofcom(String sDNI, String sMonto, String sLinea, String sBanco, String sTarjeta, String sNumTarjeta, String sVenceMes, String sVenceAno, String sCodSeg, String sTipoDNI, String sDNITarjeta, String sTitular) throws AWTException {
		if(sMonto.length() >= 4) {
			sMonto = sMonto.substring(0, sMonto.length()-1);
		}
		imagen= "TS134320";
		detalles = null;
		detalles = imagen+"-Recarga-DNI:"+sDNI;
		CBS cCBS = new CBS();
		CBS_Mattu cCBSM = new CBS_Mattu();
		String sMainBalance = cCBS.ObtenerValorResponse(cCBSM.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer iMainBalance = Integer.parseInt(sMainBalance.substring(0, (sMainBalance.length()) - 1));
		
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		String sAccid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+ sAccid);
		detalles +="-Cuenta:"+sAccid;
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(25000);
		cc.seleccionarCardPornumeroLinea(sLinea, driver);
		sleep(5000);
		cc.irAGestionEnCard("Recarga de cr\u00e9dito");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("RefillAmount")));
		driver.findElement(By.id("RefillAmount")).sendKeys(sMonto);
		sleep(15000);
		driver.findElement(By.id("AmountSelectionStep_nextBtn")).click();
		sleep(15000);
		CustomerCare cCC = new CustomerCare(driver);
		String sOrden = cCC.obtenerOrden2(driver);
		detalles +="-Orden:"+sOrden;
		driver.findElement(By.xpath("//*[@id=\"InvoicePreview_nextBtn\"]")).click();
		sleep(15000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals", "tarjeta de debito");
		sleep(20000);
		driver.switchTo().frame(cambioFrame(driver, By.id("BankingEntity-0")));
		selectByText(driver.findElement(By.id("BankingEntity-0")), sBanco);
		sleep(5000);
		selectByText(driver.findElement(By.id("CardBankingEntity-0")), sTarjeta);
		sleep(5000);
		driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")).click();
		sleep(20000);
		buscarYClick(driver.findElements(By.id("InvoicePreview_nextBtn")), "equals", "siguiente");
		sleep(20000);
		List <WebElement> wExis = driver.findElements(By.id("GeneralMessageDesing"));
		boolean bAssert = false;
		for(WebElement wAux : wExis) {
			if(wAux.getText().toLowerCase().contains("la orden se realiz\u00f3 con \u00e9xito")) {
				bAssert = true;
			}
			Assert.assertTrue(bAssert);
		}
		String orden = cCC.obtenerTNyMonto2(driver, sOrden);
		System.out.println("orden = " + orden);
		detalles+="-Monto:"+orden.split("-")[2]+"-Prefactura:"+orden.split("-")[1];
		sOrders.add("Recargas" + orden + ", cuenta:"+ sAccid +", DNI: " + sDNI + ", Monto:" + sOrden.split("-")[2]);
		CBS_Mattu cInvoSer = new CBS_Mattu();
		Assert.assertTrue(cInvoSer.PagoEnCaja("1006", sAccid , "4001", sOrden.split("-")[2], sOrden.split("-")[1],driver));
		sleep(5000);
		driver.navigate().refresh();
		sleep(10000);
		String uMainBalance = cCBS.ObtenerValorResponse(cCBSM.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		System.out.println("saldo nuevo "+uMainBalance);
		Integer uiMainBalance = Integer.parseInt(uMainBalance.substring(0, (uMainBalance.length()) - 1));
		Integer monto = Integer.parseInt(orden.split("-")[2].replace(".", ""));
		monto = Integer.parseInt(monto.toString().substring(0, monto.toString().length()-2));
		Assert.assertTrue(iMainBalance+monto == uiMainBalance);
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".hasMotif.orderTab.detailPage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		WebElement tabla = driver.findElement(By.id("ep")).findElements(By.tagName("table")).get(1);
		String datos = tabla.findElements(By.tagName("tr")).get(4).findElements(By.tagName("td")).get(1).getText();
		Assert.assertTrue(datos.equalsIgnoreCase("activada")||datos.equalsIgnoreCase("activated"));
	}
	
	@Test (groups = {"Suspension", "GestionesPerfilOficina","E2E","Ciclo3"}, dataProvider="CuentaSuspension") //No se puede visualizar en el panel izquierdo el numero de orden en UAT y no se suspende la cuenta; y en SIT no existe la opci�n de DNI/CUIT
	public void TS_98484_CRM_Movil_REPRO_Suspension_por_Fraude_DNI_CUIT_Comercial_Fraude_por_suscripcion_Administrativo(String cDNI, String cLinea, String cProvincia, String cCiudad, String cPartido) {
		imagen = "TS98484";
		detalles = null;
		detalles = imagen + "-Suspension - DNI:" + cDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(5000);
		cc.irAGestion("suspensiones y reconexion back");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step1SelectSuspensionOrReconnection_nextBtn")));
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "contains", "suspensi\u00f3n");
		driver.findElement(By.id("Step1SelectSuspensionOrReconnection_nextBtn")).click();
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "contains", "dni/cuit");
		driver.findElement(By.id("Step2-SelectAssetOrDocument_nextBtn")).click();
		sleep(10000);
		driver.findElement(By.id("Step3_nextBtn")).click();
		sleep(8000);
		selectByText(driver.findElement(By.id("SelectFraud")),"Comercial");
		selectByText(driver.findElement(By.id("SelectSubFraud")),"Administrativo por suscripci\u00f3n");
		driver.findElement(By.id("TxtComment")).sendKeys("Fraude");
		driver.findElement(By.id("Step4_nextBtn")).click();
		sleep(8000);
		driver.findElement(By.id("StepSummary_nextBtn")).click();
		sleep(8000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")),"contains", "continue");
		boolean a = false;
		List <WebElement> realiz = driver.findElements(By.cssSelector(".slds-box.ng-scope"));
		for(WebElement x : realiz) {
			if(x.getText().toLowerCase().contains("tu gesti\u00f3n se realiz\u00f3 con \u00e9xito")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
		sleep(8000);
		CBS_Mattu cCBSM = new CBS_Mattu();
		Assert.assertTrue(cCBSM.obtenerStatusLinea(cLinea).equals("suspension fraude"));
		String orden = cc.obtenerOrden(driver, "Suspension administrativa");
		detalles+=" - Orden: "+orden;
	}
	
	@Test (groups = {"Suspension", "GestionesPerfilOficina","E2E","Ciclo3"}, dataProvider="CuentaSuspension")//No se puede visualizar en el panel izquierdo el numero de orden en UAT y no se suspende la cuenta
	public void TS_98491_CRM_Movil_REPRO_Suspension_por_Fraude_Linea_Comercial_Desconocimiento_Administrativo(String cDNI, String cLinea, String cProvincia, String cCiudad, String cPartido) {
		imagen = "TS98491";
		detalles = null;
		detalles = imagen + "- Suspension -DNI:" + cDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(5000);
		cc.irAGestion("suspensiones y reconexion back");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step1SelectSuspensionOrReconnection_nextBtn")));
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "contains", "suspensi\u00f3n");
		driver.findElement(By.id("Step1SelectSuspensionOrReconnection_nextBtn")).click();
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "contains", "linea");
		driver.findElement(By.id("Step2-SelectAssetOrDocument_nextBtn")).click();
		sleep(8000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")),"contains", "nombre: ");
		driver.findElement(By.id("Step3_nextBtn")).click();
		sleep(8000);
		selectByText(driver.findElement(By.id("SelectFraud")),"Comercial");
		selectByText(driver.findElement(By.id("SelectSubFraud")),"Desconocimiento");
		driver.findElement(By.id("TxtComment")).sendKeys("Fraude");
		driver.findElement(By.id("Step4_nextBtn")).click();
		sleep(8000);
		driver.findElement(By.id("StepSummary_nextBtn")).click();
		sleep(8000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")),"contains", "continue");
		sleep(15000);
		boolean a = false;
		List <WebElement> realiz = driver.findElements(By.cssSelector(".slds-box.ng-scope"));
		for(WebElement x : realiz) {
			if(x.getText().toLowerCase().contains("tu gesti\u00f3n se realiz\u00f3 con \u00e9xito")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
		sleep(8000);
		CBS_Mattu cCBSM = new CBS_Mattu();
		Assert.assertTrue(cCBSM.obtenerStatusLinea(cLinea).equals("suspension fraude"));
		String orden = cc.obtenerOrden(driver, "Suspension administrativa");
		detalles+=" - Orden: "+orden;
		
	}
	
	@Test (groups = {"GestionesPerfilOficina","Venta de Packs","E2E","Ciclo1"}, dataProvider="PackOfCom")
	public void Venta_de_Pack(String sDNI, String sLinea, String sPackOfCom, String cBanco, String cTarjeta, String cPromo, String cCuotas) throws AWTException, KeyManagementException, NoSuchAlgorithmException{
		imagen = "Venta De Pack Oficina";
		detalles = null;
		detalles = imagen + "- DNI: "+sDNI+" - Linea: "+sLinea;
		PagePerfilTelefonico pagePTelefo = new PagePerfilTelefonico(driver);
		CustomerCare cCC = new CustomerCare(driver);
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		pagePTelefo.buscarAssert();
		cCC.seleccionarCardPornumeroLinea(sLinea, driver);
		pagePTelefo.comprarPack();
		pagePTelefo.closerightpanel();
		sleep(8000);
		pagePTelefo.agregarPack(sPackOfCom);
		pagePTelefo.tipoDePago("en factura de venta");
		pagePTelefo.getTipodepago().click();
		sleep(12000);
		pagePTelefo.getSimulaciondeFactura().click();
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals", "tarjeta de credito");
		sleep(12000);
		selectByText(driver.findElement(By.id("BankingEntity-0")), cBanco);
		selectByText(driver.findElement(By.id("CardBankingEntity-0")), cTarjeta);
		selectByText(driver.findElement(By.id("promotionsByCardsBank-0")), cPromo);
		selectByText(driver.findElement(By.id("Installment-0")), cCuotas);
		String sOrden = cc.obtenerOrden2(driver);
		detalles+=" -Orden: "+sOrden;
		pagePTelefo.getMediodePago().click();
		sleep(15000);
		pagePTelefo.getOrdenSeRealizoConExito().click();
		sleep(15000);
		driver.navigate().refresh();
		sleep(10000);
		String orden = cCC.obtenerTNyMonto2(driver, sOrden);
		System.out.println("orden = "+orden);
		detalles+="-Monto:"+orden.split("-")[2]+"-Prefactura:"+orden.split("-")[1];
		CBS_Mattu cCBSM = new CBS_Mattu();
		cCBSM.PagarTCPorServicio(sOrden);
		String invoice = cCC.obtenerMontoyTNparaAlta(driver, sOrden);
		System.out.println(invoice);
		sleep(10000);
		driver.navigate().refresh();
		sleep(10000);
		CBS cCBS = new CBS();
		Assert.assertTrue(cCBS.validarActivacionPack(cCBSM.Servicio_QueryFreeUnit(sLinea), sPackOfCom));
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".hasMotif.orderTab.detailPage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		WebElement tabla = driver.findElement(By.id("ep")).findElements(By.tagName("table")).get(1);
		String datos = tabla.findElements(By.tagName("tr")).get(4).findElements(By.tagName("td")).get(1).getText();
		Assert.assertTrue(datos.equalsIgnoreCase("activada")||datos.equalsIgnoreCase("activated"));
	}
	
	
	@Test(groups = { "GestionesPerfilOficina", "Cambio de simcard","Ciclo3" }, priority = 1, dataProvider = "CambioSimCardOficina")
	public void TSCambioSimCardOficina(String sDNI, String sLinea) throws AWTException {
		imagen = "TSCambioSimCardOficina";
		detalles = null;
		detalles = imagen + "- DNI: " + sDNI+ "- Linea: "+sLinea;
		SalesBase sale = new SalesBase(driver);
		BasePage cambioFrameByID = new BasePage();
		CustomerCare cCC = new CustomerCare(driver);
		PagePerfilTelefonico pagePTelefo = new PagePerfilTelefonico(driver);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(8000);
		sale.BuscarCuenta("DNI", sDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(25000);
		cCC.seleccionarCardPornumeroLinea(sLinea, driver);
		sleep(3000);
		cCC.irAGestionEnCard("Cambio SimCard");
		sleep(2000);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("DeliveryMethodSelection")));
		sleep(15000);
		Select metodoEntrega = new Select (driver.findElement(By.id("DeliveryMethodSelection")));
		metodoEntrega.selectByVisibleText("Presencial");
		cCC.obligarclick(driver.findElement(By.id("DeliveryMethodConfiguration_nextBtn")));
		sleep(18000);
		cCC.obligarclick(driver.findElement(By.id("InvoicePreview_nextBtn")));
		sleep(12000);
		String orden = driver.findElement(By.className("top-data")).findElement(By.className("ng-binding")).getText();
		detalles+=" - Orden: "+orden;
		System.out.println("Orden " + orden);
		orden = orden.substring(orden.length()-8);
		//buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals","Efectivo");
		cCC.obligarclick(driver.findElement(By.id("OrderSumary_nextBtn")));
		sleep(15000);		
		sOrders.add("Cambio sim card Agente- Cuenta: "+accid+"Invoice: "+orden);
		//cCC.obligarclick(driver.findElement(By.id("OrderSumary_nextBtn")));
		sleep(15000);
		try {
			driver.findElement(By.id("Step_Error_Huawei_S029_nextBtn")).click();
			System.out.println("Error en prefactura huawei");
		}catch(Exception ex1) {}
		sleep(5000);
		driver.navigate().refresh();
		sleep(10000);
		String invoice = cCC.obtenerMontoyTNparaAlta(driver, orden);
		detalles+=" - Cuenta: "+accid+"Invoice: "+invoice.split("-")[0];
		System.out.println(invoice);
		sleep(10000);
		CBS_Mattu invoSer = new CBS_Mattu();
		Assert.assertTrue(invoSer.PagoEnCaja("1006", accid, "1001", invoice.split("-")[2], invoice.split("-")[1],driver));
		driver.navigate().refresh();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".hasMotif.orderTab.detailPage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		WebElement tabla = driver.findElement(By.id("ep")).findElements(By.tagName("table")).get(1);
		String datos = tabla.findElements(By.tagName("tr")).get(4).findElements(By.tagName("td")).get(1).getText();
		Assert.assertTrue(datos.equalsIgnoreCase("activada")||datos.equalsIgnoreCase("activated"));
		
	}
	
	@Test (groups = {"GestionesPerfilOficina","RenovacionCuota","E2E", "Ciclo1"}, dataProvider="RenovacionCuotaSinSaldoConTC")
	public void TS135397_CRM_Movil_REPRO_Renovacion_de_cuota_Presencial_Internet_50_MB_Dia_TC_sin_Credito(String sDNI, String sLinea, String sBanco, String sTarjeta, String sPromo, String sCuota, String sNumTarjeta, String sVenceMes, String sVenceAno, String sCodSeg, String sTipoDNI, String sDNITarjeta, String sTitular) throws AWTException, KeyManagementException, NoSuchAlgorithmException {
		imagen = "TS135397";
		//Check all
		detalles = "Renocavion de cuota: "+imagen+"DNI: "+sDNI+"Linea: "+sLinea;
		CBS cCBS = new CBS();
		CBS_Mattu cCBSM = new CBS_Mattu();
		String datosInicial = cCBS.ObtenerUnidadLibre(cCBSM.Servicio_QueryFreeUnit(sLinea), "Datos Libres");
		BasePage cambioFrameByID=new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI", sDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		detalles += "-Cuenta: "+accid;
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(25000);
		CustomerCare cCC = new CustomerCare(driver);
		cCC.seleccionarCardPornumeroLinea(sLinea, driver);
		sleep(3000);
		
		cCC.irAGestionEnCard("Renovacion de Datos");
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("combosMegas")));
		List<WebElement> elementos = driver.findElement(By.cssSelector(".table.slds-table.slds-table--bordered.slds-table--cell-buffer")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		for(WebElement UnE:elementos) {
			if(UnE.findElement(By.tagName("td")).getText().contains("50 MB")) {
				UnE.findElement(By.className("slds-checkbox")).click();
			}
		}
		driver.findElement(By.id("CombosDeMegas_nextBtn")).click();
		sleep(10000);
		List<WebElement> pago = driver.findElement(By.id("PaymentTypeRadio|0")).findElements(By.cssSelector(".slds-radio.ng-scope"));
		for (WebElement UnP : pago) {
			if (UnP.getText().toLowerCase().contains("factura")){
				UnP.click();
				break;
			}
		}
		cCC.obligarclick(driver.findElement(By.id("SetPaymentType_nextBtn")));
		sleep(15000);
		cCC.obligarclick(driver.findElement(By.id("InvoicePreview_nextBtn")));
		sleep(15000);
		String sOrden = cCC.obtenerOrden2(driver);
		detalles += "-Orden:" + sOrden;
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals", "tarjeta de credito");
		sleep(7000);
		driver.switchTo().frame(cambioFrame(driver, By.id("BankingEntity-0")));
		selectByText(driver.findElement(By.id("BankingEntity-0")), sBanco);
		sleep(5000);
		selectByText(driver.findElement(By.id("CardBankingEntity-0")), sTarjeta);
		sleep(5000);
		selectByText(driver.findElement(By.id("promotionsByCardsBank-0")), sPromo);
		sleep(5000);
		selectByText(driver.findElement(By.id("Installment-0")), sCuota);
		sleep(5000);
		driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")).click();
		sleep(20000);
		String msj = driver.findElement(By.cssSelector(".message.description.ng-binding.ng-scope")).getText(); 
		String check = driver.findElement(By.id("GeneralMessageDesing")).getText();
		Assert.assertTrue(msj.toLowerCase().contains("se ha enviado correctamente la factura a huawei. dirigirse a caja para realizar el pago de la misma"));
		Assert.assertTrue(check.toLowerCase().contains("la orden se realiz\u00f3 con \u00e9xito"));
		driver.findElement(By.id("SaleOrderMessages_nextBtn")).click();
		sleep(8000);
		String orden = cc.obtenerTNyMonto2(driver, sOrden);
		System.out.println("orden = "+orden);
		detalles+=", Monto:"+orden.split("-")[2]+"Prefactura: "+orden.split("-")[1];
		cCBSM.PagarTCPorServicio(sOrden);
		sleep(5000);
		driver.navigate().refresh();
		sleep(10000);
		String datosFinal = cCBS.ObtenerUnidadLibre(cCBSM.Servicio_QueryFreeUnit(sLinea), "Datos Libres");
		Assert.assertTrue((Integer.parseInt(datosInicial)+51200)==Integer.parseInt(datosFinal));
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".hasMotif.orderTab.detailPage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		WebElement tabla = driver.findElement(By.id("ep")).findElements(By.tagName("table")).get(1);
		String datos = tabla.findElements(By.tagName("tr")).get(4).findElements(By.tagName("td")).get(1).getText();
		Assert.assertTrue(datos.equalsIgnoreCase("activada")||datos.equalsIgnoreCase("activated"));
	}
	
	@Test (groups = {"GestionesPerfilOficina", "AnulacionDeVenta", "E2E","Ciclo4"}, dataProvider = "CuentaAnulacionDeVenta")
	public void Anulacion_De_Venta(String cDNI) {
		imagen = "Anulacion_De_Venta";
		detalles = null;
		detalles = imagen + " - DNI: " +cDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		cc.irAGestion("anulacion de ordenes");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-select.ng-pristine.ng-untouched.ng-valid.ng-not-empty")));
		buscarYClick(driver.findElements(By.cssSelector(".slds-button.slds-button--neutral")), "equals", "anulaci\u00f3n de venta");
		sleep(10000);
		if (TestBase.urlAmbiente.contains("sit")) {
			driver.switchTo().frame(cambioFrame(driver, By.id("AnnulmentReasonSelect")));
			selectByText(driver.findElement(By.id("AnnulmentReasonSelect")), "Arrepentimiento");
			driver.findElement(By.id("AnnulmentReason_nextBtn")).click();
			sleep(20000);
		}
		driver.switchTo().frame(cambioFrame(driver, By.xpath("//*[@id=\"ep\"]/div[2]/div[2]/table")));
		String gestion = driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[2]/table")).findElements(By.tagName("tr")).get(4).getText();
		Assert.assertTrue(gestion.contains("Estado") && (gestion.contains("Cancelada") || gestion.contains("Cancelled")));
	}
	
	@Test (groups = {"GestionesPerfilOficina","NumerosAmigos","E2E","Ciclo1"}, dataProvider="NumerosAmigosLetras")
	public void TXGPO0001_CRM_Movil_REPRO_FF_Alta_Presencial_Ingreso_Letras(String sDNI, String sLinea) {
		imagen = "TXGPO0001";
		BasePage cambioFrame=new BasePage();
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.id("SearchClientDocumentType")));
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
		wNumerosAmigos.get(0).findElement(By.tagName("input")).sendKeys("A");
		wNumerosAmigos.get(1).findElement(By.tagName("input")).sendKeys("B");
		wNumerosAmigos = driver.findElements(By.cssSelector(".slds-col--padded.slds-size--1-of-2"));
		Assert.assertFalse(wNumerosAmigos.get(0).findElement(By.tagName("input")).getText().equals("A"));
		Assert.assertFalse(wNumerosAmigos.get(1).findElement(By.tagName("input")).getText().equals("B"));
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Historial de Recargas", "Ciclo2"}, dataProvider = "CuentaProblemaRecarga")
	public void TS135346_Historial_de_Recargas_Consultar_detalle_de_Recargas_por_Canal_TODOS_Fan_FRONT_OOCC(String sDNI, String sNumTarjeta) {
		imagen = "TS135346";
		detalles = null;
		detalles = imagen + " - Historial de recargas - DNI:" + sDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(10000);
		cc.irAHistoriales();
		WebElement historialDeRecargas = null;
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-button.slds-button_brand")));
		for (WebElement x : driver.findElements(By.className("slds-card"))) {
			if (x.getText().toLowerCase().contains("historial de recargas"))
				historialDeRecargas = x;
		}
		historialDeRecargas.findElement(By.cssSelector(".slds-button.slds-button_brand")).click();
		sleep(7000);
		driver.switchTo().frame(cambioFrame(driver, By.id("text-input-03")));
		driver.findElement(By.id("text-input-03")).click();
		driver.findElement(By.xpath("//*[text() = 'Todos']")).click();
		if (driver.findElement(By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small")).isDisplayed()) {
			driver.findElement(By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small")).click();
			Assert.assertTrue(true);
		} else
			Assert.assertTrue(false);
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Consulta de Saldo", "Ciclo1"}, dataProvider = "ConsultaSaldo")
	public void TS_134373_CRM_Movil_Prepago_Vista_360_Consulta_de_Saldo_Verificar_credito_prepago_de_la_linea_FAN_Front_OOCC(String sDNI){
		imagen ="TS134373";
		detalles = null;
		detalles = imagen + "- Consulta de Saldo - DNI:"+sDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		WebElement cred = driver.findElement(By.xpath("//*[@id=\"j_id0:j_id5\"]/div/div/ng-include/div/div[2]/div[1]/ng-include/section[1]/div[2]/ul[2]/li[1]/span[3]"));
		Assert.assertTrue(!(cred.getText().isEmpty()));
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Consulta de Saldo", "Ciclo1"}, dataProvider = "ConsultaSaldo")
	public void TS_134376_CRM_Movil_Prepago_Vista_360_Consulta_de_Saldo_Verificar_saldo_del_cliente_FAN_Front_OOCC(String sDNI) {
		imagen="TS134376";
		detalles = null;
		detalles = imagen + "- Consulta de Saldo - DNI:" +sDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		cc.openleftpanel();
		cc.irAFacturacion();
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		boolean a = false;
		List <WebElement> saldo = driver.findElements(By.cssSelector(".slds-text-heading_medium.expired-date.expired-pink"));
		for(WebElement x : saldo) {
			if(x.getText().toLowerCase().equals("balance")) {
				a = true;
			}
			Assert.assertFalse(a);
		}
	}
	
	@Test (groups = {"GestionesPerfilOficina", "DetalleDeConsumo", "Ciclo2"}, dataProvider = "CuentaTriviasYSuscripciones")
	public void TS134783_CRM_Movil_Prepago_Vista_360_Detalle_de_consumo_Consulta_detalle_de_consumo_Datos_FAN_Front_OOCC_134783(String cDNI) {
		imagen = "TS134783";
		detalles = null;
		detalles = imagen + "- Detalle de Consumo - DNI: "+cDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		sleep(3000);
		driver.findElement(By.className("card-top")).click();
		sleep(3000);
		buscarYClick(driver.findElements(By.className("slds-text-body_regular")), "equals", "detalle de consumos");
		driver.switchTo().frame(cambioFrame(driver, By.className("summary-container")));
		WebElement ConsumoDatos =  driver.findElement(By.className("summary-container")).findElements(By.tagName("div")).get(0).findElement(By.className("unit-div"));
		System.out.println(ConsumoDatos.getText());
		Assert.assertTrue(ConsumoDatos.isDisplayed());
		}
	
	@Test (groups = {"GestionesPerfilOficina", "DetalleDeConsumo", "Ciclo2"}, dataProvider = "CuentaTriviasYSuscripciones")
	public void TS134782_CRM_Movil_Prepago_Vista_360_Detalle_de_consumo_Consulta_detalle_de_consumo_SMS_FAN_Front_OOCC_134782(String cDNI) {
		imagen = "TS134782";
		detalles = null;
		detalles = imagen + "- Detalle de Consumos - DNI: "+cDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		sleep(3000);
		driver.findElement(By.className("card-top")).click();
		sleep(3000);
		buscarYClick(driver.findElements(By.className("slds-text-body_regular")), "equals", "detalle de consumos");
		driver.switchTo().frame(cambioFrame(driver, By.className("summary-container")));
		WebElement ConsumoDatos = driver.findElements(By.xpath("//*[@id='j_id0:j_id5']/div//div[2]/ng-include/div/div[2]/div[*]")).get(2).findElement(By.className("unit-div"));										 
		System.out.println(ConsumoDatos.getText());
		Assert.assertTrue(ConsumoDatos.isDisplayed());
		}
	
	@Test (groups = {"GestionesPerfilOficina", "DetalleDeConsumo", "Ciclo2"}, dataProvider = "CuentaTriviasYSuscripciones")
	public void TS134784_CRM_Movil_Prepago_Vista_360_Detalle_de_consumo_Consulta_detalle_de_consumo_Voz_FAN_Front_OOCC(String cDNI) {
		imagen = "TS134784";
		detalles = null;
		detalles = imagen + "-Vista 360 - DNI: "+cDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		sleep(3000);
		driver.findElement(By.className("card-top")).click();
		sleep(3000);
		buscarYClick(driver.findElements(By.className("slds-text-body_regular")), "equals", "detalle de consumos");
		driver.switchTo().frame(cambioFrame(driver, By.className("summary-container")));
		WebElement ConsumoDatos = driver.findElements(By.xpath("//*[@id='j_id0:j_id5']/div//div[2]/ng-include/div/div[2]/div[*]")).get(1).findElement(By.className("unit-div"));
		System.out.println(ConsumoDatos.getText());
		Assert.assertTrue(ConsumoDatos.isDisplayed());
		}
	
	@Test (groups = {"GestionesPerfilOficina", "DetalleDeConsumo", "Ciclo2"}, dataProvider = "CuentaTriviasYSuscripciones")
	public void TS134785_CRM_Movil_Prepago_Vista_360_Detalle_de_consumo_Consulta_detalle_de_Otros_consumos_FAN_Front_OOCC_134785(String cDNI) {
		imagen = "TS134785";
		detalles = null;
		detalles = imagen + "- Detalles de Consumos -DNI:" + cDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		sleep(3000);
		driver.findElement(By.className("card-top")).click();
		sleep(3000);
		buscarYClick(driver.findElements(By.className("slds-text-body_regular")), "equals", "detalle de consumos");
		driver.switchTo().frame(cambioFrame(driver, By.className("summary-container")));
		sleep(15000);
		WebElement ConsumoDatos = driver.findElements(By.xpath("//*[@id='j_id0:j_id5']/div//div[2]/ng-include/div/div[2]/div[*]")).get(3).findElement(By.className("unit-div"));
		System.out.println(ConsumoDatos.getText());
		Assert.assertTrue(ConsumoDatos.isDisplayed());
		
	}
	
	
	@Test (groups= {"GestionesPerfilOficina", "HistorialDeRecargasSOS", "Ciclo2"},  dataProvider = "HistoriaRecarga")
	public void TS134470_CRM_Movil_Prepago_Historial_De_Recargas_SOS_S440_FAN_Front_OOCC(String cDNI, String cLinea) {
		imagen = "TS134470";
		detalles = null;
		detalles = imagen + "-Historial de recargas - DNI: "+cDNI;
		boolean enc = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		CustomerCare cc = new CustomerCare(driver);
		cc.seleccionarCardPornumeroLinea(cLinea, driver);
		driver.findElement(By.xpath("//*[@id=\"j_id0:j_id5\"]/div/div/ng-include/div/div[2]/div[2]/ng-include/section[1]/div[2]/ul[1]/li[2]/a/i")).click();
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-card.slds-m-around--small.ta-fan-slds")));
		List <WebElement> historiales = driver.findElements(By.cssSelector(".slds-m-around_small.ta-fan-slds"));
		for (WebElement UnH: historiales) {
			System.out.println(UnH.findElement(By.cssSelector(".slds-card__header.slds-grid")).getText());
			if(UnH.findElement(By.cssSelector(".slds-card__header.slds-grid")).getText().equals("Historial de recargas S.O.S")) {
				enc = true;
				driver.findElements(By.cssSelector(".slds-button.slds-button_brand")).get(1).click();
				sleep(5000);
				driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small")));
				driver.findElement(By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small")).click();
				sleep(5000);
				Assert.assertTrue(true);
				break;
			}
		}
		Assert.assertTrue(enc);
	}
	
	@Test (groups= {"GestionesPerfilOficina", "Historial de Recargas", "Ciclo2"},  dataProvider = "CuentaModificacionDeDatos")
	public void TS134473_CRM_Movil_Prepago_Historial_De_Packs_Fan_Front_OOCC(String cDNI, String sLinea) {
		imagen = "TS134473";
		detalles = null;
		detalles = imagen + "- Historial de packs - DNI: "+cDNI;
		boolean enc = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(20000);
		CustomerCare cc = new CustomerCare(driver);
		cc.irAHistoriales();
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-card.slds-m-around--small.ta-fan-slds")));
		List <WebElement> historiales = driver.findElements(By.className("slds-card"));
		for (WebElement UnH: historiales) {
			System.out.println(UnH.findElement(By.cssSelector(".slds-card__header.slds-grid")).getText());
			if(UnH.findElement(By.cssSelector(".slds-card__header.slds-grid")).getText().equals("Historial de packs")) {
				enc = true;
				driver.findElement(By.cssSelector(".slds-button.slds-button_brand")).click();
				sleep(5000);
				driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small")));
				driver.findElement(By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small")).click();
				sleep(5000);
				Assert.assertTrue(true);
				break;
			}
		}
		Assert.assertTrue(enc);
	}
	

	@Test(groups={"Sales","GestionDeClientes", "Ciclo1"},dataProvider = "validaDocumentacion")
	public void TS135496_CRM_Movil_REPRO_Busqueda_DNI_Numero_de_Documento(String sDNI, String sNumeroDeCuenta, String sNombre, String sApellido, String sLibreta, String sRazon, String sEmail ){
		imagen = "TS135496";
		detalles = null;
		detalles = imagen + "- Gestion de clientes - DNI:" + sDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> solapas = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		assertTrue(solapas.get(0).findElement(By.tagName("a")).getText().equals("Clientes Activos"));
	}
	
	@Test(groups={"Sales","GestionDeClientes", "Ciclo1"}, dataProvider = "validaDocumentacion")
	public void TS135503_CRM_Movil_REPRO_Busqueda_Apellido(String sDNI, String sNumeroDeCuenta, String sNombre, String sApellido, String sLibreta, String sRazon, String sEmail){
		imagen = "TS135503";
		detalles = null;
		detalles = imagen + "- Gestion de Clientes - DNI: "+sDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarAvanzada("",sApellido,"","","");
		sleep(1500);
		driver.findElement(By.id("SearchClientsDummy")).click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> solapas = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		assertTrue(solapas.get(0).findElement(By.tagName("a")).getText().equals("Clientes Activos"));
	}
	
	@Test(groups={"Sales","GestionDeClientes", "Ciclo1"}, dataProvider = "validaDocumentacion")
	public void TS135509_CRM_Movil_REPRO_Busqueda_Numero_de_Cuenta(String sDNI, String sNumeroDeCuenta, String sNombre, String sApellido, String sLibreta, String sRazon, String sEmail){
		imagen = "TS135509";
		detalles = null;
		detalles = imagen + "- Gestion de Clientes - DNI: "+sDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarAvanzada("", "", "", sNumeroDeCuenta, "");
		sleep(1500);
		driver.findElement(By.id("SearchClientsDummy")).click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> solapas = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		assertTrue(solapas.get(0).findElement(By.tagName("a")).getText().equals("Clientes Activos"));
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Nominacion", "Ciclo1"}, dataProvider = "DatosSalesNominacionExistenteOfCom")
	public void TS85097_CRM_Movil_REPRO_Nominatividad_Cliente_Existente_Presencial_DOC_OfCom(String sLinea, String sDni) {
		imagen = "TS85097";
		detalles = null;
		detalles = imagen + " -Nominacion: " + sDni+"- Linea: "+sLinea;
		boolean nominacion = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		driver.findElement(By.id("PhoneNumber")).sendKeys(sLinea);
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep(5000);
		driver.findElement(By.cssSelector(".slds-button.slds-button--icon.slds-m-right--x-small.ng-scope")).click();
		sleep(2000);
		WebElement botonNominar = null;
		for (WebElement x : driver.findElements(By.cssSelector(".slds-hint-parent.ng-scope"))) {
			if (x.getText().toLowerCase().contains("plan con tarjeta"))
				botonNominar = x;
		}
		for (WebElement x : botonNominar.findElements(By.tagName("td"))) {
			if (x.getAttribute("data-label").equals("actions"))
				botonNominar = x;
		}
		botonNominar.findElement(By.tagName("a")).click();
		sleep(5000);
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact2("DNI", sDni, "Masculino");
		driver.findElement(By.id("Contact_nextBtn")).click();
		sleep(10000);
		contact.tipoValidacion("documento");
		File directory = new File("Dni.jpg");
		contact.subirArchivo(new File(directory.getAbsolutePath()).toString(), "si");
		sleep(30000);
		for (WebElement x : driver.findElement(By.id("NominacionExitosa")).findElements(By.tagName("p"))) {
			if (x.getText().toLowerCase().contains("nominaci\u00f3n exitosa!"))
				nominacion = true;
		}
		Assert.assertTrue(nominacion);
		driver.findElement(By.id("FinishProcess_nextBtn")).click();
		sleep(3000);
	}
	
	@Test (groups = {"GestionesPerfilOficina", "ReseteoDeClave", "Ciclo2"}, dataProvider = "CuentaReseteoClave")
	public void TS95980_CRM_Movil_REPRO_Reseteo_de_Clave_Presencial(String sDNI) {
		imagen = "TS95980";
		detalles = null;
		detalles = imagen + "-ReseteoDeClave - DNI: "+sDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("profile-box")));
		buscarYClick(driver.findElements(By.className("profile-edit")),"contains", "reseteo clave");
		sleep(3000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step 1_nextBtn")));
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("Step 1_nextBtn")).click();
		sleep(5000);
		WebElement msj = driver.findElement(By.className("ta-care-omniscript-done"));
		Assert.assertTrue(msj.getText().contains("Su n\u00famero de confirmaci\u00f3n es: "));
	}
	
	@Test (groups = {"GestionesPerfilOficina", "ReseteoDeClave", "Ciclo2"}, dataProvider = "CuentaReseteoClave")
	public void TS95982_CRM_Movil_REPRO_No_Reseteo_de_Clave_Presencial(String sDNI) {
		imagen = "TS95982";
		detalles = null;
		detalles = imagen + "-ReseteoDeClave - DNI: "+sDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", "30998801");
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.className("profile-box")));
		buscarYClick(driver.findElements(By.className("profile-edit")),"contains", "reseteo clave");
		sleep(3000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Step 1_nextBtn")));
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "no");
		driver.findElement(By.id("Step 1_nextBtn")).click();
		sleep(5000);
		WebElement msj = driver.findElement(By.className("ta-care-omniscript-done"));
		Assert.assertTrue(msj.getText().contains("Su n\u00famero de confirmaci\u00f3n es: "));
	}
	
	@Test (groups = {"GestionesPerfilOficina", "RenovacionCuota","E2E"}, dataProvider="RenovacionCuotaConSaldo")
	public void TS130056_CRM_Movil_REPRO_Renovacion_de_cuota_Presencial_Reseteo_200_MB_por_Dia_Efectivo_con_Credito(String sDNI, String sLinea) throws AWTException {
		imagen="TS130056";
		detalles = null;
		detalles = "Renocavion de cuota: "+imagen+" - DNI: "+sDNI+" - Linea: "+sLinea;
		CBS cCBS = new CBS();
		CBS_Mattu cCBSM = new CBS_Mattu();
		String datosInicial = cCBS.ObtenerUnidadLibre(cCBSM.Servicio_QueryFreeUnit(sLinea), "Datos Libres");
		BasePage cambioFrameByID=new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI", sDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		detalles+="-Cuenta: "+accid;
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(20000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(5000);
		CustomerCare cCC = new CustomerCare(driver);
		cCC.irAGestionEnCard("Renovacion de Datos");
		sleep(12000);
		driver.switchTo().frame(cambioFrame(driver, By.id("combosMegas")));
		List<WebElement> elementos = driver.findElement(By.cssSelector(".table.slds-table.slds-table--bordered.slds-table--cell-buffer")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		for(WebElement UnE:elementos) {
			if(UnE.findElement(By.tagName("td")).getText().contains("200 MB")) {
				UnE.findElement(By.className("slds-checkbox")).click();
			}
		}sleep(2000);
		cCC.obligarclick(driver.findElement(By.id("CombosDeMegas_nextBtn")));
		sleep(10000);
		List<WebElement> pago = driver.findElement(By.id("PaymentTypeRadio|0")).findElements(By.cssSelector(".slds-radio.ng-scope"));
		for (WebElement UnP : pago) {
			if (UnP.getText().toLowerCase().contains("factura")){
				UnP.click();
				break;
			}
		}
		cCC.obligarclick(driver.findElement(By.id("SetPaymentType_nextBtn")));
		sleep(16000);
		cCC.obligarclick(driver.findElement(By.id("InvoicePreview_nextBtn")));
		sleep(12000);
		String sOrden = cCC.obtenerOrden2(driver);
		detalles+="-Orden: "+sOrden;
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals", "efectivo");
		sleep(8000);
		driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")).click();
		sleep(15000);
		String msj = driver.findElement(By.cssSelector(".message.description.ng-binding.ng-scope")).getText(); 
		String check = driver.findElement(By.id("GeneralMessageDesing")).getText();
		Assert.assertTrue(msj.toLowerCase().contains("se ha enviado correctamente la factura a huawei. dirigirse a caja para realizar el pago de la misma"));
		Assert.assertTrue(check.toLowerCase().contains("la orden se realiz\u00f3 con \u00e9xito"));
		driver.findElement(By.id("SaleOrderMessages_nextBtn")).click();
		sleep(8000);
		String orden = cc.obtenerTNyMonto2(driver, sOrden);
		System.out.println("orden = "+orden);
		detalles +="-Monto: "+orden.split("-")[2]+"-Prefactura: "+orden.split("-")[1];
		sOrders.add("Recargas" + orden + ", cuenta:"+accid+", DNI: " + sDNI +", Monto:"+orden.split("-")[2]);
		CBS_Mattu invoSer = new CBS_Mattu();
		Assert.assertTrue(invoSer.PagoEnCaja("1006", accid, "1001", orden.split("-")[2], orden.split("-")[1],driver));
		sleep(5000);
		driver.navigate().refresh();
		sleep(10000);
		String datosFinal = cCBS.ObtenerUnidadLibre(cCBSM.Servicio_QueryFreeUnit(sLinea), "Datos Libres");
		Assert.assertTrue((Integer.parseInt(datosInicial)+204800)==Integer.parseInt(datosFinal));
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".hasMotif.orderTab.detailPage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		WebElement tabla = driver.findElement(By.id("ep")).findElements(By.tagName("table")).get(1);
		String datos = tabla.findElements(By.tagName("tr")).get(4).findElements(By.tagName("td")).get(1).getText();
		Assert.assertTrue(datos.equalsIgnoreCase("activada")||datos.equalsIgnoreCase("activated"));
	}
	
	@Test (groups = {"GestionesPerfilOficina", "RenovacionCuota","E2E"}, dataProvider="RenovacionCuotaConSaldo")
	public void TS130069_CRM_Movil_REPRO_Renovacion_de_cuota_Presencial_Reseteo_200_MB_por_Dia_Descuento_de_saldo_con_Credito(String sDNI, String sLinea) {
		imagen = "TS130069";
		detalles = null;
		detalles = "Renocavion de cuota: "+imagen+" - DNI: "+sDNI+" - Linea: "+sLinea;
		CBS cCBS = new CBS();
		CBS_Mattu cCBSM = new CBS_Mattu();
		String datosInicial = cCBS.ObtenerUnidadLibre(cCBSM.Servicio_QueryFreeUnit(sLinea), "Datos Libres");
		String sMainBalance = cCBS.ObtenerValorResponse(cCBSM.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer iMainBalance = Integer.parseInt(sMainBalance.substring(0, (sMainBalance.length()) - 1));
		System.out.println("datos inicial:"+datosInicial);
		System.out.println("Saldo Inicial:"+iMainBalance);
		BasePage cambioFrameByID=new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI", sDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		detalles += "-Cuenta: "+accid;
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(20000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(5000);
		CustomerCare cCC = new CustomerCare(driver);
		cCC.irAGestionEnCard("Renovacion de Datos");
		sleep(12000);
		driver.switchTo().frame(cambioFrame(driver, By.id("combosMegas")));
		List<WebElement> elementos = driver.findElement(By.cssSelector(".table.slds-table.slds-table--bordered.slds-table--cell-buffer")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		for(WebElement UnE:elementos) {
			if(UnE.findElement(By.tagName("td")).getText().contains("200 MB")) {
				UnE.findElement(By.className("slds-checkbox")).click();
			}
		}sleep(2000);
		cCC.obligarclick(driver.findElement(By.id("CombosDeMegas_nextBtn")));
		sleep(10000);
		List<WebElement> pago = driver.findElement(By.id("PaymentTypeRadio|0")).findElements(By.cssSelector(".slds-radio.ng-scope"));
		for (WebElement UnP : pago) {
			if (UnP.getText().toLowerCase().contains("saldo")){
				UnP.click();
				break;
			}
		}		
		cCC.obligarclick(driver.findElement(By.id("SetPaymentType_nextBtn")));
		sleep(18000);
		String mesj = driver.findElement(By.cssSelector(".slds-box.ng-scope")).getText();
		System.out.println(mesj);
		Assert.assertTrue(mesj.equalsIgnoreCase("La operaci\u00f3n termino exitosamente"));
		String uMainBalance = cCBS.ObtenerValorResponse(cCBSM.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer uiMainBalance = Integer.parseInt(uMainBalance.substring(0, (uMainBalance.length()) - 1));
		System.out.println("Saldo final:"+uiMainBalance);
		Assert.assertTrue(iMainBalance > uiMainBalance);
		String datosFinal = cCBS.ObtenerUnidadLibre(cCBSM.Servicio_QueryFreeUnit(sLinea), "Datos Libres");
		System.out.println("Datos final:"+datosFinal);
		Assert.assertTrue((Integer.parseInt(datosInicial)+204800)==Integer.parseInt(datosFinal));
		
	}
		
	@Test (groups = {"GestionesPerfilOficina","RenovacionCuota","E2E", "Ciclo1"}, dataProvider="RenovacionCuotaConSaldo")
	public void TS135395_CRM_Movil_REPRO_Renovacion_de_cuota_Presencial_Internet_50_MB_Dia_Efectivo_con_Credito(String sDNI, String sLinea) throws AWTException {
		imagen = "TS135395";
		//Check all
		detalles = null;
		detalles = "Renocavion de cuota: "+imagen+" - DNI: "+sDNI+" - Linea: "+sLinea;
		CBS cCBS = new CBS();
		CBS_Mattu cCBSM = new CBS_Mattu();
		String datosInicial = cCBS.ObtenerUnidadLibre(cCBSM.Servicio_QueryFreeUnit(sLinea), "Datos Libres");
		BasePage cambioFrameByID=new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI", sDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		detalles +="-Cuenta:"+accid;
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(25000);
		CustomerCare cCC = new CustomerCare(driver);
		cCC.seleccionarCardPornumeroLinea(sLinea, driver);
		sleep(3000);
		
		cCC.irAGestionEnCard("Renovacion de Datos");
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("combosMegas")));
		List<WebElement> elementos = driver.findElement(By.cssSelector(".table.slds-table.slds-table--bordered.slds-table--cell-buffer")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		for(WebElement UnE:elementos) {
			if(UnE.findElement(By.tagName("td")).getText().contains("50 MB")) {
				UnE.findElement(By.className("slds-checkbox")).click();
			}
		}sleep(2000);
		cCC.obligarclick(driver.findElement(By.id("CombosDeMegas_nextBtn")));
		sleep(10000);
		List<WebElement> pago = driver.findElement(By.id("PaymentTypeRadio|0")).findElements(By.cssSelector(".slds-radio.ng-scope"));
		for (WebElement UnP : pago) {
			if (UnP.getText().toLowerCase().contains("factura")){
				UnP.click();
				break;
			}
		}
		cCC.obligarclick(driver.findElement(By.id("SetPaymentType_nextBtn")));
		sleep(15000);
		cCC.obligarclick(driver.findElement(By.id("InvoicePreview_nextBtn")));
		sleep(18000);
		String sOrden = cCC.obtenerOrden2(driver);
		detalles+="-Orden: "+sOrden;
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals", "efectivo");
		sleep(8000);
		cCC.obligarclick(driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")));
		sleep(30000);
		String msj = driver.findElement(By.cssSelector(".message.description.ng-binding.ng-scope")).getText(); 
		String check = driver.findElement(By.id("GeneralMessageDesing")).getText();
		Assert.assertTrue(msj.toLowerCase().contains("se ha enviado correctamente la factura a huawei. dirigirse a caja para realizar el pago de la misma"));
		Assert.assertTrue(check.toLowerCase().contains("la orden se realiz\u00f3 con \u00e9xito"));
		driver.findElement(By.id("SaleOrderMessages_nextBtn")).click();
		sleep(12000);
		String orden = cc.obtenerTNyMonto2(driver, sOrden);
		System.out.println("orden = "+orden);
		detalles +="-Monto: "+orden.split("-")[2]+"-Prefactura: "+orden.split("-")[1];
		sOrders.add("Recargas" + orden + ", cuenta:"+accid+", DNI: " + sDNI +", Monto:"+orden.split("-")[2]);
		CBS_Mattu invoSer = new CBS_Mattu();
		Assert.assertTrue(invoSer.PagoEnCaja("1006", accid, "1001", orden.split("-")[2], orden.split("-")[1],driver));
		sleep(5000);
		driver.navigate().refresh();
		sleep(10000);
		String datosFinal = cCBS.ObtenerUnidadLibre(cCBSM.Servicio_QueryFreeUnit(sLinea), "Datos Libres");
		Assert.assertTrue((Integer.parseInt(datosInicial)+51200)==Integer.parseInt(datosFinal));
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".hasMotif.orderTab.detailPage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		WebElement tabla = driver.findElement(By.id("ep")).findElements(By.tagName("table")).get(1);
		String datos = tabla.findElements(By.tagName("tr")).get(4).findElements(By.tagName("td")).get(1).getText();
		Assert.assertTrue(datos.equalsIgnoreCase("activada")||datos.equalsIgnoreCase("activated"));
	}
	
	@Test (groups={"Sales","GestionDeClientes", "Ciclo1"},dataProvider = "invalidaDocumentacion")
	public void TS135497_CRM_Movil_REPRO_Busqueda_DNI_Numero_de_Documento_no_existente(String sDNI, String sNumeroDeCuenta, String sNombre, String sApellido, String sLibreta, String sRazon, String sEmail){
		imagen = "TS135497";
		detalles = null;
		detalles = imagen+"-Gestion de clientes - DNI: "+sDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		boolean a = false;
		for(WebElement x : driver.findElements(By.className("slds-form-element__control"))){
			if(x.getText().toLowerCase().equals("no hay ning\u00fan cliente con este tipo y n\u00famero de documento. busc\u00e1 con otro dato o cre\u00e1 un nuevo cliente.")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups={"Sales","GestionDeClientes", "Ciclo1"},dataProvider = "validaDocumentacion")
	public void TS135499_CRM_Movil_REPRO_Busqueda_Libreta_de_enrolamiento_Numero_de_Documento(String sDNI, String sNumeroDeCuenta, String sNombre, String sApellido, String sLibreta, String sRazon, String sEmail){
		imagen = "TS135499";
		detalles = null;
		detalles = imagen+"- Gestion de clientes - Libreta de enrolamiento: "+sLibreta;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("Libreta de Enrolamiento", sLibreta);
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> activo = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		assertTrue(activo.get(0).findElement(By.tagName("a")).getText().equals("Clientes Activos"));
	}
	
	@Test (groups={"Sales","GestionDeClientes", "Ciclo1"},dataProvider = "invalidaDocumentacion")
	public void TS135500_CRM_Movil_REPRO_Busqueda_Libreta_dE_enrolamiento_Numero_de_Documento_no_existente(String sDNI, String sNumeroDeCuenta, String sNombre, String sApellido, String sLibreta, String sRazon, String sEmail) {
		imagen = "TS135500";
		detalles = null;
		detalles = imagen+"- Gestion de clientes - Libreta de enrolamiento: "+sLibreta;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("Libreta de Enrolamiento", sLibreta);
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		boolean a = false;
		for(WebElement x : driver.findElements(By.className("slds-form-element__control"))){
			if(x.getText().toLowerCase().contains("no hay ning\u00fan cliente con este tipo y n\u00famero de documento.")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups={"Sales","GestionDeClientes", "Ciclo1"},dataProvider = "validaDocumentacion")
	public void TS135501_CRM_Movil_REPRO_Busqueda_Nombre(String sDNI, String sNumeroDeCuenta, String sNombre, String sApellido, String sLibreta, String sRazon, String sEmail) {
		imagen = "TS135501";
		detalles = null;
		detalles = imagen+" - Gestion de clientes - Nombre: "+sNombre;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarAvanzada(sNombre,"","","","");
		sleep(1500);
		driver.findElement(By.id("SearchClientsDummy")).click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> activo = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		assertTrue(activo.get(0).findElement(By.tagName("a")).getText().equals("Clientes Activos"));
	}
	
	@Test (groups={"Sales","GestionDeClientes", "Ciclo1"},dataProvider = "invalidaDocumentacion")
	public void TS135502_CRM_Movil_REPRO_Busqueda_Nombre_No_existente(String sDNI, String sNumeroDeCuenta, String sNombre, String sApellido, String sLibreta, String sRazon, String sEmail) {
		imagen = "TS135502";
		detalles = null;
		detalles = imagen+" - Gestion de clientes - Nombre: "+sNombre;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarAvanzada(sNombre,"","","","");
		sleep(1500);
		driver.findElement(By.id("SearchClientsDummy")).click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		boolean a = false;
		for(WebElement x : driver.findElements(By.className("slds-form-element__control"))){
			if(x.getText().toLowerCase().equals("no hay ning\u00fan cliente con este tipo y n\u00famero de documento. busc\u00e1 con otro dato o cre\u00e1 un nuevo cliente.")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups={"Sales","GestionDeClientes", "Ciclo1"},dataProvider = "invalidaDocumentacion")
	public void TS135504_CRM_Movil_REPRO_Busqueda_Apellido_No_existente(String sDNI, String sNumeroDeCuenta, String sNombre, String sApellido, String sLibreta, String sRazon, String sEmail) {
		imagen = "TS135504";
		detalles = null;
		detalles = imagen+"- Gestion de clinetes - Apellido: "+sApellido;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarAvanzada("",sApellido,"","","");
		sleep(1500);
		driver.findElement(By.id("SearchClientsDummy")).click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		boolean a = false;
		for(WebElement x : driver.findElements(By.className("slds-form-element__control"))){
			if(x.getText().toLowerCase().equals("no hay ning\u00fan cliente con este tipo y n\u00famero de documento. busc\u00e1 con otro dato o cre\u00e1 un nuevo cliente.")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups={"Sales","GestionDeClientes", "Ciclo1"},dataProvider = "validaDocumentacion")
	public void TS135505_CRM_Movil_REPRO_Busqueda_Razon_Social(String sDNI, String sNumeroDeCuenta, String sNombre, String sApellido, String sLibreta, String sRazon, String sEmail) {
		imagen = "TS135505";
		detalles = null;
		detalles = imagen+" - Gestion de clientes - Razon social: "+sRazon;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarAvanzada("","",sRazon,"","");
		sleep(1500);
		driver.findElement(By.id("SearchClientsDummy")).click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> activo = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		assertTrue(activo.get(0).findElement(By.tagName("a")).getText().equals("Clientes Activos"));
	}
	
	@Test (groups={"Sales","GestionDeClientes", "Ciclo1"},dataProvider = "invalidaDocumentacion")
	public void TS135506_CRM_Movil_REPRO_Busqueda_Razon_social_No_existente(String sDNI, String sNumeroDeCuenta, String sNombre, String sApellido, String sLibreta, String sRazon, String sEmail) {
		imagen = "TS135506";
		detalles = null;
		detalles = imagen+" - Gestion de clientes - Razon social: "+sRazon;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarAvanzada("","",sRazon,"","");
		sleep(1500);
		driver.findElement(By.id("SearchClientsDummy")).click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> vacio = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		Assert.assertTrue(vacio.get(0).findElement(By.tagName("a")).getText().isEmpty());
	}
	
	@Test (groups={"Sales","GestionDeClientes", "Ciclo1"},dataProvider = "validaDocumentacion")
	public void TS_135507_CRM_Movil_REPRO_Busqueda_Email(String sDNI, String sNumeroDeCuenta, String sNombre, String sApellido, String sLibreta, String sRazon, String sEmail) {
		imagen = "TS135507";
		detalles = null;
		detalles = imagen+" - Gestion de clientes - Email: "+sEmail;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarAvanzada("","","","",sEmail);
		sleep(1500);
		driver.findElement(By.id("SearchClientsDummy")).click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> activo = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		assertTrue(activo.get(0).findElement(By.tagName("a")).getText().equals("Clientes Activos"));
	}
	
	@Test (groups={"Sales","GestionDeClientes", "Ciclo1"},dataProvider = "invalidaDocumentacion")
	public void TS135508_CRM_Movil_REPRO_Busqueda_Email_No_existente(String sDNI, String sNumeroDeCuenta, String sNombre, String sApellido, String sLibreta, String sRazon, String sEmail) {
		imagen = "TS135508";
		detalles = null;
		detalles = imagen+" - Gestion de clientes - Email: "+sEmail;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarAvanzada("","","","",sEmail);
		sleep(1500);
		driver.findElement(By.id("SearchClientsDummy")).click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		boolean a = false;
		for(WebElement x : driver.findElements(By.className("slds-form-element__control"))){
			if(x.getText().toLowerCase().equals("no hay ning\u00fan cliente con este tipo y n\u00famero de documento. busc\u00e1 con otro dato o cre\u00e1 un nuevo cliente.")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups={"Sales","GestionDeClientes", "Ciclo1"},dataProvider = "invalidaDocumentacion")
	public void TS135510_CRM_Movil_REPRO_Busqueda_Numero_de_Cuenta_No_existente(String sDNI, String sNumeroDeCuenta, String sNombre, String sApellido, String sLibreta, String sRazon, String sEmail) {
		imagen = "TS135510";
		detalles = null;
		detalles = imagen+" - Gestion de clientes - Numero de cuenta: "+sNumeroDeCuenta;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarAvanzada("", "", "", sNumeroDeCuenta, "");
		sleep(1500);
		driver.findElement(By.id("SearchClientsDummy")).click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> vacio = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		Assert.assertTrue(vacio.get(0).findElement(By.tagName("a")).getText().isEmpty());
	}
	
	@Test (groups={"Sales","GestionDeClientes", "Ciclo1"})
	public void TS135495_CRM_Movil_REPRO_Busqueda_Tipo_de_documento_DNI() {
		imagen = "TS135495";
		detalles = null;
		detalles = imagen+" - Gestion de clientes";
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		selectByText(driver.findElement(By.id("SearchClientDocumentType")), "DNI");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		boolean a = false;
			if(driver.findElement(By.id("SearchClientDocumentType")).getText().toLowerCase().contains("dni")) {
				a = true;
			}
			Assert.assertTrue(a);
		}
	
	@Test (groups={"Sales","GestionDeClientes", "Ciclo1"})
	public void TS135498_CRM_Movil_REPRO_Busqueda_Tipo_de_documento_Libreta_de_enrolamiento() {
		imagen = "TS135498";
		detalles = null;
		detalles = imagen+" - Gestion de clientes";
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		selectByText(driver.findElement(By.id("SearchClientDocumentType")), "Libreta de Enrolamiento");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		boolean a = false;
			if(driver.findElement(By.id("SearchClientDocumentType")).getText().toLowerCase().contains("dni")) {
				a = true;
			}
			Assert.assertTrue(a);
	}
	
	@Test (groups= {"GestionPerfilOficina", "Ciclo2", "Vista360"}, dataProvider = "documentacionVista360")
	public void TS_134379_CRM_Movil_Prepago_Vista_360_Mis_Servicios_Visualizacion_del_estado_de_los_servicios_activos_FAN_Front_OOCC(String sDNI) {
		imagen = "TS134379";
		detalles = null;
		detalles = imagen + "-Vista 360 - DNI: "+sDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		sleep(3000);
		driver.findElement(By.className("card-top")).click();
		sleep(3000);
		buscarYClick(driver.findElements(By.className("slds-text-body_regular")), "equals", "productos y servicios");
		sleep(10000);
		boolean a = false;
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-grid.slds-wrap.slds-card.slds-m-bottom--small.slds-p-around--medium")));
		WebElement verif = driver.findElement(By.cssSelector(".via-slds.slds-m-around--small.ng-scope"));
		if(verif.getText().toLowerCase().contains("servicios incluidos")) {
			a = true;
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Nominacion", "Ciclo1"},dataProvider = "DatosSalesNominacionNuevoPasaporteOfCom")
	public void TS128436_CRM_Movil_REPRO_Nominatividad_Presencial_DOC_Pasaporte_OfCom(String sLinea, String sPasaporte, String sNombre, String sApellido, String sSexo, String sPermanencia, String sFnac, String sEmail, String sProvincia, String sLocalidad, String sCalle, String sNumCa, String sCP) {
		imagen = "TS128436";
		detalles = null;
		detalles = imagen + " -Nominacion: " + sPasaporte + " -Linea: "+sLinea;
		boolean nominacion = false;
		SalesBase SB = new SalesBase(driver);
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		driver.findElement(By.id("PhoneNumber")).sendKeys(sLinea);
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep(5000);
		driver.findElement(By.cssSelector(".slds-button.slds-button--icon.slds-m-right--x-small.ng-scope")).click();
		sleep(2000);
		WebElement botonNominar = null;
		for (WebElement x : driver.findElements(By.cssSelector(".slds-hint-parent.ng-scope"))) {
			if (x.getText().toLowerCase().contains("plan con tarjeta"))
				botonNominar = x;
		}
		for (WebElement x : botonNominar.findElements(By.tagName("td"))) {
			if (x.getAttribute("data-label").equals("actions"))
				botonNominar = x;
		}
		botonNominar.findElement(By.tagName("a")).click();
		sleep(5000);
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact2("Pasaporte", sPasaporte, "Masculino");
		contact.Llenar_Contacto(sNombre, sApellido, sFnac);
		driver.findElement(By.id("PermanencyDueDate")).sendKeys(sPermanencia);
		driver.findElement(By.id("Contact_nextBtn")).click();
		sleep(10000);
		contact.tipoValidacion("documento");
		File directory = new File("Dni.jpg");
		contact.subirArchivo(new File(directory.getAbsolutePath()).toString(), "si");
		sleep(7000);
		BasePage bp = new BasePage(driver);
		bp.setSimpleDropdown(driver.findElement(By.id("ImpositiveCondition")), "IVA Consumidor Final");
		SB.Crear_DomicilioLegal(sProvincia, sLocalidad, sCalle, "", sNumCa, "", "", sCP);
		sleep(38000);
		directory = new File("form.pdf");
		driver.findElement(By.id("UploadSignedForm")).sendKeys(new File(directory.getAbsolutePath()).toString());
		driver.findElement(By.id("PDFForm_nextBtn")).click();
		sleep(30000);
		CBS_Mattu invoSer = new CBS_Mattu();
		invoSer.ValidarInfoCuenta(sLinea, sNombre,sApellido, "Plan con Tarjeta Repro");
		for (WebElement x : driver.findElement(By.id("NominacionExitosa")).findElements(By.tagName("p"))) {
			if (x.getText().toLowerCase().contains("nominaci\u00f3n exitosa!"))
				nominacion = true;
		}
		Assert.assertTrue(nominacion);
		driver.findElement(By.id("FinishProcess_nextBtn")).click();
		sleep(3000);
		invoSer.ValidarInfoCuenta(sLinea, sNombre,sApellido, "Plan con Tarjeta Repro");
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Nominacion", "Ciclo1"}, dataProvider = "DatosNoNominaNuevoEdadOfCom")
	public void TS130071_CRM_Movil_REPRO_No_Nominatividad_Presencial_DOC_Edad_OfCom(String sLinea, String sDni, String sSexo,String sFnac) {
		imagen = "TS130071";
		detalles = null;
		detalles = imagen + "No nominatividad- dni: "+sDni+" -Linea: "+sLinea;
		sleep(2000);
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		driver.findElement(By.id("PhoneNumber")).sendKeys(sLinea);
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep(5000);
		driver.findElement(By.cssSelector(".slds-button.slds-button--icon.slds-m-right--x-small.ng-scope")).click();
		sleep(2000);
		WebElement botonNominar = null;
		for (WebElement x : driver.findElements(By.cssSelector(".slds-hint-parent.ng-scope"))) {
			if (x.getText().toLowerCase().contains("plan con tarjeta"))
				botonNominar = x;
		}
		for (WebElement x : botonNominar.findElements(By.tagName("td"))) {
			if (x.getAttribute("data-label").equals("actions"))
				botonNominar = x;
		}
		botonNominar.findElement(By.tagName("a")).click();
		sleep(5000);
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact2("DNI", sDni, sSexo);
		driver.findElement(By.id("Birthdate")).sendKeys(sFnac);
		sleep(3000);
		WebElement error = driver.findElement(By.cssSelector(".message.description.ng-binding.ng-scope"));
		Assert.assertTrue(error.getText().toLowerCase().contains("fecha de nacimiento inv\u00e1lida"));
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Vista360", "Ciclo2"}, dataProvider = "CuentaVista360")
	public void TS134495_CRM_Movil_Prepago_Vista_360_Distribucion_de_paneles_Informacion_del_cliente_FAN_Front_OOCC(String sDNI, String sNombre) {
		imagen = "TS134495";
		detalles = null;
		detalles = imagen + "-Vista 360 - DNI: "+sDNI+ " - Nombre: "+sNombre;
		
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("profile-box")));
		WebElement nombre = driver.findElement(By.cssSelector(".slds-text-heading_large.title-card"));
		WebElement dni = null;
		for (WebElement x : driver.findElements(By.cssSelector(".slds-text-body_regular.account-detail-content"))) {
			if (x.getText().toLowerCase().contains(sDNI))
				dni = x;
		}
		Assert.assertTrue(nombre.getText().contains(sNombre) && dni.getText().contains(sDNI));
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Vista360", "Ciclo2"}, dataProvider = "CuentaVista360")
	public void TS134745_CRM_Movil_Prepago_Vista_360_Producto_Activo_del_cliente_Datos_FAN_Front_OOCC(String sDNI, String sNombre) {
		imagen = "TS134745";
		detalles = null;
		detalles = imagen + " - Vista 360 - DNI: "+sDNI+" - Nombre: "+sNombre;
		boolean creditoRecarga = false, creditoPromocional = false, estado = false, internetDisponible = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		for (WebElement x : driver.findElements(By.cssSelector(".slds-text-body_regular.detail-label"))) {
			if (x.getText().toLowerCase().contains("cr\u00e9dito recarga"))
				creditoRecarga = true;
			if (x.getText().toLowerCase().contains("cr\u00e9dito promocional"))
				creditoPromocional = true;
			if (x.getText().toLowerCase().contains("estado"))
				estado = true;
			if (x.getText().toLowerCase().contains("internet disponible"))
				internetDisponible = true;
		}
		Assert.assertTrue(creditoRecarga && creditoPromocional && estado && internetDisponible);
	}
	@Test (groups = {"GestionesPerfilOficina","Vista360","E2E", "Ciclo1"}, dataProvider="RenovacionCuotaConSaldo")
	public void TS134380_CRM_Movil_Prepago_Vista_360_Mis_Servicios_Visualizacion_del_estado_de_los_Productos_activos_FAN_Front_OOCC(String sDNI, String sLinea){
		imagen = "TS134380";
		//Check all
		detalles = null;
		detalles = imagen + " - Vista 360 - DNI: "+sDNI+" - Linea: "+sLinea;
		CustomerCare cCC = new CustomerCare(driver);
		BasePage cambioFrameByID=new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(25000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		cCC.irAProductosyServicios();
		driver.switchTo().frame(cambioFrame(driver, By.className("ext-strict")));
		sleep(8000);
		boolean a = false;
		List <WebElement> pp = driver.findElements((By.className("slds-text-heading_mediumtitle")));
			for(WebElement p : pp){
				if(p.getText().contains("Plan Prepago")){
					p.isDisplayed();
					a = true;
				}
			}
	}
	
	@Test (groups = {"GestionesPerfilOficina","Vista360","E2E", "Ciclo2"}, dataProvider = "CuentaVista360")
	public void TS134496_CRM_Movil_Prepago_Vista_360_Distribucion_de_paneles_Perfil_FAN_Front_OOCC(String sDNI, String sNombre) {
		imagen = "TS134496";
		detalles = null;
		detalles = imagen + " - Vista 360 - DNI: "+sDNI+" - Nombre: "+sNombre;
		boolean cuenta = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		sleep(3000);
		driver.switchTo().defaultContent();
		List<WebElement> pestanas = driver.findElements(By.className("x-tab-strip-closable"));
		pestanas.addAll(driver.findElements(By.cssSelector(".x-tab-strip-closable.x-tab-strip-active")));
		for (WebElement x : pestanas) {
			if (x.getText().equalsIgnoreCase(sNombre))
				cuenta = true;
		}
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".console-card.active")));
		Assert.assertTrue(cuenta && driver.findElement(By.cssSelector(".console-card.active")).isDisplayed());
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Actualizar Datos", "E2E", "Ciclo3"},  dataProvider = "CuentaModificacionDeDatos")
	public void TS134834_CRM_Movil_REPRO_Modificacion_de_datos_Actualizar_los_datos_del_cliente_completos_FAN_Front_OOCC(String sDNI, String sLinea) {
		imagen = "TS134834";
		detalles = null;
		detalles = imagen + " -ActualizarDatos - DNI: " + sDNI+ " - Linea: "+sLinea;
		String nuevoNombre = "Otro";
		String nuevoApellido = "Apellido";
		String nuevoNacimiento = "10/10/1982";
		String nuevoMail = "maildetest@gmail.com";
		String nuevoPhone = "3574409239";
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("profile-box")));
		driver.findElements(By.className("profile-edit")).get(0).click();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("DocumentNumber")));
		String nombre = driver.findElement(By.id("FirstName")).getAttribute("value");
		String apellido = driver.findElement(By.id("LastName")).getAttribute("value");
		String fechaNacimiento = driver.findElement(By.id("Birthdate")).getAttribute("value");
		String mail = driver.findElement(By.id("Email")).getAttribute("value");
		String phone = driver.findElement(By.id("MobilePhone")).getAttribute("value");
		driver.findElement(By.id("FirstName")).clear();
		driver.findElement(By.id("FirstName")).sendKeys(nuevoNombre);
		driver.findElement(By.id("LastName")).clear();
		driver.findElement(By.id("LastName")).sendKeys(nuevoApellido);
		driver.findElement(By.id("Birthdate")).clear();
		driver.findElement(By.id("Birthdate")).sendKeys(nuevoNacimiento);
		driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys(nuevoMail);
		driver.findElement(By.id("MobilePhone")).clear();
		driver.findElement(By.id("MobilePhone")).sendKeys(nuevoPhone);
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
		sleep(10000);
		Assert.assertTrue(driver.findElement(By.className("ta-care-omniscript-done")).findElement(By.className("ng-binding")).getText().equalsIgnoreCase("Las modificaciones se realizaron con \u00e9xito!"));
		String orden = driver.findElement(By.cssSelector(".vlc-slds-inline-control__label.ng-binding")).getText();
		orden = orden.substring(orden.length()-9, orden.length()-1);
		detalles +="-Orden:"+orden;	
		mk.closeActiveTab();
		CBS cCBS = new CBS();
		CBS_Mattu cCBSM = new CBS_Mattu();
		assertTrue(cCBS.ObtenerValorResponse(cCBSM.Servicio_QueryCustomerInfo(sLinea), "bcc:Email").equals(nuevoMail));
		assertTrue(cCBS.ObtenerValorResponse(cCBSM.Servicio_QueryCustomerInfo(sLinea), "bcc:FirstName").equalsIgnoreCase(nuevoNombre));
		assertTrue(cCBS.ObtenerValorResponse(cCBSM.Servicio_QueryCustomerInfo(sLinea), "bcc:LastName").equalsIgnoreCase(nuevoApellido));
		assertTrue(cCBS.ObtenerValorResponse(cCBSM.Servicio_QueryCustomerInfo(sLinea), "bcc:Birthday").contains("19821010"));
		driver.switchTo().frame(cambioFrame(driver, By.className("profile-box")));
		driver.findElements(By.className("profile-edit")).get(0).click();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("DocumentNumber")));
		Assert.assertTrue(driver.findElement(By.id("FirstName")).getAttribute("value").equals(nuevoNombre));
		Assert.assertTrue(driver.findElement(By.id("LastName")).getAttribute("value").equals(nuevoApellido));
		Assert.assertTrue(driver.findElement(By.id("Birthdate")).getAttribute("value").equals(nuevoNacimiento));
		Assert.assertTrue(driver.findElement(By.id("Email")).getAttribute("value").equals(nuevoMail));
		Assert.assertTrue(driver.findElement(By.id("MobilePhone")).getAttribute("value").equals(nuevoPhone));
		Assert.assertTrue(driver.findElement(By.id("DocumentType")).getAttribute("disabled").equals("true"));
		driver.findElement(By.id("FirstName")).clear();
		driver.findElement(By.id("FirstName")).sendKeys(nombre);
		driver.findElement(By.id("LastName")).clear();
		driver.findElement(By.id("LastName")).sendKeys(apellido);
		driver.findElement(By.id("Birthdate")).clear();
		driver.findElement(By.id("Birthdate")).sendKeys(fechaNacimiento);
		driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys(mail);
		driver.findElement(By.id("MobilePhone")).clear();
		driver.findElement(By.id("MobilePhone")).sendKeys(phone);
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
		sleep(8000);
		Assert.assertTrue(driver.findElement(By.className("ta-care-omniscript-done")).getText().contains("Las modificaciones se realizaron con \u00e9xito"));
		orden = driver.findElement(By.cssSelector(".vlc-slds-inline-control__label.ng-binding")).getText();
		orden = orden.substring(orden.length()-9, orden.length()-1);
		detalles +="-Orden:"+orden;	
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Actualizar Datos", "E2E", "Ciclo3"},  dataProvider = "CuentaModificacionDeDatos")
	public void TS129335_CRM_Movil_REPRO_Modificacion_de_datos_Actualizar_datos_campo_Correo_Electronico_Cliente_FAN_Front_OOCC(String sDNI, String sLinea) {
		imagen = "TS129335";
		detalles = null;
		detalles = imagen + " -ActualizarDatos - DNI: "+sDNI+" - Linea: "+sLinea;
		String nuevoMail = "maildetest@gmail.com";
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("profile-box")));
		driver.findElements(By.className("profile-edit")).get(0).click();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("DocumentNumber")));
		String mail = driver.findElement(By.id("Email")).getAttribute("value");
		driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys(nuevoMail);
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
		sleep(10000);
		Assert.assertTrue(driver.findElement(By.className("ta-care-omniscript-done")).findElement(By.className("ng-binding")).getText().equalsIgnoreCase("Las modificaciones se realizaron con \u00e9xito!"));
		String orden = driver.findElement(By.cssSelector(".vlc-slds-inline-control__label.ng-binding")).getText();
		orden = orden.substring(orden.length()-9, orden.length()-1);
		detalles +="-Orden:"+orden;	
		mk.closeActiveTab();
		CBS cCBS = new CBS();
		CBS_Mattu cCBSM = new CBS_Mattu();
		assertTrue(cCBS.ObtenerValorResponse(cCBSM.Servicio_QueryCustomerInfo(sLinea), "bcc:Email").equals(nuevoMail));
		driver.switchTo().frame(cambioFrame(driver, By.className("profile-box")));
		driver.findElements(By.className("profile-edit")).get(0).click();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("DocumentNumber")));
		Assert.assertTrue(driver.findElement(By.id("Email")).getAttribute("value").equals(nuevoMail));
		driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys(mail);
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
		sleep(8000);
		Assert.assertTrue(driver.findElement(By.className("ta-care-omniscript-done")).getText().contains("Las modificaciones se realizaron con \u00e9xito"));
		orden = driver.findElement(By.cssSelector(".vlc-slds-inline-control__label.ng-binding")).getText();
		orden = orden.substring(orden.length()-9, orden.length()-1);
		detalles +="-Orden:"+orden;	
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Actualizar Datos", "E2E", "Ciclo3"}, dataProvider = "CuentaModificacionDeDatos")
	public void TS129329_CRM_Movil_REPRO_Modificacion_de_datos_No_Permite_Actualizar_datos_campo_DNI_CUIT_Cliente_FAN_Front_OOCC(String sDNI, String sLinea) {
		imagen = "TS129329";
		detalles = null;
		detalles = imagen+"- Modificacion de datos No modifica - DNI: "+sDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("profile-box")));
		driver.findElements(By.className("profile-edit")).get(0).click();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("DocumentNumber")));
		Assert.assertTrue(driver.findElement(By.id("DocumentType")).getAttribute("disabled").equals("true"));
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Actualizar Datos", "E2E", "Ciclo3"}, dataProvider = "CuentaModificacionDeDatos")
	public void TS103660_CRM_Movil_REPRO_No_Actualizar_datos_Cliente_FAN_Front_OOCC(String sDNI, String sLinea) {
		imagen = "TS103660";
		detalles = null;
		detalles = imagen+"-Modificacion de datos No modifica - DNI: "+sDNI;
		boolean cancelar = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("profile-box")));
		driver.findElements(By.className("profile-edit")).get(0).click();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("DocumentNumber")));
		if (driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).isDisplayed()) {
			driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).click();
			sleep(3000);
			driver.findElement(By.cssSelector(".slds-button.slds-button--neutral.ng-binding")).click();
			cancelar = true;
		}
		Assert.assertTrue(cancelar);
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Actualizar Datos", "E2E", "Ciclo3"}, dataProvider = "CuentaModificacionDeDatos")
	public void TS103659_CRM_Movil_REPRO_Modificacion_de_datos_Actualizar_datos_Cliente_FAN_Front_OOCC(String sDNI, String sLinea) {
		imagen = "TS103659";
		detalles = null;
		detalles = imagen + " -ActualizarDatos - DNI: " + sDNI;
		String nuevoMail = "maildetest@gmail.com";
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("profile-box")));
		driver.findElements(By.className("profile-edit")).get(0).click();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("DocumentNumber")));
		String mail = driver.findElement(By.id("Email")).getAttribute("value");
		driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys(nuevoMail);
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
		sleep(10000);
		Assert.assertTrue(driver.findElement(By.className("ta-care-omniscript-done")).findElement(By.className("ng-binding")).getText().equalsIgnoreCase("Las modificaciones se realizaron con \u00e9xito!"));
		String orden = driver.findElement(By.cssSelector(".vlc-slds-inline-control__label.ng-binding")).getText();
		orden = orden.substring(orden.length()-9, orden.length()-1);
		detalles +="-Orden:"+orden;	
		mk.closeActiveTab();
		CBS cCBS = new CBS();
		CBS_Mattu cCBSM = new CBS_Mattu();
		assertTrue(cCBS.ObtenerValorResponse(cCBSM.Servicio_QueryCustomerInfo(sLinea), "bcc:Email").equals(nuevoMail));
		driver.switchTo().frame(cambioFrame(driver, By.className("profile-box")));
		driver.findElements(By.className("profile-edit")).get(0).click();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("DocumentNumber")));
		Assert.assertTrue(driver.findElement(By.id("Email")).getAttribute("value").equals(nuevoMail));
		driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys(mail);
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
		sleep(8000);
		Assert.assertTrue(driver.findElement(By.className("ta-care-omniscript-done")).getText().contains("Las modificaciones se realizaron con \u00e9xito"));
		orden = driver.findElement(By.cssSelector(".vlc-slds-inline-control__label.ng-binding")).getText();
		orden = orden.substring(orden.length()-9, orden.length()-1);
		detalles +="-Orden:"+orden;	
		
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Actualizar Datos", "E2E", "Ciclo3"}, dataProvider = "CuentaModificacionDeDatos")
	public void TS84237_CRM_Movil_REPRO_Modificacion_de_datos_Cliente_FAN_Front_OOCC(String sDNI, String sLinea) {
		imagen = "TS84237";
		detalles = null;
		detalles = imagen + " -ActualizarDatos - DNI: " + sDNI;
		String nuevoNombre = "Otro";
		String nuevoApellido = "Apellido";
		String nuevoNacimiento = "10/10/1982";
		String nuevoMail = "maildetest@gmail.com";
		String nuevoPhone = "3574409239";
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("profile-box")));
		driver.findElements(By.className("profile-edit")).get(0).click();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("DocumentNumber")));
		String nombre = driver.findElement(By.id("FirstName")).getAttribute("value");
		String apellido = driver.findElement(By.id("LastName")).getAttribute("value");
		String fechaNacimiento = driver.findElement(By.id("Birthdate")).getAttribute("value");
		String mail = driver.findElement(By.id("Email")).getAttribute("value");
		String phone = driver.findElement(By.id("MobilePhone")).getAttribute("value");
		driver.findElement(By.id("FirstName")).clear();
		driver.findElement(By.id("FirstName")).sendKeys(nuevoNombre);
		driver.findElement(By.id("LastName")).clear();
		driver.findElement(By.id("LastName")).sendKeys(nuevoApellido);
		driver.findElement(By.id("Birthdate")).clear();
		driver.findElement(By.id("Birthdate")).sendKeys(nuevoNacimiento);
		driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys(nuevoMail);
		driver.findElement(By.id("MobilePhone")).clear();
		driver.findElement(By.id("MobilePhone")).sendKeys(nuevoPhone);
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
		sleep(10000);
		Assert.assertTrue(driver.findElement(By.className("ta-care-omniscript-done")).findElement(By.className("ng-binding")).getText().equalsIgnoreCase("Las modificaciones se realizaron con \u00e9xito!"));
		String orden = driver.findElement(By.cssSelector(".vlc-slds-inline-control__label.ng-binding")).getText();
		orden = orden.substring(orden.length()-9, orden.length()-1);
		detalles +="-Orden:"+orden;	
		mk.closeActiveTab();
		CBS cCBS = new CBS();
		CBS_Mattu cCBSM = new CBS_Mattu();
		assertTrue(cCBS.ObtenerValorResponse(cCBSM.Servicio_QueryCustomerInfo(sLinea), "bcc:Email").equals(nuevoMail));
		assertTrue(cCBS.ObtenerValorResponse(cCBSM.Servicio_QueryCustomerInfo(sLinea), "bcc:FirstName").equalsIgnoreCase(nuevoNombre));
		assertTrue(cCBS.ObtenerValorResponse(cCBSM.Servicio_QueryCustomerInfo(sLinea), "bcc:LastName").equalsIgnoreCase(nuevoApellido));
		assertTrue(cCBS.ObtenerValorResponse(cCBSM.Servicio_QueryCustomerInfo(sLinea), "bcc:Birthday").contains("19821010"));
		driver.switchTo().frame(cambioFrame(driver, By.className("profile-box")));
		driver.findElements(By.className("profile-edit")).get(0).click();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("DocumentNumber")));
		Assert.assertTrue(driver.findElement(By.id("FirstName")).getAttribute("value").equals(nuevoNombre));
		Assert.assertTrue(driver.findElement(By.id("LastName")).getAttribute("value").equals(nuevoApellido));
		Assert.assertTrue(driver.findElement(By.id("Birthdate")).getAttribute("value").equals(nuevoNacimiento));
		Assert.assertTrue(driver.findElement(By.id("Email")).getAttribute("value").equals(nuevoMail));
		Assert.assertTrue(driver.findElement(By.id("MobilePhone")).getAttribute("value").equals(nuevoPhone));
		Assert.assertTrue(driver.findElement(By.id("DocumentType")).getAttribute("disabled").equals("true"));
		driver.findElement(By.id("FirstName")).clear();
		driver.findElement(By.id("FirstName")).sendKeys(nombre);
		driver.findElement(By.id("LastName")).clear();
		driver.findElement(By.id("LastName")).sendKeys(apellido);
		driver.findElement(By.id("Birthdate")).clear();
		driver.findElement(By.id("Birthdate")).sendKeys(fechaNacimiento);
		driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys(mail);
		driver.findElement(By.id("MobilePhone")).clear();
		driver.findElement(By.id("MobilePhone")).sendKeys(phone);
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
		sleep(8000);
		Assert.assertTrue(driver.findElement(By.className("ta-care-omniscript-done")).getText().contains("Las modificaciones se realizaron con \u00e9xito"));
		orden = driver.findElement(By.cssSelector(".vlc-slds-inline-control__label.ng-binding")).getText();
		orden = orden.substring(orden.length()-9, orden.length()-1);
		detalles +="-Orden:"+orden;	
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Historial de Recargas", "Ciclo2"}, dataProvider = "RecargasHistorias")
	public void TS_134787_CRM_Movil_Prepago_Historial_de_Recargas_Consultar_detalle_de_Recargas_con_Beneficios_FAN_Front_OOCC(String sDNI) {
		imagen = "TS134787";
		detalles = null;
		detalles = imagen + " - Historial de recargas - DNI:" + sDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(10000);
		cc.irAHistoriales();
		WebElement historialDeRecargas = null;
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-button.slds-button_brand")));
		for (WebElement x : driver.findElements(By.className("slds-card"))) {
			if (x.getText().toLowerCase().contains("historial de recargas"))
				historialDeRecargas = x;
		}
		historialDeRecargas.findElement(By.cssSelector(".slds-button.slds-button_brand")).click();
		sleep(7000);
		driver.switchTo().frame(cambioFrame(driver, By.id("text-input-03")));
		driver.findElement(By.id("text-input-03")).click();
		driver.findElement(By.xpath("//*[text() = 'Todos']")).click();
		driver.findElement(By.id("text-input-04")).click();
		driver.findElement(By.xpath("//*[text() = 'Con Beneficios']")).click();
		if (driver.findElement(By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small")).isDisplayed()) {
			driver.findElement(By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small")).click();
			Assert.assertTrue(true);
		} else
			Assert.assertTrue(false);
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Historial de Recargas", "Ciclo2"}, dataProvider = "RecargasHistorias")
	public void TS_134788_CRM_Movil_Prepago_Historial_de_Recargas_Consultar_detalle_de_Recargas_Sin_Beneficios_FAN_Front_OOCC(String sDNI){
		imagen = "TS134788";
		detalles = null;
		detalles = imagen + "- Historial de recargas - DNI: "+sDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(10000);
		cc.irAHistoriales();
		WebElement historialDeRecargas = null;
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-button.slds-button_brand")));
		for (WebElement x : driver.findElements(By.className("slds-card"))) {
			if (x.getText().toLowerCase().contains("historial de recargas"))
				historialDeRecargas = x;
		}
		historialDeRecargas.findElement(By.cssSelector(".slds-button.slds-button_brand")).click();
		sleep(7000);
		driver.switchTo().frame(cambioFrame(driver, By.id("text-input-03")));
		driver.findElement(By.id("text-input-03")).click();
		driver.findElement(By.xpath("//*[text() = 'Todos']")).click();
		driver.findElement(By.id("text-input-04")).click();
		driver.findElement(By.xpath("//*[text() = 'Sin Beneficios']")).click();
		if (driver.findElement(By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small")).isDisplayed()) {
			driver.findElement(By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small")).click();
			Assert.assertTrue(true);
		} else
			Assert.assertTrue(false);
	}
	
	
	@Test (groups = {"GestionesPerfilOficina","Historial de Reacargas","E2E", "Ciclo1"},  dataProvider = "CuentaModificacionDeDatos")
	public void TS135468_CRM_Movil_Prepago_Historial_de_Packs_Nombre_del_Pack_TODOS_FAN_Front_OOCC(String sDNI, String sLinea){
		imagen = "TS135468";
		detalles = null;
		detalles = imagen + " - Historial de packs - DNI: " + sDNI+ " - Linea: "+sLinea;
		boolean enc = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(20000);
		CustomerCare cc = new CustomerCare(driver);
		cc.irAHistoriales();
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-card.slds-m-around--small.ta-fan-slds")));
		List <WebElement> historiales = driver.findElements(By.className("slds-card"));
		for (WebElement UnH: historiales) {
			System.out.println(UnH.findElement(By.cssSelector(".slds-card__header.slds-grid")).getText());
			if(UnH.findElement(By.cssSelector(".slds-card__header.slds-grid")).getText().equals("Historial de packs")) {
				enc = true;
				driver.findElement(By.cssSelector(".slds-button.slds-button_brand")).click();
				sleep(5000);
				driver.findElement(By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small")).click();
				sleep(5000);
				//Assert.assertTrue(true);
				break;
			}
		}
		driver.findElement(By.id("text-input-id-1")).click();
		WebElement table = driver.findElement(By.cssSelector(".slds-datepicker.slds-dropdown.slds-dropdown--left"));
		sleep(3000);
		List<WebElement> tableRows = table.findElements(By.xpath("//tr//td"));
		for (WebElement cell : tableRows) {
			try {
				if (cell.getText().equals("25")) {
					cell.click();
				}
			} catch (Exception e) {}
		}
		driver.findElement(By.id("text-input-id-2")).click();
		WebElement table_2 = driver.findElement(By.cssSelector(".slds-datepicker.slds-dropdown.slds-dropdown--left"));
		sleep(3000);
		List<WebElement> tableRows_2 = table_2.findElements(By.xpath("//tr//td"));
		for (WebElement cell : tableRows_2) {
			try {
				if (cell.getText().equals("16")) {
					cell.click();
				}
			} catch (Exception e) {}
		}	
		sleep(3000);
		driver.findElement(By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small")).click();
		sleep(3000);
		driver.findElement(By.id("text-input-03")).click();
		sleep(2000);
		List<WebElement> todos = driver.findElement(By.cssSelector(".slds-dropdown__list.slds-dropdown--length-5")).findElements(By.tagName("li"));
			for(WebElement t : todos){
				if(t.getText().equals("Todos")){
					t.click();
				}	
			}
		Assert.assertTrue(enc);
	}

	@Test (groups = {"GestionesPerfilOficina","Historial de Reacargas","E2E", "Ciclo1"},  dataProvider = "CuentaAjustesREPRO")
	public void TS135361_CRM_Movil_Prepago_Otros_Historiales_Historial_de_ajustes_FAN_Front_OOCC_S138(String sDNI, String sLinea){
		imagen = "TS135361";
		detalles = null;
		detalles = imagen + "- Historial de packs - DNI: " + sDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(20000);
		CustomerCare cc = new CustomerCare(driver);
		cc.irAHistoriales();
		sleep(8000);WebElement historialDeRecargas = null;
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-button.slds-button_brand")));
		for (WebElement x : driver.findElements(By.className("slds-card"))) {
			if (x.getText().toLowerCase().contains("historial de ajustes")){
				historialDeRecargas = x;
				historialDeRecargas.findElement(By.cssSelector(".slds-button.slds-button_brand")).click();
			}
		}
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small")));
		driver.findElement(By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small")).click();
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-table.slds-table--bordered.slds-table--resizable-cols.via-slds-table-pinned-header")).isDisplayed());
	
	}
	
	@Test (groups = {"GestionesPerfilOficina", "VentaDePack", "Ciclo1"},priority=1, dataProvider = "VentaPacks")
	public void TS139727_CRM_Movil_REPRO_Venta_de_pack_50_min_y_50_SMS_x_7_dias_Factura_de_Venta_Efectivo_OOCC(String sDNI, String sLinea, String sventaPack) throws AWTException {
		imagen = "TS139727";
		detalles = null;
		detalles = imagen+"- Venta de pack - DNI: "+sDNI+" - Linea: "+sLinea;
		SalesBase sale = new SalesBase(driver);
		BasePage cambioFrameByID=new BasePage();
		CustomerCare cCC = new CustomerCare(driver);
		PagePerfilTelefonico pagePTelefo = new PagePerfilTelefonico(driver);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("SearchClientDocumentType")));	
		sleep(8000);
		sale.BuscarCuenta("DNI", sDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		detalles +="-Cuenta:"+accid;
		pagePTelefo.buscarAssert();
		cCC.seleccionarCardPornumeroLinea(sLinea, driver);
		pagePTelefo.comprarPack();
		pagePTelefo.closerightpanel();
		try {
			pagePTelefo.PackCombinado(sventaPack);
		}
		catch (Exception eE) {
			driver.navigate().refresh();
			sleep(10000);
			mk.closeTabByName(driver, "Comprar SMS");
			cCC.seleccionarCardPornumeroLinea(sLinea, driver);
			pagePTelefo.comprarPack("comprar sms");
			pagePTelefo.PackCombinado(sventaPack);
		}
		pagePTelefo.tipoDePago("en factura de venta");
		sleep(12000);
		pagePTelefo.getTipodepago().click();
		sleep(12000);
		String sOrden = cc.obtenerOrden2(driver);
		detalles+="-Orden:"+sOrden;
		pagePTelefo.getSimulaciondeFactura().click();
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals", "efectivo");
		sleep(8000);
		pagePTelefo.getMediodePago().click();
		sleep(45000);
		pagePTelefo.getOrdenSeRealizoConExito().click();// No se puede procesr (Ups, hay problemas para procesar su pago.)
		sleep(10000);
		String orden = cCC.obtenerTNyMonto2(driver, sOrden);
		detalles+="-Monto:"+orden.split("-")[1]+"-Prefactura:"+orden.split("-")[0];
		CBS_Mattu invoSer = new CBS_Mattu();
		Assert.assertTrue(invoSer.PagoEnCaja("1006", accid, "1001", orden.split("-")[1], orden.split("-")[0],driver));
		sleep(10000);
		driver.navigate().refresh();
		CBS cCBS = new CBS();
		CBS_Mattu cCBSM = new CBS_Mattu();
		Assert.assertTrue(cCBS.validarActivacionPack(cCBSM.Servicio_QueryFreeUnit(sLinea), sventaPack));
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".hasMotif.orderTab.detailPage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		WebElement tabla = driver.findElement(By.id("ep")).findElements(By.tagName("table")).get(1);
		String datos = tabla.findElements(By.tagName("tr")).get(4).findElements(By.tagName("td")).get(1).getText();
		Assert.assertTrue(datos.equalsIgnoreCase("activada")||datos.equalsIgnoreCase("activated"));	
		System.out.println("Operacion: Compra de Pack "+ "Order: " + sOrden + "Cuenta: "+ accid + "Fin");
	}
	
	@Test (groups = {"GestionPerfilOficina", "VentaDePack", "Ciclo1"}, dataProvider = "ventaX1Dia" )
	public void TS123163_CRM_Movil_REPRO_Venta_de_pack_1000_min_a_Personal_y_1000_SMS_x_1_dia_Factura_de_Venta_TC_Presencial(String sDNI, String sLinea, String sVentaPack, String sBanco, String sTarjeta, String sPromo, String sCuotas, String sNumTarjeta, String sVenceMes, String sVenceAno, String sCodSeg, String sTipoDNI, String sDNITarjeta, String sTitular) throws KeyManagementException, NoSuchAlgorithmException{
		imagen = "TS123163";
		detalles = null;
		detalles = imagen+"- Venta de pack - DNI: "+sDNI+" - Linea: "+sLinea;
		SalesBase sale = new SalesBase(driver);
		BasePage cambioFrameByID=new BasePage();
		CustomerCare cCC = new CustomerCare(driver);
		PagePerfilTelefonico pagePTelefo = new PagePerfilTelefonico(driver);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("SearchClientDocumentType")));	
		sleep(8000);
		sale.BuscarCuenta("DNI", sDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		detalles +="-Cuenta:"+accid;
		pagePTelefo.buscarAssert();
		cCC.seleccionarCardPornumeroLinea(sLinea, driver);
		//cCC.closerightpanel();
		pagePTelefo.comprarPack();
		pagePTelefo.PackCombinado(sVentaPack);
		pagePTelefo.tipoDePago("en factura de venta");
		sleep(12000);
		pagePTelefo.getTipodepago().click();
		sleep(12000);
		String sOrden = cc.obtenerOrden2(driver);
		detalles+="-Orden:"+sOrden;
		pagePTelefo.getSimulaciondeFactura().click();
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals", "tarjeta de credito");
		selectByText(driver.findElement(By.id("BankingEntity-0")), sBanco);
		selectByText(driver.findElement(By.id("CardBankingEntity-0")), sTarjeta);
		selectByText(driver.findElement(By.id("promotionsByCardsBank-0")), sPromo);
		selectByText(driver.findElement(By.id("Installment-0")), sCuotas);
		pagePTelefo.getMediodePago().click();
		sleep(45000);
		pagePTelefo.getOrdenSeRealizoConExito().click();// No se puede procesr (Ups, hay problemas para procesar su pago.)
		sleep(10000);
		String orden = cCC.obtenerTNyMonto2(driver, sOrden);
		detalles+="-Monto:"+orden.split("-")[1]+"-Prefactura:"+orden.split("-")[0];
		CBS_Mattu cCBSM = new CBS_Mattu();
		cCBSM.PagarTCPorServicio(sOrden);
		sleep(10000);
		driver.navigate().refresh();
		CBS cCBS = new CBS();
		Assert.assertTrue(cCBS.validarActivacionPack(cCBSM.Servicio_QueryFreeUnit(sLinea), sVentaPack));
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".hasMotif.orderTab.detailPage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		WebElement tabla = driver.findElement(By.id("ep")).findElements(By.tagName("table")).get(1);
		String datos = tabla.findElements(By.tagName("tr")).get(4).findElements(By.tagName("td")).get(1).getText();
		Assert.assertTrue(datos.equalsIgnoreCase("activada")||datos.equalsIgnoreCase("activated"));	
		System.out.println("Operacion: Compra de Pack "+ "Order: " + sOrden + "Cuenta: "+ accid + "Fin");
		//Blocked
	}
	
	@Test (groups = {"GestionesPerfilOficina","Historial de Reacargas","E2E", "Ciclo1"},  dataProvider = "CuentaModificacionDeDatos")
	public void TS135483_CRM_Movil_Prepago_Historial_de_Packs_Seleccion_de_Fechas_FAN_Front_OOCC(String sDNI, String sLinea){
		imagen = "TS135483";
		detalles = null;
		detalles = imagen + " - Historial de packs - DNI: " + sDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(20000);
		CustomerCare cc = new CustomerCare(driver);
		cc.irAHistoriales();
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-card.slds-m-around--small.ta-fan-slds")));
		driver.findElements(By.className("slds-card"));
		System.out.println(driver.findElement(By.cssSelector(".slds-card__header.slds-grid")).getText());
		driver.findElement(By.cssSelector(".slds-card__header.slds-grid")).getText().equals("Historial de packs");
		driver.findElement(By.cssSelector(".slds-button.slds-button_brand")).click();
		sleep(2000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-grid.slds-wrap.slds-grid--pull-padded.slds-m-around--medium.slds-p-around--medium.negotationsfilter")));
		driver.findElement(By.id("text-input-id-1")).click();
		WebElement table = driver.findElement(By.cssSelector(".slds-datepicker.slds-dropdown.slds-dropdown--left"));
		sleep(3000);
		List<WebElement> tableRows = table.findElements(By.xpath("//tr//td"));
		for (WebElement cell : tableRows) {
			try {
				if (cell.getText().equals("27")) {
					cell.click();
				}
			} catch (Exception e) {}
		}
		driver.findElement(By.id("text-input-id-2")).click();
		WebElement table_2 = driver.findElement(By.cssSelector(".slds-datepicker.slds-dropdown.slds-dropdown--left"));
		sleep(3000);
		List<WebElement> tableRows_2 = table_2.findElements(By.xpath("//tr//td"));
		for (WebElement cell : tableRows_2) {
			try {
				if (cell.getText().equals("10")) {
					cell.click();
				}
			} catch (Exception e) {}
		}
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-grid.slds-wrap.slds-grid--pull-padded.slds-m-around--medium.slds-p-around--medium.negotationsfilter")));
		sleep(5000);
		WebElement visu = driver.findElement(By.cssSelector(".slds-grid.slds-wrap.slds-grid--pull-padded.slds-m-around--medium.slds-p-around--medium.negotationsfilter"));
		Assert.assertTrue(visu.isDisplayed());
	}
	
	@Test (groups = {"GestionesPerfilOficina","NumerosAmigos","E2E", "Ciclo1"}, dataProvider="NumerosAmigosNoPersonalModificacion")
	public void TS100609_CRM_Movil_REPRO_FF_No_Modificacion_Presencial(String sDNI, String sLinea, String sNumeroVOZ, String sNumeroSMS) {
		imagen = "TS100609";
		detalles = null;
		detalles = imagen+"- Numeros Amigos - DNI: "+sDNI+" - Linea: "+sLinea;
		BasePage cambioFrame=new BasePage();
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI", sDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		detalles +="-Cuenta:"+accid;
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
				//Delete next line after TS100601 and TS100602 works
				Assert.assertFalse(wNumerosAmigos.get(0).findElement(By.tagName("input")).getText().isEmpty());
				wNumerosAmigos.get(0).findElement(By.tagName("input")).clear();
				wNumerosAmigos.get(0).findElement(By.tagName("input")).sendKeys(sNumeroVOZ);
				break;
			case 1:
				//Delete next line after TS100601 and TS100602 works
				Assert.assertFalse(wNumerosAmigos.get(1).findElement(By.tagName("input")).getText().isEmpty());
				wNumerosAmigos.get(1).findElement(By.tagName("input")).clear();
				wNumerosAmigos.get(1).findElement(By.tagName("input")).sendKeys(sNumeroSMS);
				break;
			default:
				Assert.assertTrue(false);
		}
		sleep(5000);
		
		switch (iIndice) {
			case 0:
				Assert.assertTrue(driver.findElements(By.cssSelector("[class='vlc-slds-error-block']")).get(0).findElement(By.cssSelector("[class='error']")).getText().equalsIgnoreCase("La linea no pertenece a Telecom, verifica el n\u00famero."));
				break;
			case 1:
				Assert.assertTrue(driver.findElements(By.cssSelector("[class='vlc-slds-error-block']")).get(1).findElement(By.cssSelector("[class='error']")).getText().equalsIgnoreCase("La linea no pertenece a Telecom, verifica el n\u00famero."));
				break;
			default:
				Assert.assertTrue(false);
		}
		//Verify when the page works
	}
	
	@Test (groups = {"GestionesPerfilOficina","NumerosAmigos","E2E", "Ciclo1"}, dataProvider="NumerosAmigosNoPersonalAlta")
	public void TS100607_CRM_Movil_REPRO_FF_No_Alta_Amigo_No_Personal_Presencial(String sDNI, String sLinea, String sNumeroVOZ, String sNumeroSMS) {
		imagen = "TS100607";
		detalles = null;
		detalles = imagen+"- Numeros Amigos - DNI:"+sDNI+" - Linea: "+sLinea;
		BasePage cambioFrame=new BasePage();
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI", sDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		detalles +="-Cuenta:"+accid;
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
		
		switch (iIndice) {
			case 0:
				Assert.assertTrue(driver.findElements(By.cssSelector("[class='vlc-slds-error-block']")).get(0).findElement(By.cssSelector("[class='error']")).getText().equalsIgnoreCase("La linea no pertenece a Telecom, verifica el n\u00famero."));
				break;
			case 1:
				Assert.assertTrue(driver.findElements(By.cssSelector("[class='vlc-slds-error-block']")).get(1).findElement(By.cssSelector("[class='error']")).getText().equalsIgnoreCase("La linea no pertenece a Telecom, verifica el n\u00famero."));
				break;
			default:
				Assert.assertTrue(false);
		}
	}
	
	@Test (groups = {"GestionesPerfilOficina","NumerosAmigos","E2E", "Ciclo1"}, dataProvider="NumerosAmigosNoPersonalBaja")
	public void TS100612_CRM_Movil_REPRO_FF_No_Baja_Presencial(String sDNI, String sLinea, String sNumeroVOZ, String sNumeroSMS) {
		imagen = "TS100612";
		detalles = null;
		detalles = imagen+"- Numeros Amigos - DNI:"+sDNI+" - Linea: "+sLinea;
		BasePage cambioFrame=new BasePage();
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI", sDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		detalles +="-Cuenta:"+accid;
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
				//Delete next line after TS100601 and TS100602 works
				Assert.assertFalse(wNumerosAmigos.get(0).findElement(By.tagName("input")).getText().isEmpty());
				wNumerosAmigos.get(0).findElement(By.tagName("input")).clear();
				break;
			case 1:
				//Delete next line after TS100601 and TS100602 works
				Assert.assertFalse(wNumerosAmigos.get(1).findElement(By.tagName("input")).getText().isEmpty());
				wNumerosAmigos.get(1).findElement(By.tagName("input")).clear();
				break;
			default:
				Assert.assertTrue(false);
		}
		sleep(5000);
		
		//Complete when the page works
		//Verify when the page works
	
	}

	@Test (groups = {"GestionesPerfilOficina","Vista 360","E2E", "Ciclo 1"}, dataProvider="CuentaModificacionDeDatos")
	public void TS134371_CRM_Movil_Prepago_Vista_360_Consulta_por_gestiones_Gestiones_abiertas_Plazo_No_vencido_Consulta_registrada_FAN_Front_OOCC(String sDNI){
		imagen = "TS134371";
		detalles = null;
		detalles = imagen+"- Vista 360 - DNI: "+sDNI;
		boolean gestion = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		sleep(3000);
		driver.findElement(By.className("card-top")).click();
		sleep(3000);
		buscarYClick(driver.findElements(By.className("slds-text-body_regular")), "equals", "gestiones");
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-grid.slds-wrap.slds-grid--pull-padded.slds-m-around--medium.slds-p-around--medium.negotationsfilter")));
		driver.findElement(By.id("text-input-id-1")).click();
		WebElement table = driver.findElement(By.cssSelector(".slds-datepicker.slds-dropdown.slds-dropdown--left"));
		sleep(3000);
		List<WebElement> tableRows = table.findElements(By.xpath("//tr//td"));
		for (WebElement cell : tableRows) {
			try {
				if (cell.getText().equals("26")) {
					cell.click();
				}
			} catch (Exception e) {}
		}
		driver.findElement(By.id("text-input-id-2")).click();
		WebElement table_2 = driver.findElement(By.cssSelector(".slds-datepicker.slds-dropdown.slds-dropdown--left"));
		sleep(3000);
		List<WebElement> tableRows_2 = table_2.findElements(By.xpath("//tr//td"));
		for (WebElement cell : tableRows_2) {
			try {
				if (cell.getText().equals("01")) {
					cell.click();
				}
			} catch (Exception e) {}
		}
		driver.switchTo().frame(cambioFrame(driver, By.id("text-input-03")));
		driver.findElement(By.id("text-input-03")).click();
		driver.findElement(By.xpath("//*[text() = 'Ordenes']")).click();
		sleep(5000);
		driver.findElement(By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small.secondaryFont")).click();
		sleep(5000);
		WebElement nroCaso = driver.findElement(By.cssSelector(".slds-table.slds-table--bordered.slds-table--resizable-cols.slds-table--fixed-layout.via-slds-table-pinned-header")).findElement(By.tagName("tbody")).findElement(By.tagName("tr"));
		nroCaso.findElements(By.tagName("td")).get(2).click();
		sleep(5000);
		WebElement estado = null;
		driver.switchTo().frame(cambioFrame(driver, By.name("ta_clone")));
		for (WebElement x : driver.findElements(By.className("detailList"))) {
			if (x.getText().toLowerCase().contains("n\u00famero de pedido"))
				estado = x;
		}
		for (WebElement x : estado.findElements(By.tagName("tr"))) {
			if (x.getText().toLowerCase().contains("estado"))
				estado = x;
		}
		if (estado.getText().toLowerCase().contains("activada") || (estado.getText().toLowerCase().contains("iniciada")))
			gestion = true;
		Assert.assertTrue(gestion);
	}

				
	@Test (groups = {"GestionesPerfilOficina", "Vista360", "E2E","ConsultaPorGestion", "Ciclo2"}, dataProvider = "CuentaModificacionDeDatos")
	public void TS134370_CRM_Movil_Prepago_Vista_360_Consulta_por_gestiones_Gestiones_no_registradas_FAN_Front_OOCC(String sDNI , String sLinea) {
		imagen = "TS134370";
		detalles = null;
		detalles = imagen+"- Vista 360 - DNI: "+sDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		buscarYClick(driver.findElements(By.className("slds-text-body_regular")), "equals", "gestiones");
		sleep(2000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-grid.slds-wrap.slds-grid--pull-padded.slds-m-around--medium.slds-p-around--medium.negotationsfilter")));
		driver.findElement(By.id("text-input-id-1")).click();
		WebElement table = driver.findElement(By.cssSelector(".slds-datepicker.slds-dropdown.slds-dropdown--left"));
		sleep(3000);
		List<WebElement> tableRows = table.findElements(By.xpath("//tr//td"));
		for (WebElement cell : tableRows) {
			try {
				if (cell.getText().equals("06")) {
					cell.click();
				}
			} catch (Exception e) {}
		}
		driver.findElement(By.id("text-input-id-2")).click();
		WebElement table_2 = driver.findElement(By.cssSelector(".slds-datepicker.slds-dropdown.slds-dropdown--left"));
		sleep(3000);
		List<WebElement> tableRows_2 = table_2.findElements(By.xpath("//tr//td"));
		for (WebElement cell : tableRows_2) {
			try {
				if (cell.getText().equals("31")) {
					cell.click();
				}
			} catch (Exception e) {}
		}
		sleep(3000);
		driver.findElement(By.id("text-input-id-2")).click();
		WebElement table_3 = driver.findElement(By.cssSelector(".slds-datepicker.slds-dropdown.slds-dropdown--left"));
		sleep(3000);
		List<WebElement> tableRows_3 = table_3.findElements(By.xpath("//tr//td"));
		for (WebElement cell : tableRows_3) {
			try {
				if (cell.getText().equals("06")) {
					cell.click();
				}
			} catch (Exception e) {}
		}
		driver.findElement(By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small.secondaryFont")).click();
		driver.findElement(By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small.secondaryFont")).click();
		sleep(3000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-table.slds-table--bordered.slds-table--resizable-cols.slds-table--fixed-layout.via-slds-table-pinned-header")));
		sleep(5000);
		boolean a = false;
		for(WebElement x : driver.findElements(By.cssSelector(".ng-pristine.ng-untouched.ng-valid.ng-empty"))) {
			if(x.getText().toLowerCase().equals("order")) {
				a = true;
			}
		}
		Assert.assertFalse(a);
	}
	
	@Test (groups = {"GestionesPerfilOficina", "RenovacionDeCuota", "E2E", "Ciclo1"}, dataProvider = "RenovacionCuotaSinSaldo")
	public void TS135512_CRM_Movil_REPRO_No_Renovacion_de_cuota_Pack_de_datos_Activo_Presencial(String sDNI, String sLinea) {
		imagen = "TS135512";
		detalles = null;
		detalles = imagen + "- No renovacion de cuota - DNI:" + sDNI+ "-Linea: "+sLinea;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(3000);
		cc.irAGestionEnCard("Renovacion de Datos");
		sleep(7000);
		driver.switchTo().frame(cambioFrame(driver, By.className("slds-checkbox--faux")));
		driver.findElements(By.className("slds-checkbox--faux")).get(2).click();
		driver.findElement(By.id("CombosDeMegas_nextBtn")).click();
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "contains", "descuento de saldo");
		driver.findElement(By.id("SetPaymentType_nextBtn")).click();
		sleep(5000);
		driver.findElement(By.id("alert-ok-button")).click();
		sleep(3000);
		WebElement msj = driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-radio-control.ng-scope.ng-dirty.ng-valid-parse.ng-valid.ng-valid-required"));
		msj = msj.findElement(By.className("vlc-slds-error-block"));
		Assert.assertTrue(msj.getText().contains("El Saldo No Es Suficiente Para Comprar El Pack"));
		Assert.assertTrue(false);//investigar si debe ser asi o si debe negar la activacion del pack si ya esta activo
	}
	
	@Test (groups = {"GestionesPerfilOficina", "Vista360", "Ciclo2"}, dataProvider = "CuentaVista360")
	public void TS134349_CRM_Movil_Prepago_Vista_360_Consulta_por_gestiones_Gestiones_abiertas_Plazo_vencido_Asistencia_registrada_FAN_Front_OOCC(String sDNI, String sNombre) {
		imagen = "TS134349";
		detalles = null;
		detalles = imagen + " - Vista 360 - DNI: "+sDNI+" - Nombre: "+sNombre;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(25000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		cc.irAGestiones();
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small.secondaryFont")));
		driver.findElement(By.id("text-input-03")).click();
		driver.findElement(By.xpath("//*[text() = 'Casos']")).click();
		driver.findElement(By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small.secondaryFont")).click();
		sleep(3000);
		WebElement nroCaso = driver.findElement(By.cssSelector(".slds-table.slds-table--bordered.slds-table--resizable-cols.slds-table--fixed-layout.via-slds-table-pinned-header")).findElement(By.tagName("tbody")).findElement(By.tagName("tr"));
		nroCaso.findElements(By.tagName("td")).get(2).click();
		System.out.println(nroCaso.getText());
		sleep(5000);
		WebElement fechaYHora = null;
			driver.switchTo().frame(cambioFrame(driver, By.name("close")));
		for (WebElement x : driver.findElements(By.className("pbSubsection"))) {
			if (x.getText().toLowerCase().contains("owned by me"))
				fechaYHora = x;
		}
		fechaYHora = fechaYHora.findElement(By.tagName("tbody"));
		for (WebElement x : fechaYHora.findElements(By.tagName("tr"))) {
			if (x.getText().toLowerCase().contains("fecha/hora de cierre"))
				fechaYHora = x;
		}
		Assert.assertTrue(fechaYHora.getText().contains("Fecha/Hora de cierre"));
		Assert.assertTrue(fechaYHora.findElements(By.tagName("td")).get(3).getText().matches("^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}$"));
	}
	
	@Test (groups = {"GestionesPerfilOficina", "ServicioTecnico","E2E", "Ciclo4"}, dataProvider = "serviciotecnicoC")
	public void TS121362_CRM_Movil_REPRO_Servicio_Tecnico_Realiza_configuraciones_de_equipos_Validacion_de_IMEI_Ok_Sin_Garantia_Sin_Muleto_Reparar_ahora_No_acepta_presupuesto_ofCom(String sDNI, String sIMEI, String sEmail, String sLinea, String sOpcion, String sOperacion, String sSintoma) throws InterruptedException {
		imagen = "TS121362";
		detalles = null;
		detalles = imagen + " -ServicioTecnico - DNI: " + sDNI;
		BasePage cambioFrameByID=new BasePage();
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(10000);
		searchAndClick(driver, "Servicio T\u00e9cnico");
		sleep(8000);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".imgItemContainer.ng-scope")));
		List<WebElement> clickOnButtons= driver.findElements(By.xpath(".//*[@class='borderOverlay']"));
		clickOnButtons.get(1).click();
		WebElement IMEI = driver.findElement(By.id("ImeiCode"));
		IMEI.click();
		IMEI.sendKeys(sIMEI);
		driver.findElement(By.id("ImeiInput_nextBtn")).click();
		sleep(15000);
		try {
			driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver,By.id("PopulateRepairs")));
			driver.findElement(By.cssSelector(".vlc-slds-remote-action__content.ng-scope")).findElement(By.xpath("//*[@id=\"PopulateRepairs\"]/div/p[3]"));
			List<WebElement> butt=driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope"));
			butt.get(1).click();
		}
		catch (Exception e) {
		Assert.assertTrue(false);
	}
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.className("vlc-control-wrapper")).getLocation().y+" )");
		driver.findElement(By.id("AlternativeEmail")).click();
		driver.findElement(By.id("AlternativeEmail")).sendKeys(sEmail);
		driver.findElement(By.id("AlternativePhone")).click();
		driver.findElement(By.id("AlternativePhone")).sendKeys(sLinea);
		sleep(8000);
		sOpcion=sOpcion.toLowerCase();
		boolean flag=false;
		for(WebElement opt:driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"))) {
			if(opt.getText().toLowerCase().contains(sOpcion)) {
				opt.click();
				flag=true;
				break;
			}
		}
		if(!flag) System.out.println("Opcion no disponible");
		sleep(8000);
		buscarYClick(driver.findElements(By.id("ClientInformation_nextBtn")),"equals", "continuar");
		sleep(8000);
		selectByText(driver.findElement(By.id("SelectOperationType")), sOperacion);
		sleep(8000);
		driver.findElement(By.className("vlc-control-wrapper")).click();
		List<WebElement> sint = driver.findElement(By.cssSelector(".slds-list--vertical.vlc-slds-list--vertical")).findElements(By.tagName("li"));
		for(WebElement sintoma : sint) {
			if(sintoma.getText().contains(sSintoma));
			sintoma.click();
			break;
		}
		sleep(8000);
		buscarYClick(driver.findElements(By.id("SymptomExplanation_nextBtn")), "equals", "continuar");
	}
	
	
	
	@Test (groups = {"GestionesPerfilOficina", "ServicioTecnico","E2E", "Ciclo4"}, dataProvider = "serviciotecnicoR")
	public void TS121372_CRM_Movil_REPRO_Servicio_Tecnico_Realiza_reparaciones_de_equipos_Busqueda_de_cliente_Reparacion_Sin_Muleto_Equipo_en_destruccion_total_Con_seguro_de_proteccion_total_No_se_pudo_realizar_la_reparacion_OfCom(String sDNI, String sIMEI, String sEmail, String sLinea, String sOpcion, String sOperacion, String sSintoma) throws InterruptedException {
		imagen = "TS121372";
		detalles = null;
		detalles = imagen + " -ServicioTecnico - DNI: " + sDNI+" - Linea: "+sLinea;
		BasePage cambioFrameByID=new BasePage();
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(10000);
		searchAndClick(driver, "Servicio T\u00e9cnico");
		sleep(8000);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".imgItemContainer.ng-scope")));
		List<WebElement> clickOnButtons= driver.findElements(By.xpath(".//*[@class='borderOverlay']"));
		clickOnButtons.get(1).click();
		WebElement IMEI = driver.findElement(By.id("ImeiCode"));
		IMEI.click();
		IMEI.sendKeys(sIMEI);
		driver.findElement(By.id("ImeiInput_nextBtn")).click();
		sleep(15000);
		try {
			driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver,By.id("PopulateRepairs")));
			driver.findElement(By.cssSelector(".vlc-slds-remote-action__content.ng-scope")).findElement(By.xpath("//*[@id=\"PopulateRepairs\"]/div/p[3]"));
			List<WebElement> butt=driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope"));
			butt.get(1).click();
		}
		catch (Exception e) {
	}
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.className("vlc-control-wrapper")).getLocation().y+" )");
		driver.findElement(By.id("AlternativeEmail")).click();
		driver.findElement(By.id("AlternativeEmail")).sendKeys(sEmail);
		driver.findElement(By.id("AlternativePhone")).click();
		driver.findElement(By.id("AlternativePhone")).sendKeys(sLinea);
		sleep(8000);
		sOpcion=sOpcion.toLowerCase();
		boolean flag=false;
		for(WebElement opt:driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"))) {
			if(opt.getText().toLowerCase().contains(sOpcion)) {
				opt.click();
				flag=true;
				break;
			}
		}
		if(!flag) System.out.println("Opcion no disponible");
		sleep(5000);
		buscarYClick(driver.findElements(By.id("ClientInformation_nextBtn")),"equals", "continuar");
		sleep(8000);
		driver.findElement(By.cssSelector(".slds-form-element__control.slds-input-has-icon.slds-has-input-has-icon--right"));
		selectByText(driver.findElement(By.id("SelectOperationType")), sOperacion);
		sleep(8000);
		driver.findElement(By.id("SelectSymptomType")).click();
		sleep(5000);
		List<WebElement> sint = driver.findElement(By.cssSelector(".slds-list--vertical.vlc-slds-list--vertical")).findElements(By.tagName("li"));
		for(WebElement sintoma : sint) {
			System.out.println(sintoma.getText());
			if(sintoma.getText().equalsIgnoreCase(sSintoma)) {
				System.out.println("entreeeeeee");
				sintoma.click();
				break;
			}
	}
		sleep(8000);
		buscarYClick(driver.findElements(By.id("SymptomExplanation_nextBtn")), "equals", "continuar");
		sleep(8000);
		boolean precio = false;
		List<WebElement> soluciones = driver.findElement(By.cssSelector(".slds-table.slds-table--bordered.slds-table--cell-buffer.techCare-PriceListSelection.ng-scope")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		for(WebElement sol: soluciones) {
			if(sol.getText().contains("$0")) {
				sol.findElement(By.tagName("td")).findElement(By.tagName("td")).getText();
				System.out.println(sol);
				precio = true;
				}
			}
					
		for(WebElement sol:soluciones) {
			if(sol.getText().contains("Irreparable")) {
				System.out.println(sol.getText());
				sleep(8000);
				sol.findElement(By.tagName("td")).findElement(By.className("slds-checkbox")).click();
			}
		}
		sleep(8000);
		buscarYClick(driver.findElements(By.id("1stSolutionList_nextBtn")), "equals", "continuar");
		List<WebElement> costo = driver.findElement(By.cssSelector(".slds-table.slds-table--bordered.slds-table--cell-bufferslds-m-bottom--x-large.ta-table-list.ng-scope")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		for(WebElement cost:costo) {
			if(cost.getText().contains("$0"));{
				System.out.println(cost.getText());
				precio = true;
				Assert.assertTrue(precio);
				}
			}
		
		sleep(8000);
		List<WebElement> presup = driver.findElement(By.className("slds-form-element__control")).findElement(By.tagName("label")).findElements(By.tagName("div"));
		for(WebElement opt:presup) {
			if(opt.getText().toLowerCase().contains("Si")) {
				System.out.println(opt.getText());
				opt.findElement(By.tagName("label")).findElement(By.id("RadioBudgetAcceptance")).click();
				break;
			}
		}
		buscarYClick(driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.TechCare-createOrder-Btn")), "equals", "crear orden");
		
	}
		
	@Test (groups = {"GestionesPerfilOficina", "DiagnosticoInconveniente","E2E", "Ciclo3"}, dataProvider = "Diagnostico")
	public void TS111871_CRM_Movil_REPRO_Diagnostico_SVA_Configuracion_Disponible_Presencial_SMS_Saliente_SMS_a_fijo_Geo_No_Ok_Desregistrar_OfCom(String sDNI, String sLinea) throws Exception  {
		imagen = "TS111871";
		detalles = null;
		detalles = imagen + " -ServicioTecnico - DNI: "+sDNI+" - Linea: "+sLinea;
		boolean desregistrar = false;
		CustomerCare cCC=new CustomerCare(driver);
		TechCare_Ola1 page=new TechCare_Ola1(driver);
		TechnicalCareCSRDiagnosticoPage tech = new TechnicalCareCSRDiagnosticoPage(driver);
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(5000);
		cCC.irAProductosyServicios();
		tech.verDetalles();
	    tech.clickDiagnosticarServicio("sms", "SMS Saliente", true);
	    tech.selectionInconvenient("SMS a fijo");
	    tech.continuar();
	    tech.seleccionarRespuesta("no");
	    buscarYClick(driver.findElements(By.id("KnowledgeBaseResults_nextBtn")), "equals", "continuar");
	    page.seleccionarPreguntaFinal("S\u00ed");
	    buscarYClick(driver.findElements(By.id("BalanceValidation_nextBtn")), "equals", "continuar");
	    tech.categoriaRed("Desregistrar");
	    Assert.assertTrue(desregistrar);
	}

	@Test(groups = {"Sales", "Nominacion","E2E","Ciclo1"}, dataProvider="DatosSalesNominacionPyRNuevoOfCom") 
	public void TS85099_CRM_Movil_REPRO_Nominatividad_Cliente_Existente_Presencial_Preguntas_Y_Respuestas_Ofcom(String sLinea, String sDni, String sNombre, String sApellido, String sSexo, String sFnac, String sEmail, String sProvincia, String sLocalidad, String sCalle, String sNumCa, String sCP) { 
		imagen = "TS85099-Nominacion"+sDni;
		detalles = null;
		detalles = imagen +"-Linea: "+sLinea;
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		SalesBase SB = new SalesBase(driver);
		driver.findElement(By.id("PhoneNumber")).sendKeys(sLinea);
		sleep(1500);
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep(10000);
		WebElement cli = driver.findElement(By.id("tab-scoped-1"));
		cli.findElement(By.tagName("tbody")).findElement(By.tagName("tr")).click();
		sleep(3000);
		List<WebElement> Lineas = driver.findElement(By.id("tab-scoped-1")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		for(WebElement UnaL: Lineas) {
			if(UnaL.getText().toLowerCase().contains("plan con tarjeta repro")||UnaL.getText().toLowerCase().contains("plan prepago nacional")) {
				UnaL.findElements(By.tagName("td")).get(6).findElement(By.tagName("svg")).click();
				System.out.println("Linea Encontrada");
				break;
			}
		}
		sleep(13000);
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact2("DNI", sDni, sSexo);
		contact.Llenar_Contacto(sNombre, sApellido, sFnac);
		try {contact.ingresarMail(sEmail, "si");}catch (org.openqa.selenium.ElementNotVisibleException ex1) {}
		contact.tipoValidacion("preguntas y respuestas");
		sleep(5000);
		CustomerCare cCC = new CustomerCare(driver);
		cCC.obligarclick(driver.findElement(By.id("QAContactData_nextBtn")));
		sleep(5000);
		BasePage bp = new BasePage(driver);
		bp.setSimpleDropdown(driver.findElement(By.id("ImpositiveCondition")), "IVA Consumidor Final");
		SB.Crear_DomicilioLegal(sProvincia, sLocalidad, sCalle, "", sNumCa, "", "", sCP);
		sleep(38000);
		CBS_Mattu invoSer = new CBS_Mattu();
		invoSer.ValidarInfoCuenta(sLinea, sNombre,sApellido, "Plan con Tarjeta Repro");
		List <WebElement> element = driver.findElement(By.id("NominacionExitosa")).findElements(By.tagName("p"));
		System.out.println("cont="+element.get(0).getText());
		boolean a = false;
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("nominaci\u00f3n exitosa!")) {
				a = true;
				System.out.println(x.getText());
			}
		}
		Assert.assertTrue(a);
		driver.findElement(By.id("FinishProcess_nextBtn")).click();
		sleep(3000);
		invoSer.ValidarInfoCuenta(sLinea, sNombre,sApellido, "Plan con Tarjeta Repro");
	}
	
	@Test (groups = {"GestionesPerfilOficina", "BaseDeConocimiento", "Ciclo3"}, dataProvider = "CuentaVista360")
	public void TS100978_CRM_REPRO_BDC_Customer_Care_Suspensiones_y_Rehabilitaciones_Perfil_OOCC_Acceso_a_base_de_conocimiento(String sDNI, String sNombre) {
		imagen = "TS100978";
		detalles = null;
		detalles = imagen + "-Base de Conocimiento - DNI: " +sDNI+ " - Nombre: "+sNombre;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		cc.irAGestion("suspensiones");
		driver.switchTo().defaultContent();
		buscarYClick(driver.findElements(By.className("x-btn-text")), "contains", "knowledge");
		driver.switchTo().frame(cambioFrame(driver, By.id("knowledge2HomePage_kbOneTab")));
		Assert.assertTrue(driver.findElement(By.id("knowledge2HomePage_kbOneTab")).isDisplayed());
	}
	
	@Test (groups = {"GestionesPerfilOficina", "BaseDeConocimiento", "Ciclo3"}, dataProvider = "CuentaVista360")
	public void TS125107_CRM_REPRO_BDC_Customer_Care_Problemas_con_recargas_Tarjetas_Prepagas_Verificar_acceso_a_BC(String sDNI, String sNombre) {
		imagen = "TS125107";
		detalles = null;
		detalles = imagen + "- Base Conocimiento - DNI: "+sDNI+ " - Nombre: "+sNombre;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(3000);
		cc.irAGestionEnCard("Problemas con Recargas");
		sleep(5000);
		driver.switchTo().defaultContent();
		buscarYClick(driver.findElements(By.className("x-btn-text")), "contains", "knowledge");
		driver.switchTo().frame(cambioFrame(driver, By.id("knowledge2HomePage_kbOneTab")));
		Assert.assertTrue(driver.findElement(By.id("knowledge2HomePage_kbOneTab")).isDisplayed());
	}
	
	@Test (groups = {"GestionesPerfilOficina", "BaseDeConocimiento", "Ciclo3"}, dataProvider = "CuentaVista360")
	public void TS130755_CRM_REPRO_BDC_Customer_Care_Problemas_con_Recargas_PerfilTelefonico_Articulo_de_Medios_de_Recargas(String sDNI, String sNombre) {
		imagen = "TS125107";
		detalles = null;
		detalles = imagen + "- Base de Conocimiento - DNI: "+sDNI+" - Nombre: "+sNombre;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(3000);
		cc.irAGestionEnCard("Problemas con Recargas");
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.className("vlc-slds-knowledge-list-item")));
		Assert.assertTrue(driver.findElement(By.className("vlc-slds-knowledge-list-item")).getText().contains("Problemas con recargas Online"));
	}
	
	@Test (groups = {"GestionPerfilOficina", "BasedeConocimiento", "Ciclo3"}, dataProvider = "BaseDeConocimiento")
	public void TS124899_CRM_REPRO_BDC_Technical_Care_CSR_Suscripciones_Base_de_Conocimiento(String sDNI, String sLinea) {
		imagen = "TS124899";
		detalles = null;
		detalles = imagen+"-Base de Conocimiento -DNI: "+sDNI+" -Linea: "+sLinea;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(3000);
		driver.switchTo().frame(cambioFrame(driver, By.className("community-flyout-actions-card")));
		Assert.assertTrue(driver.findElement(By.className("community-flyout-actions-card")).getText().contains("Suscripciones"));
		sleep(3000);
		cc.irAGestionEnCard("Suscripciones");
		sleep(7000);
		driver.switchTo().defaultContent();
		driver.findElement(By.id("scc_widget_knowledgeOne_button")).click();
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".zen-unit.zen-lastUnit.kb-center")));
		Assert.assertTrue(driver.findElement(By.cssSelector(".zen-unit.zen-lastUnit.kb-center")).isDisplayed());	
	}
	
	@Test (groups = {"GestionPerfilOficina", "BasedeConocimiento", "Ciclo3"}, dataProvider = "BaseDeConocimiento")
	public void TS125103_CRM_REPRO_BDC_Customer_Care_Suspensiones_y_Rehabilitaciones_Valoracion_positiva_de_un_articulo(String sDNI, String sLinea) {
		imagen = "TS125103";
		detalles = null;
		detalles = imagen+"- Base de Conocimiento - DNI: "+sDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().defaultContent();
		driver.findElement(By.id("scc_widget_knowledgeOne_button")).click();
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".zen-unit.zen-lastUnit.kb-center")));
		driver.findElement(By.id("knowledgeSearchInput_kbOneTab")).sendKeys("Suspensiones y Reconexiones - Requisitos");
		driver.findElement(By.cssSelector(".knowledgeSearchBoxButton.knowledgeSearchButton.leftColumn")).click();
		sleep(3000);
		driver.switchTo().frame(cambioFrame(driver, By.id("articleList_kbOneTab")));
		driver.findElement(By.xpath("//*[@id=\"kA0c0000000DD1B_kbOneTab\"]/div/p[1]/a")).click();
		sleep(3000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".ptBody.secondaryPalette.brandSecondaryBrd")));
		boolean a = false;
		for(WebElement x : driver.findElements(By.className("voteUDInlineUpCount"))) {
			if(x.getText().toLowerCase().contains("2")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = {"GestionPerfilOficina", "BasedeConocimiento", "Ciclo3"}, dataProvider = "BaseDeConocimiento")
	public void TS124900_CRM_REPRO_BDC_Technical_Care_CSR_Inconvenientes_con_Servicios_Varios_Base_de_Conocimiento(String sDNI, String sLinea) {
		imagen = "TS124900";
		detalles = null;
		detalles = imagen+"-Base de Conocimiento:"+sDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		cc.irAGestion("inconvenientes");
		sleep(5000);
		driver.switchTo().defaultContent();
		buscarYClick(driver.findElements(By.className("x-btn-text")), "contains", "knowledge");
		driver.switchTo().frame(cambioFrame(driver, By.id("knowledge2HomePage_kbOneTab")));
		Assert.assertTrue(driver.findElement(By.id("knowledge2HomePage_kbOneTab")).isDisplayed());
	}
	@Test (groups = {"GestionesPerfilOficina", "ABMServicios", "E2E", "Ciclo3"}, dataProvider = "BajaServicios")
	public void TC135738_CRM_Movil_REPRO_Baja_de_Servicio_sin_costo_Voice_Mail_con_Clave_Presencial_OfCom(String sDNI, String sLinea){
		imagen = "TS135738";
		detalles = null;
		detalles = imagen+" - BajaServicio - DNI: "+sDNI+" -Linea: "+sLinea;
		BasePage cambioFrameByID=new BasePage();
		sleep(30000);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		sb.BuscarCuenta("DNI",sDNI);
		//sb.BuscarCuenta(sLinea);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		detalles +="-Cuenta:"+accid;
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(25000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		cc.seleccionarCardPornumeroLinea(sLinea, driver);
		sleep(5000);
		cc.irAGestionEnCard("Alta/Baja de Servicios");
		sleep(35000);
		//cc.openrightpanel();
		//cc.closerightpanel();
		//cc.openleftpanel();
		//cc.closeleftpanel();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-text-body--small.slds-page-header__info.taDevider")));
		String sOrder = driver.findElement(By.cssSelector(".slds-text-body--small.slds-page-header__info.taDevider")).getText();
		sOrder = sOrder.replace("Nro. Orden:", "");
		sOrder = sOrder.replace(" ", "");
		detalles +="-Orden:"+sOrder;
		detalles +="-Servicio:VoiceMailConClave";
		try {
			cc.closeleftpanel();
		}
		catch (Exception x) {
			//Always empty
		}
		try {
			driver.findElement(By.id("ext-comp-1039__scc-st-10")).click();
		}
		catch (Exception x) {
			//Always empty
		}
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.id("tab-default-1")));
		sleep(15000);
		driver.findElement(By.cssSelector(".slds-button.cpq-item-has-children")).click();
		sleep(5000);
		boolean bAssert = false;
		List<WebElement> sServicios= driver.findElements(By.xpath("//*[@class='cpq-item-product-child-level-1 cpq-item-child-product-name-wrapper']"));
		for(WebElement a: sServicios) {
			if (a.getText().toLowerCase().contains("servicios basicos general movil".toLowerCase())) {
					a.findElement(By.tagName("button")).click();
						sleep(8000);
						bAssert= true;
						break;
			}
		}
		Assert.assertTrue(bAssert);
		sleep(17000);
		bAssert = false;
		List <WebElement> wContestador = driver.findElements(By.cssSelector(".cpq-item-product-child-level-2.cpq-item-child-product-name-wrapper"));
		for(WebElement d : wContestador){
			if(d.getText().contains("CONTESTADOR")){
			   cc.obligarclick(d.findElement(By.cssSelector(".slds-button.slds-button_icon-small")));
			   bAssert = true;
			   break;
			}
		}
		Assert.assertTrue(bAssert);
		sleep(10000);
		List <WebElement> wServicios = driver.findElements(By.cssSelector("[class='cpq-item-product-child-level-2 ng-not-empty ng-valid'] [class='slds-is-relative']"));
		for(WebElement r : wServicios){
			if(r.getText().contains("Voice Mail con Clave")){
				sleep(5000);
				cc.obligarclick(r.findElement(By.cssSelector(".slds-button.slds-button_icon-border-filled.cpq-item-actions-dropdown-button")));
				sleep(5000);
				List<WebElement> wButtons = r.findElements(By.cssSelector(".slds-dropdown__item.cpq-item-actions-dropdown__item"));
				for(WebElement wAux : wButtons) {
					if(wAux.getText().equalsIgnoreCase("Delete")) {
						cc.obligarclick(wAux);
					}
				}
				break;
			}
		}
		driver.findElement(By.cssSelector(".slds-button.slds-button--destructive")).click();
		sleep(10000);
		driver.findElement(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand")).click();
		sleep(10000);
		WebElement wMessageBox = driver.findElement(By.id("TextBlock1")).findElement(By.className("ng-binding"));
		sleep(5000);
		Assert.assertTrue(wMessageBox.getText().toLowerCase().contains("la orden " + sOrder + " se realiz\u00f3 con \u00e9xito!"));
		sleep(15000);
		driver.navigate().refresh();
		Assert.assertTrue(cc.corroborarEstadoCaso(sOrder, "Activada"));
	}
	
	@Test (groups = {"GestionesPerfilOficina","E2E","Ciclo3"}, dataProvider="BajaServicios")
	public void TS135739_CRM_Movil_REPRO_Baja_de_Servicio_sin_costo_DDI_sin_Roaming_Internacional_Presencial_OfCom(String sDNI, String sLinea){
		imagen = "TS135739";
		detalles = null;
		detalles = imagen+"- BajaServicio - DNI: "+sDNI+" - Linea: "+sLinea;
		BasePage cambioFrameByID=new BasePage();
		sleep(30000);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI",sDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		detalles +="-Cuenta:"+accid;
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(18000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(5000);
		cc.irAGestionEnCard("Alta/Baja de Servicios");
		sleep(35000);
		cc.openrightpanel();
		cc.closerightpanel();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-text-body--small.slds-page-header__info.taDevider")));
		String sOrder = driver.findElement(By.cssSelector(".slds-text-body--small.slds-page-header__info.taDevider")).getText();
		sOrder = sOrder.replace("Nro. Orden:", "");
		sOrder = sOrder.replace(" ", "");
		detalles +="-Orden:"+sOrder;
		detalles +="-Servicio:DDIsinRoamingInternacional";
		try {
			cc.closeleftpanel();
		}
		catch (Exception x) {
			//Always empty
		}
		try {
			driver.findElement(By.id("ext-comp-1039__scc-st-10")).click();
		}
		catch (Exception x) {
			//Always empty
		}
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.id("tab-default-1")));
		sleep(15000);
		driver.findElement(By.cssSelector(".slds-button.cpq-item-has-children")).click();
		sleep(5000);
		boolean bAssert = false;
		List<WebElement> servicios= driver.findElements(By.xpath("//*[@class='cpq-item-product-child-level-1 cpq-item-child-product-name-wrapper']"));
		for(WebElement a: servicios) {
			if (a.getText().toLowerCase().contains("servicios basicos general movil".toLowerCase())) {
					a.findElement(By.tagName("button")).click();
						sleep(8000);
						bAssert= true;
						break;
			}
		}
		Assert.assertTrue(bAssert);
		sleep(17000);
		bAssert = false;
		List <WebElement> ddi = driver.findElements(By.cssSelector(".cpq-item-product-child-level-2.cpq-item-child-product-name-wrapper"));
		for(WebElement d : ddi){
			if(d.getText().contains("DDI")){
			   cc.obligarclick(d.findElement(By.cssSelector(".slds-button.slds-button_icon-small")));
			   bAssert = true;
			   break;
			}
		}
		Assert.assertTrue(bAssert);
		sleep(10000);
		List <WebElement> roam = driver.findElements(By.cssSelector(".cpq-item-base-product"));
		for(WebElement r : roam){
			if(r.getText().contains("DDI sin Roaming Internacional")){
				sleep(5000);
				cc.obligarclick(r.findElement(By.cssSelector(".slds-button.slds-button_icon-border-filled.cpq-item-actions-dropdown-button")));
				sleep(5000);
				List<WebElement> wButtons = r.findElements(By.cssSelector(".slds-dropdown__item.cpq-item-actions-dropdown__item"));
				for(WebElement wAux : wButtons) {
					if(wAux.getText().equalsIgnoreCase("Delete")) {
						cc.obligarclick(wAux);
					}
				}
				break;
			}
		}
		driver.findElement(By.cssSelector(".slds-button.slds-button--destructive")).click();
		sleep(10000);
		driver.findElement(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand")).click();
		sleep(10000);
		WebElement wMessageBox = driver.findElement(By.id("TextBlock1")).findElement(By.className("ng-binding"));
		sleep(5000);
		Assert.assertTrue(wMessageBox.getText().toLowerCase().contains("la orden " + sOrder + " se realiz\u00f3 con \u00e9xito!"));
		sleep(15000);
		driver.navigate().refresh();
		Assert.assertTrue(cc.corroborarEstadoCaso(sOrder, "Activada"));
	}
	
	@Test (groups = {"GestionesPerfilOficina", "ABMServicios", "E2E", "Ciclo3"}, dataProvider = "AltaServicios")
	public void TS135743_CRM_Movil_REPRO_Alta_Servicio_sin_costo_Restriccion_Ident_de_Llamadas_Presencial_Agente(String sDNI, String sLinea) throws AWTException{
		imagen = "TS135743";
		detalles = null;
		detalles = imagen+" - AltaServicio - DNI: "+sDNI+" - Linea: "+sLinea;
		GestionFlow gGF = new GestionFlow();
		Assert.assertTrue(gGF.FlowConsultaServicioInactivo(driver, sLinea, "Restricci\u00f3n de la Identificaci�n de Llamadas"));
		BasePage cambioFrameByID=new BasePage();
		sleep(30000);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		sb.BuscarCuenta("DNI",sDNI);
		//sb.BuscarCuenta(sLinea);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		detalles +="-Cuenta:"+accid;
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(25000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		cc.seleccionarCardPornumeroLinea(sLinea, driver);
		sleep(5000);
		cc.irAGestionEnCard("Alta/Baja de Servicios");
		sleep(35000);
		//cc.openrightpanel();
		//cc.closerightpanel();
		//cc.openleftpanel();
		//cc.closeleftpanel();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-text-body--small.slds-page-header__info.taDevider")));
		String sOrder = driver.findElement(By.cssSelector(".slds-text-body--small.slds-page-header__info.taDevider")).getText();
		sOrder = sOrder.replace("Nro. Orden:", "");
		sOrder = sOrder.replace(" ", "");
		detalles +="-Orden:"+sOrder;
		detalles +="-Servicio:RestriccionIdent.deLlamadas";
		try {
			cc.closeleftpanel();
		}
		catch (Exception x) {
			//Always empty
		}
		try {
			driver.findElement(By.id("ext-comp-1039__scc-st-10")).click();
		}
		catch (Exception x) {
			//Always empty
		}
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.id("tab-default-1")));
		sleep(15000);
		//driver.findElement(By.cssSelector(".slds-button.cpq-item-has-children")).click();//Plan con Tarjeta Repro button
		//ppt.getwPlanConTarjetaRepro().click();//Plan con Tarjeta Repro button
		ppt = new PagePerfilTelefonico(driver);
		ppt.altaBajaServicio("Alta", "servicios basicos general movil", "Restriccion Ident. de Llamadas", driver);
		driver.findElement(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand")).click();//Continuar
		//ppt.getwAltaBajaContinuar().click();//Continuar
		sleep(20000);
		WebElement wMessageBox = driver.findElement(By.xpath("//*[@id='TextBlock1']/div/p/p[2]"));
		System.out.println("wMessage.getText: " + wMessageBox.getText().toLowerCase());
		Assert.assertTrue(wMessageBox.getText().toLowerCase().contains("la orden " + sOrder + " se realiz\u00f3 con \u00e9xito!".toLowerCase()));
		sleep(15000);
		sOrders.add("Baja de Servicio, orden numero: " + sOrder + ", DNI: " + sDNI);
		driver.navigate().refresh();
		Assert.assertTrue(cc.corroborarEstadoCaso(sOrder, "Activada"));
		sleep(20000);
		Assert.assertTrue(gGF.FlowConsultaServicioActivo(driver, sLinea, "Restricci\u00f3n de la Identificaci�n de Llamadas"));
	}
	
	@Test (groups = {"GestionesPerfilOficina", "ABMServicios", "E2E", "Ciclo3"}, dataProvider = "AltaServicios")
	public void TC135744_CRM_Movil_REPRO_Alta_Servicio_sin_costo_Voice_Mail_con_Clave_Presencial_OfCom(String sDNI, String sLinea){
		imagen = "TS135744";
		detalles = null;
		detalles = imagen+"- AltaServicio - DNI: "+sDNI+" - Linea: "+sLinea;
		BasePage cambioFrameByID=new BasePage();
		sleep(30000);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		sb.BuscarCuenta("DNI",sDNI);
		//sb.BuscarCuenta(sLinea);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		detalles +="-Cuenta:"+accid;
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(25000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		cc.seleccionarCardPornumeroLinea(sLinea, driver);
		sleep(5000);
		cc.irAGestionEnCard("Alta/Baja de Servicios");
		sleep(35000);
		//cc.openrightpanel();
		//cc.closerightpanel();
		//cc.openleftpanel();
		//cc.closeleftpanel();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-text-body--small.slds-page-header__info.taDevider")));
		String sOrder = driver.findElement(By.cssSelector(".slds-text-body--small.slds-page-header__info.taDevider")).getText();
		sOrder = sOrder.replace("Nro. Orden:", "");
		sOrder = sOrder.replace(" ", "");
		detalles +="-Orden:"+sOrder;
		detalles +="-Servicio:VoiceMailConClave";
		try {
			cc.closeleftpanel();
		}
		catch (Exception x) {
			//Always empty
		}
		try {
			driver.findElement(By.id("ext-comp-1039__scc-st-10")).click();
		}
		catch (Exception x) {
			//Always empty
		}
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.id("tab-default-1")));
		sleep(15000);
		ppt = new PagePerfilTelefonico(driver);
		ppt.altaBajaServicio("Alta", "Servicios basicos general movil", "Contestador", "Voice Mail con Clave", driver);
		driver.findElement(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand")).click();
		sleep(10000);
		WebElement wMessageBox = driver.findElement(By.id("TextBlock1")).findElement(By.className("ng-binding"));
		sleep(5000);
		Assert.assertTrue(wMessageBox.getText().toLowerCase().contains("la orden " + sOrder + " se realiz\u00f3 con \u00e9xito!"));
		sleep(15000);
		driver.navigate().refresh();
		Assert.assertTrue(cc.corroborarEstadoCaso(sOrder, "Activada"));
	}
	
	@Test (groups = {"GestionesPerfilOficina","E2E","Ciclo3"}, dataProvider="AltaServicios")
	public void TS135745_CRM_Movil_REPRO_Alta_Servicio_sin_costo_DDI_sin_Roaming_Internacional_Presencial_OfCom(String sDNI, String sLinea){
		imagen = "TS135745";
		detalles = imagen+" - AltaServicio - DNI: "+sDNI+" - Linea: "+sLinea;
		BasePage cambioFrameByID=new BasePage();
		sleep(30000);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI",sDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		detalles +="-Cuenta:"+accid;
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(18000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(5000);
		cc.irAGestionEnCard("Alta/Baja de Servicios");
		sleep(35000);
		cc.openrightpanel();
		cc.closerightpanel();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-text-body--small.slds-page-header__info.taDevider")));
		String sOrder = driver.findElement(By.cssSelector(".slds-text-body--small.slds-page-header__info.taDevider")).getText();
		sOrder = sOrder.replace("Nro. Orden:", "");
		sOrder = sOrder.replace(" ", "");
		detalles +="-Orden:"+sOrder;
		detalles +="-Servicio:DDIsinRoamingInternacional";
		try {
			cc.closeleftpanel();
		}
		catch (Exception x) {
			//Always empty
		}
		try {
			driver.findElement(By.id("ext-comp-1039__scc-st-10")).click();
		}
		catch (Exception x) {
			//Always empty
		}
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.id("tab-default-1")));
		sleep(15000);
		driver.findElement(By.cssSelector(".slds-button.cpq-item-has-children")).click();
		sleep(5000);
		boolean bAssert = false;
		List<WebElement> servicios= driver.findElements(By.xpath("//*[@class='cpq-item-product-child-level-1 cpq-item-child-product-name-wrapper']"));
		for(WebElement a: servicios) {
			if (a.getText().toLowerCase().contains("servicios basicos general movil".toLowerCase())) {
					a.findElement(By.tagName("button")).click();
						sleep(8000);
						bAssert= true;
						break;
			}
		}
		Assert.assertTrue(bAssert);
		sleep(17000);
		bAssert = false;
		List <WebElement> ddi = driver.findElements(By.cssSelector(".cpq-item-product-child-level-2.cpq-item-child-product-name-wrapper"));
		for(WebElement d : ddi){
			if(d.getText().contains("DDI")){
			   cc.obligarclick(d.findElement(By.cssSelector(".slds-button.slds-button_icon-small")));
			   bAssert = true;
			   break;
			}
		}
		Assert.assertTrue(bAssert);
		sleep(10000);
		List <WebElement> roam = driver.findElements(By.cssSelector(".cpq-item-base-product"));
		for(WebElement r : roam){
			if(r.getText().contains("DDI sin Roaming Internacional")){
				cc.obligarclick(r.findElement(By.cssSelector(".slds-button.slds-button_neutral")));
				sleep(5000);
				break;
			}
		}
		driver.findElement(By.cssSelector(".slds-button.slds-button--destructive")).click();
		sleep(10000);
		driver.findElement(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand")).click();
		sleep(10000);
		WebElement wMessageBox = driver.findElement(By.id("TextBlock1")).findElement(By.className("ng-binding"));
		sleep(5000);
		Assert.assertTrue(wMessageBox.getText().toLowerCase().contains("la orden " + sOrder + " se realiz\u00f3 con \u00e9xito!"));
		sleep(15000);
		driver.navigate().refresh();
		Assert.assertTrue(cc.corroborarEstadoCaso(sOrder, "Activada"));
	}
		
	@Test (groups = {"GestionesPerfilOficina", "BaseDeConocimiento", "Ciclo3"}, dataProvider = "CuentaVista360")
	public void TS125098_CRM_REPRO_BDC_Customer_Care_Problemas_con_recargas_Valoracion_negativa_en_BC(String sDNI, String sNombre) {
		imagen = "TS125098";
		detalles = null;
		detalles = imagen + "-Base de Conocimiento - DNI: "+sDNI+ " - Nombre: "+sNombre;
		boolean downVote = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(3000);
		cc.irAGestionEnCard("Problemas con Recargas");
		sleep(5000);
		driver.switchTo().defaultContent();
		buscarYClick(driver.findElements(By.className("x-btn-text")), "contains", "knowledge");
		driver.switchTo().frame(cambioFrame(driver, By.xpath("//*[@id=\"knowledgeSearchInput_kbOneTab\"]")));
		driver.findElement(By.xpath("//*[@id=\"knowledgeSearchInput_kbOneTab\"]")).sendKeys("problemas con recargas");
		driver.findElement(By.xpath("//*[@id=\"knowledgeSearchInput_kbOneTab\"]")).sendKeys(Keys.ENTER);
		sleep(3000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".articleListContainer.loaded")));
		driver.findElement(By.cssSelector(".articleListContainer.loaded")).findElement(By.className("articleEntry")).findElement(By.tagName("a")).click();
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.className("articleRendererBorderRight")));
		WebElement negativo = driver.findElement(By.className("articleRendererBorderRight")).findElements(By.tagName("td")).get(1).findElements(By.tagName("a")).get(1);
		if (negativo.getAttribute("title").contains("Haga clic si no le gusta este art\u00edculo de knowledge"))
			downVote = true;
		Assert.assertTrue(downVote);
	}
	
	@Test(groups = { "GestionesPerfilOficina","CambioSimCard", "E2E","Ciclo3" }, priority = 1, dataProvider = "SimCardSiniestroOfCom")
	public void TS99030_CRM_Movil_REPRO_Cambio_de_SimCard_Presencial_Siniestro_DOC_Delivery_EFE_Ofcom(String sDNI, String sLinea) throws AWTException {
		imagen = "99030";
		detalles = null;
		detalles = imagen + "-DNI:" + sDNI;
		SalesBase sale = new SalesBase(driver);
		BasePage cambioFrameByID = new BasePage();
		CustomerCare cCC = new CustomerCare(driver);
		PagePerfilTelefonico pagePTelefo = new PagePerfilTelefonico(driver);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(8000);
		sale.BuscarCuenta("DNI", sDNI);
		sleep(8000);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		detalles +="-Cuenta:"+accid;
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(25000);
		cCC.seleccionarCardPornumeroLinea(sLinea, driver);
		sleep(3000);
		cCC.irAGestion("Cambio de Simcard");
		sleep(10000);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("SelectAsset0")));
		driver.findElement(By.id("SelectAsset0")).findElement(By.cssSelector(".slds-radio.ng-scope")).click();
		driver.findElement(By.id("AssetSelection_nextBtn")).click();
		sleep(5000);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("DeliveryMethodSelection")));
		sleep(15000);
		Select metodoEntrega = new Select (driver.findElement(By.id("DeliveryMethodSelection")));
		metodoEntrega.selectByVisibleText("Presencial");
		cCC.obligarclick(driver.findElement(By.id("DeliveryMethodConfiguration_nextBtn")));
		sleep(16000);
		//cCC.obligarclick(driver.findElement(By.id("ICCDAssignment_nextBtn")));
		//sleep(16000);
		cCC.obligarclick(driver.findElement(By.id("InvoicePreview_nextBtn")));
		sleep(16000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals","Efectivo");
		cCC.obligarclick(driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")));
		sleep(15000);
		String orden = driver.findElement(By.className("top-data")).findElement(By.className("ng-binding")).getText();
		detalles += "-Orden:" + orden;
		System.out.println("Orden " + orden);
		orden = orden.substring(orden.length()-8);
		cCC.obligarclick(driver.findElement(By.id("OrderSumary_nextBtn")));
		sleep(15000);
		String check = driver.findElement(By.id("GeneralMessageDesing")).getText();
		Assert.assertTrue(check.toLowerCase().contains("la orden se realiz\u00f3 con \u00e9xito"));
		sleep(5000);
		driver.navigate().refresh();
		sleep(10000);
		String invoice = cCC.obtenerMontoyTNparaAlta(driver, orden);
		System.out.println(invoice);
		detalles+="-Monto:"+invoice.split("-")[2]+"-Prefactura:"+invoice.split("-")[1];
		sleep(10000);
		//datosOrden.add("Cambio sim card Agente- Cuenta: "+accid+"Invoice: "+invoice.split("-")[0]);
		CBS_Mattu invoSer = new CBS_Mattu();
		Assert.assertTrue(invoSer.PagoEnCaja("1006", accid, "1001", invoice.split("-")[2], invoice.split("-")[1],driver));
		sleep(5000);
		driver.navigate().refresh();
		sleep(10000);
		//cc.obtenerTNyMonto2(driver, sOrden);
		//sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".hasMotif.orderTab.detailPage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		WebElement tabla = driver.findElement(By.id("ep")).findElements(By.tagName("table")).get(1);
		String datos = tabla.findElements(By.tagName("tr")).get(4).findElements(By.tagName("td")).get(1).getText();
		Assert.assertTrue(datos.equalsIgnoreCase("activada")||datos.equalsIgnoreCase("activated"));
		
	}
	@Test(groups = { "GestionesPerfilOficina","CambioSimCard", "E2E","Ciclo3" }, priority = 1, dataProvider = "CambioSimCardOficina")
	public void TS134381_CRM_Movil_REPRO_Cambio_de_simcard_sin_costo_Voluntario_Ofcom_Presencial_Con_entega_de_pedido(String sDNI, String sLinea) throws AWTException	{
		imagen = "134381";
		detalles = null;
		detalles = imagen + "-DNI:" + sDNI;
		SalesBase sale = new SalesBase(driver);
		BasePage cambioFrameByID = new BasePage();
		CustomerCare cCC = new CustomerCare(driver);
		PagePerfilTelefonico pagePTelefo = new PagePerfilTelefonico(driver);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(8000);
		sale.BuscarCuenta("DNI", sDNI);
		sleep(8000);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		detalles +="-Cuenta:"+accid;
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(25000);
		cCC.seleccionarCardPornumeroLinea(sLinea, driver);
		sleep(3000);
		cCC.irAGestion("Cambio de Simcard");
		sleep(10000);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("SelectAsset0")));
		driver.findElement(By.id("SelectAsset0")).findElement(By.cssSelector(".slds-radio.ng-scope")).click();
		driver.findElement(By.id("AssetSelection_nextBtn")).click();
		sleep(5000);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("DeliveryMethodSelection")));
		sleep(15000);
		Select metodoEntrega = new Select (driver.findElement(By.id("DeliveryMethodSelection")));
		metodoEntrega.selectByVisibleText("Presencial");
		cCC.obligarclick(driver.findElement(By.id("DeliveryMethodConfiguration_nextBtn")));
		sleep(16000);
		//cCC.obligarclick(driver.findElement(By.id("ICCDAssignment_nextBtn")));
		//sleep(16000);
		cCC.obligarclick(driver.findElement(By.id("InvoicePreview_nextBtn")));
		sleep(16000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals","Efectivo");
		cCC.obligarclick(driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")));
		sleep(15000);
		String orden = driver.findElement(By.className("top-data")).findElement(By.className("ng-binding")).getText();
		detalles += "-Orden:" + orden;
		System.out.println("Orden " + orden);
		orden = orden.substring(orden.length()-8);
		cCC.obligarclick(driver.findElement(By.id("OrderSumary_nextBtn")));
		sleep(15000);
		String check = driver.findElement(By.id("GeneralMessageDesing")).getText();
		Assert.assertTrue(check.toLowerCase().contains("la orden se realiz\u00f3 con \u00e9xito"));
		sleep(5000);
		driver.navigate().refresh();
		sleep(10000);
		String invoice = cCC.obtenerMontoyTNparaAlta(driver, orden);
		System.out.println(invoice);
		detalles+="-Monto:"+invoice.split("-")[2]+"-Prefactura:"+invoice.split("-")[1];
		sleep(10000);
		//datosOrden.add("Cambio sim card Agente- Cuenta: "+accid+"Invoice: "+invoice.split("-")[0]);
		CBS_Mattu invoSer = new CBS_Mattu();
		Assert.assertTrue(invoSer.PagoEnCaja("1006", accid, "1001", invoice.split("-")[2], invoice.split("-")[1],driver));
		sleep(5000);
		driver.navigate().refresh();
		sleep(10000);
		//cc.obtenerTNyMonto2(driver, sOrden);
		//sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".hasMotif.orderTab.detailPage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		WebElement tabla = driver.findElement(By.id("ep")).findElements(By.tagName("table")).get(1);
		String datos = tabla.findElements(By.tagName("tr")).get(4).findElements(By.tagName("td")).get(1).getText();
		Assert.assertTrue(datos.equalsIgnoreCase("activada")||datos.equalsIgnoreCase("activated"));
	}
	
	@Test (groups = {"ProblemasConRecargas", "GestionesPerfilOficina", "E2E", "Ciclo3"}, dataProvider = "CuentaProblemaRecarga") 
	public void TS104346_CRM_Movil_Repro_Problemas_con_Recarga_Presencial_On_Line_Ofcom(String sDNI, String sLinea) {
		imagen = "TS104346";
		detalles = null;
		detalles = imagen + " -Problemas Con Recargas-DNI: " + sDNI;
		String datoViejo = cbs.ObtenerValorResponse(cbsm.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer datosInicial = Integer.parseInt(datoViejo.substring(0, 5));
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(3000);
		cc.irAGestionEnCard("Problemas con Recargas");
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.className("borderOverlay")));
		driver.findElements(By.className("borderOverlay")).get(1).click();
		driver.findElement(By.id("RefillMethods_nextBtn")).click();
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.id("OnlineRefillData_nextBtn")));
		driver.findElement(By.id("RefillDate")).sendKeys("11-08-2018");
		driver.findElement(By.id("RefillAmount")).sendKeys("5000");
		driver.findElement(By.id("ReceiptCode")).sendKeys("111");
		driver.findElement(By.id("OnlineRefillData_nextBtn")).click();
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "si");
		driver.findElement(By.id("FileAttach")).sendKeys("C:\\Users\\Nicolas\\Desktop\\descarga.jpg");
		driver.findElement(By.id("AttachDocuments_nextBtn")).click();
		sleep(5000);
		driver.findElement(By.id("Summary_nextBtn")).click();
		sleep(5000);
		WebElement gestion = driver.findElement(By.className("ta-care-omniscript-done")).findElement(By.tagName("header")).findElement(By.tagName("h1"));
		Assert.assertTrue(gestion.getText().contains("Recarga realizada con \u00e9xito"));
		String datoNuevo = cbs.ObtenerValorResponse(cbsm.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer datosFinal = Integer.parseInt(datoNuevo.substring(0, 5));
		Assert.assertTrue(datosInicial + 500 == datosFinal);
	}
	
	@Test (groups = {"ProblemasConRecargas", "GestionesPerfilOficina", "E2E", "Ciclo3"}, dataProvider = "CuentaProblemaRecarga") 
	public void TS104351_CRM_Movil_Repro_Problemas_con_Recarga_On_line_Sin_comprobante_En_espera_del_cliente_Ofcom(String sDNI, String sLinea) {
		imagen = "TS104351";
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(25000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(3000);
		cc.irAGestionEnCard("Problemas con Recargas");
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.className("borderOverlay")));
		driver.findElements(By.className("borderOverlay")).get(1).click();
		driver.findElement(By.id("RefillMethods_nextBtn")).click();
		sleep(5000);
		driver.findElement(By.id("RefillDate")).sendKeys("11-08-2018");
		driver.findElement(By.id("RefillAmount")).sendKeys("5000");
		driver.findElement(By.id("ReceiptCode")).sendKeys("111");
		driver.findElement(By.id("OnlineRefillData_nextBtn")).click();
		sleep(5000);		
		try {
			driver.findElement(By.xpath("//*[@id=\"SessionCase|0\"]/div/div[1]/label[2]/span/div/div")).click();
			driver.findElement(By.id("ExistingCase_nextBtn")).click();
			sleep(5000);
		} catch (Exception e) {}	
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope")), "equals", "no");
		driver.findElement(By.id("AttachDocuments_nextBtn")).click();
		sleep(5000);
		driver.findElement(By.id("Summary_nextBtn")).click();
		sleep(5000);
		WebElement gestion = driver.findElement(By.className("ta-care-omniscript-done")).findElement(By.tagName("header")).findElement(By.tagName("h1"));
		Assert.assertTrue(gestion.getText().contains("La gesti\u00f3n fue derivada"));
	}
	
	@Test (groups = {"ProblemasConRecargas", "GestionesPerfilOficina", "E2E", "Ciclo3"}, dataProvider = "CuentaProblemaRecarga") 
	public void TS104353_CRM_Movil_Repro_Problemas_con_Recarga_Presencial_Tarjeta_Scratch_Caso_Nuevo_Tarjeta_Activa_y_Disponible_Ofcom(String sDNI, String sLinea) {
		imagen = "TS104353";
		detalles = null;
		detalles = imagen + " -Problemas Con Recargas-DNI: " + sDNI;
		String datoViejo = cbs.ObtenerValorResponse(cbsm.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer datosInicial = Integer.parseInt(datoViejo.substring(0, 5));
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(25000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(3000);
		cc.irAGestionEnCard("Problemas con Recargas");
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.className("borderOverlay")));
		driver.findElements(By.className("borderOverlay")).get(0).click();
		driver.findElement(By.id("RefillMethods_nextBtn")).click();
		sleep(5000);
		driver.findElement(By.id("BatchNumber")).sendKeys("11120000009300");
		driver.findElement(By.id("PIN")).sendKeys("0101");
		driver.findElement(By.id("PrepaidCardData_nextBtn")).click();
		sleep(5000);
		WebElement estado = driver.findElement(By.id("PrepaidCardStatusLabel"));
		Assert.assertTrue(estado.getText().toLowerCase().contains("activa"));
		driver.findElement(By.id("Summary_nextBtn")).click();
		sleep(5000);
		WebElement gestion = driver.findElement(By.className("ta-care-omniscript-done")).findElement(By.tagName("header")).findElement(By.tagName("h1"));
		Assert.assertTrue(gestion.getText().contains("Recarga realizada con \u00e9xito"));
		String datoNuevo = cbs.ObtenerValorResponse(cbsm.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer datosFinal = Integer.parseInt(datoNuevo.substring(0, 5));
		Assert.assertTrue(datosInicial + 500 == datosFinal);
	}
	
	@Test (groups = {"GestionesPerfilTelefonico", "HistorialDeRecargas", "E2E", "Ciclo2"}, dataProvider = "HistoriaRecarga")
	public void TS134840_CRM_Movil_Prepago_Historial_de_Recargas_Consultar_detalle_de_Recargas_por_Fecha_Fan_FRONT_OOCC(String sDNI, String sLinea) {
		imagen = "TS134840";
		detalles = null;
		detalles = imagen+"-Historial De Recarga-DNI:"+sDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(20000);
		CustomerCare cc = new CustomerCare(driver);
		cc.seleccionarCardPornumeroLinea(sLinea, driver);
		sleep(3000);
		cc.irAHistoriales();
		sleep(3000);
		cc.verificacionDeHistorial("Historial de packs");
		cc.verificacionDeHistorial("Historial de ajustes");
		cc.verificacionDeHistorial("Historial de recargas");
		cc.verificacionDeHistorial("Historial de recargas S.O.S");
		sleep(3000);
		cc.seleccionDeHistorial("historial de recargas");
		sleep(3000);
		driver.switchTo().frame(cambioFrame(driver, By.id("text-input-03")));
		WebElement canal = driver.findElement(By.id("text-input-03"));
		canal.click();
		System.out.println(canal.getText());
		Assert.assertTrue(canal.isDisplayed());
		try {
		driver.findElement(By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small")).click();
		} catch (Exception e) {
			driver.findElement(By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small")).click();
		}
		sleep(3000);
		boolean a = false;
		List <WebElement> fecha = driver.findElements(By.cssSelector(".slds-truncate.slds-th__action"));		
		for(WebElement x : fecha) {
			if(x.getText().toLowerCase().contains("fecha")) {
				a= true;
			}
		}
		Assert.assertTrue(a);
		sleep(3000);
		WebElement paginas = driver.findElement(By.cssSelector(".slds-grid.slds-col"));
		Assert.assertTrue(paginas.getText().contains("Filas"));
	}
	
	@Test (groups = {"GestionesPerfilTelefonico", "HistorialDeRecargas", "E2E", "Ciclo2"}, dataProvider = "HistoriaRecarga")
	public void TS134747_CRM_Movil_Prepago_Historial_de_Recargas_S141_FAN_Front_OOCC(String sDNI, String sLinea){
		imagen = "TS134747";
		detalles = null;
		detalles = imagen+"-Historial De Recarga-DNI:"+sDNI;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(20000);
		CustomerCare cc = new CustomerCare(driver);
		cc.seleccionarCardPornumeroLinea(sLinea, driver);
		sleep(3000);
		cc.irAHistoriales();
		sleep(3000);
		cc.verificacionDeHistorial("Historial de packs");
		cc.verificacionDeHistorial("Historial de ajustes");
		cc.verificacionDeHistorial("Historial de recargas");
		cc.verificacionDeHistorial("Historial de recargas S.O.S");
		sleep(3000);
		cc.seleccionDeHistorial("historial de recargas");
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-grid.slds-wrap.slds-card.slds-m-bottom--small.slds-p-around--medium")));
		WebElement conf = driver.findElement(By.cssSelector(".slds-grid.slds-wrap.slds-card.slds-m-bottom--small.slds-p-around--medium"));
		Assert.assertTrue(conf.isDisplayed());
		try {
			driver.findElement(By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small")).click();
			} catch (Exception e) {
				driver.findElement(By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small")).click();
			}
		sleep(3000);
		WebElement conf_1 = driver.findElement(By.cssSelector(".slds-p-bottom--small.slds-p-left--medium.slds-p-right--medium"));
		Assert.assertTrue(conf_1.isDisplayed());
		sleep(3000);
		boolean a = false;
		List <WebElement> fecha = driver.findElements(By.cssSelector(".slds-truncate.slds-th__action"));		
		for(WebElement x : fecha) {
			if(x.getText().toLowerCase().contains("fecha")) {
				a= true;
			}
		}
		Assert.assertTrue(a);
		sleep(3000);
		WebElement paginas = driver.findElement(By.cssSelector(".slds-grid.slds-col"));
		Assert.assertTrue(paginas.getText().contains("Filas"));
	}
	
	@Test (groups = {"GestionesPerfilOficina", "DiagnosticoInconveniente","E2E", "Ciclo3"}, dataProvider = "Diagnostico")
	public void TS119262_CRM_Movil_REPRO_Diagnostico_de_Voz_Valida_Red_y_Navegacion_Motivo_de_contacto_No_puedo_Llamar_desde_otro_pais_Sin_Locacion_NO_recupera_locacion_Geo_Fuera_de_area_de_cobertura_OfCom(String sDNI, String sLinea) throws Exception  {
		boolean caso = false;
		imagen = "TS119262";
		detalles = null;
		detalles = imagen + " -ServicioTecnico: " + sDNI;
		CustomerCare cCC=new CustomerCare(driver);
		TechCare_Ola1 page=new TechCare_Ola1(driver);
		TechnicalCareCSRDiagnosticoPage tech = new TechnicalCareCSRDiagnosticoPage(driver);
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(5000);
		cCC.irAGestionEnCard("Diagn\u00f3stico");
		driver.switchTo().frame(cambioFrame(driver, By.id("Motive")));
		driver.findElement(By.name("loopname")).click();
		selectByText(driver.findElement(By.id("Motive")), "No puedo llamar desde otro pa\u00eds");
		buscarYClick(driver.findElements(By.id("MotiveIncidentSelect_nextBtn")), "equals", "continuar");
		page.seleccionarPreguntaFinal("S\u00ed");
		buscarYClick(driver.findElements(By.id("BalanceValidation_nextBtn")), "equals", "continuar");
		tech.categoriaRed("Desregistrar");
		buscarYClick(driver.findElements(By.id("NetworkCategory_nextBtn")), "equals", "continuar");
		page.seleccionarPreguntaFinal("S\u00ed");
		buscarYClick(driver.findElements(By.id("DeregisterSpeech_nextBtn")), "equals", "continuar");
		page.seleccionarPreguntaFinal("S\u00ed");
		buscarYClick(driver.findElements(By.id("BlackListValidationOk_nextBtn")), "equals", "continuar");
		tech.categoriaRed("Fuera del Area de Cobertura");
		sleep(10000);
		WebElement gesti = driver.findElement(By.xpath("//*[@id='OutOfCoverageMessage']/div/p/p[2]/span/strong"));
		String Ncaso = gesti.getText();
		System.out.println("El numero de caso es: "+Ncaso);
		caso = true;
		assertTrue(caso);
	}
	
	
	@Test (groups = {"GestionesPerfilOficina", "Actualizar Datos", "E2E", "Ciclo3"},  dataProvider = "CuentaModificacionDeDNI")
	public void TS129325_CRM_Movil_REPRO_Modificacion_de_datos_Actualizar_datos_campo_DNI_CUIT_Cliente_FAN_Front_OOCC(String sDNI, String sLinea) {
		String nuevoDNI = "22222070";
		String nuevoMail = "maildetest@gmail.com";
		String numeroTelefono = "1533546987";
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.className("profile-box")));
		driver.findElements(By.className("profile-edit")).get(0).click();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("DocumentNumber")));
		driver.findElement(By.id("DocumentNumber")).getAttribute("value");
		driver.findElement(By.id("Email")).getAttribute("value");
		driver.findElement(By.id("MobilePhone")).getAttribute("value");
		driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys(nuevoMail);
		driver.findElement(By.id("MobilePhone")).clear();
		driver.findElement(By.id("MobilePhone")).sendKeys(numeroTelefono);
		driver.findElement(By.id("DocumentNumber")).clear();
		driver.findElement(By.id("DocumentNumber")).sendKeys(nuevoDNI);
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
		sleep(10000);
		Assert.assertTrue(driver.findElement(By.className("ta-care-omniscript-done")).findElement(By.className("ng-binding")).getText().equalsIgnoreCase("Las modificaciones se realizaron con \u00e9xito!"));
		String orden = driver.findElement(By.cssSelector(".vlc-slds-inline-control__label.ng-binding")).getText();
		orden = orden.substring(orden.length()-9, orden.length()-1);
		
	}
}