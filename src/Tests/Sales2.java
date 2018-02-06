package Tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.CustomerCare;
import Pages.SalesBase;
import Tests.TestBase.IrA;

public class Sales2 extends TestBase{

	SalesBase sb;
	String DNI = "DNI";

	//@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	//@AfterMethod
		public void deslogin() {
			sleep(3000);
			driver.get("https://crm--sit.cs14.my.salesforce.com/home/home.jsp?tsid=02u41000000QWha/");
			sleep(10000);
	}
		
	@BeforeClass
	public void init() {
		inicializarDriver();
		sb = new SalesBase(driver);
		loginAndres(driver);
		IrA.CajonDeAplicaciones.Ventas();
	}
	
	@BeforeMethod
	public void setup() throws Exception {
		sleep(5000);
		driver.findElement(By.xpath("//a[@href=\'https://crm--sit--c.cs14.visual.force.com/apex/taClientSearch']")).click();
		sleep(7000);
	}
	@Test(groups={"Sales", "Nueva Venta", "Ola1"})
	public void TS94699_Nueva_Venta_Modo_de_Entrega_Verificar_Solicitud_de_Domicilio_de_envio_Envio_Express(){
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		boolean x = false;
		sleep(15000);
		List<WebElement> cam = driver.findElements(By.cssSelector(".slds-m-left--x-small.slds-button.slds-button--brand"));
		for(WebElement c : cam ){	
			if(c.getText().toLowerCase().equals("cambiar")){
				c.click();
			}
		sleep(7000);	
		List<WebElement> frame2 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame2.get(0));
		Select env = new Select (driver.findElement(By.id("DeliveryMethodSelection")));
		env.selectByVisibleText("Delivery");
		driver.findElement(By.id("SalesChannelConfiguration_nextBtn")).click();
		sleep(10000);
		driver.switchTo().defaultContent();
		}
		sb.elegirplan("Plan con Tarjeta Repro");
		sleep(15000);
		List<WebElement> cont = driver.findElements(By.cssSelector(".slds-form-element__label--toggleText.ng-binding"));
			for(WebElement c : cont){
				if(c.getText().equals("Continuar")){
					c.click();
				}
			}
		sleep(5000);
		List<WebElement> direc = driver.findElements(By.cssSelector(".slds-form-element__label--toggleText.ng-binding"));
			for(WebElement d : direc){
				if(d.getText().equals("Modificar b\u00fasqueda")){
					//d.click();
					System.out.println(d.getText());
					System.out.println(d.getAttribute("value"));
				}
			}
			//Assert.assertTrue(driver.findElement(By.id("SelectProvincia")).isDisplayed());	
		}
	
}
