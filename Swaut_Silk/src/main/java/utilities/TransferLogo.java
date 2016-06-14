package utilities;

import utilities.GenericKeywords;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TransferLogo
{
  public TransferLogo() {}
  
  public static void transferLogo()
  {
    try
    {
      new File(GenericKeywords.outputDirectory + "\\Logos\\").mkdir();
      
      File sourcecompanyLogo = new File(Common.getConfigProperty("sun_logo_path"));
      File sourceclientLogo = new File(Common.getConfigProperty("client_logo_path"));
      File designationcompanyLogo = new File(GenericKeywords.outputDirectory + Common.getConfigProperty("sun_logo_path"));
      File designationclientLogo = new File(GenericKeywords.outputDirectory + Common.getConfigProperty("client_logo_path"));
      InputStream in = new java.io.FileInputStream(sourcecompanyLogo);
      OutputStream out = new FileOutputStream(designationcompanyLogo);
      byte[] buf = new byte[124];
      int len;
      while ((len = in.read(buf)) > 0) {
        out.write(buf, 0, len);
      }
      in.close();
      out.close();
      InputStream in1 = new java.io.FileInputStream(sourceclientLogo);
      OutputStream out1 = new FileOutputStream(designationclientLogo);
      byte[] buf1 = new byte[124];
      int len1;
      while ((len1 = in1.read(buf1)) > 0) {
        out1.write(buf1, 0, len1);
      }
      in1.close();
      out1.close();


    }
    catch (FileNotFoundException ex)
    {

      System.out.println(ex.getMessage() + " in  the specified directory.");
      System.exit(0);


    }
    catch (IOException e)
    {

      System.out.println(e.getMessage());
    }
  }
}
