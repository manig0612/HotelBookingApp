package com.omrbranch.hotelbooking;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CancelOrderTask {

	WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		CancelOrderTask cancel = new CancelOrderTask();
		cancel.launchBrowser();
		cancel.login("manikandang0612@gmail.com", "Mani@0612");
		cancel.goToProfile();
		cancel.goToMyAccount();
		cancel.searchOrder();
		//cancel.cancelOrder();
		
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
		} catch (Exception e) {
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
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void searchOrder() throws InterruptedException {
		

			WebElement bookingList = driver.findElement(By.id("bookinglist"));
			List<WebElement> editBtn = bookingList.findElements(By.xpath("//button[@class='edit btn filter_btn']"));
			for (WebElement cancelorder : editBtn) {
				if (cancelorder.isDisplayed()) {
					WebElement toBeSearchOrder = bookingList.findElement(By.tagName("span"));
					System.out.println(toBeSearchOrder.getText());
					//WebElement searchTxt = toBeSearchOrder.getFirst();
					WebElement searchField = driver.findElement(By.name("search"));
					searchField.sendKeys(toBeSearchOrder.getText().substring(1), Keys.ENTER);
					cancelOrder();
					break;
				}
				
		//cancelOrder();
			}

		
	}

	public void cancelOrder() throws InterruptedException {
		WebElement cancelBtnClick = driver.findElement(By.xpath("//a[@class = 'cancle btn filter_btn']"));
		cancelBtnClick.click();
		driver.switchTo().alert().accept();
		Thread.sleep(5000);
		
		WebElement getCancelMsg = driver.findElement(By.xpath("//li[@class = 'alertMsg']"));
		System.out.println(getCancelMsg.getText());
		closeWindow();
	}

	public void closeWindow() {
		if (driver != null) {
			driver.close();
		}
	}

}
