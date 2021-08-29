package com.erpfx;

import Data.Database;
import Forms.CloseableTabbedPane;
import Forms.ProjektSeznamFormControler;
import Forms.TaskFormController;
import Forms.ZalohaFormControler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
//import com.intellij.uiDesigner.core.GridConstraints;
//import com.intellij.uiDesigner.core.GridLayoutManager;
//import com.intellij.uiDesigner.core.Spacer;

import java.net.URL;
import java.util.ResourceBundle;


public class MainGuiController implements Initializable {
    @FXML
    private BorderPane Main;
    private Menu fileMenu;
    private Menu projektMenu;
    private Menu autoMenu;
    private Menu prodejMenu;
    private Menu ukolyMenu;

    private Pane paneCenter;
    private HBox boxBottom;
    private ListView list1;
    private TextField textField1;
    private Button tiskButton;
    private Button saveButton;
    private Button newButton;
    private Menu moznostMenu;
    private TaskFormController tsk;
    private ProjektSeznamFormControler ps;
    private Database db;
    @FXML
    private TabPane tabbedPane1;

    // Menu items
    MenuItem fileNastaveni;
    MenuItem fileZaloha;
    MenuItem fileKonec;


    MenuItem projektNovy;
    MenuItem projektSeznam;
    MenuItem projektKonec;

    MenuItem prodejFaktura;
    MenuItem prodejZakaznik;

    MenuItem autoKnihaJizd;
    MenuItem autoServis;

    MenuItem taskList;

    public static void main(String[] args) {
        MainGuiController mg = new MainGuiController();
    }


    public MainGuiController() {
        //JFrame Form = new JFrame("ERP");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            db = new Database();
        } catch (Exception e) {
            e.getMessage();
        }
        fileNastaveni = new MenuItem("Nastavení");
        fileZaloha = new MenuItem("Záloha");
        fileKonec = new MenuItem("Konec");


        projektNovy = new MenuItem("Nový projekt");
        projektSeznam = new MenuItem("Seznam");

        prodejFaktura = new MenuItem("Faktura");
        prodejZakaznik = new MenuItem("Zákazník");

        autoKnihaJizd = new MenuItem("Kniha jízd");
        autoServis = new MenuItem("Servis");

        taskList = new MenuItem("Seznam úkolů");

        //fileMenu.addActionListener(this);
//        fileNastaveni.setAction(e -> {
//        });
//        fileZaloha.addActionListener(this);
//          fileKonec.setOnAction((ActionEvent t) -> {
//              System.exit(0);
//          });
//
//        prodejFaktura.addActionListener(this);
//        prodejZakaznik.addActionListener(this);
//
//        projektNovy.addActionListener(this);
//        projektSeznam.addActionListener(this);
//
//        autoKnihaJizd.addActionListener(this);
//        autoServis.addActionListener(this);
//
//        taskList.addActionListener(this);

        fileMenu.getItems().add(fileZaloha);
        SeparatorMenuItem separator = new SeparatorMenuItem();
        fileMenu.getItems().add(separator);
        fileMenu.getItems().add(fileKonec);

        projektMenu.getItems().add(projektNovy);
        projektMenu.getItems().add(projektSeznam);

        prodejMenu.getItems().add(prodejFaktura);
        prodejMenu.getItems().add(prodejZakaznik);

        autoMenu.getItems().add(autoKnihaJizd);
        autoMenu.getItems().add(autoServis);

        ukolyMenu.getItems().add(taskList);


        fileZaloha.setOnAction((ActionEvent a)  -> {
            ZalohaFormControler ff = new ZalohaFormControler();
        });

        fileKonec.setOnAction((ActionEvent a) -> {
            System.exit(0);
        });

        prodejFaktura.setOnAction((ActionEvent a) -> {
            fakturaForm ff = new fakturaForm();
        });

        if (e.getActionCommand().equals(prodejZakaznik.getText())) {
            ZakaznikForm zk = new ZakaznikForm(db);
            System.out.println("cmd1 => " + e.getActionCommand());
        }

        if (e.getActionCommand().equals(taskList.getText())) {
            boolean isOpen = false;

            if (tsk == null) {
                tsk = new TaskFormController(db);
                if (tabbedPane1 == null) tabbedPane1 = new TabPane();
                tabbedPane1.getTabs().add(new Tab("Úkoly");
            } else {
                for (int i = 0; i < tabbedPane1.getTabs().size(); i++)
                    if (tabbedPane1.getTabs().get(i).equals("Úkoly")) isOpen = true;
                if (!isOpen) tabbedPane1.getTabs().add(new Tab("Úkoly");
            }

            System.out.println("cmd1 => " + e.getActionCommand());
        }

        if (e.getActionCommand().equals(projektNovy.getText())) {
            ProjektSeznamFormControler pf = new ProjektSeznamFormControler();
            System.out.println("cmd projekt => " + e.getActionCommand());
        }

        if (e.getActionCommand().equals(projektSeznam.getText())) {
            boolean isOpen = false;

            if (ps == null) {
                ps = new ProjektSeznamFormControler(db);
                if (tabbedPane1 == null) tabbedPane1 = new CloseableTabbedPane();
                tabbedPane1.("Seznam projektů", ps.$$$getRootComponent$$$());
            } else {
                for (int i = 0; i < tabbedPane1.getTabCount(); i++)
                    if (tabbedPane1.getTitleAt(i).equals("Seznam projektů")) isOpen = true;
                if (!isOpen) tabbedPane1.add("Seznam projektů", ps.$$$getRootComponent$$$());
            }

            System.out.println("cmd1 => " + e.getActionCommand());
        }


    }
}




