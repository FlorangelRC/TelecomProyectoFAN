package Tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Pages.Login;


public class TestBase {
	private WebDriver driver;
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
			driver.findElement(By.id("nav-tab-9")).click();
			break;
		}
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
	public void loginSCPAdmin(WebDriver driver) {
	     driver.get("https://crm--uat2.cs92.my.salesforce.com");
	     try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	       Login page0 = new Login(driver);
	       page0.ingresarAdminSCP();
	   }
	   
	   
	     public void loginSCPUsuario(WebDriver driver) {
	       driver.get("https://crm--uat2.cs92.my.salesforce.com");
	       try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	         Login page0 = new Login(driver);
	         page0.ingresarUsuarioSCP();
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
	    
	public void waitFor2(WebDriver driver, By element) {
		WebElement myDynamicElement = (new WebDriverWait(driver, 10))
				  .until(ExpectedConditions.presenceOfElementLocated(element));
	}
	public void waitFor(WebDriver driver, By element) {
		WebElement myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(element));
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
<<<<<<< HEAD
				  .until(ExpectedConditions.presenceOfElementLocated(element));}

	public void waitFor2(WebDriver driver, By element) {
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
		 * Para el Modulo Sales tiene vinculado el perfil de Agente y Atención a clientes		 */
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
}

