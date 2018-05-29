package Tests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.CustomerCare;
import Pages.OM;
import Pages.SCP;
import Pages.setConexion;

public class OMN extends TestBase {

	private WebDriver driver;
	protected OM om;
	protected SCP scp;
	protected BasePage bp;


	@BeforeClass (alwaysRun = true)
	public void init() {
		driver = setConexion.setupEze();
		sleep(5000);
		login(driver, "https://crm--sit.cs14.my.salesforce.com/", "U585991", "Testa10k");
		sleep(5000);
		om = new OM(driver);
		bp = new BasePage(driver);
	}
	
	@BeforeMethod (alwaysRun = true)
	public void before() {
		driver.switchTo().defaultContent();
		sleep(2000);
		bp.cajonDeAplicaciones("Sales");
		sleep(5000);
		driver.findElement(By.id("Order_Tab")).click();
		sleep(3000);
	}
	
	//@AfterClass (alwaysRun = true)
	public void quit() {
		driver.quit();
		sleep(5000);
	}
	
	
	@Test (groups = "OM")
	public void TS6729_Ordenes_Order_Detail_Adjunto_de_archivos_Formato_JPG() {
		om.primeraOrden();
		driver.findElement(By.name("attachFile")).click();
		sleep(5000);
		boolean a = false;
		if (driver.findElement(By.id("file")).isEnabled()) {
			driver.findElement(By.id("file")).sendKeys("C:\\Users\\Nicolas\\Desktop\\Archivos\\jpg.jpg");
			a = true;
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = "OM")
	public void TS6730_Ordenes_Order_Detail_Adjunto_de_archivos_Formato_PNG() {
		om.primeraOrden();
		driver.findElement(By.name("attachFile")).click();
		sleep(5000);
		boolean a = false;
		if (driver.findElement(By.id("file")).isEnabled()) {
			driver.findElement(By.id("file")).sendKeys("C:\\Users\\Nicolas\\Desktop\\Archivos\\png.png");
			a = true;
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = "OM")
	public void TS6731_Ordenes_Order_Detail_Adjunto_de_archivos_Formato_PDF() {
		om.primeraOrden();
		driver.findElement(By.name("attachFile")).click();
		sleep(5000);
		boolean a = false;
		if (driver.findElement(By.id("file")).isEnabled()) {
			driver.findElement(By.id("file")).sendKeys("C:\\Users\\Nicolas\\Desktop\\Archivos\\pdf.pdf");
			a = true;
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = "OM")
	public void TS6732_Ordenes_Order_Detail_Adjunto_de_archivos_Formato_XML() {
		om.primeraOrden();
		driver.findElement(By.name("attachFile")).click();
		sleep(5000);
		boolean a = false;
		if (driver.findElement(By.id("file")).isEnabled()) {
			driver.findElement(By.id("file")).sendKeys("C:\\Users\\Nicolas\\Desktop\\Archivos\\xml.xml");
			a = true;
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = "OM")
	public void TS6733_Ordenes_Order_Detail_Adjunto_de_archivos_Formato_TXT() {
		om.primeraOrden();
		driver.findElement(By.name("attachFile")).click();
		sleep(5000);
		boolean a = false;
		if (driver.findElement(By.id("file")).isEnabled()) {
			driver.findElement(By.id("file")).sendKeys("C:\\Users\\Nicolas\\Desktop\\Archivos\\txt.txt");
			a = true;
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = "OM")
	public void TS6734_Ordenes_Order_Detail_Adjunto_de_archivos_Formato_XLS() {
		om.primeraOrden();
		driver.findElement(By.name("attachFile")).click();
		sleep(5000);
		boolean a = false;
		if (driver.findElement(By.id("file")).isEnabled()) {
			driver.findElement(By.id("file")).sendKeys("C:\\Users\\Nicolas\\Desktop\\Archivos\\xls.xlsm");
			a = true;
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = "OM")
	public void TS6735_Ordenes_Order_Detail_Adjunto_de_archivos_Formato_DOC() {
		om.primeraOrden();
		driver.findElement(By.name("attachFile")).click();
		sleep(5000);
		boolean a = false;
		if (driver.findElement(By.id("file")).isEnabled()) {
			driver.findElement(By.id("file")).sendKeys("C:\\Users\\Nicolas\\Desktop\\Archivos\\doc.docx");
			a = true;
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = "OM")
	public void TS6736_Ordenes_Order_Detail_Adjunto_de_archivos_Formato_VSO() {
		om.primeraOrden();
		driver.findElement(By.name("attachFile")).click();
		sleep(5000);
		boolean a = false;
		if (driver.findElement(By.id("file")).isEnabled()) {
			driver.findElement(By.id("file")).sendKeys("C:\\Users\\Nicolas\\Desktop\\Archivos\\vso.vso");
			a = true;
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = "OM")
	public void TS6737_Ordenes_Order_Detail_Adjunto_de_archivos_Varios_formatos() {
		om.primeraOrden();
		driver.findElement(By.name("attachFile")).click();
		sleep(5000);
		boolean a = false;
		if (driver.findElement(By.id("file")).isEnabled()) {
			driver.findElement(By.id("file")).sendKeys("C:\\Users\\Nicolas\\Desktop\\Archivos\\doc.docx");
			sleep(2000);
			driver.findElement(By.id("file")).sendKeys("C:\\Users\\Nicolas\\Desktop\\Archivos\\xls.xlsm");
			sleep(2000);
			driver.findElement(By.id("file")).sendKeys("C:\\Users\\Nicolas\\Desktop\\Archivos\\txt.txt");
			sleep(2000);
			driver.findElement(By.id("file")).sendKeys("C:\\Users\\Nicolas\\Desktop\\Archivos\\xml.xml");
			sleep(2000);
			driver.findElement(By.id("file")).sendKeys("C:\\Users\\Nicolas\\Desktop\\Archivos\\pdf.pdf");
			sleep(2000);
			driver.findElement(By.id("file")).sendKeys("C:\\Users\\Nicolas\\Desktop\\Archivos\\png.png");
			sleep(2000);
			driver.findElement(By.id("file")).sendKeys("C:\\Users\\Nicolas\\Desktop\\Archivos\\jpg.jpg");
			sleep(2000);
			a = true;
		}
		Assert.assertTrue(a);
	}
	
	@Test (groups = "OM")
	public void TS6740_Ordenes_Order_Detail_Links_funcionales() {
		om.primeraOrden();
		driver.findElement(By.name("attachFile")).click();
		sleep(5000);
		driver.findElement(By.id("file")).sendKeys("C:\\Users\\Nicolas\\Desktop\\Archivos\\jpg.jpg");
		driver.findElement(By.id("Attach")).click();
		sleep(3000);
		driver.findElement(By.name("cancel")).click();
		sleep(5000);
		WebElement row = driver.findElements(By.cssSelector(".dataRow.even.last.first")).get(0);
		WebElement file = row.findElements(By.tagName("td")).get(1);
		System.out.println(file.getText());
		//file.click();
	}
}