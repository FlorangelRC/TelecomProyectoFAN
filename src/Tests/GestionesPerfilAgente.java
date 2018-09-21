package Tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.BasePage;
import Pages.CustomerCare;
import Pages.Marketing;
import Pages.PagePerfilTelefonico;
import Pages.SalesBase;
import Pages.setConexion;

public class GestionesPerfilAgente extends TestBase{

	private WebDriver driver;
	private SalesBase sb;
	private CustomerCare cc;
	List <String> datosOrden =new ArrayList<String>();
	String imagen;
	
	@BeforeClass(alwaysRun=true)
	public void init() {
		driver = setConexion.setupEze();
		sleep(5000);
		loginAgente(driver);
		sleep(35000);
		driver.findElement(By.id("tabBar")).findElement(By.tagName("a")).click();
		sleep(18000);
		SalesBase sb = new SalesBase(driver);
		driver.switchTo().defaultContent();
		sleep(3000);
		goToLeftPanel2(driver, "Inicio");
		sleep(18000);
		try {
			sb.cerrarPestaniaGestion(driver);
		} catch (Exception ex1) {}
		Accounts ac = new Accounts(driver);
		driver.switchTo().frame(ac.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		List <WebElement> frames = driver.findElements(By.tagName("iframe"));
		boolean enc = false;
		int index = 0;
		for(WebElement frame : frames) {
			try {
				//System.out.println("aca");
				driver.switchTo().frame(frame);
				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).getText(); //each element is in the same iframe.
				//System.out.println(index); //prints the used index.
				driver.findElement(By.cssSelector(".slds-grid.slds-m-bottom_small.slds-wrap.cards-container")).isDisplayed(); //each element is in the same iframe.
				//System.out.println(index); //prints the used index.
				driver.switchTo().frame(ac.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
				enc = true;
				break;
			} catch(NoSuchElementException noSuchElemExcept) {
				index++;
				driver.switchTo().frame(ac.getFrameForElement(driver, By.cssSelector(".hasMotif.homeTab.homepage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
			}
		}
		if(enc == false)
			index = -1;
		try {
			driver.switchTo().frame(frames.get(index));
		} catch(ArrayIndexOutOfBoundsException iobExcept) {
			System.out.println("Elemento no encontrado en ningun frame 2.");			
		}
		List <WebElement> botones = driver.findElements(By.tagName("button"));
		for (WebElement UnB : botones) {
			System.out.println(UnB.getText());
			if(UnB.getText().equalsIgnoreCase("gesti\u00f3n de clientes")) {
				UnB.click();
				break;
			}
		}
		sleep(14000);
	}
	
	@AfterMethod(alwaysRun=true)
	public void after() throws IOException {
		guardarListaTxt(datosOrden);
		datosOrden.clear();
		tomarCaptura(driver,imagen);
		sleep(5000);
	}
	
	//@AfterClass(alwaysRun=true)
	public void quit() throws IOException {
		//guardarListaTxt(datosOrden);
		//driver.quit();
		sleep(5000);
	}
	
	@Test (groups = {"GestionesPerfilAgente","Recargas","E2E"}, dataProvider="RecargaTC")
	public void TS134322_CRM_Movil_REPRO_Recargas_Presencial_TC_Agente(String sDNI, String sMonto, String sLinea, String sBanco, String sTarjeta, String sNumTarjeta, String sVenceMes, String sVenceAno, String sCodSeg, String sTipoDNI, String sDNITarjeta, String sTitular, String sPromo, String sCuotas) {
		//Check All
		imagen = "134322";
		if(sMonto.length() >= 4) {
			sMonto = sMonto.substring(0, sMonto.length()-1);
		}
		if(sVenceMes.length() >= 2) {
			sVenceMes = sVenceMes.substring(0, sVenceMes.length()-1);
		}
		if(sVenceAno.length() >= 5) {
			sVenceAno = sVenceAno.substring(0, sVenceAno.length()-1);
		}
		if(sCodSeg.length() >= 5) {
			sCodSeg = sCodSeg.substring(0, sCodSeg.length()-1);
		}
		BasePage cambioFrame=new BasePage();
		sleep(5000);
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI", sDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(18000);
		
		CustomerCare cCC = new CustomerCare(driver);
		cCC.seleccionarCardPornumeroLinea(sLinea, driver);
		sleep(12000);
		cCC.irAGestionEnCard("Recarga de cr\u00e9dito");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("RefillAmount")));
		driver.findElement(By.id("RefillAmount")).sendKeys(sMonto);
		driver.findElement(By.id("AmountSelectionStep_nextBtn")).click();
		sleep(15000);
		String sOrden = cCC.obtenerOrden2(driver);
		driver.findElement(By.id("InvoicePreview_nextBtn")).click();
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals", "tarjeta de credito");
		selectByText(driver.findElement(By.id("BankingEntity-0")), sBanco);
		selectByText(driver.findElement(By.id("CardBankingEntity-0")), sTarjeta);
		selectByText(driver.findElement(By.id("promotionsByCardsBank-0")), sPromo);
		driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")).click();
		sleep(5000);
		selectByText(driver.findElement(By.id("Installment-0")), sCuotas);
		driver.findElement(By.id("CardNumber-0")).sendKeys(sNumTarjeta);
		selectByText(driver.findElement(By.id("expirationMonth-0")), sVenceMes);
		selectByText(driver.findElement(By.id("expirationYear-0")), sVenceAno);
		driver.findElement(By.id("securityCode-0")).sendKeys(sCodSeg);
		selectByText(driver.findElement(By.id("documentType-0")), sTipoDNI);
		driver.findElement(By.id("documentNumber-0")).sendKeys(sDNITarjeta);
		driver.findElement(By.id("cardHolder-0")).sendKeys(sTitular);				
		driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")).click();
		sleep(20000);
		buscarYClick(driver.findElements(By.id("InvoicePreview_nextBtn")), "equals", "siguiente");
		List <WebElement> exis = driver.findElements(By.id("GeneralMessageDesing"));
		boolean a = false;
		for(WebElement x : exis) {
			if(x.getText().toLowerCase().contains("la orden se realiz\u00f3 con \u00e9xito")) {
				a = true;
			}
			Assert.assertTrue(a);
		}
		String orden = cCC.obtenerTNyMonto2(driver, sOrden);
		//String orden = cCC.obtenerOrdenMontoyTN(driver, "Recarga");
		System.out.println("orden = "+orden);
		datosOrden.add("Recargas" + orden + " de cuenta "+accid+" con DNI: " + sDNI);
		CBS_Mattu invoSer = new CBS_Mattu();
		invoSer.PagoEnCaja("1005", accid, "2001", orden.split("-")[2], orden.split("-")[1]);
		sleep(5000);
		driver.navigate().refresh();
		sleep(10000);
		cCC.obtenerTNyMonto2(driver, sOrden);
		//cCC.obtenerOrdenMontoyTN(driver, "Recarga");
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Status_ilecell")));
		Assert.assertTrue(driver.findElement(By.id("Status_ilecell")).getText().equalsIgnoreCase("activada"));
	}
	@Test(groups = { "GestionesPerfilAgente", "E2E" }, priority = 1, dataProvider = "CambioSimCardAgente")
	public void TSCambioSimCardAgente(String sDNI, String sLinea) {
		imagen = "TSCambioSimCardAgente";
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
		sleep(12000);
		cCC.obligarclick(driver.findElement(By.id("ICCDAssignment_nextBtn")));
		sleep(12000);
		cCC.obligarclick(driver.findElement(By.id("InvoicePreview_nextBtn")));
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals","Efectivo");
		cCC.obligarclick(driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")));
		sleep(15000);
		String orden = driver.findElement(By.className("top-data")).findElement(By.className("ng-binding")).getText();
		System.out.println("Orden " + orden);
		orden = orden.substring(orden.length()-8);
		cCC.obligarclick(driver.findElement(By.id("OrderSumary_nextBtn")));
		sleep(15000);
		try {
			driver.findElement(By.id("Step_Error_Huawei_S029_nextBtn")).click();
			System.out.println("Error en prefactura huawei");
		}catch(Exception ex1) {}
		sleep(5000);
		driver.navigate().refresh();
		sleep(10000);
		String invoice = cCC.obtenerMontoyTNparaAlta(driver, orden);
		System.out.println(invoice);
		sleep(10000);
		datosOrden.add("Cambio sim card Agente- Cuenta: "+accid+"Invoice: "+invoice.split("-")[0]);
		CBS_Mattu invoSer = new CBS_Mattu();
		if(urlAmbiente.contains("sit")) 
			Assert.assertTrue(invoSer.PagoEnCaja("1006", accid, "2001", invoice.split("-")[2], invoice.split("-")[1]));
		else
			Assert.assertTrue(invoSer.PagoEnCaja("1006", accid, "2001", invoice.split("-")[2], invoice.split("-")[1]));
		driver.navigate().refresh();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Status_ilecell")));
		Assert.assertTrue(driver.findElement(By.id("Status_ilecell")).getText().equalsIgnoreCase("activada"));
		
	}
	
	@Test (groups = {"GestionesPerfilOficina","E2E"}, dataProvider="PackAgente")
	public void Venta_de_Pack(String sDNI, String sLinea, String sPackAgente, String cBanco, String cTarjeta, String cPromo, String cCuotas){
		PagePerfilTelefonico pagePTelefo = new PagePerfilTelefonico(driver);
		SalesBase sale = new SalesBase(driver);
		CustomerCare cCC = new CustomerCare(driver);
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sale.BuscarCuenta("DNI", sDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		pagePTelefo.buscarAssert();
		pagePTelefo.comprarPack("comprar internet");
		sleep(8000);
		pagePTelefo.closerightpanel();
		sleep(8000);
		pagePTelefo.agregarPack(sPackAgente);
		pagePTelefo.tipoDePago("en factura de venta");
		pagePTelefo.getTipodepago().click();
		sleep(12000);
		pagePTelefo.getSimulaciondeFactura().click();
		sleep(12000);
		String sOrden = cCC.obtenerOrden2(driver);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals", "efectivo");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals", "tarjeta de credito");
		sleep(12000);
		selectByText(driver.findElement(By.id("BankingEntity-0")), cBanco);
		selectByText(driver.findElement(By.id("CardBankingEntity-0")), cTarjeta);
		selectByText(driver.findElement(By.id("promotionsByCardsBank-0")), cPromo);
		selectByText(driver.findElement(By.id("Installment-0")), cCuotas);
		pagePTelefo.getMediodePago().click();
		sleep(15000);
		pagePTelefo.getOrdenSeRealizoConExito().click();
		sleep(15000);
		driver.navigate().refresh();
		sleep(10000);
		String invoice = cCC.obtenerMontoyTNparaAlta(driver, sOrden);
		System.out.println(invoice);
		sleep(10000);
		datosOrden.add("Operacion: Compra de Pack- Cuenta: "+accid+"Invoice: "+invoice.split("-")[0]);
		CBS_Mattu invoSer = new CBS_Mattu();
		if(urlAmbiente.contains("sit")) 
			Assert.assertTrue(invoSer.PagoEnCaja("1005", accid, "2001", invoice.split("-")[2], invoice.split("-")[1]));
		else
			Assert.assertTrue(invoSer.PagoEnCaja("1005", accid, "2001", invoice.split("-")[2], invoice.split("-")[1]));
		driver.navigate().refresh();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("Status_ilecell")));
		Assert.assertTrue(driver.findElement(By.id("Status_ilecell")).getText().equalsIgnoreCase("activada"));
		

		}
	
	
}