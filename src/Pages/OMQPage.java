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

	private static final String pDatos = null;

	final WebDriver driver;
	
	@FindBy(xpath=".//*[@id='hotlist']/table/tbody/tr/td[2]/input")
	private WebElement NewOrder;
	
	@FindBy(xpath= ".//*[@id='topButtonRow']/input[9]")
	private WebElement CPQ;

	@FindBy(className="slds-button slds-button_neutral cpq-add-button")
	private WebElement Agregar;	
	
	@FindBy(xpath="//*[@id=\"js-cpq-product-cart-config-form\"]/div[1]/div/form/div[4]/div[1]/input")
	private WebElement NumerodeLinea;
	
								
	@FindBy(name = "productconfig_field_3_1") 
	private WebElement ICCID;
	
	@FindBy(name = "productconfig_field_3_2") 
	private WebElement IMSI;
	
	@FindBy(name = "productconfig_field_3_3")
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
		sleep(4000);
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
		sleep(5000);
		driver.findElement(By.cssSelector(".slds-button.cpq-item-has-children")).click();
		sleep(3000);
		driver.findElement(By.xpath(".//*[@id='tab-default-1']/div[1]/ng-include/div/div/div/div[4]/div[2]/div/ng-include/div/div[2]/ng-include/div/div[1]/div/div[2]/div[11]")).click();
		List<WebElement> lista = driver.findElements(By.cssSelector(".slds-dropdown__list.cpq-item-actions-dropdown__list"));
		//System.out.println(lista.size());
		lista.get(1).click();
		sleep(3000);
		List<WebElement> todos = driver.findElements(By.cssSelector(".slds-form_stacked.ng-pristine.ng-untouched.ng-valid.vlocity-dynamic-form.ng-valid-required.ng-valid-step")).get(1).findElements(By.className("slds-form-element"));
		 ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.className("slds-section")).getLocation().y+" )");
		 for (WebElement uno : todos) {
			 if(uno.findElement(By.tagName("label")).getText().equalsIgnoreCase("ICCID")) {
				 uno.click();
				 uno.findElement(By.tagName("input")).clear();
				 uno.findElement(By.tagName("input")).sendKeys(""+r.nextInt(200000));
			 }
			 if(uno.findElement(By.tagName("label")).getText().equalsIgnoreCase("IMSI")) {
				 uno.click();
				 uno.findElement(By.tagName("input")).clear();
				 uno.findElement(By.tagName("input")).sendKeys(""+r.nextInt(200000));
			 }
			 if(uno.findElement(By.tagName("label")).getText().equalsIgnoreCase("KI")) {
				 uno.click();
				 uno.findElement(By.tagName("input")).clear();
				 uno.findElement(By.tagName("input")).sendKeys(""+r.nextInt(200000));
				 uno.findElement(By.tagName("input")).submit();
				 break;
			 }
			 
		 }
		/* driver.findElement(By.name("productconfig_field_3_1")).click();
		 driver.findElement(By.name("productconfig_field_3_1")).clear();
		 driver.findElement(By.name("productconfig_field_3_1")).sendKeys(""+r.nextInt(200000));
		 /*ICCID.click();
		 ICCID.clear();
		 ICCID.sendKeys(""+r.nextInt(200000));*/
		 /*sleep(2000);
		 IMSI.click();
		 IMSI.clear();
		 IMSI.sendKeys(""+r.nextInt(200000));
		 sleep(2000);
		 KI.click();
		 KI.clear();
		 KI.sendKeys(""+r.nextInt(200000));*/
		 //KI.submit();
		sleep(5000);
		//driver.switchTo().defaultContent();
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/ng-include/div/div[2]/div[2]/div[3]/div/div/ng-include/div/div[1]/div/button")).click();
		sleep(5000);
		 
		
	}
	

	
	public void agregarPack(String servicio) {
		sleep(8000);
		driver.findElement(By.cssSelector(".slds-button.cpq-item-has-children")).click();
		List<WebElement> list1 = driver.findElements(By.xpath("//*[@class='cpq-item-product-child-level-1 cpq-item-child-product-name-wrapper']//*[@class='slds-button slds-button_icon-small']"));
		list1.get(3).click();
		sleep(8000);
		List<WebElement> pDatos = driver.findElements(By.xpath("//*[@class='cpq-item-product-child-level-2 cpq-item-child-product-name-wrapper']//*[@class='slds-button slds-button_icon-small']"));
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.xpath("//*[@class='cpq-item-product-child-level-2 cpq-item-child-product-name-wrapper']//*[@class='slds-button slds-button_icon-small']")).getLocation().y+")");
		pDatos.get(2).click();
		 List<WebElement> tablas=driver.findElements(By.className("cpq-item-base-product-details"));

		 //subtista
		 List<WebElement> servicios=tablas.get(0).findElements(By.cssSelector(".cpq-item-base-product-name-field.cpq-item-text-value.cpq-item-product-title"));
		 for(WebElement S:servicios) {
			   if(S.getText().toLowerCase().contains(servicio.toLowerCase())) {
				   System.out.println(servicio);
				   try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();	}
				   		List<WebElement> Agregar=driver.findElements(By.xpath(".//*[@id='tab-default-1']/div[1]//ng-include/div//div[11]/button")); //findElement(By.className("slds-button slds-button_neutral")).findElements(By.tagName("div")).get(0);
				   			Agregar.get(10).click();
							break;
							
				  		}
			 //Click ViewRecord
				driver.findElement(By.id("-import-btn")).click();
				sleep(7000);
		 }

 }
		 

			   
	
	
			
		


	public WebElement getNewOrder() {
		return NewOrder;
	}


	public WebElement getCPQ() {
		return CPQ;
	}
	
	public void scrollToElement(List<WebElement> pDatos2) {
		((JavascriptExecutor)driver)
	        .executeScript("arguments[0].scrollIntoView();", pDatos2);
	  }
	



public void scrollToElement(WebElement element) {
	((JavascriptExecutor)driver)
    .executeScript("arguments[0].scrollIntoView();", element);

}
}


