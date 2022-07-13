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

public class TC12HLDAPOnlyTopUpSalariedNRI extends SetUp
{
	public LoginPage login;
	public HLDAPIntialJourney HLSalesIntial;
	public HLDAPModulesJourney HLSalesModule ;
	public HLDAPEndJourney HLSalesEnd ;
	public HLDAPOnlyTopUp HLSalesTopUp;
	
	@Test
	public void HLDAPOnlyTopUpSalariedNRI() throws Exception
	{
		String sheetName = "HLDAPOnlyTopUpSalariedNRI";
		  
		  if (!(CommonMethods.isTestRunnable("HLDAPOnlyTopUpSalariedNRI"))) {

				throw new SkipException(
						"Skipping the test HLDAPOnlyTopUpSalariedNRI as the Run mode is NO");
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
				HLSalesModule.NRIPANCibilScreen(sheetName);
				HLSalesModule.incomeDetails(sheetName);
				
				HLSalesTopUp = new HLDAPOnlyTopUp(driver);
				HLSalesTopUp.topUpDetailsScreen(sheetName);
				
				HLSalesModule.CrossSellScreen(sheetName);
				
				HLSalesEnd = new HLDAPEndJourney(driver);
				HLSalesEnd.runEndJourney(sheetName);
	  
	}
}
