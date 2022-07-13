package TestCases;

import org.testng.SkipException;
import org.testng.annotations.Test;

import CommonUtility.CommonMethods;
import CommonUtility.SetUp;
import POM_CRMLeadCreation.LoginPage;
import POM_HLSalesDAP.HLDAPEndJourney;
import POM_HLSalesDAP.HLDAPIntialJourney;
import POM_HLSalesDAP.HLDAPModulesJourney;

public class TC06HLDAPHomeLoanNonIndividual extends SetUp{
	public LoginPage login;
	public HLDAPIntialJourney HLSalesIntial;
	public HLDAPModulesJourney HLSalesModule ;
	public HLDAPEndJourney HLSalesEnd ;
	
@Test
  public void HLDAPHomeLoanNonIndividual() throws Exception {
	
	String sheetName = "HLDAPHomeLoanNonIndividual";
	if (!(CommonMethods.isTestRunnable("HLDAPHomeLoanNonIndividual"))) {

		throw new SkipException(
				"Skipping the test HLDAPHomeLoanNonIndividual as the Run mode is NO");
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
		HLSalesModule.PANCibilDeatils(sheetName);
		HLSalesModule.selfEmpIncomeDetails(sheetName);
		HLSalesModule.propertyDetailsScreen(sheetName);
		HLSalesModule.ITRScreen();
		HLSalesModule.CrossSellScreen(sheetName);
		
		HLSalesEnd  = new HLDAPEndJourney(driver);
		HLSalesEnd.EligibilityScreen(sheetName,"Continue to Sanction Letter");
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
