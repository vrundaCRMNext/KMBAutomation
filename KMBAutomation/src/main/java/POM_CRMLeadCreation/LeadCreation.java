package POM_CRMLeadCreation;

import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import CommonUtility.ExcelOperation;
import CommonUtility.CommonMethods;
import CommonUtility.ScreenShot;
import CommonUtility.SetUp;
import TestSripts.runTest;


public class LeadCreation extends SetUp
{
	public static Logger log =LogManager.getLogger(LeadCreation.class.getName());
	public WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(30));
	public int row;
	public LeadCreation(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/*******************For NEW LEAD ***********************/
	
		//Homepage
		
		@FindBy(xpath="//body/div[@id='main']/div[1]/div[1]/ul[1]/li[3]/div[1]/div[1]/a[1]")  //Sales
		//@FindBy(xpath="//div[@id='topnavdiv']//a[text()='Leads']")
		private WebElement sales;
		public WebElement sales()
		{
			return sales;
		}
		
		@FindBy(xpath="//span[contains(text(),'Leads')]")   //Clicking on Leads
		private WebElement Leads;
		public WebElement Leads() 
		{
			return Leads;
		}

		@FindBy(xpath="//div[@class='page-title__link']//*[text()='New']")    //New button 
		private WebElement New;
		public WebElement New()
		{
			return New;
		}
		
		@FindBy(xpath="//span[contains(text(),'Home Loan')]")
		private WebElement HomeLoan;
		public WebElement HomeLoan()
		{
			return HomeLoan;
		}
		
		//To change User Role
		@FindBy(xpath= "//header/div[1]/div[3]/div[3]/a[1]/img[1]")   //Profile button
		private WebElement ProfileBtn;
		public WebElement ProfileBtn()
		{
			return ProfileBtn;
		}
		
		@FindBy(xpath= "//div[@data-autoid='header_loginroles']/div[1]/div[1]/div[1]/div[1]/div[1]/select[1]")   //User Role LOVs
		private WebElement UserRoleLOVs;
		public WebElement UserRoleLOVs()
		{
			return UserRoleLOVs;
		}
	
	public void NewLead(String sheetName) throws Exception
	{
		
		try {
			Thread.sleep(1000);
			CommonMethods.ExWait(ProfileBtn());
			CommonMethods.highLight(ProfileBtn());
			ProfileBtn().click();
			log.info("Clicked on Profile icon");
			Thread.sleep(1000);
			CommonMethods.highLight(UserRoleLOVs());
			Thread.sleep(1000);
			CommonMethods.selectByText(UserRoleLOVs(),sheetName,"UserRole", 1);

			Thread.sleep(1000);
			
		}catch(Exception e) {System.out.println("Profile exception:"+e.getMessage());}
		
	
		CommonMethods.ExWait(sales);
		CommonMethods.highLight(sales);
		// GenericUtils.mouseHover(login.sales());
		sales.click();
		ScreenShot.Ashot("sales","Pass");
		 
		/*
		 * try { CommonMethods.ExWait(Leads); CommonMethods.highLight(Leads());
		 * Leads().click(); ScreenShot.takeSnapShot("Leads"); }catch(Exception e){}
		 */
		
		CommonMethods.ExWait(New);
		CommonMethods.highLight(New);
		CommonMethods.mouseHover(New);
		Thread.sleep(2000);
		ScreenShot.Ashot("NewLead","Pass");

		CommonMethods.ExWait(HomeLoan);
		CommonMethods.highLight(HomeLoan);
		HomeLoan.click();
		Thread.sleep(2000);

		 
		 //ScreenShot.takeSnapShot(driver,"C:\\Users\\Vrunda Vibhute\\eclipse-workspace\\KotakHL_Lead\\Screenshots\\newLead.png");
		 //ScreenShot.Ashot(driver,"C:\\Users\\Vrunda Vibhute\\eclipse-workspace\\KotakHL_Lead\\Screenshots\\ashot1.jpg");
	}
	
	/*******************************END NEW LEAD ************************************/
	
	
	//webelements locators for LeadCreation Page
	
	@FindBy(xpath="//label[@title='Lead Source']")     //Lead Source Label
	private WebElement leadSourceLbl;
	public WebElement leadSourceLbl()
	{
		return leadSourceLbl;
	}
	
	@FindBy(xpath="//select[@name='LE_LEADSOURCE']")	//Lead Source Field LOVs
	private WebElement leadSrcLOV;
	public WebElement leadSrcLOV()
	{
		return leadSrcLOV;
	}
	
	@FindBy(xpath="//div[@data-autoid='LE_LEADSOURCE']//span[@class='error-message pt1 db']")   //Lead Source Error message
	private WebElement leadSrcError;
	public WebElement leadSrcError()
	{
		return leadSrcError;
	}
	
	
	@FindBy(xpath="//label[@title='Sub-Source']")   //Sub Source Label
	private WebElement subSrcLbl;
	public WebElement subSrcLbl()
	{
		return subSrcLbl;
	}

	@FindBy(xpath="//select[@name='cust_113']")    //Sub Source LOVS
	private WebElement subSrcLOV;
	public WebElement subSrcLov()
	{
		return subSrcLOV;
	}
	
	@FindBy(xpath="//label[@title='Lead Priority']")   //Lead Priority label
	private WebElement leadPriorityLbl;
	public WebElement leadPriorityLbl()
	{
		return leadPriorityLbl;
	}

	@FindBy(xpath="//select[@name='LE_LEADRATING']")     //Lead Priority LOVS
	private WebElement leadPriorityLOV;
	public WebElement leadPriorityLOV()
	{
		return leadPriorityLOV;
	}

	@FindBy(xpath="//div[@data-autoid='cust_103']/label")   //Application Type Label
	private WebElement appTypeLbl;
	public WebElement appTypeLbl()
	{
		return appTypeLbl;
	}
	
	@FindBy(xpath="//select[@name='cust_103']")     //Application Type LOV
	private WebElement applicationType;
	public WebElement applcationType()
	{
		return applicationType;
	}
	
	@FindBy(xpath="//div[@data-autoid='LE_PRODUCT']/label")   //Product Type Label
	private WebElement prodTypeLbl;
	public WebElement prodTypeLbl()
	{
		return prodTypeLbl;
	}
		
	@FindBy(xpath="//div[@data-autoid='LE_PRODUCT']//select[@name='LE_PRODUCT']")   //Product Type LOVs
	private WebElement prodTypeLov;
	public WebElement prodTypeLov()
	{
		return prodTypeLov;
	}

	@FindBy(xpath="//div[@data-autoid='LE_NAME']/label")   //Applicant Name Label
	private WebElement appNameLbl;
	public WebElement appNameLbl()
	{
			return appNameLbl;
	}
	
	@FindBy(xpath="//select[@data-autoid='SALUTATION_ctrl']")  //Applicant salutation lovs
	private WebElement salutation;
	public WebElement salutation()
	{
		return salutation;
	}
	
	@FindBy(xpath="//input[@data-autoid='FIRSTNAME_ctrl']")   //FirstName txt field
	private WebElement firstName;
	public WebElement firstName()
	{
		return firstName;
	}
	
	@FindBy(xpath="//input[@data-autoid='MIDDLENAME_ctrl']")   //MiddleName txt field
	private WebElement middleName;
	public WebElement middleName()
	{
		return middleName;
	}
	
	@FindBy(xpath="//input[@data-autoid='LASTNAME_ctrl']")   //LastName txt field
	private WebElement lastName;
	public WebElement lastName()
	{
		return lastName;
	}
	
	@FindBy(xpath="//div[@data-autoid='LASTNAME']//span")  //LastName Error message
	private WebElement lastNameError;
	public WebElement lastNameError()
	{
		return lastNameError;
	}
	
	@FindBy(xpath="//div[@data-autoid='LE_MOBILE']/label")  //Mobile Number label
	private WebElement mobileNoLbl;
	public WebElement mobileNoLbl()
	{
		return mobileNoLbl;
	}
	
	@FindBy(xpath="//input[@data-autoid='LE_MOBILE_ctrl']")  //Mobile Number field
	private WebElement mobileNo;
	public WebElement mobileNo()
	{
		return mobileNo;
	}
	
	@FindBy(xpath="//div[@data-autoid='LE_MOBILE']//span")  //Mobile Number error message
	private WebElement mobileNoError;
	public WebElement mobileNoError()
	{
		return mobileNoError;
	}
	
	
	@FindBy(xpath="//div[@data-autoid='LE_EMAIL']/label")  //Email label
	private WebElement eMailLbl;
	public WebElement eMailLbl()
	{
		return eMailLbl;
	}

	@FindBy(xpath="//input[@data-autoid='LE_EMAIL_ctrl']")  //Email Txt field
	private WebElement eMail;
	public WebElement eMail()
	{
		return eMail;
	}
	
	@FindBy(xpath="//div[@data-autoid='LE_EMAIL']//span")  //Email Error message
	private WebElement eMailError;
	public WebElement eMailError()
	{
		return eMailError;
	}
	
	@FindBy(xpath="//div[@data-autoid='LE_DATEOFBIRTH']/label")  //DOB label
	private WebElement DOBLbl;
	public WebElement DOBLbl()
	{
		return DOBLbl;
	}
	
	@FindBy(xpath="//input[@data-autoid='LE_DATEOFBIRTH_ctrl']")  //DOB field
	private WebElement DOBField;
	public WebElement DOBField()
	{
		return DOBField;
	}
	
	@FindBy(xpath="//div[@data-autoid='LE_DATEOFBIRTH']//span[2]")  //DOB Error message
	private WebElement DOBError;
	public WebElement DOBError()
	{
		return DOBError;
	}
	
	@FindBy(xpath="//input[@data-autoid='cust_314_ctrl']")  //Gross Annual Income field
	private WebElement AnnualIncome;
	public WebElement AnnualIncome()
	{
		return AnnualIncome;
	}
	
	@FindBy(xpath="//input[@data-autoid='cust_314_ctrl']")  //Gross Annual Income lable
	private WebElement AnnualIncomeLbl;
	public WebElement AnnualIncomeLbl()
	{
		return AnnualIncomeLbl;
	}
	
	
	@FindBy(xpath="//div[@data-autoid='cust_120']/label")  //Property Identified label
	private WebElement PropIdentifiedLbl;
	public WebElement PropIdentifiedLbl()
	{
		return PropIdentifiedLbl;
	}
	
	@FindBy(xpath="//select[@data-autoid='cust_120_ctrl']")  //Property Identified LOVs
	private WebElement PropIdentifiedFld;
	public WebElement PropIdentifiedFld()
	{
		return PropIdentifiedFld;
	}
	
	@FindBy(xpath="//div[@data-autoid='LE_AMOUNT']/label")  //Loan amount label
	private WebElement loanAmtLbl;
	public WebElement loanAmtLbl()
	{
		return loanAmtLbl;
	}
	
	@FindBy(xpath="//input[@data-autoid='LE_AMOUNT_ctrl']")  //Loan amount Field
	private WebElement loanAmtFld;
	public WebElement loanAmtFld()
	{
		return loanAmtFld;
	}
	
	@FindBy(xpath="//div[@data-autoid='cust_73']/label")  //Loan Tenure label
	private WebElement loanTenureLbl;
	public WebElement loanTenureLbl()
	{
		return loanTenureLbl;
	}
	
	@FindBy(xpath="//input[@data-autoid='cust_73_ctrl']")  //Loan Tenure field
	private WebElement loanTenureFld;
	public WebElement loanTenureFld()
	{
		return loanTenureFld;
	}
	
	
	@FindBy(xpath="//a[@data-autoid='FlowNext']")  // Save & Proceed Button
	private WebElement saveProceedBtn;
	public WebElement saveProceedBtn()
	{
		return saveProceedBtn;
	}
		
	@FindBy(xpath="//div[@class='mobPopupBox db']/div")  // Save & Proceed Button, error pop up
	private WebElement saveProcError;
	public WebElement saveProcError()
	{
		return saveProcError;
	}
	
	/***************************NEW LEAD CREATE ***********************************************/
	public void CreateNewLead(String sheetName) throws Exception
	{
		try {
			
		//Source
			CommonMethods.selectByText(leadSrcLOV(),sheetName,"Lead Source",1);
			System.out.println(leadSourceLbl().getText()+"LOVS :"+leadSrcLOV().getText());
			
		//SubSource
			Thread.sleep(2000);
			CommonMethods.selectByText(subSrcLov(),sheetName,"Sub Source",1);
			System.out.println(subSrcLbl().getText()+"LOVs : "+subSrcLov().getText());
			
		//Lead Priority
			CommonMethods.selectByText(leadPriorityLOV(),sheetName,"Lead Priority",1);
			System.out.println(leadPriorityLbl().getText()+"LOVs : "+leadPriorityLOV().getText());
		
		//Applicant Type
			CommonMethods.selectByText(applcationType(), sheetName,"Applicant Type",1);
			System.out.println(appTypeLbl().getText()+ "LOVs : "+applcationType().getText());

			CommonMethods.scrollByVisibilityofElement(prodTypeLov());
			
		//Product Type
			CommonMethods.selectByText(prodTypeLov(),sheetName,"Product Type",1);
			System.out.println(prodTypeLbl().getText()+"LOVs :"+prodTypeLov().getText());
			
			
		//Salutation
			CommonMethods.selectByText(salutation(),sheetName,"Salutation",1);
			System.out.println(appNameLbl().getText()+ "LOVs :"+salutation().getText());
			
		//FirstName
			CommonMethods.input(firstName, sheetName,"First Name",1);
			System.out.println(appNameLbl().getText()+" = "+firstName().getAttribute("value"));
		
		//Middle Name
			//middleName().sendKeys(ExcelOperation.readData(sheetName, 1, 9));
			//System.out.println(appNameLbl().getText()+ "= "+middleName().getAttribute("value"));
			
		//Lastname
			CommonMethods.input(lastName, sheetName,"Last Name",1);
			System.out.println(appNameLbl().getText()+ "= "+lastName().getAttribute("value"));
			
		//Mobile No
			CommonMethods.input(mobileNo, sheetName,"Mobile No", 1);
			System.out.println(mobileNoLbl().getText()+ "= "+mobileNo().getAttribute("value"));
			
			String MobileNoLbl = mobileNoLbl().getText();
			String MobileNo = mobileNo().getAttribute("value");
			
		//EmailID	
			wait.until(ExpectedConditions.visibilityOf(eMail()));
			//eMail().sendKeys(ExcelOperation.readData(sheetName, 1, 12));
			eMail().sendKeys(ExcelOperation.getCellData(sheetName,"Email",1));
			System.out.println(eMailLbl().getText()+ "= "+eMail().getAttribute("value"));
			
		//DOB
			wait.until(ExpectedConditions.visibilityOf(DOBField()));
			//DOBField().sendKeys(ExcelOperation.readData(sheetName, 1, 13));
			DOBField().sendKeys(ExcelOperation.getCellData(sheetName,"DOB",1));
			System.out.println(DOBLbl().getText()+ "= "+DOBField().getAttribute("value"));
		
		//Gross Annual Income
			//wait.until(ExpectedConditions.visibilityOf(AnnualIncome()));
			AnnualIncome().clear();
			//AnnualIncome().sendKeys(ExcelOperation.readData(sheetName, 1, 14));
			AnnualIncome().sendKeys(ExcelOperation.getCellData(sheetName, "Annual Income",1));
			System.out.println(AnnualIncomeLbl().getText() +" = "+AnnualIncome().getAttribute("value"));
			
		//Property Identified
			CommonMethods.selectByText(PropIdentifiedFld(),sheetName,"Property Identified",1);
			System.out.println(PropIdentifiedLbl().getText()+" = "+PropIdentifiedFld().getAttribute("value"));
		
		//Loan Amount
			wait.until(ExpectedConditions.visibilityOf(loanAmtFld()));
			//loanAmtFld().sendKeys(ExcelOperation.readData(sheetName, 1, 16));
			loanAmtFld().sendKeys(ExcelOperation.getCellData(sheetName, "Loan Amount",1));
			System.out.println(loanAmtLbl().getText() +" = "+loanAmtFld().getAttribute("value"));
			
		//Loan Tenure
			Thread.sleep(1000);
			loanTenureFld().clear();
			//loanTenureFld().sendKeys(ExcelOperation.readData(sheetName, 1, 17));
			loanTenureFld().sendKeys(ExcelOperation.getCellData(sheetName, "Loan Tenure",1));
			System.out.println(loanTenureLbl().getText()+" = "+loanTenureFld().getAttribute("value"));
			
			
			ScreenShot.takeSnapShot("Leadcreate","Pass");
		
			//Save and Proceed button
			
			CommonMethods.highLight(saveProceedBtn());
			Thread.sleep(1000);
			saveProceedBtn().click();
			try 
			{	
				//System.out.println("Error clicking on Save & Continue");
				System.out.println("Lead Source error :" +leadSrcError().getText());
				System.out.println("Mobile Number Error :" +mobileNoError().getText());
				System.out.println("Save & Proceed Error pop up : "+saveProcError().getText());
				ScreenShot.takeSnapShot("OnSaveError", "Fail");
			} catch (Exception e) 
			{			}
			Thread.sleep(3000);
			
			  //Write Data into excel 
			try {
				wait.until(ExpectedConditions.visibilityOf(leadStatusCodeLbl()));
			  	row = ExcelOperation.getRowCount("Output");
			  	//System.out.println("No of Rows :" +row);
				Thread.sleep(2000);
				
				ExcelOperation.writeToExcel("Output", row+1, 0, runTest.TestCaseIDName);
			  	ExcelOperation.writeToExcel("Output", row+1, 1, MobileNo);
			  	ExcelOperation.writeToExcel("Output", row+1, 2, leadID().getText());
			  	ExcelOperation.writeToExcel("Output", row+1, 3, leadStatusCodeVal().getText());
			  	ExcelOperation.writeToExcel("Output", row+1, 4, leadLastModifyVal().getText());
				Thread.sleep(2000);
				ScreenShot.takeSnapShot("Leadcreate3","Pass");

			  	String leadStatus = ExcelOperation.getCellData("Output", "Lead Status", row+1);
				System.out.println("Lead status = "+leadStatus);
				if(leadStatus.equalsIgnoreCase("New Lead"))
				{
					ExcelOperation.writeToExcel("Output",row+1, 5, "PASS");
				}
				else
				{
					ExcelOperation.writeToExcel("Output", row+1, 5, "FAIL");
				}
				
			  }
			  catch (Exception e) {}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			//KMBLog.logError("LeadCreation Exception :"+e.getMessage());
			ScreenShot.takeSnapShot("Failure in Create New Lead","Fail");
			}
	}

	/*****************************Follow Up NEW****************************************************/
	
	@FindBy(xpath="//div[@data-autoid='LE_NUMBER']/label")   //Lead ID label
	private WebElement leadIDLbl;
	public WebElement leadIDLbl()
	{
		return leadIDLbl;
	}

	@FindBy(xpath="//span[@data-autoid='LE_NUMBER_ctrl']")   //Lead ID label
	private WebElement leadID;
	public WebElement leadID()
	{
		return leadID;
	}	
	
	@FindBy(xpath="//div[@data-autoid='LE_STATUSCODE']/label")   //Lead Status code label
	private WebElement leadStatusCodeLbl;
	public WebElement leadStatusCodeLbl()
	{
		return leadStatusCodeLbl;
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
	
	@FindBy(xpath="//div[@title='click here to see more actions']")   //Click here to see more actions
	private WebElement LeadMoreAction;
	public WebElement LeadMoreAction()
	{
		return LeadMoreAction;
	}
	
	@FindBy(xpath="//a[@data-autoid='Edit_1']/span[1]")   //Lead Edit button
	private WebElement LeadEdit;
	public WebElement LeadEdit()
	{
		return LeadEdit;
	}
	
	@FindBy(xpath="//div[@id='state_0']")   //Follow-up New button Tab
	private WebElement followUp;
	public WebElement followUp()
	{
		return followUp;
	}
	
	@FindBy(xpath="//div[@data-autoid='cust_402']/label")   //Follow-up date time label
	private WebElement followUpDtTimeLbl;
	public WebElement followUpDtTimeLbl()
	{
		return followUpDtTimeLbl;
	}
	
	@FindBy(xpath="//div[@data-autoid='cust_402']//div[1]//div[1]//div[1]//div[1]//div[1]//input")   //Follow-up date 
	private WebElement followUpDate;
	public WebElement followUpDate()
	{
		return followUpDate;
	}
	
	@FindBy(xpath="//div[@data-autoid='cust_402']//div[1]//div[1]//div[1]//div[1]//div[1]//a[@title='Today Date']")   //Follow-up today date 
	private WebElement followUpTodayDate;
	public WebElement followUpTodayDate()
	{
		return followUpTodayDate;
	}
	
	@FindBy(xpath="//div[@data-autoid='cust_402']//div[1]//div[1]//div[2]//div[1]//div[1]//input")   //Follow-up Time 
	private WebElement followUpTime;
	public WebElement followUpTime()
	{
		return followUpTime;
	}
	
	@FindBy(xpath="//div[@data-autoid='cust_132']/label")   //Follow-up Reason label
	private WebElement followUpReasonLbl;
	public WebElement followUpReasonLbl()
	{
		return followUpReasonLbl;
	}
	
	@FindBy(xpath="//select[@name='cust_132']")   //Follow-up Reason select lovs
	private WebElement followUpReasonLOV;
	public WebElement followUpReasonLOV()
	{
		return followUpReasonLOV;
	}
	
	@FindBy(xpath="//div[@data-autoid='cust_46']/label")   //Follow-up Remark label
	private WebElement followUpRemarkLbl;
	public WebElement followUpRemarkLbl()
	{
		return followUpRemarkLbl;
	}
	
	@FindBy(xpath="//textarea[@data-autoid='cust_46_ctrl']")   //Follow-up Remark text area
	private WebElement followUpRemarkTextFld;
	public WebElement followUpRemarkTextFld()
	{
		return followUpRemarkTextFld;
	}
	
/**********************EDIT LEAD from View **************************/
	
		
	@FindBy(xpath="//select[@data-autoid='QueryCategoryId_ctrl']")   //Category LOVs
	private WebElement ViewCategory;
	public WebElement ViewCategory()
	{
		return ViewCategory;
	}
	
	@FindBy(xpath="//select[@name='QueryViewId' or @data-autoid='QueryViewId_ctrl']")   //View Sub-Category LOVs
	private WebElement ViewSubCat;
	public WebElement ViewSubCat()
	{
		return ViewSubCat;
	}
	
	@FindBy(xpath="//a[@data-autoid='gridHF_View0']")   //Category ViewSrchArrow icon
	private WebElement ViewSrchArrow;
	public WebElement ViewSrchArrow()
	{
		return ViewSrchArrow;
	}

	@FindBy(xpath="//a[@data-autoid='LeadID_0']")   //first lead id to edit
	private WebElement LeadIdToEdit;
	public WebElement LeadIdToEdit()
	{
		return LeadIdToEdit;
	}
	
	//Edit function to perform Edit operation on Lead
	public void Edit(String sheetName) throws Exception
	{
		try 
		{
			row=ExcelOperation.getRowCount("Output");
			CommonMethods.ExWait(sales);
			CommonMethods.highLight(sales);
			wait.until(ExpectedConditions.elementToBeClickable(sales)).click();
			Thread.sleep(1000);
		
			//View Category
			CommonMethods.selectByText(ViewCategory,sheetName,"Product Type",1);
			Thread.sleep(1000);
			
			//View sub Category
			CommonMethods.selectByText(ViewSubCat,"Output","Lead Status",row);
			
			Thread.sleep(1000);
			ViewSrchArrow.click();
		
		try {
			Thread.sleep(1000);
			CommonMethods.ExWait(LeadIdToEdit);
			String ViewLeadID = LeadIdToEdit.getText();
			String ExcelLeadID = ExcelOperation.getCellData("Output", "Lead ID", row);
			
			if(ViewLeadID.equalsIgnoreCase(ExcelLeadID))
			{
				CommonMethods.highLight(LeadIdToEdit);
				LeadIdToEdit.click();
			}
			
			} catch (Exception e) {	}
			
			Thread.sleep(1000);
			CommonMethods.scrollByVisibilityofElement(LeadEdit);
			CommonMethods.highLight(LeadEdit);
			LeadEdit.click();
			Thread.sleep(1000);
		} 
		catch (Exception e) 
		{
			System.out.println("Edit Lead exception:"+e.getMessage());
			//KMBLog.logError("Edit Lead exception:"+e.getMessage());
			ScreenShot.takeSnapShot("Failure in Edit Lead", "Fail");
		}
	}
	/***************************END EDIT ********************************************/
	//Edit Lead to Follow up new appointment
	public void FollowUpNew(String sheetName) throws Exception   
	{
		try 
		{	

			followUp.click();
			Thread.sleep(1000);
			//followUpDate.sendKeys(ReadExcel.readData(sheetName,1 ,18 ));
			followUpTodayDate.click();
			System.out.println(followUpDtTimeLbl.getText()+" = "+followUpDate.getAttribute("value"));
			
			Thread.sleep(1000);
			//followUpTime.sendKeys(ExcelOperation.readData(sheetName, 1, 19));
			followUpTime.clear();
			followUpTime.sendKeys(ExcelOperation.getCellData(sheetName,"Follow up Time",1));
			System.out.println(followUpDtTimeLbl.getText()+" = "+followUpTime.getAttribute("value"));
			
			Thread.sleep(1000);
			CommonMethods.selectByText(followUpReasonLOV,sheetName, "Follow Up Reason", 1);
			
			Thread.sleep(1000);
			//followUpRemarkTextFld.sendKeys(ExcelOperation.readData(sheetName, 1, 21));
			followUpRemarkTextFld.clear();
			followUpRemarkTextFld.sendKeys(ExcelOperation.getCellData(sheetName,"Follow Up Remark",1));
			System.out.println(followUpRemarkLbl.getText()+" = "+followUpRemarkTextFld.getAttribute("value"));
			
			ScreenShot.takeSnapShot("FollowupNewForm","Pass");

			Thread.sleep(2000);
			saveProceedBtn.click();      //Save Button
			try 
			{
				Thread.sleep(2000);
				System.out.println("Warning : "+driver.findElement(By.xpath("//div[@class='warning__message']")).getText());
				driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			Thread.sleep(2000);
			//Write Data into excel 
			try {
				wait.until(ExpectedConditions.visibilityOf(leadStatusCodeLbl()));
			  
			  	//ExcelOperation.writeToExcel("Output", row+1, 2, leadStatusCodeVal().getText());
			  	ExcelOperation.writeToExcel("Output", row, 3,"Follow-Up New" );
			  	ExcelOperation.writeToExcel("Output", row, 4, leadLastModifyVal().getText());
			  	
			  	String leadStatus = ExcelOperation.getCellData("Output", "Lead Status", row);
				System.out.println("Lead status = "+leadStatus);
				if(leadStatus.equalsIgnoreCase("Follow-Up New"))
				{
					ExcelOperation.writeToExcel("Output",row, 5, "PASS");
				}
				else
				{
					ExcelOperation.writeToExcel("Output", row, 5, "FAIL");
				}
				
			  }
			  catch (Exception e) {}
			Thread.sleep(2000);
			ScreenShot.takeSnapShot("FollowupNew2","Pass");
			
		} catch (Exception e) {
			System.out.println("Followup exception :" +e.getMessage());
			//KMBLog.logError("Followup exception :" +e.getMessage());
			ScreenShot.takeSnapShot("Failure in Followup App","Fail");
		}
	}
	
/*****************************END Follow Up NEW****************************************************/

	
	
	
	
	
}


