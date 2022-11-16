package saffchen.theme;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * @author saffchen created on 21.08.2022
 */
public interface Theme {

    Font REPORT_HEADER = FontFactory.getFont(FontFactory.COURIER, 20, Font.BOLD,
            new CMYKColor(0, 255, 0, 0));
    Font TABLE_HEADER = FontFactory.getFont(FontFactory.COURIER, 11, Font.BOLD,
            new CMYKColor(0, 0, 0, 255));
    Font CELL_HEADER = FontFactory.getFont(FontFactory.COURIER, 11, Font.NORMAL,
            new CMYKColor(0, 0, 0, 255));

    BaseColor getBaseColor();

    String getName();

    default PdfPTable drawTable(Integer columnCount) throws DocumentException {
        PdfPTable table = new PdfPTable(columnCount);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        table.setPaddingTop(50f);

        float[] columnWidths = new float[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columnWidths[i] = 1f;
        }
        table.setWidths(columnWidths);

        return table;
    }

    default PdfPCell drawCell(String text) {
        return drawCell(text, getBaseColor(), getCellHeader());
    }

    default PdfPCell drawHeader(String text) {
        return drawCell(text, getBaseColor(), getTableHeader());
    }

    default PdfPCell drawCell(String text, BaseColor color, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(text, font));
        cell.setBorderColor(getBorderColor());
        cell.setBackgroundColor(color);
        cell.setPaddingLeft(10);
        cell.setPaddingTop(10);
        cell.setPaddingBottom(10);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        return cell;
    }

    default BaseColor getBorderColor() {
        return BaseColor.DARK_GRAY;
    }

    default Font getReportHeader() {
        return REPORT_HEADER;
    }

    default Font getTableHeader() {
        return TABLE_HEADER;
    }

    default Font getCellHeader() {
        return CELL_HEADER;
    }
}