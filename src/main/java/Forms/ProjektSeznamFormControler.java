package Forms;

import Data.Database;
import Data.ResultSetTableModel;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import javax.swing.*;


public class ProjektSeznamFormControler {
    private Pane contentPane;
    private TableView tbProjekty;
    private Button pridatButton;
    private Button ukoncitButton;
    private Database db;
    private ResultSetTableModel result = null;
    private String[] columnNames = {"Název", "Začátek", "Konec", "Akt. hodin", "Budget hodiny", "Budget cena"};
    String dataRow[][] = {{"101", "Amit", "670000", "1", "2", "2"},
            {"102", "Jai", "780000", "1", "2", "2"},
            {"101", "Sachin", "700000", "1", "2", "2"}};

//    DefaultTableModel data = new DefaultTableModel(dataRow, columnNames);
    TableView.TableViewSelectionModel data = tbProjekty.getSelectionModel();

    public ProjektSeznamFormControler(Database db) {
        this.db = db;


//        this.setContentPane(this.contentPane);
        System.out.println("Dialog");

        contentPane.setVisible(true);
//        aresButton.a

    }



    private void addComponents() {

        if (db != null) {
            result = db.getProjektTable();
        }

//        if (result != null) {
//            tbProjekty.setsetModel(result);
//            System.out.println("Data:" + result.getValueAt(1, 1));
//        } else
//            tbProjekty.setModel(data);
//
//        tbProjekty.setBounds(30, 40, 200, 300);


    }



}
