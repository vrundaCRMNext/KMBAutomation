package TestCases;

import org.testng.SkipException;
import org.testng.annotations.Test;

import CommonUtility.CommonMethods;
import CommonUtility.SetUp;
import POM_CRMLeadCreation.LoginPage;
import POM_HLSalesDAP.HLDAPEndJourney;
import POM_HLSalesDAP.HLDAPIntialJourney;
import POM_HLSalesDAP.HLDAPModulesJourney;

public class TC17HLDAPKotakSmartHomeIndividual extends SetUp
{
	public LoginPage login;
	public HLDAPIntialJourney HLSalesIntial;
	public HLDAPModulesJourney HLSalesModule ;
	public HLDAPEndJourney HLSalesEnd ;
	@Test
	  public void HLDAPKotakSmartHomeIndividual() throws Exception
	  {
		String sheetName = "HLDAPKotakSmartHomeIndividual";
		if (!(CommonMethods.isTestRunnable("HLDAPKotakSmartHomeIndividual"))) {

			throw new SkipException(
					"Skipping the test HLDAPKotakSmartHomeIndividual as the Run mode is NO");
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
			HLSalesModule.PANCibilDeatils(sheetName);
			HLSalesModule.incomeDetails(sheetName);
			HLSalesModule.propertyDetailsScreen(sheetName);
			HLSalesModule.ITRScreen();
			HLSalesModule.CrossSellScreen(sheetName);
			
			HLSalesEnd  = new HLDAPEndJourney(driver);
			HLSalesEnd.runEndJourney(sheetName);
	  }
}
