package TestCases;


import org.testng.SkipException;
import org.testng.annotations.Test;

import CommonUtility.CommonMethods;
import CommonUtility.ExcelOperation;
import CommonUtility.SetUp;
import POM_CRMLeadCreation.LoginPage;
import POM_SecuredCC.SecuredCC;

public class SecuredCCDAP extends SetUp
{
	public LoginPage login;
	public SecuredCC SCC;
	@Test
  public void SecuredCCDap() throws Exception 
  {
	  if (!(CommonMethods.isTestRunnable("SecuredCCDap"))) {

			throw new SkipException(
					"Skipping the test SecuredCCDap as the Run mode is NO");
		}
	  String sheetName = "SecuredCCDAP";
	  
	  int totalCRN = ExcelOperation.getRowCount("Secured CC ETB");
		//System.out.println("Total row count :"+totalCRN);
		for(int j=1;j<totalCRN;j++)
		{
			setUpTest();
			//Login	
			login = new LoginPage(driver);
			login.CRMLogin(sheetName);
			
			SCC = new SecuredCC(driver);
			SCC.clearCRN(j,sheetName);
			SCC.intiateSCC(j,sheetName);
			//log.info(TestCaseIDName+"=========CAPTURED LEAD ID AND STORED AGAINST CRN IN EXCEL=============");
			driver.get("https://kmb.crmnext.com/sng7/app/login/login");
			driver.manage().window().maximize();
			login.CRMLogin(sheetName);
			SCC.captureLeadID(j,sheetName);
			//Logout	
			login.Logout();
		}
	
  }
  
}
