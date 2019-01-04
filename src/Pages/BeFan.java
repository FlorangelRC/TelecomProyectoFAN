package Pages;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Tests.TestBase;

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
	
	public  int numeroDiasEntreDosFechas(Date fecha1, Date fecha2){
	     long startTime = fecha1.getTime();
	     long endTime = fecha2.getTime();
	     long diffTime = endTime - startTime;
	     return (int)TimeUnit.DAYS.convert(diffTime, TimeUnit.MILLISECONDS);
	}
	
	public Date fechaAvanzada() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, +1);
		cal.add(Calendar.MONTH, +1);
		//cal.add(Calendar.MINUTE, +1);
		date = cal.getTime();
		return (date);
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
	
	public boolean verificarMensajeExitoso() {
		return driver.findElement(By.xpath("//*[@class='alert alert-dismissable alert-success'] //h4")).getText().equalsIgnoreCase("La operaci\u00f3n se ejecut\u00f3 satisfactoriamente.");
	}
	
	public boolean buscarRegion(String sRegion) {
		driver.findElement(By.xpath("//*[@type='search']")).clear();
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys(sRegion);
		sleep(3000);
		WebElement wBody = driver.findElement(By.className("panel-data"));
		List<WebElement> wList = wBody.findElements(By.xpath("//*[@class='panel-group'] //*[@class='collapsed'] //*[@class='ng-binding']"));
		
		boolean bAssert = true;
		for (WebElement wAux : wList) {
			if (!wAux.getText().toLowerCase().contains(sRegion.toLowerCase())) {
				bAssert = false;
				break;
			}
		}
		
		return bAssert;
	}
	
	public List<String> agregarPrefijos(String sRegion) {
		buscarRegion(sRegion);
		WebElement wBody = driver.findElement(By.className("panel-data"));
		List<WebElement> wList = wBody.findElements(By.xpath("//*[@class='panel-group panel-group-alternative ng-scope']"));
		
		for (WebElement wAux : wList) {
			if (wAux.findElement(By.xpath("//*[@class='collapsed'] //*[@class='ng-binding']")).getText().equalsIgnoreCase(sRegion)) {
				wAux.click();
				wAux.findElement(By.xpath("//*[@class='row ng-scope'] //*[@class='btn btn-link']")).click();
				break;
			}
		}
		sleep(5000);
		List<String> sPrefijos = new ArrayList<String>();
		//List<WebElement> wPrefijos = driver.findElements(By.id("compatibility"));
		sPrefijos.add(driver.findElement(By.xpath("//*[@id='compatibility'][1] //label")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id='compatibility'][1] //label")).getText());
		driver.findElement(By.xpath("//*[contains(@class,'check-filter')] [1]")).click();
		sPrefijos.add(driver.findElement(By.xpath("//*[@id='compatibility'][2] //label")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id='compatibility'][2] //label")).getText());
		driver.findElement(By.xpath("//*[@id='compatibility'][2] //label /.. /input")).click();
		driver.findElement(By.xpath("//*[@ng-show='mensajeAgregarCtrl.container.showConfirmation'] //*[@class='btn btn-primary']")).click();
		sleep(3000);
		verificarMensajeExitoso();
		driver.findElement(By.xpath("//*[contains(@ng-show, 'container.showSuccess')] //*[@class='btn btn-primary']")).click();
		driver.navigate().refresh();
		
		buscarRegion(sRegion);
		wBody = driver.findElement(By.className("panel-data"));
		wList = wBody.findElements(By.xpath("//*[@class='panel-group panel-group-alternative ng-scope']"));
		
		for (WebElement wAux2 : wList) {
			if (wAux2.findElement(By.xpath("//*[@class='collapsed'] //*[@class='ng-binding']")).getText().equalsIgnoreCase(sRegion)) {
				wAux2.click();
				break;
			}
		}
		
		return sPrefijos;
	}
	
	public void buscarYAbrirRegion(String sRegion) {
		buscarRegion(sRegion);
		WebElement wBody = driver.findElement(By.className("panel-data"));
		List<WebElement> wList = wBody.findElements(By.xpath("//*[@class='panel-group panel-group-alternative ng-scope']"));
		
		for (WebElement wAux : wList) {
			if (wAux.findElement(By.xpath("//*[@class='collapsed'] //*[@class='ng-binding']")).getText().equalsIgnoreCase(sRegion)) {
				wAux.click();
				wAux.findElement(By.xpath("//*[@class='row ng-scope'] //*[@class='btn btn-link']")).click();
				break;
			}
		}
	}
	
}
