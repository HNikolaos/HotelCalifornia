package fr.projet_selenium;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class TestDragAndDrop {
	
	WebDriver driver;
	
	
	@Before
	public void datasetBDD() throws Exception {
		driver = OutilsTechnique.choisirNavigateur(ENavigateur.chrome);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		BddOutils.deleteAllData("src\\test\\resources\\DeleteData.xml");
		BddOutils.insertData("src\\test\\resources\\Dataset.xml");
	}
	
	
	@Test //Test lien BDD
	public void testHotelBDD() throws SQLException, Exception {
		driver.get("http://localhost/TutorialHtml5HotelPhp/");
		PageBooking page_booking = PageFactory.initElements(driver, PageBooking.class);
		assertTrue(page_booking.titrePage.isDisplayed());
		assertTrue(driver.findElement(By.xpath("//div[contains(text(),\"resa1\")]")).getText().contains("resa1"));
		page_booking.dragAndDrop(driver);
		Thread.sleep(1000);
		assertTrue(driver.findElement(By.xpath("//div[@class='scheduler_default_message']/..")).getAttribute("style").contains("opacity"));
		Thread.sleep(7000);
		assertTrue(driver.findElement(By.xpath("//div[@class='scheduler_default_message']/..")).getAttribute("style").contains("none"));
		page_booking.delete(driver);
		BddOutils.compareData("reservations", "src\\\\test\\\\resources\\\\DeleteData.xml");
	}

}
