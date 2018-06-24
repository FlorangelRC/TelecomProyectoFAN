package Pages;

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
	
	
	//button[contains(concat(' ',normalize-space(@class),' '),'slds-button slds-button_neutral') and contains(text(),'Add to Cart')]//../preceding-sibling::*//*[contains(text(),'Llamada en espera')]
	
	//*[contains(text(),'Llamada en espera')]//../preceding-sibling::*//*[contains(concat(' ',normalize-space(@class),' '),'slds-button slds-button_neutral') and contains(text(),'Add to Cart')]//button
	
	//*[contains(text(),'Llamada en espera')]//../parent::*//../following-sibling::*//*[contains(concat(' ',normalize-space(@class),' '),'slds-button slds-button_neutral') and contains(text(),'Add to Cart')]//button

	
	//PERFECT!!!
	//*[contains(text(),'Llamada en espera')]//../parent::*//../following-sibling::*//*[contains(concat(' ',normalize-space(@class),' '),'slds-button slds-button_neutral') and contains(text(),'Add to Cart')]
	
	//*[contains(text(),'Llamada en espera')]//../parent::*//../following-sibling::*//*[contains(concat(' ',normalize-space(@class),' '),'slds-button slds-button_neutral') and contains(text(),'Add to Cart')]
	
	
	[../preceding-sibling::div[contains(text(),'Llamada en espera')]]

			
	
	//*[(class = slds-button slds-button_neutral) and contains(text(),'Add to cart')]//*[contains(text(),'Llamada en espera')]
	
	//
	cpq-product-cart-item-child
	//cpq-product-name js-cpq-cart-product-hierarchy-path-01tc000000578LBAAY<01tc000000578KIAAY
	
	//CallerID
	cpq-item-base-product
	/* Caller ID Text -->>*/ cpq-product-name js-cpq-cart-product-hierarchy-path-01tc000000578LBAAY<01tc000000578KIAAY<01tc000000578KDAAY
	slds-button slds-button_icon-border-filled cpq-item-actions-dropdown-button
	//*[contains(text(),'Caller Id')]

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
}
