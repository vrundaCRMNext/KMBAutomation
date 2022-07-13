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

public class TC14HLDAPOnlyTopUpNonIndividual extends SetUp{

	public LoginPage login;
	public HLDAPIntialJourney HLSalesIntial;
	public HLDAPModulesJourney HLSalesModule ;
	public HLDAPEndJourney HLSalesEnd ;
	public HLDAPOnlyTopUp HLSalesTopUp;
	@Test
	public void HLDAPOnlyTopUpNonIndividual() throws Exception
	{
		String sheetName = "HLDAPOnlyTopUpNonIndividual";
		  
		  if (!(CommonMethods.isTestRunnable("HLDAPOnlyTopUpNonIndividual"))) {

				throw new SkipException(
						"Skipping the test HLDAPOnlyTopUpNonIndividual as the Run mode is NO");
			}
		
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
			HLSalesModule.incomeDetails(sheetName);
			
			HLSalesTopUp = new HLDAPOnlyTopUp(driver);
			HLSalesTopUp.topUpDetailsScreen(sheetName);
			
			HLSalesModule.ITRScreen();
			HLSalesModule.CrossSellScreen(sheetName);
			
			HLSalesEnd  = new HLDAPEndJourney(driver);
			HLSalesEnd.EligibilityScreen(sheetName,"Continue to Sanction Letter");
			HLSalesEnd.ProcessingFeeScreen(sheetName);
			
		//Digi App Form for NonIndividual	
			HLSalesEnd.ReferenceDetailsScreen(sheetName);
			HLSalesEnd.AppPropertyDetailsScreen(sheetName);
			HLSalesEnd.FinIndividualAppQue(sheetName);
		
		
		
		
	}
}
