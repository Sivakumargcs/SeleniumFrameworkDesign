package Framework.BaseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import framework.Pageobejects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LoginPage loginpage;
	
	public WebDriver InitializeBrowser() throws IOException {
	
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\framework\\Resources\\Global.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")){
			
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();	
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			
			System.setProperty("WebDriver.driver.Firefox", "path");
			driver= new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			System.setProperty("WebDriver.driver.edge", "path");
			driver= new EdgeDriver();			
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getjsonDataToMap(String FilePath) throws IOException {
		String jsonData = FileUtils.readFileToString(new File(FilePath),
				StandardCharsets.UTF_8);
	
		ObjectMapper mapper =new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonData, new TypeReference<List<HashMap<String, String>>>() {
		});
		return data;
	}
	

	public String getScreenShot(String testCseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"\\Reports\\"+testCseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"\\Reports\\"+testCseName+".png";
	}
	
	@BeforeMethod(alwaysRun=true)
	public LoginPage launchApplication() throws IOException {
		driver= InitializeBrowser();
		loginpage = new LoginPage(driver);
		loginpage.GoTo();
		return loginpage;
	}
	
	@AfterMethod
	public void tearDown() {
	   driver.close();
	}
	
}
