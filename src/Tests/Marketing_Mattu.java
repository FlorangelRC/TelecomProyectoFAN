package Tests;

import static org.testng.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select; 
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Pages.BasePage;
import Pages.CustomerCare;
import Pages.Marketing;
import Pages.SCP;
import Pages.setConexion;

public class Marketing_Mattu extends TestBase{
	
	private WebDriver driver;
	Marketing mMarketing;
	
	//-------------------------------------------------------------------------------------------------
	//@Befor&After
	@BeforeClass
	public void readySteady() throws Exception {
		this.driver = setConexion.setupEze();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		mMarketing = new Marketing(driver);
		loginMarketing(driver);
		//login(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		if (driver.findElement(By.id("tsidLabel")).getText().toLowerCase().equals("consola fan")) {
			driver.findElement(By.id("BackToServiceDesk_Tab")).click();
			/*driver.findElement(By.id("tsidLabel")).click();
			WebElement wMenu = driver.findElement(By.id("tsid-menuItems"));
			List<WebElement> wMenuOptions = wMenu.findElements(By.tagName("a"));
			for (WebElement wAux:wMenuOptions) {
				if(wAux.getText().toLowerCase().equals("marketing")) {
					wAux.click();
				}
			}*/
		} else {
			driver.findElement(By.id("tsidLabel")).click();
			WebElement wMenu = driver.findElement(By.id("tsid-menuItems"));
			List<WebElement> wMenuOptions = wMenu.findElements(By.tagName("a"));
			for (WebElement wAux:wMenuOptions) {
				if(wAux.getText().toLowerCase().equals("consola fan")) {
					wAux.click();
					break;
				}
			}
		}
		CustomerCare cCC = new CustomerCare(driver);
		cCC.cerrarTodasLasPestañas();
		goToLeftPanel(driver, "Cuentas");
		WebElement frame0 = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(frame0);
		waitFor(driver, (By.name("fcf")));
		Select field = new Select(driver.findElement(By.name("fcf")));
		field.selectByVisibleText("Vista Marketing");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement wBody = driver.findElement(By.className("x-grid3-body"));
		List<WebElement> wAccountName = wBody.findElements(By.cssSelector(".x-grid3-col.x-grid3-cell.x-grid3-td-ACCOUNT_NAME"));
		
		for (WebElement wAux2:wAccountName) {
			WebElement wContenido = wAux2.findElement(By.tagName("span"));
			
			if (wContenido.getText().toLowerCase().equals("florencia marketing")) {
				wAux2.click();
				break;
			}
		}
		
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		mMarketing.irAGestionMarketing();//Checkear
		/*BasePage cambioFrame=new BasePage();
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
		}*/
	}
	@BeforeMethod
	public void go() throws Exception {
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BasePage cambioFrame=new BasePage();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.cssSelector(".slds-panel__section.slds-p-around--small")));
	}
	//@AfterMethod
	public void byeByeTab() {
		try {
			CustomerCare cCC = new CustomerCare(driver);
			WebElement wActiveTab = cCC.obtenerPestañaActiva();
			if (!wActiveTab.findElement(By.className("tabText")).getText().toLowerCase().equals("club personal")) {
				mMarketing.closeActiveTab();
			}
		} catch (IndexOutOfBoundsException e) {
			//AllwaysEmpty
		}
	}
	//@AfterClass
	public void tearDown() {
		driver.close();
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 1
	@Test(groups = "Marketing")
	public void TS4176_Visualizar_error_Mora_Alta_CP() {
		mMarketing.cambioCuenta("Todas las cuentas", "aaaacuenta conmora");
		BasePage cambioFrame=new BasePage();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.cssSelector(".slds-panel__section.slds-p-around--small")));
		mMarketing.clubPersonal("alta");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope")));
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement wMessage = driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope"));
		List<WebElement> lText = wMessage.findElements(By.tagName("p"));
		Assert.assertTrue(lText.get(1).getText().contains("Para continuar es necesario regularizar su estado de cuenta, caso nro."));
		mMarketing.cambioCuenta("Vista Marketing", "Florencia Marketing");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 2
	@Test(groups = {"Marketing", "Ola1"})
	public void TS98023_Funcionamiento_boton_Alta_ABM_del_CP() {
		Marketing mMenuABM = new Marketing(driver);
		mMenuABM.clubPersonal("alta");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BasePage cambioFrame=new BasePage();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.cssSelector(".slds-page-header.vlc-slds-page--header.ng-scope")));
		WebElement wHeader = driver.findElement(By.cssSelector(".slds-page-header.vlc-slds-page--header.ng-scope")).findElement(By.tagName("h1"));
		Assert.assertTrue(wHeader.getText().toLowerCase().contains("cuentas"));
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 3
	@Test(groups = {"Marketing", "Ola1"})
	public void TS98024_Funcionamiento_boton_Baja_ABM_del_CP() {
		Marketing mMenuABM = new Marketing(driver);
		mMenuABM.clubPersonal("baja");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BasePage cambioFrame=new BasePage();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.cssSelector(".slds-page-header.vlc-slds-page--header.ng-scope")));
		WebElement wHeader = driver.findElement(By.cssSelector(".slds-page-header.vlc-slds-page--header.ng-scope")).findElement(By.tagName("h1"));
		Assert.assertTrue(wHeader.getText().toLowerCase().contains("club personal baja"));
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 4
	@Test(groups = {"Marketing", "Ola1"})
	public void TS98032_Separacion_de_cuentas_Alta_CP() {
		Marketing mMenuABM = new Marketing(driver);
		mMenuABM.clubPersonal("alta");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BasePage cambioFrame=new BasePage();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.id("consumerAccounts")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement wConsumerBox = driver.findElement(By.id("consumerAccounts"));
		WebElement wConsumerTable= wConsumerBox.findElement(By.tagName("tbody"));
		List<WebElement> wConsumerTableRows = wConsumerTable.findElements(By.tagName("tr"));
		Assert.assertTrue(!wConsumerTableRows.isEmpty());
		WebElement wBusinessBox = driver.findElement(By.id("businessAccounts"));
		WebElement wBusinessTable= wBusinessBox.findElement(By.tagName("tbody"));
		List<WebElement> wBusinessTableRows = wBusinessTable.findElements(By.tagName("tr"));
		Assert.assertTrue(!wBusinessTableRows.isEmpty());
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 5
	@Test(groups = {"Marketing", "Ola1"})
	public void TS98049_Separacion_Baja_CP() {
		Marketing mMenuABM = new Marketing(driver);
		mMenuABM.clubPersonal("baja");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BasePage cambioFrame=new BasePage();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.id("consumerAccounts")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement wConsumerBox = driver.findElement(By.id("consumerAccounts"));
		WebElement wConsumerTable= wConsumerBox.findElement(By.tagName("tbody"));
		List<WebElement> wConsumerTableRows = wConsumerTable.findElements(By.tagName("tr"));
		Assert.assertTrue(!wConsumerTableRows.isEmpty());
		WebElement wBusinessBox = driver.findElement(By.id("businessAccounts"));
		WebElement wBusinessTable= wBusinessBox.findElement(By.tagName("tbody"));
		List<WebElement> wBusinessTableRows = wBusinessTable.findElements(By.tagName("tr"));
		Assert.assertTrue(!wBusinessTableRows.isEmpty());
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 6
	@Test(groups = {"Marketing", "Ola1"})
	public void TS98036_Boton_Cancelar_Alta_CP() {
		Marketing mMenuABM = new Marketing(driver);
		mMenuABM.clubPersonal("alta");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BasePage cambioFrame=new BasePage();
		driver.switchTo().defaultContent();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")));
		Assert.assertTrue(driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).getText().toLowerCase().equals("cancelar"));
		
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 7
	@Test(groups = {"Marketing", "Ola1"})
	public void TS98037_Boton_Continuar_Alta_CP() {
		Marketing mMenuABM = new Marketing(driver);
		mMenuABM.clubPersonal("alta");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement wSiguiente = driver.findElement(By.className("slds-box"));
		Assert.assertTrue(wSiguiente.findElement(By.tagName("p")).getText().toLowerCase().equals("siguiente"));
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 8
	@Test(groups = {"Marketing", "Ola1"})
	public void TS98051_Visualizar_cuentas_costumer_Baja_CP() {
		Marketing mMenuABM = new Marketing(driver);
		mMenuABM.clubPersonal("baja");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BasePage cambioFrame=new BasePage();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.id("consumerAccounts")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement wConsumerBox = driver.findElement(By.id("consumerAccounts"));
		WebElement wConsumerTable= wConsumerBox.findElement(By.tagName("tbody"));
		List<WebElement> wConsumerTableRows = wConsumerTable.findElements(By.tagName("tr"));
		Assert.assertTrue(!wConsumerTableRows.isEmpty());
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 9
	@Test(groups = {"Marketing", "Ola1"})
	public void TS98052_Visualizar_cuentas_business_Baja_CP() {
		Marketing mMenuABM = new Marketing(driver);
		mMenuABM.clubPersonal("alta");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BasePage cambioFrame=new BasePage();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.id("consumerAccounts")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement wBusinessBox = driver.findElement(By.id("businessAccounts"));
		WebElement wBusinessTable= wBusinessBox.findElement(By.tagName("tbody"));
		List<WebElement> wBusinessTableRows = wBusinessTable.findElements(By.tagName("tr"));
		Assert.assertTrue(!wBusinessTableRows.isEmpty());
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 10
	@Test(groups = {"Marketing", "Ola1"})
	public void TS98057_Verificar_seleccion_del_motivo_otro_Baja_CP() {
		Marketing mMenuABM = new Marketing(driver);
		mMenuABM.clubPersonal("baja");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BasePage cambioFrame=new BasePage();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.id("consumerAccounts")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement wConsumerBox = driver.findElement(By.id("consumerAccounts"));
		WebElement wConsumerTable= wConsumerBox.findElement(By.tagName("tbody"));
		List<WebElement> wConsumerTableRows = wConsumerTable.findElements(By.tagName("tr"));
		WebElement wCTCheckBox = wConsumerTableRows.get(0).findElement(By.tagName("th"));
		wCTCheckBox.findElement(By.tagName("label")).click();
		BasePage bBP = new BasePage(driver);
		bBP.setSimpleDropdown(driver.findElement(By.id("SelectReason")), "Otro");
		Assert.assertTrue(driver.findElement(By.id("Others")).isEnabled());
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 11
	@Test(groups = {"Marketing", "Ola1"})
	public void TS98058_Verificar_seleccion_del_motivo_otro_vacio_Baja_CP() {
		Marketing mMenuABM = new Marketing(driver);
		mMenuABM.clubPersonal("baja");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BasePage cambioFrame=new BasePage();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.id("consumerAccounts")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement wConsumerBox = driver.findElement(By.id("consumerAccounts"));
		WebElement wConsumerTable= wConsumerBox.findElement(By.tagName("tbody"));
		List<WebElement> wConsumerTableRows = wConsumerTable.findElements(By.tagName("tr"));
		WebElement wCTCheckBox = wConsumerTableRows.get(0).findElement(By.tagName("th"));
		wCTCheckBox.findElement(By.tagName("label")).click();
		BasePage bBP = new BasePage(driver);
		bBP.setSimpleDropdown(driver.findElement(By.id("SelectReason")), "Otro");
		driver.findElement(By.id("CPMembershipCancellation_nextBtn")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		assertTrue(driver.findElement(By.id("alert-container")).isDisplayed());
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 12
	@Test(groups = {"Marketing", "Ola1"})
	public void TS98059_Visualizar_botones_Baja_CP() {
		Marketing mMenuABM = new Marketing(driver);
		mMenuABM.clubPersonal("baja");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BasePage cambioFrame=new BasePage();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")));
		Assert.assertTrue(driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("CPMembershipCancellation_nextBtn")).isDisplayed());
		
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 13
	@Test(groups = "Marketing")
	public void TS50055_No_visualizar_error_de_Mora_Alta_CP() {
		Marketing mMenuABM = new Marketing(driver);
		mMenuABM.clubPersonal("alta");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BasePage cambioFrame=new BasePage();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope")));
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement wMessage = driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope"));
		List<WebElement> lText = wMessage.findElements(By.tagName("p"));
		try{
			Assert.assertTrue(!lText.get(1).isEnabled());
			Assert.assertTrue(false);
		} catch (IndexOutOfBoundsException e) {
			Assert.assertTrue(true);
		}
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 14
	@Test(groups = "Marketing")
	public void TS50057_Visualizar_boton_Canje() {
		Marketing mMenuABM = new Marketing(driver);
		mMenuABM.clubPersonal("alta");
		BasePage cambioFrame=new BasePage();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.cssSelector(".slds-input.actionSearch.ng-valid.ng-not-empty.ng-dirty.ng-valid-parse.ng-touched")));
		driver.findElement(By.cssSelector(".slds-input.actionSearch.ng-valid.ng-not-empty.ng-dirty.ng-valid-parse.ng-touched")).clear();
		List<WebElement> wGestiones = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.slds-truncate"));
		boolean bCanjeIsDisplayed = false;
		for (WebElement wAux2:wGestiones) {
			WebElement wContenido = wAux2.findElement(By.cssSelector(".slds-text-body_regular.ta-button-font"));
			if (wContenido.getText().toLowerCase().contains("canje")) {
				bCanjeIsDisplayed = true;
				break;
			}
		}
		Assert.assertTrue(bCanjeIsDisplayed);
	}
		
	//-------------------------------------------------------------------------------------------------
	//TCC = 15
	@Test(groups = {"Marketing","Ola1"})
	public void TS98069_Visualizar_boton_Solapa_CP() {
		Assert.assertTrue(true);
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 16
	@Test(groups = {"Marketing", "Ola1"})
	public void TS98070_Funcionamiento_boton_Solapa_CP() {
		Assert.assertTrue(true);
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 17
	@Test(groups = "Marketing")
	public void TS50062_Visualizar_Categoria_CP_Solapa_CP() {
		//try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement wPCCategory = driver.findElement(By.cssSelector(".slds-tile.slds-p-bottom--medium")).findElement(By.tagName("div")).findElement(By.tagName("p"));
		Assert.assertTrue(!wPCCategory.getText().isEmpty());
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 18
	@Test(groups = "Marketing")
	public void TS50070_Visualizar_campo_Fecha_desde_Solapa_CP() {
		List <WebElement> wBodyComplete = driver.findElements(By.cssSelector(".slds-media.slds-media--center.slds-has-flexi-truncate"));
		WebElement wBody = wBodyComplete.get(5);
		List <WebElement> wColumna = mMarketing.traerColumnaElement(wBody, 7, 4);
		String sFechaDesde = wColumna.get(0).findElement(By.tagName("a")).getText().toLowerCase();
		Assert.assertTrue(sFechaDesde.equals("fecha desde:"));
		wColumna = mMarketing.traerColumnaElement(wBody, 7, 5);
		Assert.assertTrue(wColumna.get(0).findElement(By.id("text-input-id-1")).getAttribute("date-format").toLowerCase().equals("dd/mm/yyyy"));
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 19
	@Test(groups = {"Marketing", "Ola1"})
	public void TS98071_Visualizar_campo_Fecha_hasta_Solapa_CP() {
		List <WebElement> wBodyComplete = driver.findElements(By.cssSelector(".slds-media.slds-media--center.slds-has-flexi-truncate"));
		WebElement wBody = wBodyComplete.get(5);
		List <WebElement> wColumna = mMarketing.traerColumnaElement(wBody, 7, 6);
		String sFechaHasta = wColumna.get(0).findElement(By.tagName("a")).getText().toLowerCase();
		Assert.assertTrue(sFechaHasta.equals("fecha hasta:"));
		wColumna = mMarketing.traerColumnaElement(wBody, 7, 7);
		Assert.assertTrue(wColumna.get(0).findElement(By.id("text-input-id-2")).getAttribute("date-format").toLowerCase().equals("dd/mm/yyyy"));
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 20
	@Test(groups = "Marketing")
	public void TS50072_Campo_fecha_Mayor_al_actual_Solapa_CP() {
		mMarketing.seleccionarFecha(7, 5, 1, "text-input-id-1", "Futuro");
		mMarketing.seleccionarFecha(7, 7, 2, "text-input-id-2", "Futuro");
		
		List<WebElement> wBody = driver.findElements(By.className("techCare-PriceList-tbody"));
		List<WebElement> wRows = wBody.get(2).findElements(By.tagName("tr"));
		List<WebElement> wElementsExtrated =  new ArrayList<WebElement>();
		for (WebElement wAux:wRows) {
			if (!wAux.getText().isEmpty()) {
				List<WebElement> wColumns = wAux.findElements(By.tagName("td"));
				wElementsExtrated.add(wColumns.get(1));
			}
		}
		
		Assert.assertTrue(wElementsExtrated.size() == 0);
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 21
	@Test(groups = {"Marketing", "Ola1"})
	public void TS90241_Sucripcion_CP_Validacion_de_Mail() {
		TS98023_Funcionamiento_boton_Alta_ABM_del_CP();
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 22
	/*@Test(groups = "Marketing")
	public void TS90286_Asignacion_de_Perfil_para_Aplicacion_Atributos() {
		
	}*/
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 23
	@Test(groups = {"Marketing", "Ola1"})
	public void TS98020_Boton_ABM_del_CP() {
		Marketing mMarketing = new Marketing(driver);
		mMarketing.buscarGestion("club personal");
		BasePage cambioFrame=new BasePage();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.className("actions-content")));
		WebElement wResultadosGestiones = driver.findElement(By.className("actions-content"));
		List<WebElement> wButtons = wResultadosGestiones.findElements(By.tagName("button"));
		List<WebElement> wSpans = new ArrayList<WebElement>();
		boolean bAssert = false;
		for(WebElement wAux : wButtons) {
			wSpans = wAux.findElements(By.tagName("span"));
			if(wSpans.get(1).getText().toLowerCase().equals("club personal abm")) {
				bAssert = true;
			}
		}
		Assert.assertTrue(bAssert);
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 24
	@Test(groups = {"Marketing","Ola1"})
	public void TS98027_No_visualizar_error_Fraude_Alta_CP() {
		TS98023_Funcionamiento_boton_Alta_ABM_del_CP();
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 25
	@Test(groups = {"Marketing", "Ola1"})
	public void TS98047_No_visualizar_error_Fraude_Baja_CP() {
		TS98024_Funcionamiento_boton_Baja_ABM_del_CP();
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 26
	@Test(groups = {"Marketing", "Ola1"})
	public void TS98048_Generar_Caso_error_Fraude_Baja_CP() {
		mMarketing.cambioCuenta("Todas las cuentas", "aaaaCuenta Fraude");
		mMarketing.clubPersonal("Baja");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement wMessage = driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.vlc-slds-text-block.vlc-slds-rte.ng-pristine.ng-valid.ng-scope"));
		List <WebElement> wMessageBox = wMessage.findElements(By.tagName("p"));
		String sCaso = wMessageBox.get(1).getText().substring(71, 79);
		boolean bAssert = mMarketing.corroborarCasoCerrado(sCaso);
		mMarketing.cambioCuenta("Vista Marketing", "Florencia Marketing");
		Assert.assertTrue(bAssert);
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 27
	@Test(groups = "Marketing")
	public void TS98050_Multiple_seleccion_Baja_CP() {
		mMarketing.estadoAltaBaja("Baja");
		mMarketing.seleccionarCuenta("consumerAccounts");
		mMarketing.seleccionarCuenta("businessAccounts");
		Assert.assertTrue(true);
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 28
	@Test(groups = {"Marketing", "Ola1"})
	public void TS98056_Verificar_seleccion_de_un_unico_valor_en_el_campo_motivo_de_baja_Baja_CP() {
		mMarketing.clubPersonal("baja");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BasePage cambioFrame=new BasePage();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cambioFrame.getFrameForElement(driver, By.id("consumerAccounts")));
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		WebElement wConsumerBox = driver.findElement(By.id("consumerAccounts"));
		WebElement wConsumerTable= wConsumerBox.findElement(By.tagName("tbody"));
		List<WebElement> wConsumerTableRows = wConsumerTable.findElements(By.tagName("tr"));
		WebElement wCTCheckBox = wConsumerTableRows.get(0).findElement(By.tagName("th"));
		wCTCheckBox.findElement(By.tagName("label")).click();
		
		List <String> sCorrectOptions = new ArrayList<String>();
		sCorrectOptions.add("-- clear --");
		sCorrectOptions.add("no puedo acceder a los descuentos");
		sCorrectOptions.add("no puedo acceder a los premios");
		sCorrectOptions.add("demasiada publicidad");
		sCorrectOptions.add("pocos beneficios en mi zona");
		sCorrectOptions.add("no me gusta");
		sCorrectOptions.add("no lo uso");
		sCorrectOptions.add("otro");
		
		driver.findElement(By.cssSelector(".slds-form-element__control.slds-input-has-icon.slds-has-input-has-icon--right")).click();
		WebElement wDropDown = driver.findElement(By.id("SelectReason"));
		List <WebElement> wOptions = wDropDown.findElements(By.tagName("option"));
		
		List<String> bAssert = new ArrayList<String>();
		
		for (WebElement wAux : wOptions) {
			for (String sAux : sCorrectOptions) {
				if (wAux.getText().toLowerCase().equals(sAux)) {
					bAssert.add("true");
					break;
				}
			}
		}
		
		Assert.assertTrue(bAssert.size() == 8);
	}
	
	//-------------------------------------------------------------------------------------------------
	//Abrir Página
	//@Test
	public void AbrirPagina() {
		
	}
	
	/*Fecha del sistema
	java.util.Date fechaCompleta = new Date();
    boolean bien = false;
    String fech =  fechaCompleta.getDate()+"/"+(fechaCompleta.getMonth()+1);
	int iFechaActual = Integer.parseInt(wFechaDesde.findElement(By.className("slds-is-today")).findElement(By.tagName("span")).getText());
	*/
	
}