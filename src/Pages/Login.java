package Pages;
 
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login extends BasePage {
	
	final WebDriver driver;
	
	//Fields
	
	@FindBy (how = How.NAME, using = "username")
	private WebElement userMarketing;
	
	@FindBy (how = How.NAME, using = "pw")
	private WebElement passwordMarketing;
	
	@FindBy (how = How.ID, using = "Login")
	private WebElement loginMarketing;
	
	@FindBy (how = How.NAME, using = "Ecom_User_ID")
	private WebElement username;
	
	@FindBy (how = How.NAME, using = "Ecom_Password")
	private WebElement password;

	@FindBy (how = How.ID, using = "loginButton2")
	private WebElement login;
	
	@FindBy (how = How.NAME, using = "rememberUn")
	private WebElement rememberMe;
	
	@FindBy(how = How.ID, using = "idp_section_buttons")
	private WebElement logininterno;
	
	   @FindBy (how= How.NAME, using = "Ecom_User_ID")
	   private WebElement Ecom_User_ID;
	   
	   @FindBy (how= How.NAME, using = "Ecom_Password")
	   private WebElement Ecom_Password;
	   
	   @FindBy (how = How.ID, using = "loginButton2")
	   private WebElement loginButton2;
	//Constructor
	
	public Login(WebDriver driver){
		this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	//Methods
	public void ingresarSCPconTodo() { 
		 try {logininterno.click();}
		 catch(org.openqa.selenium.ElementNotVisibleException ex1) {
			 driver.findElement(By.id("idp_hint")).click();
		 }
		 Ecom_User_ID.sendKeys("UA198998");
	     Ecom_Password.sendKeys("Teco1234");
	     loginButton2.click();
	     try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	public void ingresarAdminSCP() {
		 logininterno.click();
	     Ecom_User_ID.sendKeys("UAT585244");
	     Ecom_Password.sendKeys("Testa10k");
	     loginButton2.click();
	     try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	   }
	   
	  
	   public void ingresarUsuarioSCP() {
		 try {logininterno.click();}
		 catch(org.openqa.selenium.ElementNotVisibleException ex1) {
			 driver.findElement(By.id("idp_hint")).click();
		 }
	     Ecom_User_ID.sendKeys("UA177426");
	     Ecom_Password.sendKeys("Testa10k");
	     loginButton2.click();
	     try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	   }
	 
		public void ingresar() {
			logininterno.click();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			//Usuario Daniel
			//username.sendKeys("u589831");
			//Usuario UAT OOCC
			username.sendKeys("UAT195528");
			password.sendKeys("Testa10k");
	    	//rememberMe.click();
			login.click();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		}
		public void ingresarDani() {
			logininterno.click();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			//Usuario Daniel
			username.sendKeys("u589831");
			//Usuario UAT OOCC
			//username.sendKeys("UAT195528");
			password.sendKeys("Testa10k");
	    	//rememberMe.click();
			login.click();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		}
		
		public void ingresarMarketing() {
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			logininterno.click();
			//Usuario Cesar
			//username.sendKeys("u198427");
			//Usuario UAT OOCC
			username.sendKeys("UAT195528");
			password.sendKeys("Testa10k");
			login.click();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		}
		
		//******************Agente
	   //Para el Modulo Sales tiene vinculado el perfil de Agente y Atención a clientes
		public void ingresarAndres() {
			  logininterno.click();
			  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			   Ecom_User_ID.sendKeys("UAT549492");
			  Ecom_Password.sendKeys("Testa10k");
			  loginButton2.click();
			  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			 }
		//*************************Telefonico
		//Para el Modulo Sales tiene vinculado el perfil de Call center
		public void ingresarElena() {
			  logininterno.click();
			  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			  Ecom_User_ID.sendKeys("UAT569076");
			  Ecom_Password.sendKeys("Testa10k");
			  loginButton2.click();
			  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			 }
		
		//Para el Modulo Sales tiene vinculado el perfil de Vendedor Oficina Comercial
		public void ingresarFrancisco() {
			  logininterno.click();
			  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			  Ecom_User_ID.sendKeys("u581441");
			  Ecom_Password.sendKeys("Testa10k");
			  loginButton2.click();
			  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			 }
		//***********************Oficina comercial
		public void ingresarNominaciones() {
			  logininterno.click();
			  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			  Ecom_User_ID.sendKeys("UAT195528");
			  Ecom_Password.sendKeys("Testa10k");
			  loginButton2.click();
			  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			 }
		
		//Para el Modulo Sales tiene vinculado el perfil de Logistica
		public void ingresarNicolas() {
			  logininterno.click();
			  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			  Ecom_User_ID.sendKeys("u586760");
			  Ecom_Password.sendKeys("Testa10k");
			  loginButton2.click();
			  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			 }
		
		//Para el Modulo Sales tiene vinculado el perfil de Entregas y Configuraciones
		public void ingresarMarcela() {
			  logininterno.click();
			  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			  Ecom_User_ID.sendKeys("u591584");
			  Ecom_Password.sendKeys("Testa10k");
			  loginButton2.click();
			  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			 }
		
		public void ingresarFabiana() {
			  logininterno.click();
			  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			  Ecom_User_ID.sendKeys("u585244");
			  Ecom_Password.sendKeys("Testa10k");
			  loginButton2.click();
			  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		}

		public void ingresarVictor() {
			  logininterno.click();
			  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			  Ecom_User_ID.sendKeys("U585991");
			  Ecom_Password.sendKeys("Testa10k");
			  loginButton2.click();
			  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		}
		
		public void ingresarSCPAdminServices() {
			try {logininterno.click();}
			 catch(org.openqa.selenium.ElementNotVisibleException ex1) {
				 driver.findElement(By.id("idp_hint")).click();
			 }
			  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			  Ecom_User_ID.sendKeys("UA090571");
			  Ecom_Password.sendKeys("Testa10k");
			  loginButton2.click();
			  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		}
		
		public void ingresarSCPConPermisos() {
			try {logininterno.click();}
			 catch(org.openqa.selenium.ElementNotVisibleException ex1) {
				 driver.findElement(By.id("idp_hint")).click();
			 }
			  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			  Ecom_User_ID.sendKeys("UAT090022");
			  Ecom_Password.sendKeys("Testa10K");
			  loginButton2.click();
			  try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		}
		
		public void ingresar(String User, String Password) {
			logininterno.click();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			username.sendKeys(User);
			password.sendKeys(Password);
			login.click();
			try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		}
}
