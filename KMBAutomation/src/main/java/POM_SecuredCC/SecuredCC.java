package POM_SecuredCC;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CommonUtility.CommonMethods;
import CommonUtility.ExcelOperation;
import CommonUtility.ScreenShot;
import CommonUtility.SetUp;

public class SecuredCC extends SetUp
{

	public Logger log = LoggerFactory.getLogger(SecuredCC.class);
	
	public SecuredCC(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath="//input[@name='crn']")
	private WebElement CRNtxtfld;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement submitBtn;
	
	@FindBy(xpath="//div[@id='c_74027150151']")
	private WebElement chkBox;
	
	@FindBy(xpath="//button[@id='missing_429']")
	private WebElement getItNowBtn;
		
	
	@FindBy(xpath="//input[@data-autoid='100000000_ctrl']")
	private WebElement V_CRN;
	
	@FindBy(xpath="//a[@data-autoid='Fetch']")
	private WebElement FetchBtn;
	
	
	@FindBy(xpath="//button[@id='missing_101']")
	private WebElement confirmBtn;
	
	
	//To capture LeadID from CRM
	@FindBy(xpath="//body/div[@id='main']/div[1]/div[1]/ul[1]/li[3]/div[1]/div[1]/a[1]")  //Sales
	private WebElement sales;
	
	@FindBy(xpath="//span[@title='Leads']")
	private WebElement LeadsObject;
	
	@FindBy(xpath="//a[@data-autoid='APP_SEARCH_ITEM_ICON']")
	private WebElement leadSrchIcon;
	
	@FindBy(xpath="//input[@name='cust_23']")
	private WebElement CRNNOSrch;
	
	@FindBy(xpath="//a[@data-autoid='Search']")
	private WebElement CRMSrchBtn;
	
	@FindBy(xpath="//span[@data-autoid='LE_NUMBER_ctrl']")
	private WebElement LeadIDFld;
	
	@FindBy(xpath="//div[@data-autoid='cust_23']/div")
	private WebElement CRNNoFld;

	public void clearCRN(int rowNum, String sheetName)
	{
		try 
		{
			CommonMethods.Click(V_CRN);
			CommonMethods.input(V_CRN,sheetName, "CRN", rowNum);
			CommonMethods.Click(FetchBtn);
			Thread.sleep(2000);
			log.info("CRN Clearing done ..");
			ScreenShot.takeSnapShot("Clearing_CRN", "Pass");
			
		} catch (Exception e) {
			log.error("Exception occured while Clearing CRN from CRM site due to :"+e.getMessage());
		}
	}
	
	
	public void intiateSCC(int rowNum, String sheetName) throws Exception
	{
		try 
		{
			Thread.sleep(2000);
			driver.get("https://kmb.crmnext.com/sng7/vividkotak/kotak.html");
			driver.manage().window().maximize();
			Thread.sleep(2000);
			
			CommonMethods.Click(CRNtxtfld);
			CommonMethods.input(CRNtxtfld, sheetName, "CRN", rowNum);
			ScreenShot.takeSnapShot("SecurredCCStep1","Pass");
			try {
				CommonMethods.Click(submitBtn);
			}catch(Exception e) {}
			Thread.sleep(1000);
			
			ScreenShot.Ashot("SecurredCCStep2", "Pass");
			CommonMethods.scrollByVisibilityofElement(chkBox);
			CommonMethods.Click(chkBox);
			CommonMethods.highLight(getItNowBtn);
			CommonMethods.Click(getItNowBtn);
			Thread.sleep(2000);
			
			ScreenShot.Ashot("SecurredCCStep3", "Pass");
			CommonMethods.Click(confirmBtn);
			
		} catch (Exception e) {
			log.error("Exception occured while running Securred CC journey due to :"+e.getMessage());
			ScreenShot.takeSnapShot("SecuredCCException", "Fail");
		}
	}
	
	public void captureLeadID(int rowNum, String sheetName)
	{
		try 
		{
			CommonMethods.highLight(sales);
			CommonMethods.mouseHover(sales);
			CommonMethods.highLight(LeadsObject);
			CommonMethods.Click(LeadsObject);
			CommonMethods.highLight(leadSrchIcon);
			CommonMethods.Click(leadSrchIcon);
			CommonMethods.Click(CRNNOSrch);
			CommonMethods.input(CRNNOSrch,sheetName, "CRN", rowNum);
			CommonMethods.Click(CRMSrchBtn);
			Thread.sleep(2000);
			CommonMethods.scrollByVisibilityofElement(LeadIDFld);
			//String LeadID = CommonMethods.getElementText(LeadIDFld);
			log.info("Lead ID = "+CommonMethods.getElementText(LeadIDFld)+" For CRN = "+CommonMethods.getElementText(CRNNoFld));
			ScreenShot.takeSnapShot("CaptureLeadID", "Pass");
			//Write Capture Lead ID to excel
			Thread.sleep(2000);
			ExcelOperation.writeToExcel(sheetName,rowNum, 1,CommonMethods.getElementText(LeadIDFld));
			Thread.sleep(2000);

			
		} catch (Exception e) {
			log.error("Exception in capture Lead ID from CRM due to "+e.getMessage());
		}
	}
	
}
