package Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.ContactSearch;
import Pages.CustomerCare;
import Pages.SalesBase;
import Pages.setConexion;

class linea{
	String tProducto, mercado, plan, segmento, iva, iibb, provincia;
	int cantidad;
	
	linea(XSSFRow row){
		tProducto = row.getCell(1).getStringCellValue();
		mercado = row.getCell(2).getStringCellValue();
		plan = row.getCell(3).getStringCellValue();
		segmento = row.getCell(4).getStringCellValue();
		iva = row.getCell(5).getStringCellValue();
		if (!row.getCell(6).getStringCellValue().isEmpty())
			iibb = row.getCell(6).getStringCellValue();
		else
			iibb = "";
		provincia = row.getCell(8).getStringCellValue(); 
		Double x = row.getCell(13).getNumericCellValue();
		cantidad = Integer.parseInt(x.toString().substring(0, x.toString().length()-2));
	}
	
	void VerLinea() {
		System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
		System.out.println("Tipo de producto: "+tProducto);
		System.out.println("Mercado: "+mercado);
		System.out.println("Plan: "+plan);
		System.out.println("Segmento: "+segmento);
		System.out.println("IVA: "+iva);
		System.out.println("IIBB: "+iibb);
		System.out.println("Provincia: "+provincia);
		System.out.println("Cantidad: "+cantidad);
	}
}

public class AltadeLineas extends TestBase {
	List<linea> lineas = new ArrayList<linea>();
	String nombre="Matias";
	String apellido="Rodriguez";
	String fNacimiento="19/08/1989";
	String calle="Santa Fe";
	String CP= "1609";
	String altura="1234";
	protected WebDriver driver;
	protected  WebDriverWait wait;
	
	@BeforeClass(alwaysRun=true)
	public void setup() throws Exception {		
		this.driver = setConexion.setupEze();
		 wait = new WebDriverWait(driver, 10);
		 loginAndres(driver);
		 sleep(5000);
		
	}
	
	@BeforeMethod(alwaysRun=true)
	 void cargarDatos() throws IOException {
		 File archivo = new File("C:\\Users\\florangel\\Desktop\\Altas_Necesarias.xlsx");
		 FileInputStream file = new FileInputStream(archivo);
	     XSSFWorkbook workbook = new XSSFWorkbook(file); 
	     XSSFSheet sheet = workbook.getSheetAt(0);
	     Iterator<Row> rows = sheet.rowIterator();
	     rows.next();
		 while (rows.hasNext()) {
		     XSSFRow row = (XSSFRow) rows.next();
		     lineas.add(new linea(row));
		 }
		 workbook.close();
		 for (linea UnaL : lineas) {
			 UnaL.VerLinea();
		 }
		 try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		 driver.findElement(By.xpath("//a[@href=\'https://crm--sit--c.cs14.visual.force.com/apex/taClientSearch']")).click();
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		 
	}
	
	@Test(groups={"Sales", "AltaDeContacto", "Ola1"}) 
	//public void TS_Alta_De_Lineas(String sProducto, String sPlan, String sIva, String sProvincia
	public void TS_Alta_De_Lineas(){
		SalesBase SB = new SalesBase(driver);
		SB.BtnCrearNuevoCliente();
		String asd = driver.findElement(By.id("SearchClientDocumentNumber")).getAttribute("value");
		CustomerCare CC = new CustomerCare(driver);
		ContactSearch contact = new ContactSearch(driver);
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("FirstName")).sendKeys(nombre);
		driver.findElement(By.id("LastName")).sendKeys(apellido);
		driver.findElement(By.id("Birthdate")).sendKeys(fNacimiento);
		contact.sex("masculino");
		driver.findElement(By.id("Contact_nextBtn")).click();
		try {Thread.sleep(8000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		sleep(15000);
		SB.elegirplan("Plan con Tarjeta Repro");
		SB.continuar();
		sleep(20000);
		SB.Crear_DomicilioLegal("Buenos Aires","ab", calle, "", altura, "", "", CP);
		sleep(15000);
		driver.findElement(By.id("LineAssignment_nextBtn")).click();
		sleep(10000);
		
	}
	
	
}
