package POM_HLSalesDAP;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;

import CommonUtility.CommonMethods;
import CommonUtility.ScreenShot;
import CommonUtility.SetUp;

public class HLDAPOnlyTopUp extends SetUp
{
	public Log log = LogFactory.getLog(HLDAPOnlyTopUp.class);
	
	public HLDAPOnlyTopUp(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[contains(text(),'Provide applicant current home loan details')]")
	private WebElement screenHdr;
	
	@FindBy(xpath="//span[contains(text(),'EMI Paid Currently')]")			//EMI lbl
	private WebElement EMILbl;
	
	@FindBy(xpath="//span[contains(text(),'EMI Paid ')]/following::div[1] //input")			//EMI fld
	private WebElement EMIFld;
	//span[contains(text(),'EMI Paid ')]/following::div[1] //input
	//input[@data-tip=\"Please enter EMI\"]
	
	@FindBy(xpath="//span[contains(text(),'Purchase price of property')]")		//price of Property Lbl
	private WebElement priceOfPropertyLbl;

	@FindBy(xpath="//span[contains(text(),'Purchase price of property')]/following::div[1] //input")		//Price Of property fld
	private WebElement propertyPriceFld;
	
	@FindBy(xpath="//span[contains(text(),'Required loan amount')]/following::div[1] //input")
	private WebElement reqLoanAmtFld;
	
	@FindBy(xpath="//span[contains(text(),'Required loan amount')]")
	private WebElement reqLoanAmtLbl;
	
	@FindBy(xpath="//button[contains(text(),'Proceed')]")		//Prcoeed Button
	private WebElement proceedBtn;
	
	public void topUpDetailsScreen(String sheetName)throws Exception
	{
		log.info(CommonMethods.getElementText(screenHdr));
		
		CommonMethods.input(EMIFld, sheetName, "Current EMI", 1);
		log.info(CommonMethods.getElementText(EMILbl)+" = "+CommonMethods.getElementValue(EMIFld));
		
		CommonMethods.input(propertyPriceFld, sheetName, "Price Of Property", 1);
		log.info(CommonMethods.getElementText(priceOfPropertyLbl)+" = "+CommonMethods.getElementValue(propertyPriceFld));
		
		CommonMethods.input(reqLoanAmtFld, sheetName, "Loan Amount", 1);
		log.info(CommonMethods.getElementText(reqLoanAmtLbl)+" = "+CommonMethods.getElementValue(reqLoanAmtFld));
		
		ScreenShot.takeSnapShot("TopUpDetailsScreen", "Pass");
		CommonMethods.Click(proceedBtn);
		
	}
	
	
	
}
