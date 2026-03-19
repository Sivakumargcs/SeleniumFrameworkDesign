package framework.Pageobejects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.Abstract.AbstractClass;

public class LoginPage extends AbstractClass {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}

	@FindBy(id="userEmail")
	WebElement useremail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	public ProductSearch LoginApplication(String email, String pwd) {
		useremail.sendKeys(email);
		password.sendKeys(pwd);
		submit.click();		
		return new ProductSearch(driver);
	}
	
	public void GoTo() {
		driver.get("https://rahulshettyacademy.com/client");

	}
	
	
}
