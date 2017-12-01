package Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class SCP extends BasePage {
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
		try {Thread.sleep(3000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
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
}