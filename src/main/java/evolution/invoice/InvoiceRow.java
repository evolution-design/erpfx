package evolution.invoice;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class InvoiceRow {
    private String productNumber;
    private String productDescription;
    private double quantity;
    private BigDecimal price;

    public InvoiceRow() {
        if(true) {
            this.setProductNumber((String) "productId");
        }
        if(true) {
            this.setProductDescription((String) "description");
        }
        if(true) {
            this.setQuantity((String) "2");
        }
        if(true) {
            this.setPrice((String) "100.0");
        }
    }

    public BigDecimal addTotal(BigDecimal totalCost) {
        return totalCost.add(this.getTotal());
    }

    public void printPDF(PDPageContentStream contents, int rowY) throws IOException {
        Color strokeColor = new Color(100, 100, 100);
        contents.setStrokingColor(strokeColor);

        PDFont font = PDType1Font.HELVETICA;
        PDFPrinter textPrinter = new PDFPrinter(contents, font, 8);
        textPrinter.putText(60, rowY+7, this.getProductNumber());
        textPrinter.putText(160, rowY+7, this.getProductDescription());
        textPrinter.putTextToTheRight(420, rowY+7, this.getQuantityString());
        textPrinter.putTextToTheRight(490, rowY+7, this.getPriceString());
        textPrinter.putTextToTheRight(560, rowY+7, this.getTotalString());
    }

    public String getTotalString() {
        BigDecimal printTotal = getTotal();
        printTotal.setScale(2, RoundingMode.HALF_EVEN);
        return printTotal.toString();
    }

    public String getPriceString() {
        BigDecimal printPrice = getPrice();
        printPrice.setScale(2, RoundingMode.HALF_EVEN);
        return printPrice.toString();
    }

    public String getQuantityString() {
        return Double.toString(quantity);
    }


    public BigDecimal getTotal() {
        return this.price.multiply(new BigDecimal(quantity));
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }
    public String getProductNumber() {
        return this.productNumber;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    public String getProductDescription() {
        return this.productDescription;
    }
    public void setQuantity(String quantity) {
        this.quantity = Double.parseDouble(quantity.replace(",", "."));
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    public double getQuantity() {
        return this.quantity;
    }
    public void setPrice(double price) {
        this.price = new BigDecimal(price);
    }
    public void setPrice(String price) {
        this.price = new BigDecimal(price.replace(",", "."));
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public BigDecimal getPrice() {
        return this.price;
    }
}
