package Tests;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.ContactSearch;
import Pages.CustomerCare;
import Pages.SalesBase;
import Tests.TestBase.IrA;

public class Sales2 extends TestBase{

	SalesBase sb;
	String DNI = "DNI";
	String provincia="Chaco" ;
	String localidad="BASAIL";
	
	//@AfterClass(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}
	
	//@AfterMethod(alwaysRun=true)
	public void deslogin() {
		sleep(3000);
		driver.get("https://crm--sit.cs14.my.salesforce.com/home/home.jsp?tsid=02u41000000QWha/");
		sleep(10000);
	}
		
	@BeforeClass(alwaysRun=true)
	public void init() {
		inicializarDriver();
		sb = new SalesBase(driver);
		loginAndres(driver);
		IrA.CajonDeAplicaciones.Ventas();
	}
	
	@BeforeMethod(alwaysRun=true)
	public void setup() throws Exception {
		sleep(5000);
		driver.findElement(By.xpath("//a[@href=\'https://crm--sit--c.cs14.visual.force.com/apex/taClientSearch']")).click();
		sleep(7000);
	}
	
	@Test(groups={"Sales", "NuevaVenta", "Ola1"})
	public void TS94698_Nueva_Venta_Modo_de_Entrega_Verificar_Solicitud_de_Domicilio_de_envio_Envio_Estandar(){
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		boolean x = false;
		sleep(15000);
		List<WebElement> cam = driver.findElements(By.cssSelector(".slds-m-left--x-small.slds-button.slds-button--brand"));
		for(WebElement c : cam ){	
			if(c.getText().toLowerCase().equals("cambiar")){
				c.click();
			}
		sleep(7000);	
		List<WebElement> frame2 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame2.get(0));
		Select env = new Select (driver.findElement(By.id("DeliveryMethodSelection")));
		env.selectByVisibleText("Delivery");
		driver.findElement(By.id("SalesChannelConfiguration_nextBtn")).click();
		sleep(10000);
		driver.switchTo().defaultContent();
		}
		sb.elegirplan("Plan con Tarjeta Repro");
		sb.continuar();
		sleep(10000);
		List<WebElement> cont = driver.findElements(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand"));
		for(WebElement c : cont){
			c.getText().equals("Continuar");
			c.click();
				
		}
		sleep(5000);
		CustomerCare page = new CustomerCare(driver);
		WebElement sig = driver.findElement(By.id("LineAssignment_nextBtn"));
		page.obligarclick(sig);
		Select delir= new Select (driver.findElement(By.id("DeliveryServiceType")));
		delir.selectByVisibleText("Env\u00edo Est\u00e1ndar");	
		Assert.assertEquals(delir.getFirstSelectedOption().getText(),"Env\u00edo Est\u00e1ndar");
	}
	
	@Test(groups = { "Sales", "NuevaVenta", "Ola1" })
	public void TS94699_Nueva_Venta_Modo_de_Entrega_Verificar_Solicitud_de_Domicilio_de_envio_Envio_Express() {
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		boolean x = false;
		sleep(15000);
		List<WebElement> cam = driver.findElements(By.cssSelector(".slds-m-left--x-small.slds-button.slds-button--brand"));
		for (WebElement c : cam) {
			if (c.getText().toLowerCase().equals("cambiar")) {
				c.click();
			}
			sleep(7000);
			List<WebElement> frame2 = driver.findElements(By.tagName("iframe"));
			driver.switchTo().frame(frame2.get(0));
			Select env = new Select(driver.findElement(By.id("DeliveryMethodSelection")));
			env.selectByVisibleText("Delivery");
			driver.findElement(By.id("SalesChannelConfiguration_nextBtn")).click();
			sleep(10000);
			driver.switchTo().defaultContent();
		}
		sb.elegirplan("Plan con Tarjeta Repro");
		sb.continuar();
		sleep(10000);
		List<WebElement> cont = driver.findElements(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand"));
		for (WebElement c : cont) {
			c.getText().equals("Continuar");
			c.click();

		}
		sleep(5000);
		CustomerCare page = new CustomerCare(driver);
		WebElement sig = driver.findElement(By.id("LineAssignment_nextBtn"));
		page.obligarclick(sig);
		Select delir = new Select(driver.findElement(By.id("DeliveryServiceType")));
		delir.selectByVisibleText("Env\u00edo Express");
		Assert.assertEquals(delir.getFirstSelectedOption().getText(), "Env\u00edo Express");
	}
	
	@Test(groups={"Sales", "AltaDeContacto", "Ola1"})
	public void TS94880_Alta_De_Contacto_Busqueda_Verificar_Accion_De_Ver_Detalle_De_Contacto(){//dentro del ver detalles no se muestran las opciones de actualizar ni lanzar carrito
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta(DNI, "34073329");
		driver.findElement(By.id("tab-scoped-3__item")).click();
		SB.acciondecontacto("ver contacto");
		Assert.assertTrue(false);
	}
	
	@Test(groups={"Sales", "AltaDeContacto", "Ola1"})
	public void TS94590_Alta_De_Contacto_Persona_Fisica_Verificar_Categoria_Impositiva_Por_Default(){
		sb.BuscarCuenta(DNI, "");
		driver.findElement(By.id("tab-scoped-3__item")).click();
		sb.acciondecontacto("catalogo");
		boolean x = false;
		sleep(15000);
		List<WebElement> cam = driver.findElements(By.cssSelector(".slds-m-left--x-small.slds-button.slds-button--brand"));
		for(WebElement c : cam ){	
			if(c.getText().toLowerCase().equals("cambiar")){
				c.click();
			}
		sleep(7000);	
		List<WebElement> frame2 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame2.get(0));
		Select env = new Select (driver.findElement(By.id("DeliveryMethodSelection")));
		env.selectByVisibleText("Delivery");
		driver.findElement(By.id("SalesChannelConfiguration_nextBtn")).click();
		sleep(10000);
		driver.switchTo().defaultContent();
		}
		sb.elegirplan("Plan con Tarjeta Repro");
		sleep(15000);
		sb.continuar();
		sleep(5000);
		Select condI = new Select(driver.findElement(By.id("ImpositiveCondition")));
		Assert.assertTrue(condI.getFirstSelectedOption().getText().equalsIgnoreCase("iva consumidor final"));
	}
	
	@Test(groups={"Sales", "AltaDeCuenta", "Ola1"})
	public void TS95515_Alta_de_Cuenta_Business_Visualizar_los_campos_de_documentacion_impositiva_abajo() {
		sb.BuscarCuenta(DNI, "11111111");
		sb.acciondecontacto("nueva cuenta");
		sleep(7000);
		BasePage imp = new BasePage(driver);
		imp.setSimpleDropdown(driver.findElement(By.id("ImpositiveCondition")), "IVA Responsable Inscripto");
		sleep(2000);
		WebElement dom = driver.findElement(By.id("State"));
		WebElement doc = driver.findElement(By.id("ImageDNI"));
		Assert.assertTrue(doc.getLocation().y > dom.getLocation().y);
	}
	
	@Test(groups={"Sales", "Ventas", "Ola1"})  //Falta terminar los pasos despues del carrito
	public void TS94637_Ventas_Nueva_Venta_Verificar_creacion_orden_de_venta_Usuario() {
		sb.BuscarCuenta(DNI, "11111111");
		sb.acciondecontacto("catalogo");
		sb.agregarplan("plan con tarjeta");
		Assert.assertTrue(false);
	}
	
	@Test(groups={"Sales", "Ventas", "Ola1"})
	public void TS94652_Ventas_NumeroOrden_Verificar_Orden_de_Venta_Abierta_Modo_de_Entrega() {
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		sleep(15000);
		driver.findElement(By.cssSelector(".slds-m-left--x-small.slds-button.slds-button--brand")).click();
		sleep(7000);
		driver.switchTo().frame(cambioFrame(driver, By.id("DeliveryMethodSelection")));
		Select env = new Select (driver.findElement(By.id("DeliveryMethodSelection")));
		env.selectByVisibleText("Delivery");
		driver.findElement(By.id("SalesChannelConfiguration_nextBtn")).click();
		sleep(10000);
		driver.switchTo().defaultContent();
		sb.elegirplan("Plan con Tarjeta Repro");
		sleep(15000);
		driver.findElement(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand")).click();
		sleep(15000);
		driver.findElement(By.id("LineAssignment_nextBtn")).click();
		sleep(10000);
		List <WebElement> num = driver.findElements(By.className("slds-form-element__control"));
		boolean a = false;
		for (WebElement x : num) {
			if (x.getText().contains("Nro. orden:")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test(groups={"Sales", "Ventas", "Ola1"})
	public void TS94651_Ventas_NumeroOrden_Verificar_Orden_de_Venta_Abierta_Seleccion_de_Linea() {
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		sb.elegirplan("Plan con Tarjeta Repro");
		sleep(15000);
		driver.findElement(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand")).click();
		sleep(15000);
		List <WebElement> num = driver.findElements(By.className("slds-form-element__control"));
		boolean a = false;
		for (WebElement x : num) {
			if (x.getText().contains("Nro. orden:")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test(groups={"Sales", "Ventas", "Ola1"})  
	public void TS94650_Ventas_NumeroOrden_Verificar_Orden_de_Venta_Abierta_Seleccionar_un_producto() {
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		sleep(15000);
		List<WebElement> cam = driver.findElements(By.cssSelector(".slds-m-left--x-small.slds-button.slds-button--brand"));
			for(WebElement c : cam ){	
				if(c.getText().toLowerCase().equals("cambiar")){
				c.click();
			}
		sleep(7000);	
		List<WebElement> frame2 = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frame2.get(0));
		Select env = new Select (driver.findElement(By.id("DeliveryMethodSelection")));
		env.selectByVisibleText("Store Pick Up");
		sleep(2000);
		Select sta = new Select (driver.findElement(By.id("State")));
		sta.selectByVisibleText("Ciudad Aut\u00f3noma de Buenos Aires");
		sleep(2000);
		Select cit = new Select(driver.findElement(By.id("City")));
		cit.selectByVisibleText("CIUD AUTON D BUENOS AIRES");
		sleep(3000);
		driver.findElement(By.id("Store")).click();
		driver.findElements(By.cssSelector(".slds-list__item.ng-binding.ng-scope")).get(1).click();
		sleep(2000);
		driver.findElement(By.id("SalesChannelConfiguration_nextBtn")).click();
		sleep(10000);
		driver.switchTo().defaultContent();
		}
		sb.elegirplan("Plan con Tarjeta Repro");
		sb.continuar();
		sleep(10000);
		List<WebElement> cont = driver.findElements(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand"));
			for(WebElement c : cont){
				c.getText().equals("Continuar");
					c.click();
			}
		sleep(5000);
		CustomerCare page = new CustomerCare(driver);
		WebElement sig = driver.findElement(By.id("LineAssignment_nextBtn"));
		page.obligarclick(sig);
		sleep(7000);
		Assert.assertFalse(driver.findElement(By.id("DeliveryMethod")).isEnabled());
		
	
		
	}
	
	@Test(groups={"Sales", "Ventas", "Ola1"})  
	public void TS94646_Ventas_NumeroOrden_Verificar_Orden_de_Venta_Abierta_Medio_de_Pago() {
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		sb.elegirplan("Plan con Tarjeta Repro");
		sb.continuar();
		sleep(10000);
		List<WebElement> cont = driver.findElements(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand"));
			for(WebElement c : cont){
				c.getText().equals("Continuar");
					c.click();
			}
		sleep(5000);
		CustomerCare page = new CustomerCare(driver);
		WebElement sig = driver.findElement(By.id("LineAssignment_nextBtn"));
		page.obligarclick(sig);
		sleep(8000);
		driver.findElement(By.id("ICCDAssignment_nextBtn")).click();
		sleep(8000);
		WebElement sigue = driver.findElement(By.id("InvoicePreview_nextBtn"));
		page.obligarclick(sigue);
		sleep(15000);
		boolean x = false;
		List<WebElement> ord = driver.findElements(By.cssSelector(".ng-binding"));
			for(WebElement o : ord){
				if(	o.getText().contains("Nro. orden")){
				o.isDisplayed();
				x=true;
				}
			}
		Assert.assertTrue(x);
	}
	
	@Test(groups={"Sales", "Ventas", "Ola1"})
	public void TS94641_Ventas_NumeroOrden_Verificar_Orden_de_Venta_Abierta_Nueva_Venta() {
		sb.BuscarCuenta(DNI, "11111111");
		sb.acciondecontacto("catalogo");
		sleep(15000);
		WebElement num = driver.findElement(By.cssSelector(".slds-text-body--small.slds-page-header__info.taDevider"));
		Assert.assertTrue(num.getText().contains("Nro. de Orden:"));
	}
	
	@Test(groups={"Sales", "Ventas", "Ola1"})
	public void TS94643_Ventas_NumeroOrden_Verificar_Orden_de_Venta_Abierta_Seleccion_de_Linea() {
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		sleep(15000);
		sb.elegirplan("Plan con Tarjeta Repro");
		sleep(15000);
		driver.findElement(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand")).click();
		sleep(10000);
		List <WebElement> num = driver.findElements(By.className("slds-form-element__control"));
		boolean a = false;
		for (WebElement x : num) {
			if (x.getText().contains("Nro. orden:")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test(groups={"Sales", "Ventas", "Ola1"})  //Falta terminar, no se puede crear venta desde la V360
	public void TS94639_Ventas_Nueva_Venta_Verificar_creacion_orden_de_venta_desde_un_Asset_Usuario() {
		driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).click();
		sleep(2000);
		driver.findElement(By.id("alert-ok-button")).click();
		sleep(5000);
		IrA.CajonDeAplicaciones.ConsolaFAN();
		CustomerCare cc = new CustomerCare(driver);
		sb.cerrarTodasLasPestanias();
		cc.elegirCuenta("aaaaRaul Care");
		Assert.assertTrue(false);
	}
	
	@Test(groups={"Sales", "Ventas", "Ola1"})
	public void TS94716_Ventas_VentasGestiones_Visualizar_un_historico_de_gestiones_realizadas() {
		driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).click();
		sleep(2000);
		driver.findElement(By.id("alert-ok-button")).click();
		sleep(5000);
		IrA.CajonDeAplicaciones.ConsolaFAN();
		CustomerCare cc = new CustomerCare(driver);
		sb.cerrarTodasLasPestanias();
		cc.elegirCuenta("aaaaRaul Care");
		driver.switchTo().frame(cambioFrame(driver, By.cssSelector(".slds-media.slds-media--timeline.slds-timeline__media--custom-custom91")));
		List <WebElement> gest = driver.findElements(By.cssSelector(".slds-media.slds-media--timeline.slds-timeline__media--custom-custom91"));
		boolean a = false;
		boolean b = false;
		for (WebElement x : gest) {
			if (x.getText().toLowerCase().contains("t\u00edtulo")) {
				a = true;
			}
			if (x.getText().toLowerCase().contains("status")) {
				b = true;
			}
		}
		Assert.assertTrue(a && b);
	}
	
	@Test(groups={"Sales", "AltaDeLinea", "Ola1"})
	public void TS94611_Alta_Linea_Nueva_Venta_Verificar_acceso_a_Nueva_Venta_desde_vista_360() {
		driver.findElement(By.cssSelector(".vlc-slds-button--tertiary.ng-binding.ng-scope")).click();
		sleep(2000);
		driver.findElement(By.id("alert-ok-button")).click();
		sleep(5000);
		IrA.CajonDeAplicaciones.ConsolaFAN();
		CustomerCare cc = new CustomerCare(driver);
		sb.cerrarTodasLasPestanias();
		cc.elegirCuenta("aaaaRaul Care");
		WebElement nv = driver.findElement(By.cssSelector(".console-card.open"));
		Assert.assertTrue(nv.getText().toLowerCase().contains("nueva venta"));
	}
	
	@Test(groups={"Sales", "AltaDeCuenta", "Ola1"})
	public void TS94972_Alta_Cuenta_Busqueda_Verificar_flujo_de_cuenta_Clonada() {
		sb.BuscarCuenta(DNI, "11111111");
		sb.acciondecontacto("catalogo");
		sb.agregarplan("plan con tarjeta");
		sleep(15000);
		driver.findElement(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand")).click();
		sleep(10000);
		Assert.assertTrue(driver.findElement(By.id("ContactName")).getAttribute("value").toLowerCase().contains("adela sales"));
		List <WebElement> prov = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		boolean a = false;
		for (WebElement x : prov) {
			if (x.getText().toLowerCase().contains("provincia")) {
				a = true;
			}
		}
		Assert.assertTrue(a);
	}
	
	@Test(groups={"Sales", "AltaDeContacto", "Ola1"})
	public void TS94582_Alta_de_Contacto_Persona_Fisica_Verificar_confirmacion_de_adjunto_exitoso_XX() {
		sb.BuscarCuenta(DNI, "11111111");
		sb.acciondecontacto("nueva cuenta");
		sleep(7000);
		driver.findElement(By.id("ImageDNI")).sendKeys("C:\\Users\\Nicolas\\Desktop\\descarga.jpg");
		sleep(3000);
		WebElement up = driver.findElement(By.cssSelector(".vlc-slds-box__max-width-80.ng-binding"));
		Assert.assertTrue(up.getText().toLowerCase().contains("descarga.jpg"));
		Assert.assertTrue(up.getText().toLowerCase().contains("5.97 kb"));
	}
	
	@Test(groups={"Sales", "AltaDeContacto", "Ola1"})
	public void TS94529_Alta_de_Contacto_Persona_Fisica_Confirmar_direccion_de_email_existente_30() {
		sb.BuscarCuenta(DNI, "11111111");
		String a = driver.findElement(By.xpath("//*[@id=\"tab-scoped-3\"]/section/div/table/tbody/tr/td[4]")).getText();
		List <WebElement> cuenta = driver.findElements(By.cssSelector(".slds-truncate.ng-binding"));
		for (WebElement x : cuenta) {
			if (x.getText().toLowerCase().contains("adela sales")) {
				x.click();
				break;
			}
		}
		sleep(7000);
		String b = driver.findElement(By.cssSelector(".slds-input.form-control.ng-pristine.ng-untouched.ng-valid.ng-not-empty.ng-dirty")).getAttribute("value");
		Assert.assertTrue(a.equals(b));
	}
	
	@Test(groups={"Sales", "Ventas", "Ola1"})
	public void TS94913_Ventas_General_Verificar_Completitud_Pendiente_para_cada_estado() {
		sb.BuscarCuenta(DNI, "11111111");
		sb.acciondecontacto("catalogo");
		sb.agregarplan("plan con tarjeta");
		sleep(15000);
		driver.findElement(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand")).click();
		sleep(7000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".list-group.vertical-steps.ng-scope")).isDisplayed());
	}
	
	@Test(groups={"Sales", "AltaDeContacto", "Ola1"})
	public void TS94530_Alta_de_Contacto_Persona_Fisica_Dar_de_alta_un_contacto_nuevo_48() {
		BasePage dni = new BasePage(driver);
		Random aleatorio = new Random(System.currentTimeMillis());
		aleatorio.setSeed(System.currentTimeMillis());
		int intAleatorio = aleatorio.nextInt(8999999)+1000000;
		dni.setSimpleDropdown(driver.findElement(By.id("SearchClientDocumentType")),"DNI");
		driver.findElement(By.id("SearchClientDocumentNumber")).click();
		driver.findElement(By.id("SearchClientDocumentNumber")).sendKeys(Integer.toString(intAleatorio));
		driver.findElement(By.id("SearchClientsDummy")).click();
		String a = driver.findElement(By.id("SearchClientDocumentNumber")).getAttribute("value");
		sleep(3000);
		List <WebElement> cc = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for (WebElement x : cc) {
			if (x.getText().toLowerCase().contains("+ crear nuevo cliente")) {
				x.click();
				break;
			}
		}
		sleep(10000);
		List <WebElement> gen = driver.findElements(By.cssSelector(".slds-radio.ng-scope"));
		for (WebElement x : gen) {
			if (x.getText().toLowerCase().contains("masculino")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("FirstName")).sendKeys("Cuenta");
		driver.findElement(By.id("LastName")).sendKeys("Generica");
		driver.findElement(By.id("Birthdate")).sendKeys("15/05/1980");
		driver.findElements(By.cssSelector(".slds-form-element__control.slds-input-has-icon.slds-input-has-icon--right")).get(2).findElement(By.tagName("input")).sendKeys("asdasd@gmail.com");
		driver.findElement(By.id("Contact_nextBtn")).click();
		sleep(10000);
		driver.get("https://crm--sit.cs14.my.salesforce.com/home/home.jsp?tsid=02u41000000QWha/");
		sleep(7000);
		driver.findElement(By.xpath("//a[@href=\'https://crm--sit--c.cs14.visual.force.com/apex/taClientSearch']")).click();
		sleep(7000);
		sb.BuscarCuenta(DNI, a);
		List <WebElement> cuenta = driver.findElements(By.cssSelector(".slds-truncate.ng-binding"));
		boolean b = false;
		for (WebElement x : cuenta) {
			if (x.getText().toLowerCase().contains("cuenta generica")) {
				b = true;
			}
		}
		WebElement verdni = driver.findElement(By.xpath("//*[@id=\"tab-scoped-3\"]/section/div/table/tbody/tr/td[3]"));
		Assert.assertTrue(b);
		Assert.assertTrue(verdni.getText().toLowerCase().contains(a));
	}
	
	@Test(groups={"Sales", "Ventas", "Ola1"})
	public void TS94696_Nueva_Venta_Modo_de_Entrega_Verificar_LOV_Tipo_de_Delivery() {
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		sleep(15000);
		driver.findElement(By.cssSelector(".slds-m-left--x-small.slds-button.slds-button--brand")).click();
		sleep(7000);
		driver.switchTo().frame(cambioFrame(driver, By.id("DeliveryMethodSelection")));
		Select env = new Select (driver.findElement(By.id("DeliveryMethodSelection")));
		env.selectByVisibleText("Delivery");
		driver.findElement(By.id("SalesChannelConfiguration_nextBtn")).click();
		sleep(10000);
		driver.switchTo().defaultContent();
		sb.elegirplan("Plan con Tarjeta Repro");
		sleep(15000);
		driver.findElement(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand")).click();
		sleep(15000);
		driver.findElement(By.id("LineAssignment_nextBtn")).click();
		sleep(10000);
		driver.findElement(By.id("DeliveryServiceType")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[text() = 'Env\u00edo Est\u00e1ndar']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//*[text() = 'Env\u00edo Express']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//*[text() = 'Retiro en Sucursal de Correo']")).isDisplayed());
		
	}
	
	@Test(groups={"Sales", "Ventas", "Ola1"})
	public void TS94690_Nueva_Venta_Modo_de_Entrega_Verificar_que_no_se_puede_cambiar_el_Modo_de_Entrega_Delivery() {
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		sleep(15000);
		driver.findElement(By.cssSelector(".slds-m-left--x-small.slds-button.slds-button--brand")).click();
		sleep(7000);
		driver.switchTo().frame(cambioFrame(driver, By.id("DeliveryMethodSelection")));
		Select env = new Select (driver.findElement(By.id("DeliveryMethodSelection")));
		env.selectByVisibleText("Delivery");
		driver.findElement(By.id("SalesChannelConfiguration_nextBtn")).click();
		sleep(10000);
		driver.switchTo().defaultContent();
		sb.elegirplan("Plan con Tarjeta Repro");
		sleep(15000);
		driver.findElement(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand")).click();
		sleep(15000);
		driver.findElement(By.id("LineAssignment_nextBtn")).click();
		sleep(10000);
		WebElement mde = driver.findElement(By.id("DeliveryMethod"));
		Assert.assertTrue(mde.getAttribute("disabled").equals("true"));
	}
	
	@Test(groups={"Sales", "Ventas", "Ola1"})
	public void TS94691_Nueva_Venta_Modo_de_Entrega_Verificar_que_no_se_puede_cambiar_el_Modo_de_Entrega_Store_Pick_Up() {
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		sleep(15000);
		driver.findElement(By.cssSelector(".slds-m-left--x-small.slds-button.slds-button--brand")).click();
		sleep(7000);
		driver.switchTo().frame(cambioFrame(driver, By.id("DeliveryMethodSelection")));
		Select env = new Select (driver.findElement(By.id("DeliveryMethodSelection")));
		env.selectByVisibleText("Store Pick Up");
		Select prov = new Select (driver.findElement(By.id("State")));
		prov.selectByVisibleText("Ciudad Aut\u00f3noma de Buenos Aires");
		Select loc = new Select (driver.findElement(By.id("City")));
		loc.selectByVisibleText("CIUD AUTON D BUENOS AIRES");
		driver.findElement(By.id("Store")).click();
		driver.findElements(By.cssSelector(".slds-list__item.ng-binding.ng-scope")).get(1).click();
		driver.findElement(By.id("SalesChannelConfiguration_nextBtn")).click();
		sleep(10000);
		driver.switchTo().defaultContent();
		sb.elegirplan("Plan con Tarjeta Repro");
		sleep(15000);
		driver.findElement(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand")).click();
		sleep(15000);
		driver.findElement(By.id("LineAssignment_nextBtn")).click();
		sleep(10000);
		WebElement mde = driver.findElement(By.id("DeliveryMethod"));
		Assert.assertTrue(mde.getAttribute("disabled").equals("true"));
	}
	
	@Test(groups={"Sales", "Ventas", "Ola1"})
	public void TS94694_Nueva_Venta_Modo_de_Entrega_Verificar_que_se_habilite_Tipo_de_Delivery() {
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		sleep(15000);
		driver.findElement(By.cssSelector(".slds-m-left--x-small.slds-button.slds-button--brand")).click();
		sleep(7000);
		driver.switchTo().frame(cambioFrame(driver, By.id("DeliveryMethodSelection")));
		Select env = new Select (driver.findElement(By.id("DeliveryMethodSelection")));
		env.selectByVisibleText("Delivery");
		driver.findElement(By.id("SalesChannelConfiguration_nextBtn")).click();
		sleep(10000);
		driver.switchTo().defaultContent();
		sb.elegirplan("Plan con Tarjeta Repro");
		sleep(15000);
		driver.findElement(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand")).click();
		sleep(15000);
		driver.findElement(By.id("LineAssignment_nextBtn")).click();
		sleep(10000);
		Assert.assertTrue(driver.findElement(By.id("DeliveryServiceType")).isEnabled());
	}
	
	@Test(groups={"Sales", "Ventas", "Ola1"})
	public void TS94730_Alta_De_Linea_Verificar_LOV_De_Modalidad_Entrega_Para_Canal_Presencial_Agentes() {
		boolean Pr = false;
		boolean Dl = false;
		boolean St = false;
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		sleep(15000);
		driver.findElement(By.cssSelector(".slds-m-left--x-small.slds-button.slds-button--brand")).click();
		sleep(7000);
		driver.switchTo().frame(sb.getFrameForElement(driver, By.id("DeliveryMethodSelection")));
		List<WebElement> OMdE = new Select(driver.findElement(By.id("SalesChannelConfiguration")).findElement(By.id("DeliveryMethodSelection"))).getOptions();
		for (WebElement UnM : OMdE) {
			if (UnM.getText().equalsIgnoreCase("presencial"))
				Pr = true;
			if (UnM.getText().equalsIgnoreCase("delivery"))
				Dl = true;
			if (UnM.getText().equalsIgnoreCase("store pick up"))
				St = true;
		}
		Assert.assertTrue(Pr&&Dl&&St);
	}
	
	@Test(groups={"Sales", "AltaDeLinea", "Ola1"})
	public void TS94733_Alta_De_Linea_Verificar_Default_De_Modalidad_Entrega_Para_Canal_Presencial_Agentes(){
		SalesBase SB = new SalesBase(driver);
		SB.BuscarCuenta(DNI, "34073329");
		SB.acciondecontacto("catalogo");
		sleep(15000);
		assertTrue(driver.findElement(By.cssSelector(".slds-col.taChangeDeliveryMethod.slds-text-body--small.slds-m-left--large")).findElement(By.tagName("strong")).getText().contains("Presencial"));
	}
	
	 @Test(groups = {"Sales", "Ventas","Ola1"})
	  public void TS94714_Ventas_BuscarCliente_Verificar_Solo_Clientes_No_Activos() {
		  driver.findElement(By.id("PhoneNumber")).sendKeys("1157572274");
		  driver.findElement(By.id("SearchClientsDummy")).click();
		  sleep(5000);
		  List <WebElement> cai = driver.findElement(By.className("slds-tabs--scoped__nav")).findElements(By.tagName("li"));
		  if (cai.get(0).isDisplayed() || !cai.get(1).isDisplayed()) {
			  Assert.assertTrue(false);
		  }
		  Assert.assertTrue(cai.get(1).findElement(By.tagName("a")).getText().contains("Inactivos"));
	 }
	
	@Test(groups={"Sales", "Ventas", "Ola1"})
	public void TS95111_Ventas_General_Verificar_Que_No_Se_Puede_Seleccionar_Una_Linea_Decisora_ProcesoVenta() {
		boolean esta = false;
		ContactSearch contact = new ContactSearch(driver);
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		sleep(15000);
		sb.elegirplan("Plan con Tarjeta Repro");
		sleep(15000);
		driver.findElement(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand")).click();
		sleep(15000);
		List<WebElement> LD = driver.findElements(By.tagName("a"));
		for (WebElement UnLD : LD) {
			System.out.println(UnLD.getText());
			if (UnLD.getText().equalsIgnoreCase("elegir como l\u00ednea decisora"))
				esta = true;
		}
		Assert.assertFalse(esta);
	}
	
	@Test(groups={"Sales", "AltaDeContacto", "Ola1"})
	public void TS94554_Alta_De_Contacto_Persona_Fisica_Verificar_Campo_Tipo_De_Documento_Por_Default() {
		boolean esta = false;
		Random aleatorio = new Random(System.currentTimeMillis());
		aleatorio.setSeed(System.currentTimeMillis());
		int intAleatorio = aleatorio.nextInt(8999999)+1000000;
		ContactSearch contact = new ContactSearch(driver);
		driver.findElement(By.id("SearchClientDocumentNumber")).sendKeys(Integer.toString(intAleatorio));
		driver.findElement(By.id("SearchClientsDummy")).click();
		sleep(5000);
		List <WebElement> cc = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for (WebElement x : cc) {
			if (x.getText().toLowerCase().contains("+ crear nuevo cliente")) {
				x.click();
				break;
			}
		}
		sleep(5000);
		System.out.println(new Select(driver.findElement(By.id("DocumentType"))).getFirstSelectedOption().getText());
		Assert.assertTrue(new Select(driver.findElement(By.id("DocumentType"))).getFirstSelectedOption().getText().equalsIgnoreCase("DNI"));
		
	}
	
	@Test(groups={"Sales", "AltaDeContacto","Ola1"})  // verificar
	public void TS94566_Alta_De_Contacto_Persona_Fisica_Verificar_Mascara_Del_Campo_CUIT(){
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		boolean as = false;
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("CUIT", "22458954", "");
		WebElement num = driver.findElement(By.id("SearchClientDocumentNumber"));
		Assert.assertTrue(num.getText().matches("\\d{2}-\\d{8}-\\d{1}"));
		List <WebElement> cc = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding"));
		for (WebElement x : cc) {
			if (x.getText().toLowerCase().contains("+ crear nuevo cliente")) {
				x.click();
				break;
			}
		}
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.id("DocumentNumber")).getAttribute("value").matches("\\d{2}-\\d{8}-\\d{1}"));
	}
	
	@Test(groups={"Sales", "Ventas", "Ola1"})
	public void TS94763_Ventas_Entregas_General_Modificar_el_lugar_de_entrega() {
		sb.BuscarCuenta(DNI, "11111111");
		sb.acciondecontacto("catalogo");
		sb.agregarplan("plan con tarjeta");
		sleep(15000);
		String a = driver.findElement(By.cssSelector(".slds-col.taChangeDeliveryMethod.slds-text-body--small.slds-m-left--large")).getText();
		driver.findElement(By.cssSelector(".slds-m-left--x-small.slds-button.slds-button--brand")).click();
		sleep(7000);
		driver.switchTo().frame(cambioFrame(driver, By.id("DeliveryMethodSelection")));
		Select env = new Select (driver.findElement(By.id("DeliveryMethodSelection")));
		env.selectByVisibleText("Delivery");
		driver.findElement(By.id("SalesChannelConfiguration_nextBtn")).click();
		sleep(7000);
		String b = driver.findElement(By.cssSelector(".slds-col.taChangeDeliveryMethod.slds-text-body--small.slds-m-left--large")).getText();
		Assert.assertTrue(!a.equals(b));
	}
	
	@Test(groups={"Sales","AltaDeLinea","Ola1"})
	public void TS94498_Alta_Linea_Configurar_Nueva_Linea_Modificar_linea_pre_asignada_ultimos_cuatro_digitos_XX(){
		CustomerCare CC = new CustomerCare(driver);
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		sleep(15000);
		sb.elegirplan("Plan con Tarjeta Repro");
		sb.continuar();
		sleep(10000);
		List<WebElement> modi = driver.findElements(By.cssSelector(".slds-form-element__label--toggleText.ng-binding"));
			for(WebElement m : modi){
				m.getText().equals("Modificar b\u00fasqueda");
					m.click();}
		WebElement digit = driver.findElement(By.id("Sufijo"));
		digit.sendKeys("7354");
		sleep(1500);
		WebElement cont = driver.findElement(By.id("ChangeNumber"));
		CC.obligarclick(cont);
		sleep(8000);
		WebElement ultnum = driver.findElement(By.cssSelector(".slds-checkbox__label.ta-cyan-color-dark")).findElements(By.tagName("span")).get(1);
		System.out.println(ultnum.getText());
		Assert.assertTrue(ultnum.getText().contains("7354"));
 	}
	
	@Test(groups={"Sales", "Configuracion", "Ola1"})  
	public void TS94807_Configuracion_Verificar_Asignacion_De_Seriales_AgentePresencial() {
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		sleep(15000);
		sb.elegirplan("Plan con Tarjeta Repro");
		sb.continuar();
		sleep(10000);
		CustomerCare page = new CustomerCare(driver);
		WebElement sig = driver.findElement(By.id("LineAssignment_nextBtn"));
		page.obligarclick(sig);
		sleep(10000);
		WebElement serial = driver.findElement(By.id("ICCIDConfiguration")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(1);
		Assert.assertTrue(!serial.findElement(By.tagName("input")).getAttribute("value").isEmpty());
	}
	
	@Test(groups={"Sales", "Ventas", "Ola1"})
	public void TS94777_Ventas_Entregas_General_Store_Pickup_Consulta_stock_por_PDV_Visualizar_campos_filtro_de_la_consulta() {
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		sleep(15000);
		driver.findElement(By.cssSelector(".slds-m-left--x-small.slds-button.slds-button--brand")).click();
		sleep(7000);
		driver.switchTo().frame(cambioFrame(driver, By.id("DeliveryMethodSelection")));
		Select env = new Select (driver.findElement(By.id("DeliveryMethodSelection")));
		env.selectByVisibleText("Store Pick Up");
		sleep(2000);
		Select prov = new Select (driver.findElement(By.id("State")));
		prov.selectByVisibleText("Ciudad Aut\u00f3noma de Buenos Aires");
		sleep(2000);
		Select loc = new Select(driver.findElement(By.id("City")));
		loc.selectByVisibleText("CIUD AUTON D BUENOS AIRES");
		sleep(3000);
		Assert.assertTrue(driver.findElement(By.id("State")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("City")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("Store")).isEnabled());
	}
	
	@Test(groups={"Sales", "Ventas", "Ola1"})  //falta validar los campos porque los campos no son opcionales
	public void TS94935_Ventas_Modo_De_Pago_Tarjeta_Verificar_Campos_Opcionales_Medio_De_Pago_TC() {
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		sleep(15000);
		sb.elegirplan("Plan con Tarjeta Repro");
		sb.continuar();
		sleep(10000);
		List<WebElement> cont = driver.findElements(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand"));
			for(WebElement c : cont){
				c.getText().equals("Continuar");
					c.click();
			}
		sleep(5000);
		CustomerCare page = new CustomerCare(driver);
		WebElement sig = driver.findElement(By.id("LineAssignment_nextBtn"));
		page.obligarclick(sig);
		sleep(10000);
		page.obligarclick(driver.findElement(By.id("ICCDAssignment_nextBtn")));
		sleep(10000);
		page.obligarclick(driver.findElement(By.id("InvoicePreview_nextBtn")));
		sleep(10000);
		driver.findElement(By.id("PaymentMethodRadio")).click();
		sleep(4000);
		List<WebElement> mediosP = driver.findElements(By.cssSelector(".slds-list__item.ng-binding.ng-scope"));
		for (WebElement UnMP : mediosP) {
			if (UnMP.getText().toLowerCase().contains("tarjeta de credito"))
				UnMP.click();
		}
		sleep(4000);
		System.out.println(driver.findElement(By.id("CardBankingEntity")).getAttribute("required"));
		Assert.assertTrue(false);
		//Assert.assertTrue(driver.findElement(By.id("CardBankingEntity")).getAttribute("required"));
	}
	
	@Test(groups={"Sales", "Ventas", "Ola1"})  //falta validar los campos porque el campo requerido no existe
	public void TS94936_Ventas_Modo_De_Pago_Tarjeta_Verificar_Campos_requeridos_Medio_De_Pago_TC() {
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		sleep(15000);
		sb.elegirplan("Plan con Tarjeta Repro");
		sb.continuar();
		sleep(10000);
		List<WebElement> cont = driver.findElements(By.cssSelector(".slds-button.slds-m-left--large.slds-button--brand.ta-button-brand"));
			for(WebElement c : cont){
				c.getText().equals("Continuar");
					c.click();
			}
		sleep(5000);
		CustomerCare page = new CustomerCare(driver);
		WebElement sig = driver.findElement(By.id("LineAssignment_nextBtn"));
		page.obligarclick(sig);
		sleep(10000);
		page.obligarclick(driver.findElement(By.id("ICCDAssignment_nextBtn")));
		sleep(10000);
		page.obligarclick(driver.findElement(By.id("InvoicePreview_nextBtn")));
		sleep(10000);
		driver.findElement(By.id("PaymentMethodRadio")).click();
		sleep(4000);
		List<WebElement> mediosP = driver.findElements(By.cssSelector(".slds-list__item.ng-binding.ng-scope"));
		for (WebElement UnMP : mediosP) {
			if (UnMP.getText().toLowerCase().contains("tarjeta de credito"))
				UnMP.click();
		}
		sleep(5000);
		driver.findElement(By.id("CreditCardData")).click();
		sleep(1000);
		Assert.assertTrue(false);
		Assert.assertTrue(driver.findElement(By.id("CardBankingEntity")).getAttribute("required").equals("true"));
		//Assert.assertTrue(driver.findElement(By.id("CardBankingEntity")).getAttribute("required"));
	}
	
	@Test(groups={"Sales", "Ventas", "Ola1"})
	public void TS94779_Ventas_Entregas_General_Store_Pickup_Consulta_stock_por_PDV_Visualizar_el_campo_LOCALIDAD_con_un_desplegable_que_permita_seleccionar_una() {
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		sleep(15000);
		driver.findElement(By.cssSelector(".slds-m-left--x-small.slds-button.slds-button--brand")).click();
		sleep(7000);
		driver.switchTo().frame(cambioFrame(driver, By.id("DeliveryMethodSelection")));
		Select env = new Select (driver.findElement(By.id("DeliveryMethodSelection")));
		env.selectByVisibleText("Store Pick Up");
		sleep(2000);
		Select prov = new Select (driver.findElement(By.id("State")));
		prov.selectByVisibleText("Ciudad Aut\u00f3noma de Buenos Aires");
		sleep(2000);
		driver.findElement(By.id("City")).click();
		boolean a = false;
		List <WebElement> list = driver.findElement(By.id("City")).findElements(By.tagName("option"));
		if (list.size() >= 2) {
			a = true;
		}
		Assert.assertTrue(a);
	}
	
	@Test(groups={"Sales", "Ventas", "Ola1"})
	public void TS94778_Ventas_Entregas_General_Store_Pickup_Consulta_stock_por_PDV_Visualizar_el_campo_PROVINCIA_con_un_desplegable_que_permita_seleccionar_una() {
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		sleep(15000);
		driver.findElement(By.cssSelector(".slds-m-left--x-small.slds-button.slds-button--brand")).click();
		sleep(7000);
		driver.switchTo().frame(cambioFrame(driver, By.id("DeliveryMethodSelection")));
		Select env = new Select (driver.findElement(By.id("DeliveryMethodSelection")));
		env.selectByVisibleText("Store Pick Up");
		sleep(2000);
		driver.findElement(By.id("State")).click();
		boolean a = false;
		List <WebElement> list = driver.findElement(By.id("State")).findElements(By.tagName("option"));
		if (list.size() >= 2) {
			a = true;
		}
		Assert.assertTrue(a);
	}
	
	
	@Test(groups={"Sales","AltaDeLinea","Ola1"})  //Continua aunque no se asigne las lianeas
	public void TS94497_Alta_Linea_Configurar_Nueva_Linea_Intentar_pasar_al_siguiente_paso_lineas_incompletas_XX(){
		CustomerCare CC = new CustomerCare(driver);
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		sleep(15000);
		sb.elegirplan("Plan con Tarjeta Repro");
		sb.continuar();
		sleep(10000);
		WebElement bx = driver.findElement(By.id("tree0-node1__label"));
		CC.obligarclick(bx);
		sleep(3000);
		WebElement sig = driver.findElement(By.id("LineAssignment_nextBtn"));
		CC.obligarclick(sig);
		sleep(8000);
		boolean x = true;
		List<WebElement> serial = driver.findElements(By.cssSelector(".slds-page-header__title.vlc-slds-page-header__title.slds-truncate.ng-binding"));
			for(WebElement s : serial){
				s.getText().equals("Ingreso de serial");
				System.out.println(s.getText());
				s.isDisplayed();
				x=false;
			}
		Assert.assertTrue(x);
	}
	
	@Test(groups={"Sales","AltaDeLinea","Ola1"}) // No figura el lote de lineas
	public void TS94494_Alta_Linea_Configurar_Nueva_Linea_Buscar_nuevo_lote_de_lineas_pre_asignadas_XX(){
		CustomerCare CC = new CustomerCare(driver);
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		sleep(15000);
		sb.elegirplan("Plan con Tarjeta Repro");
		sb.continuar();
		sleep(10000);
		WebElement bx = driver.findElement(By.id("tree0-node1__label"));
		CC.obligarclick(bx);
		sleep(3000);
		Assert.assertTrue(false);
	}
	
	@Test(groups={"Sales","AltaDeLinea","Ola1"}) 	
	public void TS94503_Alta_Linea_Configurar_Nueva_Linea_Visualizar_lineas_pre_asignadas_automaticamente_XX(){
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		sleep(15000);
		sb.elegirplan("Plan con Tarjeta Repro");
		sb.continuar();
		sleep(10000);
		WebElement bx = driver.findElement(By.id("tree0-node1"));
		Assert.assertTrue(bx.isDisplayed());
	}
	
	@Test(groups={"Sales","AltaDeLinea","Ola1"}) 	
	public void TS94499_Alta_Linea_Configurar_Nueva_Linea_Presionar_el_boton_Buscar_XX(){
		CustomerCare CC = new CustomerCare(driver);
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		sleep(15000);
		sb.elegirplan("Plan con Tarjeta Repro");
		sb.continuar();
		sleep(10000);
		List<WebElement> modi = driver.findElements(By.cssSelector(".slds-form-element__label--toggleText.ng-binding"));
			for(WebElement m : modi){
				m.getText().equals("Modificar b\u00fasqueda");
					m.click();}
		Select prov = new Select (driver.findElement(By.id("SelectProvincia")));
		prov.selectByVisibleText("Catamarca");
		sleep(3000);
		Select loc = new Select (driver.findElement(By.id("SelectLocalidad")));
		loc.selectByVisibleText("ACHALCO");
		WebElement digit = driver.findElement(By.id("Sufijo"));
		digit.sendKeys("7354");
		sleep(1500);
		WebElement cont = driver.findElement(By.id("ChangeNumber"));
		CC.obligarclick(cont);
		sleep(8000);
		WebElement txt = driver.findElement(By.id("LineAssingmentMessage")).findElement(By.tagName("div")).findElements(By.tagName("strong")).get(0);
		Assert.assertTrue((txt.getText().equals("Catamarca")));
		WebElement txt2 = driver.findElement(By.id("LineAssingmentMessage")).findElement(By.tagName("div")).findElements(By.tagName("strong")).get(1);
		Assert.assertTrue(txt2.getText().equals("ACHALCO"));
	}
	
	@Test(groups={"Sales","AltaDeLinea","Ola1"}) 	
	public void TS94501_Alta_Linea_Configurar_Nueva_Linea_Visualizar_filtros_de_localidad_y_provincia_al_modificar_linea_XX(){
		CustomerCare CC = new CustomerCare(driver);
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		sleep(15000);
		sb.elegirplan("Plan con Tarjeta Repro");
		sb.continuar();
		sleep(10000);
		List<WebElement> modi = driver.findElements(By.cssSelector(".slds-form-element__label--toggleText.ng-binding"));
			for(WebElement m : modi){
				m.getText().equals("Modificar b\u00fasqueda");
					m.click();}
		sleep(3000);
		try{ driver.findElement(By.id("SelectProvincia")).click();
		driver.findElement(By.id("SelectLocalidad")).click();
		Assert.assertTrue(true);}
		catch(org.openqa.selenium.ElementNotVisibleException ex1){
		Assert.assertTrue(false);	
		}
	}	
	
	@Test(groups={"Sales","AltaDeLinea","Ola1"}) 	
	public void TS94502_Alta_Linea_Configurar_Nueva_Linea_Visualizar_home_de_la_linea_pre_asignada_correspondiente_a_direccion_de_facturacion_XX(){
		sb.BtnCrearNuevoCliente();
		CustomerCare CC = new CustomerCare(driver);
		ContactSearch contact = new ContactSearch(driver);
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("FirstName")).sendKeys("yy");
		driver.findElement(By.id("LastName")).sendKeys("z");
		driver.findElement(By.id("Birthdate")).sendKeys("28/12/1999");
		contact.sex("masculino");
		driver.findElement(By.id("Contact_nextBtn")).click();
		sleep(3000);
		sb.elegirplan("Plan con Tarjeta Repro");
		sb.continuar();
		sleep(10000);
		sb.Crear_DomicilioLegal(provincia, localidad, "falsa", "", "1000", "", "", "1549");
		sleep(7000);
		WebElement text = driver.findElement(By.id("LineAssingmentMessage")).findElement(By.tagName("div")).findElements(By.tagName("strong")).get(0);
		WebElement text2 = driver.findElement(By.id("LineAssingmentMessage")).findElement(By.tagName("div")).findElements(By.tagName("strong")).get(1);
		Assert.assertTrue(text.getText().equals(provincia));
		Assert.assertTrue(text2.getText().equals(localidad));
	
	}
	
	@Test(groups={"Sales","AltaDeLinea","Ola1"})
	public void TS94504_Alta_Linea_Configurar_Nueva_Linea_Visualizar_mensaje_y_opciones_de_lineas_no_disponibles_XX(){
		sb.BtnCrearNuevoCliente();
		CustomerCare CC = new CustomerCare(driver);
		ContactSearch contact = new ContactSearch(driver);
		try {Thread.sleep(10000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("FirstName")).sendKeys("yy");
		driver.findElement(By.id("LastName")).sendKeys("z");
		driver.findElement(By.id("Birthdate")).sendKeys("28/12/1999");
		contact.sex("masculino");
		driver.findElement(By.id("Contact_nextBtn")).click();
		sleep(3000);
		sb.elegirplan("Plan con Tarjeta Repro");
		sb.continuar();
		sleep(10000);
		sb.Crear_DomicilioLegal(provincia, localidad, "falsa", "", "1000", "", "", "1549");
		sleep(7000);
		boolean x = false;
		WebElement lindec = driver.findElement(By.id("tree0-node1")).findElement(By.tagName("ul")).findElements(By.tagName("div")).get(0);
		System.out.println(lindec.getText());
		System.out.println(lindec.getAttribute("value"));
		//Assert.assertTrue(lindec.isDisplayed());
		List<WebElement> modi = driver.findElements(By.cssSelector(".slds-form-element__label--toggleText.ng-binding"));
			for(WebElement m : modi){
			m.getText().equals("Modificar b\u00fasqueda");
				m.click();}
		sleep(3000);
		try{ driver.findElement(By.id("SelectProvincia")).click();
		driver.findElement(By.id("SelectLocalidad")).click();
		Assert.assertTrue(true);}
		catch(org.openqa.selenium.ElementNotVisibleException ex1){
		Assert.assertTrue(false);}
	}
		
	@Test(groups={"Sales","AltaDeLinea","Ola1"})
	public void TS94505_Alta_Linea_Configurar_Nueva_Linea_Visualizar_misma_cantidad_de_lineas_que_planes_XX(){
		CustomerCare CC = new CustomerCare(driver);
		sb.BuscarCuenta(DNI, "34073329");
		sb.acciondecontacto("catalogo");
		sleep(15000);
		sb.elegirplan("Plan con Tarjeta Repro");
		sleep(8000);
		List<WebElement> agregar = driver.findElements(By.cssSelector(".slds-button.slds-button--neutral.add-button")); 
		agregar.get(0).click();
		sleep(8000);
		List<WebElement> plan = driver.findElements(By.id("tab-default-1"));
		System.out.println(plan.size());
		}
		
		//sb.continuar();
		//sleep(10000);
	
}