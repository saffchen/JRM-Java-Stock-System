package saffchen.command;

import saffchen.product.Product;
import saffchen.reports.PdfGenerator;

public class ReceiverDB {

    void addProduct(Product product){
        System.out.println("Adding the product...");
    }

    public void modifyProduct(){
        System.out.println("Modified the product...");
    }

    void deleteProduct(){
        System.out.println("Deleting the product...");
    }

    public void showAll(){
        System.out.println("Selecting the product...");
    }

    public void createReport(){
        PdfGenerator pdfReport = new PdfGenerator();
        pdfReport.createPDFDocument();
    }

}
