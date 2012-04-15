package ge.tsu.java.JavaDBExample;

import ge.tsu.java.JavaDBExample.model.MyTableModel;
import ge.tsu.java.JavaDBExample.ui.MyAddDialog;
import ge.tsu.java.JavaDBExample.ui.MyEditDialog;
import ge.tsu.java.JavaDBExample.ui.MyMainFrame;

public class MyFactoryImpl implements IMyFactory {
    private static MyMainFrame mainFrame;
    private static MyAddDialog myAddDialog;
    private static MyTableModel tableModel;


    @Override
    public MyMainFrame getMyMainFrame() {
        if (mainFrame==null) mainFrame=new MyMainFrame();
        return mainFrame;
    }

    @Override
    public MyAddDialog getMyAddDialog() {
        if (myAddDialog==null) myAddDialog=new MyAddDialog();
        return myAddDialog;
    }

    @Override
    public MyTableModel getMyTableModel() {
        if (tableModel==null) tableModel=new MyTableModel();
        return tableModel;
    }
}
