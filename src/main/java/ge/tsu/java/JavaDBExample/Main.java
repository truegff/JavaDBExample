package ge.tsu.java.JavaDBExample;

import ge.tsu.java.JavaDBExample.listener.MainFrameAddButtonActionListener;
import ge.tsu.java.JavaDBExample.listener.MainFrameDeleteButtonActionListener;
import ge.tsu.java.JavaDBExample.ui.MyMainFrame;

import java.sql.Connection;

public class Main {
    public static void main(String... args) throws Exception {
        Connection connection = Database.getConection();
        DerbyTest derbyTest = new DerbyTest(connection);

        IMyFactory factory = new MyFactoryImpl();
                
        MyMainFrame frame = factory.getMyMainFrame();

        Database.loadData(factory.getMyTableModel());

        frame.pack();
        frame.setVisible(true);

        connection.close();
    }
}
