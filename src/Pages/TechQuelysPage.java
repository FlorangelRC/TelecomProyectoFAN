package Pages;

import java.util.List;

import org.junit.FixMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class TechQuelysPage extends BasePage {
	
	private WebDriver driver;
	
	
	@FindBy(id="ChannelSelection")
	private WebElement channelSelection;
	
	@FindBy(id="ServiceSelection")
	private WebElement ServiceSelection;
	
		
	@FindBy(id="MotiveSelection")
	private WebElement MotiveSelection;
	
	@FindBy(id="SelfManagementStep_nextBtn")
	private WebElement selfManagementStepBtn;
	
	@FindBy(xpath=".//*[@class='borderOverlay']") 
	private  List<WebElement> borderOverlay;
	
	@FindBy(xpath=".//*[@id='KnowledgeBaseResults_nextBtn']") 
	private WebElement knowledgeBaseBtn;

	@FindBy(xpath=".//*[@id='CreatedClosedCaseText']/div/p/p/strong[1]")
	private WebElement numCaso;
	
	@FindBy(xpath=".//*[@id='SimilCaseInformation']/div/p/p[3]/strong[1]")
	private WebElement existCaso;
	
	@FindBy(id= "phSearchInput")
	private WebElement search;

	@FindBy(xpath="//*[@id='Case_body']/table/tbody/tr[2]/th/a") 
	private WebElement caseBody;

	@FindBy(className="optionContainer")
	private WebElement optionContainer;

	@FindBy(xpath=" //*[@id='00Nc0000002Ja0S_ilecell']")  
	private WebElement verificar;
	
	
	public void listadoDeSeleccion(String seleccion, String servicio, String motivo) {
		selectByText(getChannelSelection(),seleccion);
		selectByText(getServiceSelection(),servicio);
		selectByText(getMotiveSelection(),motivo) ;	
	}
	

	public TechQuelysPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public boolean elementExists(WebElement element) throws InterruptedException {
	    sleep(2000);

	    try {
	      boolean isDisplayed = element.getSize().height > 0;
	      return isDisplayed;
	    } catch (Exception ex) {
	      return false;
	    }
	  }
		
	public void clickOnButtons() throws InterruptedException  {
		scrollToElement(selfManagementStepBtn);
		selfManagementStepBtn.click();
		sleep(7000);
		if (!elementExists(existCaso)) { //!existCaso.isDisplayed()) {
		borderOverlay.get(1).click();
		sleep(4000);
		scrollToElement(knowledgeBaseBtn);
		knowledgeBaseBtn.click();
		sleep(3000);
		}
	}
	
	

	public void buscarCaso(String numero) throws InterruptedException{
				search.click();
				search.clear();
				search.sendKeys(numero);
				search.submit();
				sleep(5000);
		
				
	}

		
	public WebElement getExistCaso() {
		return existCaso;
	}


	public WebElement getChannelSelection() {
		return channelSelection;
	}


	public WebElement getServiceSelection() {
		return ServiceSelection;
	}
	
	public WebElement getMotiveSelection() {
		return MotiveSelection;
	}
	
	public WebElement getNumCaso(){
		return numCaso;
	}
	

	public WebElement getCaseBody(){
		return caseBody;
	}

	public List<WebElement> getOptionContainer(){
		return optionContainer.findElements(By.tagName("li"));
	}

	public WebElement getVerificar(){
		return verificar;
	}
	
	public String verificarCaso() throws InterruptedException {
		String caso="";
		if(elementExists(existCaso)) {
			 caso=existCaso.getText();
			}
			else {		
				caso=numCaso.getText();
			}
		driver.switchTo().defaultContent();
		buscarCaso(caso);
		driver.switchTo().defaultContent();
		return caso;			
			}
	
	private  void sleep(int miliseconds) throws InterruptedException {
	Thread.sleep(miliseconds);
	}
	
	
	public void scrollToElement(WebElement element) {
		((JavascriptExecutor)driver)
	        .executeScript("arguments[0].scrollIntoView();", element);
	  }
}



