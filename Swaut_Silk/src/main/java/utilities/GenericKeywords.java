package utilities;

import utilities.Common;
import utilities.Common.browserType;
import utilities.Common.identifierType;

import com.borland.silktest.jtf.BrowserBaseState;
import com.borland.silktest.jtf.Desktop;
import com.borland.silktest.jtf.Dialog;
import com.borland.silktest.jtf.PushButton;
import com.borland.silktest.jtf.common.BrowserType;
import com.borland.silktest.jtf.common.LaunchApplicationTimeoutException;
import com.borland.silktest.jtf.common.ObjectNotEnabledException;
import com.borland.silktest.jtf.common.ObjectNotFoundException;
import com.borland.silktest.jtf.common.TechDomain;
import com.borland.silktest.jtf.xbrowser.BrowserApplication;
import com.borland.silktest.jtf.xbrowser.BrowserWindow;
import com.borland.silktest.jtf.xbrowser.DomButton;
import com.borland.silktest.jtf.xbrowser.DomCheckBox;
import com.borland.silktest.jtf.xbrowser.DomElement;
import com.borland.silktest.jtf.xbrowser.DomForm;
import com.borland.silktest.jtf.xbrowser.DomLink;
import com.borland.silktest.jtf.xbrowser.DomListBox;
import com.borland.silktest.jtf.xbrowser.DomTextField;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;



public class GenericKeywords
  extends Common
{
	public static ExtentReports extent;
	  public static ExtentTest parent;
	  public static ExtentTest child;
  public static Desktop desktop;
  public static Dialog dialog;
  public static PushButton pushButton;
  public static BrowserType browserToLaunch;
  public static BrowserBaseState browserBaseState;
  public static BrowserApplication browserApplication;
  public static BrowserWindow browserWindow;
  public static String application;
  public static String window;
  public static String locator;
  public static String locatorType;
  public static DomTextField domTextField;
  public static DomElement domElement;
  public static DomButton domButton;
  public static DomLink domLink;
  public static DomListBox domListBox;
  public static DomCheckBox domCheckBox;
  public static DomForm domForm;
  public static LocatorType locType;
  public static int testCaseRow;
  public static String identifier;
  public static String locatorDescription;
  public static String outputDirectory;
  public static String currentExcelBook;
  public static String mainWindow;
  public static String currentBrowser = "";
  public static Logger logger;
  public static int currentTestCaseNumber;
  public static int currentExcelSheet;
  public static int currentStep;
  public static int failureNo; public static int screenshotNo; public static int rowCount; public static int colCount; public static Common.identifierType idType; public static boolean testFailure = false;
  public static boolean loadFailure = false;
  public static int temp = 1;
  public static String testStatus = "";
  public static int testCaseDataRow; public static int testLoadWaitTime; public static int elementLoadWaitTime; public static int implicitlyWaitTime; public static int pageLoadWaitTime = 0;
  public static final String XSLT_FILE_CoverPage = ".\\xsltfiles\\CoverPage.xslt";
  public static final String XSLT_FILE_SummaryPage = ".\\xsltfiles\\SummaryReport.xslt";
  public static final String XSLT_FILE_PdfPage = ".\\data\\PdfReport.xslt";
  public static final ArrayList<String> testCaseNames = new ArrayList();
  public static ArrayList<String> testCaseDataSets = new ArrayList();
  public static boolean testCaseExecutionStatus = false;
  public static boolean windowreadyStateStatus = true;
  public static int testSuccessCount = 0;
  public static int testFailureCount = 0;
  public static int testSkippedCount = 0;
  public static String timeStamp = "";
  
  public GenericKeywords() {}
  
  public static void openBrowser() {
    writeToLogFile("INFO", "Open browser: No values passed retrieving default values...");
    openBrowser(getConfigProperty("AppBrowser"), getConfigProperty("AppURL"));
  }
  

  public static void openBrowser(String browser, String URL)
  {
    try
    {
      Common.browserType b = Common.browserType.valueOf(browser.toUpperCase().trim());
      switch (b)
      {
      case CHROME: 
        browserToLaunch = BrowserType.GoogleChrome;
        break;
      case FIREFOX: 
        browserToLaunch = BrowserType.Firefox;
        break;
      case INTERNETEXPLORER: 
        browserToLaunch = BrowserType.InternetExplorer;
        break;
      default: 
        browserToLaunch = BrowserType.InternetExplorer;
      }
      
      browserBaseState = new BrowserBaseState(browserToLaunch, URL, new TechDomain[] { TechDomain.XBROWSER });
      desktop = new Desktop();
      browserBaseState.execute(desktop);
      browserApplication = (BrowserApplication)desktop.find("/BrowserApplication");
      maximiseWindow();
      browserWindow = (BrowserWindow)browserApplication.find("//BrowserWindow");
      elementLoadWaitTime = Integer.parseInt(getConfigProperty("ElementLoadWaitTime").toString().trim());
      testLoadWaitTime = Integer.parseInt(getConfigProperty("TextLoadWaitTime").toString().trim());
      pageLoadWaitTime = Integer.parseInt(getConfigProperty("PageLoadWaitTime").toString().trim());
      implicitlyWaitTime = Integer.parseInt(getConfigProperty("ImplicitlyWaitTime").toString().trim());
      writeToLogFile("INFO", "Element time out set");
      writeToLogFile("INFO", "Browser: Open Successful: " + browser.toLowerCase());
      //testReporter("Green", "Opened browser: ''<font color='blue'>" + convertToProperCase(browser) + "</font><font color='green'>'' and navigated to url :''<font color='blue'>" + URL + "</font><font color='green'>''");
    }
    catch (LaunchApplicationTimeoutException e) {
      e.printStackTrace();
      testStepFailed("Page fail to load within in " + getConfigProperty("pageLoadWaitTime") + " seconds");
    }
    catch (Exception e) {
      e.printStackTrace();
      writeToLogFile("ERROR", "Browser: Open Failure/Navigation cancelled, please check the application window.");
      writeToLogFile("Error", e.toString());
      testReporter("Red", e.toString());
      testStepFailed("Open Browser : " + browser.toUpperCase());
    }
  }
  
  public static void navigateTo(String url)
  {
    try
    {
      writeToLogFile("INFO", "Navigating to URL : " + url);
      browserWindow.navigate(url);
      writeToLogFile("INFO", "Navigation successful: " + url);
      testReporter("Green", "Navigated to url :''<font color='blue'>" + url + "</font><font color='green'>''");
    }
    catch (LaunchApplicationTimeoutException e) {
      testStepFailed("Page fail to load within in " + pageLoadWaitTime + " seconds");
    }
    catch (Exception e) {
      writeToLogFile("ERROR", "Browser: Open Failure/Navigation cancelled, please check the application window.");
      testStepFailed("Navigation to: " + url);
    }
  }
  
  public static void closeBrowser()
  {
    try
    {
      writeToLogFile("INFO", "Closing Browser...");
      deleteAllCookies();
      browserApplication = (BrowserApplication)desktop.find("/BrowserApplication");
      browserApplication.close();
      desktop = null;
      writeToLogFile("INFO", "Browser: Closed Successfully");
      testReporter("Green", "Closed All Opened Browser");      
      
    }
    catch (Exception e)
    {
      writeToLogFile("ERROR", "Browser: Close Failure");
      testStepFailed("Close Browser");
    }
  }
  
  public static void clickOn(String objLocator)
  {
    clickOn(objLocator, Boolean.valueOf(false));
  }
  
  public static void clickOn(String objLocator, Boolean stepColorDemarkation)
  {
    try
    {
    	parseidentifyByAndlocator(objLocator);
      waitForElement(objLocator);      
      switch (locType)
      {
      case CHK: 
        domTextField.click();
        break;
      case ELE: 
        domLink.click();
        break;
      case LNK: 
        domButton.click();
        break;
      case LST: 
        domForm.click();
        break;
      case FOM: default: 
        domElement.click();
      }
      
      if (stepColorDemarkation.booleanValue()) {
        testStepPassed("Clicked on ''<font color='blue'>" + locatorDescription + "</font><font color='green'>''" + identifier);
      } else {
        testStepPassed("Clicked on ''" + locatorDescription + "''" + identifier);
      }
    }
    catch (ObjectNotEnabledException ex) {
      testStepFailed(locatorDescription + identifier + " Object is not enabled: " + ex.getMessage());
    }
    catch (Exception ex)
    {
      testStepFailed(ex.getMessage());
    }
  }
  
  public static void typeKeys(String objLocator, String text)
  {
    try {
      waitForElement(objLocator);
      domTextField.typeKeys(text);
      testStepPassed("''<font color='blue'>" + text + "</font><font color='green'>'' was typed in ''" + locatorDescription + "''" + identifier);
    }
    catch (ObjectNotEnabledException ex)
    {
      testStepFailed("''" + locatorDescription + "''" + identifier + " Object is not enabled: " + ex.getMessage());
    }
    catch (Exception ex)
    {
      testStepFailed(ex.getMessage());
    }
  }
  
  public static void pressKeys(String objLocator, String text)
  {
    try
    {
      waitForElement(objLocator);
      domTextField.pressKeys(text);
      testStepPassed("''<font color='blue'>" + text + "</font><font color='green'>'' was typed in ''" + locatorDescription + "''" + identifier);
    }
    catch (ObjectNotEnabledException ex)
    {
      testStepFailed("''" + locatorDescription + "''" + identifier + " Object is not enabled: " + ex.getMessage());
    }
    catch (Exception ex)
    {
      testStepFailed(ex.getMessage());
    }
  }
  
  public static void setText(String objLocator, String text)
  {
    try
    {
      waitForElement(objLocator);
      domTextField.setText(text);
      testStepPassed("''<font color='blue'>" + text + "</font><font color='green'>'' was typed in ''" + locatorDescription + "''" + identifier);
    }
    catch (ObjectNotEnabledException ex)
    {
      testStepFailed("''" + locatorDescription + "''" + identifier + " Object is not enabled: " + ex.getMessage());
    }
    catch (Exception ex)
    {
      testStepFailed(ex.getMessage());
    }
  }
  
  public static void parseidentifyByAndlocator(String objLocator)
  {
    String[] objType;
    try {
      String[] webElement = new String[3];
      if (objLocator.contains("."))
      {
        webElement = objLocator.split("\\.", 5);
      }
      switch (webElement.length)
      {
      case 3: 
        application = webElement[0];
        window = webElement[1];
        locator = webElement[2];
        break;
      case 2: 
        application = getConfigProperty("OR");
        window = webElement[0];
        locator = webElement[1];
        break;
      default: 
        application = "/BrowserApplication";
        window = "//BrowserWindow";
        locator = objLocator;
      }
      
    }
    catch (Exception e)
    {
      application = "/BrowserApplication";
      window = "//BrowserWindow";
      locator = objLocator;
    }
    finally {
      String[] objType1;
      objType1 = new String[2];
      if (locator.contains("_"))
      {
        objType1 = locator.split("_", 2);
      }
      locatorDescription = objType1[1].replaceAll("_", " ");
      locatorDescription = convertToProperCase(locatorDescription);
      locType = LocatorType.valueOf(objType1[0].toUpperCase().trim());
      writeToLogFile("INFO", "Application : " + application);
      writeToLogFile("INFO", "window : " + window);
      writeToLogFile("INFO", "Locator : " + locator);
      writeToLogFile("INFO", "Locator description :" + locatorDescription);
    }
  }
  
  public static void identifyBy(String objLocator)
  {
    try
    {
      browserApplication = (BrowserApplication)desktop.find(application);
      browserWindow = (BrowserWindow)browserApplication.find(window);
      switch (locType)
      {
      case CHK: 
        domTextField = (DomTextField)browserWindow.find(locator);
        identifier = " text box";
        break;
      case ELE: 
        domLink = (DomLink)browserWindow.find(locator);
        identifier = " link";
        break;
      case FOM: 
        domCheckBox = (DomCheckBox)browserWindow.find(locator);
        identifier = " check box";
        break;
      case LNK: 
        domButton = (DomButton)browserWindow.find(locator);
        identifier = " button";
        break;
      case LST: 
        domForm = (DomForm)browserWindow.find(locator);
        identifier = " form";
        break;
      case TBL: 
        domListBox = (DomListBox)browserWindow.find(locator);
        identifier = " list box";
        break;
      case TXT: 
        break;
      
      default: 
        domElement = (DomElement)browserWindow.find(locator);
        identifier = " element";
      }
      
      writeToLogFile("INFO", "Identifier :" + identifier);
    }
    catch (ClassCastException e)
    {
      browserApplication = (BrowserApplication)desktop.find(application);
      browserWindow = (BrowserWindow)browserApplication.find(window);
      domElement = (DomElement)browserWindow.find(locator);
      identifier = " element";
      writeToLogFile("INFO", "Identifier :" + identifier);
    }
    catch (Exception e)
    {
      testStepFailed(e.toString());
    }
  }
  
  public static enum LocatorType
  {
    ELE, 
    TXT, 
    LNK, 
    CHK, 
    BTN, 
    FOM, 
    LST, 
    TBL;
  }
  







































  public static void waitForElement(String objName)
  {
    waitForElement(objName, elementLoadWaitTime);
  }
  
  public static void waitForElement(String objectName, int timeout)
  {
    try {
      parseidentifyByAndlocator(objectName);
      desktop.setOption("OPT_WAIT_RESOLVE_OBJDEF", Integer.valueOf(timeout * 1000));
      writeToLogFile("INFO", "waiting for the object : " + locatorDescription);
      identifyBy(objectName);
      writeToLogFile("INFO", locatorDescription + " is found");
    }
    catch (ObjectNotEnabledException e)
    {
      testStepFailed("Object is not enabled " + e.toString());
    }
    catch (ObjectNotFoundException e)
    {
      testStepFailed("Object Not Found in " + timeout + " seconds " + e.toString());
    }
    catch (Exception e)
    {
      testStepFailed(e.toString());
    }
    finally
    {
      desktop.setOption("OPT_WAIT_RESOLVE_OBJDEF", Integer.valueOf(elementLoadWaitTime * 1000));
    }
  }
  
























































  public static void verifyElementText(String objectLocator, String expectedText) {}
  























































  public static void findWebElement(String objectLocator)
  {
    parseidentifyByAndlocator(objectLocator);
    writeToLogFile("INFO", "waiting for the object : " + locatorDescription);
    identifyBy(objectLocator);
    writeToLogFile("INFO", locatorDescription + " is found");
  }
  
























  public static void typeIn(String objectLocator, String inputValue) {}
  
























  public static void sendkeys(String Locator, String inputValue) {}
  























  public static void refreshPage()
  {
    try
    {
      browserWindow = (BrowserWindow)desktop.find("//BrowserWindow");
      browserWindow.reload();
      writeToLogFile("INFO", "Sucessfully Refreshed browser");

    }
    catch (Exception e)
    {
      testStepFailed("Error refreshing browser");
      testStepFailed("Exception Error '" + e.toString() + "'");
    }
  }
  














































  public static void clickOnBackButton()
  {
    try
    {
      browserWindow = (BrowserWindow)desktop.find("//BrowserWindow");
      browserWindow.back();
      writeToLogFile("INFO", "Sucessfully moved to 'Back' page");

    }
    catch (Exception e)
    {
      testStepFailed("Error moving to 'Back' page");
      testStepFailed("Exception Error '" + e.toString() + "'");
    }
  }
  














































  public static void clickOnForwardButton() {}
  














































  public static void closeAlertWindow()
  {
    try
    {
      dialog = (Dialog)desktop.find("//Dialog");
      dialog.close();
      writeToLogFile("INFO", "Sucessfully closed the alert window");
      testReporter("Green", "Closed alert window");
    }
    catch (Exception e) {
      testStepFailed("Exception Error '" + e.toString() + "'");
    }
  }
  
  public static void alertOk()
  {
    try
    {
      pushButton = (PushButton)desktop.find("//Dialog//PushButton[@caption='OK']");
      pushButton.select();
      writeToLogFile("INFO", "Sucessfully clicked on Alert OK button");
      testReporter("Green", "Clicked on Alert OK button");
    }
    catch (Exception e)
    {
      testStepFailed("Exception Error '" + e.toString() + "'");
    }
  }
  
  public static void alertCancel()
  {
    try
    {
      pushButton = (PushButton)desktop.find("//Dialog//PushButton[@caption='CANCEL']");
      pushButton.select();
      writeToLogFile("INFO", "Sucessfully clicked on Alert Cancel button");
      testReporter("Green", "Clicked on Alert Cancel button");
    }
    catch (Exception e)
    {
      testStepFailed("Exception Error '" + e.toString() + "'");
    }
  }
  
  public static boolean isAlertWindowPresent()
  {
    try
    {
      desktop.exists("//Dialog");
      return true;
    }
    catch (Exception E) {}
    
    return false;
  }
  



































  public static void verifyElement(String objLocator) {}
  



































  public static void mouseOver(String objLocator) {}
  



































  public static void waitTime(long waittime)
  {
    writeToLogFile("INFO", "Waiting for " + waittime + " seconds...");
    try {
      Thread.sleep(waittime * 1000L);
    }
    catch (InterruptedException e)
    {
      writeToLogFile("ERROR", "Thread.sleep operation failed, during waitTime function call");
    }
  }
  

  public static void selectFromDropdown(String objLocator, String valueToSelect)
  {
    try
    {
      waitForElement(objLocator);
      domListBox.select(valueToSelect);
      testStepPassed("''<font color='blue'>" + valueToSelect + "</font><font color='green'>'' is selected from ''" + locatorDescription + "''" + identifier);
    }
    catch (ObjectNotEnabledException ex)
    {
      testStepFailed("''" + locatorDescription + "''" + identifier + " Object is not enabled: " + ex.getMessage());
    }
    catch (Exception ex)
    {
      testStepFailed(ex.getMessage());
    }
  }
  



































































  public static void selectFromDropdown(String objLocator, int indexNumber)
  {
    try
    {
      waitForElement(objLocator);
      domListBox.select(indexNumber);
      testStepPassed("<font color='green'> List item number ''</font><font color='blue'>" + indexNumber + "</font><font color='green'>'' is selected from ''" + locatorDescription + "''" + identifier);
    }
    catch (ObjectNotEnabledException ex)
    {
      testStepFailed("''" + locatorDescription + "''" + identifier + " Object is not enabled: " + ex.getMessage());
    }
    catch (Exception ex)
    {
      testStepFailed(ex.getMessage());
    }
  }
  


















































  public static void verifyPageTitle(String partialTitle) {}
  


















































  public static void verifyLinkText(String txt) {}
  


















































  public static void verifyAttribute(String objLocator, String attributeType, String expectedAttributeValue) {}
  


















































  public static void waitForText(String txt)
  {
    waitForText(txt, testLoadWaitTime);
  }
  











































  public static void waitForText(String txt, int timeout) {}
  










































  public static void switchToWindow(String objTitle) {}
  










































  public static void dragAndDrop(String sourceObjLocator, String destinationObjLocator) {}
  










































  public static void switchFrame(String fr) {}
  










































  public static void clearEditBox(String objLocator) {}
  










































  public static void rightClick(String objLocator) {}
  










































  public static void doubleClick(String objLocator) {}
  










































  public static boolean elementPresent(String objectLocator)
  {
    try
    {
      findWebElement(objectLocator);
      return true;
    }
    catch (ObjectNotFoundException e)
    {
      return false;
    }
    catch (Exception e)
    {
      testStepFailed(e.toString()); }
    return false;
  }
  

























  public static void verifyPageShouldContainText(String text) {}
  

























  public static void verifyPageShouldNotContainText(String text) {}
  
























  public static String getTextSelectedOption(String objLocator)
  {
    waitForElementToDisplay(objLocator, elementLoadWaitTime);
    String SelectText = "";
    












































    return SelectText;
  }
  































  public static void verifyTextFieldCount(String objLocator, int CountNumber) {}
  






























  public static void verifyTextValueNotCharacter(String objLocator)
  {
    waitForElement(objLocator, elementLoadWaitTime);
  }
  

























































  public static void verifyTextValueNotNumber(String objLocator)
  {
    waitForElement(objLocator, elementLoadWaitTime);
  }
  




































  public static void verifyAlertTextShouldContain(String expectedAlertText) {}
  




































  public static void verifyTextFieldShouldContain(String objectLocator, String expectedValue)
  {
    waitForElement(objectLocator, elementLoadWaitTime);
  }
  




























  public static void verifyTextFieldShouldNotContain(String objectLocator, String expectedValue) {}
  




























  public static void closeAllBrowser() {}
  




























  public static void closeChildBrowser(String windowTitle) {}
  



























  public static enum keys
  {
    ENTER, 
    SPACE, 
    ESCAPE, 
    CONTROL, 
    ALT, 
    BACKSPACE, 
    CANCEL, 
    DELETE, 
    PAGEDOWN, 
    PAGEUP, 
    TAB;
  }
  
































































































  public static String getText(String objLocator)
  {
    String getText = null;
    try
    {
      waitForElement(objLocator);
      switch (locType)
      {
      case CHK: 
        getText = domTextField.getText();
        break;
      case ELE: 
        getText = domLink.getText();
        break;
      case LST: 
        getText = domForm.getText();
        break;
      case FOM: case LNK: default: 
        getText = domElement.getText();
      }
      
      writeToLogFile("Info", "Sucessfully got the text '" + getText + "'");
    }
    catch (ObjectNotEnabledException ex)
    {
      testStepFailed("''" + locatorDescription + "''" + identifier + " Object is not enabled: " + ex.getMessage());
    }
    catch (Exception ex)
    {
      testStepFailed(ex.getMessage());
    }
    













































    return getText;
  }
  

  public static String getAttributeValue(String objLocator, String attributeName)
  {
    String getAttributeValue = null;
    













































    return getAttributeValue;
  }
  

  public static int getMatchingXpathCount(String objLocator)
  {
    List<DomElement> xpathCount = null;
    try
    {
      xpathCount = desktop.findAll(objLocator);
      writeToLogFile("Info", "Sucessfully got the matchingxPath Count'" + xpathCount + "'");
    }
    catch (ObjectNotEnabledException e)
    {
      testStepFailed("Object is not enabled " + e.toString());
    }
    catch (ObjectNotFoundException e)
    {
      testStepFailed("Object Not Found in " + elementLoadWaitTime + " seconds " + e.toString());
    }
    catch (Exception e)
    {
      testStepFailed(e.toString());
    }
    















































    return xpathCount.size();
  }
  
  public static void verifySelectOptionsSortOrder(String objLocator, String sortOrder)
  {
    verifySelectOptionsSortOrder(objLocator, sortOrder, "None");
  }
  























  public static void verifySelectOptionsSortOrder(String objLocator, String sortOrder, String excludeOption) {}
  























  public static void UnSelectFrame() {}
  























  public static void waitForAlertWindow(int timeout)
  {
    for (int i = 0; i <= timeout; i++)
    {
      if (isAlertWindowPresent()) {
        break;
      }
      


      waitTime(1L);
      
      if (i == timeout)
      {
        testStepFailed("Alert Window is not present within '" + timeout + "' timeout");
      }
    }
  }
  

  public static void waitForAlertWindow(String alertTitle, int timeout)
  {
    for (int i = 0; i <= timeout; i++)
    {
      if (isAlertWindowPresent()) {
        break;
      }
      


      waitTime(1L);
      
      if (i == timeout)
      {
        testStepFailed(alertTitle + " alert Window is not present within '" + timeout + "' timeout");
      }
    }
  }
  






























  public static void waitForChildWindow(String windowTitle, int timeout) {}
  






























  public static void waitForElementToDisplay(String objLocator, int timeout) {}
  





























  public static void clickOnSpecialElement(String objectLocator) {}
  





























  public static boolean isElementDisplayed(String objectLocator)
  {
    try
    {
      findWebElement(objectLocator);
      return true;
    }
    catch (ObjectNotFoundException e)
    {
      return false;
    }
    catch (Exception e)
    {
      testStepFailed(e.toString()); }
    return false;
  }
  












  public static boolean isTextPresent(String expectedText)
  {
    if (desktop.textExists(expectedText))
    {
      return true;
    }
    

    return false;
  }
  

  public static void deleteAllCookies()
  {
    try
    {
      browserApplication.clearCache(3);
      writeToLogFile("INFO", "Successfully deleted all cookies");
    }
    catch (Exception e)
    {
      windowreadyStateStatus = false;
      testStepFailed("Delete All cookies keyword exception error" + e.toString());
    }
  }
  
  public static void maximiseWindow()
  {
    try
    {
      browserApplication.maximize();
      writeToLogFile("INFO", "Successfully Maximised Browser Window");
    }
    catch (Exception e)
    {
      windowreadyStateStatus = false;
      testStepFailed("Maximise window keyword exception error" + e.toString());
    }
  }
  

  public static void selectCheckBox(String objLocator)
  {
    try
    {
      waitForElement(objLocator);
      domCheckBox.check();
      testStepPassed("Checked on the ''" + locatorDescription + "''" + " checkbox");
    }
    catch (ObjectNotEnabledException ex)
    {
      testStepFailed("''" + locatorDescription + "''" + identifier + " Object is not enabled: " + ex.getMessage());
    }
    catch (Exception ex)
    {
      testStepFailed(ex.getMessage());
    }
  }
  













  public static void unSelectCheckBox(String objLocator)
  {
    try
    {
      waitForElement(objLocator);
      domCheckBox.uncheck();
      testStepPassed("Unchecked the ''" + locatorDescription + "''" + " checkbox");
    }
    catch (ObjectNotEnabledException ex)
    {
      testStepFailed("''" + locatorDescription + "''" + identifier + " Object is not enabled: " + ex.getMessage());
    }
    catch (Exception ex)
    {
      testStepFailed(ex.getMessage());
    }
  }
  













  public static void verifyCheckBoxIsChecked(String objLocator)
  {
    try
    {
      waitForElement(objLocator);
      if (domCheckBox.isChecked())
      {
        testStepPassed("Verified that ''" + locatorDescription + "'' is checked");
      }
      else
      {
        testStepFailed("''" + locatorDescription + "'' is not checked");
      }
    }
    catch (ObjectNotEnabledException ex)
    {
      testStepFailed(locatorDescription + identifier + " Object is not enabled: " + ex.getMessage());
    }
    catch (Exception ex)
    {
      testStepFailed(ex.getMessage());
    }
  }
  

















  public static void verifyCheckBoxIsUnChecked(String objLocator)
  {
    try
    {
      waitForElement(objLocator);
      if (!domCheckBox.isChecked())
      {
        testStepPassed("Verified that ''" + locatorDescription + "'' is Unchecked");
      }
      else
      {
        testStepFailed("''" + locatorDescription + "'' is checked");
      }
    }
    catch (ObjectNotEnabledException ex)
    {
      testStepFailed("''" + locatorDescription + "''" + identifier + " Object is not enabled: " + ex.getMessage());
    }
    catch (Exception ex)
    {
      testStepFailed(ex.getMessage());
    }
  }
}
