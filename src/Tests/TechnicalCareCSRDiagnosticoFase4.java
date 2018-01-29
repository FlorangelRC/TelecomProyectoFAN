package Tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.AccountType;
import Pages.Accounts;
import Pages.BasePage;
import Pages.HomeBase;
import Pages.TechQuelysPage;
import Pages.TechnicalCareCSRDiagnosticoPage;
import Pages.setConexion;


public class TechnicalCareCSRDiagnosticoFase4 extends TestBase{
	
private WebDriver driver;
	
 	@BeforeClass(groups= {"Fase4","TechnicalCare", "Diagnostico"})
 	public void init() throws InterruptedException{
	
	this.driver = setConexion.setupEze();
    sleep(5000);
    login(driver);
    sleep(5000);
    HomeBase homePage = new HomeBase(driver);
       if(driver.findElement(By.id("tsidLabel")).getText().equals("Consola FAN")) {
         homePage.switchAppsMenu();
         sleep(2000);
         homePage.selectAppFromMenuByName("Ventas");
         sleep(5000);
       }
       homePage.switchAppsMenu();
       sleep(2000);
       homePage.selectAppFromMenuByName("Consola FAN");
       sleep(5000);
	   goToLeftPanel2(driver, "Cuentas");
	   sleep(2000);  
	}
 	
 	@BeforeMethod(groups = "TechnicalCare") 
	public void setUp() throws Exception {
	 Accounts accountPage = new Accounts(driver);
     //Selecciono Vista Tech
     driver.switchTo().defaultContent();
     accountPage.accountSelect("Vista Tech");
     sleep(8000);
     accountPage.selectAccountByName("Adrian Tech");
 	}

 	@Test
 	public void TS74093_CRM_Fase_4_Technical_Care_CSR_Diagnostico_Verificacion_de_consulta_al_HLR_despues_de_desregistrar() {
 		BasePage cambioFrameByID=new BasePage();
		TechnicalCareCSRDiagnosticoPage tech = new TechnicalCareCSRDiagnosticoPage(driver);
 		sleep (4000); 
 		/*
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		Integer numberOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
		System.out.println("Number of iframes on the page are " + numberOfFrames);*/
 		  driver.switchTo().frame(driver.findElement(By.id("j_id0:j_id5")));
 		  
 		tech.getPlanConTarjeta().click(); 
 		
	}
}
	



