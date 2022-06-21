package TestSripts;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import CommonUtility.AutoConst;
import CommonUtility.ExcelOperation;
import CommonUtility.SetUp;
import CommonUtility.TestListeners;
import POM_CRMLeadCreation.CustomerAcceptance;
import POM_CRMLeadCreation.FileClosedInCRM;
import POM_CRMLeadCreation.LeadCreation;
import POM_CRMLeadCreation.LoginPage;
import POM_HLSalesDAP.HLDAPEndJourney;
import POM_HLSalesDAP.HLDAPIntialJourney;
import POM_HLSalesDAP.HLDAPModulesJourney;
import POM_SecuredCC.SecuredCC;

public class runTest extends SetUp implements AutoConst
{
	//Variables for reading excel sheet
		public static int TotalTestScenarios;
		public static ArrayList<String> sheetNames,methodnames;
		public static String RunMode;
		public static String TestCaseIDName;
		public static String TestCaseDesc;
		public static String LeadStatus;	
		
		//Objects of POM classes
		public LoginPage login;
		public LeadCreation leadCreate; 
		public CustomerAcceptance custAccept;
		public FileClosedInCRM fileClosed;
		public HLDAPIntialJourney HLSalesIntial;
		public HLDAPModulesJourney HLSalesModule;
		public HLDAPEndJourney HLSalesEnd;
		
		public SecuredCC SCC;
		
		//LOgs
		public Logger log =LogManager.getLogger(runTest.class.getName());
		
		@Test
			void CRMLeadGeneration()
			{
				try {
					TotalTestScenarios=ExcelOperation.getRowCount(SheetName);
					log.info("Total Test Scenarios :"+TotalTestScenarios);
					for(int i=0;i<=TotalTestScenarios;i++)
					{
						RunMode=ExcelOperation.getCellData(SheetName,"RunMode", i);
						TestCaseIDName=ExcelOperation.getCellData(SheetName, "TCID", i);
						TestCaseDesc=ExcelOperation.getCellData(SheetName, "TC Name", i);
						log.info("Run  Mode is :"+RunMode + "| TestCase ID Name :"+TestCaseIDName+"| Test Case Description : "+TestCaseDesc);
						TestListeners.extentInfo("Run  Mode is :"+RunMode + "| TestCase ID Name :"+TestCaseIDName+"| Test Case Description : "+TestCaseDesc);
					//To check whether Run Mode is Yes if true then execute that scenarios only
						if(RunMode.equalsIgnoreCase("Yes") && TestCaseIDName.equalsIgnoreCase("TC01"))
						{
							log.info("===================CRM LEAD GENERATION RUN START ================");
							//Browser Launch
							SetUp.setUpTest();
						//Login	
							login = new LoginPage(driver);
							log.info(TestCaseIDName+"=========CRM LOGIN INITIATE=============");
							login.CRMLogin(TestCaseDesc);
						//New Lead Creation
							leadCreate = new LeadCreation(driver);
							log.info(TestCaseIDName+"=========NEW Lead Creation=============");
							leadCreate.NewLead(TestCaseDesc);
							leadCreate.CreateNewLead(TestCaseDesc);
							Thread.sleep(2000);
							leadCreate.Edit(TestCaseDesc);
							Thread.sleep(1000);
							leadCreate.FollowUpNew(TestCaseDesc);
						//Customer acceptance
							custAccept = new CustomerAcceptance(driver);
							Thread.sleep(1000);
							log.info(TestCaseIDName+"=========Customer Acceptance=============");
							leadCreate.Edit(TestCaseDesc);
							custAccept.AppointmentFixed(TestCaseDesc);
							Thread.sleep(1000);
							leadCreate.Edit(TestCaseDesc);
							custAccept.DocumentPending(TestCaseDesc);
							
						//File closed in CRMNext to Sent To LOS
							fileClosed = new FileClosedInCRM(driver);
							Thread.sleep(1000);
							log.info(TestCaseIDName+"=======FileClosedInCRM=======");
							leadCreate.Edit(TestCaseDesc);
							fileClosed.DocCollected(TestCaseDesc);
							Thread.sleep(2000);
							leadCreate.Edit(TestCaseDesc);
							fileClosed.SentToLOS();
							log.info(TestCaseIDName+"=========Sent To LOS Completed=============");
						}
					}
					
				} catch (Exception e) {
					log.error("CRMLeadGeneration exception occured due to " +e.getMessage());
				}
			}
		
		@Test
			void HLSalesDAP()
			{
				try {
					TotalTestScenarios=ExcelOperation.getRowCount(SheetName);
					log.info("Total Test Scenarios :"+TotalTestScenarios);
					for(int i=0;i<=TotalTestScenarios;i++)
					{
						RunMode=ExcelOperation.getCellData(SheetName,"RunMode", i);
						TestCaseIDName=ExcelOperation.getCellData(SheetName, "TCID", i);
						TestCaseDesc=ExcelOperation.getCellData(SheetName, "TC Name", i);
						log.info("Run  Mode is :"+RunMode + "| TestCase ID Name :"+TestCaseIDName+"| Test Case Description : "+TestCaseDesc);
						TestListeners.extentInfo("Run  Mode is :"+RunMode + "| TestCase ID Name :"+TestCaseIDName+"| Test Case Description : "+TestCaseDesc);
						//To check whether Run Mode is Yes if true then execute that scenarios only
						if(RunMode.equalsIgnoreCase("Yes") && TestCaseIDName.equalsIgnoreCase("TC03"))
						{
							log.info("===================KMB HL SALES APP DAP JOURENY RUN START ================");
							//Browser Launch
							SetUp.setUpTest();
						//Login	
							login = new LoginPage(driver);
							log.info(TestCaseIDName+"=========CRM LOGIN INITIATE=============");
							login.CRMLogin(TestCaseDesc);
							
							
						//Open HL Sales App link through quick link 
							HLSalesIntial = new HLDAPIntialJourney(driver);
							log.info(TestCaseIDName+"=========HL SALES APP DAP JOURNEY INITIATE=============");
							HLSalesIntial.runIntialJourney(TestCaseDesc);
							
							
							HLSalesModule = new HLDAPModulesJourney(driver);
							HLSalesModule.runModuleJoureny(TestCaseDesc);
							
							HLSalesEnd = new HLDAPEndJourney(driver);
							HLSalesEnd.runEndJourney(TestCaseDesc);
							
							
						}
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		
		@Test
			void HLDAPSalariedHomeloanNRI()
			{
				try {
					TotalTestScenarios=ExcelOperation.getRowCount(SheetName);
					log.info("Total Test Scenarios :"+TotalTestScenarios);
					for(int i=0;i<=TotalTestScenarios;i++)
					{
						RunMode=ExcelOperation.getCellData(SheetName,"RunMode", i);
						TestCaseIDName=ExcelOperation.getCellData(SheetName, "TCID", i);
						TestCaseDesc=ExcelOperation.getCellData(SheetName, "TC Name", i);
						log.info("Run  Mode is :"+RunMode + "| TestCase ID Name :"+TestCaseIDName+"| Test Case Description : "+TestCaseDesc);
						TestListeners.extentInfo("Run  Mode is :"+RunMode + "| TestCase ID Name :"+TestCaseIDName+"| Test Case Description : "+TestCaseDesc);
						//To check whether Run Mode is Yes if true then execute that scenarios only
						if(RunMode.equalsIgnoreCase("Yes") && TestCaseIDName.equalsIgnoreCase("TC04"))
						{
							log.info("===================Salaried: Fresh Home loan for NRI  JOURENY RUN START ================");
							//Browser Launch
							SetUp.setUpTest();
						//Login	
							login = new LoginPage(driver);
							log.info(TestCaseIDName+"=========CRM LOGIN INITIATE=============");
							login.CRMLogin(TestCaseDesc);
							
							
						//Open HL Sales App link through quick link 
							HLSalesIntial = new HLDAPIntialJourney(driver);
							log.info(TestCaseIDName+"=========HL SALES APP DAP JOURNEY INITIATE=============");
							HLSalesIntial.runIntialJourney(TestCaseDesc);
							
							HLSalesModule = new HLDAPModulesJourney(driver);
							HLSalesModule.Digital_InprincipleSanction(TestCaseDesc);
							//HLSalesIntial.resumeWithLink(TestCaseDesc);

							HLSalesModule.NRIPANCibilScreen(TestCaseDesc);
							HLSalesModule.incomeDetails(TestCaseDesc);
							HLSalesModule.propertyDetailsScreen(TestCaseDesc);
							HLSalesModule.CrossSellScreen(TestCaseDesc);
							
							
							HLSalesEnd = new HLDAPEndJourney(driver);
							HLSalesEnd.runEndJourney(TestCaseDesc);
							
							
						}
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		@Test
		void HLDAPSelfEmpIndividualHomeLoan()
		{
			try {
				TotalTestScenarios=ExcelOperation.getRowCount(SheetName);
				log.info("Total Test Scenarios :"+TotalTestScenarios);
				for(int i=0;i<=TotalTestScenarios;i++)
				{
					RunMode=ExcelOperation.getCellData(SheetName,"RunMode", i);
					TestCaseIDName=ExcelOperation.getCellData(SheetName, "TCID", i);
					TestCaseDesc=ExcelOperation.getCellData(SheetName, "TC Name", i);
					log.info("Run  Mode is :"+RunMode + "| TestCase ID Name :"+TestCaseIDName+"| Test Case Description : "+TestCaseDesc);
					TestListeners.extentInfo("Run  Mode is :"+RunMode + "| TestCase ID Name :"+TestCaseIDName+"| Test Case Description : "+TestCaseDesc);
					//To check whether Run Mode is Yes if true then execute that scenarios only
					if(RunMode.equalsIgnoreCase("Yes") && TestCaseIDName.equalsIgnoreCase("TC05"))
					{
						log.info("===================Salaried: Fresh Home loan for NRI  JOURENY RUN START ================");
						//Browser Launch
						SetUp.setUpTest();
					//Login	
						login = new LoginPage(driver);
						log.info(TestCaseIDName+"=========CRM LOGIN INITIATE=============");
						login.CRMLogin(TestCaseDesc);
						//Open HL Sales App link through quick link 
						HLSalesIntial = new HLDAPIntialJourney(driver);
						log.info(TestCaseIDName+"=========HL SALES APP DAP JOURNEY INITIATE=============");
						HLSalesIntial.runIntialJourney(TestCaseDesc);
						
						HLSalesModule = new HLDAPModulesJourney(driver);
						HLSalesModule.Digital_InprincipleSanction(TestCaseDesc);
						HLSalesModule.PANCibilDeatils(TestCaseDesc);
						HLSalesModule.incomeDetails(TestCaseDesc);
						
						
						
						
						
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		@Test
			void SecuredCCDAP()
			{
				try {
					TotalTestScenarios=ExcelOperation.getRowCount(SheetName);
					log.info("Total Test Scenarios :"+TotalTestScenarios);
					for(int i=0;i<=TotalTestScenarios;i++)
					{
						RunMode=ExcelOperation.getCellData(SheetName,"RunMode", i);
						TestCaseIDName=ExcelOperation.getCellData(SheetName, "TCID", i);
						TestCaseDesc=ExcelOperation.getCellData(SheetName, "TC Name", i);
						log.info("Run  Mode is :"+RunMode + "| TestCase ID Name :"+TestCaseIDName+"| Test Case Description : "+TestCaseDesc);
						TestListeners.extentInfo("Run  Mode is :"+RunMode + "| TestCase ID Name :"+TestCaseIDName+"| Test Case Description : "+TestCaseDesc);
						//To check whether Run Mode is Yes if true then execute that scenarios only
						if(RunMode.equalsIgnoreCase("Yes") && TestCaseIDName.equalsIgnoreCase("TC02"))
						{
							int totalCRN = ExcelOperation.getRowCount("Secured CC ETB");
							//System.out.println("Total row count :"+totalCRN);
							for(int j=1;j<totalCRN;j++)
							{
								System.out.println("Value of j ="+j);
								//Browser Launch
								SetUp.setUpTest();
							//Login	
								login = new LoginPage(driver);
								log.info(TestCaseIDName+"=========CRM LOGIN INITIATE=============");
								login.CRMLogin(TestCaseDesc);
								
								SCC = new SecuredCC(driver);
								log.info(TestCaseIDName+"=========SECURED CC JOURNEY INITIATE=============");
								SCC.clearCRN(j,TestCaseDesc);
								SCC.intiateSCC(j,TestCaseDesc);
								
								Thread.sleep(3000);			
								log.info(TestCaseIDName+"=========CAPTURED LEAD ID AND STORED AGAINST CRN IN EXCEL=============");
								driver.get("https://kmb.crmnext.com/sng7/app/login/login");
								//log.info("URL : "+);
								driver.manage().window().maximize();
								login.CRMLogin(TestCaseDesc);
								SCC.captureLeadID(j,TestCaseDesc);
							//Logout	
								login.Logout();
							}
						}
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
}
