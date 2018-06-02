package Tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Pages.OM;
import Pages.SCP;
import Pages.setConexion;

public class OM_Mattu extends TestBase{

	private WebDriver driver;
	protected OM page;
	
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
	
	//@AfterClass(alwaysRun=true)
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
	    
	    driver.findElement(By.id("fname")).sendKeys("OM_View_Mattu");
	    
	    Select sSelectDropdown = new Select(driver.findElement(By.id("fcol1")));
		sSelectDropdown.selectByVisibleText("Order Number");
		sSelectDropdown = new Select(driver.findElement(By.id("fop1")));
		sSelectDropdown.selectByVisibleText("greater than");
		driver.findElement(By.id("fval1")).sendKeys("350");
		
		/*sSelectDropdown = new Select(driver.findElement(By.id("fcol2")));
		sSelectDropdown.selectByVisibleText("Order Number");
		sSelectDropdown = new Select(driver.findElement(By.id("fop2")));
		sSelectDropdown.selectByVisibleText("not equal to");
		driver.findElement(By.id("fval2")).sendKeys("320");*/
		
		sSelectDropdown = new Select(driver.findElement(By.id("fcol3")));
		sSelectDropdown.selectByVisibleText("Order Amount");
		sSelectDropdown = new Select(driver.findElement(By.id("fop3")));
		sSelectDropdown.selectByVisibleText("less than");
		driver.findElement(By.id("fval3")).sendKeys("80");
		
		sSelectDropdown = new Select(driver.findElement(By.id("fcol4")));
		sSelectDropdown.selectByVisibleText("Order Amount");
		sSelectDropdown = new Select(driver.findElement(By.id("fop4")));
		sSelectDropdown.selectByVisibleText("equals");
		driver.findElement(By.id("fval4")).sendKeys("423");
		
		driver.findElement(By.name("save")).click();
		
		sleep(5000);
		boolean bAssert = true;
		WebElement wBody = driver.findElement(By.id("ext-gen10"));
		List<String> sColumn = traerColumna(wBody, 2);
		
		for (String sAux : sColumn) {
			int iAux = Integer.parseInt(sAux);
			if (iAux < 350) {
				bAssert = false;
			}
		}
		
		sColumn = traerColumna(wBody, 4);
		for (String sAux : sColumn) {
			sAux = sAux.substring(1, sAux.length()).replace(",", ".");
			double fAux = Double.parseDouble(sAux);
			if (fAux > 80 && fAux != 423) {
				bAssert = false;
			}
		}
		
		/*List<WebElement> wFilterLinks = driver.findElement(By.className("filterLinks")).findElements(By.tagName("a"));
		wFilterLinks.get(1).click();
		
		Alert confirmDelete = driver.switchTo().alert();
		confirmDelete.accept();*/
		
		Assert.assertTrue(bAssert);
	}
	
	public List<String> traerColumna(WebElement wBody, int iColumn) {
		List<WebElement> wRows = wBody.findElements(By.tagName("tr"));
		List<String> sColumn = new ArrayList<String>();
		for(WebElement wAux : wRows) {
			List<WebElement> wTd = wAux.findElements(By.tagName("td"));
			sColumn.add(wTd.get(iColumn).getText());
		}

		return sColumn;
	}

}