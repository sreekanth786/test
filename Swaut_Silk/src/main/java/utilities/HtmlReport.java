package utilities;

import utilities.GenericKeywords;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;


public class HtmlReport
{
  public static String temp = "<B></B>";
  public static String str;
  public static String name;
  
  public HtmlReport() {}
  
  public static void htmlReport() { try { xmlToxml();
      javaToXml();
      renameNode();
      getNodeText();
      splitNodeText();
      transformXsltToHtmlCoverPage();
      transformXsltToHtmlSummaryPage();

    }
    catch (Exception e)
    {
      System.out.println(e);
    }
  }
  
  public static void xmlToxml()
  {
    try
    {
      File f1 = new File(GenericKeywords.outputDirectory + "/testng" + 
        "\\testng-results.xml");
      File f2 = new File(GenericKeywords.outputDirectory + "/testng" + 
        "\\copyfile.xml");
      
      InputStream in = new FileInputStream(f1);
      OutputStream out = new FileOutputStream(f2);
      byte[] buf = new byte[124];
      int len;
      while ((len = in.read(buf)) > 0) {
        out.write(buf, 0, len);
      }
      in.close();
      out.close();

    }
    catch (FileNotFoundException ex)
    {
      System.out.println(ex.getMessage() + 
        " in  the specified directory.");
      System.exit(0);

    }
    catch (IOException e)
    {

      System.out.println(e.getMessage());
    }
  }
  
  public static void javaToXml()
  {
    try
    {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document document = db.parse(GenericKeywords.outputDirectory + "/testng" + 
        "\\copyfile.xml");
      NodeList nodeList = document.getElementsByTagName("testng-results");
      for (int i = 0; i < nodeList.getLength(); i++)
      {
        if (i == 0)
        {
          Node node = nodeList.item(0);
          Element project = document.createElement("ProjectName");
          project.appendChild(document.createTextNode(
            Common.getConfigProperty("Project_Name")));
          node.appendChild(project);
          Element Date = document.createElement("Date");
          Date.appendChild(document.createTextNode(OutputAndLog.mon + 
            "/" + OutputAndLog.day + "/" + OutputAndLog.yr));
          node.appendChild(Date);
          Element version_Name = document
            .createElement("VersionName");
          version_Name.appendChild(document.createTextNode(
            Common.getConfigProperty("Version_Name")));
          node.appendChild(version_Name);
          Element mailTo = document.createElement("MailTo");
          mailTo.appendChild(document.createTextNode(
            Common.getConfigProperty("ToEmail")));
          node.appendChild(mailTo);
          Element mailCc = document.createElement("MailCc");
          mailCc.appendChild(document.createTextNode(
            Common.getConfigProperty("CcEmail")));
          node.appendChild(mailCc);
        }
      }
      TransformerFactory tff = TransformerFactory.newInstance();
      Transformer transformer = tff.newTransformer();
      DOMSource xmlSource = new DOMSource(document);
      StreamResult outputTarget = new StreamResult(
        GenericKeywords.outputDirectory + "/testng" + "\\copyfile.xml");
      transformer.transform(xmlSource, outputTarget);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
  
  public static void renameNode()
  {
    try {
      File file = new File(GenericKeywords.outputDirectory + "/testng" + 
        "\\copyfile.xml");
      
      DocumentBuilderFactory factory = 
        DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(file);
      TransformerFactory tFactory = TransformerFactory.newInstance();
      Transformer tFormer = tFactory.newTransformer();
      NodeList links = doc.getElementsByTagName("test-method");
      for (int k = 0; k < links.getLength(); k++) {
        Element link = (Element)links.item(k);
        String a = link.getAttribute("name");
        if (a.equalsIgnoreCase("testStart")) {
          doc.renameNode(links.item(k), null, "before-method");
        }
        

        if (a.equalsIgnoreCase("testEnd"))
        {
          doc.renameNode(links.item(k), null, "after-method");
        }
        
        if (a.equalsIgnoreCase("startupScripts"))
        {
          doc.renameNode(links.item(k), null, "startup-scripts");
        }
      }
      


      doc.normalize();
      Source source = new DOMSource(doc);
      Result dest = new StreamResult(GenericKeywords.outputDirectory + "/testng" + 
        "\\copyfile.xml");
      tFormer.transform(source, dest);
      System.out.println();
    }
    catch (Exception e) {
      System.err.println(e);
      System.exit(0);
    }
  }
  
  public static void getNodeText()
  {
    try {
      File f2 = new File(GenericKeywords.outputDirectory + "/testng" + "\\temp.xml");
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = dbf.newDocumentBuilder();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("temp");
      doc.appendChild(rootElement);
      Document document = db.parse(GenericKeywords.outputDirectory + "/testng" + 
        "\\copyfile.xml");
      NodeList line = document.getElementsByTagName("line");
      NodeList links = document.getElementsByTagName("test-method");
      NodeList testCaseDecription = document
        .getElementsByTagName("testng-results");
      Node nodetestCaseDescription = testCaseDecription.item(0);
      Element testCases = doc.createElement("TestCases");
      
      for (int i = 0; i < line.getLength(); i++) {
        Element link = (Element)line.item(i);
        String a = link.getTextContent();
        testCases.appendChild(doc.createCDATASection(a));
        rootElement.appendChild(testCases);
        if (a.contains("<B></B>")) {
          String a1 = link.getTextContent();
          Element testcase = document
            .createElement("testCaseDescription");
          testcase.appendChild(document.createTextNode(a1.replaceAll(
            "\\<.*?>", "")));
          nodetestCaseDescription.appendChild(testcase);
        }
      }
      TransformerFactory tff = TransformerFactory.newInstance();
      Transformer transformer = tff.newTransformer();
      DOMSource xmlSource = new DOMSource(doc);
      DOMSource xmlSource1 = new DOMSource(document);
      StreamResult outputTarget1 = new StreamResult(
        GenericKeywords.outputDirectory + "/testng" + "\\copyfile.xml");
      StreamResult outputTarget = new StreamResult(
        GenericKeywords.outputDirectory + "/testng" + "\\temp.xml");
      transformer.transform(xmlSource, outputTarget);
      transformer.transform(xmlSource1, outputTarget1);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
  
  public static void splitNodeText()
  {
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      DocumentBuilder docBuilder = dbf.newDocumentBuilder();
      Document document = db.parse(GenericKeywords.outputDirectory + "/testng" + 
        "\\temp.xml");
      Document testngDocument = db.parse(GenericKeywords.outputDirectory + "/testng" + 
        "\\copyfile.xml");
      NodeList links = testngDocument.getElementsByTagName("test-method");
      NodeList line = document.getElementsByTagName("TestCases");
      NodeList testCaseLinks = testngDocument
        .getElementsByTagName("testng-results");
      Element testcases = (Element)line.item(0);
      String testCases = testcases.getTextContent();
      str = testcases.getTextContent();
      testCases.split(temp);
      String[] str = testCases.split(temp);
      Node node = testCaseLinks.item(0);
      new File(GenericKeywords.outputDirectory + "\\Resultfiles\\")
        .mkdir();
      for (int k = 1; k < links.getLength() + 1; k++) {
        Element link = (Element)links.item(k - 1);
        String a = link.getAttribute("name");
        PrintWriter pw1 = new PrintWriter(new FileWriter(
          GenericKeywords.outputDirectory + "\\Resultfiles\\" + a + 
          ".html"));
        PrintStream ps = new PrintStream(
          GenericKeywords.outputDirectory + "\\Resultfiles\\" + a + 
          ".html");
        try
        {
          ps.println("<html><body>" + str[k] + "<center><a href=" + "'" + 
            "..\\SummaryPage.html'>back to summary</a></center>" + 
            "</body></html>");
          ps.flush();
          ps.close();
        }
        catch (Exception localException1) {}
        


        Element testCaseName = testngDocument
          .createElement("testCaseLinks");
        testCaseName.appendChild(testngDocument
          .createTextNode(".\\Resultfiles\\" + a + ".html"));
        node.appendChild(testCaseName);
      }
      
      TransformerFactory tff = TransformerFactory.newInstance();
      Transformer transformer = tff.newTransformer();
      DOMSource xmlSource = new DOMSource(testngDocument);
      StreamResult outputTarget = new StreamResult(
        GenericKeywords.outputDirectory + "/testng" + "\\copyfile.xml");
      transformer.transform(xmlSource, outputTarget);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
  
  public static void transformXsltToHtmlCoverPage()
  {
    try {
      PrintWriter pw = new PrintWriter(new FileWriter(
        GenericKeywords.outputDirectory + "\\CoverPage.html"));
      Source input = new StreamSource(GenericKeywords.outputDirectory + "/testng" + 
        "\\copyfile.xml");
      Source xsl = new StreamSource(".\\xsltfiles\\CoverPage.xslt");
      Result output = new StreamResult(pw);
      TransformerFactory factory = TransformerFactory.newInstance();
      Transformer transformer = factory.newTransformer(xsl);
      transformer.setOutputProperty("indent", "yes");
      transformer.transform(input, output);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
  
  public static void transformXsltToHtmlSummaryPage()
  {
    try {
      PrintWriter pw = new PrintWriter(new FileWriter(
        GenericKeywords.outputDirectory + "\\SummaryPage.html"));
      Source input = new StreamSource(GenericKeywords.outputDirectory + "/testng" + 
        "\\copyfile.xml");
      Source xsl = new StreamSource(".\\xsltfiles\\SummaryReport.xslt");
      Result output = new StreamResult(pw);
      TransformerFactory factory = TransformerFactory.newInstance();
      Transformer transformer = factory.newTransformer(xsl);
      transformer.setOutputProperty("indent", "yes");
      transformer.transform(input, output);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
  
  public static void transformXsltToPdfSummaryPage()
  {
    try {
      PrintWriter pw = new PrintWriter(new FileWriter(
        GenericKeywords.outputDirectory + "\\PdfReport.html"));
      Source input = new StreamSource(GenericKeywords.outputDirectory + 
        "\\copyfile.xml");
      Source xsl = new StreamSource(".\\data\\PdfReport.xslt");
      Result output = new StreamResult(pw);
      TransformerFactory factory = TransformerFactory.newInstance();
      Transformer transformer = factory.newTransformer(xsl);
      transformer.setOutputProperty("indent", "yes");
      transformer.transform(input, output);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
  
  public static void htmlToxhtml() {
    try {
      File file = new File(GenericKeywords.outputDirectory + 
        "\\PdfReport.html");
      InputStream is = null;
      OutputStream out = new FileOutputStream(
        GenericKeywords.outputDirectory + "/pdffile.xml");
      
      is = new FileInputStream(file);
      Tidy tidy = new Tidy();
      tidy.setXHTML(true);
      tidy.parse(is, out);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
  

  public static void xhtmlTopdf()
  {
    try
    {
      String inputFile = GenericKeywords.outputDirectory + "/pdffile.xml";
      String url = new File(inputFile).toURI().toURL().toString();
      String outputFile = GenericKeywords.outputDirectory + 
        "//pdfReport1.pdf";
      OutputStream os = new FileOutputStream(outputFile);
      ITextRenderer renderer = new ITextRenderer();
      renderer.setDocument(url);
      renderer.layout();
      renderer.createPDF(os);
      os.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
