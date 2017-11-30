package Tests;


import org.openqa.selenium.By;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import Pages.setConexion;


public class SmokeTest extends TestBase  {

	
	private WebDriver driver;
	@AfterTest(groups="Fase2")
	public void tearDown() {
			driver.close();
	}
	
	@AfterMethod(groups="Fase2")
	public void home(){
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		String a = driver.findElement(By.id("tsidLabel")).getText();
		if(!a.equals("Ventas")){
		driver.findElement(By.id("tsidLabel")).click();
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.xpath("//a[@href=\'/home/home.jsp?tsid=02u41000000QWha\']")).click();}
	}

	
	
	@Test(groups="Fase2")
	public void TS1_Login(){
			this.driver = setConexion.setupEze();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			login(driver);
			System.out.println("Login = OK");}
	
	@Test(groups="Fase2")
	public void TS2_Modulo_Ventas(){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		String a = driver.findElement(By.id("tsidLabel")).getText();
		if(a.equals("Ventas")){
		System.out.println("Modulo Ventas = OK");}
		else{driver.findElement(By.id("tsidLabel")).click();
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.xpath("//a[@href=\'/home/home.jsp?tsid=02u41000000QWha\']")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		String b = driver.findElement(By.id("tsidLabel")).getText();
		Assert.assertTrue(b.equals("Ventas"));
		Assert.assertTrue(
				driver.findElement(By.id("Contact_Tab")).isEnabled()&&
				driver.findElement(By.id("Account_Tab")).isEnabled()&&
				driver.findElement(By.id("Lead_Tab")).isEnabled()&&
				driver.findElement(By.id("Opportunity_Tab")).isEnabled()&&
				driver.findElement(By.id("Forecasting3_Tab")).isEnabled()&&
				driver.findElement(By.id("report_Tab")).isEnabled());
		System.out.println("Modulo Ventas = OK");}
	}
	
	@Test(groups="Fase2")
	public void TS3_Consola_FAN(){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		String a = driver.findElement(By.id("tsidLabel")).getText();
		if(a.equals("Consola FAN")){
		System.out.println("Modulo Consola FAN = OK");}
		else{driver.findElement(By.id("tsidLabel")).click();
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.xpath("//a[@href=\'/console?tsid=02uc0000000D6Hd\']")).click();
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		Assert.assertTrue(driver.findElement(By.className("sd_custom_logo")).isEnabled());
		System.out.println("Modulo Consola FAN = OK");}
	}
	
	@Test(groups="Fase2")
	public void TS4_SCP(){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		String a = driver.findElement(By.id("tsidLabel")).getText();		
		if(a.equals("SCP")){
		System.out.println("Modulo SCP= OK");}
		else{driver.findElement(By.id("tsidLabel")).click();
		try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.xpath("//a[@href=\'/home/home.jsp?tsid=02uc000000093iX\']")).click();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		String b = driver.findElement(By.id("tsidLabel")).getText();
		Assert.assertTrue(b.equals("SCP"));
		Assert.assertTrue(driver.findElement(By.className("wt-Strategic-Client-Plan")).isEnabled());
		System.out.println("Modulo SCP = OK");}
		
	}
}
