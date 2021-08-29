package evolution;

import Data.faktura;
import evolution.invoice.Invoice;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;


public class PdfDoc {

    void PdfDoc() {
        //Creating PDF document object

    }

    public int createFile(faktura data) {
        PDDocument document = new PDDocument();
        PDPage myPage = new PDPage();
        document.addPage(myPage);
        Invoice invoice = new Invoice(data);

        PDPageContentStream contents = null;
        try {
            contents = new PDPageContentStream(document, myPage);

        } catch (IOException exception) {
            System.out.println("Err5");
            exception.printStackTrace();
        }

        System.out.println("Data1: "+data.getPopis());
        try {

            invoice.printPDF(document, contents);
        } catch (IOException exception) {
            System.out.println("Err6");
            exception.printStackTrace();
        }

        try {
            contents.close();
            document.save("C:\\tmp\\my_doc.pdf");

            System.out.println("PDF created");

            //Closing the document
            document.close();
        } catch (IOException e) {
            System.out.println("Chyba "+ e.getLocalizedMessage()+e.getCause());
        }
        return 0;
    }

    public  void  viewPdf() {
        try {

//            ProcessBuilder p = new ProcessBuilder();
//            p.command("cmd.exe ");// cmd.exe /c c:\\Program Files (x86)\\Adobe\\Acrobat Reader DC\\Reader\\AcroRd32.exe C:\\tmp\\my_doc.pdf"
           System.out.println("PDF viewed");
//            p.start();
            Runtime r = Runtime.getRuntime();
            r.exec("c:\\Program Files (x86)\\Adobe\\Acrobat Reader DC\\Reader\\AcroRd32.exe C:\\tmp\\my_doc.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
