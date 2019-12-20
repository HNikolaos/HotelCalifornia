package fr.projet_selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageAccueil extends PageBandeau{
	
	@FindBy (xpath = "//font")
	WebElement messagePerso;
	
	@FindBy (xpath = "//img[contains(@src, 'fish')]")
	WebElement fishLink;
	
	@FindBy (xpath = "//img[contains(@src, 'dogs')]")
	WebElement dogLink;
	
	
	public PageCategorie selectionCategorie(WebDriver driver, String categorie) {
		driver.findElement(By.xpath("//img[contains(@src, '"+categorie+"')]")).click();
		return PageFactory.initElements(driver, PageCategorie.class);
	}
	
}
