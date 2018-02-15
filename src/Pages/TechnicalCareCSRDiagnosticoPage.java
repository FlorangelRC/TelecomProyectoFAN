package Pages;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class TechnicalCareCSRDiagnosticoPage extends BasePage{
	
	final WebDriver driver;
	
	@FindBy(xpath="//*[@id=\"j_id0:j_id5\"]/div/div/ng-include/div/div[2]/div[2]/ng-include/section[2]/div[3]/ng-transclude/div/ng-include/div/div[1]")
	private List<WebElement> menuOpcion;
		
	
	@FindBy(xpath=".//*[@id='j_id0:j_id5']/div/div/ng-include/div/div[2]/div[2]/ng-include/section/div[1]")
	private WebElement planConTarjeta;
	
	@FindBy(xpath="//*[@id='j_id0:j_id5']/div/div/ng-include/div/div[2]/div[1]/ng-include/section/div[1]")
	private WebElement planConTarjeta2;
	
	

	public TechnicalCareCSRDiagnosticoPage(WebDriver driver){
		this.driver = driver;
        PageFactory.initElements(driver, this);

	}	
	
	public void clickOpt() throws InterruptedException {
		planConTarjeta.click();
		sleep(4000);
	}
	    

	
		
	
	public WebElement getPlanConTarjeta() {
		return planConTarjeta;
	}

	public WebElement getPlanConTarjeta2() {
		return planConTarjeta2;
	}
	
	
	public void scrollToElement(WebElement element) {
		((JavascriptExecutor)driver)
	        .executeScript("arguments[0].scrollIntoView();", element);
	  }

	public List<WebElement> getmenuOpcion() {
		return menuOpcion;
	}
}
