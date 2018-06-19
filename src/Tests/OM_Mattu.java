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

import Pages.BasePage;
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
		cambiarListaLightningAVistaClasica(driver);
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
		driver.findElement(By.id("fval1")).sendKeys("00000350");
		
		sSelectDropdown = new Select(driver.findElement(By.id("fcol2")));
		sSelectDropdown.selectByVisibleText("Order Number");
		sSelectDropdown = new Select(driver.findElement(By.id("fop2")));
		sSelectDropdown.selectByVisibleText("not equal to");
		driver.findElement(By.id("fval2")).sendKeys("00000372");
		
		sSelectDropdown = new Select(driver.findElement(By.id("fcol3")));
		sSelectDropdown.selectByVisibleText("Order Amount");
		sSelectDropdown = new Select(driver.findElement(By.id("fop3")));
		sSelectDropdown.selectByVisibleText("less than");
		driver.findElement(By.id("fval3")).sendKeys("80");
		
		sSelectDropdown = new Select(driver.findElement(By.id("fcol4")));
		sSelectDropdown.selectByVisibleText("Order Amount");
		sSelectDropdown = new Select(driver.findElement(By.id("fop4")));
		sSelectDropdown.selectByVisibleText("not equal to");
		driver.findElement(By.id("fval4")).sendKeys("423");
		
		driver.findElement(By.name("save")).click();
		
		sleep(5000);
		boolean bAssert = true;
		WebElement wBody = driver.findElement(By.id("ext-gen10"));
		List<String> sColumn = traerColumna(wBody, 2);
		
		for (String sAux : sColumn) {
			int iAux = Integer.parseInt(sAux);
			if (iAux <= 350 || iAux == 372) {
				bAssert = false;
			}
		}
		
		sColumn = traerColumna(wBody, 4);
		for (String sAux : sColumn) {
			sAux = sAux.substring(1, sAux.length()).replace(",", ".");
			double fAux = Double.parseDouble(sAux);
			if (fAux >= 80 || fAux == 423) {
				bAssert = false;
			}
		}
		
		List<WebElement> wFilterLinks = driver.findElement(By.className("filterLinks")).findElements(By.tagName("a"));
		wFilterLinks.get(1).click();
		
		Alert confirmDelete = driver.switchTo().alert();
		confirmDelete.accept();
		
		Assert.assertTrue(bAssert);
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 2
	@Test(groups = "OM")
	public void TS8217_OM_Ordenes_Panel_Principal_Busqueda_Por_todos_los_campos(){		 
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
		driver.findElement(By.id("fval1")).sendKeys("00000350");
		
		sSelectDropdown = new Select(driver.findElement(By.id("fcol2")));
		sSelectDropdown.selectByVisibleText("Order Number");
		sSelectDropdown = new Select(driver.findElement(By.id("fop2")));
		sSelectDropdown.selectByVisibleText("not equal to");
		driver.findElement(By.id("fval2")).sendKeys("00000372");
		
		driver.findElement(By.name("save")).click();
		
		sleep(5000);
		boolean bAssert = true;
		WebElement wBody = driver.findElement(By.id("ext-gen10"));
		List<String> sColumn = traerColumna(wBody, 2);
		
		for (String sAux : sColumn) {
			int iAux = Integer.parseInt(sAux);
			if (iAux <= 350 || iAux == 372) {
				bAssert = false;
			}
		}
		
		List<WebElement> wFilterLinks = driver.findElement(By.className("filterLinks")).findElements(By.tagName("a"));
		wFilterLinks.get(1).click();
		
		Alert confirmDelete = driver.switchTo().alert();
		confirmDelete.accept();
		
		Assert.assertTrue(bAssert);
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 3
	@Test(groups = "OM")
	public void TS8218_OM_Ordenes_Panel_Principal_Busqueda_Operador_Igual_a(){		 
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
		sSelectDropdown.selectByVisibleText("equals");
		driver.findElement(By.id("fval1")).sendKeys("00000350");
		
		driver.findElement(By.name("save")).click();
		
		sleep(5000);
		boolean bAssert = true;
		WebElement wBody = driver.findElement(By.id("ext-gen10"));
		List<String> sColumn = traerColumna(wBody, 2);
		
		for (String sAux : sColumn) {
			int iAux = Integer.parseInt(sAux);
			if (iAux != 350) {
				bAssert = false;
			}
		}
		
		List<WebElement> wFilterLinks = driver.findElement(By.className("filterLinks")).findElements(By.tagName("a"));
		wFilterLinks.get(1).click();
		
		Alert confirmDelete = driver.switchTo().alert();
		confirmDelete.accept();
		
		Assert.assertTrue(bAssert);
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 4
	@Test(groups = "OM")
	public void TS8219_OM_Ordenes_Panel_Principal_Busqueda_Operador_No_Igual_a(){		 
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
		sSelectDropdown.selectByVisibleText("not equal to");
		driver.findElement(By.id("fval1")).sendKeys("00000350");
		
		sSelectDropdown = new Select(driver.findElement(By.id("fcol2")));
		sSelectDropdown.selectByVisibleText("Order Number");
		sSelectDropdown = new Select(driver.findElement(By.id("fop2")));
		sSelectDropdown.selectByVisibleText("less than");
		driver.findElement(By.id("fval2")).sendKeys("00000404");
		
		driver.findElement(By.name("save")).click();
		
		sleep(5000);
		boolean bAssert = true;
		WebElement wBody = driver.findElement(By.id("ext-gen10"));
		List<String> sColumn = traerColumna(wBody, 2);
		
		for (String sAux : sColumn) {
			int iAux = Integer.parseInt(sAux);
			if (iAux == 350) {
				bAssert = false;
			}
		}
		
		sColumn = traerColumna(wBody, 2);
		for (String sAux : sColumn) {
			int iAux = Integer.parseInt(sAux);
			if (iAux >= 404) {
				bAssert = false;
			}
		}
		
		List<WebElement> wFilterLinks = driver.findElement(By.className("filterLinks")).findElements(By.tagName("a"));
		wFilterLinks.get(1).click();
		
		Alert confirmDelete = driver.switchTo().alert();
		confirmDelete.accept();
		
		Assert.assertTrue(bAssert);
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 5
	@Test(groups = "OM")
	public void TS8220_OM_Ordenes_Panel_Principal_Busqueda_Operador_Menor_que(){		 
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
		sSelectDropdown.selectByVisibleText("less than");
		driver.findElement(By.id("fval1")).sendKeys("00000404");
		
		driver.findElement(By.name("save")).click();
		
		sleep(5000);
		boolean bAssert = true;
		WebElement wBody = driver.findElement(By.id("ext-gen10"));
		List<String> sColumn = traerColumna(wBody, 2);
		
		for (String sAux : sColumn) {
			int iAux = Integer.parseInt(sAux);
			if (iAux >= 404) {
				bAssert = false;
			}
		}
		
		List<WebElement> wFilterLinks = driver.findElement(By.className("filterLinks")).findElements(By.tagName("a"));
		wFilterLinks.get(1).click();
		
		Alert confirmDelete = driver.switchTo().alert();
		confirmDelete.accept();
		
		Assert.assertTrue(bAssert);
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 6
	@Test(groups = "OM")
	public void TS8221_OM_Ordenes_Panel_Principal_Busqueda_Operador_Mayor_que(){		 
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
		driver.findElement(By.id("fval1")).sendKeys("00030321");
		
		driver.findElement(By.name("save")).click();
		
		sleep(5000);
		boolean bAssert = true;
		WebElement wBody = driver.findElement(By.id("ext-gen10"));
		List<String> sColumn = traerColumna(wBody, 2);
		
		for (String sAux : sColumn) {
			int iAux = Integer.parseInt(sAux);
			if (iAux <= 30321) {
				bAssert = false;
			}
		}
		
		List<WebElement> wFilterLinks = driver.findElement(By.className("filterLinks")).findElements(By.tagName("a"));
		wFilterLinks.get(1).click();
		
		Alert confirmDelete = driver.switchTo().alert();
		confirmDelete.accept();
		
		Assert.assertTrue(bAssert);
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 7
	@Test(groups = "OM")
	public void TS8222_OM_Ordenes_Panel_Principal_Busqueda_Operador_Menor_o_Igual(){		 
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
		sSelectDropdown.selectByVisibleText("less or equal");
		driver.findElement(By.id("fval1")).sendKeys("00000404");
		
		driver.findElement(By.name("save")).click();
		
		sleep(5000);
		boolean bAssert = true;
		WebElement wBody = driver.findElement(By.id("ext-gen10"));
		List<String> sColumn = traerColumna(wBody, 2);
		
		for (String sAux : sColumn) {
			int iAux = Integer.parseInt(sAux);
			if (iAux > 404) {
				bAssert = false;
			}
		}
		
		List<WebElement> wFilterLinks = driver.findElement(By.className("filterLinks")).findElements(By.tagName("a"));
		wFilterLinks.get(1).click();
		
		Alert confirmDelete = driver.switchTo().alert();
		confirmDelete.accept();
		
		Assert.assertTrue(bAssert);
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 8
	@Test(groups = "OM")
	public void TS8223_OM_Ordenes_Panel_Principal_Busqueda_Operador_Mayor_o_Igual(){		 
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
		sSelectDropdown.selectByVisibleText("greater or equal");
		driver.findElement(By.id("fval1")).sendKeys("00030321");
		
		driver.findElement(By.name("save")).click();
		
		sleep(5000);
		boolean bAssert = true;
		WebElement wBody = driver.findElement(By.id("ext-gen10"));
		List<String> sColumn = traerColumna(wBody, 2);
		
		for (String sAux : sColumn) {
			int iAux = Integer.parseInt(sAux);
			if (iAux < 30321) {
				bAssert = false;
			}
		}
		
		List<WebElement> wFilterLinks = driver.findElement(By.className("filterLinks")).findElements(By.tagName("a"));
		wFilterLinks.get(1).click();
		
		Alert confirmDelete = driver.switchTo().alert();
		confirmDelete.accept();
		
		Assert.assertTrue(bAssert);
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 9
	@Test(groups = "OM")
	public void TS8224_OM_Ordenes_Panel_Principal_Busqueda_Operador_Contiene(){		 
	    List<WebElement> wLinks = driver.findElement(By.className("fFooter")).findElements(By.tagName("a"));
	    for (WebElement wAux : wLinks) {
	      if(wAux.getText().equals("Create New View")) {
	        wAux.click();
	      }
	    }
	    
	    driver.findElement(By.id("fname")).sendKeys("OM_View_Mattu");
	    
	    Select sSelectDropdown = new Select(driver.findElement(By.id("fcol1")));
		sSelectDropdown.selectByVisibleText("Account Name");
		sSelectDropdown = new Select(driver.findElement(By.id("fop1")));
		sSelectDropdown.selectByVisibleText("contains");
		driver.findElement(By.id("fval1")).sendKeys("Mattu");
		
		driver.findElement(By.name("save")).click();
		
		sleep(5000);
		boolean bAssert = true;
		WebElement wBody = driver.findElement(By.id("ext-gen10"));
		List<String> sColumn = traerColumna(wBody, 3);
		
		for (String sAux : sColumn) {
			if (!sAux.contains("Mattu")) {
				bAssert = false;
			}
		}
		
		List<WebElement> wFilterLinks = driver.findElement(By.className("filterLinks")).findElements(By.tagName("a"));
		wFilterLinks.get(1).click();
		
		Alert confirmDelete = driver.switchTo().alert();
		confirmDelete.accept();
		
		Assert.assertTrue(bAssert);
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 10
	@Test(groups = "OM")
	public void TS8225_OM_Ordenes_Panel_Principal_Busqueda_Operador_No_Contiene(){		 
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
		sSelectDropdown.selectByVisibleText("less than");
		driver.findElement(By.id("fval1")).sendKeys("00025375");
		
		sSelectDropdown = new Select(driver.findElement(By.id("fcol2")));
		sSelectDropdown.selectByVisibleText("Order Number");
		sSelectDropdown = new Select(driver.findElement(By.id("fop2")));
		sSelectDropdown.selectByVisibleText("greater than");
		driver.findElement(By.id("fval2")).sendKeys("00025365");
		
		sSelectDropdown = new Select(driver.findElement(By.id("fcol3")));
		sSelectDropdown.selectByVisibleText("Account Name");
		sSelectDropdown = new Select(driver.findElement(By.id("fop3")));
		sSelectDropdown.selectByVisibleText("does not contain");
		driver.findElement(By.id("fval3")).sendKeys("Mattu");
		
		driver.findElement(By.name("save")).click();
		
		sleep(5000);
		boolean bAssert = true;
		WebElement wBody = driver.findElement(By.id("ext-gen10"));
		List<String> sColumn = traerColumna(wBody, 3);
		
		for (String sAux : sColumn) {
			if (sAux.contains("Mattu")) {
				bAssert = false;
			}
		}
		
		List<WebElement> wFilterLinks = driver.findElement(By.className("filterLinks")).findElements(By.tagName("a"));
		wFilterLinks.get(1).click();
		
		Alert confirmDelete = driver.switchTo().alert();
		confirmDelete.accept();
		
		Assert.assertTrue(bAssert);
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 11
	@Test(groups = "OM")
	public void TS8226_OM_Ordenes_Panel_Principal_Busqueda_Operador_Comienza_Por(){		 
	    List<WebElement> wLinks = driver.findElement(By.className("fFooter")).findElements(By.tagName("a"));
	    for (WebElement wAux : wLinks) {
	      if(wAux.getText().equals("Create New View")) {
	        wAux.click();
	      }
	    }
	    
	    driver.findElement(By.id("fname")).sendKeys("OM_View_Mattu");
	    
	    Select sSelectDropdown = new Select(driver.findElement(By.id("fcol1")));
		sSelectDropdown.selectByVisibleText("Account Name");
		sSelectDropdown = new Select(driver.findElement(By.id("fop1")));
		sSelectDropdown.selectByVisibleText("starts with");
		driver.findElement(By.id("fval1")).sendKeys("Mattu");
		
		driver.findElement(By.name("save")).click();
		
		sleep(5000);
		boolean bAssert = true;
		WebElement wBody = driver.findElement(By.id("ext-gen10"));
		List<String> sColumn = traerColumna(wBody, 3);
		
		for (String sAux : sColumn) {
			if (!sAux.startsWith("Mattu")) {
				bAssert = false;
			}
		}
		
		List<WebElement> wFilterLinks = driver.findElement(By.className("filterLinks")).findElements(By.tagName("a"));
		wFilterLinks.get(1).click();
		
		Alert confirmDelete = driver.switchTo().alert();
		confirmDelete.accept();
		
		Assert.assertTrue(bAssert);
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 12
	@Test(groups = "OM")
	public void TS8230_OM_Ordenes_Panel_Principal_Busqueda_Separado_Por_Comas(){		 
	    List<WebElement> wLinks = driver.findElement(By.className("fFooter")).findElements(By.tagName("a"));
	    for (WebElement wAux : wLinks) {
	      if(wAux.getText().equals("Create New View")) {
	        wAux.click();
	      }
	    }
	    
	    driver.findElement(By.id("fname")).sendKeys("OM_View_Mattu");
	    
	    Select sSelectDropdown = new Select(driver.findElement(By.id("fcol1")));
		sSelectDropdown.selectByVisibleText("Account Name");
		sSelectDropdown = new Select(driver.findElement(By.id("fop1")));
		sSelectDropdown.selectByVisibleText("contains");
		driver.findElement(By.id("fval1")).sendKeys("FlorOM, Mattu");
		
		driver.findElement(By.name("save")).click();
		
		sleep(5000);
		boolean bAssert = true;
		WebElement wBody = driver.findElement(By.id("ext-gen10"));
		List<String> sColumn = traerColumna(wBody, 3);
		
		for (String sAux : sColumn) {
			if (!sAux.contains("Mattu") && !sAux.contains("Flor")) {
				bAssert = false;
			}
		}
		
		List<WebElement> wFilterLinks = driver.findElement(By.className("filterLinks")).findElements(By.tagName("a"));
		wFilterLinks.get(1).click();
		
		Alert confirmDelete = driver.switchTo().alert();
		confirmDelete.accept();
		
		Assert.assertTrue(bAssert);
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 12 Bis
	@Test(groups = "OM")
	public void TS6723_OM_Ordenes_Vista_Configuración_Borrar_Vista(){
		crearVistaOM("OM_View_Mattu", "OM_Test_Mattu");
		crearVistaOM("OM_View_Mattu_2", "OM_Test_Mattu");
		
		sleep(5000);
		selectVistaByVisibleText("OM_View_Mattu");
		sleep(2000);
		List<WebElement> wFilterLinks = driver.findElement(By.className("filterLinks")).findElements(By.tagName("a"));
		wFilterLinks.get(1).click();
		Alert confirmDelete = driver.switchTo().alert();
		confirmDelete.accept();
		
		sleep(5000);
		selectVistaByVisibleText("OM_View_Mattu_2");
		sleep(2000);
		wFilterLinks = driver.findElement(By.className("filterLinks")).findElements(By.tagName("a"));
		wFilterLinks.get(1).click();
		confirmDelete = driver.switchTo().alert();
		confirmDelete.accept();
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 14
	@Test(groups = "OM")
	public void TS6727_OM_Ordenes_Order_Detail_Visualización_del_flujo_de_orquestacion(){
		selectVistaByVisibleText("All Orders");
		selectVistaByVisibleText("OM_View_Mattu_Static");
		sleep(10000);
		driver.switchTo().defaultContent();
		WebElement wBody = driver.findElement(By.id("ext-gen10"));
		List<WebElement> wColumn = traerElementoColumna(wBody, 2);
		for (WebElement wAux : wColumn) {
			if (wAux.getText().contains("25365")) {
				wAux.findElement(By.tagName("a")).click();
				break;
			}
		}
		sleep(3000);
		WebElement wTopMenu = driver.findElement(By.id("topButtonRow"));
		wTopMenu.findElement(By.name("vlocity_cmt__vieworchestrationplan")).click();
		
		sleep(15000);
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTab.get(1));
		sleep(5000);
		driver.findElement(By.id("idlist")).click();
		sleep(10000);
		newTab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTab.get(0));
		sleep(10000);
		
		String sOrderLabel = driver.findElement(By.className("order-label-container")).getText();
		System.out.println("sOrderLabel.getText: " + sOrderLabel);
		Assert.assertTrue(sOrderLabel.contains("25365"));
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 15
	@Test(groups = "OM")
	public void TS79026_OM_Ordenes_Cliente_existente_Alta_de_linea_Sin_delivery_Sin_VAS(){
		page.crearOrden("MattuOM");
		
	}
	
	//-------------------------------------------------------------------------------------------------
	//Methods
	
	public List<String> traerColumna(WebElement wBody, int iColumn) {
		List<WebElement> wRows = wBody.findElements(By.tagName("tr"));
		List<String> sColumn = new ArrayList<String>();
		for(WebElement wAux : wRows) {
			List<WebElement> wTd = wAux.findElements(By.tagName("td"));
			sColumn.add(wTd.get(iColumn).getText());
		}

		return sColumn;
	}
	
	public List<WebElement> traerElementoColumna(WebElement wBody, int iColumn) {
		List<WebElement> wRows = wBody.findElements(By.tagName("tr"));
		List<WebElement> wColumn = new ArrayList<WebElement>();
		for(WebElement wAux : wRows) {
			List<WebElement> wTd = wAux.findElements(By.tagName("td"));
			wColumn.add(wTd.get(iColumn));
		}

		return wColumn;
	}
	
	public boolean crearVistaOM(String nombreVista, String nombreCuenta) {
		driver.findElement(By.id("tabBar")).findElement(By.id("Order_Tab")).click();
		sleep(2000);
		try {
			driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]")).click();
			sleep(3000);
			driver.findElement(By.id("fname")).sendKeys(nombreVista);

			// Filtros de Busqueda
			Select campo = new Select(driver.findElement(By.id("fcol1")));
			campo.selectByValue("SALES.ACCOUNT.NAME");
			Select operador = new Select(driver.findElement(By.id("fop1")));
			operador.selectByValue("e");
			driver.findElement(By.id("fval1")).sendKeys(nombreCuenta);
			;
			sleep(1000);
			// click guardar
			driver.findElement(By.cssSelector(".btn.primary")).click();
			sleep(2000);
			if (driver.findElement(By.name("fcf")).getText().contains(nombreVista))
				return true;
			else
				return false;
		} catch (Exception e) {
			System.out.println("Vista '" + nombreVista + "' no creada.");
			e.printStackTrace();
			return false;
		}
	}
	
	public void selectVistaByVisibleText(String vista) {
		Select vistaSelect = new Select(driver.findElement(By.name("fcf")));
		vistaSelect.selectByVisibleText(vista);
	}

}