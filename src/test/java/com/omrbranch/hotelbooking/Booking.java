package com.omrbranch.hotelbooking;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Booking {
	
	WebDriver driver;
	Select select;
	
	
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
		WebElement selectState = driver.findElement(By.id("state"));
		select = new Select(selectState);
		select.selectByIndex(5);
		
		WebElement selectCity = driver.findElement(By.id("city"));
		select = new Select(selectCity);
		select.selectByIndex(4);
		
		WebElement selectRoom = driver.findElement(By.id("room_type"));
		select = new Select(selectRoom);
		List<WebElement> roomOptions = select.getOptions();
		for (int i = 1; i < roomOptions.size(); i++) {
			WebElement option = roomOptions.get(i);
			
	       if(option.isEnabled()) {
	    	   System.out.println(option.getText());
	       }
	       select.selectByIndex(i);
			
			
		}
		
		WebElement checkIn = driver.findElement(By.name("check_in"));
		checkIn.click();
		selectdate("14");
		
		WebElement checkOut = driver.findElement(By.name("check_out"));
		checkOut.click();
		
		selectdate("20");
		
		WebElement noOfRooms = driver.findElement(By.id("no_rooms"));
		select = new Select(noOfRooms);
		select.selectByIndex(3);
		
		WebElement noOfAdults = driver.findElement(By.id("no_adults"));
		select = new Select(noOfAdults);
		select.selectByIndex(2);
				
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
		List<WebElement> priceDetails = hotelElements.findElements(By.tagName("h2"));
		
		for (WebElement titles : hoteLTiltes) {
			System.out.println(titles.getText());
				
		}
		
		for (WebElement prices : priceDetails) {
			System.out.println(prices.getText());
		}
		
		WebElement clickHotel = driver.findElement(By.xpath("(//a[text() = 'Continue'])[3]"));
		clickHotel.click();
		
		driver.switchTo().alert().accept();
		
		WebElement selectedHotelName = driver.findElement(By.xpath("//h2[@class = 'px-3 py-2']"));
		System.out.println(selectedHotelName.getText());
		
	}
	
	public void fillDetatils() throws InterruptedException {
		
		WebElement selectMy = driver.findElement(By.id("own"));
		selectMy.click();
		
		WebElement chooseTitle = driver.findElement(By.name("title"));
		select = new Select(chooseTitle);
		select.selectByIndex(1);
		
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
		
		WebElement paymentType = driver.findElement(By.id("payment_type"));
		select = new Select(paymentType);
		select.selectByIndex(1);
		
		WebElement cardType = driver.findElement(By.id("card_type"));
		select = new Select(cardType);
		select.selectByIndex(1);
		
		WebElement cardNo = driver.findElement(By.id("card_no"));
		cardNo.sendKeys("5555555555552222");
		
		WebElement cardName = driver.findElement(By.id("card_name"));
		cardName.sendKeys("manikandan");
		
		WebElement expiryMonth = driver.findElement(By.id("card_month"));
		select = new Select(expiryMonth);
		select.selectByIndex(2);
		
		WebElement expiryyear = driver.findElement(By.id("card_year"));
		select = new Select(expiryyear);
		select.selectByIndex(5);
		
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
	
	public void selectdate(String date) {
		WebElement calenderdetails = driver.findElement(By.xpath("//table[@class = 'ui-datepicker-calendar']"));
		List<WebElement> dateelements = calenderdetails.findElements(By.tagName("td"));
		for (int i = 0; i < dateelements.size(); i++) {
			WebElement tableRow = dateelements.get(i);
			if (tableRow.getText().contains(date)) {
				tableRow.click();
				break;
			}
		}

	}

}
