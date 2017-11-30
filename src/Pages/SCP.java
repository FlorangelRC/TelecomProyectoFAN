package Pages;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SCP extends BasePage {
	
	private static String downloadPath = "C:\\Users\\Sofia Chardin\\Downloads";
	final WebDriver driver;
	private Select listSelect;
	 private List<WebElement> accountsList;
	
	@FindBy (how = How.ID, using = "Account_Tab")
	private WebElement tabAccount; //pestania cuentas
	
	@FindBy (how = How.ID, using = "Contact_Tab")
	private WebElement tabContact; //pestania contacto
	
	@FindBy (how = How.ID, using = "Opportunity_Tab")
	private WebElement tabOpportunity; //pestania oportunidad
	
	@FindBy (how = How.ID, using = "AllTab_Tab")
	private WebElement allTab; //pestania +    wt-Strategic-Client-Plan
	
	@FindBy (how = How.CLASS_NAME, using = "wt-Strategic-Client-Plan")
	private WebElement StrategicClient; //pestania plan de estrategia de cliente   
	
	//Constructor
	  public SCP(WebDriver driver){
	    this.driver = driver;
	        PageFactory.initElements(driver, this);
	}
	
	  //Hace click en el tab buscado por nombre
	public void clickOnTabByName(String tabName) {
		switch (tabName.toLowerCase()) {
		case "cuentas":
			tabAccount.click();
		break;
		case "contactos":
			tabContact.click();
		break;
		case "oportunidades":
			tabOpportunity.click();
		break;
		case "estrategia cliente":
			StrategicClient.click();
		break;
		case "+":
			allTab.click();
		break;
		}
    }
	
	//selecciona la visualizacion de la lista de acuerdo al texto
	public void listTypeAcc(String listaBuscar) {
		//driver.switchTo().defaultContent();
		//driver.switchTo().frame(getFrameForElement(driver, By.id("fcf")));
		listSelect = new Select(driver.findElement(By.tagName("select")));
		if (driver.findElement(By.id("fcf")).getText().equals(listaBuscar)) {
			driver.findElement(By.id("fcf")).findElement(By.className("btn")).click();
		}else {
			listSelect.selectByVisibleText(listaBuscar);//ojo con mayusculas y minusculas
		}
	}
	
	//selecciona la primera cuenta en la lista de cuentas recientes
	public void clickOnFirstAccRe() {
		driver.findElement(By.className("hotListElement")).findElement(By.cssSelector(".dataRow.even.first")).findElement(By.tagName("a")).click();
	}
	
	public void goToMenu(String Name) {
	    
		  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		  Name= Name.toLowerCase();
		  String actual = driver.findElement(By.id("tsidLabel")).getText();
		    
		  if (actual.toLowerCase().contains(Name)){
		    return;}
		  else {
		    driver.findElement(By.id("tsid")).click();
		    try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		    switch(Name) {
		    case "scp": driver.findElement(By.xpath("//a[@href=\"/home/home.jsp?tsid=02u3F000000CjqC\"]")).click();
		    break;
		    
		    case "ventas": driver.findElement(By.xpath("//a[@href=\"/home/home.jsp?tsid=02u41000000QWha\"]")).click();
		    break;
		    
		    case "marketing": driver.findElement(By.xpath("//a[@href=\"/home/home.jsp?tsid=02u41000000QWhf\"]")).click();
		    break;
		    
		    case "salesforce chatter": driver.findElement(By.xpath("//a[@href=\"/home/home.jsp?tsid=02u41000000QWhg\"]")).click();
		    break;
		    
		    case "service cloud console": driver.findElement(By.xpath("//a[@href=\"/console?tsid=02u41000000QWhZ\"]")).click();
		    break;
		    
		    case "chatter answers moderator": driver.findElement(By.xpath("//a[@href=\"/console?tsid=02u41000000QWhc\"]")).click();
		    break;
		    
		    case "appexchange": driver.findElement(By.xpath("//a[@href=\"https://appexchange.salesforce.com\"]")).click();
		    break;
		    
		    case "comunidad de desarrolladores": driver.findElement(By.xpath("//a[@href=\"http://developer.force.com\"]")).click();
		    break;
		    
		    case "success community": driver.findElement(By.xpath("//a[@href=\"https://success.salesforce.com\"]")).click();
		    break;
		    }}
		  }
	
	/**
	 * El xpath debe ser construido en el metodo que invoque moveToElementOnAccAndClick y debe ser enviado como cadena por parametros, el parametro se llama referencia
	 * @param identificador
	 * @param referencia
	 */
	
	public void moveToElementOnAccAndClick(String identificador, String referencia) {
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BasePage Bp= new BasePage();
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame(Bp.getFrameForElement(driver, By.id(identificador)));
		WebElement idele= driver.findElement(By.id(identificador));
		idele.click();
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		idele.findElement(By.xpath(referencia)).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	public void nuevoservicio(String categoria, String servicio, String color){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

		driver.switchTo().defaultContent();
		setSimpleDropdown(driver.findElement(By.name("j_id0:j_id89:j_id110")), categoria);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		setSimpleDropdown(driver.findElement(By.id("j_id0:j_id89:serviciosRender")), servicio);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		setSimpleDropdown(driver.findElement(By.id("j_id0:j_id89:j_id117")), color);
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.className("dateFormat")).getLocation().y+")");
		driver.findElement(By.className("dateFormat")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".glyphicon.glyphicon-plus")).getLocation().y+")");
		driver.findElement(By.cssSelector(".glyphicon.glyphicon-plus")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

		
		
	}
	
	public boolean validarservicionuevo(String categoria, String servicio, String color){
		boolean a = false;
		List<WebElement> tabla = driver.findElements(By.xpath("//*[@id='tableList']/tbody/tr"));
		System.out.println(tabla.get((tabla.size()-1)).getText());
		if(!driver.findElement(By.xpath("//*[@id='tableList']/tbody/tr["+(tabla.size()-1)+"]")).getText().contains(servicio+categoria+color)){
			a = true;
		}

			return a;
		}
		
		
	
	
	public boolean validarservicioborrado(String categoria, String servicio, String color){
		boolean a = true;
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

		List<WebElement> tabla = driver.findElements(By.xpath("//*[@id='tableList']/tbody/tr"));
	
		Integer b;
		for(int i=0; i<tabla.size();i++){
			if(tabla.get(i).getText().equals("Categoria de los servicios: Categoria1")){
				b=i+3;
			}
		}
		//Cantidad celdas categoria1
	List<WebElement> borrar = driver.findElements(By.cssSelector(".btn.btn.btn-default.btn-sm"));
	((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+borrar.get((borrar.size()-2)).getLocation().y+")");
	borrar.get((borrar.size()-2)).click();
	try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}




	try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	if(!driver.findElement(By.xpath("//*[@id='tableList']/tbody/tr[17]")).getText().contains(servicio+categoria+color)){
		a = false;
	}

		return a;
	}
	
	private boolean isFileDownloaded_Ext(String dirPath, String ext){
		boolean flag=false;
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        flag = false;
	    }
	    
	    for (int i = 1; i < files.length; i++) {
	    	if(files[i].getName().contains(ext)) {
	    		flag=true;
	    	}
	    }
	    return flag;
	}
	
	public void servicioexportarexcel(){
		
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.xpath("//*[@id='j_id0:j_id89:j_id91']/div/div/button[2]")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    Assert.assertTrue(isFileDownloaded_Ext(downloadPath, "Parque_de_servicios.xls"), "Failed to download document which has extension .xls");
	}
	
	public void servicioguardar(){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.xpath("//*[@id='j_id0:j_id89:j_id91']/div/div/button[1]")).click();
		
	}
			 
		public void clickOnFirstAcc() {//Cambiar
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//driver.switchTo().defaultContent();
		//driver.switchTo().frame(getFrameForElement(driver, By.className("hotListElement")));
		driver.findElement(By.className("hotListElement")).findElement(By.cssSelector(".dataRow.even.first")).findElement(By.className("dataCell")).click();
			 }


		public void goTop() {
			WebElement subir = driver.findElement(By.id("tsidButton"));
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+subir.getLocation().y+")");
		}
		
		/**
		 * En la vista Asignacion de values driver a oportunidades. hace click en la primera oportunidad y verifica que este disponible la estructura de la oportunidad.
		 * OJO: Esta programada desde Asignacion...
		 */
		public boolean goToOportunity() {
			try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			if(driver.findElement(By.cssSelector(".dataCell.droppableTD.ui-droppable.sorting_1")).isDisplayed()) {
				WebElement oportunidad=driver.findElement(By.cssSelector(".dataCell.droppableTD.ui-droppable.sorting_1")).findElement(By.tagName("a"));
				oportunidad.click();
				try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
				if(driver.findElement(By.id("bodyCell")).isDisplayed())
					return true;
				else
					return false;
			}
			else return false;
		}
		
		
		
		/** EN CONSTRUCCION
		 * Verifica si las categorias estan en el orden especifico
		 */
		public boolean categoriasDisponiblesEnOrden() {
			boolean valor=false;
			int i=0;
			String[] datos = {"detalle de la oportunidad", "productos de la oportunidad", "valorizado de la oportunidad",
					"estado de proyectos delta", "actividades abiertas", "competidores",
					"notas y archivos adjuntos", "información adicional de ventas", "historial de campos"};
			
			List <WebElement> elements= driver.findElements(By.id("xxxxx"));
			for(WebElement a : elements) {
				//System.out.println(a.getText().toLowerCase()+"  "+datos[i]);
				if(a.getText().toLowerCase().equals(datos[i]))
					valor=true;
				else
					return false;
			i++;
			}
			return valor;
		}
		
		/**
		 * Elegi la opcion de segun el circulo y la opcion
		 * @param (id de los titulos)
		 * @param (opcion a elegir)
		 */
		public void moveToElementOnAccAndClick(String titulo, int opcion) {
			  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			  BasePage Bp= new BasePage();
			  driver.switchTo().defaultContent();
			  driver.switchTo().frame(Bp.getFrameForElement(driver, By.id(titulo)));
			  WebElement idele= driver.findElement(By.id(titulo));
			  idele.click();
			  try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			  idele.findElements(By.tagName("a")).get(opcion).click();
			  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			  driver.switchTo().defaultContent();
			 }
}
