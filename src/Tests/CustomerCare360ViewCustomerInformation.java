package Tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.sql.Driver;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.CustomerCare;
import Pages.Login;
import Pages.setConexion;

public class CustomerCare360ViewCustomerInformation extends TestBase  {

	private WebDriver driver;
 	
	//@AfterClass

	public void tearDown() {
			driver.close();
	}

//	@AfterMethod
	public void alert (){
		driver.get("https://cs14.salesforce.com/home/home.jsp");
		try{
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}catch(org.openqa.selenium.NoAlertPresentException e){}
	}
	
	@BeforeClass
	public void init() throws Exception
	{
		this.driver = setConexion.setupEze();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		login(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	@BeforeMethod
	public void setup() throws Exception {
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		String a = driver.findElement(By.id("tsidLabel")).getText();
		driver.findElement(By.id("tsidLabel")).click();
		if(a.equals("Ventas"))
		{
			driver.findElement(By.xpath("//a[@href=\"/console?tsid=02uc0000000D6Hd\"]")).click();
		}else
		{
			driver.findElement(By.xpath("//a[@href=\"/home/home.jsp?tsid=02u41000000QWha\"]")).click();
			try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.findElement(By.id("tsidLabel")).click();
			driver.findElement(By.xpath("//a[@href=\"/console?tsid=02uc0000000D6Hd\"]")).click();
		}
    CustomerCare page = new CustomerCare(driver);
    page.cerrarultimapestaña();
    page.elegircuenta("Andres Care");
    page.openleftpanel();
}
	


	
	@Test
	public void TS7069_ValidationButtons () {

		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		 BasePage cambioFrameByID=new BasePage();
	     try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	     driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("profile-links")));
		List<WebElement> botones = driver.findElements(By.className("profile-links"));
		Assert.assertEquals("Actualizar Datos", botones.get(0).getText());
		Assert.assertEquals("| Reseteo Clave", botones.get(1).getText());
		driver.switchTo().defaultContent();
	}

	@Test
	public void TS7068_ValidationFields (){

	try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	List<WebElement> frame1 = driver.findElements(By.tagName("iframe"));
	driver.switchTo().frame(frame1.get(5));
	driver.findElements(By.className("ext-webkit.ext-chromehg"));
	List<WebElement> profileinfo = driver.findElements(By.className("client-data-detail"));
	driver.findElement(By.className("console-account-info"));
	Assert.assertEquals("Correo Electrónico:", profileinfo.get(0).getText());
	Assert.assertEquals("TelÃ©fono:", profileinfo.get(1).getText());
	Assert.assertEquals("Club Personal:", profileinfo.get(2).getText());
	Assert.assertEquals("CategorÃ­a:", profileinfo.get(3).getText());
	driver.switchTo().defaultContent();
}


@Test
public void TS7070_ValidationClubPersonalBasico (){
	String basico = "Basico" ;

try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	List<WebElement> frame1 = driver.findElements(By.tagName("iframe"));
	driver.switchTo().frame(frame1.get(5));
	driver.findElements(By.className("ext-webkit.ext-chromehg"));
	List<WebElement> profileinfo = driver.findElements(By.className("acct-info"));
	driver.findElement(By.className("client-data-detail"));
	//access profileInfo, finds fourth element with client data (Categoriax	)
	Assert.assertTrue(profileinfo.get(0).findElements(By.className("client-data-detail")).get(3).getText().contains(basico)); 
	driver.switchTo().defaultContent();
}

@Test
public void TS7072_ValidationNPS(){

try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	List<WebElement> frame1 = driver.findElements(By.tagName("iframe"));
	driver.switchTo().frame(frame1.get(5));
	driver.findElements(By.className("ext-webkit.ext-chromehg"));
	driver.findElement(By.className("detail-card"));
	List<WebElement> profileinfo = driver.findElements(By.className("account-detail-content"));
	for(int i=1; i<20; i++){
		String b = Integer.toString(i);
		Assert.assertFalse(profileinfo.get(0).getText().contains("-"+b));
}
driver.switchTo().defaultContent();
}

//@Test
public void TS7070_ValidationClubPersonalPremium (){
	String premium = "CategorÃ­a: Premium" ;

try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
List<WebElement> frame1 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame1.get(5));
driver.findElements(By.className("ext-webkit.ext-chromehg"));
List<WebElement> profileinfo = driver.findElements(By.className("acct-info"));
driver.findElement(By.className("client-data-detail"));
Assert.assertTrue(profileinfo.get(0).getText().contains(premium)); 
driver.switchTo().defaultContent();
}

	@Test
	public void TS7126_ValidationPerfilPanel (){	
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame1 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame1.get(5));
		driver.findElements(By.className("ext-webkit.ext-chromehg"));
	    driver.findElement(By.className("ng-hide"));
	    driver.switchTo().defaultContent();
	}
	
	@Test
	public void TS7106_ValidationNumberEstatus (){
	try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List<WebElement> frame1 = driver.findElements(By.tagName("iframe"));
	driver.switchTo().frame(frame1.get(5));
	driver.findElements(By.className("ext-webkit.ext-chromehg"));
	List<WebElement> profileinfo = driver.findElements(By.className("story-field"));
	try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	//pasar todo a un metodo en page
	//Lista de estados posibles
	List<String> expected = new ArrayList<String>();
	expected.add("New");
	expected.add("Closed");
	for(int i=0; i<profileinfo.size(); i+=2){
		String b = (profileinfo.get(i).getText());
		b = b.replaceAll("[0-9]","");
		b = b.replaceAll("-","");
		b = b.replaceAll(" ","");
    Assert.assertTrue(expected.contains(b));
	}
	for(int i=0; i<profileinfo.size(); i+=2){
		String b = (profileinfo.get(i).getText());
    Assert.assertTrue(b.contains("0"));
	}
	driver.switchTo().defaultContent();
	}
	
	@Test
		public void TS7112_ValidationDateFormat (){

	try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
List<WebElement> frame1 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame1.get(5));
	driver.findElements(By.className("ext-webkit.ext-chromehg"));
	List<WebElement> profileinfo = driver.findElements(By.className("story-field"));	
	for(int i=1; i<profileinfo.size(); i+=2){
		String b = (profileinfo.get(i).getText());		
		String datePattern = "\\d{2}/\\d{2}/\\d{4}";
		String date1 = b;
		Boolean isDate1 = date1.matches(datePattern);
	Assert.assertTrue(isDate1);	
	}
	driver.switchTo().defaultContent();
	}
	
	@Test
	public void TS7108_ValidationCustomerTransactionsViewFilter (){

	try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	List<WebElement> frame1 = driver.findElements(By.tagName("iframe"));
	driver.switchTo().frame(frame1.get(5));
	driver.findElements(By.className("ext-webkit.ext-chromehg"));
	driver.findElement(By.id("text-input-01"));	
	driver.switchTo().defaultContent();
}
	
	@Test
	public void TS7110_ValidationCustomerTransactionsScroll(){

	try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
List<WebElement> frame1 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame1.get(5));
	((JavascriptExecutor)driver).executeScript("scroll(0,400)");
	JavascriptExecutor javascript = (JavascriptExecutor) driver;
	Boolean VertscrollStatus = (Boolean) javascript.executeScript("return document.documentElement.scrollHeight>document.documentElement.clientHeight;");
	assertTrue(VertscrollStatus);
	driver.switchTo().defaultContent();
	}
	
	@Test
	public void TS7113_ValidationCustomerTransactionsIconType(){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		List <WebElement> frame1 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame1.get(5));
		driver.findElement(By.className("slds-icon_container"));
		driver.switchTo().defaultContent();
	}	
}