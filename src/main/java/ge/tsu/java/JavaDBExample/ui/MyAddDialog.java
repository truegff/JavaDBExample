package ge.tsu.java.JavaDBExample.ui;

import ge.tsu.java.JavaDBExample.Database;
import ge.tsu.java.JavaDBExample.IMyFactory;
import ge.tsu.java.JavaDBExample.MyFactoryImpl;
import ge.tsu.java.JavaDBExample.model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyAddDialog extends JDialog{
    private JButton addButton = new JButton("Add");
    private JButton cancelButton = new JButton("Cancel");
    private JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel fields = new JPanel(new GridLayout(4,2));

    private JLabel idLabel = new JLabel("ID");
    private JTextField id = new JTextField();
    private JLabel nameLabel = new JLabel("FIRSTNAME");
    private JTextField firstname = new JTextField();
    private JLabel lastnameLabel = new JLabel("LASTNAME");
    private JTextField lastname = new JTextField();
    private JLabel emailLabel = new JLabel("EMAIL");
    private JTextField email = new JTextField();

    IMyFactory factory = new MyFactoryImpl();
    
    public MyAddDialog() {
        setTitle("Record addition dialog.");
        init();
    }

    public void init () {
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());

        id.setEnabled(false);
        
        fields.add(idLabel, 0);
        fields.add(id, 1);
        fields.add(nameLabel, 2);
        fields.add(firstname, 3);
        fields.add(lastnameLabel, 4);
        fields.add(lastname, 5);
        fields.add(emailLabel, 6);
        fields.add(email);


        buttons.add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean result = false;
                try {
                    result = Database.addRecord(new Student(0, firstname.getText(), lastname.getText(),  email.getText()));
                    if (result) Database.loadData(factory.getMyTableModel());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        buttons.add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                factory.getMyMainFrame().enableButtons();
                dispose();
            }
        });
        
        add(buttons, BorderLayout.SOUTH);
        add(fields, BorderLayout.CENTER);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                factory.getMyMainFrame().enableButtons();
            }
        });
        setMinimumSize(new Dimension(400, 150));
        pack();
    }
}
