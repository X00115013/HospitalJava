package GUI;

import Model.Security;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Thomas Murray on 24/04/2015.
 */
public class TabSecurity extends JFrame implements ActionListener {
    JButton confirm;
    JButton cancel;
    JLabel patientNum;
    JLabel password;
    JLabel title;
    JTextField passwordText;
    JFrame f;
    boolean testField=false;

    public TabSecurity() {
        f = new JFrame("Security");
        f.setLayout(new FlowLayout());
        f.setSize(420, 220);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);



        JPanel clock= new JPanel(new FlowLayout(FlowLayout.LEFT));
        f.add(clock,BorderLayout.EAST);

        JPanel offTop= new JPanel(new FlowLayout(FlowLayout.CENTER));
        f.add(offTop,BorderLayout.NORTH);


        title = new JLabel("SECURITY");
        offTop.add(title);
        title.setFont(new Font("Arial", Font.BOLD, 44));



        JPanel middle=new JPanel();
        f.add(middle,BorderLayout.CENTER);
        middle.setLayout(new GridBagLayout());

        //Password
        password = new JLabel("Admin/Medical Password");
        middle.add(password, getConstraints(0, 2, 1, 1, GridBagConstraints.WEST));
        //text field
        passwordText = new JTextField(30);
        middle.add(passwordText, getConstraints(0, 3, 1, 1, GridBagConstraints.WEST));


        JPanel bottom= new JPanel(new FlowLayout(FlowLayout.LEFT));
        f.add(bottom,BorderLayout.SOUTH);

        // Confirm button
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        bottom.add(confirm);


        // Cancel button
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        bottom.add(cancel);

        f.setVisible(true);
    }

    private GridBagConstraints getConstraints(int gridx, int gridy, int gridwidth, int gridheight, int anchor) {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
        c.anchor = anchor;
        return c;
    }

    public boolean isTest() {
        return testField;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cancel)) {
            f.setVisible(false);

        } else if (e.getSource().equals(confirm)) {
            if (passwordText.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Password is a Required Field");
            } else {
                try {
                    int choice = Integer.parseInt(passwordText.getText());
                    if (choice == 789) {
                        testField=true;
                        System.out.println("In security "+testField);
                        f.setVisible(false);
                    }else{
                        passwordText.setText("");
                        JOptionPane.showMessageDialog(null, "Password is Incorrect");

                    }
                } catch (NumberFormatException im) {
                    JOptionPane.showMessageDialog(null, "Password must be Numeric");
                    passwordText.setText("");
                }
            }
        }
    }
}
