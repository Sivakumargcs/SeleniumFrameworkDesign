package framework.Pageobejects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.Abstract.AbstractClass;

public class ProductSearch extends AbstractClass {
	
	WebDriver driver;
	
	public ProductSearch(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}

	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement loading;
	
	
	
	By searchProducts = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> productlist() {
		WaitForElementToAppear(searchProducts);
		return products;
		}
	
	public WebElement GetProductByText(String productName) {
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).
				getText().equals(productName)).findFirst().orElse(null);
		return prod;
		}
	
	public void AddToCart(String productName) throws InterruptedException {
		WebElement prod=GetProductByText(productName);
		prod.findElement(addToCart).click();
		WaitForElementToAppear(toastMessage);
		WaitForElementToDisappear(loading);
		
		
	}

	

	
}
