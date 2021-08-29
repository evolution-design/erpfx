package Forms;

import Data.Database;
import Data.ResultSetTableModel;
import Data.Task;


import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class TaskFormController extends AbstractCellEditor implements TableCellEditor, ActionListener, MouseListener {
    private JTable tbTasks = null;
    private JPanel panelTasks;
    private Database db;
    TableColumn statusColumn;
    JDialog dialog;
    JScrollPane scrollPane;
    Vector<Task> tsk = new Vector();
    //  DefaultTableModel model = new DefaultTableModel(null, columnNames);
    ResultSetTableModel result = null;
    String[] columnNames = {"Projekt",
            "Pracovník",
            "Popis",
            "Datum",
            "Status"};


    //    ArrayList<String> data = new ArrayList<String>();
    String dataRow[][] = {{"101", "Amit", "670000", "1", "2"},
            {"102", "Jai", "780000", "1", "2"},
            {"101", "Sachin", "700000", "1", "2"}};
    DefaultTableModel data = new DefaultTableModel(dataRow, columnNames);


    private void createUIComponents() {
        // TODO: place custom component creation code here
        //Create the scroll pane and add the table to it.
        //tbTasks.setModel(new MyTableModel());
        data.setColumnIdentifiers(columnNames);
        //data.setDataVector(tsk);
//        data.addRow(dataRow[1]);
        tbTasks = new JTable(data); //, columnNames
        //statusColumn = tbTasks.getColumnModel().getColumn(4);

        JComboBox statusBox = new JComboBox();
        statusBox.addItem("Zapsán  ");
        statusBox.addItem("Řešen");
        statusBox.addItem("Dokončen");

        //statusColumn.setCellEditor(new DefaultCellEditor(statusBox));

    }

    private void addComponents() {

//        Database db = null;
//
//        try {
//            db = new Database();
//        } catch (Exception e) {
//            e.getMessage();
//        }

        if (db != null) {
            result = db.getTaskTable();
        }

        if (result != null) {
            tbTasks.setModel(result);
            System.out.println("Data:" + result.getValueAt(1, 1));
        } else
            tbTasks.setModel(data);

        tbTasks.setBounds(30, 40, 200, 300);


    }

    private void setColumnNames() {

        // tbTasks.setTableHeader(new JTableHeader());
        // table.setTableHeader(tableHeader);
        //  tableHeader.setDefaultRenderer(this);

        for (int i = 0; i < columnNames.length; i++) {

            TableColumn tc = tbTasks.getColumnModel().getColumn(i);
            tc.setHeaderValue(columnNames[i]);

            DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
            dtcr.setHorizontalAlignment(SwingConstants.CENTER); //For Aligning the Elements of all columns to CENTER
            tc.setCellRenderer(dtcr);
        }
    }

    public TaskFormController(Database db) {
        this.db = db;

        addComponents();
        tbTasks.addMouseListener(this);
        setColumnNames();


//        db.setTaskTable(tsk.get());
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("popup");
        System.out.println("cmd2 => " + e.getActionCommand());
//        if (e.getActionCommand().equals()) {
//
//            System.out.println("cmd1 => " + e.getActionCommand());
//        }


    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return null;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        //PopupDialog pp = new PopupDialog();
        //pp.show();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    class MyTableModel extends AbstractTableModel {


        @Override
        public int getRowCount() {
            return 0;
        }

        @Override
        public int getColumnCount() {
            return 0;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return null;
        }
    }

    /*
     *   Simple dialog containing the actual editing component
     */
    class PopupDialog extends JDialog implements ActionListener {
        private JTextArea textArea;
        private String currentText = "";

        public PopupDialog() {
            super((Frame) null, "Change Description", true);

            textArea = new JTextArea(5, 20);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            KeyStroke keyStroke = KeyStroke.getKeyStroke("ENTER");
            textArea.getInputMap().put(keyStroke, "none");
            JScrollPane scrollPane = new JScrollPane(textArea);
            getContentPane().add(scrollPane);

            JButton cancel = new JButton("Cancel");
            cancel.addActionListener(this);
            JButton ok = new JButton("Ok");
            ok.setPreferredSize(cancel.getPreferredSize());
            ok.addActionListener(this);

            JPanel buttons = new JPanel();
            buttons.add(ok);
            buttons.add(cancel);
            getContentPane().add(buttons, BorderLayout.SOUTH);
            pack();

            getRootPane().setDefaultButton(ok);
        }

        public void setText(String text) {
            textArea.setText(text);
        }

        /*
         *   Save the changed text before hiding the popup
         */
        public void actionPerformed(ActionEvent e) {
            if ("Ok".equals(e.getActionCommand())) {
                currentText = textArea.getText();
            }

            textArea.requestFocusInWindow();
            setVisible(false);
        }
    }

}
