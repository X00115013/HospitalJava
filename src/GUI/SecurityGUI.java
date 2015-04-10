package GUI;

import Model.Security;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.util.InputMismatchException;

/**
 * Created by Thomas Murray on 01/03/2015.
 */
public class SecurityGUI extends JFrame implements ActionListener {
    private int answer = 0, selection;

    JButton confirm;
    JButton cancel;

    JLabel patientNum;
    JLabel password;
    JLabel title;
    JTextField patientNumText, passwordText;

    JFrame f;

    public SecurityGUI(int selectionIn) {
        selection = selectionIn;
        f = new JFrame("Security");
        f.setLayout(new FlowLayout());
        f.setSize(420, 310);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);


        JPanel clock = new JPanel(new FlowLayout(FlowLayout.LEFT));
        f.add(clock, BorderLayout.EAST);


        JPanel offTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        f.add(offTop, BorderLayout.NORTH);


        title = new JLabel("SECURITY");
        offTop.add(title);
        title.setFont(new Font("Arial", Font.BOLD, 44));


        JPanel middle = new JPanel();
        f.add(middle, BorderLayout.CENTER);
        middle.setLayout(new GridBagLayout());

        //Patient Number
        patientNum = new JLabel("Patient Number");
        middle.add(patientNum, getConstraints(0, 0, 1, 1, GridBagConstraints.WEST));
        //text field
        patientNumText = new JTextField(30);
        middle.add(patientNumText, getConstraints(0, 1, 1, 1, GridBagConstraints.WEST));

        //Password
        password = new JLabel("Admin/Medical Password");
        middle.add(password, getConstraints(0, 2, 1, 1, GridBagConstraints.WEST));
        //text field
        passwordText = new JTextField(30);
        middle.add(passwordText, getConstraints(0, 3, 1, 1, GridBagConstraints.WEST));


        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        f.add(bottom, BorderLayout.SOUTH);

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

    public int getAnswer() {
        return answer;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cancel)) {
            f.setVisible(false);

        } else if (e.getSource().equals(confirm)) {
            boolean test = true;
            if (patientNumText.getText().equals("") || passwordText.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Both Fields Must Be Populated");
            } else {
                try {
                    int choice1=Integer.parseInt(patientNumText.getText());
                    int choice2=Integer.parseInt(passwordText.getText());
                    Security security = new Security(selection, choice1, choice2);
                    if (security.passCheck() == -1) {
                        passwordText.setText("");
                        JOptionPane.showMessageDialog(null, "Password is Incorrect");
                    } else if (security.patientNumCheck()) {
                    }
                    if (!security.patientNumCheck()) {
                        patientNumText.setText("");
                        passwordText.setText("");
                        JOptionPane.showMessageDialog(null, "Patient Does Not Exist");
                    } else{
                        f.setVisible(false);
                    }
                } catch (NumberFormatException im) {
                    patientNumText.setText("");
                    passwordText.setText("");
                    JOptionPane.showMessageDialog(null, "Both Files Must Be Numeric");
                    System.out.println(im);
                }
            }
        }
    }
}

