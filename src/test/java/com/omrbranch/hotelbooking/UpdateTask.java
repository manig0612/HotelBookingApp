package com.omrbranch.hotelbooking;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UpdateTask {
	
	WebDriver driver;
	

	public static void main(String[] args) {
		UpdateTask ut = new UpdateTask();
		ut.launchBrowser();
		ut.login("manikandang0612@gmail.com", "Mani@0612");
		ut.goToProfile();
		ut.goToMyAccount();
		ut.searchOrder();
		ut.updateOrderDetails("2024-12-20");
		ut.closeWindow();
	}
	
	public void launchBrowser() {
		driver = new ChromeDriver();
		driver.get("https://www.omrbranch.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}
	
	public void login(String user, String pass) {
		try {
		WebElement userTxt = driver.findElement(By.id("email"));
	    userTxt.sendKeys(user);
	    WebElement passTxt = driver.findElement(By.id("pass"));
	    passTxt.sendKeys(pass);
	    WebElement loginBtnClicked = driver.findElement(By.xpath("//button[@value = 'login']"));
	    loginBtnClicked.click();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	    
	}
	
	public void goToProfile() {
		try {
			WebElement profile = driver.findElement(By.xpath("//a[@data-testid = 'username']"));
			profile.click();
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void goToMyAccount() {
		try {
		WebElement myAccount = driver.findElement(By.xpath("//a[contains(text() , 'Account')]"));
		myAccount.click();
		}
		catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void searchOrder() {
		try {
			
			WebElement bookingList = driver.findElement(By.id("bookinglist"));
			List<WebElement> toBeSearchOrder = bookingList.findElements(By.tagName("span"));
			WebElement searchTxt = toBeSearchOrder.getFirst();
			WebElement searchField = driver.findElement(By.name("search"));
			searchField.sendKeys(searchTxt.getText().substring(1), Keys.ENTER);
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void updateOrderDetails(String data) {
		try {
		WebElement editBtn = driver.findElement(By.xpath("//button[@class='edit btn filter_btn']"));
		editBtn.click();
		
		WebElement updateDetails = driver.findElement(By.name("check_in"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value', '"+data+"')", updateDetails);
		
		WebElement confirmBtn = driver.findElement(By.xpath("//button[@class = 'edit btn filter_btn']"));
		confirmBtn.click();
		
		WebElement getUpdateMsg = driver.findElement(By.xpath("//li[@class = 'alertMsg']"));
		System.out.println(getUpdateMsg.getText());
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public void closeWindow() {
		if(driver!= null) {
			driver.close();
		}
	}
	
	
}
