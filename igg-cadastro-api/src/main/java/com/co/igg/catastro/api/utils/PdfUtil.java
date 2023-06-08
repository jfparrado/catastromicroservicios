package com.co.igg.catastro.api.utils;

import java.awt.geom.Point2D;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.qoppa.pdf.PDFException;
import com.qoppa.pdf.TextPosition;
import com.qoppa.pdfProcess.PDFDocument;
import com.qoppa.pdfProcess.PDFPage;

public class PdfUtil {
	
	public static void processPDF3(String src, String dest) throws PDFException, IOException, DocumentException {
		// Open the document
				PDFDocument inDoc = new PDFDocument (src, null);
				
				PdfReader reader = new PdfReader(src);
				PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
				
				// Loop through the pages, searching for text
				for (int pageIx = 0; pageIx < inDoc.getPageCount(); ++pageIx)
				{
					PdfContentByte canvas = stamper.getOverContent(pageIx+1);
				    canvas.saveState();
				    
					 // Search for the text in a page
					 PDFPage page = inDoc.getPage (pageIx);
					 Vector<TextPosition> searchResults = page.findText("CC#", false, false);
					 searchResults.addAll(page.findText("Nro Matrícula", false, false));
					 searchResults.addAll(page.findText("CODIGO CATASTRAL", false, false));
					 searchResults.addAll(page.findText("NIT#", false, false));
					 
					 System.out.println ("Page " + pageIx + " - Found " + searchResults.size() + " instances");
					 if (searchResults.size () > 0)
					 {
						 
					   for (int count = 0; count < searchResults.size(); ++count)
					   {
					     // Get the position of the text
					     TextPosition textPos = (TextPosition)searchResults.get (count);
					     // Vector for annotation quadrilateral bounds
					     Point2D[] p = textPos.getQuadrilateral();
					     int x = (int) p[2].getX();
					     int y = (int) (790-p[2].getY());
					     canvas.rectangle(x, y, 86, 15);
					     canvas.setColorStroke(BaseColor.YELLOW);
					     canvas.setColorFill(BaseColor.YELLOW);
					     canvas.stroke();
					     
					     System.out.println("Page"+pageIx+":"+searchResults.get(count).getText()+"Posicion:"+ (x) +","+ (y));
					   }
					   canvas.fill();
				       canvas.restoreState();
				     
					 }
				}
				stamper.close();
		        reader.close();
	}
}
