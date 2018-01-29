package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Tests.TestBase;

public class Marketing extends BasePage {
	
	final WebDriver driver;
	
	//Constructor
	public Marketing(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void ClubPersonal (String sAltaBajaModificación) {
		WebElement wMenuABM = driver.findElement(By.cssSelector(".slds-grid.slds-wrap.via-slds-action-grid-card"));
		List<WebElement> lMenuesABM = wMenuABM.findElements(By.cssSelector(".slds-size--1-of-2.slds-x-small-size--1-of-3.slds-medium-size--1-of-4.slds-large-size--1-of-6.slds-align--absolute-center.slds-m-bottom--xx-small.slds-m-top--xx-small.slds-p-left--xx-small.slds-p-right--xx-small.slds-grid"));
		
		switch (sAltaBajaModificación.toLowerCase()) {
			case "alta":
				lMenuesABM.get(0).click();
				break;
			case "baja":
				lMenuesABM.get(1).click();
				break;
			case "modificacion":
				lMenuesABM.get(2).click();
				break;
			default:
				System.out.println("Las opciones validas solo son 'alta', 'baja', y 'modificacion'.");
		}
	}
	
	public void CloseActiveTab () {
		CustomerCare cCC = new CustomerCare(driver);
		WebElement wCloseTab = cCC.obtenerPestañaActiva();
		Actions aAction = new Actions(driver);
		WebElement wClose = wCloseTab.findElement(By.className("x-tab-strip-close"));
		aAction.moveToElement(wClose).perform();
		wClose.click();
	}
	
	/*Datos login Marketing:s
		Username: usit@telecom.sit
		Password: pruebas09
	*/
}