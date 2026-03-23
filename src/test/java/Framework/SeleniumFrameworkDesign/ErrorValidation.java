package Framework.SeleniumFrameworkDesign;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import Framework.BaseTest.BaseTest;
import framework.Pageobejects.CartPage;
import framework.Pageobejects.ConfirmationPage;
import framework.Pageobejects.LoginPage;
import framework.Pageobejects.PaymentPage;
import framework.Pageobejects.ProductSearch;


public class ErrorValidation extends BaseTest { 
	
	@Test
	public void LoginErrorValidation() throws InterruptedException, IOException {
	
	loginpage.LoginApplication("sivakumargcs@gmail.com", "Siva@1234");
	Assert.assertEquals("Incorrect email or password.", loginpage.getErrorMessage());
	}

	@Test
	public void ProductErrorValidation() throws InterruptedException {
		
		String productName = "ZARA COAT 3";
		ProductSearch productSearch=loginpage.LoginApplication("sivakumargcs@gmail.com", "Siva@123");
		productSearch.productlist();
		productSearch.AddToCart(productName);
		CartPage cart= productSearch.GoToCart();
		Boolean cartprod = cart.Verifyproducts("ZARA COAT 2");
		Assert.assertFalse(cartprod);
	}
} 			
