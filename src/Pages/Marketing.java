package Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Tests.TestBase;

public class Marketing extends CustomerCare {
	

	
	//final WebDriver driver;
	
	//Constructor
	public Marketing(WebDriver driver){
		super(driver);
	}
	
	public void clubPersonal (String sAltaBajaModificacion) {
		waitForVisibilityOfElementLocated(By.cssSelector(".slds-grid.slds-wrap.via-slds-action-grid-card"));
		WebElement wMenuABM = driver.findElement(By.cssSelector(".slds-grid.slds-wrap.via-slds-action-grid-card"));
		List<WebElement> lMenuesABM = wMenuABM.findElements(By.cssSelector(".slds-size--1-of-2.slds-x-small-size--1-of-3.slds-medium-size--1-of-4.slds-large-size--1-of-6.slds-align--absolute-center.slds-m-bottom--xx-small.slds-m-top--xx-small.slds-p-left--xx-small.slds-p-right--xx-small.slds-grid"));
		
		switch (sAltaBajaModificacion.toLowerCase()) {
			case "alta":
				lMenuesABM.get(0).click();
				sleep(3000);
				cambiarAFrameActivo();
				break;
			case "baja":
				lMenuesABM.get(1).click();
				try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
				BasePage cambioFrame=new BasePage();
				driver.switchTo().defaultContent();
				driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.cssSelector(".slds-page-header.vlc-slds-page--header.ng-scope")));
				break;
			case "modificacion":
				lMenuesABM.get(2).click();
				break;
			default:
				System.out.println("Las opciones validas solo son 'alta', 'baja', y 'modificacion'.");
		}
	}
	
	public void closeActiveTab () {
		CustomerCare cCC = new CustomerCare(driver);
		WebElement wCloseTab = cCC.obtenerPestañaActiva();
		Actions aAction = new Actions(driver);
		WebElement wClose = wCloseTab.findElement(By.className("x-tab-strip-close"));
		aAction.moveToElement(wClose).perform();
		wClose.click();
	}
	
	/*Datos login Marketing:s
		Username: usit@telecom.sit
		Password: pruebas09
	*/
	
	public void irAGestionMarketing() {
		buscarGestion("Club Personal");
		if (gestionesEncontradas.isEmpty()) {
			System.err.println("ERROR: No existe la gestión 'Club Personal'");
			Assert.assertFalse(gestionesEncontradas.isEmpty());
		}
		gestionesEncontradas.get(1).click();
		TestBase.sleep(3000);
		cambiarAFrameActivo();
	}
	
	public void buscarGestion(String gest) {
		CustomerCare cCC = new CustomerCare(driver);
		cCC.panelDerecho();
		buscadorGestiones.clear();
		buscadorGestiones.sendKeys(gest);
	}
	
	private void cambiarAFrameActivo() {
		driver.switchTo().defaultContent();
		for (WebElement t : panelesCentrales) {
			if (!t.getAttribute("class").contains("x-hide-display")) {
				driver.switchTo().frame(t.findElement(By.cssSelector("div iframe")));
			}
		}
	}
	
	public List<WebElement> traerColumnaElement(WebElement wBody, int iColumnas, int iColumna) {
		//wBody = WebElement del cuerpo completo del cuadro
		//iColumnas = cantidad de columnas
		//iColumna = columna requerida
		WebElement wSubBody = wBody.findElement(By.tagName("tbody"));
		List<WebElement> wRows = wSubBody.findElements(By.tagName("tr"));
		List<WebElement> wElements =   new ArrayList<WebElement>();
		for (int i = 0; i < wRows.size(); i++) {
			wElements.clear();
			wElements = wRows.get(i).findElements(By.tagName("td"));
			if (wElements.size() < iColumnas) {
				wRows.remove(i);
			}
		}
		List<WebElement> wElementsExtrated =  new ArrayList<WebElement>();
		for (WebElement wAux:wRows) {
			if (!wAux.getText().isEmpty()) {
				List<WebElement> wColumns = wAux.findElements(By.tagName("td"));
				wElementsExtrated.add(wColumns.get(iColumna - 1));
			}
		}
		
		return wElementsExtrated;
	}
	
	public List<WebElement> obtenerBotonesClubPersonal() {
		return driver.findElements(By.cssSelector(".slds-align--absolute-center.slds-m-bottom--xx-small"));
	}
	
	public void seleccionarFecha (int iCantidadColumnas, int iColumna, int iDias, String sId, String sTiempo) {
		List <WebElement> wBodyComplete = driver.findElements(By.cssSelector(".slds-media.slds-media--center.slds-has-flexi-truncate"));
		WebElement wBody = wBodyComplete.get(5);
		List <WebElement> wColumna = traerColumnaElement(wBody, iCantidadColumnas, iColumna);
		WebElement wFechaDesde = wColumna.get(0).findElement(By.id(sId));
		wFechaDesde.click();
		wColumna = traerColumnaElement(wBody, iCantidadColumnas, iColumna);
		wFechaDesde = wColumna.get(0);
		List<WebElement> wDias = wFechaDesde.findElements(By.tagName("td"));
		switch (sTiempo.toLowerCase()) {
			case "futuro":
				for(int i = 0; i < wDias.size(); i++) {
					if (wDias.get(i).getAttribute("class").equals("slds-is-today")) {
						wDias.get(i + iDias).findElement(By.tagName("span")).click();
						break;
					}
				}
				break;
			case "pasado":
				for(int i = 0; i < wDias.size(); i++) {
					if (wDias.get(i).getAttribute("class").equals("slds-is-today")) {
						wDias.get(i - iDias).findElement(By.tagName("span")).click();
						break;
					}
				}
			default:
				System.out.println("Palabra incorrecta. Usar 'Futuro' o 'Pasado'");
				break;
		}
	}
	
	public void cambioCuenta(String sVista, String sCliente) {
		TestBase tTB = new TestBase();
		CustomerCare cCC = new CustomerCare(driver);
		cCC.cerrarTodasLasPestañas();
		tTB.goToLeftPanel(driver, "Cuentas");
		WebElement frame0 = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(frame0);
		tTB.waitFor(driver, (By.name("fcf")));
		Select field = new Select(driver.findElement(By.name("fcf")));
		field.selectByVisibleText(sVista);
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement wBody = driver.findElement(By.className("x-grid3-body"));
		List<WebElement> wAccountName = wBody.findElements(By.cssSelector(".x-grid3-col.x-grid3-cell.x-grid3-td-ACCOUNT_NAME"));
		
		for (WebElement wAux:wAccountName) {
			WebElement wContenido = wAux.findElement(By.tagName("span"));
			
			if (wContenido.getText().toLowerCase().equals(sCliente.toLowerCase())) {
				wAux.click();
				break;
			}
		}
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BasePage cambioFrame=new BasePage();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.cssSelector(".slds-input.actionSearch.ng-pristine.ng-untouched.ng-valid.ng-empty")));
		driver.findElement(By.cssSelector(".slds-input.actionSearch.ng-pristine.ng-untouched.ng-valid.ng-empty")).clear();
		driver.findElement(By.cssSelector(".slds-input.actionSearch.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys("Club Personal");
		List<WebElement> wGestiones = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.slds-truncate"));
		
		for (WebElement wAux2:wGestiones) {
			WebElement wContenido = wAux2.findElement(By.cssSelector(".slds-text-body_regular.ta-button-font"));
			if (wContenido.getText().toLowerCase().equals("club personal")) {
				wAux2.click();
				break;
			}
		}
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.cssSelector(".slds-panel__section.slds-p-around--small")));
	}
	
	public Boolean verificarMensajeDeErrorCuentaFraude() {
		waitForVisibilityOfElementLocated(By.xpath("//ng-form[@id='TxtError']"));
		String msg = driver.findElement(By.xpath("//ng-form[@id='TxtError']")).getText();
		return msg.contains("Para continuar es necesario regularizar su estado de cuenta");
	}
	
	public String obtenerNumeroCasoCuentaFraude() {
		String msg = driver.findElement(By.xpath("//ng-form[@id='TxtError']")).getText();
		int i = 0;
		while(msg.charAt(i++) != '0') {	}
		return msg.substring(i-1, msg.length());
	}
	
}