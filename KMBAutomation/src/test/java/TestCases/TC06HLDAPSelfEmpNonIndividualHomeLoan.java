package TestCases;

import org.testng.SkipException;
import org.testng.annotations.Test;

import CommonUtility.CommonMethods;
import CommonUtility.SetUp;
import POM_CRMLeadCreation.LoginPage;
import POM_HLSalesDAP.HLDAPEndJourney;
import POM_HLSalesDAP.HLDAPIntialJourney;
import POM_HLSalesDAP.HLDAPModulesJourney;

public class TC06HLDAPSelfEmpNonIndividualHomeLoan extends SetUp{
	public LoginPage login;
	public HLDAPIntialJourney HLSalesIntial;
	public HLDAPModulesJourney HLSalesModule ;
	public HLDAPEndJourney HLSalesEnd ;
	
@Test
  public void HLDAPSelfEmpNonIndividualHomeloan() throws Exception {
	
	String sheetName = "HLDAPSelfEmpNonIndividualHomeLo";
	if (!(CommonMethods.isTestRunnable("HLDAPSelfEmpNonIndividualHomeLoan"))) {

		throw new SkipException(
				"Skipping the test HLDAPSelfEmpNonIndividualHomeLoan as the Run mode is NO");
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
		HLSalesModule.selfEmpIncomeDetails(sheetName);
		HLSalesModule.propertyDetailsScreen(sheetName);
		HLSalesModule.ITRScreen();
		HLSalesModule.CrossSellScreen(sheetName);
		
		HLSalesEnd  = new HLDAPEndJourney(driver);
		HLSalesEnd.EligibilityScreen(sheetName);
		HLSalesEnd.ProcessingFeeScreen(sheetName);
		
		HLSalesEnd.ReferenceDetailsScreen(sheetName);
		
	//	String propertyIdentifed= ExcelOperation.getCellData(sheetName, "Property Identified", 1);
		//if(propertyIdentifed.equalsIgnoreCase("Yes"))
		{
			HLSalesEnd.AppPropertyDetailsScreen(sheetName);
		}
		
		HLSalesEnd.FinIndividualAppQue(sheetName);
		
		
		
		
  }
}