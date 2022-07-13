package TestCases;

import org.testng.SkipException;
import org.testng.annotations.Test;

import CommonUtility.CommonMethods;
import CommonUtility.SetUp;
import POM_CRMLeadCreation.LoginPage;
import POM_HLSalesDAP.HLDAPEndJourney;
import POM_HLSalesDAP.HLDAPIntialJourney;
import POM_HLSalesDAP.HLDAPModulesJourney;


public class TC03HLDAPHomeLoanSalariedIndian extends SetUp
{
	public LoginPage login;
	public HLDAPIntialJourney HLSalesIntial;
	public HLDAPModulesJourney HLSalesModule ;
	public HLDAPEndJourney HLSalesEnd ;
	
  @Test
  public void HLDAPHomeLoanSalariedIndian() throws Exception 
  {
	  String sheetName = "HLDAPHomeLoanSalariedIndian";
	  
	  if (!(CommonMethods.isTestRunnable("HLDAPHomeLoanSalariedIndian"))) {

			throw new SkipException(
					"Skipping the test HLDAPHomeLoanSalariedIndian as the Run mode is NO");
		}
	   
	   		//setUpTest();
	  		setUpTest1(sheetName);
	   	//Login	
			login = new LoginPage(driver);
			login.CRMLogin(sheetName);
		//Open HL Sales App link through quick link 
			try {
				HLSalesIntial = new HLDAPIntialJourney(driver);
				HLSalesIntial.runIntialJourney(sheetName);
			} catch (Exception e) {
				System.out.println("Exception ="+e.getMessage());
			}
			
			
			HLSalesModule = new HLDAPModulesJourney(driver);
			HLSalesModule.runModuleJoureny(sheetName);
			
			HLSalesEnd = new HLDAPEndJourney(driver);
			HLSalesEnd.runEndJourney(sheetName);
  }
  
  
  
  
}
