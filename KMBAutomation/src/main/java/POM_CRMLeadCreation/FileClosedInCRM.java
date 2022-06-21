package POM_CRMLeadCreation;

import java.time.Duration;

import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CommonUtility.ExcelOperation;
import CommonUtility.CommonMethods;
import CommonUtility.ScreenShot;
import CommonUtility.SetUp;

public class FileClosedInCRM extends SetUp
{
	public Logger log = LoggerFactory.getLogger(FileClosedInCRM.class);
	
	public FileClosedInCRM(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[contains(text(),'File Closed in CRM')]")  //File Closed in CRM Tab   
	private WebElement FileClosedTab;
	public WebElement FileClosedTab()
	{
		return FileClosedTab;
	}
	
	
	@FindBy(xpath="//select[@data-autoid='cust_608_ctrl']")  //Individual/Non Individual LOVs
	private WebElement IndividualLOV;
	public WebElement IndividualLOV()
	{
		return IndividualLOV;
	}
	
	
	@FindBy(xpath="//input[@data-autoid='cust_439_ctrl']")  //Employer Name Label
	private WebElement employerNameLbl;
	public WebElement employerNameLbl()
	{
		return employerNameLbl;
	}
	
	@FindBy(xpath="//input[@data-autoid='cust_439_ctrl']")  //Employer Name field
	private WebElement employerName;
	public WebElement employerName()
	{
		return employerName;
	}
	
	@FindBy(xpath="//label[@data-autoid='cust_545_lbl']")  //Location Label
	private WebElement LocationLbl;
	public WebElement LocationLbl()
	{
		return LocationLbl;
	}
	
	@FindBy(xpath="//input[@data-autoid='cust_545_ctrl']")  //Location field
	private WebElement LocationFld;
	public WebElement LocationFld()
	{
		return LocationFld;
	}
	
	@FindBy(xpath="//label[@data-autoid='cust_546_lbl']")  //Sub Location Label
	private WebElement SubLocationLbl;
	public WebElement SubLocationLbl()
	{
		return SubLocationLbl;
	}
	
	@FindBy(xpath="//input[@data-autoid='cust_546_ctrl']")  //Sub Location field
	private WebElement SubLocationFld;
	public WebElement SubLocationFld()
	{
		return SubLocationFld;
	}
	
	@FindBy(xpath="//a[@id='WEBR_40']")  //Click here to fetch link
	private WebElement SubLocationLink;
	public WebElement SubLocationLink()
	{
		return SubLocationLink;
	}
	
	
	@FindBy(xpath="//label[contains(text(),'PAN / Company PAN')]")  //PAN Label
	private WebElement PANLbl;
	public WebElement PANLbl()
	{
		return PANLbl;
	}
	
	@FindBy(xpath="//input[@data-autoid='LE_PANNUMBER_ctrl']")  //PAN Field
	private WebElement PANFld;
	public WebElement PANFld()
	{
		return PANFld;
	}
	
	@FindBy(xpath="//label[contains(text(),'Work Experience')]")  //Work experience label
	private WebElement WorkExpLbl;
	public WebElement WorkExpLbl()
	{
		return WorkExpLbl ;
	}
	
	@FindBy(xpath="//select[@data-autoid='cust_118_ctrl']")  //Work experience LOVs
	private WebElement WorkExpLOV;
	public WebElement WorkExpLOV()
	{
		return WorkExpLOV ;
	}
	
	@FindBy(xpath="//select[@data-autoid='cust_3183_ctrl']")  //DIGI HL LOVs
	private WebElement DigiHLLOV;
	public WebElement DigiHLLOV()
	{
		return DigiHLLOV ;
	}
	
	@FindBy(xpath="//div[@data-autoid='cust_612']/label")  //CIBIL consent label
	private WebElement CIBILConstLbl;
	public WebElement CIBILConstLbl()
	{
		return CIBILConstLbl;
	}
	
	@FindBy(xpath="//div[@data-autoid='cust_612']//div[1]/div[1]/div[1]/div[1]/label")  //CIBIL consent check box
	private WebElement CIBILConstChkbox ;
	public WebElement CIBILConstChkbox()
	{
		return CIBILConstChkbox;
	}
	
	
	@FindBy(xpath="//label[contains(text(),'Credit scheme')]")  //Credit scheme label
	private WebElement CreditSchmLbl ;
	public WebElement CreditSchmLbl()
	{
		return CreditSchmLbl;
	}
	
	@FindBy(xpath="//select[@data-autoid='cust_4425_ctrl']")  //Credit scheme LOVS
	private WebElement CreditSchmLOVs;
	public WebElement CreditSchmLOVs()
	{
		return CreditSchmLOVs;
	}
	
	//Address Details
	
	@FindBy(xpath="//label[contains(text(),'Address Type')]")  //Address Type label
	private WebElement AddTypeLbl ;
	public WebElement AddTypeLbl()
	{
		return AddTypeLbl;
	}
	
	@FindBy(xpath="//select[@data-autoid='cust_48_ctrl']")  //Address Type LOVS
	private WebElement AddTypeLOVS ;
	public WebElement AddTypeLOVS()
	{
		return AddTypeLOVS;
	}
	
	
	@FindBy(xpath="//div[@data-autoid='LE_ZIP_CODE']/label")  //PINCODE label
	private WebElement PincodeLbl;
	public WebElement PincodeLbl()
	{
		return PincodeLbl;
	}
	
	@FindBy(xpath="//input[@data-autoid='Grid_SearchTextBox_ctrl']")  //Pincode Search text box
	private WebElement PincodeSrchTxtBox;
	public WebElement PincodeSrchTxtBox()
	{
		return PincodeSrchTxtBox;
	}
	
	@FindBy(xpath="//a[@data-autoid='LE_ZIP_CODE_srch']")  //pincode search button
	private WebElement PincodeSrchBtn;
	public WebElement PincodeSrchBtn()
	{
		return PincodeSrchBtn;
	}
	
	@FindBy(xpath="//a[@data-autoid='gridHF_LE_ZIP_CODE']")  //pincode search arrow
	private WebElement PinSrchArrow;
	public WebElement PinSrchArrow()
	{
		return PinSrchArrow;
	}
	
	@FindBy(xpath="//div[@data-autoid='PinCode_0']")  //1st pincode 
	private WebElement FirstPincode;
	public WebElement FirstPincode()
	{
		return FirstPincode;
	}
	
	
	@FindBy(xpath="//div[@data-autoid='cust_31']/label")  //Address Line 1 Label
	private WebElement Add1Lbl ;
	public WebElement Add1Lbl ()
	{
		return Add1Lbl;
	}
	
	@FindBy(xpath="//input[@data-autoid='cust_31_ctrl']")  //Address line1 field
	private WebElement Add1Fld;
	public WebElement Add1Fld()
	{
		return Add1Fld ;
	}
	
	@FindBy(xpath="//div[@data-autoid='cust_275']/label")  //permanent Address Lable
	private WebElement PermanentAddLbl;
	public WebElement PermanentAddLbl()
	{
		return PermanentAddLbl;
	}
	
	@FindBy(xpath="//select[@data-autoid='cust_275_ctrl']")  //Permanent Add LOVs
	private WebElement PermanentAddLOVs ;
	public WebElement PermanentAddLOVs()
	{
		return PermanentAddLOVs;
	}
	
	@FindBy(xpath="//label[@data-autoid='LE_GENDER_lbl']")  //Gender Label
	private WebElement GenderLbl;
	public WebElement GenderLbl()
	{
		return GenderLbl;
	}	
	
	
	@FindBy(xpath="//select[@data-autoid='LE_GENDER_ctrl']")  //Gender LOVs
	private WebElement GenderLov;
	public WebElement GenderLov()
	{
		return GenderLov;
	}	
	
	@FindBy(xpath="//input[@data-autoid='cust_3897_ctrl']")  //Maiden Name txtfld
	private WebElement MaidenName;
	public WebElement MaidenName()
	{
		return MaidenName;
	}	
	
	@FindBy(xpath="//select[@data-autoid='cust_3898_ctrl']")  //Marital status LOV
	private WebElement MaritalStatusLOV;
	public WebElement MaritalStatusLOV()
	{
		return MaritalStatusLOV;
	}	
	
	@FindBy(xpath="//select[@data-autoid='cust_3899_ctrl']")  //Occupation Type LOV
	private WebElement OccTypeLOV;
	public WebElement OccTypeLOV()
	{
		return OccTypeLOV;
	}	
	
	@FindBy(xpath="//select[@data-autoid='cust_3900_ctrl']")  //Income LOV
	private WebElement IncomeLOV;
	public WebElement IncomeLOV()
	{
		return IncomeLOV;
	}	
	
	@FindBy(xpath="//select[@data-autoid='cust_3902_ctrl']")  //Education LOV
	private WebElement EducationLOV;
	public WebElement EducationLOV()
	{
		return EducationLOV;
	}	
	
	
	@FindBy(xpath="//div[@data-autoid='F662_0']")  //RM EMP COde after clicking on CLick here to fetch
	private WebElement RMEmpCode;
	public WebElement RMEmpCode()
	{
		return RMEmpCode;
	}	
	
	@FindBy(xpath="//button[contains(text(),'Ok')]")  //OK button on Pop Up
	private WebElement OkBtn;
	public WebElement OkBtn()
	{
		return OkBtn;
	}	
	
	@FindBy(xpath="//a[@data-autoid='FlowNext']")  // Save & Proceed Button
	private WebElement saveProceedBtn;
	public WebElement saveProceedBtn()
	{
		return saveProceedBtn;
	}
	
	@FindBy(xpath="//span[@data-autoid='LE_STATUSCODE_ctrl']")   //Lead Status code value
	private WebElement leadStatusCodeVal;
	public WebElement leadStatusCodeVal()
	{
		return leadStatusCodeVal;
	}
	
	@FindBy(xpath="//label[contains(text(),'Last Modified On')]")   //Lead Last Modified on label
	private WebElement leadLastModifyLbl;
	public WebElement leadLastModifyLbl()
	{
		return leadLastModifyLbl;
	}
	
	@FindBy(xpath="//span[@data-autoid='Band_lastmodifiedon_ctrl']")   //Lead Last Modified on value
	private WebElement leadLastModifyVal;
	public WebElement leadLastModifyVal()
	{
		return leadLastModifyVal;
	}
	
	//Edit function to perform Edit operation on Lead
			@FindBy(xpath="//div[@data-autoid='LE_STATUSCODE']/label")   //Lead Status code label
			private WebElement leadStatusCodeLbl;
			public WebElement leadStatusCodeLbl()
			{
				return leadStatusCodeLbl;
			}	
			
			
			@FindBy(xpath="//a[@data-autoid='Edit_1']/span[1]")   //Lead Edit button
			private WebElement LeadEdit;
			public WebElement LeadEdit()
			{
				return LeadEdit;
			}
			
			@FindBy(xpath="//div[@data-autoid='LE_NUMBER']/label")   //Lead ID label
			private WebElement leadIDLbl;
			public WebElement leadIDLbl()
			{
				return leadIDLbl;
			}

			@FindBy(xpath="//span[@data-autoid='LE_NUMBER_ctrl']")   //Lead ID 
			private WebElement leadID;
			public WebElement leadID()
			{
				return leadID;
			}	
			
		//AddressProf Download
			@FindBy(xpath="//div[@data-autoid='AutoDocRow_2957_108']/label")    //Voter ID Card Label
			private WebElement VoterIdLbl;
			public WebElement VoterIdLbl()
			{
				return VoterIdLbl;
			}
			
			@FindBy(xpath="//div[@data-autoid='AutoDocRow_2957_108']/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[@type='file']")    //Voter ID Card Upload 	
			private WebElement VoterIdUploadBtn;
			public WebElement VoterIdUploadBtn()
			{
				return VoterIdUploadBtn;
			}
			
			public void Edit() throws InterruptedException
			{
				Thread.sleep(1000);
				CommonMethods.ExWait(leadStatusCodeLbl);
				CommonMethods.scrollByVisibilityofElement(LeadEdit);
				CommonMethods.highLight(LeadEdit);
				LeadEdit.click();
				Thread.sleep(1000);
			}
	
			public void writeToExcel()
			{
				 //Write Data into excel 
				try {
					Thread.sleep(2000);
					CommonMethods.ExWait(leadStatusCodeLbl);
				  	ExcelOperation.writeToExcel("Output", ExcelOperation.getRowCount("Output"), 2, leadStatusCodeVal().getText());
				  	ExcelOperation.writeToExcel("Output", ExcelOperation.getRowCount("Output"), 3, leadLastModifyVal().getText());
				  }
				  catch (Exception e) {}
			}
			
			
			//upload Warning pop up
			
			@FindBy(xpath="//div[@class='warning__message']")   //Warning message
			private WebElement warningMsg;
			public WebElement warningMsg()
			{
				return warningMsg;
			}
			
			@FindBy(xpath="//button[@type='button' and contains(text(),'Ok')]")   //Warning message OK button
			private WebElement warningOKBtn;
			public WebElement warningOKBtn()
			{
				return warningOKBtn;
			}
			
			@FindBy(xpath="//select[@name='cust_4949']")		//Financial Applicant LOVs
			private WebElement fincialAppLovs;
			
			
			public void DocCollected(String sheetName) throws Exception
			{
				try 
				{
					//Edit();
					CommonMethods.highLight(FileClosedTab);
					Thread.sleep(1000);
					FileClosedTab.click();
					
					CommonMethods.selectByText(IndividualLOV,sheetName, "Individual/Non-Individual",1);
					Thread.sleep(1000);

					employerName.clear();
					employerName.sendKeys(ExcelOperation.getCellData(sheetName, "Employer Name",1));

					//Location sub location
					try {
						Thread.sleep(1000);
						CommonMethods.scrollByVisibilityofElement(SubLocationLink);
						CommonMethods.highLight(SubLocationLink);
						CommonMethods.Click(SubLocationLink);
						Thread.sleep(2000);
						CommonMethods.highLight(RMEmpCode);
						CommonMethods.Click(RMEmpCode);
						Thread.sleep(1000);
						CommonMethods.Click(OkBtn);
					}catch(Exception e) 
					{    
						log.info("Unable to select Location and SubLocation due to "+e.getMessage());
					}
					
					Thread.sleep(1000);

					CommonMethods.scrollByVisibilityofElement(PANFld);
					CommonMethods.ExWait(PANLbl);
					PANFld.clear();
					PANFld.sendKeys(ExcelOperation.getCellData(sheetName, "PAN",1));
					
					CommonMethods.selectByText(WorkExpLOV, sheetName, "Work Experince",1);
					
					CommonMethods.selectByText(DigiHLLOV, sheetName, "DIGI HL",1);

					Thread.sleep(1000);
					CommonMethods.Click(CIBILConstChkbox);
		
					try {
					//Financial Coapplicant required yes or no		
						CommonMethods.selectByText(fincialAppLovs, sheetName, "Financial Applicant", 1);
						
					} catch (Exception e) {					}
					
					CommonMethods.selectByText(AddTypeLOVS,sheetName, "Address Type",1);
					Thread.sleep(1000);
					
					try {
						CommonMethods.Click(PincodeSrchBtn);
						Thread.sleep(3000);
						CommonMethods.ExWait(PincodeSrchTxtBox);
						PincodeSrchTxtBox.sendKeys(ExcelOperation.getCellData(sheetName, "Pincode",1));
						CommonMethods.Click(PinSrchArrow);
						Thread.sleep(1000);
						CommonMethods.Click(FirstPincode);
						
					} catch (Exception e) {
						log.info("Unable to select Pincode due to "+e.getMessage());
					}
					Thread.sleep(1000);
					CommonMethods.ExWait(Add1Fld);
					Add1Fld.clear();
					Add1Fld.sendKeys(ExcelOperation.getCellData(sheetName, "Address line 1",1));

					CommonMethods.selectByText(PermanentAddLOVs,sheetName, "Is Permanent Add",1);

				//Identity Proof Upload
				
				/*
				 * try { Thread.sleep(2000);
				 * CommonMethods.scrollByVisibilityofElement(VoterIdLbl);
				 * CommonMethods.ExWait(VoterIdLbl); //
				 * VoterIdUploadBtn.sendKeys(ExcelOperation.getCellData(
				 * sheetName,"Document Upload", 1));
				 * CommonMethods.input(VoterIdUploadBtn,sheetName,"Document Upload", 1); try {
				 * System.out.println("Doc Upload warning: "+warningMsg.getText());
				 * warningOKBtn.click(); } catch (Exception e) {}
				 * 
				 * 
				 * } catch (Exception e) {
				 * System.out.println("voter card ID Upload exception :"+e.getMessage()); }
				 */
					
				//Gender	
					Thread.sleep(1000);					
					CommonMethods.scrollByVisibilityofElement(GenderLbl);
					CommonMethods.selectByText(GenderLov,sheetName, "Gender",1);

				//Maiden Name
					Thread.sleep(1000);
					CommonMethods.scrollByVisibilityofElement(MaidenName);
					MaidenName.sendKeys(ExcelOperation.getCellData(sheetName,"Maiden Name", 1));
					
				//Martial status
					Thread.sleep(1000);
					CommonMethods.selectByText(MaritalStatusLOV ,sheetName, "Marital Status", 1);
				
				//Occupation Type	
					Thread.sleep(1000);
					CommonMethods.selectByText(OccTypeLOV ,sheetName, "Occupation Type", 1);
				
				//Income
					Thread.sleep(1000);
					CommonMethods.selectByText(IncomeLOV ,sheetName, "Income", 1);
				
				//Education
					Thread.sleep(1000);
					CommonMethods.selectByText(EducationLOV ,sheetName, "Education", 1);
				
					Thread.sleep(1000);
					ScreenShot.Ashot("FileClosedInCRM1","Pass");

					
				//Save & Proceed Button	
					Thread.sleep(2000);
					CommonMethods.highLight(saveProceedBtn);
					CommonMethods.Click(saveProceedBtn);
					
					
					Thread.sleep(3000);
					CommonMethods.ExWait(leadStatusCodeLbl);
					writeToExcel();
					Thread.sleep(1000);
					ScreenShot.takeSnapShot("DocCollected","Pass");
					
				} catch (Exception e) {
					//System.out.println("Doc collected Exception :"+e.getMessage());
					ScreenShot.takeSnapShot("Faiure in Document Collected ", "Fail");
				}
			}
		
/**********************Sent to LOS**************************/		
			@FindBy(xpath="//span[contains(text(),'Sent to LOS')]")   //Sent To LOS tab
			private WebElement SentToLOSTab;
			public WebElement SentToLOSTab()
			{
				return SentToLOSTab;
			}
		
			public void SentToLOS() throws Exception
			{
				try 
				{
					Thread.sleep(1000);
					CommonMethods.highLight(FileClosedTab);
					CommonMethods.Click(FileClosedTab);
					
					Thread.sleep(1000);
					CommonMethods.highLight(SentToLOSTab);
					CommonMethods.Click(SentToLOSTab);
					CommonMethods.Click(saveProceedBtn);
					Thread.sleep(1000);
					CommonMethods.ExWait(leadStatusCodeLbl);
					Thread.sleep(1000);
					writeToExcel();
					Thread.sleep(1000);
					
					ScreenShot.takeSnapShot("SentToLOS1","Pass");
					String leadStatus = ExcelOperation.getCellData("Output", "Lead Status", ExcelOperation.getRowCount("Output"));
					System.out.println("Lead status = "+leadStatus);
					if(leadStatus.equalsIgnoreCase("Sent to LOS"))
					{
						ExcelOperation.writeToExcel("Output", ExcelOperation.getRowCount("Output"), 4, "PASS");
					}
					else
					{
						ExcelOperation.writeToExcel("Output", ExcelOperation.getRowCount("Output"), 4, "FAIL");
					}
					
				} catch (Exception e) 
				{	
					System.out.println(e.getMessage());	
					ScreenShot.takeSnapShot("Failure in SentToLOS","Fail");
				}
					
			}
			
	
}
