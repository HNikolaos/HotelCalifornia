package fr.projet_selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PageAccount {

	@FindBy (xpath = "//font[1]/h3")
	WebElement userInfos;
	
	@FindBy (xpath = "//font[2]/h3")
	WebElement accountInfos;
	
	@FindBy (xpath = "//font[3]/h3")
	WebElement profileInfos;
	
	@FindBy (xpath="//select[@name = \"account.languagePreference\"]")
	WebElement language;
	
	@FindBy (xpath="//select[@name = \"account.favouriteCategoryId\"]")
	WebElement animalFavori;
	
	@FindBy (xpath="//input[@name='account.listOption']")
	WebElement enableMyList;
	
	@FindBy (xpath="//input[@name='account.bannerOption']")
	WebElement enableMyBanner;
	
	Select select_language;
	String favorite_language;
	Select select_animal;
	String favorite_animal;
	
	public void choixLanguage(String language_value) {
		select_language = new Select(language);
		select_language.selectByValue(language_value);
		favorite_language = select_language.getFirstSelectedOption().getText();
	}
	
	public void choixAnimal(String animal_value) {
		select_animal = new Select(animalFavori);
		select_animal.selectByVisibleText(animal_value);
		favorite_animal = select_animal.getFirstSelectedOption().getText();
	}
	
}
