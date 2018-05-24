package Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BeFan extends BasePage{

	//*********************************CONSTRUCTOR******************************************************//
	static WebDriver driver;
	
	public BeFan(WebDriver driver){
		this.driver = driver;
			PageFactory.initElements(driver, this);
	}
	
	
	//*********************************ELEMENTOS******************************************************//
	
	//Login
	@FindBy(name="username")
	private WebElement user;
	
	@FindBy(name="txtPass")
	private WebElement password;
	
	@FindBy(name="btnIngresar")
	private WebElement IngresarBoton;
	
	//Ventana Importacion
	@FindBy(css=".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-not-empty")
	private WebElement prefijo;
	
	@FindBy(css=".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")
	private WebElement cantidad;
	
	@FindBy(name="btnAgregar")
	private WebElement agregar;
	
	@FindBy(id="fileinput")
	private WebElement adjuntar;
	
	@FindBy(linkText="Aceptar")
	private WebElement aceptar;
	
	//Ventana gestion
	
	@FindBy(css=".text.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")
	private WebElement Estado;
	
	@FindBy(css=".form-control.ng-pristine.ng-valid.ng-empty.ng-touched")
	private WebElement nombreArchivo;
	
	@FindBy(id="dataPickerDesde")
	private WebElement fechaDesde;

	@FindBy(id="dataPickerHasta")
	private WebElement fechaHasta;
	
	
	//Mensajes
	@FindBy(xpath="//*[@id='NotUpdatedPhoneMessage']/div/p/p[2]/span/strong")
	private WebElement NotUpdatedPhoneMessage;
	
	
	
	
	//********************************METODOS*******************************************************//
	
	public static WebDriver initDriver() {
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("start-maximized");
	    driver = new ChromeDriver(options);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    return driver;
	}
	
	public static void irABefan() {
		driver.get("http://befantest.personal.corp/#/signin");
	}
	public void loginBefan(String user, String password) {
		try {
		this.user.sendKeys(user);
		this.password.sendKeys(password);
		IngresarBoton.click();
		}catch(Exception e) {
			System.out.print("No se puede Ingresar en BeFan: ");
			e.printStackTrace();
		}
	}
	
	public void adjuntarArchivoBefan(String FileAddress) {
		adjuntar.sendKeys(FileAddress);
	}
	
	/**
	 * Selecciona una opcion de la lista despegable de SIM
	 * @param action
	 */
	public void opcionDeSim(String opcion) {
		List <WebElement> despegables=driver.findElements(By.className("dropdown-toggle"));
		for(WebElement op:despegables) {
			if(op.getText().toLowerCase().contains("sims"))
				op.click();
			}
		List<WebElement> opciones=driver.findElements(By.className("multi-column-dropdown")).get(1).findElements(By.tagName("li"));
		for(WebElement op:opciones) {
			if(op.getText().toLowerCase().contains(opcion.toLowerCase()))
				op.click();
			}
		
	}
	
	public void selectPrefijo(String prefijo) {
		Select Prefijo=new Select(this.prefijo);
		Prefijo.selectByVisibleText(prefijo);
	}
	
	public void setCantidad(String cantidad) {

		this.cantidad.sendKeys(cantidad);
	}
	
	
	/**
	 * Hace click en un boton segun el Etiqueta del boton.
	 * Sirve para el boton Importar y el boton buscar de la ventana Gestion.
	 */
	public void clickEnBoton(String EtiquetaBoton) {
		List<WebElement> botones=driver.findElements(By.cssSelector(".btn.btn-primary"));
		System.out.println("Numero de Botones: "+botones.size());
	    for(WebElement opcion:botones)
	    	if(opcion.getText().equalsIgnoreCase(EtiquetaBoton))
	    		opcion.click();
	}
	
	/**Estados Predefinidos:
	 * 
	 * -Pendiente
	 * -Eliminado
	 * -En Proceso
	 * -Procesado.
	 * 
	 * @param estado
	 */
	public void selectEstado(String estado) {
		Select sEstado=new Select(this.Estado);
		sEstado.selectByVisibleText(estado);
	}
	
	public void setNombreArchivo(String nombreArchivo) {
		driver.findElement(By.className("col-lg-3")).findElement(By.tagName("input")).sendKeys(nombreArchivo);
		//this.nombreArchivo.sendKeys(nombreArchivo);
	}
	

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde.clear();
		this.fechaDesde.sendKeys(fechaDesde);
	}

	public void setFechaDesde() {
		this.fechaDesde.clear();
		this.fechaDesde.sendKeys("/00200F");
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta.clear();
		this.fechaHasta.sendKeys(fechaHasta);
	}
	
	public void SeleccionarFechas(String Desde, String Hasta) {
		setFechaDesde(Desde);
		setFechaHasta(Hasta);
	}
	
	public void clickAceptar() {
		this.aceptar.click();
	}
	
}
