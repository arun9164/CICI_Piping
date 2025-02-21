package rahulshetty.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshetty.abstractcomponents.AbstractComponents;

public class OrderHistoryPage extends AbstractComponents {
	WebDriver driver;

	public OrderHistoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//tr[@class='ng-star-inserted']/td[2]")
	List<WebElement> Oproducts;

	public boolean verifyOrderedProductInOrderPage(String productname) {
		boolean produ = Oproducts.stream().anyMatch(prod -> prod.getText().equals(productname));
		return produ;
	}

}
