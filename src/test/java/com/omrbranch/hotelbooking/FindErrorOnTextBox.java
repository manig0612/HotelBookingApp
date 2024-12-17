package com.omrbranch.hotelbooking;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindErrorOnTextBox {

	WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		FindErrorOnTextBox bookError = new FindErrorOnTextBox();
		bookError.launchBrowser();
		bookError.login();
		bookError.selectHotelBooking();
		bookError.getErrorMsg();
		bookError.closeDriver();
		

	}

	public void launchBrowser() {
		driver = new ChromeDriver();
		driver.get("https://www.omrbranch.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}

	public void login() {

		WebElement userTxt = driver.findElement(By.id("email"));
		userTxt.sendKeys("manikandang0612@gmail.com");
		WebElement passTxt = driver.findElement(By.id("pass"));
		passTxt.sendKeys("Mani@0612");
		WebElement lgnBtnClick = driver.findElement(By.xpath("//button[@value = 'login']"));
		lgnBtnClick.click();

	}

	public void selectHotelBooking() {
		WebElement selectBook = driver.findElement(By.xpath("//img[@alt = 'hotel booking']"));
		selectBook.click();
	}

	public void searchBtnClicked() {
		WebElement frame = driver.findElement(By.xpath("//iframe[@class = 'iframe']"));
		driver.switchTo().frame(frame);
		WebElement searchBtn = driver.findElement(By.id("searchBtn"));
		searchBtn.click();
		driver.switchTo().defaultContent();
	}

	public void getErrorMsg() {
		searchBtnClicked();

		WebElement inValidState = driver.findElement(By.id("invalid-state"));
		WebElement inValidCity = driver.findElement(By.id("invalid-city"));
		WebElement inValidCheckIn = driver.findElement(By.id("invalid-check_in"));
		WebElement inValidCheckOut = driver.findElement(By.id("invalid-check_out"));
		WebElement inValidRooms = driver.findElement(By.id("invalid-no_rooms"));
		WebElement inValidAdult = driver.findElement(By.id("invalid-no_adults"));
		List<String> errorList = Arrays.asList(inValidState.getText(), inValidCity.getText(), inValidCheckIn.getText(),
				inValidCheckOut.getText(), inValidCheckOut.getText(), inValidRooms.getText(), inValidAdult.getText());
		for (String errorMsg : errorList) {
			System.out.println(errorMsg);
		}

	}
	
	public void closeDriver() {
		if(driver!= null) {
			driver.quit();
		}
	}

}
