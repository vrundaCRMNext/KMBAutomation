package TestCases;

import org.testng.SkipException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import CommonUtility.CommonMethods;
import CommonUtility.ExcelOperation;
import CommonUtility.SetUp;
import POM_CRMLeadCreation.LoginPage;
import POM_HLSalesDAP.HLDAPEndJourney;
import POM_HLSalesDAP.HLDAPIntialJourney;
import POM_HLSalesDAP.HLDAPModulesJourney;

public class TC20HLDAPHomeLoanOverdraftNonIndividual extends SetUp
{
	public LoginPage login;
	public HLDAPIntialJourney HLSalesIntial;
	public HLDAPModulesJourney HLSalesModule ;
	public HLDAPEndJourney HLSalesEnd ;
	@Test
	@Parameters("productName")
	public void HLDAPHomeLoanOverdraftNonIndividual(String productName) throws Exception
	{
		String sheetName = "HLDAPHomeLoanOverdraftNonInd";
		 
		if (!(CommonMethods.isTestRunnable("HLDAPHomeLoanOverdraftNonIndividual"))) {

			throw new SkipException(
					"Skipping the test HLDAPHomeLoanOverdraftNonIndividual as the Run mode is NO");
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
			HLSalesIntial.SelfEMPTypeSelection(sheetName);
			HLSalesIntial.selfEmp_ETBNTB_Check(sheetName);
			HLSalesIntial.OTPVerification(sheetName,"MainApplicant");
			HLSalesIntial.residentStatusForHLOD(sheetName);	
			
			
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
			HLSalesEnd.AppPropertyDetailsScreen(sheetName);
			HLSalesEnd.FinIndividualAppQue(sheetName);
			
	  
	}
}
