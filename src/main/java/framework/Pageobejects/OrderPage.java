package framework.Pageobejects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.Abstract.AbstractClass;

public class OrderPage extends AbstractClass {
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}

	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> OrderProducts;
			
	public Boolean VerifyOrders(String productName) {		
		Boolean orderprod = OrderProducts.stream().anyMatch(product-> 
		product.getText().equalsIgnoreCase(productName));
		return orderprod;
		
	}

}
