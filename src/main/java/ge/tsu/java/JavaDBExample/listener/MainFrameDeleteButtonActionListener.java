package ge.tsu.java.JavaDBExample.listener;

import ge.tsu.java.JavaDBExample.Database;
import ge.tsu.java.JavaDBExample.IMyFactory;
import ge.tsu.java.JavaDBExample.MyFactoryImpl;
import ge.tsu.java.JavaDBExample.model.MyTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainFrameDeleteButtonActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        IMyFactory factory = new MyFactoryImpl();
        MyTableModel myTableModel = factory.getMyTableModel();

        JTable table = factory.getMyMainFrame().getTable();
        if (table.getSelectedRows().length > 0) {
            for (int i : table.getSelectedRows()) {
                try {
                    Database.deleteRecord((Integer) myTableModel.getValueAt(i, 0));
                } catch (SQLException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }

            try {
                Database.loadData(myTableModel);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
