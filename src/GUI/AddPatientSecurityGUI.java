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
 * Created by Roland on 01/03/2015.
 */
public class AddPatientSecurityGUI extends JFrame implements ActionListener{
    private  int answer=0,selection;

    JButton confirm;
    JButton cancel,add;

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

    public AddPatientSecurityGUI(int selectionIn) {
        selection=selectionIn;
        f = new JFrame("Security");
        f.setLayout(new FlowLayout());
        f.setSize(420, 310);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);



        JPanel clock= new JPanel(new FlowLayout(FlowLayout.LEFT));
        f.add(clock,BorderLayout.EAST);




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

        // Cancel button
        add = new JButton("Add New");
        add.addActionListener(this);
        bottom.add(add);

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

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(cancel))
        {
            f.setVisible(false);

        } else if (e.getSource().equals(confirm))
        {boolean test=true;
            try {
                while (test) {
                    Security security = new Security(selection, Integer.parseInt(field2.getText()));
//            answer= security.getAnswer();
                    field1.setText("");
                    field2.setText("");

                    f.setVisible(false);
                    test = false;
                }
            }catch(NumberFormatException im){
                field2.setText("");
                System.out.println(im);
            }
        } else if (e.getSource().equals(add)){
            AddPatientGUI addPatientGUI=new AddPatientGUI();
        }
    }
}
