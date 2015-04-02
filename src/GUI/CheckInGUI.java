package GUI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Lionhart and Thomas Murray on 20/03/2015.
 */
public class CheckInGUI extends JFrame implements ActionListener
{
    JButton confirm,checkOut;
    JButton cancel;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label5;
    JLabel label6;
    JLabel label12;
    JLabel label13;
    JLabel label14;
    JLabel label15;

    JTextField field1;
    JTextField field2;
    JTextField field3;
    JTextField field11;
    JTextField field12;
    JTextField field13;
    JFrame f;
    private int securityAnsIn;

    public CheckInGUI()
    {
        f = new JFrame();
        f.setTitle("Check-In");
        f.setSize(600, 800);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        Border loweredBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);

        JPanel holder=new JPanel(new GridLayout(3,1));
        Clock.DigitalClock clockD=new Clock.DigitalClock();
        JPanel clock = new JPanel(new FlowLayout(FlowLayout.LEFT));
        clock.add(clockD);
        label5 = new JLabel("Check-In");
        clock.add(label5);
        label5.setFont(new Font("Arial", Font.BOLD, 38));
        holder.add(clock);

        JPanel test2=new JPanel(new FlowLayout(FlowLayout.LEFT));

        //middle
        JPanel middle = new JPanel(new GridBagLayout());
        test2.add(middle, BorderLayout.WEST);
        //labels
        label1 = new JLabel("Appointment Number");
        middle.add(label1, getConstraints(0, 0, 1, 1, (GridBagConstraints.WEST)));
        //text field
        field1 = new JTextField(50);
        field1.setBorder(loweredBorder);
        label6 = new JLabel("OR");
        middle.add(label6, getConstraints(0, 2, 1, 1, (GridBagConstraints.WEST)));
        middle.add(field1, getConstraints(0, 1, 1, 1, (GridBagConstraints.WEST)));
        label2 = new JLabel("First Name");
        middle.add(label2, getConstraints(0, 3, 1, 1, (GridBagConstraints.WEST)));
        //text field
        field2 = new JTextField(50);
        field2.setBorder(loweredBorder);
        middle.add(field2, getConstraints(0, 4, 1, 1, (GridBagConstraints.WEST)));
        label3 = new JLabel("Surname");
        middle.add(label3, getConstraints(0, 5, 1, 1, (GridBagConstraints.WEST)));
        //text field
        field3 = new JTextField(50);
        field3.setBorder(loweredBorder);
        middle.add(field3, getConstraints(0, 6, 1, 1, (GridBagConstraints.WEST)));
        holder.add(test2);

        //DOB labels
        JPanel test =new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel dobs = new JPanel(new GridBagLayout());
        test.add(dobs, BorderLayout.SOUTH);
        label12 = new JLabel("Patient DOB");
        dobs.add(label12, getConstraints(0, 0, 1, 1, GridBagConstraints.WEST));
        label13 = new JLabel("Day");
        dobs.add(label13, getConstraints(0, 2, 1, 1, GridBagConstraints.WEST));
        label14 = new JLabel("Month");
        dobs.add(label14, getConstraints(1, 2, 1, 1, GridBagConstraints.WEST));
        label15 = new JLabel("Year");
        dobs.add(label15, getConstraints(2, 2, 1, 1, GridBagConstraints.WEST));

        //DOB text fields
        field11 = new JTextField(5);
        field11.setBorder(loweredBorder);
        dobs.add(field11, getConstraints(0, 3, 1, 1, GridBagConstraints.WEST));
        field12 = new JTextField(5);
        field12.setBorder(loweredBorder);
        dobs.add(field12, getConstraints(1, 3, 1, 1, GridBagConstraints.WEST));
        field13 = new JTextField(5);
        field13.setBorder(loweredBorder);
        dobs.add(field13, getConstraints(2, 3, 1, 1, GridBagConstraints.WEST));
        holder.add(test);

        // Confirm button
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        dobs.add(confirm, getConstraints(0, 4, 1, 1, GridBagConstraints.WEST));

        // Cancel button
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        dobs.add(cancel, getConstraints(1, 4, 1, 1, GridBagConstraints.WEST));

        // CheckOut button
        checkOut = new JButton("Check-Out");
        checkOut.addActionListener(this);
        dobs.add(checkOut, getConstraints(2, 4, 1, 1, GridBagConstraints.WEST));

        f.add(holder);
        f.setVisible(true);
    }




    private GridBagConstraints getConstraints(int gridx, int gridy, int gridwidth, int gridheight, int anchor) {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 5, 10, 10);
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
        else if (e.getSource().equals(checkOut))
        {
        SecurityGUI securityGUI=new SecurityGUI(10);
        }
    }
}
