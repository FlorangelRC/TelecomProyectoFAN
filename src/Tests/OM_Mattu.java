package Tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Pages.OM;
import Pages.SCP;
import Pages.setConexion;

public class OM_Mattu extends TestBase{

	private WebDriver driver;
	
	//-------------------------------------------------------------------------------------------------
	//@Befor&After
	@BeforeClass(alwaysRun=true)
	public void readySteady() throws Exception {
		this.driver = setConexion.setupEze();
		sleep(5000);
		//Usuario Victor OM
		login(driver, "https://crm--sit.cs14.my.salesforce.com/", "U585991", "Testa10k");
		sleep(5000);
	}
	
	@BeforeMethod(alwaysRun=true)
	public void setUp() throws Exception {
		driver.switchTo().defaultContent();
		sleep(2000);
		SCP pageSCP= new SCP(driver);
		pageSCP.goToMenu("Ventas");
		
		//click +
		sleep(5000);
		OM pageOm=new OM(driver);
		pageOm.clickMore();
		sleep(3000);
		
		//click en Ordenes
		pageOm.clickOnListTabs("Orders");
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDown() {
		sleep(2000);
		driver.quit();
		sleep(1000);
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 1
	@Test(groups = "OM")
	public void TS8214_OM_Ordenes_Panel_Principal_Busqueda(){
		 
	    List<WebElement> wLinks = driver.findElement(By.className("fFooter")).findElements(By.tagName("a"));
	 
	    for (WebElement wAux : wLinks) {
	 
	      if(wAux.getText().equals("Create New View")) {
	 
	        wAux.click();
	 
	      }
	 
	    }
	 
	    
	 
	    driver.findElement(By.id("fscope1")).click();
	 
	    
	 
	  }
	 

}