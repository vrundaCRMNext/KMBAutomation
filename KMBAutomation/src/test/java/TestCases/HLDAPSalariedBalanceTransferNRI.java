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

public class HLDAPSalariedBalanceTransferNRI extends SetUp
{
	public LoginPage login;
	public HLDAPIntialJourney HLSalesIntial;
	public HLDAPModulesJourney HLSalesModule ;
	public HLDAPEndJourney HLSalesEnd ;
	public HLDAPBalanceTransfer HLSalesBT;
	@Test
	public void HLDAPSalariedBalancetransferNRI() throws Exception
	{
		String sheetName = "HLDAPSalariedBalanceTransferNRI";
		  
		  if (!(CommonMethods.isTestRunnable("HLDAPSalariedBalanceTransferNRI"))) {

				throw new SkipException(
						"Skipping the test HLDAPSalariedBalanceTransferNRI as the Run mode is NO");
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

				HLSalesModule.NRIPANCibilScreen(sheetName);
				HLSalesModule.incomeDetails(sheetName);
				
				HLSalesBT = new HLDAPBalanceTransfer(driver);
				HLSalesBT.BalanceTransferScreen(sheetName);
				HLSalesBT.BalanceTransferPlan(sheetName);
				
				HLSalesModule.CrossSellScreen(sheetName);
				
				HLSalesEnd = new HLDAPEndJourney(driver);
				HLSalesEnd.runEndJourney(sheetName);
	  }
		
	
}
