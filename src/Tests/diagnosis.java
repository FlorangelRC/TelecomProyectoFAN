package Tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.diagnosisTab;
import Pages.setConexion;
import Pages.BasePage;
import Pages.HomeBase;
import Pages.TechCareDiagnostic;

public class diagnosis extends TestBase {
	
	private WebDriver driver;

	@AfterClass
	public void tearDown2() {
		//driver.close();
	}
	
	@BeforeClass
	public void init() throws Exception
	{
		this.driver = setConexion.setupEze();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		login(driver);
		driver.get("https://crm--sit.cs14.my.salesforce.com/home/home.jsp?tsid=02u41000000QWha");
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}

	@AfterMethod
	public void tearDown() {

		//driver.get("https://crm--sit.cs14.my.salesforce.com/home/home.jsp?tsid=02u41000000QWha");

		driver.get("https://cs14.salesforce.com/home/home.jsp?tsid=02u41000000QWha");

	}

	@BeforeMethod
	public void setUp() throws Exception {
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		if (!driver.getCurrentUrl().toString().contains("/console")){
			driver.findElement(By.id("tsidLabel")).click();
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.findElement(By.xpath("//a[@href=\"/console?tsid=02uc0000000D6Hd\"]")).click();
		}
	}
	/*
	@Test
	public void TS6269_isExeccuteButtonPresent() {
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try{ for(WebElement e : driver.findElements(By.className("x-tab-strip-close"))) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
		} } catch (org.openqa.selenium.StaleElementReferenceException e) {}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		goToLeftPanel(driver, "Cuentas");
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement frame0 = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(frame0);
		Select field = new Select(driver.findElement(By.name("fcf")));
		field.selectByVisibleText("Todas las cuentas");
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> accounts = driver.findElements(By.xpath("//*[text() = 'Chronos Automata']"));
		accounts.get(0).click();
		driver.switchTo().defaultContent();
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
		List<WebElement> accountTabs = driver.findElements(By.className("tabText"));
		accountTabs.get(2).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame2 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame2.get(2));
		List<WebElement> squares = driver.findElements(By.cssSelector(".console-card.active"));
		for (WebElement e : squares) {
			if (e.getText().contains("INTERNET")) {
		e.click(); }
		}
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.xpath("//*[@id=\"j_id0:j_id5\"]/div/div/ng-include/div/div[2]/div[3]/ng-include/section[2]/div[3]/ng-transclude/div/ng-include/div/div[1]/div/ng-include/div/ul/li[3]")).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		List<WebElement> frame3 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame3.get(4));
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("SelectedMotives")).click();
		List<WebElement> drops1=driver.findElements(By.cssSelector("ul > li"));
		//driver.findElement(By.xpath("//li[contains(text(),'No me funciona internet')]")).click();
		//WebElement aa = driver.findElement(By.xpath("//*[@id=\"SelectBlock\"]/div/div/div[2]/child[2]/div/div/ng-form/div[2]/div[1]/ul/li[2]"));
		//System.out.println(aa.getTagName());
		//System.out.println(aa.getSize());
		//List<WebElement> drops1=driver.findElements(By.tagName("li"));
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//List<WebElement> list = driver.findElements(By.xpath("//*[text() = 'No me funciona internet']"));
		//list.get(0).click();
		
        System.out.println(drops1.size());
        System.out.println(drops1.get(1).getText());
        drops1.get(0).click();
        /*for(WebElement  obj1:drops1)
        {
        	JavascriptExecutor executor = (JavascriptExecutor) driver;
        	executor.executeScript("arguments[1].click();", obj1);
        	System.out.println(obj1.getText());
        }*/
	/*
		driver.findElement(By.xpath("/html/body/span/div/span/div/ng-view/div/div/bptree/child[6]/div/section/form/div[1]/div/child[3]/div/ng-form/div/div/div[2]/child[2]/div/div/ng-form/div[2]/div[1]/ul/li[4]"));
		WebElement aaa = driver.findElement(By.id("SelectedMotives"));
		BasePage base = new BasePage();
		//base.setSimpleDropdown(aa, "No me funciona internet");
		diagnosisTab page1 = new diagnosisTab(driver);
		page1.setMotive();
		Assert.assertTrue(page1.isExecuteButtonPresent());
	}
	*/
	//Need to be reviewed
	
	/*@Test
	public void TS6277_verifyTroubleshootingRulesAreAvailable() {
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try{ for(WebElement e : driver.findElements(By.className("x-tab-strip-close"))) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
		} } catch (org.openqa.selenium.StaleElementReferenceException e) {}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		goToLeftPanel(driver, "Cuentas");
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement frame1 = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(frame1);
		Accounts page0 = new Accounts(driver);
		//page0.clickOnV();
		page0.clickOnLetter("V");
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page0.clickOnFirstAccount();
		driver.switchTo().defaultContent();
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
		List<WebElement> accountTabs = driver.findElements(By.className("tabText"));
		accountTabs.get(2).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame2 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame2.get(2));
		List<WebElement> squares = driver.findElements(By.cssSelector(".console-card.active"));
		for (WebElement e : squares) {
			if (e.getText().contains("INTERNET")) {
		e.click(); }
		}
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
		
		driver.switchTo().defaultContent();
		try {
			for(WebElement frame : frame2) {
				driver.switchTo().frame(frame);
				driver.findElement(By.xpath("//*[@id=\"j_id0:j_id5\"]/div/div/ng-include/div/div[2]/div[3]/ng-include/section[2]/div[3]/ng-transclude/div/ng-include/div/div[1]/div/ng-include/div/ul/li[3]")).click();
				System.out.println("done.");
				break;
			}
		}catch(NoSuchElementException noSuchElemExcept) {
			driver.switchTo().defaultContent();
		}
		
		//driver.findElement(By.xpath("//*[@id=\"j_id0:j_id5\"]/div/div/ng-include/div/div[2]/div[3]/ng-include/section[2]/div[3]/ng-transclude/div/ng-include/div/div[1]/div/ng-include/div/ul/li[3]")).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		List<WebElement> frame3 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame3.get(4));
		diagnosisTab page1 = new diagnosisTab(driver);
		page1.setMotive();
		page1.clickOnExeccute();
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(driver.findElements(By.cssSelector(".slds-radio.ng-scope")).size() != 0);
	}
	*/
	
	/*@Test//User victorcito doesn't have internet connection
	public void TS6325_isInternetAvailable() {
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try{ for(WebElement e : driver.findElements(By.className("x-tab-strip-close"))) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
		} } catch (org.openqa.selenium.StaleElementReferenceException e) {}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		goToLeftPanel(driver, "Cuentas");
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Accounts accountPage = new Accounts(driver);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe")));
		accountPage.accountSelect("Vista Tech");
		/*WebElement frame1 = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(frame1);*/
		/*try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Accounts page0 = new Accounts(driver);
		page0.clickOnLetter("V");
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		page0.clickOnFirstAccount();
		driver.switchTo().defaultContent();
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
		List<WebElement> accountTabs = driver.findElements(By.className("tabText"));
		accountTabs.get(2).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame2 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame2.get(2));
		List<WebElement> squares = driver.findElements(By.cssSelector(".console-card.active"));
		Boolean a = false;
		for (WebElement e : squares) {
			if (e.getText().contains("Internet")) {//doesnt contain...
				a = true; 
			}
		}		
		Assert.assertTrue(a == true);
	}*/
	
	/*
	@Test
	public void TS0000_diagnosticInternetCheck() {
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try{ for(WebElement e : driver.findElements(By.className("x-tab-strip-close"))) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
		} } catch (org.openqa.selenium.StaleElementReferenceException e) {}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		goToLeftPanel(driver, "Cuentas");
		clickLeftPanel(driver);
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Accounts accountPage = new Accounts(driver);
		//Selecciono Vista Tech
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe")));
		accountPage.accountSelect("Vista Tech");
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//select accountName "Robo Tech", currently has index 10.
		accountPage.selectAccountByName("Robo Tech");
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accountPage.clickRightPanelButtonByName("Asistencia Técnica");
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		List<WebElement> frame = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame.get(4));
		//Service Selector
		driver.findElement(By.id("LookupSelectofService")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.getElementsByClassName('slds-list__item ng-binding ng-scope')[1].click()");
		/*WebElement serviceSelector = (accountPage.getServiceSelector());
		System.out.println(serviceSelector.getAttribute("aria-hidden"));
		serviceSelector.click();
		serviceSelector.sendKeys("Internet");*/
		//accountSelect;
	    /*js.executeScript("document.getElementsByClassName('slds-list__item ng-binding ng-scope')[0].click()");
		try {Thread.sleep(1500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("SelectServiceStep_nextBtn")).click();
		try {Thread.sleep(1500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//driver.findElement(By.id("SelectServiceStep_nextBtn")).click();
		//accountPage.continueFromService(); //next page.//no se puede clickear
		try {Thread.sleep(3500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//Contact Motive Selector
		driver.findElement(By.id("SelectedMotivesLookup")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		// the index for "No me funciona internet" is 8, 9 elements, 
		//some not visible (previous option elements)
	    js.executeScript("document.getElementsByClassName('slds-list__item ng-binding ng-scope')[8].click()");
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//execute.
		accountPage.executeInternetDiagnosis();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//checks togglers Text
		Assert.assertTrue(accountPage.isTextInTogglersPresent("Estado del Servicio"));
		Assert.assertTrue(accountPage.isTextInTogglersPresent("Asistencia"));
		goToLeftPanel2(driver, "Cuentas");
	}*/

	@Test(groups="a")
	public void TS6256_Boton_Ejecutar_Grisado_Chequeo() {
		//actually checks if not visible, it ist greyed out.
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try{ for(WebElement e : driver.findElements(By.className("x-tab-strip-close"))) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
		} } catch (org.openqa.selenium.StaleElementReferenceException e) {}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		goToLeftPanel(driver, "Cuentas");
		clickLeftPanel(driver);
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Accounts accountPage = new Accounts(driver);
		//Selecciono Vista Tech
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe")));
		accountPage.accountSelect("Vista Tech");
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//select accountName "Robo Tech", currently has index 10.
		accountPage.selectAccountByName("Robo Tech");
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accountPage.clickRightPanelButtonByName("Asistencia Técnica");
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		List<WebElement> frame = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame.get(4));
		//Service Selector
		Assert.assertFalse(accountPage.isExecuteButtonPresent());
		driver.findElement(By.id("LookupSelectofService")).click();
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertFalse(accountPage.isExecuteButtonPresent());
		JavascriptExecutor js = (JavascriptExecutor)driver;
	    js.executeScript("document.getElementsByClassName('slds-list__item ng-binding ng-scope')[0].click()");
		try {Thread.sleep(1500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertFalse(accountPage.isExecuteButtonPresent());
		goToLeftPanel2(driver, "Cuentas");
	}
	
	@Test
	public void TS6269_Habilitado_Al_Seleccionar_Motivo_Y_Sintoma() {
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try{ for(WebElement e : driver.findElements(By.className("x-tab-strip-close"))) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
		} } catch (org.openqa.selenium.StaleElementReferenceException e) {}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		goToLeftPanel(driver, "Cuentas");
		clickLeftPanel(driver);
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Accounts accountPage = new Accounts(driver);
		//Selecciono Vista Tech
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe")));
		accountPage.accountSelect("Vista Tech");
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//select accountName "Robo Tech", currently has index 10.
		accountPage.selectAccountByName("Robo Tech");
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accountPage.clickRightPanelButtonByName("Asistencia Técnica");
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frames.get(4));
		//Service Selector
		driver.findElement(By.id("LookupSelectofService")).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		JavascriptExecutor js = (JavascriptExecutor)driver;
	    js.executeScript("document.getElementsByClassName('slds-list__item ng-binding ng-scope')[0].click()");
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		
		accountPage.continueFromService();//mismo error
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		//Contact Motive Selector
		try {
			driver.switchTo().defaultContent();
			driver.findElement(By.id("SelectedMotivesLookup")).click();
		}catch(NoSuchElementException noSuchElemExcept){
			frames = driver.findElements(By.tagName("iframe"));
			for(WebElement frame : frames) {
				try {			
					driver.findElement(By.id("SelectedMotivesLookup")).click();
					break;
					//System.out.println("seleccionado");
				}catch(NoSuchElementException noSuchElemeExcept){
					//System.out.println("NoSeleccionado");
					driver.switchTo().defaultContent();
					driver.switchTo().frame(frame);
				}
			}
		}
		try {Thread.sleep(1500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		// the index for "No me funciona internet" is 8, 9 elements, 
		//some not visible (previous option elements)
	    js.executeScript("document.getElementsByClassName('slds-list__item ng-binding ng-scope')[6].click()");
		try {Thread.sleep(3500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(accountPage.isExecuteButtonPresent());
		goToLeftPanel2(driver, "Cuentas");
	}

	@Test
	public void TS6320_Visualizar_Motivo_de_Contacto_Y_Sintoma() {
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try{ for(WebElement e : driver.findElements(By.className("x-tab-strip-close"))) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
		} } catch (org.openqa.selenium.StaleElementReferenceException e) {}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		goToLeftPanel(driver, "Cuentas");
		clickLeftPanel(driver);
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Accounts accountPage = new Accounts(driver);
		//Selecciono Vista Tech
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe")));
		accountPage.accountSelect("Vista Tech");
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//select accountName "Robo Tech", currently has index 10.
		accountPage.selectAccountByName("Robo Tech");
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accountPage.clickRightPanelButtonByName("Asistencia Técnica");
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		List<WebElement> frame = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame.get(4));
		//Service Selector
		driver.findElement(By.id("LookupSelectofService")).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		JavascriptExecutor js = (JavascriptExecutor)driver;
		//Index 4? is for Robo TF Tech
	    js.executeScript("document.getElementsByClassName('slds-list__item ng-binding ng-scope')[4].click()");
	    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    accountPage.continueFromService();
	    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//Contact Motive Selector
		driver.findElement(By.id("SelectedMotivesLookup")).click();
		try {Thread.sleep(1500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		// the index for "No funciona mi telefono" is 5
		//some not visible (previous option elements)
	    js.executeScript("document.getElementsByClassName('slds-list__item ng-binding ng-scope')[5].click()");
		try {Thread.sleep(3500);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(accountPage.isExecuteButtonPresent());
		goToLeftPanel2(driver, "Cuentas");
	}

	@Test
	public void TS6410_Ingreso_A_Tech_Care_Desde_La_Vista_360() {
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try{ for(WebElement e : driver.findElements(By.className("x-tab-strip-close"))) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
		} } catch (org.openqa.selenium.StaleElementReferenceException e) {}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		goToLeftPanel(driver, "Cuentas");
		clickLeftPanel(driver);
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Accounts accountPage = new Accounts(driver);
		//Selecciono Vista Tech
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe")));
		accountPage.accountSelect("Vista Tech");
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//select accountName "Robo Tech", currently has index 10.
		accountPage.selectAccountByName("Robo Tech");
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		accountPage.clickRightPanelButtonByName("Asistencia Técnica");
		driver.switchTo().defaultContent();
		List<WebElement> frame = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame.get(4));
		//Service Selector
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("LookupSelectofService")).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		JavascriptExecutor js = (JavascriptExecutor)driver;
		//Index 4? is for Robo TF Tech
	    js.executeScript("document.getElementsByClassName('slds-list__item ng-binding ng-scope')[3].click()");
		goToLeftPanel2(driver, "Cuentas");//tech service existence assured.
	}
	
}
