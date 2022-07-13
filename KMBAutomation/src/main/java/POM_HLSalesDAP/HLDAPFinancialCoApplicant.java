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

public class HLDAPFinancialCoApplicant extends SetUp
{
	public Logger log = LoggerFactory.getLogger(HLDAPFinancialCoApplicant.class);
	HLDAPIntialJourney h1= new HLDAPIntialJourney(driver);
	HLDAPModulesJourney h2 = new HLDAPModulesJourney(driver);
	HLDAPEndJourney h3 = new HLDAPEndJourney(driver);
	
	
	public HLDAPFinancialCoApplicant(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void addFinancialCoApp(String sheetName) throws Exception
	{
		h3.EligibilityScreen(sheetName,"Add Financial CO-app");
		String coApplicantType = ExcelOperation.getCellData(sheetName, "CoAppType", 1);
		
		h1.applicantTypeSelection(sheetName,coApplicantType);
		coAppETB_NTBCheck(sheetName);
		h1.OTPVerification(sheetName, "CoApplicant");
		
		String residentTypeExcel = ExcelOperation.getCellData(sheetName, "CoAppResidentType", 1);
		h1.ResidentStatusSelection(sheetName);
		if(residentTypeExcel.equalsIgnoreCase("NRI"))
		{
			h1.NRIRefScreen1(sheetName);
			h1.NRIRefScreen2(sheetName);
		}
		
		h2.Digital_InprincipleSanction(sheetName, "CoApplicant");
		coAppPANScreen(sheetName);
		coAppIncomeDetails(sheetName,coApplicantType);
		//As for NRI CoApplicant Karza and Perfios should not be display same for Self Emp Individual
		
		if(coApplicantType.equalsIgnoreCase("Salaried") && residentTypeExcel.equalsIgnoreCase("Indian"))
		{
			h2.applicantWorkDetails(sheetName);
			h2.karzaWaitingScreen(sheetName);
			h2.KarzaFailureScreen(sheetName);
			h2.bankStmtUpload(sheetName);
			h2.perfiosFailure(sheetName);
		}
		//Revised Eligibility Screen
		if(coApplicantType.equalsIgnoreCase("Self employed"))
		{
			h2.ITRScreen();
		}
		
	}

	/**************************ETB/NTB screen****************************************/
	
	@FindBy(xpath="//span[contains(text(),'Check if')]")					//MOB DOB Screen header
	private WebElement mob_dob_Header;
	
	@FindBy(xpath="//input[@id='txtmissing_287']")		//Mobile No fld
	private WebElement mobileNo;
	
	@FindBy(xpath="//div[@class='radioInlineElementKotak'][1]")	//DOB radio button
	private WebElement DOB_DOIBtn;
	
	@FindBy(xpath="//button[text()='Check']")          		//Check button
	private WebElement CheckBtn;
	
	
	//if enter same no as Main applicant
	@FindBy(xpath="//span[@id='c_15912355246']")			//no used by applicant pop up msg
	private WebElement popUpMsg;
	
	@FindBy(xpath="//button[@id='c_159121665456379']")		//Ok button on pop up
	private WebElement okBtn;
	
	
	
	@FindBy(xpath="//input[@id='txtmissing_300']")			//Name of co-app
	private WebElement coAppName;
	
	@FindBy(xpath="//input[@id='txtmissing_306']")			//coAppEmail
	private WebElement coAppEmail;
	
	@FindBy(xpath="//select[@id='ddlmissing_313']")			//relationship with app LOVS
	private WebElement relationshipWithAppLovs;
	
	@FindBy(xpath="//select[@id='ddlmissing_319']")			//Gender LOVS
	private WebElement coAppGender;
	
	@FindBy(xpath="//button[text()='Proceed']")				//Proceed Button
	private WebElement ProceedBtn;
	
	public void coAppETB_NTBCheck(String sheetName) throws Exception
	{
		log.info(CommonMethods.getElementText(mob_dob_Header));
		
		CommonMethods.input(mobileNo, sheetName, "FinCoAppMobile", 1);
		CommonMethods.Click(DOB_DOIBtn);
		h1.selectDate(sheetName);
		
		Thread.sleep(1000);
		CommonMethods.Click(CheckBtn);
		Thread.sleep(1000);
		
		//If same number as Main applicant is used for CoApp then display popUp
		try {
			CommonMethods.getElementText(popUpMsg);
			ScreenShot.takeSnapShot("MobileNumUsedPopup", "Pass");
			CommonMethods.Click(okBtn);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		CommonMethods.input(coAppName, sheetName, "FinCoAppName", 1);
		CommonMethods.input(coAppEmail, sheetName, "FinCoAppEmail", 1);
		CommonMethods.selectByText(relationshipWithAppLovs, sheetName, "FinCoAppRealtion", 1);
		CommonMethods.selectByText(coAppGender, sheetName, "FinCoAppGender", 1);
		
		
		CommonMethods.highLight(ProceedBtn);
		CommonMethods.Click(ProceedBtn);
		
	}
	/**************************ETB/NTB screen****************************************/

	/**************************PAN CIBIL Screen****************************************/
	@FindBy(xpath="//span[contains(text(),'Just')]")						//PAN CIBIL Header 
	private WebElement PANScreenHeader;
	
	@FindBy(xpath="//div[contains(text(),'Co-Applicant')]/following::div[1] //input")	//PAN fld
	private WebElement coAppPANFld;
	
	@FindBy(xpath="//div[contains(text(),'Address line 1')]")				//Address1 lbl
	private WebElement Add1Lbl;
	
	@FindBy(xpath="//input[@placeholder='Enter Address 1']")					//Address1 Fld
	private WebElement Add1Fld;
	
	@FindBy(xpath="//div[contains(text(),'Address line 2')]")				//Address2 lbl
	private WebElement Add2Lbl;
	
	@FindBy(xpath="//input[@placeholder='Enter Address 2']")					//Address2 Fld
	private WebElement Add2Fld;
	
	@FindBy(xpath="//button[text()='Proceed']")		//PANProceedbtn
	private WebElement PANProceedBtn;
	
	@FindBy(xpath="//div[contains(text(),'City')]")				//City lbl
	private WebElement CityLbl;
	
	@FindBy(xpath="//div[@name='ContainerCity'] //input")		//City fld
	private WebElement Cityfld;
	
	@FindBy(xpath="//div[contains(text(),'Pincode')]")		//Pincode Lbl
	private WebElement PinodeLbl;
	
	@FindBy(xpath="//div[@name='pinContainer'] //input")		//Pincode Fld
	private WebElement PinodeFld;
	
	//PAN Mismatch
	@FindBy(xpath="//div[@name=\"InvalidPANContainer\"]")		//Pop up message 1st line
	private WebElement invalidPanMsg;
	
	@FindBy(xpath="//div[@name=\"NoMatchPANContainer\"]")		//
	private WebElement nameMismatchMsg;
	
	@FindBy(xpath="//div[@name=\"SubmitContainer\"]/button[contains(text(),'Skip and Proceed')]")		//Skip n Proceed btn
	private WebElement skipProceedBtn;
	
	
	public void coAppPANScreen(String sheetName) throws Exception
	{
		log.info(CommonMethods.getElementText(PANScreenHeader));
		
		//PAN fld
		try {
			CommonMethods.input(coAppPANFld, sheetName, "CoAppPAN", 1);
		} catch (Exception e) 
		{			}
		
		//Address Line1
			CommonMethods.input(Add1Fld, sheetName, "Address line 1", 1);
			log.info(CommonMethods.getElementText(Add1Lbl)+" = "+CommonMethods.getElementValue(Add1Fld));

		//Address 2
			CommonMethods.input(Add2Fld, sheetName, "Address line 2", 1);
			log.info(CommonMethods.getElementText(Add2Lbl)+" = "+CommonMethods.getElementValue(Add2Fld));
			
		//City
			log.info(CommonMethods.getElementText(CityLbl)+" = "+CommonMethods.getElementValue(Cityfld));
	
		//Pincode
			log.info(CommonMethods.getElementText(PinodeLbl)+" = "+CommonMethods.getElementValue(PinodeFld));
		
			ScreenShot.takeSnapShot("CoAppPANCIBILScreen", "Pass");
			Thread.sleep(1000);
			CommonMethods.scrollDown(400);
			CommonMethods.highLight(PANProceedBtn);
			CommonMethods.Click(PANProceedBtn);
			
		//to handle mismatch popup
			try 
			{
				log.info(CommonMethods.getElementText(invalidPanMsg));
				log.info(CommonMethods.getElementText(nameMismatchMsg));
				
				ScreenShot.takeSnapShot("PANNameMismatch", "Pass");
				CommonMethods.Click(skipProceedBtn);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		
	}
	
/**************************PAN CIBIL Screen****************************************/
/**************************Income Screen Screen****************************************/

@FindBy(xpath="//div[@name='Coapp_header'] ")			//Header of screen
private WebElement incomeScreenHdr;

@FindBy(xpath="//div[@name='Coapp_netsallery'] //input ")		//net salary fld
private WebElement netSalaryFld;

@FindBy(xpath="//div[@name=\"CoappProfitContainer\"] //input")		//Profit after tax for individual
private WebElement profitFld;

@FindBy(xpath="//div[@name=\"Coapp_addincome\"] //button")		//add source + button
private WebElement addSrcBtn;

@FindBy(xpath="//div[@name=\"CappContaineranu\"] //input")		//Add income fld
private WebElement addIncomeFld;


@FindBy(xpath="//div[@name=\"coapp_addEMI\"] //button")			//Add EMI button
private WebElement addEMIBtn;

@FindBy(xpath="//div[@name=\"Coappcurrentcol\"] //input")		//Add current EMI fld;
private WebElement addEMIFld;



public void coAppIncomeDetails(String sheetName,String coApplicantType) throws Exception
{
	CommonMethods.ExWait(incomeScreenHdr);
	log.info(CommonMethods.getElementText(incomeScreenHdr));
	
	if(coApplicantType.equalsIgnoreCase("Salaried"))
	{
		//Net Salary
			CommonMethods.input(netSalaryFld, sheetName, "CoAppNetSalary", 1);
	}
	else if(coApplicantType.equalsIgnoreCase("Self employed"))
	{
		//Profit after tax
			CommonMethods.input(profitFld, sheetName, "CoAppProfitAfterTax", 1);
	}
	
	//Add another source
		CommonMethods.Click(addSrcBtn);
		CommonMethods.input(addIncomeFld, sheetName, "Additional Income", 1);
	
		
	//Add EMI
		CommonMethods.Click(addEMIBtn);
		CommonMethods.input(addEMIFld, sheetName, "Current EMI", 1);
	
		ScreenShot.takeSnapShot("CoAppIncomeDetails", "Pass");
		CommonMethods.highLight(ProceedBtn);
		CommonMethods.Click(ProceedBtn);
	
}
	
/**************************Income Screen Screen****************************************/


	

}
