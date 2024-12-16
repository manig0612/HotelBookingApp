package com.omrbranch.hotelbooking;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;



public class BookingSortOrder {
	
		WebDriver driver;
		Select select;
		JavascriptExecutor js;
		List<Integer>dev;
		List<Integer>qa;
		
		
		public static void main(String[] args) throws InterruptedException {
			BookingSortOrder bookSort = new BookingSortOrder();
			bookSort.launchBrowser();
			bookSort.login("manikandang0612@gmail.com", "Mani@0612");
			
			bookSort.SearchHotels();
			bookSort.iterateHotelPrice();
			bookSort.closeSetup();
			
		}
		
		public void launchBrowser() {
			driver = new ChromeDriver();
			driver.get("https://www.omrbranch.com/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}
		
		public void login(String userName, String passWord) {
			
			try {
			WebElement userTxt = driver.findElement(By.id("email"));
			userTxt.sendKeys(userName);
			WebElement passTxt = driver.findElement(By.id("pass"));
			passTxt.sendKeys(passWord);
			WebElement lgnBtnClick = driver.findElement(By.xpath("//button[@value = 'login']"));
			lgnBtnClick.click();
			}
			catch (NoSuchElementException e) {
				System.out.println(e.getLocalizedMessage());
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		public void searchBtnClicked() {
			try {
			WebElement frame = driver.findElement(By.xpath("//iframe[@class = 'iframe']"));
			driver.switchTo().frame(frame);
			WebElement searchBtn = driver.findElement(By.id("searchBtn"));
			searchBtn.click();
	    	driver.switchTo().defaultContent();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		
	
		public void SearchHotels() {
			
			try {
			
			WebElement selectBook = driver.findElement(By.xpath("//img[@alt = 'hotel booking']"));
			selectBook.click();
			
			selectDropDown(By.id("state"), "Tamil Nadu");
			selectDropDown(By.id("city"), "Coimbatore");
			
			selectAllOption(By.id("room_type"));
			executeJscript(By.name("check_in"), "2024-12-18");
			
			executeJscript(By.name("check_out"), "2024-12-20");
			
			selectDropDown(By.id("no_rooms"), "1-One");
			selectDropDown(By.id("no_adults"), "2-Two");
					
			WebElement noOdChilds = driver.findElement(By.id("no_child"));
			noOdChilds.sendKeys("1");
			
			searchBtnClicked();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void selectLowToHigh() {
			WebElement lowToHighBtn = driver.findElement(By.id("value_pltoh"));
			js.executeScript("arguments[0].click()", lowToHighBtn);
			
		}
		
		public void iterateHotelPrice() throws InterruptedException  {
			try {
			selectLowToHigh();
			Thread.sleep(5000);
			dev = new ArrayList<Integer>();
			qa = new ArrayList<Integer>();
			WebElement hotelList = driver.findElement(By.id("hotellist"));
			List<WebElement> hotelprices = hotelList.findElements(By.tagName("strong"));
			
			for (WebElement price : hotelprices) {
				
				dev.add(Integer.parseInt(price.getText().substring(2).replace(",", "")));	
			}
			
			qa.addAll(dev);
			Collections.sort(qa);
			
			if (dev.equals(qa)) {
				System.out.println("true");
			}
			else {
				System.out.println("false");
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
					
		}
		
		public void selectDropDown(By locator, String selectext) {
			WebElement element = driver.findElement(locator);
			select = new Select(element);
			select.selectByVisibleText(selectext);
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
		
		public void executeJscript(By locator, String data) {
			WebElement element = driver.findElement(locator);
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('value','"+data+"' )", element);
		}
		
		public void closeSetup() {
			if (driver!= null) {
				driver.close();
			}
		}

	}


