package TestCases;
import java.util.ArrayList;
import java.util.List;
import org.testng.TestNG;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import utilities.GenericKeywords;

import silk4J.ApplicationKeywords;

public class TestDriver extends ApplicationKeywords
{
	
	@Test
	public void Launch(){
		try {			
			startup();			
			TestNG testng = new TestNG();
			List<String> suites = new ArrayList<String>();
			suites.add("./Config/testng.xml");			
			testng.setTestSuites(suites);			
			GenericKeywords.extent = new ExtentReports(GenericKeywords.outputDirectory+"/Results.html", true);
			testng.run();		

		} catch (Exception e) {
			writeToLogFile("error", e.toString());
		} finally {
			try {								
				cleanup();
				

			} catch (Exception e) {
				writeToLogFile("error", e.toString());
			} finally {
				writeToLogFile("INFO", "###################################");
				writeToLogFile("INFO", "Script Execution Complete");
				writeToLogFile("INFO", "####################################");

			}

		}
	}
		
}

