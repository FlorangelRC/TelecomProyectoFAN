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
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta(DNI, "34073329");
		SB.acciondecontacto("catalogo");
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
		sleep(7000);
		driver.switchTo().defaultContent();
		}
		SB.elegirplan("Plan con Tarjeta Repro");
		sleep(15000);
		List<WebElement> cont = driver.findElements(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand"));
			for(WebElement c : cont){
				if(c.getText().equals("Continuar")){
					c.click();
				}
			}
		/*sleep(20000);			
		WebElement sig = driver.findElement(By.id("LineAssignment_nextBtn"));
		sig.click();
		sleep(5000);
		WebElement deliv = driver.findElement(By.id("DeliveryMethod"));
		Assert.assertTrue(deliv.getText().equals("Delivery"));*/
	}	
	
	
	
}
