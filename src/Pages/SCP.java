package Pages;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Tests.TestBase;

public class SCP extends BasePage {
	final WebDriver driver;
	private static String downloadPath = "C:\\Users\\Pablo\\Downloads";
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
	
	public void clickEnCuentaPorNombre(String name)
	{
		boolean enc = false;
		List<WebElement> cuentas = driver.findElement(By.className("hotListElement")).findElements(By.cssSelector(".dataRow.odd"));
		cuentas.add(driver.findElement(By.className("hotListElement")).findElement(By.cssSelector(".dataRow.even.first")));
		for (WebElement unaCuenta : cuentas) {
			System.out.println("Cuenta:"+ unaCuenta.findElement(By.tagName("a")).getText());
			if(unaCuenta.findElement(By.tagName("a")).getText().toLowerCase().contains(name.toLowerCase())) {
				unaCuenta.findElement(By.tagName("a")).click();
				enc = true;
				break;
			}
		}
		if (enc == false)
			clickOnFirstAccRe();
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
	
	public void moveToElementOnAccAndClick(String identificador, int i) {
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		BasePage Bp= new BasePage();
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame(Bp.getFrameForElement(driver, By.id(identificador)));
		
		WebElement idele= driver.findElement(By.id(identificador));
		if(!(identificador.equals("primerTitulo"))) {
			idele.click();
		}
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		idele.findElements(By.tagName("a")).get(i).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.switchTo().defaultContent();
	}
	
	public void goTop() {
		  WebElement subir = driver.findElement(By.id("tsidButton"));
		  ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+subir.getLocation().y+")");
	}
	
	public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
	    File dir = new File(downloadPath);
	    File[] dir_contents = dir.listFiles();
	  	    
	    for (int i = 0; i < dir_contents.length; i++) {
	        if (dir_contents[i].getName().equals(fileName))
	            return flag=true;
	            }

	    return flag;
	}
	
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
	
	public boolean validarservicioborrado(String categoria, String servicio, String color){
		boolean a = true;
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

		List<WebElement> tabla = driver.findElements(By.xpath("//*[@id='tableList']/tbody/tr"));
		//Baja Hasta el servicio creado y lo borra
	for(WebElement S:tabla) {
		//System.out.println(S.getText());
		String D=S.getText().replaceAll(" ","").replaceAll("\r\n", "");
		//System.out.println(D);
		if(D.contains("01/01/2016")) {
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+S.getLocation().y+")");
				S.findElement(By.cssSelector(".btn.btn.btn-default.btn-sm")).click();
				break;
			}
	}
	try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	//Verifica si quedo vacia la lista y returna verdadero
	List<WebElement> Servicios=driver.findElements(By.xpath("//*[@id=\"tableList\"]/tbody/tr"));
	if(Servicios.isEmpty())
		return true;
	//Sino quedo vacia, verifica que no este disponible el que creo.
	for(WebElement S:Servicios) {
		//System.out.println(S.getText());
		String D=S.getText().replaceAll(" ","").replaceAll("\r\n", "");
		//System.out.println(D);
		if(D.contains("01/01/2016"))
			return false;
		}
	return a;
	}
	
	public void nuevoservicio(String categoria, String servicio, String color){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

		driver.switchTo().defaultContent();
		setSimpleDropdown(driver.findElement(By.name("j_id0:j_id89:j_id106")), categoria);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		setSimpleDropdown(driver.findElement(By.id("j_id0:j_id89:serviciosRender")), servicio);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		setSimpleDropdown(driver.findElement(By.id("j_id0:j_id89:j_id113")), color);
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.className("dateFormat")).getLocation().y+")");
		driver.findElement(By.className("dateFormat")).click();
		
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".glyphicon.glyphicon-plus")).getLocation().y+")");
		driver.findElement(By.cssSelector(".glyphicon.glyphicon-plus")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	}
	
	/**
	 * Se Creo para usar en el caso 112782 de Parque de servicio usando la fecha 01/01/16 
	 * @param categoria
	 * @param servicio
	 * @param color
	 */
	public void nuevoservicioEspecifico(String categoria, String servicio, String color){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

		driver.switchTo().defaultContent();
		setSimpleDropdown(driver.findElement(By.name("j_id0:j_id89:j_id106")), categoria);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		setSimpleDropdown(driver.findElement(By.id("j_id0:j_id89:serviciosRender")), servicio);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		setSimpleDropdown(driver.findElement(By.id("j_id0:j_id89:j_id113")), color);
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.className("dateFormat")).getLocation().y+")");
		
		driver.findElement(By.id("j_id0:j_id89:datepicker")).sendKeys("01/01/2016");
		
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".glyphicon.glyphicon-plus")).getLocation().y+")");
		driver.findElement(By.cssSelector(".glyphicon.glyphicon-plus")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}	}
	
	public boolean validarservicionuevo(String categoria, String servicio, String color){
		boolean a = false;
		List<WebElement> tabla = driver.findElements(By.xpath("//*[@id='tableList']/tbody/tr"));
		//System.out.println(tabla.get((tabla.size()-1)).getText());
		if(!driver.findElement(By.xpath("//*[@id='tableList']/tbody/tr["+(tabla.size()-1)+"]")).getText().contains(servicio+categoria+color)){
			a = true;
		}

			return a;
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

public void comentarycompartir(String comentario){
	TestBase TB = new TestBase();
	TB.waitFor(driver, By.cssSelector(".publisherTextAreaInner"));
	WebElement element = driver.findElement(By.cssSelector(".publisherTextAreaInner"));
	((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+element.getLocation().y+")");
	TB.waitFor(driver, By.id("publishereditablearea"));
	driver.findElement(By.id("publishereditablearea")).click();
	TB.waitFor(driver, By.tagName("iframe"));
	WebElement iframe =driver.findElement(By.tagName("iframe"));
	driver.switchTo().frame(iframe);
	WebElement description=driver.findElement(By.xpath("//body[@class='chatterPublisherRTE cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']"));
	try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	description.click();
	description.sendKeys(comentario);
	driver.switchTo().defaultContent();
	try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	driver.findElement(By.id("publishersharebutton")).click();
	
}

public void validarcomentario(String comentario){
	try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}

	List <WebElement> comentarios = driver.findElements(By.cssSelector(".feeditemtext.cxfeeditemtext"));
	System.out.println(comentarios.size());
	Assert.assertTrue(comentarios.get(0).getText().equals(comentario));
	Assert.assertEquals(driver.findElement(By.cssSelector(".topics.init")).getText(), "Haga clic para agregar temas:   Sin sugerencias. Añada sus propios temas.");
}


public void validarcomentarioajeno(String comentario){
	try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	String cuentaactiva = driver.findElement(By.id("userNavLabel")).getText();
	List <WebElement> comentarios = driver.findElements(By.cssSelector(".feeditembodyandfooter"));
	Assert.assertTrue(comentarios.get(0).findElement(By.cssSelector(".cxfeeditemtextwrapper")).getText().equals(comentario));
	Assert.assertFalse(comentarios.get(0).findElement(By.cssSelector(".feeditemfirstentity")).getText().equals(cuentaactiva));

}

public boolean cuentalogeada(String cuenta){
	boolean a=false;
  TestBase TB = new TestBase();
  TB.waitFor(driver, By.id("userNavLabel"));
	String cuentaactiva = driver.findElement(By.id("userNavLabel")).getText();
	if(cuentaactiva.equals(cuenta)){
		a=true;}
	return a;}




	public void Desloguear_Loguear(String usuario) {
		driver.findElement(By.id("userNav")).click();
		TestBase TB = new TestBase();
		List<WebElement> opcionesMenu = driver.findElement(By.id("userNav-menuItems")).findElements(By.tagName("a"));
		for (WebElement UnaO : opcionesMenu) {
			if(UnaO.getText().toLowerCase().contains("finalizar sesión")) {
				UnaO.click();
				break;
			}
		}
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		if (usuario.toLowerCase().contains("fabiana"))
			TB.loginSCPUsuario(driver);
		else
			TB.loginSCPAdminServices(driver);
	}
	
	public void Desloguear_Loguear_Comentar(String usuario, String otroUsuario, String comentario, String identificador, int indice) {
		Desloguear_Loguear(otroUsuario);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		clickOnTabByName("cuentas");
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try {Thread.sleep(7000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		clickEnCuentaPorNombre("Florencia Di Ci");
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		moveToElementOnAccAndClick(identificador,indice);
		comentarycompartir(comentario);
		Desloguear_Loguear(usuario);
	}
	
	public boolean Triangulo_Ordenador_Validador() {
		TestBase TB = new TestBase();
		TB.waitFor(driver, By.xpath("//*[@id=\"mainTable\"]/tbody/tr"));
		
		List<WebElement> wOportunityByUs = driver.findElements(By.xpath("//*[@id=\"mainTable\"]/tbody/tr"));
		ArrayList<String> sOportunityByUs = new ArrayList<String>();
		
		driver.findElement(By.xpath("//*[@id=\"mainTable\"]/thead/tr/th[2]")).click();
		List<WebElement> wOportunityByThem = driver.findElements(By.xpath("//*[@id=\"mainTable\"]/tbody/tr"));
		ArrayList<String> sOportunityByThem = new ArrayList<String>();
		
		for (int a = 0; a < wOportunityByUs.size(); a++) {
			sOportunityByUs.add(wOportunityByUs.get(a).getText().toLowerCase());
			sOportunityByThem.add(wOportunityByThem.get(a).getText().toLowerCase());
		}
		
		Collections.sort(sOportunityByUs);
		boolean bBoolean = true;
		
		for(int i = 0; i < sOportunityByUs.size(); i++) { 
			if (!sOportunityByUs.get(i).equals(sOportunityByThem.get(i))) {
			 bBoolean = false;
		 }
		}
		
		return bBoolean;
		
	}


}
