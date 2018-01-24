package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ContactSearch extends BasePage {
	
final WebDriver driver;
	
	@FindBy (how = How.ID, using = "DocumentTypeSearch")
	private WebElement documentType;
	
	@FindBy (how = How.ID, using = "DocumentInputSearch")
	private WebElement document;
	
	@FindBy (how = How.CSS, using = ".slds-radio--faux.ng-scope")
	private List<WebElement> gender;
	
	@FindBy (how = How.ID, using = "ContactInfo_nextBtn")
	private WebElement next;
	
	@FindBy (how = How.CSS, using = ".vlc-slds-button--tertiary.ng-binding.ng-scope")
	private WebElement cancel;
	
public ContactSearch(WebDriver driver){
		this.driver = driver;
        PageFactory.initElements(driver, this);
}

public void searchContact(String docType, String docValue, String genero) {
	try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

	setSimpleDropdown(documentType, docType);
	document.click();
	document.sendKeys(docValue);
	try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

	switch(genero) {
	case "femenino":
		gender.get(0).click();
		break;
	case "masculino":
		gender.get(1).click();
		break;
	}
	driver.findElement(By.cssSelector(".OSradioButton.ng-scope.only-buttom")).click();
}

public void DNI(String DNI)
{
	document.sendKeys(DNI);
}
public void sex(String genero){
	switch(genero) {
	case "femenino":
		gender.get(0).click();
		break;
	case "masculino":
		gender.get(1).click();
		break;
	}
	
}


}
