package com.omrbranch.hotelbooking;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Booking {
	
	WebDriver driver;
	Select select;
	JavascriptExecutor js;
	
	
	public static void main(String[] args) throws InterruptedException {
		
		Booking bb = new Booking();
		bb.launchBrowser();
		bb.login();
		bb.selectHotelBooking();
		bb.SearchHotels();
		bb.getHotels();
		bb.fillDetatils();
		bb.specialReaquest();
		bb.payMentMethods();
		bb.getBookingDetails();
		bb.closeWindow();
		
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
	
	public void clickSearchBtn() {
		WebElement frame = driver.findElement(By.xpath("//iframe[@class = 'iframe']"));
		driver.switchTo().frame(frame);
		WebElement serchBtn = driver.findElement(By.id("searchBtn"));
		serchBtn.click();
		driver.switchTo().defaultContent();
		WebElement searchText = driver.findElement(By.xpath("//h5[text() = 'Select Hotel']"));
		System.out.println(searchText.getText());
	}
	
	public void getHotels() {
		WebElement hotelElements = driver.findElement(By.id("hotellist"));
		List<WebElement> hoteLTiltes = hotelElements.findElements(By.tagName("h5"));
		List<WebElement> priceDetails = hotelElements.findElements(By.tagName("strong"));
		List<WebElement> continebtnElements = hotelElements.findElements(By.xpath("//a[@class='btn filter_btn']"));
		
		for (int i = 0; i < hoteLTiltes.size(); i++) {
			System.out.println(hoteLTiltes.get(i).getText());
			System.out.println(priceDetails.get(i).getText());
		}
		
		WebElement continueBtnClick = continebtnElements.get(2);
		continueBtnClick.click();
		
		driver.switchTo().alert().accept();
		
		WebElement selectedHotelName = driver.findElement(By.xpath("//h2[@class = 'px-3 py-2']"));
		System.out.println(selectedHotelName.getText());
		
	}
	
	public void fillDetatils() throws InterruptedException {
		
		WebElement selectMy = driver.findElement(By.id("own"));
		selectMy.click();
		
		selectDropDown(By.name("title"), "Mr.");
		
		WebElement firstNameTxt = driver.findElement(By.id("first_name"));
		firstNameTxt.sendKeys("Mani");
		
		WebElement lastNameTxt = driver.findElement(By.id("last_name"));
		lastNameTxt.sendKeys("Kandan");
		
		WebElement mobileNumTxt = driver.findElement(By.id("user_phone"));
		mobileNumTxt.sendKeys("9003004000");
		
		WebElement emailTxt = driver.findElement(By.id("user_email"));
		emailTxt.sendKeys("manikandan345@gmail.com");
		
		WebElement gst = driver.findElement(By.id("gst"));
		gst.click();
		
		WebElement registerGst = driver.findElement(By.id("gst_registration"));
		registerGst.sendKeys("9043592058");
		
		WebElement gstCompanyName = driver.findElement(By.id("company_name"));
		gstCompanyName.sendKeys("Greens Tech OMR Branch");
		
		WebElement gstCompantAddress = driver.findElement(By.id("company_address"));
		gstCompantAddress.sendKeys("Thoraipakkam");
		
		WebElement clickNxtBtn = driver.findElement(By.id("step1next"));
		clickNxtBtn.click();
		
		
		
	}
	
	
	public void specialReaquest() {
		
		WebElement largeBed = driver.findElement(By.id("bed"));
		largeBed.click();
		
		WebElement clickToNext = driver.findElement(By.id("step2next"));
		clickToNext.click();
		
	}
	
	public void payMentMethods() {
		WebElement selectPatment = driver.findElement(By.xpath("//div[@class = 'credit-card pm']"));
		selectPatment.click();
	
		selectDropDown(By.id("payment_type"), "Debit Card");
		selectDropDown(By.id("card_type"), "Visa");
		
		WebElement cardNo = driver.findElement(By.id("card_no"));
		cardNo.sendKeys("5555555555552222");
		
		WebElement cardName = driver.findElement(By.id("card_name"));
		cardName.sendKeys("manikandan");
	
		selectDropDown(By.id("card_month"), "April");
		selectDropDown(By.id("card_year"), "2027");
		
		WebElement cvvTxt = driver.findElement(By.id("cvv"));
		cvvTxt.sendKeys("333");
				
		WebElement subitBtn = driver.findElement(By.id("submitBtn"));
		subitBtn.click();
		
		
	}
	
	public void getBookingDetails() {
		WebElement getBookingId = driver.findElement(By.xpath("//h2[@class='couppon-code']"));
		System.out.println(getBookingId.getText().replace("#", ""));
		
		WebElement element = driver.findElement(By.xpath("//p[contains(text(),  'Hotel ')]"));
		WebElement hotelName = element.findElement(By.tagName("strong"));
		System.out.println(hotelName.getText());
		
		
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
			driver.close();
		}
	}
	
	

}
