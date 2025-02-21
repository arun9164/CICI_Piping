package rahulshetty.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshetty.abstractcomponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents{
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(xpath="//h1[@class='hero-primary']")
	WebElement orderedTitle;
	
	public String confirmOrderBymessage() {
		visibilityOfElementLocated(By.xpath("//h1[@class='hero-primary']"));
		String message = orderedTitle.getText();
		return message;
	}

}


