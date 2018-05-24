package Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
}
