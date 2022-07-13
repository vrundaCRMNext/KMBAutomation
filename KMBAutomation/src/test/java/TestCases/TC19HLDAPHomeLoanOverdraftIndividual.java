package TestCases;

import org.testng.SkipException;
import org.testng.annotations.Test;

import CommonUtility.CommonMethods;
import CommonUtility.ExcelOperation;
import CommonUtility.SetUp;
import POM_CRMLeadCreation.LoginPage;
import POM_HLSalesDAP.HLDAPEndJourney;
import POM_HLSalesDAP.HLDAPIntialJourney;
import POM_HLSalesDAP.HLDAPModulesJourney;

public class TC19HLDAPHomeLoanOverdraftIndividual extends SetUp
{
	public LoginPage login;
	public HLDAPIntialJourney HLSalesIntial;
	public HLDAPModulesJourney HLSalesModule ;
	public HLDAPEndJourney HLSalesEnd ;
	@Test
	public void HLDAPHomeLoanOverdraftIndividual() throws Exception  
	{
		String sheetName = "HLDAPHomeLoanOverdraftIndividul";
		if (!(CommonMethods.isTestRunnable("HLDAPHomeLoanOverdraftIndividual"))) {

			throw new SkipException(
					"Skipping the test HLDAPHomeLoanOverdraftIndividual as the Run mode is NO");
		}
	   
	   		//setUpTest();
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
			String applicantTypeExcel = ExcelOperation.getCellData(sheetName,"Applicant Type", 1);
			HLSalesIntial.applicantTypeSelection(sheetName,applicantTypeExcel);
			HLSalesIntial.Sal_ETB_NTBCheck(sheetName);
			HLSalesIntial.OTPVerification(sheetName,"MainApplicant");
			HLSalesIntial.selectBusinessVintage(sheetName);
			HLSalesIntial.ResidentStatusSelection(sheetName);
			
			HLSalesModule = new HLDAPModulesJourney(driver);
			HLSalesModule.Digital_InprincipleSanction(sheetName,"MainApplicant");
			HLSalesModule.PANCibilDeatils(sheetName);
			HLSalesModule.incomeDetails(sheetName);
			HLSalesModule.propertyDetailsScreen(sheetName);
			HLSalesModule.ITRScreen();
			HLSalesModule.CrossSellScreen(sheetName);
			
			//HLSalesIntial.resumeWithLink(sheetName);
			HLSalesEnd  = new HLDAPEndJourney(driver);
			HLSalesEnd.runEndJourney(sheetName);
	}
}
