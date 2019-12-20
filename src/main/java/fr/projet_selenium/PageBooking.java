package fr.projet_selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class PageBooking {
	@FindBy (xpath = "//a[.='HTML5 Hotel Room Booking (JavaScript/PHP)']")
	WebElement titrePage;
	
	@FindBy (xpath = "//*[@class=\"scheduler_default_cell\"]")
	WebElement cellule1;
	
	@FindBy (xpath = "//iframe")
	WebElement frame;
	
	@FindBy (xpath = "//input[@id = 'name']")
	WebElement frame_name;
	
	@FindBy (xpath = "//input[@type = 'submit']")
	WebElement frame_save;
	
	@FindBy (xpath = "//div[@class='scheduler_default_message']")
	WebElement message;
	
	@FindBy (xpath = "//div[@class='scheduler_default_event_inner']")
	WebElement reservation;
	
	@FindBy (xpath = "//*[@class='scheduler_default_cell scheduler_default_cell_business']")
	WebElement a_cote;

	@FindBy (xpath = "//div[contains(@class,'delete')]")
	WebElement croixDelete;

	
	public void reserver(String resa) {
		frame_name.sendKeys(resa);
		frame_save.click();
	}
	
	public void dragAndDrop(WebDriver driver) {
	Actions a = new Actions(driver);
	a.clickAndHold(reservation).moveToElement(a_cote).release(a_cote).build().perform();
	}
	
	public void delete(WebDriver driver) {
		Actions b = new Actions(driver);
		b.moveToElement(reservation).build().perform();
		b.moveToElement(croixDelete).click().build().perform();
	
	}
}
