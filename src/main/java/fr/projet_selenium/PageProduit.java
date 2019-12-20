package fr.projet_selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageProduit extends PageBandeau {
	
	@FindBy (xpath = "//img[contains(@src, \"add_to_cart.gif\")]")
	WebElement ajouterPanier;
	
	WebElement titreTable;
	
	public PagePanier clicAddToCart(WebDriver driver) {
		ajouterPanier.click();
		return PageFactory.initElements(driver, PagePanier.class);
		
	}
	
	public void findTable(WebDriver driver, String race) {
		titreTable = driver.findElement(By.xpath("//font[.='"+race+"']"));
	}
}
