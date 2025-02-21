package rahulshetty.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshetty.abstractcomponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
@FindBy(xpath="//input[@placeholder='Select Country']")
WebElement countryDropdown;
@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
WebElement selectCountry;
@FindBy(xpath="//a[normalize-space()='Place Order']")
WebElement placeOrder;

public void selectcountryFromDropdown(String countryName) throws InterruptedException {
	visibilityOfElementLocated(By.xpath("//input[@placeholder='Select Country']"));
	countryDropdown.sendKeys(countryName);
	Thread.sleep(1000);
	selectCountry.click();
}
public ConfirmationPage placeOrder() {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,600)");
	visibilityOfElementLocated(By.xpath("//a[normalize-space()='Place Order']"));
	placeOrder.click();
	ConfirmationPage confirmPage = new ConfirmationPage(driver);
	return confirmPage;
}

}

