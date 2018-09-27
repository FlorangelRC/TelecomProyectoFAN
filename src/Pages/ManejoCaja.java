package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ManejoCaja extends BasePage {
	
	private String UrlCaja ="https://10.75.39.140:8081/main.action?ssLogin=true&BMEWebToken=be935f78-f517-441c-a299-c5a1ba3f1f411b7c8915-7f90-4b1d-bee6-15837afe7b05" ;
	private String usuario = "CBS593572";
	private String clave = "Testa10k";
	
	@FindBy(id="login")
	private WebElement pantaLog;
	
	private WebElement user = pantaLog.findElement(By.id("username"));

	private WebElement pass = pantaLog.findElement(By.id("password"));
	
	private Select idioma = new Select(pantaLog.findElement(By.id("languaje")));
	
	private WebElement boton_login = pantaLog.findElement(By.id("submitBtn"));
	
	@FindBy(id="topFrame")
	private WebElement cintaSuperior;
	
	private Select selectorProyecto = new Select (cintaSuperior.findElement(By.id("prj_select")));
	
	
	
	@FindBy(id="sitemap")
	private WebElement menu;
	
	@FindBy(id="catalog")
	private WebElement catalogo;
	
	@FindBy(className="crm_sitemap_category")
	private List<WebElement> subMenu;
	
	@FindBy(id="inputCurrency_value")
	private WebElement montoCaja;
	
	@FindBy(id="commitCashRegisterButton")
	private WebElement botonIniciarCaja;
	
	@FindBy(id="resetCashRegisterButton")
	private WebElement botonReiniciarCaja;
	
	@FindBy(id="cashRegPrintButton")
	private WebElement botonImprimirCaja;
	
	@FindBy(id="cashRegCloseButton")
	private WebElement botonCerrarCaja;
	
	
	
	
	public void ingresarCaja(WebDriver driver) {
		driver.get(UrlCaja);
		sleep(10000);
		Select idiom = new Select(driver.findElement(By.id("login")).findElement(By.id("language")));
		//System.out.println(driver.findElement(By.id("login")).getText());
		idiom.selectByVisibleText("Spanish");
		sleep(3000);
		driver.findElement(By.id("login")).findElement(By.id("username")).sendKeys(usuario);
		driver.findElement(By.id("login")).findElement(By.id("password")).sendKeys(clave);
		driver.findElement(By.id("login")).findElement(By.id("submitBtn")).click();
		sleep(5000);
	}
	
	public void configuracionesIniciales(WebDriver driver) {
		//driver.findElement(By.className("popwin_middle_center")).findElement(By.className("bc_btn bc_ui_ele")).click();
		selectorProyecto.deselectByVisibleText("MSC");
		if(!cintaSuperior.findElement(By.id("current_BE_ID")).getText().equalsIgnoreCase("TA")) {
			cintaSuperior.findElement(By.id("current_BE_ID")).click();
			driver.findElement(By.className("popwin_middle_center")).findElement(By.className("bc_btn bc_ui_ele")).click();
			sleep(4000);
			List <WebElement> opc = driver.findElement(By.id("beTree")).findElements(By.tagName("a"));
			for(WebElement UnaO: opc) {
				if(UnaO.getText().toLowerCase().contains("ta")) {
					UnaO.findElement(By.tagName("ins")).click();
					break;
				}
			}
			driver.findElement(By.id("bepicker_select")).click();
			sleep(4000);
		}
		driver.findElement(By.className("popwin_middle_center")).findElement(By.className("bc_btn bc_ui_ele")).click();
	}
	
	public void seleccionarOpcionCatalogo(WebDriver driver, String opcion) {
		menu.click();
		List <WebElement> opcMenu = catalogo.findElements(By.className("body2"));
		for(WebElement UnaO: opcMenu) {
			if(UnaO.getText().equalsIgnoreCase(opcion)) {
				UnaO.click();
				break;
			}
		}
	}
	
	public void seleccionarOpcionSubMenu(WebDriver driver, String SO) {
		boolean enc = false;
		for(WebElement UnS: subMenu) {
			List<WebElement> subOpciones = UnS.findElements(By.tagName("span"));
			for(WebElement UnaS: subOpciones) {
				if (UnaS.getText().equalsIgnoreCase(SO)) {
					UnaS.click();
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
		if (driver.findElement(By.className("popwin_middle_center")).findElement(By.className("bc_btn bc_ui_ele")).isDisplayed())
			driver.findElement(By.className("popwin_middle_center")).findElement(By.className("bc_btn bc_ui_ele")).click();
		else
		{
			botonImprimirCaja.click();
			sleep(1000);
			driver.findElement(By.className("popwin_middle_center")).findElement(By.className("bc_btn bc_ui_ele")).click();
			sleep(5000);
			driver.findElement(By.className("cancel")).click();
			driver.findElement(By.className("popwin_close")).sendKeys(Keys.ESCAPE);
			sleep(1000);
			driver.findElement(By.id("popwin_close")).click();
			botonCerrarCaja.click();
			driver.findElement(By.className("popwin_middle_center")).findElement(By.className("bc_btn bc_ui_ele")).click();
		}
	}
	
	public void abrirCajaRegistradora(WebDriver driver) {
		seleccionarOpcionSubMenu(driver, "Abrir caja registradora");
		if (driver.findElement(By.className("popwin_middle_center")).findElement(By.className("bc_btn bc_ui_ele")).isDisplayed())
			driver.findElement(By.className("popwin_middle_center")).findElement(By.className("bc_btn bc_ui_ele")).click();
		else
		{
			montoCaja.clear();
			montoCaja.sendKeys("10,00");
			botonIniciarCaja.click();
			sleep(1000);
			driver.findElement(By.className("popwin_middle_center")).findElement(By.className("bc_btn bc_ui_ele")).click();
			sleep(1000);
			driver.findElement(By.className("popwin_middle_center")).findElement(By.className("bc_btn bc_ui_ele")).click();
		}	
		
	}
	
	public void cerrarPestanias(WebDriver driver) {
		List<WebElement> pestanias = driver.findElements(By.className("bc_tabitem_close"));
		for(WebElement UnaP:pestanias) {
			UnaP.click();
		}
	}

}
