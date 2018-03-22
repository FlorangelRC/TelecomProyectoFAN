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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
	}
	
	@Test(groups={"Sales", "AltaDeContacto", "Ola1"}) 
	public void TS_Alta_De_Lineas(){
		
	}
	
	
}
