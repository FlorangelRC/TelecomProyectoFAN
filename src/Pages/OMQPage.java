package Pages;
import static org.testng.Assert.assertTrue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.sun.corba.se.pept.transport.Connection;

import Tests.TestBase;
import javafx.scene.control.ScrollToEvent;

public class OMQPage extends BasePage {

	final WebDriver driver;
	
	@FindBy(xpath=".//*[@id='hotlist']/table/tbody/tr/td[2]/input")
	private WebElement NewOrder;
	
	@FindBy(xpath= ".//*[@id='topButtonRow']/input[9]")
	private WebElement CPQ;

	@FindBy(className="slds-button slds-button_neutral cpq-add-button")
	private WebElement Agregar;	
	
	@FindBy(xpath="//*[@id=\"js-cpq-product-cart-config-form\"]/div[1]/div/form/div[4]/div[1]/input")
	private WebElement NumerodeLinea;
	
	@FindBy(xpath=".//*[@id='js-cpq-product-cart-config-form']/div[1]/div/form/div[14]/div/input")
	private WebElement ICCID;
	
	@FindBy(xpath=".//*[@id='js-cpq-product-cart-config-form']/div[1]/div/form/div[15]/div/input")
	private WebElement IMSI;
	
	@FindBy(xpath=".//*[@id='js-cpq-product-cart-config-form']/div[1]/div/form/div[16]/div/input")
	private WebElement KI;
	


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
	       sleep(6000);
	      	    driver.findElement(By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid")).sendKeys(PlandeServicio);		
	      	  sleep(6000);
	      	    		List<WebElement> agregar = driver.findElements(By.cssSelector(".slds-button.slds-button_neutral.cpq-add-button")); 
	      	    			agregar.get(0).click();
	      	    			sleep(6000);
			
	      }
		
		public void configuracion() {
		sleep(2000);
		driver.switchTo().defaultContent();
		sleep(2000);
		driver.findElement(By.xpath(".//*[@id='tab-default-1']/div/ng-include//div[10]//button")).click();
		sleep(2000);
		List<WebElement> list = driver.findElements(By.cssSelector(".slds-dropdown__item.cpq-item-actions-dropdown__item")); 
		//System.out.println(list.size());
		list.get(2).click();
		agregarNumerodeLinea();
		SimCard();
		driver.findElement(By.id("-import-btn")).click();
		sleep(5000);
			
						
		}
		
			
	public void agregarNumerodeLinea() {
		Random r = new Random();
		driver.switchTo().defaultContent();
		NumerodeLinea.click();
		//NumerodeLinea.sendKeys("3413103661");
		NumerodeLinea.sendKeys("11" + r.nextInt(200000000) );
		NumerodeLinea.submit();
		sleep(8000);
		//driver.switchTo().defaultContent();
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/ng-include/div/div[2]/div[2]/div[3]/div/div/ng-include/div/div[1]/div/button")).click();
		sleep(5000);
	}
	
	
	public void SimCard() {
		Random r = new Random();
		driver.findElement(By.cssSelector(".slds-button.cpq-item-has-children")).click();
		sleep(3000);
		driver.findElement(By.xpath(".//*[@id='tab-default-1']/div[1]/ng-include/div/div/div/div[4]/div[2]/div/ng-include/div/div[2]/ng-include/div/div[1]/div/div[2]/div[11]")).click();
		List<WebElement> lista = driver.findElements(By.cssSelector(".slds-dropdown__list.cpq-item-actions-dropdown__list"));
		//System.out.println(lista.size());
		lista.get(1).click();
		sleep(3000);
		 ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.className("slds-section")).getLocation().y+" )");
		 ICCID.click();
		 ICCID.sendKeys(""+r.nextInt(200000));
		 sleep(2000);
		 IMSI.click();
		 IMSI.sendKeys(""+r.nextInt(200000));
		 sleep(2000);
		 KI.click();
		 KI.sendKeys(""+r.nextInt(200000));
		 KI.submit();
		sleep(5000);
		//driver.switchTo().defaultContent();
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/ng-include/div/div[2]/div[2]/div[3]/div/div/ng-include/div/div[1]/div/button")).click();
		sleep(5000);
		 
		
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

