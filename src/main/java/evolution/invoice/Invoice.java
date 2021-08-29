package evolution.invoice;

import Data.faktura;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private Header header = null;
    private Address shipTo = null;
    private Address billTo = null;
    private List<InvoiceRow> rows = new ArrayList<InvoiceRow>();
    private ShippingData shipData = null;
    private String notes;
    private faktura data;
    private int maxRowSize = 23;
    private int maxPageWithSummation = 16;
    private int breakPoint = 12;

    public Invoice(faktura fakt) {
        this.data = new faktura();
        this.data = fakt;

    }

        private PDPageContentStream newPage (PDDocument pdfDocument, PDPageContentStream contents,int rowY, int numRows) throws
        IOException {
            contents.close();
            PDPage pdfPage = new PDPage();
            pdfDocument.addPage(pdfPage);
            contents = new PDPageContentStream(pdfDocument, pdfPage);
            printRowHeader(contents, rowY);
            printRowBackGround(contents, rowY - 21, numRows);
            return contents;
        }


        private boolean newPageRequired ( int numPrintedRows, int rowsLeft){
            if (numPrintedRows >= this.maxRowSize) return true;
            if (this.maxPageWithSummation < rowsLeft && rowsLeft < this.maxRowSize) {
                if (numPrintedRows >= this.breakPoint) return true;
            }
            return false;
        }

        public void printPDF (PDDocument pdfDocument, PDPageContentStream contents) throws IOException {
//            int rowY = 520;
//            int numPrintedRows = 0;
//            printHeader(pdfDocument, contents);
//
//            int rowsLeft = rows.size();
//
//            printRowHeader(contents, rowY);
//            printRowBackGround(contents, rowY-21,
//                    rowsLeft < this.maxPageWithSummation ? this.maxPageWithSummation : this.maxRowSize
//            );
//
//            BigDecimal totalCost = BigDecimal.ZERO;
//            for (InvoiceRow invoiceRow : rows) {
//                numPrintedRows++;
//                rowY -= 20;
//                invoiceRow.printPDF(contents, rowY);
//                totalCost = invoiceRow.addTotal(totalCost);
//                if(newPageRequired(numPrintedRows, rowsLeft)) {
//                    rowsLeft -= numPrintedRows;
//                    numPrintedRows = 0;
//                    maxRowSize = 30;
//                    maxPageWithSummation = 23;
//                    breakPoint = 18;
//                    rowY = 660;
//                    contents = newPage(pdfDocument, contents, rowY,
//                            rowsLeft < this.maxPageWithSummation ? this.maxPageWithSummation : this.maxRowSize
//                    );
//
//                }
//            }

            printRowHeader(contents, 660);

            printCustomer(contents, true);
            printCustomer(contents, false);
            printSummary(contents);
            printFooter(contents);

        }

        public void printCustomer(PDPageContentStream contents, boolean rightSide) throws IOException  {
            PDFont font = PDType1Font.HELVETICA;
            Color color = new Color(80, 80, 80);

            int x = rightSide ? 400 : 120;

            int y = 660;

            PDFPrinter headerPrinter = new PDFPrinter(contents, font, 10);
            headerPrinter.putText(x, y, rightSide ? "Bill to:" : "Ship to:");

            y -= 12;
            PDFPrinter addressPrinter = new PDFPrinter(contents, font, 10, color);
            addressPrinter.putText(x, y, data.getFullName());
            y -= 12;
            addressPrinter.putText(x, y, data.getZakaznik().getUlice());
            y -= 12;
        //    if (data.hasAddress2()) {
                addressPrinter.putText(x, y, data.getZakaznik().getMesto());
                y -= 12;
         //   }
//            if (data.hasAddress3()) {
//                addressPrinter.putText(x, y, data.getAddress3());
//                y -= 12;
//            }
            addressPrinter.putText(x, y, data.getZakaznik().getPsc() +" "+ data.getZakaznik().getMesto());
            y -= 12;
            addressPrinter.putText(x, y, data.getZakaznik().getStat());
        }
        public void printHeader (PDDocument pdfDocument, PDPageContentStream contents) throws IOException {
            PDImageXObject pdImage = PDImageXObject.createFromFile("logo.png", pdfDocument);
            final float width = 60f;
            final float scale = width / pdImage.getWidth();
            contents.drawImage(pdImage, 50, 720, width, pdImage.getHeight()*scale);

            Color color = new Color(200, 200, 200);
            PDFont font = PDType1Font.HELVETICA;
            PDFPrinter invoiceHeaderPrinter = new PDFPrinter(contents, font, 24, color);

            invoiceHeaderPrinter.putText(450, 740, "Faktura");

            invoiceHeaderPrinter.putText(400, 710, "Invoice date:");
            invoiceHeaderPrinter.putText(400, 698, "Invoice number:");
            invoiceHeaderPrinter.putText(500, 710, data.getVystavenoStr());


            PDFont headerFont = PDType1Font.HELVETICA_BOLD;
            PDFPrinter headerPrinter = new PDFPrinter(contents, headerFont, 16);
            headerPrinter.putText(120, 740, "Evolution design s.r.o.");

            PDFont font2 = PDType1Font.HELVETICA;
            PDFPrinter textPrinter = new PDFPrinter(contents, font2, 10);
            textPrinter.putText(120, 720, "Dlouhá 67/39");
            textPrinter.putText(120, 708, "Teplice, 415 01");
            textPrinter.putText(120, 696, "www.evolution-design.cz");

        }
        public void printSummary (PDPageContentStream contents) throws IOException {
            Color strokeColor = new Color(100, 100, 100);
            contents.setStrokingColor(strokeColor);
            Color fillColor = new Color(240, 240, 240);
            contents.setNonStrokingColor(fillColor);

            PDFPrinter summeryLabelPrinter = new PDFPrinter(contents, PDType1Font.HELVETICA_BOLD, 8);
            PDFPrinter summeryValuePrinter = new PDFPrinter(contents, PDType1Font.HELVETICA, 12);




            int summeryStartY = 171;

            summeryLabelPrinter.putText(451, summeryStartY, "Sub total");
            contents.addRect(450, summeryStartY - 17, 120, 16);
            contents.stroke();
            summeryValuePrinter.putTextToTheRight(566, summeryStartY - 13, data.getCena_suma() + " SEK");

            summeryLabelPrinter.putText(451, summeryStartY - 30, "Vat");
            contents.addRect(450, summeryStartY - 30 - 17, 120, 16);
            contents.stroke();
            summeryValuePrinter.putTextToTheRight(566, summeryStartY - 30 - 13, data.getCena_suma()+ " SEK");

            summeryLabelPrinter.putText(451, summeryStartY - 60, "Total price");
            contents.addRect(450, summeryStartY - 60 - 17, 120, 16);
            contents.stroke();
            summeryValuePrinter.putTextToTheRight(566, summeryStartY - 60 - 13, data.getCena_suma() + " SEK");
        }

        public void printRowBackGround (PDPageContentStream contents,int rowY, int numRows) throws IOException {
            Color strokeColor = new Color(100, 100, 100);
            contents.setStrokingColor(strokeColor);
            Color fillColor = new Color(240, 240, 240);
            contents.setNonStrokingColor(fillColor);

            boolean odd = true;
            for (int i = 0; i < numRows; i++) {
                if (odd) {
                    contents.addRect(51, rowY, 518, 20);
                    contents.fill();
                }

                contents.moveTo(50, rowY);
                contents.lineTo(50, rowY + 20);
                contents.moveTo(570, rowY);
                contents.lineTo(570, rowY + 20);
                contents.stroke();
                rowY -= 20;
                odd = !odd;
            }

            contents.moveTo(50, rowY + 20);
            contents.lineTo(570, rowY + 20);
            contents.stroke();
        }

        public void printRowHeader (PDPageContentStream contents,int headerY) throws IOException {
            Color fillColor = new Color(230, 230, 230);
            Color strokeColor = new Color(100, 100, 100);
            contents.setStrokingColor(strokeColor);
            contents.setNonStrokingColor(fillColor);
            contents.addRect(50, headerY, 520, 20);
            contents.fillAndStroke();

            PDFont font = PDType1Font.HELVETICA;
            PDFPrinter headPrinter = new PDFPrinter(contents, font, 12);
            headPrinter.putText(60, headerY + 7, "Product no.");
            headPrinter.putText(160, headerY + 7, "Description");
            headPrinter.putText(380, headerY + 7, "Quantity");
            headPrinter.putText(440, headerY + 7, "Unit price");
            headPrinter.putText(510, headerY + 7, "Total");
        }

        public void printFooter (PDPageContentStream contents) throws IOException {
            Color strokeColor = new Color(100, 100, 100);
            contents.setStrokingColor(strokeColor);
            contents.addRect(50, 35, 370, 135);
            contents.stroke();

            PDFPrinter footerLabelPrinter = new PDFPrinter(contents, PDType1Font.HELVETICA_BOLD, 8);
            PDFPrinter footerValuePrinter = new PDFPrinter(contents, PDType1Font.HELVETICA, 8);
            footerLabelPrinter.putText(50, 172, "Notes");
            int rowY = 160;
//           StringBuilder sb = new StringBuilder();
//
//
//            footerValuePrinter.putText(55, rowY, sb.toString());
        }

        public List<InvoiceRow> getRows () {
            return this.rows;
        }
        public void setRow(InvoiceRow row){
            this.rows.add(row);
        }
        public void setNotes (String notes){
            this.notes = notes;
        }
        public String getNotes () {
            return this.notes;
        }
    }