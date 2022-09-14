package saffchen.reports;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import saffchen.database.FileConnection;
import saffchen.entities.ProductEntity;
import saffchen.utils.FileStorageUtils;

import java.io.FileOutputStream;
import java.util.List;

public class PDFReportFromFile implements Report {
    private String criteries;
    private String field;
    private FileConnection fileConnection = FileConnection.getInstance("stock_import_csv.csv");
    private FileStorageUtils fileStorageUtils = new FileStorageUtils(fileConnection);

    private final Font reportHeader = FontFactory.getFont(FontFactory.COURIER, 20, Font.BOLD,
            new CMYKColor(0, 255, 0, 0));
    private final Font tableHeader = FontFactory.getFont(FontFactory.COURIER, 11, Font.BOLD,
            new CMYKColor(0, 0, 0, 255));
    private final Font cellHeader = FontFactory.getFont(FontFactory.COURIER, 11, Font.NORMAL,
            new CMYKColor(0, 0, 0, 255));


    public PDFReportFromFile(String field, String criteries) {
        this.criteries = criteries;
        this.field = field;
    }

    private PdfPTable drawTable(Integer columnCount) throws DocumentException {
        PdfPTable table = new PdfPTable(columnCount); // 6 columns.
        table.setWidthPercentage(100); // Width 100%
        table.setSpacingBefore(10f); // Space before table
        table.setSpacingAfter(10f); // Space after table
        table.setPaddingTop(50f);
        // Set Column widths
        float[] columnWidths = new float[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columnWidths[i] = 1f;
        }
        table.setWidths(columnWidths);
        return table;
    }

    private PdfPCell drawCell(String text, BaseColor fontColor, Font style) {
        PdfPCell cell = new PdfPCell(new Paragraph(text, style));
        cell.setBorderColor(BaseColor.DARK_GRAY);
        cell.setBackgroundColor(fontColor);
        cell.setPaddingLeft(10);
        cell.setPaddingTop(10);
        cell.setPaddingBottom(10);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        return cell;
    }

    @Override
    public void generateReport() throws Exception {
        List<ProductEntity> tableData = fileStorageUtils.getDataForReportFromCSV(field, criteries);

        Document document = new Document();
        try {
            boolean isLight = true; //if background is light

            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("reportBy" + field + ".pdf"));
            document.open();
            document.add(new Paragraph("Found Products (by "
                    + field + " with criteria \""
                    + criteries + "\")", reportHeader));

            PdfPTable table = drawTable(7);
            List<String> headers = fileStorageUtils.getHeadersFromCSV();
            for (String cell : headers) {
                table.addCell(drawCell(cell.toUpperCase(), BaseColor.LIGHT_GRAY, tableHeader));
            }

            BaseColor color;
            for (ProductEntity product : tableData) {
                if (isLight)
                    color = BaseColor.LIGHT_GRAY;
                else
                    color = BaseColor.GRAY;

                table.addCell(drawCell(product.getName(), color, cellHeader));
                table.addCell(drawCell(product.getDescription(), color, cellHeader));
                table.addCell(drawCell(product.getPrice().toString(), color, cellHeader));
                table.addCell(drawCell(product.getTags().toString(), color, cellHeader));
                table.addCell(drawCell(product.getCategory(), color, cellHeader));
                table.addCell(drawCell(product.getCount().toString(), color, cellHeader));
                table.addCell(drawCell(product.getStore().getName(), color, cellHeader));

                isLight = !isLight;
                table.completeRow();
            }
            document.add(table);
            document.close();
            writer.close();
            System.out.println("Report was created successfully!");
        } catch (Exception e) {
            throw new Exception(e);
        }

    }
}