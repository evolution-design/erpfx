package Data;

public class Company {
    private String nazev;
    private String zastupceTitul;
    private String zastupceJmeno;
    private String zastupcePrijmeni;
    private String zkratka;

    private String ulice;
    private String mesto;
    private String psc;
    private String stat;
    private String ico;
    private String dic;
    private String ean;
    private long telefon;
    private long mobil;
    private String email;
    private String web;
    private boolean dph;
    private String banka;
    private String skupina;
    private String vztah;
    private String popis;

    public Company() {
        this.nazev = "";
        this.zastupceTitul = "";
        this.zastupceJmeno = "";
        this.zastupcePrijmeni = "";
        this.zkratka = "";
        this.ulice = "";
        this.mesto = "";
        this.psc = "";
        this.stat = "";
        this.ico = "";
        this.dic = "";
        this.ean = "";
//        this.telefon = new Double();
//        this.mobil = new Double();
        this.email = "";
        this.web = "";
//        this.dph = dph;
        this.banka = "";
        this.skupina = "";
        this.vztah = "";
    }

    public String getTitul() {
        return zastupceTitul;
    }

    public void setTitul(String titul) {
        this.zastupceTitul = titul;
    }

    public String getJmeno() {
        return zastupceJmeno;
    }

    public void setJmeno(String jmeno) {
        this.zastupceJmeno = jmeno;
    }

    public String getPrijmeni() {
        return zastupcePrijmeni;
    }


    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public void setPrijmeni(String prijmeni) {
        this.zastupcePrijmeni = prijmeni;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getZkratka() {
        return zkratka;
    }

    public void setZkratka(String zkratka) {
        this.zkratka = zkratka;
    }

    public String getUlice() {
        return ulice;
    }

    public void setUlice(String ulice) {
        this.ulice = ulice;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getPsc() {
        return psc;
    }

    public void setPsc(String psc) {
        this.psc = psc;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getDic() {
        return dic;
    }

    public void setDic(String dic) {
        this.dic = dic;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public long getTelefon() {
        return telefon;
    }

    public void setTelefon(long telefon) {
        this.telefon = telefon;
    }

    public long getMobil() {
        return mobil;
    }

    public void setMobil(long mobil) {
        this.mobil = mobil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public boolean isDph() {
        return dph;
    }

    public void setDph(boolean dph) {
        this.dph = dph;
    }

    public String getBanka() {
        return banka;
    }

    public void setBanka(String banka) {
        this.banka = banka;
    }

    public String getSkupina() {
        return skupina;
    }

    public void setSkupina(String skupina) {
        this.skupina = skupina;
    }

    public String getVztah() {
        return vztah;
    }

    public void setVztah(String vztah) {
        this.vztah = vztah;
    }

    public boolean isTitul() {
        return (!zastupceTitul.isEmpty());
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public boolean isEmpty(){
        return (ico.isEmpty() && nazev.isEmpty());
    }
}
