package saffchen.reports;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.util.ArrayList;
import java.util.List;

public class PdfGenerator implements ReportGenerator{
    public String getDataForReport(){
        return "";
    }
    public PDDocument createPDFDocument(){
        PDDocument pdfDocument = null;
        List<PDPage> pages = new ArrayList<>();
        try {
            pdfDocument = new PDDocument();
            pages.add(new PDPage());
            for (PDPage page:pages) {
                pdfDocument.addPage(page);
            }
            PDPage page = pdfDocument.getPage(0);
            PDPageContentStream contentStream = new PDPageContentStream(pdfDocument, page);

            //Begin the Content stream
            contentStream.beginText();

            //Setting the font to the Content stream
            contentStream.setFont(PDType1Font.COURIER, 18);

            //Setting the position for the line
            contentStream.newLineAtOffset(100, 500);

            String text = "TEST REPORT FROM JRM STOCK JAVA SYSTEM";

            //Adding text in the form of string
            contentStream.showText(text);

            //Ending the content stream
            contentStream.endText();

            //Closing the content stream
            contentStream.close();

            //Saving the document
            pdfDocument.save("Report.pdf");
            pdfDocument.close();

            System.out.println("Report was created successfully!");

        }catch (Exception e){
            System.out.println("Error: Can't create PDF document");
        }

        return pdfDocument;
    }

    @Override
    public void generateReport() {
        createPDFDocument();
    }
}
