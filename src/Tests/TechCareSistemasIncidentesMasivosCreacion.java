package Tests;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.BasePage;
import Pages.HomeBase;
import Pages.RegistroEventoMasivo;
import Pages.setConexion;

public class TechCareSistemasIncidentesMasivosCreacion extends TestBase {
	private WebDriver driver;
	
	@BeforeClass(groups = "Fase2") 
	public void init() throws Exception
	{
		this.driver = setConexion.setupEze();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		login(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	@BeforeMethod(groups = "Fase2") 
	  public void setUp() throws Exception {
	    
	    //Entrar en Velocity Telecomunication servics
	    try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    String a = driver.findElement(By.id("tsidLabel")).getText();
	    if (a.contains("Vlocity Telecommunication...")){}
	    else {
	      driver.findElement(By.id("tsidButton")).click();
	      try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	      driver.findElement(By.xpath("//a[@href=\"/home/home.jsp?tsid=02uc0000000D663\"]")).click();
	    }
	    
	    //click +
	    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    driver.findElement(By.xpath("//a[@href=\"/home/showAllTabs.jsp\"]")).click();
	    try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	        
	    //click velociti omniscript designer
	    //WebElement BenBoton = driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[3]/div[2]/table/tbody/tr[59]/td[2]/a"));
	   
	    List<WebElement> optns= driver.findElements(By.cssSelector(".dataCol.Custom108Block.col02"));
	    for (WebElement option : optns) {
			if(option.getText().toLowerCase().equals("Vlocity OmniScript Designer".toLowerCase())){
				WebElement BenBoton = option.findElement(By.tagName("a"));
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+BenBoton.getLocation().y+")");
			    BenBoton.click();
				break;
			}
		}
	    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	        
	    //click techcare massive create
	    WebElement tcmc = driver.findElement(By.id("omni-home-group-44-toggle-button"));
	    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+tcmc.getLocation().y+")");
	    tcmc.click();
	    try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	        
	    //click version 8
	    //WebElement cv8 = driver.findElement(By.xpath("//a[text()=\"ta-techCare-MassiveIncident-CreateCase (Version 8)\"]"));
	    //click version 9
	    WebElement cv8 = driver.findElement(By.xpath("//a[text()=\"ta-techCare-MassiveIncident-CreateCase (Version 9)\"]"));
	    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+cv8.getLocation().y+")");
	    cv8.click();
	    try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	          
	    //click preview
	    WebElement cpw = driver.findElement(By.xpath("//*[@id=\"bodyTable\"]/tbody/tr/td/div[2]/div[4]/div/div/div[3]/div[1]/ul/li[2]/a"));
	    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+cpw.getLocation().y+")");
	    cpw.click();
	    try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    
	    }
	
	@AfterMethod(groups = "Fase2") 
	public void closeTechCareTab() {
		driver.switchTo().defaultContent();
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+ driver.findElement(By.id("tsidButton")).getLocation().y+")");
        driver.findElement(By.id("tsidButton")).click();
        try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
        List<WebElement> options = driver.findElement(By.id("tsid-menuItems")).findElements(By.tagName("a"));
		for (WebElement option : options) {
			if(option.getText().toLowerCase().equals("Sales".toLowerCase()) || option.getText().toLowerCase().equals("Ventas".toLowerCase()) ){
				option.click();
				break;
			}
		}
	}
	
	@AfterClass
		public void tearDown() {
			driver.close();
		}
	
	@Test(groups = "Fase2") 
	public void TS16336_Fecha_Estimada_De_Cierre_No_Obligatorio() {
		 RegistroEventoMasivo REM = new RegistroEventoMasivo(driver);
		Accounts accPage = new Accounts(driver);
		REM.llenarListas(By.id("EndDate"));
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		REM.llenarTextos(By.id("EndDate"));
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("TextIncidentTitle")).sendKeys("Algo mas");
		REM.ClickOnSave();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("ConfirmationText")));
		assertTrue(driver.findElement(By.id("ConfirmationText")).getText().contains("correctamente el incidente masivo"));
	}
	
	@Test(groups = "Fase2") 
	public void TS16318_Fecha_De_Inicio_Formato() {
		RegistroEventoMasivo REM = new RegistroEventoMasivo(driver);
		Accounts accPage = new Accounts(driver);
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("InputValues_nextBtn")));
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+ driver.findElement(By.id("StartDate")).getLocation().y+")");
		driver.findElement(By.id("StartDate")).click();
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+ driver.findElement(By.id("datepickers-container")).findElement(By.cssSelector(".datepicker--cell.datepicker--cell-day.-current-")).getLocation().y+")");
		driver.findElement(By.id("datepickers-container")).findElement(By.cssSelector(".datepicker--cell.datepicker--cell-day.-current-")).click();
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		assertTrue(REM.validarFecha(driver.findElement(By.id("StartDate")).getAttribute("value"), "dd/mm/yyyy HH:mm"));
	}
	
	@Test(groups = "Fase2") 
	public void TS16321_Fecha_De_Fin_Formato() {
		RegistroEventoMasivo REM = new RegistroEventoMasivo(driver);
		Accounts accPage = new Accounts(driver);
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("InputValues_nextBtn")));
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+ driver.findElement(By.id("EndDate")).getLocation().y+")");
		driver.findElement(By.id("EndDate")).click();
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("datepickers-container")).findElements(By.cssSelector(".datepicker--cell.datepicker--cell-day.-current-")).get(1).click();
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		assertTrue(REM.validarFecha(driver.findElement(By.id("EndDate")).getAttribute("value"), "dd/mm/yyyy HH:mm") || REM.validarFecha(driver.findElement(By.id("EndDate")).getAttribute("value"), "dd-mm-yyyy HH:mm"));
	}
	
	@Test(groups = "Fase2") 
	public void TS16246_Canal_Formato() {
		RegistroEventoMasivo REM = new RegistroEventoMasivo(driver);
		Accounts accPage = new Accounts(driver);
		boolean estan = true;
		String[] todos = {"web","ivr","sms","sistema de gestion","ussd","todos los medios","redes sociales"};
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.id("InputValues_nextBtn")));
		driver.findElement(By.id("SelectChannel")).click();
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> optns = driver.findElement(By.id("SelectChannel")).findElements(By.tagName("option"));
		List<String> textos = new ArrayList<String>();
		for (WebElement optn : optns)
		{
			textos.add(optn.getText().toLowerCase());
			System.out.println("optn: "+optn.getText());
		}
		for (String uno : todos) {
			if (!textos.contains(uno)) {
				System.out.println("uno: "+uno);
				estan = false;
			}
		}
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		assertTrue(estan);
	}
	
}
