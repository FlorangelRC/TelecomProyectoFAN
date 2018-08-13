package Tests;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.CBS;
import Pages.setConexion;

public class CBS_Mattu extends TestBase {
	
	private WebDriver driver;
	
	//-------------------------------------------------------------------------------------------------
	//@Befor&After
	//@BeforeClass(alwaysRun=true)
	public void readySteady() throws Exception {
		this.driver = setConexion.setupEze();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		loginCBS(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	//@AfterClass(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC =
	@Test
	public void openPage() {
		String sEndPoint = "Pago en Caja";
		String sPaymentChannelID = "1006";
		String sAccountKey = "9900000687010001";
		String sPaymentMethod = "1001";
		String sAmount = "97420000";
		String sInvoiceno = "20180812000000056508";
		String sPaymentSerialNo = ((new java.text.SimpleDateFormat("yyyyMMddHHmmss")).format(new Date())).toString()+Integer.toString((int)(Math.random()*1000));
		
		SOAPClientSAAJ sSCS = new SOAPClientSAAJ();
		CBS cCBS = new CBS();
		String sResponse = sSCS.callSoapWebService(cCBS.sRequest(sPaymentSerialNo, sPaymentChannelID, sAccountKey, sPaymentMethod, sAmount, sInvoiceno), sEndPoint);
		System.out.println("sResponse: " + sResponse);
	}
	
	@Test
	public void openPage2(String sOrder) {
		String sEndPoint = "Pago Simulado";
		//String sOrder = "00072466";
		
		SOAPClientSAAJ sSCS = new SOAPClientSAAJ();
		CBS cCBS = new CBS();
		String sResponse = sSCS.callSoapWebService(cCBS.sRequestByOrder(sOrder), sEndPoint);
		System.out.println("sResponse: " + sResponse);
	}
	
}