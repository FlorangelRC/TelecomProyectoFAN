package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class TechCare_Ola1 {
	
	private WebDriver driver;
	
	//*************************** CONSTRUCTOR *****************************//
	public TechCare_Ola1(WebDriver driver) {
		this.driver=driver;
	}
	
	//**************************** VARIABLES ******************************//
	
	@FindBy (how = How.CSS, using = ".slds-button.slds-button--brand")
	private WebElement verDetalle;
	
	@FindBy (how= How.CSS, using = ".slds-input.ng-valid.ng-touched.ng-dirty.ng-valid-parse.ng-empty")
	private WebElement campoBusqueda;
	
	
	
	
	
	
	
	
	//***************************** METODOS *******************************//
	
	public void clickVerDetalle() {
		Accounts accPage = new Accounts(driver);
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.cssSelector(".slds-button.slds-button--brand")));
		
		WebElement vD=driver.findElement(By.cssSelector(".slds-button.slds-button--brand"));
		//System.out.println(vD.getText());
		
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".slds-button.slds-button--brand")).getLocation().y+")");
		sleep(1000);
		driver.findElement(By.cssSelector(".slds-button.slds-button--brand")).click();
	}
	
	public void buscarServicio(String servicio) {
		
		campoBusqueda.sendKeys(servicio);	
	}

	public void sleep(long ms) {
		//ms=ms*1000;
		try {Thread.sleep(ms);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	public void clickOpcionEnAsset(String Asset,String Opcion) {
		boolean assetEncontrado=false,opcion=false;
		Opcion=Opcion.toLowerCase();
		Accounts accPage = new Accounts(driver);
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.cssSelector(".console-card.active")));
		List<WebElement> asset=driver.findElements(By.cssSelector(".console-card.active"));
		for(WebElement a:asset) {
			//System.out.println(a.getText());
			if(a.findElement(By.className("header-right")).getText().contains(Asset)) {
				assetEncontrado=true;
				List<WebElement> opciones=a.findElement(By.className("actions")).findElements(By.tagName("li"));
				for(WebElement o:opciones) {
					
					if(o.findElement(By.tagName("span")).getText().toLowerCase().contains(Opcion)) {
						o.findElement(By.tagName("span")).click();
						opcion=true;
						break;
					}
				}
			}
		if(assetEncontrado) break;
		}
		if(!assetEncontrado) System.out.println("Asset No encontrado");
		if(!opcion) System.out.println("asset encontrado, Opcion No encontrada");
	}
	
	public void clickDiagnosticarServicio(String servicio, String subServicio) {
		sleep(5000);
		boolean sEncontrado=true;
		Accounts accPage = new Accounts(driver);
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.cssSelector(".slds-card__body.cards-container")));
		List<WebElement> tablas=driver.findElements(By.cssSelector(".slds-card__body.cards-container"));
		//Listado de opciones
		List<WebElement> servicios=tablas.get(0).findElements(By.xpath("//table//tbody//tr"));
		for(WebElement S:servicios) {
			if(S.getText().toLowerCase().contains(servicio.toLowerCase())) {
				S.findElement(By.className("addedValueServices-arrowWrapper")).click();
				sleep(2000);
				sEncontrado=false;
				break;
			}
		}
		if(sEncontrado) { System.out.println("Servicio no encontrado."); return;}
		
		List<WebElement> sServicios=tablas.get(selectionTable(servicio)).findElements(By.xpath("//table//tbody//tr"));
	      for(WebElement S:sServicios) {
	        //System.out.println(S.getText());
	        if(S.getText().toLowerCase().contains(subServicio.toLowerCase())) {
	          ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+S.getLocation().y+")");
	          sleep(100);
	          S.findElement(By.className("slds-cell-shrink")).click();
	          sleep(2000);
	          try {
	            S.findElement(By.className("slds-cell-shrink")).findElement(By.xpath("//div//div//ul//li")).click();
	          }catch(org.openqa.selenium.ElementNotVisibleException e) {
	          sleep(2000);
	          List<WebElement> actions=  S.findElement(By.className("slds-cell-shrink")).findElements(By.xpath("//*[@class='dropdown__list']//li"));
	               //S.findElements(By.xpath("//*[@class='dropdown__list']//li"));
	           for (WebElement opt : actions) {
	        	   if (opt.isDisplayed()) {
	        		   opt.click();
	        		   break;} }
	          }
	        }
	  
	      }
		
	}
	
	
	public void clickDiagnosticarServicio(String servicio) {
	      sleep(5000);
	      Accounts accPage = new Accounts(driver);
	      driver.switchTo().frame(accPage.getFrameForElement(driver, By.cssSelector(".slds-card__body.cards-container")));
	      List<WebElement> tablas=driver.findElements(By.cssSelector(".slds-card__body.cards-container"));
	      //Listado de opciones
	      List<WebElement> servicios=tablas.get(selectionTable(servicio)).findElements(By.xpath("//table//tbody//tr"));
	      for(WebElement S:servicios) {
	        //System.out.println(S.getText());
	        if(S.getText().toLowerCase().contains(servicio.toLowerCase())) {
	          ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+S.getLocation().y+")");
	          sleep(100);
	          S.findElement(By.className("slds-cell-shrink")).click();
	          sleep(2000);
	          try {
	            S.findElement(By.className("slds-cell-shrink")).findElement(By.xpath("//div//div//ul//li")).click();
	          }catch(org.openqa.selenium.ElementNotVisibleException e) {
	          sleep(2000);
	          List<WebElement> actions=  S.findElement(By.className("slds-cell-shrink")).findElements(By.xpath("//*[@class='dropdown__list']//li"));
	               //S.findElements(By.xpath("//*[@class='dropdown__list']//li"));
	           for (WebElement opt : actions) {
	        	   if (opt.isDisplayed()) {
	        		   opt.click();
	        		   break;} }
	          }
	        }
	  
	      } 
	  
	  }
	
	
	private int selectionTable(String serviceName) {
	    switch (serviceName.toUpperCase()) {
	    case "SMS":
	      return 1;
	    case "VOZ":
	      return 2;
	    default:
	      return 0;
	    }}
}
