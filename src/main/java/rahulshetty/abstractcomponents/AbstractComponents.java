package rahulshetty.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshetty.pageobject.AddCartPage;
import rahulshetty.pageobject.OrderHistoryPage;

public class AbstractComponents {
	public WebDriver driver;
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="(//button[@class='btn btn-custom'])[3]")
	WebElement cart;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderButton;
	 
		public AddCartPage GoToCart() throws InterruptedException {
//		visibilityOfElementLocated(By.xpath("(//button[@class='btn btn-custom'])[3]"));
		Thread.sleep(6000);
		cart.click();
		 AddCartPage cartPage = new AddCartPage(driver);
		 return cartPage;
	}
		public OrderHistoryPage GoToOrderPage() {
			orderButton.click();
			OrderHistoryPage historyPage = new OrderHistoryPage(driver);
			return historyPage;
		}
		
	public void waitElementToBeAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
		
	}
	
	public void visibilityOfElementLocated(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

}
