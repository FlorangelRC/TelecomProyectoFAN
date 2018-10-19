package Tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.setConexion;

public class Comunidad extends TestBase {
	
	private WebDriver driver;
	
	private void mobileEmulation() throws AWTException {
		Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_F12);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_SHIFT);
        sleep(1);
        robot.keyPress(KeyEvent.VK_M);
        sleep(1);
        robot.keyRelease(KeyEvent.VK_M);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	//Befores & Afters
	
	@BeforeClass(alwaysRun=true)
	public void init() {
		driver = setConexion.setupEze();
		sleep(5000);
		loginCommunity(driver);
		driver.switchTo().defaultContent();
		sleep(3000);
		
	}
	
	//@AfterMethod(alwaysRun=true)
	public void backToTheInicio() throws Exception {
		driver.findElement(By.className("slds-container_fluid")).click();
		sleep(10000);
	}

	//@AfterClass(alwaysRun=true)
	public void quit() throws IOException {
		driver.quit();
		sleep(5000);
	}
	
	@Test (groups = {"Communities","E2E"})
	public void CRM_PRE_Community_Desktop_Pagina_Servicios_Assets(){
		List <WebElement> gest = driver.findElements(By.cssSelector(".via-slds.ta-community-services"));
		boolean aa = false;
		for(WebElement g : gest){
			if(g.getText().toLowerCase().equals("mis gestiones")){
				g.isDisplayed();
				aa = true;
			}
		}
	}
	
	@Test (groups = {"Communities", "E2E"})
	public void CRM_PRE_Community_Desktop_Mis_gestiones_Filtro_Fecha() {
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".vlocity.via-slds")));
		buscarYClick(driver.findElements(By.cssSelector(".slds-col.slds-size--1-of-1")), "equals", "plan con tarjeta repro");
		sleep(2000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-grid.slds-wrap.slds-grid--pull-padded.slds-m-right--medium")));
		buscarYClick(driver.findElements(By.className("availables_text")),"equals", "mis gestiones");
		sleep(2000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-grid.slds-wrap.slds-grid--pull-padded.slds-m-around--medium.slds-p-around--medium.negotationsfilter")));
		driver.findElement(By.id("text-input-id-1")).click();
		
		
		}//*[@id="text-input-id-1"]
	//*[@id="text-input-id-2"]
	@Test (groups = {"Communities", "E2E"})
	public void CRM_PRE_Community_Desktop_Gestiones_en_Curso_y_Completadas_5() {
		boolean cursoYCompletadas = false;
		sleep(3000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-grid.slds-wrap.slds-grid--pull-padded.card_misg_desk")));
		for (WebElement x : driver.findElements(By.cssSelector(".slds-grid.slds-wrap.slds-grid--pull-padded.card_misg_desk"))) {
			if (x.getText().contains("En curso y completadas"))
				cursoYCompletadas = true;
		}
		Assert.assertTrue(cursoYCompletadas);
	}
	
	@Test (groups = {"Communities", "E2E"})
	public void CRM_PRE_Community_Desktop_Alta_en_Mi_cuenta_Cliente_inexistente_en_CRM() {
		driver.get("https://sit-scrumcella.cs14.force.com/clientes/s/login/?startURL=%2Fclientes%2Fs%2F&ec=302");
		sleep(3000);
		driver.findElement(By.cssSelector(".slds-button.slds-button--neutral.sfdc_button.register.uiButton--default.uiButton")).click();
		sleep(3000);
		driver.findElement(By.cssSelector(".input.sfdc_usernameinput.sfdc.input.uiInput.uiInputText.uiInput--default.uiInput--input")).sendKeys("12259449");
		driver.findElement(By.cssSelector(".slds-button.slds-button--neutral.sfdc_button.checkBtn.uiButton--default.uiButton")).click();
		sleep(2000);
		String msj = driver.findElement(By.className("uiOutputRichText")).getText();
		Assert.assertTrue(msj.contains(" ingresado no ha sido encontrado."));
	}
	
	@Test (groups = {"Communities", "E2E"})
	public void CRM_PRE_Community_Desktop_Modificar_datos_cliente() {
		driver.findElement(By.cssSelector(".profileIcon")).click();
		driver.findElement(By.cssSelector(".profile.uiMenuItem")).click();
		sleep(3000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-size--1-of-1.slds-p-vertical--medium.slds-p-horizontal--small.slds-border--bottom.vloc-action-grid-cell.slds-border--top")));
		driver.findElement(By.cssSelector(".slds-size--1-of-1.slds-p-vertical--medium.slds-p-horizontal--small.slds-border--bottom.vloc-action-grid-cell.slds-border--top")).findElement(By.tagName("div")).click();
		sleep(7000);
		driver.switchTo().frame(cambioFrame(driver, By.id("FirstName")));
		String viejoNombre = driver.findElement(By.id("FirstName")).getAttribute("value");
		driver.findElement(By.id("FirstName")).clear();
		driver.findElement(By.id("FirstName")).sendKeys("Automatizacion");
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
		sleep(7000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-size--1-of-1.slds-medium-size--1-of-1.slds-large-size--1-of-1.slds-m-bottom--small.slds-align--absolute-center")));
		String nuevoNombre = driver.findElement(By.cssSelector(".slds-size--1-of-1.slds-medium-size--1-of-1.slds-large-size--1-of-1.slds-m-bottom--small.slds-align--absolute-center")).getText();
		Assert.assertTrue(nuevoNombre.startsWith("Automatizacion"));
		driver.findElement(By.cssSelector(".slds-size--1-of-1.slds-p-vertical--medium.slds-p-horizontal--small.slds-border--bottom.vloc-action-grid-cell.slds-border--top")).findElement(By.tagName("div")).click();
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.id("FirstName")));
		driver.findElement(By.id("FirstName")).clear();
		driver.findElement(By.id("FirstName")).sendKeys(viejoNombre);
		driver.findElement(By.id("ClientInformation_nextBtn")).click();
		sleep(7000);
	}
	
	@Test (groups = {"Communities", "E2E"})
	public void CRM_PRE_Community_Desktop_Mis_Servicios_Full_Responsive() {
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-size--1-of-1.slds-align-middle.slds-p-vertical--small.cursor")));
		driver.findElement(By.cssSelector(".slds-size--1-of-1.slds-align-middle.slds-p-vertical--small.cursor")).click();
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-size--1-of-1.slds-p-vertical--medium.slds-m-horizontal--xxx-small.slds-border--bottom.vloc-action-grid-cell.gestionlink")));
		buscarYClick(driver.findElements(By.cssSelector(".slds-size--1-of-1.slds-p-vertical--medium.slds-m-horizontal--xxx-small.slds-border--bottom.vloc-action-grid-cell.gestionlink")), "contains", "mis servicios");
		sleep(3000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-grid.slds-wrap.slds-card.slds-m-bottom--small.slds-p-around--medium")));
		WebElement productos = null, servicios = null;
		for (WebElement x : driver.findElements(By.cssSelector(".slds-grid.slds-wrap.slds-card.slds-m-bottom--small.slds-p-around--medium"))) {
			if (x.getText().toLowerCase().contains("productos activos"))
				productos = x;
			if (x.getText().toLowerCase().contains("servicios incluidos"))
				servicios = x;
		}
		WebElement tablaProductos = productos.findElement(By.className("slds-text-heading--label"));
		WebElement tablaServicios = servicios.findElement(By.className("slds-text-heading--label"));
		Assert.assertTrue(tablaProductos.getText().contains("NOMBRE") && tablaProductos.getText().contains("FECHA DE ACTIVACI\u00d3N") && tablaProductos.getText().contains("MONTO"));
		Assert.assertTrue(tablaServicios.getText().contains("NOMBRE") && tablaServicios.getText().contains("FECHA DE ESTADO") && tablaServicios.getText().contains("ESTADO"));
	}
	
	@Test (groups = {"Communities", "E2E"})
	public void CRM_PRE_Community_Desktop_Alta_DNI_existente_en_Mi_cuenta_Cliente_existente_en_CRM() {
		driver.get("https://sit-scrumcella.cs14.force.com/clientes/s/login/?startURL=%2Fclientes%2Fs%2F&ec=302");
		sleep(3000);
		driver.findElement(By.cssSelector(".slds-button.slds-button--neutral.sfdc_button.register.uiButton--default.uiButton")).click();
		sleep(3000);
		driver.findElement(By.cssSelector(".input.sfdc_usernameinput.sfdc.input.uiInput.uiInputText.uiInput--default.uiInput--input")).sendKeys("22930856");
		driver.findElement(By.cssSelector(".slds-button.slds-button--neutral.sfdc_button.checkBtn.uiButton--default.uiButton")).click();
		sleep(2000);
		String msj = driver.findElement(By.className("uiOutputRichText")).getText();
		Assert.assertTrue(msj.contains(" ya tiene usuario, seleccione "));
	}

	@Test (groups = {"Communities","E2E"})
	public void CRM_PRE_Community_Desktop_Gestiones_Abandonadas_Mayores_a_5(){
		
	}
	
	@Test (groups = {"Communities","E2E"}) //This TC is Mobile
	public void CRM_PRE_Community_Mobile_Gestiones_en_Curso_y_Completadas_Mayores_a_5() throws AWTException{
		mobileEmulation();
	}
	
	@Test (groups = {"Communities","E2E"}) //This TC is Mobile
	public void CRM_PRE_Community_Mobile_Gestiones_Abandonadas_Mayores_a_5(){
		
	}
	
}