package Data;

import java.util.Date;

public class Item {
    private int typ;
    private Date datum;
    private short priplatky;
    private int hodin;
    private int osob;
    private int km;
    private float hotel;
    private int hodinyAuto;
    private String popis;

    public int getTyp() {
        return typ;
    }

    public void setTyp(int typ) {
        this.typ = typ;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public short getPriplatky() {
        return priplatky;
    }

    public void setPriplatky(short priplatky) {
        this.priplatky = priplatky;
    }

    public int getHodin() {
        return hodin;
    }

    public void setHodin(int hodin) {
        this.hodin = hodin;
    }

    public int getOsob() {
        return osob;
    }

    public void setOsob(int osob) {
        this.osob = osob;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public float getHotel() {
        return hotel;
    }

    public void setHotel(float hotel) {
        this.hotel = hotel;
    }

    public int getHodinyAuto() {
        return hodinyAuto;
    }

    public void setHodinyAuto(int hodinyAuto) {
        this.hodinyAuto = hodinyAuto;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }
}
