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

	@AfterClass(groups= "CustomerCare")
	public void tearDown() {
		driver.quit();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}

	//@AfterMethod(groups= "CustomerCare")
	public void alert() {
		CustomerCare page = new CustomerCare(driver);
		page.cerrarultimapestaña();
		driver.switchTo().defaultContent();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try {
			Alert alert = driver.switchTo().alert();
			System.out.println(alert.getText());
		} catch (org.openqa.selenium.NoAlertPresentException e) {}
	}

	@BeforeClass(groups= "CustomerCare")
	public void init() throws Exception {
		this.driver = setConexion.setupEze();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		login(driver);
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		String a = driver.findElement(By.id("tsidLabel")).getText();
		driver.findElement(By.id("tsidLabel")).click();
		if (a.equals("Ventas")) {
			driver.findElement(By.xpath("//a[@href=\'/console?tsid=02uc0000000D6Hd\']")).click();
		} else {
			driver.findElement(By.xpath("//a[@href=\'/home/home.jsp?tsid=02u41000000QWha\']")).click();
			try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.findElement(By.id("tsidLabel")).click();
			driver.findElement(By.xpath("//a[@href=\'/console?tsid=02uc0000000D6Hd\']")).click();
		}
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		CustomerCare page = new CustomerCare(driver);
		page.elegircuenta("Fernando Care");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}

	@BeforeMethod(groups= "CustomerCare")
	public void setup() {
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}

	
	@Test(groups= "CustomerCare")
	public void TS7059_Verificar_Visualizar_Panel_Promociones() {
		CustomerCare page = new CustomerCare(driver);
		page.openleftpanel();
		BasePage cambioFrameByID = new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver,By.cssSelector(".via-slds-story-cards--header.spacer.acct-spacer")));
		List<WebElement> promociones = driver.findElements(By.cssSelector(".via-slds-story-cards--header.spacer.acct-spacer"));
		Assert.assertTrue(promociones.get(1).isDisplayed());
	}

	
	@Test(groups= "CustomerCare")
	public void TS7058_Visualizar_Panel_Servicios_Activos() {
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.xpath("//*[text() ='Servicios']")).click();
	}

	
	@Test(groups= "CustomerCare")
	public void TS7200_VerifyDisplayLogo() {
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.className("sd_custom_logo"));
	}

	
	// @Test(groups= "CustomerCare")
	public void TS7060_Visualizar_Panel_Alertas() {
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame1 = driver.findElements(By.tagName("iframe"));
		driver.findElement(By.xpath("//*[text() ='Servicios']")).click();
		driver.switchTo().frame(frame1.get(2));
		driver.findElement(By.className("ta-alertMessage-content"));
		driver.switchTo().defaultContent();
	}

	
	@Test(groups= "CustomerCare")
	public void TS7201_VerifyDisplayfilterAccounts() {
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		CustomerCare page = new CustomerCare(driver);
		page.openleftpanel();
		BasePage cambioFrameByID = new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("account-select-table")));
		List<WebElement> filtro = driver.findElements(By.className("account-select-table"));
		Assert.assertTrue(filtro.get(0).isDisplayed());
		driver.switchTo().defaultContent();
	}

	
	@Test(groups= "CustomerCare")
	public void TS7066_VerifyDisplayPanelAccountsClient() {
		CustomerCare page = new CustomerCare(driver);
		page.openleftpanel();
		BasePage cambioFrameByID = new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("account-select-container")));
		driver.findElement(By.className("account-select-container")).click();
		driver.switchTo().defaultContent();
	}

	
	@Test(groups= "CustomerCare")
	public void TS7054_VerifyDisplayPanelBusinessData() {
		CustomerCare page = new CustomerCare(driver);
		page.openleftpanel();
		BasePage cambioFrameByID = new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("profile-box")));
		driver.findElement(By.className("profile-box"));
		driver.switchTo().defaultContent();
	}

	
	@Test(groups= "CustomerCare")
	public void TS7061_Visualizar_Panel_Sesiones_Guiadas() {
		BasePage cambioFrameByID = new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("actions-content")));
		Assert.assertTrue(driver.findElement(By.className("actions-content")).isDisplayed());
		driver.switchTo().defaultContent();
	}

	
	@Test(groups= "CustomerCare")
	public void TS7062_Visualizar_Panel_Gestiones_abandonadas() {
		CustomerCare page = new CustomerCare(driver);
		page.openrightpanel();
		page.GestionAbandonadapanel();
		driver.switchTo().defaultContent();
	}

	
	@Test(groups= "CustomerCare")
	public void TS7063_Contraccion_Paneles() {
		BasePage cambioFrameByID = new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver,By.cssSelector(".slds-p-right--x-small.via-slds-story-cards--header-title")));
		driver.findElement(By.cssSelector(".slds-p-right--x-small.spacer")).click();
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-grid.slds-p-around--small.slds-wrap.via-slds-story-cards--header.slds-theme--shade.profile-tags-header"));
		element.get(1).click();
		driver.findElement(By.cssSelector(".slds-grid.slds-p-around--small.slds-wrap.via-slds-story-cards--header.slds-theme--shade.story-header.customerStory-header")).click();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver,By.className("actions-content")));
		List <WebElement> x = driver.findElements(By.cssSelector(".via-slds-story-cards--header.spacer"));
		x.get(0).click();
		x.get(1).click();
		x.get(2).click();
		try {
			Assert.assertFalse(driver.findElement(By.className("actions-content")).isDisplayed());
		} catch (org.openqa.selenium.NoSuchElementException e) {
			Assert.assertTrue(true);
		}
	}

	
	@Test(groups= "CustomerCare")
	public void TS7064_Contraccion_Panel_Datos_Comerciales() {
		BasePage cambioFrameByID = new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver,By.className("profile-box")));
		Assert.assertTrue(driver.findElement(By.className("profile-box")).isDisplayed());
		driver.switchTo().defaultContent();
	}

	
	@Test(groups= "CustomerCare")
	public void TS7065_Expansion_Paneles() {
		BasePage cambioFrameByID = new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver,By.cssSelector(".slds-p-right--x-small.via-slds-story-cards--header-title")));
		driver.findElement(By.cssSelector(".slds-p-right--x-small.spacer")).click();
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-grid.slds-p-around--small.slds-wrap.via-slds-story-cards--header.slds-theme--shade.profile-tags-header"));
		element.get(1).click();
		element.get(1).click();
		driver.findElement(By.cssSelector(".slds-grid.slds-p-around--small.slds-wrap.via-slds-story-cards--header.slds-theme--shade.story-header.customerStory-header")).click();
		driver.findElement(By.cssSelector(".slds-grid.slds-p-around--small.slds-wrap.via-slds-story-cards--header.slds-theme--shade.story-header.customerStory-header")).click();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver,By.className("actions-content")));
		List <WebElement> x = driver.findElements(By.cssSelector(".via-slds-story-cards--header.spacer"));
		x.get(0).click();
		x.get(0).click();
		x.get(1).click();
		x.get(1).click();
		x.get(2).click();
		x.get(2).click();
		Assert.assertTrue(driver.findElement(By.className("actions-content")).isDisplayed());
		driver.switchTo().defaultContent();
	}

	
	@Test(groups= "CustomerCare")
	public void TS7074_Key_Metrics_Visualizar_Picklist() {
		BasePage cambioFrameByID = new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver,By.id("text-input-01")));
		Assert.assertTrue(driver.findElement(By.id("text-input-01")).isDisplayed());
		driver.switchTo().defaultContent();
	}

	
	@Test(groups= "CustomerCare")
	public void TS7075_Key_Metrics_Funcionamiento_Picklist() {
		BasePage cambioFrameByID = new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver,By.cssSelector(".slds-p-right--x-small.via-slds-story-cards--header-title")));
		driver.findElement(By.xpath("/html/body/div/div[1]/ng-include/div[5]/ng-include/div/div[1]/div/div[1]/div")).click();
		driver.findElement(By.id("text-input-01")).click();
		List <WebElement> x = driver.findElements(By.cssSelector(".slds-lookup__item-action.slds-lookup__item-action--label.customer-story-label"));
		for (WebElement a : x) {
			if (a.getText().toLowerCase().contains("preocupaciones")) {
				a.click();
			}
		}
		WebElement element = driver.findElement(By.className("profile-tags-container"));
		Assert.assertTrue(element.getText().toLowerCase().contains("preocupaciones"));
		driver.switchTo().defaultContent();
	}

	
	@Test(groups= "CustomerCare")
	public void TS7076_Key_Metrics_Visualizar_boton_Añadir_Nuevo_Intereses_personales() {
		BasePage cambioFrameByID = new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver,By.cssSelector(".slds-p-right--x-small.via-slds-story-cards--header-title")));
		driver.findElement(By.xpath("/html/body/div/div[1]/ng-include/div[5]/ng-include/div/div[1]/div/div[1]/div")).click();
		driver.findElement(By.id("text-input-01")).click();
		List <WebElement> x = driver.findElements(By.cssSelector(".slds-lookup__item-action.slds-lookup__item-action--label.customer-story-label"));
		for (WebElement a : x) {
			if (a.getText().toLowerCase().contains("todos")) {
				a.click();
			}
		}
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.profile-tags-btn"));
		Assert.assertTrue(element.get(5).isDisplayed());
		driver.switchTo().defaultContent();
	}

	
	@Test(groups= "CustomerCare")
	public void TS7078_Key_Metrics_Visualizar_boton_Añadir_Nuevo_Criterios_de_compra() {
		BasePage cambioFrameByID = new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver,By.cssSelector(".slds-p-right--x-small.via-slds-story-cards--header-title")));
		driver.findElement(By.xpath("/html/body/div/div[1]/ng-include/div[5]/ng-include/div/div[1]/div/div[1]/div")).click();
		driver.findElement(By.id("text-input-01")).click();
		List <WebElement> x = driver.findElements(By.cssSelector(".slds-lookup__item-action.slds-lookup__item-action--label.customer-story-label"));
		for (WebElement a : x) {
			if (a.getText().toLowerCase().contains("todos")) {
				a.click();
			}
		}
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.profile-tags-btn"));
		Assert.assertTrue(element.get(3).isDisplayed());
		driver.switchTo().defaultContent();
	}

	
	@Test(groups= "CustomerCare")
	public void TS7079_Key_Metrics_Visualizar_boton_Añadir_Nuevo_Familia() {
		BasePage cambioFrameByID = new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver,By.cssSelector(".slds-p-right--x-small.via-slds-story-cards--header-title")));
		driver.findElement(By.xpath("/html/body/div/div[1]/ng-include/div[5]/ng-include/div/div[1]/div/div[1]/div")).click();
		driver.findElement(By.id("text-input-01")).click();
		List <WebElement> x = driver.findElements(By.cssSelector(".slds-lookup__item-action.slds-lookup__item-action--label.customer-story-label"));
		for (WebElement a : x) {
			if (a.getText().toLowerCase().contains("todos")) {
				a.click();
			}
		}
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.profile-tags-btn"));
		Assert.assertTrue(element.get(4).isDisplayed());
		driver.switchTo().defaultContent();
	}

	
	@Test(groups= "CustomerCare")
	public void TS7080_Key_Metrics_Visualizar_boton_Añadir_Nuevo_Productos_de_interes() {
		BasePage cambioFrameByID = new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver,By.cssSelector(".slds-p-right--x-small.via-slds-story-cards--header-title")));
		driver.findElement(By.xpath("/html/body/div/div[1]/ng-include/div[5]/ng-include/div/div[1]/div/div[1]/div")).click();
		driver.findElement(By.id("text-input-01")).click();
		List <WebElement> x = driver.findElements(By.cssSelector(".slds-lookup__item-action.slds-lookup__item-action--label.customer-story-label"));
		for (WebElement a : x) {
			if (a.getText().toLowerCase().contains("todos")) {
				a.click();
			}
		}
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.profile-tags-btn"));
		Assert.assertTrue(element.get(1).isDisplayed());
		driver.switchTo().defaultContent();
	}

	
	@Test(groups= "CustomerCare")
	public void TS7081_Key_Metrics_Visualizar_boton_Añadir_Nuevo_Preocupaciones() {
		BasePage cambioFrameByID = new BasePage();
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver,By.cssSelector(".slds-p-right--x-small.via-slds-story-cards--header-title")));
		driver.findElement(By.xpath("/html/body/div/div[1]/ng-include/div[5]/ng-include/div/div[1]/div/div[1]/div")).click();
		driver.findElement(By.id("text-input-01")).click();
		List <WebElement> x = driver.findElements(By.cssSelector(".slds-lookup__item-action.slds-lookup__item-action--label.customer-story-label"));
		for (WebElement a : x) {
			if (a.getText().toLowerCase().contains("todos")) {
				a.click();
			}
		}
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.profile-tags-btn"));
		Assert.assertTrue(element.get(0).isDisplayed());
		driver.switchTo().defaultContent();
	}

	
	@Test(groups= "CustomerCare")
	public void TS7082_Visualizar_Fecha_de_vencimiento() throws ParseException {
		CustomerCare page = new CustomerCare(driver);
		page.openleftpanel();
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-text-body_regular.story-field"));
		for (WebElement x : element) {
			if (x.getText().contains("  /  /    ")) {
				Assert.assertTrue(x.isDisplayed());
			}
		}		
	}

	
	@Test(groups= "CustomerCare")
	public void TS7120_Key_Metrics_Panel_Perfil_Visualizar_Scroll() {
		BasePage cambioFrameByID = new BasePage(driver);
		driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("profile-box")));
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		Boolean VertscrollStatus = (Boolean) javascript.executeScript("return document.documentElement.scrollHeight>document.documentElement.clientHeight;");
		assertTrue(VertscrollStatus);
	}

	
	@Test(groups= "CustomerCare")
	public void TS7144_Customer_Account_Management_Customer_Segmentation_Estado_Activo_Usuario_Externo() {
		CustomerCare page = new CustomerCare(driver);
		page.usarpanelcentral("Detalles");
		page.validarstatus("Active");
	}

	
	@Test(groups= "CustomerCare")
	public void TS7144_Customer_Account_Management_Customer_Segmentation_Estado_inactivo_Usuario_Externo() {
		CustomerCare page = new CustomerCare(driver);
		page.usarpanelcentral("Detalles");
		page.validarstatus("Active");
	}
}