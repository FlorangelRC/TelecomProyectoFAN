package Tests;
 

 
import java.text.ParseException;
 
import java.util.ArrayList;
 
import java.util.Collections;
 
import java.util.List;
 
import org.openqa.selenium.By;
 
import org.openqa.selenium.JavascriptExecutor;
 
import org.openqa.selenium.WebDriver;
 
import org.openqa.selenium.WebElement;
 
import org.openqa.selenium.support.ui.Select;
 
import org.testng.Assert;
 
import org.testng.annotations.AfterClass;
 
import org.testng.annotations.BeforeClass;
 
import org.testng.annotations.BeforeMethod;
 
import org.testng.annotations.Test;
 
import Pages.BasePage;
 
import Pages.CustomerCare;
 
import Pages.SCP;
 
import Pages.setConexion;
 

 
public class Marketing_Mattu extends TestBase{
 
  
 
  private WebDriver driver;
 
  
 
  //-------------------------------------------------------------------------------------------------
 
  //@Befor&After
 
  @AfterClass
 
  public void tearDown() {
 
    //driver.close();
 
  }
 
  
 
  @BeforeClass
 
  public void Init() throws Exception
 
  {
 
    this.driver = setConexion.setupEze();
 
    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
 
    login(driver);
 
    try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
 
  }
 

 
  @BeforeMethod
 
  public void setup() throws Exception {
 
      driver.findElement(By.id("tsidLabel")).click();
 
    WebElement wMenu = driver.findElement(By.id("tsid-menuItems"));
 
    List<WebElement> wMenuOptions = wMenu.findElements(By.tagName("a"));
 
    wMenuOptions.get(0).click();
 
    driver.findElement(By.id("tsidLabel")).click();
 
    wMenu = driver.findElement(By.id("tsid-menuItems"));
 
    wMenuOptions = wMenu.findElements(By.tagName("a"));
 
    wMenuOptions.get(9).click();
 
    goToLeftPanel(driver, "Cuentas");
 
    WebElement frame0 = driver.findElement(By.tagName("iframe"));
 
    driver.switchTo().frame(frame0);
 
    waitFor(driver, (By.name("fcf")));  
 
    Select field = new Select(driver.findElement(By.name("fcf")));
 
    field.selectByVisibleText("Vista Marketing");
 
    try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
 
    WebElement wBody = driver.findElement(By.id("ext-gen12"));
 
    List<WebElement> wAccountName = wBody.findElements(By.cssSelector(".x-grid3-col.x-grid3-cell.x-grid3-td-ACCOUNT_NAME"));
 
    for (WebElement wAux:wAccountName) {
 
      WebElement wContenido = wAux.findElement(By.tagName("span"));
 
      if (wContenido.getText().toLowerCase().equals("florencia marketing")) {
 
        wAux.click();
 
        break;
 
      }
 
    }
 
    
 
    try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
 
    BasePage cambioFrameByID=new BasePage();
 
    driver.switchTo().defaultContent();
 
    
 
    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-input.actionSearch.ng-pristine.ng-untouched.ng-valid.ng-empty")));
 
      driver.findElement(By.cssSelector(".slds-input.actionSearch.ng-pristine.ng-untouched.ng-valid.ng-empty")).clear();
 
      driver.findElement(By.cssSelector(".slds-input.actionSearch.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys("Club Personal");
 
      List<WebElement> wGestiones = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.slds-truncate"));
 
      for (WebElement wAux2:wGestiones) {
 
        WebElement wContenido = wAux2.findElement(By.cssSelector(".slds-text-body_regular.ta-button-font"));
 
      if (wContenido.getText().toLowerCase().equals("club personal")) {
 
        wAux2.click();
 
        System.out.println("Clickeo");
 
        break;
 
      }
 
      else {
 
        System.out.println("No clickeo nada");
 
      }
 
    }
 
  }
 
    
 
    //-------------------------------------------------------------------------------------------------
 
    //TCC = 1
 
  @Test(groups = "Marketing")
 
  public void TS5816_Visualizar_boton_Solapa_CP() {
 
    BasePage cambioFrameByID = new BasePage();
 
    driver.switchTo().defaultContent();
 
    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-panel__section.slds-p-around--small")));
 
    Assert.assertTrue(driver.findElement(By.className("slds-text-heading--medium")).getText().equals("Club Personal"));
 
  }
 
  //-------------------------------------------------------------------------------------------------
  //TCC = 2
 
  /*@Test(groups = "Marketing")
 
  public void TS4601_No_visualizacion_de_sin_seleccionar_Alta_CP() {
 
    BasePage cambioFrameByID = new BasePage();
 
    driver.switchTo().defaultContent();
 
    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-panel__section.slds-p-around--small")));

  }*/
  
}