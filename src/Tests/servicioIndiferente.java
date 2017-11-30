package Tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.BasePage;
import Pages.CustomerCare;
import Pages.HomeBase;
import Pages.setConexion;



/**
 * Ejecutar desde el modulo de venta
 * @author Almer
 *
 */

public class servicioIndiferente extends TestBase {
	private WebDriver driver;

	@BeforeClass(groups = "Fase2")
	public void Init() throws Exception
	{
		this.driver = setConexion.setupEze();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		login(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	@BeforeMethod(groups = "Fase2")
	public void setUp() throws Exception {
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		HomeBase homePage = new HomeBase(driver);
	       if(driver.findElement(By.id("tsidLabel")).getText().equals("Consola FAN")) {
	        homePage.switchAppsMenu();
	        try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	        homePage.selectAppFromMenuByName("Ventas");
	        try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}    
	       }
	       homePage.switchAppsMenu();
	       try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	       homePage.selectAppFromMenuByName("Consola FAN");
	       
	       CustomerCare cerrar = new CustomerCare(driver);
			cerrar.cerrarultimapestaña();
		}
	
	@AfterMethod(groups = "Fase2")
	public void afterMethod() {
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> mainTabs = driver.findElements(By.className("x-tab-strip-close"));
		  for (WebElement e : mainTabs) {
		  try {((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);} catch (org.openqa.selenium.StaleElementReferenceException b) {}
		  }
	}
	
	@AfterClass(groups = "Fase2")
	public void tearDown() {
		driver.close();
	}
	
	
	@Test(groups = "Fase2")
	public void TS11600_CRM_Fase_2_Technical_Care_CSR_Diagnostico_Servicio_Indiferente_Boton_ejecutar_no_disponible(){
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
	field.selectByVisibleText("Todas Las cuentas");
	try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	List<WebElement> accounts = driver.findElements(By.xpath("//*[text() = 'Adrian Tech']"));
	accounts.get(0).click();
	
	try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	driver.switchTo().defaultContent();
	try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	if(driver.findElements(By.cssSelector(".x-layout-collapsed.x-layout-collapsed-east.x-layout-cmini-east")).size() != 0) {
		driver.findElement(By.cssSelector(".x-layout-collapsed.x-layout-collapsed-east.x-layout-cmini-east")).click();
		}
	try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	
	//Cambia el Frame
	BasePage sImput=new BasePage();
	List<WebElement> frame1 = driver.findElements(By.tagName("iframe"));
	int indexFrame = sImput.getIndexFrame(driver, By.xpath("/html/body/div/div[1]/ng-include/div/div[1]/ng-include/div/div[2]/input"));
	driver.switchTo().frame(frame1.get(indexFrame));
	
	//Escribe en el search Imput y hace Click
	WebElement  buscar = driver.findElement(By.xpath("/html/body/div/div[1]/ng-include/div/div[1]/ng-include/div/div[2]/input"));
	buscar.sendKeys("Asisten");
	try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	WebElement aTecnica= driver.findElement(By.xpath("//*[text() = 'Asistencia Técnica']"));
	aTecnica.click();
	
	
	driver.switchTo().defaultContent();
	try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
    BasePage pagina = new BasePage (driver);
    driver.switchTo().frame(pagina.getFrameForElement(driver, By.id("LookupSelectofService")));
    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
    WebElement BenBoton = driver.findElement((By.id("LookupSelectofService")));
    BenBoton.click();
    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
    JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("document.getElementsByClassName('slds-list__item ng-binding ng-scope')[0].click();");
	WebElement Continuar = driver.findElement((By.id("SelectServiceStep_nextBtn")));
	((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+Continuar.getLocation().y+")");
	try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	Continuar.click();
	try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	
	
	//Click ventana emergente
	try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	 JavascriptExecutor jsa = (JavascriptExecutor)driver;
	jsa.executeScript("document.getElementsByClassName('slds-button slds-button--neutral ng-binding ng-scope')[1].click();");
	try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	
	//Verificamos la prueba
	boolean Error=false;
	try {
	if(driver.findElement((By.id("IntegProc_Diagnóstico"))).isDisplayed())
		Error=false;
	}
	catch (org.openqa.selenium.NoSuchElementException e) {Error=true; }
	assertTrue(Error);
	driver.switchTo().defaultContent();	
	}
	
	
}
