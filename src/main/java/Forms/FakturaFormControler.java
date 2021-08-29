package Forms;

import Data.Company;
import Data.faktura;
//import com.evolution.PdfDoc;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilCalendarModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;

public class FakturaFormControler extends JDialog implements ActionListener {
    private Button buttonOK;
    private Button buttonCancel;
    private TextField tfZakaznik;
    private TextArea taPopis;
    private TextField tfDocumentNumber;
    private TextField tfVariabil;
    private ComboBox comboBoxState;
    private TextField tfSleva;
    private TextField tfPrice_10DPH;
    private TextField tfPrice_15DPH;
    private TextField tfPrice_21DPH;
    private TextField tfPrice_0DPH;
    private TextField tfPSC;
    private Button newCustomerButton;
    private TextField tfStreet;
    private TextField tfTown;
    private TextField tfICO;
    private TextField tfDIC;
    private ComboBox cbDoklad;
    private TableView tablepolozky;
    private TextField tfStatus;
    private ProgressBar progressBarStatus;
    private Label label;
    private Label labelStav;
    private TextField tfDph_0;
    private TextField tfDph_10;
    private TextField tfDph_15;
    private TextField tfDph_21;
    private TextField tfPrice_0DPH_Sum;
    private TextField tfPrice_10DPH_Sum;
    private TextField tfPrice_15DPH_Sum;
    private TextField tfPrice_21DPH_Sum;
    private TextField tfPrice_Sum;
    private TextField tfPrice_DPH;
    private TextField tfPrice_DPH_Sum;
    private Button buttonSave;
    private TextField tfZastupcePrijmeni;
    private TextField tfZastupceTitul;
    private TextField tfZastupceJmeno;
    private Panel jpDatum;
    private faktura fakt = null;
    private UtilCalendarModel dateModelVystaveno;
    private UtilCalendarModel dateModelSplatnost;
    private UtilCalendarModel dateModelZdanPlneni;
    private UtilCalendarModel dateModelZauctovani;
    private DatePicker dpVystaveno;
    private DatePicker dpSplatnost;
    private DatePicker dpZdanPlneni;
    private DatePicker dpZauctovani;
    private TextField tfEAN;
//    private DatePanelImpl dpVystavenoPanel;
//    private DatePanelImpl dpSplatnostPanel;
//    private DatePanelImpl dpZdanPlneniPanel;
//    private DatePanelImpl dpZauctovaniPanel;
    private Company comp;

//    public boolean isFill() {
//        return (!tfZakaznik.getText().isEmpty() && !taPopis.getText().isEmpty() && !tfDocumentNumber.getText().isEmpty() &&
//                !tfVariabil.getText().isEmpty() && !tfSleva.getText().isEmpty());
//    }

    public FakturaFormControler() {
        fakt = new faktura();

        buttonCancel.addActionListener(this);
        buttonOK.addActionListener(this);


        this.pack();
        this.setVisible(true);


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });


        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        dateModelVystaveno.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //JSpinner s = (JSpinner) e.getSource();
                fakt.setVystaveno(dateModelVystaveno.getValue());
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


    }

    private void selectDate() {
        System.out.println("Zadej datum");


    }

    private void onOK() {
        // add your code here
        System.out.println("Číslo dokladu: " + tfDocumentNumber.getText());

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        getData();
        PdfDoc pd = new PdfDoc();
        pd.createFile(fakt);
        pd.viewPdf();
    }

    public static void main(String[] args) {
        fakturaForm dialog = new fakturaForm();


        dialog.pack();
        dialog.setVisible(true);
        // System.exit(0);
    }

    private void createUIComponents() {
        Properties i18nStrings = new Properties();
        Properties i18nStrings1 = new Properties();
        JFormattedTextField.AbstractFormatter af = new JFormattedTextField.AbstractFormatter() {
            private String datePattern = "dd.MM.yyyy";
            private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

            @Override
            public Object stringToValue(String text) throws ParseException {
                return dateFormatter.parseObject(text);
            }

            @Override
            public String valueToString(Object value) throws ParseException {
                if (value != null) {
                    Calendar cal = (Calendar) value;
                    return dateFormatter.format(cal.getTime());
                }
                return "";
            }
        };


        //       getContentPane().add(new Label("Ahoj"), BorderLayout.CENTER);
        dateModelVystaveno = new UtilCalendarModel();
        dateModelSplatnost = new UtilCalendarModel();
        dateModelZauctovani = new UtilCalendarModel();
        dateModelZdanPlneni = new UtilCalendarModel();


        dpVystavenoPanel = new JDatePanelImpl(dateModelVystaveno, i18nStrings);
        dpVystaveno = new DatePickerImpl(dpVystavenoPanel, af);
        dpVystaveno.addActionListener(new ActionListener() {
            //performed action
            public void actionPerformed(ActionEvent arg0) {
                //create frame new object  f
                final JFrame f = new JFrame();
                //set text which is collected by date picker i.e. set date
                fakt.setVystaveno((Calendar) dpVystaveno.getModel().getValue());
            }
        });


        dpSplatnostPanel = new JDatePanelImpl(dateModelSplatnost, i18nStrings);
        dpSplatnost = new JDatePickerImpl(dpSplatnostPanel, af);
        dpSplatnost.addActionListener(new ActionListener() {
            //performed action
            public void actionPerformed(ActionEvent arg0) {
                //create frame new object  f
                final JFrame f = new JFrame();
                //set text which is collected by date picker i.e. set date
                fakt.setSplatnost((Calendar) dpSplatnost.getModel().getValue());
            }
        });

        dpZauctovaniPanel = new JDatePanelImpl(dateModelZauctovani, i18nStrings);
        dpZauctovani = new JDatePickerImpl(dpZauctovaniPanel, af);
        dpZauctovani.addActionListener(this);

        dpZdanPlneniPanel = new JDatePanelImpl(dateModelZdanPlneni, i18nStrings);
        dpZdanPlneni = new JDatePickerImpl(dpZdanPlneniPanel, af);
        dpZdanPlneni.setName("Zdaneni");
        dpZdanPlneni.addActionListener(this);

    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        System.out.println("jmeno: " + e.getID());
        if (e.getActionCommand().equals(buttonOK.getText())) {
            onOK();
            System.out.println("Vystaven: " + fakt.getVystavenoStr());
        }
        if (e.getActionCommand().equals(buttonCancel.getText())) {
            onCancel();
        }

        if (e.getActionCommand().equals("Date selected")) {
            //selectDate();

            System.out.println("Den: " + dpVystaveno.getModel().getDay() + ". Měsíc: " + (dpVystaveno.getModel().getMonth() + 1) + ". Rok: " + dpVystaveno.getModel().getYear());


        }


    }


    public void getData() {
        Company cp = new Company();
        //if (!tfPrice_Sum.getText().isEmpty()) fakt.setZaklad_0DPH(Float.parseFloat(tfPrice_Sum.getText()));
        fakt.setTyp(String.valueOf(cbDoklad.getSelectedItem()));
        fakt.setNumber(tfDocumentNumber.getText());
        if (!tfVariabil.getText().isEmpty()) fakt.setVariabil(Double.parseDouble(tfVariabil.getText()));
        if (!taPopis.getText().isEmpty()) fakt.setPopis(new StringBuffer(this.taPopis.getText()));
        // DATUMY
        fakt.setVystaveno((Calendar) dpVystaveno.getModel().getValue());
        fakt.setSplatnost((Calendar) dpSplatnost.getModel().getValue());
        fakt.setZauctovani((Calendar) dpZauctovani.getModel().getValue());
        fakt.setZdanPlneni((Calendar) dpZdanPlneni.getModel().getValue());
        //System.out.println("Datum: " + fakt.getVystavenoStr() + "-- Číslo: " + fakt.getNumber());

        NumberFormat myNumForm = NumberFormat.getInstance(Locale.ENGLISH);
        // Zákazník
        cp.setNazev(tfZakaznik.getText());
        cp.setMesto(tfTown.getText());
        cp.setUlice(tfStreet.getText());
        cp.setPsc(tfPSC.getText());
        cp.setIco(tfICO.getText());
        cp.setDic(tfDIC.getText());
        cp.setTitul(tfZastupceTitul.getText());
        cp.setJmeno(tfZastupceJmeno.getText());
        cp.setPrijmeni(tfZastupcePrijmeni.getText());

        fakt.setZakaznik(cp);

        // peníze
        // základ
        fakt.setZaklad_0DPH(Float.parseFloat(tfPrice_0DPH.getText()));
        fakt.setZaklad_10DPH(Float.parseFloat(tfPrice_10DPH.getText()));
        fakt.setZaklad_15DPH(Float.parseFloat(tfPrice_15DPH.getText()));
        fakt.setZaklad_21DPH(Float.parseFloat(tfPrice_21DPH.getText()));

        // DPH
        //fakt.setDan_0DPH(Float.parseFloat(tfDph_0.getText()));
        fakt.setDan_10DPH(Float.parseFloat(tfDph_10.getText()));
        fakt.setDan_15DPH(Float.parseFloat(tfDph_15.getText()));
        fakt.setDan_21DPH(Float.parseFloat(tfDph_21.getText()));

        // Komplet
        fakt.setCena_dan_0DPH(Float.parseFloat(tfPrice_0DPH_Sum.getText()));
        fakt.setCena_dan_10DPH(Float.parseFloat(tfPrice_10DPH_Sum.getText()));
        fakt.setCena_dan_15DPH(Float.parseFloat(tfPrice_15DPH_Sum.getText()));
        fakt.setCena_dan_21DPH(Float.parseFloat(tfPrice_21DPH_Sum.getText()));

        // suma
        fakt.setCena_suma(Float.parseFloat(tfPrice_DPH_Sum.getText()));
    }


    public TextField getVariabil() {
        return tfVariabil;
    }

    public void setTextFieldVariabil(TextField textFieldVariabil) {
        this.tfVariabil = tfVariabil;
    }

    public ComboBox getComboBoxState() {
        return comboBoxState;
    }

    public void setComboBoxState(JComboBox comboBoxState) {
        comboBoxState = comboBoxState;
    }

    public TextField getTextFieldSleva() {
        return tfSleva;
    }

    public void setSleva(TextField tfSleva) {
        this.tfSleva = tfSleva;
    }

    public TextField getPrice_10DPH() {
        return tfPrice_10DPH;
    }

    public void setPrice_10DPH(TextField tfPrice_10DPH) {
        this.tfPrice_10DPH = tfPrice_10DPH;
    }

    public TextField get_15DPH() {
        return tfPrice_15DPH;
    }

    public void set_15DPH(TextField tf_15DPH) {
        this.tfPrice_15DPH = tf_15DPH;
    }

    public TextField get_21DPH() {
        return tfPrice_21DPH;
    }

    public void set_21DPH(TextField tf_21DPH) {
        this.tfPrice_21DPH = tf_21DPH;
    }

    public TextField getPrice_0DPH() {
        return tfPrice_0DPH;
    }

    public void setPrice_0DPH(TextField tfPrice_0DPH) {
        this.tfPrice_0DPH = tfPrice_0DPH;
    }

    public TextField getPSC() {
        return tfPSC;
    }

    public void setPSC(TextField tfPSC) {
        this.tfPSC = tfPSC;
    }

    public TextField getStreet() {
        return tfStreet;
    }

    public void setStreet(TextField tfStreet) {
        this.tfStreet = tfStreet;
    }

    public TextField getTown() {
        return tfTown;
    }

    public void setTown(TextField tfTown) {
        this.tfTown = tfTown;
    }

    public FormattedTextField getFormattedTextField2() {
        return tfICO;
    }

    public void setFormattedTextField2(FormattedTextField formattedtf2) {
        this.tfICO = formattedtf2;
    }

    public FormattedTextField getDIC() {
        return tfDIC;
    }

    public void setTextieldDIC(FormattedTextField tfDIC) {
        this.tfDIC = tfDIC;
    }

    public Object getZdanPlneni() {
        return dpZdanPlneni.getModel().getValue();
    }

    public void setZdanPlneni(Calendar zdanPlneni) {
        this.dpZdanPlneni.getModel().setDate(zdanPlneni.DAY_OF_MONTH, zdanPlneni.MONTH, zdanPlneni.YEAR);
        ;
    }

    public Object getZauctovani() {
        return this.dpZauctovani;
    }

    public void setZauctovani(Calendar zauctovani) {
        this.dpZauctovani.getModel().setDate(zauctovani.DAY_OF_MONTH, zauctovani.MONTH, zauctovani.YEAR);
    }

    public Object getSplatnost() {
        return this.dpSplatnost;
    }

    public void setSplatnost(Calendar splatnost) {
        this.dpSplatnost.getModel().setDate(splatnost.DAY_OF_MONTH, splatnost.MONTH, splatnost.YEAR);
    }

    public Object getVystaveno() {
        return dpVystaveno.getModel().getValue();
    }

    public void setVystaveno(Calendar vystaveno) {
        this.dpVystaveno.getModel().setDate(vystaveno.DAY_OF_MONTH, vystaveno.MONTH, vystaveno.YEAR);
    }

    public ComboBox getComboBox3() {
        return cbDoklad;
    }

    public void setComboBox3(ComboBox comboBox3) {
        this.cbDoklad = comboBox3;
    }

    public TableView getTablepolozky() {
        return tablepolozky;
    }

    public void setTablepolozky(TableView tablepolozky) {
        this.tablepolozky = tablepolozky;
    }

    public TextField getTextFeldStatus() {
        return tfStatus;
    }

    public void setStatus(TextField tfStatus) {
        this.tfStatus = tfStatus;
    }

    public ProgressBar getProgressBarStatus() {
        return progressBarStatus;
    }

    public void setProgressBarStatus(ProgressBar progressBarStatus) {
        this.progressBarStatus = progressBarStatus;
    }

    public TextField getDph_0() {
        return tfDph_0;
    }

    public void setDph_0(TextField tfDph_0) {
        this.tfDph_0 = tfDph_0;
    }

    public TextField getDph_10() {
        return tfDph_10;
    }

    public void setDph_10(TextField tfDph_10) {
        this.tfDph_10 = tfDph_10;
    }

    public TextField getDph_15() {
        return tfDph_15;
    }

    public void setDph_15(TextField tfDph_15) {
        this.tfDph_15 = tfDph_15;
    }

    public TextField getDph_21() {
        return tfDph_21;
    }

    public void setDph_21(TextField tfDph_21) {
        this.tfDph_21 = tfDph_21;
    }

    public TextField getTextFieldPrice_0DPH_Sum() {
        return tfPrice_0DPH_Sum;
    }

    public void setTextFieldPrice_0DPH_Sum(TextField tfPrice_0DPH_Sum) {
        this.tfPrice_0DPH_Sum = tfPrice_0DPH_Sum;
    }

    public TextField getTextFieldPrice_10DPH_Sum() {
        return tfPrice_10DPH_Sum;
    }

    public void setTextFieldPrice_10DPH_Sum(TextField tfPrice_10DPH_Sum) {
        this.tfPrice_10DPH_Sum = tfPrice_10DPH_Sum;
    }

    public TextField getTextFieldPrice_15DPH_Sum() {
        return tfPrice_15DPH_Sum;
    }

    public void setTextFieldPrice_15DPH_Sum(TextField tfPrice_15DPH_Sum) {
        this.tfPrice_15DPH_Sum = tfPrice_15DPH_Sum;
    }

    public TextField getTextFieldPrice_21DPH_Sum() {
        return tfPrice_21DPH_Sum;
    }

    public void setTextFieldPrice_21DPH_Sum(TextField tfPrice_21DPH_Sum) {
        this.tfPrice_21DPH_Sum = tfPrice_21DPH_Sum;
    }

    public TextField getTextFieldPrice_Sum() {
        return tfPrice_Sum;
    }

    public void setTextFieldPrice_Sum(TextField tfPrice_Sum) {
        this.tfPrice_Sum = tfPrice_Sum;
    }

    public TextField getTextFieldPrice_DPH() {
        return tfPrice_DPH;
    }

    public void setTextFieldPrice_DPH(TextField tfPrice_DPH) {
        this.tfPrice_DPH = tfPrice_DPH;
    }

    public TextField getTextFieldPrice_DPH_Sum() {
        return tfPrice_DPH_Sum;
    }

    public void setTextFieldPrice_DPH_Sum(TextField tfPrice_DPH_Sum) {
        this.tfPrice_DPH_Sum = tfPrice_DPH_Sum;
    }


}
