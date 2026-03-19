package Framework.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import framework.Pageobejects.CartPage;
import framework.Pageobejects.ConfirmationPage;
import framework.Pageobejects.LoginPage;
import framework.Pageobejects.PaymentPage;
import framework.Pageobejects.ProductSearch;
import io.github.bonigarcia.wdm.WebDriverManager;

public class pageObjTest { 
	
	public static void main(String[] args) throws InterruptedException {
	
	String productName = "ZARA COAT 3";
		
	WebDriverManager.chromedriver().setup();
	WebDriver driver= new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	
	LoginPage loginpage = new LoginPage(driver);
	loginpage.GoTo();
	ProductSearch productSearch=loginpage.LoginApplication("sivakumargcs@gmail.com", "Siva@123");
		
	productSearch.productlist();
	productSearch.GetProductByText(productName);
	productSearch.AddToCart(productName);
	CartPage cart= productSearch.GoToCart();
	
	Boolean cartprod = cart.Verifyproducts(productName);
	Assert.assertTrue(cartprod);
	PaymentPage pamymentpage =cart.CheckOutButton();
	
	pamymentpage.SelectCountry("India");
	ConfirmationPage confirmpage =pamymentpage.placeOrder();
	
	String confirmMessage = confirmpage.GetConfirmMessage();;	
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	driver.close();
	
	
	}

}
