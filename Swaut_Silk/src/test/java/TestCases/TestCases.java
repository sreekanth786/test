package TestCases;


import org.testng.annotations.Test;

import java.io.File;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import utilities.GenericKeywords;
import Module.ModuleFunctionalities;
import utilities.Common;


@Listeners 
public class TestCases extends Common
{

	@BeforeClass
	public void start(){	
		GenericKeywords.extent.loadConfig(new File("D:\\Sreekanth Data\\Workspace_Sreekanth\\Swaut_Silk\\config\\extent-config.xml"));
		
	}
	public void TestStart(String strName,String testCaseDescription)
	{	
		GenericKeywords.currentStep=0;
		GenericKeywords.testCaseExecutionStatus = false;
		reportStart(strName,testCaseDescription);
		GenericKeywords.openBrowser();
	}



	////////////////////////////////////////////////////////////////////////////////
	//Function Name  :TC001
	//Purpose    	 :Verify Login
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Prakash Shetty
	//Created on     :11/04/2015
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	@Test(alwaysRun=true)
	public void TC_001() 
	{
		String strName = new Exception().getStackTrace()[0].getMethodName();
		TestStart(strName,"Verify Login");
		for (String testDataSet : GenericKeywords.testCaseDataSets) {
			GenericKeywords.testCaseDataRow = returnRowNumber(testDataSet);
			testStepInfoStart("########### Start of Test Case Data Set: "+testDataSet + " ###########");

			ModuleFunctionalities.loginToApp();
			

			testStepInfoEnd("########### End of Test Case Data Set: "+testDataSet + " ###########");
		}
		testEnd();				
	}



	////////////////////////////////////////////////////////////////////////////////
	//Function Name  :TC002
	//Purpose    	 :Verify Book Flight
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Prakash Shetty
	//Created on     :11/04/2015
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	/*@Test(alwaysRun=true)
	public void TC_002() 
	{
		String strName = new Exception().getStackTrace()[0].getMethodName();
		TestStart(strName,"Verify Login2");
		for (String testDataSet : GenericKeywords.testCaseDataSets) {
			GenericKeywords.testCaseDataRow = returnRowNumber(testDataSet);
			testStepInfoStart("########### Start of Test Case Data Set: "+testDataSet + " ###########");

			ModuleFunctionalities.loginToApp();
			

			testStepInfoEnd("########### End of Test Case Data Set: "+testDataSet + " ###########");
		}
		testEnd();				
	}
*/
	@Test
	public void testEnd() {
		try {
			GenericKeywords.closeBrowser();			
		} catch (Exception e) {
			System.out.println("Expception : " + e.getMessage());
		}finally{
			GenericKeywords.extent.endTest(GenericKeywords.parent);
			GenericKeywords.extent.flush();
		}
	}

	

}