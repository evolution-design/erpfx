package Data;

import java.util.Calendar;

public class Task {
    private int projekt;
    private int pracovnik;
    private StringBuffer popis;
    private Calendar datum;
    private Status stav = Status.ZAPSAN;

    enum Status {
        ZAPSAN,
        RESENI,
        UKONCEN
    }

    public int getProjekt() {
        return projekt;
    }

    public void setProjekt(int projekt) {
        this.projekt = projekt;
    }

    public int getPracovnik() {
        return pracovnik;
    }

    public void setPracovnik(int pracovnik) {
        this.pracovnik = pracovnik;
    }

    public StringBuffer getPopis() {
        return popis;
    }

    public void setPopis(StringBuffer popis) {
        this.popis = popis;
    }

    public Calendar getDatum() {
        return datum;
    }

    public void setDatum(Calendar datum) {
        this.datum = datum;
    }

    public Status getStav() {
        return stav;
    }

    public void setStav(Status stav) {
        this.stav = stav;
    }




}
