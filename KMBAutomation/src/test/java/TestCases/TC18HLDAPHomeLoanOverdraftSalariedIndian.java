package TestCases;

import org.testng.SkipException;
import org.testng.annotations.Test;

import CommonUtility.CommonMethods;
import CommonUtility.SetUp;
import POM_CRMLeadCreation.LoginPage;
import POM_HLSalesDAP.HLDAPEndJourney;
import POM_HLSalesDAP.HLDAPIntialJourney;
import POM_HLSalesDAP.HLDAPModulesJourney;

public class TC18HLDAPHomeLoanOverdraftSalariedIndian extends SetUp
{
	public LoginPage login;
	public HLDAPIntialJourney HLSalesIntial;
	public HLDAPModulesJourney HLSalesModule ;
	public HLDAPEndJourney HLSalesEnd ;
	@Test
	public void HLDAPHomeLoanOverdraftSalariedIndian() throws Exception
	{
		String sheetName="HLDAPHomeLoanOverdraftSalIndian";
		
		if (!(CommonMethods.isTestRunnable("HLDAPHomeLoanOverdraftSalariedIndian"))) {

			throw new SkipException(
					"Skipping the test HLDAPHomeLoanOverdraftSalariedIndian as the Run mode is NO");
		}
	  	  	
			setUpTest1(sheetName);
	   	//Login	
			login = new LoginPage(driver);
			login.CRMLogin(sheetName);
		//Open HL Sales App link through quick link 
			HLSalesIntial = new HLDAPIntialJourney(driver);
			HLSalesIntial.runIntialJourney(sheetName);
			
			HLSalesModule = new HLDAPModulesJourney(driver);
			HLSalesModule.runModuleJoureny(sheetName);
			
			HLSalesEnd = new HLDAPEndJourney(driver);
			HLSalesEnd.runEndJourney(sheetName);
		
	}
}
