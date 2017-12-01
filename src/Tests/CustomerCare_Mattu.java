package Tests;

import static org.testng.Assert.assertTrue;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.BasePage;
import Pages.CustomerCare;
import Pages.setConexion;

public class CustomerCare_Mattu extends TestBase{
	
	private WebDriver driver;
	
	@BeforeClass (groups= "Fase2")
    public void init() throws Exception
    {
        this.driver = setConexion.setupEze();
        login(driver);
        try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
        String a = driver.findElement(By.id("tsidLabel")).getText();
        driver.findElement(By.id("tsidLabel")).click();
        if(a.equals("Ventas"))
        {
            driver.findElement(By.xpath("//a[@href=\'/console?tsid=02uc0000000D6Hd\']")).click();
        }else
        {            driver.findElement(By.xpath("//a[@href=\'/home/home.jsp?tsid=02u41000000QWha\']")).click();
            try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
            driver.findElement(By.id("tsidLabel")).click();
            driver.findElement(By.xpath("//a[@href=\'/console?tsid=02uc0000000D6Hd\']")).click();
        }
    }    
    @BeforeMethod (groups= "Fase2")
    public void setup(){
        try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
        CustomerCare page = new CustomerCare(driver);
        page.cerrarultimapestaña();
    }

    //-------------------------------------------------------------------------------------------------
    
	@Test(groups="CustomerCare")
	public void TS_38553_Problems_with_Refills_Problemas_con_Recargas_Medio_de_recarga_PIN_Ingresa_15_dígitos() {
		CustomerCare CP = new CustomerCare (driver);
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
		CP.elegircuenta("Fernando Care");
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
	    BasePage cambioFrameByID=new BasePage();
	    driver.switchTo().defaultContent();
	    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("card-info")));
	    WebElement wCardInfo = driver.findElement(By.className("card-info"));
	    wCardInfo.click();
	    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    driver.switchTo().defaultContent();
	    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions")));
	    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".console-flyout.active.flyout")).getLocation().y+")");
	    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
	    driver.findElement(By.cssSelector(".console-flyout.active.flyout")).findElements(By.tagName("i")).get(1).click();
	    List<WebElement> wAsd = driver.findElements(By.id("refillMethod"));
	    for (WebElement x:wAsd) {
	    	if (x.getText().toLowerCase().contains("tarjeta prepaga")) {
	    		x.click();
	    	}
	    }
	    try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    driver.switchTo().defaultContent();
	    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("stepChooseMethod_nextBtn")));
	    
	    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).getLocation().y+")");
	    List <WebElement> wX = driver.findElements(By.className("slds-form-element__control"));
	    for (WebElement x : wX) {
	    	if (x.getText().toLowerCase().contains("siguiente")) {
	    		x.click();
	    		break;
	    	}
	    }
	    try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    driver.findElement(By.id("pinNumber")).sendKeys("123456789012345");
	    Assert.assertTrue(!driver.findElement(By.cssSelector(".error.ng-scope")).getText().isEmpty());
	}
	
	//---------------------------------------------------------------------------------------------------------------------------------------------
	
	@Test(groups="CustomerCare")
	public void TS_38553_Problems_with_Refills_Problemas_con_Recargas_Medio_de_recarga_PIN_Ingresa_16_dígitos() {
		CustomerCare CP = new CustomerCare (driver);
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
		CP.elegircuenta("Fernando Care");
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
	    BasePage cambioFrameByID=new BasePage();
	    driver.switchTo().defaultContent();
	    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("card-info")));
	    WebElement wCardInfo = driver.findElement(By.className("card-info"));
	    wCardInfo.click();
	    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    driver.switchTo().defaultContent();
	    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions")));
	    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".console-flyout.active.flyout")).getLocation().y+")");
	    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
	    driver.findElement(By.cssSelector(".console-flyout.active.flyout")).findElements(By.tagName("i")).get(1).click();
	    List<WebElement> wAsd = driver.findElements(By.id("refillMethod"));
	    for (WebElement x:wAsd) {
	    	if (x.getText().toLowerCase().contains("tarjeta prepaga")) {
	    		x.click();
	    	}
	    }
	    try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    driver.switchTo().defaultContent();
	    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("stepChooseMethod_nextBtn")));
	    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).getLocation().y+")");
	    List <WebElement> wX = driver.findElements(By.className("slds-form-element__control"));
	    for (WebElement x : wX) {
	    	if (x.getText().toLowerCase().contains("siguiente")) {
	    		x.click();
	    		break;
	    	}
	    }
	    try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    driver.findElement(By.id("pinNumber")).sendKeys("1234567890123456");
	    Assert.assertTrue(driver.findElement(By.cssSelector(".error.ng-scope")).getText().isEmpty());
	}
	
	//---------------------------------------------------------------------------------------------------------------------------------------------
	
		@Test(groups="CustomerCare")
		public void TS_38553_Problems_with_Refills_Problemas_con_Recargas_Medio_de_recarga_PIN_Ingresa_17_dígitos() {
			CustomerCare CP = new CustomerCare (driver);
			try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
			CP.elegircuenta("Fernando Care");
			try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
		    BasePage cambioFrameByID=new BasePage();
		    driver.switchTo().defaultContent();
		    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("card-info")));
		    WebElement wCardInfo = driver.findElement(By.className("card-info"));
		    wCardInfo.click();
		    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    driver.switchTo().defaultContent();
		    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions")));
		    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".console-flyout.active.flyout")).getLocation().y+")");
		    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
		    driver.findElement(By.cssSelector(".console-flyout.active.flyout")).findElements(By.tagName("i")).get(1).click();
		    List<WebElement> wAsd = driver.findElements(By.id("refillMethod"));
		    for (WebElement x:wAsd) {
		    	if (x.getText().toLowerCase().contains("tarjeta prepaga")) {
		    		x.click();
		    	}
		    }
		    try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    driver.switchTo().defaultContent();
		    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("stepChooseMethod_nextBtn")));
		    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).getLocation().y+")");
		    List <WebElement> wX = driver.findElements(By.className("slds-form-element__control"));
		    for (WebElement x : wX) {
		    	if (x.getText().toLowerCase().contains("siguiente")) {
		    		x.click();
		    		break;
		    	}
		    }
		    try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    driver.findElement(By.id("pinNumber")).sendKeys("12345678901234567");
		    Assert.assertTrue(!driver.findElement(By.cssSelector(".error.ng-scope")).getText().isEmpty());
		}
		
		//---------------------------------------------------------------------------------------------------------------------------------------------
		
			@Test(groups="CustomerCare")
			public void TS_38553_Problems_with_Refills_Problemas_con_Recargas_Medio_de_recarga_PIN_Ingresa_letras() {
				CustomerCare CP = new CustomerCare (driver);
				try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
				CP.elegircuenta("Fernando Care");
				try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
			    BasePage cambioFrameByID=new BasePage();
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("card-info")));
			    WebElement wCardInfo = driver.findElement(By.className("card-info"));
			    wCardInfo.click();
			    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions")));
			    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".console-flyout.active.flyout")).getLocation().y+")");
			    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
			    driver.findElement(By.cssSelector(".console-flyout.active.flyout")).findElements(By.tagName("i")).get(1).click();
			    List<WebElement> wAsd = driver.findElements(By.id("refillMethod"));
			    for (WebElement x:wAsd) {
			    	if (x.getText().toLowerCase().contains("tarjeta prepaga")) {
			    		x.click();
			    	}
			    }
			    try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("stepChooseMethod_nextBtn")));
			    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).getLocation().y+")");
			    List <WebElement> wX = driver.findElements(By.className("slds-form-element__control"));
			    for (WebElement x : wX) {
			    	if (x.getText().toLowerCase().contains("siguiente")) {
			    		x.click();
			    		break;
			    	}
			    }
			    try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    driver.findElement(By.id("pinNumber")).sendKeys("a");
			    Assert.assertTrue(!driver.findElement(By.cssSelector(".error.ng-scope")).getText().isEmpty());
			}
			
			//---------------------------------------------------------------------------------------------------------------------------------------------
			
			@Test(groups="CustomerCare")
			public void TS_38553_Problems_with_Refills_Problemas_con_Recargas_Medio_de_recarga_Seleccionar_Tarjeta_Pre_Paga_PIN_Invisible() {
				CustomerCare CP = new CustomerCare (driver);
				try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
				CP.elegircuenta("Fernando Care");
				try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
			    BasePage cambioFrameByID=new BasePage();
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("card-info")));
			    WebElement wCardInfo = driver.findElement(By.className("card-info"));
			    wCardInfo.click();
			    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions")));
			    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".console-flyout.active.flyout")).getLocation().y+")");
			    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
			    driver.findElement(By.cssSelector(".console-flyout.active.flyout")).findElements(By.tagName("i")).get(1).click();
			    List<WebElement> wAsd = driver.findElements(By.id("refillMethod"));
			    for (WebElement x:wAsd) {
			    	if (x.getText().toLowerCase().contains("tarjeta prepaga")) {
			    		x.click();
			    	}
			    }
			    try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("stepChooseMethod_nextBtn")));
			    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).getLocation().y+")");
			    List <WebElement> wSiguiente = driver.findElements(By.className("slds-form-element__control"));
			    for (WebElement wX : wSiguiente) {
			    	if (wX.getText().toLowerCase().contains("siguiente")) {
			    		wX.click();
			    		break;
			    	}
			    }
			    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    driver.findElement(By.id("lotNumber")).sendKeys("2222222222222222");
			    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("stepPrepaidCardData_nextBtn")).getLocation().y+")");
			    driver.findElement(By.xpath("//*[@id=\"stepPrepaidCardData_nextBtn\"]")).click();
			    try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"useExistingCase|0\"]/div/div[1]/label[1]/span/div/div")).isDisplayed());
			}
			
			//-------------------------------------------------------------------------------------------------
		    
			@Test(groups="CustomerCare")
			public void TS_38553_Problems_with_Refills_Problemas_con_Recargas_Recarga_sin_PIN_Gestión_pendiente_Recarga_sin_PIN_Lote_Ingresa_15_dígitos() {
				CustomerCare CP = new CustomerCare (driver);
				try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
				CP.elegircuenta("Fernando Care");
				try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
			    BasePage cambioFrameByID=new BasePage();
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("card-info")));
			    WebElement wCardInfo = driver.findElement(By.className("card-info"));
			    wCardInfo.click();
			    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions")));
			    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".console-flyout.active.flyout")).getLocation().y+")");
			    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
			    driver.findElement(By.cssSelector(".console-flyout.active.flyout")).findElements(By.tagName("i")).get(1).click();
			    List<WebElement> wAsd = driver.findElements(By.id("refillMethod"));
			    for (WebElement x:wAsd) {
			    	if (x.getText().toLowerCase().contains("tarjeta prepaga")) {
			    		x.click();
			    	}
			    }
			    try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("stepChooseMethod_nextBtn")));
			    
			    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("stepChooseMethod_nextBtn")).getLocation().y+")");
			    List <WebElement> wX = driver.findElements(By.className("slds-form-element__control"));
			    for (WebElement x : wX) {
			    	if (x.getText().toLowerCase().contains("siguiente")) {
			    		x.click();
			    		break;
			    	}
			    }
			    try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    driver.findElement(By.id("lotNumber")).sendKeys("123456789012345");
			    Assert.assertTrue(!driver.findElement(By.cssSelector(".error.ng-scope")).getText().isEmpty());
			}
			
			//-------------------------------------------------------------------------------------------------
		    
			@Test(groups="CustomerCare")
			public void TS_38553_Problems_with_Refills_Problemas_con_Recargas_Recarga_sin_PIN_Gestión_pendiente_Recarga_sin_PIN_Lote_Ingresa_16_dígitos() {
				CustomerCare CP = new CustomerCare (driver);
				try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
				CP.elegircuenta("Fernando Care");
				try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
			    BasePage cambioFrameByID=new BasePage();
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("card-info")));
			    WebElement wCardInfo = driver.findElement(By.className("card-info"));
			    wCardInfo.click();
			    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions")));
			    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".console-flyout.active.flyout")).getLocation().y+")");
			    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
			    driver.findElement(By.cssSelector(".console-flyout.active.flyout")).findElements(By.tagName("i")).get(1).click();
			    List<WebElement> wAsd = driver.findElements(By.id("refillMethod"));
			    for (WebElement x:wAsd) {
			    	if (x.getText().toLowerCase().contains("tarjeta prepaga")) {
			    		x.click();
			    	}
			    }
			    try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("stepChooseMethod_nextBtn")));
			    
			    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).getLocation().y+")");
			    List <WebElement> wX = driver.findElements(By.className("slds-form-element__control"));
			    for (WebElement x : wX) {
			    	if (x.getText().toLowerCase().contains("siguiente")) {
			    		x.click();
			    		break;
			    	}
			    }
			    try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    driver.findElement(By.id("lotNumber")).sendKeys("1234567890123456");
			    Assert.assertTrue(driver.findElement(By.cssSelector(".error.ng-scope")).getText().isEmpty());
			}
			
			//-------------------------------------------------------------------------------------------------
		    
			@Test(groups="CustomerCare")
			public void TS_38553_Problems_with_Refills_Problemas_con_Recargas_Recarga_sin_PIN_Gestión_pendiente_Recarga_sin_PIN_Lote_Ingresa_17_dígitos() {
				CustomerCare CP = new CustomerCare (driver);
				try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
				CP.elegircuenta("Fernando Care");
				try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
			    BasePage cambioFrameByID=new BasePage();
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("card-info")));
			    WebElement wCardInfo = driver.findElement(By.className("card-info"));
			    wCardInfo.click();
			    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions")));
			    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".console-flyout.active.flyout")).getLocation().y+")");
			    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
			    driver.findElement(By.cssSelector(".console-flyout.active.flyout")).findElements(By.tagName("i")).get(1).click();
			    List<WebElement> wAsd = driver.findElements(By.id("refillMethod"));
			    for (WebElement x:wAsd) {
			    	if (x.getText().toLowerCase().contains("tarjeta prepaga")) {
			    		x.click();
			    	}
			    }
			    try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("stepChooseMethod_nextBtn")));
			    
			    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("stepChooseMethod_nextBtn")).getLocation().y+")");
			    List <WebElement> wX = driver.findElements(By.className("slds-form-element__control"));
			    for (WebElement x : wX) {
			    	if (x.getText().toLowerCase().contains("siguiente")) {
			    		x.click();
			    		break;
			    	}
			    }
			    try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    driver.findElement(By.id("lotNumber")).sendKeys("12345678901234567");
			    Assert.assertTrue(!driver.findElement(By.cssSelector(".error.ng-scope")).getText().isEmpty());
			}
			
			//-------------------------------------------------------------------------------------------------
		    
			@Test(groups="CustomerCare")
			public void TS_38553_Problems_with_Refills_Problemas_con_Recargas_Recarga_sin_PIN_Gestión_pendiente_Recarga_sin_PIN_Lote_Ingresa_Letras() {
				CustomerCare CP = new CustomerCare (driver);
				try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
				CP.elegircuenta("Fernando Care");
				try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
			    BasePage cambioFrameByID=new BasePage();
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("card-info")));
			    WebElement wCardInfo = driver.findElement(By.className("card-info"));
			    wCardInfo.click();
			    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions")));
			    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".console-flyout.active.flyout")).getLocation().y+")");
			    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
			    driver.findElement(By.cssSelector(".console-flyout.active.flyout")).findElements(By.tagName("i")).get(1).click();
			    List<WebElement> wAsd = driver.findElements(By.id("refillMethod"));
			    for (WebElement x:wAsd) {
			    	if (x.getText().toLowerCase().contains("tarjeta prepaga")) {
			    		x.click();
			    	}
			    }
			    try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("stepChooseMethod_nextBtn")));
			    
			    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("stepChooseMethod_nextBtn")).getLocation().y+")");
			    List <WebElement> wX = driver.findElements(By.className("slds-form-element__control"));
			    for (WebElement x : wX) {
			    	if (x.getText().toLowerCase().contains("siguiente")) {
			    		x.click();
			    		break;
			    	}
			    }
			    try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    driver.findElement(By.id("lotNumber")).sendKeys("a");
			    Assert.assertTrue(!driver.findElement(By.cssSelector(".error.ng-scope")).getText().isEmpty());
			}
			
			//-------------------------------------------------------------------------------------------------
		    
			@Test(groups="CustomerCare")
			public void TS_38553_Problems_with_Refills_UX_Tarjeta_de_Recarga_Pre_paga_Verificación_Visualizar_Botón_Cancelar() {
				CustomerCare CP = new CustomerCare (driver);
				try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
				CP.elegircuenta("Fernando Care");
				try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
			    BasePage cambioFrameByID=new BasePage();
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("card-info")));
			    WebElement wCardInfo = driver.findElement(By.className("card-info"));
			    wCardInfo.click();
			    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions")));
			    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".console-flyout.active.flyout")).getLocation().y+")");
			    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
			    driver.findElement(By.cssSelector(".console-flyout.active.flyout")).findElements(By.tagName("i")).get(1).click();

			    try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("stepChooseMethod_nextBtn")));
			    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).getLocation().y+")");
			    Assert.assertTrue(driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).isDisplayed());
			}
			
			//-------------------------------------------------------------------------------------------------
		    
			@Test(groups="CustomerCare")
			public void TS_38553_Problems_with_Refills_UX_Tarjeta_de_Recarga_Pre_paga_Verificación_Visualizar_Botón_Consultar() {
				CustomerCare CP = new CustomerCare (driver);
				try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
				CP.elegircuenta("Fernando Care");
				try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}  
			    BasePage cambioFrameByID=new BasePage();
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("card-info")));
			    WebElement wCardInfo = driver.findElement(By.className("card-info"));
			    wCardInfo.click();
			    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions")));
			    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".console-flyout.active.flyout")).getLocation().y+")");
			    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
			    driver.findElement(By.cssSelector(".console-flyout.active.flyout")).findElements(By.tagName("i")).get(1).click();

			    try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("stepChooseMethod_nextBtn")));
			    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.id("stepChooseMethod_nextBtn")).getLocation().y+")");
			    Assert.assertTrue(driver.findElement(By.id("stepChooseMethod_nextBtn")).isDisplayed());
			}//Not reported
	
}