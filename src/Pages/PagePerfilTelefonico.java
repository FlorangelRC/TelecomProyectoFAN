package Pages;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.BasePage;
import Pages.Marketing;
import Pages.OM;
import Pages.SalesBase;
import Pages.setConexion;
import Tests.TestBase;

/**
 * @author Quelys
 *
 */
public class PagePerfilTelefonico extends TestBase{
	
	
	
	public PagePerfilTelefonico(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="SetPaymentType_nextBtn")
	private WebElement Tipodepago;
	
	@FindBy(id="InvoicePreview_nextBtn")
	private WebElement SimulaciondeFactura;
	
	@FindBy(id="DeliveryMethodConfiguration_nextBtn")
	private WebElement Delivery;
	
	@FindBy(id="SaleOrderMessages_nextBtn")
	private WebElement OrdenSeRealizoConExito;
	
	@FindBy(id="SelectPaymentMethodsStep_nextBtn")
	private WebElement MediodePago;

	
	
	public WebElement getMediodePago() {
		return MediodePago;
	}


	public WebElement getOrdenSeRealizoConExito() {
		return OrdenSeRealizoConExito;
	}


	public WebElement getSimulaciondeFactura() {
		return SimulaciondeFactura;
	}


	public WebElement getTipodepago() {
		return Tipodepago;
	}
	
	public WebElement getDelivery() {
		return Delivery;
	}


	public void buscarAssert() {
	sleep(8000);
	driver.findElement(By.cssSelector(".slds-tree__item.ng-scope")).findElement(By.tagName("div")).click();
	sleep(12000);
	driver.switchTo().frame(cambioFrame(driver, By.className("card-top"))); 
	sleep(8000);
	driver.findElement(By.className("card-top")).click(); 
	sleep(12000); 
	}
	
	
	public void comprarPack(String pack) {
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".items-card.ng-not-empty.ng-valid")));
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".slds-grid.community-flyout-content")).getLocation().y+")");
		List<WebElement> comprar = driver.findElements(By.className("community-flyout-grid-items-card"));
		for (WebElement comp : comprar) {
			if (comp.getText().toLowerCase().contains(pack)) {
				comp.findElement(By.tagName("button")).click();
				sleep(45000);
				break;
			}
		}		

	}
	
	public void agregarPack(String Pack1) {
		sleep(5000);		
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-button.cpq-item-has-children")));
		Pack("Packs Opcionales", "Packs de Datos", Pack1);
		driver.findElement(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand")).click();
		sleep(45000);
		try{ 
		      driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")).get(1).click(); 
		      sleep(8000); 
		    }catch(Exception ex1){} 
		sleep(12000); 
	}
	
	public void PackCombinado(String Pack1) {
		sleep(5000);		
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-button.cpq-item-has-children")));
		Pack("Packs Opcionales", "Packs Combinados", Pack1);
		sleep(10000);
		driver.findElement(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand")).click();
		sleep(25000);
		try{ 
		      driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")).get(1).click(); 
		      sleep(8000); 
		    }catch(Exception ex1){} 
		sleep(12000); 
	}
	
	
	
	public void tipoDePago(String tipodepago) {
	List<WebElement> tipodePago = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
	for (WebElement pago : tipodePago) {
		//System.out.print(pago.getText().toLowerCase());
			if (pago.getText().toLowerCase().contains(tipodepago)) {
				System.out.println(tipodepago);
					pago.findElement(By.tagName("span")).click();
						sleep(8000);
							break;
						}
					}
				}
	
	public void siguiente() {
	sleep(5000);
	WebElement siguiente=driver.findElement(By.className("vlc-control-wrapper"));
	((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+siguiente.getLocation().y+")");
	sleep(8000);
	try {driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope")).get(1).click();}
		catch(Exception ex1){
			sleep(8000);
		 	 try {driver.findElement(By.id("Step_Error_Huawei_S029_nextBtn")).click();}
		 	 	catch(org.openqa.selenium.ElementNotVisibleException EnviodefacturayDatos) {
		 	 		sleep(8000);
		 	 		try {driver.findElement(By.id("Step_Error_Huawei_S013_nextBtn")).click();}
		 	 			catch(org.openqa.selenium.ElementNotVisibleException facturayDatos) {
		 	 				sleep(8000);
		 	 			}
					}
				}
			}
			
		

	
	public void mododeEntrega() {
		BasePage cambioFrameByID=new BasePage();
		sleep(12000);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("DeliveryMethodSelection")));
		sleep(15000);
		Select metodoEntrega = new Select (driver.findElement(By.id("DeliveryMethodSelection")));
		metodoEntrega.selectByVisibleText("Store Pick Up");
		Select State = new Select (driver.findElement(By.id("PickState")));
		State.selectByVisibleText("Ciudad Aut\u00f3noma de Buenos Aires");
		Select City = new Select (driver.findElement(By.id("PickCity")));
		City.selectByVisibleText("CIUD AUTON D BUENOS AIRES");
		Select Store = new Select (driver.findElement(By.id("Store")));
		Store.selectByVisibleText("Centro de Servicio Santa Fe - Juan de Garay 444");
		Delivery.click();
		sleep(25000);
		SimulaciondeFactura.click();
		}
	
	
	public void Pack(String servicio1, String servicio2,String Pack1){
		CustomerCare cCC = new CustomerCare(driver);
		sleep(5000);
		driver.findElement(By.cssSelector(".slds-button.cpq-item-has-children")).click();
		sleep(5000);
		List<WebElement> NomPack = driver.findElements(By.xpath("//*[@class='cpq-item-product-child-level-1 cpq-item-child-product-name-wrapper']"));
		
		for(WebElement a: NomPack) {
			//System.out.print(a.getText().toLowerCase());
			//System.out.println(" : "+servicio1.toLowerCase());
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
			//System.out.println("+++++"+b.getText().substring(b.getText().indexOf("\n")+1, b.getText().length())+"++++++");
			
					if (b.getText().substring(b.getText().indexOf("\n")+1, b.getText().length()).toLowerCase().contains(servicio2.toLowerCase())) {
						System.out.println(servicio2);
						  b.findElement(By.tagName("button")).click();
						   sleep(8000);
						     break;
						}
			
			    }
			}
		
		 //subtablas
		List<String> packs = Arrays.asList(Pack1);
		 List<WebElement> Pack = driver.findElements( By.xpath("//*[@class='cpq-item-product-child-level-3 ng-not-empty ng-valid']//*[@class='cpq-item-no-children']"));
		 List<WebElement> Agregar= driver.findElements(By.xpath("//*[@class='cpq-item-product-child-level-3 ng-not-empty ng-valid']//*[@class='slds-button slds-button_neutral']"));
		 if (Pack.size() == Agregar.size()) {
			 for (int i = 0; i < Pack.size(); i++) {
				 for (int j = 0; j < packs.size(); j++) {
				 if (Pack.get(i).getText().equals(packs.get(j))) {
					sleep(8000);
					System.out.println(Pack.get(i).getText());
					cCC.obligarclick(Agregar.get(i));
							break;	
						}
					}	
				}
			}
		}
	
	
	public void closerightpanel() {
		sleep(10000);
		try {
		driver.switchTo().defaultContent();
		if(driver.findElements(By.cssSelector(".x-layout-mini.x-layout-mini-east.x-layout-mini-custom-logo")).size() != 0) {
			driver.findElement(By.cssSelector(".x-layout-mini.x-layout-mini-east.x-layout-mini-custom-logo")).click();
		}
		}
		catch (ElementNotVisibleException e) {
		
		}
		}
	
			     
}		     
		
	
