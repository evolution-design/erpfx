package evolution.invoice;

import Data.faktura;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class ShippingData {
    private static final String dateFormat = "dd.MM.yyyy";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private String shipNumber;
    private String salesRep;
    private LocalDate shipDate;
    private String shipVia;
    private String terms;
    private LocalDate dueDate;

    public ShippingData(faktura fakt) {
        shipNumber = new String(fakt.getNumber());
        salesRep = new String("vvvv");
        shipDate =  LocalDate.of(fakt.getVystaveno().get(Calendar.YEAR),fakt.getVystaveno().get(Calendar.MONTH),fakt.getVystaveno().get(Calendar.DAY_OF_MONTH));
        shipVia = new String("Kaufland");
        terms  = new String("Kaufl");
        dueDate =  LocalDate.of(fakt.getVystaveno().get(Calendar.YEAR),fakt.getVystaveno().get(Calendar.MONTH),fakt.getVystaveno().get(Calendar.DAY_OF_MONTH)+1);

    }


    public void printPDF(PDPageContentStream contents) throws IOException {

        Color fillColor = new Color(230, 230, 230);
        Color strokeColor = new Color(100, 100, 100);
        contents.setStrokingColor(strokeColor);
        contents.setNonStrokingColor(fillColor);
        contents.addRect(50, 570, 520, 20);
        contents.fillAndStroke();
        contents.addRect(50, 550, 520, 20);
        contents.stroke();

        final int headerY = 577;
        PDFont font = PDType1Font.HELVETICA;
        PDFPrinter headerPrinter = new PDFPrinter(contents, font, 12);
        headerPrinter.putText(60, headerY, "Ship. number");
        headerPrinter.putText(160, headerY, "Sales Rep.");
        headerPrinter.putText(280, headerY, "Ship date");
        headerPrinter.putText(340, headerY, "Ship via");
        headerPrinter.putText(450, headerY, "Terms");
        headerPrinter.putText(510, headerY, "Due date");

        final int textY = 557;
        PDFPrinter textPrinter = new PDFPrinter(contents, font, 8);
        textPrinter.putText(60, textY, this.getShipNumber());
        textPrinter.putText(160, textY, this.getSalesRep());
        textPrinter.putText(280, textY, this.getShipDateString());
        textPrinter.putText(340, textY, this.getShipVia());
        textPrinter.putText(450, textY, this.getTerms());
        textPrinter.putText(510, textY, this.getDueDateString());
    }

    public void setShipNumber(String shipNumber) {
        this.shipNumber = shipNumber;
    }
    public String getShipNumber() {
        return this.shipNumber;
    }
    public void setSalesRep(String salesRep) {
        this.salesRep = salesRep;
    }
    public String getSalesRep() {
        return this.salesRep;
    }
    public void setShipDate(String shipDate) {

            this.shipDate = LocalDate.parse(shipDate, formatter);

    }
    public String getShipDateString() {
        return this.shipDate.toString();
    }
    public void setShipDate(LocalDate shipDate) {
        this.shipDate = shipDate;
    }
    public LocalDate getShipDate() {
        return this.shipDate;
    }
    public void setShipVia(String shipVia) {
        this.shipVia = shipVia;
    }
    public String getShipVia() {
        return this.shipVia;
    }
    public void setTerms(String terms) {
        this.terms = terms;
    }
    public String getTerms() {
        return this.terms;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = LocalDate.parse(dueDate, formatter);

    }
    public String getDueDateString() {
        dueDate.format(formatter);
        return this.dueDate.toString();
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public LocalDate getDueDate() {
        return this.dueDate;
    }
}
