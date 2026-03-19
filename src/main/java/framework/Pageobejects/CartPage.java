package framework.Pageobejects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.Abstract.AbstractClass;

public class CartPage extends AbstractClass {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}

	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	
	public Boolean Verifyproducts(String productName) {		
		Boolean cartprod =cartProducts.stream().anyMatch(cartproduct -> 
		cartproduct.getText().equalsIgnoreCase(productName));
		return cartprod;
	}
	
	public PaymentPage CheckOutButton() {
		checkout.click();
		return new PaymentPage(driver);
	}
	
}
