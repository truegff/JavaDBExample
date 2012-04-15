package ge.tsu.java.JavaDBExample.listener;

import ge.tsu.java.JavaDBExample.IMyFactory;
import ge.tsu.java.JavaDBExample.MyFactoryImpl;
import ge.tsu.java.JavaDBExample.model.Student;
import ge.tsu.java.JavaDBExample.ui.MyEditDialog;

import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrameEditButtonListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        IMyFactory factory = new MyFactoryImpl();
        int selectedRow = factory.getMyMainFrame().getTable().getSelectedRow();
        if (selectedRow!=-1) {
            factory.getMyMainFrame().disableButtons();
            TableModel tm = factory.getMyTableModel();
            Student current = new Student(
                    ((Integer)tm.getValueAt(selectedRow,0)).intValue(),
                    (String)tm.getValueAt(selectedRow,1),
                    (String)tm.getValueAt(selectedRow,2),
                    (String)tm.getValueAt(selectedRow,3));
            MyEditDialog editDialog = new MyEditDialog(current);
            editDialog.setVisible(true);
        }
    }
}
