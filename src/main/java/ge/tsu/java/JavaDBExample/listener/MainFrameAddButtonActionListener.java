package ge.tsu.java.JavaDBExample.listener;

import ge.tsu.java.JavaDBExample.IMyFactory;
import ge.tsu.java.JavaDBExample.MyFactoryImpl;
import ge.tsu.java.JavaDBExample.ui.MyAddDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrameAddButtonActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        IMyFactory factory = new MyFactoryImpl();
        MyAddDialog dialog = factory.getMyAddDialog();
        factory.getMyMainFrame().disableButtons();
        dialog.setVisible(true);
    }
}
