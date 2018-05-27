package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/*In this framework, keywords are developed which are equal to a unit level functionality. 
 * It is an independent framework which perform automation based on the keywords specified in the excel sheet. 
 * Based on the type of application, the number of keywords will be increased to handle different functionalities.*/

public class KeyWordExample {

	static WebDriver driver;
	static WebDriverWait wait;

	// In this method we need to pass browser name which will invoke the respective driver.
	// example, If the user pass 'chrome' as a browser name, it will invoke the
	// chrome driver.
	public void open_Browser(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("Firefox")) {
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", "src/main/resources/seleniumDrivers/chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", "src/main/resources/seleniumDrivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
		}
	}
	//In this method we need to pass the URL which we want to get to.
	public void enter_URL(String URL) {
		driver.navigate().to(URL);
	}
	
	//In this method we pass the locator type (e.g. xpath) and it's string value
	public By locatorValue(String locatorTpye, String value) {
		By by;
		switch (locatorTpye) {
		case "id":
			by = By.id(value);
			break;
		case "name":
			by = By.name(value);
			break;
		case "xpath":
			by = By.xpath(value);
			break;
		case "css":
			by = By.cssSelector(value);
			break;
		case "linkText":
			by = By.linkText(value);
			break;
		case "partialLinkText":
			by = By.partialLinkText(value);
			break;
		default:
			by = null;
			break;
		}
		return by;
	}

	// This method is used to enter the text by using sendkeys method.
	// Here we need to use three parameters the first one is locator type that can
	// be id / name / any other locator, second parameter
	// should be the locator value And the last method should be the data that you
	// want to pass into the text field.
	public void enter_Text(String locatorType, String value, String text) {
		try {
			By locator;
			locator = locatorValue(locatorType, value);
			WebElement element = driver.findElement(locator);
			element.sendKeys(text);
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to enter text" + e);
		}
	}

	// Here we have to have two parameters, First parameter is locator type, and it should be linkText or partialLinkText
	// and the text that we need to click on.
	public void click_On_Link(String locatorType, String value) {
		try {
			By locator;
			locator = locatorValue(locatorType, value);
			WebElement element = driver.findElement(locator);
			element.click();
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to enter text" + e);
		}
	}

	// Here we have to have two parameters, First parameter is locator type, and it
	// should be linkText or partialLinkText and the
	// text that we need to click on.
	public void click_On_Button(String locatorType, String value) {
		try {
			By locator;
			locator = locatorValue(locatorType, value);
			WebElement element = driver.findElement(locator);
			element.click();
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to perform click" + e);
		}
	}
	//In this method we close the browser
	public void close_Browser() {
		driver.quit();
	}
}
