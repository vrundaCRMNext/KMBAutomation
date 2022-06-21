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
import POM_HLSalesDAP.HLDAPOnlyTopUp;

public class HLDAPSalariedOnlyTopUpIndian extends SetUp
{
	public LoginPage login;
	public HLDAPIntialJourney HLSalesIntial;
	public HLDAPModulesJourney HLSalesModule ;
	public HLDAPEndJourney HLSalesEnd ;
	public HLDAPOnlyTopUp HLSalesTopUp;
	@Test
	public void HLDAPSalariedOnlyTopupIndian() throws Exception
	{
		String sheetName = "HLDAPSalariedOnlyTopUpIndian";
		  
		  if (!(CommonMethods.isTestRunnable("HLDAPSalariedOnlyTopUpIndian"))) {

				throw new SkipException(
						"Skipping the test HLDAPSalariedOnlyTopUpIndian as the Run mode is NO");
			}
		   
		   		setUpTest();
		   	//Login	
				login = new LoginPage(driver);
				login.CRMLogin(sheetName);
			//Open HL Sales App link through quick link 
				HLSalesIntial = new HLDAPIntialJourney(driver);
				HLSalesIntial.runIntialJourney(sheetName);
				
				HLSalesModule = new HLDAPModulesJourney(driver);
				HLSalesModule.Digital_InprincipleSanction(sheetName);
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
