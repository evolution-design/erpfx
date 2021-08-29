package Data;

import java.util.Calendar;

public class Todo {
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
}
