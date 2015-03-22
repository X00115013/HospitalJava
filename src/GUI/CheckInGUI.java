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
public class CheckInGUI extends JFrame implements ActionListener
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

    JTextField field1;
    JTextField field2;
    JTextField field3;
    JTextField field4;
    JTextField field5;
    JTextField field6;
    JTextField field11;
    JTextField field12;
    JTextField field13;
    JTextField field14;
    JFrame f;

    public CheckInGUI()
    {
        f = new JFrame();
        f.setTitle("Check-In");
        f.setLayout(new GridLayout(5,1,1,1));

        f.setSize(600, 700);
        f.setResizable(true);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Border loweredBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);

        JPanel offTop = new JPanel(new GridBagLayout());
        f.add(offTop, BorderLayout.PAGE_START);


        //Patient Clock label
        label4 = new JLabel("CLOCK");
        offTop.add(label4, getConstraints(0, 0, 1, 1, (GridBagConstraints.PAGE_START)));
        //app title

        //app no




        label5 = new JLabel("Check-In");
        offTop.add(label5, getConstraints(6, 0, 1, 1, (GridBagConstraints.NORTH)));
        label5.setFont(new Font("Arial", Font.BOLD, 38));

        //middle
        JPanel middle = new JPanel(new GridBagLayout());
        f.add(middle,BorderLayout.CENTER);

        //labels
        label1 = new JLabel("Appointment Number");
        middle.add(label1, getConstraints(0, 0, 1, 1, (GridBagConstraints.LINE_START)));
        //text field
        field1 = new JTextField(30);
        field1.setBorder(loweredBorder);
        label6 = new JLabel("OR");
        middle.add(label6, getConstraints(0, 2, 1, 1, (GridBagConstraints.LINE_START)));
        middle.add(field1, getConstraints(0, 1, 1, 1, (GridBagConstraints.LINE_START)));
        label2 = new JLabel("First Name");
        middle.add(label2, getConstraints(0, 3, 1, 1, (GridBagConstraints.LINE_START)));
        //text field
        field2 = new JTextField(30);
        field2.setBorder(loweredBorder);
        middle.add(field2, getConstraints(0, 4, 1, 1, (GridBagConstraints.LINE_START)));
        label3 = new JLabel("Surname");
        middle.add(label3, getConstraints(0, 5, 1, 1, (GridBagConstraints.LINE_START)));
        //text field
        field3 = new JTextField(30);
        field3.setBorder(loweredBorder);
        middle.add(field3, getConstraints(0, 6, 1, 1, (GridBagConstraints.LINE_START)));

        //DOB labels
        JPanel dobs = new JPanel(new GridBagLayout());
        f.add(dobs,BorderLayout.SOUTH);
        label12 = new JLabel("Patient Date Of Birth");
        dobs.add(label12, getConstraints(0, 13, 1, 1, GridBagConstraints.LINE_START));
        label13 = new JLabel("Day");
        dobs.add(label13, getConstraints(0, 14, 1, 1, GridBagConstraints.LINE_START));
        label14 = new JLabel("Month");
        dobs.add(label14, getConstraints(1, 14, 1, 1, GridBagConstraints.LINE_START));
        label15 = new JLabel("Year");
        dobs.add(label15, getConstraints(2, 14, 1, 1, GridBagConstraints.LINE_START));

        //DOB text fields
        field11 = new JTextField(5);
        field11.setBorder(loweredBorder);
        dobs.add(field11, getConstraints(0, 15, 1, 1, GridBagConstraints.LINE_START));
        field12 = new JTextField(5);
        field12.setBorder(loweredBorder);
        dobs.add(field12, getConstraints(1, 15, 1, 1, GridBagConstraints.LINE_START));
        field13 = new JTextField(5);
        field13.setBorder(loweredBorder);
        dobs.add(field13, getConstraints(2, 15, 1, 1, GridBagConstraints.LINE_START));


        //bottom
        JPanel bottom = new JPanel(new FlowLayout());
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


    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(cancel))
        {
            System.exit(0);
        }
        else if (e.getSource().equals(confirm))
        {

        }
    }
}
