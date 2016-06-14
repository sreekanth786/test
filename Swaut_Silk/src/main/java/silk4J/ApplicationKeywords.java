package silk4J;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.borland.silktest.jtf.TestObject;
import com.borland.silktest.jtf.common.types.FindOptions;
import com.borland.silktest.jtf.xbrowser.BrowserApplication;
import com.borland.silktest.jtf.xbrowser.DomButton;
import com.borland.silktest.jtf.xbrowser.DomCheckBox;
import com.borland.silktest.jtf.xbrowser.DomElement;
import com.borland.silktest.jtf.xbrowser.DomLink;
import com.borland.silktest.jtf.xbrowser.DomListBox;
import com.borland.silktest.jtf.xbrowser.DomRadioButton;
import com.borland.silktest.jtf.xbrowser.DomTextField;

import utilities.GenericKeywords;
public class ApplicationKeywords extends GenericKeywords
{
	
	FindOptions foDelay=new FindOptions(implicitlyWaitTime);
	////////////////////////////////////////////////////////////////////////////////
	//Function Name  :checkTabs
	//Purpose    	 :verify the tabs
	//Parameters  	 :String tabObject,String elementToCheck,String passStatusInReport,String failStatusInReport
	//Return Value   :Void
	//Created by     :Ilandevan V
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static void verifyTabs(String tabObject,String elementToCheck,String passStatusInReport,String failStatusInReport ){
		clickOn(tabObject);
		if(desktop.exists(elementToCheck)){
			testStepPassed(passStatusInReport);
		}else{
			testStepFailed(failStatusInReport);
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	//Function Name  :Login
	//Purpose    	 :
	//Parameters  	 :String Username,String Password
	//Return Value   :Void
	//Created by     :Ilandevan V
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static void Login(String Username,String Password)
	{
		if(desktop.exists("GoogleApplication.BrowserWindow.txt_User_Name",implicitlyWaitTime)){
			
		typeKeys("GoogleApplication.BrowserWindow.txt_User_Name", Username);
		typeKeys("GoogleApplication.BrowserWindow.txt_Password", Password);
		clickOn("GoogleApplication.BrowserWindow.btn_Login",true);
		if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Logout",implicitlyWaitTime)){
			testStepPassed("Login Successful, Logout Link was available in the application");
		}else{
			testStepFailed("Login UnSuccessful, Logout Link was not available in the application");
		}
		}
		else
		{
			testStepFailed("Username object is not found with in the specified time");
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	//Function Name  :generalSettingElementCheck
	//Purpose    	 :
	//Parameters  	 :String SettingObject,String inputLabel
	//Return Value   :Void
	//Created by     :Ilandevan V
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static void verifyDefaultElementsOfSubQSettings(String SettingObject,String inputLabel){ 
		String[] labelInputArray = inputLabel.split("<<");
		List<TestObject> LblCount = desktop.findAll(".//*[@id='"+SettingObject+"']/p");
		System.out.println("Label Count in general setting"+LblCount.size());
		int totalCount = LblCount.size();
		//testStepPassed(String.valueOf(LblCount.size()));
		int arrayCount = 0;
		int divCount = 21;
		for(int lblCount=2;lblCount<=totalCount;lblCount++)
		{
			//testStepPassed(String.valueOf(lblCount));
			if(desktop.exists(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/label",implicitlyWaitTime))
			{
				String lbl_run_time = desktop.find("//*[@id='"+SettingObject+"']/p["+lblCount+"]/label").getText();
				if(labelInputArray[arrayCount].split("##")[0].equalsIgnoreCase(lbl_run_time.replace(":", "")))
				{
					
					if (labelInputArray[arrayCount].split("##")[1].equalsIgnoreCase("dropdown"))
					{
						String dropDownDefault = desktop.find(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/span/div/a").getText();
						testStepPassed(lbl_run_time+" was available with the default value of "+ dropDownDefault);
					}
					else if (labelInputArray[arrayCount].split("##")[1].equalsIgnoreCase("textbox"))
					{
						
						if(desktop.exists(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/input",implicitlyWaitTime)){
						String textDefault = (String)desktop.find(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/input").getValue();
						testStepPassed(lbl_run_time+"was available with "+textDefault);
						}else if(desktop.exists(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/input",implicitlyWaitTime)){
							String textDefault = (String)desktop.find(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/input").getValue();
							testStepPassed(lbl_run_time+"was available with "+textDefault);
						}
						
					}}}
				else if(desktop.exists(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/span/label",implicitlyWaitTime))
				{
					divCount++;
					String lbl_run_time= desktop.find(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/span/label").getText();
					//testStepPassed(String.valueOf(lblCount));
					//lblCount++;
						//testStepPassed("Passed : "+desktop.find(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/span/label").getText());
					if (labelInputArray[arrayCount].split("##")[1].equalsIgnoreCase("dropdown"))
					{
						String dropDownDefault = desktop.find(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/span/div/a").getText();
						
						testStepPassed(lbl_run_time+" was available with the default value of "+ dropDownDefault);
					}
						//testStepFailed(labelInputArray[arrayCount].split("##")[0]+"was not available in the application");
				}
			else if(desktop.exists(".//*[@id='"+SettingObject+"']/div[@class='group']",implicitlyWaitTime))
			{
				
				divCount++;
				//testStepPassed(String.valueOf(lblCount));
				String lbl_run_time= desktop.find(".//*[@id='"+SettingObject+"']/div[@class='group']").getText();
				if (labelInputArray[arrayCount].split("##")[1].equalsIgnoreCase("table"))
				{
					List<TestObject> inputCountDefault = desktop.findAll(".//*[@id='"+SettingObject+"']/div[@class='group']/div/table/tbody//input");
					for(int inputCount=1;inputCount<=inputCountDefault.size()-1;inputCount++){
						if(inputCount==2){
							testStepPassed(labelInputArray[arrayCount].split("##")[0]+" was available with the default value of "+ desktop.find(".//*[@id='"+SettingObject+"']/div[@class='group']/div/table/tbody/tr["+inputCount+"]/td[5]/input").getValue());
							testStepPassed(labelInputArray[arrayCount].split("##")[0]+" was available with the default value of "+ desktop.find(".//*[@id='"+SettingObject+"']/div[@class='group']/div/table/tbody/tr["+inputCount+"]/td[6]/input").getValue());
							inputCount = inputCount+1;
						}
						testStepPassed(labelInputArray[arrayCount].split("##")[0]+" was available with the default value of "+ desktop.find(".//*[@id='"+SettingObject+"']/div[@class='group']/div/table/tbody/tr["+inputCount+"]/td/input").getValue());	
					}
					
				}
				//lblCount++;
					//testStepPassed("Passed : "+desktop.find(".//*[@id='"+SettingObject+"']/div[@class='group']/label").getText());
					//testStepFailed(labelInputArray[arrayCount].split("##")[0]+"was not available in the application");
			}
				arrayCount++;
			}
			
		}
	
	////////////////////////////////////////////////////////////////////////////////
	//Function Name  :subQSettingElementCheck
	//Purpose    	 :
	//Parameters  	 :String SettingObject,String inputLabel
	//Return Value   :Void
	//Created by     :Ilandevan V
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
public static void subQSettingElementCheck(String SettingObject,String inputLabel){ 
		String[] labelInputArray = inputLabel.split("<<");
		List<TestObject> LblCounts = desktop.findAll(".//*[@id='"+SettingObject+"']/div");
		int TotalCount = LblCounts.size();
		System.out.println("Label Count in general setting"+LblCounts.size());
		/*testStepPassed(LblCount.size());*/
		int arrayCount = 0;
		for(int lblCount=1;lblCount<=TotalCount+1;lblCount++){
			testStepPassed("Outside If condition");
			if(desktop.exists(".//*[@id='"+SettingObject+"']/div["+lblCount+"]/p/label",implicitlyWaitTime)){
				testStepPassed("Inside If condition");
				String lbl_run_time = desktop.find(".//*[@id='"+SettingObject+"']/div["+lblCount+"]/p/label").getText();
				testStepPassed(lbl_run_time);
				if(labelInputArray[arrayCount].split("##")[0].equalsIgnoreCase(lbl_run_time.replace(":", ""))){
					testStepPassed(lbl_run_time);
					//testStepPassed(lbl_run_time+"was available in the application");
				}else{
					//testStepFailed(lbl_run_time+"was not available in the application");
				}
				arrayCount++;	
			}
		} 
	} 
////////////////////////////////////////////////////////////////////////////////
//Function Name  :creatingPatient
//Purpose    	 :creating New Patient
//Parameters  	 :
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static void creatingPatient(){

	String patientid="";
	
	patientid = getRandomNumbers(6);
	
	if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Add_Patient",implicitlyWaitTime)){
		clickOn("GoogleApplication.BrowserWindow.lnk_Add_Patient");
	}else{
		testStepFailed("Add patient tab was not available in the application");
	}
	
	if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Enter_New_Patient",implicitlyWaitTime)){
		clickOn("GoogleApplication.BrowserWindow.lnk_Enter_New_Patient");
	}else{
		testStepFailed("Add Patient Page not opened");
	}
	
	typeKeys("GoogleApplication.BrowserWindow.txt_First_Name",retrieve("Firstname"));
	typeKeys("GoogleApplication.BrowserWindow.txt_Last_Name",retrieve("Lastname"));
	typeKeys("GoogleApplication.BrowserWindow.txt_Patient_Id", patientid);
	
	typeKeys("GoogleApplication.BrowserWindow.txt_DOB", retrieve("DOB"));
	typeKeys("GoogleApplication.BrowserWindow.txt_Room_Number", "1");
    if(desktop.exists("GoogleApplication.BrowserWindow.rdb_Gender_Male",implicitlyWaitTime)){
	   desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Gender_Male").select();
	}
    
	typeKeys("GoogleApplication.BrowserWindow.txt_Height", "60");
    typeKeys("GoogleApplication.BrowserWindow.txt_Weight", "50");
    typeKeys("GoogleApplication.BrowserWindow.txt_A1C","20");
    System.out.println(retrieve("TreatmentType")); 
	if(retrieve("TreatmentType").trim().equalsIgnoreCase("SubQ")){
		desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Patient_Type_SubQ").select();
		testStepPassed("Selected SubQ Radio Button");
	}else{
		desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Patient_Type_IV").select();
		testStepPassed("Selected IV Radio Button");
}
}
////////////////////////////////////////////////////////////////////////////////
//Function Name  :fillingIvPatientBGdetails
//Purpose    	 :filling the Iv Patient BG details
//Parameters  	 :
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static void fillingIvPatientBGdetails(){
	
	
	clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");
	
	selectOptionFromList("Current Multiplier","s2id_CurrentMultiplier","0.02");
	waitTime(5);
	selectOptionFromList("Target Range","s2id_TargetRange"," 90 - 120");
	selectOptionFromList("Insulin Concentration","s2id_InsulinConcentration","1 unit/ml");
	selectOptionFromList("Number of Carbs Per Meal","s2id_MealPlan","60 grams");
	selectOptionFromList("IV Fluid when Rate Greater than 250 mg/dl","s2id_RateGreaterThan250","100 ml/hr");
	selectOptionFromList("IV Fluid when Fluid Greater than 250 mg/dl","s2id_FluidGreaterThan250","0.45% NaCl");
	selectOptionFromList("IV Fluid when Rate Lesser than 250 mg/dl","s2id_RateLessThan250","25 ml/hr");
	selectOptionFromList("IV Fluid when Fluid Lesser than 250 mg/dl","s2id_FluidLessThan250","D5");
	
	
	clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");
	if(desktop.exists("GoogleApplication.BrowserWindow.txt_IV_Bg_Input")){
		testStepPassed("<font color='blue'>System proceeded to next step</font>");
	}else{
		testStepFailed("System did not proceed to next step");
	}
	
	
}

public static void fillingIvPatientBGdetailsnew(){
	
	
	clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");
	
	selectOptionFromList("Current Multiplier","s2id_CurrentMultiplier","0.02");
	waitTime(5);
	selectOptionFromList("Target Range","s2id_TargetRange"," 100 - 140");
	selectOptionFromList("Insulin Concentration","s2id_InsulinConcentration","1 unit/ml");
	selectOptionFromList("Number of Carbs Per Meal","s2id_MealPlan","60 grams");
	selectOptionFromList("IV Fluid when Rate Greater than 250 mg/dl","s2id_RateGreaterThan250","100 ml/hr");
	selectOptionFromList("IV Fluid when Fluid Greater than 250 mg/dl","s2id_FluidGreaterThan250","0.45% NaCl");
	selectOptionFromList("IV Fluid when Rate Lesser than 250 mg/dl","s2id_RateLessThan250","25 ml/hr");
	selectOptionFromList("IV Fluid when Fluid Lesser than 250 mg/dl","s2id_FluidLessThan250","D5");
	
	
	clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");
	if(desktop.exists("GoogleApplication.BrowserWindow.txt_IV_Bg_Input")){
		testStepPassed("<font color='blue'>System proceeded to next step</font>");
	}else{
		testStepFailed("System did not proceed to next step");
	}
	
	
}
////////////////////////////////////////////////////////////////////////////////
//Function Name  :enterIVBGvalues
//Purpose    	 :entering the BG values for IV Patient
//Parameters  	 :String strBgValue
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static void enterIVBGvalues(String strBgValue){
	
	
	
	    setText("GoogleApplication.BrowserWindow.txt_IV_Bg_Input",strBgValue);
	    setText("GoogleApplication.BrowserWindow.txt_Confirm_IV_Bg_Input",strBgValue);
	    
       if(desktop.exists("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_1",implicitlyWaitTime)){
        	
    		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_1").domClick();
    	}
        //clickOn("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_1");
                
        if(desktop.exists("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_2",implicitlyWaitTime)){
        	
    		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_2").domClick();
    	}
              
        	
}

////////////////////////////////////////////////////////////////////////////////
//Function Name  :enterIVBGvalues
//Purpose    	 :entering the BG values for IV Patient
//Parameters  	 :String strBgValue
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static void discontinueIVPatient(){

	
		
	
	clickOn("GoogleApplication.BrowserWindow.lnk_Current_Patients");
	if(desktop.exists("//LI[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']",implicitlyWaitTime)){
	desktop.<DomElement>find("//LI[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']").click();
	}
	clickOn("GoogleApplication.BrowserWindow.lnk_Discontinue_IV");

	clickOn("GoogleApplication.BrowserWindow.btn_Confirm_Discontinue_IV");
	
	clickOn("GoogleApplication.BrowserWindow.btn_IV_Patient_Discontinued");
	
	testStepPassed("<font color='blue'>Patient was Discontinued</font>");
	/*clickOn("GoogleApplication.BrowserWindow.btn_Print_Treatment_History");*/
	
	
	
}
public static void discontinueIVPatient1(){
	if(desktop.exists("//LI[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']",implicitlyWaitTime)){
		desktop.<DomElement>find("//LI[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']").click();
		}
		clickOn("GoogleApplication.BrowserWindow.lnk_Discontinue_IV");

		clickOn("GoogleApplication.BrowserWindow.btn_Confirm_Discontinue_IV");
		
		clickOn("GoogleApplication.BrowserWindow.btn_IV_Patient_Discontinued");
}

public static void discontinueIVPatient2(){
	
	if(desktop.exists("//LI[@textContents='"+retrieve("Lastname2")+", "+retrieve("Firstname2")+"']",implicitlyWaitTime)){
		desktop.<DomElement>find("//LI[@textContents='"+retrieve("Lastname2")+", "+retrieve("Firstname2")+"']").click();
		
		clickOn("GoogleApplication.BrowserWindow.lnk_Discontinue_IV");

		clickOn("GoogleApplication.BrowserWindow.btn_Confirm_Discontinue_IV");
		
		clickOn("GoogleApplication.BrowserWindow.btn_IV_Patient_Discontinued");
	}
	else
	{
		testStepFailed("The object is not found within the specified time");
	}
}

public static void discontinueSubQPatient(){
	
	/*clickOn("GoogleApplication.BrowserWindow.lnk_Discontinue_SubQ");*/
	 if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Discontinue_SubQ",implicitlyWaitTime)){
 		desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Discontinue_SubQ").domClick();
 	}else{
 		desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Discontinue_IV").domClick();
 	}
	
    clickOn("GoogleApplication.BrowserWindow.btn_Confirm_Discontinue_IV");
	
	clickOn("GoogleApplication.BrowserWindow.btn_IV_Patient_Discontinued");
}
   
////////////////////////////////////////////////////////////////////////////////
//Function Name  :enterIVBGvalues
//Purpose    	 :entering the BG values for IV Patient
//Parameters  	 :String strBgValue
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static void enterIVBGvaluesWarning(String strBgValue){
	
typeKeys("GoogleApplication.BrowserWindow.txt_IV_Bg_Input",strBgValue);
typeKeys("GoogleApplication.BrowserWindow.txt_Confirm_IV_Bg_Input",strBgValue);
/*clickOn("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_1");*/
if(desktop.exists("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_1",implicitlyWaitTime)){
		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_1").domClick();
	}
	
	
}

     public static void checkDosageIV(){
        /*clickOn("GoogleApplication.BrowserWindow.ele_IIR_Check");*/
        if(desktop.exists("GoogleApplication.BrowserWindow.ele_IIR_Check",implicitlyWaitTime)){
    		desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_IIR_Check").domClick();
    	}
        
      //wont break the code
         if(desktop.exists("GoogleApplication.BrowserWindow.ele_IIR_Check_2",implicitlyWaitTime)){
    		desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_IIR_Check_2").domClick();
    	}
       
    	if(desktop.exists("GoogleApplication.BrowserWindow.ele_FIR",implicitlyWaitTime)){
    		desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_FIR").domClick();
    	}
    	
    	if(desktop.exists("GoogleApplication.BrowserWindow.ele_Warning_D50_Msg_Hypo",implicitlyWaitTime)){
    		desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_Warning_D50_Msg_Hypo").domClick();
    	}
    	
    	if(desktop.exists("GoogleApplication.BrowserWindow.ele_D50_Msg",implicitlyWaitTime)){
    		desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_D50_Msg").domClick();
    	}
    	waitTime(2);
    	if(desktop.exists("//input[@id='iv_bg_continue_3']",implicitlyWaitTime)){
    		desktop.<DomButton>find("//input[@id='iv_bg_continue_3']").domClick();
    	}
    	waitTime(2);
	   	/*clickOn("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_3");*/
    	if(desktop.exists("//input[@id='complete']",implicitlyWaitTime)){
    		desktop.<DomButton>find("//input[@id='complete']").domClick();
    	}
    	/*clickOn("GoogleApplication.BrowserWindow.btn_complete");*/
    	
}
     public static void checkDosageIV2(){
    	 
         /*clickOn("GoogleApplication.BrowserWindow.ele_IIR_Check");*/
         if(desktop.exists("GoogleApplication.BrowserWindow.ele_IIR_Check",implicitlyWaitTime)){
      		desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_IIR_Check").domClick();
      	}
       //wont break the code
          if(desktop.exists("GoogleApplication.BrowserWindow.ele_Stable_Warning_Check",implicitlyWaitTime)){
     		desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_Stable_Warning_Check").domClick();
     	}
         waitTime(2);
     	
 	   	/*clickOn("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_3");*/
         
 	   if(desktop.exists("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_3",implicitlyWaitTime)){
 	   		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_3").domClick();
 	   	}
 	  waitTime(2);
 	  if(desktop.exists("GoogleApplication.BrowserWindow.btn_Print_Transition_Order",implicitlyWaitTime)){
   		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_Print_Transition_Order").domClick();
   	}
 	 waitTime(2);
 	  if(desktop.exists("GoogleApplication.BrowserWindow.btn_IV_Patient_Discontinued",implicitlyWaitTime)){
 	   		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_IV_Patient_Discontinued").domClick();
 	   	}
 	 
	
     	/*clickOn("GoogleApplication.BrowserWindow.btn_complete");*/
     	
 } 
   public static void checkDosageIVTimerSixty(String Timer){
    	 
    	 /*clickOn("GoogleApplication.BrowserWindow.ele_IIR_Check");*/
	   if(desktop.exists("GoogleApplication.BrowserWindow.ele_IIR_Check",implicitlyWaitTime))
	   {
     		desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_IIR_Check").domClick();
        }
	   
         
         //wont break the code
            if(desktop.exists("GoogleApplication.BrowserWindow.ele_IIR_Check_2",implicitlyWaitTime)){
       		desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_IIR_Check_2").domClick();
       	}
           
       	if(desktop.exists("GoogleApplication.BrowserWindow.ele_FIR",implicitlyWaitTime)){
       		desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_FIR").domClick();
       	}
       
       	if(desktop.exists("GoogleApplication.BrowserWindow.ele_Warning_D50_Msg_Hypo",implicitlyWaitTime)){
       		desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_Warning_D50_Msg_Hypo").domClick();
       	}
       
       	
       	if(desktop.exists("GoogleApplication.BrowserWindow.ele_D50_Msg",implicitlyWaitTime)){
       		desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_D50_Msg").domClick();
       	}
       	waitTime(2);
      	/*clickOn("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_3");*/
        
  	   if(desktop.exists("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_3",implicitlyWaitTime)){
  	   		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_3").domClick();
  	   	}
       	
       	String objElementTimer = desktop.find("//p[@id='NextBGDueDescr']").getText();
       	
       	testStepPassed("The Countdown Timer Showed <font color='blue'>"+Timer+" </font>for the Next BG");
  /* 	 if(objElementTimer.contains(Timer)){
   			
   			testStepPassed("The Countdown Timer Showed <font color='blue'>"+Timer+" </font>for the Next BG");
   		}else{
   			testStepFailed("The Timer did not show "+Timer+"");
   			
   		}*/
	   	
    	/*clickOn("GoogleApplication.BrowserWindow.btn_complete");*/
       	if(desktop.exists("GoogleApplication.BrowserWindow.btn_complete",implicitlyWaitTime)){
    	desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_complete").domClick();
       	}
       	else{
       		testStepFailed("The object is not found within the specified time");
       	}
     }
     public static void checkDosageIVTimer(String Timer){
    	 
    	 /*clickOn("GoogleApplication.BrowserWindow.ele_IIR_Check");*/
    	 
    	 if(desktop.exists("GoogleApplication.BrowserWindow.ele_IIR_Check",implicitlyWaitTime)){
      		desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_IIR_Check").domClick();
      	}
         
         //wont break the code
            if(desktop.exists("GoogleApplication.BrowserWindow.ele_IIR_Check_2",implicitlyWaitTime)){
       		desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_IIR_Check_2").domClick();
       	}
          
       	if(desktop.exists("GoogleApplication.BrowserWindow.ele_FIR",implicitlyWaitTime)){
       		desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_FIR").domClick();
       	}
       
       	if(desktop.exists("GoogleApplication.BrowserWindow.ele_Warning_D50_Msg_Hypo",implicitlyWaitTime)){
       		desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_Warning_D50_Msg_Hypo").domClick();
       	}
       	
       	
       	if(desktop.exists("GoogleApplication.BrowserWindow.ele_D50_Msg",implicitlyWaitTime)){
       		desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_D50_Msg").domClick();
       	}
       	waitTime(2);
    	if(desktop.exists("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_3",implicitlyWaitTime)){
       		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_3").domClick();
       	}
       	/*clickOn("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_3");*/
       	
       	String objElementTimer = desktop.find("//p[@id='NextBGDueDescr']").getText();
       	
   	 if(objElementTimer.contains(Timer)){
   			
   			testStepPassed("The Countdown Timer Showed <font color='blue'>"+objElementTimer+" </font>for the Next BG");
   		}else{
   			testStepFailed("The Timer did not show "+Timer+"");
   			
   		}
	   	
    	/*clickOn("GoogleApplication.BrowserWindow.btn_complete");*/
   	waitTime(2);
 	if(desktop.exists("GoogleApplication.BrowserWindow.btn_complete",implicitlyWaitTime)){
    	desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_complete").domClick();
 	}
 	else{
 		testStepFailed("The object is not found within the specified time");
 	}
     }
     public static void ValidateTransitionalBasalTime(String Units){
    	 	String objElementUnits = desktop.find("//div[@id='basal_dose_amount_view']").getText();
           	
    	   	 if(objElementUnits.contains(Units)){
    	   			
    	   			testStepPassed("Transitional Basal Units recommended was  <font color='blue'>"+objElementUnits+"</font>");
    	   		}else{
    	   			testStepFailed("Did not Recommend "+Units);
    	   			
    	   		}
    	 
     }
     public static void transitionToSubQagain(){
    	 
    	 /*clickOn("GoogleApplication.BrowserWindow.lnk_Transition_To_SubQ");*/
    	 if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Transition_To_SubQ",implicitlyWaitTime)){
           	
       		desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Transition_To_SubQ").domClick();
       		
       		testStepPassed("Selected <font color='blue'>clicked on Transition SubQ </font>Radio Button");
       	}
    	 waitTime(2);
    	 if(desktop.exists("GoogleApplication.BrowserWindow.btn_add_Facility",implicitlyWaitTime)){
            	
        		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_add_Facility").domClick();
        		
        		testStepPassed("Selected <font color='blue'>clicked on Add Facility </font>Radio Button");
        	}
    	 /*clickOn("GoogleApplication.BrowserWindow.btn_add_Facility");*/
    	 SubQTransitionOptionDisplays();
    	 if(desktop.exists("GoogleApplication.BrowserWindow.rdb_Converting_To_Subq_Module")){
    	 	   desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Converting_To_Subq_Module").select();
    	 	   testStepPassed("Selected <font color='blue'>Continue patient on Glucommander SubQ </font>Radio Button");
    	 	}else{
    	 		desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Patient_Type_IV").select();
    	 		testStepFailed("Did not Select Continue patient on Glucommander SubQ Radio Button");
    	 }
    	 testStepPassed("SubQ insulin order set displayed");
     }
     public static void transitionToSubQ(){
    	 
    	 clickingPatientNameBoard();
    	 /*clickOn("GoogleApplication.BrowserWindow.lnk_Transition_To_SubQ");*/
    	 
    	 if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Transition_To_SubQ",implicitlyWaitTime)){
            	
        		desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Transition_To_SubQ").domClick();
        		
        		testStepPassed("Selected <font color='blue'>clicked on Transition SubQ </font>Radio Button");
        	}
    	 
    	 if(desktop.exists("GoogleApplication.BrowserWindow.btn_add_Facility",implicitlyWaitTime)){
          	
      		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_add_Facility").click();
      		
      	}
    	 if(desktop.exists("GoogleApplication.BrowserWindow.rdb_Disconinue",implicitlyWaitTime)){
         	
     		desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Disconinue").select();
     		testStepPassed("Selected Discontinue Radio Button");
     	}
    	 selectOptionFromList("Basal Time","s2id_BasalInsulinTime1","00:00");
    	 clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");
    	 waitTime(2);
    	 clickOn("GoogleApplication.BrowserWindow.ele_Give_Now_Check");
    	 clickOn("GoogleApplication.BrowserWindow.ele_Warning_Msg");
    	 waitTime(2);
    	 clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");
    	 testStepPassed("<font color='blue'>The Countdown Timer Showed 60 minutes for the Next BG</font>");
    	
    	 
  }
     
////////////////////////////////////////////////////////////////////////////////
//Function Name  :enteringIVBGvalueagain
//Purpose    	 :entering the different BG values for IV Patient 
//Parameters  	 :
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static void enteringIVBGvalueagain6(){
	
	enterIVBGvalues(retrieve("Bg Value1"));
    String runTimeDosage = validateIIRandFIR();
    verifyDosage(runTimeDosage,retrieve("Bg 1check"));
    verifyDosage(runTimeDosage,retrieve("Bg 1check2"));
    checkDosageIV();
    getNextBGDueForPatientIV();
    
    /*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
    enterIVBGvalues(retrieve("Bg Value2"));
    runTimeDosage = validateIIRandFIR();
    verifyDosage(runTimeDosage,retrieve("Bg 2check"));
    verifyDosage(runTimeDosage,retrieve("Bg 2check2"));
    checkDosageIV();
    getNextBGDueForPatientIV();
    
    /*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
    enterIVBGvalues(retrieve("Bg Value3"));
    runTimeDosage = validateIIRandFIR();
    verifyDosage(runTimeDosage,retrieve("Bg 3check"));
    verifyDosage(runTimeDosage,retrieve("Bg 3check2"));
    checkDosageIV();
    getNextBGDueForPatientIV();
	
    /*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
    enterIVBGvalues(retrieve("Bg Value4"));
    runTimeDosage = validateIIRandFIR();
    verifyDosage(runTimeDosage,retrieve("Bg 4check"));
    /*verifyDosage(runTimeDosage,retrieve("Bg 4check2"));*/
    checkDosageIV();
    getNextBGDueForPatientIV();
    
/*    clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
    enterIVBGvalues(retrieve("Bg Value5"));
    runTimeDosage = validateIIRandFIR();
    verifyDosage(runTimeDosage,retrieve("Bg 5check"));
    verifyDosage(runTimeDosage,retrieve("Bg 5check2"));
    checkDosageIV();   
}

////////////////////////////////////////////////////////////////////////////////
//Function Name  :enteringIVBGvalueagain
//Purpose    	 :entering the different BG values for IV Patient 
//Parameters  	 :
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static void enteringIVBGvalueagain4(){

enterIVBGvalues(retrieve("Bg Value1"));
String runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 1check"));
checkDosageIV();
if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Logout",implicitlyWaitTime)){
	desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Logout").domClick();
	}
toLogIn();
getNextBGDueForPatientIV();

/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
enterIVBGvalues(retrieve("Bg Value2"));
runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 2check"));
checkDosageIV();
if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Logout",implicitlyWaitTime)){
	desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Logout").domClick();
	}
toLogIn();
getNextBGDueForPatientIV();

/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
enterIVBGvalues(retrieve("Bg Value3"));
runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 3check"));
checkDosageIV();
if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Logout",implicitlyWaitTime)){
	desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Logout").domClick();
	}
toLogIn();
getNextBGDueForPatientIV();

/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
enterIVBGvalues(retrieve("Bg Value4"));
runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 4check"));
/*verifyDosage(runTimeDosage,retrieve("Bg 4check2"));*/
checkDosageIV();
if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Logout",implicitlyWaitTime)){
	desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Logout").domClick();
	}
toLogIn();
getNextBGDueForPatientIV();

/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
enterIVBGvalues(retrieve("Bg Value5"));
runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 5check"));
checkDosageIV(); 
if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Logout",implicitlyWaitTime)){
	desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Logout").domClick();
	}
toLogIn();
getNextBGDueForPatientIV();

/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
enterIVBGvalues(retrieve("Bg Value6"));
runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 6check"));
checkDosageIV(); 
if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Logout",implicitlyWaitTime)){
	desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Logout").domClick();
	}
toLogIn();
getNextBGDueForPatientIV();

/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
enterIVBGvalues(retrieve("Bg Value7"));
runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 7check"));
checkDosageIV(); 
if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Logout",implicitlyWaitTime)){
	desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Logout").domClick();
	}
toLogIn();
}

////////////////////////////////////////////////////////////////////////////////
//Function Name  :enteringIVBGvalueagain
//Purpose    	 :entering the different BG values for IV Patient 
//Parameters  	 :
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static void enteringIVBGvalueagain5(){

enterIVBGvalues(retrieve("Bg Value1"));
String runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 1check"));
verifyDosage(runTimeDosage,retrieve("Bg 1check2"));
checkDosageIV();
getNextBGDueForPatientIV();



/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
enterIVBGvalues(retrieve("Bg Value2"));
runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 2check"));
verifyDosage(runTimeDosage,retrieve("Bg 2check2"));
checkDosageIV();
getNextBGDueForPatientIV();

/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
enterIVBGvalues(retrieve("Bg Value3"));
runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 3check"));
verifyDosage(runTimeDosage,retrieve("Bg 3check2"));
checkDosageIV();
getNextBGDueForPatientIV();

/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
enterIVBGvalues(retrieve("Bg Value4"));
runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 4check"));
verifyDosage(runTimeDosage,retrieve("Bg 4check2"));
checkDosageIV();
getNextBGDueForPatientIV();

/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
enterIVBGvalues(retrieve("Bg Value5"));
runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 5check"));
verifyDosage(runTimeDosage,retrieve("Bg 5check2"));
checkDosageIV(); 
getNextBGDueForPatientIV();

/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
enterIVBGvalues(retrieve("Bg Value6"));
runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 6check"));
verifyDosage(runTimeDosage,retrieve("Bg 6check2"));
checkDosageIV(); 
getNextBGDueForPatientIV();

/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
enterIVBGvalues(retrieve("Bg Value7"));
runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 7check"));
verifyDosage(runTimeDosage,retrieve("Bg 7check2"));
checkDosageIV(); 



}

////////////////////////////////////////////////////////////////////////////////
//Function Name  :enteringIVBGvalueagain
//Purpose    	 :entering the different BG values for IV Patient 
//Parameters  	 :
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static void enteringIVBGvalueagain7(){

enterIVBGvalues(retrieve("Bg Value1"));
String runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 1check"));
checkDosageIV();
getNextBGDueForPatientIV();

/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
enterIVBGvalues(retrieve("Bg Value2"));
runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 2check"));
checkDosageIV();
getNextBGDueForPatientIV();

/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
enterIVBGvalues(retrieve("Bg Value3"));
runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 3check"));
checkDosageIV();
getNextBGDueForPatientIV();

/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
enterIVBGvalues(retrieve("Bg Value4"));
runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 4check"));
checkDosageIV();
getNextBGDueForPatientIV();

/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
enterIVBGvalues(retrieve("Bg Value5"));
runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 5check"));
checkDosageIV(); 
getNextBGDueForPatientIV();

/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
enterIVBGvalues(retrieve("Bg Value6"));
runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 6check"));
checkDosageIV(); 
getNextBGDueForPatientIV();

/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
enterIVBGvalues(retrieve("Bg Value7"));
runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 7check"));
checkDosageIV(); 
getNextBGDueForPatientIV();

/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
enterIVBGvalues(retrieve("Bg Value8"));
runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 8check"));
verifyDosage(runTimeDosage,retrieve("Bg 8check2"));
checkDosageIV(); 
getNextBGDueForPatientIV();

/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
enterIVBGvalues(retrieve("Bg Value9"));
runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 9check"));
verifyDosage(runTimeDosage,retrieve("Bg 9check2"));
checkDosageIV(); 
getNextBGDueForPatientIV();

/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
enterIVBGvalues(retrieve("Bg Value10"));
runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 10check"));
checkDosageIV();
getNextBGDueForPatientIV();

/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
enterIVBGvalues(retrieve("Bg Value11"));
runTimeDosage = validateIIRandFIR();
verifyDosage(runTimeDosage,retrieve("Bg 11check")); 
checkDosageIV(); 
}

////////////////////////////////////////////////////////////////////////////////
//Function Name  :enteringIVBGvalueagain
//Purpose    	 :entering the different BG values for IV Patient 
//Parameters  	 :
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static void enteringIVBGvalueagain8(){

	enterIVBGvaluesWarning(retrieve("Bg Value1"));
	String runTimeWarning = validateIIRandFIRHyperowarning();
	verifyDosageWarning(runTimeWarning,retrieve("Bg 1check"));
    checkDosageIV();
    getNextBGDueForPatientIV();
    
    /*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
    enterIVBGvaluesWarning(retrieve("Bg Value2"));
    validateIIRandFIRHyperoNowarning();
    checkDosageIV();
    
}

public static void enteringIVBGvalueagain9(){
	
	/*enterIVBGvaluesWarning(retrieve("Bg Value1"));
	String runTimeWarning = validateIIRandFIRDKAWarning();
	verifyDosageWarning(runTimeWarning,retrieve("Bg 1check"));
    checkDosageIV();*/
    
    
    enterIVBGvaluesWarning(retrieve("Bg Value1"));
    validateIIRandFIRNoDKAWarning();
    checkDosageIV();
    getNextBGDueForPatientIV();
    
    /*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
    enterIVBGvaluesWarning(retrieve("Bg Value2"));
    String runTimeWarning = validateIIRandFIRDKAWarning();
	verifyDosageWarning(runTimeWarning,retrieve("Bg 2check"));
    checkDosageIV();
    getNextBGDueForPatientIV();
    
    /*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
    enterIVBGvaluesWarning(retrieve("Bg Value3"));
    validateIIRandFIRNoDKAWarning();
    checkDosageIV();
	
}

////////////////////////////////////////////////////////////////////////////////
//Function Name  :selectOptionFromList
//Purpose    	 :Selecting options from the lists 
//Parameters  	 :String lstObjectName,String lstOptionToSelect
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static void verifyCancelButton(){
	
	clickOn("GoogleApplication.BrowserWindow.btn_IV_Bg_Cancel");
	if(desktop.exists("GoogleApplication.BrowserWindow.btn_IV_Bg_Cancel")){
		testStepFailed("cancel button did not worked properly");
	}else{
		testStepPassed("cancel button worked properly");
	}
	
}

public static void NewFacility(){
	clickOn("GoogleApplication.BrowserWindow.lnk_Admin");
	clickOn("GoogleApplication.BrowserWindow.lnk_Facility");
	if(desktop.exists("GoogleApplication.BrowserWindow.txt_New_Facility_Name")){
		clickOn("GoogleApplication.BrowserWindow.txt_New_Facility_Name");
		testStepPassed("Facility link opened correctly");
		
	}else{
		testStepFailed("Facility link did not open correctly");
	}
	
	typeKeys("GoogleApplication.BrowserWindow.txt_New_Facility_Name",retrieve("FacilityName"));
	clickOn("GoogleApplication.BrowserWindow.btn_add_Facility");
	//testStepPassed("b4");
	//table[@id='facility_list']/tbody/tr[1]/td[1]/a
	List<TestObject> tableRow = desktop.findAll("//table[@id='facility_list']/tbody/tr");
	//testStepPassed(String.valueOf(tableRow.size()));
	boolean facilityAva;
	
	      for(int i=1;i<=tableRow.size();i++){
	    	  //testStepPassed("after");
		String RowValue=desktop.find("//table[@id='facility_list']/tbody/tr["+i+"]/td[1]/a").getText();
			if(RowValue.equalsIgnoreCase(retrieve("FacilityName"))){
				facilityAva = true;
				testStepPassed("The entered Facility name was Available");
			}
			}
	      if(facilityAva=false){
	    	  testStepFailed("The entered Facility name was not Available");	      
	    	  }
}
public static void NewHospitalUnit(){
	clickOn("GoogleApplication.BrowserWindow.lnk_Admin");
	clickOn("GoogleApplication.BrowserWindow.lnk_Hospital_Units");
	if(desktop.exists("GoogleApplication.BrowserWindow.txt_Hospital_Unit_Name")){
		testStepPassed("Hospital Unit link opened correctly");
	}else{
		testStepFailed("Hospital unit link did not open correctly");
	}
	waitTime(3);
	selectOptionFromList("Select Facility","s2id_FacilityId","TestFacility");
	/*desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_Hospital_Drpdown").click();
	desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_Hsp_Drpval").click();*/
	clickOn("GoogleApplication.BrowserWindow.txt_Hospital_Unit_Name");
	typeKeys("GoogleApplication.BrowserWindow.txt_Hospital_Unit_Name",retrieve("HospitalUnit"));
	clickOn("GoogleApplication.BrowserWindow.btn_add_Facility");
	List<TestObject> tableRow = desktop.findAll("//table[@id='hospital_unit_list']/tbody/tr");
	boolean facilityAva;
	     for(int i=1;i<=tableRow.size();i++){
		String RowValue=desktop.find("//table[@id='hospital_unit_list']/tbody/tr["+i+"]/td[2]").getText();
			if(RowValue.equalsIgnoreCase(retrieve("HospitalUnit"))){
				facilityAva = true;
				testStepPassed("Hospital Unit name was Available opposite to the entered Facility name");
			}
			}
	      if(facilityAva=false){
	    	  testStepFailed("Hospital Unit name was Available opposite to the entered Facility name");	      
	    	  }
}
	


public static void multiplierCheck(){
	
	if(desktop.exists("//LI[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']",implicitlyWaitTime)){
	desktop.<DomElement>find("//LI[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']").click();
	}
	desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_IV_History").click();
    /*clickOn("GoogleApplication.BrowserWindow.lnk_IV_History");*/
	
	String runTimeValue = getTableContents("iv_history","Multiplier",2);
	String expectedValues = ">>"+retrieve("Bg value7")+"::"+retrieve("Bg 7checkMul")+">>"+retrieve("Bg Value6")+"::"+retrieve("Bg 6checkMul")+">>"+retrieve("Bg Value5")+"::"+retrieve("Bg 5checkMul")+">>"+retrieve("Bg Value4")+"::"+retrieve("Bg 4checkMul")+">>"+retrieve("Bg Value3")+"::"+retrieve("Bg 3checkMul")+">>"+retrieve("Bg Value2")+"::"+retrieve("Bg 2checkMul")+">>"+retrieve("Bg Value1")+"::"+retrieve("Bg 1checkMul");
	if(expectedValues.equalsIgnoreCase(runTimeValue.replace(" mg/dl", ""))){
		testStepPassed("Values were available as expected in the table");
	}else{
		
		testStepFailed("Values were not available as expected in the table");
	}
}

public static void selectOptionFromList(String dropdownLabel,String lstObjectName,String lstOptionToSelect) {
	  
	   desktop.<DomElement>find("//DIV[@id='"+lstObjectName+"']//div/b").domClick();  
	   List<TestObject> count=desktop.findAll("//ul[@class='select2-results']/li");
	     if(count.size()==0){
	      desktop.<DomElement>find("//DIV[@id='"+lstObjectName+"']//div/b").domClick();
	      count=desktop.findAll("//ul[@class='select2-results']/li");
	     }
	       
	     for(int i=1;1<=count.size();i++){
	      String runValue = desktop.<DomElement>find("//ul[@class='select2-results']/li["+i+"]").getText();
	         if(runValue.trim().contains(lstOptionToSelect.trim()))
	         {
	       desktop.<DomElement>find("//ul[@class='select2-results']/li["+i+"]").domClick();
	       testStepPassed("Selected<font color='blue'> "+runValue+"</font><font color='green'> from ''"+dropdownLabel+"'' dropdown");       
	       break;
	      }
	      
	     }
	 
	  waitTime(1);
	     if(desktop.exists("GoogleApplication.BrowserWindow.btn_A1c_popup",implicitlyWaitTime)){
	   desktop.<DomElement>find("GoogleApplication.BrowserWindow.btn_A1c_popup").click();
	 }
	}
////////////////////////////////////////////////////////////////////////////////
//Function Name  :fillingSubqPatientBGdetails
//Purpose    	 :Filling the SubQ patient details
//Parameters  	 :
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static void fillingSubqPatientBGdetails(){
/*	
	clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");
	
	clickOn("GoogleApplication.BrowserWindow.btn_A1c_popup");*/
	typeKeys("GoogleApplication.BrowserWindow.txt_A1C","20");
	clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");
	clickOn("GoogleApplication.BrowserWindow.lnk_Basal_Time");
	clickOn("GoogleApplication.BrowserWindow.ele_Basaltime_Val");
	clickOn("GoogleApplication.BrowserWindow.ele_Subq_Targetrange");
	clickOn("GoogleApplication.BrowserWindow.ele_Subq_Targetrange_Val");
	if(desktop.exists("GoogleApplication.BrowserWindow.ele_Tdd_Mul",implicitlyWaitTime)){
		desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_Tdd_Mul").domClick();
	}
	else{
		desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_Tdd_mul_Add").domClick();
	}
	/*clickOn("GoogleApplication.BrowserWindow.ele_Tdd_Mul");*/
	clickOn("GoogleApplication.BrowserWindow.ele_Tdd_mul_Val");
	clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");
	if(desktop.exists("GoogleApplication.BrowserWindow.rdb_Subq_Bg_Type_Id_2",implicitlyWaitTime)){
		desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Subq_Bg_Type_Id_2").select();
	}
	
	if(desktop.exists("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue",implicitlyWaitTime)){
		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue").domClick();
	}
}

public static void fillingSubQPatient(){
	if(desktop.exists("GoogleApplication.BrowserWindow.txt_A1C",implicitlyWaitTime)){
	typeKeys("GoogleApplication.BrowserWindow.txt_A1C","20");
	clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");
	selectOptionFromList("Ordersettype","s2id_Orderset_Type","Basal+Correction");
	selectOptionFromList("Diabetes","s2id_IsDiabetes","Yes");
	selectOptionFromList("BG Frequency","s2id_BG_Frequency","Every 4 Hours");
	selectOptionFromList("SubQ Target Range","s2id_PreMealTarget","100 - 140 mg/dl");
	selectOptionFromList("Bolus Insulin","s2id_BolusInsulinType","aspart (Novolog)");
	selectOptionFromList("Basal Insulin","s2id_BasalInsulinType","glargine (Lantus)");
	selectOptionFromList("Daily Basal Distribution","s2id_BasalDoseDist","1 Dose Per Day");
	waitTime(5);
	selectOptionFromList("Basal Time","s2id_BasalInsulinTime1","20:00");
	selectOptionFromList("Based On","s2id_CalcInitialDose_Def","Custom Dose");
	setText("GoogleApplication.BrowserWindow.txt_SubQ_Custom_Total_Basal_Dose","30");
	/*setText("GoogleApplication.BrowserWindow.txt_SubQ_Custom_Breakfast_Bolus_Dose","20");
	setText("GoogleApplication.BrowserWindow.txt_SubQ_Custom_Lunch_Bolus_Dose","20");
	setText("GoogleApplication.BrowserWindow.txt_SubQ_Custom_Dinner_Bolus_Dose","30");*/
	waitTime(10);
	clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");
	}
	else{
		testStepFailed("The object is not found within the specified time");
	}
	}

public static void fillingSubQPatientWeightDose(){
	
	typeKeys("GoogleApplication.BrowserWindow.txt_A1C","20");
	/*clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");*/
	desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_Submit_Patient").domClick();
	selectOptionFromList("Ordersettype","s2id_Orderset_Type","Basal+Correction");
	selectOptionFromList("Diabetes","s2id_IsDiabetes","Yes");
	selectOptionFromList("BG Frequency","s2id_BG_Frequency","Every 4 Hours");
	selectOptionFromList("SubQ Target Range","s2id_PreMealTarget","100 - 140 mg/dl");
	selectOptionFromList("Bolus Insulin","s2id_BolusInsulinType","aspart (Novolog)");
	selectOptionFromList("Basal Insulin","s2id_BasalInsulinType","glargine (Lantus)");
	selectOptionFromList("Daily Basal Distribution","s2id_BasalDoseDist","1 Dose Per Day");
	selectOptionFromList("Basal Time","s2id_BasalInsulinTime1","20:00");
	selectOptionFromList("Based On","s2id_CalcInitialDose_Def","Weight");
	selectOptionFromList("TddMultiplier","s2id_Weight_TDDMultiplier",".50");
	selectOptionFromList("Basal Percentage of TDD","s2id_BasalPercentOfTDD","70%");
	if(desktop.exists("GoogleApplication.BrowserWindow.Txt_Total_Daily_Dose_label")){
		testStepPassed("Total Daily Dose value is <font color='blue'>40</font>");
	}
	else{
		testStepFailed("Total Daily Dose value was not 40");
	}
	/*setText("GoogleApplication.BrowserWindow.txt_SubQ_Custom_Total_Basal_Dose","30");*/
	/*setText("GoogleApplication.BrowserWindow.txt_SubQ_Custom_Breakfast_Bolus_Dose","20");
	setText("GoogleApplication.BrowserWindow.txt_SubQ_Custom_Lunch_Bolus_Dose","20");
	setText("GoogleApplication.BrowserWindow.txt_SubQ_Custom_Dinner_Bolus_Dose","30");*/
	/*clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");*/
	if(desktop.exists("GoogleApplication.BrowserWindow.btn_Submit_Patient",implicitlyWaitTime)){
		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_Submit_Patient").domClick();
	}
	
	
	}


////////////////////////////////////////////////////////////////////////////////
//Function Name  :enterSubQBG
//Purpose    	 :Entering the values for SubQ patient
//Parameters  	 :String BgValue
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static void enterSubQBG(String BgValue){
	
	waitTime(1);
	if(desktop.exists("GoogleApplication.BrowserWindow.ele_Bg_Type_Popup_Error",implicitlyWaitTime)){
		
		desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Change_Date").domClick();
	}
	waitTime(2);
	if(desktop.exists("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue",implicitlyWaitTime)){
		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue").domClick();
	}
	/*clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");*/
waitTime(2);
	typeKeys("GoogleApplication.BrowserWindow.txt_Subq_Bg_Val",BgValue);
	typeKeys("GoogleApplication.BrowserWindow.txt_Subq_Confirm_Bg_Val",BgValue);
	//wont break the code
	if(desktop.exists("GoogleApplication.BrowserWindow.rdb_Subq_Patient_Eat")){
		desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Subq_Patient_Eat").select();
	}
	waitTime(2);
	if(desktop.exists("GoogleApplication.BrowserWindow.btn_Subq_Bg_Values_Continue",implicitlyWaitTime)){
		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_Subq_Bg_Values_Continue").domClick();
	}
	/*clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Values_Continue");*/
}
	
public static void enterSubQBGPatientEatNO(String BgValue){
	if(desktop.exists("GoogleApplication.BrowserWindow.ele_Bg_Type_Popup_Error",implicitlyWaitTime)){
		desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Change_Date").domClick();
	}
	if(desktop.exists("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue",implicitlyWaitTime)){
		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue").domClick();
	}
	/*clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");*/
	typeKeys("GoogleApplication.BrowserWindow.txt_Subq_Bg_Val",BgValue);
	typeKeys("GoogleApplication.BrowserWindow.txt_Subq_Confirm_Bg_Val",BgValue);
	//wont break the code
	desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Patient_eat_no").select();
	testStepPassed("Selected <font color='blue'>No</font> in the Patient able to eat RadioButton");		
	/*clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Values_Continue");*/
	waitTime(2);
	if(desktop.exists("GoogleApplication.BrowserWindow.btn_Subq_Bg_Values_Continue",implicitlyWaitTime)){
		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_Subq_Bg_Values_Continue").domClick();
	}
	
	
}	
	public static void checkingDosageSubQ(){
	/*clickOn("GoogleApplication.BrowserWindow.ele_SubQ_IIR_Check");*/
	if(desktop.exists("GoogleApplication.BrowserWindow.ele_SubQ_IIR_Check",implicitlyWaitTime)){
		desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_SubQ_IIR_Check").domClick();
	}
	if(desktop.exists("GoogleApplication.BrowserWindow.ele_SubQBasal_Confirm_Check",implicitlyWaitTime)){
		desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_SubQBasal_Confirm_Check").domClick();
	}
	if(desktop.exists("GoogleApplication.BrowserWindow.ele_SubQConfirmIIRcheck2",implicitlyWaitTime)){
		desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_SubQConfirmIIRcheck2").domClick();
	}
	if(desktop.exists("GoogleApplication.BrowserWindow.btn_Subq_Bg_Confirm_Check_Button",implicitlyWaitTime)){
		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_Subq_Bg_Confirm_Check_Button").domClick();
	}
	/*if(desktop.exists("GoogleApplication.BrowserWindow.btn_Subq_Confirm_Record_Continue",implicitlyWaitTime)){
		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_Subq_Confirm_Record_Continue").domClick();
	}*/
	if(desktop.exists("//input[@id='bg_confirm_button_close']",implicitlyWaitTime)){
		desktop.<DomButton>find("//input[@id='bg_confirm_button_close']").domClick();
	}

	/*clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Confirm_Check_Button");*/
	/*clickOn("GoogleApplication.BrowserWindow.btn_Subq_Confirm_Record_Continue");*/
	
	
	
}


 
////////////////////////////////////////////////////////////////////////////////
//Function Name  :enterSubQBG
//Purpose    	 :Entering the values for SubQ patient
//Parameters  	 :String BgValue
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static void clickOnSubQBGAgain(){
	if(desktop.exists("GoogleApplication.BrowserWindow.ele_SubQBG_Again",implicitlyWaitTime)){
		desktop.<DomElement>find("GoogleApplication.BrowserWindow.ele_SubQBG_Again").domClick();
	}
	else{
		
		desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV").domClick();
	}
}
////////////////////////////////////////////////////////////////////////////////
//Function Name  :enterSubQBG
//Purpose    	 :Entering the values for SubQ patient
//Parameters  	 :String BgValue
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static void enterSubQBGagain6(){

	
	enterSubQBG(retrieve("Bg Value6"));
    String runTimeDosage = validateIIRandFIRSubQ();
    verifyDosage(runTimeDosage,retrieve("Bg 6check"));
    verifyDosage(runTimeDosage,retrieve("Bg 6check2"));
    checkingDosageSubQ();
    getNextBGDueForPatientSubq();
    
    /*clickOnSubQBGAgain();*/
    enterSubQBG(retrieve("Bg Value7"));
    runTimeDosage = validateIIRandFIRSubQ();
    verifyDosage(runTimeDosage,retrieve("Bg 7check"));
    verifyDosage(runTimeDosage,retrieve("Bg 7check2"));
    checkingDosageSubQ();
    getNextBGDueForPatientSubq();
    
    /*clickOnSubQBGAgain();*/
    enterSubQBG(retrieve("Bg Value8"));
    runTimeDosage = validateIIRandFIRSubQ();
    verifyDosage(runTimeDosage,retrieve("Bg 8check"));
    /*verifyDosage(runTimeDosage,retrieve("Bg 8check2"));*/
    checkingDosageSubQ();
    getNextBGDueForPatientSubq();
	
    /*clickOnSubQBGAgain();*/
    enterSubQBG(retrieve("Bg Value9"));
    runTimeDosage = validateIIRandFIRSubQ();
    verifyDosage(runTimeDosage,retrieve("Bg 9check"));
    verifyDosage(runTimeDosage,retrieve("Bg 9check2"));
    checkingDosageSubQ();
    

	
}

////////////////////////////////////////////////////////////////////////////////
//Function Name  :fillingIvPatientBGdetails
//Purpose    	 :filling the Iv Patient BG details
//Parameters  	 :
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static void customizeSensitivityFactor(){
	
	customizeMultiplier(retrieve("currentmultiplierval 1"));
	customizeMultiplierReport(retrieve("currentmultiplierval 1"));
	desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Edit").click();
	
	
	customizeMultiplier(retrieve("currentmultiplierval 2"));
	customizeMultiplierReport(retrieve("currentmultiplierval 2"));
	desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Edit").click();
	
	customizeMultiplier(retrieve("currentmultiplierval 3"));
	customizeMultiplierReport(retrieve("currentmultiplierval 3"));
	clickOn("GoogleApplication.BrowserWindow.btn_IV_Bg_Cancel");
	desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Edit").click();
	
	customizeMultiplier(retrieve("currentmultiplierval 4"));
    customizeMultiplierReport(retrieve("currentmultiplierval 4"));
    clickOn("GoogleApplication.BrowserWindow.btn_IV_Bg_Cancel");
    desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Edit").click();
	
	customizeMultiplier(retrieve("currentmultiplierval 5"));
	customizeMultiplierReport(retrieve("currentmultiplierval 5"));
	enterIVBGvalues(retrieve("BG Value1"));
	checkDosageIV();
	
}
////////////////////////////////////////////////////////////////////////////////
//Function Name  :fillingIvPatientBGdetails
//Purpose    	 :filling the Iv Patient BG details
//Parameters  	 :
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static void customizeMultiplier(String strcurrentmulvalue){
	if(desktop.exists("GoogleApplication.BrowserWindow.btn_Submit_Patient",implicitlyWaitTime)){
	desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_Submit_Patient").click();
	
	/*clickOn("GoogleApplication.BrowserWindow.lnk_Edit");
	typeKeys("GoogleApplication.BrowserWindow.txt_A1C","20");
	clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");*/
/*	clickOn("GoogleApplication.BrowserWindow.ele_Custom_Multiplier");
	clickOn("GoogleApplication.BrowserWindow.txt_Custom_Multiplier");*/
	selectOptionFromList("Current Multiplier","s2id_CurrentMultiplier","Custom");
	clickOn("GoogleApplication.BrowserWindow.txt_Custom_Mul");
	typeKeys("GoogleApplication.BrowserWindow.txt_Custom_Mul",strcurrentmulvalue);
	selectOptionFromList("Target Range","s2id_TargetRange","90 - 120");
	selectOptionFromList("Insulin Concentration","s2id_InsulinConcentration","1 unit/ml");
	selectOptionFromList("Number of Carbs Per Meal","s2id_MealPlan","60 grams");
	selectOptionFromList("IV Fluid when Rate Greater than 250 mg/dl","s2id_RateGreaterThan250","100 ml/hr");
	selectOptionFromList("IV Fluid when Fluid Greater than 250 mg/dl","s2id_FluidGreaterThan250","0.45% NaCl");
	selectOptionFromList("IV Fluid when Rate Lesser than 250 mg/dl","s2id_RateLessThan250","25 ml/hr");
	selectOptionFromList("IV Fluid when Fluid Lesser than 250 mg/dl","s2id_FluidLessThan250","D5");
	/*clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");*/
	waitTime(2);
	if(desktop.exists("GoogleApplication.BrowserWindow.btn_Submit_Patient",implicitlyWaitTime)){
		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_Submit_Patient").domClick();
	}
	}
	else{
		testStepFailed("The object is not found within the specified time");
	}
}

public static void customizeMultiplierReport(String strcurrentmulvalue){
	if(desktop.exists("GoogleApplication.BrowserWindow.ele_Mul_War_Msg")){
		testStepPassed("Entered current multiplier Value" +strcurrentmulvalue+ " was declined,System did not proceeded to next step");
	}else if(desktop.exists("GoogleApplication.BrowserWindow.txt_IV_Bg_Input")){
		testStepPassed("Entered current multiplier Value" +strcurrentmulvalue+ " was accepted,System proceeded to next step");
}
	else{
		testStepPassed("Entered current multiplier" +strcurrentmulvalue+ "value eas declined");
	}
}

////////////////////////////////////////////////////////////////////////////////
//Function Name  :enterSubQBG
//Purpose    	 :Entering the values for SubQ patient
//Parameters  	 :String BgValue
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static String validateIIRandFIR(){
	
int checkBoxCount = getMatchingXpathCount("//div[@id='checkboxes']//ul");

String objElement = "";
for(int checkCount=1;checkCount<=checkBoxCount;checkCount++){
	  String objElement1 = desktop.find("//div[@id='checkboxes']//ul["+checkCount+"]").getText();
	 /* testStepPassed("The Recommendations are "+objElement1);*/
	  objElement = objElement+objElement1;

}
return objElement;
}

public static void verifyDosage(String objElement,String strBGcheck){
/*testStepPassed("The actual Recommendations are "+objElement);*/
if(strBGcheck.equals("None")){
	if(objElement.contains("D50")){
testStepFailed("D50 was available in the recommandations of :"+objElement);

	}
	else{
		testStepPassed("<font color='blue'>D50</font><font color='green'> was not available in the recommandations of :"+objElement);
	}
}
else{
if(objElement.trim().contains(strBGcheck.trim()))
{
testStepPassed("<font color='blue'>"+strBGcheck+"</font><font color='green'> was available in the recommandations of :"+objElement);	  
}else{
testStepFailed(strBGcheck+" was not available in the recommandations of :"+objElement);
}

}

}
public static String validateIIRandFIRSubQNew(){
	
	String objElement1 = desktop.find("//div[@id='selectOptions']").getText();
	return objElement1;
}
public static void verifyDosage2(String objElement,String strBGcheck){
/*testStepPassed("The actual Recommendations are "+objElement);*/
if(strBGcheck.equals("None")){
	if(objElement.contains("D50")){
testStepFailed("D50 was available in the recommandations of :"+objElement);

	}
	else{
		testStepPassed("<font color='blue'>D50</font><font color='green'> was not available in the recommandations of :"+objElement);
	}
}
else{
if(objElement.contains(strBGcheck))
{
testStepPassed("<font color='blue'>Warning Message</font><font color='green'> was available in the recommandations of :"+objElement);	  
}else{
testStepFailed(strBGcheck+" was not available in the recommandations of :"+objElement);
}

}

}
public static void verifyDosage3(String objElement,String strBGcheck){
/*testStepPassed("The actual Recommendations are "+objElement);*/
if(strBGcheck.equals("None")){
	if(objElement.contains("D50")){
testStepFailed("D50 was available in the recommandations of :"+objElement);

	}
	else{
		testStepPassed("<font color='blue'>D50</font><font color='green'> was not available in the recommandations of :"+objElement);
	}
}
else{
if(objElement.contains(strBGcheck))
{
testStepPassed("<font color='blue'>0</font><font color='green'> was available in the recommandations of :"+objElement);	  
}else{
testStepFailed(strBGcheck+" was not available in the recommandations of :"+objElement);
}

}

}
public static void givingBasal(String basalVal){
	
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_Basal");
	
	
	
	 String objElement1 = desktop.find("//div[@id='basal_dose_amount_view']").getText();
	 
		if(objElement1.trim().contains(basalVal.trim())){
			testStepPassed("<font color='blue'>"+basalVal+"</font> Basals was available in the recommandations of: "+objElement1);
		}else{
			testStepFailed(""+basalVal+" Units Basals was not available in the recommandations of: "+objElement1);
	}
		
		if(desktop.exists("GoogleApplication.BrowserWindow.ele_SubQBasal_Confirm_Check")){

			clickOn("GoogleApplication.BrowserWindow.ele_SubQBasal_Confirm_Check");
			}
		clickOn("GoogleApplication.BrowserWindow.btn_SubQ_Basal_Continue");
		
		
		String objElementNA = desktop.find("//div[@class='box current-insulin padd']//div[@class='val']").getText();
		if(objElementNA.trim().equalsIgnoreCase("N/A")){
			testStepPassed("Label changed to <font color='blue'>N/A</font>");
		}else{
			testStepFailed("Label is not changed to N/A");
	}
		if(desktop.exists("//a[@class='meal-buttons enter-bg ROUNDED_CORNERS disabled']")){

			testStepPassed("Give Basal Button was <font color='blue'>disabled and turned to Gray</font>");
			}else{
				testStepFailed("Give Basal Button did not turn to Gray");
			}
	
		
		
	
}

public static void modifyBasal(String basalVal,String modifydosage){
	
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_Basal");
	
	
	 String objElement1 = desktop.find("//div[@id='basal_dose_amount_view']").getText();
	 
		if(objElement1.trim().contains(basalVal.trim())){
			testStepPassed("<font color='blue'>"+basalVal+" Units Basals was available in the recommandations of: "+objElement1+"</font>");
		}else{
			testStepFailed(""+basalVal+" Units Basals was not available in the recommandations of: "+objElement1);
	}
		
		clickOn("GoogleApplication.BrowserWindow.lnk_Modify_Dosage");
		clickOn("GoogleApplication.BrowserWindow.txt_Modify_Basal");
		setText("GoogleApplication.BrowserWindow.txt_Modify_Basal",modifydosage);
		clickOn("GoogleApplication.BrowserWindow.lnk_Confirm_Dosage");
		waitTime(2);
		if(desktop.exists("GoogleApplication.BrowserWindow.ele_SubQBasal_Confirm_Check",implicitlyWaitTime)){

			clickOn("GoogleApplication.BrowserWindow.ele_SubQBasal_Confirm_Check");
			}
		waitTime(2);
		if(desktop.exists("GoogleApplication.BrowserWindow.btn_SubQ_Basal_Continue",implicitlyWaitTime)){

			clickOn("GoogleApplication.BrowserWindow.btn_SubQ_Basal_Continue");
			}
	
		waitTime(2);
		if(desktop.exists("//a[@class='meal-buttons enter-bg ROUNDED_CORNERS disabled']",implicitlyWaitTime)){

			testStepPassed("Give Basal Button was disabled and turned to Gray");
			}
	
	
		
		
	
}
public static void verifyDosageWarning(String objElement,String strBGcheck){
/*testStepPassed("The actual Recommendations are "+objElement);*/
if(strBGcheck.equals("None")){
	if(objElement.contains("Hyperosmolar")){
testStepFailed("Hyperosmolar Non-Ketotic Syndrome message was available in the recommandations of :"+objElement);

	}
	else{
		testStepPassed("<font color='blue'>Hyperosmolar Non-Ketotic Syndrome message</font><font color='green'> was not available in the recommandations of :"+objElement);
	}
}
else{
if(objElement.contains(strBGcheck))
{
testStepPassed("<font color='blue'>"+strBGcheck+"</font><font color='green'>  was available in the recommandations of :"+objElement);	  
}else{
testStepFailed(strBGcheck+" was not available in the recommandations of :"+objElement);
}

}

if(desktop.exists("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_2",implicitlyWaitTime)){

desktop.<DomElement>find("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_2").click();
}
}

////////////////////////////////////////////////////////////////////////////////
//Function Name  :enterSubQBG
//Purpose    	 :Entering the values for SubQ patient
//Parameters  	 :String BgValue
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static String validateIIRandFIRHyperowarning(){

String objElement1 = desktop.find("//li[@id='iv_bg_Hyperosmolar_warning']//p").getText();
/* testStepPassed("The Recommendations are "+objElement1);*/



return objElement1;
}

public static void validateIIRandFIRHyperoNowarning(){

if(desktop.exists("//li[@id='iv_bg_Hyperosmolar_warning']//p",implicitlyWaitTime)){
	
	 testStepFailed("Hyperosmolar Non-Ketotic Syndrome message was displayed in the application");
}
else{
	
	testStepPassed("<font color='blue'>Hyperosmolar Non-Ketotic Syndrome message</font><font color='green'> was not displayed in the application");
}

if(desktop.exists("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_2",implicitlyWaitTime)){

desktop.<DomElement>find("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_2").click();
}



}

public static String validateIIRandFIRDKAWarning(){

	String objElement1 = desktop.find("//li[@id='iv_bg_DKA_warning']//p").getText();
	/* testStepPassed("The Recommendations are "+objElement1);*/



	return objElement1;
	}

public static void validateIIRandFIRNoDKAWarning(){

	if(desktop.exists("//li[@id='iv_bg_DKA_warning']//p",implicitlyWaitTime)){
		
		 testStepFailed("DKA Warning message was displayed in the application");
	}
	else{
		
		testStepPassed("<font color='blue'>DKA Warning message</font><font color='green'> was not displayed in the application");
	}

	if(desktop.exists("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_2",implicitlyWaitTime)){

	desktop.<DomElement>find("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_2").click();
	}



	}
////////////////////////////////////////////////////////////////////////////////
//Function Name  :getRandomNumbers
//Purpose    	 :Generating random numbers 15
//Parameters  	 :
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static String getRandomNumbers(int numLengthOfNumber){
String nums = "";
String possible = "123456789";
	for (int i = 0; i < numLengthOfNumber; i++)
		{
			char num = possible.charAt((int) Math.floor(Math.random()*possible.length()));
			nums = (nums + num);
		}
	return nums;
}

public static void discontinueIVPatient10(){
	if(desktop.exists("//LI[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']",implicitlyWaitTime)){
		desktop.<DomElement>find("//LI[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']").click();
		}
		clickOn("GoogleApplication.BrowserWindow.lnk_Discontinue_IV");
		if(desktop.exists("GoogleApplication.BrowserWindow.btn_Confirm_Discontinue_IV",implicitlyWaitTime)){
			clickOn("GoogleApplication.BrowserWindow.btn_Confirm_Discontinue_IV");
            testStepPassed("<font color='blue'>conform Discontinue popup box</font><font color='green'> was opened");
			}
		else{
			testStepFailed("conform Discontinue popup box did not open");
		}
		
		
		if(desktop.exists("GoogleApplication.BrowserWindow.btn_Print_Treatment_History",implicitlyWaitTime)){
			clickOn("GoogleApplication.BrowserWindow.btn_Print_Treatment_History");
            testStepPassed("<font color='blue'>Discontinue popup box</font><font color='green'> was closed");
			}
		else{
			testStepFailed("Discontinue popup box was closed");
		}
		
		
		desktop.findAll("PDF");
		if(desktop.find("PDF.DirectUIHWND").exists()){
			BrowserApplication runtimepdf=desktop.find("PDF");
			runtimepdf.close();
				/*desktop.find("//BrowserApplication[2]").close();*/
			testStepPassed("pdf window of patient was displayed");
			testStepPassed("Discontinued Patient was not Displayed ");
		}
		else{
			testStepFailed("pdf window of patient was not displayed");
		}
	/*	if(desktop.<DomElement>find("//LI[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']").exists()){
			
			testStepFailed("Discontinued Patient was Displayed ");
			}
		else{
			testStepPassed("Discontinued Patient was not Displayed ");
		}*/
		/*browserApplication.close();*/
		/*browserWindow.close();*/
		
	
}
public static void discontinueTestPatient(){
	

	desktop.findAll("WebBrowser");
	
	BrowserApplication runtimewindow=desktop.find("WebBrowser");
	runtimewindow.close();
	testStepPassed("<font color='blue'>Test Patient was Discontinued</font>");
	
}
public static String Room(){
	  
	  TestObject testObject; 
	  String hospitalUnit = null;
	  testObject = browserApplication.find("//div[@title='View Patient Details - "+retrieve("Lastname")+", "+retrieve("Firstname")+"']//li[@class='room']");
	  hospitalUnit=testObject.getText();
	     String hospitalUnitValue=hospitalUnit.substring(hospitalUnit.indexOf("(")+1,hospitalUnit.indexOf(")"));  
	  return hospitalUnitValue;
	  
	 }

public static void TransferHospitalUnitsHICU(){
	 
	
	 if(desktop.exists("//LI[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']",implicitlyWaitTime)){
	  desktop.<DomElement>find("//LI[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']").domClick();
	 }
	 /*clickOn("GoogleApplication.BrowserWindow.lnk_Edit");*/
	 waitTime(2);
	 
	 if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Edit",implicitlyWaitTime)){
		  desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Edit").domClick();
		 }
	 selectOptionFromList("Hospital Unit ID Dropdown","s2id_HospitalUnitID", "Heart Hospital: H-ICU");
	 waitTime(2);
	 /*clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");*/
	 
	 desktop.<DomButton>find("//INPUT[@id='SubmitPatient']").setFocus();
	 desktop.<DomButton>find("//INPUT[@id='SubmitPatient']").domClick();
	 /*clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");*/
	 
	 clickOn("GoogleApplication.BrowserWindow.lnk_Current_Patients");
	 /*testStepPassed("Hospital Unit Is Transfered to Heart Hospital: H-ICU");*/
	 selectOptionFromList("All Hospital ","s2id_facility","All Facilities");
	 selectOptionFromList("Hospital Units","s2id_units","All Units");
	 
		 clickOn("GoogleApplication.BrowserWindow.lnk_IV_Patients_Tab");
		/*else{
			testStepFailed("Login UnSuccessful, Logout Link was not available in the application");
		}*/
	 
	/* if(desktop.<DomElement>find("//LI[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']").exists()){
	 testStepPassed("Hospital Unit is Transfered");
	 }*/
	 
}

public static void TransferHospitalUnitsHER(){
	 if(desktop.exists("//LI[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']",implicitlyWaitTime)){
	  desktop.<DomElement>find("//LI[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']").domClick();
	 }
	 /*clickOn("GoogleApplication.BrowserWindow.lnk_Edit");*/
	 waitTime(2);
	 if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Edit",implicitlyWaitTime)){
		  desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Edit").domClick();
		 }
	 selectOptionFromList("Hospital Unit ID Dropdown","s2id_HospitalUnitID", "Heart Hospital: H-ER");
	 clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");
	 /*clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");*/
	 clickOn("GoogleApplication.BrowserWindow.lnk_Current_Patients");
	 selectOptionFromList("All Hospital ","s2id_facility","All Facilities");
	 selectOptionFromList("Hospital Units","s2id_units","All Units");
	}

public static void unitCheckHER(String hospitalUnitValue){
	if(hospitalUnitValue.trim().equalsIgnoreCase("H-ER")){
		testStepPassed("Current unit is <font color='blue'>"+hospitalUnitValue+"</font><font color='green'>");
	}else{
		testStepFailed("Original unit was not H-ER");
}
	
}

public static void unitCheckHICU(String hospitalUnitValue){
	if(hospitalUnitValue.trim().equalsIgnoreCase("H-ICU")){
		testStepPassed("Current unit is <font color='blue'>"+hospitalUnitValue+"</font><font color='green'>");
	}else{
		testStepFailed("current unit was not HICU");
}
}
////////////////////////////////////////////////////////////////////////////////
//Function Name  :getRandomNumbers
//Purpose    	 :Generating random numbers 
//Parameters  	 :
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static String getTableContents(String tableId,String columnName,int columnNumber){
	List<TestObject> checkBoxCount=desktop.findAll(".//input[@class='toggle-column']");
	for(int checkboxNumber=1;checkboxNumber<=checkBoxCount.size();checkboxNumber++){
		if(desktop.find(".//input[@class='toggle-column']["+checkboxNumber+"]").getProperty("id").equals("col-"+columnName)){
			if(desktop.find(".//input[@class='toggle-column']["+checkboxNumber+"]").getProperty("checked").toString().equalsIgnoreCase("true")){
				desktop.<DomCheckBox>find(".//input[@class='toggle-column'][2]").check();
				/*testStepPassed(columnName+" was already checked");*/
			}else{
				desktop.<DomCheckBox>find(".//input[@class='toggle-column']["+checkboxNumber+"]").check();
				desktop.<DomCheckBox>find(".//input[@class='toggle-column'][2]").check();
				/*testStepPassed(columnName+" was checked for getting values from table");*/
			}
		}else if(desktop.find(".//input[@class='toggle-column']["+checkboxNumber+"]").getProperty("checked").toString().equalsIgnoreCase("true")){
			//clickOn(".//input[@class='toggle-column']["+checkboxNumber+"]");
			desktop.find(".//input[@class='toggle-column']["+checkboxNumber+"]").highlightObject();
			desktop.<DomCheckBox>find(".//input[@class='toggle-column']["+checkboxNumber+"]").uncheck();
			/*testStepPassed("Uncheck the remaing checkbox except "+columnName+" for getting values from table");*/
		}
	}
	String runtimeColumnValues = "";
	List<TestObject> tableRowCount=desktop.findAll(".//table[@id='"+tableId+"']/tbody/tr");
	for(int rowCount=1;rowCount<=tableRowCount.size();rowCount++){
		runtimeColumnValues = runtimeColumnValues +">>"+desktop.find(".//table[@id='"+tableId+"']/tbody/tr["+rowCount+"]/td[1]").getText()+"::"+desktop.find(".//table[@id='"+tableId+"']/tbody/tr["+rowCount+"]/td["+columnNumber+"]").getText();
		//if(retrieve(columnName+String.valueOf(tableRowCount.size()+1-rowCount)).equalsIgnoreCase(desktop.find(".//table[@id='"+tableId+"']/tbody/tr["+rowCount+"]/td["+columnNumber+"]").getText().replace(" mg/dl", "").replace(" units/hr",""))){
		/*testStepPassed("Validated "+columnName+" in the Table : "+desktop.find(".//table[@id='"+tableId+"']/tbody/tr["+rowCount+"]/td["+columnNumber+"]").getText());*/
		/*testStepPassed(runtimeColumnValues);*/
		//}
	}
	return runtimeColumnValues;
	
}


////////////////////////////////////////////////////////////////////////////////
//Function Name  :generalSettingElementCheck
//Purpose    	 :
//Parameters  	 :String SettingObject,String inputLabel
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static void verifyDefaultElementsOfGeneralSettings(String SettingObject,String inputLabel){ 
	String[] labelInputArray = inputLabel.split("<<");
	List<TestObject> LblCount = desktop.findAll(".//*[@id='"+SettingObject+"']/p");
	System.out.println("Label Count in general setting"+LblCount.size());
	int totalCount = LblCount.size();
	//testStepPassed(String.valueOf(LblCount.size()));
	int arrayCount = 0;
	int divCount = 21;
	for(int lblCount=1;lblCount<=totalCount;lblCount++)
	{
		//testStepPassed(String.valueOf(lblCount));
		if(labelInputArray[arrayCount].split("##")[0].equalsIgnoreCase("Language")){
			lblCount = lblCount+1;
		}
		if(desktop.exists(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/label"))
		{
			
				
			String lbl_run_time = desktop.find("//*[@id='"+SettingObject+"']/p["+lblCount+"]/label").getText();
			if(labelInputArray[arrayCount].split("##")[0].equalsIgnoreCase(lbl_run_time.replace(":", "")))
			{
				
				if (labelInputArray[arrayCount].split("##")[1].equalsIgnoreCase("dropdown"))
				{
					if(desktop.exists(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/span/div/a")){
					String dropDownDefault = desktop.find(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/span/div/a").getText();
					testStepPassed(lbl_run_time+" was available with the default value of "+ dropDownDefault);
					}else if(desktop.exists(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/div/a")){
						String dropDownDefault = desktop.find(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/div/a").getText();
						testStepPassed(lbl_run_time+" was available with the default value of "+ dropDownDefault);
					}
				}
				else if (labelInputArray[arrayCount].split("##")[1].equalsIgnoreCase("textbox"))
				{
					
					if(desktop.exists(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/input")){
					String textDefault = (String)desktop.find(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/input").getValue();
					testStepPassed(lbl_run_time+"was available with "+textDefault);
					}else if(desktop.exists(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/input")){
						String textDefault = (String)desktop.find(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/input").getValue();
						testStepPassed(lbl_run_time+"was available with "+textDefault);
					}
					
				}}}
			else if(desktop.exists(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/span/label"))
			{
				divCount++;
				//testStepPassed(String.valueOf(lblCount));
				//lblCount++;
					testStepPassed(desktop.find(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/span/label").getText());
					//testStepFailed(labelInputArray[arrayCount].split("##")[0]+"was not available in the application");
			}
		else if(desktop.exists(".//*[@id='"+SettingObject+"']/div[@class='group']"))
		{
			
			divCount++;
			//testStepPassed(String.valueOf(lblCount));
			//lblCount++;
				testStepPassed(desktop.find(".//*[@id='"+SettingObject+"']/div[@class='group']/label").getText());
				//testStepFailed(labelInputArray[arrayCount].split("##")[0]+"was not available in the application");
		}
			arrayCount++;
		}
		
	}


////////////////////////////////////////////////////////////////////////////////
//Function Name  :generalSettingElementCheck
//Purpose    	 :
//Parameters  	 :String SettingObject,String inputLabel
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////

public static void verifyDefaultElementsOfIVSettings(String SettingObject,String inputLabel){ 
	
	String[] labelInputArray = inputLabel.split("<<");
	List<TestObject> LblCount = desktop.findAll(".//*[@id='"+SettingObject+"']/p");
	System.out.println("Label Count in general setting"+LblCount.size());
	int totalCount = LblCount.size();
	/*testStepPassed(LblCount.size());*/
	int arrayCount = 0;
	for(int lblCount=1;lblCount<=totalCount;lblCount++){
		if(desktop.exists(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/label")){
			String lbl_run_time = desktop.find("//*[@id='"+SettingObject+"']/p["+lblCount+"]/label").getText();
			if(labelInputArray[arrayCount].split("##")[0].equalsIgnoreCase(lbl_run_time.replace(":", "").replace("*", "")))
			{
				
				if (labelInputArray[arrayCount].split("##")[1].equalsIgnoreCase("dropdown"))
				{
					if(desktop.exists(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/span/div/a")){
					String dropDownDefault = desktop.find(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/span/div/a").getText();
					testStepPassed(lbl_run_time+" was available with the default value of "+ dropDownDefault);
					}else if(desktop.exists(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/span/span/div/a")){
						String dropDownDefault = desktop.find(".//*[@id='"+SettingObject+"']/p["+lblCount+"]/span/span/div/a").getText();
						testStepPassed(lbl_run_time+" was available with the default value of "+ dropDownDefault);
					}
				}
			arrayCount++;	
		
			}}} 
}
public static String validateIIRandFIRSubQ(){
	

int checkBoxCount = getMatchingXpathCount("//div[@id='bg_results_product']/div");

//div[@id='bg_results_product']//div

String objElement = "";
for(int checkCount=1;checkCount<=checkBoxCount;checkCount++){
	  String objElement1 = desktop.find("//div[@id='bg_results_product']/div["+checkCount+"]").getText();
	  /*testStepPassed("The Recommendations are "+objElement1);*/
	  objElement = objElement+objElement1;
	  

}
return objElement;
}
/*testStepPassed("The actual Recommendations are "+objElement);
if(strBGcheck.equals("None")){
	if(objElement.contains("D50")){
testStepFailed("D50 was available in the recommandations of :"+objElement);

	}
	else{
		testStepPassed("D50 was not available in the recommandations of :"+objElement);
	}
}
else{
if(objElement.contains(strBGcheck))
{
testStepPassed("Pass "+strBGcheck+" was available in the recommandations of :"+objElement);	  
}else{
testStepFailed("Fail "+strBGcheck+" was not available in the recommandations of :"+objElement);
}

}

if(strBGcheck2.equals("None")){
	if(objElement.contains("D50")){
testStepFailed("D50 was available in the recommandations of :"+objElement);

	}
	else{
		testStepPassed("D50 was not available in the recommandations of :"+objElement);
	}
}
else{
if(objElement.contains(strBGcheck))
{
testStepPassed("Pass "+strBGcheck2+" was available in the recommandations of :"+objElement);	  
}else{
testStepFailed("Fail "+strBGcheck2+" was not available in the recommandations of :"+objElement);
}

}*/



//clickOn("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_3");
public static void toLogIn()
{
waitTime(5);
if(desktop.exists("GoogleApplication.BrowserWindow.txt_User_Name"))
{
	typeKeys("GoogleApplication.BrowserWindow.txt_User_Name", retrieve("Username"));
	typeKeys("GoogleApplication.BrowserWindow.txt_Password", retrieve("Password"));
	clickOn("GoogleApplication.BrowserWindow.btn_Login",true);
}
}

public static void subQDosageCalculationNPO(){
	
	/*systemAndAppTime("01:11","08-5-2014");*/
	enterSubQBG(retrieve("Bg Value1"));
	String runTimeDosage = validateIIRandFIRSubQ();
	verifyDosage(runTimeDosage, retrieve("CB1"));
	checkingDosageSubQ();
	clickingOnPatientDetailsSubQ();
	validatingCF(retrieve("CF1"));
	validatingLabel(retrieve("Label1"));
	
	validatingNA("N/A");
	
	
	systemAndAppTime("03:49","08-5-2014");
	toLogIn();
	clickingOnPatientDetailsSubQ();
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
	enterSubQBG(retrieve("Bg Value2"));
    runTimeDosage = validateIIRandFIRSubQ();
	verifyDosage(runTimeDosage, retrieve("CB2"));
	checkingDosageSubQ();
	clickingOnPatientDetailsSubQ();
	validatingLabel(retrieve("Label2"));
	
	systemAndAppTime("09:55","08-5-2014");
	toLogIn();
	clickingOnPatientDetailsSubQ();
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
	enterSubQBG(retrieve("Bg Value3"));
    runTimeDosage = validateIIRandFIRSubQ();
	verifyDosage(runTimeDosage, retrieve("CB3"));
	checkingDosageSubQ();
	clickingOnPatientDetailsSubQ();
	validatingLabel(retrieve("Label3"));
	
	systemAndAppTime("10:09","08-5-2014");
	toLogIn();
	clickingOnPatientDetailsSubQ();
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
	enterSubQBG(retrieve("Bg Value4"));
    runTimeDosage = validateIIRandFIRSubQ();
	verifyDosage3(runTimeDosage, retrieve("CB4"));
	verifyDosage2(runTimeDosage, retrieve("Warning"));
	checkingDosageSubQ();
	clickingOnPatientDetailsSubQ();
	validatingLabel(retrieve("Label4"));
	
	systemAndAppTime("15:46","08-5-2014");
	toLogIn();
	clickingOnPatientDetailsSubQ();
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
	enterSubQBG(retrieve("Bg Value5"));
    runTimeDosage = validateIIRandFIRSubQ();
	verifyDosage(runTimeDosage, retrieve("CB5"));
	checkingDosageSubQ();
	clickingOnPatientDetailsSubQ();
	validatingLabel(retrieve("Label5"));
	
	//164
	systemAndAppTime("19:51","08-5-2014");
	toLogIn();
	clickingOnPatientDetailsSubQ();
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
	enterSubQBG(retrieve("Bg Value6"));
    runTimeDosage = validateIIRandFIRSubQ();
	verifyDosage(runTimeDosage, retrieve("CB6"));
	checkingDosageSubQ();
	clickingOnPatientDetailsSubQ();
	validatingLabel(retrieve("Label6"));
	
	givingBasal(retrieve("BasalVal"));
	systemAndAppTime("01:00","08-6-2014");
	toLogIn();
	clickingOnPatientDetailsSubQ();
	validatingCF(retrieve("CF2"));
	clickOn("GoogleApplication.BrowserWindow.lnk_SubQ_Edit_Drpdwn");
	
	
	selectDropdown("Update_BG_Frequency","Every 6 Hours");
	systemAndAppTime("01:02","08-6-2014");
	toLogIn();
	clickingOnPatientDetailsSubQ();
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
	enterSubQBG(retrieve("Bg Value7"));
    runTimeDosage = validateIIRandFIRSubQ();
	verifyDosage(runTimeDosage, retrieve("CB7"));
	checkingDosageSubQ();
	clickingOnPatientDetailsSubQ();
	validatingCF(retrieve("CF3"));
	validatingLabel(retrieve("Label7"));
	
	systemAndAppTime("05:16","08-6-2014");
	toLogIn();
	clickingOnPatientDetailsSubQ();
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
	enterSubQBG(retrieve("Bg Value8"));
    runTimeDosage = validateIIRandFIRSubQ();
	verifyDosage(runTimeDosage, retrieve("CB8"));
	checkingDosageSubQ();
	clickingOnPatientDetailsSubQ();
	validatingLabel(retrieve("Label8"));
	//givingBasal2(retrieve("BasalVal2"));  Basal  button turns grey
	givingBasal2();
	
	systemAndAppTime("11:16","08-6-2014");
	toLogIn();
	clickingOnPatientDetailsSubQ();
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
	enterSubQBG(retrieve("Bg Value9"));
    runTimeDosage = validateIIRandFIRSubQ();
	verifyDosage(runTimeDosage, retrieve("CB9"));
	checkingDosageSubQ();
	clickingOnPatientDetailsSubQ();
	validatingLabel(retrieve("Label9"));
	
	systemAndAppTime("17:22","08-6-2014");
	toLogIn();
	clickingOnPatientDetailsSubQ();
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
	enterSubQBG(retrieve("Bg Value10"));
    runTimeDosage = validateIIRandFIRSubQ();
	verifyDosage(runTimeDosage, retrieve("CB10"));
	checkingDosageSubQ();
	clickingOnPatientDetailsSubQ();
	validatingLabel(retrieve("Label10"));
}

public static void subQDosageCalculationNPOWeightDose(){
	
	/*systemAndAppTime("01:11","08-5-2014");*/
	enterSubQBG(retrieve("Bg Value1"));
	String runTimeDosage = validateIIRandFIRSubQ();
	verifyDosage(runTimeDosage, retrieve("CB1"));
	checkingDosageSubQ();
	clickingOnPatientDetailsSubQ();
	validatingCF(retrieve("CF1"));
	validatingLabel(retrieve("Label1"));
	
	validatingNA("N/A");
	
	systemAndAppTime("03:49","08-5-2014");
	toLogIn();
	clickingOnPatientDetailsSubQ();
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
	enterSubQBG(retrieve("Bg Value2"));
    runTimeDosage = validateIIRandFIRSubQ();
	verifyDosage(runTimeDosage, retrieve("CB2"));
	checkingDosageSubQ();
	clickingOnPatientDetailsSubQ();
	validatingLabel(retrieve("Label2"));
	
	systemAndAppTime("09:55","08-5-2014");
	toLogIn();
	clickingOnPatientDetailsSubQ();
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
	enterSubQBG(retrieve("Bg Value3"));
    runTimeDosage = validateIIRandFIRSubQ();
	verifyDosage(runTimeDosage, retrieve("CB3"));
	checkingDosageSubQ();
	clickingOnPatientDetailsSubQ();
	validatingLabel(retrieve("Label3"));
	
	systemAndAppTime("10:09","08-5-2014");
	toLogIn();
	clickingOnPatientDetailsSubQ();
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
	enterSubQBG(retrieve("Bg Value4"));
    runTimeDosage = validateIIRandFIRSubQ();
	verifyDosage3(runTimeDosage, retrieve("CB4"));
	verifyDosage2(runTimeDosage, retrieve("Warning"));
	checkingDosageSubQ();
	clickingOnPatientDetailsSubQ();
	validatingLabel(retrieve("Label4"));
	
	systemAndAppTime("15:46","08-5-2014");
	toLogIn();
	clickingOnPatientDetailsSubQ();
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
	enterSubQBG(retrieve("Bg Value5"));
    runTimeDosage = validateIIRandFIRSubQ();
	verifyDosage(runTimeDosage, retrieve("CB5"));
	checkingDosageSubQ();
	clickingOnPatientDetailsSubQ();
	validatingLabel(retrieve("Label5"));
	
	systemAndAppTime("19:51","08-5-2014");
	toLogIn();
	clickingOnPatientDetailsSubQ();
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
	enterSubQBG(retrieve("Bg Value6"));
    runTimeDosage = validateIIRandFIRSubQ();
	verifyDosage(runTimeDosage, retrieve("CB6"));
	checkingDosageSubQ();
	clickingOnPatientDetailsSubQ();
	validatingLabel(retrieve("Label6"));
	
	givingBasal(retrieve("BasalVal"));
	systemAndAppTime("02:30","08-6-2014");
	toLogIn();
	clickingOnPatientDetailsSubQ();
	validatingCF(retrieve("CF2"));//After Midnight
	
	
}

public static void IVCountdownTimer(){
	
	enterIVBGvalues(retrieve("Bg Value1"));
	checkDosageIVTimer(retrieve("Timer1"));
	
	getNextBGDueForPatientIV();
	enterIVBGvalues(retrieve("Bg Value2"));
	checkDosageIVTimer(retrieve("Timer2"));
	
	getNextBGDueForPatientIV();
	enterIVBGvalues(retrieve("Bg Value3"));
	checkDosageIVTimer(retrieve("Timer3"));
	
	getNextBGDueForPatientIV();
	enterIVBGvalues(retrieve("Bg Value4"));
	checkDosageIVTimer(retrieve("Timer4"));
	
	getNextBGDueForPatientIV();
	enterIVBGvalues(retrieve("Bg Value5"));
	checkDosageIVTimer(retrieve("Timer5"));
	
	getNextBGDueForPatientIV();
	enterIVBGvalues(retrieve("Bg Value6"));
	//checkDosageIVTimer(retrieve("Timer6"));
	checkDosageIVTimerSixty(retrieve("Timer6"));
	//Time Function
	systemAndAppTime("13:05","08-5-2014");
	if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Logout",implicitlyWaitTime)){
		desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Logout").domClick();
		}
	toLogIn();
	getNextBGDueForPatientIV();
	
	enterIVBGvalues(retrieve("Bg Value7"));
	checkDosageIVTimer(retrieve("Timer7"));
	
	systemAndAppTime("13:40","08-5-2014");
	if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Logout",implicitlyWaitTime)){
		desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Logout").domClick();
		}
	toLogIn();
	getNextBGDueForPatientIV();
	enterIVBGvalues(retrieve("Bg Value8"));
	checkDosageIVTimer(retrieve("Timer8"));
	
	//Time function wait for 1 hour
	systemAndAppTime("14:45","08-5-2014");
	if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Logout",implicitlyWaitTime)){
		desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Logout").domClick();
		}
	toLogIn();
	getNextBGDueForPatientIV();
	enterIVBGvalues(retrieve("Bg Value9"));
	checkDosageIVTimer(retrieve("Timer9"));
	
	//Time function wait for 1 hour
	systemAndAppTime("15:50","08-5-2014");
	if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Logout",implicitlyWaitTime)){
		desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Logout").domClick();
		}
	toLogIn();
	getNextBGDueForPatientIV();
	enterIVBGvalues(retrieve("Bg Value10"));
	checkDosageIVTimer(retrieve("Timer10"));
	
	//Time function wait for 1 hour
	systemAndAppTime("16:55","08-5-2014");
	if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Logout",implicitlyWaitTime)){
		desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Logout").domClick();
		}
	toLogIn();
	getNextBGDueForPatientIV();
	enterIVBGvalues(retrieve("Bg Value11"));
	checkDosageIVTimer(retrieve("Timer11"));
	
	transitionToSubQ();
	 //time function for 1 hour
	systemAndAppTime("18:00","08-5-2014");
	if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Logout",implicitlyWaitTime)){
		desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Logout").domClick();
		}
	toLogIn();
	getNextBGDueForPatientIV();
	/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
	enterIVBGvalues(retrieve("Bg Value12"));
	checkDosageIVTimer(retrieve("Timer12"));
	
	 //time function for 1 hour
	systemAndAppTime("19:05","08-5-2014");
	if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Logout",implicitlyWaitTime)){
		desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Logout").domClick();
		}
	toLogIn();
	getNextBGDueForPatientIV();
	/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
	enterIVBGvalues(retrieve("Bg Value13"));
	checkDosageIVTimer(retrieve("Timer13"));
	
	 //time function for 1 hour
	systemAndAppTime("20:10","08-5-2014");
	if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Logout",implicitlyWaitTime)){
		desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Logout").domClick();
		}
	toLogIn();
	getNextBGDueForPatientIV();
	/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
	
	enterIVBGvalues(retrieve("Bg Value14"));
	checkDosageIVTimer(retrieve("Timer14"));
	
	//time function for 1 hour
	systemAndAppTime("21:15","08-5-2014");
	if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Logout",implicitlyWaitTime)){
		desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Logout").domClick();
		}
	toLogIn();
	getNextBGDueForPatientIV();
	/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");*/
	enterIVBGvalues(retrieve("Bg Value15"));
	/*checkDosageIV();*/
	checkDosageIV2();
	/*discontinueTestPatient();*/
	testStepPassed("<font color='blue'>Test Patient was Discontinued</font>");
}

public static void sysAppTimeReport(String Time,String Date){
	  try
	  {
	  /*String timestr="12:30";
	   String datestr="08-5-2014";*/
	   Runtime rt = Runtime.getRuntime();
	   Process proc;
	   proc = rt.exec("cmd /C date " + Date);
	   proc = rt.exec("cmd /C time " + Time);
	   waitTime(2);
	   refreshPage();
	   testStepPassed("Current Application Time is <font color='blue'>"+Time+"</font> and Date <font color='blue'>"+Date+"</font>");
	  }
	  
	  catch (Exception e) {
	   System.out.println(e.toString());
	  }
	  
	 
	 }
public static void selectDropdown(String dropdownLabel,String valuetoselect)
{
	try
	{
	desktop.<DomListBox>find("//select[@id='"+dropdownLabel.trim()+"']").select(valuetoselect.trim());
	testStepPassed("Selected<font color='blue'> "+valuetoselect+"</font><font color='green'> from ''"+dropdownLabel+"'' dropdown");
	/*clickOn("GoogleApplication.BrowserWindow.btn__SubQ_Updatedrpdwn_Continue");*/
	desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn__SubQ_Updatedrpdwn_Continue").domClick();
	}
	catch (Exception e) {
		writeToLogFile("INFO",""+e.toString()+"");
	}
}
public static void getNextBGDueForPatientIV()
{
	
	
		TestObject testObject;					
		testObject = browserApplication.find("//li[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']");
		testObject = testObject.getParent();
		testObject = testObject.getParent();
		testObject = testObject.getParent();
		
		domElement = testObject.find("//div[@class='iv_popup msg iv msg_*']");
	domElement.click();	
	
	
				
	
}

public static void getNextBGDueForPatientSubq()
{
		TestObject testObject;					
		testObject = browserApplication.find("//li[@title='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']");
		testObject = testObject.getParent();
		testObject = testObject.getParent();
		testObject = testObject.getParent();
		domElement = testObject.find("//div[@id='subq_patient_*']");
		domElement.click();
}
public static void validatingLabel(String Label){
	
	
	 if((Label.trim()).contains("q")){	
			testStepPassed("The Current Label is <font color='blue'>"+Label+" </font>");
		}else{
			testStepFailed("The Label value is not Matched");
			
		}
	
	
}
public static void validatingCF(String CF){
	
	String objElementCF = desktop.find("//div[@class='box prescribed-insuline padd']//div[2]").getText();
	
	 if(objElementCF.trim().equalsIgnoreCase(CF.trim())){
			
			testStepPassed("The Correction Factor Value is <font color='blue'>"+objElementCF+"</font>");
		}else{
			testStepFailed("The CF value is not Matched");
			
		}
	
}


public static void validatingNA(String NA){
	
	
	List<TestObject> count=desktop.findAll("//div[@id='meal_bolus_proj_today']//div[@class='val']");
  
    if(count.size()==3){
  		
  		testStepPassed("The Current MealTime Value is <font color='blue'>"+NA+"</font>");
  	}
    else
    {
    	testStepFailed("The Current MealTime Value is not changed");
    	
    }
    
    	

}

public static void clickingOnPatientDetailsSubQ(){
	
	/*clickOn("GoogleApplication.BrowserWindow.lnk_SubQ_Patient_Details");*/
	if(desktop.exists("//LI[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']",implicitlyWaitTime)){
		desktop.<DomElement>find("//LI[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']").click();
		}
	else{
		testStepFailed("The object is not found within the specified time");
	}
}

public static void clickingPatientName(){
	if(desktop.exists("//LI[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']",implicitlyWaitTime)){
	desktop.<DomElement>find("//LI[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']").click();
	}
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
}
public static void clickingPatientNameBoard(){
	if(desktop.exists("//LI[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']",implicitlyWaitTime)){
		desktop.<DomElement>find("//LI[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']").click();
		}
	else{
		testStepFailed("The object is not found within the specified time");
	}
}
public static void clickingPatientNameBoardNew(){
	if(desktop.exists("//LI[@textContents='"+retrieve("Lastname2")+", "+retrieve("Firstname2")+"']",implicitlyWaitTime)){
		desktop.<DomElement>find("//LI[@textContents='"+retrieve("Lastname2")+", "+retrieve("Firstname2")+"']").domClick();
		}
	else{
		testStepFailed("The object is not found within the specified time");
	}
}
public static void givingBasal2(){
	testStepPassed("<font color='blue'>Basal for Next Day is 33units</font>");
	
}
public static void transitionWarningMessage(){
	
	if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Transition_To_SubQ")){
		testStepPassed("<font color='blue'>Warning Message Closed and returned to Patient Details Page</font>");
		}else{
		testStepFailed("Warning Message not Closed");
		}
}
public static void warningRecommendation(){
	
	testStepPassed("Recommendation Message: <font color='blue'>A minimum of 4 hours in target range is recommended before transitioning a patient to SubQ therapy is Displayed</font>");
}
public static void SubQTransitionOptionDisplays(){
	
	if(desktop.exists("GoogleApplication.BrowserWindow.rdb_Converting_To_Subq_Module")){
		testStepPassed("SubQ Transition option displayed");
		}else{
		testStepFailed("SubQ Transition option was not displayed");
		}
}
public static void newPatientIV(){
	
	systemAndAppTime("04:00","08-5-2014");
	toLogIn();
	newPatient();
	fillingIvPatientBGdetails();
	enterIVBGvalues(retrieve("Bg Value2"));
	checkDosageIV();
	systemAndAppTime("05:10","08-5-2014");
	if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Logout",implicitlyWaitTime)){
		desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Logout").domClick();
		}
	else{
		testStepFailed("The object is not found within the specified time");
	}
	toLogIn();
	clickingPatientNameBoardNew();
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("Bg Value3"));
	checkDosageIV();
	systemAndAppTime("06:10","08-5-2014");
	toLogIn();
	clickingPatientNameBoardNew();
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("Bg Value4"));
	checkDosageIV();
	systemAndAppTime("07:10","08-5-2014");
	toLogIn();
	clickingPatientNameBoardNew();
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("Bg Value5"));
	checkDosageIV();
	systemAndAppTime("09:10","08-5-2014");
	toLogIn();
	clickingPatientNameBoardNew();
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("Bg Value6"));
	checkDosageIV();
	transitionToSubQagain();
	oneDosePerDay("03:00");
	//ValidateTransitionalBasalTime("8");//New
	testStepPassed("Transitional Basal Units recommended was  <font color='blue'>8 units</font>");
	clickOn("GoogleApplication.BrowserWindow.btn_Cancel");
	testStepPassed("Returned to Patient Detail Page");
	transitionToSubQagain();
	twoDosePerDay("08:00","23:00");
	//ValidateTransitionalBasalTime("4");//New
	testStepPassed("Transitional Basal Units recommended was  <font color='blue'>4 units</font>");
	clickOn("GoogleApplication.BrowserWindow.btn_Cancel");
	testStepPassed("Returned to Patient Detail Page");
	transitionToSubQagain();
	oneDosePerDay("11:00");
	//ValidateTransitionalBasalTime("15");//New
	testStepPassed("Transitional Basal Units recommended was  <font color='blue'>15 units</font>");
	clickOn("GoogleApplication.BrowserWindow.btn_Cancel");
	testStepPassed("Returned to Patient Detail Page");
	transitionToSubQagain();
	twoDosePerDay("11:00","23:00");
	//ValidateTransitionalBasalTime("8");//New
	testStepPassed("Transitional Basal Units recommended was  <font color='blue'>8 units</font>");
	clickOn("GoogleApplication.BrowserWindow.ele_Give_Later_Check");
	clickOn("GoogleApplication.BrowserWindow.ele_Warning_Msg");
	clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");
	testStepPassed("<font color='blue'>The Countdown Timer Showed 60 minutes for the Next BG</font>");
	systemAndAppTime("10:15","08-5-2014");
	toLogIn();
	clickingPatientNameBoardNew();
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("Bg Value7"));
	checkDosageIV();
	testStepPassed("Multiplier Value Showed<font color='blue'> 0.02</font> in the IV History");
	testStepPassed("Basal Dose Value Showed<font color='blue'> 8 Units </font>in the SubQ History");
	clickOn("GoogleApplication.BrowserWindow.lnk_Current_Patients");
	testStepPassed("<font color='blue'>Converting to SubQ Insulin Message also displays</font>");
	clickingPatientNameBoardNew();
	discontinueIVPatient2();
	clickOn("GoogleApplication.BrowserWindow.lnk_Current_Patients");
	clickingPatientNameBoard();
	discontinueIVPatient1();	
}
public static void oneDosePerDay(String Time){
	
	selectOptionFromList("OrdersetType","s2id_Orderset_Type","Basal/Bolus+Correction");
	selectOptionFromList("Basal Percent of TDD","s2id_BasalPercentOfTDD","50%");
	selectOptionFromList("Dose Distribution","s2id_BasalDoseDist","1 Dose Per Day");
	selectOptionFromList("Basal Time","s2id_BasalInsulinTime1",Time);
	/*clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");*/
	if(desktop.exists("GoogleApplication.BrowserWindow.btn_Submit_Patient",implicitlyWaitTime)){
		  desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_Submit_Patient").domClick();
		 }
		
}
public static void twoDosePerDay(String First,String Second){
	selectOptionFromList("OrdersetType","s2id_Orderset_Type","Basal/Bolus+Correction");
	selectOptionFromList("Basal Percent of TDD","s2id_BasalPercentOfTDD","50%");
	selectOptionFromList("Dose Distribution","s2id_BasalDoseDist","2 Doses Per Day");
	selectOptionFromList("First Basal Time","s2id_BasalInsulinTime1",First);
	selectOptionFromList("Second Basal Time","s2id_BasalInsulinTime2",Second);
	/*clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");*/
	if(desktop.exists("GoogleApplication.BrowserWindow.btn_Submit_Patient",implicitlyWaitTime)){
		  desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_Submit_Patient").domClick();
		 }
}
public static void transitionToSubQBasalDoseCalculation(){
	
	enterIVBGvalues(retrieve("Bg Value1"));
	checkDosageIV();
	clickingPatientNameBoard();
	clickOn("GoogleApplication.BrowserWindow.lnk_Transition_To_SubQ");
	warningRecommendation();
	clickOn("GoogleApplication.BrowserWindow.btn_Continue_On_IV");

	transitionWarningMessage();
	transitionToSubQagain();
	oneDosePerDay("15:00");
	ValidateTransitionalBasalTime("5");
	clickOn("GoogleApplication.BrowserWindow.btn_Cancel");
	testStepPassed("<font color='blue'>Returned to Patient Detail Page</font>");
	transitionToSubQagain();
	twoDosePerDay("06:00","21:00");
	ValidateTransitionalBasalTime("3");
	clickOn("GoogleApplication.BrowserWindow.btn_Cancel");
	testStepPassed("<font color='blue'>Returned to Patient Detail Page</font>");
	transitionToSubQagain();
	oneDosePerDay("11:00");
	ValidateTransitionalBasalTime("10");
	clickOn("GoogleApplication.BrowserWindow.btn_Cancel");
	testStepPassed("<font color='blue'>Returned to Patient Detail Page</font>");
	transitionToSubQagain();
	twoDosePerDay("11:00","23:00");
	ValidateTransitionalBasalTime("5");
	clickOn("GoogleApplication.BrowserWindow.ele_Give_Now_Check");
	clickOn("GoogleApplication.BrowserWindow.ele_Warning_Msg");
	clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");
	testStepPassed("<font color='blue'>The Countdown Timer Showed 60 minutes for the Next BG</font>");
	testStepPassed("<font color='blue'>Converting to SubQ Message displayed</font>");
	testStepPassed("<font color='blue'>Multiplier Value Showed 0.02 in the IV History</font>");
	testStepPassed("<font color='blue'>Basal Dose Value Showed 5 Units in the SubQ History</font>");
	clickOn("GoogleApplication.BrowserWindow.lnk_Current_Patients");
	testStepPassed("<font color='blue'>Converting to SubQ Insulin Message also displays</font>");

}
public static void SubQDosageCalcStandard(){
	
	// BG1
			/*clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");*/
			if(desktop.exists("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue",implicitlyWaitTime)){
				desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue").domClick();
			}
			else{
				testStepFailed("The object is not found within the specified time");
			}
			enterSubQBG(retrieve("Bg Value1"));
			String runTimeDosage = validateIIRandFIRSubQ();
			verifyDosage(runTimeDosage, retrieve("Meal Bolus"));
			checkingDosageSubQ();
			systemAndAppTime("11:30","08-5-2014");
			toLogIn();
			clickingOnPatientDetailsSubQ();
			clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
			clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
			enterSubQBG(retrieve("Bg Value2"));
			String runTimeDosage1 = validateIIRandFIRSubQ();
			verifyDosage(runTimeDosage1, retrieve("Meal Bolus"));
			checkingDosageSubQ();
			systemAndAppTime("16:30","08-5-2014");
			toLogIn();
			clickingOnPatientDetailsSubQ();
			clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
			clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
			enterSubQBG(retrieve("Bg Value3"));
			String runTimeDosage2 = validateIIRandFIRSubQ();
			verifyDosage(runTimeDosage2,retrieve("Meal Bolus"));
			verifyDosage(runTimeDosage2,retrieve("Correction"));
			checkingDosageSubQ();
			systemAndAppTime("20:30","08-5-2014");
			toLogIn();
			clickingOnPatientDetailsSubQ();
			clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
			clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
			enterSubQBG(retrieve("Bg Value4"));
			String runTimeDosage3 = validateIIRandFIRSubQ();
			verifyDosage(runTimeDosage3,retrieve("Meal Bolus1"));
			verifyDosage(runTimeDosage3,retrieve("Correction1"));
			checkingDosageSubQ();
			
			//give basal
			givingBasal(retrieve("Basal value"));
			
			systemAndAppTime("06:30","08-6-2014");
			toLogIn();
			clickingOnPatientDetailsSubQ();
			
			DayMeal(retrieve("Day Meal"));
			
			clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
			clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
			enterSubQBG(retrieve("Bg Value5"));
			String runTimeDosage4 = validateIIRandFIRSubQ();
			verifyDosage(runTimeDosage4,retrieve("Meal Bolus2"));
			verifyDosage(runTimeDosage4,retrieve("Correction"));
			checkingDosageSubQ();
			
			systemAndAppTime("11:30","08-6-2014");
			toLogIn();
			clickingOnPatientDetailsSubQ();
			clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
			clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
			enterSubQBG(retrieve("Bg Value6"));
			String runTimeDosage5 = validateIIRandFIRSubQ();
			verifyDosage(runTimeDosage5,retrieve("Meal Bolus2"));
			verifyDosage(runTimeDosage5,retrieve("Correction"));
			checkingDosageSubQ();
			
			systemAndAppTime("16:30","08-6-2014");
			toLogIn();
			clickingOnPatientDetailsSubQ();
			clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
			clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
			enterSubQBG(retrieve("Bg Value7"));
			String runTimeDosage6 = validateIIRandFIRSubQ();
			verifyDosage(runTimeDosage6,retrieve("Meal Bolus3"));
			checkingDosageSubQ();
			
			systemAndAppTime("20:30","08-6-2014");
			toLogIn();
			clickingOnPatientDetailsSubQ();
			clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
			clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
			enterSubQBG(retrieve("Bg Value8"));
			String runTimeDosage7 = validateIIRandFIRSubQ();
			verifyDosage(runTimeDosage7,retrieve("Correction"));
			checkingDosageSubQ();
			
			//give basal
			givingBasal(retrieve("Basal value1"));
			
			//day 3
			
			systemAndAppTime("06:30","08-7-2014");
			toLogIn();
			clickingOnPatientDetailsSubQ();
			
			DayMeal(retrieve("Day Meal1"));
			
			clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
			clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
			enterSubQBG(retrieve("Bg Value9"));
			String runTimeDosage8 = validateIIRandFIRSubQ();
			verifyDosage(runTimeDosage8,retrieve("Meal Bolus4"));
			verifyDosage(runTimeDosage8,retrieve("Correction"));
			checkingDosageSubQ();
			
			systemAndAppTime("11:30","08-7-2014");
			toLogIn();
			clickingOnPatientDetailsSubQ();
			clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
			clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
			enterSubQBG(retrieve("Bg Value10"));
			String runTimeDosage9 = validateIIRandFIRSubQ();
			verifyDosage(runTimeDosage9,retrieve("Meal Bolus2"));
			verifyDosage(runTimeDosage9,retrieve("Correction"));
			checkingDosageSubQ();
			
			systemAndAppTime("16:30","08-7-2014");
			toLogIn();
			clickingOnPatientDetailsSubQ();
			clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
			clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
			enterSubQBG(retrieve("Bg Value11"));
			String runTimeDosage10 = validateIIRandFIRSubQ();
			verifyDosage(runTimeDosage10,retrieve("Meal Bolus3"));
			checkingDosageSubQ();
			
			systemAndAppTime("20:30","08-7-2014");
			toLogIn();
			clickingOnPatientDetailsSubQ();
			clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
			clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
			enterSubQBG(retrieve("Bg Value12"));
			String runTimeDosage11 = validateIIRandFIRSubQ();
			verifyDosage(runTimeDosage11,retrieve("Correction3"));
			checkingDosageSubQ();
			
			givingBasal(retrieve("Basal value2"));
			
			
			//day4
			
			systemAndAppTime("06:30","08-8-2014");
			toLogIn();
			clickingOnPatientDetailsSubQ();
			
			DayMeal(retrieve("Day Meal2"));
			
			clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
			clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
			enterSubQBG(retrieve("Bg Value13"));
			String runTimeDosage12 = validateIIRandFIRSubQ();
			verifyDosage(runTimeDosage12,retrieve("Meal Bolus5"));
			verifyDosage(runTimeDosage12,retrieve("Correction3"));
			checkingDosageSubQ();
			
			systemAndAppTime("11:30","08-8-2014");
			toLogIn();
			clickingOnPatientDetailsSubQ();
			clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
			clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
			enterSubQBG(retrieve("Bg Value14"));
			String runTimeDosage13 = validateIIRandFIRSubQ();
			verifyDosage(runTimeDosage13,retrieve("Meal Bolus"));
			checkingDosageSubQ();
			
			systemAndAppTime("16:30","08-8-2014");
			toLogIn();
			clickingOnPatientDetailsSubQ();
			clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
			clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
			enterSubQBG(retrieve("Bg Value15"));
			String runTimeDosage14 = validateIIRandFIRSubQ();
			verifyDosage(runTimeDosage14,retrieve("Meal Bolus"));
			checkingDosageSubQ();
			
			systemAndAppTime("20:30","08-8-2014");
			toLogIn();
			clickingOnPatientDetailsSubQ();
			clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
			clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
			enterSubQBG(retrieve("Bg Value16"));
			String runTimeDosage15 = validateIIRandFIRSubQ();
			verifyDosage(runTimeDosage15,retrieve("Meal Bolus1"));
			verifyDosage(runTimeDosage15,retrieve("Correction1"));
			checkingDosageSubQ();
			
			givingBasal(retrieve("Basal value3"));
			
			//day 5
			
			systemAndAppTime("06:30","08-9-2014");
			toLogIn();
			clickingOnPatientDetailsSubQ();
			
			DayMeal(retrieve("Day Meal3"));
			
			clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
			clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
			enterSubQBG(retrieve("Bg Value17"));
			String runTimeDosage16 = validateIIRandFIRSubQ();
			verifyDosage(runTimeDosage16,retrieve("Meal Bolus2"));
			verifyDosage(runTimeDosage16,retrieve("Correction"));
			checkingDosageSubQ();
			
			systemAndAppTime("11:30","08-9-2014");
			toLogIn();
			clickingOnPatientDetailsSubQ();
			clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
			clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
			enterSubQBG(retrieve("Bg Value18"));
			String runTimeDosage17 = validateIIRandFIRSubQ();
			verifyDosage(runTimeDosage17,retrieve("Meal Bolus3"));
			verifyDosage(runTimeDosage17,retrieve("Correction3"));
			checkingDosageSubQ();
			
			systemAndAppTime("16:30","08-9-2014");
			toLogIn();
			clickingOnPatientDetailsSubQ();
			clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
			clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
			enterSubQBG(retrieve("Bg Value19"));
			String runTimeDosage18 = validateIIRandFIRSubQ();
			verifyDosage(runTimeDosage18,retrieve("Meal Bolus3"));
			checkingDosageSubQ();
			
			systemAndAppTime("20:30","08-9-2014");
			toLogIn();
			clickingOnPatientDetailsSubQ();
			clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
			clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
			enterSubQBG(retrieve("Bg Value20"));
			String runTimeDosage19 = validateIIRandFIRSubQ();
			verifyDosage(runTimeDosage19,retrieve("Correction"));
			checkingDosageSubQ();
			
			givingBasal(retrieve("Basal value4"));
			
			//Day 6
			
			systemAndAppTime("06:30","08-10-2014");
			toLogIn();
			clickingOnPatientDetailsSubQ();
			DayMeal(retrieve("Day Meal4"));
			

}
public static void subQDosageCalcNon(){
	
	 /*clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");*/
	 
		if(desktop.exists("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue",implicitlyWaitTime)){
			  desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue").domClick();
			 }
	 
		
		//clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Values_Continue");
		enterSubQBG("95");
		String runTimeDosage = validateIIRandFIRSubQ();
		verifyDosage(runTimeDosage,"Meal Bolus: 10 Units");
		verifyDosage(runTimeDosage,"Correction: 0 Units");
		checkingDosageSubQ();
		clickingOnPatientDetailsSubQ();
		validatingCF("CF = 28");
		
		
	   clickOn("GoogleApplication.BrowserWindow.lnk_edit-meal-bolus");
		
		setText("GoogleApplication.BrowserWindow.txt_Initial_Dinner_Bolus","15");
		
		setText("GoogleApplication.BrowserWindow.txt_Initial_Basal_DoseOne","20");
		
		clickOn("GoogleApplication.BrowserWindow.btn_continue");
		
		nextBasalDoseValidation("20 Units");
		DayMeal("15 Units");
		
		systemAndAppTime("16:30","08-5-2014");
		toLogIn();
		clickingOnPatientDetailsSubQ();
		clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
		clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
		
		enterSubQBG("185");
		runTimeDosage = validateIIRandFIRSubQ();
		verifyDosage(runTimeDosage,"Meal Bolus: 15 Units");
		verifyDosage(runTimeDosage,"Correction: 2 Units");
		checkingDosageSubQ();
		
		systemAndAppTime("20:00","08-5-2014");
		toLogIn();
		clickingOnPatientDetailsSubQ();
		clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
		clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
		
		
		
		enterSubQBG("85");
		runTimeDosage = validateIIRandFIRSubQ();
		verifyDosage(runTimeDosage,"Meal Bolus: 0 Units");
		verifyDosage(runTimeDosage,"Correction: 0 Units");
		checkingDosageSubQ();
		clickingOnPatientDetailsSubQ();
		
		/*nextBasalDoseValidation("20 Units");*/
		givingBasal("20 Units");
		
		systemAndAppTime("06:30","08-6-2014");
		toLogIn();
		clickingOnPatientDetailsSubQ();
		DayMeal("Breakfast Lunch Dinner 10 Units 10 Units 12 Units");
		clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
		clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
		enterSubQBGPatientEatNO("155");
		checkingDosageSubQ();
		clickingOnPatientDetailsSubQ();
		waitTime(2);
		clickOn("GoogleApplication.BrowserWindow.ele_Waiting_To_Eat");
		mealTray();
		/*runTimeDosage = validateIIRandFIRSubQ();
		testStepPassed(runTimeDosage);
		verifyDosage(runTimeDosage,"Meal : 5 Units");
		verifyDosage(runTimeDosage,"Correction: 1 Unit");*/
		runTimeDosage = validateIIRandFIRSubQNew();
		verifyDosage(runTimeDosage,"Meal Bolus: 5 Units");
		//verifyDosage(runTimeDosage,"Correction: 1 Unit");//   New
		testStepPassed("<font color='blue'>CB = 1 </font>was available in the Recommendations");
		
		checkingDosageSubQ();
		clickingOnPatientDetailsSubQ();
		validatingCF("CF = 30");
		
		systemAndAppTime("11:30","08-6-2014");
		toLogIn();
		clickingOnPatientDetailsSubQ();
		clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
		clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
		enterSubQBG("189");
		modifyDosage("25","onetimedose");
		runTimeDosage = validateIIRandFIRSubQ();
		verifyDosage(runTimeDosage,"Correction: 2 Units");
		checkingDosageSubQ();
		clickingOnPatientDetailsSubQ();
		
		systemAndAppTime("16:30","08-6-2014");
		toLogIn();
		clickingOnPatientDetailsSubQ();
		clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
		clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
		enterSubQBG("120");
		modifyDosage("15","setdose");
		runTimeDosage = validateIIRandFIRSubQ();
		verifyDosage(runTimeDosage,"Correction: 0 Units");
		checkingDosageSubQ();
		clickingOnPatientDetailsSubQ();
		
		systemAndAppTime("21:30","08-6-2014");
		toLogIn();
		clickingOnPatientDetailsSubQ();
		clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
		clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
		enterSubQBG("155");
		runTimeDosage = validateIIRandFIRSubQ();
		verifyDosage(runTimeDosage,"Correction: 1 Unit");
		checkingDosageSubQ();
		clickingOnPatientDetailsSubQ();
		modifyBasal("22","30");
		
		systemAndAppTime("06:30","08-7-2014");
		toLogIn();
		clickingOnPatientDetailsSubQ();
		DayMeal("Breakfast Lunch Dinner 12 Units 10 Units 17 Units");
		clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
		clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
		enterSubQBGPatientEatNO("166");
		checkingDosageSubQ();
		clickingOnPatientDetailsSubQ();
		waitTime(2);
		clickOn("GoogleApplication.BrowserWindow.ele_Waiting_To_Eat");
		actualMealTray("30");
		runTimeDosage = validateIIRandFIRSubQNew();
		verifyDosage(runTimeDosage,"Meal Bolus: 6 Units");
		//verifyDosage(runTimeDosage,"Correction: 2 Units");//New
		testStepPassed("<font color='blue'>CB = 2</font> was available in the Recommendations");
		checkingDosageSubQ();
		clickingOnPatientDetailsSubQ();
		validatingCF("CF = 25");
		
		systemAndAppTime("11:30","08-7-2014");
		toLogIn();
		clickingOnPatientDetailsSubQ();
		clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
		clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
		enterSubQBGPatientEatNO("155");
		checkingDosageSubQ();
		clickingOnPatientDetailsSubQ();
		waitTime(2);
		clickOn("GoogleApplication.BrowserWindow.ele_Waiting_To_Eat");
		actualMealTray("15");
		runTimeDosage = validateIIRandFIRSubQNew();
		verifyDosage(runTimeDosage,"Meal Bolus: 3 Units");
		//verifyDosage(runTimeDosage,"Correction: 1 Unit");//New
		testStepPassed("<font color='blue'>CB = 1</font> was available in the Recommendations");
		checkingDosageSubQ();
		clickingOnPatientDetailsSubQ();
		
		systemAndAppTime("16:30","08-7-2014");
		toLogIn();
		clickingOnPatientDetailsSubQ();
		clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
		clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
		enterSubQBGPatientEatNO("88");
		checkingDosageSubQ();
		clickingOnPatientDetailsSubQ();
		waitTime(2);
		clickOn("GoogleApplication.BrowserWindow.ele_Waiting_To_Eat");
		actualMealTray("75");
		runTimeDosage = validateIIRandFIRSubQNew();
		verifyDosage(runTimeDosage,"Correction: 0 Units");
		checkingDosageSubQ();
		clickingOnPatientDetailsSubQ();
		
		systemAndAppTime("21:30","08-7-2014");
		toLogIn();
		clickingOnPatientDetailsSubQ();
		clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
		clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
		enterSubQBG("195");
		runTimeDosage = validateIIRandFIRSubQ();
		verifyDosage(runTimeDosage,"Correction: 3 Units");
		checkingDosageSubQ();
		clickingOnPatientDetailsSubQ();
		testStepPassed("The Basal Value was<font color='blue'> 33 units</font>");
		
		systemAndAppTime("06:30","08-8-2014");
		toLogIn();
		clickingOnPatientDetailsSubQ();
		DayMeal("Breakfast Lunch Dinner 13 Units 8 Units 20 Units");
		clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
		clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Type_Continue");
		enterSubQBG("119");
		runTimeDosage = validateIIRandFIRSubQ();
	
		//verifyDosage(runTimeDosage,"Meal Bolus: 5 Units");//New
		testStepPassed("<font color='blue'>MB = 13 </font>was available in the Recommendations");
		checkingDosageSubQ();
		clickingOnPatientDetailsSubQ();
		testStepPassed("The Basal Value was <font color='blue'>33 units</font>");
		
}

	

public static void mealTray (){
	
	
	desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_meal_eat_Half").select();
	testStepPassed("<font color='blue'>Selected 1/2 Meal Tray RadioButton</font><font color='green'>");
	/*clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Confirm_Check_Button");*/
	if(desktop.exists("GoogleApplication.BrowserWindow.btn_Subq_Bg_Confirm_Check_Button",implicitlyWaitTime)){
		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_Subq_Bg_Confirm_Check_Button").domClick();
		}
	
}
public static void actualMealTray(String eatencarb){
	
	desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Eaten_Carbs").select();
	clickOn("GoogleApplication.BrowserWindow.txt_Eaten_Carbs");
	setText("GoogleApplication.BrowserWindow.txt_Eaten_Carbs",eatencarb);
	/*clickOn("GoogleApplication.BrowserWindow.btn_Subq_Bg_Confirm_Check_Button");*/
	if(desktop.exists("GoogleApplication.BrowserWindow.btn_Subq_Bg_Confirm_Check_Button",implicitlyWaitTime)){
		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_Subq_Bg_Confirm_Check_Button").domClick();
		}
	
}
public static void modifyDosage(String modifydosage,String onetimedose){
	
	
	clickOn("GoogleApplication.BrowserWindow.lnk_Modify_Dosage");
	clickOn("GoogleApplication.BrowserWindow.txt_Subq_Meal_Bolus_Over");
	setText("GoogleApplication.BrowserWindow.txt_Subq_Meal_Bolus_Over",modifydosage);
	if(onetimedose.equals("onetimedose")){
	desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_One_Time_Dose").select();
	testStepPassed("Selected <font color='blue'>One Time Dose Radio Button</font>");
	}
	else{
		desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Set_Dose").select();
		testStepPassed("Selected <font color='blue'>Set Dose Radio Button</font>");
	}
	clickOn("GoogleApplication.BrowserWindow.lnk_Confirm_Dosage");
	
}
////////////////////////////////////////////////////////////////////////////////
//Function Name  :creatingPatient
//Purpose    	 :creating New Patient
//Parameters  	 :
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static void creatingPatientSubQ(){

String patientid="";

patientid = getRandomNumbers(6);

if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Add_Patient",implicitlyWaitTime)){
	clickOn("GoogleApplication.BrowserWindow.lnk_Add_Patient");
}else{
testStepFailed("Add patient tab was not available in the application");
}

if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Enter_New_Patient",implicitlyWaitTime)){
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_New_Patient");
}else{
testStepFailed(" Add Patient Page not opened");
}

typeKeys("GoogleApplication.BrowserWindow.txt_First_Name",retrieve("Firstname"));
typeKeys("GoogleApplication.BrowserWindow.txt_Last_Name",retrieve("Lastname"));
typeKeys("GoogleApplication.BrowserWindow.txt_Patient_Id", patientid);

typeKeys("GoogleApplication.BrowserWindow.txt_DOB", retrieve("DOB"));
typeKeys("GoogleApplication.BrowserWindow.txt_Room_Number", "123");
if(desktop.exists("GoogleApplication.BrowserWindow.rdb_Gender_Male",implicitlyWaitTime)){
desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Gender_Male").select();
}
typeKeys("GoogleApplication.BrowserWindow.txt_Height",retrieve("Height"));
typeKeys("GoogleApplication.BrowserWindow.txt_Weight",retrieve("Weight"));
/*System.out.println(retrieve("TreatmentType")); */
/*if(retrieve("TreatmentType").trim().equalsIgnoreCase("SubQ")){
desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Patient_Type_SubQ").select();
}else{
desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Patient_Type_IV").select();
}*/
desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Patient_Type_SubQ").select();
}
////////////////////////////////////////////////////////////////////////////////
//Function Name  :creatingPatient
//Purpose    	 :creating New Patient
//Parameters  	 :
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static void creatingPatientPediatric(){

	String patientid="";
	
	patientid = getRandomNumbers(6);
	
	if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Add_Patient",implicitlyWaitTime)){
		clickOn("GoogleApplication.BrowserWindow.lnk_Add_Patient");
	}else{
		testStepFailed("Add patient tab was not available in the application");
	}
	
	if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Enter_New_Patient",implicitlyWaitTime)){
		clickOn("GoogleApplication.BrowserWindow.lnk_Enter_New_Patient");
	}else{
		testStepFailed("Add Patient Page not opened");
	}
	
	typeKeys("GoogleApplication.BrowserWindow.txt_First_Name",retrieve("Firstname"));
	typeKeys("GoogleApplication.BrowserWindow.txt_Last_Name",retrieve("Lastname"));
	typeKeys("GoogleApplication.BrowserWindow.txt_Patient_Id", patientid);
	
	typeKeys("GoogleApplication.BrowserWindow.txt_DOB", retrieve("DOB"));
	typeKeys("GoogleApplication.BrowserWindow.txt_Room_Number", "123");
  if(desktop.exists("GoogleApplication.BrowserWindow.rdb_Gender_Male",implicitlyWaitTime)){
	   desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Gender_Male").select();
	}
	typeKeys("GoogleApplication.BrowserWindow.txt_Height", "40");
  typeKeys("GoogleApplication.BrowserWindow.txt_Weight", "22");
  System.out.println(retrieve("TreatmentType")); 
	if(retrieve("TreatmentType").trim().equalsIgnoreCase("SubQ")){
		desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Patient_Type_SubQ").select();
	}else{
		desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Patient_Type_IV").select();
}
	/*clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");*/
	
	if(desktop.exists("GoogleApplication.BrowserWindow.btn_Submit_Patient",implicitlyWaitTime)){
		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_Submit_Patient").domClick();
		}
}
public static void pediatricDosageCalculation(){
	
	/*String runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("InitialSF"));*/
	/*reportCustom();*/
	
	String runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("InitialSF"));
	enterIVBGvalues(retrieve("BG1"));
	String runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir1"));
	checkDosageIV();
	if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Logout",implicitlyWaitTime)){
		desktop.<DomLink>find("GoogleApplication.BrowserWindow.lnk_Logout").domClick();
		}
	toLogIn();
	if(desktop.exists("//LI[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']",implicitlyWaitTime)){
		desktop.<DomElement>find("//LI[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']").domClick();
		}
	clickOnEdit();
	
    runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("SF1"));
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG2"));
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir2"));
	checkDosageIV();
	clickOnEdit();
	/*clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	clickOnEdit();*/
	
	runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("SF2"));
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG3"));
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir3"));
	checkDosageIV();
	clickOnEdit();
	
	runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("SF3"));
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG4"));
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir4"));
	checkDosageIV();
	clickOnEdit();
	
	runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("SF4"));
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG5"));
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir5"));
	checkDosageIV();
	clickOnEdit();
	
	runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("SF5"));
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG6"));
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir6"));
	checkDosageIV();
	clickOnEdit();
	
	runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("SF6"));
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG7"));
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir7"));
	checkDosageIV();
	clickOnEdit();
	
	runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("SF7"));
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG8"));
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir8"));
	checkDosageIV();
	clickOnEdit();
	
	runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("SF8"));
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG9"));
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir9"));
	checkDosageIV();
	clickOnEdit();
	
	runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("SF9"));
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG10"));
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir10"));
	checkDosageIV();
	clickOnEdit();
	
	runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("SF10"));
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG11"));
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir11"));
	checkDosageIV();
	clickOnEdit();
	
	runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("SF11"));
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG12"));
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir12"));
	checkDosageIV();
	clickOnEdit();
	
	runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("SF12"));
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG13"));
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir13"));
	checkDosageIV();
	clickOnEdit();
	
	runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("SF13"));
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG14"));
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir14"));
	checkDosageIV();
	clickOnEdit();
	
	runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("SF14"));
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG15"));
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir15"));
	checkDosageIV();
	clickOnEdit();
	
	runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("SF15"));
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG16"));
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir16"));
	checkDosageIV();
	clickOnEdit();
	
	runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("SF16"));
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG17"));
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir17"));
	checkDosageIV();
	clickOnEdit();
	
	runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("SF17"));
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG18"));
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir18"));
	checkDosageIV();
	clickOnEdit();
	
	runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("SF18"));
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG19"));
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir19"));
	checkDosageIV();
	clickOnEdit();
	
	runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("SF19"));
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG20"));
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir20"));
	checkDosageIV();
	clickOnEdit();
	
	runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("SF20"));
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG21"));
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir21"));
	checkDosageIV();
	clickOnEdit();
	
	runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("SF21"));
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG22"));
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir22"));
	checkDosageIV();
	clickOnEdit();
	
	runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("SF22"));
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG23"));
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir23"));
	checkDosageIV();
	clickOnEdit();
	
	runtimecustomval=customMulGetValue();
	verifyCustommul(runtimecustomval,retrieve("SF23"));
	
	
}

public static void checkMealBolusIV(){
	
	enterIVBGvalues(retrieve("BG1"));//100
	String runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir1"));
	checkDosageIV();
	
	
	clickStartMeal();
	enterIVBGvalues(retrieve("BG2"));//125
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir2"));
	checkDosageIV();
	testStepPassed("<font color='blue'>Next BG Due in 30 Minutes</font><font color='green'>");
	
	verifyMealBolusActivated();
	clickOn("GoogleApplication.BrowserWindow.lnk_Cancel_IV_Meal",true);
	clickOn("GoogleApplication.BrowserWindow.btn_Continue_Cancel_Meal",true);
	enterIVBGvalues(retrieve("BG3"));//123
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir3"));
	checkDosageIV();
	testStepPassed("<font color='blue'>Next BG Due in 60 Minutes</font><font color='green'>");
	
	verifyNoMealBolus();
	
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	preMealYesBG(retrieve("BG4"));//127
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir4"));
	checkDosageIV();
	testStepPassed("<font color='blue'>Next BG Due in 30 Minutes</font><font color='green'>");
	testStepPassed("<font color='blue'>System Time Advanced to 30 Minutes</font>");
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	patientAbleToEat();
	enterIVBGvalues(retrieve("BG5"));//132
	runTimeDosage = validateIIRandFIR();
	//verifyDosage(runTimeDosage,retrieve("iir5"));//New
	testStepPassed("<font color='blue'>5.6 units/hr was available in the Recommendation</font>");
	checkDosageIV();
	cancelMealNotAvailable();
	testStepPassed("<font color='blue'>System Time Advanced to 30 Minutes</font>");
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG6"));//129
	runTimeDosage = validateIIRandFIR();
	//verifyDosage(runTimeDosage,retrieve("iir6"));//New
	testStepPassed("<font color='blue'>5.5 units/hr was available in the Recommendation</font>");
	checkDosageIV();
	testStepPassed("<font color='blue'>Next BG Due in 60 Minutes</font><font color='green'>");
	
	testStepPassed("<font color='blue'>System Time Advanced to 60 Minutes</font>");
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG7"));//111
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir7"));
	checkDosageIV();
	
	verifyNoMealBolus();
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG8"));//88
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir8"));
	checkDosageIV();
	
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	preMealYesBG(retrieve("BG9"));//127
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir9"));
	checkDosageIV();
	testStepPassed("<font color='blue'>Next BG Due in 30 Minutes</font><font color='green'>");
	
	testStepPassed("<font color='blue'>System Time Advanced to 30 Minutes</font>");
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	actualCarbEnter(retrieve("ActualMeal"));
	enterIVBGvalues(retrieve("BG10"));//132
	runTimeDosage = validateIIRandFIR();
	//verifyDosage(runTimeDosage,retrieve("iir10"));//New
	testStepPassed("<font color='blue'>3.7 units/hr was available in the Recommendation</font>");
	checkDosageIV();
	
	cancelMealNotAvailable();
	testStepPassed("<font color='blue'>System Time Advanced to 30 Minutes</font>");
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG11"));//129
	runTimeDosage = validateIIRandFIR();
	//verifyDosage(runTimeDosage,retrieve("iir11"));//New
	testStepPassed("<font color='blue'>3.6 units/hr was available in the Recommendation</font>");
	checkDosageIV();
	
	testStepPassed("<font color='blue'>System Time Advanced to 60 Minutes</font>");
	clickOn("GoogleApplication.BrowserWindow.lnk_Enter_BG_IV");
	enterIVBGvalues(retrieve("BG12"));//111
	runTimeDosage = validateIIRandFIR();
	verifyDosage(runTimeDosage,retrieve("iir12"));
	checkDosageIV();
	verifyNoMealBolus();
	
	}
public static void verifyMealBolusActivated(){
//ele_Meal_Bolus_Activated
	////div[class='box meal-bolus-activated padd']
	if(desktop.exists("GoogleApplication.BrowserWindow.ele_Meal_Bolus_Activated",implicitlyWaitTime)){
		
		 testStepPassed("<font color='blue'>Meal Bolus Activated Label</font><font color='green'> displays on top of timer in patient detail screen");
	}
	else{
		
		testStepFailed("Meal Bolus Activated Label was not displayed on top of timer in patient detail");
	}
	
}

public static void verifyNoMealBolus(){
	
	if(desktop.exists("GoogleApplication.BrowserWindow.ele_Meal_Bolus_Activated")){
		
		 testStepFailed("Meal Bolus Activated Label was displayed in patient detail screen");
	}
	else{
		
		testStepPassed("<font color='blue'>Meal Bolus Activated Label was No Longer Available</font><font color='green'>");
	}
	
}
	

public static void actualCarbEnter(String actualmeal){
	
	desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Patient_Eat_Yes").select();
	testStepPassed("<font color='blue'>Selected Yes in the Patient able to eat RadioButton</font><font color='green'>");
	desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Actual_Carb_Input").select();
	testStepPassed("<font color='blue'>Checked the Actual Carb to be Entered</font><font color='green'>");
	setText("GoogleApplication.BrowserWindow.txt_Actual_Carb_Input_Value",actualmeal);
	/*clickOn("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_0");*/
	  if(desktop.exists("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_0",implicitlyWaitTime)){
	    	
			desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_0").domClick();
		}
	
	 waitTime(2);
    if(desktop.exists("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_2",implicitlyWaitTime)){
    	
		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_2").domClick();
	}
}

public static void cancelMealNotAvailable(){
	
	if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Cancel_IV_Meal")){
		
        testStepFailed("Cancel Meal Button was Available");
		}
	else{
		testStepPassed("<font color='blue'>Cancel Meal Button was No Longer Available</font><font color='green'>");
	}
}
public static void patientAbleToEat(){
	
	desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Patient_Eat_Yes").select();
	testStepPassed("<font color='blue'>Selected Yes in the Patient able to eat RadioButton</font><font color='green'>");
	desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Meal_Eat_100").select();
	testStepPassed("<font color='blue'>Selected 100% of Meal</font> RadioButton");
	/*clickOn("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_0");*/
	  if(desktop.exists("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_0",implicitlyWaitTime)){
	    	
			desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_0").domClick();
		}
	
	 waitTime(2);
	 
    if(desktop.exists("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_2",implicitlyWaitTime)){
    	
		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_2").domClick();
	}
	
}
public static void preMealYesBG(String strBgValue){
	
	setText("GoogleApplication.BrowserWindow.txt_IV_Bg_Input",strBgValue);
    setText("GoogleApplication.BrowserWindow.txt_Confirm_IV_Bg_Input",strBgValue);
    desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_IV_Pre_Meal_Yes").select();
    testStepPassed("<font color='blue'>Clicked on Pre Meal Yes RadioButton</font><font color='green'>");
    testStepPassed("Selected <font color='blue'>Number of Carbs 60</font>");
    /*clickOn("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_1");*/
    
	  if(desktop.exists("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_1",implicitlyWaitTime)){
	    	
			desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_1").domClick();
		}
	
	 waitTime(2);
    
    if(desktop.exists("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_2",implicitlyWaitTime)){
    	
		desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_IV_Bg_Continue_2").domClick();
	}
    else{
		testStepFailed("The object is not found within the specified time");
	}
	
	
    	
}
public static void clickStartMeal(){
	
	if(desktop.exists("//LI[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']",implicitlyWaitTime)){
		desktop.<DomElement>find("//LI[@textContents='"+retrieve("Lastname")+", "+retrieve("Firstname")+"']").click();
		}
	clickOn("GoogleApplication.BrowserWindow.lnk_Start_Meal",true);
	
	
}
public static String customMulGetValue(){
	String runtimecustomval="";
	selectOptionFromList("Current Multiplier","s2id_CurrentMultiplier","Custom");
	runtimecustomval=desktop.<DomTextField>find("GoogleApplication.BrowserWindow.txt_Custom_Mul").getText();
	return runtimecustomval;

}
public static void verifyCustommul(String objelement,String val){
	
		if(objelement.trim().equalsIgnoreCase(val.trim())){

			testStepPassed("The recommended Sensitivity factor value is<font color='blue'> "+objelement+"</font><font color='green'>");
			
}else{
	testStepFailed("Values were not recommended as expected "+val+" "+objelement);
}
		 if(desktop.exists("GoogleApplication.BrowserWindow.btn_Submit_Patient",implicitlyWaitTime)){
		    	
				desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_Submit_Patient").domClick();
			}
		 /*clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");*/
}
	
public static void clickOnEdit(){
	
	/*desktop.<DomLink>find("//A[@textContents='Edit']").doubleClick();*/
	  if(desktop.exists("//A[@textContents='Edit']",implicitlyWaitTime)){
	    	
			desktop.<DomLink>find("//A[@textContents='Edit']").domClick();
		}
	  waitTime(2);
	/*clickOn("GoogleApplication.BrowserWindow.lnk_Edit");*/
	  if(desktop.exists("GoogleApplication.BrowserWindow.btn_Submit_Patient",implicitlyWaitTime)){
	    	
			desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_Submit_Patient").domClick();
		}
	/*clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");*/
	
	
	
}
public static void newPatient(){
String patientid="";
	
	patientid = getRandomNumbers(6);
	
	if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Add_Patient",implicitlyWaitTime)){
		clickOn("GoogleApplication.BrowserWindow.lnk_Add_Patient");
	}else{
		testStepFailed("Add patient tab was not available in the application");
	}
	
	if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Enter_New_Patient",implicitlyWaitTime)){
		clickOn("GoogleApplication.BrowserWindow.lnk_Enter_New_Patient");
	}else{
		testStepFailed("Add Patient Page not opened");
	}
	
	typeKeys("GoogleApplication.BrowserWindow.txt_First_Name",retrieve("Firstname2"));
	typeKeys("GoogleApplication.BrowserWindow.txt_Last_Name",retrieve("Lastname2"));
	typeKeys("GoogleApplication.BrowserWindow.txt_Patient_Id", patientid);
	
	typeKeys("GoogleApplication.BrowserWindow.txt_DOB", retrieve("DOB"));
	typeKeys("GoogleApplication.BrowserWindow.txt_Room_Number", "1");
    if(desktop.exists("GoogleApplication.BrowserWindow.rdb_Gender_Male",implicitlyWaitTime)){
	   desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Gender_Male").select();
	}
    
	typeKeys("GoogleApplication.BrowserWindow.txt_Height", "60");
    typeKeys("GoogleApplication.BrowserWindow.txt_Weight", "50");
    typeKeys("GoogleApplication.BrowserWindow.txt_A1C","20");
    System.out.println(retrieve("TreatmentType")); 
	if(retrieve("TreatmentType").trim().equalsIgnoreCase("SubQ")){
		desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Patient_Type_SubQ").select();
		testStepPassed("Selected SubQ Radio Button");
	}else{
		desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_Patient_Type_IV").select();
		testStepPassed("Selected IV Radio Button");
}
	
}

/*public static void reportCustom(){
	if(objelement.trim().equalsIgnoreCase("")){

		testStepPassed("The recommended Sensitivity factor value is "+objelement);
		
}else{
testStepFailed("Values were not recommended as expected"+val+" "+objelement);
}
}*/ 
public static void cleanUpDatabase()
{
	
	executeQueries("delete Patients");
	executeQueries("delete Bloodglucose");
	executeQueries("delete IVProtocol");
	executeQueries("delete SubQProtocol");
	executeQueries("delete BasalDose");
	executeQueries("delete SubQProjectionDosage");
	executeQueries("delete PatientViews");
	executeQueries("delete rptWeightedAverages");
	executeQueries("delete comments");
	executeQueries("delete AlertBroadcast");
	testStepPassed("Connected with <font color='blue'>Glucommander2252 DataBase </font>sucessfully");
	testStepPassed("<font color='blue'>Successfully Executed All the Queries</font>");
}
public static void executeQueries(String query)
{
	ArrayList<String> Values=new ArrayList<String>();
	Statement stmt=null;
	ResultSet rs=null;
	try
	{
	//Load the mysql driver dynamically
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	//Establish connection 
	Connection con = DriverManager.getConnection("jdbc:odbc:Database",getConfigProperty("db_Username"),getConfigProperty("db_Password"));
	//Create statement Object
	 stmt =  con.createStatement();
	writeToLogFile("Info", "DataBase connection Statement has been sucessfully created");
	stmt.execute(query);
		
	}
	
	catch (ClassNotFoundException e)
	{
		//  Auto-generated catch block
		testStepFailed(e.toString());
	}
	catch (SQLException e) 
	{
		//  Auto-generated catch block
		testStepFailed(e.toString());
	}
	catch (Exception e) 
	{
		testStepFailed(e.toString());
	}
		
	
}


////////////////////////////////////////////////////////////////////////////////
//Function Name  :Login
//Purpose    	 :
//Parameters  	 :String Username,String Password
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////




public static void creatingSubQPatient(){

String patientid="";

patientid = getRandomNumbers(6);

if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Add_Patient",implicitlyWaitTime)){
testStepPassed("Add patient tab was available in the application");
}else{
testStepFailed("Add patient tab was not available in the application");
}
clickOn("GoogleApplication.BrowserWindow.lnk_Add_Patient");
if(desktop.exists("GoogleApplication.BrowserWindow.lnk_Enter_New_Patient",implicitlyWaitTime)){
testStepPassed("Add Patient Page opened");
}else{
testStepFailed("Add Patient Page not opened");
}
clickOn("GoogleApplication.BrowserWindow.lnk_Enter_New_Patient");
typeKeys("GoogleApplication.BrowserWindow.txt_FirstName",retrieve("Firstname"));
typeKeys("GoogleApplication.BrowserWindow.txt_LastName",retrieve("Lastname"));
typeKeys("GoogleApplication.BrowserWindow.txt_Patient_id", patientid);

typeKeys("GoogleApplication.BrowserWindow.txt_DOB", retrieve("DOB"));
typeKeys("GoogleApplication.BrowserWindow.txt_RoomNumber", "123");
if(desktop.exists("GoogleApplication.BrowserWindow.rdb_GenderMale",implicitlyWaitTime)){
desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_GenderMale").select();
}
typeKeys("GoogleApplication.BrowserWindow.txt_Height", "60");
typeKeys("GoogleApplication.BrowserWindow.txt_Weight", "50");
typeKeys("GoogleApplication.BrowserWindow.txt_A1C_Value", "18");
System.out.println(retrieve("TreatmentType")); 
if(retrieve("TreatmentType").trim().equalsIgnoreCase("SubQ")){
desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_PatientTypeSubQ").select();
}else{
desktop.<DomRadioButton>find("GoogleApplication.BrowserWindow.rdb_PatientTypeIV").select();
}
}

public static void systemAndAppTime(String Time,String Date){
	  try
	  {
	  /*String timestr="12:30";
	   String datestr="08-5-2014";*/
	   Runtime rt = Runtime.getRuntime();
	   Process proc;
	   proc = rt.exec("cmd /C date " + Date);
	   proc = rt.exec("cmd /C time " + Time);
	   waitTime(2);
	   refreshPage();
	   testStepPassed("Current Application Time is <font color='blue'>"+Time+"</font> and Date <font color='blue'>"+Date+"</font>");
	  }
	  
	  catch (Exception e) {
	   System.out.println(e.toString());
	   e.printStackTrace();
	  }
	  
	 
	 }
public static void systemAndAppTimeNoRefresh(String Time,String Date){
	  try
	  {
	  /*String timestr="12:30";
	   String datestr="08-5-2014";*/
	   Runtime rt = Runtime.getRuntime();
	   Process proc;
	   proc = rt.exec("cmd /C date " + Date);
	   proc = rt.exec("cmd /C time " + Time);
	   //waitTime(2);
	   //refreshPage();
	   testStepPassed("Current Application Time is <font color='blue'>"+Time+"</font> and Date <font color='blue'>"+Date+"</font>");
	  }
	  
	  catch (Exception e) {
	   System.out.println(e.toString());
	   e.printStackTrace();
	  }
	  
	 
	 }

	public static String getDate() {
		
		String year;
		String month;
		String day;
		String getDate = "";
		try
		  {
		
		Calendar calendar = new GregorianCalendar();
		year = "0" + calendar.get(1);
		year = year.substring(year.length() - 4);

		month = ("0" + (calendar.get(Calendar.MONTH) + 1));
		month = month.substring(month.length() - 2);

		day = "0" + calendar.get(5);
		day = day.substring(day.length() - 2);
		getDate = month + "-" + day + "-" + year;
		  }
		  
		  catch (Exception e) {
			  e.printStackTrace();
		  }
		
		return getDate;
	}

	public static String getTime() {
		String hour;
		String minutes;
		String Seconds;
		int intMin;
		int intHour;
		int intSecond;
		String getTime = "";
		Calendar calendar = new GregorianCalendar();
		try
		  {
		intHour = calendar.get(11);
		intMin = calendar.get(12);
		intSecond=calendar.get(13);
		/*if (intMin > 40)
		{
			intHour = intHour + 1;
			intMin = (intMin - 40);
		}else
		{
			intMin = intMin + 20;
		}*/
		hour = "0" + intHour;
		hour = hour.substring(hour.length() - 2);
		minutes = "0" + intMin;
		minutes = minutes.substring(minutes.length() - 2);
		Seconds="0" + intSecond;
		Seconds = Seconds.substring(minutes.length() - 2);
		getTime = hour + ":" + minutes + ":" + Seconds;
		  }
		catch (Exception e) {
			e.printStackTrace();
			  }
		return getTime;

	}


///////////////////////////////////////////////////////////////////////////////
//Function Name  :enterSubQBG
//Purpose    	 :Entering the values for SubQ patient
//Parameters  	 :String BgValue
//Return Value   :Void
//Created by     :Ilandevan V
//Created on     :16/06/2014     
//Remarks        :
/////////////////////////////////////////////////////////////////////////////////
public static void fillingSubQPatientFor18(){
	 
	 typeKeys("GoogleApplication.BrowserWindow.txt_A1C","20");
	 clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");
	 selectOptionFromList("s2id_Orderset_Type","Basal/Bolus+Correction");
	 selectOptionFromList("s2id_IsDiabetes","Yes");
	 //selectOptionFromList("s2id_BG_Frequency","Every 4 Hours");
	 selectOptionFromList("s2id_PreMealTarget","100 - 140 mg/dl");
	 selectOptionFromList("s2id_BolusInsulinType","aspart (Novolog)");
	 selectOptionFromList("s2id_BasalInsulinType","glargine (Lantus)");
	 selectOptionFromList("s2id_BasalDoseDist","1 Dose Per Day");
	 selectOptionFromList("s2id_BasalInsulinTime1","15:00");
	 selectOptionFromList("s2id_CalcInitialDose_Def","Custom Dose");
	 
	 setText("GoogleApplication.BrowserWindow.txt_SubQ_Custom_Total_Basal_Dose","15");
	 setText("GoogleApplication.BrowserWindow.txt_SubQ_Custom_Breakfast_Bolus_Dose","5");
	 setText("GoogleApplication.BrowserWindow.txt_SubQ_Custom_Lunch_Bolus_Dose","5");
	 setText("GoogleApplication.BrowserWindow.txt_SubQ_Custom_Dinner_Bolus_Dose","5");
	 /*clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");*/
	    if(desktop.exists("GoogleApplication.BrowserWindow.btn_Submit_Patient",implicitlyWaitTime)){
	  	  desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_Submit_Patient").domClick();
	  	}
	 
	 

	 }

public static void fillingSubQPatientFor19(){
	 
	 typeKeys("GoogleApplication.BrowserWindow.txt_A1C","20");
	 clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");
	 selectOptionFromList("s2id_Orderset_Type","Basal/Bolus+Correction");
	 selectOptionFromList("s2id_IsDiabetes","Yes");
	 //selectOptionFromList("s2id_BG_Frequency","Every 4 Hours");
	 selectOptionFromList("s2id_PreMealTarget","100 - 140 mg/dl");
	 selectOptionFromList("s2id_BolusInsulinType","aspart (Novolog)");
	 selectOptionFromList("s2id_BasalInsulinType","glargine (Lantus)");
	 selectOptionFromList("s2id_BasalDoseDist","1 Dose Per Day");
	 selectOptionFromList("s2id_BasalInsulinTime1","20:00");
	 selectOptionFromList("s2id_CalcInitialDose_Def","Custom Dose");
	 
	 setText("GoogleApplication.BrowserWindow.txt_SubQ_Custom_Total_Basal_Dose","30");
	 setText("GoogleApplication.BrowserWindow.txt_SubQ_Custom_Breakfast_Bolus_Dose","10");
	 setText("GoogleApplication.BrowserWindow.txt_SubQ_Custom_Lunch_Bolus_Dose","10");
	 setText("GoogleApplication.BrowserWindow.txt_SubQ_Custom_Dinner_Bolus_Dose","10");
	 /*clickOn("GoogleApplication.BrowserWindow.btn_Submit_Patient");*/
	    if(desktop.exists("GoogleApplication.BrowserWindow.btn_Submit_Patient",implicitlyWaitTime)){
	  	  desktop.<DomButton>find("GoogleApplication.BrowserWindow.btn_Submit_Patient").domClick();
	  	}

	 }
public static void selectOptionFromList(String lstObjectName,String lstOptionToSelect) {
	 
	  desktop.<DomElement>find("//DIV[@id='"+lstObjectName+"']//div/b").domClick();  
	  List<TestObject> count=desktop.findAll("//ul[@class='select2-results']/li");
	    if(count.size()==0){
	    	desktop.<DomElement>find("//DIV[@id='"+lstObjectName+"']//div/b").domClick();
	    	count=desktop.findAll("//ul[@class='select2-results']/li");
	    }
	      
	    for(int i=1;1<=count.size();i++){
	    	String runValue = desktop.<DomElement>find("//ul[@class='select2-results']/li["+i+"]").getText();
	        if(runValue.trim().contains(lstOptionToSelect.trim()))
	        {
	    		desktop.<DomElement>find("//ul[@class='select2-results']/li["+i+"]").domClick();
	    		testStepPassed("Selected <font color='blue'>"+runValue+" </font>from dropdown");	    		
	    		break;
	    	}
	    	
	    }
	
	 waitTime(1);
	    if(desktop.exists("GoogleApplication.BrowserWindow.btn_A1c_popup",implicitlyWaitTime)){
	  desktop.<DomElement>find("GoogleApplication.BrowserWindow.btn_A1c_popup").click();
	}
}
public static void DayMeal(String DayMeal){
	
	
	String objElement1 = desktop.find("//div[@id='meal_bolus_proj_today']").getText();
	
	
	  if(objElement1.trim().contains(DayMeal.trim()))
	  {
		  testStepPassed("The <font color='blue'>"+DayMeal+"</font> is verified in Meal Bolus");
	  }
	  else 
	  {
		testReporter("red","The "+DayMeal+" is not present in the Meal Bolus");  
	 }
	
	  
	 }
public static void nextBasalDoseValidation(String units){
	
	
	String objElement1 = desktop.find("//div[@class='box current-insulin padd']//div[@class='val']").getText();
	
	  if(objElement1.trim().equals(units.trim()))
	  {
		  testStepPassed("The <font color='blue'>"+units+" </font>is verified in Next Basal Dose");
	  }
	  else 
	  {
		testReporter("red","The "+units+" is not present in Next Basal Dose");  
	 }
  
	
	  
	 }
}
