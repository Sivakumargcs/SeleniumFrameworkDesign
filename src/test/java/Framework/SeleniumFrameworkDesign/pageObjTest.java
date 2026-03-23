package Framework.SeleniumFrameworkDesign;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import Framework.BaseTest.BaseTest;
import Framework.BaseTest.Retry;
import framework.Pageobejects.CartPage;
import framework.Pageobejects.ConfirmationPage;
import framework.Pageobejects.LoginPage;
import framework.Pageobejects.OrderPage;
import framework.Pageobejects.PaymentPage;
import framework.Pageobejects.ProductSearch;

public class pageObjTest extends BaseTest { 
		
	@Test(dataProvider = "getData", groups= {"Purchase"})
	public void SubmitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
	
	ProductSearch productSearch=loginpage.LoginApplication(input.get("email"), input.get("password"));
		
	productSearch.productlist();
	productSearch.GetProductByText(input.get("product"));
	productSearch.AddToCart(input.get("product"));
	CartPage cart= productSearch.GoToCart();
	
	Boolean cartprod = cart.Verifyproducts(input.get("product"));
	Assert.assertTrue(cartprod);
	PaymentPage pamymentpage =cart.CheckOutButton();
	
	pamymentpage.SelectCountry("India");
	ConfirmationPage confirmpage =pamymentpage.placeOrder();
	
	String confirmMessage = confirmpage.GetConfirmMessage();;	
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	System.out.println(confirmMessage);
	}

	@Test(dependsOnMethods= {"SubmitOrder"}, retryAnalyzer = Retry.class)
	public void VerifyOrderPage()
		{
		String productName = "ZARA COAT 3";
		ProductSearch productSearch=loginpage.LoginApplication("sivakumargcs@gmail.com", "Siva@123");
		OrderPage orderPage = productSearch.GotoOrderPage();
		Assert.assertTrue(orderPage.VerifyOrders(productName));
		}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		/*HashMap<String,String> Test1= new HashMap<String, String>();
		Test1.put("email","sivakumargcs@gmail.com");
		Test1.put("password", "Siva@123");
		Test1.put("product", "ZARA COAT 3");
		
		HashMap<String,String> Test2= new HashMap<String, String>();
		Test2.put("email","GcsSiva@gmail.com");
		Test2.put("password", "Siva@123");
		Test2.put("product", "ADIDAS ORIGINAL");
		*/
		List<HashMap<String,String>> data =getjsonDataToMap(System.getProperty("user.dir")+"\\src\\main\\java\\framework\\data\\Purchase.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	
	}
}
