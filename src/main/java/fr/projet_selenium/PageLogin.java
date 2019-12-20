package fr.projet_selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageLogin extends PageBandeau {
	@FindBy (xpath = "//input[@name='username']")
	WebElement champUserName;
	
	@FindBy (xpath = "//input[@name='password']")
	WebElement champPassword;
	
	@FindBy (xpath = "//input[@name='update']")
	WebElement boutonSubmit;
	
	public PageAccueil login(WebDriver driver, String username, String password) {
		champUserName.clear();
		champUserName.sendKeys(username);
		champPassword.clear();
		champPassword.sendKeys(password);
		boutonSubmit.click();
		return PageFactory.initElements(driver, PageAccueil.class);
		
	}
}
