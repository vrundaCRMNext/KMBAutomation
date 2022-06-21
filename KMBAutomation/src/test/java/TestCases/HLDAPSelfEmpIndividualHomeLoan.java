package TestCases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.SkipException;
import org.testng.annotations.Test;

import CommonUtility.CommonMethods;
import CommonUtility.SetUp;
import POM_CRMLeadCreation.LoginPage;
import POM_HLSalesDAP.HLDAPEndJourney;
import POM_HLSalesDAP.HLDAPIntialJourney;
import POM_HLSalesDAP.HLDAPModulesJourney;

public class HLDAPSelfEmpIndividualHomeLoan extends SetUp{
  
	public LoginPage login;
	public HLDAPIntialJourney HLSalesIntial;
	public HLDAPModulesJourney HLSalesModule ;
	public HLDAPEndJourney HLSalesEnd ;
@Test
  public void HLDAPSelfEmpIndividualHomeloan() throws Exception 
  {
	 String sheetName = "HLDAPSelfEmpIndividualHomeLoan";
		if (!(CommonMethods.isTestRunnable("HLDAPSelfEmpIndividualHomeloan"))) {

			throw new SkipException(
					"Skipping the test HLDAPSelfEmpIndividualHomeloan as the Run mode is NO");
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
			HLSalesModule.propertyDetailsScreen(sheetName);
			HLSalesModule.ITRScreen();
			HLSalesModule.CrossSellScreen(sheetName);
			
			HLSalesEnd  = new HLDAPEndJourney(driver);
			HLSalesEnd.runEndJourney(sheetName);
		
  }
}
