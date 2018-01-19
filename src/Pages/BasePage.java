package Pages;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import javafx.scene.control.Tab;

public class BasePage {
	
	final WebDriver driver;
	
	@FindBy (how = How.ID, using = "tsidButton")
	private WebElement mainMenuButton;
	
	@FindBy (how = How.ID, using = "tsid-menuItems")
	private WebElement menuOptionsWrapper;
	
	@FindBy (how = How.ID, using = "tabBar")
	private WebElement tabsWrapperBar;
		
	public BasePage() {
		this.driver = null;
		//older constructor maintained.
	}
	
	public BasePage(WebDriver newDriver) {
		System.out.println(newDriver.getCurrentUrl());
		this.driver = newDriver;
		System.out.println(this.driver.getCurrentUrl());
	}
	
	public void setSimpleDropdown(WebElement element, String value) {
		Select field = new Select(element);
		element.click();
		field.selectByVisibleText(value);
	}
	
	public void setElementFromList(WebElement element, String value, WebElement arrow) {
		Select field = new Select(element);
		field.selectByVisibleText(value);
		arrow.click();
	}

	public WebElement getElementFromList(List<WebElement> elements, String field) {
		List<Integer> a = new ArrayList<>();
		Integer i = 0;
		for(WebElement e : elements) {
			if(e.getText().equals(field)) {
				a.add(i);
			}
			i++;
		}
		return elements.get(a.get(0));
	}
	
	public int getIndexFrame(WebDriver driver, By byForElement) { //working correctly
		//TODO: Do the same for a WebElement instead of a By.
		int index = 0;
		driver.switchTo().defaultContent();
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		for(WebElement frame : frames) {
			try {
				driver.switchTo().frame(frame);

				driver.findElement(byForElement).getText(); //each element is in the same iframe.
				//System.out.println(index); //prints the used index.

				driver.findElement(byForElement).isDisplayed(); //each element is in the same iframe.
				//System.out.println(index); //prints the used index.

				driver.switchTo().defaultContent();
				return index;
			}catch(NoSuchElementException noSuchElemExcept) {
				index++;
				driver.switchTo().defaultContent();
			}
		}
		return -1; //if this is called, the element wasnt found.
	}
	
	public WebElement getFrameForElement(WebDriver driver, By byForElement) {
		driver.switchTo().defaultContent();
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		try {return frames.get(getIndexFrame(driver, byForElement));
		}catch(ArrayIndexOutOfBoundsException iobExcept) {System.out.println("Elemento no encontrado en ningun frame.");
			return null;
		}

	}

	public int getIndexFrame(WebDriver driver, WebElement webElementToFind) {
		//TODO: Do the same for a WebElement instead of a By.
		int index = 0;
		driver.switchTo().defaultContent();
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		for(WebElement frame : frames) {
			try {
				driver.switchTo().frame(frame);
				webElementToFind.getText();
				//System.out.println(index); //prints the used index.
				driver.switchTo().defaultContent();
				return index;
			}catch(NoSuchElementException noSuchElemExcept) {
				index++;
				driver.switchTo().defaultContent();

			}catch(NullPointerException noSuchElemExcept) {
				index++;
				driver.switchTo().defaultContent();

			}
		}
		return -1;//if this is called, the element wasnt found.
	}
	
	public WebElement getFrameForElement(WebDriver driver, WebElement webElementToFind) {
		driver.switchTo().defaultContent();
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		return frames.get(getIndexFrame(driver, webElementToFind));
	}
	
	public void switchAppsMenu() {

		driver.switchTo().defaultContent();
		mainMenuButton.click();
	}
	
	
	
	public void switchAppsMenu(WebDriver driver) {
		driver.switchTo().defaultContent();
		driver.findElement(By.className("menuButtonButton")).click();
		//mainMenuButton.click();
	}
	
	public void selectAppFromMenuByName(String optionName) {
		//each option is a menuButtonMenuLink class
//		List<WebElement> options = menuOptionsWrapper.findElements(By.className("menuButtonMenuLink"));
		driver.switchTo().defaultContent();
		List<WebElement> options = driver.findElement(By.id("tsid-menuItems")).findElements(By.tagName("a"));

		for (WebElement option : options) {
			if(option.getText().toLowerCase().equals(optionName.toLowerCase())){
				option.click();
				return;
			}
		}
		System.out.println("Opcion del menu principal NO encontrada.");
		switchAppsMenu();
	}

	public void selectMainTabByName(String tabName) {
//		An Alternative.
//		List<WebElement> tabs = tabsWrapperBar.findElements(By.tagName("li"));
		driver.switchTo().defaultContent();
		List<WebElement> tabs = tabsWrapperBar.findElements(By.tagName("a"));

		for(WebElement tab : tabs) {//tabs LINKS
			if(tab.getText().toLowerCase().equals(tabName.toLowerCase())){
				tab.click();
				return;
			}
		}
		System.out.println("Tab NO encontrado.");
	}
	
	//this is for Consola FAN, closes all tabs, takes in account Alert messages
	public void closeAllTabs(WebDriver driver) {
		driver.switchTo().defaultContent();
		List<WebElement> tabs = driver.findElement(By.id("ext-gen27")).findElements(By.className("x-tab-strip-closable"));
		for (WebElement tab : tabs) {
			//se itera al reves porque el primer elemento puede no estar visible.
			//closeTab(tab);
			closeTab(driver, tab);
		}
	}
	
	public void closeTab(WebDriver driver, WebElement tab) {
		//Warning: doesn't save when closing.
		Actions action = new Actions(driver);
		action.moveToElement(tab);
		action.moveToElement(tab.findElement(By.className("x-tab-strip-close"))).click().build().perform();
		try {
			try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			driver.switchTo().alert();
			List<WebElement> buttons = driver.findElements(By.tagName("button"));
			for(WebElement button : buttons) {
				if (button.getText().toLowerCase().contains("no guardar")) {
					button.click();
					break;
				}
			}
		}catch(NoAlertPresentException noAlertExcept) {
			//does Nothing.
		}
	}
	
	public List<String> getSelectOptions (Select select){
		  List<String> optionsNames = new ArrayList<String>();
		  for (WebElement option:select.getOptions()) {
		   optionsNames.add(option.getText());
		  }
		  
		  return optionsNames;
		 }
	
	public void selectByText(WebElement element, String data){
		Select select = new Select(element);
		select.selectByVisibleText(data);
		}
	
}
