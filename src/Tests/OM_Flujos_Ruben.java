package Tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.OM;
import Pages.SCP;
import Pages.SalesBase;
import Pages.setConexion;

public class OM_Flujos_Ruben extends TestBase {

	private WebDriver driver;
	private WebDriverWait wait;
	private OM pageOm;

	@BeforeClass(alwaysRun = true)
	public void init() throws Exception {
		this.driver = setConexion.setupEze();
		wait = new WebDriverWait(driver, 20);
		pageOm = new OM(driver);
		sleep(5000);
		// Usuario Victor OM
		login(driver, "https://crm--sit.cs14.my.salesforce.com/", "U585991", "Testa10k");
		sleep(5000);
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		driver.switchTo().defaultContent();
		sleep(2000);
		BasePage bp = new BasePage(driver);
		bp.cajonDeAplicaciones("Sales");

		// click +
		sleep(5000);
		pageOm.clickMore();
		sleep(3000);

		// click en Ordenes
		pageOm.clickOnListTabs("Orders");
	}

	// @AfterClass(alwaysRun=true)
	public void tearDown() {
		sleep(2000);
		driver.quit();
		sleep(1000);
	}

	@Test(groups = "OM")
	public void F_Alta_de_linea_en_salesforce() {

		String accountName = "Buda OM";
		String plan = "Plan Prepago Nacional";

		// Crear Orden
		WebElement newOrderButton = wait
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("new"))));
		newOrderButton.click();

		// Completar Formulario de Nueva Orden
		WebElement dateFormatButton = wait
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("dateFormat"))));
		driver.findElement(By.id("accid")).sendKeys(accountName);
		dateFormatButton.click();
		Select statusSelect = new Select(driver.findElement(By.id("Status")));
		statusSelect.selectByVisibleText("Draft");
		driver.findElement(By.name("save")).click();

		// Click en CPQ
		sleep(3000);
		WebElement cpqButton = wait
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("vlocity_cmt__cpq"))));
		String orderNumber = driver.findElement(By.cssSelector(".noSecondHeader.pageType")).getText();
		orderNumber = orderNumber.replace("Order ", "");
		System.out.println("Order #" + orderNumber);
		cpqButton.click();

		// Seleccionar Plan
		// DocumentationError #1
		// Los elementos no aparecen en la lista como dice la documentación
		sleep(5000);
		SalesBase sb = new SalesBase(driver);
		sb.elegirplan(plan);

		// Ingreso Linea
		sleep(10000);
		WebElement planLineaButton = driver.findElement(
				By.xpath("//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[3]/div[10]/div/button"));
		planLineaButton.click();
		sleep(1000);
		WebElement configureLineaButton = driver.findElement(By.xpath(
				"//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[3]/div[10]/div/div/ul/li[3]/a/span"));
		configureLineaButton.click();
		sleep(1000);
		WebElement lineaInput = driver.findElement(
				By.xpath("//*[@id=\"js-cpq-product-cart-config-form\"]/div[1]/div/form/div[4]/div[1]/input"));
		lineaInput.sendKeys(pageOm.getRandomNumber(10));
		// sleep(1000);
		WebElement closeLineaInputButton = driver.findElement(By.xpath(
				"/html/body/div[1]/div[1]/ng-include/div/div[2]/div[2]/div[3]/div/div/ng-include/div/div[1]/div/button"));
		closeLineaInputButton.click();

		// Ingreso SIM Data
		sleep(10000);
		WebElement extendPlanButton = driver.findElement(
				By.xpath("//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[3]/div[1]/div[1]/button"));
		extendPlanButton.click();
		sleep(1000);
		WebElement simCardButton = driver.findElement(By.xpath(
				"//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[4]/div[2]/div/ng-include/div/div[2]/ng-include/div/div[1]/div/div[2]/div[11]/div[2]/button"));
		simCardButton.click();
		sleep(1000);
		WebElement configureSimButton = driver.findElement(By.xpath(
				"//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[4]/div[2]/div/ng-include/div/div[2]/ng-include/div/div[1]/div/div[2]/div[11]/div[2]/div/ul/li[3]/a/span"));
		configureSimButton.click();
		WebElement iccidInput = driver.findElement(
				By.xpath("//*[@id=\"js-cpq-product-cart-config-form\"]/div[1]/div/form/div[14]/div[1]/input"));
		iccidInput.sendKeys(pageOm.getRandomNumber(9));
		WebElement imsiInput = driver.findElement(
				By.xpath("//*[@id=\"js-cpq-product-cart-config-form\"]/div[1]/div/form/div[15]/div[1]/input"));
		imsiInput.sendKeys(pageOm.getRandomNumber(8));
		WebElement kiInput = driver.findElement(
				By.xpath("//*[@id=\"js-cpq-product-cart-config-form\"]/div[1]/div/form/div[16]/div[1]/input"));
		kiInput.sendKeys(pageOm.getRandomNumber(9));
		WebElement closeSimInputButton = driver.findElement(By.xpath(
				"/html/body/div[1]/div[1]/ng-include/div/div[2]/div[2]/div[3]/div/div/ng-include/div/div[1]/div/button"));
		closeSimInputButton.click();

		// Editar Record
		sleep(5000);
		WebElement viewRecordButton = driver.findElement(By.xpath("//*[@id=\"-import-btn\"]"));
		viewRecordButton.click();
		sleep(5000);
		WebElement editOrderButton = wait
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("edit"))));
		editOrderButton.click();
		WebElement gestionElement = wait
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("00Nc0000002IvyM"))));
		Select gestionSelect = new Select(gestionElement);
		gestionSelect.selectByVisibleText("Venta");
		driver.findElement(By.name("save")).click();

		// Descomponer Orden
		sleep(10000);
		driver.findElement(By.name("ta_submit_order")).click();
		
		// Popup Login
		sleep(20000);
		String mainWindowHandle = driver.getWindowHandle();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		for(String t: tabs) {
			if(!t.equals(mainWindowHandle)) {
				driver.switchTo().window(t);
				break;
			}
		}
		driver.findElement(By.id("hint_00Dc0000003w19T005c0000003FI6A")).click();
		
		// Plan de orquestacion
		
		
		
		// sleep(60000);
		// driver.quit();

	}

}