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
 	
	@AfterClass(groups = "CustomerCare")
	public void tearDown() {
		driver.quit();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}

	//@AfterMethod(groups = "CustomerCare")
	public void alert (){
		driver.get("https://cs14.salesforce.com/home/home.jsp");
		try{
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}catch(org.openqa.selenium.NoAlertPresentException e){}
	}
	
	@BeforeClass(groups = "CustomerCare")
	public void init() throws Exception{
		this.driver = setConexion.setupEze();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		login(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	 	String a = driver.findElement(By.id("tsidLabel")).getText();
	 	driver.findElement(By.id("tsidLabel")).click();
	 	if(a.equals("Ventas")){
	 		driver.findElement(By.xpath("//a[@href=\'/console?tsid=02uc0000000D6Hd\']")).click();
	 	}else{   
	 		driver.findElement(By.xpath("//a[@href=\'/home/home.jsp?tsid=02u41000000QWha\']")).click();
	 		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	 		driver.findElement(By.id("tsidLabel")).click();
	 		driver.findElement(By.xpath("//a[@href=\'/console?tsid=02uc0000000D6Hd\']")).click();
	 	}
	 } 
	 
	 @BeforeMethod(groups = "CustomerCare")
	 public void setup(){
		 try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		 CustomerCare page = new CustomerCare(driver);
		 page.cerrarultimapestaña();
	 }

	 
	@Test(groups = "CustomerCare")
	public void TS7069_ValidationButtons () {
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		CustomerCare page = new CustomerCare(driver);
		page.elegircuenta("Fernando Care");
		BasePage cambioFrameByID=new BasePage();
	    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("profile-box")));
		List<WebElement> botones = driver.findElements(By.className("profile-box"));
		for (WebElement x : botones) {
			Assert.assertTrue(x.getText().toLowerCase().contains("actualizar datos"));
			Assert.assertTrue(x.getText().toLowerCase().contains("reseteo clave"));
		}
		driver.switchTo().defaultContent();
	}

	
	@Test(groups = "CustomerCare")
	public void TS7068_ValidationFields (){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		CustomerCare page = new CustomerCare(driver);
		page.elegircuenta("Fernando Care");
		List <WebElement> element = driver.findElements(By.className("acct-info"));
		for (WebElement x : element) {
			Assert.assertTrue(x.getText().toLowerCase().contains("correo electrónico"));
			Assert.assertTrue(x.getText().toLowerCase().contains("teléfono"));
			Assert.assertTrue(x.getText().toLowerCase().contains("club personal"));
			Assert.assertTrue(x.getText().toLowerCase().contains("categoría"));
		}
		driver.switchTo().defaultContent();
	}

	
	@Test(groups = "CustomerCare")
	public void TS7070_ValidationClubPersonalBasico (){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		CustomerCare page = new CustomerCare(driver);
		page.elegircuenta("Andres Care");
		List<WebElement> profileinfo = driver.findElements(By.className("acct-info"));
		for (WebElement x : profileinfo) {
			Assert.assertTrue(x.getText().toLowerCase().contains("básico"));
		}
		driver.switchTo().defaultContent();
	}

	
	@Test(groups = "CustomerCare")
	public void TS7072_ValidationNPS(){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		CustomerCare page = new CustomerCare(driver);
		page.elegircuenta("Fernando Care");
		BasePage cambioFrameByID=new BasePage();
	    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("account-detail-content")));
		List <WebElement> profileinfo = driver.findElements(By.className("account-detail-content"));
		for(int i=1; i<20; i++){
			String b = Integer.toString(i);
			Assert.assertFalse(profileinfo.get(0).getText().contains("-"+b));
		}
		driver.switchTo().defaultContent();
	}

	
	@Test(groups = "CustomerCare")
	public void TS7071_ValidationClubPersonalPremium (){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		CustomerCare page = new CustomerCare(driver);
		page.elegircuenta("Andres Care");
		List<WebElement> profileinfo = driver.findElements(By.className("acct-info"));
		for (WebElement x : profileinfo) {
			Assert.assertTrue(x.getText().toLowerCase().contains("premium"));
		}
		driver.switchTo().defaultContent();
	}

	
	@Test(groups = "CustomerCare")
	public void TS7126_ValidationPerfilPanel (){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		CustomerCare page = new CustomerCare(driver);
		page.elegircuenta("Fernando Care");
		BasePage cambioFrameByID=new BasePage();
	    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("profile-box-details")));
		Assert.assertTrue(driver.findElement(By.className("profile-box-details")).isDisplayed());
	    driver.switchTo().defaultContent();
	}
	
	
	@Test(groups = "CustomerCare")
	public void TS7106_ValidationNumberEstatus (){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		CustomerCare page = new CustomerCare(driver);
		page.elegircuenta("Fernando Care");
		BasePage cambioFrameByID=new BasePage();
	    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("story-field")));
	
	
	
		//try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		//List<WebElement> frame1 = driver.findElements(By.tagName("iframe"));
		//driver.switchTo().frame(frame1.get(5));
		//driver.findElements(By.className("ext-webkit.ext-chromehg"));
		
		
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
	
	
	@Test(groups = "CustomerCare")
	public void TS7112_ValidationDateFormat (){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		CustomerCare page = new CustomerCare(driver);
		page.elegircuenta("Fernando Care");
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
	
	
	@Test(groups = "CustomerCare")
	public void TS7108_ValidationCustomerTransactionsViewFilter (){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		CustomerCare page = new CustomerCare(driver);
		page.elegircuenta("Fernando Care");
		BasePage cambioFrameByID=new BasePage();
	    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("profile-box-details")));
		driver.findElement(By.id("text-input-01"));	
		driver.switchTo().defaultContent();
	}
	
	
	@Test(groups = "CustomerCare")
	public void TS7110_ValidationCustomerTransactionsScroll(){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		CustomerCare page = new CustomerCare(driver);
		page.elegircuenta("Fernando Care");
		BasePage cambioFrameByID=new BasePage();
	    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("profile-box-details")));
	    ((JavascriptExecutor)driver).executeScript("scroll(0,400)");
	    JavascriptExecutor javascript = (JavascriptExecutor) driver;
	    Boolean VertscrollStatus = (Boolean) javascript.executeScript("return document.documentElement.scrollHeight>document.documentElement.clientHeight;");
	    assertTrue(VertscrollStatus);
	    driver.switchTo().defaultContent();
	}
	
	
	@Test(groups = "CustomerCare")
	public void TS7113_ValidationCustomerTransactionsIconType(){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		CustomerCare page = new CustomerCare(driver);
		page.elegircuenta("Fernando Care");
		BasePage cambioFrameByID=new BasePage();
	    driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.className("profile-box-details")));
		driver.findElement(By.className("slds-icon_container"));
		driver.switchTo().defaultContent();
	}
}