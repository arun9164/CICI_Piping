package rahulshetty.tests;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshetty.components.Base;
import rahulshetty.components.Retry;

public class AddToCartTest extends Base{
	WebDriver driver;

	@Test(retryAnalyzer=Retry.class)
	void hi() throws InterruptedException {
		driver = new ChromeDriver();
//	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("arunnatikar99@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Rahul@1234");
		driver.findElement(By.id("login")).click();
	
		List<String> arr = Arrays.asList("QWERTY");
		String fine = "IPHONE 13 PRO";
		String orderMSG = "Thankyou for the order.";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body']//b")));
//		Thread.sleep(3000);
		List<WebElement> prod = driver.findElements(By.xpath("//div[@class='card-body']//b"));

		for (String prod1 : arr) {

			WebElement element1 = prod.stream().filter(product -> product.getText().equals(prod1)).findFirst()
					.orElse(null);

			if (element1 != null) {
				element1.findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();

			} else {
				System.out.println("product not poud " + prod1);
			}

		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='btn btn-custom'])[3]")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@class='btn btn-custom'])[3]")));
//		Thread.sleep(15000);

		 driver.findElement(By.xpath("(//button[@class='btn btn-custom'])[3]")).click();
//		wait.until(ExpectedConditions.visibilityOf(cartButton));

		
		List<WebElement> cartItems = driver.findElements(By.xpath("//ul[contains(@class,'cartWrap')]//h3"));
		boolean itemPresent = cartItems.stream().anyMatch(poduct -> poduct.getText().equals(fine));
		System.out.println(itemPresent);
		Assert.assertTrue(itemPresent, "Item  is missing from cart");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[.='Checkout']")));
		WebElement checkOutButton = driver.findElement(By.xpath("//button[.='Checkout']"));
		
		checkOutButton.click();
		WebElement selectCountry = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));

		wait.until(ExpectedConditions.visibilityOf(selectCountry));
		selectCountry.sendKeys("india");
		Thread.sleep(3000);

		WebElement countryDropdown = driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]"));

		System.out.println("name -" + countryDropdown);
		countryDropdown.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		
		js.executeScript("window.scrollBy(0,600)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Place Order']")));
		 driver.findElement(By.xpath("//a[normalize-space()='Place Order']")).click();
		 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='hero-primary']")));

		  WebElement orderPage = driver.findElement(By.xpath("//h1[@class='hero-primary']"));
		 
//		 wait.until(ExpectedConditions.visibilityOf(orderPage));
		 String orderPageMess = orderPage.getText();
		 System.out.println(orderPageMess);
		 if(orderMSG.equals(orderPageMess)) {
			 System.out.println("Successfully Ordered");
		 }
		 else {
			 System.out.println("Order not placed");
		 }
		 
		 
		

//	 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//ul[contains(@class,'cartWrap')]"))));
//	 for(WebElement item :cartItems) {
//		String itemsInCart = item.getText();
//		 
//		 if(itemsInCart.equals(fine)) {
//			 System.out.println(itemsInCart+" yes product present in the cart");
//		 }
//		 else {
//			 System.out.println(itemsInCart+" itnme is not present in the cart");
//		 }
//	 }
//	 

//	Iterator<WebElement> it = prod.iterator();
//	
//	while(it.hasNext()) {
//		
//	WebElement hi = it.next();
//	System.out.println(hi.getText());
//	
//	if(hi.getText().equals("IPHONE 13 PRO") ||hi.getText().equals("Banarsi Saree")) {
//		
//	System.out.println(" ---- --- "+hi.getText());
//		 WebElement clik = hi.findElement(By.xpath("//div[@class='card-body']//button[@class='btn w-10 rounded']"));
//		 clik.click();
////		wait.until(ExpectedConditions.elementToBeClickable(clik));

//	driver.close();
	}
}
