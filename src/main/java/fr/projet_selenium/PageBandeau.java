package fr.projet_selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageBandeau {
	
	@FindBy (xpath = "//img[@name='img_signin']")
	private WebElement signIn;
	
	@FindBy (xpath = "//img[@name='img_signout']")
	WebElement signOut;
	
	@FindBy (xpath = "//img[contains(@src, \"my_account.gif\")]")
	WebElement myAccount;
	
	@FindBy (xpath = "//input[@name='keyword']")
	WebElement barreRecherche;
	
	@FindBy (xpath = "//input[contains(@src,'search.gif')]")
	WebElement searchButton;
	
	public PageLogin clicSignIn(WebDriver driver) {
		signIn.click();
		return PageFactory.initElements(driver, PageLogin.class);
	}
	
	public PageAccount clicMyAccount(WebDriver driver) {
		myAccount.click();
		return PageFactory.initElements(driver, PageAccount.class);
	}
	
	public PageRecherche rechercher(WebDriver driver, String keyword) {
		OutilsTechnique.remplirChamp(barreRecherche, keyword);;
		searchButton.click();
		return PageFactory.initElements(driver, PageRecherche.class);
		
		
	}
}
