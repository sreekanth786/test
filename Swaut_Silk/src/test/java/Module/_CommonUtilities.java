package Module;


import com.borland.silktest.jtf.TestObject;
import com.borland.silktest.jtf.common.ObjectNotEnabledException;
import com.borland.silktest.jtf.common.ObjectNotFoundException;

import silk4J.ApplicationKeywords;

//import org.openqa.selenium.By;






public class _CommonUtilities extends ApplicationKeywords
{
////////////////////////////////////////////////////////////////////////////////
	//Function Name  :getNextBGDueForPatient
	//Purpose    	 :Getting next BG due for Patient
	//Parameters  	 :String firstName,String lastName
	//Return Value   :Void
	//Created by     :Ilandevan V
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static boolean flag = true;
	
	public static String getNextBGDueForPatient(String firstName,String lastName)
	{
		System.out.println(firstName);
		System.out.println(lastName);
		String nextBGDue = null;
		
		
		
		try
		{
			TestObject testObject;					
			testObject = browserApplication.find("//li[@textContents='"+lastName+", "+firstName+"']");
			testObject = testObject.getParent();
			testObject = testObject.getParent();
			testObject = testObject.getParent();
			domElement = testObject.find("//div[@id='subq_patient_*']");
//			domElement = testObject.find("//div[@id='patient_*']");
			nextBGDue = domElement.getText();
			
			if(flag)
				{
				domElement.click();
				flag = false;
				}
			System.out.println("BG: "+nextBGDue);
		}
		catch (ObjectNotFoundException ex)
		{
			System.out.println("The specified object is not found within the time limit specified");
			System.out.println(ex.toString());
		}
		catch (ObjectNotEnabledException ex)
		{
			System.out.println(ex.toString());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}			
		return nextBGDue;
	}
////////////////////////////////////////////////////////////////////////////////
	//Function Name  :getNextBGDueForPatientIV
	//Purpose    	 :Getting next BG due for IV patient
	//Parameters  	 :String firstName,String lastName
	//Return Value   :Void
	//Created by     :Ilandevan V
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static String getNextBGDueForPatientIV(String firstName,String lastName)
	{
		
		String nextBGDue = null;
		
		
		
		try
		{
			TestObject testObject;					
			testObject = browserApplication.find("//li[@textContents='"+lastName+", "+firstName+"']");
			testObject = testObject.getParent();
			testObject = testObject.getParent();
			testObject = testObject.getParent();
			
			domElement = testObject.find("//div[@class='iv_popup msg iv msg_*']");
			nextBGDue = domElement.getText();
			
			
			System.out.println("BG: "+nextBGDue);
		}
		catch (ObjectNotFoundException ex)
		{
			System.out.println("The specified object is not found within the time limit specified");
			System.out.println(ex.toString());
		}
		catch (ObjectNotEnabledException ex)
		{
			System.out.println(ex.toString());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}			
		return nextBGDue;
	}
	
////////////////////////////////////////////////////////////////////////////////
	//Function Name  :getNextBGDueForPatientSubq
	//Purpose    	 :getting next BG due for patient SubQ
	//Parameters  	 :String firstName,String lastName
	//Return Value   :Void
	//Created by     :Ilandevan V
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static String getNextBGDueForPatientSubq(String firstName,String lastName)
	{
		System.out.println(firstName);
		System.out.println(lastName);
		String nextBGDue = null;
		
		
		
		try
		{
			TestObject testObject;					
			testObject = browserApplication.find("//li[@title='"+lastName+", "+firstName+"']");
			testObject = testObject.getParent();
			testObject = testObject.getParent();
			testObject = testObject.getParent();
			domElement = testObject.find("//div[@id='subq_patient_*']");
//			domElement = testObject.find("//div[@id='patient_*']");
			nextBGDue = domElement.getText();
			
			
			System.out.println("BG: "+nextBGDue);
		}
		catch (ObjectNotFoundException ex)
		{
			System.out.println("The specified object is not found within the time limit specified");
			System.out.println(ex.toString());
		}
		catch (ObjectNotEnabledException ex)
		{
			System.out.println(ex.toString());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}			
		return nextBGDue;
	}
////////////////////////////////////////////////////////////////////////////////
	//Function Name  :clickOn_
	//Purpose    	 :perform the action Click
	//Parameters  	 :String obj
	//Return Value   :Void
	//Created by     :Ilandevan V
	//Created on     :16/06/2014     
	//Remarks        :
	/////////////////////////////////////////////////////////////////////////////////
	public static void clickOn_(String obj)
	{
		try
		{
			String[] webElement = null;
			if (obj.contains("."))
			{
				webElement = obj.split("\\.",5);
			}
			if (webElement.length == 3)
			{
				browserApplication = desktop.find(webElement[0]);
				browserWindow = browserApplication.find(webElement[1]);
				domElement = browserWindow.find(webElement[2]);
			}
			else if (webElement.length == 2)
			{
				browserApplication = desktop.find("2240_glytecenterprise_com");
				browserWindow = browserApplication.find(webElement[0]);
				domElement = browserWindow.find(webElement[1]);
			}
			else
			{
				browserApplication = desktop.find("/BrowserApplication");
				browserWindow = browserApplication.find("//BrowserWindow");
				domElement = browserWindow.find(obj);
			}
			domElement.click();
			System.out.println("Successfully Clicked on"+obj);
		}
		catch (ObjectNotFoundException ex)
		{
			System.out.println(ex.toString());
		}
		catch (ObjectNotEnabledException ex)
		{
			System.out.println(ex.toString());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	
	}
}


