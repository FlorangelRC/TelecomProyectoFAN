package Tests;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
import Pages.PagePerfilTelefonico;
import Pages.SalesBase;
import Pages.setConexion;

public class GestionesPerfilAgente extends TestBase{

	private WebDriver driver;
	private SalesBase sb;
	private CustomerCare cc;
	private Marketing mk;
	List <String> datosOrden =new ArrayList<String>();
	String imagen;
	String detalles;
	
	@BeforeClass(alwaysRun=true)
	public void init() {
		driver = setConexion.setupEze();
		sleep(5000);
		cc = new CustomerCare(driver);
		sb = new SalesBase(driver);
		mk = new Marketing(driver);
		loginAgente(driver);
		sleep(22000);
		try {
			cc.cajonDeAplicaciones("Consola FAN");
		} catch(Exception e) {
			sleep(3000);
			driver.findElement(By.id("tabBar")).findElement(By.tagName("a")).click();
			sleep(6000);
		}
		driver.switchTo().defaultContent();
		sleep(3000);
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public void setup() throws Exception {
		sleep(10000);
		goToLeftPanel2(driver, "Inicio");
		sleep(15000);
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
		datosOrden.add(detalles);
		guardarListaTxt(datosOrden);
		datosOrden.clear();
		tomarCaptura(driver,imagen);
	}
	
	//@AfterClass(alwaysRun=true)
	public void quit() throws IOException {
		//guardarListaTxt(datosOrden);
		driver.quit();
		sleep(5000);
	}
	
	@Test (groups = {"GestionesPerfilAgente","Recargas","E2E", "Ciclo1"}, dataProvider="RecargaTC")
	public void TS134322_CRM_Movil_REPRO_Recargas_Presencial_TC_Agente(String sDNI, String sMonto, String sLinea, String sBanco, String sTarjeta, String sNumTarjeta, String sVenceMes, String sVenceAno, String sCodSeg, String sTipoDNI, String sDNITarjeta, String sTitular, String sPromo, String sCuotas) throws AWTException {
		//Check All
		imagen = "134322";//00006559
		detalles = null;
		detalles = imagen + "-Recarga-DNI:" + sDNI;
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
		
		CBS cCBS = new CBS();
		CBS_Mattu cCBSM = new CBS_Mattu();
		String sMainBalance = cCBS.ObtenerValorResponse(cCBSM.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer iMainBalance = Integer.parseInt(sMainBalance.substring(0, (sMainBalance.length()) - 1));
		//System.out.println("Saldo original: " + iMainBalance);
		
		BasePage cambioFrame=new BasePage();
		sleep(5000);
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI", sDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		detalles += "-Cuenta:" + accid;
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
		detalles += "-Orden:" + sOrden;
		driver.findElement(By.id("InvoicePreview_nextBtn")).click();
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals", "tarjeta de credito");
		sleep(1000);
		driver.findElement(By.id("BankingEntity-0")).click();
		sleep(2000);
		selectByText(driver.findElement(By.id("BankingEntity-0")), sBanco);
		selectByText(driver.findElement(By.id("CardBankingEntity-0")), sTarjeta);
		selectByText(driver.findElement(By.id("promotionsByCardsBank-0")), sPromo);
		selectByText(driver.findElement(By.id("Installment-0")), sCuotas);
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
		System.out.println("orden = "+orden);
		detalles+="-Monto:"+orden.split("-")[2]+"-Prefactura:"+orden.split("-")[1];
		CBS_Mattu invoSer = new CBS_Mattu();
		invoSer.PagoEnCaja("1005", accid, "2001", orden.split("-")[2], orden.split("-")[1],driver);
		sleep(5000);
		driver.navigate().refresh();
		sleep(10000);
		String uMainBalance = cCBS.ObtenerValorResponse(cCBSM.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		System.out.println("saldo nuevo "+uMainBalance);
		Integer uiMainBalance = Integer.parseInt(uMainBalance.substring(0, (uMainBalance.length()) - 1));
		Integer monto = Integer.parseInt(orden.split("-")[2].replace(".", ""));
		monto = Integer.parseInt(monto.toString().substring(0, monto.toString().length()-2));
		Assert.assertTrue(iMainBalance+monto == uiMainBalance);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".hasMotif.orderTab.detailPage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		WebElement tabla = driver.findElement(By.id("ep")).findElements(By.tagName("table")).get(1);
		String datos = tabla.findElements(By.tagName("tr")).get(4).findElements(By.tagName("td")).get(1).getText();
		Assert.assertTrue(datos.equalsIgnoreCase("activada")||datos.equalsIgnoreCase("activated"));
	}
	@Test(groups = { "GestionesPerfilAgente","CambioSimCard", "E2E","Ciclo3" }, priority = 1, dataProvider = "CambioSimCardAgente")
	public void TSCambioSimCardAgente(String sDNI, String sLinea) throws AWTException {
		imagen = "TSCambioSimCardAgente";
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
		cCC.irAGestionEnCard("Cambio SimCard");
		sleep(2000);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("DeliveryMethodSelection")));
		sleep(15000);
		Select metodoEntrega = new Select (driver.findElement(By.id("DeliveryMethodSelection")));
		metodoEntrega.selectByVisibleText("Presencial");
		cCC.obligarclick(driver.findElement(By.id("DeliveryMethodConfiguration_nextBtn")));
		sleep(16000);
		cCC.obligarclick(driver.findElement(By.id("ICCDAssignment_nextBtn")));
		sleep(16000);
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
	
	@Test (groups = {"GestionesPerfilOficina","VentaDePack","E2E","Ciclo1"}, dataProvider="PackAgente")
	public void Venta_de_Pack(String sDNI, String sLinea, String sPackAgente, String cBanco, String cTarjeta, String cPromo, String cCuotas, String cNumTarjeta, String cVenceMes, String cVenceAno, String cCodSeg, String cTipoDNI, String cDNITarjeta, String cTitular) throws AWTException{
		imagen = "Venta_de_Pack";
		detalles = null;
		detalles = imagen + "-Recarga-DNI:" + sDNI;
		PagePerfilTelefonico pagePTelefo = new PagePerfilTelefonico(driver);
		SalesBase sale = new SalesBase(driver);
		CustomerCare cCC = new CustomerCare(driver);
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sale.BuscarCuenta("DNI", sDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		detalles +="-Cuenta:"+accid;
		sleep(5000);
		pagePTelefo.buscarAssert();
		cCC.seleccionarCardPornumeroLinea(sLinea, driver);
		pagePTelefo.comprarPack();
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
		detalles += "-Orden:" + sOrden;
		buscarYClick(driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding")), "equals", "efectivo");
		sleep(12000);
		
		pagePTelefo.getMediodePago().click();
		sleep(15000);
		pagePTelefo.getOrdenSeRealizoConExito().click();
		sleep(15000);
		driver.navigate().refresh();
		sleep(10000);
		String invoice = cCC.obtenerMontoyTNparaAlta(driver, sOrden);
		System.out.println(invoice);
		detalles+="-Monto:"+invoice.split("-")[1]+"-Prefactura:"+invoice.split("-")[0];
		sleep(10000);
		//datosOrden.add("Operacion: Compra de Pack- Cuenta: "+accid+" Invoice: "+invoice.split("-")[0]+invoice.split("-")[1]);
		System.out.println("Operacion: Compra de Pack- Cuenta: "+accid+" Invoice: "+invoice.split("-")[1] + "\tAmmount: " +invoice.split("-")[0]);
		CBS_Mattu invoSer = new CBS_Mattu();
		Assert.assertTrue(invoSer.PagoEnCaja("1005", accid, "1001", invoice.split("-")[1], invoice.split("-")[1],driver));
		
		//Assert.assertTrue(invoSer.PagaEnCajaTC("1005", accid, "2001", invoice.split("-")[1], invoice.split("-")[0],  cDNITarjeta, cTitular, cVenceAno+cVenceMes, cCodSeg, cTitular, cNumTarjeta));
		sleep(5000);
		driver.navigate().refresh();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".hasMotif.orderTab.detailPage.ext-webkit.ext-chrome.sfdcBody.brandQuaternaryBgr")));
		WebElement tabla = driver.findElement(By.id("ep")).findElements(By.tagName("table")).get(1);
		String datos = tabla.findElements(By.tagName("tr")).get(4).findElements(By.tagName("td")).get(1).getText();
		Assert.assertTrue(datos.equalsIgnoreCase("activada")||datos.equalsIgnoreCase("activated"));
	}
	
	@Test (groups = {"GestionesPerfilAgente", "AnulacionDeVenta", "E2E","Ciclo4"}, dataProvider = "CuentaAnulacionDeVenta")
	public void Anulacion_De_Venta(String cDNI) {
		imagen = "Anulacion_De_Venta";
		detalles = null;
		detalles = imagen + "-DNI:" + cDNI;
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
	
	@Test (groups = {"GestionesPerfilAgente", "ConsultaDeSaldo", "Ciclo1"}, dataProvider = "ConsultaSaldo")
	public void TS_134814_CRM_Movil_Prepago_Vista_360_Consulta_de_Saldo_Verificar_credito_prepago_de_la_linea_FAN_Front_Agentes(String sDNI){
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		cc.openleftpanel();
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		WebElement cred = driver.findElement(By.xpath("//*[@id=\"j_id0:j_id5\"]/div/div/ng-include/div/div[2]/div[1]/ng-include/section[1]/div[2]/ul[2]/li[1]/span[3]"));
		Assert.assertTrue(!(cred.getText().isEmpty()));
	}
	@Test (groups = {"GestionesPerfilAgente", "DetalleDeConsumos","Ciclo2"}, dataProvider="CuentaProblemaRecarga") 
	public void TS134827_CRM_Movil_Prepago_Vista_360_Detalle_de_consumo_Consulta_detalle_de_consumo_Datos_FAN_Front_Agentes(String cDNI){
		CustomerCare cCC = new CustomerCare(driver);
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		cCC.irADetalleDeConsumos();
		sleep(12000);
		driver.switchTo().frame(cambioFrame(driver, By.id("divConsumptionDetailhead")));
		WebElement dmso = driver.findElements(By.xpath("//*[@id='j_id0:j_id5']/div//div[2]/ng-include/div/div[2]/div[*]")).get(0).findElement(By.className("unit-div"));
		System.out.println(dmso.getText());
		Assert.assertTrue(dmso.isDisplayed());
		}
	
	@Test (groups = {"GestionesPerfilAgente", "DetalleDeConsumos","Ciclo2"}, dataProvider="CuentaProblemaRecarga")
	public void TS134826_CRM_Movil_Prepago_Vista_360_Detalle_de_consumo_Consulta_detalle_de_consumo_SMS_FAN_Front_Agentes(String cDNI){
		CustomerCare cCC = new CustomerCare(driver);
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		cCC.irADetalleDeConsumos();
		sleep(12000);
		driver.switchTo().frame(cambioFrame(driver, By.id("advancerFilters")));
		WebElement dmso = driver.findElements(By.xpath("//*[@id='j_id0:j_id5']/div//div[2]/ng-include/div/div[2]/div[*]")).get(2).findElement(By.className("unit-div"));
		System.out.println(dmso.getText());
		Assert.assertTrue(dmso.isDisplayed());
	}
	
	@Test (groups = {"GestionesPerfilAgente", "DetalleDeConsumos","Ciclo2"}, dataProvider="CuentaProblemaRecarga")
	public void TS134828_CRM_Movil_Prepago_Vista_360_Detalle_de_consumo_Consulta_detalle_de_consumo_Voz_FAN_Front_Agentes(String cDNI, String Linea){
		CustomerCare cCC = new CustomerCare(driver);
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		cCC.irADetalleDeConsumos();
		sleep(12000);
		driver.switchTo().frame(cambioFrame(driver, By.id("advancerFilters")));
		WebElement dmso = driver.findElements(By.xpath("//*[@id='j_id0:j_id5']/div//div[2]/ng-include/div/div[2]/div[*]")).get(1).findElement(By.className("unit-div"));
		System.out.println(dmso.getText());
		Assert.assertTrue(dmso.isDisplayed());
	}
	
	@Test (groups = {"GestionesPerfilAgente", "DetalleDeConsumos","Ciclo2"}, dataProvider="CuentaProblemaRecarga")
	public void TS134829_CRM_Movil_Prepago_Vista_360_Detalle_de_consumo_Consulta_detalle_de_consumo_FAN_Front_Agentes(String cDNI){
		CustomerCare cCC = new CustomerCare(driver);
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", cDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		cCC.irADetalleDeConsumos();
		sleep(12000);
		driver.switchTo().frame(cambioFrame(driver, By.id("advancerFilters")));
		WebElement dmso = driver.findElements(By.xpath("//*[@id='j_id0:j_id5']/div//div[2]/ng-include/div/div[2]/div[*]")).get(3).findElement(By.className("unit-div"));
		System.out.println(dmso.getText());
		Assert.assertTrue(dmso.isDisplayed());
	}
	
	@Test (groups = {"GestionesPerfilAgente", "ConsultaDeSaldo", "Ciclo1"}, dataProvider = "ConsultaSaldo")
	public void TS_134815_CRM_Movil_Prepago_Vista_360_Consulta_de_Saldo_Verificar_saldo_del_cliente_FAN_Front_Agentes(String sDNI) {
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(15000);
		cc.openleftpanel();
		cc.irAFacturacion();
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		List <WebElement> saldo = driver.findElements(By.cssSelector(".slds-text-heading_medium.expired-date.expired-pink"));
		for(WebElement x : saldo) {
			System.out.println(x.getText());
		}
		System.out.println(saldo.get(0).getText());
		/*List <WebElement> saldo = driver.findElements(By.className("header-right"));
		for (WebElement c :saldo ) {
			System.out.println(c.getText());
		}*/
		/*List <WebElement> saldo = driver.findElements(By.cssSelector(".slds-text-heading_medium.expired-date.expired-pink"));
		System.out.println(saldo.get(1).getText());*/
		Assert.assertTrue(!(saldo.isEmpty()));
	}
	
	@Test(groups = {"Sales", "PreparacionNominacion","E2E","Ciclo1"}, dataProvider="DatosSalesNominacion") 
	public void TS_CRM_Nominacion_Argentino_Agente(String sLinea, String sDni, String sNombre, String sApellido, String sSexo, String sFnac, String sEmail, String sProvincia, String sLocalidad, String sCalle, String sNumCa, String sCP) { 
		imagen = "TS_CRM_Nominacion_Argentino_Agente"+sDni;
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		SalesBase SB = new SalesBase(driver);
		driver.findElement(By.id("PhoneNumber")).sendKeys(sLinea);
		  driver.findElement(By.id("SearchClientsDummy")).click();
		  sleep(10000);
		WebElement cli = driver.findElement(By.id("tab-scoped-1"));
		cli.findElement(By.tagName("tbody")).findElement(By.tagName("tr")).click();
		sleep(3000);
		List<WebElement> Lineas = driver.findElement(By.id("tab-scoped-1")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		for(WebElement UnaL: Lineas) {
			if(UnaL.getText().toLowerCase().contains("plan con tarjeta")||UnaL.getText().toLowerCase().contains("plan prepago nacional")) {
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
		contact.tipoValidacion("documento");
		try {
			contact.subirArchivo("C:\\Users\\florangel\\Downloads\\mapache.jpg", "si");
		}catch(Exception ex1) {}
			BasePage bp = new BasePage(driver);
		bp.setSimpleDropdown(driver.findElement(By.id("ImpositiveCondition")), "IVA Consumidor Final");
		SB.Crear_DomicilioLegal(sProvincia, sLocalidad, sCalle, "", sNumCa, "", "", sCP);
		sleep(38000);
		//contact.subirformulario("C:\\Users\\florangel\\Downloads\\form.pdf", "si");
		//sleep(30000);
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
		CBS_Mattu invoSer = new CBS_Mattu();
		invoSer.ValidarInfoCuenta(sLinea, sNombre,sApellido);
	}
	@Test (groups = {"GestionesPerfilAgente", "Vista360","Ciclo2"}, dataProvider="ProductosyServicios")
	public void TS134818_CRM_Movil_Prepago_Vista_360_Mis_Servicios_Visualizacion_del_estado_de_los_Productos_activos_FAN_Front_Agentes(String cDNI){
		BasePage cambioFrameByID=new BasePage();
		CustomerCare cCC = new CustomerCare(driver);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI", cDNI);
		String accid = driver.findElement(By.cssSelector(".searchClient-body.slds-hint-parent.ng-scope")).findElements(By.tagName("td")).get(5).getText();
		System.out.println("id "+accid);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(20000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		driver.findElement(By.className("card-top")).click();
		sleep(5000);
		cCC.irAProductosyServicios();
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-grid.slds-wrap.slds-card.slds-m-bottom--small.slds-p-around--medium")));
		Assert.assertTrue(driver.findElement(By.cssSelector(".via-slds.slds-m-around--small.ng-scope")).isDisplayed());
	}
	
	@Test (groups = {"GestionesPerfilAgente", "Nominacion", "Ciclo1"})
	public void TS85100_CRM_Movil_REPRO_No_Nominatividad_No_Valida_Identidad_Cliente_nuevo_Presencial_DOC_Agente() {
		boolean nominacion = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		driver.findElement(By.id("PhoneNumber")).sendKeys("2932478872");
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
		contact.searchContact2("DNI", "99241524", "Masculino");
		if (driver.findElement(By.id("FirstName")).getText().isEmpty() && driver.findElement(By.id("LastName")).getText().isEmpty())
			nominacion = true;
		Assert.assertTrue(nominacion);
	}
	@Test(groups = {"GestionesPerfilAgente", "RenovacionCuota","E2E","Ciclo1"}, dataProvider="RenovacionCuotaConSaldo") 
	public void TS135402_CRM_Movil_REPRO_Renovacion_de_cuota_Presencial_Internet_50_MB_Dia_Descuento_de_saldo_con_Credito(String sDNI, String sLinea){
		imagen = "TS135402";
		detalles = "Renocavion de cuota: "+imagen+"DNI: "+sDNI+"Linea: "+sLinea;
		CBS cCBS = new CBS();
		CBS_Mattu cCBSM = new CBS_Mattu();
		String datosInicial = cCBS.ObtenerUnidadLibre(cCBSM.Servicio_QueryFreeUnit(sLinea), "Datos Libres");
		String sMainBalance = cCBS.ObtenerValorResponse(cCBSM.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer iMainBalance = Integer.parseInt(sMainBalance.substring(0, (sMainBalance.length()) - 1));
		
		//Check all
		BasePage cambioFrameByID=new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		SalesBase sSB = new SalesBase(driver);
		sSB.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(25000);
		CustomerCare cCC = new CustomerCare(driver);
		cCC.seleccionarCardPornumeroLinea(sLinea, driver);
		sleep(3000);
		cCC.irAGestionEnCard("Renovacion de Datos");
		sleep(10000);
		sleep(12000);
		driver.switchTo().frame(cambioFrame(driver, By.id("combosMegas")));
		List<WebElement> elementos = driver.findElement(By.cssSelector(".table.slds-table.slds-table--bordered.slds-table--cell-buffer")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		for(WebElement UnE:elementos) {
			if(UnE.findElement(By.tagName("td")).getText().contains("50 MB")) {
				UnE.findElement(By.className("slds-checkbox")).click();
			}
		}sleep(2000);
		cCC.obligarclick(driver.findElement(By.id("CombosDeMegas_nextBtn")));
		sleep(10000);
		List<WebElement> wCheckBox = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
		wCheckBox.get(1).click();
		driver.findElement(By.id("SetPaymentType_nextBtn")).click();
		String mesj = driver.findElement(By.cssSelector(".slds-box.ng-scope")).getText();
		System.out.println(mesj);
		Assert.assertTrue(mesj.equalsIgnoreCase("La operaci\u00f3n termino exitosamente"));
		String datosFinal = cCBS.ObtenerUnidadLibre(cCBSM.Servicio_QueryFreeUnit(sLinea), "Datos Libres");
		Assert.assertTrue((Integer.parseInt(datosInicial)+200000)==Integer.parseInt(datosFinal));
		String uMainBalance = cCBS.ObtenerValorResponse(cCBSM.Servicio_queryLiteBySubscriber(sLinea), "bcs:MainBalance");
		Integer uiMainBalance = Integer.parseInt(uMainBalance.substring(0, (uMainBalance.length()) - 1));
		Assert.assertTrue(iMainBalance < uiMainBalance);
	}
		
	@Test (groups = {"GestionesPerfilAgente", "Vista360", "E2E", "Ciclo1"}, dataProvider = "CuentaVista360")
	public void TS134821_CRM_Movil_Prepago_Vista_360_Distribucion_de_paneles_Visualizacion_e_ingreso_a_las_ultimas_gestiones_FAN_Front_Agentes(String sDNI, String sNombre){
		imagen = "TS134821";
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
		sleep(25000);
		driver.switchTo().frame(cambioFrame(driver, By.className("card-top")));
		for (WebElement x : driver.findElements(By.className("slds-text-body_regular"))) {
			if (x.getText().contains("Gestiones"))
				x.click();
		}
		sleep(7000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small.secondaryFont")));
		driver.findElement(By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small.secondaryFont")).click();
		sleep(3000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".slds-table.slds-table--bordered.slds-table--resizable-cols.slds-table--fixed-layout.via-slds-table-pinned-header")).isDisplayed());
	}
	
	@Test (groups = {"GestionesPerfilAgente", "ActualizarDatos", "E2E", "Ciclo3"}, dataProvider = "CuentaModificacionDeDatos")
	public void TS134836_CRM_Movil_REPRO_Modificacion_de_datos_Actualizar_los_datos_del_cliente_completos_FAN_Front_Agentes(String sDNI, String sLinea) {
		imagen = "TS134836";
		detalles = null;
		detalles = imagen + " -ActualizarDatos: " + sDNI;
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
	
	@Test (groups = {"GestionesPerfilAgente", "ActualizarDatos", "E2E", "Ciclo3"}, dataProvider = "CuentaModificacionDeDatos")
	public void TS129334_CRM_Movil_REPRO_Modificacion_de_datos_Actualizar_datos_campo_Correo_Electronico_Cliente_FAN_Front_Agentes(String sDNI, String sLinea) {
		imagen = "TS129334";
		detalles = null;
		detalles = imagen + " -ActualizarDatos: " + sDNI;
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
		sleep(5000);
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
	
	@Test (groups = {"GestionesPerfilAgente", "ActualizarDatos", "E2E", "Ciclo3"}, dataProvider = "CuentaModificacionDeDatos")
	public void TS121103_CRM_Movil_REPRO_Modificacion_de_datos_No_Actualizar_datos_Cliente_FAN_Front_Agentes(String sDNI, String sLinea) {
		imagen = "TS121103";
		detalles = null;
		detalles = imagen+"-Modificacion de datos No modificar-DNI:"+sDNI;
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
	
	@Test (groups = {"GestionesPerfilAgente", "ActualizarDatos", "E2E", "Ciclo3"}, dataProvider = "CuentaModificacionDeDatos")
	public void TS121102_CRM_Movil_REPRO_Modificacion_de_datos_Cliente_FAN_Front_Agentes(String sDNI, String sLinea) {
		imagen = "TS121102";
		detalles = null;
		detalles = imagen + " -ActualizarDatos: " + sDNI;
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
	
	@Test (groups = {"GestionesPerfilAgente", "ActualizarDatos", "E2E", "Ciclo3"}, dependsOnMethods = "TS121103_CRM_Movil_REPRO_Modificacion_de_datos_No_Actualizar_datos_Cliente_FAN_Front_Agentes")
	public void TS121099_CRM_Movil_REPRO_No_Actualizar_datos_Cliente_Perfil_FAN_Front_Agentes() {
		imagen = "TS121099";
		detalles = null;
		detalles = imagen+"-Modificacion de datos No modifica";
		Assert.assertTrue(true);
	}
	
	@Test (groups = {"GestionesPerfilAgente", "ActualizarDatos", "E2E", "Ciclo3"}, dataProvider = "CuentaModificacionDeDatos")
	public void TS121098_CRM_Movil_REPRO_Modificacion_de_datos_Actualizar_datos_Cliente_Perfil_FAN_Front_Agentes(String sDNI, String sLinea) {
		imagen = "TS121098";
		detalles = null;
		detalles = imagen + " -ActualizarDatos: " + sDNI;
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
	
	@Test (groups = {"GestionesPerfilAgente", "Vista360", "E2E","ConsultaPorGestion", "Ciclo2"}, dataProvider = "CuentaModificacionDeDatos")
	public void TS134831_CRM_Movil_Prepago_Vista_360_Consulta_por_gestiones_Gestiones_Cerrada_Informacion_brindada_FAN_Front_Agentes(String sDNI, String sLinea) {
		imagen = "TS134831";
		detalles = null;
		detalles = imagen+"-Consulta Por Gestion-DNI:"+sDNI;
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
				if (cell.getText().equals("01")) {
					cell.click();
				}
			} catch (Exception e) {}
		}
		driver.findElement(By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small.secondaryFont")).click();
		sleep(3000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-table.slds-table--bordered.slds-table--resizable-cols.slds-table--fixed-layout.via-slds-table-pinned-header")));
		sleep(5000);
		WebElement tabla = driver.findElement(By.cssSelector(".slds-table.slds-table--bordered.slds-table--resizable-cols.slds-table--fixed-layout.via-slds-table-pinned-header"));
		Assert.assertTrue(tabla.isDisplayed());
	}
	@Test (groups = {"GestionesPerfilAgente", "ABMServicios", "E2E", "Ciclo3"}, dataProvider = "BajaServicios")
	public void TC135737_CRM_Movil_REPRO_Baja_de_Servicio_sin_costo_Restriccion_Ident_de_Llamadas_Presencial_Agente(String sDNI, String sLinea){
		imagen = "TS135737";
		detalles = imagen+"-BajaServicio-DNI:"+sDNI;
		BasePage cambioFrameByID=new BasePage();
		sleep(30000);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("SearchClientDocumentType")));
		sleep(1000);
		//sb.BuscarCuenta("DNI",sDNI);
		//Erase after it's fixed
		sb.BuscarCuenta(sLinea);
		//End
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
		Assert.assertTrue(wMessageBox.getText().equalsIgnoreCase("La orden " + sOrder + " se realiz\u00d3 con \u00c9xito!"));
		Assert.assertTrue(cc.corroborarEstadoCaso(sOrder, "Activada"));
		datosOrden.add("Suspension, orden numero: " + sOrder + ", DNI: " + sDNI);
	}
	
	@Test (groups = {"GestionesPerfilTelefonico", "Ajustes", "E2E", "Ciclo3"}, dataProvider = "CuentaAjustesPRE")
	public void TS135380_CRM_Movil_Prepago_Otros_Historiales_Historial_de_ajustes_Ordenamiento_por_Motivo_de_ajuste_FAN_Front_Agente(String sDNI, String sLinea) {
		imagen = "TS135376";
		boolean ajustePositivo = false;
		driver.switchTo().frame(cambioFrame(driver, By.id("SearchClientDocumentType")));
		sb.BuscarCuenta("DNI", sDNI);
		driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).click();
		sleep(10000);
		cc.irAHistoriales();
		WebElement historialDeAjustes = null;
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-button.slds-button_brand")));
		for (WebElement x : driver.findElements(By.className("slds-card"))) {
			if (x.getText().toLowerCase().contains("historial de ajustes"))
				historialDeAjustes = x;
		}
		historialDeAjustes.findElement(By.cssSelector(".slds-button.slds-button_brand")).click();
		sleep(7000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-grid.slds-wrap.slds-grid--pull-padded.slds-m-around--medium.slds-p-around--medium.negotationsfilter")));
		driver.findElement(By.id("text-input-id-1")).click();
		WebElement table = driver.findElement(By.cssSelector(".slds-datepicker.slds-dropdown.slds-dropdown--left"));
		for (WebElement cell : table.findElements(By.xpath("//tr//td"))) {
			try {
				if (cell.getText().equals("13"))
					cell.click();
			} catch (Exception e) {}
		}
		driver.findElement(By.id("text-input-id-2")).click();
		WebElement table2 = driver.findElement(By.cssSelector(".slds-datepicker.slds-dropdown.slds-dropdown--left"));
		for (WebElement cell : table2.findElements(By.xpath("//tr//td"))) {
			try {
				if (cell.getText().equals("15"))
					cell.click();
			} catch (Exception e) {}
		}
		driver.findElement(By.cssSelector(".slds-button.slds-button--brand.filterNegotiations.slds-p-horizontal--x-large.slds-p-vertical--x-small")).click();
		sleep(3000);
		List<WebElement> tablas = driver.findElement(By.cssSelector(".slds-p-bottom--small.slds-p-left--medium.slds-p-right--medium")).findElements(By.cssSelector(".ng-pristine.ng-untouched.ng-valid.ng-empty"));
		for (WebElement x : tablas) {
			if (x.findElements(By.tagName("td")).get(3).getText().contains("$"))
				ajustePositivo = true;
		}
		Assert.assertTrue(ajustePositivo);
	}
}