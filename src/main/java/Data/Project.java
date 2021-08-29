package Data;

import java.util.Date;

public class Project {
    private String Nazev;
    private Company zakaznik;
    private int zakaznikID;
    private Date datumZalozeni;
    private Date datumUkonceni;
    private int aktHodin;
    private int budgetHodin;
    private int aktCena;
    private int budgetCena;
    private projektStav stav;

    enum projektStav {
        NABIDNUTO,
        PRIJATO,
        PRIPRAVA,
        ZPRACOVAVAN,
        UKONCEN
    }

    public String getNazev() {
        return Nazev;
    }

    public void setNazev(String nazev) {
        Nazev = nazev;
    }

    public Company getZakaznik() {
        return zakaznik;
    }

    public void setZakaznik(Company zakaznik) {
        this.zakaznik = zakaznik;
    }

    public int getZakaznikID() {
        return zakaznikID;
    }

    public void setZakaznikID(int zakaznikID) {
        this.zakaznikID = zakaznikID;
    }

    public Date getDatumZalozeni() {
        return datumZalozeni;
    }

    public void setDatumZalozeni(Date datumZalozeni) {
        this.datumZalozeni = datumZalozeni;
    }

    public Date getDatumUkonceni() {
        return datumUkonceni;
    }

    public void setDatumUkonceni(Date datumUkonceni) {
        this.datumUkonceni = datumUkonceni;
    }

    public int getAktHodin() {
        return aktHodin;
    }

    public void setAktHodin(int aktHodin) {
        this.aktHodin = aktHodin;
    }

    public int getBudgetHodin() {
        return budgetHodin;
    }

    public void setBudgetHodin(int budgetHodin) {
        this.budgetHodin = budgetHodin;
    }

    public int getAktCena() {
        return aktCena;
    }

    public void setAktCena(int aktCena) {
        this.aktCena = aktCena;
    }

    public int getBudgetCena() {
        return budgetCena;
    }

    public void setBudgetCena(int budgetCena) {
        this.budgetCena = budgetCena;
    }

    public projektStav getStav() {
        return stav;
    }

    public void setStav(projektStav stav) {
        this.stav = stav;
    }
}
