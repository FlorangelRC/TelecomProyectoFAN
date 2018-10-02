package Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import Tests.TestBase;

public class ManejoCaja extends BasePage {
	
	private String UrlCaja ="https://10.75.39.140:8081/main.action?ssLogin=true&BMEWebToken=be935f78-f517-441c-a299-c5a1ba3f1f411b7c8915-7f90-4b1d-bee6-15837afe7b05" ;
	private String usuario = "CBS593572";
	private String clave = "Testa10k";
	
	@FindBy(id="login")
	private WebElement pantaLog;
	
	@FindBy(id="topFrame")
	private WebElement cintaSuperior;
	
	@FindBy(id="sitemap")
	private WebElement menu;
	
	@FindBy(id="catalog")
	private WebElement catalogo;
	
	@FindBy(className="crm_sitemap_category")
	private List<WebElement> subMenu;
	
	@FindBy(id="commitCashRegisterButton")
	private WebElement botonIniciarCaja;
	
	@FindBy(id="resetCashRegisterButton")
	private WebElement botonReiniciarCaja;
	
	@FindBy(id="cashRegPrintButton")
	private WebElement botonImprimirCaja;
	
	@FindBy(id="cashRegCloseButton")
	private WebElement botonCerrarCaja;
	
	TestBase TB = new TestBase();
	
	
	public void ingresarCaja(WebDriver driver) {
		driver.get(UrlCaja);
		sleep(10000);
		Select idiom = new Select(driver.findElement(By.id("login")).findElement(By.id("language")));
		//System.out.println(driver.findElement(By.id("login")).getText());
		idiom.selectByVisibleText("Spanish");
		sleep(3000);
		//user.sendKeys(usuario);
		driver.findElement(By.id("login")).findElement(By.id("username")).sendKeys(usuario);
		driver.findElement(By.id("login")).findElement(By.id("password")).sendKeys(clave);
		driver.findElement(By.id("login")).findElement(By.id("submitBtn")).click();
		sleep(5000);
	}
	
	public void configuracionesIniciales(WebDriver driver) {
		new Select(driver.findElement(By.id("topFrame")).findElement(By.id("prj_select"))).selectByVisibleText("MSC");
		if(!driver.findElement(By.id("topFrame")).findElement(By.id("current_BE_ID")).getText().equalsIgnoreCase("TA")) {
			driver.findElement(By.id("topFrame")).findElement(By.id("current_BE_ID")).click();
			sleep(2000);
			driver.findElement(By.className("popwin_middle_center")).findElement(By.cssSelector(".bc_btn.bc_ui_ele")).click();
			sleep(6000);
			System.out.println(driver.findElement(By.className("popwin_middle_center")).getText());
			driver.switchTo().frame(TB.cambioFrame(driver,By.id("bepicker_select")));
			List <WebElement> opc = driver.findElements(By.tagName("a"));
			for(WebElement UnaO: opc) {
				if(UnaO.getText().toLowerCase().contains("ta")) {
					UnaO.findElement(By.tagName("ins")).click();
					break;
				}
			}
			driver.findElement(By.id("bepicker_select")).click();
			sleep(4000);
			//driver.findElement(By.className("popwin_middle_center")).findElement(By.cssSelector(".bc_btn.bc_ui_ele")).click();
		}
	}
	
	public void seleccionarOpcionCatalogo(WebDriver driver, String opcion) {
		driver.findElement(By.id("pluginbar")).findElement(By.id("sitemap")).click();
		sleep(2000);
		driver.switchTo().frame(TB.cambioFrame(driver,By.id("catalog")));
		List <WebElement> opcMenu = driver.findElement(By.id("catalog")).findElements(By.className("body2"));
		for(WebElement UnaO: opcMenu) {
			if(UnaO.getText().equalsIgnoreCase(opcion)) {
				UnaO.click();
				break;
			}
		}
	}
	
	public void seleccionarOpcionSubMenu(WebDriver driver, String SO) {
		boolean enc = false;
		List<WebElement> subMenu = driver.findElement(By.id("sitemap")).findElement(By.cssSelector(".crm_sitemap_body.selected")).findElements(By.className("crm_sitemap_category"));
		for(WebElement UnS: subMenu) {
			List<WebElement> subOpciones = UnS.findElements(By.tagName("span"));
			for(WebElement UnaS: subOpciones) {
				if (UnaS.getText().equalsIgnoreCase(SO)) {
					System.out.println(UnaS.getText());
					UnaS.findElement(By.tagName("a")).click();
					enc=true;
					break;
				}
			}
			if(enc == true)
				break;
		}
	}
	
	public void cerrarCajaRegistradora(WebDriver driver) {
		seleccionarOpcionSubMenu(driver, "Cerrar caja registradora");
		sleep(2000);
		driver.switchTo().frame(TB.cambioFrame(driver,By.id("closeview")));
		driver.findElement(By.id("closeview")).findElement(By.id("cashRegPrintButton")).click();
		sleep(1000);
		driver.switchTo().defaultContent();
		driver.findElement(By.className("popwin_middle_center")).findElement(By.cssSelector(".bc_btn.bc_ui_ele")).click();
		sleep(5000);
		//driver.switchTo().frame(TB.cambioFrame(driver,By.id("print-preview")));
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
		driver.findElement(By.className("cancel")).click();
		driver.switchTo().window(tabs2.get(0));
		//driver.findElement(By.className("popwin_close")).sendKeys(Keys.ESCAPE);
		sleep(1000);
		driver.findElement(By.id("popwin_close")).click();
		driver.switchTo().frame(TB.cambioFrame(driver,By.id("closeview")));
		driver.findElement(By.id("closeview")).findElement(By.id("cashRegCloseButton")).click();
		sleep(3000);
		driver.switchTo().defaultContent();
		driver.findElement(By.className("popwin_middle_center")).findElement(By.cssSelector(".bc_btn.bc_ui_ele")).click();
		cerrarPestanias(driver);
		
	}
	
	public void abrirCajaRegistradora(WebDriver driver) {
		seleccionarOpcionSubMenu(driver, "Abrir caja registradora");
		sleep(2000);
		driver.switchTo().frame(TB.cambioFrame(driver,By.id("openCashRegView")));
		WebElement montoCaja = driver.findElement(By.id("openCashRegView")).findElement(By.id("inputCurrency_value"));
		montoCaja.clear();
		montoCaja.sendKeys("10,00");
		driver.findElement(By.id("openCashRegView")).findElement(By.id("commitCashRegisterButton")).click();
		sleep(1000);
		driver.switchTo().defaultContent();
		driver.findElement(By.className("popwin_middle_center")).findElement(By.cssSelector(".bc_btn.bc_ui_ele")).click();
		sleep(3000);
		driver.findElement(By.className("popwin_middle_center")).findElement(By.cssSelector(".bc_btn.bc_ui_ele")).click();	
		cerrarPestanias(driver);
		
	}
	
	public void cerrarPestanias(WebDriver driver) {
		List<WebElement> pestanias = driver.findElement(By.id("tabpage")).findElements(By.className("bc_tabitem_close"));
		pestanias.remove(0);
		for(WebElement UnaP:pestanias) {
			UnaP.click();
		}
	}
	
	public void pagarEfectivo(WebDriver driver,String prefactura, String cuenta) {// nueva 20181001000000095169
		seleccionarOpcionCatalogo(driver, "Cuentas por cobrar");
		seleccionarOpcionSubMenu(driver, "Pago");
		sleep(2000);
		driver.switchTo().frame(TB.cambioFrame(driver,By.id("queryUserInfoButton")));
		driver.findElement(By.id("balanceAdjustQueryCondition_content")).findElement(By.id("paymentType_condition_input_1")).click();
		driver.findElement(By.id("balanceAdjustQueryCondition_content")).findElement(By.id("acctCode_condition_input_value")).sendKeys(cuenta);
		driver.findElement(By.id("balanceAdjustQueryCondition_content")).findElement(By.id("invoiceNo_condition_input_value")).sendKeys(prefactura);
		driver.findElement(By.id("balanceAdjustQueryCondition_content")).findElement(By.id("queryUserInfoButton")).click();
		//cerrarPestanias(driver);
	}
	
	public void pagarTC(WebDriver driver,String prefactura, String cuenta) {//otra prefactura 20181001000000095162
		seleccionarOpcionCatalogo(driver, "Cuentas por cobrar");
		seleccionarOpcionSubMenu(driver, "Pago");
		sleep(2000);
		driver.switchTo().frame(TB.cambioFrame(driver,By.id("queryUserInfoButton")));
		driver.findElement(By.id("balanceAdjustQueryCondition_content")).findElement(By.id("paymentType_condition_input_1")).click();
		driver.findElement(By.id("balanceAdjustQueryCondition_content")).findElement(By.id("acctCode_condition_input_value")).sendKeys(cuenta);
		driver.findElement(By.id("balanceAdjustQueryCondition_content")).findElement(By.id("invoiceNo_condition_input_value")).sendKeys(prefactura);
		driver.findElement(By.id("balanceAdjustQueryCondition_content")).findElement(By.id("queryUserInfoButton")).click();
		sleep(4000);
		driver.findElement(By.id("invoiceInfoPanel_content")).findElement(By.id("selected_0")).click();
		sleep(4000);
		driver.findElement(By.id("salesInvoiceDetailIOList")).findElement(By.id("selectedPayment_0")).click();
		sleep(4000);
		Select pinpad = new Select(driver.findElement(By.id("wonderSoftPanel")).findElement(By.name("#BMEModel.paymentInfo4WonderSoft.pinPadSerial")));
		driver.findElement(By.id("paymentMethodPanel_content")).findElement(By.id("submitButton")).click();
		
		
		//cerrarPestanias(driver);
	}
}
