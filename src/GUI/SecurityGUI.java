package GUI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;

/**
 * Created by Roland on 01/03/2015.
 */
public class SecurityGUI extends JFrame implements ActionListener{
    JButton confirm;
    JButton cancel;

    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;
    JLabel label6;
    JLabel label7;


    JTextField field1;
    JTextField field2;
    JTextField field3;
    JFrame f;

    public SecurityGUI() {
        f = new JFrame("Security");
        f.setLayout(new FlowLayout());
        f.setSize(420, 350);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        JPanel clock= new JPanel(new FlowLayout(FlowLayout.LEFT));
        f.add(clock,BorderLayout.EAST);


        //Patient Phone label
        label5 = new JLabel("CLOCK");
        clock.add(label5);

        JPanel top=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        f.add(top,BorderLayout.WEST);
        //GO Location label
        label6 = new JLabel("GP Number");
        top.add(label6);
        //text field
        field3 = new JTextField(5);
        top.add(field3);

        JPanel offTop= new JPanel(new FlowLayout(FlowLayout.CENTER));
        f.add(offTop,BorderLayout.NORTH);
        //Reason for Visit label
        label7 = new JLabel("SECURITY");
        offTop.add(label7);
        label7.setFont(new Font("Arial",Font.BOLD, 44));



        JPanel middle=new JPanel();
        f.add(middle,BorderLayout.CENTER);
        middle.setLayout(new GridBagLayout());

        //Patient Number
        label1 = new JLabel("Patient Number");
        middle.add(label1, getConstraints(0, 0, 1, 1, GridBagConstraints.WEST));
        //text field
        field1 = new JTextField(30);
        middle.add(field1, getConstraints(0, 1, 1, 1, GridBagConstraints.WEST));

        //Password
        label2 = new JLabel("Admin/Medical Password");
        middle.add(label2, getConstraints(0, 2, 1, 1, GridBagConstraints.WEST));
        //text field
        field2 = new JTextField(30);
        middle.add(field2, getConstraints(0, 3, 1, 1, GridBagConstraints.WEST));


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
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(cancel))
        {
            System.exit(0);

        } else if (e.getSource().equals(confirm))
        {
            //unsure of action
        }
    }
}
