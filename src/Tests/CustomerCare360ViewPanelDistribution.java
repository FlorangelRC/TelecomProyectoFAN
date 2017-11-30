package Tests;

import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.CustomerCare;
import Pages.Login;
import Pages.TechCareDiagnostic;
import Pages.setConexion;

public class CustomerCare360ViewPanelDistribution extends TestBase {

	private WebDriver driver;


//@AfterTest
public void tearDown() {
		driver.close();
}
//@AfterMethod
public void alert (){
	driver.get("https://cs14.salesforce.com/console");
	try {Thread.sleep(1000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	try{
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}catch(org.openqa.selenium.NoAlertPresentException e){}
}

@BeforeClass//(groups= "Fase2")
public void init() throws Exception
{
	this.driver = setConexion.setupEze();
	try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	login(driver);
	try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	String a = driver.findElement(By.id("tsidLabel")).getText();
	driver.findElement(By.id("tsidLabel")).click();
	if(a.equals("Ventas"))
	{
		driver.findElement(By.xpath("//a[@href=\'/console?tsid=02uc0000000D6Hd\']")).click();
	}else
	{			driver.findElement(By.xpath("//a[@href=\'/home/home.jsp?tsid=02u41000000QWha\']")).click();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("tsidLabel")).click();
		driver.findElement(By.xpath("//a[@href=\'/console?tsid=02uc0000000D6Hd\']")).click();
	}
}	
@BeforeMethod
public void setup(){
	try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	CustomerCare page = new CustomerCare(driver);
	page.cerrarultimapestaña();
	CustomerCare CC = new CustomerCare(driver);
	CC.elegircuenta("Andres Care");
}
	
		
	@Test
	public void TS7059_Verificar_Visualizar_Panel_Promociones() {	
		CustomerCare CC = new CustomerCare(driver);
		CC.elegircuenta("Andres Care");
		BasePage BP = new BasePage(driver);
		CC.openrightpanel();
		driver.switchTo().frame(BP.getFrameForElement(driver, By.cssSelector(".promotions-section-header")));
		Assert.assertTrue(driver.findElement(By.cssSelector(".promotions-section-header")).isEnabled());
		driver.switchTo().defaultContent();
	}
	
	@Test
	public void TS7058_Visualizar_Panel_Servicios_Activos() {
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame1 = driver.findElements(By.tagName("iframe"));
	
		driver.findElement(By.xpath("//*[text() ='Servicios']")).click();
		driver.switchTo().frame(frame1.get(2));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	

		driver.findElement(By.cssSelector(".console-card.active"));
		driver.switchTo().defaultContent();
	}
	
	@Test
	public void TS7200_VerifyDisplayLogo() {	
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	
		driver.findElement(By.className("sd_custom_logo"));
	
	}
	
	@Test
	public void TS7060_Visualizar_Panel_Alertas() {	

		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame1 = driver.findElements(By.tagName("iframe"));
	
		driver.findElement(By.xpath("//*[text() ='Servicios']")).click();
		driver.switchTo().frame(frame1.get(2));
		driver.findElement(By.className("ta-alertMessage-content"));	
		driver.switchTo().defaultContent();
	}
	
	@Test
	public void TS7201_VerifyDisplayfilterAccounts() {	
		CustomerCare CC = new CustomerCare(driver);

		BasePage BP = new BasePage(driver);
		CC.openleftpanel();
		driver.switchTo().frame(BP.getFrameForElement(driver, By.cssSelector(".account-select-table")));
		
		driver.findElement(By.className("account-select-table")).click();	
	
		driver.switchTo().defaultContent();
	}
	@Test
	public void TS7066_VerifyDisplayPanelAccountsClient() {	
		CustomerCare CC = new CustomerCare(driver);
	
		BasePage BP = new BasePage(driver);
		CC.openleftpanel();
		driver.switchTo().frame(BP.getFrameForElement(driver, By.cssSelector(".account-select-container")));
		
		driver.findElement(By.className("account-select-container")).click();
		driver.switchTo().defaultContent();
	}
	@Test
	public void TS7054_VerifyDisplayPanelBusinessData() {	
		CustomerCare CC = new CustomerCare(driver);

		BasePage BP = new BasePage(driver);
		CC.openleftpanel();
		driver.switchTo().frame(BP.getFrameForElement(driver, By.cssSelector(".profile-box")));
		
		driver.findElement(By.className("profile-box"));	
		driver.switchTo().defaultContent();
	}
	
	
	
	// FALTA TODO ESTO
	@Test
	public void TS7061_Visualizar_Panel_Sesiones_Guiadas() {
		CustomerCare page = new CustomerCare(driver);
		page.openrightpanel();
		page.verificaciondebotonesdegestion();
		driver.switchTo().defaultContent();
	}
	
	@Test
	public void TS7062_Visualizar_Panel_Gestiones_abandonadas() {	
		CustomerCare page = new CustomerCare(driver);
		page.openrightpanel();
		page.GestionAbandonadapanel();
		driver.switchTo().defaultContent();
	}
	
	@Test
	public void TS7063_Contraccion_Paneles() {
		CustomerCare page = new CustomerCare(driver);
		page.openleftpanel();
		page.panelizq("todosOFF");
		page.verificacrhideleft("todosOFF");
		page.closeleftpanel();
		page.openrightpanel();
		page.panelder("todos");
		page.verificarhideright("todosOFF");
		page.closerightpanel();
		driver.switchTo().defaultContent();
	}
	@Test
	public void TS7064_Contraccion_Panel_Datos_Comerciales() {
		CustomerCare page = new CustomerCare(driver);
		page.openleftpanel();
		page.verificarnohidedatoscomerciales();
		driver.switchTo().defaultContent();
	}
	@Test
	public void TS7065_Expansion_Paneles() {
		CustomerCare page = new CustomerCare(driver);
		page.openleftpanel();
		page.panelizq("todosON");
		page.verificacrhideleft("todosON");
		page.closeleftpanel();
		page.openrightpanel();
		page.panelder("todos");
		page.verificarhideright("todosON");
		page.closerightpanel();
		driver.switchTo().defaultContent();
	}	
//360 View Customer Key Metrics
	@Test
	public void TS7074_Key_Metrics_Visualizar_Picklist() {
		CustomerCare page = new CustomerCare(driver);
		page.openleftpanel();
		page.verificarpicklist();
		driver.switchTo().defaultContent();
	}
	
	@Test
	public void TS7075_Key_Metrics_Funcionamiento_Picklist() {
		CustomerCare page = new CustomerCare(driver);
		page.openleftpanel();
		page.funcionamientopicklist();
		driver.switchTo().defaultContent();

	}
	@Test
	public void TS7076_Key_Metrics_Visualizar_boton_Añadir_Nuevo_Intereses_personales() {
		CustomerCare page = new CustomerCare(driver);
		page.openleftpanel();
		page.panelizq("perfil");
		page.validarbtnsperfil("Intereses Personales");
		driver.switchTo().defaultContent();
}
	@Test
	public void TS7078_Key_Metrics_Visualizar_boton_Añadir_Nuevo_Criterios_de_compra() {
		CustomerCare page = new CustomerCare(driver);
		page.openleftpanel();
		page.panelizq("perfil");
		page.validarbtnsperfil("Criterios de compra");
		driver.switchTo().defaultContent();
}

	@Test
	public void TS7079_Key_Metrics_Visualizar_boton_Añadir_Nuevo_Familia() {
		CustomerCare page = new CustomerCare(driver);
		page.openleftpanel();
		page.panelizq("perfil");
		page.validarbtnsperfil("Familia");
}
	@Test
	public void TS7080_Key_Metrics_Visualizar_boton_Añadir_Nuevo_Productos_de_interes() {
		CustomerCare page = new CustomerCare(driver);
		page.openleftpanel();
		page.panelizq("perfil");
		page.validarbtnsperfil("Productos de interes");
}
	@Test
	public void TS7081_Key_Metrics_Visualizar_boton_Añadir_Nuevo_Preocupaciones() {
		CustomerCare page = new CustomerCare(driver);
		page.openleftpanel();
		page.panelizq("perfil");
		page.validarbtnsperfil("Preocupaciones");
		driver.switchTo().defaultContent();
}
	@Test
	public void TS7082() throws ParseException {
		CustomerCare page = new CustomerCare(driver);
		page.openleftpanel();
		page.comparaciondefechas();
		driver.switchTo().defaultContent();
	}
	@Test
	public void TS7120_Key_Metrics_Panel_Perfil_Visualizar_Scroll(){
		CustomerCare page = new CustomerCare(driver);
		page.openleftpanel();
	((JavascriptExecutor)driver).executeScript("scroll(0,400)");
	JavascriptExecutor javascript = (JavascriptExecutor) driver;
	Boolean VertscrollStatus = (Boolean) javascript.executeScript("return document.documentElement.scrollHeight>document.documentElement.clientHeight;");
	assertTrue(VertscrollStatus);
	driver.switchTo().defaultContent();
	}
	
	@Test
	public void TS7144_Customer_Account_Management_Customer_Segmentation_Estado_Activo_Usuario_Externo() {
		CustomerCare page = new CustomerCare(driver);
		page.usarpanelcentral("Detalles");
		page.validarstatus("Active");
		}
	
	@Test
	public void TS7144_Customer_Account_Management_Customer_Segmentation_Estado_inactivo_Usuario_Externo() {
		CustomerCare page = new CustomerCare(driver);
		page.usarpanelcentral("Detalles");
		page.validarstatus("Active");
		}
}