package TestCases;

import org.testng.SkipException;
import org.testng.annotations.Test;

import CommonUtility.CommonMethods;
import CommonUtility.SetUp;
import POM_CRMLeadCreation.LoginPage;
import POM_HLSalesDAP.HLDAPBalanceTransfer;
import POM_HLSalesDAP.HLDAPEndJourney;
import POM_HLSalesDAP.HLDAPIntialJourney;
import POM_HLSalesDAP.HLDAPModulesJourney;

public class TC07HLDAPBalanceTransferSalariedIndian extends SetUp {
	public LoginPage login;
	public HLDAPIntialJourney HLSalesIntial;
	public HLDAPModulesJourney HLSalesModule ;
	public HLDAPEndJourney HLSalesEnd ;
	public HLDAPBalanceTransfer HLSalesBT;
	
@Test
  public void HLDAPBalanceTransferSalariedIndian() throws Exception 
  {
 String sheetName = "HLDAPBalTransferSalariedInd";
	 
	  if (!(CommonMethods.isTestRunnable("HLDAPBalanceTransferSalariedIndian"))) {

			throw new SkipException(
					"Skipping the test HLDAPBalanceTransferSalariedIndian as the Run mode is NO");
		}
	   
	   		//setUpTest();
	  	setUpTest1(sheetName);
	   	//Login	
			login = new LoginPage(driver);
			login.CRMLogin(sheetName);
		//Open HL Sales App link through quick link 
			HLSalesIntial = new HLDAPIntialJourney(driver);
			HLSalesIntial.runIntialJourney(sheetName);
			
			HLSalesModule = new HLDAPModulesJourney(driver);
			HLSalesModule.Digital_InprincipleSanction(sheetName,"MainApplicant");
			HLSalesModule.PANCibilDeatils(sheetName);
			HLSalesModule.incomeDetails(sheetName);
			HLSalesModule.applicantWorkDetails(sheetName);
			HLSalesModule.karzaWaitingScreen(sheetName);
			HLSalesModule.KarzaFailureScreen(sheetName);
			
			HLSalesBT = new HLDAPBalanceTransfer(driver);
			HLSalesBT.BalanceTransferScreen(sheetName);
			HLSalesBT.BalanceTransferPlan(sheetName);
			
			HLSalesModule.bankStmtUpload(sheetName);
			HLSalesModule.perfiosFailure(sheetName);
			HLSalesModule.CrossSellScreen(sheetName);	
			
			HLSalesEnd = new HLDAPEndJourney(driver);
			HLSalesEnd.runEndJourney(sheetName);
	  
  }
}
