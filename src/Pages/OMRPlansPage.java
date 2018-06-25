package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OMRPlansPage extends BasePage {
	final WebDriver driver;

	/* ELEMENTS */
	@FindBy(css = ".slds-button.cpq-item-has-children")
	private WebElement planButton;

	//Servicios Telefonia Movil
	@FindBy(xpath = "//div[contains(concat(' ',normalize-space(@class),' '),'cpq-item-base-product-name cpq-item-product-group js-cpq-cart-product-hierarchy-path-01tc000000578LBAAY<01tc000000578J0AAI')]//button")
	private WebElement serviciosTelefoniaMovil;
	
	//Servicios Basicos General Movil
	@FindBy(xpath = "//div[contains(concat(' ',normalize-space(@class),' '),'cpq-item-base-product-name cpq-item-product-group js-cpq-cart-product-hierarchy-path-01tc000000578LBAAY<01tc000000578KIAAY')]//button")
	private WebElement serviciosBasicosGeneralMovil;
	
	@FindBy(xpath = "//div[contains(concat(' ',normalize-space(@class),' '),'cpq-item-base-product-name cpq-item-product-group js-cpq-cart-product-hierarchy-path-01tc000000578LBAAY<01tc000000578KIAAY<01tc0000005M7ySAAS')]//button")
	private WebElement sbgmContestador;

	@FindBy(xpath = "//div[contains(concat(' ',normalize-space(@class),' '),'cpq-item-base-product-name cpq-item-product-group js-cpq-cart-product-hierarchy-path-01tc000000578LBAAY<01tc000000578KIAAY<01tc0000005JSuAAAW')]//button")
	private WebElement sbgmDDI;

	//Servicios Internet por Dia
	@FindBy(xpath = "//div[contains(concat(' ',normalize-space(@class),' '),'cpq-item-base-product-name cpq-item-product-group js-cpq-cart-product-hierarchy-path-01tc000000578LBAAY<01tc0000005M7yJAAS')]//button")
	private WebElement serviciosInternetPorDia;
	
	//Friends&Family
	@FindBy(xpath = "//div[contains(concat(' ',normalize-space(@class),' '),'cpq-item-base-product-name cpq-item-product-group js-cpq-cart-product-hierarchy-path-01tc000000578LBAAY<01tc000000578JtAAI')]//button")
	private WebElement friendsAndFamily;
	
	//Packs Opcionales
	@FindBy(xpath = "//div[contains(concat(' ',normalize-space(@class),' '),'cpq-item-base-product-name cpq-item-product-group js-cpq-cart-product-hierarchy-path-01tc000000578LBAAY<01tc0000005fMVtAAM')]//button")
	private WebElement packsOpcionales;
	
	//Renovacion de Cuota
	@FindBy(xpath = "//div[contains(concat(' ',normalize-space(@class),' '),'cpq-item-base-product-name cpq-item-product-group js-cpq-cart-product-hierarchy-path-01tc000000578LBAAY<01tc000000578KOAAY')]//button")
	private WebElement renovacionDeCuota;
	
	
	public OMRPlansPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	public WebElement getPlanButton() {
		return planButton;
	}
	
	public WebElement getServiciosTelefoniaMovil() {
		return serviciosTelefoniaMovil;
	}

	public WebElement getServiciosBasicosGeneralMovil() {
		return serviciosBasicosGeneralMovil;
	}

	public WebElement getSBGMContestador() {
		return sbgmContestador;
	}

	public WebElement getSBGMDDI() {
		return sbgmDDI;
	}

	public WebElement getServiciosInternetPorDia() {
		return serviciosInternetPorDia;
	}

	public WebElement getFriendsAndFamily() {
		return friendsAndFamily;
	}

	public WebElement getPacksOpcionales() {
		return packsOpcionales;
	}

	public WebElement getRenovacionDeCuota() {
		return renovacionDeCuota;
	}

	public void addServiceToCartByName(String service) {
		String addToCartButtonXpath = "//*[contains(text(),'" + service + "')]//../parent::*//../following-sibling::*//*[contains(concat(' ',normalize-space(@class),' '),'slds-button slds-button_neutral') and contains(text(),'Add to Cart')]";
		WebElement addToCartButton = driver.findElement(By.xpath(addToCartButtonXpath));
		addToCartButton.click();
	}
	
	private WebElement findShowActionsButtonByServiceName(String service) {
		String showActionsButtonXpath = "//*[contains(text(),'" + service + "')]//../parent::*//../following-sibling::*//*[contains(concat(' ',normalize-space(@class),' '),'slds-button slds-button_icon-border-filled cpq-item-actions-dropdown-button')]";
		return driver.findElement(By.xpath(showActionsButtonXpath));
	}
	
	public void deleteService(String service) {
		WebElement showActionsButton = findShowActionsButtonByServiceName(service);
		showActionsButton.click();
		WebElement deleteServiceButton = showActionsButton.findElement(By.xpath("//../following-sibling::*//span[contains(.,'Delete')]"));
		deleteServiceButton.click();
		WebElement confirmDeleteButton = driver.findElement(By.cssSelector(".slds-button.slds-button--destructive"));
		confirmDeleteButton.click();
	}
	
	public void configureService(String service) {
		WebElement showActionsButton = findShowActionsButtonByServiceName(service);
		showActionsButton.click();
		WebElement configureServiceButton = showActionsButton.findElement(By.xpath("//../following-sibling::*//span[contains(.,'Configure')]"));
		configureServiceButton.click();
	}
	
	//*[contains(text(),'Llamada en espera')]//../parent::*//../following-sibling::*//*[contains(concat(' ',normalize-space(@class),' '),'slds-button slds-button_icon-border-filled cpq-item-actions-dropdown-button')]//../following-sibling::*//span[contains(.,'Delete')]
}
