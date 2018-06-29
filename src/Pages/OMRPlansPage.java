package Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class OMRPlansPage extends BasePage {
	final WebDriver driver;
	static FluentWait<WebDriver> fluentWait;

	/* ELEMENTS */
	@FindBy(css = ".slds-button.cpq-item-has-children")
	private WebElement planButton;

	//Servicios Telefonia Movil
	@FindBy(xpath = "//div[contains(concat(' ',normalize-space(@class),' '),'cpq-item-base-product-name cpq-item-product-group js-cpq-cart-product-hierarchy-path-01tc000000578LBAAY<01tc000000578J0AAI')]//button")
	private WebElement serviciosTelefoniaMovil;
	
	//Servicios Basicos General Movil
	@FindBy(xpath = "//*[contains(text(),'Servicios Basicos General Movil')]//../parent::*//*[contains(concat(' ',normalize-space(@class),' '),'slds-button slds-button_icon-small')]")
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
		fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.withTimeout(45, TimeUnit.SECONDS)
			.pollingEvery(3, TimeUnit.SECONDS)
			.ignoring(org.openqa.selenium.NoSuchElementException.class)
			.ignoring(org.openqa.selenium.ElementNotVisibleException.class)
			;
	}
		
	public WebElement getPlanButton() {
		fluentWait.until(ExpectedConditions.elementToBeClickable(planButton));
		return planButton;
	}
	
	public WebElement getServiciosTelefoniaMovil() {
		fluentWait.until(ExpectedConditions.elementToBeClickable(serviciosTelefoniaMovil));
		return serviciosTelefoniaMovil;
	}

	public WebElement getServiciosBasicosGeneralMovil() {
		fluentWait.until(ExpectedConditions.elementToBeClickable(serviciosBasicosGeneralMovil));
		return serviciosBasicosGeneralMovil;
	}

	public WebElement getSBGMContestador() {
		fluentWait.until(ExpectedConditions.visibilityOf(sbgmContestador));
		return sbgmContestador;
	}

	public WebElement getSBGMDDI() {
		fluentWait.until(ExpectedConditions.visibilityOf(sbgmDDI));
		return sbgmDDI;
	}

	public WebElement getServiciosInternetPorDia() {
		fluentWait.until(ExpectedConditions.elementToBeClickable(serviciosInternetPorDia));
		return serviciosInternetPorDia;
	}

	public WebElement getFriendsAndFamily() {
		fluentWait.until(ExpectedConditions.elementToBeClickable(friendsAndFamily));
		return friendsAndFamily;
	}

	public WebElement getPacksOpcionales() {
		fluentWait.until(ExpectedConditions.elementToBeClickable(packsOpcionales));
		return packsOpcionales;
	}

	public WebElement getRenovacionDeCuota() {
		fluentWait.until(ExpectedConditions.elementToBeClickable(renovacionDeCuota));
		return renovacionDeCuota;
	}
	
	private WebElement findAddToCartButtonByServiceName(String service) {
		String addToCartButtonXpath = "//*[contains(text(),'" + service + "')]//../parent::*//../following-sibling::*//*[contains(concat(' ',normalize-space(@class),' '),'slds-button slds-button_neutral') and contains(text(),'Add to Cart')]";
		return driver.findElement(By.xpath(addToCartButtonXpath));
	}

	private WebElement findShowActionsButtonByServiceName(String service) {
		String showActionsButtonXpath = "//*[contains(text(),'" + service + "')]//../parent::*//../following-sibling::*//*[contains(concat(' ',normalize-space(@class),' '),'slds-button slds-button_icon-border-filled cpq-item-actions-dropdown-button')]";
		WebElement showActionsButton = fluentWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(showActionsButtonXpath))));
		return showActionsButton;
	}
	
	
	//*[contains(text(),'Llamada en espera')]//../parent::*//../following-sibling::*//*[contains(concat(' ',normalize-space(@class),' '),'slds-button slds-button_icon-border-filled cpq-item-actions-dropdown-button')]//../following-sibling::*//span[contains(.,'Delete')]

	
	public void addServiceToCartByName(String service) {
		WebElement addToCartButton = fluentWait.until(ExpectedConditions.visibilityOf(findAddToCartButtonByServiceName(service)));
		addToCartButton.click();
	}
		
	public void deleteService(String service) {
		WebElement showActionsButton = findShowActionsButtonByServiceName(service);
		showActionsButton.click();
//		WebElement deleteServiceButton = driver.findElement(By.xpath("//*[contains(text(),'Llamada en espera')]//../parent::*//../following-sibling::*//*[contains(concat(' ',normalize-space(@class),' '),'slds-button slds-button_icon-border-filled cpq-item-actions-dropdown-button')]//../following-sibling::*//span[contains(.,'Delete')]"));
		WebElement deleteServiceButton = fluentWait.until(ExpectedConditions.elementToBeClickable(showActionsButton.findElement(By.xpath("//../child::*//span[contains(.,'Delete')]"))));
		//*[contains(text(),'espera')]//../parent::*//../following-sibling::*//*[contains(concat(' ',normalize-space(@class),' '),'slds-button slds-button_icon-border-filled cpq-item-actions-dropdown-button')]//../following-sibling::*//span[contains(.,'Delete')]
		deleteServiceButton.click();
		WebElement confirmDeleteButton = fluentWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Delete')]"))));
		confirmDeleteButton.click();
	}
	
	//By.cssSelector(".slds-button.slds-button--destructive")
	
	public void configureService(String service) {
		WebElement showActionsButton = findShowActionsButtonByServiceName(service);
		showActionsButton.click();
		WebElement configureServiceButton = fluentWait.until(ExpectedConditions.elementToBeClickable(showActionsButton.findElement(By.xpath("//../following-sibling::*//*[contains(.,'Configure')]"))));
		configureServiceButton.click();
	}
	
	//*[contains(text(),'Llamada en espera')]//../parent::*//../following-sibling::*//*[contains(concat(' ',normalize-space(@class),' '),'slds-button slds-button_icon-border-filled cpq-item-actions-dropdown-button')]//../following-sibling::*//span[contains(.,'Delete')]
}
