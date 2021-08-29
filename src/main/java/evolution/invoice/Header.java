package evolution.invoice;

import Data.faktura;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Header {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private Calendar invoiceDate;
    private StringBuffer invoiceNumber;
    private faktura data;

    public Header(faktura dt) {

        data = new faktura();
        data = dt;

        System.out.println("Vystaveno: " + data.getVystavenoStr());
        invoiceDate = dt.getVystaveno();
        System.out.println("Vystaveno2: " + invoiceDate.getTime());
        invoiceNumber = dt.getNumber();
        System.out.println("Číslo faktury: " + dt.getNumber() +" -- " + invoiceNumber);

    }

    public String getInvoiceNumber() {
        return this.invoiceNumber.toString();
    }

    public void printPDF(PDDocument pdfDocument, PDPageContentStream contents) throws IOException {
        PDImageXObject pdImage = PDImageXObject.createFromFile("logo.png", pdfDocument);
        final float width = 60f;
        final float scale = width / pdImage.getWidth();
        contents.drawImage(pdImage, 50, 720, width, pdImage.getHeight()*scale);


        PDFont headerFont = PDType1Font.HELVETICA_BOLD;
        PDFPrinter headerPrinter = new PDFPrinter(contents, headerFont, 16);
        headerPrinter.putText(120, 740, "Evolution design s.r.o.");

        PDFont font = PDType1Font.HELVETICA;
        PDFPrinter textPrinter = new PDFPrinter(contents, font, 10);
        textPrinter.putText(120, 720, "Dlouhá 67/39");
        textPrinter.putText(120, 708, "Teplice, 415 01");
        textPrinter.putText(120, 696, "www.evolution-design.cz");

        Color color = new Color(200, 200, 200);
        PDFPrinter invoiceHeaderPrinter = new PDFPrinter(contents, font, 24, color);
        invoiceHeaderPrinter.putText(450, 740, "Faktura");

        textPrinter.putText(400, 710, "Invoice date:");
        textPrinter.putText(400, 698, "Invoice number:");
        textPrinter.putText(500, 710, invoiceDate.get(Calendar.DAY_OF_MONTH) + "." + (invoiceDate.get(Calendar.MONTH) + 1) + "." + invoiceDate.get(Calendar.YEAR));
        if(this.getInvoiceNumber() != null && !this.getInvoiceNumber().isEmpty()) textPrinter.putText(500, 698, invoiceNumber.toString());
    }
}
