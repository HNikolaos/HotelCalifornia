package fr.projet_selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

public class OutilsTechnique {
	
	static WebDriver driver;
	
	public static WebDriver choisirNavigateur(ENavigateur navigateur) {
		switch(navigateur) {
			case firefox:  
			System.setProperty("webdriver.gecko.driver", "src/main/resources/driver/geckodriver.exe");
			driver = new FirefoxDriver();
			return driver;
			case chrome:  
				System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
				driver = new ChromeDriver();
				return driver;
			case IE:  
				System.setProperty("webdriver.ie.driver", "src/main/resources/driver/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				return driver;
			default: return null;
		}
	}
	public static void remplirChamp(WebElement e, String s) {
		e.clear();
		e.sendKeys(s);
	}
	
}
