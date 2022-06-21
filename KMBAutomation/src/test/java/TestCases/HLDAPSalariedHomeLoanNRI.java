package TestCases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.SkipException;
import org.testng.annotations.Test;

import CommonUtility.CommonMethods;
import CommonUtility.ExcelOperation;
import CommonUtility.SetUp;
import POM_CRMLeadCreation.LoginPage;
import POM_HLSalesDAP.HLDAPEndJourney;
import POM_HLSalesDAP.HLDAPIntialJourney;
import POM_HLSalesDAP.HLDAPModulesJourney;

public class HLDAPSalariedHomeLoanNRI extends SetUp{
	
	public LoginPage login ;
	public HLDAPIntialJourney HLSalesIntial;
	public HLDAPModulesJourney HLSalesModule ;
	public HLDAPEndJourney HLSalesEnd ;	
  @Test
  public void HLDAPSalariedHomeloanNRI() throws Exception 
  {
	  String sheetName = "HLDAPSalariedHomeLoanNRI";
	  //System.out.println("SheetName = "+sheetName+ " and  rows ="+ExcelOperation.getRowCount(sheetName) );
	 
	  if (!(CommonMethods.isTestRunnable(sheetName))) 
	  {
		  throw new SkipException("Skipping the test HLDAPSalariedHomeloanNRI sas the Run mode is NO");
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
			//HLSalesIntial.resumeWithLink();

			HLSalesModule.NRIPANCibilScreen(sheetName);
			HLSalesModule.incomeDetails(sheetName);
			HLSalesModule.propertyDetailsScreen(sheetName);
			HLSalesModule.CrossSellScreen(sheetName);
			
			
			HLSalesEnd = new HLDAPEndJourney(driver);
			HLSalesEnd.runEndJourney(sheetName);
  }
}
