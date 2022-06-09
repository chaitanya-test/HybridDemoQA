package BaseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	public static WebDriver driver;
	public Properties config;
	public Properties locators;
	
	@BeforeClass
	public void setup() throws IOException
	{
		locators = new Properties();
		FileInputStream fis1 = new FileInputStream("C:\\Users\\chaitanya\\workspace\\DemoQAProject\\src\\Utility\\locators.properties");
		locators.load(fis1);
		
		config = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\chaitanya\\workspace\\DemoQAProject\\src\\Utility\\configurations.properties");
		config.load(fis);
		
		if((config.getProperty("browser")).equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if((config.getProperty("browser")).equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(config.getProperty("url"));
	}
	
	public void sendit(String locator,String value)
	{
		driver.findElement(By.xpath(locators.getProperty(locator))).sendKeys(value);
	}
	
	public void clickit(String locator)
	{
		driver.findElement(By.xpath(locators.getProperty(locator))).click();
	}
	
	@AfterMethod
	public void takescreenshot(ITestResult result) throws IOException
	{
		if(ITestResult.FAILURE == result.getStatus())
		{
			TakesScreenshot screen = (TakesScreenshot)driver;
			File screenshot = screen.getScreenshotAs(OutputType.FILE);
			File dest = new File("C:\\Users\\chaitanya\\workspace\\DemoQAProject\\src\\Outputs\\ScreenShots\\"+result.getName()+".jpg");
			FileUtils.copyFile(screenshot, dest);
		}	
			
	}
		
	@AfterClass
	public void tearDown()
	{
		
		driver.quit();		
	}
	
	
}
	
