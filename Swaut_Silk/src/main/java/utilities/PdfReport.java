package utilities;


import com.lowagie.text.Document;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfReport
{
  public PdfReport() {}
  
  public static void pdf()
  {
    try
    {
    	com.lowagie.text.pdf.PdfReader reader = new com.lowagie.text.pdf.PdfReader(GenericKeywords.outputDirectory + 
    	        "//pdfReport1.pdf");
    	      int n = reader.getNumberOfPages();
    	      com.lowagie.text.Rectangle psize = reader.getPageSize(1);
    	      Document document = new Document(psize);
    	      
    	      com.lowagie.text.pdf.PdfWriter writer = com.lowagie.text.pdf.PdfWriter.getInstance(document, 
    	        new java.io.FileOutputStream(GenericKeywords.outputDirectory + 
    	        "//pdfReport.pdf"));
    	      document.open();
    	      document.open();
    	      com.lowagie.text.Image image1 = com.lowagie.text.Image.getInstance("Logos/18.jpg");
    	      com.lowagie.text.Image image2 = com.lowagie.text.Image.getInstance("Logos/Indium-Software-Logo.jpg");
    	      
    	      image1.setAbsolutePosition(0.0F, 0.0F);
    	      image2.setAbsolutePosition(0.0F, 0.0F);
    	      
    	      PdfContentByte byte1 = writer.getDirectContent();
    	      com.lowagie.text.pdf.PdfTemplate tp1 = byte1.createTemplate(600.0F, 150.0F);
    	      tp1.addImage(image2);
    	      
    	      PdfContentByte byte2 = writer.getDirectContent();
    	      com.lowagie.text.pdf.PdfTemplate tp2 = byte2.createTemplate(600.0F, 150.0F);
    	      
    	      tp2.addImage(image1);
    	      
    	      byte1.addTemplate(tp1, 0.0F, 715.0F);
    	      byte2.addTemplate(tp2, 0.0F, 50.0F);
    	      
    	     // com.lowagie.text.Phrase phrase1 = new Phrase(byte1, com.lowagie.text.FontFactory.getFont("Times-Roman", 7.0F, 0));
    	    //  com.lowagie.text.Phrase phrase2 = new Phrase(byte2, com.lowagie.text.FontFactory.getFont("Times-Roman", 7.0F, 0));
    	      
    	     // com.lowagie.text.Phrase phrase1 = new Phrase(byte1, com.lowagie.text.FontFactory.getFont("Times-Roman", 7.0F, 0));
    	     // com.lowagie.text.Phrase phrase2 = new Phrase(byte2, com.lowagie.text.FontFactory.getFont("Times-Roman", 7.0F, 0));
    	      
    	      //HeaderFooter header = new HeaderFooter(phrase1, true);
    	      
    	     // HeaderFooter footer = new HeaderFooter(phrase2, true);
    	      
    	      HeaderFooter header = new HeaderFooter(null, true);
    	      
    	       HeaderFooter footer = new HeaderFooter(null, true);
    	      
    	      document.setHeader(header);
    	      com.lowagie.text.pdf.PdfImportedPage page = writer.getImportedPage(reader, 1);
    	      byte1.addTemplate(page, 0.0F, 0.0F);
    	      document.setFooter(footer);
    	      document.close();
    }
    catch (Exception ex) {
      System.out.println(ex);
    }
  }
  
  public static void pdfReport()
  {
    try
    {
      com.lowagie.text.pdf.PdfReader reader = new com.lowagie.text.pdf.PdfReader(utilities.GenericKeywords.outputDirectory + 
        "//TestCaseResults.pdf");
      com.lowagie.text.pdf.PdfReader reader1 = new com.lowagie.text.pdf.PdfReader(utilities.GenericKeywords.outputDirectory + 
        "//pdfReport.pdf");
      int n = reader.getNumberOfPages();
      com.lowagie.text.Rectangle psize = reader.getPageSize(1);
      com.lowagie.text.Rectangle psize1 = reader1.getPageSize(1);
      Document document = new Document(psize);
      Document document1 = new Document(psize1);
      com.lowagie.text.pdf.PdfWriter writer = com.lowagie.text.pdf.PdfWriter.getInstance(document, 
        new java.io.FileOutputStream(utilities.GenericKeywords.outputDirectory + 
        "//PdfTestCaseResult.pdf"));
      document.open();
      com.lowagie.text.pdf.PdfImportedPage page = writer.getImportedPage(reader1, 1);
      PdfContentByte byte1 = writer.getDirectContent();
      byte1.addTemplate(page, 0.0F, 0.0F);
      document.newPage();
      for (int i = 1; i <= n; i++) {
        com.lowagie.text.pdf.PdfImportedPage page1 = writer.getImportedPage(reader, i);
        PdfContentByte byte2 = writer.getDirectContentUnder();
        byte2.addTemplate(page1, 0.0F, 0.0F);
        document.newPage();
      }
      document.close();
    }
    catch (java.io.IOException e) {
      e.printStackTrace();
    }
    catch (com.lowagie.text.DocumentException e) {
      e.printStackTrace();
    }
  }
}
