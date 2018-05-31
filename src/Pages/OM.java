package Pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OM {

static WebDriver driver;
	
	//*********************************CONSTRUCTOR******************************************************//

	public OM(WebDriver driver){
		this.driver = driver;
			PageFactory.initElements(driver, this);
	}
	
	//*********************************ELEMENTOS******************************************************//
	
	
	@FindBy(id="tabBar")
	private WebElement TabBar;
	
	@FindBy(css=".dataCol.orderBlock")
	private List <WebElement> listTabs;
	
	@FindBy(name="btnAgregar")
	private WebElement agregar;
	
	@FindBy(id="fileinput")
	private WebElement adjuntar;
	
	
	//********************************METODOS*******************************************************//
	public void sleep(long s) {
		try {Thread.sleep(s);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	/**
	 * Hace click en los tabs principal segun el "Id" recibido.
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
	 * @param tab
	 */
	public void clickOnListTabs(String tab) {
		boolean flag=true;
		for (WebElement option : listTabs) {
			System.out.println(option.getText());
			if(option.getText().toLowerCase().equals(tab.toLowerCase())){
					WebElement BenBoton = option.findElement(By.tagName("a"));
						((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+BenBoton.getLocation().y+")");
							sleep(1000);
							BenBoton.click();
							flag=false;
				break;}
			}
		if(flag) System.out.println("No hizo Click en: "+tab);
		sleep(5000);
		}
	
	/**
	 * Cambia de Pestana en el Navegador.
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
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+Elemento.getLocation().y+")");
		return true;
			}catch(NullPointerException e){
				System.out.println("Error: No se puede hacer Scroll");
				return false;
			}
		}
	
	public boolean scrollDownInAView(WebElement Elemento) {
		try {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",Elemento);
		return true;
			}catch(NullPointerException e){
				System.out.println("Error: No se puede hacer Scroll");
				return false;
			}
		}
	
	public void goToMenuOM() {
		  sleep(5000);
		  String actual = driver.findElement(By.id("tsidLabel")).getText();
		  
		  if(actual.toLowerCase().contains("sales")||actual.toLowerCase().contains("ventas"))
					  return;
		  else {
				    driver.findElement(By.id("tsid")).click();
				    try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
				    driver.findElement(By.xpath("//a[@href=\"/home/home.jsp?tsid=02u41000000QWha\"]")).click();
				}
		  }
	/**
	 * Crea una orden desde la vista de todas las ordenes.
	 */
	public void crearOrden(String Cuenta) {
		
		driver.findElement(By.name("new")).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		crearCuentaOM(Cuenta);
		driver.findElement(By.id("accid")).sendKeys(Cuenta);
		driver.findElement(By.className("dateFormat")).click();
		Select Estado= new Select(driver.findElement(By.id("Status")));
		Estado.selectByVisibleText("Draft");
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.name("save")).click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	
	/**
	 * Crea una orden desde la vista de todas las ordenes.
	 */
	public void crearCuentaOM(String Cuenta) {
		sleep(1000);
		List<WebElement> buscarCuenta=driver.findElements(By.className("lookupIcon"));
		for(WebElement op: buscarCuenta) {
			if(op.getAttribute("alt").equalsIgnoreCase("Account Name Lookup (New Window)")) {
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

		WebElement inputNombreCuenta=driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[2]/table/tbody/tr[1]/td[2]/div/input"));
		inputNombreCuenta.click();
		inputNombreCuenta.clear();
		inputNombreCuenta.sendKeys(Cuenta);
		driver.findElements(By.name("save")).get(1).click();
		cambiarVentanaNavegador(0);
		driver.switchTo().defaultContent();
		
	}
	
	/**
	 * Crea una vista desde la ventana "Ordenes"
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
		
		//Filtros de Busqueda
		Select campo=new Select(driver.findElement(By.id("fcol1")));
		campo.selectByValue("SALES.ACCOUNT.NAME");
		Select operador=new Select(driver.findElement(By.id("fop1")));
		operador.selectByValue("e");
		driver.findElement(By.id("fval1")).sendKeys(nombreCuenta);;
		sleep(1000);
		//click guardar
		driver.findElement(By.cssSelector(".btn.primary")).click();
		sleep(2000);
		if(driver.findElement(By.name("fcf")).getText().contains(nombreVista))
			return true;
		else
			return false;
		}catch(Exception e) {
			System.out.println("Vista '"+nombreVista+"' no creada.");
			e.printStackTrace();
			return false;
		}
	}
	
}
