package com.omrbranch.hotelbooking;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class HotelTitleReverse {
	
	WebDriver driver;
	Select select;
	JavascriptExecutor js;
	List<String> qa;
	
	public static void main(String[] args) throws InterruptedException {
		HotelTitleReverse ht = new HotelTitleReverse();
		ht.launchBrowser();
		ht.login();
		ht.selectHotelBooking();
		ht.SearchHotels();
		ht.desendingOrderofHotelTitle();
		ht.getHotelTitle();
		ht.closeWindow();
		
	}
	
	
	public void launchBrowser() {
		driver = new ChromeDriver();
		driver.get("https://www.omrbranch.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}
	
	public void login() {
		WebElement txtUser = driver.findElement(By.id("email"));
		txtUser.sendKeys("manikandang0612@gmail.com");
		WebElement passTxt = driver.findElement(By.id("pass"));
		passTxt.sendKeys("Mani@0612");
		
		WebElement lgnBtn = driver.findElement(By.xpath("//button[@value = 'login']"));
		lgnBtn.click();
		
		WebElement verifyUser = driver.findElement(By.xpath("//a[@data-testid = 'username']"));
		System.out.println(verifyUser.getText());
		
		
	}
	
	public void selectHotelBooking() {
		
		WebElement selectHotel = driver.findElement(By.xpath("//img[@alt = 'hotel booking']"));
		selectHotel.click();
		
	}
	
	public void SearchHotels() {
		
		selectDropDown(By.id("state"), "Tamil Nadu");
		selectDropDown(By.id("city"), "Coimbatore");
		selectAllOption(By.id("room_type"));
		
		executeJscript(By.name("check_in"), "2024-12-18");
	
		executeJscript(By.name("check_out"), "2024-12-20");
		selectDropDown(By.id("no_rooms"), "1-One");
	
		selectDropDown(By.id("no_adults"), "2-Two");
				
		WebElement noOdChilds = driver.findElement(By.id("no_child"));
		noOdChilds.sendKeys("1");
		clickSearchBtn();

	}
	
	public void desendingOrderofHotelTitle() {
		
		WebElement desecCheckBox = driver.findElement(By.id("value_ndesc"));
		js.executeScript("arguments[0].click()", desecCheckBox);
		
	}
	
	public void getHotelTitle( ) throws InterruptedException {
		qa = new ArrayList<String>();
		Thread.sleep(5000);
		WebElement hotelList = driver.findElement(By.id("hotellist"));
		List<WebElement> hoteltTitles = hotelList.findElements(By.tagName("h5"));
	
		
		for (WebElement title : hoteltTitles) {
			
		     qa.add(title.getText());
		}
		
		Collections.reverse(qa);
		for (String reversedTitle : qa) {
			System.out.println(reversedTitle);
		}
		
	}
	
	
	
	public void clickSearchBtn() {
		WebElement frame = driver.findElement(By.xpath("//iframe[@class = 'iframe']"));
		driver.switchTo().frame(frame);
		WebElement serchBtn = driver.findElement(By.id("searchBtn"));
		serchBtn.click();
		driver.switchTo().defaultContent();
		WebElement searchText = driver.findElement(By.xpath("//h5[text() = 'Select Hotel']"));
		System.out.println(searchText.getText());
	}
	
	public void selectAllOption(By locator) {
		WebElement element = driver.findElement(locator);
		select = new Select(element);
		List<WebElement> options = select.getOptions();
		for (WebElement selcbytext : options) {
			
			if(!selcbytext.isEnabled()) {
			System.out.println(selcbytext.getText());
		}
			else {
				select.selectByVisibleText(selcbytext.getText());
			}
		}
		
	}
	
	public void selectDropDown(By locator, String selectext) {
		WebElement element = driver.findElement(locator);
		select = new Select(element);
		select.selectByVisibleText(selectext);
	}
	
	
	public void executeJscript(By locator, String data) {
		WebElement element = driver.findElement(locator);
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','"+data+"' )", element);
	}
	
	public void closeWindow() {
		if (driver != null) {
			driver.quit();
		}
	}
	

}
