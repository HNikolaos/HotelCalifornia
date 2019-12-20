package fr.projet_selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageRecherche {

	@FindBy (xpath = "/html/body/table[3]")
	WebElement tableauRecherche;

	WebElement cellule;

	public int numeroLigne(String item) {
		int numero_ligne = 1;
		List<WebElement> liste_item = tableauRecherche.findElements(By.xpath("tbody/tr"));
		for (WebElement lignes : liste_item) {
			List<WebElement> cases = lignes.findElements(By.xpath("td"));
			if (cases.get(2).getText().equals(item)) {
				return numero_ligne;
			}
			numero_ligne++;
		}
		return -1;
	}
	
	public void getCellule(WebDriver driver, int row, int col) {
		cellule = driver.findElement(By.xpath("//table[3]/tbody/tr["+row+"]/td["+col+"]"));
	}
	
	public PageProduit clicId(WebDriver driver) {
		cellule.click();
		return PageFactory.initElements(driver, PageProduit.class);
	}
}
