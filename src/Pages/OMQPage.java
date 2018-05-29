package Pages;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import javafx.scene.control.ScrollToEvent;

public class OMQPage extends BasePage {

	final WebDriver driver;
	
	@FindBy(xpath=".//*[@id='hotlist']/table/tbody/tr/td[2]/input")
	private WebElement NewOrder;
	
	@FindBy(xpath= ".//*[@id='topButtonRow']/input[9]")
	private WebElement CPQ;

	@FindBy(className="slds-button slds-button_neutral cpq-add-button")
	private WebElement Agregar;	
	


	public OMQPage (WebDriver driver){
		this.driver = driver;
			PageFactory.initElements(driver, this);
	}
	
	
	public void CrearOrden() {
		NewOrder.click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("accid")).sendKeys("Cambia OM");
		driver.findElement(By.className("dateFormat")).click();
		Select Estado= new Select(driver.findElement(By.id("Status")));
		Estado.selectByVisibleText("Draft");
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.name("save")).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		assertTrue(driver.findElement(By.cssSelector(".noSecondHeader.pageType")).isDisplayed());
		sleep(3000);
	}
	
		public void colocarPlan(String PlandeServicio) throws InterruptedException{
	       sleep(3000);
	       driver.switchTo().defaultContent();
	       sleep(3000);
	      	    driver.findElement(By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid")).sendKeys(PlandeServicio);		
	      	  sleep(3000);
	      	    		List<WebElement> agregar = driver.findElements(By.cssSelector(".slds-button.slds-button_neutral.cpq-add-button")); 
	      	    			agregar.get(0).click();
	      	    			sleep(3000);
			
	      }
		
		public void configuracion() {
			sleep(2000);
			driver.switchTo().defaultContent();
			sleep(2000);
			driver.findElement(By.xpath(".//*[@id='tab-default-1']/div[1]/ng-include/div/div/div/div[3]/div[10]/div/button")).click();
			sleep(2000);
			List<WebElement> list = driver.findElements(By.cssSelector(".slds-dropdown__item.cpq-item-actions-dropdown__item"));
			System.out.println(list.size());
			list.get(2).click();
			sleep(2000);
			
		}
		  
	
	
	
			
		


	public WebElement getNewOrder() {
		return NewOrder;
	}


	public WebElement getCPQ() {
		return CPQ;
	}
	
	public void scrollToElement(WebElement element) {
		((JavascriptExecutor)driver)
	        .executeScript("arguments[0].scrollIntoView();", element);
	  }

}

