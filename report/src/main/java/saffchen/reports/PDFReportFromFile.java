package saffchen.reports;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import saffchen.database.FileConnection;
import saffchen.dto.ProductDtoReport;
import saffchen.theme.Theme;
import saffchen.theme.ThemeSelectionService;
import saffchen.utils.FileStorageUtils;

import java.io.FileOutputStream;
import java.util.List;

public class PDFReportFromFile implements Report {
    private String criteries;
    private String field;
    private final FileConnection fileConnection = FileConnection.getInstance("stock_import_csv.csv");
    private final FileStorageUtils fileStorageUtils = new FileStorageUtils(fileConnection);
    private final ThemeSelectionService themeSelectionService = new ThemeSelectionService();


    public PDFReportFromFile(String field, String criteries) {
        this.field = field;
        this.criteries = criteries;
    }

    @Override
    public void generateReport(String themeName) throws Exception {
        Theme theme = themeSelectionService.getTheme(themeName);
        this.report(theme);
    }

    private void report(Theme theme) throws Exception {
        List<ProductDtoReport> tableData = fileStorageUtils.getDataForReportFromCSV(field, criteries);

        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("reportBy" + field + ".pdf"));
            document.open();
            document.add(new Paragraph("Found Products (by "
                    + field + " with criteria \""
                    + criteries + "\")", theme.getReportHeader()));

            List<String> headers = fileStorageUtils.getHeadersFromCSV();

            PdfPTable table = buildTable(theme, tableData, headers);

            document.add(table);
            document.close();
            writer.close();
            System.out.println("Report was created successfully!");
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private PdfPTable buildTable(Theme theme, List<ProductDtoReport> tableData, List<String> headers) throws DocumentException {

        PdfPTable table = theme.drawTable(8);
        for (String cell : headers) {
            table.addCell(theme.drawHeader(cell.toUpperCase()));
        }

        for (ProductDtoReport product : tableData) {
            table.addCell(theme.drawCell(product.getId()));
            table.addCell(theme.drawCell(product.getTitle()));
            table.addCell(theme.drawCell(product.getDescription()));
            table.addCell(theme.drawCell(product.getPrice().toString()));
            table.addCell(theme.drawCell(product.getTags().toString()));
            table.addCell(theme.drawCell(product.getCategory()));
            table.addCell(theme.drawCell(product.getCount().toString()));
            table.addCell(theme.drawCell(product.getStore()));

            table.completeRow();
        }
        return table;
    }
}