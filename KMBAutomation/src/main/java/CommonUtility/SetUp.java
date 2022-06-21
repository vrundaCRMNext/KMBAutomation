package CommonUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.bonigarcia.wdm.WebDriverManager;



public class SetUp implements AutoConst 
{
	public static WebDriver driver;
	//public static WebDriverWait wait;
	//public static JavascriptExecutor js = (JavascriptExecutor) driver;
	static Logger log = LoggerFactory.getLogger(SetUp.class);

	
	/*
	 * public static void browserLaunch() throws IOException { Properties prop=new
	 * Properties(); String currentDir =System.getProperty("user.dir");
	 * FileInputStream fis =new FileInputStream(currentDir+
	 * "\\src\\main\\java\\CommonUtility\\Config.properties");
	 * 
	 * prop.load(fis);
	 * 
	 * String browserName=prop.getProperty("Browser"); //using webdriver manager
	 * if(browserName.equalsIgnoreCase("Chrome")) { ChromeOptions chromeOptions =
	 * new ChromeOptions(); WebDriverManager.chromedriver().setup(); driver = new
	 * ChromeDriver(chromeOptions); log.info("Chrome Browser Launched.."); } else
	 * if(browserName.equalsIgnoreCase("Firefox")) {
	 * 
	 * FirefoxOptions firefoxOptions = new FirefoxOptions();
	 * WebDriverManager.firefoxdriver().setup(); driver = new
	 * FirefoxDriver(firefoxOptions); log.info("Firefox Browser Launched.."); }
	 * 
	 * }
	 */
	
	public static void setUpTest() throws IOException 
	{
		
		Properties prop=new Properties();
		String currentDir =System.getProperty("user.dir");
		FileInputStream fis =new FileInputStream(currentDir+"\\src\\main\\java\\CommonUtility\\Config.properties");

		prop.load(fis);
		
		String URL = prop.getProperty("URL");
		String URL1 = prop.getProperty("URL1");
		String CRNClrURL = prop.getProperty("CRNClrURL");
		String browserName=prop.getProperty("Browser");
		System.out.println("Browser name = "+browserName);
		
		//using webdriver manager
				if(browserName.equalsIgnoreCase("Chrome"))
				{
					ChromeOptions chromeOptions = new ChromeOptions();
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver(chromeOptions);
					log.info("Chrome Browser Launched..");
				}
				else if(browserName.equalsIgnoreCase("Firefox"))
				{
					
					FirefoxOptions firefoxOptions = new FirefoxOptions();
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver(firefoxOptions);
					log.info("Firefox Browser Launched..");
				}
		

		int TotalTestScenarios=ExcelOperation.getRowCount(SheetName);
		
		for(int i=0;i<=TotalTestScenarios;i++)
		{
			String TestCaseIDName=ExcelOperation.getCellData(SheetName, "TCID", i);
			String RunMode=ExcelOperation.getCellData(SheetName,"RunMode", i);
			if(RunMode.equalsIgnoreCase("Yes"))
			{
				if(TestCaseIDName.equalsIgnoreCase("TC01"))
				{
					driver.get(URL);
					log.info("URL : "+URL);
					driver.manage().window().maximize();
					//wait = new WebDriverWait(driver,Duration.ofSeconds(70));
				}
				else if(TestCaseIDName.equalsIgnoreCase("TC03") || TestCaseIDName.equalsIgnoreCase("TC04") || TestCaseIDName.equalsIgnoreCase("TC05") || TestCaseIDName.equalsIgnoreCase("TC06") || TestCaseIDName.equalsIgnoreCase("TC07"))
				{
					driver.get(URL);
					log.info("URL : "+URL);
					driver.manage().window().maximize();
				}
				else if(TestCaseIDName.equalsIgnoreCase("TC02"))
				{
					driver.get(CRNClrURL);
					log.info("URL : "+CRNClrURL);
					driver.manage().window().maximize();
				}
			}//end o
				
		}
		
		
	}

	//@AfterTest
	public void tearDownTest() 
	{
		//Close Browser		
		  driver.close();
		// driver.quit(); 
		  log.info("Browser Closed..");
	}


}
