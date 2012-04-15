package ge.tsu.java.JavaDBExample.ui;

import ge.tsu.java.JavaDBExample.Database;
import ge.tsu.java.JavaDBExample.IMyFactory;
import ge.tsu.java.JavaDBExample.MyFactoryImpl;
import ge.tsu.java.JavaDBExample.listener.MainFrameAddButtonActionListener;
import ge.tsu.java.JavaDBExample.listener.MainFrameDeleteButtonActionListener;
import ge.tsu.java.JavaDBExample.listener.MainFrameEditButtonListener;
import ge.tsu.java.JavaDBExample.model.MyTableModel;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MyMainFrame extends JFrame{
    JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    JPanel jbuttonpanel = new JPanel();
    JScrollPane scrollPane = new JScrollPane();
    JTable jTable = new JTable();
    
    IMyFactory factory = new MyFactoryImpl();

    MyTableModel tableModel;

    JButton addButton = new JButton("Add...");
    JButton deleteButton = new JButton("Delete");
    JButton editButton = new JButton("Edit...");

    public MyMainFrame() {
        super("MyMainFrame");
        init();
    }

    public void init() {
        this.add(jsp);

        jsp.setRightComponent(scrollPane);
        jsp.setLeftComponent(jbuttonpanel);
        jTable.setModel(factory.getMyTableModel());
        scrollPane.setViewportView(jTable);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addButton.addActionListener(new MainFrameAddButtonActionListener());
        jbuttonpanel.add(addButton);

        deleteButton.addActionListener(new MainFrameDeleteButtonActionListener());
        jbuttonpanel.add(deleteButton);

        editButton.addActionListener(new MainFrameEditButtonListener());
        jbuttonpanel.add(editButton);
        
        this.setMinimumSize(new Dimension(800, 600));
        this.setSize(800, 600);
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JTable getTable() {
        return jTable;
    }

    public void disableButtons() {
        addButton.setEnabled(false);
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);
    }

    public void enableButtons() {
        addButton.setEnabled(true);
        editButton.setEnabled(true);
        deleteButton.setEnabled(true);
    }
}
