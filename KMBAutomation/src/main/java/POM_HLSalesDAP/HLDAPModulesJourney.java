package POM_HLSalesDAP;

import java.util.List;

import javax.script.AbstractScriptEngine;

import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.html.HTMLIsIndexElement;

import CommonUtility.CommonMethods;
import CommonUtility.ExcelOperation;
import CommonUtility.ScreenShot;
import CommonUtility.SetUp;

public class HLDAPModulesJourney extends SetUp
{

	public Logger log = LoggerFactory.getLogger(HLDAPModulesJourney.class);
	HLDAPIntialJourney h1= new HLDAPIntialJourney(driver);

	public HLDAPModulesJourney(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	public void runModuleJoureny(String sheetName) throws Exception
	{
		//ScreenShot.takeSnapShot("ModulesScreen", "Pass");
		Thread.sleep(1000);

		Digital_InprincipleSanction(sheetName);
		PANCibilDeatils(sheetName);

		incomeDetails(sheetName);
		//h1.resumeWithLink(sheetName);

		applicantWorkDetails(sheetName);
		karzaWaitingScreen(sheetName);
		KarzaFailureScreen(sheetName);
		
		propertyDetailsScreen(sheetName);
		bankStmtUpload(sheetName);
		perfiosFailure(sheetName);
		CrossSellScreen(sheetName);	
	}
	
	/*****************************Modules Screen  - Digital/In_principle Sanction********************************/
	@FindBy(xpath="//span[@title='HL Sales App Fields']")				//HL Sales App Fields Tab
	private WebElement HLSalesAppFields;
	
	@FindBy(xpath="//div[@name='Container']/following::button[contains(text(),'Proceed to')]")
	private WebElement sanctionProceed;
	
	@FindBy(xpath="//button[text()='Proceed']")		//Proceed 
	private WebElement consentProceed;
	
	@FindBy(xpath="//span[@data-autoid='cust_4046_ctrl']")			//Consent SMS
	private WebElement consentSMS;
	
	@FindBy(xpath="//div[@name='T&CContainer'] //label")		//TnC checkbox
	private WebElement TnC_checkbox;
	
	@FindBy(xpath="//div[@name='termsContainer']")			//TNC text
	private WebElement TnCContent;
	
	
	@FindBy(xpath="//button[text()='Submit']")		//consent submit button
	private WebElement consentSubmit;
	
	
	public void Digital_InprincipleSanction(String sheetName) throws Exception
	{
		
			try {
			//Modules Screen	
				CommonMethods.waitForURL("etb2");
				CommonMethods.mouseHover(sanctionProceed);
				ScreenShot.takeSnapShot("DigitalInprincipleSaction", "Pass");
				CommonMethods.Click(sanctionProceed);
				Thread.sleep(1000);
			}catch(Exception e) {
				log.error("Modules screen exception due to "+e.getMessage());
				}
			try {
			//Consent Screen
				CommonMethods.waitForURL("hlconsent");
				ScreenShot.takeSnapShot("UserConsentScreen", "Pass");
				CommonMethods.scrollAtBottom();
				CommonMethods.highLight(consentProceed);
				CommonMethods.Click(consentProceed);
				Thread.sleep(2000);
				CommonMethods.waitForURL("Consent_waiting");
				ScreenShot.takeSnapShot("ConsentWaitingScreen", "Pass");
			}catch(Exception e) {log.error("Consent Screen exception due to :"+e.getMessage());}
				Thread.sleep(2000);
			//Consent Approval - get Link CRM lead page
				log.info("Consent Approval started");
				h1.retriveLinkFromSMS(consentSMS,HLSalesAppFields,sheetName);
				Thread.sleep(1000);
				ScreenShot.takeSnapShot("LeadConsentScreen", "Pass");
				
			//CLick on TNC and Submit consent	
				log.info(CommonMethods.getElementText(TnCContent));
				CommonMethods.Click(TnC_checkbox);
				try {
					CommonMethods.highLight(consentSubmit);
					CommonMethods.Click(consentSubmit);
					CommonMethods.waitForURL("thanks");
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			//Close consent window and switch back to DAP journey	
				Thread.sleep(1000);
				//driver.close();
				driver.navigate().back();
				Thread.sleep(2000);
				CommonMethods.switchToWindowByTitle("Customer Digital Journey");
				ScreenShot.takeSnapShot("ConsentApprovalScreen", "Pass");	

		
	}
	/*****************************Modules Screen  - Digital/In_principle Sanction********************************/

	/*********************************PAN/CIBIL Screen****************************************************/
	@FindBy(xpath="//span[contains(text(),'Just')]")						//PAN CIBIL Header 
	private WebElement PANScreenHeader;
	
	@FindBy(xpath="//div[@name='INDIAN_PAN_Container']/div/div[1]")				//PAN lable
	private WebElement PANLbl;
	
	@FindBy(xpath="//div[@name='INDIAN_PAN_Container'] //input")				//Indian PAN Fld
	private WebElement PANFld;
	
	@FindBy(xpath="//div[@name='NRI_PAN_Container'] //input")				//NRI PAN FLd
	private WebElement NRIPANFld;
	
	@FindBy(xpath="//div[@name=\"Entity_PAN_Container\"] //input")      //Entity PAN fld
	private WebElement EntityPANFld;
	
	@FindBy(xpath="//div[contains(text(),'Entity PAN No')]")        //Entity PAN Lbl
	private WebElement EntityPANLbl;
	
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
	
	@FindBy(xpath="//div[@id='id_152826618567254']/span")		//PAN Error msg
	private WebElement PANErrMsg;
	
	@FindBy(xpath="//button[@id='c_159121652579']")				//PAN Error Ok button
	private WebElement PANErrOkBtn;
	
	
	public void PANCibilDeatils(String sheetName) throws Exception
	{
		
			log.info(CommonMethods.getElementText(PANScreenHeader));
			//PAN data
				try {
					//PANFld.click();
					//js.executeScript("document.getElementById('txtmissing_207').value= 'AQPPR4745L' ; " );
					//js.executeScript(" document.getElementById('txtmissing_207').value ='" + PAN + "' ; " );
					String applicantType = ExcelOperation.getCellData(sheetName, "Applicant Type", 1);
					String SelfEmpType = ExcelOperation.getCellData(sheetName, "Individual/Non-Individual", 1);
					
					if(applicantType.equalsIgnoreCase("Salaried") || SelfEmpType.equalsIgnoreCase("Individual"))
					{
						CommonMethods.input(PANFld, sheetName, "PAN", 1);
						String PANval = CommonMethods.getElementValue(PANFld);
						if(PANval.isEmpty())
						{
							CommonMethods.input(PANFld, sheetName, "PAN", 1);
						}
					}
					//else if(applicantType.equalsIgnoreCase("Self employed"))
					else if(SelfEmpType.equalsIgnoreCase("Non-Individual"))
					{
						CommonMethods.input(EntityPANFld, sheetName, "PAN", 1);
						
					}
				} catch (Exception e) {
					log.error("PAN field error "+e.getMessage());
				}
			
			
			//Address Line1
				//CommonMethods.Click(Add1Fld);
				//String Add1 = ExcelOperation.getCellData(sheetName, "Address line 1", 1);
				//js.executeScript("document.getElementById('txtmissing_225').value='A-302, Dahisar Sagar CHS, GharatanPada 2' ;" );
				CommonMethods.input(Add1Fld, sheetName, "Address line 1", 1);
				log.info(CommonMethods.getElementText(Add1Lbl)+" = "+CommonMethods.getElementValue(Add1Fld));

			//Address 2
					CommonMethods.input(Add2Fld, sheetName, "Address line 2", 1);
					log.info(CommonMethods.getElementText(Add2Lbl)+" = "+CommonMethods.getElementValue(Add2Fld));
					
			//City
				log.info(CommonMethods.getElementText(CityLbl)+" = "+CommonMethods.getElementValue(Cityfld));
			
			//Pincode
				log.info(CommonMethods.getElementText(PinodeLbl)+" = "+CommonMethods.getElementValue(PinodeFld));
				
				ScreenShot.takeSnapShot("PANCIBIL Screen", "Pass");
				Thread.sleep(1000);
				CommonMethods.scrollByVisibilityofElement(PANProceedBtn);
				CommonMethods.highLight(PANProceedBtn);
				CommonMethods.Click(PANProceedBtn);
				try {
					log.error(CommonMethods.getElementText(PANErrMsg));
					CommonMethods.Click(PANErrOkBtn);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			
		
	}
	
	@FindBy(xpath="//div[@class='Select-menu']/div/div/div")			//City auto suggestlist
	private List<WebElement> cityList;
	
	public void NRIPANCibilScreen(String sheetName) throws Exception
	{
		
			log.info(CommonMethods.getElementText(PANScreenHeader));
			CommonMethods.input(Add1Fld, sheetName, "Address line 1", 1);
			CommonMethods.input(Add2Fld, sheetName, "Address line 2", 1);
			
			//city
			try {
				String cityVal= CommonMethods.getElementValue(Cityfld);
				if(cityVal.isEmpty())
				{
					CommonMethods.input(Cityfld, sheetName, "City", 1);
					Thread.sleep(2000);
					CommonMethods.ExWaitsForWebelements(cityList);
					for(WebElement ele : cityList)
					{
						String cityNm = CommonMethods.getElementText(ele);
						if(cityNm.equalsIgnoreCase(ExcelOperation.getCellData(sheetName, "City", 1)))
						{
							CommonMethods.Click(ele);
							Thread.sleep(1000);
							break;
						}
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			Thread.sleep(1000);
			CommonMethods.highLight(PANProceedBtn);
			CommonMethods.Click(PANProceedBtn);
		
	}
	
	/*********************************PAN/CIBIL Screen****************************************************/
	/*********************************Income Details Screen****************************************************/
	@FindBy(xpath="//div[@name='headerContainer']/span")				//Income screen header
	private WebElement incomeHdr;
	
	@FindBy(xpath="//div[@name='NetSalleryContainer'] //div[2]/span")				//Applicant net sal lbl
	private WebElement NetSalLbl;
	
	@FindBy(xpath="//div[@name='NetSalleryContainer'] //input")			//Net Salary fld
	private WebElement NetSalFld;
	
	@FindBy(xpath="//div[@name='hidanuContainer']/child::div[2]")		//addSrc lable
	private WebElement addSrcLbl;
	
	@FindBy(xpath="//div[@name='hidanuContainer'] //button")			//addSrc Button
	private WebElement addScrBtn;
	
	@FindBy(xpath="//div[@name='Containeranu'] //input")			//additionl income fld;
	private WebElement addIncomeFld;
	
	@FindBy(xpath="//div[contains(text(),'Additional Income')]")		//Additional Income Lbl
	private WebElement addIncomeLbl;
	
	//EMI
	@FindBy(xpath="//div[@name='hidcurContainer'] //span")				//Add EMI Lbl
	private WebElement addEMILbl;
	
	@FindBy(xpath="//div[@name='hidcurContainer'] //button")			//Add EMI Button
	private WebElement addEMIButton;
	
	@FindBy(xpath="//div[contains(text(),'Current EMI')]")		//Add Current EMI Lbl
	private WebElement currentEMILbl;
	
	@FindBy(xpath="//div[@name='Containeranu']/following::div[1] //input")			//Add Current EMI fld
	private WebElement currentEMIfld;

	@FindBy(xpath="//button[text()='Proceed']")			//Income Proceed Button
	private WebElement incomeProceed;
	
	
//For Self EMployed Individual
	@FindBy(xpath="//div[@name='ProfitContainer'] //div[2]/div/div[1]")		//profit lbl
	private WebElement profitLbl;
	
	@FindBy(xpath="//div[@name='ProfitContainer'] //input")				//Profit fld
	private WebElement profitFld;
	
	
	public void incomeDetails(String sheetName)throws Exception
	{
		
			//CommonMethods.waitForURL("incomedetail");
			log.info(CommonMethods.getElementText(incomeHdr));
			
				if(ExcelOperation.getCellData(sheetName, "Applicant Type", 1).equalsIgnoreCase("Salaried"))
				{
					CommonMethods.input(NetSalFld, sheetName,"Salary Per Month", 1);
					log.info(CommonMethods.getElementText(NetSalLbl)+" = "+CommonMethods.getElementValue(NetSalFld));
				}
				else if(ExcelOperation.getCellData(sheetName, "Applicant Type", 1).equalsIgnoreCase("Self employed") && ExcelOperation.getCellData(sheetName, "Individual/Non-Individual", 1).equalsIgnoreCase("Individual"))
				{
					CommonMethods.input(profitFld, sheetName, "Salary Per Month", 1);
					log.info(CommonMethods.getElementText(profitLbl) +" = "+CommonMethods.getElementValue(profitFld));
				}
			//Add additional Income
			Thread.sleep(1000);
			log.info(CommonMethods.getElementText(addSrcLbl));
			CommonMethods.Click(addScrBtn);
			CommonMethods.input(addIncomeFld, sheetName, "Additional Income", 1);
			log.info(CommonMethods.getElementText(addIncomeLbl)+" = "+CommonMethods.getElementValue(addIncomeFld));
			
			//Add EMI
			log.info(CommonMethods.getElementText(addEMILbl));
			CommonMethods.Click(addEMIButton);
			CommonMethods.input(currentEMIfld, sheetName, "Current EMI", 1);
			log.info(CommonMethods.getElementText(currentEMILbl)+" = "+CommonMethods.getElementValue(currentEMIfld));
			
			ScreenShot.takeSnapShot("IncomeDetailsScreen", "Pass");
			
			Thread.sleep(1000);
			CommonMethods.highLight(incomeProceed);
			CommonMethods.Click(incomeProceed);
			
		
	}
/*********************************Income Details Screen****************************************************/
/*********************************Self Emp Non Individual Income Details Screen****************************************************/
@FindBy(xpath="//div[@name=\"HomebtnContainer\"]/following::div[1]/div //span")   //Header
private WebElement selfIncomeHdr;

@FindBy(xpath="//div[@name=\"Entity_Container\"]/div[1]//span[1]")     //entity annual turnover lbl;
private WebElement turnOverLbl;

@FindBy(xpath="//div[@name=\"Entity_Container\"] //input")         //annual turnover fld
private WebElement turnOverFld;

@FindBy(xpath="//div[@name=\"Entity_Profit_Container\"]/div[1]//span[1]")		//annual profit after tax Lbl
private WebElement annualProfitLbl;

@FindBy(xpath="//div[@name=\"Entity_Profit_Container\"] //input")		//annual profit after tax fld
private WebElement annualProfitFld;

	
public void selfEmpIncomeDetails(String sheetName)throws Exception
{
	
		log.info(CommonMethods.getElementText(selfIncomeHdr));
		
		CommonMethods.input(turnOverFld, sheetName, "Annual Turnover", 1);
		CommonMethods.input(annualProfitFld, sheetName, "Annual profit after tax", 1);
		
		//Add additional Income
		Thread.sleep(1000);
		log.info(CommonMethods.getElementText(addSrcLbl));
		CommonMethods.Click(addScrBtn);
		CommonMethods.input(addIncomeFld, sheetName, "Additional Income", 1);
		log.info(CommonMethods.getElementText(addIncomeLbl)+" = "+CommonMethods.getElementValue(addIncomeFld));
		
		//Add EMI
		log.info(CommonMethods.getElementText(addEMILbl));
		CommonMethods.Click(addEMIButton);
		CommonMethods.input(currentEMIfld, sheetName, "Current EMI", 1);
		log.info(CommonMethods.getElementText(currentEMILbl)+" = "+CommonMethods.getElementValue(currentEMIfld));
		
		ScreenShot.takeSnapShot("SelfEmpIncomeDetailsScreen", "Pass");
		
		Thread.sleep(1000);
		CommonMethods.highLight(incomeProceed);
		CommonMethods.Click(incomeProceed);
	
}
/*********************************Self Emp Non Individual Income Details Screen****************************************************/

/*********************************Applicant work Details Screen****************************************************/

	@FindBy(xpath="//div[@name='applicantContainer']")				//work details header
	private WebElement workDetailsHeader;
	
	@FindBy(xpath="//span[text()='Organisation Name']")				//organization name lbl
	private WebElement orgNameLbl;
	
	@FindBy(xpath="//input[@class='autocomplet-fld ']")				//Organization Name fld
	private WebElement orgNameFld;		
	
	@FindBy(xpath="//span[text()='Work Email Address']")				//work email lbl
	private WebElement workEmailLbl;
	
	@FindBy(xpath="//input[@placeholder='Enter Work Email Address']")	//Work Email add fld
	private WebElement workEmailFld;
	
	@FindBy(xpath="//span[text()='Nature Of Organization']")			//nature of org lbl
	private WebElement OrgNatureLbl;
	
	@FindBy(xpath="//div[@name='Nature_Of_Organization'] //select")					//Nature Of Orgnization LOVs
	private WebElement OrgNatureLOVs;
	
	@FindBy(xpath="//span[text()='Qualification']")					//Qualification Label
	private WebElement qualificationLbl;
	
	@FindBy(xpath="//div[@name='qualification_Container'] //select")					//Qualification LOVS
	private WebElement qualificationLOVs;		
	
	@FindBy(xpath="//button[text()='Proceed']")						//Work details Proceed button
	private WebElement workProceed;
	
	@FindBy(xpath="//button[text()='Skip & Proceed']")				//Skip n Proceed on Karza waiting screen
	private WebElement skipProceedOnKarza;
	
	public void applicantWorkDetails(String sheetName)throws Exception
	{
		
			Thread.sleep(1000);
			//CommonMethods.ExWait(workDetailsHeader);
			log.info("Fill "+CommonMethods.getElementText(workDetailsHeader));
			
			CommonMethods.input(orgNameFld, sheetName, "Employer Name", 1);
			log.info(CommonMethods.getElementText(orgNameLbl)+" = "+CommonMethods.getElementValue(orgNameFld));
			
			CommonMethods.input(workEmailFld, sheetName, "WorkEmail Address",1);
			log.info(CommonMethods.getElementText(workEmailLbl)+" = "+CommonMethods.getElementValue(workEmailFld));
			
			CommonMethods.selectByText(OrgNatureLOVs, sheetName, "Nature Of Organization", 1);
			
			CommonMethods.selectByText(qualificationLOVs, sheetName, "Education", 1);
			
			ScreenShot.takeSnapShot("ApplicantWorkDetailScreen", "Pass");
			Thread.sleep(1000);
			CommonMethods.highLight(workProceed);
			CommonMethods.Click(workProceed);
		
	}
	/*********************************Applicant work Details Screen****************************************************/
	
	/*********************************Karza Waiting/Failure Screen****************************************************/

	@FindBy(xpath="//div[@name='Container NS']/span")
	private WebElement karzaWtingmsg;
	
	public void karzaWaitingScreen(String sheetName) throws Exception
	{
			//Karza waiting screen
			//CommonMethods.waitForURL("karza_check");
			log.info("Karza Waiting screen open" +CommonMethods.getElementText(karzaWtingmsg));
			ScreenShot.takeSnapShot("KarzaWaitingScreen", "Pass");
			CommonMethods.highLight(skipProceedOnKarza);
			CommonMethods.Click(skipProceedOnKarza);
		
	}
	
	@FindBy(xpath="//span[text()='Employer verification has failed']")			//failure msg
	private WebElement karzaFailMsg;
	
	@FindBy(xpath="//button[text()='Upload 3 month pay slips']")			//Upload 3 months payslip
	private WebElement upload3MonthsSal;
	
	@FindBy(xpath="//button[text()='Skip and Proceed']")			//SkipProceedBtn
	private WebElement karzaFailSkip;
	
	public void KarzaFailureScreen(String sheetName)throws Exception
	{
		
			CommonMethods.waitForURL("karzafail");
			log.info("Karza Failure occured due to "+CommonMethods.getElementText(karzaFailMsg));
			ScreenShot.takeSnapShot("KarzaFailureScreen", "Pass");
			
			CommonMethods.highLight(karzaFailSkip);
			CommonMethods.Click(karzaFailSkip);
		
		
	}
	
/*********************************Karza Waiting/Failure Screen****************************************************/

/*********************************Property Details Screen****************************************************/

	@FindBy(xpath="//span[text()='Property Details']")					//Property Details header
	private WebElement propDetailsHdr;
	
	@FindBy(xpath="//div[@name='Container1'] //label")			 //Property Not identified radio
	private WebElement PNIRadioBtn;
	
	@FindBy(xpath="//div[@name='Container2'] //label")			//Property Identified radio button
	private WebElement PIRadioBtn;
	
	@FindBy(xpath="//span[text()='Required Loan Amount']")					//RequiredLoan Amount Lbl
	private WebElement reqLoanAmtLbl;
	
	@FindBy(xpath="//div[@name=\"loan_container\"] //input	")	 //Required Loan Amt fld
	private WebElement reqLoanAmtFld;
	
	@FindBy(xpath="//span[text()='Type of Property']")					//Type of property Lbl
	private WebElement typeOfPropLbl;
	
	@FindBy(xpath="//div[@name=\"loan_container\"] //select")			//type of Prop LOVs
	private WebElement typeOfPropLOVS;
	
	@FindBy(xpath="//button[text()='Proceed']")				//Proceed on Property details screen
	private WebElement propDetailsProceed;
	
	@FindBy(xpath="//div[@name=\"PurposeContainer\"] //label")		//Purpose of loan list
	private List<WebElement> purposeOfLoan;
	
	@FindBy(xpath="//div[@name=\"COP\"]/span[1]")				//Cost of property Lbl
	private WebElement costOfPropLbl;
	
	@FindBy(xpath="//div[@name=\"COP\"] //input")				//Cost of Property Fld
	private WebElement costOfPropertyFld;
	
	@FindBy(xpath="//div[@name=\"buildercontain\"]/span")		//Name of builder lbl
	private WebElement nameOfBuilderLbl;
	
	@FindBy(xpath="//div[@name=\"buildercontain\"] //input") 	//Name of builder fld
	private WebElement nameOfBuilderFld;
	
	@FindBy(xpath="//div[@name=\"Property_type_Container\"] //select")		//Stage of property LOVS
	private WebElement stageOfPropertyLOVS;
	
	
	public void propertyDetailsScreen(String sheetName)throws Exception
	{
			//CommonMethods.waitForURL("propdetails");
			log.info(CommonMethods.getElementText(propDetailsHdr));
			ScreenShot.takeSnapShot("PropertyDetailsScreen", "Pass");
			
			String excelValue = ExcelOperation.getCellData(sheetName, "Property Identified", 1);
			if(excelValue.equalsIgnoreCase("No"))
			{
				//CommonMethods.mouseHover(PNIRadioBtn);
					CommonMethods.mouseClick(PNIRadioBtn);
			
					log.info(excelValue +" Fill other details");
				//Required Loan Amount
					CommonMethods.input(reqLoanAmtFld, sheetName, "Loan Amount", 1);
					log.info(CommonMethods.getElementText(reqLoanAmtLbl)+" = "+CommonMethods.getElementValue(reqLoanAmtFld));
					
				//Type of property
					CommonMethods.selectByText(typeOfPropLOVS, sheetName, "Type Of Property", 1);
				
			}
			else if(excelValue.equalsIgnoreCase("Yes"))
			{
				//CommonMethods.mouseHover(PIRadioBtn);
					CommonMethods.mouseClick(PIRadioBtn);
				//Required Loan Amount
					CommonMethods.input(reqLoanAmtFld, sheetName, "Loan Amount", 1);
					log.info(CommonMethods.getElementText(reqLoanAmtLbl)+" = "+CommonMethods.getElementValue(reqLoanAmtFld));
				
				//Type of property
					CommonMethods.selectByText(typeOfPropLOVS, sheetName, "Type Of Property", 1);
					
				//Purpose of Loan
					for(WebElement ele : purposeOfLoan)
					{
						String purposeOfLoanExcel = CommonMethods.getElementText(ele) ;
						if(purposeOfLoanExcel.equalsIgnoreCase(ExcelOperation.getCellData(sheetName, "Purpose Of Loan", 1)))
						{
							CommonMethods.mouseClick(ele);
							break;
						}
					}
					
					CommonMethods.scrollAtBottom();
				//cost of property
					CommonMethods.input(costOfPropertyFld, sheetName, "Cost Of Property", 1);
					log.info(CommonMethods.getElementText(costOfPropLbl) +" : "+CommonMethods.getElementValue(costOfPropertyFld));
					
				//Stage of Property
					CommonMethods.selectByText(stageOfPropertyLOVS, sheetName, "Stage Of Property", 1);
				
			}
			
			ScreenShot.Ashot("PropertyDeatils", "Pass");		
			CommonMethods.highLight(propDetailsProceed);
			CommonMethods.Click(propDetailsProceed);
			
	}
	
/*********************************Property Details Screen****************************************************/

/*********************************Bank Statement Upload/Perfios Screen****************************************************/
	
@FindBy(xpath="//span[text()='Bank Statement Upload by Applicant']")			//Bank stmt screen header
private WebElement bankStUploadHedr;

@FindBy(xpath="//div[@name='Container1'] //span")			//Upload by customer labl
private WebElement uploadByCustLbl;

@FindBy(xpath="//div[@name='Container1'] //label")	//Upload By Customer Radio btn
private WebElement uploadByCustoRadioBtn;

@FindBy(xpath="//div[@name='Container2'] //span")			//Upload By RM Lable
private WebElement uploadByRMLbl;

@FindBy(xpath="//div[@name='Container2'] //label")	//Upload By RM eadio btn
private WebElement uploadByRMRadioBtn;
	
@FindBy(xpath="//div[contains(text(),'Applicant Account Type')]")		//Account Type Lable
private WebElement	accountTypeLbl;

@FindBy(xpath="//div[@name='Container'] //select")		//Account Type LOVs
private WebElement accountTypeLOVS;

@FindBy(xpath="//button[text()='Proceed']")		//Bank Upload Proceed
private WebElement bankUploadProceed;


@FindBy(xpath="//span[text()='Please wait to verify Perfios status']")		//Perfios status msg
private WebElement perfiosStatusMsg;

@FindBy(xpath="//div[@name='Container'] //a")		//Perfios Link for RM user
private WebElement perfiosLink;

@FindBy(xpath="//button[text()='Skip And Proceed']")		//Perfios status Skip N Proceed
private WebElement 	SkipProceedPerfios;

public void bankStmtUpload(String sheetName)throws Exception
{
	
		//CommonMethods.waitForURL("statementup");
		//CommonMethods.ExWait(bankStUploadHedr);
		log.info(CommonMethods.getElementText(bankStUploadHedr));
		String bankUploadBy =ExcelOperation.getCellData(sheetName, "Bank Statement Upload", 1);
		if(bankUploadBy.equalsIgnoreCase("Customer"))
		{
			//CommonMethods.Click(uploadByCustoRadioBtn);
			CommonMethods.mouseClick(uploadByCustoRadioBtn);
			log.info(CommonMethods.getElementText(uploadByCustLbl)+" get selected");
		}
		else if(bankUploadBy.equalsIgnoreCase("RM"))
		{
			//CommonMethods.Click(uploadByRMRadioBtn);
			CommonMethods.mouseClick(uploadByRMRadioBtn);
			log.info(CommonMethods.getElementText(uploadByRMLbl)+"get selected .");
			
		}
		
		Thread.sleep(1000);
		log.info(CommonMethods.getElementText(accountTypeLbl));
		CommonMethods.selectByText(accountTypeLOVS, sheetName, "Account Type", 1);
		Thread.sleep(1000);
		ScreenShot.takeSnapShot("BankStatmentUpload", "Pass");
		
		CommonMethods.highLight(bankUploadProceed);
		CommonMethods.Click(bankUploadProceed);
		
		Thread.sleep(2000);
		
		try {
			if(bankUploadBy.equalsIgnoreCase("RM"))
			{
				CommonMethods.waitForURL("perfiosstatus");
				log.info(CommonMethods.getElementText(perfiosStatusMsg));
				ScreenShot.takeSnapShot("PerfiosStatus", "Pass");

				log.info("Perfios Link for RM user :"+CommonMethods.getElementText(perfiosLink));
				//CommonMethods.Click(perfiosLink);
				//ScreenShot.takeSnapShot("RMPerfiosLink", "Pass");
				//CommonMethods.switchToWindowByTitle("Customer Digital Journey");
			}
			Thread.sleep(1000);
			CommonMethods.highLight(SkipProceedPerfios);
			CommonMethods.Click(SkipProceedPerfios);
		} catch (Exception e) {
			log.error("Perfios Status exception due to "+e.getMessage());
		}
	
}

/*********************************Bank Statement Upload/Perfios Screen****************************************************/

/*********************************Bank Statement Upload/Perfios Failure Screen****************************************************/
@FindBy(xpath="//span[text()='Documents are not in correct format']")			//bank stmt upload fail msg
private WebElement BankSTFailureMsg;

@FindBy(xpath="//button[@id='missing_229']")		//ReUpload Documnets
private WebElement reuploadBankDoc;

@FindBy(xpath="//button[text()='Continue and Proceed with In-principal sanction']")		//Skip and Continue Proceed Btn
private WebElement ContinuePerfiosFailBtn;


public void perfiosFailure(String sheetName)throws Exception
{
	
		//CommonMethods.waitForURL("statementfail");
		ScreenShot.takeSnapShot("BankStmtUpalodFailure", "Pass");
		log.info("Bank Statement upload Fails due to " +CommonMethods.getElementText(BankSTFailureMsg));
		
		//Continue and Proceed
		CommonMethods.highLight(ContinuePerfiosFailBtn);
		CommonMethods.Click(ContinuePerfiosFailBtn);
		
}
	
/*********************************Bank Statement Upload/Perfios Failure Screen****************************************************/
/*********************************ITR Screen****************************************************/
@FindBy(xpath="//button[text()='Proceed']")			//Proceed btn
private WebElement proceedBtn;

@FindBy(xpath="//div[@name=\"Container\"]/child::span")		//Screen hdr
private WebElement ITRHdr;

@FindBy(xpath="//div[@name=\"Perfios_Pass\"] //span")
private WebElement ITRPerfiosPassMsg;

@FindBy(xpath="//div[@name=\"Perfios_progress\"] //span")	//perfios status msg
private WebElement ITRperfiosStatusMsg;

@FindBy(xpath="//button[text()='Check']")			//Check button
private WebElement checkBtn;

@FindBy(xpath="//button[text()='Skip And Proceed']")			//Skip&Proceed Btn
private WebElement ITRSkip;

public void ITRScreen() throws Exception
{
	Thread.sleep(2000);
	CommonMethods.ExWait(ITRHdr);
	log.info(CommonMethods.getElementText(ITRHdr));
	ScreenShot.takeSnapShot("ITReturnScreen", "Pass");
	CommonMethods.highLight(proceedBtn);
	CommonMethods.Click(proceedBtn);
	
	//perfios check 
	log.info(CommonMethods.getElementText(ITRPerfiosPassMsg));
	CommonMethods.highLight(checkBtn);
	CommonMethods.Click(checkBtn);
	log.info(CommonMethods.getElementText(ITRperfiosStatusMsg));
	
	CommonMethods.highLight(ITRSkip);
	CommonMethods.Click(ITRSkip);
	
}

/*********************************ITR Screen****************************************************/


/*********************************Cross Sell Screen****************************************************/
@FindBy(xpath="//span[text()='Add-ons']")
private WebElement CrossSellHdr;

@FindBy(xpath="//button[text()='Skip']")			//Skip Button
private WebElement CrossSellSkipBtn;

public void CrossSellScreen(String sheetName)throws Exception
{
	
		CommonMethods.ExWait(CrossSellHdr);
		
		ScreenShot.takeSnapShot("CrossSellScreen", "Pass");
		CommonMethods.highLight(CrossSellSkipBtn);
		CommonMethods.Click(CrossSellSkipBtn);
		
}

/*********************************Cross Sell Screen****************************************************/


}
