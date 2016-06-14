package utilities;

import utilities.GenericKeywords;
import utilities.OutputAndLog;
import utilities.HtmlReport;
import utilities.TransferLogo;
import utilities.DataDriver;
import utilities.PropertiesFile;

import com.itextpdf.text.DocumentException;
import com.relevantcodes.extentreports.LogStatus;

import OR.OR;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import jxl.Sheet;

import org.testng.Reporter;
import org.xml.sax.SAXException;





public class Common implements OR
{
  public static String testName;
  public static int testCaseDataNo;
  public static int textLoadWaitTime;
  
  public Common() {}
  
  public static int testCaseexecutionNo = 0;
  
  public static String getConfigProperty(String keyword) {
    Properties properties = new Properties();
    try
    {
      properties.load(new FileInputStream(".\\Config\\TestConfiguration.properties"));
    }
    catch (FileNotFoundException e)
    {
      writeToLogFile("ERROR", "File Not Found Exception thrown while getting value of " + keyword + " from Test Configuration file");
    }
    catch (IOException e) {
      writeToLogFile("ERROR", "IO Exception thrown while getting value of " + keyword + " from Test Configuration file");
    }
    writeToLogFile("INFO", "Getting value of " + keyword + " from Test Configuration file : " + properties.getProperty(keyword));
    
    return properties.getProperty(keyword);
  }
  
  public static void writeToLogFile(String type, String message)
  {
    String t = type.toUpperCase();
    if (t.equalsIgnoreCase("DEBUG"))
    {
      GenericKeywords.logger.debug(message);
    }
    else if (t.equalsIgnoreCase("INFO"))
    {
      GenericKeywords.logger.info(message);
    }
    else if (t.equalsIgnoreCase("WARN"))
    {
      GenericKeywords.logger.warn(message);
    }
    else if (t.equalsIgnoreCase("ERROR"))
    {
      GenericKeywords.logger.error(message);
    }
    else if (t.equalsIgnoreCase("FATAL"))
    {
      GenericKeywords.logger.fatal(message);
    }
    else {
      GenericKeywords.logger.error("Invalid log Type :" + type + ". Unable to log the message.");
    }
  }
  










  public static void supportSeleniumRC() {}
  










  public static void startup()
  {
 
    try
    {
    OutputAndLog.createOutputDirectory();
      PropertiesFile.properties();
      loadTestCaseData();
    }
    catch (Exception e)
    {
      writeToLogFile("INFO", "Startup activities - Done...");
    }
  }
  


  public static void report()
    throws ParserConfigurationException, SAXException, IOException, TransformerException, DocumentException
  {
	  TransferLogo.transferLogo();
	  HtmlReport.htmlReport();
  }
  

  public static void cleanup()
  {
    try
    {
      TransferLogo.transferLogo();
      DeleteTempFiles.delete();
      writeToLogFile("INFO", "Cleanup activites...");
      writeToLogFile("INFO", "Cleanup activities - Done...");
      
      if (GenericKeywords.getConfigProperty("SendMail(Yes/No)").trim().equalsIgnoreCase("yes"))
      {
        ZipReportFile.zipReport();
        writeToLogFile("INFO", "<<<<<<<<<<<<<Sending mail...>>>>>>>>>>>>>>>>>");
        Mailing.sendMail();
      }
      if (GenericKeywords.getConfigProperty("SendMsg(Yes/No)").trim().equalsIgnoreCase("yes"))
      {
        Texting.sendMsg();
      }
    }
    catch (Exception e)
    {
      System.out.println(e.toString());
    }
  }
  



  public static void testReporter(String color, String report)
  {
    colorTypes ct = colorTypes.valueOf(color.toLowerCase());
    if (!color.contains("white"))
    {
      GenericKeywords.currentStep += 1;
    }
    
    switch (ct)
    {
    case green: 
    	GenericKeywords.child.log(LogStatus.PASS,"<font color=green>" + GenericKeywords.currentStep + ". " + report + "</font><br/>");writeToLogFile("PASS", "Report step generation success : " + report);System.out.println("green" + GenericKeywords.currentStep); break;
    case blue:  GenericKeywords.child.log(LogStatus.INFO,"<font color=blue>" + GenericKeywords.currentStep + ". " + report + "</font><br/>");writeToLogFile("INFO", "Report step generation success : " + report);System.out.println("blue" + GenericKeywords.currentStep); break;
    case orange:  GenericKeywords.child.log(LogStatus.WARNING,"<font color=orange>" + GenericKeywords.currentStep + ". " + report + "</font><br/>");writeToLogFile("WARN", "Report step generation success : " + report); break;
    case red:  GenericKeywords.child.log(LogStatus.FAIL,"<font color=red>" + GenericKeywords.currentStep + ". " + report + "</font><br/>");writeToLogFile("ERROR", "Report step generation success : " + report);System.out.println("red" + GenericKeywords.currentStep); break;
    case white:  GenericKeywords.child.log(LogStatus.INFO,report);writeToLogFile("WARN", "Report step generation success : " + report);
    }
    
  }
  
  public static enum colorTypes
  {
    green, 
    red, 
    blue, 
    orange,  white;
  }
  
  public static enum browserType
  {
    FIREFOX, 
    INTERNETEXPLORER, 
    CHROME, 
    OPERA;
  }
  
  public static enum identifierType {
    xpath, 
    name, 
    id, 
    lnktext, 
    partiallinktext, 
    classname, 
    cssselector, 
    tagname;
  }
  


  public static String convertToProperCase(String inputString)
  {
    String[] arr = inputString.split(" ");
    String strProper = "";
    for (int i = 0; i < arr.length; i++)
    {

      String tmp = arr[i].substring(0, 1).toUpperCase() + arr[i].substring(1, arr[i].length());
      strProper = strProper + tmp + " ";
    }
    
    return strProper.trim();
  }
  
  public static void screenShot(String filename)
  {
    String scrPath = GenericKeywords.outputDirectory + "\\Screenshots";
    File file = new File(scrPath);
    file.mkdir();
    try {
      Robot robot = new Robot();
      Rectangle captureSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
      BufferedImage bufferedImage = robot.createScreenCapture(captureSize);
      File outputfile = new File(scrPath + "\\" + filename + ".png");
      ImageIO.write(bufferedImage, "png", outputfile);
      GenericKeywords.writeToLogFile("INFO", "Taken screenshot of failing screen");
    }
    catch (AWTException e) {
      GenericKeywords.writeToLogFile("ERROR", "AWT Exception : While taking screenshot of the failing test case");
    } catch (IOException e) {
      GenericKeywords.writeToLogFile("ERROR", "IO Exception : While taking screenshot of the failing test case");
    }
  }
  
  



  public static void testFailed() {}
  


  public static void captureScreenShot(String filename)
  {
    File scrFile = null;
    String scrImagePath = null;
    String scrPath = GenericKeywords.outputDirectory + "\\Screenshots";
    File file = new File(scrPath);
    file.mkdir();
    GenericKeywords.maximiseWindow();
    try
    {
      scrImagePath = GenericKeywords.browserWindow.captureBitmap(scrPath + "\\" + filename);
      File input = new File(scrImagePath);
      BufferedImage image = ImageIO.read(input);
      File output = new File(scrImagePath);
      
      ImageIO.write(image, "png", output);
      scrFile = new File(scrImagePath.replace(".bmp", ".png"));
      output.renameTo(scrFile);
    }
    catch (Exception e)
    {
      testReporter("Red", e.toString());
      screenShot(filename);
    }
  }
  
  public static void testStepFailed(String errMessage)
  {
	  GenericKeywords.testFailure = true;
	  GenericKeywords.failureNo += 1;

	  writeToLogFile("Error", errMessage);
	  testReporter("Red", errMessage);
	  if (!GenericKeywords.windowreadyStateStatus)
	  {
		  screenShot("TestFailure" + GenericKeywords.failureNo);
		  GenericKeywords.windowreadyStateStatus = true;
	  }
	  else
	  {
		  captureScreenShot("TestFailure" + GenericKeywords.failureNo);
	  }	  
	  String pathAndFile = GenericKeywords.outputDirectory + "\\Screenshots\\TestFailure" + GenericKeywords.failureNo+ ".png";
	  GenericKeywords.child.log(LogStatus.FAIL, "Check ScreenShot Below: " + GenericKeywords.child.addScreenCapture(pathAndFile));	  
	  if (getConfigProperty("ExecuteRemainingStepsOnFailure(Yes/No)").toUpperCase().contains("YES"))
	  {

		  GenericKeywords.testCaseExecutionStatus = true;
		  GenericKeywords.elementLoadWaitTime = Integer.parseInt(getConfigProperty("OverideTimeoutOnFailure"));
		  GenericKeywords.textLoadWaitTime = Integer.parseInt(getConfigProperty("OverideTimeoutOnFailure"));
		  GenericKeywords.pageLoadWaitTime = Integer.parseInt(getConfigProperty("OverideTimeoutOnFailure"));
		  GenericKeywords.implicitlyWaitTime = Integer.parseInt(getConfigProperty("OverideTimeoutOnFailure"));
	  }
	  else if (getConfigProperty("ExecuteRemainingStepsOnFailure(Yes/No)").toUpperCase().contains("NO"))
	  {
		  testFailed();
	  }
	  else
	  {
		  testReporter("Red", "Invalid option 'ExecuteRemainingStepsOnFailure(Yes/No)--'" + getConfigProperty("ExecuteRemainingStepsOnFailure(Yes/No)"));
		  testFailed();
	  }	  
	 
  }
  
  public static void testStepPassed(String errMessage)
  {
    writeToLogFile("Info", errMessage);
    testReporter("Green", errMessage);
  }
  
  public static void testStepInfo(String errMessage)
  {
    writeToLogFile("Info", errMessage);
    testReporter("Blue", errMessage);
  }
  
  public static void testStepInfoStart(String testDataSet) {
	  GenericKeywords.child = GenericKeywords.extent.startTest(testDataSet);
	  GenericKeywords.child.log(LogStatus.INFO, "########### Start of Test Case Data Set: "+testDataSet + " ###########");	    
  }
  
  
  public static void testStepInfoEnd(String testDataSet) {
		GenericKeywords.child.log(LogStatus.INFO, "########### End of Test Case Data Set: "+testDataSet + " ###########");
		GenericKeywords.parent
        .appendChild(GenericKeywords.child);
	  }
  public static void reportStart(String strName,String testCaseDescription)
  {
    GenericKeywords.writeToLogFile("INFO", "##### Start of Test Case : " + testCaseDescription + " #####");
   // testReporter("white", "<B></B><h1><font color=SaddleBrown>" + testCaseDescription + ":</h1></font><br/>");
    for (String testCaseName : GenericKeywords.testCaseNames)
    {
      if (testCaseName.equals(((String)PropertiesFile.testCases.get(testCaseexecutionNo)).trim()))
      {
        writeToLogFile("INFO", "Test Case Name: " + testCaseName);
        updateTestDataSet(testCaseName);
        testCaseexecutionNo += 1;
        break;
      }
      testCaseDataNo += 1;
    }
    GenericKeywords.parent = GenericKeywords.extent.startTest(strName,"<font size=4 color=black>" +testCaseDescription+ "</font><br/>");
  }
  


  public static void updateTestDataSet(String testCaseName)
  {
    useExcelSheet(getConfigProperty("TestDataFile"), 1);
    Sheet readsheet = DataDriver.w.getSheet(0);
    String testCaseDataSet = null;
    String executionFlag = null;
    Boolean flag = Boolean.valueOf(false);
    for (int caseRow = 0; caseRow < readsheet.getRows(); caseRow++) {
      GenericKeywords.testCaseDataSets.clear();
      if (testCaseName.equals(readsheet.getCell(1, caseRow).getContents()))
      {


        for (int DataRow = caseRow; DataRow < readsheet.getRows(); DataRow++)
        {
          GenericKeywords.testCaseRow = caseRow + 1;
          testCaseDataSet = readsheet.getCell(1, DataRow).getContents();
          writeToLogFile("INFO", "Test Data Set Name: " + testCaseDataSet);
          executionFlag = readsheet.getCell(2, DataRow).getContents();
          writeToLogFile("INFO", "Execution Flag: " + executionFlag);
          if ((testCaseDataSet.startsWith(testCaseName)) && (executionFlag.toUpperCase().equals("YES")))
          {
            GenericKeywords.testCaseDataSets.add(testCaseDataSet);
          } else if (testCaseDataSet.isEmpty())
          {
            flag = Boolean.valueOf(true);
            break;
          }
        }
        if (flag.booleanValue()) {
          break;
        }
      }
    }
  }









  public static void embedScreenshot(String colour, String pathAndFile)
  {   
	  GenericKeywords.child.log(LogStatus.INFO, "Manual Verification Point: " + GenericKeywords.child.addScreenCapture(pathAndFile+ ".png"));    
  }

  public static void takeScreenshot(String comment)
  {
    GenericKeywords.screenshotNo += 1;        
    screenShot("Screenshot" + GenericKeywords.screenshotNo);
    embedScreenshot("orange", GenericKeywords.outputDirectory + "\\Screenshots" + "\\Screenshot" + GenericKeywords.screenshotNo);
  }
  
  

  public static void useExcelSheet(String pathOfExcel, int sheetNumber)
  {
    DataDriver.useExcelSheet(pathOfExcel, sheetNumber);
  }
  


  public static void closeExcelSheet() {}
  

  public static String retrieve(String Label)
  {
    //return DataDriver.retrieve(GenericKeywords.testCaseDataRow, Label);
	  return DataDriver.retrieve(GenericKeywords.testCaseRow, GenericKeywords.testCaseDataRow, Label);
  }
  
  public static int returnRowNumber(String Label)
  {
    return DataDriver.returnRowNo(2, Label);
  }
  
  public static void loadTestCaseData() {
    useExcelSheet(getConfigProperty("TestDataFile"), 1);
    
    Sheet readsheet = DataDriver.w.getSheet(0);
    for (int i = 0; i < readsheet.getRows(); i++) {
      String testCaseName = readsheet.getCell(1, i).getContents();
      GenericKeywords.testCaseNames.add(testCaseName);
    }
  }
}
