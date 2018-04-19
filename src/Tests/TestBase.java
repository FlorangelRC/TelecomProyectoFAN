package Tests;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;

import Pages.Accounts;
import Pages.BasePage;
import Pages.HomeBase;
import Pages.Login;
import Pages.setConexion;

//Data provider
import DataProvider.ExcelUtils;


public class TestBase {
	protected static WebDriver driver;//
	
	//@AfterSuite (alwaysRun = true, groups = {"CustomerCare", "AjustesYEscalamiento", "SuspensionYRehabilitacion", "Ola1"})
	public void afterSuite() {
		driver.quit();
	}
	
	public void leftDropdown(WebDriver driver, String selection) {
		driver.findElement(By.className("x-btn-mc")).click();
		switch(selection) {
		case "Cuentas":
		driver.findElement(By.id("ext-gen211")).click();;
		break;
		}
	}
	

	
	public void goToLeftPanel(WebDriver driver, String selection) {
		WebElement element = driver.findElement(By.className("x-btn-split"));
		Actions builder = new Actions(driver);   
		builder.moveToElement(element, 245, 20).click().build().perform();
		switch(selection) {
		case "Cuentas":
		driver.findElement(By.id("nav-tab-0")).click();
		break;
		case "Casos":
			driver.findElement(By.id("nav-tab-1")).click();
			break;
		}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	public void goToLeftPanel2(WebDriver driver, String selection) {
		/*WebElement element = driver.findElement(By.className("x-btn-split"));
		Actions builder = new Actions(driver);   
		builder.moveToElement(element, 245, 20).click().build().perform();*/
		driver.switchTo().defaultContent();
		try {
			driver.findElement(By.className("x-btn-split"));
		}catch(NoSuchElementException noSuchElemExcept) {
			List<WebElement> frames = driver.findElements(By.tagName("iframe"));
			for (WebElement frame : frames) {
				try {
					driver.findElement(By.className("x-btn-split"));
					break;
				}catch(NoSuchElementException noSuchElemExceptInside) {
					driver.switchTo().defaultContent();
					driver.switchTo().frame(frame);
				}
			}
		}
		WebElement dropDown = driver.findElement(By.className("x-btn-split"));
		Actions builder = new Actions(driver);   
		builder.moveToElement(dropDown, 245, 20).click().build().perform();
		List<WebElement> options = driver.findElements(By.tagName("li"));
		for(WebElement option : options) {
			if(option.findElement(By.tagName("span")).getText().toLowerCase().equals(selection.toLowerCase())) {
				option.findElement(By.tagName("a")).click();
				//System.out.println("Seleccionado"); //13/09/2017 working.
				break;
			}
		}
	}
	
	public void login(WebDriver driver) {
		driver.get("https://crm--sit.cs14.my.salesforce.com/");
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//if(driver.findElement(By.id("idcard")).isDisplayed())
		//{
	    Login page0 = new Login(driver);
	    page0.ingresar();
		//}else{
			//driver.findElement(By.id("chooser")).click();
	//	}
	}
	
	public void loginMarketing(WebDriver driver) {
		driver.get("https://crm--sit.cs14.my.salesforce.com/");
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    Login lLogin = new Login(driver);
	    lLogin.ingresarMarketing();
	}
	
	public void loginSCPAdmin(WebDriver driver) {
	     driver.get("https://telecomcrm--uat.cs8.my.salesforce.com");
	     try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	       Login page0 = new Login(driver);
	       page0.ingresarAdminSCP();
	   }
	   
	public void loginSCPconTodo(WebDriver driver) {
	     driver.get("https://telecomcrm--uat.cs8.my.salesforce.com");
	     try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	       Login page0 = new Login(driver);
	       page0.ingresarSCPconTodo();
	   }
	   
	     public void loginSCPUsuario(WebDriver driver) {
	       driver.get("https://telecomcrm--uat.cs8.my.salesforce.com");
	       try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	         Login page0 = new Login(driver);
	         page0.ingresarUsuarioSCP();
	     }
	     
	     
	     public void loginSCPAdminServices(WebDriver driver) {
		       driver.get("https://telecomcrm--uat.cs8.my.salesforce.com");
		       try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		         Login page0 = new Login(driver);
		         page0.ingresarSCPAdminServices();
		     }
	     public void loginSCPConPermisos(WebDriver driver) {
		       driver.get("https://telecomcrm--uat.cs8.my.salesforce.com");
		       try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		         Login page0 = new Login(driver);
		         page0.ingresarSCPConPermisos();
		     }
	     
	public void login1(WebDriver driver) {
		driver.get("https://goo.gl/ETjDYJ");
		try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//if(driver.findElement(By.id("idcard")).isDisplayed())
		//{
	    Login page0 = new Login(driver);
	    page0.ingresar();
	    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

	}	
	public void IngresarCred(WebDriver driver) {
		
	    Login page0 = new Login(driver);
	    page0.ingresar();
	    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

	}
	    
	public void waitFor(WebDriver driver, By element) {
		(new WebDriverWait(driver, 5)).until(ExpectedConditions.visibilityOfElementLocated(element));
	}


	
	public void clickLeftPanel(WebDriver driver) {
		List<WebElement> buttons = driver.findElements(By.tagName("button"));
		for (WebElement btn : buttons) {
			if(btn.getAttribute("id").equals("ext-gen33")) {
				btn.click();
				break;
			}
		}
	}
	
		//}else{
		//	driver.findElement(By.id("chooser")).click();
		//}

	    
/*public void waitFor(WebDriver driver, By element) {
		WebElement myDynamicElement = (new WebDriverWait(driver, 10))

				  .until(ExpectedConditions.presenceOfElementLocated(element));}

	/*public void waitFor2(WebDriver driver, By element) {
		WebElement myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(element));
	}
=======

				  .until(ExpectedConditions.presenceOfElementLocated(element));
*/
	
	

	
	//Sales Fase 3
		
	
		public void loginsales(WebDriver driver, String tipo){
	
		driver.get("https://crm--sit.cs14.my.salesforce.com/");
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    Login page0 = new Login(driver);
	    switch (tipo){
	    case "agente":
	    	page0.ingresarAndres();
	    	break;
	    case "callcenter":
	    	page0.ingresarElena();
	    	break;
	    case "vendedor":
	    	page0.ingresarFrancisco();
	    	break;
	    case "logistica":
	    	page0.ingresarNicolas();
	    	break;
	    case "entregas":
	    	page0.ingresarMarcela();
	    	break;
	    }
	   
	    
	}
		/**Ingresa con los datos de la cuenta Andres
		 * Para el Modulo Sales tiene vinculado el perfil de Agente y Atenci�n a clientes		 */
		public void loginAndres(WebDriver driver) {
			driver.get("https://crm--sit.cs14.my.salesforce.com/");
			try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    Login page0 = new Login(driver);
		    page0.ingresarAndres();
		}
		
		/**Ingresa con los datos de la cuenta Elena
		 * Para el Modulo Sales tiene vinculado el perfil de Call center		 */
		public void loginElena(WebDriver driver) {
			driver.get("https://crm--sit.cs14.my.salesforce.com/");
			try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    Login page0 = new Login(driver);
		    page0.ingresarElena();
		}
	
		
		/**Ingresa con los datos de la cuenta Francisco
		 * Para el Modulo Sales tiene vinculado el perfil de Vendedor Oficina Comercial		 */
		public void loginFranciso(WebDriver driver) {
			driver.get("https://crm--sit.cs14.my.salesforce.com/");
			try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    Login page0 = new Login(driver);
		    page0.ingresarFrancisco();
		}
		
		/**Ingresa con los datos de la cuenta Nicolas.
		 * Para el Modulo Sales tiene vinculado el perfil de Logistica	 */
		public void loginNicolas(WebDriver driver) {
			driver.get("https://crm--sit.cs14.my.salesforce.com/");
			try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    Login page0 = new Login(driver);
		    page0.ingresarNicolas();
		}
		
		/**Ingresa con los datos de la cuenta de Marcela
		 * Para el Modulo Sales tiene vinculado el perfil de Entregas y Configuraciones	 */
		public void loginMarcela(WebDriver driver) {
			driver.get("https://crm--sit.cs14.my.salesforce.com/");
			try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    Login page0 = new Login(driver);
		    page0.ingresarMarcela();
		}
		public void loginFabiana(WebDriver driver) {
			driver.get("https://crm--sit.cs14.my.salesforce.com/");
			try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    Login page0 = new Login(driver);
		    page0.ingresarFabiana();
		}
		public void elegirmodulo(String modulo){
			try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			String a = driver.findElement(By.id("tsidLabel")).getText();
			driver.findElement(By.id("tsidLabel")).click();
			
			List <WebElement> mdls = driver.findElements(By.cssSelector(".menuButtonMenuLink"));
			try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

			if(a.equals("Ventas"))
			{			try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

				for(WebElement e: mdls){
					if(e.getText().toLowerCase().equals(modulo)){
						e.click();
						}break;}
			}else{
				for(WebElement e: mdls){
					if(e.getText().toLowerCase().equals("ventas")){
						e.click();
						}break;}
				try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
				driver.findElement(By.id("tsidLabel")).click();
				for(WebElement e: mdls){
					if(e.getText().toLowerCase().equals(modulo)){
						e.click();
						}break;}
			}
		}
		
		
		public boolean verificarContenidoLista(String[] consultar,List <WebElement> Lista) {
		 
		     List<String> titleTabla = new ArrayList<String>();
		     for(WebElement a : Lista) {
		       titleTabla.add(a.getText().toLowerCase());
		       System.out.println(a.getText());//Para Verificar que este imprimiendo el texto que buscamos
		     }     
		     for(String a:consultar) {
		      if(!(titleTabla.contains(a))) {
		    	  System.out.println("fallo en "+a);
		    	  return false;
		      }
		     }
		     return true;
		}
		/**
		 * Desarrollado para ir a consola Fan en F3. (primero va ventas y luego retorna a consola fan)
		 * @param driver
		 * @author Almer Fase 3
		 */
		public void goInitToConsolaFanF3(WebDriver driver) {
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
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
		       try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		       try {
					driver.switchTo().alert().accept();
					driver.switchTo().defaultContent();
				}
				catch(org.openqa.selenium.NoAlertPresentException e) {}
		}
		
		/**
		 * Selecciona una Cuenta segun el Nombre
		 * @param driver
		 * @author Almer Fase 3
		 */
		public void seleccionCuentaPorNombre(WebDriver driver, String nombreCuenta) {
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			goToLeftPanel2(driver, "Cuentas");
			WebElement frame0 = driver.findElement(By.tagName("iframe"));
			driver.switchTo().frame(frame0);
			Select field = new Select(driver.findElement(By.name("fcf")));
			field.selectByVisibleText("Vista Tech");
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			List<WebElement> accounts = driver.findElements(By.xpath("//*[text() = '"+nombreCuenta+"']"));
			accounts.get(0).click();
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().defaultContent();
		}
		
		/**
		 * Selecciona una Cuenta segun el Nombre
		 * @param driver
		 * @author Almer Fase 3
		 */
		public void searchAndClick(WebDriver driver, String busqueda) {
			
			Accounts view=new Accounts(driver);
			try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			view.deployEastPanel();
			
			BasePage searchImput=new BasePage();
			List<WebElement> frame1 = driver.findElements(By.tagName("iframe"));
			int indexFrame = searchImput.getIndexFrame(driver, By.xpath("/html/body/div/div[1]/ng-include/div/div[1]/ng-include/div/div[2]/input"));
			driver.switchTo().frame(frame1.get(indexFrame));
			WebElement elemento = driver.findElement(By.xpath("/html/body/div/div[1]/ng-include/div/div[1]/ng-include/div/div[2]/input"));
			//Escribe en campo de busqueda
			elemento.sendKeys(busqueda);
			
			//Click en el resultado buscado
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			WebElement resultado= driver.findElement(By.xpath("//*[text() = '"+busqueda+"']"));
			resultado.click();
			driver.switchTo().defaultContent();
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			
			
		}
		
		
	/////////////////
	
	public static void inicializarDriver() {
		driver = setConexion.setupEze();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public static void cerrarTodo() {
		driver.close();
	}
	
	public static void login() {
		//driver.get("https://crm--sit.cs14.my.salesforce.com/");
		//waitFor(By.xpath("//button[@class='button mb24 secondary wide']"));
		//driver.findElement(By.xpath("//button[@class='button mb24 secondary wide']")).click();
		
		driver.get("https://tuidsrvtest.telecom.com.ar:8443/nidp/app/login?id=IDM_NamePassword&sid=0&option=credential&sid=0&target=https%3A%2F%2Ftuidsrvtest.telecom.com.ar%3A8443%2Fnidp%2Fsaml2%2Fidpsend%3FSAMLRequest%3DjZNtb6owFMe%252FiuE9WB58gAwNiOyyzcEERfbGYCmKSqttgc1PP6Z3y%252B6b5Z6kSU%252FOw%252F%252B0%252FfVu%252FFYeOzWirCDYFGQJCB2EIckKvDWFReSKQ2E8umNpeTwZVsV3eI7OFWK809ZhZlwDplBRbJCUFczAaYmYwaERWrMnQ5GAcaKEE0iOQsdiDFHeCk0IZlWJaIhoXUC0mD%252BZwo7zEzO6XUhLUQy9SIJM1qTyXWLpEbGcUIgkSMoxIyYADgRXUxtZj4SO0w5U4JRfz%252FDViFdFxmjN25jE0RG1xZ8NpJQaQ01Tu7jITt3P%252BZVuu2MIZ%252BMiM8NvtXYGoeM5prBWJlZrsVU4iq%252FOpsMmuVjNzPle4DMMaDZ93CJO5Nw%252FkOfDKZPzHF0WsI%252FtvQfDnftq7zeibfui1uPzZaasEnXpDvk9kQdbSvnpwUly61zF%252BkUnMFELmvW2jyKAzT7FAad59rLcupGLDvpa4d59NfCUd%252F5Sb%252BLFTEtk1d0Dp9gAFTEvOITuLHn0c9H2YxXG6C1YDhprFl1ca1NPFScIavGgNvNYfz739b0zqUMShCt%252FveaYNBSfs2Ja9adsAYKnSmm0OEwUZ%252BW86dZ2kheBdlg95E0SuPtd%252FG7PVziDbvrS3hZjFfIw4ynmpqAAeSDKsgj6kdwzQM9QNElR%252B69CJ%252FiLhF3gG2i%252F8bO5JTHjTxQFYuCH7bMsv4BtE4QbnsZVnP7g8ve26ReMwug%252F0bvr%252FtAZ3bx%252FP8XoAw%253D%253D%26RelayState%3D%252F%26SigAlg%3Dhttp%253A%252F%252Fwww.w3.org%252F2000%252F09%252Fxmldsig%2523rsa-sha1%26Signature%3DTt2v5J4iLGiCWAZy1OvWbgjc93hORZDfCHxkk2WSnVWIXVucRmjzDalrxjE6ND96lDjYxV8nzcuDMAReZKFOf%252B9uS9EOUhC%252FpMRgrdrdz4dMQ5x4Y5XC%252F9W1VWMGDtL%252FwxIXNbSj5UH8T4zGLtCEB6IvDBi6w5dfNxJgtae71L8cJ2wf1b%252BOWJ7yAUZVCN3ZUUkrrYg6UJPCt8CqNo%252FGfwyeN8wtb4T25%252FDhJzhIpmWmjLJFgZTZqcvYpE7NKkwWv6FZ8oO4iRti94O2829xVNV5oXDJ03E9MdoL9JnltlmHSV97WUYb8OKz9mqqnucfZMloUlGmIhp2wGLCEU8%252BZM4lLjt%252F5Z0FD0icF02v9eFvi3gg9lg4%252F7Xu7%252BhnHrw2bH83mCsqNQlJW3eguz8BRYgRFEG8naVRX7MrmiPejZ2146l9GssJxh0naB4YgQDjhb01gYBqiLBliFIWDvrgGF%252BY2heuYVG3dE80MTYsc14zD9G49S5ib0g679P4m5HEx2D2eZEkxRVgGDb58ej5igd%252FWpQ3JAkyq6%252FqU595jyhWa8HlCH9UeyMXSwtgakHABbewF%252Bug%252F6jN%252F61XYhpmb9Wa5fugZNKC5E9jSo2FZUrJnTx0ShCnRwITUcwKM75wLI6ra33eLuMmwv3t6H6%252BFhKiDiGmnMyqBPzhFrwgzVg%253D%26id%3DSalesforceSIT");
		driver.findElement(By.xpath("//input[@name='Ecom_User_ID']")).sendKeys("u589831");
		driver.findElement(By.xpath("//input[@name='Ecom_Password']")).sendKeys("Testa10k");
		driver.findElement(By.xpath("//input[@name='Ecom_Password']")).submit();
	}
		
	public static class IrA {
		public static class CajonDeAplicaciones {
			public static void ConsolaFAN() {
				driver.switchTo().defaultContent();
				driver.findElement(By.xpath("//span[@id='tsidLabel']")).click();
				driver.findElement(By.xpath("//a[contains(.,'Consola FAN')]")).click();
				
				try {
					(new WebDriverWait(driver, 3)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".x-window.x-window-plain.x-window-dlg")));
					driver.findElement(By.tagName("button")).click();
					driver.findElement(By.xpath("//span[@id='tsidLabel']")).click();
					driver.findElement(By.xpath("//a[contains(.,'Consola FAN')]")).click();
				} catch (TimeoutException e) {}
				
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				try {driver.switchTo().alert().accept();} catch (org.openqa.selenium.NoAlertPresentException e) {}
			}
			
			public static void Ventas() {
				HomeBase homePage = new HomeBase(driver);
			    String a = driver.findElement(By.id("tsidLabel")).getText();
				if (a.contains("Ventas")){}
			    else {
			    	homePage.switchAppsMenu();
			    	try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			    	homePage.selectAppFromMenuByName("Ventas");
			    	try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}            
			    }	    
				
				
				//driver.switchTo().defaultContent();
				//driver.findElement(By.xpath("//span[@id='tsidLabel']")).click();
				//driver.findElement(By.xpath("//a[contains(.,'Ventas')]")).click();
			}
		}
	}
		
	public static WebDriverWait dynamicWait() {
		WebDriverWait ed = new WebDriverWait(driver, 7);
		return ed;
	}
	
	public static class waitFor {
		public static void visibilityOfAllElements(List<WebElement> elems) {
			dynamicWait().until(ExpectedConditions.visibilityOfAllElements(elems));
		}
		
		public static void visibilityOfElement(WebElement elem) {
			dynamicWait().until(ExpectedConditions.visibilityOf(elem));
		}
		
		public static void elementToBeSelected(WebElement elem) {
			dynamicWait().until(ExpectedConditions.elementToBeSelected(elem));
		}
		
		public static void attributeContains(WebElement elem, String atrib, String valor) {
			dynamicWait().until(ExpectedConditions.attributeContains(elem, atrib, valor));
		}
		
		public static void elementToBeClickable(WebElement elem) {
			dynamicWait().until(ExpectedConditions.elementToBeClickable(elem));
		}
	}
		
	public static void sleep(int miliseconds) {
		try {Thread.sleep(miliseconds);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	public List<String> obtenerElAtributoDeLosElementos(String atributo, List<WebElement> elementos) {
		List<String> valores = new ArrayList<String>();
		for (WebElement elem : elementos) {
			valores.add(elem.getAttribute(atributo));
		}
		
		return valores;
	}
	
	public Boolean esObligatorio(WebElement campo) {
		return campo.getAttribute("class").contains("ng-invalid-required");
	}
	
	public Boolean esValido(WebElement campo) {
		sleep(300);
		return (!campo.getAttribute("class").contains("invalid"));
	}
	
	public String obtenerValorDelCampo(WebElement campo) {
		return campo.getAttribute("value");
	}
	
	public String fechaDeHoy() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return(dateFormat.format(date));
	}
	
	public void selectByText(WebElement element, String data){
		Select select = new Select(element);
		select.selectByVisibleText(data);
	}
	
	public int getIndexFrame(WebDriver driver, By byForElement) { //working correctly
		//TODO: Do the same for a WebElement instead of a By.
		int index = 0;
		driver.switchTo().defaultContent();
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		for(WebElement frame : frames) {
			try {
				driver.switchTo().frame(frame);

				driver.findElement(byForElement).getText(); //each element is in the same iframe.
				//System.out.println(index); //prints the used index.

				driver.findElement(byForElement).isDisplayed(); //each element is in the same iframe.
				//System.out.println(index); //prints the used index.

				driver.switchTo().defaultContent();
				return index;
			}catch(NoSuchElementException noSuchElemExcept) {
				index++;
				driver.switchTo().defaultContent();
			}
		}
		return -1; //if this is called, the element wasnt found.
	}
	
	public WebElement cambioFrame(WebDriver driver, By byForElement) {
		driver.switchTo().defaultContent();
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		try {return frames.get(getIndexFrame(driver, byForElement));
		}catch(ArrayIndexOutOfBoundsException iobExcept) {System.out.println("Elemento no encontrado en ningun frame.");
			return null;
		}

	}
	
	//Metodo para obtener el dato deseado del excel indicando la hoja o pesta;a donde se encuentra (se agrupa por modulo)
	public String buscarCampoExcel(int hoja, String desc, int columna) throws IOException
	{
		String Campo = null;
		 File archivo = new File("Cuentas.xlsx");
		 FileInputStream file = new FileInputStream(archivo);
	     XSSFWorkbook workbook = new XSSFWorkbook(file); 
	     XSSFSheet sheet = workbook.getSheetAt(hoja);
	     Iterator<Row> rows = sheet.rowIterator();
	    // rows.next();
	     System.out.println("Aquiiiii");
	     System.out.println(rows.next().getCell(0).getStringCellValue());
	     while (rows.hasNext()) {
	    	 
		    XSSFRow row = (XSSFRow) rows.next();
		   // System.out.println(row.getCell(0).getStringCellValue());
		    if (row.getCell(0).getStringCellValue().toLowerCase().contains(desc.toLowerCase())){
		    	try {Campo = row.getCell(columna).getStringCellValue();}
		    	catch (java.lang.IllegalStateException ex1) 
		    	{  
		    		Campo = Double.toString(row.getCell(columna).getNumericCellValue());
		    		if(Campo.contains("E")) 
		    		{	
		    			Campo = Double.toString(row.getCell(columna).getNumericCellValue());
		    			Campo = Campo.substring(0, Campo.indexOf("E")).replace(".","" );
		    		}
		    	}
		    	break;
		    }
		 }
		return (Campo);
	}
	
	@DataProvider
	public Object[][] Tech() throws Exception{

	 Object[][] testObjArray = ExcelUtils.getTableArray("Cuentas.xlsx","Tech",1,1,3);

	 return (testObjArray);

	}
	
	@DataProvider
	public Object[][] SalesCuentaInactiva() throws Exception{

	 Object[][] testObjArray = ExcelUtils.getTableArray("Cuentas.xlsx","Sales",1,1,3,"Cuenta Inactiva");

	 return (testObjArray);

	}
	
	@DataProvider
	public Object[][] CustomerCuentaActiva() throws Exception{

	 Object[][] testObjArray = ExcelUtils.getTableArray("Cuentas.xlsx","Customer",1,1,1);

	 return (testObjArray);

	}
	
	@DataProvider
	public Object[][] SalesCuentaActiva() throws Exception{

	 Object[][] testObjArray = ExcelUtils.getTableArray("Cuentas.xlsx","Sales",1,1,3,"Cuenta Activa");

	 return (testObjArray);

	}
	
	@DataProvider
	public Object[][] SalesContactoSinCuenta() throws Exception{

	 Object[][] testObjArray = ExcelUtils.getTableArray("Cuentas.xlsx","Sales",1,1,2,"Contacto sin cuenta");

	 return (testObjArray);

	}
	
	@DataProvider
	public Object[][] SalesBlacklist() throws Exception{

		 Object[][] testObjArray = ExcelUtils.getTableArray("Cuentas.xlsx","Sales",1,1,2,"Blacklist");
		 return (testObjArray);

	}
	
	
	@DataProvider
	public Object[][] SalesCuentaConGestiones() throws Exception{

	 Object[][] testObjArray = ExcelUtils.getTableArray("Cuentas.xlsx","Sales",1,1,3,"Cuenta con gestiones");

	 return (testObjArray);

	}
	
	@DataProvider
	public Object[][] MarketingCuentaNormal() throws Exception{

	 Object[][] testObjArray = ExcelUtils.getTableArray("Cuentas.xlsx","Marketing",1,1,1,"Cuenta Normal");

	 return (testObjArray);

	}
	
	@DataProvider
	public Object[][] MarketingCuentaConMora() throws Exception{

	 Object[][] testObjArray = ExcelUtils.getTableArray("Cuentas.xlsx","Marketing",1,1,1,"Cuenta c/ Mora");

	 return (testObjArray);

	}
	
	@DataProvider
	public Object[][] MarketingCuentaConFraude() throws Exception{

	 Object[][] testObjArray = ExcelUtils.getTableArray("Cuentas.xlsx","Marketing",1,1,1,"Cuenta c/ Fraude");

	 return (testObjArray);

	}
	
	@DataProvider
	public Object[][] MarketingCuentaSinServicio() throws Exception{

	 Object[][] testObjArray = ExcelUtils.getTableArray("Cuentas.xlsx","Marketing",1,1,1,"Cuenta sin Servicio");

	 return (testObjArray);

	}
	
}