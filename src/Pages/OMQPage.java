package Pages;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.sun.corba.se.pept.transport.Connection;

import Tests.TestBase;
import javafx.scene.control.ScrollToEvent;
import org.json.JSONObject;

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
	
	@FindBy(xpath = "//*[@class='cpq-item-child-product-name-wrapper']")
	private List<WebElement> Pack;
	

	


	


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
		
		public void Gestion_Alta_De_Linea(String Cuenta, String Plan) throws InterruptedException {
			OM pageOm=new OM(driver);
			OMQPage OM=new OMQPage (driver);
			pageOm.crearOrden(Cuenta);
			assertTrue(driver.findElement(By.cssSelector(".noSecondHeader.pageType")).isDisplayed());
			pageOm.agregarGestion("Venta");
			sleep(2000);
			OM.getCPQ().click();
			sleep(5000);
			OM.colocarPlan1(Plan);
			OM.configuracion();
			sleep(5000);
			driver.findElement(By.name("ta_submit_order")).click();
			sleep(45000);
			pageOm.cambiarVentanaNavegador(1);
			sleep(2000);
			driver.findElement(By.id("idlist")).click();
			sleep(5000);
			pageOm.cambiarVentanaNavegador(0);
			sleep(12000);
			pageOm.completarFlujoOrquestacion();
			sleep(5000);
			driver.findElement(By.id("accid_ileinner")).findElement(By.tagName("a")).click();
			sleep(10000);
			//pageOm.irAChangeToOrder();
			
		}
		
		public void colocarPlan1(String PlandeServicio) throws InterruptedException{
		       sleep(3000);
		       driver.switchTo().defaultContent();
		       sleep(6000);
		      	    driver.findElement(By.cssSelector(".slds-input.ng-pristine.ng-untouched.ng-valid")).sendKeys(PlandeServicio);		
		      	  sleep(6000);			
		      	    		List<WebElement> productos = driver.findElements(By.cssSelector(".slds-media.cpq-product-item-container"));
		      	    		List<WebElement> botones = driver.findElements(By.cssSelector(".slds-button.slds-button_neutral.cpq-add-button"));
		      	    		for(int i=0;i<= productos.size();i++) {
		      	    			
		      	    		if (productos.get(i).getText().substring(0,productos.get(i).getText().indexOf("\n")).equalsIgnoreCase(PlandeServicio)) {
								
								botones.get(i).click();
									break;
		      	  			}
		      	  		}
		      	  		
		    
		}
		
		public void configuracion() {
		sleep(2000);
		driver.switchTo().defaultContent();
		sleep(9000);
		driver.findElement(By.xpath(".//*[@id='tab-default-1']/div/ng-include//div[10]//button")).click();
		sleep(2000);
		List<WebElement> list = driver.findElements(By.cssSelector(".slds-dropdown__item.cpq-item-actions-dropdown__item")); 
		//System.out.println(list.size());
		list.get(2).click();
		sleep(2000);
		agregarNumerodeLinea();  
		SimCard();
		driver.findElement(By.id("-import-btn")).click();
		sleep(5000);
			
						
		}
		
			
	public void agregarNumerodeLinea() {
		Random r = new Random();
		driver.switchTo().defaultContent();
		List<WebElement> posibles = driver.findElement(By.name("productconfig")).findElements(By.className("slds-form-element"));
		for(WebElement UnP : posibles) {
			if(UnP.findElement(By.tagName("label")).getText().equalsIgnoreCase("numero de linea")) {
				 UnP.click();
				 UnP.findElement(By.tagName("input")).clear();
				 UnP.findElement(By.tagName("input")).sendKeys("11" + r.nextInt(200000000));
				 UnP.submit();
				 break;
			}
		}
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
		
		sleep(5000);
		//driver.switchTo().defaultContent();
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/ng-include/div/div[2]/div[2]/div[3]/div/div/ng-include/div/div[1]/div/button")).click();
		sleep(5000);
		 
		
	}
	public void fechaAv(String fecha) {
	driver.findElement(By.id("RequestDate")).sendKeys(fecha);
	driver.findElement(By.cssSelector(".form-control.btn.btn-primary.ng-binding")).click();
	sleep(12000);
	
}
	
	public List <String> SimCard2() {
		Random r = new Random();
		List<String> datos = new ArrayList<String>();
		sleep(7000);
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
				 datos.add(uno.findElement(By.tagName("input")).getAttribute("value"));
				 
			 }
			 if(uno.findElement(By.tagName("label")).getText().equalsIgnoreCase("IMSI")) {
				 uno.click();
				 uno.findElement(By.tagName("input")).clear();
				 uno.findElement(By.tagName("input")).sendKeys(""+r.nextInt(200000));
				 datos.add(uno.findElement(By.tagName("input")).getAttribute("value"));
			 }
			 if(uno.findElement(By.tagName("label")).getText().equalsIgnoreCase("KI")) {
				 uno.click();
				 uno.findElement(By.tagName("input")).clear();
				 uno.findElement(By.tagName("input")).sendKeys(""+r.nextInt(200000));
				 datos.add(uno.findElement(By.tagName("input")).getAttribute("value"));
				 uno.findElement(By.tagName("input")).submit();
				 break;
			 }
			 
			 
		 }
		 
		sleep(5000);
	
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/ng-include/div/div[2]/div[2]/div[3]/div/div/ng-include/div/div[1]/div/button")).click();
		sleep(5000);
		return(datos);
		
	}

	
	public void agregarPack(String servicio1, String servicio2,String Pack1,String Pack2,String Pack3) {
		driver.findElement(By.cssSelector(".slds-button.cpq-item-has-children")).click();
		sleep(5000);
		List<WebElement> NomPack = driver.findElements(By.xpath("//*[@class='cpq-item-product-child-level-1 cpq-item-child-product-name-wrapper']"));
		
		for(WebElement a: NomPack) {
			System.out.print(a.getText().toLowerCase());
			System.out.println(" : "+servicio1.toLowerCase());
				if (a.getText().toLowerCase().contains(servicio1.toLowerCase())) {
					System.out.println(servicio1);
						a.findElement(By.tagName("button")).click();
							sleep(8000);
								break;
							}
						}
	
		List<WebElement> subPack = driver.findElements(By.xpath("//*[@class='cpq-item-product-child-level-2 cpq-item-child-product-name-wrapper']"));
		List<WebElement> Btnsubpack = driver.findElements(By.xpath("//*[@class='cpq-item-product-child-level-2 cpq-item-child-product-name-wrapper']//*[@class='slds-button slds-button_icon-small']"));			
		if (subPack.size() == Btnsubpack.size()) {
		for(WebElement b: subPack) {			
			System.out.println("+++++"+b.getText().substring(b.getText().indexOf("\n")+1, b.getText().length())+"++++++");
			
			//System.out.print(b.getText().toLowerCase());
			//System.out.println(" : "+servicio2.toLowerCase());
					if (b.getText().substring(b.getText().indexOf("\n")+1, b.getText().length()).toLowerCase().contains(servicio2.toLowerCase())) {
						System.out.println(servicio2);
						  b.findElement(By.tagName("button")).click();
						   sleep(8000);
						     break;
						}
					}
			}
		
		
	
		
		
		
		 //subtablas
		 List<String> packs= Arrays.asList(Pack1, Pack2, Pack3);
		 List<WebElement> Pack = driver.findElements( By.xpath("//*[@class='cpq-item-product-child-level-3 ng-not-empty ng-valid']//*[@class='cpq-item-no-children']"));
		 List<WebElement> Agregar=driver.findElements(By.xpath("//*[@class='cpq-item-product-child-level-3 ng-not-empty ng-valid']//*[@class='slds-button slds-button_neutral']"));
		 if (Pack.size() == Agregar.size()) {
		 for (int i = 0; i < Pack.size(); i++) {
			 for (int j = 0; j < packs.size(); j++) {
					if (Pack.get(i).getText().equals(packs.get(j))) {
						System.out.println(packs);
						Agregar.get(i).click();
						sleep(8000);
						break;						
					}
			 	}
		 	}
		 }	
	}



	
		

			
public void sincroProducto() {//(String Products) {
	
	//boolean a= false;
	driver.switchTo().defaultContent();
	((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.xpath("//*[@id='bodyCell']/div[6]/div[1]/div/div[2]/table")).getLocation().y+")");
	WebElement productos=driver.findElement(By.xpath("//*[@id='bodyCell']/div[6]/div[1]/div/div[2]/table/tbody/tr[*]/th/a"));
	productos.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[6]/div[1]/div/div[2]/table/tbody/tr[2]/th/a")).click();
	System.out.println(productos.getText());	
	sleep(25000);
	/*List<WebElement> prod=driver.findElements(By.xpath("//*[@id='bodyCell']/div[6]/div[1]/div/div[2]/table/tbody/tr[*]/th/a"));
	
	for (int i = 0; i < prod.size(); i++) {
		if (prod.get(i).getText().equals(Products)) {
			a=true;
			 System.out.println(prod.get(i).getText());
			 prod.get(i).click();
				sleep(5000);
			 break;*/
	}

	//Boton sincronizar
	
	public void clickSincronizar() {
		WebElement sincronizar= driver.findElement(By.id("topButtonRow")).findElement(By.xpath("//*[@id=\"topButtonRow\"]/input[6]"));
		sincronizar.click();
		driver.switchTo().defaultContent();
		try{driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div/div/div[1]/div/form/div[3]/button")).click();
		
		}catch(org.openqa.selenium.NoSuchElementException e) {
		sleep(18000);
		OM pageOm=new OM(driver);
		pageOm.cambiarVentanaNavegador(1);
		sleep(16000);
		driver.findElement(By.id("idlist")).click();
		sleep(15000);
		pageOm.cambiarVentanaNavegador(0);
		sleep(15000);
		driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div/div/div[1]/div/form/div[3]/button")).click();
		sleep(40000);
		
	}
		
	
}
		public void request(String S203,String envio, String S326) {
			OM pageOm=new OM(driver);
			boolean chiqui = false;
			while (chiqui == false) {

				try {
					driver.findElement(By.id("zoomOut")).click();
				} catch (Exception ex1) {
					chiqui = true;
					driver.findElement(By.id("zoomIn")).click();
					break;
				}

			}
			sleep(10000);
			List<WebElement> cajas = driver.findElements(By.cssSelector(".item-label-container.item-header.item-failed"));
			cajas.addAll(driver.findElements(By.cssSelector(".item-label-container.item-header.item-fatally-failed")));
			cajas.addAll(driver.findElements(By.cssSelector(".item-label-container.item-header.item-running")));
			int i = 1;
			while (cajas.size() > 0) {
			for (WebElement UnaC : cajas) {
				if (UnaC.getText().equalsIgnoreCase(S203)) {
				UnaC.click();
				sleep(5000);
				pageOm.cambiarVentanaNavegador(i);
				//i++;
				sleep(5000);
				List<WebElement> botones = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-scope"));
				for (WebElement UnB : botones) {
					if (UnB.getText().equals("Complete")) {
						UnB.click();
						sleep(4000);
						System.out.println("Hizo click");
						driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div/ng-view/div/div/div/ul/li[2]/a")).click();
						sleep(4000);
						driver.findElement(By.xpath(".//*[@id='bodyCell']//table/tbody/tr/td[3]//a")).click();
						sleep(4000);								
						WebElement verirequest = driver.findElement(By.xpath(".//*[@id='bodyCell']//table/tbody/tr[2]/td//json-value/pre"));
						JSONObject obj = new JSONObject(verirequest.getText());
						String infoProducto=obj.getJSONArray("listaOfertasAdicionales").getJSONObject(0).toString(0);
						System.out.println(infoProducto);
						pageOm.cambiarVentanaNavegador(0);
								//return !infoProducto.isEmpty();
								//sleep(4000);
						break;
						 
				
						}
					}
				}
		
				if (UnaC.getText().equalsIgnoreCase(envio)) {
					UnaC.click();
					sleep(5000);
					pageOm.cambiarVentanaNavegador(i);
					//i++;
					sleep(5000);
					List<WebElement> botones = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-scope"));
				for (WebElement UnB : botones) {
					if (UnB.getText().equals("Complete")) {
						UnB.click();
						sleep(4000);
						System.out.println("Hizo click");
						pageOm.cambiarVentanaNavegador(0);
						break;
				}
			}
		}
				
				if (UnaC.getText().equalsIgnoreCase(S326)) {
					UnaC.click();
					sleep(5000);
					pageOm.cambiarVentanaNavegador(i);
					//i++;
					sleep(5000);
					List<WebElement> botones = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-scope"));
				for (WebElement UnB : botones) {
					if (UnB.getText().equals("Complete")) {
						UnB.click();
						sleep(4000);
						System.out.println("Hizo click");
						pageOm.cambiarVentanaNavegador(0);
						break;
				}
			}
		}
				
				sleep(10000);
				pageOm.cambiarVentanaNavegador(0);
				sleep(10000);
				pageOm.closeAllOtherTabs();
				sleep(35000);
				break;
						
			}
						cajas = driver.findElements(By.cssSelector(".item-label-container.item-header.item-failed"));
						cajas.addAll(driver.findElements(By.cssSelector(".item-label-container.item-header.item-fatally-failed")));
						cajas.addAll(driver.findElements(By.cssSelector(".item-label-container.item-header.item-running")));
			}
				
						pageOm.closeAllOtherTabs();
						sleep(5000);
						driver.findElement(By.className("submit-button")).click();
						sleep(6000);
						pageOm.cambiarVentanaNavegador(1);
						sleep(5000);
						pageOm.closeAllOtherTabs();
					
						
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















