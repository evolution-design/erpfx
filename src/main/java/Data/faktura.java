package Data;

import java.util.Calendar;

public class faktura {
    private StringBuffer Typ;
    private StringBuffer number;
    private double variabil;
    private StringBuffer popis;
    private Calendar vystaveno;
    private Calendar zdanPlneni;
    private Calendar splatnost;
    private Calendar zauctovani;
    private Company zakaznik;
    private float sleva;
    private float dph;
    private float cenaZaklad;
    private float zaklad_0DPH;
    private float zaklad_10DPH;
    private float zaklad_15DPH;
    private float zaklad_21DPH;
    private float dan_0DPH;
    private float dan_10DPH;
    private float dan_15DPH;
    private float dan_21DPH;
    private float cena_dan_0DPH;
    private float cena_dan_10DPH;
    private float cena_dan_15DPH;
    private float cena_dan_21DPH;
    private float cena_suma;
    private Item[] polozky;
    private StringBuffer formaObjednavky;

    public faktura() {
        Typ = new StringBuffer();
        number = new StringBuffer();
        popis = new StringBuffer();
        this.zakaznik = new Company();
    }

    enum Sate {
        NEZAPLACENO,
        ZAPLACENO
    }

    public String getFullName() {
        return(zakaznik.getTitul()+" "+zakaznik.getJmeno()+" "+zakaznik.getPrijmeni());
    }

    public StringBuffer getTyp() {
        return Typ;
    }

    public void setTyp(String typ) {
        Typ.append(typ);
    }

    public StringBuffer getNumber() {
        return number;
    }

    public void setNumber(String num) {
        this.number.append(num);
    }

    public double getVariabil() {
        return variabil;
    }

    public void setVariabil(double variabil) {
        this.variabil = variabil;
    }

    public StringBuffer getPopis() {
        return this.popis;
    }

    public void setPopis(StringBuffer popis) {
        this.popis = popis;
    }

    public Calendar getVystaveno() {
        return vystaveno;
    }

    public String getVystavenoStr() {
        StringBuffer str = new StringBuffer();

        str.append(vystaveno.get(Calendar.DAY_OF_MONTH));
        str.append(".");
        str.append(vystaveno.get(Calendar.MONTH));
        str.append(".");
        str.append(vystaveno.get(Calendar.YEAR));
        str.append(".");


        return str.toString();
    }

    public void setVystaveno(Calendar datumVystaveni) {
        this.vystaveno = datumVystaveni;
        System.out.println("Vystaven nastaveno: Den: " + vystaveno.get(Calendar.DAY_OF_MONTH) + ". Měsíc: " + (vystaveno.get(Calendar.MONTH) + 1) + ". Rok: " + vystaveno.get(Calendar.YEAR));
    }

    public Calendar getZdanPlneni() {
        return zdanPlneni;
    }

    public void setZdanPlneni(Calendar zdanPlneni) {
        this.zdanPlneni = zdanPlneni;
    }

    public Calendar getSplatnost() {
        return splatnost;
    }

    public String getSplatnostStr() {
        StringBuffer str = new StringBuffer();

        str.append(splatnost.get(Calendar.DAY_OF_MONTH));
        str.append(".");
        str.append(splatnost.get(Calendar.MONTH));
        str.append(".");
        str.append(splatnost.get(Calendar.YEAR));
        str.append(".");


        return str.toString();
    }

    public void setSplatnost(Calendar splatnost) {
        this.splatnost = splatnost;
    }

    public Calendar getZauctovani() {
        return zauctovani;
    }

    public String getZauctovaniStr() {
        StringBuffer str = new StringBuffer();

        str.append(zauctovani.get(Calendar.DAY_OF_MONTH));
        str.append(".");
        str.append(zauctovani.get(Calendar.MONTH));
        str.append(".");
        str.append(zauctovani.get(Calendar.YEAR));
        str.append(".");


        return str.toString();
    }
    public void setZauctovani(Calendar zauctovani) {
        this.zauctovani = zauctovani;
    }

    public Company getZakaznik() {
        return zakaznik;
    }

    public void setZakaznik(Company zakaznik) {
        this.zakaznik = zakaznik;
    }

    public float getSleva() {
        return sleva;
    }

    public void setSleva(float sleva) {
        this.sleva = sleva;
    }

    public float getDph() {
        return dph;
    }

    public void setDph(float dph) {
        this.dph = dph;
    }

    public float getZaklad_0DPH() {
        return zaklad_0DPH;
    }

    public void setZaklad_0DPH(float zaklad_0DPH) {
        this.zaklad_0DPH = zaklad_0DPH;
    }

    public float getZaklad_10DPH() {
        return zaklad_10DPH;
    }

    public void setZaklad_10DPH(float zaklad_10DPH) {
        this.zaklad_10DPH = zaklad_10DPH;
    }

    public float getZaklad_15DPH() {
        return zaklad_15DPH;
    }

    public void setZaklad_15DPH(float zaklad_15DPH) {
        this.zaklad_15DPH = zaklad_15DPH;
    }

    public float getZaklad_21DPH() {
        return zaklad_21DPH;
    }

    public void setZaklad_21DPH(float zaklad_21DPH) {
        this.zaklad_21DPH = zaklad_21DPH;
    }

    public float getDan_0DPH() {
        return dan_0DPH;
    }

    public void setDan_0DPH(float dan_0DPH) {
        this.dan_0DPH = dan_0DPH;
    }

    public float getDan_10DPH() {
        return dan_10DPH;
    }

    public void setDan_10DPH(float dan_10DPH) {
        this.dan_10DPH = dan_10DPH;
    }

    public float getDan_15DPH() {
        return dan_15DPH;
    }

    public void setDan_15DPH(float dan_15DPH) {
        this.dan_15DPH = dan_15DPH;
    }

    public float getDan_21DPH() {
        return dan_21DPH;
    }

    public void setDan_21DPH(float dan_21DPH) {
        this.dan_21DPH = dan_21DPH;
    }

    public float getCena_dan_0DPH() {
        return cena_dan_0DPH;
    }

    public void setCena_dan_0DPH(float cena_dan_0DPH) {
        this.cena_dan_0DPH = cena_dan_0DPH;
    }

    public float getCena_dan_10DPH() {
        return cena_dan_10DPH;
    }

    public void setCena_dan_10DPH(float cena_dan_10DPH) {
        this.cena_dan_10DPH = cena_dan_10DPH;
    }

    public float getCena_dan_15DPH() {
        return cena_dan_15DPH;
    }

    public void setCena_dan_15DPH(float cena_dan_15DPH) {
        this.cena_dan_15DPH = cena_dan_15DPH;
    }

    public float getCena_dan_21DPH() {
        return cena_dan_21DPH;
    }

    public void setCena_dan_21DPH(float cena_dan_21DPH) {
        this.cena_dan_21DPH = cena_dan_21DPH;
    }

    public float getCena_suma() {
        return cena_suma;
    }

    public void setCena_suma(float cena_suma) {
        this.cena_suma = cena_suma;
    }
}
