package Forms;

import javax.swing.*;


public class ProjektFormControler extends JDialog {
    private JPanel contentPane;
    private JTextField tfZakaznik;
    private JTextField tfPSC;
    private JButton newCustomerButton;
    private JTextField tfStreet;
    private JTextField tfTown;
    private JFormattedTextField tfICO;
    private JTextField tfZastupceJmeno;
    private JTextField tfZastupceTitul;
    private JTextField tfZastupcePrijmeni;
    private JTextField textField1;
    private JTextArea popisTextArea;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox comboBox1;
    private JTextField textField5;
    private JButton zrušitButton;
    private JButton uložitButton;
    private JButton aresButton;
    private JPanel mistoProvedeni;

    public ProjektFormControler() {

//        aresButton.addActionListener(this);
        this.setContentPane(this.contentPane);
        System.out.println("Dialog");
        setContentPane(contentPane);
        setModal(true);
        contentPane.setVisible(true);
        this.pack();
        this.setVisible(true);


    }


}
