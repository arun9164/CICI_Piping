package rahulshetty.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshetty.abstractcomponents.AbstractComponents;

public class AddCartPage extends AbstractComponents{
	
WebDriver driver;

public AddCartPage(WebDriver driver) {
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
}
@FindBy(xpath="//ul[contains(@class,'cartWrap')]//h3")
List<WebElement> cartItems;
@FindBy(xpath="//button[.='Checkout']")
WebElement checkOut;

public boolean getproductName(String productName) throws InterruptedException {
	Thread.sleep(2000);
	boolean itemPresent = cartItems.stream().anyMatch(poduct -> poduct.getText().equals(productName));
	
	return itemPresent;
	
}
public CheckOutPage checkOut() {
	checkOut.click();
	CheckOutPage checkout = new CheckOutPage(driver);
	return checkout;
}

}


//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[.='Checkout']")));
//WebElement checkOutButton = driver.findElement(By.xpath("//button[.='Checkout']"));
//
//checkOutButton.click();