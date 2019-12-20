package fr.projet_selenium;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;


public class TestWd {


	WebDriver driver;
	WebElement e;
	WebDriverWait wait;

	@Ignore //Wait
	public void setup() {
		driver = OutilsTechnique.choisirNavigateur(ENavigateur.firefox);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //Wait implicite
		wait = new WebDriverWait(driver, 20); //Wait explicite
	}

	@Ignore
	public void afterTest() {
		driver.quit();
	}


	@Ignore
	public void test1() {
		driver = OutilsTechnique.choisirNavigateur(ENavigateur.chrome);
		driver.get("https://lemonde.fr");
		String title = driver.getTitle();
		System.out.println(title);
		assertEquals("Le Monde.fr - Actualités et Infos en France et dans le monde",title);
	}

	@Ignore //jpetStore sans Page Object
	public void testJpet() {
		driver = OutilsTechnique.choisirNavigateur(ENavigateur.firefox);
		//Acces au site
		driver.get("http://localhost:8090/jpetstore-1.0.5-env2/");
		System.out.println(driver.getCurrentUrl());
		//Acces au menu de connexion
		driver.findElement(By.name("img_signin")).click();
		System.out.println(driver.getCurrentUrl());
		//Connexion au compte j2ee
		e = driver.findElement(By.name("username"));
		OutilsTechnique.remplirChamp(e, "j2ee");
		e = driver.findElement(By.name("password"));
		OutilsTechnique.remplirChamp(e, "j2ee");
		driver.findElement(By.name("update")).click();
		System.out.println(driver.getCurrentUrl());
		//Vérification de la page d'accueil
		assertTrue(driver.findElement(By.name("img_signout")).isDisplayed());
		assertEquals("Welcome ABC!",driver.findElement(By.xpath("//font")).getText());
		//Acces à la page Fish
		driver.findElement(By.xpath("//img[contains(@src,'fish.gif')]")).click();
		System.out.println(driver.getCurrentUrl());
		//Vérification de la page Fish
		assertEquals("Fish", driver.findElement(By.xpath("//h2")).getText());
		//Selectionner un produit
		driver.findElement(By.xpath("//font[.='FI-SW-02']")).click();
		System.out.println(driver.getCurrentUrl());
		//Ajouter un produit au panier
		driver.findElement(By.xpath("//img[contains(@src, 'add_to_cart.gif')]")).click();
		System.out.println(driver.getCurrentUrl());
		//Affichage du panier
		assertEquals("Shopping Cart",driver.findElement(By.xpath("//h2")).getText());
		//passer la quantité à 2
		e = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[2]/form/table/tbody/tr[2]/td[5]/input"));
		OutilsTechnique.remplirChamp(e, "2");
		driver.findElement(By.name("update")).click();
		//Prix total egal au double du prix unitaire
		assertEquals("$18,50", driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[2]/form/table/tbody/tr[2]/td[6]")).getText());
		String PU = driver.findElement(By.xpath("//tr[2]/td[6]")).getText();
		String PT = driver.findElement(By.xpath("//tr[2]/td[7]")).getText();
		PU = PU.substring(1).replace(",", ".");
		PT = PT.substring(1).replace(",", ".");
		System.out.println(PU+" "+PT);
		double PUtoDouble = Double.parseDouble(PU);
		double PTtoDouble = Double.parseDouble(PT);
		System.out.println(PUtoDouble+" "+PTtoDouble);
		assertEquals("Le prix n'est conforme", PTtoDouble, PUtoDouble*2, 0.0);
		System.out.println("Prix conforme");
	}
	
	//JDD
	String login = "j2ee";
	String password = "j2ee";
	String idProduit = "FI-SW-02";
	String idCategorie = "Fish";
	String categorie = idCategorie.toLowerCase();
	String prixUnite = "$18,50";
	String quantite = "2";
	
	@Ignore //jpetStore avec methode Page Object
	public void testJpetPageObject() {
		driver = OutilsTechnique.choisirNavigateur(ENavigateur.firefox);
		PageIndex page_index = PageFactory.initElements(driver, PageIndex.class);
		//Acces au site//
		driver.get("http://localhost:8090/jpetstore-1.0.5-env2/");
		System.out.println(driver.getCurrentUrl());
		//Acces au menu de connexion//
		PageLogin page_login= page_index.clicSignIn(driver);
		System.out.println(driver.getCurrentUrl());
		//Connexion au compte j2ee//
		PageAccueil page_accueil = page_login.login(driver, login, password);
		System.out.println(driver.getCurrentUrl());
		//Vérification de la page d'accueil//
		assertEquals("Welcome ABC!",page_accueil.messagePerso.getText());
		assertTrue(page_accueil.signOut.isEnabled());
		//Acces à la page Fish//
		PageCategorie page_categorie = page_accueil.selectionCategorie(driver, categorie);
		System.out.println(driver.getCurrentUrl());
		//Vérification de la page Fish//
		assertEquals("La catégorie est introuvable", idCategorie, page_categorie.titreTable.getText());
		//Selectionner un produit//
		PageProduit page_produit = page_categorie.selectionProduit(driver, idProduit);
		System.out.println(driver.getCurrentUrl());
		//Ajouter un produit au panier
		PagePanier page_panier = page_produit.clicAddToCart(driver);
		System.out.println(driver.getCurrentUrl());
		//Affichage du panier//
		assertEquals("Titre du tableau n'est pas Shopping Cart ou absent", "Shopping Cart",page_panier.titreTable.getText());
		//passer la quantité à 2
		page_panier.changeQuantite(quantite);
		//Prix total egal au double du prix unitaire//
		assertEquals(prixUnite, page_panier.prixUnitaire.getText());
		double prixUnitaire = page_panier.transformPrixUnitaire();
		double prixTotal = page_panier.transformPrixTotal();
		System.out.println(prixUnitaire+" "+prixTotal);
		assertEquals("Le prix n'est pas conforme", prixTotal, prixUnitaire*Integer.parseInt(quantite), 0.0);
		System.out.println("Prix conforme");
	}
	
	//JDD
	String language_value = "japanese";
	String animal_value = "Reptiles";
	
	@Ignore //jpetStore avec test checkbox et menu deroulant
	public void testCheckbox() {
		driver = OutilsTechnique.choisirNavigateur(ENavigateur.firefox);
		PageIndex page_index = PageFactory.initElements(driver, PageIndex.class);
		//Acces au site//
		driver.get("http://localhost:8090/jpetstore-1.0.5-env2/");
		System.out.println(driver.getCurrentUrl());
		//Acces au menu de connexion//
		PageLogin page_login= page_index.clicSignIn(driver);
		System.out.println(driver.getCurrentUrl());
		//Connexion au compte j2ee//
		PageAccueil page_accueil = page_login.login(driver, login, password);
		System.out.println(driver.getCurrentUrl());
		//Vérification de la page d'accueil//
		assertEquals("Welcome ABC!",page_accueil.messagePerso.getText());
		assertTrue(page_accueil.signOut.isEnabled());
		//Acces Account et vérification page//
		PageAccount page_account = page_accueil.clicMyAccount(driver);
		assertEquals("User Information",page_account.userInfos.getText());
		assertEquals("Account Information",page_account.accountInfos.getText());
		assertEquals("Profile Information",page_account.profileInfos.getText());
		//Selectionner japanese//
		page_account.choixLanguage(language_value);
		page_account.choixAnimal(animal_value);
		assertEquals(language_value,page_account.favorite_language);
		assertEquals(animal_value,page_account.favorite_animal);
		//Verifier checkbox//
		assertTrue("Case non cochée", page_account.enableMyList.isSelected()); //assert spécifique à une checkbox
		assertEquals("true", page_account.enableMyBanner.getAttribute("value"));//assert ok mais moins adapté à la checkbox et moins performant
		//Deselectionner "Enable My List" et vérifier//
		page_account.enableMyList.click();
		assertFalse("Checkbox est encore cochée", page_account.enableMyList.isSelected());
	}
	
	//JDD
	String keyword = "dog";	
	String race = "Dalmation";
	
	@Ignore //jpetStore avec parcours tableau
		public void testTableau() {
		driver = OutilsTechnique.choisirNavigateur(ENavigateur.firefox);
		PageIndex page_index = PageFactory.initElements(driver, PageIndex.class);
		//Acces au site//
		driver.get("http://localhost:8090/jpetstore-1.0.5-env2/");
		System.out.println(driver.getCurrentUrl());
		//Acces au menu de connexion//
		PageLogin page_login= page_index.clicSignIn(driver);
		System.out.println(driver.getCurrentUrl());
		//Connexion au compte j2ee//
		PageAccueil page_accueil = page_login.login(driver, login, password);
		System.out.println(driver.getCurrentUrl());
		//Vérification de la page d'accueil//
		assertEquals("Welcome ABC!",page_accueil.messagePerso.getText());
		assertTrue(page_accueil.signOut.isEnabled());
		//Entrer dog dans la barre de recherche et rechercher//
		PageRecherche page_recherche = page_accueil.rechercher(driver, keyword);
		//Vérifier l'affichage de la page//
		assertTrue(page_recherche.tableauRecherche.isDisplayed());
		//Cliquer sur le lien du Dalmation//
		int row = page_recherche.numeroLigne(race);
		page_recherche.getCellule(driver, row, 2);
		PageProduit page_produit = page_recherche.clicId(driver);
		page_produit.findTable(driver, race);
		//assertEquals("La page est incorrecte", item, page_produit.titreTable.getText());
		
	}
	
	@Ignore
	public void testWaitExplicitly() {
		driver.get("http://newtours.demoaut.com/");
		e = driver.findElement(By.name("userName"));
		OutilsTechnique.remplirChamp(e, "mercury");
		e = driver.findElement(By.name("password"));
		OutilsTechnique.remplirChamp(e, "mercury");
		WebElement Login = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@alt = \"Sign-In\"]"))));
		WebElement BoutonLogin = driver.findElement(By.xpath("//*[@alt = \"Sign-In\"]"));
		driver.findElement(By.xpath("//*[@alt = \"Sign-In\"]"));
		//wait.until(ExpectedConditions.textToBe(By.xpath("//*[@alt = \"Find a Flight\"]"), "Tricot"));
		wait.until(ExpectedConditions.elementToBeClickable(BoutonLogin)).click();
	}

	//JDD
	int numero_cellule1 = 1;
	int numero_cellule2 = 6;
	
	@Ignore // site Hotel, test des frames et alertes
	public void testHotel() throws InterruptedException {
		driver = OutilsTechnique.choisirNavigateur(ENavigateur.chrome);
		driver.get("http://localhost/TutorialHtml5HotelPhp/");
		PageBooking page_booking = PageFactory.initElements(driver, PageBooking.class);
		assertTrue(page_booking.titrePage.isDisplayed());
		page_booking.cellule1.click();
		driver.switchTo().frame(page_booking.frame);
		page_booking.reserver("resa1");
		driver.switchTo().defaultContent();
		//assertTrue(driver.findElement(By.xpath("//div[contains(text(),\"resa1\")]")).getText().contains("resa1"));
		page_booking.dragAndDrop(driver);
		Thread.sleep(1000);
		assertTrue(driver.findElement(By.xpath("//div[@class='scheduler_default_message']/..")).getAttribute("style").contains("opacity"));
		Thread.sleep(7000);
		assertTrue(driver.findElement(By.xpath("//div[@class='scheduler_default_message']/..")).getAttribute("style").contains("none"));
		page_booking.delete(driver);
		//assertTrue(driver.findElement(By.xpath("//*[@id='dp']/div[7][contains(@style,'none')]")).isDisplayed());
		//driver.switchTo().frame(page_booking.message);
		//System.out.println(page_booking.message.getText());
		//System.out.println(driver.switchTo().alert().getText());
		//page_booking.delete(driver, numero_cellule2);
		}
	
	
	@Before
	public void datasetBDD() throws Exception {
		driver = OutilsTechnique.choisirNavigateur(ENavigateur.chrome);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		BddOutils.deleteAllData("src\\test\\resources\\DeleteData.xml");
	}
	
	
	@Test //Test lien BDD
	public void testHotelBDD() throws SQLException, Exception {
		driver.get("http://localhost/TutorialHtml5HotelPhp/");
		PageBooking page_booking = PageFactory.initElements(driver, PageBooking.class);
		assertTrue(page_booking.titrePage.isDisplayed());
		page_booking.cellule1.click();
		driver.switchTo().frame(page_booking.frame);
		page_booking.reserver("resa1");
		driver.switchTo().defaultContent();
		assertTrue(driver.findElement(By.xpath("//div[contains(text(),\"resa1\")]")).getText().contains("resa1"));
		BddOutils.compareData("reservations","src\\test\\resources\\Dataset.xml", "id");
		/*page_booking.dragAndDrop(driver);
		Thread.sleep(1000);
		assertTrue(driver.findElement(By.xpath("//div[@class='scheduler_default_message']/..")).getAttribute("style").contains("opacity"));
		Thread.sleep(7000);
		assertTrue(driver.findElement(By.xpath("//div[@class='scheduler_default_message']/..")).getAttribute("style").contains("none"));
		page_booking.delete(driver);*/
		
	}

}
