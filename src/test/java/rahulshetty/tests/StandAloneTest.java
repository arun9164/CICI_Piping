package rahulshetty.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshetty.components.Base;
import rahulshetty.components.Retry;
import rahulshetty.pageobject.AddCartPage;
import rahulshetty.pageobject.CheckOutPage;
import rahulshetty.pageobject.ConfirmationPage;
import rahulshetty.pageobject.LandingPage;
import rahulshetty.pageobject.ProductCataloguePage;

public class StandAloneTest extends Base {

	public WebDriver driver;

//	List<String> arr = Arrays.asList("IPHONE 13 PRO");
//	String productName = "IPHONE 13 PRO";  QWERTY
	String orderMSG = "THANKYOU FOR THE ORDER.";

	@Test(dataProvider = "getData", groups = {"SubmitOrder"}, retryAnalyzer=Retry.class)
	void submitOrder(String email, String pass, String productName) throws InterruptedException, IOException {

		String countryName = "India";
		LandingPage land = getLandingPage();
		ProductCataloguePage logue = land.LoginIntoApplication(email, pass);

		List<WebElement> pot = logue.getProductList();
		logue.addProductToCart(productName);
		AddCartPage cartPage = logue.GoToCart();

		boolean product = cartPage.getproductName(productName);
		Assert.assertTrue(product, "Iphone is not present");
		CheckOutPage checkout = cartPage.checkOut();

		checkout.selectcountryFromDropdown(countryName);
		ConfirmationPage confirmPage = checkout.placeOrder();

		String message = confirmPage.confirmOrderBymessage();
		System.out.println("Message should " + message);
		AssertJUnit.assertEquals(message, orderMSG);

	}

	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { "arunnatikar99@gmail.com", "Rahul@1234", "IPHONE" },{ "arunnatikar99@gmail.com", "Rahul@1234", "ADIDAS" } };

	}
//	{"arunnatikar99@gmail.com","Rahul@1234", "IPHONE 13 PRO"},

//	@Test(dependsOnMethods= {"submitOrder"})
//	public void orderHistoryTest() {
//		ProductCataloguePage logue = land.LoginIntoApplication("arunnatikar99@gmail.com", "Rahul@1234");
//		OrderHistoryPage historyPage = logue.GoToOrderPage();
//		boolean orderedProd = historyPage.verifyOrderedProductInOrderPage(productName);
//		Assert.assertTrue(orderedProd);
//		
//		
//}
//	driver.close();  */
}
