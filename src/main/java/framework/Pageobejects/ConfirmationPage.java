package framework.Pageobejects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.Abstract.AbstractClass;

public class ConfirmationPage extends AbstractClass {
	
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}

	By confirmText = By.xpath("//*[contains(text(),'Thankyou')]");
	
	public String GetConfirmMessage() {
	
		return WaitForElementToAppear(confirmText).getText();
	}
	
	
}
