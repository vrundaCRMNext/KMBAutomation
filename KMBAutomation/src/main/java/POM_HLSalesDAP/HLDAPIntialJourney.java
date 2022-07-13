package POM_HLSalesDAP;

import static org.testng.Assert.assertEquals;

import java.util.List;

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
import java.awt.Robot;	
import java.awt.event.KeyEvent;	


public class HLDAPIntialJourney extends SetUp
{
	public Logger log = LoggerFactory.getLogger(HLDAPIntialJourney.class);
	public String applicantTypeExcel;
	public String selfEMPTypeExcel;
	public String residentTypeExcel;
	
	
	public HLDAPIntialJourney(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	/**********************************Common Function that call all other modules****************************************************/		
	public void runIntialJourney(String sheetName)throws Exception
	{
		
			log.info("Intial Journey Started");
			intiateDAPJourney(sheetName);
			productSelection(sheetName);
			subSourceSelection(sheetName);
			applicantTypeExcel = ExcelOperation.getCellData(sheetName,"Applicant Type", 1);  				 //Salaried or Self Employed
			applicantTypeSelection(sheetName,applicantTypeExcel);

			if(applicantTypeExcel.equalsIgnoreCase("Salaried"))
			{
				Sal_ETB_NTBCheck(sheetName);
			}
			else if (applicantTypeExcel.equalsIgnoreCase("Self employed"))
			{
				SelfEMPTypeSelection(sheetName);
			}
			
			selfEMPTypeExcel = ExcelOperation.getCellData(sheetName,"Individual/Non-Individual",1);     	//Individual or NonIndividual

			if(selfEMPTypeExcel.equalsIgnoreCase("Individual"))
			{
				Sal_ETB_NTBCheck(sheetName);
			}
			else if(selfEMPTypeExcel.equalsIgnoreCase("Non-Individual"))
			{
				selfEmp_ETBNTB_Check(sheetName);
			}
			Thread.sleep(1000);
			//resumeWithLink(sheetName);

			OTPVerification(sheetName,"MainApplicant");
			
			log.info("Applicant Type ="+applicantTypeExcel +" and " +selfEMPTypeExcel);
			if(applicantTypeExcel.equalsIgnoreCase("Self employed") )
			{
				if(selfEMPTypeExcel.equalsIgnoreCase("Individual"))
					selectBusinessVintage(sheetName);
			}
			
			ResidentStatusSelection(sheetName);
			residentTypeExcel = ExcelOperation.getCellData(sheetName, "ResidentType", 1);

			if(residentTypeExcel.equalsIgnoreCase("NRI"))
			{
				NRIRefScreen1(sheetName);
				NRIRefScreen2(sheetName);
			}
		
	}
/**********************************Common Function that call all other modules****************************************************/		
	
	
	@FindBy(xpath="//body/div[@id='main']/div[1]/ul[1]/li[2]")    //Quick links
	private WebElement QuickLinks;
	
	@FindBy(xpath="//div[@id='QuickLinksDiv']//ul/li")            //List of quick links items
	private List<WebElement> ListOfQuickLinks;
	
	@FindBy(xpath="//span[contains(text(),'HL Sales App')]")     	//HL Sales App link from Quick links
	private WebElement HlSalesAppLink;
	
	@FindBy(xpath="//div[@name='MainContainer']/child::div[1]/child::div[1]/span")			//Product list header
	private WebElement productScreenHeader;
	
	@FindBy(xpath="//div[@name='MainContainer'] //div")      			//Product List
	private List<WebElement> ProductList;
	
	@FindBy(xpath="//span[text()='Select Sub-Source details of Lead']")					//SubSource LOV Header	
	private WebElement subSourceHeader;
	
	@FindBy(xpath="//div[@name='ContainercityResiInd'] //select")				//SubSource LOV
	private WebElement subSourceLOV;
	
	@FindBy(xpath="//button[text()='Proceed']")					//proccedBtn om subsource screen
	private WebElement proceedBtnSubSrc;
	
	@FindBy(xpath="//div[@name='Parent1Container']/child::div[2] //span")                   //Employment type selection screen header
	private WebElement ApplicantTypeHeader;
	
	@FindBy(xpath="//div[@name='Parent1Container']/child::div //button")
	private List<WebElement> ApplicantTypes;
	
	//MOB-DOB screen
		
		@FindBy(xpath="//span[contains(text(),'Check if')]")					//MOB DOB Screen header
		private WebElement mob_dob_Header;
		
		@FindBy(xpath="//div[@name='mobileContainer'] //input")				//Mobile No txt field
		private WebElement mobileTxt;
		
		@FindBy(xpath="//div[@name='Mob_Container']/preceding::span[1]")              //Mobile No error msg
		private WebElement mobileErrmsg;
		
		@FindBy(xpath="//div[@class='radioInlineElementKotak'][1]")	//DOB radio button
		private WebElement DOB_DOIBtn;
		
		@FindBy(xpath="//div[@class='radioInlineElementKotak'][2]")			//CRN Button
		WebElement CRNButton;
		
		@FindBy(xpath="//div[@name=\"CRNCont\"] //input")				//CRN fld
		private WebElement CRNFld;
		
		
		
		@FindBy(xpath="//button[text()='Check']")          		//Check button
		private WebElement CheckBtn;
		
		//calander handling
		
		@FindBy(xpath="//div[@id='calenderOutterDiv'] //button")
		private WebElement calanderBtn;
		
		@FindBy(xpath="//div[@class='month-year bb-1 bg-white']/div[1]/select[1]")   	//Month LOVs
		private WebElement monthLOVs;
		
		@FindBy(xpath="//div[@class='month-year bb-1 bg-white']/div[2]/select[1]")		//Year LOVS
		private WebElement yearLOVs;
		
		@FindBy(xpath="//div[@class='react-calendar__month-view__days']/button")	 //Dates List
		private List<WebElement> dates;
		
		@FindBy(xpath="//input[@id='day']")					//Bdate txt field
		private WebElement bday;
		
		@FindBy(xpath="//input[@id='month']")				//BMonth txt field
		private WebElement bMonth;
		
		@FindBy(xpath="//input[@id='year']")				//BYear txt field
		private WebElement bYear;
		
		@FindBy(xpath="//input[@placeholder='Applicant Name']")     	//Applicant Name
		private WebElement applicantName;
		//div[@name='Mob_Popup_Proceed']/preceding::div[13] //input
		
		@FindBy(xpath="//div[@name='gender_Container'] //select")		//Gender LOV
		private WebElement gender;
		
		@FindBy(xpath="//input[@placeholder='Enter Email ID']")				//Email ID
		private WebElement emailID;
		//div[@name='Mob_Popup_Proceed']/preceding::div[1] //input
		
		@FindBy(xpath="//button[text()='Proceed']")		//Proceed button
		private WebElement proceedBtn;
		

	//To open HL Sales App DAP journey form CRM user Quick links
	public void intiateDAPJourney(String sheetName)
	{
		try 
		{
			Thread.sleep(1000);

			CommonMethods.mouseHover(QuickLinks);
			Thread.sleep(1000);
			CommonMethods.highLight(HlSalesAppLink);
			ScreenShot.takeSnapShot("Intiate_HLSales_DAP","Pass");
			CommonMethods.Click(HlSalesAppLink);
			
			Thread.sleep(1000);
			CommonMethods.switchToWindow();
			
			String currentUrl = driver.getCurrentUrl()+"?userId=1353";
			driver.get(currentUrl);
			log.info("URL for user = "+ExcelOperation.getCellData(sheetName,"Username", 1)+ " ||"+currentUrl);
			
			
		} catch (Exception e) {
			log.info("Unable to intiate DAP Journey due to :"+e.getMessage());
		}
	}
	
	
	//Product Selection Screen
	public void productSelection(String sheetName) throws Exception
	{
		
			String ProductNameFromExcel = ExcelOperation.getCellData(sheetName,"Product Type", 1);
			//To iterate product list and click on given product from excel
			log.info(CommonMethods.getElementText(productScreenHeader));
			assertEquals(CommonMethods.getElementText(productScreenHeader),"Select Product type customer is interested in?");
		
			log.info("Product to select from excel sheet : "+ProductNameFromExcel);
			ScreenShot.takeSnapShot("Product_Selection_Screen", "Pass");
			
			Thread.sleep(2000);
			List<WebElement> prodList = ProductList;
			for(WebElement ele : prodList)
			{
				String ProdName = CommonMethods.getElementText(ele);
				Thread.sleep(1000);
				if(ProdName.equalsIgnoreCase(ProductNameFromExcel))
				{
					CommonMethods.mouseHover(ele);
					CommonMethods.Click(ele);
					log.info(ele+" Product get selected and naviagte to next screen");
					break;
				}
			}
		
	}
	
	//Sub Source Selection Screen 
	public void subSourceSelection(String sheetName) throws Exception
	{
		
			Thread.sleep(1000);
			assertEquals(CommonMethods.getElementText(subSourceHeader), "Select Sub-Source details of Lead");
			log.info("Sub Source Selection screen open..");
			log.info("Header of screen : "+CommonMethods.getElementText(subSourceHeader));
			CommonMethods.selectByText(subSourceLOV, sheetName, "Sub Source", 1);
			ScreenShot.takeSnapShot("SubSource_Selection_Screen", "Pass");
			CommonMethods.Click(proceedBtnSubSrc);	
		
	}
	
	//Select Applicant type Screen
	public void applicantTypeSelection(String sheetName, String applicantTypeExcel) throws Exception
	{
		
			Thread.sleep(1000);
			assertEquals(CommonMethods.getElementText(ApplicantTypeHeader), "Is the applicant Salaried or Self-employed?");
			log.info("Header of screen : "+CommonMethods.getElementText(ApplicantTypeHeader));
			applicantTypeExcel = ExcelOperation.getCellData(sheetName,"Applicant Type", 1);
			log.info("Applicant Type to select from excel sheet : "+applicantTypeExcel);
			ScreenShot.takeSnapShot("ApplicantType_Selection_Screen", "Pass");
			
			Thread.sleep(2000);
			List<WebElement> ApplicantTypeList = ApplicantTypes;
			for(WebElement ele : ApplicantTypeList)
			{
				String ProdName = CommonMethods.getElementText(ele);
				Thread.sleep(1000);
				if(ProdName.equalsIgnoreCase(applicantTypeExcel))
				{
					CommonMethods.mouseHover(ele);
					CommonMethods.Click(ele);
					log.info(ele+" Applicant Type get selected and naviagte to next screen");
					break;
				}
			}
		
	}
	
	/**************************SELF EMPLOYED TYPE SELECTION**********************************/
	@FindBy(xpath="//span[@id='id_151037222178923']")			
	private WebElement selfEmpTypeHeader;
	
	@FindBy(xpath="//div[@name=\"parenContainer\"] //button")
	private List<WebElement> selfEmpTypeList;
	
	
	public void SelfEMPTypeSelection(String sheetName) throws Exception
	{
		
			log.info(CommonMethods.getElementText(selfEmpTypeHeader));
			selfEMPTypeExcel = ExcelOperation.getCellData(sheetName,"Individual/Non-Individual",1);
			log.info("Self Employed Type to select from excel sheet : "+selfEMPTypeExcel);
			ScreenShot.takeSnapShot("SelfEmpType_Selection_Screen", "Pass");
			
			for(WebElement ele : selfEmpTypeList)
			{
				String selfEmpType = CommonMethods.getElementText(ele);
				if(selfEmpType.equalsIgnoreCase(selfEMPTypeExcel))
				{
					CommonMethods.mouseHover(ele);
					CommonMethods.Click(ele);
					log.info(selfEmpType +" Self Employed Type get selected ");
					break;
				}
			}
			
		
	}
	
	@FindBy(xpath="//span[contains(text(),'Business Vintage')]")			//Screen hdr
	private WebElement businessHdr;

	@FindBy(xpath="//div[@name=\"Container\"] //button")		//list of buttons
	private List<WebElement> businessVintageBtnList;
	
	public void selectBusinessVintage(String sheetName) throws Exception
	{
		try {
			log.info(CommonMethods.getElementText(businessHdr));
			
			for(WebElement ele:businessVintageBtnList)
			{
				String btnName = CommonMethods.getElementText(ele);
				if(btnName.equalsIgnoreCase(ExcelOperation.getCellData(sheetName, "BusinessVintage", 1)))
				{
					CommonMethods.highLight(ele);
					ScreenShot.takeSnapShot("BusinessVintageScreen", "Pass");
					CommonMethods.Click(ele);
					log.info(CommonMethods.getElementText(businessHdr)+" : "+ btnName+" get selected and naviagte to next screen");
					break;
				}
			}
		}catch(Exception e) {log.error("Business vintage screen exc due to :"+e.getMessage());}	
	}
	
	
	public void selectDate(String sheetName)throws Exception
	{
		//Select DOB or DOI Date using Calendar
				String DObDate = ExcelOperation.getCellData(sheetName,"DOBDate", 1);
				String DOBMnth = ExcelOperation.getCellData(sheetName,"DOBMonth", 1);
				String DOBYear = ExcelOperation.getCellData(sheetName,"DOBYear", 1);

					CommonMethods.Click(calanderBtn);
		
				//Month 
					try {
					Thread.sleep(2000);
					CommonMethods.Click(monthLOVs);
					CommonMethods.selectByText(monthLOVs,sheetName,"DOBMonth", 1); 
					}catch(Exception e) 
					{
						log.error("Month selection Exception occured due to "+e.getMessage()); 
					}

				//Year 
					try { 
						Thread.sleep(1000);
						CommonMethods.Click(yearLOVs);
						CommonMethods.selectByText(yearLOVs,sheetName,"DOBYear", 1);
					}catch(Exception e) 
					{
						log.error("Year selection Exception occured due to "+e.getMessage()); 
					}


				try { 
				//Date 
					Thread.sleep(1000); 
					for(WebElement ele:dates) 
					{ 
						String dateName =CommonMethods.getElementText(ele); 
						if(dateName.equalsIgnoreCase(DObDate)) 
						{ 
							CommonMethods.Click(ele);
							log.info("Date :" +dateName+ " get selected"); 
							break;
						} 
					} 
				} catch (Exception e) {
					log.error("Date selection exception occured due to "+e.getMessage()); }


					
				/*
				 * try{ Thread.sleep(1000); //CommonMethods.Click(bday);
				 * CommonMethods.input(bday, sheetName,"DOBDate", 2); Thread.sleep(2000);
				 * }catch(Exception e) {}
				 * 
				 * try { CommonMethods.Click(calanderBtn);
				 * 
				 * //CommonMethods.Click(bday); CommonMethods.input(bday, sheetName,"DOBDate",
				 * 2);
				 * 
				 * //CommonMethods.Click(bMonth); CommonMethods.input(bMonth,
				 * sheetName,"DOBMonth", 2);
				 * 
				 * //CommonMethods.Click(bYear); CommonMethods.input(bYear,
				 * sheetName,"DOBYear", 2); log.info("DOB selected.."); }catch(Exception e) {}
				 */
			
	}
	
	
	/********************************Salaried ETB/NTB Check Screen **********************************/
	public void Sal_ETB_NTBCheck(String sheetName ) throws Exception
	{
		
			Thread.sleep(1000);
			assertEquals(CommonMethods.getElementText(mob_dob_Header), "Check if applicant is an Existing customer and has a PA offer?");
			log.info("Header of screen : "+CommonMethods.getElementText(mob_dob_Header));
			
			CommonMethods.input(mobileTxt,sheetName,"Mobile No",1);
			Thread.sleep(1000);
			
			try {
				String MobNo = CommonMethods.getElementValue(mobileTxt);
				if(MobNo.isEmpty())
						CommonMethods.input(mobileTxt,sheetName,"Mobile No",1);
			} catch (Exception e) {}

			String ETB_NTBType= ExcelOperation.getCellData(sheetName, "ETB/NTB", 1);
			if(ETB_NTBType.equalsIgnoreCase("NTB"))
			{
				log.info("Applicant is NTB type, so select DOB and Proceed.");
				CommonMethods.Click(DOB_DOIBtn);
				selectDate(sheetName);
				
			} //end of if
			else if(ETB_NTBType.equalsIgnoreCase("ETB"))
			{
				//CRN 
				log.info("Applicant is ETB type, so select CRN and Proceed.");
				CommonMethods.Click(CRNButton);
				CommonMethods.input(CRNFld, sheetName, "CRN", 1);
				
			}
			
			Thread.sleep(1000);
			CommonMethods.Click(CheckBtn);
			Thread.sleep(2000);

			
			String AppName = applicantName.getAttribute("value");
			boolean Gender = gender.isSelected();
			String email = emailID.getAttribute("value");
			
			if(AppName.isBlank())
			{
				CommonMethods.input(applicantName,sheetName,"Last Name", 1);
				Thread.sleep(1000);
				log.info("NTB Applicant name is :"+applicantName.getAttribute("value"));
			}
			else
			{
				log.info("ETB Applicant name is :"+AppName);
			}
			
			Thread.sleep(1000);
			
			if(Gender)
			{
				log.info("ETB Applicant Gender selected");
			}
			else
			{
				CommonMethods.selectByText(gender, sheetName,"Gender" , 1);
			}
			Thread.sleep(1000);
			if(email.isBlank())
			{
				CommonMethods.input(emailID, sheetName,"Email", 1);
				log.info("NTB Applicant email id ="+emailID.getAttribute("value"));
			}
			else
			{
				log.info("ETB Applicant email id ="+emailID.getAttribute("value"));
			}
			Thread.sleep(1000);
			ScreenShot.takeSnapShot("MOB_DOB_Screen", "Pass");
			CommonMethods.scrollByVisibilityofElement(proceedBtn);
			CommonMethods.Click(proceedBtn);
			Thread.sleep(1000);
			
	}
/********************************Salaried ETB/NTB Check Screen**********************************/

	
/*****************************SELF EMP Non Individual ETB/NTB CHECK******************************************/
	@FindBy(xpath="//div[@name=\"firmlabel\"] //span")   			//type of firm lable
	WebElement typeOfFirmLbl;
	
	@FindBy(xpath="//div[@name=\"Container\"] //select")  	//Type of firm LOVS
	WebElement typeOfFirmLOVS;
	
	@FindBy(xpath="//button[text()='Proceed']")			//Proceed Button
	WebElement nonIndProceed;
	
	@FindBy(xpath="//span[text()='Name of Entity']")		//Name of entity lbl
	WebElement nameOfEntityLbl;
	
	@FindBy(xpath="//span[text()='Name of Entity']/following::input[1]")	//Name of entity txt fld
	WebElement nameOfEntityFld;
	
	@FindBy(xpath="//span[text()='Name of point of contact']")		//Name of POC Lable
	WebElement nameOfPOCLbl;
	
	@FindBy(xpath="//span[text()='Name of point of contact']/following::input[1]")		//Name of POC txt fld
	WebElement nameOfPOCFld;
	
	@FindBy(xpath="//span[text()='Mobile No']")		//Mobileno Lbl
	WebElement mobileLbl;
	
	@FindBy(xpath="//span[text()='Mobile No']/following::input[1]")		//Mobile no fld
	WebElement NonIndmobileNoFld;
	
	@FindBy(xpath="//span[text()='Gender of point of contact']")			//Gender lable
	WebElement genderLbl;
	
	@FindBy(xpath="//span[text()='Gender of point of contact']/following::select")		//Gender LOVS
	WebElement genderLovs;
	
	
	public void selfEmp_ETBNTB_Check(String sheetName)throws Exception
	{
		
			Thread.sleep(1000);
			log.info("Self Employed Non Individual Applicant selected.. ");
			CommonMethods.selectByText(typeOfFirmLOVS,sheetName,"Type of Firm",1);
			//log.info(CommonMethods.getElementText(typeOfFirmLbl)+" : "+ExcelOperation.getCellData(sheetName,"Type of Firm",1) +" Get selected");
			CommonMethods.highLight(nonIndProceed);
			CommonMethods.Click(nonIndProceed);
			
			Thread.sleep(2000);
			CommonMethods.input(nameOfEntityFld, sheetName, "Name of Entity", 1);
			CommonMethods.input(nameOfPOCFld, sheetName, "Last Name", 1);
			CommonMethods.input(NonIndmobileNoFld, sheetName, "Mobile No", 1);
			CommonMethods.selectByText(genderLovs, sheetName, "Gender", 1);
		
			String ETB_NTBType= ExcelOperation.getCellData(sheetName, "ETB/NTB", 1);
			if(ETB_NTBType.equalsIgnoreCase("NTB"))
			{
				log.info("Applicant is NTB type, so select DOI and Proceed.");
				CommonMethods.Click(DOB_DOIBtn);
				selectDate(sheetName);
				
			} //end of if
			else if(ETB_NTBType.equalsIgnoreCase("ETB"))
			{
				//CRN 
				log.info("Applicant is ETB type, so select CRN and Proceed.");
				CommonMethods.Click(CRNButton);
				CommonMethods.input(CRNFld,sheetName, "CRN", 1);
			}
			
			CommonMethods.Click(proceedBtn);
			
	}
/*****************************SELF EMP Non Individual ETB/NTB CHECK******************************************/

	
/******************************OTP Verfication***************************************************/		
			@FindBy(xpath="//div[@id='topnavdiv'] //a[@href='/sng7/app/CRMNextObject/Home/Lead'][1]")  //Leads option from object pannel
			private WebElement Leads;
		
			@FindBy(xpath="//a[@data-autoid='APP_SEARCH_ITEM_ICON']")      //Lead srch icon
			private WebElement srchLeadIcon;
			
			@FindBy(xpath="//input[@name='LE_MOBILE']")					//Mobile no field
			private WebElement MobileNoSrch;
			
			@FindBy(xpath="//a[@data-autoid='Search']")					//Search btn
			private WebElement srchBtn;
			
			@FindBy(xpath="//div[@class='react-grid-Cell'][1] //a")		//Searched Lead IDs for Mobile No
			private List<WebElement> searchedLeadIDs; 
			
			@FindBy(xpath="//label[@title='Lead ID']")					//Lead ID label
			private WebElement leadIdLbl;
			
			@FindBy(xpath="//span[@data-autoid='LE_NUMBER_ctrl']")    	//Lead ID 
			private WebElement LeadId;
			
			@FindBy(xpath="//div[@data-autoid='LE_STATUSCODE']/label")   //Lead Status code label
			private WebElement leadStatusCodeLbl;
			
			@FindBy(xpath="//span[@data-autoid='LE_STATUSCODE_ctrl']")   //Lead Status code value
			private WebElement leadStatusCodeVal;
			
			@FindBy(xpath="//label[contains(text(),'Last Modified On')]")   //Lead Last Modified on label
			private WebElement leadLastModifyLbl;
			
			@FindBy(xpath="//span[@data-autoid='Band_lastmodifiedon_ctrl']")   //Lead Last Modified on value
			private WebElement leadLastModifyVal;
		
			@FindBy(xpath="//a[@title='Toggle to Detail View']")				//Toggle to Detail View
			private WebElement detailView;
			
			@FindBy(xpath="//span[@title='HL Sales App Fields']")				//HL Sales App Fields Tab
			private WebElement HLSalesAppFields;
	
			@FindBy(xpath="//label[@data-autoid='sec_5_4']")					//Others section
			private WebElement othersSection;
			
			@FindBy(xpath="//span[@data-autoid='cust_4002_ctrl']")				//HLSA SMS body
			private WebElement otpSMSBody;
			
			@FindBy(xpath="//div[@class='notification-message']")	
			private WebElement otpNotificationMSG;
			
			@FindBy(xpath="//span[@class='notification-dismiss']")
			private WebElement closeNotification;
			
			@FindBy(xpath="//div[@name='Container'] //input")				//OTP txt box
			private WebElement OTPTxtbox;
			
			@FindBy(xpath="//div[@name='SubmitBtnContainer'] //button")				//OTP Submit button
			private WebElement otpSubmit;
			
			@FindBy(xpath="//div[@name='Container 1']")					//OTP waitingscreen msg
			private WebElement otpWaitingmsg;
			
		//Form list of leads sort leads	
			@FindBy(xpath="//div[contains(text(),'Lead Id')]")			//Sort leads in Descending orde
			private WebElement sortLeads;
			
			@FindBy(xpath="//a[@data-autoid='LeadID_0']")				//1st lead id from sorted list
			private WebElement FirstLeadId;
			
			@FindBy(xpath="//div[@class=\"crm-grid-row relative\"]/div/div[1] //a")
			private List<WebElement> LeadIDList;
			
			@FindBy(xpath="//span[contains(text(),'Search')]")
			private WebElement srchByFilterBtn;
			
			
			public  void LeadSearch(String sheetName)throws Exception
			{
				
					CommonMethods.switchToWindowByTitle("CRMnext - Smart.Easy.Complete");
					Thread.sleep(1000);
				
				//Search lead with mobile no
					CommonMethods.Click(Leads);
					CommonMethods.Click(srchLeadIcon);
					CommonMethods.input(MobileNoSrch, sheetName,"Mobile No",1);
					CommonMethods.Click(srchBtn);
					Thread.sleep(3000);
				
					try {
				//Click on recently created lead from the list of leads with same mobile no
						CommonMethods.mouseClick(sortLeads);
						Thread.sleep(2000);
						CommonMethods.mouseClick(sortLeads);
						Thread.sleep(2000);
						//CommonMethods.mouseClick(sortLeads);
						//Thread.sleep(2000);
						
						CommonMethods.highLight(FirstLeadId);
						CommonMethods.Click(FirstLeadId);
					}catch(Exception e) {}
					
			//Open details View of Recently Created Lead		
//					try 
//					{
//						ArrayList<Integer> arrayList = new ArrayList<>();
//					//To iterate Lead ID list to find largest Lead ID	
//						for(WebElement ele: LeadIDList)
//						{
//							String LeadId= CommonMethods.getElementText(ele);
//							int leadId = Integer.parseInt(LeadId);
//							arrayList.add(leadId);
//						}
//						
//						//document.querySelector('.react-grid-Canvas').scrollBy(0,150)
//
//						log.info("List of Lead IDs = " +arrayList);
//						
//						
//						Collections.sort(arrayList);
//						Integer recentLeadId = Collections.max(arrayList);
//						log.info("Recent Lead ID  = "+recentLeadId );
//						
//						
//					//again iterate Lead ID list to click on Recently created Lead	
//						String LeadId = recentLeadId.toString();
//						for(WebElement ele : LeadIDList)
//						{
//							if(ele.getText().equalsIgnoreCase(LeadId))
//							{
//								CommonMethods.Click(ele);
//								log.info("Click on recently created Lead ID ="+ele.getText());;
//								break;
//							}
//						}
//						
//					} catch (Exception e) {log.error("Exception while seaching recent Lead ="+e.getMessage() );}
//					
					Thread.sleep(1000);
					ScreenShot.takeSnapShot("LeadCardView", "Pass");
			}
			
	public void retriveLinkFromSMS(WebElement SMS, WebElement LeadTab, String sheetName) throws Exception
	{
		
			LeadSearch(sheetName);
			
			//write data to excel
			try {
					String mobileNo=ExcelOperation.getCellData(sheetName,"Mobile No",1);
					log.info("Writing lead details excel "+sheetName);
					CommonMethods.ExWait(leadStatusCodeLbl);
					ExcelOperation.setCellData(sheetName, "Lead Mobile No.", 1, mobileNo);
				  	ExcelOperation.setCellData(sheetName, "Lead ID", 1, CommonMethods.getElementText(LeadId));
				  	ExcelOperation.setCellData(sheetName, "Lead Status", 1, CommonMethods.getElementText(leadStatusCodeVal));
				  	ExcelOperation.setCellData(sheetName, "Last Modified On", 1, CommonMethods.getElementText(leadLastModifyVal));
				} catch (Exception e) {
					log.error("Excel writing exception due to "+e.getMessage());
				}

			//To get OTP verification URL from lead details page 
				try {
					CommonMethods.Click(detailView);
				}catch(Exception e) {}
				Thread.sleep(1000);
				ScreenShot.takeSnapShot("LeadDetailView", "Pass");
				CommonMethods.Click(LeadTab);
				CommonMethods.scrollByVisibilityofElement(othersSection);
				String SMSbody=CommonMethods.getElementText(SMS);
				log.info("SMS = "+SMSbody);
				String link=SMSbody.split("link")[1].trim().split(" ")[0];
				log.info("OTP/Consent Verification link : "+link);

			//open OTP verification link on new tab
				//driver.switchTo().newWindow(WindowType.TAB);
				//driver.get(link);
				driver.navigate().to(link);
		
	}
	@FindBy(xpath="//div[@name=\"Container\"] //span")			//Screen hdr
	private WebElement Hdr;	
	
	@FindBy(xpath="//span[@data-autoid='cust_4330_ctrl']")						//OTP SMS
	private WebElement OtpSMS;
	
	public void OTPVerification(String sheetName, String appType)throws Exception
	{
		
			Thread.sleep(2000);
			CommonMethods.ExWait(otpWaitingmsg);
			assertEquals(CommonMethods.getElementText(otpWaitingmsg) , "Please wait while the OTP is being verified");
			
			ScreenShot.takeSnapShot("OTPWaitingScreen", "Pass");
			log.info("OTP Verification started");
			Thread.sleep(1000);

			if(appType.equalsIgnoreCase("MainApplicant"))
			{
				retriveLinkFromSMS(otpSMSBody, HLSalesAppFields, sheetName);
			}
			else if(appType.equalsIgnoreCase("CoApplicant"))
			{
				HLDAPEndJourney h3 = new HLDAPEndJourney(driver);
				h3.retriveCoAppOTPSMS(OtpSMS,sheetName);
			}
			
			Thread.sleep(2000);
			CommonMethods.waitForURL("OTP");
			log.info("OTP Notification :"+CommonMethods.getElementText(otpNotificationMSG));
				CommonMethods.Click(closeNotification);
				Thread.sleep(1000);
				ScreenShot.takeSnapShot("OTPVerification","Pass");
				CommonMethods.Click(OTPTxtbox);
				OTPTxtbox.sendKeys("123456");
				CommonMethods.Click(otpSubmit);
				Thread.sleep(1000);
				driver.navigate().back();
				Thread.sleep(1000);
				CommonMethods.switchToWindowByTitle("Customer Digital Journey");
				Thread.sleep(1000);
				try {
					log.info(CommonMethods.getElementText(Hdr));
				}catch(Exception e) {}
	}//OTP Verification 
/******************************END OTP Verification***************************************************/		

	
/*******************************Resident Status Screen 16/05/2022****************************************/
	
	@FindBy(xpath="//span[contains(text(),'Select')]")   //Resident Status screen header
	private WebElement residentStatusHeader;
	 //div[@name='App_Container']/span
	
	@FindBy(xpath="//div[@id='missing_668']/div")   //Select resident status from both options
	private List<WebElement> ResidentTypeList;
	
	@FindBy(xpath="//div[@name='ResidentButtonContainer']/button")	//Indian Button
	private WebElement indianBtn;
	
	@FindBy(xpath="//div[@name='NRIcontain']/button")	//NRI Button
	private WebElement NRIBtn;
	
	@FindBy(xpath="//div[@name='ContainerResiCountry']/div/div[1]")		//India Country label
	private WebElement countryLbl;
	
	@FindBy(xpath="//div[@name='ContainerResiCountry']/div/div[2]")		//india country value
	private WebElement countryFld;
	
	@FindBy(xpath="//div[contains(text(),'Resident City')]")		//City Label
	private WebElement cityLabel;
	
	@FindBy(xpath="//div[@name='ContainercityResiInd'] //input")  //City field
	private WebElement cityFld;
	
	@FindBy(xpath="//div[@class='Select-menu']/div/div/div")			//City auto suggestlist
	private List<WebElement> cityList;
	
	@FindBy(xpath="//div[contains(text(),'Resident Pincode')]")		//Pincode Label
	private WebElement pincodeLbl;
	
	@FindBy(xpath="//div[@name='ContainerPinResiInd'] //input")		//Pincode field
	private WebElement pincodeFld;
	
	@FindBy(xpath="//button[text()='Proceed']")	//Proceed Button
	private WebElement IndianProceedBtn;	
	
	//NRI
		@FindBy(xpath="//div[@name='ContainercountryNRI'] //div[@class='float-labelAuto']")
		private WebElement NRICountryLbl;
		
		@FindBy(xpath="//div[@name='ContainercountryNRI'] //input")
		private WebElement NRICountryFld;
		
		@FindBy(xpath="//div[@name='ContainerNRICity']/div/div[1]")
		private WebElement NRICitylbl;
		
		@FindBy(xpath="//div[@name='ContainerNRICity'] //input")
		private WebElement NRICityFld;
		
		@FindBy(xpath="//button[text()='Proceed']")  //NRI Proceed
		private WebElement NRIProceedBtn;
		
	
	public void ResidentStatusSelection(String sheetName)throws Exception
	{
				log.info(CommonMethods.getElementText(residentStatusHeader));
				String residentTypeFromExel = ExcelOperation.getCellData(sheetName, "ResidentType", 1);
						
				if(residentTypeFromExel.equalsIgnoreCase("Indian"))
				{
					CommonMethods.Click(indianBtn);
					log.info(CommonMethods.getElementText(indianBtn)+" get selected");
					
					Thread.sleep(2000);
					//Get value of country
					log.info(CommonMethods.getElementText(countryLbl)+" = "+CommonMethods.getElementValue(countryFld));
					
//					try {
//					//Select City
//						CommonMethods.input(cityFld, sheetName, "City", 1);
//						CommonMethods.ExWaitsForWebelements(cityList);
//						for(WebElement ele : cityList)
//						{
//							String cityNm = CommonMethods.getElementText(ele);
//							if(cityNm.equalsIgnoreCase(ExcelOperation.getCellData(sheetName, "City", 1)))
//							{
//								CommonMethods.Click(ele);
//								log.info(CommonMethods.getElementText(cityLabel)+" = "+CommonMethods.getElementValue(cityFld));
//								break;
//							}
//						}
//					}catch(Exception e) {}	
				
					try {
						//Enter Pincode
						CommonMethods.input(pincodeFld, sheetName, "Pincode", 1);
						log.info(CommonMethods.getElementText(pincodeLbl)+" = "+CommonMethods.getElementValue(pincodeFld));
						
					} catch (Exception e) {}
					
					ScreenShot.takeSnapShot("IndianResidentScreen", "Pass");
					CommonMethods.highLight(IndianProceedBtn);
					CommonMethods.Click(IndianProceedBtn);
				}
				else if(residentTypeFromExel.equalsIgnoreCase("NRI"))
				{
					CommonMethods.Click(NRIBtn);
					log.info(CommonMethods.getElementText(NRIBtn)+" get selected");
					
				//NRI Country	
					try {
						CommonMethods.input(NRICountryFld, sheetName, "NRI Country", 1);
						//log.info(CommonMethods.getElementText(NRICountryLbl)+" = "+CommonMethods.getElementValue(NRICountryFld));
						CommonMethods.ExWaitsForWebelements(cityList);
						for(WebElement ele : cityList)
						{
							String cityNm = CommonMethods.getElementText(ele);
							if(cityNm.equalsIgnoreCase(ExcelOperation.getCellData(sheetName, "NRI Country", 1)))
							{
								CommonMethods.Click(ele);
								Thread.sleep(1000);
								log.info(CommonMethods.getElementText(cityLabel)+" = "+CommonMethods.getElementValue(cityFld));
								break;
							}
						}
					}catch(Exception e) {}
				//NRI CITY	
					CommonMethods.input(NRICityFld, sheetName, "NRI City", 1);
					//log.info(CommonMethods.getElementText(NRICitylbl)+" = "+CommonMethods.getElementValue(NRICityFld));
					ScreenShot.takeSnapShot("NRIResidentScreen", "Pass");
					
					CommonMethods.highLight(NRIProceedBtn);
					CommonMethods.Click(NRIProceedBtn);
				}
		
	}
	
	public void residentStatusForHLOD(String sheetName) throws Exception
	{
		log.info(CommonMethods.getElementText(residentStatusHeader));
		//Get value of country
		log.info(CommonMethods.getElementText(countryLbl)+" = "+CommonMethods.getElementValue(countryFld));
	
		try {
			//Select City
				CommonMethods.input(cityFld, sheetName, "City", 1);
				CommonMethods.ExWaitsForWebelements(cityList);
				for(WebElement ele : cityList)
				{
					String cityNm = CommonMethods.getElementText(ele);
					if(cityNm.equalsIgnoreCase(ExcelOperation.getCellData(sheetName, "City", 1)))
					{
						CommonMethods.Click(ele);
						log.info(CommonMethods.getElementText(cityLabel)+" = "+CommonMethods.getElementValue(cityFld));
						break;
					}
				}
			}catch(Exception e) {}	
			
		try {
				//Enter Pincode
				
				CommonMethods.input(pincodeFld, sheetName, "Pincode", 1);
				Thread.sleep(1000);
				String pincodeval = CommonMethods.getElementValue(pincodeFld);
				if(pincodeval.isEmpty())
				{
					CommonMethods.input(pincodeFld, sheetName, "Pincode", 1);
				}
				
//				CommonMethods.ExWaitsForWebelements(cityList);
//				for(WebElement ele : cityList)
//				{
//					String pincode = CommonMethods.getElementText(ele);
//					if(pincode.equalsIgnoreCase(ExcelOperation.getCellData(sheetName, "City", 1)))
//					{
//						CommonMethods.Click(ele);
//						log.info(CommonMethods.getElementText(pincodeLbl)+" = "+CommonMethods.getElementValue(pincodeFld));
//						break;
//					}
//				}
				
				
			} catch (Exception e) {}
		
			ScreenShot.takeSnapShot("ResidentScreenForOD", "Pass");
			CommonMethods.highLight(IndianProceedBtn);
			CommonMethods.Click(IndianProceedBtn);
	}
	
	
	
/*******************Resident Status Screen*************************/
	
/*******************NRI Power of Attorney Screen1*************************/
	
@FindBy(xpath="//span[text()='NRI Applicants Reference Details']")			//screen header
private WebElement poaScreenHdr;

@FindBy(xpath="//div[contains(text(),'Reference Full Name')]")				//Ref name Lbl
private WebElement refNameLbl;

@FindBy(xpath="//div[contains(text(),'Reference Full Name')]/following::input[1]")		//Ref Name Fld
private WebElement refNameFld;

@FindBy(xpath="//div[contains(text(),'Relationship with Applicant')]")			//relationship with app Lbl
private WebElement relWithAppLbl;

@FindBy(xpath="//div[contains(text(),'Relationship with Applicant')]/following::select[1]")		//Relationship with app LOVS
private WebElement relWithAppLOVs;

@FindBy(xpath="//div[contains(text(),'Gender')]")			//Gender lbl
private WebElement NRIgenderLbl;

@FindBy(xpath="//div[contains(text(),'Gender')]/following::select")			//Gender LOV 
private WebElement NRIGenderLovs;

@FindBy(xpath="//div[contains(text(),'Mobile No.')]")     //Mobile No Lbl
private WebElement NRIMobileNoLbl;

@FindBy(xpath="//div[contains(text(),'Mobile No.')]/following::input[1]")			//Mobile No fld;
private WebElement NRIMobileFld;


	public void NRIRefScreen1(String sheetName)throws Exception
	{
		
			
			log.info(CommonMethods.getElementText(poaScreenHdr));
			Thread.sleep(1000);
			CommonMethods.input(refNameFld,sheetName,"Reference Name1", 1);
			CommonMethods.selectByText(relWithAppLOVs, sheetName, "RelationShipWithApplicant", 1);
			CommonMethods.selectByText(NRIGenderLovs, sheetName, "Gender", 1);
			CommonMethods.input(NRIMobileFld, sheetName, "Reference Mobile1", 1);
			
			ScreenShot.takeSnapShot("NRIRefDetails", "Pass");
			CommonMethods.highLight(proceedBtn);
			CommonMethods.Click(proceedBtn);
		
	}
	
/*******************NRI Power of Attorney Screen1*************************/
	
/*******************NRI Power of Attorney Screen2 Communication details*************************/
	@FindBy(xpath="//span[text()='Reference Communication Details']")   //hdr
	private WebElement NRICommDetailsHdr;
	
	@FindBy(xpath="//div[contains(text(),'Address Line 1')]/following::input[1]")		//Addressline 1
	private WebElement NRIAdd1Fld;

	@FindBy(xpath="//div[contains(text(),'Address Line 2')]/following::input[1]")		//Addressline 2
	private WebElement NRIAdd2Fld;

	@FindBy(xpath="//div[@name='ContainerCity'] //input")			//City fld
	private WebElement NRIRefCityFld;
	
	@FindBy(xpath="//div[@name='Pin_Container'] //input")			//pincode
	private WebElement NRIRefPincode;
	
	public void NRIRefScreen2(String sheetName)throws Exception
	{
		
			
			log.info(CommonMethods.getElementText(NRICommDetailsHdr));
			CommonMethods.input(NRIAdd1Fld, sheetName, "Address line 1", 1);
			CommonMethods.input(NRIAdd2Fld, sheetName, "Address line 2", 1);
			
		//City	
			try {
			CommonMethods.input(NRIRefCityFld, sheetName, "City", 1);
			Thread.sleep(2000);
			CommonMethods.ExWaitsForWebelements(cityList);
			for(WebElement ele : cityList)
			{
				String cityNm = CommonMethods.getElementText(ele);
				if(cityNm.equalsIgnoreCase(ExcelOperation.getCellData(sheetName, "City", 1)))
				{
					CommonMethods.Click(ele);
					Thread.sleep(1000);
					log.info(CommonMethods.getElementText(cityLabel)+" = "+CommonMethods.getElementValue(cityFld));
					break;
				}
			}
			}catch(Exception e) {}
			CommonMethods.input(NRIRefPincode, sheetName, "Pincode", 1);
			
			ScreenShot.takeSnapShot("NRIRefCommDetails", "Pass");
			CommonMethods.highLight(proceedBtn);
			CommonMethods.Click(proceedBtn);
		
	}
	
	
/*******************NRI Power of Attorney Screen2*************************/


	
/*******************Resume Journey*************************/

	@FindBy(xpath="//div[@data-autoid='mash_1'] //button[@id='btnClickFinger']")		//Resume button
	private WebElement ResumeLinkBtn;
	
	public void resumeJourney(String sheetName)throws Exception
	{
		
			log.info("Resume Journey Intiated");
			//Search lead with mobile no
			LeadSearch(sheetName);
			/*
			 * CommonMethods.Click(Leads); CommonMethods.Click(srchLeadIcon);
			 * CommonMethods.input(MobileNoSrch, sheetName,"Mobile No",2);
			 * CommonMethods.Click(srchBtn);
			 */
			Thread.sleep(2000);
			try {
				CommonMethods.Click(detailView);
			}catch(Exception e) {}
			Thread.sleep(2000);
			CommonMethods.highLight(HLSalesAppFields);
			CommonMethods.Click(HLSalesAppFields);
			Thread.sleep(2000);

			CommonMethods.scrollByVisibilityofElement(ResumeLinkBtn);
			CommonMethods.Click(ResumeLinkBtn);
			
	}
	
	public void resumeWithLink(String sheetName) throws Exception
	{
		Robot r = new Robot();
		
			String leadId= ExcelOperation.getCellData(sheetName, "Lead ID", 1);
			Thread.sleep(1000);
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_T);
			r.keyRelease(KeyEvent.VK_CONTROL); 
			r.keyRelease(KeyEvent.VK_T);
			Thread.sleep(1000);

			CommonMethods.switchToWindow();
			Thread.sleep(1000);
			driver.get("https://kmb.crmnext.com/sng7/vividkotak/vividflow/run/hl_salaried_process?LeadId="+leadId);
		
	}
/*******************Resume Journey*************************/


}
