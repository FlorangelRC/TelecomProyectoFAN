package Tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.ContactSearch;
import Pages.CustomerCare;
import Pages.HomeBase;
import Pages.OM;
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
	
	@BeforeClass(alwaysRun=true)
	public void Init2() {
		driver = setConexion.setupEze();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}		
			 loginAgente(driver);  
			 try {Thread.sleep(22000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
				
				driver.findElement(By.id("tabBar")).findElement(By.tagName("a")).click();
				sleep(18000);
				SalesBase SB = new SalesBase(driver);
				driver.switchTo().defaultContent();
				sleep(3000);
				goToLeftPanel2(driver, "Inicio");
				sleep(18000);
				try{
					SB.cerrarPestaniaGestion(driver);}
				catch(Exception ex1) {
				}
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
		
		sleep(14000);
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.id("SearchClientDocumentNumber")));
	}
	
	//@AfterMethod(alwaysRun=true)
		public void deslogin(){
			sleep(2000);
			SalesBase SB = new SalesBase(driver);
			driver.switchTo().defaultContent();
			sleep(6000);
			SB.cerrarPestaniaGestion(driver);
			
			sleep(5000);

		}
	
	//@AfterClass(alwaysRun=true)
	public void Exit() {
		driver.quit();
		sleep(2000);
	}
	
	
	@Test(groups={"Sales", "AltaLineaDatos"}, priority=1, dataProvider="DatosAltaLineaAgente")
	public void TS_CRM_Alta_de_Linea_Agente(String sDni, String sNombre, String sApellido, String sSexo, String sFNac, String sEmail, String sPlan, String sProvincia, String sLocalidad) throws IOException {
		CustomerCare cc = new CustomerCare(driver);
		SalesBase sb = new SalesBase(driver);
		sleep(5000);
		sb.Crear_Cliente(sDni);
		ContactSearch contact = new ContactSearch(driver);
		contact.sex(sSexo);
		contact.Llenar_Contacto(sNombre, sApellido, sFNac);
		driver.findElement(By.id("EmailSelectableItems")).findElement(By.tagName("input")).sendKeys(sEmail);
		driver.findElement(By.id("Contact_nextBtn")).click();
		sleep(18000);
		sb.elegirplan(sPlan);
		sb.continuar();
		sleep(20000);
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
		cc.obligarclick(driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")));
		sleep(20000);
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
	
	@Test(groups={"Sales", "AltaLineaDatos"}, priority=2, dataProvider="DatosAltaLineaOfCom")
	public void TS_CRM_Alta_de_Linea_OfCom(String sDni, String sNombre, String sApellido, String sSexo, String sFNac, String sEmail, String sPlan, String sProvincia, String sLocalidad) throws IOException {
		CustomerCare cc = new CustomerCare(driver);
		SalesBase sb = new SalesBase(driver);
		sleep(15000);
		sb.Crear_Cliente(sDni);
		ContactSearch contact = new ContactSearch(driver);
		contact.sex(sSexo);
		contact.Llenar_Contacto(sNombre, sApellido, sFNac);
		driver.findElement(By.id("EmailSelectableItems")).findElement(By.tagName("input")).sendKeys(sEmail);
		driver.findElement(By.id("Contact_nextBtn")).click();
		sleep(18000);
		sb.elegirplan(sPlan);
		sleep(10000);
		sb.continuar();
		sleep(20000);
		sb.Crear_DomicilioLegal(sProvincia, sLocalidad, "falsa", "", "1000", "", "", "1549");
		sleep(24000);
		WebElement sig = driver.findElement(By.id("LineAssignment_nextBtn"));
		cc.obligarclick(sig);
		sleep(20000);
		cc.obligarclick(driver.findElement(By.id("InvoicePreview_nextBtn")));
		sleep(20000);
		cc.obligarclick(driver.findElement(By.id("SelectPaymentMethodsStep_nextBtn")));
		sleep(20000);
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
	
	
}
