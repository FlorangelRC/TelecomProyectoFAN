package Pages;

import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Random;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OM {

	static WebDriver driver;

	// *********************************CONSTRUCTOR******************************************************//

	public OM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// *********************************ELEMENTOS******************************************************//

	@FindBy(id = "tabBar")
	private WebElement TabBar;

	@FindBy(css = ".dataCol.orderBlock")
	private List<WebElement> listTabs;

	@FindBy(name = "btnAgregar")
	private WebElement agregar;

	@FindBy(id = "fileinput")
	private WebElement adjuntar;

	// ********************************METODOS*******************************************************//
	public void sleep(long s) {
		try {
			Thread.sleep(s);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Hace click en los tabs principal segun el "Id" recibido.
	 * 
	 * @param id
	 */
	public void clickTab(String id) {
		TabBar.findElement(By.id(id)).click();
	}

	/**
	 * Click en "+".
	 */
	public void clickMore() {
		TabBar.findElement(By.id("AllTab_Tab")).click();
	}

	/**
	 * Hace click al tab del listado que aparece despues de Hacerle click en "+".
	 * 
	 * @param tab
	 */
	public void clickOnListTabs(String tab) {
		boolean flag = true;
		for (WebElement option : listTabs) {
			System.out.println(option.getText());
			if (option.getText().toLowerCase().equals(tab.toLowerCase())) {
				WebElement BenBoton = option.findElement(By.tagName("a"));
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + BenBoton.getLocation().y + ")");
				sleep(1000);
				BenBoton.click();
				flag = false;
				break;
			}
		}
		if (flag)
			System.out.println("No hizo Click en: " + tab);
		sleep(5000);
	}

	/**
	 * Cambia de Pestana en el Navegador.
	 * 
	 * @param Ventana
	 */
	public void cambiarVentanaNavegador(int Ventana) {
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTab.get(Ventana));
	}

	public void primeraOrden() {
		WebElement fila = driver.findElement(By.cssSelector(".dataRow.even.first"));
		WebElement nro = fila.findElement(By.tagName("th")).findElement(By.tagName("a"));
		nro.click();
		sleep(5000);
	}

	public boolean scrollDown(WebElement Elemento) {
		try {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + Elemento.getLocation().y + ")");
			return true;
		} catch (NullPointerException e) {
			System.out.println("Error: No se puede hacer Scroll");
			return false;
		}
	}

	public boolean scrollDownInAView(WebElement Elemento) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Elemento);
			return true;
		} catch (NullPointerException e) {
			System.out.println("Error: No se puede hacer Scroll");
			return false;
		}
	}

	public void goToMenuOM() {
		sleep(5000);
		String actual = driver.findElement(By.id("tsidLabel")).getText();

		if (actual.toLowerCase().contains("sales") || actual.toLowerCase().contains("ventas"))
			return;
		else {
			driver.findElement(By.id("tsid")).click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			driver.findElement(By.xpath("//a[@href=\"/home/home.jsp?tsid=02u41000000QWha\"]")).click();
		}
	}

	/**
	 * Crea una orden desde la vista de todas las ordenes.
	 */
	public void crearOrden(String Cuenta) {

		driver.findElement(By.name("new")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		// crearCuentaOM(Cuenta);
		// buscarCuentaOMenOrden(Cuenta);
		driver.findElement(By.id("accid")).sendKeys(Cuenta);
		driver.findElement(By.className("dateFormat")).click();
		Select Estado = new Select(driver.findElement(By.id("Status")));
		Estado.selectByVisibleText("Draft");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		driver.findElement(By.name("save")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Pasa todas las cajas rojas del flujo de orquestacion a verdes.
	 */
	public void completarFlujoOrquestacion() {
		boolean chiqui = false;
		while (chiqui == false) {

			try {
				driver.findElement(By.id("zoomOut")).click();
			} catch (Exception ex1) {
				chiqui = true;
				break;
			}

		}
		sleep(10000);
		List<WebElement> cajas = driver.findElements(By.cssSelector(".item-label-container.item-header.item-failed"));
		// cajas.addAll(driver.findElements(By.cssSelector(".item-label-container.item-header.item-fatally-failed")));
		int i = 1;
		while (cajas.size() > 0) {
			for (WebElement UnaC : cajas) {
				UnaC.click();
				sleep(5000);
				cambiarVentanaNavegador(i);
				i++;
				sleep(5000);
				List<WebElement> botones = driver
						.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-binding.ng-scope"));
				for (WebElement UnB : botones) {
					if (UnB.getText().equals("Complete")) {
						UnB.click();
						sleep(2000);
						UnB.click();
						break;
					}
				}
				sleep(10000);
				cambiarVentanaNavegador(0);
				sleep(1000);
				// closeAllOtherTabs();
				sleep(15000);
				break;
			}
			cajas = driver.findElements(By.cssSelector(".item-label-container.item-header.item-failed"));
			// cajas.addAll(driver.findElements(By.cssSelector(".item-label-container.item-header.item-fatally-failed")));

		}
		closeAllOtherTabs();
		sleep(5000);
		driver.findElement(By.className("submit-button")).click();
		sleep(6000);
		cambiarVentanaNavegador(1);
		sleep(5000);
		closeAllOtherTabs();
	}

	/**
	 * Crea una orden desde la vista de todas las ordenes.
	 */
	public void crearCuentaOM(String Cuenta) {
		sleep(1000);
		List<WebElement> buscarCuenta = driver.findElements(By.className("lookupIcon"));
		for (WebElement op : buscarCuenta) {
			if (op.getAttribute("alt").equalsIgnoreCase("Account Name Lookup (New Window)")) {
				op.click();
			}
		}
		sleep(3000);
		cambiarVentanaNavegador(1);
		sleep(1000);

		driver.switchTo().frame(driver.findElement(By.id("searchFrame")));
		driver.findElement(By.name("new")).click();
		sleep(2000);
		driver.switchTo().defaultContent();
		sleep(200);
		driver.switchTo().frame(driver.findElement(By.id("resultsFrame")));

		WebElement inputNombreCuenta = driver
				.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[2]/table/tbody/tr[1]/td[2]/div/input"));
		inputNombreCuenta.click();
		inputNombreCuenta.clear();
		inputNombreCuenta.sendKeys(Cuenta);
		driver.findElements(By.name("save")).get(1).click();
		cambiarVentanaNavegador(0);
		driver.switchTo().defaultContent();

	}

	/**
	 * Crea una orden desde la vista de todas las ordenes.
	 */
	public void buscarCuentaOMenOrden(String Cuenta) {
		sleep(1000);
		List<WebElement> buscarCuenta = driver.findElements(By.className("lookupIcon"));
		for (WebElement op : buscarCuenta) {
			if (op.getAttribute("alt").equalsIgnoreCase("Account Name Lookup (New Window)")) {
				op.click();
			}
		}
		sleep(3000);
		cambiarVentanaNavegador(1);
		sleep(1000);

		driver.switchTo().frame(driver.findElement(By.id("searchFrame")));
		driver.findElement(By.id("lksrch")).sendKeys(Cuenta);
		driver.findElement(By.name("go")).click();
		sleep(4000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("resultsFrame")));
		int i = -1;
		List<WebElement> cuentas = driver.findElement(By.tagName("table")).findElements(By.tagName("tr"));
		cuentas.remove(0);
		for (WebElement UnaC : cuentas) {
			if (UnaC.findElement(By.tagName("th")).getText().equals(Cuenta)) {
				i++;
			} else {
				System.out.println(UnaC.findElement(By.tagName("th")).getText());
				break;
			}
		}
		cuentas.get(i).findElement(By.tagName("th")).click();
		sleep(5000);
		cambiarVentanaNavegador(0);
		driver.switchTo().defaultContent();

	}

	/**
	 * Crea una vista desde la ventana "Ordenes"
	 * 
	 * @param
	 * @return
	 */
	public boolean crearVistaOM(String nombreVista, String nombreCuenta) {
		clickTab("Order_Tab");
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

	/* Obtiene un elemento aleatorio de una lista de WebElements */
	public WebElement getRandomElementFromList(List<WebElement> lista) {

		int listaSize = lista.size();
		Random rand = new Random();
		WebElement randomWebElement = lista.get(rand.nextInt(listaSize));
		return randomWebElement;

	}

	/* Genera un String de numeros al azar de n digitos */
	public String getRandomNumber(int digitos) {
		Random rand = new Random();
		StringBuilder number = new StringBuilder(digitos);
		// Asegura que el primer digito no sea 0
		number.append((char) ('1' + rand.nextInt(9)));
		// Genera el resto de los digitos
		for (int i = 0; i < (digitos - 1); i++) {
			number.append((char) ('0' + rand.nextInt(10)));
		}
		return number.toString();
	}

	/* Cierra todas las ventanas que no sean la principal */
	public void closeAllOtherTabs() {
		String mainWindowHandle = driver.getWindowHandle();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		for (String t : tabs) {
			if (!t.equals(mainWindowHandle)) {
				driver.switchTo().window(t);
				driver.close();
			}
		}
		driver.switchTo().window(mainWindowHandle);
	}

	/* Cambia a la nueva ventana/tab/popup -- Recibe la ventana actual */
	public void switchToPopup(String currentWindowHandle) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		for (String t : tabs) {
			if (!t.equals(currentWindowHandle)) {
				driver.switchTo().window(t);
				break;
			}
		}
	}

	/* Selecciona una Vista por Texto Visible */
	public void selectVistaByVisibleText(String vista) {
		Select vistaSelect = new Select(driver.findElement(By.name("fcf")));
		vistaSelect.selectByVisibleText(vista);
		try {
			driver.findElement(By.name("go")).click();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("Go Button not found exception");
		}
	}

	/* Elimina la Vista Seleccionada */
	public void eliminarVista() {
		try {
			driver.findElement(By.name("delID")).click();
			Alert confirmDelete = driver.switchTo().alert();
			confirmDelete.accept();
			// sleep(5000);
		} catch (UnhandledAlertException f) {
			try {
				// Aceptar Alerta para Borrar Lista
				Alert confirmDelete = driver.switchTo().alert();
				confirmDelete.accept();
			} catch (NoAlertPresentException e) {
				e.printStackTrace();
			}
		}
	}

	public void agregarGestion(String Gestion) {
		boolean esta = false;
		List<WebElement> campos = driver.findElement(By.className("detailList")).findElements(By.tagName("td"));
		for (WebElement UnC : campos) {
			if (esta == true) {
				Actions action = new Actions(driver);
				action.moveToElement(UnC).doubleClick().build().perform();
				sleep(2000);
				Select gestiones = new Select(driver.findElement(By.tagName("select")));
				gestiones.selectByVisibleText(Gestion);
				break;
			}
			if (UnC.getText().equalsIgnoreCase("gestion")) {
				esta = true;
			}
		}
		sleep(2000);
		driver.findElement(By.id("topButtonRow")).findElement(By.tagName("input")).click();
		sleep(3000);
	}

	public void irAChangeToOrder() {
		Accounts accountPage = new Accounts(driver);
		driver.switchTo().defaultContent();
		driver.switchTo()
				.frame(accountPage.getFrameForElement(driver, By.cssSelector(".panel.panel-default.panel-assets")));
		List<WebElement> assets = driver.findElement(By.cssSelector(".panel.panel-default.panel-assets"))
				.findElements(By.cssSelector(".root-asset.ng-scope"));
		assets.get(assets.size() - 1).findElement(By.className("p-check")).click();
		driver.findElement(By.className("asset-action")).findElements(By.cssSelector(".btn.btn-default")).get(1)
				.click();
		;

	}

	public List<String> traerColumna(WebElement wBody, int iColumn) {
		List<WebElement> wRows = wBody.findElements(By.tagName("tr"));
		List<String> sColumn = new ArrayList<String>();
		for (WebElement wAux : wRows) {
			List<WebElement> wTd = wAux.findElements(By.tagName("td"));
			sColumn.add(wTd.get(iColumn).getText());
		}

		return sColumn;
	}

	public Date fechaAvanzada() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, +1);
		cal.add(Calendar.DATE, +1);
		date = cal.getTime();
		return (date);

	}

	public String getFechaAvanzadaFormateada_MM_dd_yyyy() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String formattedDate = simpleDateFormat.format(fechaAvanzada());
		return formattedDate;
	}

	// Ir hasta SIM config
	public void goToSimConfig() {
		// Plan
		driver.findElement(
				By.xpath("//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[3]/div[1]/div[1]/button"))
				.click();
		// Sim
		sleep(1000);
		driver.findElement(By.xpath(
				"//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[4]/div[2]/div/ng-include/div/div[2]/ng-include/div/div[1]/div/div[2]/div[11]/div[2]/button"))
				.click();
		// Sim Config
		sleep(1000);
		driver.findElement(By.xpath(
				"//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[4]/div[2]/div/ng-include/div/div[2]/ng-include/div/div[1]/div/div[2]/div[11]/div[2]/div/ul/li[3]/a/span"))
				.click();

	}

	// Edita el campo de Gestión
	public void setGestionField(String gestion) {
		WebDriverWait wait;
		wait = new WebDriverWait(driver, 20);
		WebElement editOrderButton = wait
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("edit"))));
		editOrderButton.click();
		WebElement gestionElement = wait
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("00Nc0000002IvyM"))));
		Select gestionSelect = new Select(gestionElement);
		gestionSelect.selectByVisibleText(gestion);
		driver.findElement(By.name("save")).click();
	}

}
