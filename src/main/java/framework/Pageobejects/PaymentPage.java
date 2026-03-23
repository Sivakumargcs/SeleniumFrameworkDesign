package framework.Pageobejects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.Abstract.AbstractClass;

public class PaymentPage extends AbstractClass {
	
	WebDriver driver;
	
	
	public PaymentPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}

	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement clickCountry;
	
		
	By placeOrder = By.cssSelector(".action__submit");
	By placbtn = By.cssSelector(".ngx-spinner-overlay");
	By results = By.cssSelector(".ta-results");
	
	
	public void SelectCountry(String countryName) throws InterruptedException {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		WaitForElementToAppear(results);
		clickCountry.click();
		
	}
	
	public ConfirmationPage placeOrder() throws InterruptedException  {
		
		WaitForElementToDisappearBy(placbtn);
		WebElement placebutton = WaitForElementToClickable(placeOrder);
		ScrollToView(placebutton);
		return new ConfirmationPage(driver);
		 
		
	}

	
}
