package TestCases;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.Test;

import CommonUtility.CommonMethods;
import CommonUtility.SetUp;
import POM_CRMLeadCreation.LoginPage;
import POM_HLSalesDAP.HLDAPEndJourney;
import POM_HLSalesDAP.HLDAPIntialJourney;
import POM_HLSalesDAP.HLDAPModulesJourney;


public class HLDAPSalariedHomeLoanIndian extends SetUp
{
	public LoginPage login;
	public HLDAPIntialJourney HLSalesIntial;
	public HLDAPModulesJourney HLSalesModule ;
	public HLDAPEndJourney HLSalesEnd ;
	
  @Test
  public void HLDAPSalariedHomeloanIndian() throws Exception 
  {
	  String sheetName = "HLDAPSalariedHomeLoanIndian";
	  
	  if (!(CommonMethods.isTestRunnable("HLDAPSalariedHomeloanIndian"))) {

			throw new SkipException(
					"Skipping the test HLDAPSalariedHomeLoanIndian as the Run mode is NO");
		}
	   
	   		setUpTest();
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
