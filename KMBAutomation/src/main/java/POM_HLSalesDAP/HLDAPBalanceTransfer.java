package POM_HLSalesDAP;

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

public class HLDAPBalanceTransfer extends SetUp
{
	public Logger log = LoggerFactory.getLogger(HLDAPBalanceTransfer.class);
	
	
	public HLDAPBalanceTransfer(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
/**************************Balance Transfer/Top Up Screen ****************************/
@FindBy(xpath="//span[contains(text(),'Provide applicant current Home loan details')]")				//Screen header\
private WebElement btHeader;

@FindBy(xpath="//div[@name=\"Home_Container\"] //select")			//Home Loan Provider LOVS
private WebElement homeLoanProviderLOVS;

@FindBy(xpath="//span[contains(text(),'Outstanding loan amount')]")		//Outstanding Loan amt lbl
private WebElement loanAmtLbl;

@FindBy(xpath="//span[contains(text(),'Outstanding loan amount')]/following::div[1] //input")		//Loan Amt fld
private WebElement loanAmtFld;

@FindBy(xpath="//span[contains(text(),'Purchase price of property')]")		//price of Property Lbl
private WebElement priceOfPropertyLbl;

@FindBy(xpath="//span[contains(text(),'Purchase price of property')]/following::div[1] //input")		//Price Of property fld
private WebElement propertyPriceFld;

@FindBy(xpath="//span[contains(text(),'EMI Paid currently')]")		//EMI Lbl
private WebElement EMILbl;

@FindBy(xpath="//span[contains(text(),'EMI Paid currently')]/following::div[1] //input")		//EMI fld
private WebElement EMIFld;

@FindBy(xpath="//span[contains(text(),'Outstanding Tenure')]")		//tenure Lbl
private WebElement TenureLbl;

@FindBy(xpath="//span[contains(text(),'Outstanding Tenure')]/following::div[1] //input")	//Tenure fld
private WebElement TenureFld;

@FindBy(xpath="//div[@name=\"Property_type_Container\"] //select")		//type of property LOVs
private WebElement typeOfPropertyLOVS;

@FindBy(xpath="//button[contains(text(),'Proceed')]")		//Prcoeed Button
private WebElement proceedBtn;

public void BalanceTransferScreen(String sheetName)throws Exception
{
	log.info(CommonMethods.getElementText(btHeader));
	
	//Home Loan Provider
		CommonMethods.selectByText(homeLoanProviderLOVS, sheetName, "Home Loan Provider", 1);
	
	//Loan Amount
		CommonMethods.input(loanAmtFld, sheetName, "Loan Amount", 1);
		log.info(CommonMethods.getElementText(loanAmtLbl)+" = "+CommonMethods.getElementValue(loanAmtFld));
		
	//Price of Property
		CommonMethods.input(propertyPriceFld, sheetName, "Price Of Property", 1);
		log.info(CommonMethods.getElementText(priceOfPropertyLbl)+" = "+CommonMethods.getElementValue(propertyPriceFld));
		
	//EMI
		CommonMethods.input(EMIFld, sheetName, "Current EMI", 1);
		log.info(CommonMethods.getElementText(EMILbl)+" = "+CommonMethods.getElementValue(EMIFld));
		
	//Tenure in year	
		CommonMethods.input(TenureFld, sheetName, "Loan Tenure", 1);
		log.info(CommonMethods.getElementText(TenureLbl)+" = "+CommonMethods.getElementValue(TenureFld));
		
	//Type Of Property
		CommonMethods.selectByText(typeOfPropertyLOVS, sheetName, "Type Of Property", 1);
		
		ScreenShot.takeSnapShot("BalanceTransferSreen", "Pass");
		
		CommonMethods.Click(proceedBtn);
		
}
/**************************Balance Transfer/Top Up Screen ****************************/

/**************************Balance Transfer/Top Up Plan Screen ****************************/

@FindBy(xpath="//div[@name=\"MAINContainer\"]/child::div[1] //span")		//BT Plan header
private WebElement BTPlanHdr;

@FindBy(xpath="//div[@name=\"MAINContainer\"]/child::div[3]/div[1]")		//Balance trnsfer Plan
private WebElement BTPlanOptions;

@FindBy(xpath="//div[@name=\"radio1Container\"] //label")			//Radio btn 1 Bt+Top Up
private WebElement BTTopUpRadioBtn;

@FindBy(xpath="//div[@name=\"radio2Container\"] //label")			//just BT radio btn
private WebElement BTRadioBtn;

@FindBy(xpath="//span[contains(text(),'Required Top-Up Amount')]")		//required top up amt lbl
private WebElement topUpAmtLbl;

@FindBy(xpath="//div[@name='Container3'] //input")			//topup amt Fld
private WebElement topUpAMtFld;


public void BalanceTransferPlan(String sheetname)throws Exception
{
	log.info(CommonMethods.getElementText(BTPlanHdr));
	log.info("Balance Transfer Plan : "+CommonMethods.getElementText(BTPlanOptions));
	
	String BTPlan = ExcelOperation.getCellData(sheetname, "Balance Transfer Plan", 1);
	if(BTPlan.equalsIgnoreCase("Just Balance Transfer"))
	{
		CommonMethods.mouseClick(BTRadioBtn);
	}
	else if(BTPlan.equalsIgnoreCase("Balance Transfer Plus TopUp"))
	{
		CommonMethods.mouseClick(BTTopUpRadioBtn);
		Thread.sleep(1000);
		CommonMethods.input(topUpAMtFld, sheetname, "Required Topup Amount", 1);
		log.info(CommonMethods.getElementText(topUpAmtLbl)+" = "+CommonMethods.getElementValue(topUpAMtFld));
		
	}
	ScreenShot.takeSnapShot("BalanceTransferPlanScrren", "Pass");
	CommonMethods.Click(proceedBtn);
	
}

/**************************Balance Transfer/Top Up Plan Screen ****************************/




	
}
