package Tests;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Accounts;
import Pages.CustomerCare;
import Pages.HomeBase;
import Pages.setConexion;

public class CustomerCareFase3F  extends TestBase {
	
	private Accounts ac;
	private HomeBase hb;
	private CustomerCare cc;
	
	
	@AfterClass(groups = {"CustomerCare", "Vista360Layout","ProblemasConRecargas"})
	public void tearDown2() {
		driver.quit();	
	}
	
	@BeforeClass(groups = {"CustomerCare", "Vista360Layout", "ProblemasConRecargas"})
	public void init() throws Exception {
		driver = setConexion.setupEze();
		ac = new Accounts(driver);
		hb = new HomeBase(driver);
		cc = new CustomerCare(driver);
		login(driver);
		sleep(5000);
		if (driver.findElement(By.id("tsidLabel")).getText().equals("Consola FAN")) {
			hb.switchAppsMenu();
			sleep(2000);
			hb.selectAppFromMenuByName("Ventas");
			sleep(5000);
		}
		hb.switchAppsMenu();
		sleep(2000);
		hb.selectAppFromMenuByName("Consola FAN");
		sleep(10000);
		goToLeftPanel2(driver, "Cuentas");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(ac.getFrameForElement(driver, By.cssSelector(".topNav.primaryPalette")));
		Select field = new Select(driver.findElement(By.name("fcf")));
		try {
			field.selectByVisibleText("Todas Las cuentas");
		} catch (org.openqa.selenium.NoSuchElementException ExM) {
			field.selectByVisibleText("Todas las cuentas");
		}
	}
	
	@BeforeMethod(groups = {"CustomerCare", "Vista360Layout", "ProblemasConRecargas"})
	public void setUp() throws Exception {
		sleep(6000);
		cc.cerrarultimapesta�a();
		sleep(12000);
		cc.elegircuenta("aaaaFernando Care");
		sleep(14000);		
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".console-card.active")));
		sleep(9000);
	}
	
	
	@Test(groups = {"CustomerCare", "Vista360Layout"})
	public void TS37501_Historial_De_Recargas_PrePago_Monto_Total_De_Recargas_Visualizar_Monto_Total_De_Recargas() {
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".console-card.active")));
		driver.findElement(By.className("card-info")).findElements(By.tagName("li")).get(1).click();
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".via-slds.slds-m-around--small.ng-scope")));
		List<WebElement> historiales = driver.findElements(By.cssSelector(".via-slds.slds-m-around--small.ng-scope"));
		for (WebElement UnH : historiales) {
			if (UnH.findElement(By.cssSelector(".slds-text-heading--large.slds-size--1-of-1.slds-medium-size--1-of-1.slds-large-size--1-of-1.slds-m-bottom--small")).findElement(By.tagName("p")).getText().toLowerCase().equals("historial de recargas")) {
				List<WebElement> tuplas = driver.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
				for (WebElement UnaT : tuplas) {
					if (UnaT.findElements(By.tagName("td")).get(3).findElement(By.tagName("b")).getText().isEmpty())
						assertTrue(false);
				}
				break;
			}
		}
		ac.closeAccountServiceTabByName("Historiales");
	}
	
	@Test(groups = {"CustomerCare", "Vista360Layout"})
	public void TS37508_Historial_De_Recargas_PrePago_Ordenamiento_Y_Paginado_De_Grilla_Ordenar_Por_Beneficios() {
		boolean enc = false;
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".console-card.active")));
		driver.findElement(By.className("card-info")).findElements(By.tagName("li")).get(1).click();
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".via-slds.slds-m-around--small.ng-scope")));
		List<WebElement> historiales = driver.findElements(By.cssSelector(".via-slds.slds-m-around--small.ng-scope"));
		for (WebElement UnH : historiales) {
			if (UnH.findElement(By.cssSelector(".slds-text-heading--large.slds-size--1-of-1.slds-medium-size--1-of-1.slds-large-size--1-of-1.slds-m-bottom--small")).findElement(By.tagName("p")).getText().toLowerCase().equals("historial de recargas")) {
				List<WebElement> tuplas = driver.findElement(By.tagName("thead")).findElements(By.tagName("th"));
				for (WebElement UnaT : tuplas) {
					if (UnaT.findElement(By.tagName("a")).getText().toLowerCase().equals("beneficios")) {
						enc = true;
						assertTrue(UnaT.findElement(By.tagName("a")).getAttribute("ng-click").equals("sortBy(field)"));
						break;
					}
				}
				break;
			}
		}
		assertTrue(enc);
		ac.closeAccountServiceTabByName("Historiales");
	}
	
	@Test(groups = {"CustomerCare", "Vista360Layout"})
	public void TS37504_Historial_De_Recargas_PrePago_Ordenamiento_Y_Paginado_De_Grilla_Ordenar_Por_Canal() {
		boolean enc = false;
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".console-card.active")));
		driver.findElement(By.className("card-info")).findElements(By.tagName("li")).get(1).click();
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".via-slds.slds-m-around--small.ng-scope")));
		List<WebElement> historiales = driver.findElements(By.cssSelector(".via-slds.slds-m-around--small.ng-scope"));
		for (WebElement UnH : historiales) {
			if (UnH.findElement(By.cssSelector(".slds-text-heading--large.slds-size--1-of-1.slds-medium-size--1-of-1.slds-large-size--1-of-1.slds-m-bottom--small")).findElement(By.tagName("p")).getText().toLowerCase().equals("historial de recargas")) {
				List<WebElement> tuplas = driver.findElement(By.tagName("thead")).findElements(By.tagName("th"));
				for (WebElement UnaT : tuplas) {
					if (UnaT.findElement(By.tagName("a")).getText().toLowerCase().equals("canal")) {
						enc = true;
						assertTrue(UnaT.findElement(By.tagName("a")).getAttribute("ng-click").equals("sortBy(field)"));
						break;
					}
				}
				break;
			}
		}
		assertTrue(enc);
		ac.closeAccountServiceTabByName("Historiales");
	}
	
	@Test(groups = {"CustomerCare", "Vista360Layout"})
	public void TS37505_Historial_De_Recargas_PrePago_Ordenamiento_Y_Paginado_De_Grilla_Ordenar_Por_Descripcion() {
		boolean enc = false;
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".console-card.active")));
		driver.findElement(By.className("card-info")).findElements(By.tagName("li")).get(1).click();
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".via-slds.slds-m-around--small.ng-scope")));
		List<WebElement> historiales = driver.findElements(By.cssSelector(".via-slds.slds-m-around--small.ng-scope"));
		for (WebElement UnH : historiales) {
			if (UnH.findElement(By.cssSelector(".slds-text-heading--large.slds-size--1-of-1.slds-medium-size--1-of-1.slds-large-size--1-of-1.slds-m-bottom--small")).findElement(By.tagName("p")).getText().toLowerCase().equals("historial de recargas")) {
				List<WebElement> tuplas = driver.findElement(By.tagName("thead")).findElements(By.tagName("th"));
				for (WebElement UnaT : tuplas) {
					if (UnaT.findElement(By.tagName("a")).getText().toLowerCase().equals("descripci�n")) {
						enc = true;
						assertTrue(UnaT.findElement(By.tagName("a")).getAttribute("ng-click").equals("sortBy(field)"));
						break;
					}
				}
				break;
			}
		}
		assertTrue(enc);
		ac.closeAccountServiceTabByName("Historiales");
	}
	
	@Test(groups = {"CustomerCare", "Vista360Layout"})
	public void TS37503_Historial_De_Recargas_PrePago_Ordenamiento_Y_Paginado_De_Grilla_Ordenar_Por_Fecha_Y_Hora() {
		boolean enc = false;
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".console-card.active")));
		driver.findElement(By.className("card-info")).findElements(By.tagName("li")).get(1).click();
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".via-slds.slds-m-around--small.ng-scope")));
		List<WebElement> historiales = driver.findElements(By.cssSelector(".via-slds.slds-m-around--small.ng-scope"));
		for (WebElement UnH : historiales) {
			if (UnH.findElement(By.cssSelector(".slds-text-heading--large.slds-size--1-of-1.slds-medium-size--1-of-1.slds-large-size--1-of-1.slds-m-bottom--small")).findElement(By.tagName("p")).getText().toLowerCase().equals("historial de recargas")) {
				List<WebElement> tuplas = driver.findElement(By.tagName("thead")).findElements(By.tagName("th"));
				for (WebElement UnaT : tuplas) {
					if (UnaT.findElement(By.tagName("a")).getText().toLowerCase().equals("fecha")) {
						enc = true;
						assertTrue(UnaT.findElement(By.tagName("a")).getAttribute("ng-click").equals("sortBy(field)"));
						break;
					}
				}
				break;
			}
		}
		assertTrue(enc);
		ac.closeAccountServiceTabByName("Historiales");
	}
	
	@Test(groups = {"CustomerCare", "Vista360Layout"})
	public void TS37506_Historial_De_Recargas_PrePago_Ordenamiento_Y_Paginado_De_Grilla_Ordenar_Por_Monto() {
		boolean enc = false;
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".console-card.active")));
		driver.findElement(By.className("card-info")).findElements(By.tagName("li")).get(1).click();
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".via-slds.slds-m-around--small.ng-scope")));
		List<WebElement> historiales = driver.findElements(By.cssSelector(".via-slds.slds-m-around--small.ng-scope"));
		for (WebElement UnH : historiales) {
			if (UnH.findElement(By.cssSelector(".slds-text-heading--large.slds-size--1-of-1.slds-medium-size--1-of-1.slds-large-size--1-of-1.slds-m-bottom--small")).findElement(By.tagName("p")).getText().toLowerCase().equals("historial de recargas")) {
				List<WebElement> tuplas = driver.findElement(By.tagName("thead")).findElements(By.tagName("th"));
				for (WebElement UnaT : tuplas) {
					if (UnaT.findElement(By.tagName("a")).getText().toLowerCase().equals("monto")) {
						enc = true;
						assertTrue(UnaT.findElement(By.tagName("a")).getAttribute("ng-click").equals("sortBy(field)"));
						break;
					}
				}
				break;
			}
		}
		assertTrue(enc);
		ac.closeAccountServiceTabByName("Historiales");
	}
	
	@Test(groups = {"CustomerCare", "Vista360Layout"})
	public void TS37507_Historial_De_Recargas_PrePago_Ordenamiento_Y_Paginado_De_Grilla_Ordenar_Por_Vencimiento() {
		boolean enc = false;
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".console-card.active")));
		driver.findElement(By.className("card-info")).findElements(By.tagName("li")).get(1).click();
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".via-slds.slds-m-around--small.ng-scope")));
		List<WebElement> historiales = driver.findElements(By.cssSelector(".via-slds.slds-m-around--small.ng-scope"));
		for (WebElement UnH : historiales) {
			if (UnH.findElement(By.cssSelector(".slds-text-heading--large.slds-size--1-of-1.slds-medium-size--1-of-1.slds-large-size--1-of-1.slds-m-bottom--small")).findElement(By.tagName("p")).getText().toLowerCase().equals("historial de recargas")) {
				List<WebElement> tuplas = driver.findElement(By.tagName("thead")).findElements(By.tagName("th"));
				for (WebElement UnaT : tuplas) {
					if (UnaT.findElement(By.tagName("a")).getText().toLowerCase().equals("vencimiento")) {
						enc = true;
						assertTrue(UnaT.findElement(By.tagName("a")).getAttribute("ng-click").equals("sortBy(field)"));
						break;
					}
				}
				break;
			}
		}
		assertTrue(enc);
		ac.closeAccountServiceTabByName("Historiales");
	}
	
	@Test(groups = {"CustomerCare", "ProblemasConRecargas"})
	public void TS37326_Problems_With_Refills_Tarjeta_De_Recarga_Prepaga_Verificaci�n_Numero_De_Lote_Ingresa_15_D�gitos() {
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".console-card.active")));
		driver.findElement(By.className("card-info")).findElement(By.className("details")).click();
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions")));
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+ driver.findElement(By.cssSelector(".console-flyout.active.flyout")).getLocation().y + ")");
		sleep(3000);
		driver.findElement(By.cssSelector(".console-flyout.active.flyout")).findElements(By.tagName("i")).get(1).click();
		List<WebElement> wAsd = driver.findElements(By.id("refillMethod"));
		for (WebElement x : wAsd) {
			if (x.getText().toLowerCase().contains("tarjeta prepaga")) {
				x.click();
			}
		}
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.id("stepChooseMethod_nextBtn")));
		sleep(5000);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+ driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).getLocation().y+ ")");
		List<WebElement> wX = driver.findElements(By.className("slds-form-element__control"));
		for (WebElement x : wX) {
			if (x.getText().toLowerCase().contains("siguiente")) {
				x.click();
				break;
			}
		}
		sleep(5000);
		driver.findElement(By.id("lotNumber")).sendKeys("145789654212458");
		assertTrue(driver.findElement(By.cssSelector(".vlc-slds-error-block.ng-scope")).findElement(By.cssSelector(".error.ng-scope")).findElement(By.cssSelector(".description.ng-binding")).getText().toLowerCase().equals("longitud m�nima de 16"));
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + driver.findElement(By.id("stepPrepaidCardData_nextBtn")).getLocation().y + ")");
		driver.findElement(By.id("stepPrepaidCardData_nextBtn")).click();
		sleep(3000);
		assertTrue(driver.findElement(By.cssSelector(".slds-modal__header.slds-theme--info.slds-theme--alert-texture.slds-theme--error")).isDisplayed());
		ac.closeAccountServiceTabByName("Problemas con Recargas");
	}
	
	@Test(groups = {"CustomerCare", "ProblemasConRecargas"})
	public void TS37328_Problems_With_Refills_Tarjeta_De_Recarga_Prepaga_Verificaci�n_Numero_De_Lote_Ingresa_Letras() {
		boolean enc = true;
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".console-card.active")));
		driver.findElement(By.className("card-info")).findElement(By.className("details")).click();
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions")));
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+ driver.findElement(By.cssSelector(".console-flyout.active.flyout")).getLocation().y + ")");
		sleep(3000);
		driver.findElement(By.cssSelector(".console-flyout.active.flyout")).findElements(By.tagName("i")).get(1).click();
		List<WebElement> wAsd = driver.findElements(By.id("refillMethod"));
		for (WebElement x : wAsd) {
			if (x.getText().toLowerCase().contains("tarjeta prepaga")) {
				x.click();
			}
		}
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.id("stepChooseMethod_nextBtn")));
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+ driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).getLocation().y+ ")");
		List<WebElement> wX = driver.findElements(By.className("slds-form-element__control"));
		for (WebElement x : wX) {
			if (x.getText().toLowerCase().contains("siguiente")) {
				x.click();
				break;
			}
		}
		sleep(5000);
		driver.findElement(By.id("lotNumber")).sendKeys("letrasletrasletr");
		sleep(2000);
		List<WebElement> errores = driver.findElement(By.cssSelector(".vlc-slds-error-block.ng-scope")).findElement(By.cssSelector(".error.ng-scope")).findElements(By.cssSelector(".description.ng-binding"));
		for (WebElement UnE : errores) {
			if (UnE.getText().toLowerCase().equals("s�lo se permiten n�meros")) {
				enc = true;
				break;
			}
		}
		assertTrue(enc);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + driver.findElement(By.id("stepPrepaidCardData_nextBtn")).getLocation().y + ")");
		driver.findElement(By.id("stepPrepaidCardData_nextBtn")).click();
		sleep(3000);
		assertTrue(driver.findElement(By.cssSelector(".slds-modal__header.slds-theme--info.slds-theme--alert-texture.slds-theme--error")).isDisplayed());
		ac.closeAccountServiceTabByName("Problemas con Recargas");
	}
	
	@Test(groups = {"CustomerCare", "ProblemasConRecargas"})
	public void TS37327_Problems_With_Refills_Tarjeta_De_Recarga_Prepaga_Verificaci�n_Numero_De_Lote_Ingresa_17_D�gitos() {
		boolean enc = true;
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".console-card.active")));
		driver.findElement(By.className("card-info")).findElement(By.className("details")).click();
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions")));
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+ driver.findElement(By.cssSelector(".console-flyout.active.flyout")).getLocation().y + ")");
		sleep(3000);
		driver.findElement(By.cssSelector(".console-flyout.active.flyout")).findElements(By.tagName("i")).get(1).click();
		List<WebElement> wAsd = driver.findElements(By.id("refillMethod"));
		for (WebElement x : wAsd) {
			if (x.getText().toLowerCase().contains("tarjeta prepaga")) {
				x.click();
			}
		}
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.id("stepChooseMethod_nextBtn")));
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+ driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).getLocation().y+ ")");
		List<WebElement> wX = driver.findElements(By.className("slds-form-element__control"));
		for (WebElement x : wX) {
			if (x.getText().toLowerCase().contains("siguiente")) {
				x.click();
				break;
			}
		}
		sleep(5000);
		driver.findElement(By.id("lotNumber")).sendKeys("12457856321457895");
		List<WebElement> errores = driver.findElement(By.cssSelector(".vlc-slds-error-block.ng-scope")).findElement(By.cssSelector(".error.ng-scope")).findElements(By.cssSelector(".description.ng-binding"));
		for (WebElement UnE : errores) {
			if (UnE.getText().toLowerCase().equals("longitud m�xima de 16")) {
				enc = true;
				break;
			}
		}
		assertTrue(enc);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + driver.findElement(By.id("stepPrepaidCardData_nextBtn")).getLocation().y + ")");
		driver.findElement(By.id("stepPrepaidCardData_nextBtn")).click();
		sleep(3000);
		assertTrue(driver.findElement(By.cssSelector(".slds-modal__header.slds-theme--info.slds-theme--alert-texture.slds-theme--error")).isDisplayed());
		ac.closeAccountServiceTabByName("Problemas con Recargas");
	}
	
	@Test(groups = {"CustomerCare", "ProblemasConRecargas"})
	public void TS37325_Problems_With_Refills_Tarjeta_De_Recarga_Prepaga_Verificaci�n_Numero_De_Lote_Ingresa_16_D�gitos() {
		boolean enc = true;
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".console-card.active")));
		driver.findElement(By.className("card-info")).findElement(By.className("details")).click();
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions")));
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+ driver.findElement(By.cssSelector(".console-flyout.active.flyout")).getLocation().y + ")");
		sleep(3000);
		driver.findElement(By.cssSelector(".console-flyout.active.flyout")).findElements(By.tagName("i")).get(1).click();
		List<WebElement> wAsd = driver.findElements(By.id("refillMethod"));
		for (WebElement x : wAsd) {
			if (x.getText().toLowerCase().contains("tarjeta prepaga")) {
				x.click();
			}
		}
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.id("stepChooseMethod_nextBtn")));
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+ driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).getLocation().y+ ")");
		List<WebElement> wX = driver.findElements(By.className("slds-form-element__control"));
		for (WebElement x : wX) {
			if (x.getText().toLowerCase().contains("siguiente")) {
				x.click();
				break;
			}
		}
		sleep(5000);
		driver.findElement(By.id("lotNumber")).sendKeys("1245785632145789");
		List<WebElement> errores = driver.findElement(By.cssSelector(".vlc-slds-error-block.ng-scope")).findElement(By.cssSelector(".error.ng-scope")).findElements(By.cssSelector(".description.ng-binding"));
		for (WebElement UnE : errores) {
			if (!UnE.getText().isEmpty()) {
				enc = false;
				break;
			}
		}
		assertTrue(enc);
		ac.closeAccountServiceTabByName("Problemas con Recargas");
	}
	
	@Test(groups = {"CustomerCare", "ProblemasConRecargas"})
	public void TS37534_Problems_With_Refills_Problemas_Con_Recargas_Medio_De_Recarga_Seleccionar_ROL() {
		String filePath = "C:\\Users\\Florangel\\Downloads\\usuaria.png";
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".console-card.active")));
		driver.findElement(By.className("card-info")).findElement(By.className("details")).click();
		sleep(5000);
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-small-size--3-of-12.slds-medium-size--3-of-12.slds-large-size--3-of-12.flyout-actions")));
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+ driver.findElement(By.cssSelector(".console-flyout.active.flyout")).getLocation().y + ")");
		sleep(3000);
		driver.findElement(By.cssSelector(".console-flyout.active.flyout")).findElements(By.tagName("i")).get(1).click();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("refillMethod")));
		driver.findElements(By.cssSelector(".slds-radio.ng-scope")).get(1).click();
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.id("stepChooseMethod_nextBtn")));
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+ driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).getLocation().y+ ")");
		List<WebElement> wX = driver.findElements(By.className("slds-form-element__control"));
		for (WebElement x : wX) {
			if (x.getText().toLowerCase().contains("siguiente")) {
				x.click();
				break;
			}
		}
		sleep(8000);
		driver.switchTo().frame(cambioFrame(driver, By.id("refillDate")));
		driver.findElement(By.id("refillDate")).sendKeys("12-15-2017");
		driver.findElement(By.id("refillAmount")).sendKeys("150");
		driver.findElement(By.id("receiptCode")).sendKeys("150");
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + driver.findElement(By.id("stepInternetRefill_nextBtn")).getLocation().y + ")");
		driver.findElement(By.id("stepInternetRefill_nextBtn")).click();
		sleep(8000);
		wX = driver.findElements(By.id("useExistingCase"));
		for (WebElement x : wX) {
			if (x.getText().toLowerCase().contains("nuevo")) {
				x.click();
			}
		}
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + driver.findElement(By.id("StepExistingCase_nextBtn")).getLocation().y + ")");
		driver.findElement(By.id("StepExistingCase_nextBtn")).click();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("HasVoucher")));
		/*Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id("HasVoucher"))).doubleClick().build().perform();*/
		driver.findElement(By.cssSelector(".slds-radio--faux.ng-scope")).click();
		sleep(2000);
		driver.findElement(By.id("FileAttach")).sendKeys(filePath);
		sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + driver.findElement(By.id("stepAttachDocuments_nextBtn")).getLocation().y + ")");
		driver.findElement(By.id("stepAttachDocuments_nextBtn")).click();
		sleep(8000);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + driver.findElement(By.id("stepSummary_nextBtn")).getLocation().y + ")");
		driver.findElement(By.id("stepSummary_nextBtn")).click();
		sleep(8000);
		assertTrue(driver.findElement(By.id("txtROLConfirmationSuccess")).findElement(By.tagName("h1")).getText().toLowerCase().contains("la recarga se realiz� con �xito"));
		//ac.closeAccountServiceTabByName("Problemas con Recargas");
	}
}
