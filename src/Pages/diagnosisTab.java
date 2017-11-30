package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class diagnosisTab extends BasePage{
	
final WebDriver driver;

@FindBy (how = How.ID, using = "SelectedMotives")
private WebElement motives;

@FindBy (how = How.CLASS_NAME, using = "ng-binding")
private List<WebElement> buttons;

public diagnosisTab(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
}

public void setMotive() {
	setSimpleDropdown(motives, "No me funciona internet");
}

public Boolean isExecuteButtonPresent() {
	Boolean a = false;
	if(buttons.get(14).getText().equals("Ejecutar")) {
		a = true;
	}
	return a;	
}

public void clickOnExeccute() {
	buttons.get(14).click();
}
}
