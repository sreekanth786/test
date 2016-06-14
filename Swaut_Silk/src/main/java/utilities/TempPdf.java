package utilities;

import utilities.GenericKeywords;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;



public class TempPdf
{
  public static String FILE = GenericKeywords.outputDirectory + 
    "\\TestCaseResults.pdf";
  
  public TempPdf() {}
  
  public static void pdf() { try { Document document = new Document();
      PdfWriter.getInstance(document, 
        new FileOutputStream(FILE));
      document.open();
      addContent(document);
      document.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
  
  public static void addContent(Document document)
    throws DocumentException, SAXException, IOException, ParserConfigurationException
  {
    Anchor anchor = new Anchor();
    anchor.setName("");
    String[] str = HtmlReport.str.split(HtmlReport.temp);
    for (int i = 1; i < str.length; i++) {
      Paragraph subPara = new Paragraph("");
      if ((str[i].contains("TestFailure")) && (str[i].contains("Manual"))) {
        String[] str1 = str[i].replaceAll("\\<.*?>", "").split("\n");
        for (int j = 0; j < str1.length; j++) {
          if (!str1[j].contains(GenericKeywords.outputDirectory)) {
            subPara.add(new Paragraph(str1[j].replaceAll("\\<.*?>", 
              "").trim()));
          }
          else {
            String[] str2 = str1[j]
              .split("Screenshot repository :-");
            Image image2 = Image.getInstance(new URL("file:///" + 
              str2[1].trim()));
            image2.scaleAbsolute(150.0F, 150.0F);
            subPara.add(image2);
          }
        }
        

        document.add(subPara);
        document.newPage();
        System.out.println("And condition");
      } else if (str[i].contains("TestFailure")) {
        String[] str1 = str[i].replaceAll("\\<.*?>", "").split("\n");
        for (int j = 0; j < str1.length; j++) {
          if (!str1[j].contains(GenericKeywords.outputDirectory)) {
            subPara.add(new Paragraph(str1[j].replaceAll("\\<.*?>", 
              "").trim()));
          }
          else {
            String[] str2 = str1[j]
              .split("Screenshot repository :-");
            Image image2 = Image.getInstance(new URL("file:///" + 
              str2[1].trim()));
            image2.scaleAbsolute(150.0F, 150.0F);
            subPara.add(image2);
          }
        }
        

        document.add(subPara);
        document.newPage();
      } else if (str[i].contains("Manual")) {
        String[] str1 = str[i].replaceAll("\\<.*?>", "").split("\n");
        
        for (int j = 0; j < str1.length; j++) {
          if (!str1[j].contains(GenericKeywords.outputDirectory)) {
            subPara.add(new Paragraph(str1[j].replaceAll("\\<.*?>", 
              "").trim()));
          }
          else {
            String[] str2 = str1[j]
              .split("Screenshot repository :-");
            Image image2 = Image.getInstance(new URL("file:///" + 
              str2[1].trim()));
            image2.scaleAbsolute(150.0F, 150.0F);
            subPara.add(image2);
          }
        }
        

        document.add(subPara);
        document.newPage();
      } else {
        subPara.add(new Paragraph(str[i].replaceAll("\\<.*?>", "")));
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 5);
        subPara.add(paragraph);
        document.add(subPara);
        document.newPage();
      }
    }
  }
  
  public static void addEmptyLine(Paragraph paragraph, int number) {
    for (int i = 0; i < number; i++) {
      paragraph.add(new Paragraph(" "));
    }
  }
}
