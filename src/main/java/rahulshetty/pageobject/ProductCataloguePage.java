package rahulshetty.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshetty.abstractcomponents.AbstractComponents;

public class ProductCataloguePage extends AbstractComponents {
	public WebDriver driver;
	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	
	By prodPageLoct = By.xpath("//div[@class='card-body']//b");
	
	public List<WebElement> getProductList()  {
		visibilityOfElementLocated(prodPageLoct);
		return products;
	}
	
//	public WebElement getProductByName(String pname) {
//	
////			WebElement prod = getProductList().stream().filter(product -> product.getText().equals(pname)).findFirst()
////					.orElse(null);
//		WebElement prod = getProductList().stream().filter(product -> 
//		product.findElement(By.xpath("//b")).getText().equals(pname)).findFirst().orElse(null);
//			return prod;
//		
//	}
	
	
	
	
	public WebElement getProductByName(String pname) {
	    // Debugging: Print available product names
	    getProductList().forEach(product -> System.out.println("Product found: " + product.findElement(By.xpath(".//b")).getText()));

	    WebElement prod = getProductList().stream()
	            .filter(product -> product.findElement(By.xpath(".//b")).getText().equals(pname))
	            .findFirst()
	            .orElse(null);
	    
	    if (prod == null) {
	        System.out.println("Product '" + pname + "' not found in the list!");
	    }
	    
	    return prod;
	}

	public void addProductToCart(String pname) {
		 WebElement prod1 = getProductByName(pname);
		prod1.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	}
	
	
	
}
