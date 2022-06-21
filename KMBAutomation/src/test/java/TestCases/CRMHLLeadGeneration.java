package TestCases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.SkipException;
import org.testng.annotations.Test;

import CommonUtility.CommonMethods;
import CommonUtility.SetUp;
import POM_CRMLeadCreation.CustomerAcceptance;
import POM_CRMLeadCreation.FileClosedInCRM;
import POM_CRMLeadCreation.LeadCreation;
import POM_CRMLeadCreation.LoginPage;

public class CRMHLLeadGeneration extends SetUp
{
	public LoginPage login;
	public LeadCreation leadCreate ;
	public CustomerAcceptance custAccept;
	public FileClosedInCRM fileClosed ;
  @Test
  public void CRMHLLeadgeneration() throws Exception 
  {
	String sheetName = "CRMHLLeadGeneration";

	if (!(CommonMethods.isTestRunnable("CRMHLLeadgeneration"))) {

			throw new SkipException(
					"Skipping the test CRMHLLeadgeneration as the Run mode is NO");
		}
	   
	   		setUpTest();
	   		//login to CRM
	   		login = new LoginPage(driver);
			login.CRMLogin(sheetName);
			//New Lead Creation
			leadCreate = new LeadCreation(driver);
			leadCreate.NewLead(sheetName);
			leadCreate.CreateNewLead(sheetName);
			Thread.sleep(2000);
			leadCreate.Edit(sheetName);
			Thread.sleep(1000);
			leadCreate.FollowUpNew(sheetName);
			
			//Customer acceptance
			custAccept = new CustomerAcceptance(driver);
			leadCreate.Edit(sheetName);
			custAccept.AppointmentFixed(sheetName);
			Thread.sleep(1000);
			leadCreate.Edit(sheetName);
			custAccept.DocumentPending(sheetName);
			//File closed in CRMNext to Sent To LOS
			fileClosed = new FileClosedInCRM(driver);
			leadCreate.Edit(sheetName);
			fileClosed.DocCollected(sheetName);
			Thread.sleep(2000);
			leadCreate.Edit(sheetName);
			fileClosed.SentToLOS();
			
  }
}
