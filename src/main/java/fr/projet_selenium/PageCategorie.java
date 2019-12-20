package fr.projet_selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageCategorie extends PageBandeau {
	
	@FindBy (xpath = "//h2")
	WebElement titreTable;
	
	@FindBy (xpath = "//font[.='FI-SW-02']")
	WebElement tigerSharkLink;	
	
	public PageProduit selectionProduit(WebDriver driver, String produit) {
		driver.findElement(By.xpath("//font[.='"+produit+"']")).click();
		return PageFactory.initElements(driver, PageProduit.class);
	}
}
