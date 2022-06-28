package TestCases;

import org.openqa.selenium.TimeoutException;
import org.testng.SkipException;
import org.testng.annotations.Test;

import CommonUtility.CommonMethods;
import CommonUtility.ExcelOperation;
import CommonUtility.SetUp;
import POM_CRMLeadCreation.LoginPage;
import POM_HLSalesDAP.HLDAPEndJourney;
import POM_HLSalesDAP.HLDAPIntialJourney;
import POM_HLSalesDAP.HLDAPModulesJourney;
import POM_HLSalesDAP.HLDAPOnlyTopUp;

public class TC13HLDAPOnlyTopUpIndividual extends SetUp{
  
	public LoginPage login;
	public HLDAPIntialJourney HLSalesIntial;
	public HLDAPModulesJourney HLSalesModule ;
	public HLDAPEndJourney HLSalesEnd ;
	public HLDAPOnlyTopUp HLSalesTopUp;
	@Test(expectedExceptions = TimeoutException.class)
	public void HLDAPOnlyTopUpIndividual() throws Exception
	{
		String sheetName = "HLDAPOnlyTopUpIndividual";
		  
		  if (!(CommonMethods.isTestRunnable("HLDAPOnlyTopUpIndividual"))) {

				throw new SkipException(
						"Skipping the test HLDAPOnlyTopUpIndividual as the Run mode is NO");
			}
		
		setUpTest1(sheetName);
	   	//Login	
			login = new LoginPage(driver);
			login.CRMLogin(sheetName);
		//Open HL Sales App link through quick link 
			HLSalesIntial = new HLDAPIntialJourney(driver);
			//HLSalesIntial.runIntialJourney(sheetName);
			HLSalesIntial.intiateDAPJourney(sheetName);
			HLSalesIntial.productSelection(sheetName);
			HLSalesIntial.subSourceSelection(sheetName);
			HLSalesIntial.applicantTypeSelection(sheetName);
			HLSalesIntial.SelfEMPTypeSelection(sheetName);
			HLSalesIntial.Sal_ETB_NTBCheck(sheetName);
			HLSalesIntial.OTPVerification(sheetName);
			HLSalesIntial.selectBusinessVintage(sheetName);
			HLSalesIntial.ResidentStatusSelection(sheetName);
			
			
			HLSalesModule = new HLDAPModulesJourney(driver);
			HLSalesModule.Digital_InprincipleSanction(sheetName);
			HLSalesModule.PANCibilDeatils(sheetName);
			HLSalesModule.incomeDetails(sheetName);
			
			HLSalesTopUp = new HLDAPOnlyTopUp(driver);
			HLSalesTopUp.topUpDetailsScreen(sheetName);
			
			HLSalesModule.ITRScreen();
			HLSalesModule.CrossSellScreen(sheetName);
			
			HLSalesEnd  = new HLDAPEndJourney(driver);
			HLSalesEnd.runEndJourney(sheetName);
		
	}
}
