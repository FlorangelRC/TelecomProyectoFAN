package Pages;

import static org.testng.Assert.assertTrue;

import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import Tests.SalesNominaciones;

public class OM {

	static WebDriver driver;
	static FluentWait<WebDriver> fluentWait;

	// *********************************CONSTRUCTOR******************************************************//

	public OM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.withTimeout(30, TimeUnit.SECONDS)
			.pollingEvery(5, TimeUnit.SECONDS)
			.ignoring(org.openqa.selenium.NoSuchElementException.class)
			.ignoring(org.openqa.selenium.ElementNotVisibleException.class);
		
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
	
	
	@FindBy(css=".form-control.btn.btn-primary.ng-binding")
	private WebElement Next;

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
		cajas.addAll(driver.findElements(By.cssSelector(".item-label-container.item-header.item-fatally-failed")));
		cajas.addAll(driver.findElements(By.cssSelector(".item-label-container.item-header.item-running")));
		int i = 1;
		while (cajas.size() > 0) {
			for (WebElement UnaC : cajas) {
				UnaC.click();
				sleep(5000);
				cambiarVentanaNavegador(i);
				//i++;
				sleep(5000);
				List<WebElement> botones = driver
						.findElements(By.cssSelector(".slds-button.slds-button--neutral.ng-scope"));
				for (WebElement UnB : botones) {
					if (UnB.getText().equals("Complete")) {
						UnB.click();
						sleep(4000);
						System.out.println("Hizo click");
						break;
					}
				}
				sleep(10000);
				cambiarVentanaNavegador(0);
				sleep(10000);
				closeAllOtherTabs();
				sleep(35000);
				break;
			}
			cajas = driver.findElements(By.cssSelector(".item-label-container.item-header.item-failed"));
			cajas.addAll(driver.findElements(By.cssSelector(".item-label-container.item-header.item-fatally-failed")));
			cajas.addAll(driver.findElements(By.cssSelector(".item-label-container.item-header.item-running")));
			
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
			//System.out.println("Go Button not found exception");
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
		driver.switchTo().frame(accountPage.getFrameForElement(driver, By.cssSelector(".panel.panel-default.panel-assets")));
		List<WebElement> assets = driver.findElement(By.cssSelector(".panel.panel-default.panel-assets")).findElements(By.cssSelector(".root-asset.ng-scope"));
		assets.get(assets.size() - 1).findElement(By.className("p-check")).click();
		driver.findElement(By.className("asset-action")).findElements(By.cssSelector(".btn.btn-default")).get(1).click();
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
        return(date);
       		
	}
	public void fechaAvanzada2() {
		//Accounts accountPage = new Accounts(driver);
		/*DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		driver.findElement(By.id("RequestDate")).sendKeys(dateFormat.format(om.fechaAvanzada()));*/
		driver.findElement(By.id("RequestDate")).sendKeys("09-19-2019");
		driver.findElement(By.cssSelector(".form-control.btn.btn-primary.ng-binding")).click();
		sleep(15000);
		}


	/*public String getFechaAvanzadaFormateada_MM_dd_yyyy() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String formattedDate = simpleDateFormat.format(fechaAvanzada());
		return formattedDate;
	}
*/
	// Ir hasta SIM config
	public void goToSimConfig() {
		// Plan
		WebElement plan = fluentWait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[3]/div[1]/div[1]/button")));
		plan.click();
		// Sim
		sleep(1000);
		driver.findElement(By.xpath(
				"//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[4]/div[2]/div/ng-include/div/div[2]/ng-include/div/div[1]/div/div[2]/div[11]/div[2]/button")).click();
		// Sim Config
		sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[4]/div[2]/div/ng-include/div/div[2]/ng-include/div/div[1]/div/div[2]/div[11]/div[2]/div/ul/li[3]/a/span")).click();}

	

	// Cambia el n�mero
	public void cambiarNumero(String numero) {
		WebElement cambiarNumero;
		try {
			cambiarNumero = driver.findElement(
					By.name("productconfig_field_3_0"));
					
					
		} catch (org.openqa.selenium.NoSuchElementException e) {
			cambiarNumero = driver.findElement(By.xpath("//*[@id=\"js-cpq-product-cart-config-form\"]/div[1]/div/form/div[2]/div[1]/input"));
		};
		cambiarNumero.clear();
		cambiarNumero.sendKeys(numero);
		// Close Sim Input
		sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div/ng-include/div/div[2]/div[2]/div[3]/div/div/ng-include/div/div[1]/div/button")).click();
	}

	// Edita el campo de Gesti�	
	public void setGestionField(String gestion) {
		WebDriverWait wait;
		wait = new WebDriverWait(driver, 20);
		WebElement editOrderButton = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("edit"))));
		editOrderButton.click();
		WebElement gestionElement = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("00Nc0000002IvyM"))));
		Select gestionSelect = new Select(gestionElement);
		gestionSelect.selectByVisibleText(gestion);
		driver.findElement(By.name("save")).click();
	}
	
public void deleteOrdersNoActivated(String Vista) {
		
		Select allOrder=new Select(driver.findElement(By.id("fcf")));
		allOrder.selectByVisibleText(Vista);
		sleep(1000);
		try {driver.findElement(By.name("go")).click();}catch(org.openqa.selenium.NoSuchElementException e) {}
		sleep(3000);
		
		//Solo para listar la cantidad de Ordenes que hay
		List<WebElement> listadoDeOrdenes=driver.findElement(By.className("x-panel-bwrap")).findElement(By.className("x-grid3-body")).findElements(By.className("x-grid3-row"));
		listadoDeOrdenes.add(driver.findElement(By.className("x-panel-bwrap")).findElement(By.className("x-grid3-body")).findElement(By.cssSelector(".x-grid3-row.x-grid3-row-first")));
		listadoDeOrdenes.add(driver.findElement(By.className("x-panel-bwrap")).findElement(By.className("x-grid3-body")).findElement(By.cssSelector(".x-grid3-row.x-grid3-row-last")));
		
		//System.out.println("aqui: "+listadoDeOrdenes.get(0).getText());
		int i=0;
		System.out.println("Cantidad de Ordenes: "+listadoDeOrdenes.size());
		while(i<=listadoDeOrdenes.size()-2){ //OJO Aca con el 2 se hizo para dos elementos fantasmas.
			System.out.println("ejecucion: "+i);
			WebElement ordenes=driver.findElement(By.className("x-panel-bwrap")).findElement(By.className("x-grid3-body")).findElements(By.tagName("div")).get(0);
			try {
			//Eliminar
			ordenes.findElement(By.xpath("//table/tbody/tr/td[2]/div/a[2]")).click();
			sleep(1000);
			}catch(org.openqa.selenium.NoSuchElementException e) {}
			try {
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
			}catch(org.openqa.selenium.NoAlertPresentException e) {}
		i++;
		sleep(3000);
		}

		
	}
	
	public WebElement getNext() {
		return Next;
	}

	/**
	 * Metodo para cambiar el nombre de la cuenta en todos los servicios de un asset, usado para la gestion cambio de titularidad
	 * Recibe como parametro el nombre de la cuenta a la que se va a cambiar el asset.
	 */
	public void cambioDeCuentaServicios(String Cuenta) {
		
		List<WebElement> listadoDeServicios=driver.findElement(By.className("pbBody")).findElement(By.className("list")).findElements(By.tagName("tr"));
		if(listadoDeServicios.get(0).getAttribute("class").equalsIgnoreCase("headerRow"))
			listadoDeServicios.remove(0);
		int i=1;
		//System.out.println("aqui: "+listadoDeServicios.get(0).getText());
		while(i<listadoDeServicios.size()+1){
			driver.switchTo().defaultContent();
			sleep(1000);
			
			WebElement servicio=driver.findElement(By.className("pbBody")).findElement(By.className("list")).findElements(By.tagName("tr")).get(i);
			servicio.findElement(By.className("actionColumn")).findElements(By.tagName("a")).get(0).click();
			sleep(3000);
			
			Select action=new Select(driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[3]/table/tbody/tr[6]/td[2]")).findElement(By.tagName("select")));
			action.selectByVisibleText("Change");
			
			WebElement billingAccount=driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[3]/table/tbody/tr[11]/td[2]/span")).findElement(By.tagName("input"));
			billingAccount.click();
			billingAccount.clear();
			billingAccount.sendKeys(Cuenta);
			sleep(300);
			
			//FALTA SERVICE ACCOUNT
			WebElement serviceAccount=driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[3]/table/tbody/tr[35]/td[4]/span")).findElement(By.tagName("input"));
			serviceAccount.click();
			serviceAccount.clear();
			serviceAccount.sendKeys(Cuenta);
			
			sleep(500);
			//Guardar
			driver.findElement(By.name("save")).click();
			sleep(3000);
			i++;
		}
		
	}
	
	/**
	 * Hace click en editar orden, y cambia los valores, cuenta y gestion. Usada en Cambio de titularidad.
	 * 
	 * @param Cuenta
	 * @param Gestion
	 */
	public void cambiarCuentaYGestionEnOrden(String Cuenta, String Gestion) {
		//Click en editar
		driver.findElement(By.name("edit")).click();
		sleep(4000);
		WebElement accountName=driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[3]/table/tbody/tr[3]/td[2]/div/span")).findElement(By.tagName("input"));
		accountName.click();
		accountName.clear();
		accountName.sendKeys(Cuenta);
		sleep(1000);
		Select gestion=new Select(driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[3]/table/tbody/tr[14]/td[4]/span")).findElement(By.tagName("select")));
		gestion.selectByValue(Gestion);
		//Guardamos
		driver.findElement(By.name("save")).click();
		sleep(4000);
	}
	public void Cambio_De_SimCard(String fecha) throws InterruptedException {
		sleep(5000);
		OM pageOm=new OM(driver);
		OMQPage OM=new OMQPage (driver);
		//Mientras, seleccion de vista
		pageOm.selectVistaByVisibleText("LineasFlor");
		sleep(3000);
		//Selecciona la primera cuenta de la lista en la vista seleccionada
		WebElement primeraCuenta=driver.findElement(By.cssSelector(".x-grid3-col.x-grid3-cell.x-grid3-td-SALES_ACCOUNT_NAME"));
		primeraCuenta.findElement(By.tagName("div")).findElement(By.tagName("a")).click();
		sleep(5000);
		pageOm.irAChangeToOrder();	
		sleep(20000);
		Accounts accountPage = new Accounts(driver);
		driver.switchTo().defaultContent(); 
		sleep(4000);
        /*DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		driver.findElement(By.id("RequestDate")).sendKeys(dateFormat.format(pageOm.fechaAvanzada()));*/
		driver.findElement(By.id("RequestDate")).sendKeys(fecha);
		driver.findElement(By.cssSelector(".form-control.btn.btn-primary.ng-binding")).click();
		sleep(12000);
		OM.SimCard();
		driver.findElement(By.id("-import-btn")).click();
		sleep(8000);
		pageOm.agregarGestion("Cambio de SIM");
		sleep(5000);
		/*driver.findElement(By.name("ta_submit_order")).click();
		sleep(35000);
		pageOm.cambiarVentanaNavegador(1);
		sleep(2000);
		driver.findElement(By.id("idlist")).click();
		sleep(5000);
		pageOm.cambiarVentanaNavegador(0);
		sleep(12000);*/
	}
	
	
	
	
	public void Cambio_De_SimCard_Por_Siniestro(String Vista) throws InterruptedException {
		//TS_CRM_OM_Gestion_Alta_De_Linea();
		OM pageOm=new OM(driver);
		OMQPage OM=new OMQPage (driver);
		//Mientras, seleccion de vista
		/*pageOm.selectVistaByVisibleText(Vista);
		sleep(3000);
		//Selecciona la primera cuenta de la lista en la vista seleccionada
		WebElement primeraCuenta=driver.findElement(By.cssSelector(".x-grid3-col.x-grid3-cell.x-grid3-td-SALES_ACCOUNT_NAME"));
		primeraCuenta.findElement(By.tagName("div")).findElement(By.tagName("a")).click();*/
		sleep(5000);
		irAChangeToOrder();	
		sleep(10000);
		Accounts accountPage = new Accounts(driver);
		driver.switchTo().defaultContent(); 
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		driver.findElement(By.id("RequestDate")).sendKeys(dateFormat.format(pageOm.fechaAvanzada()));
		//driver.findElement(By.id("RequestDate")).sendKeys("07-14-2018");
		driver.findElement(By.cssSelector(".form-control.btn.btn-primary.ng-binding")).click();
		sleep(18000);
		driver.findElement(By.id("-import-btn")).click();
		sleep(8000);
		pageOm.agregarGestion("Suspension");
		sleep(5000);
		SuspenderProductos();
		sleep(5000);
		driver.findElement(By.id("accid_ileinner")).findElement(By.tagName("a")).click();
		sleep(12000);
		irAChangeToOrder();
		OM.SimCard();
		driver.findElement(By.id("-import-btn")).click();
		sleep(8000);
		pageOm.agregarGestion("Cambio de SIM por siniestro");
		sleep(5000);
		driver.findElement(By.name("ta_submit_order")).click();
		sleep(45000);
		try {
			pageOm.cambiarVentanaNavegador(1);
			sleep(2000);
			driver.findElement(By.id("idlist")).click();
			sleep(5000);
			pageOm.cambiarVentanaNavegador(0);
		}catch(java.lang.IndexOutOfBoundsException ex1) {}
		sleep(12000);
		pageOm.completarFlujoOrquestacion();
		sleep(5000);
	}
	
	public void Gestion_Alta_De_Linea(String Cuenta, String Plan) throws InterruptedException {
		OM pageOm=new OM(driver);
		OMQPage OM=new OMQPage (driver);
		pageOm.crearOrden(Cuenta);
		assertTrue(driver.findElement(By.cssSelector(".noSecondHeader.pageType")).isDisplayed());
		pageOm.agregarGestion("Venta");
		sleep(2000);
		OM.getCPQ().click();
		sleep(5000);
		OM.colocarPlan(Plan);
		OM.configuracion();
		sleep(5000);
		driver.findElement(By.name("ta_submit_order")).click();
		sleep(45000);
		pageOm.cambiarVentanaNavegador(1);
		sleep(2000);
		driver.findElement(By.id("idlist")).click();
		sleep(5000);
		pageOm.cambiarVentanaNavegador(0);
		sleep(12000);
		pageOm.completarFlujoOrquestacion();
		sleep(5000);
		driver.findElement(By.id("accid_ileinner")).findElement(By.tagName("a")).click();
		sleep(10000);
		//pageOm.irAChangeToOrder();
		
	}
	
	    public void Gestion_Cambio_de_Numero(String Vista, String Fecha) throws InterruptedException{ 
	      Date date = new Date(); 
	      OM om = new OM(driver); 
	    //Mientras, seleccion de vista 
	     /* Select allOrder=new Select(driver.findElement(By.id("fcf"))); 
	      allOrder.selectByVisibleText(Vista); 
	      sleep(1000); 
	      try {driver.findElement(By.name("go")).click();}catch(org.openqa.selenium.NoSuchElementException e) {} 
	      sleep(3000); 
	    //Selecciona la primera cuenta de la lista en la vista seleccionada 
	      WebElement primeraCuenta=driver.findElement(By.cssSelector(".x-grid3-col.x-grid3-cell.x-grid3-td-SALES_ACCOUNT_NAME")); 
	      primeraCuenta.findElement(By.tagName("div")).findElement(By.tagName("a")).click(); */
	      sleep(8000); 
	    //Seleccion del ultimo Asset 
	      om.irAChangeToOrder();   
	      sleep(8000); 
	    //Ingreso de fecha avanzada 
	      Accounts accountPage = new Accounts(driver); 
	      /*DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy"); 
	      driver.findElement(By.id("RequestDate")).sendKeys(dateFormat.format(om.fechaAvanzada()));*/ 
	      driver.findElement(By.id("RequestDate")).sendKeys(Fecha); 
	      driver.findElement(By.cssSelector(".form-control.btn.btn-primary.ng-binding")).click();
	      sleep(35000); 
	    //SIM 
	      driver.findElement(By.cssSelector(".slds-button.cpq-item-has-children")).click(); 
	      sleep(3000); 
	      driver.switchTo().defaultContent(); 
	      driver.findElement(By.xpath(".//*[@id='tab-default-1']/div[1]/ng-include/div/div/div/div[4]/div[2]/div/ng-include/div/div[2]/ng-include/div/div[1]/div/div[2]/div[11]")).click(); 
	      sleep(3000); 
	      driver.findElement(By.xpath(".//*[@id='tab-default-1']/div[1]/ng-include/div/div/div/div[4]/div[2]/div/ng-include/div/div[2]/ng-include/div/div[1]/div/div[2]/div[11]/div[2]/div/ul/li[3]/a")).click(); 
	      sleep(5000); 
	      ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.className("slds-section")).getLocation().y+" )"); 
	      WebElement msi = driver.findElement(By.xpath("//*[@id='js-cpq-product-cart-config-form']/div[1]/div/form/div[18]/div[1]/input")); 
	      Random r = new Random(); 
	      msi.clear(); 
	      msi.sendKeys("11" + r.nextInt(200000000) ); 
	      msi.submit(); 
	      sleep(30000); 
	      driver.findElement(By.id("-import-btn")).click(); 
	      sleep(5000); 
	    //Gestion 
	      om.agregarGestion("Cambio de n\u00famero"); 
	      driver.findElements(By.id("topButtonRow")).get(0); 
	      sleep(7000); 
	      driver.findElement(By.name("ta_submit_order")).click(); 
	      sleep(45000);
			try {
				om.cambiarVentanaNavegador(1);
				sleep(2000);
				driver.findElement(By.id("idlist")).click();
				sleep(5000);
				om.cambiarVentanaNavegador(0);
			}catch(java.lang.IndexOutOfBoundsException ex1) {}
			sleep(12000);
			om.completarFlujoOrquestacion();
			sleep(5000);
	       
	      }
	    
		// Metodo para cuando olvidamos cambiar la fecha para ejecutar gestiones
	    // Avisa si se ingres� una fecha incorrecta y da unos segundos para cambiarla y continuar el test
	    // ��ATENCION!! No olvidar quitarlo del codigo una vez que funcione
		public void checkFutureDateRestriction() {
			try {
				String futureDateText = driver.findElement(By.cssSelector(".col-md-12.col-sm-12.vlc-header")).getText();
				if (futureDateText
						.contains("Can not create the Order as there is already an Order with Request Date greater than")) {
					System.out.println("Invalid Date. Please select a valid date to continue. Don't forget to update your code =)");
					Toolkit.getDefaultToolkit().beep();
					sleep(30000);
				}

			} catch (NoSuchElementException e) {
				System.out.println("Date OK");
			};
		}
		
		public boolean ordenCajasVerdes(String primeraCaja, String segundaCaja, String terceraCaja) {
			boolean ordenCajas = false;
			Integer a = 0, b = 0, c = 0;
			List <WebElement> cajasVerdes = driver.findElements(By.cssSelector(".item-header.item-completed"));
			for (WebElement x : cajasVerdes) {
				if (x.getText().equalsIgnoreCase(primeraCaja)) {
					a = x.getLocation().getX();
				}
			}
			for (WebElement x : cajasVerdes) {
				if (x.getText().equalsIgnoreCase(segundaCaja)) {
					b = x.getLocation().getX();
				}
			}
			for (WebElement x : cajasVerdes) {
				if (x.getText().equalsIgnoreCase(terceraCaja)) {
					c = x.getLocation().getX();
				}
			}	
			if (a < b && b < c) {
				ordenCajas = true;
			}
			return ordenCajas;
		}
		
		public void Gestion_Nominacion(String sCuenta, String sDni, String sLinea) throws Exception {
			SalesBase sb = new SalesBase(driver);
			HomeBase homePage = new HomeBase(driver);
			try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    String a = driver.findElement(By.id("tsidLabel")).getText(); 
		    if (a.contains("Ventas")){}
		    else {
		    	homePage.switchAppsMenu();
		    	try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    	homePage.selectAppFromMenuByName("Ventas");
		    	try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}            
		    }
		    driver.findElement(By.xpath("//a[@href=\'https://crm--sit--c.cs14.visual.force.com/apex/taClientSearch']")).click();		
		    try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}    
		    String NyA = sCuenta;
			sb.BuscarAvanzada(NyA.split(" ")[0], NyA.split(" ")[1], "", "", "");
			WebElement cli = driver.findElement(By.id("tab-scoped-1"));
			if (cli.findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElement(By.tagName("div")).getText().equals("Cliente Wholesale")) {
				cli.findElement(By.tagName("tbody")).findElement(By.tagName("tr")).click();
			}
			sleep(3000);
			WebElement cua = driver.findElement(By.id("tab-scoped-1")).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(36).findElements(By.tagName("td")).get(6).findElement(By.tagName("svg"));
			System.out.println("1: "+driver.findElement(By.id("tab-scoped-1")).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(36).findElements(By.tagName("td")).get(1).getText());
			cua.click();
			sleep(13000);
			ContactSearch contact = new ContactSearch(driver);
			contact.searchContact2("DNI", sDni, sLinea);
			try {contact.ingresarMail("asdads@gmail.com", "si");}catch (org.openqa.selenium.ElementNotVisibleException ex1) {}
			contact.tipoValidacion("documento");
			contact.subirArchivo("C:\\Users\\florangel\\Downloads\\mapache.jpg", "si");
			BasePage bp = new BasePage(driver);
			bp.setSimpleDropdown(driver.findElement(By.id("ImpositiveCondition")), "IVA Consumidor Final");
			sb.Crear_DomicilioLegal("Buenos Aires", "Vicente Lopez", "falsa", "", "1000", "", "", "1549");
			//sleep(10000);
			//contact.subirformulario("C:\\Users\\florangel\\Downloads\\form.pdf", "si");
			sleep(35000);
			List <WebElement> element = driver.findElements(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope"));
			driver.findElement(By.id("FinishProcess_nextBtn")).click();
			sleep(10000);
			driver.switchTo().defaultContent();
			sleep(2000);
			OM pageOm=new OM(driver);
			pageOm.goToMenuOM();
			
			//click +
			sleep(5000);
			
			pageOm.clickMore();
			sleep(3000);
			
			//click en Ordenes
			pageOm.clickOnListTabs("Pedidos");
			sleep(5000);
			pageOm.primeraOrden();
		}
		
		public void Gestion_Alta_De_Servicio(String Vista, String Servicio) throws InterruptedException {
			//TS_CRM_OM_Gestion_Alta_De_Linea();
			OM pageOm=new OM(driver);
			OMQPage OM=new OMQPage (driver);
			//Mientras, seleccion de vista
			/*pageOm.selectVistaByVisibleText(Vista);
			sleep(3000);
			//Selecciona la primera cuenta de la lista en la vista seleccionada
			WebElement primeraCuenta=driver.findElement(By.cssSelector(".x-grid3-col.x-grid3-cell.x-grid3-td-SALES_ACCOUNT_NAME"));
			primeraCuenta.findElement(By.tagName("div")).findElement(By.tagName("a")).click();*/
			sleep(5000);
			pageOm.irAChangeToOrder();	
			sleep(10000);
			driver.switchTo().defaultContent(); 
	        /*DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			driver.findElement(By.id("RequestDate")).sendKeys(dateFormat.format(pageOm.fechaAvanzada()));*/
			driver.findElement(By.id("RequestDate")).sendKeys("07-15-2018");
			driver.findElement(By.cssSelector(".form-control.btn.btn-primary.ng-binding")).click();
			sleep(12000);
			Agregar_Servicio(Servicio);
			driver.findElement(By.id("-import-btn")).click();
			sleep(8000);
			pageOm.agregarGestion("Alta o Baja SVA");
			sleep(5000);
			driver.findElement(By.name("ta_submit_order")).click();
			sleep(45000);
			try {
				pageOm.cambiarVentanaNavegador(1);
				sleep(2000);
				driver.findElement(By.id("idlist")).click();
				sleep(5000);
				pageOm.cambiarVentanaNavegador(0);
			}catch(java.lang.IndexOutOfBoundsException ex1) {}
			sleep(12000);
			pageOm.completarFlujoOrquestacion();
			sleep(5000);
		}
		
		public void Agregar_Servicio(String Servicio) {
			sleep(5000);
			driver.findElement(By.cssSelector(".slds-button.cpq-item-has-children")).click();
			sleep(3000);
			List<WebElement> lista = driver.findElements(By.cssSelector(".slds-button__icon.slds-button__icon--.cpq-fix-slds-close-switch"));
			lista.remove(0);
			lista.remove(0);
			for(WebElement UnaL : lista) {
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.className("slds-section")).getLocation().y+" )");
				UnaL.click();
				sleep(1500);
			}
			//System.out.println(lista.size());
			sleep(3000);
			lista = driver.findElements(By.className("cpq-item-base-product"));
			 ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.className("slds-section")).getLocation().y+" )");
			for(WebElement UnaL : lista) {
				if(UnaL.findElement(By.className("cpq-item-child-product-name-wrapper")).findElement(By.className("cpq-product-name")).getText().equalsIgnoreCase(Servicio)) {
					UnaL.findElement(By.cssSelector(".slds-button.slds-button_neutral")).click();
					sleep(4000);
					break;
				}
			}
			 
		}
		
		public void SuspenderProductos() {
			int i=0;
			CustomerCare cc = new CustomerCare(driver);
			List<WebElement> Productos = new ArrayList<WebElement>();
			do {
				try {
					cc.obligarclick(driver.findElement(By.cssSelector(".listRelatedObject.orderBlock")).findElement(By.className("pShowMore")).findElement(By.tagName("a")));
					sleep(5000);
				}catch(Exception ex1) {}
				Productos = driver.findElement(By.cssSelector(".listRelatedObject.orderBlock")).findElement(By.className("pbBody")).findElements(By.tagName("tr"));
				Productos.remove(0);
				if(i<Productos.size()) {
					cc.obligarclick(Productos.get(i).findElement(By.tagName("th")).findElement(By.tagName("a")));
					i=i+1;
					System.out.println("i="+i);
					sleep(5000);
					boolean esta = false;
					List<WebElement> campos = driver.findElement(By.className("detailList")).findElements(By.tagName("td"));
					for (WebElement UnC : campos) {
						if (esta == true) {
							Actions action = new Actions(driver);
							action.moveToElement(UnC).doubleClick().build().perform();
							sleep(2000);
							Select gestiones = new Select(driver.findElement(By.tagName("select")));
							gestiones.selectByVisibleText("Suspend-Siniestro");
							break;
						}
						if (UnC.getText().equalsIgnoreCase("sub action")) {
							esta = true;
						}
					}
					esta = false;
					for (WebElement UnC : campos) {
						if (esta == true) {
							Actions action = new Actions(driver);
							action.moveToElement(UnC).doubleClick().build().perform();
							sleep(2000);
							UnC.findElement(By.tagName("input")).clear();
							UnC.findElement(By.tagName("input")).sendKeys("Suspend");
							break;
						}
						if (UnC.getText().equalsIgnoreCase("provisioning status")) {
							esta = true;
						}
						
					}
					sleep(2000);
					driver.findElement(By.id("topButtonRow")).findElement(By.tagName("input")).click();
					sleep(3000);
					cc.obligarclick(driver.findElement(By.id("Order_ileinner")).findElement(By.tagName("a")));
					sleep(4000);
					
					//SuspenderProductos(i++);
				}else {
					System.out.println("llegue aqui");
				}
			}while(i<Productos.size());
		}
	    
}