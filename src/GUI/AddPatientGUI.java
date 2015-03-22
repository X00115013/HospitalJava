package GUI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Lionhart on 20/03/2015.
 */
public class AddPatientGUI extends JFrame implements ActionListener
{
    JButton confirm;
    JButton cancel;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;
    JLabel label6;
    JLabel label7;
    JLabel label8;
    JLabel label11;
    JLabel label12;
    JLabel label13;
    JLabel label14;
    JLabel label15;
    JRadioButton male;
    JRadioButton female;
    JTextField field1;
    JTextField field2;
    JTextField field3;
    JTextField field4;
    JTextField field5;
    JTextField field6;
    JTextField field7;
    JTextField field11;
    JTextField field12;
    JTextField field13;
    JTextField field14;
    JFrame f;

    public AddPatientGUI()
    {
        f = new JFrame();
        f.setTitle("Add Patient Admin Record");
        f.setSize(600, 900);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        Border loweredBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        f.setLayout(new FlowLayout(FlowLayout.LEFT));


        Clock.DigitalClock clockD=new Clock.DigitalClock();


        JPanel clock = new JPanel(new FlowLayout(FlowLayout.LEFT));
        clock.add(clockD);
        label6 = new JLabel("Patient No.");
        clock.add(label6);
        field1 = new JTextField(5);
        label6.setLayout(new FlowLayout(FlowLayout.TRAILING));
        field1.setLayout(new FlowLayout(FlowLayout.RIGHT));
        clock.add(field1);
        f.add(clock, BorderLayout.PAGE_START);

        JPanel offTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        label5 = new JLabel("Add Patient Admin Record");
        offTop.add(label5);
        label5.setFont(new Font("Arial", Font.BOLD, 28));

        f.add(offTop, BorderLayout.NORTH);


        JPanel test2=new JPanel(new GridLayout(1,1));
        //middle
        JPanel middle = new JPanel(new GridBagLayout());
        label1 = new JLabel("Patient First Name");
        middle.add(label1, getConstraints(0, 0, 1, 1, (GridBagConstraints.WEST)));
        field2 = new JTextField(50);
        field2.setBorder(loweredBorder);
        middle.add(field2, getConstraints(0, 1, 1, 2, (GridBagConstraints.WEST)));

        label2 = new JLabel("Patient Surname");
        middle.add(label2, getConstraints(0, 3, 1, 1, (GridBagConstraints.WEST)));
        //text field
        field3 = new JTextField(50);
        field3.setBorder(loweredBorder);
        middle.add(field3, getConstraints(0, 4, 1, 2, (GridBagConstraints.WEST)));

        label3 = new JLabel("Patient Address");
        middle.add(label3, getConstraints(0, 6, 1, 1, (GridBagConstraints.WEST)));
        //text field
        field4 = new JTextField(50);
        field4.setBorder(loweredBorder);
        middle.add(field4, getConstraints(0, 7, 1, 2, (GridBagConstraints.WEST)));

        label4 = new JLabel("Patient Email");
        middle.add(label4, getConstraints(0, 9, 1, 1, (GridBagConstraints.WEST)));
        //text field
        field5 = new JTextField(50);
        field5.setBorder(loweredBorder);
        middle.add(field5, getConstraints(0, 10, 1, 2, (GridBagConstraints.WEST)));

        label5 = new JLabel("Patient Occupation");
        middle.add(label5, getConstraints(0, 12, 1, 1, (GridBagConstraints.WEST)));
        //text field
        field6 = new JTextField(50);
        field6.setBorder(loweredBorder);
        middle.add(field6, getConstraints(0, 13, 1, 2, (GridBagConstraints.WEST)));

        label6 = new JLabel("Patient Phone");
        middle.add(label6, getConstraints(0, 15, 1, 1, (GridBagConstraints.WEST)));
        //text field
        field7 = new JTextField(50);
        field7.setBorder(loweredBorder);
        middle.add(field7, getConstraints(0, 16, 1, 2, (GridBagConstraints.WEST)));
        test2.add(middle);
        f.add(test2);



        //DOB labels
        JPanel test =new JPanel(new GridLayout(2,1));
        JPanel dobs = new JPanel(new GridBagLayout());
        male = new JRadioButton("Male");
        male.addActionListener(this);
        dobs.add(male, getConstraints(0, 11, 1, 1, (GridBagConstraints.WEST)));
        female = new JRadioButton("Female");
        female.addActionListener(this);
        dobs.add(female, getConstraints(1, 11, 1, 1, (GridBagConstraints.WEST)));
        label12 = new JLabel("Patient Date Of Birth");
        dobs.add(label12, getConstraints(1, 13, 1, 1, GridBagConstraints.LINE_START));
        label13 = new JLabel("Day");
        dobs.add(label13, getConstraints(1, 14, 1, 1, GridBagConstraints.LINE_START));
        label14 = new JLabel("Month");
        dobs.add(label14, getConstraints(2, 14, 1, 1, GridBagConstraints.LINE_START));
        label15 = new JLabel("Year");
        dobs.add(label15, getConstraints(3, 14, 1, 1, GridBagConstraints.LINE_START));

        //DOB text fields
        field11 = new JTextField(5);
        field11.setBorder(loweredBorder);
        dobs.add(field11, getConstraints(1, 15, 1, 1, GridBagConstraints.LINE_START));
        field12 = new JTextField(5);
        field12.setBorder(loweredBorder);
        dobs.add(field12, getConstraints(2, 15, 1, 1, GridBagConstraints.LINE_START));
        field13 = new JTextField(5);
        field13.setBorder(loweredBorder);
        dobs.add(field13, getConstraints(3, 15, 1, 1, GridBagConstraints.LINE_START));
        test.add(dobs);
        //bottom

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        test.add(bottom);
        // Confirm button

        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        bottom.add(confirm);


        // Cancel button
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        bottom.add(cancel);
        f.add(test, BorderLayout.NORTH);

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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cancel)) {
            System.exit(0);

        } else if (e.getSource().equals(confirm)) {
        }
    }
}
