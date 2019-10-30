package tn.esprit.service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class testpdf {
    
    public  void main(String[] args) throws IOException, DocumentException {
    
    //	createPdf(DEST);
    }
    public static void createPdf(String dest,String Nom,String Prenom,String Email, String ROLE) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());
        Document myDocument = new Document();
        PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(dest));
       
        myDocument.open();
        
        Image image = Image.getInstance("C://Users//PC//Pictures//2.png");
        image.setAbsolutePosition(473f, 750f);
        image.scaleAbsolute(80f,70f);
                
       
        PdfPTable table = new PdfPTable(2);
       // table.getDefaultCell().setBorder(0);
        myDocument.add(image);
        myDocument.add(new Paragraph(Nom+Prenom,FontFactory.getFont(FontFactory.TIMES_BOLD,32,Font.BOLD,BaseColor.DARK_GRAY ))); 
        myDocument.add(new Paragraph("",FontFactory.getFont(FontFactory.TIMES_BOLD,9,Font.BOLD,BaseColor.DARK_GRAY))); 
        myDocument.add(new Paragraph("",FontFactory.getFont(FontFactory.TIMES_BOLD,9,Font.BOLD,BaseColor.DARK_GRAY)));
        myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
        myDocument.add(new Paragraph("CONTACT DETAILS",FontFactory.getFont(FontFactory.TIMES_BOLD,9,Font.BOLD,BaseColor.DARK_GRAY ))); 
        myDocument.add(new Paragraph(Email,FontFactory.getFont(FontFactory.TIMES_BOLD,7,Font.ITALIC,BaseColor.DARK_GRAY  )));
        myDocument.add(new Paragraph("+21655182042",FontFactory.getFont(FontFactory.TIMES_BOLD,7,Font.ITALIC ,BaseColor.DARK_GRAY )));
        myDocument.add(new Paragraph("tunisien",FontFactory.getFont(FontFactory.TIMES_BOLD,7,Font.ITALIC,BaseColor.DARK_GRAY  )));
        myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
        myDocument.add(new Paragraph("SKILLS",FontFactory.getFont(FontFactory.TIMES_BOLD,9,Font.BOLD,BaseColor.DARK_GRAY  )));
        table.setHeaderRows(1);
        /*PdfPCell cell;
        cell = new PdfPCell(new Phrase("The below are my skills"));
        cell.setColspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("SKILLS"));
        cell.setRowspan(1);
        table.addCell(cell);*/
      
        myDocument.add(table);
        myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
        myDocument.add(new Paragraph("QUALIFICATIONS",FontFactory.getFont(FontFactory.TIMES_BOLD,9,Font.BOLD,BaseColor.DARK_GRAY  )));
        myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
        myDocument.add(new Paragraph("WORK EXPERIENCE",FontFactory.getFont(FontFactory.TIMES_BOLD,10,Font.BOLD,BaseColor.DARK_GRAY  )));
        myDocument.add(new Paragraph("Advyteam" + System.lineSeparator()+ROLE,FontFactory.getFont(FontFactory.TIMES_BOLD,7,Font.ITALIC,BaseColor.DARK_GRAY)));
        myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
        myDocument.add(new Paragraph("REFERENCES",FontFactory.getFont(FontFactory.TIMES_BOLD,9,Font.BOLD,BaseColor.DARK_GRAY  )));
        myDocument.add(new Paragraph("Available upon request",FontFactory.getFont(FontFactory.TIMES_BOLD,6,Font.ITALIC,BaseColor.DARK_GRAY  )));


        myDocument.close(); 
    }
}
