package Tests;

import static org.testng.Assert.assertTrue;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Pages.Accounts;
import Pages.BasePage;
import Pages.CustomerCare;
import Pages.setConexion;

public class CustomerCare_Mattu extends TestBase{
	
	private WebDriver driver;
	
	//-------------------------------------------------------------------------------------------------
	//@Befor&After
	@BeforeClass (groups = {"CustomerCare", "ProblemasConRecargas", "ActualizarDatos", "DetalleDeConsumos", "Vista360Layout"})
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
    @BeforeMethod (groups = {"CustomerCare", "ProblemasConRecargas", "ActualizarDatos", "DetalleDeConsumos", "Vista360Layout"})
    public void setup(){
        try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
        CustomerCare page = new CustomerCare(driver);
        page.cerrarultimapestaña();
    }
    /*@AfterClass (groups = {"CustomerCare", "ProblemasConRecargas", "ActualizarDatos", "DetalleDeConsumos", "Vista360Layout"})
    public void quit() {
      driver.quit();
      sleep(4000);
    }*/
    
    //-------------------------------------------------------------------------------------------------
    //TCC = 1
	@Test(groups = {"CustomerCare", "ProblemasConRecargas"})
	public void TS_38553_Problems_with_Refills_Problemas_con_Recargas_Medio_de_recarga_PIN_Ingresa_15_dígitos() {
		CustomerCare CP = new CustomerCare (driver);
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
		CP.elegircuenta("aaaaFernando Care");
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
	//TCC = 2
	@Test(groups = {"CustomerCare", "ProblemasConRecargas"})
	public void TS_37554_Problems_with_Refills_Problemas_con_Recargas_Medio_de_recarga_PIN_Ingresa_16_dígitos() {
		CustomerCare CP = new CustomerCare (driver);
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
		CP.elegircuenta("aaaaFernando Care");
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
	//TCC = 3
	@Test(groups = {"CustomerCare", "ProblemasConRecargas"})
	public void TS_37555_Problems_with_Refills_Problemas_con_Recargas_Medio_de_recarga_PIN_Ingresa_17_dígitos() {
		CustomerCare CP = new CustomerCare (driver);
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
		CP.elegircuenta("aaaaFernando Care");
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
	//TCC = 4
	@Test(groups = {"CustomerCare", "ProblemasConRecargas"})
	public void TS_37556_Problems_with_Refills_Problemas_con_Recargas_Medio_de_recarga_PIN_Ingresa_letras() {
		CustomerCare CP = new CustomerCare (driver);
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
		CP.elegircuenta("aaaaFernando Care");
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
	//TCC = 5
	@Test(groups = {"CustomerCare", "ProblemasConRecargas"})
	public void TS_37536_Problems_with_Refills_Problemas_con_Recargas_Medio_de_recarga_Seleccionar_Tarjeta_Pre_Paga_PIN_Invisible() {
		CustomerCare CP = new CustomerCare (driver);
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
		CP.elegircuenta("aaaaFernando Care");
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
	//TCC = 6
	@Test(groups = {"CustomerCare", "ProblemasConRecargas"})
	public void TS_37549_Problems_with_Refills_Problemas_con_Recargas_Recarga_sin_PIN_Gestión_pendiente_Recarga_sin_PIN_Lote_Ingresa_15_dígitos() {
		CustomerCare CP = new CustomerCare (driver);
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
		CP.elegircuenta("aaaaFernando Care");
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
	//TCC = 7
	@Test(groups = {"CustomerCare", "ProblemasConRecargas"})
	public void TS_37550_Problems_with_Refills_Problemas_con_Recargas_Recarga_sin_PIN_Gestión_pendiente_Recarga_sin_PIN_Lote_Ingresa_16_dígitos() {
		CustomerCare CP = new CustomerCare (driver);
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
		CP.elegircuenta("aaaaFernando Care");
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
	//TCC = 8
	@Test(groups = {"CustomerCare", "ProblemasConRecargas"})
	public void TS_37551_Problems_with_Refills_Problemas_con_Recargas_Recarga_sin_PIN_Gestión_pendiente_Recarga_sin_PIN_Lote_Ingresa_17_dígitos() {
		CustomerCare CP = new CustomerCare (driver);
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
		CP.elegircuenta("aaaaFernando Care");
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
	//TCC = 9
	@Test(groups = {"CustomerCare", "ProblemasConRecargas"})
	public void TS_37552_Problems_with_Refills_Problemas_con_Recargas_Recarga_sin_PIN_Gestión_pendiente_Recarga_sin_PIN_Lote_Ingresa_Letras() {
		CustomerCare CP = new CustomerCare (driver);
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
		CP.elegircuenta("aaaaFernando Care");
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
	//TCC = 10
	@Test(groups = {"CustomerCare", "ProblemasConRecargas"})
	public void TS_37331_Problems_with_Refills_UX_Tarjeta_de_Recarga_Pre_paga_Verificación_Visualizar_Botón_Cancelar() {
		CustomerCare CP = new CustomerCare (driver);
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
		CP.elegircuenta("aaaaFernando Care");
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
	//TCC = 11
	@Test(groups = {"CustomerCare", "ProblemasConRecargas"})
	public void TS_37338_Problems_with_Refills_UX_Tarjeta_de_Recarga_Pre_paga_Verificación_Visualizar_Botón_Consultar() {
		CustomerCare CP = new CustomerCare (driver);
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
		CP.elegircuenta("aaaaFernando Care");
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
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 12
	@Test(groups = {"CustomerCare", "ProblemasConRecargas"})
	public void TS_37330_Problems_with_Refills_UX_Tarjeta_de_Recarga_Pre_paga_Verificación_Visualizar_panel_de_Steps() {
		CustomerCare CP = new CustomerCare (driver);
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();} 
		CP.elegircuenta("aaaaFernando Care");
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
	    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".vlc-slds-wizard.ng-scope.ng-isolate-scope")));
	    Assert.assertTrue(driver.findElement(By.cssSelector(".vlc-slds-wizard.ng-scope.ng-isolate-scope")).isDisplayed());
	}
	
	//-------------------------------------------------------------------------------------------------
	//@Test
	@Test(groups = {"CustomerCare", "ActualizarDatos"})
	public void TS38042_Profile_Changes_Validación_Correo_Electronico_Cuenta_Email_Existente(){
	CustomerCare CP = new CustomerCare (driver);
	try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
	CP.elegircuenta("aaaaFernando Care");
	BasePage cambioFrameByID=new BasePage();
	driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("profile-edit")));
	List <WebElement> actualizar = driver.findElements(By.className("profile-edit"));
		  for (WebElement x : actualizar) {
			  if (x.getText().toLowerCase().contains("actualizar datos")) {
				  x.click();
			  } 
		   }
	try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	driver.switchTo().defaultContent();
	driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("Email")));
	boolean ff =false;
	WebElement qw = driver.findElement(By.id("Email"));
	try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
	String as = qw.getAttribute("value");
	 qw.clear();
	 try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
	WebElement qwe = driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-dirty.ng-invalid.ng-valid-email.ng-invalid-required"));
		if(qwe.isDisplayed());
		ff = true;
	driver.findElement(By.xpath("//*[@id=\"Email\"]")).sendKeys(as);
	try {
		   Assert.assertFalse(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-scope.ng-dirty.ng-invalid.ng-valid-email.ng-invalid-required")).isDisplayed());
		  } catch (org.openqa.selenium.NoSuchElementException e) {
		   Assert.assertTrue(true);
		  }
}
 
	@Test(groups = {"CustomerCare", "ActualizarDatos"})
	public void TS38043_Profile_Changes_Validación_Correo_Electronico_Cuenta_Email_No_existente(){
	 CustomerCare CP = new CustomerCare (driver);
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
		CP.elegircuenta("aaaaFernando Care");
	BasePage cambioFrameByID=new BasePage();
	driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("profile-edit")));
	List <WebElement> actualizar = driver.findElements(By.className("profile-edit"));
		  for (WebElement x : actualizar) {
		   if (x.getText().toLowerCase().contains("actualizar datos")) {
		    x.click();
		   }
		  }
	try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	driver.switchTo().defaultContent();
	driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.id("Email")));
	WebElement mail = driver.findElement(By.id("Email"));
	mail.clear();
	boolean f = true;
	try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	WebElement qwe = driver.findElement(By.cssSelector(".slds-input.form-control.ng-valid-email.ng-touched.ng-dirty.ng-empty.ng-invalid.ng-invalid-required"));
	try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	mail.sendKeys("asfasdasd");
	Assert.assertTrue(qwe.isDisplayed());
	

}

	@Test(groups = {"CustomerCare", "DetalleDeConsumos"})
	public void TS38346_Consumption_Details_Detalle_de_consumos_Servicio_Pre_pago_Prepago_Muestra_los_ultimos_3_días() {
		 CustomerCare CP = new CustomerCare (driver);
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
			CP.elegircuenta("aaaaFernando Care");
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
			BasePage cambioFrameByID=new BasePage();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("card-info")));
			List<WebElement> btn = driver.findElements(By.cssSelector(".icon.icon-v-graph-line"));
			for(WebElement b:btn) {
				b.getText().toLowerCase().contains("detalle de consumos");
				b.click();
			}
			try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
			driver.switchTo().defaultContent();
			driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-grid.slds-wrap.slds-card.slds-m-bottom--small.slds-p-around--medium")));
			//WebElement wed = driver.findElement(By.id("text-input-02"));
			//System.out.println(wed.getAttribute("value"));
			Assert.assertTrue(driver.findElement(By.id("text-input-02")).getAttribute("value").toLowerCase().contains("los últimos 3 días"));
	}

	@Test(groups = {"CustomerCare", "DetalleDeConsumos"})
	public void TS38347_Consumption_Details_Detalle_de_consumos_Servicio_Pre_pago_Prepago_Visualizar_leyenda_Los_ultimos_3_días() {
		 CustomerCare CP = new CustomerCare (driver);
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
			CP.elegircuenta("aaaaFernando Care");
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
			BasePage cambioFrameByID=new BasePage();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("card-info")));
			List<WebElement> btn = driver.findElements(By.cssSelector(".icon.icon-v-graph-line"));
			for(WebElement b:btn) {
				b.getText().toLowerCase().contains("detalle de consumos");
				b.click();
			}
			try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
			driver.switchTo().defaultContent();
			driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-grid.slds-wrap.slds-card.slds-m-bottom--small.slds-p-around--medium")));
			WebElement wed = driver.findElement(By.id("text-input-02"));
			//System.out.println(wed.getAttribute("value"));
			wed.click();
			driver.findElement(By.xpath("//*[text() = 'Los últimos 3 días']")).click();
			Assert.assertTrue(driver.findElement(By.id("text-input-01")).isDisplayed());
	}
	
	@Test(groups = {"CustomerCare", "Vista360Layout"})
	public void TS38410_360_View_Vista_360_de_facturación_clientes_individuos_Información_de_las_Cards_Visualizar_campos(){
		 CustomerCare CP = new CustomerCare (driver);
			try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
			CP.elegircuenta("aaaaFernando Care");
			try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
			Accounts aAcc = new Accounts (driver);
			aAcc.closeAccountServiceTabByName("Servicios");
			try {Thread.sleep(20000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			BasePage cambioFrameByID=new BasePage();
			aAcc.findAndClickButton("Facturación");
			driver.switchTo().defaultContent();
			try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".console-card.active")));
		
			
	 WebElement asd = driver.findElement(By.cssSelector(".console-card.active"));
	 Assert.assertTrue(asd.getText().toLowerCase().contains("último vencimiento"));
	 Assert.assertTrue(asd.getText().toLowerCase().contains("recepción de factura"));
	 Assert.assertTrue(asd.getText().toLowerCase().contains("ciclo de facturación"));
	 Assert.assertTrue(asd.getText().toLowerCase().contains("servicios"));
	}

	@Test(groups = {"CustomerCare", "Vista360Layout"})
	public void TS38440_360_View_360_Card_Servicio_Prepago_Flyout_AccionesCampañas() {
		CustomerCare CP = new CustomerCare (driver);
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
		CP.elegircuenta("aaaaFernando Care");
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
		BasePage cambioFrameByID=new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".console-card.active")));
		WebElement wCon = driver.findElement(By.cssSelector(".console-card.active"));
		wCon.click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions")));
		WebElement wElement = driver.findElement(By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions"));
		Assert.assertTrue(wElement.getText().toLowerCase().contains("campañas"));
	}
	 
	@Test(groups = {"CustomerCare", "Vista360Layout"})
	public void TS38434_360_View_360_Card_Servicio_Prepago_Flyoutv_AccionesRecargar() {
		 CustomerCare CP = new CustomerCare (driver);
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
		CP.elegircuenta("aaaaFernando Care");
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
		BasePage cambioFrameByID=new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".console-card.active")));
		WebElement wCon = driver.findElement(By.cssSelector(".console-card.active"));
		wCon.click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions")));
		WebElement wElement = driver.findElement(By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions"));
		Assert.assertTrue(wElement.getText().toLowerCase().contains("recargar"));
	}
		
	@Test(groups = {"CustomerCare", "Vista360Layout"})
	public void TS38436_360_View_360_Card_Servicio_Prepago_Flyoutv_AccionesGestiones() {
		CustomerCare CP = new CustomerCare (driver);
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
		CP.elegircuenta("aaaaFernando Care");
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
		BasePage cambioFrameByID=new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".console-card.active")));
		WebElement wCon = driver.findElement(By.cssSelector(".console-card.active"));
		wCon.click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions")));
		WebElement wElement = driver.findElement(By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions"));
		Assert.assertTrue(wElement.getText().toLowerCase().contains("gestiones"));
	}
	
	@Test(groups = {"CustomerCare", "Vista360Layout"})
	public void TS38612_Diseño_Seleccion_asset_Numero_de_linea_como_identificador_principal() {
		CustomerCare CP = new CustomerCare (driver);
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
		CP.elegircuenta("aaaaFernando Care");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
		BasePage cambioFrameByID=new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".header-right")));
		WebElement tex = driver.findElement(By.cssSelector(".slds-text-body_regular.account-number.slds-p-bottom--xx-small"));
		Assert.assertTrue(tex.getText().toLowerCase().equals("línea"));
		List<WebElement> asf = driver.findElements(By.cssSelector(".slds-text-heading_medium"));
		for(int i=0 ; i<asf.size(); i++) {
		}
		String lin = "(011) 1566 - 6666";	
		Assert.assertTrue(asf.get(2).getText().contains(lin));
	 }
		
}