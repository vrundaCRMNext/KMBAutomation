package POM_HLSalesDAP;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
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

public class HLDAPEndJourney extends SetUp
{
	public Logger log = LoggerFactory.getLogger(HLDAPEndJourney.class);
	

	public HLDAPEndJourney(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void runEndJourney(String sheetName) throws Exception
	{
		
		EligibilityScreen(sheetName,"Continue to Sanction Letter");
		ProcessingFeeScreen(sheetName);
		ReferenceDetailsScreen(sheetName);
		
		String applicantType = ExcelOperation.getCellData(sheetName,"Applicant Type", 1);
		String SelfEmpType = ExcelOperation.getCellData(sheetName, "Individual/Non-Individual", 1);

		if(applicantType.equalsIgnoreCase("Salaried")|| SelfEmpType.equalsIgnoreCase("Individual") )
		{
			workExpDetailScreen(sheetName);	
		}
		
		String propertyIdentifed= ExcelOperation.getCellData(sheetName, "Property Identified", 1);
		if(propertyIdentifed.equalsIgnoreCase("Yes"))
		{
			AppPropertyDetailsScreen(sheetName);
		}
		
		regulatoryDetailsScreen(sheetName);
		CreditLinkSubsidyScreen(sheetName);
		FinIndividualAppQue(sheetName);
		
		nonFinancialCoAppScreen1(sheetName);
		CoAppOTPVerification(sheetName);
		nonFinancialCoAppScreen2(sheetName);
		
	}
	
/*****************************ELigibility Screen*************************************/	

@FindBy(xpath="//div[@name='combinecontiueContainer'] //button")				//Continue to Digital/Principle Sanction letter button
private WebElement continueSanctionBtn;

@FindBy(xpath="//div[@name='NextbuttonContainer']/button")			//Add Financial CO-appliant
private WebElement addFinancialCo_appBtn;

@FindBy(xpath="//div[@name=\"RemoveButtonContainer\"] //button")	//Remove Fin CoApp
private WebElement removeFinCoAPPBtn;

@FindBy(xpath="//div[@name='ROIDevButtonContainer']/button")		//ROI Deviation Button
private WebElement ROIDevBtn;

@FindBy(xpath="//div[@name='LoanDevContainer']/button")				//LOAN Deviation Button
private WebElement LoanAmtDeviationBtn;

@FindBy(xpath="//div[@name=\"ProcessingContainer\"]/div[2]/span")	//Processing fee
private WebElement processingFee;
	


public void EligibilityScreen(String sheetName, String eligibilityOption)throws Exception
{
		CommonMethods.waitForURL("ligib");
		Thread.sleep(2000);
		ScreenShot.Ashot("EligibilityScreen1", "Pass");
		CommonMethods.scrollAtBottom();
		Thread.sleep(1000);
		try {
			CommonMethods.ExWait(processingFee);
		} catch (Exception e) {}
		//ScreenShot.takeSnapShot("EligibilityScreen2","Pass");
		Thread.sleep(2000);
		
		
			
		//String eligibilityOption = ExcelOperation.getCellData(sheetName, "Eligibility Screen Option", 1);
		log.info("Eligibility Screen Option to Proceed :" +eligibilityOption);
		
		if(eligibilityOption.equalsIgnoreCase("Continue to Sanction Letter"))
		{
			CommonMethods.highLight(continueSanctionBtn);
			CommonMethods.Click(continueSanctionBtn);
		}
		else if(eligibilityOption.equalsIgnoreCase("Add Financial CO-app"))
		{
			CommonMethods.highLight(addFinancialCo_appBtn);
			CommonMethods.Click(addFinancialCo_appBtn);
		}
		else if(eligibilityOption.equalsIgnoreCase("Intiate Loan Deviation"))
		{
			CommonMethods.highLight(LoanAmtDeviationBtn);
			CommonMethods.Click(LoanAmtDeviationBtn);
		}
		else if(eligibilityOption.equalsIgnoreCase("Intiate ROI Deviation"))
		{
			CommonMethods.highLight(ROIDevBtn);
			CommonMethods.Click(ROIDevBtn);
		}
}
	
/*****************************ELigibility Screen*************************************/	

/*****************************Processing Fee Screen*************************************/	

@FindBy(xpath="//div[@name='PFLabelContainer']")			//PF Scree Header
private WebElement PFhdrMsg;

@FindBy(xpath="//div[@name='PFContainer2']")				//PF amt 
private WebElement PFAmt;

@FindBy(xpath="//div[@name='PFCollectionFailContainer']/button")	//Skip and Continue 
private WebElement PFSkipContBtn;

@FindBy(xpath="//div[@name=\"PFprogress\"] //span")			//PF Progress msg
private WebElement pfProgressMsg;

@FindBy(xpath="//div[@name='Check_Status']/button")					//Check Status button
private WebElement checkStatusBtn;


public void ProcessingFeeScreen(String sheetName)throws Exception
{
		CommonMethods.waitForURL("processfee");
		CommonMethods.ExWait(PFhdrMsg);
		ScreenShot.takeSnapShot("ProcessingFee", "Pass");
		log.info(CommonMethods.getElementText(PFhdrMsg));
			try {
			log.info(CommonMethods.getElementText(PFAmt));
			}catch(Exception e) {}
		CommonMethods.highLight(PFSkipContBtn);
		Thread.sleep(1000);
		
		try {
			CommonMethods.Click(PFSkipContBtn);
		}catch(Exception e) {log.info("Not able to click on " +e.getMessage());}
		
		try {
			//CommonMethods.waitForURL("processfeefail");
			log.info(CommonMethods.getElementText(pfProgressMsg));
			ScreenShot.takeSnapShot("ProcessingFeeWaiting", "Pass");
			CommonMethods.Click(checkStatusBtn);
		}catch(Exception e) {}
	
}	
/*****************************Processing Fee Screen*************************************/	
	
/*****************************Reference Details Screen *************************************/	
@FindBy(xpath="//span[text()='Fill few additional details to generate Application Form digitally *']")					//Screen Header
private WebElement screenHdr1;

@FindBy(xpath="//span[text()='References']")					//Screen Header 2
private WebElement screenHdr2;

@FindBy(xpath="//input[@placeholder='Enter name of 1st reference']")		//1st ref name fld
private WebElement firstRefName;

@FindBy(xpath="//input[@placeholder='Enter mobile no. of 1st reference']")	//1st ref Mobile No fld
private WebElement firstMobileNo;

@FindBy(xpath="//input[@placeholder='Enter name of 2nd reference']")		//2nd refrence name fld
private WebElement secondRefName;

@FindBy(xpath="//input[@placeholder='Enter mobile no. of 2nd reference']")	//2nd Mobile No fld
private WebElement secondMobileNo;

@FindBy(xpath="//button[text()='Proceed']")									//Proceed Button
private WebElement proceedBtn;

@FindBy(xpath="//button[text()='Skip & Upload Physical App form']")			//Skip & Upload Physical App form btn
private WebElement skipUploadPhysicalFormBtn;


public void ReferenceDetailsScreen(String sheetName)throws Exception
{
	
		CommonMethods.waitForURL("refdetails");
		log.info(CommonMethods.getElementText(screenHdr1));
		log.info(CommonMethods.getElementText(screenHdr2));

		
		String skipUploadPhysicalForm = ExcelOperation.getCellData(sheetName, "Skip and Upload Physical App Form", 1);
		if(skipUploadPhysicalForm.equalsIgnoreCase("No"))
		{
			//Reference Name 1
			CommonMethods.input(firstRefName, sheetName, "Reference Name1", 1);
			log.info("First reference name = "+CommonMethods.getElementValue(firstRefName));
			
			//Reference Mobile No 1
			CommonMethods.input(firstMobileNo, sheetName, "Reference Mobile1", 1);
			log.info("First refrence Mobile No  = "+CommonMethods.getElementValue(firstMobileNo));
			
			//Reference Name 2
			CommonMethods.input(secondRefName, sheetName, "Reference Name2", 1);
			log.info("Second Reference Name = "+CommonMethods.getElementValue(secondRefName));
			
			//Reference Mobile No2
			CommonMethods.input(secondMobileNo, sheetName, "Reference Mobile2", 1);
			log.info("Second Mobile No = "+CommonMethods.getElementValue(secondMobileNo));
			
			ScreenShot.takeSnapShot("ReferenceDetailsScreen", "Pass");
			
			//Proceed Button
			CommonMethods.highLight(proceedBtn);
			CommonMethods.Click(proceedBtn);
		}
		else if(skipUploadPhysicalForm.equalsIgnoreCase("Yes"))
		{
			CommonMethods.mouseHover(skipUploadPhysicalFormBtn);
			ScreenShot.takeSnapShot("ReferenceDetailsScreen", "Pass");
			CommonMethods.highLight(skipUploadPhysicalFormBtn);
			CommonMethods.Click(skipUploadPhysicalFormBtn);
		}
		
}
/*****************************Reference Details Screen *************************************/	

/*****************************Work Experience Details Screen *************************************/	
@FindBy(xpath="//span[text()='Current Work Details']")				//Header of screen
private WebElement workExpHdr;

@FindBy(xpath="//span[text()='Current Work Experience']")				//work exp label
private WebElement workExpLbl;	

@FindBy(xpath="//input[@placeholder='Enter current work experience (in Months)']")			//Work Exp fld
private WebElement workExpFld;

@FindBy(xpath="//span[text()='Designation']")				// Desiganation
private WebElement designationLbl;

@FindBy(xpath="//input[@placeholder='Enter designation']")			//Designation fld
private WebElement designationFld;


public void workExpDetailScreen(String sheetName)throws Exception
{
	
		//CommonMethods.waitForURL("currworkdetails");
		log.info(CommonMethods.getElementText(workExpHdr));
		
		CommonMethods.input(workExpFld, sheetName, "Work Experience", 1);
		log.info(CommonMethods.getElementText(workExpLbl)+" = "+CommonMethods.getElementValue(workExpFld));
		
		CommonMethods.input(designationFld, sheetName, "Designation", 1);
		log.info(CommonMethods.getElementText(designationLbl)+" = "+CommonMethods.getElementValue(designationFld));
		
		Thread.sleep(1000);
		ScreenShot.takeSnapShot("WorkExperienceDetails", "Pass");
		CommonMethods.highLight(proceedBtn);
		CommonMethods.Click(proceedBtn);

}
/*****************************Work Experience Details Screen *************************************/	

/*****************************Property Details Screen *************************************/	
@FindBy(xpath="//span[contains(text(),'Applicant Property Details')]")
private WebElement propScreenHdr;

@FindBy(xpath="//span[contains(text(),'Property Address')]")			//property Add lbl
private WebElement propAddLbl;

@FindBy(xpath="//input[@placeholder=\"Enter Property Address\"]")		//Property Add Fld
private WebElement propAddFld;

@FindBy(xpath="//span[contains(text(),'Property type')]/following::select[1]")			//Property Type LOVS
private WebElement propTypeLOVs;

@FindBy(xpath="//span[contains(text(),'Property purchased in Name of')]")		//Property purchase name lbl
private WebElement propPurchaseNameLbl;

@FindBy(xpath="//input[@placeholder=\"Enter Property purchased in Name of\"]")		//Property purchase name fld
private WebElement propPurchaseNameFld;

@FindBy(xpath="//span[contains(text(),'Stage of construction')]/following::select")  //stage od constrution LOVs
private WebElement stageOfConstLOVS;

@FindBy(xpath="//span[contains(text(),'Usage of property')]")			//Usage of property Lbl
private WebElement usageOfPropLbl;

@FindBy(xpath="//input[@placeholder=\"Enter Usage of property\"]")		//Usage Of Prop Fld
private WebElement usageOfPropFld;


public void AppPropertyDetailsScreen(String sheetName) throws Exception
{
	
	log.info(CommonMethods.getElementText(propScreenHdr));
	
	CommonMethods.input(propAddFld, sheetName, "Property Address", 1);
	log.info(CommonMethods.getElementText(propAddLbl) +" = "+CommonMethods.getElementValue(propAddFld));
	
	CommonMethods.selectByText(propTypeLOVs, sheetName, "Type Of Property", 1);
	
	CommonMethods.input(propPurchaseNameFld, sheetName, "Property Purchase Name", 1);
	log.info(CommonMethods.getElementText(propPurchaseNameLbl) +" = "+CommonMethods.getElementValue(propPurchaseNameFld));

	CommonMethods.selectByText(stageOfConstLOVS, sheetName, "Stage Of Construction", 1);
	
	CommonMethods.input(usageOfPropFld, sheetName, "Usage of property", 1);
	log.info(CommonMethods.getElementText(usageOfPropLbl) +" = "+CommonMethods.getElementValue(usageOfPropFld));

	CommonMethods.Click(proceedBtn);
}

/*****************************Property Details Screen *************************************/	

/*****************************Regulatory  Details Screen *************************************/	

@FindBy(xpath="//span[text()='Regulatory Fields']")		//Screen Header
private WebElement regulatoryHdr;

@FindBy(xpath="//span[text()='Mothers Maiden Name']")		//mother maiden name lbl
private WebElement motherNmLbl;

@FindBy(xpath="//span[text()='Mothers Maiden Name']/following::div[1] //input")	//mother maiden name fld
private WebElement motherNmFld;

@FindBy(xpath="//span[text()='Fathers Name']")		//Father name lbl
private WebElement FatherNmLbl;

@FindBy(xpath="//span[text()='Fathers Name']/following::div[1] //input")		//Father name Fld
private WebElement FatherNmFld;

@FindBy(xpath="//span[text()='Marital Status']")		//Marital Status Lbl
private WebElement maritalStatusLbl;

@FindBy(xpath="//span[text()='Marital Status']/following::div[1] //select")		//Martial Status LOVS
private WebElement maritalStatusLOVS;

@FindBy(xpath="//span[text()='Occupation Type']]")			//Occupation Type lbl
private WebElement occupationTypeLBL;

@FindBy(xpath="//span[text()='Occupation Type']/following::div[1] //select")		//Occupation type LOVS
private WebElement occupationTypeLOVS;
	

public void regulatoryDetailsScreen(String sheetName) throws Exception
{
	
		//CommonMethods.waitForURL("regulatefield");
		log.info(CommonMethods.getElementText(regulatoryHdr));
		Thread.sleep(1000);
		
		try {
			CommonMethods.input(motherNmFld, sheetName, "Maiden Name", 1);
			log.info(CommonMethods.getElementText(motherNmLbl)+" = "+CommonMethods.getElementValue(motherNmFld));
		}catch(Exception e) {}
		
		try {
			CommonMethods.input(FatherNmFld, sheetName, "Fathers Name", 1);
			log.info(CommonMethods.getElementText(FatherNmLbl)+" = "+CommonMethods.getElementValue(FatherNmFld));
		}catch(Exception e) {}
		
		CommonMethods.selectByText(maritalStatusLOVS, sheetName, "Marital Status", 1);
		
		CommonMethods.selectByText(occupationTypeLOVS, sheetName, "Occupation Type", 1);
		
		ScreenShot.takeSnapShot("RegulatoryDetailsScreen", "Pass");
		Thread.sleep(1000);
		CommonMethods.highLight(proceedBtn);
		CommonMethods.Click(proceedBtn);
		
	
}


/*****************************Regulatory  Details Screen *************************************/	

/*****************************Credit Link Subsidy Screen *************************************/	
@FindBy(xpath="//span[text()='Credit Linked subsidy scheme (optional)']")			//Screen Header
private WebElement creditHdr;

@FindBy(xpath="//label[@class='application-form-radioLabel']")	//Credit scheme list	
private List<WebElement> creditSchemeList;

@FindBy(xpath="//button[text()='Skip & Proceed']")
private WebElement skipProcButton;

public void CreditLinkSubsidyScreen(String sheetName) throws Exception
{
	
		//CommonMethods.waitForURL("creditlinksubsidy");
		log.info(CommonMethods.getElementText(creditHdr));
		
		String creditSchemeExcel = ExcelOperation.getCellData(sheetName, "Credit scheme", 1);
		
		for(WebElement ele: creditSchemeList)
		{
			String eleVal = ele.getText();
			Thread.sleep(1000);
			if(eleVal.equalsIgnoreCase(creditSchemeExcel))
			{
				ele.click();
				log.info(eleVal +" get selected");
				break;
			}
		}
		
		ScreenShot.takeSnapShot("CreditLinkSubsidyScreen", "Pass");
		CommonMethods.Click(proceedBtn);
		CommonMethods.Click(proceedBtn);
		
	try {
		ScreenShot.takeSnapShot("CreditLinkSubsidyScreen", "Pass");
		CommonMethods.Click(skipProcButton);
		CommonMethods.Click(skipProcButton);
	} catch (Exception e) {
		// TODO: handle exception
	}
}

/*****************************Credit Link Subsidy Screen *************************************/	
/*****************************Financial Individual Applicant Questions Screen *************************************/	
@FindBy(xpath="//span[text()='Mandatory fields']")				//Header of screen
private WebElement FinIndiAppHdr;

@FindBy(xpath="//div[@name='Container1'] //span")				//1st question
private WebElement Firstquestion;

@FindBy(xpath="//div[@name='Container2'] //span")				//2nd Question
private WebElement SecondQuestion;

@FindBy(xpath="//div[@name='Container1']/following::div[1] //label")		//1st question radio btn list
private List<WebElement> FirstQAnsList;

@FindBy(xpath="//div[@name='Container2']/following::div[1] //label")		//2nd question radio btn list
private List<WebElement> SecondQAnsList;



public void FinIndividualAppQue(String sheetName) throws Exception
{
	
		//CommonMethods.waitForURL("finindiapp");
		log.info(CommonMethods.getElementText(FinIndiAppHdr));
			
		log.info("1st question = "+CommonMethods.getElementText(Firstquestion));
		log.info("2nd Question = "+CommonMethods.getElementText(SecondQuestion));
		
		for(WebElement ele: FirstQAnsList)
		{
			String eleVal = ele.getText();
			if(eleVal.equalsIgnoreCase("Yes"))
			{
				ele.click();
				log.info(eleVal + "get selected.");
				break;
			}
		}
		
		Thread.sleep(1000);
		for(WebElement ele: SecondQAnsList)
		{
			String eleVal = ele.getText();
			if(eleVal.equalsIgnoreCase("No"))
			{
				ele.click();
				log.info(eleVal +" get selected.");
				break;
			}
		}
		
		ScreenShot.takeSnapShot("FinancialIndividualAppQuesScreen", "Pass");
		CommonMethods.highLight(proceedBtn);
		CommonMethods.Click(proceedBtn);
	
}

/*****************************Individual App Questions Screen *************************************/

/*****************************Non Financial CO-applicant Screen1 *************************************/	
@FindBy(xpath="//span[contains(text(),'Name of co-applicant')]")			//name of Coapp Lbl
private WebElement CoAppNameLbl;

@FindBy(xpath="//input[@placeholder='Enter Name of co-applicant']")		//CoApp Name Fld
private WebElement CoAppNameFld;

@FindBy(xpath="//div[@name='gender_Container'] //span[1]")					//Gender Lbl
private WebElement CoAppGender;

@FindBy(xpath="//div[@name='gender_Container'] //select")				//Gender LOVs
private WebElement GenderLOVs;

@FindBy(xpath="//span[contains(text(),'Mobile Number')]")					//Mobile Lable
private WebElement MobileNoLbl;

@FindBy(xpath="//input[@placeholder='Enter Mobile No']")				//Mobile No
private WebElement CoAppMobileNoFld;

@FindBy(xpath="//button[starts-with(@class,'icon icon-calendarpick calendarButtonFontSize')]")
private WebElement calanderBtn;
//div[@id='calenderOutterDiv']/child::div[1] //button

public void nonFinancialCoAppScreen1(String sheetName) throws Exception
{
	
		//CommonMethods.waitForURL("nonfincoapp");
		HLDAPIntialJourney h1= new HLDAPIntialJourney(driver);
		CommonMethods.input(CoAppNameFld, sheetName, "CoApp Name", 1);
		log.info(CommonMethods.getElementText(CoAppNameLbl) +" = "+CommonMethods.getElementValue(CoAppNameFld));
		
		CommonMethods.Click(calanderBtn);
		h1.selectDate(sheetName);
		
		CommonMethods.selectByText(GenderLOVs, sheetName, "Gender", 1);
		
		CommonMethods.input(CoAppMobileNoFld, sheetName, "CoApp Mobile", 1);
		log.info(CommonMethods.getElementText(MobileNoLbl)+" = "+CommonMethods.getElementValue(CoAppMobileNoFld));
		
		ScreenShot.takeSnapShot("NonFinCoAppScreen1", "Pass");
		CommonMethods.highLight(proceedBtn);
		CommonMethods.Click(proceedBtn);
		
	
}
/*****************************Non Financial CO-applicant Screen1 *************************************/	

/*****************************Non Financial CO-applicant OTP Verification  *************************************/	

@FindBy(xpath="//a[@title='Toggle to Detail View']")				//Toggle to Lead Detail View 
private WebElement detailView;

@FindBy(xpath="//span[contains(text(),'Co-Applicant')]")					//On Lead Details Page CO-Applicant Tab
private WebElement coApplicantTab;

@FindBy(xpath="//a[@data-autoid='ApplicationName_0']")						//Co applicant
private WebElement CoApplicant;

@FindBy(xpath="//div[@data-autoid='cust_4330']")							//COApplicant SMS body
private WebElement CoAppSMSBody;

@FindBy(xpath="//span[@data-autoid='cust_4330_ctrl']")						//OTP SMS
private WebElement OtpSMS;




public void retriveCoAppOTPSMS(WebElement SMS,String sheetName) throws Exception
{
	
		Thread.sleep(1000);
		HLDAPIntialJourney h1= new HLDAPIntialJourney(driver);
		h1.LeadSearch(sheetName);    //Lead Search through Mobile no from CRM 
		
		//To get OTP verification URL from lead details page 
		Thread.sleep(1000);
		try {
			CommonMethods.Click(detailView);
		}catch(Exception e) {}
		Thread.sleep(1000);
		CommonMethods.highLight(coApplicantTab);
		CommonMethods.Click(coApplicantTab);
		Thread.sleep(1000);
		try {
		CommonMethods.Click(CoApplicant);
		Thread.sleep(2000);
		}catch(Exception e) {}
		ScreenShot.Ashot("CoApplicantDetailsPage", "Pass");
		CommonMethods.scrollByVisibilityofElement(SMS);
		
		String SMSbody=CommonMethods.getElementText(SMS);
		log.info("SMS = "+SMSbody);
		String link=SMSbody.split("link")[1].trim().split(" ")[0];
		log.info("OTP/Consent Verification link : "+link);
		Thread.sleep(1000);
		
		//open OTP verification link on new tab
		Thread.sleep(1000);
		driver.navigate().to(link);
		Thread.sleep(2000);
	
}

@FindBy(xpath="//div[@class='notification-message']")	
private WebElement otpNotificationMSG;

@FindBy(xpath="//span[@class='notification-dismiss']")
private WebElement closeNotification;

@FindBy(xpath="//div[@name='Container'] //input[@type='number']")				//OTP txt box
private WebElement OTPTxtbox;

@FindBy(xpath="//label[@class='thankYou-checkText']")				//Checkbox
private WebElement checkBox;

@FindBy(xpath="//div[@name='SecondCibilContainer']")				//TnC 
private WebElement TnCLbl;

@FindBy(xpath="//div[@name='ThirdCibilContainer']")					//1st condition lbl
private WebElement firstConditionLbl;

@FindBy(xpath="//div[@name='FourthCibilContainer']")				//2nd Condition lbl
private WebElement secondCondLbl;

@FindBy(xpath="//button[text()='Submit']")				//OTP Submit button
private WebElement otpSubmit;

@FindBy(xpath="//span[contains(text(),'Please wait while the OTP is being verified')]")					//OTP waiting screen message
private WebElement otpWaitingmsg;

@FindBy(xpath="//button[text()='Re check']")				//Recheck button on waiting screen
private WebElement recheckBtn;

public void CoAppOTPVerification(String sheetName)
{
	try 
	{
		Thread.sleep(2000);
		CommonMethods.waitForURL("OTP");
		CommonMethods.ExWait(otpWaitingmsg);
		assertEquals(CommonMethods.getElementText(otpWaitingmsg) , "Please wait while the OTP is being verified");
		
		ScreenShot.takeSnapShot("OTPWaitingScreen", "Pass");
		log.info("CoApplicant OTP Verification started");
		
		retriveCoAppOTPSMS(OtpSMS,sheetName);

		Thread.sleep(2000);
		log.info("OTP Notification :"+CommonMethods.getElementText(otpNotificationMSG));
			CommonMethods.Click(closeNotification);
			Thread.sleep(2000);
			CommonMethods.Click(OTPTxtbox);
			Thread.sleep(1000);
			OTPTxtbox.sendKeys("123456");
			Thread.sleep(1000);

			CommonMethods.Click(checkBox);
			
			log.info(CommonMethods.getElementText(checkBox));
			log.info(CommonMethods.getElementText(TnCLbl));
			log.info(CommonMethods.getElementText(firstConditionLbl));
			log.info(CommonMethods.getElementText(secondCondLbl));
			
			ScreenShot.takeSnapShot("OTPVerification","Pass");

			CommonMethods.Click(otpSubmit);
			Thread.sleep(2000);
			driver.navigate().back();
			Thread.sleep(1000);
			CommonMethods.switchToWindowByTitle("Customer Digital Journey");
			Thread.sleep(1000);
			CommonMethods.waitForURL("OTP");
				
		
	} catch (Exception e) {
		log.error("Non Financial co-applicant OTP verification exeption occured due to "+e.getMessage() );
	}
	
}//OTP Verification 

/*****************************Non Financial CO-applicant OTP Verification  *************************************/	

/*****************************Non Financial CO-applicant Screen 2 *************************************/	
@FindBy(xpath="//span[contains(text(),'Relationship With Applicant')]")			
private WebElement relationshipLbl;

@FindBy(xpath="//div[@name='HomebtnContainer']/following::div[1] //select")		//Relation ship with applicant LOVs
private WebElement relationshipLOVS;

@FindBy(xpath="//span[text()='PAN']")						//PAN lbl
private WebElement PANLbl;

@FindBy(xpath="//input[@placeholder='Enter PAN No.']")		//PAN fld;
private WebElement PANFld;

@FindBy(xpath="//div[contains(text(),'Pincode')]")			//Pincode Lbl
private WebElement pincodeLbl;

@FindBy(xpath="//input[@class='autocomplet-fld ']")			//Pincode Fld
private WebElement pincodeFld;

@FindBy(xpath="//div[@name='sendToreview']/button")			//Send TO review button
private WebElement sendToReviewBtn;

public void nonFinancialCoAppScreen2(String sheetName)throws Exception
{
	
			CommonMethods.waitForURL("nonfincapp2");
			log.info(CommonMethods.getElementText(relationshipLbl));
			CommonMethods.selectByText(relationshipLOVS, sheetName, "RelationShipWithApplicant", 1);
			
			CommonMethods.input(PANFld, sheetName, "PAN", 1);
			log.info(CommonMethods.getElementText(PANLbl)+" = "+CommonMethods.getElementValue(PANFld));
			
			CommonMethods.input(pincodeFld, sheetName, "Pincode", 1);
			log.info(CommonMethods.getElementText(pincodeLbl)+" = "+CommonMethods.getElementValue(pincodeFld));
			
			ScreenShot.takeSnapShot("NonFinCoappScreen2", "Pass");
			CommonMethods.highLight(sendToReviewBtn);
			CommonMethods.Click(sendToReviewBtn);
	
}

/*****************************Non Financial CO-applicant Screen 2 *************************************/	


}


