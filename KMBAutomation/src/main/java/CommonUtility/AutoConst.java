package CommonUtility;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.support.ui.WebDriverWait;

//AutoConst interface include all constant variables
public interface AutoConst 
{
	String TEST_URL = "https://kmb.crmnext.com/sng7/app/login/login";
	String ExcelPATH = "C:\\Users\\Vrunda Vibhute\\eclipse-workspace\\KMBAutomation\\TestData\\TestData_LeadDetails.xlsx";
	
	String SheetName = "TestScenario";	
	String folderDate = new SimpleDateFormat("dd-MM-yyyy HH").format(new Date());
}
