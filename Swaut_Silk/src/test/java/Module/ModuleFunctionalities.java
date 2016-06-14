package Module;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import utilities.Common;

import com.borland.silktest.jtf.common.types.MouseButton;
import com.borland.silktest.jtf.xbrowser.DomButton;
import com.borland.silktest.jtf.xbrowser.DomElement;
import com.borland.silktest.jtf.xbrowser.DomLink;
import com.borland.silktest.jtf.xbrowser.DomTextField;

public class ModuleFunctionalities extends _CommonUtilities
{

	static int j=0;
	static Thread t;
	static String strTime=null;
	static String strDate=null;
	////////////////////////////////////////////////////////////////////////////////
	//Function Name  :tc001_AccessGeneralSettings
	//Purpose    	 :AccessGeneralSettings
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Anbananthan D
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static void  tc001_AccessGeneralSettings() {
		//Step 1:Open an Internet browser
		//Step 2:type the system test link http://v2240.d04.glytecenterprise.com and pressEnter
		//Step 3:Type in the following information [user: demo1, password: Testtony1] and click the Login button	
		Login(retrieve("Username"),retrieve("Password"));
		//Step 4:Click the Learning Center tab 
		verifyTabs("GoogleApplication.BrowserWindow.lnk_Learning Center","GoogleApplication.BrowserWindow.ele_user-manual-download","User Manual download is available in the application","User Manual download is not available in the application");
		//Step 5:Click the Admin tab
		verifyTabs("GoogleApplication.BrowserWindow.lnk_Admin","GoogleApplication.BrowserWindow.ele_addUser_Title","Add User is available in the application","Add User is not available in the application");
		//Step 6:Click the General Settingslink
		verifyTabs("GoogleApplication.BrowserWindow.lnk_General_Setting","GoogleApplication.BrowserWindow.ele_General_Settings_Tab","General Setting is available in the application","General setting is not available in the application");
		//Step 7:Validate generalSettingElement
		verifyDefaultElementsOfGeneralSettings("general_settings","Glucose, Unit of Measure##dropdown<<Insulin, Unit of Measure##dropdown<<Hospital System Name##textbox<<Weight##dropdown<<Height##dropdown<<Global Second Nurse Verification##dropdown<<Force BG Login##dropdown<<Force Focus When BG Due##dropdown<<Note Verification##dropdown<<Alarm Snooze##dropdown<<Re-Alarm##dropdown<<IV History##checkbox<<SubQ History##checkbox<<Stop Insulin Recommendation if Insulin Rate >##dropdown<<Language##dropdown<<Clock Configuration##dropdown<<SubQ Module##dropdown<<Pediatric Module##dropdown<<Suspend IV Treatment##dropdown<<Suspend SubQ Treatment##dropdown<<Web Service for LIS##textbox<<Create PDF for Discontinued Patient##dropdown<<Edit Patient Information##dropdown<<Helpdesk Extension Number##textbox<<Glytec Support Number##textbox<<Kiosk Display Option##dropdown");
		//"Glucose, Unit of Measure##dropdown<<Insulin, Unit of Measure##dropdown<<Hospital System Name##textbox<<Weight##dropdown<<Height##dropdown<<Global Second Nurse Verification##dropdown<<Force BG Login##dropdown<<Force Focus When BG Due##dropdown<<Note Verification##dropdown<<Alarm Snooze##dropdown<<Re-Alarm##dropdown<<IV History##checkbox<<SubQ History##checkbox<<Stop Insulin Recommendation if Insulin Rate >##dropdown<<Language##dropdown<<Clock Configuration##dropdown<<SubQ Module##dropdown<<Pediatric Module##dropdown<<Suspend IV Treatment##dropdown<<Suspend SubQ Treatment##dropdown<<Web Service for LIS##textbox<<Create PDF for Discontinued Patient##dropdown<<Edit Patient Information##dropdown<<Helpdesk Extension Number##textbox<<Glytec Support Number##textbox<<Kiosk Display Option";
	}
	////////////////////////////////////////////////////////////////////////////////
	//Function Name  :tc002_AccessIVProtocolSettings
	//Purpose    	 :AccessIVProtocolSettings
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Anbananthan D
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static void tc002_AccessIVProtocolSettings(){
		//Step 1:Open an Internet browser
		//Step 2:type the system test link http://v2240.d04.glytecenterprise.com and pressEnter
		//Step 3:Type in the following information [user: demo1, password: Testtony1] and click the Login button
		Login(retrieve("Username"),retrieve("Password"));
		//Step 4:Click the IV Protocol Settings Link
		verifyTabs("GoogleApplication.BrowserWindow.lnk_Admin","GoogleApplication.BrowserWindow.ele_addUser_Title","Add User is available in the application","Add User is not available in the application");
		//Step 5:Validate IV_Protocol_Settings
		verifyTabs("GoogleApplication.BrowserWindow.lnk_IV_Protocol_Settings","GoogleApplication.BrowserWindow.ele_IV_Protocol_Setting","IV Protocol Settinng is  available in the application","IV Protocol Settinng is not available in the application");
		//Step 6:Select â€œHeart Hospitalâ€� for the facility
		verifyDefaultElementsOfIVSettings("protocol_settings","Facility##dropdown<<Hospital Unit##dropdown");
		//Step 7:Validategeneral Setting Element
		verifyDefaultElementsOfIVSettings("Formsettings","Initial Multiplier##dropdown<<Target Glucose Range##dropdown<<Time Interval##dropdown<<Time Interval Once Stable##dropdown<<IV Fluid Management##dropdown<<Second Nurse Verification##dropdown<<Transition to SubQ Therapy##dropdown<<Insulin Concentration##dropdown<<Hypoglycemia Threshold##dropdown<<IV Meal Bolus##dropdown<<Meal Plan##dropdown<<Display Warning if Insulin Rate >##dropdown<<Stop Insulin Recommendation if Insulin Rate >##dropdown<<Hypoglycemia Message Option##dropdown");
		//Facility##dropdown<<Hospital Unit##dropdown<<Initial Multiplier##dropdown<<Target Glucose Range##dropdown<<Time Interval##dropdown<<Time Interval Once Stable##dropdown<<IV Fluid Management##dropdown<<Second Nurse Verification##dropdown<<Transition to SubQ Therapy##dropdown<<Insulin Concentration##dropdown<<Hypoglycemia Threshold##dropdown<<IV Meal Bolus##dropdown<<Meal Plan##dropdown<<Display Warning if Insulin Rate >##dropdown<<Stop Insulin Recommendation if Insulin Rate >##dropdown<<Hypoglycemia Message Option##dropdown
	}
	////////////////////////////////////////////////////////////////////////////////
	//Function Name  :tc003_AccessSubQProtocolSettings
	//Purpose    	 :AccessSubQProtocolSettings
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Anbananthan D
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static void tc003_AccessSubQProtocolSettings(){
		//Step 1:Open an Internet browser
		//Step 2:type the system test link http://v2240.d04.glytecenterprise.com and pressEnter
		//Step 3:Type in the following information [user: demo1, password: Testtony1] and click the Login button
		Login(retrieve("Username"),retrieve("Password"));
		//Step 4:Click the Admin tab and validate addUser
		verifyTabs("GoogleApplication.BrowserWindow.lnk_Admin","GoogleApplication.BrowserWindow.ele_addUser_Title","Add User is available in the application","Add User is not available in the application");
		//Step 5:Click the SubQ Protocol Settings Link
		verifyTabs("GoogleApplication.BrowserWindow.lnk_SubQ_Protocol_Setting","GoogleApplication.BrowserWindow.ele_IV_Protocol_Setting","SubQ Protocol Settinng is  available in the application","SubQ Protocol Settinng is not available in the application");
		//verifyDefaultElements("subQ_settings","Basal Insulin Types##dropdown<<Hospital Unit*##dropdown");
		//verifyDefaultElements("subQ_settings","Initial Multiplier##dropdown<<Target Glucose Range##dropdown<<Time Interval##dropdown<<Time Interval Once Stable##dropdown<<IV Fluid Management##dropdown<<Second Nurse Verification##dropdown<<Transition to SubQ Therapy##dropdown<<Insulin Concentration##dropdown<<Hypoglycemia Threshold##dropdown<<IV Meal Bolus##dropdown<<Meal Plan##dropdown<<Display Warning if Insulin Rate >##dropdown<<Stop Insulin Recommendation if Insulin Rate >##dropdown<<Hypoglycemia Message Option##dropdown");
		//Step 6:To validate SubQ Settings
		verifyDefaultElementsOfSubQSettings("subQ_settings","Basal Insulin Types##dropdown<<Basal Percentage of Total Daily Dose##dropdown<<Daily Basal Distribution##dropdown<<Bolus Type##dropdown<<Calculate Initial Dose Based On##dropdown<<Orderset Type##dropdown<<Diabetes##dropdown<<Target Range##dropdown<<Diabetes Type##dropdown<<Hypoglycemia Threshold##dropdown<<CFR##textbox<<Breakfast End Time##dropdown<<Lunch End Time##dropdown<<Dinner End Time##dropdown<<Bedtime End Time##dropdown<<Midsleep End Time##dropdown<<Midsleep Check##dropdown<<Contact Physicians to Adjust Distribution Time if Basal is Late >##textbox<<Force Basal Login##dropdown<<TDD Multiplier##dropdown<<TDD Multiplier Pediatric##dropdown<<Edit SubQ Insulin Dose Verification##dropdown<<Hypoglycemia Message Option##dropdown<<Adjustment Factor##table<<Midsleep Correction Bolus Threshold##textbox<<Correction Limit##dropdown");
		//Facility##dropdown<<Hospital Unit##dropdown<<Initial Multiplier##dropdown<<Target Glucose Range##dropdown<<Time Interval##dropdown<<Time Interval Once Stable##dropdown<<IV Fluid Management##dropdown<<Second Nurse Verification##dropdown<<Transition to SubQ Therapy##dropdown<<Insulin Concentration##dropdown<<Hypoglycemia Threshold##dropdown<<IV Meal Bolus##dropdown<<Meal Plan##dropdown<<Display Warning if Insulin Rate >##dropdown<<Stop Insulin Recommendation if Insulin Rate >##dropdown<<Hypoglycemia Message Option##dropdown
	}
	////////////////////////////////////////////////////////////////////////////////
	//Function Name  :tc004_sensitivityFactorCheckingInsulinrate
	//Purpose    	 :sensitivityFactorCheckingInsulinrate
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Anbananthan D
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static void tc004_sensitivityFactorCheckingInsulinrate(){
		//Step 1:Open an Internet browser
		//Step 2:type the system test link http://v2240.d04.glytecenterprise.com and pressEnter
		//Step 3:Type in the following information [user: demo1, password: Testtony1] and click the Login button
		Login(retrieve("Username"),retrieve("Password"));
		//Step 4:Create a new Patient
		creatingPatient();
		//Step 5:Filling IV Patients BG Details
		fillingIvPatientBGdetails();
		//Step 6:Entering IVPatient BG value repeatedly
		enteringIVBGvalueagain4();
		multiplierCheck();
		clickOn("GoogleApplication.BrowserWindow.lnk_Current_Patients");


	}
	////////////////////////////////////////////////////////////////////////////////
	//Function Name  :tc005_sensitivityFactorCheckingFluidrate
	//Purpose    	 :sensitivityFactorCheckingFluidrate
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Anbananthan D
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static void tc005_sensitivityFactorCheckingFluidrate(){
		//Step 1:Open an Internet browser
		//Step 2:type the system test link http://v2240.d04.glytecenterprise.com and pressEnter
		//Step 3:Type in the following information [user: demo1, password: Testtony1] and click the Login button
		Login(retrieve("Username"),retrieve("Password"));
		getNextBGDueForPatientIV();
		enteringIVBGvalueagain5();
		clickOn("GoogleApplication.BrowserWindow.lnk_Current_Patients");


	}
	////////////////////////////////////////////////////////////////////////////////
	//Function Name  :tc006_hypoTreatment
	//Purpose    	 :hypoTreatment
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Anbananthan D
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static void tc006_hypoTreatment(){
		//Step 1:Open an Internet browser
		//Step 2:type the system test link http://v2240.d04.glytecenterprise.com and pressEnter
		//Step 3:Type in the following information [user: demo1, password: Testtony1] and click the Login button
		Login(retrieve("Username"),retrieve("Password"));

		getNextBGDueForPatientIV();
		enteringIVBGvalueagain6();



		creatingPatientSubQ();

		fillingSubqPatientBGdetails();

		enterSubQBGagain6();
		clickOn("GoogleApplication.BrowserWindow.lnk_Current_Patients");



	}
	////////////////////////////////////////////////////////////////////////////////
	//Function Name  :tc007_insulinWarnings
	//Purpose    	 :insulinWarnings
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Anbananthan D
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static void tc007_insulinWarnings(){
		//Step 1:Open an Internet browser
		//Step 2:type the system test link http://v2240.d04.glytecenterprise.com and pressEnter
		//Step 3:Type in the following information [user: demo1, password: Testtony1] and click the Login button
		Login(retrieve("Username"),retrieve("Password"));

		getNextBGDueForPatientIV();
		enteringIVBGvalueagain7();
		discontinueIVPatient();




	}

	////////////////////////////////////////////////////////////////////////////////
	//Function Name  :tc008_hyperosmolarityWarning
	//Purpose    	 :hyperosmolarityWarning
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Anbananthan D
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static void tc008_hyperosmolarityWarning(){
		//Step 1:Open an Internet browser
		//Step 2:type the system test link http://v2240.d04.glytecenterprise.com and pressEnter
		//Step 3:Type in the following information [user: demo1, password: Testtony1] and click the Login button
		Login(retrieve("Username"),retrieve("Password"));
		//Step 4:Create a new Patient
		creatingPatient();
		//Step 5:Filling IV Patients BG Details
		fillingIvPatientBGdetails();
		//Step 6:Entering IVPatient BG value repeatedly
		enteringIVBGvalueagain8();
		clickOn("GoogleApplication.BrowserWindow.lnk_Current_Patients");



	}
	////////////////////////////////////////////////////////////////////////////////
	//Function Name  :tc009_DKAWarning
	//Purpose    	 :DKAWarning
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Anbananthan D
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static void tc009_DKAWarning(){
		//Step 1:Open an Internet browser
		//Step 2:type the system test link http://v2240.d04.glytecenterprise.com and pressEnter
		//Step 3:Type in the following information [user: demo1, password: Testtony1] and click the Login button
		Login(retrieve("Username"),retrieve("Password"));

		getNextBGDueForPatientIV();
		enteringIVBGvalueagain9();




	}

	////////////////////////////////////////////////////////////////////////////////
	//Function Name  :tc010_DiscontinuePatient
	//Purpose    	 :DiscontinuePatient
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Anbananthan D
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static void tc010_DiscontinuePatient(){
		//Step 1:Open an Internet browser
		//Step 2:type the system test link http://v2240.d04.glytecenterprise.com and pressEnter
		//Step 3:Type in the following information [user: demo1, password: Testtony1] and click the Login button
		Login(retrieve("Username"),retrieve("Password"));


		discontinueIVPatient10();

	}

	////////////////////////////////////////////////////////////////////////////////
	//Function Name  :tc011_CustomizeSensitivityFactor
	//Purpose    	 :CustomizeSensitivityFactor
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Anbananthan D
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static void tc011_CustomizeSensitivityFactor(){

		//Step 1:Open an Internet browser
		//Step 2:type the system test link http://v2240.d04.glytecenterprise.com and pressEnter
		//Step 3:Type in the following information [user: demo1, password: Testtony1] and click the Login button
		Login(retrieve("Username"),retrieve("Password"));
		//Step 4:Create a new Patient
		creatingPatient();
		//Step 5:Filling IV Patients BG Details
		customizeSensitivityFactor();
		//enterIVBGvaluesWarning(retrieve("BG value1"));

	} 


	////////////////////////////////////////////////////////////////////////////////
	//Function Name  :tc012_ConfirmEnterBGCancel
	//Purpose    	 :ConfirmEnterBGCancel
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Anbananthan D
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static void tc012_ConfirmEnterBGCancel(){

		//Step 1:Open an Internet browser
		//Step 2:type the system test link http://v2240.d04.glytecenterprise.com and pressEnter
		//Step 3:Type in the following information [user: demo1, password: Testtony1] and click the Login button
		Login(retrieve("Username"),retrieve("Password"));

		getNextBGDueForPatientIV();
		verifyCancelButton();
	}

	///////////////////////////////////////////////////////////////////////////////
	//Function Name  :tc013_CancelConfirmBG
	//Purpose    	 :CancelConfirmBG
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Anbananthan D
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static void tc013_CancelConfirmBG(){

		//Step 1:Open an Internet browser
		//Step 2:type the system test link http://v2240.d04.glytecenterprise.com and pressEnter
		//Step 3:Type in the following information [user: demo1, password: Testtony1] and click the Login button
		Login(retrieve("Username"),retrieve("Password"));
		//Step 4:Create a new Patient
		/*creatingPatient();*/
		//Step 5:Filling IV Patients BG Details
		/*fillingIvPatientBGdetails();*/
		clickingPatientName();
		enterIVBGvaluesWarning(retrieve("BG Value1"));

		verifyCancelButton();

	}

	///////////////////////////////////////////////////////////////////////////////
	//Function Name  :tc014_VerifyAddingNewFacilityAndUnitToSystem
	//Purpose    	 :VerifyAddingNewFacilityAndUnitToSystem
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Anbananthan D
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static void tc014_VerifyAddingNewFacilityAndUnitToSystem(){
		//Step 1:Open an Internet browser
		//Step 2:type the system test link http://v2240.d04.glytecenterprise.com and pressEnter
		//Step 3:Type in the following information [user: demo1, password: Testtony1] and click the Login button
		Login(retrieve("Username"),retrieve("Password"));
		NewFacility();
		NewHospitalUnit();
		clickOn("GoogleApplication.BrowserWindow.lnk_Current_Patients");
	}

	///////////////////////////////////////////////////////////////////////////////
	//Function Name  :tc015_TransferHospitalUnit
	//Purpose    	 :TransferHospitalUnit
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Anbananthan D
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static void tc015_TransferHospitalUnit(){
		//Step 1:Open an Internet browser
		//Step 2:type the system test link http://v2240.d04.glytecenterprise.com and pressEnter
		//Step 3:Type in the following information [user: demo1, password: Testtony1] and click the Login button
		Login(retrieve("Username"),retrieve("Password"));


		String hospitalUnitValue=Room();
		unitCheckHER(hospitalUnitValue);
		TransferHospitalUnitsHICU();
		hospitalUnitValue=Room();
		unitCheckHICU(hospitalUnitValue);
		TransferHospitalUnitsHER();
		hospitalUnitValue=Room();
		unitCheckHER(hospitalUnitValue);

	}

	///////////////////////////////////////////////////////////////////////////////
	//Function Name  :tc018_SubQDosageCalcStandard
	//Purpose    	 :SubQDosageCalcStandard
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Anbananthan D
	//Created on     :16/06/2014     
	//Remarks        :

	public static void tc018_SubQDosageCalcStandard()
	{	
		navigateTo("http://gs-devtest-p03/");

		try
		{
			strDate = getDate();
			strTime = getTime();

			Runnable r = new Runnable() {
				public void run() {
					boolean flag = true;    
					int i = 0;
					while(flag){
						i++;
						j=i;

						try
						{
							Thread.sleep(1000);
						}
						catch (Exception e) {

							System.out.println(e.toString());
						}
					}
				}
			};


			t = new Thread(r);
			t.start();
			cleanUpDatabase();
			Login(retrieve("Username"), retrieve("Password"));
			systemAndAppTime("06:30", "08-5-2014");
			toLogIn();
			creatingPatientSubQ();
			fillingSubQPatientFor18();
			SubQDosageCalcStandard();

		}
		finally
		{
			t.stop();
			int secsToAdd = j;
			int milli=secsToAdd*1000;



			DateFormat formatter = new SimpleDateFormat("HH:mm:ss");

			Date start;

			try {
				start = formatter.parse(strTime);



				Long startMilli = start.getTime();

				Long resultMilli = startMilli + milli;

				Date result = new Date(resultMilli);
				String newtime = formatter.format(result);

				systemAndAppTime(newtime,strDate);
			} catch (ParseException e) {

				e.printStackTrace();
			}

		}





	}
	///////////////////////////////////////////////////////////////////////////////
	//Function Name  :tc019_SubQDosageCalcNonStandard
	//Purpose    	 :SubQDosageCalcNonStandard
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Anbananthan D
	//Created on     :16/06/2014     
	//Remarks        :
	public static void tc019_SubQDosageCalcNonStandard()
	{
		navigateTo("http://gs-devtest-p03/");
		try
		{

			strDate = getDate();
			strTime = getTime();

			Runnable r = new Runnable() {
				public void run() {
					boolean flag = true;    
					int i = 0;
					while(flag){
						i++;
						j=i;

						try
						{
							Thread.sleep(1000);
						}
						catch (Exception e) {

							System.out.println(e.toString());
						}
					}
				}
			};

			t = new Thread(r);
			t.start();
			cleanUpDatabase();

			Login(retrieve("Username"), retrieve("Password"));
			systemAndAppTime("06:30","08-5-2014");
			toLogIn();
			creatingPatientSubQ();
			fillingSubQPatientFor19();
			subQDosageCalcNon();
		}
		finally
		{
			t.stop();


			int secsToAdd = j;
			int milli=secsToAdd*1000;



			DateFormat formatter = new SimpleDateFormat("HH:mm:ss");

			Date start;
			try {
				start = formatter.parse(strTime);


				Long startMilli = start.getTime();

				Long resultMilli = startMilli + milli;

				Date result = new Date(resultMilli);
				String newtime = formatter.format(result);

				systemAndAppTime(newtime,strDate);
			} catch (ParseException e) {

				e.printStackTrace();
			}
		}
	}
	///////////////////////////////////////////////////////////////////////////////
	//Function Name  :tc020_pediatricDosageCalculation
	//Purpose    	 :pediatricDosageCalculation
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Anbananthan D
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static void tc020_pediatricDosageCalculation(){

		//Step 1:Open an Internet browser
		//Step 2:type the system test link http://v2240.d04.glytecenterprise.com and pressEnter
		//Step 3:Type in the following information [user: demo1, password: Testtony1] and click the Login button
		Login(retrieve("Username"),retrieve("Password"));
		creatingPatientPediatric();
		pediatricDosageCalculation();

	}

	///////////////////////////////////////////////////////////////////////////////
	//Function Name  :tc021_IV_Meal_Bolus
	//Purpose    	 :IV_Meal_Bolus
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Anbananthan D
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static void tc021_IV_Meal_Bolus(){

		//Step 1:Open an Internet browser
		//Step 2:type the system test link http://v2240.d04.glytecenterprise.com and pressEnter
		//Step 3:Type in the following information [user: demo1, password: Testtony1] and click the Login button
		navigateTo("http://gs-devtest-p03/");
		try
		{

			strDate = getDate();
			strTime = getTime();

			Runnable r = new Runnable() {
				public void run() {
					boolean flag = true;    
					int i = 0;
					while(flag){
						i++;
						j=i;

						try
						{
							Thread.sleep(1000);
						}
						catch (Exception e) {

							System.out.println(e.toString());
						}
					}
				}
			};

			t = new Thread(r);
			t.start(); 
			cleanUpDatabase();
			Login(retrieve("Username"),retrieve("Password"));
			//Step 4:Create a new Patient
			creatingPatient();
			//Step 5:Filling IV Patients BG Details
			fillingIvPatientBGdetails();
			checkMealBolusIV();
		}
		finally
		{
			t.stop();


			int secsToAdd = j;
			int milli=secsToAdd*1000;



			DateFormat formatter = new SimpleDateFormat("HH:mm:ss");

			Date start;
			try {
				start = formatter.parse(strTime);


				Long startMilli = start.getTime();

				Long resultMilli = startMilli + milli;

				Date result = new Date(resultMilli);
				String newtime = formatter.format(result);

				systemAndAppTime(newtime,strDate);
			} catch (ParseException e) {

				e.printStackTrace();
			}
		}

	}
	///////////////////////////////////////////////////////////////////////////////
	//Function Name  :tc022_SubQ_Dosage_Calculation_NPO
	//Purpose    	 :SubQ_Dosage_Calculation_NPO
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Anbananthan D
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static void tc022_SubQ_Dosage_Calculation_NPO(){

		navigateTo("http://gs-devtest-p03/");
		try
		{
			strDate = getDate();
			strTime = getTime();

			Runnable r = new Runnable() {
				public void run() {
					boolean flag = true;    
					int i = 0;
					while(flag){
						i++;
						j=i;

						try
						{
							Thread.sleep(1000);
						}
						catch (Exception e) {

							System.out.println(e.toString());
						}
					}
				}
			};

			t = new Thread(r);
			t.start(); 
			cleanUpDatabase();
			Login(retrieve("Username"),retrieve("Password"));
			systemAndAppTime("01:11","08-5-2014");
			toLogIn();
			creatingPatientSubQ();
			fillingSubQPatient();
			subQDosageCalculationNPO();
			discontinueSubQPatient();
		}
		finally
		{
			t.stop();


			int secsToAdd = j;
			int milli=secsToAdd*1000;



			DateFormat formatter = new SimpleDateFormat("HH:mm:ss");

			Date start;
			try {
				start = formatter.parse(strTime);


				Long startMilli = start.getTime();

				Long resultMilli = startMilli + milli;

				Date result = new Date(resultMilli);
				String newtime = formatter.format(result);

				systemAndAppTime(newtime,strDate);
			} catch (ParseException e) {

				e.printStackTrace();
			}
		}

	}

	///////////////////////////////////////////////////////////////////////////////
	//Function Name  :tc023_SubQ_Dosage_Calculation_NPO_Weight_Base
	//Purpose    	 :SubQ_Dosage_Calculation_NPO_Weight_Base
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Anbananthan D
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////

	public static void tc023_SubQ_Dosage_Calculation_NPO_Weight_Base() 
	{

		navigateTo("http://gs-devtest-p03/");
		try
		{

			strDate = getDate();
			strTime = getTime();

			Runnable r = new Runnable() {
				public void run() {
					boolean flag = true;    
					int i = 0;
					while(flag){
						i++;
						j=i;

						try
						{
							Thread.sleep(1000);
						}
						catch (Exception e) {

							System.out.println(e.toString());
						}
					}
				}
			};

			t = new Thread(r);
			t.start(); 
			cleanUpDatabase();
			Login(retrieve("Username"),retrieve("Password"));
			systemAndAppTime("01:11","08-5-2014");
			toLogIn();
			creatingPatientSubQ();
			fillingSubQPatientWeightDose();
			subQDosageCalculationNPOWeightDose();
		}
		finally
		{
			t.stop();


			int secsToAdd = j;
			int milli=secsToAdd*1000;



			DateFormat formatter = new SimpleDateFormat("HH:mm:ss");

			Date start;
			try {
				start = formatter.parse(strTime);


				Long startMilli = start.getTime();

				Long resultMilli = startMilli + milli;

				Date result = new Date(resultMilli);
				String newtime = formatter.format(result);

				systemAndAppTime(newtime,strDate);
			} catch (ParseException e) {

				e.printStackTrace();
			}

		}

	}

	///////////////////////////////////////////////////////////////////////////////
	//Function Name  :tc024_IV_Countdown_Timer
	//Purpose    	 :IV_Countdown_Timer
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Anbananthan D
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static void tc024_IV_Countdown_Timer() {

		navigateTo("http://gs-devtest-p03/");
		try
		{

			strDate = getDate();
			strTime = getTime();

			Runnable r = new Runnable() {
				public void run() {
					boolean flag = true;    
					int i = 0;
					while(flag){
						i++;
						j=i;

						try
						{
							Thread.sleep(1000);
						}
						catch (Exception e) {

							System.out.println(e.toString());
						}
					}
				}
			};

			t = new Thread(r);
			t.start(); 
			cleanUpDatabase();
			Login(retrieve("Username"),retrieve("Password"));
			systemAndAppTime("12:00","08-5-2014");
			toLogIn();
			creatingPatient();
			fillingIvPatientBGdetailsnew();
			IVCountdownTimer();
		}
		finally
		{
			t.stop();


			int secsToAdd = j;
			int milli=secsToAdd*1000;



			DateFormat formatter = new SimpleDateFormat("HH:mm:ss");

			Date start;
			try {
				start = formatter.parse(strTime);


				Long startMilli = start.getTime();

				Long resultMilli = startMilli + milli;

				Date result = new Date(resultMilli);
				String newtime = formatter.format(result);

				systemAndAppTime(newtime,strDate);
			} catch (ParseException e) {

				e.printStackTrace();
			}
		}

	}


	///////////////////////////////////////////////////////////////////////////////
	//Function Name  :tc025_IV_Transition_To_SubQ_Basal_Dose_Calculation
	//Purpose    	 :IV_Transition_To_SubQ_Basal_Dose_Calculation
	//Parameters  	 :
	//Return Value   :Void
	//Created by     :Anbananthan D
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static void tc025_IV_Transition_To_SubQ_Basal_Dose_Calculation() {

		navigateTo("http://gs-devtest-p03/");
		try
		{
			strDate = getDate();
			strTime = getTime();

			Runnable r = new Runnable() {
				public void run() {
					boolean flag = true;    
					int i = 0;
					while(flag){
						i++;
						j=i;

						try
						{
							Thread.sleep(1000);
						}
						catch (Exception e) {

							System.out.println(e.toString());
						}
					}
				}
			};

			t = new Thread(r);
			t.start(); 

			cleanUpDatabase();
			Login(retrieve("Username"),retrieve("Password"));
			systemAndAppTime("08:00","08-5-2014");                              
			toLogIn();
			creatingPatient(); 
			fillingIvPatientBGdetailsnew();
			transitionToSubQBasalDoseCalculation();
			newPatientIV();
		}
		finally
		{
			t.stop();


			int secsToAdd = j;
			int milli=secsToAdd*1000;



			DateFormat formatter = new SimpleDateFormat("HH:mm:ss");

			Date start;
			try {
				start = formatter.parse(strTime);


				Long startMilli = start.getTime();

				Long resultMilli = startMilli + milli;

				Date result = new Date(resultMilli);
				String newtime = formatter.format(result);

				systemAndAppTime(newtime,strDate);

			} catch (ParseException e) {

				e.printStackTrace();
			}
		}

	}
	public static void navigateToLifeInsurance() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		desktop.<DomTextField> find(
				"IllinoisLifeInsurePage.BrowserWindow.lnkSelectOne").click();
		desktop.<DomTextField> find(
				"IllinoisLifeInsurePage.BrowserWindow.lnkLifeInsurance").click();
		desktop.<DomTextField> find(
				"IllinoisLifeInsurePage.BrowserWindow.rbMale").click();
		//clickOn("IllinoisLifeInsurePage.BrowserWindow.lnkSelectOne",true);
		//clickOn("IllinoisLifeInsure`Page.BrowserWindow.lnkLifeInsurance",true);
		//clickOn("IllinoisLifeInsurePage.BrowserWindow.rbMale",true);
	}


	public static void loginToApp() {
		try{
			
			String userName,password;
			userName = Common.retrieve("Username");
			password = Common.retrieve("Password");
			desktop.<DomTextField> find(Xpath_userName).setText(userName);
			desktop.<DomTextField> find(Xpath_password).setText(password);
			testStepPassed("Entered Username and password");
			Thread.sleep(3000);
			desktop.<DomButton> find(Xpath_signIn).click();
			testStepPassed("Clicked on Sign In Button");
			if(desktop.<DomElement> find(xpath_flightfinder).exists()){
				System.out.println("12345689");
				testStepPassed("Flight finder Successfully");
				takeScreenshot("LoggedIn");
			}else{
				testStepFailed("Unable to login to application");
			}

		}catch(Exception ex){
			testStepFailed("Unable to login to application");
			System.out.println(ex.getMessage());
		}

	}


	public static void bookFlights() {
		desktop.<DomButton> find(
				"newtours_demoaut_com.BrowserWindow.findFlights").click();

		testStepPassed("Clicked on Find Flights");

		desktop.<DomButton> find(
				"newtours_demoaut_com.BrowserWindow.reserveFlights").click();
		
		testStepPassed("Clicked on reserve Flights");
		
		desktop.<DomTextField> find(
				"newtours_demoaut_com.BrowserWindow.passFirst0").setText(Common.retrieve("Firstname"));
		desktop.<DomTextField> find(
				"newtours_demoaut_com.BrowserWindow.passLast0").setText(Common.retrieve("Lastname"));
		desktop.<DomTextField> find(
				"newtours_demoaut_com.BrowserWindow.creditnumber").setText(Common.retrieve("CreditNumber"));
		
		testStepPassed("Entered All the details");
		
		desktop.<DomButton> find(
				"newtours_demoaut_com.BrowserWindow.buyFlights").click();
		
		testStepPassed("Clicked on Buy Flights");
		
		if(desktop.<DomElement>find(
				"newtours_demoaut_com.BrowserWindow.TicketBooked").exists()){			
			testStepPassed("Ticket has been booked successfully");
			takeScreenshot("BookedTicketSuccessfully");			
		}else{
			testStepFailed("Unable to book ticket");
		}
		
	}
	public static void navigateToHomePage() {
		if(desktop.<DomLink> find(
				"newtours_demoaut_com.BrowserWindow.lnkHome").exists()){
			desktop.<DomLink> find(
					"newtours_demoaut_com.BrowserWindow.lnkHome").click();
			testStepPassed("Clicked on Home Link Successfully");
		}else{
			testStepFailed("Unable to click on Home Link");
		}
		
	}
	
	
}

