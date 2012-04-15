package ge.tsu.java.JavaDBExample.ui;

import ge.tsu.java.JavaDBExample.Database;
import ge.tsu.java.JavaDBExample.IMyFactory;
import ge.tsu.java.JavaDBExample.MyFactoryImpl;
import ge.tsu.java.JavaDBExample.model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyEditDialog extends JDialog{
    private JButton updateButton = new JButton("Update");
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

    public MyEditDialog(Student student) {
        setTitle("Record edition dialog.");
        init(student);
    }

    public void init(Student student) {
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());

        id.setEnabled(false);
        id.setText(String.valueOf(student.getId()));
        
        fields.add(idLabel, 0);
        fields.add(id, 1);

        firstname.setText(student.getFirstname());
        fields.add(nameLabel, 2);
        fields.add(firstname, 3);

        lastname.setText(student.getLastname());
        fields.add(lastnameLabel, 4);
        fields.add(lastname, 5);

        email.setText(student.getEmail());
        fields.add(emailLabel, 6);
        fields.add(email);


        buttons.add(updateButton);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean result = false;
                try {
                    result = Database.updateRecord(new Student(Integer.parseInt(id.getText()), firstname.getText(), lastname.getText(), email.getText()));
                    if (result) {
                        Database.loadData(factory.getMyTableModel());
                        factory.getMyMainFrame().enableButtons();
                        dispose();
                    }
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
