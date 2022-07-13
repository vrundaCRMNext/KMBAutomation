package TestCases;

import org.testng.SkipException;
import org.testng.annotations.Test;

import CommonUtility.CommonMethods;
import CommonUtility.SetUp;
import POM_CRMLeadCreation.LoginPage;
import POM_HLSalesDAP.HLDAPEndJourney;
import POM_HLSalesDAP.HLDAPIntialJourney;
import POM_HLSalesDAP.HLDAPModulesJourney;
import POM_HLSalesDAP.HLDAPOnlyTopUp;

public class TC11HLDAPOnlyTopUpSalariedIndian extends SetUp
{
	public LoginPage login;
	public HLDAPIntialJourney HLSalesIntial;
	public HLDAPModulesJourney HLSalesModule ;
	public HLDAPEndJourney HLSalesEnd ;
	public HLDAPOnlyTopUp HLSalesTopUp;
	@Test
	public void HLDAPOnlyTopUpSalariedIndian() throws Exception
	{
		String sheetName = "HLDAPOnlyTopUpSalariedIndian";
		  
		  if (!(CommonMethods.isTestRunnable("HLDAPOnlyTopUpSalariedIndian"))) {

				throw new SkipException(
						"Skipping the test HLDAPOnlyTopUpSalariedIndian as the Run mode is NO");
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
				
				HLSalesTopUp = new HLDAPOnlyTopUp(driver);
				HLSalesTopUp.topUpDetailsScreen(sheetName);
				
				HLSalesModule.bankStmtUpload(sheetName);
				HLSalesModule.perfiosFailure(sheetName);
				HLSalesModule.CrossSellScreen(sheetName);	
				
				HLSalesEnd = new HLDAPEndJourney(driver);
				HLSalesEnd.runEndJourney(sheetName);
				
	}
}
