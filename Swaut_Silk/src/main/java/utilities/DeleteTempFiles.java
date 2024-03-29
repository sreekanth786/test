package utilities;

import java.io.File;

public class DeleteTempFiles
{
  public DeleteTempFiles() {}
  
  public static void delete() {
    File directory = new File(utilities.GenericKeywords.outputDirectory + "/testng");
    File directory1 = new File(utilities.GenericKeywords.outputDirectory + "/testng" + "\\Suite");
    File directory2 = new File(utilities.GenericKeywords.outputDirectory + "/testng" + "\\old");
    File directory3 = new File(utilities.GenericKeywords.outputDirectory + "/testng" + "\\junitreports");
    File directory4 = new File(utilities.GenericKeywords.outputDirectory + "/testng" + "\\old\\Suite");
    File file1 = new File(utilities.GenericKeywords.outputDirectory + 
      "\\copyfile.xml");
    File file2 = new File(utilities.GenericKeywords.outputDirectory + "\\temp.xml");
    File file3 = new File(utilities.GenericKeywords.outputDirectory + 
      "\\emailable-report.html");
    File file4 = new File(utilities.GenericKeywords.outputDirectory + "\\index.html");
    File file5 = new File(utilities.GenericKeywords.outputDirectory + 
      "\\testng-results.xml");
    File file6 = new File(utilities.GenericKeywords.outputDirectory + 
      "\\testng-failed.xml");
    File file7 = new File(utilities.GenericKeywords.outputDirectory + "\\testng.css");
    File file8 = new File(utilities.GenericKeywords.outputDirectory + 
      "\\PdfReport.html");
    File file9 = new File(utilities.GenericKeywords.outputDirectory + 
      "\\pdfReport.pdf");
    File file10 = new File(utilities.GenericKeywords.outputDirectory + 
      "\\pdfReport1.pdf");
    File file11 = new File(utilities.GenericKeywords.outputDirectory + 
      "\\TestCaseResults.pdf");
    File file12 = new File(utilities.GenericKeywords.outputDirectory + 
      "\\pdffile.xml");
    file1.delete();
    file2.delete();
    file3.delete();
    file4.delete();
    file5.delete();
    file6.delete();
    file7.delete();
    file8.delete();
    file9.delete();
    file10.delete();
    file11.delete();
    file12.delete();
    if (directory.isDirectory())
    {
      String[] files = directory.list();
      for (String temp : files) {
        File fileDelete = new File(directory, temp);
        fileDelete.delete();
      }
      if (directory.list().length == 0) {
        directory.delete();
      }
    }
    


    if (directory1.isDirectory())
    {
      String[] files1 = directory1.list();
      for (String temp1 : files1) {
        File fileDelete1 = new File(directory1, temp1);
        fileDelete1.delete();
      }
      if (directory1.list().length == 0) {
        directory1.delete();
      }
    }
    


    if (directory2.isDirectory())
    {
      String[] files1 = directory2.list();
      for (String temp1 : files1) {
        File fileDelete1 = new File(directory2, temp1);
        fileDelete1.delete();
      }
      if (directory2.list().length == 0) {
        directory2.delete();
      }
    }
    


    if (directory3.isDirectory())
    {
      String[] files1 = directory3.list();
      for (String temp1 : files1) {
        File fileDelete1 = new File(directory3, temp1);
        fileDelete1.delete();
      }
      if (directory3.list().length == 0) {
        directory3.delete();
      }
    }
    


    if (directory4.isDirectory())
    {
      String[] files1 = directory4.list();
      for (String temp1 : files1) {
        File fileDelete1 = new File(directory4, temp1);
        fileDelete1.delete();
      }
      if (directory4.list().length == 0) {
        directory4.delete();
      }
    }
    


    directory4.delete();
    directory1.delete();
    directory2.delete();
    directory3.delete();
    directory.delete();
  }
}
