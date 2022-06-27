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

public class TC08HLDAPBalanceTransferSalariedNRI extends SetUp
{
	public LoginPage login;
	public HLDAPIntialJourney HLSalesIntial;
	public HLDAPModulesJourney HLSalesModule ;
	public HLDAPEndJourney HLSalesEnd ;
	public HLDAPBalanceTransfer HLSalesBT;
	@Test
	public void HLDAPBalanceTransferSalariedNRI() throws Exception
	{
		String sheetName = "HLDAPBalanceTransferSalariedNRI";
		  
		  if (!(CommonMethods.isTestRunnable("HLDAPBalanceTransferSalariedNRI"))) {

				throw new SkipException(
						"Skipping the test HLDAPBalanceTransferSalariedNRI as the Run mode is NO");
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
