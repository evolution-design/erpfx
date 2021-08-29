package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {

    protected Connection connection;
    protected Statement statement;
    protected Connection conZakaznici;
    protected Statement statZakaznici;

    public Database() throws Exception {
        connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Lenovo\\IdeaProjects\\erp\\db\\mydatabase.db");
        statement = connection.createStatement();
        statement.setQueryTimeout(30);
    }


    public ResultSetTableModel getTaskTable() {

        try {

            ResultSet rset = statement.executeQuery("SELECT * FROM TaskTable");
            return (new ResultSetTableModel(rset));
        } catch (Exception e) {
            e.getMessage();
            return null;
        }

    }

    public ResultSetTableModel getTodoTable() {

        try {

            ResultSet rset = statement.executeQuery("SELECT * FROM rc_TodoTable");
            return (new ResultSetTableModel(rset));
        } catch (Exception e) {
            e.getMessage();
            return null;
        }

    }
    public ResultSetTableModel getProjektTable() {
        try {

            ResultSet rset = statement.executeQuery("SELECT * FROM ProjektTable");
            return (new ResultSetTableModel(rset));
        } catch (Exception e) {
            e.getMessage();
            return null;
        }

    }

    public int setTaskTable(Task ts) {

        try {

            Statement st = connection.createStatement();
            st.executeUpdate("INSERT INTO Customers " +
                    "VALUES (" + ts.getProjekt() + "," + ts.getPracovnik() + ", " + ts.getPopis() + ", " + ts.getDatum() +  "," + ts.getStav() + ")");
            System.out.println("INSERT INTO Customers " +
                    "VALUES (" + ts.getProjekt() + "," + ts.getPracovnik() + ", " + ts.getPopis() + ", " + ts.getDatum() +  "," + ts.getStav() + ")");
            return (1);
        } catch (Exception e) {
            e.getMessage();
            return 0;
        }

    }

    public int writeZakaznik(Company cm) {
        StringBuffer columns = new StringBuffer();
        StringBuffer values = new StringBuffer();

        columns.append("INSERT INTO Zakaznici  (");
        if(!cm.getNazev().isEmpty()) { columns.append("Název" + ", "); values.append("\"" + cm.getNazev() + "\", "); }
        if(!cm.getTitul().isEmpty()) { columns.append("ZastupceTitul" + ", "); values.append("\"" + cm.getTitul() + "\", "); }
        if(!cm.getJmeno().isEmpty()) { columns.append("ZastupceJmeno" + ", "); values.append("\"" + cm.getJmeno() + "\", "); }
        if(!cm.getPrijmeni().isEmpty()) { columns.append("ZastupcePrijmeni" + ", "); values.append("\"" + cm.getPrijmeni() + "\", "); }
        if(!cm.getZkratka() .isEmpty()) { columns.append("Zkratka" + ", "); values.append("\"" + cm.getZkratka() + "\", "); }
        if(!cm.getUlice().isEmpty()){ columns.append("Ulice" + ", "); values.append("\"" + cm.getUlice() + "\", "); }
        if(!cm.getMesto().isEmpty()) { columns.append("Mesto" + ", "); values.append("\"" + cm.getMesto() + "\", "); }
        if(!cm.getPsc().isEmpty()) { columns.append("PSC" + ", "); values.append("\"" + cm.getPsc() + "\", "); }
        if(!cm.getStat().isEmpty()) { columns.append("stat" + ", "); values.append("\"" + cm.getStat() + "\", "); }
        if(!cm.getIco().isEmpty()) { columns.append("ICO" + ", "); values.append("\"" + cm.getIco() + "\", "); }
        if(!cm.getIco().isEmpty()) { columns.append("DIC" + ", "); values.append("\"" + cm.getDic() + "\", "); }
        if(cm.getTelefon() != 0) { columns.append("telefon" + ", "); values.append(cm.getTelefon() + ", "); }
        if(cm.getMobil() != 0) { columns.append("mobil" + ", "); values.append(cm.getMobil() + ", "); }
        if(!cm.getEmail().isEmpty()) { columns.append("email" + ", "); values.append("\"" + cm.getEmail() + "\", "); }
        if(!cm.getWeb() .isEmpty()) { columns.append("web" + ", "); values.append("\"" + cm.getWeb() + "\", "); }
        columns.append("DPH" + ", "); values.append("\"" + cm.isDph() + "\", ");
        if(!cm.getBanka().isEmpty()) { columns.append("banka" + ", "); values.append("\"" + cm.getBanka() + "\", "); }
        if(!cm.getPopis().isEmpty()) { columns.append("popis" + ", "); values.append("\"" + cm.getPopis() + "\", "); }
        if(!cm.getSkupina().isEmpty()) { columns.append("skupina" + ", "); values.append("\"" + cm.getSkupina() + "\", "); }
        if(!cm.getVztah().isEmpty()) { columns.append("vztah"); values.append("\"" + cm.getVztah() + "\"" ); }
        columns.append(") VALUES (");
        columns.append(values.toString());
        columns.append(");");
        System.out.println(columns.toString());


        try {

            statement.executeQuery(columns.toString());

            return (1);
        } catch (Exception e) {
            e.getMessage();
            return 0;
        }
    }

    public ResultSetTableModel getZakazniciName(String name) {
        try {

            ResultSet rset = statement.executeQuery("SELECT Název FROM Zakaznici WHERE Název LIKE '%"+ name +"%' ");
            System.out.println("Vzsledek" + rset.getArray(1));
            return (new ResultSetTableModel(rset));
        } catch (Exception e) {
            e.getMessage();
            return null;
        }

    }

    public int writeFaktura(faktura fk) {
        StringBuffer columns = new StringBuffer();
        StringBuffer values = new StringBuffer();

        columns.append("INSERT INTO Zakaznici  (");
        if(!fk.getZakaznik().isEmpty()) { columns.append("Název" + ", "); values.append("\"" + fk.getZakaznik().getNazev() + "\", "); }

        columns.append(") VALUES (");
        columns.append(values.toString());
        columns.append(");");
        System.out.println(columns.toString());


        try {

            statement.executeQuery(columns.toString());

            return (1);
        } catch (Exception e) {
            e.getMessage();
            return 0;
        }

    }

}

