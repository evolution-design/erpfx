package Data;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class ResultSetTableModel extends AbstractTableModel {

    protected Vector columnHeaders;

    protected Vector tableData;

    public ResultSetTableModel(ResultSet rset) throws SQLException {
        Vector rowData;
        ResultSetMetaData rsmd = rset.getMetaData();
        int count = rsmd.getColumnCount();
        columnHeaders = new Vector(count);
        tableData = new Vector();
        for (int i = 1; i <= count; i++) {
            columnHeaders.addElement(rsmd.getColumnName(i));
        }
        while (rset.next()) {
            rowData = new Vector(count);
            for (int i = 1; i <= count; i++) {
                rowData.addElement(rset.getObject(i));
                System.out.println(i + " - " + rset.getObject(i));
            }
            tableData.addElement(rowData);
        }
    }

    public int getColumnCount() {
        return columnHeaders.size();
    }

    public int getRowCount() {
        return tableData.size();
    }

    public Object getValueAt(int row, int column) {
        Vector rowData = (Vector) (tableData.elementAt(row));
        return rowData.elementAt(column);
    }

    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public String getColumnName(int column) {
        return (String) (columnHeaders.elementAt(column));
    }

    public Vector getTableData() {
        return tableData;
    }
}
