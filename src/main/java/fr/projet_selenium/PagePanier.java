package fr.projet_selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PagePanier extends PageBandeau {

	@FindBy (xpath ="//input[@name = 'EST-3']")
	WebElement champQuantite;

	@FindBy (xpath ="//input[@name = 'update']")
	WebElement majPrix;

	@FindBy (xpath ="//h2[.=\"Shopping Cart\"]")
	WebElement titreTable;

	@FindBy (xpath ="/html/body/table[2]/tbody/tr/td[2]/form/table/tbody/tr[2]/td[6]")
	WebElement prixUnitaire;

	@FindBy (xpath ="/html/body/table[2]/tbody/tr/td[2]/form/table/tbody/tr[2]/td[7]")
	WebElement prixTotal;


	public void changeQuantite (String quantite) {
		OutilsTechnique.remplirChamp(champQuantite, quantite);
		majPrix.click();
	}


	public double transformPrixUnitaire() {
		double PUtoDouble = Double.parseDouble(prixUnitaire.getText().substring(1).replace(",", "."));
		return PUtoDouble;
	}

	public double transformPrixTotal() {
		String PT = prixTotal.getText();
		PT = PT.substring(1).replace(",", ".");
		double PTtoDouble = Double.parseDouble(PT);
		return PTtoDouble;
	}
}
