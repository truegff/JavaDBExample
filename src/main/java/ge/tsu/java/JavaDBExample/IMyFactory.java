package ge.tsu.java.JavaDBExample;

import ge.tsu.java.JavaDBExample.model.MyTableModel;
import ge.tsu.java.JavaDBExample.ui.MyAddDialog;
import ge.tsu.java.JavaDBExample.ui.MyEditDialog;
import ge.tsu.java.JavaDBExample.ui.MyMainFrame;

public interface IMyFactory {
    MyMainFrame getMyMainFrame();
    MyAddDialog getMyAddDialog();
    MyTableModel getMyTableModel();
}
