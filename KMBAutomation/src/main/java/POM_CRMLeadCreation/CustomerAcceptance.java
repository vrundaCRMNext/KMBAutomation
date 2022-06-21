package POM_CRMLeadCreation;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import CommonUtility.ExcelOperation;
import CommonUtility.CommonMethods;
import CommonUtility.ScreenShot;
import CommonUtility.SetUp;

public class CustomerAcceptance extends SetUp
{
		//default constructor to initilise elements
		public CustomerAcceptance(WebDriver driver)
		{
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		//Web Elements xpath on CustomerAcceptance Page
		
		@FindBy(xpath="//div[@id=1]")        //Customer Acceptance Tab
		private WebElement CustAcceptanceTab;
		public WebElement CustAcceptanceTab()
		{
			return CustAcceptanceTab;
		}
		
/*********************************Appointment Fixed********************************************/
	
		@FindBy(xpath="//span[contains(text(),'Appointment Fixed')]")        //Appointment Fixed Tab
		private WebElement AppFixedTab;
		public WebElement AppFixedTab()
		{
			return AppFixedTab;
		}
		
		@FindBy(xpath="//div[@data-autoid='cust_526']/label")       //Appointment Date & Time label
		private WebElement AppDtTimeLbl;
		public WebElement AppDtTimeLbl()
		{
			return AppDtTimeLbl;
		}
		
		@FindBy(xpath="//div[@data-autoid='cust_526']//div[1]//div[1]//div[1]//div[1]//div[1]//input")       //Appointment Date field
		private WebElement AppDateFld;
		public WebElement AppDateFld()
		{
			return AppDateFld;
		}
		
		@FindBy(xpath="//div[@data-autoid='cust_526']//div[1]//div[1]//div[1]//div[1]//div[1]//a[@title='Today Date']")       //Appointment Today Date btn
		private WebElement AppTodayDate;
		public WebElement AppTodayDate()
		{
			return AppTodayDate;
		}
		
		@FindBy(xpath="//div[@data-autoid='cust_526']//div[1]//div[1]//div[2]//div[1]//div[1]//input")       //Appointment Time
		private WebElement AppTime;
		public WebElement AppTime()
		{
			return AppTime;
		}
		
		@FindBy(xpath="//div[@data-autoid='LE_LOCALITY']/label")       //Meeting location label
		private WebElement MeetLocLbl;
		public WebElement MeetLocLbl()
		{
			return MeetLocLbl;
		}
		
		@FindBy(xpath="//input[@data-autoid='LE_LOCALITY_ctrl']")       //Meeting location field
		private WebElement MeetLocField;
		public WebElement MeetLocField()
		{
			return MeetLocField;
		}
		
		@FindBy(xpath="//a[@data-autoid='FlowNext']")  // Save & Proceed Button
		private WebElement saveProceedBtn;
		public WebElement saveProceedBtn()
		{
			return saveProceedBtn;
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
		
		//Edit function to perform Edit operation on Lead
		@FindBy(xpath="//a[@data-autoid='Edit_1']/span[1]")   //Lead Edit button
		private WebElement LeadEdit;
		public WebElement LeadEdit()
		{
			return LeadEdit;
		}
		
		public void Edit() throws InterruptedException
		{
			CommonMethods.ExWait(leadStatusCodeLbl);
			Thread.sleep(1000);
			CommonMethods.scrollByVisibilityofElement(LeadEdit);
			CommonMethods.highLight(LeadEdit);
			LeadEdit.click();
			Thread.sleep(1000);
		}
		
		//Appointment Fixed function
		public void AppointmentFixed(String sheetName) throws Exception
		{
			try 
			{
				Thread.sleep(1000);
				CommonMethods.highLight(CustAcceptanceTab);
				CustAcceptanceTab.click();
				
				CommonMethods.ExWait(AppFixedTab);
				AppFixedTab.click();
				Thread.sleep(1000);
				CommonMethods.scrollByVisibilityofElement(AppDtTimeLbl);
				
				AppTodayDate.click();
				AppTime.clear();
				//AppTime.sendKeys(ExcelOperation.readData(sheetName, 1, 19));
				AppTime.sendKeys(ExcelOperation.getCellData(sheetName,"Follow up Time",1));
				CommonMethods.ExWait(MeetLocLbl);
				MeetLocField.click();
				MeetLocField.clear();
				//MeetLocField.sendKeys(ExcelOperation.readData(sheetName, 1, 22));
				MeetLocField.sendKeys(ExcelOperation.getCellData(sheetName, "Meeting Location",1));
				Thread.sleep(1000);
				saveProceedBtn.click();
				Thread.sleep(1000);

				CommonMethods.ExWait(leadStatusCodeLbl);
				writeToExcel();
				ScreenShot.Ashot("Appointment Fixed","Pass");
				
			} catch (InterruptedException e) {
					System.out.println("Exception Appointment Fixed :"+e.getMessage());
					ScreenShot.takeSnapShot("Failure in Appointment Fixed", "Fail");
			}
			
		}
		
		public void writeToExcel()
		{
			 //Write Data into excel 
			try {
				Thread.sleep(2000);
				CommonMethods.ExWait(leadStatusCodeLbl);
			  	ExcelOperation.writeToExcel("Output", ExcelOperation.getRowCount("Output"), 3, leadStatusCodeVal().getText());
			  	ExcelOperation.writeToExcel("Output", ExcelOperation.getRowCount("Output"), 4, leadLastModifyVal().getText());
			  }
			  catch (Exception e) {}
		}
		
/*********************************END Appointment Fixed********************************************/
		
/**********************************Document Pending**********************************************/	
		
	@FindBy(xpath="//span[contains(text(),'Documents Pending')]")   			//Document pending Tab	
	private WebElement DocPendingTab;
	public WebElement DocPendingTab()
	{
		return DocPendingTab;
	}
		
	//Aadhar card
	@FindBy(xpath="//label[@data-autoid='AutoDocRow_2957_75_lbl']")    //Aadhar Card Lbl
	private WebElement AadharCardLbl;
	public WebElement AadharCardLbl()
	{
		return AadharCardLbl;
	}
	
	@FindBy(xpath="//div[@data-autoid='AutoDocRow_2957_75']/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[@type='file']")
	private WebElement AadharCardUploadBtn;
	public WebElement AadharCardUploadBtn()
	{
		return AadharCardUploadBtn;
	}
	
	//Driving License
	@FindBy(xpath="//label[@data-autoid='AutoDocRow_2957_4_lbl']")    //Driving License Lbl
	private WebElement DrivingLicLbl;
	public WebElement DrivingLicLbl()
	{
		return DrivingLicLbl;
	}
	
	@FindBy(xpath="//div[@data-autoid='AutoDocRow_2957_4']/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[@type='file']")
	private WebElement DrivingLicUploadBtn;
	public WebElement DrivingLicUploadBtn()
	{
		return DrivingLicUploadBtn;
	}
	
	//Elictricity Bill
	@FindBy(xpath="//label[@data-autoid='AutoDocRow_3698_146_lbl']")    //Elictricity Bill Lbl
	private WebElement ElictricityBillLbl;
	public WebElement ElictricityBillLbl()
	{
		return ElictricityBillLbl;
	}
	
	@FindBy(xpath="//div[@data-autoid='AutoDocRow_3698_146']/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[@type='file']")
	private WebElement ElictricityBillUploadBtn;
	public WebElement ElictricityBillUploadBtn()
	{
		return ElictricityBillUploadBtn;
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
	
	
	public void DocumentPending(String sheetName) throws Exception
	{
		try 
		{
			Thread.sleep(1000);
			//Edit();
			CommonMethods.highLight(DocPendingTab);
			DocPendingTab.click();
			Thread.sleep(1000);
			
			//Driving License
			try 
			{
				Thread.sleep(1000);
				CommonMethods.scrollByVisibilityofElement(DrivingLicLbl);
				CommonMethods.ExWait(DrivingLicLbl);
				DrivingLicUploadBtn.sendKeys(ExcelOperation.getCellData(sheetName,"Document Upload", 1));

				try {
					System.out.println("Doc Upload warning: "+warningMsg.getText());
					warningOKBtn.click();
				} catch (Exception e) {}
				
			} catch (Exception e) {
				System.out.println("Driving lic upload exception :"+e.getMessage());
			}
			
			//Aadhar Card
			try 
			{
				Thread.sleep(1000);
				CommonMethods.scrollByVisibilityofElement(AadharCardLbl);
				CommonMethods.ExWait(AadharCardLbl);
				AadharCardUploadBtn.sendKeys("C:\\Users\\Vrunda Vibhute\\Pictures\\Saved Pictures\\8.jpg");	
				try {
					System.out.println("Doc Upload warning: "+warningMsg.getText());
					warningOKBtn.click();
				} catch (Exception e) {}
				

			} catch (Exception e) {
				System.out.println("Aadhar card upload exception :"+e.getMessage());
			}
			
			//Elictiycity Bills
			try 
			{
				Thread.sleep(1000);	
				CommonMethods.scrollByVisibilityofElement(ElictricityBillLbl);
				CommonMethods.ExWait(ElictricityBillLbl);
				ElictricityBillUploadBtn.sendKeys(ExcelOperation.getCellData(sheetName, "Document Upload", 1));
				try {
					System.out.println("Doc Upload warning: "+warningMsg.getText());
					warningOKBtn.click();
				} catch (Exception e) {}
				

			} catch (Exception e) {
				System.out.println("Elictricity upload exception :"+e.getMessage());
			}
			
			
			saveProceedBtn.click();
			Thread.sleep(2000);
			CommonMethods.ExWait(leadStatusCodeLbl);
			writeToExcel();
			int row = ExcelOperation.getRowCount("Output");
			String leadStatus = ExcelOperation.getCellData("Output", "Lead Status", row);
			System.out.println("Lead status = "+leadStatus);
			if(leadStatus.equalsIgnoreCase("Documents Pending"))
			{
				ExcelOperation.writeToExcel("Output",row, 5, "PASS");
			}
			else
			{
				ExcelOperation.writeToExcel("Output", row, 5, "FAIL");
			}
			
			ScreenShot.Ashot("DocPending","Pass");

			
		} catch (InterruptedException e) {
			System.out.println("Exception Doument Pending");
			ScreenShot.takeSnapShot("Failure in Doc Pending", "Fail");
		}
	}
	/**********************************Document Pending**********************************************/	
	/**********************************Follow Up Appointment**********************************************/	
	@FindBy(xpath="//span[contains(text(),'Follow-up Appointment')]")   			//Follow Up Appointment Tab	
	private WebElement FollowUpAppTab;
	public WebElement FollowUpAppTab()
	{
		return FollowUpAppTab;
	}
	
	@FindBy(xpath="//div[@data-autoid='cust_143']//div[1]//div[1]//div[1]//div[1]//div[1]//a[@title='Today Date']")   //Follow-up today date 
	private WebElement followUpTodayDate;
	public WebElement followUpTodayDate()
	{
		return followUpTodayDate;
	}
	
	@FindBy(xpath="//div[@data-autoid='cust_143']//div[1]//div[1]//div[2]//div[1]//div[1]//input")   //Follow-up Time 
	private WebElement followUpTime;
	public WebElement followUpTime()
	{
		return followUpTime;
	}
	
	public void FollwUpAppointment(String sheetName) throws Exception
	{
		try 
		{
			//Edit();
			Thread.sleep(1000);

			CommonMethods.highLight(FollowUpAppTab);
			FollowUpAppTab.click();
			
			AppTodayDate.click();
			AppTime.clear();
			AppTime.sendKeys(ExcelOperation.getCellData(sheetName, "Follow up Time",1));
			Thread.sleep(1000);	
			
			saveProceedBtn.click();
			Thread.sleep(2000);
			
			CommonMethods.ExWait(leadStatusCodeLbl);
			writeToExcel();

		} catch (Exception e) {
			System.out.println("FollowUpAppointment Exception :"+e.getMessage());	
			ScreenShot.takeSnapShot("Failure in FollowUpApp", "Fail");
			}
	}
	
	
	/**********************************END Follow Up Appointment**********************************************/	

	
		
		
		
}

