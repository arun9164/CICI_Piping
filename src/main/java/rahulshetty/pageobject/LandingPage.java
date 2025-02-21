package rahulshetty.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshetty.abstractcomponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	public WebDriver driver;
	public LandingPage(WebDriver driver){
		// give life the parent class by using super keyword -- Serve parent first (do this in every subclass)
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="userEmail")
	WebElement emial;
	@FindBy(id="userPassword")
	WebElement pass;
	@FindBy(id="login")
	WebElement login;
	@FindBy(xpath="//div[contains(@class, 'ng-trigger')]")
	WebElement errorMsg;
	
	public ProductCataloguePage LoginIntoApplication(String email1, String pass1) {
		emial.sendKeys(email1);
		pass.sendKeys(pass1);
		login.click();
		ProductCataloguePage logue = new ProductCataloguePage(driver);
		return logue;
	}
	public void passURL() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getLoginPageErrorMessage() {
		waitElementToBeAppear(errorMsg);
		return errorMsg.getText();
		
	
}
}