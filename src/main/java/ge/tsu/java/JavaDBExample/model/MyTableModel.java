package ge.tsu.java.JavaDBExample.model;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MyTableModel extends AbstractTableModel{

    private String[] columnNames = {"ID", "FirstName", "LastName", "EMail"};
    private Object[][] data = new Object[][]{{null,null,null,null}};

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex]=aValue;
    }
    
    public void readResultSet(ResultSet rs) throws SQLException {
        ArrayList<Object[]> arr = new ArrayList<Object[]>();
        while (rs.next()) {
            arr.add(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)});
        }
        data = new Object[arr.size()][4];
        for (int i=0; i<arr.size(); i++) {
            setValueAt(arr.get(i)[0], i, 0);
            setValueAt(arr.get(i)[1], i, 1);
            setValueAt(arr.get(i)[2], i, 2);
            setValueAt(arr.get(i)[3], i, 3);
        }
        arr = null;
        fireTableDataChanged();
    }
    
    
}
