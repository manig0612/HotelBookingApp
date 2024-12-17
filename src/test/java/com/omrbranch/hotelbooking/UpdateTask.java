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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UpdateTask {
	
	WebDriver driver;
	

	public static void main(String[] args) {
		UpdateTask ut = new UpdateTask();
		ut.launchBrowser();
		ut.login("manikandang0612@gmail.com", "Mani@0612");
		ut.goToProfile();
		ut.goToMyAccount();
		ut.searchOrder();
		//ut.closeWindow();
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
		//	List<WebElement> toBeSearchOrder = bookingList.findElements(By.xpath("//div[@class = 'room-code']"));
			
			
			
			List<WebElement> hoteltitles = bookingList.findElements(By.tagName("h5"));
			List<WebElement> serachorder = bookingList.findElements(By.xpath("//div[@class = 'room-code']"));
			
			//List<WebElement> editButtons = bookingList.findElements(By.xpath("//button[@class='edit btn filter_btn']"));
			 List<WebElement> bookingEntries = driver.findElements(By.xpath("//div[@class='my-booking  prize']"));
			
			 System.out.println("Edit buttons count: " + bookingEntries.size());
		        System.out.println("Room codes count: " + serachorder.size());
		       int count = 0;
		       
		        for (int i = 0; i < bookingEntries.size(); i++) {
		        	
		        	WebElement bookingEntry = bookingEntries.get(i);
		        	
		        	//List<WebElement> editButtons = bookingEntry.findElements(By.xpath("//button[@class='edit btn filter_btn']"));
		        	WebElement edit = bookingEntry.findElement(By.xpath("//button[@class='cancle btn filter_btn mb-3']"));
		        	
		        	if (edit.isEnabled()) {
		        		 System.out.println(i);	
		        		 //break;
		        	}
		       
			      
			      
		           
		        }
		        System.out.println(count);
		        
//				
//		        editButtons
//				
//				 
//				if(editBtn.isDisplayed()) {
//					System.out.println(i);
//					
//					WebElement order = serachorder.get(i).findElement(By.tagName("span"));
//					System.out.println(order.getText());
//					break;
//				}
//				
//			}
			
			closeWindow();
//			for (int i = 0; i < editbuttons.size(); i++) {
//				WebElement edit = editbuttons.get(i);
//				//WebElement searchorderby = toBeSearchOrder.get(i);
//				if(edit.isDisplayed()) {
//					System.out.println("true");
//					WebElement searchorderby = toBeSearchOrder.get(i);
//					System.out.println(hoteltitles.get(i).getText());
//					System.out.println(searchorderby.getText());
//					edit.click();
//					updateOrderDetails("2024-12-20");
//					break;
//				}
//
//				else {
//					System.out.println("No order to be eligible to edit");
//				}
//			}
			
			
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void searchOrderId(String orderId) {
		WebElement searchField = driver.findElement(By.name("search"));
		searchField.sendKeys(orderId.substring(1), Keys.ENTER);
		System.out.println(orderId);
	}
	public void updateOrderDetails(String data) {
		try {
	//	WebElement editBtn = driver.findElement(By.xpath("//button[@class='edit btn filter_btn']"));
		//editBtn.click();
		
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
