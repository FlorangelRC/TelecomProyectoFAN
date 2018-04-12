package Pages;

import java.util.List;

import org.junit.FixMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class TechnicalCareCSRAutogestionPage extends BasePage {
	
	private WebDriver driver;
	
	
	@FindBy(id="ChannelSelection")
	private WebElement channelSelection;
	
	@FindBy(id="ServiceSelection")
	private WebElement ServiceSelection;
	
	@FindBy(id="ServiceSelection")
	private List<WebElement> Service;
			
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
	

	
	public TechnicalCareCSRAutogestionPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	
	
////...................................................MenudeOpciones......................................///
	
		public void listadoDeSeleccion(String seleccion, String servicio, String motivo) {
		selectByText(getChannelSelection(),seleccion);
		selectByText(getServiceSelection(),servicio);
		selectByText(getMotiveSelection(),motivo) ;	
	}
		
		public void listadeseleccion(String canal,String servicio,String inconveniente) throws InterruptedException{
	           sleep(5000);
	           selection(channelSelection,canal);
	           selection(ServiceSelection,servicio);
	           selection(MotiveSelection,inconveniente);
	           }

	  private void selection(WebElement elemento, String opcion){
	    elemento.click();
	             WebElement tabla = driver.findElement(By.cssSelector(".slds-list--vertical.vlc-slds-list--vertical"));
	                List<WebElement> canales= tabla.findElements(By.tagName("li"));
	                  for(WebElement opt : canales ){
	                    if (opt.getText().toLowerCase().contains(opcion.toLowerCase())) {
	                      opt.click();
	                    }
	                  }
	                }
		
		public void canal(String canal) throws InterruptedException{
		       sleep(5000);
		       driver.switchTo().frame(getFrameForElement(driver, By.id("SelfManagementFields")));
		       channelSelection.click();
		        sleep(5000);
		          WebElement tabla = driver.findElement(By.cssSelector(".slds-list--vertical.vlc-slds-list--vertical"));
		            List<WebElement> canales= tabla.findElements(By.tagName("li"));
		              for(WebElement opt : canales ){
		                if (opt.getText().toLowerCase().contains(canal.toLowerCase())) {
		                	opt.click();
		                      	System.out.println("Se selecciono el servicio: " +opt.getText());
		                      		sleep(3000);
		                      			break; 
				            			  }
				            		  }
				            	  }           
		                	        
		                
		          
		  
	////...................................................listaOpciones......................................///
		
		public  boolean verificarOpciones(WebElement element,String data){
		    Select select = new Select(element);
		    List<WebElement> options = select.getOptions();

		    for (WebElement opt : options) {
		        if (opt.getText().toLowerCase().contains(data.toLowerCase())) {
		         return true;
		        }
		    }
		    return false;
		}
		

		
////...................................................verificarsiexistecaso......................................///
	
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
		if (!elementExists(existCaso)) { 
		borderOverlay.get(1).click();
		sleep(4000);
		scrollToElement(knowledgeBaseBtn);
		knowledgeBaseBtn.click();
		sleep(3000);
		}
	}
	
////...................................................verificarcaso...........................................///
	
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
	
	public List<WebElement> getService() {
		return Service;
	}
	
	public WebElement getVerificar(){
		return verificar;
	}
	
		
	public void scrollToElement(WebElement element) {
		((JavascriptExecutor)driver)
	        .executeScript("arguments[0].scrollIntoView();", element);
	  }

	

	

}