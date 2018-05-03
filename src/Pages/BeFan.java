package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BeFan extends BasePage{

	//*********************************CONSTRUCTOR******************************************************//
	final WebDriver driver;
	
	public BeFan(WebDriver driver){
		this.driver = driver;
			PageFactory.initElements(driver, this);
	}
	
	//*********************************ELEMENTOS******************************************************//
	
	@FindBy(xpath="//*[@id='NotUpdatedPhoneMessage']/div/p/p[2]/span/strong")
	private WebElement NotUpdatedPhoneMessage;
}
