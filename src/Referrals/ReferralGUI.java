package GUI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Lionhart on 17/03/2015.
 */
public class ReferralGUI extends JFrame implements ActionListener {
    String[] list1 = {"med1                                                                                                                              :", "med2        ", "med3        "};
    String[] list2 = {"cons1                                                                                                                             :", "cons2       ", "cons3       "};
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
    JLabel label9;
    JLabel label10;
    JLabel label11;
    JLabel label12;
    JLabel label13;
    JLabel label14;
    JLabel label15;
    JLabel label16;
    JLabel label17;
    JLabel bgroundImg;


    JTextField field1;
    JTextField field2;
    JTextField field3;
    JTextField field4;
    JTextField field5;
    JTextField field13;
    JTextField field7;
    JTextField field8;
    JTextField field9;
    JTextField field10;
    JTextField field11;
    JTextField field12;

    JTextArea area1;
    JPanel top;
    JPanel top2;
    JPanel top3and4;
    JPanel top5;
    JPanel top6;
    JPanel top7;
    JPanel contentPane;

    JComboBox<String> combo1;
    JComboBox<String> combo2;
    JFrame f;

    public ReferralGUI() {
        f = new JFrame();
        setTitle("Referrals");
        f.setLayout(new GridLayout(2, 2));
        setLayout(new GridBagLayout());
        setSize(1100, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Border loweredBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);


       /* //Background image
        contentPane = new JPanel();
        contentPane.setOpaque(true);

        this.setContentPane(new JLabel(new ImageIcon("/recources/hospital.jpg")));
        f.setIconImage(new ImageIcon(getClass().getResource("Hospital/recources/hospital.jpg")).getImage());

        bgroundImg = new JLabel(new ImageIcon("Hospital/recources/hospital.jpg"));
        f.add(bgroundImg);
        setSize(1100, 1000);
*/

        //top panel


        label16 = new JLabel("Referrals");
        label16.setFont(new Font("Arial", Font.BOLD, 38));
        add(label16, getConstraints(0, 0, 0, 1, GridBagConstraints.NORTH));

        //GP Name label
        label1 = new JLabel("GP Full Name");
        add(label1, getConstraints(1, 1, 1, 1, GridBagConstraints.WEST));
        //text field
        field1 = new JTextField(40);
        field1.setBorder(loweredBorder);
        add(field1, getConstraints(1, 2, 1, 1, GridBagConstraints.WEST));

        //Patient Phone label
        label2 = new JLabel("Patient Phone");
        add(label2, getConstraints(2, 1, 1, 1, GridBagConstraints.WEST));
        //text field
        field2 = new JTextField(40);
        field2.setBorder(loweredBorder);
        add(field2, getConstraints(2, 2, 1, 1, GridBagConstraints.WEST));

        //GO Location label
        label3 = new JLabel("GP Location");
        add(label3, getConstraints(1, 3, 1, 1, GridBagConstraints.WEST));
        //text field
        field3 = new JTextField(40);
        field3.setBorder(loweredBorder);
        add(field3, getConstraints(1, 4, 1, 1, GridBagConstraints.WEST));

        //Reason for Visit label
        label4 = new JLabel("Reason For Visit");
        add(label4, getConstraints(2, 3, 1, 1, GridBagConstraints.WEST));
        //text field
        field4 = new JTextField(40);
        field4.setBorder(loweredBorder);
        add(field4, getConstraints(2, 4, 1, 1, GridBagConstraints.WEST));

        //Patient First Name label
        label5 = new JLabel("Patient First Name");
        add(label5, getConstraints(1, 5, 1, 1, GridBagConstraints.WEST));
        //text field
        field5 = new JTextField(40);
        field5.setBorder(loweredBorder);
        add(field5, getConstraints(1, 6, 1, 1, GridBagConstraints.WEST));

        //Recommendations label
        label6 = new JLabel("Recommendations");
        add(label6, getConstraints(2, 5, 1, 1, GridBagConstraints.WEST));
        //text field
        area1 = new JTextArea(3, 40);
        area1.setBorder(loweredBorder);
        add(area1, getConstraints(2, 6, 1, 1, GridBagConstraints.WEST));

        //Patient Surname label
        label7 = new JLabel("Patient Surname");
        add(label7, getConstraints(1, 7, 1, 1, GridBagConstraints.WEST));
        //text field
        field7 = new JTextField(40);
        field7.setBorder(loweredBorder);
        add(field7, getConstraints(1, 8, 1, 1, GridBagConstraints.WEST));

        //Patient Number label
        label8 = new JLabel("Patient Number");
        add(label8, getConstraints(1, 9, 1, 1, GridBagConstraints.WEST));
        //text field
        field8 = new JTextField(40);
        field8.setBorder(loweredBorder);
        add(field8, getConstraints(1, 10, 1, 1, GridBagConstraints.WEST));

        //Required Equipment label
        label9 = new JLabel("Medical Equipment Required");
        add(label9, getConstraints(2, 9, 1, 1, GridBagConstraints.WEST));
        //Combo-box
        combo1 = new JComboBox<String>(list1);
        add(combo1, getConstraints(2, 10, 1, 1, GridBagConstraints.WEST));

        //Patient Address label
        label10 = new JLabel("Patient Address");
        add(label10, getConstraints(1, 11, 1, 1, GridBagConstraints.WEST));
        //text field
        field10 = new JTextField(40);
        field10.setBorder(loweredBorder);
        add(field10, getConstraints(1, 12, 1, 1, GridBagConstraints.WEST));

        //Required Consultant label
        label11 = new JLabel("Consultant Type Required");
        add(label11, getConstraints(2, 11, 1, 1, GridBagConstraints.WEST));
        //Combo-Box
        combo2 = new JComboBox<String>(list2);
        add(combo2, getConstraints(2, 12, 1, 1, GridBagConstraints.WEST));


        //DOB labels
        label12 = new JLabel("Patient Date Of Birth");
        add(label12, getConstraints(1, 13, 1, 1, GridBagConstraints.WEST));
        label13 = new JLabel("Day");
        add(label13, getConstraints(1, 14, 1, 1, GridBagConstraints.WEST));
        label14 = new JLabel("Month");
        add(label14, getConstraints(2, 14, 1, 1, GridBagConstraints.WEST));
        label15 = new JLabel("Year");
        add(label15, getConstraints(3, 14, 1, 1, GridBagConstraints.WEST));

        //DOB text fields
        field11 = new JTextField(5);
        field11.setBorder(loweredBorder);
        add(field11, getConstraints(1, 15, 1, 1, GridBagConstraints.WEST));
        field12 = new JTextField(5);
        field12.setBorder(loweredBorder);
        add(field12, getConstraints(2, 15, 1, 1, GridBagConstraints.WEST));
        field13 = new JTextField(5);
        field13.setBorder(loweredBorder);
        add(field13, getConstraints(3, 15, 1, 1, GridBagConstraints.WEST));


        // calculate button
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        add(confirm, getConstraints(1, 16, 1, 1, GridBagConstraints.WEST));


        // exit button
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        add(cancel, getConstraints(2, 16, 1, 1, GridBagConstraints.WEST));

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
//            ReferralOperations ro = new ReferralOperations();
//            PatientOperations po = new PatientOperations();

            //what is checked?!?!?!
//            ro.setReferral(Integer.valueOf(field1.getText()),field1.getText(),field3.getText(),field5.getText(),field7.getText(),field10.getText(), field11.getText() + "" + field12.getText() + "" +field13.getText(),field2.getText(),field4.getText(),area1.getText(),Integer.valueOf(combo1.getActionCommand()),Integer.valueOf(combo2.getActionCommand()),Integer.valueOf(label9.getText()),label11.getText());

//            po.addPatient(label1.getText(),label3.getText(),label5.getText(),label7.getText(),label8.getText(),label10.getText(),label13.getText(),label14.getText());
        }
    }
}


//package Referrals;
//
//import DataBase.PatientOperations;
//import Referrals.ReferralOperations;
//
//import javax.swing.*;
//import javax.swing.border.BevelBorder;
//import javax.swing.border.Border;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
///**
// * Created by x00113327 on 27/02/2015.
// */
//public class ReferralGUI extends JFrame implements ActionListener {
//    String[] list1 = {"med1                                          :", "med2        ", "med3        "};
//    String[] list2 = {"cons1                                         :", "cons2       ", "cons3       "};
//    JButton confirm;
//    JButton cancel;
//
//    JLabel label1;
//    JLabel label2;
//    JLabel label3;
//    JLabel label4;
//    JLabel label5;
//    JLabel label6;
//    JLabel label7;
//    JLabel label8;
//    JLabel label9;
//    JLabel label10;
//    JLabel label11;
//    JLabel label12;
//    JLabel label13;
//    JLabel label14;
//    JLabel label15;
//    JLabel label16;
//    JLabel label17;
//    JLabel bgroundImg;
//
//
//    JTextField field1;
//    JTextField field2;
//    JTextField field3;
//    JTextField field4;
//    JTextField field5;
//    JTextField field13;
//    JTextField field7;
//    JTextField field8;
//    JTextField field9;
//    JTextField field10;
//    JTextField field11;
//    JTextField field12;
//
//    JTextArea area1;
//    JPanel top;
//    JPanel top2;
//    JPanel top3and4;
//    JPanel top5;
//    JPanel top6;
//    JPanel top7;
//    JPanel contentPane;
//
//    JComboBox<String> combo1;
//    JComboBox<String> combo2;
//    JFrame f;
//
//    public ReferralGUI() {
//        f = new JFrame();
//        setTitle("Referrals");
//        f.setLayout(new GridLayout(2, 2));
//        setLayout(new GridBagLayout());
//        setSize(1100, 1000);
//        setResizable(false);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        Border loweredBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
//
//
//       /* //Background image
//        contentPane = new JPanel();
//        contentPane.setOpaque(true);
//
//        this.setContentPane(new JLabel(new ImageIcon("/recources/hospital.jpg")));
//        f.setIconImage(new ImageIcon(getClass().getResource("Hospital/recources/hospital.jpg")).getImage());
//
//        bgroundImg = new JLabel(new ImageIcon("Hospital/recources/hospital.jpg"));
//        f.add(bgroundImg);
//        setSize(1100, 1000);
//*/
//        //GP Name label
//        label1 = new JLabel("GP Full Name");
//        add(label1, getConstraints(0, 0, 1, 1, GridBagConstraints.WEST));
//        //text field
//        field1 = new JTextField(40);
//        add(field1, getConstraints(0, 1, 1, 1, GridBagConstraints.WEST));
//
//        //Patient Phone label
//        label2 = new JLabel("Patient Phone");
//        add(label2, getConstraints(1, 0, 1, 1, GridBagConstraints.WEST));
//        //text field
//        field2 = new JTextField(40);
//        add(field2, getConstraints(1, 1, 1, 1, GridBagConstraints.WEST));
//
//        //GO Location label
//        label3 = new JLabel("GP Location");
//        add(label3, getConstraints(0, 2, 1, 1, GridBagConstraints.WEST));
//        //text field
//        field3 = new JTextField(40);
//        add(field3, getConstraints(0, 3, 1, 1, GridBagConstraints.WEST));
//
//        //Reason for Visit label
//        label4 = new JLabel("Reason For Visit");
//        add(label4, getConstraints(1, 2, 1, 1, GridBagConstraints.WEST));
//        //text field
//        field4 = new JTextField(40);
//        add(field4, getConstraints(1, 3, 1, 1, GridBagConstraints.WEST));
//
//        //Patient First Name label
//        label5 = new JLabel("Patient First Name");
//        add(label5, getConstraints(0, 4, 1, 1, GridBagConstraints.WEST));
//        //text field
//        field5 = new JTextField(40);
//        add(field5, getConstraints(0, 5, 1, 1, GridBagConstraints.WEST));
//
//        //Recommendations label
//        label6 = new JLabel("Recommendations");
//        add(label6, getConstraints(1, 4, 1, 1, GridBagConstraints.WEST));
//        //text field
//        area1 = new JTextArea(3, 40);
//        add(area1, getConstraints(1, 5, 1, 1, GridBagConstraints.WEST));
//
//        //Patient Surname label
//        label7 = new JLabel("Patient Surname");
//        add(label7, getConstraints(0, 6, 1, 1, GridBagConstraints.WEST));
//        //text field
//        field7 = new JTextField(40);
//        add(field7, getConstraints(0, 7, 1, 1, GridBagConstraints.WEST));
//
//        //Patient Number label
//        label8 = new JLabel("Patient Number");
//        add(label8, getConstraints(0, 8, 1, 1, GridBagConstraints.WEST));
//        //text field
//        field8 = new JTextField(40);
//        add(field8, getConstraints(0, 9, 1, 1, GridBagConstraints.WEST));
//
//        //Required Equipment label
//        label9 = new JLabel("Medical Equipment Required");
//        add(label9, getConstraints(1, 8, 1, 1, GridBagConstraints.WEST));
//        //Combo-box
//        combo1 = new JComboBox<String>(list1);
//        add(combo1, getConstraints(1, 9, 1, 1, GridBagConstraints.WEST));
//
//        //Patient Address label
//        label10 = new JLabel("Patient Address");
//        add(label10, getConstraints(0, 10, 1, 1, GridBagConstraints.WEST));
//        //text field
//        field10 = new JTextField(40);
//        add(field10, getConstraints(0, 11, 1, 1, GridBagConstraints.WEST));
//
//        //Required Consultant label
//        label11 = new JLabel("Consultant Type Required");
//        add(label11, getConstraints(1, 10, 1, 1, GridBagConstraints.WEST));
//        //Combo-Box
//        combo2 = new JComboBox<String>(list2);
//        add(combo2, getConstraints(1, 11, 1, 1, GridBagConstraints.WEST));
//
//
//        //DOB labels
//        label12 = new JLabel("Patient Date Of Birth");
//        add(label12, getConstraints(0, 12, 1, 1, GridBagConstraints.WEST));
//        label13 = new JLabel("Day");
//        add(label13, getConstraints(0, 13, 1, 1, GridBagConstraints.WEST));
//        label14 = new JLabel("Month");
//        add(label14, getConstraints(1, 13, 1, 1, GridBagConstraints.WEST));
//        label15 = new JLabel("Year");
//        add(label15, getConstraints(2, 13, 1, 1, GridBagConstraints.WEST));
//
//        //DOB text fields
//        field11 = new JTextField(5);
//        add(field11, getConstraints(0, 14, 1, 1, GridBagConstraints.WEST));
//        field12 = new JTextField(5);
//        add(field12, getConstraints(1, 14, 1, 1, GridBagConstraints.WEST));
//        field13 = new JTextField(5);
//        add(field13, getConstraints(2, 14, 1, 1, GridBagConstraints.WEST));
//
//
//        // calculate button
//        confirm = new JButton("Confirm");
//        confirm.addActionListener(this);
//        add(confirm, getConstraints(0, 15, 1, 1, GridBagConstraints.WEST));
//
//
//        // exit button
//        cancel = new JButton("Cancel");
//        cancel.addActionListener(this);
//        add(cancel, getConstraints(1, 15, 1, 1, GridBagConstraints.WEST));
//
//    }
//
//
//    private GridBagConstraints getConstraints(int gridx, int gridy, int gridwidth, int gridheight, int anchor) {
//        GridBagConstraints c = new GridBagConstraints();
//        c.insets = new Insets(5, 5, 5, 5);
//        c.ipadx = 0;
//        c.ipady = 0;
//        c.gridx = gridx;
//        c.gridy = gridy;
//        c.gridwidth = gridwidth;
//        c.gridheight = gridheight;
//        c.anchor = anchor;
//        return c;
//    }
//
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource().equals(cancel)) {
//            System.exit(0);
//
//        } else if (e.getSource().equals(confirm)) {
//            ReferralOperations ro = new ReferralOperations();
//            PatientOperations po = new PatientOperations();
////            ro.setReferral(new Referrals(label1.getText(),label3.getText(),label5.getText(),label7.getText(),Integer.valueOf(label8.getText()),label10.getText(),Integer.valueOf(label13.getText()),Integer.valueOf(label14.getText()),Integer.valueOf(label15.getText()),label4.getText(),label2.getText(),label5.getText(),Integer.valueOf(label9.getText()),Integer.valueOf(label11.getText())));
////            po.addPatient(new PatientAdmin(label1.getText(),label3.getText(),label5.getText(),label7.getText(),Integer.valueOf(label8.getText()),label10.getText(),Integer.valueOf(label13.getText()),Integer.valueOf(label14.getText()),Integer.valueOf(label15.getText()),label4.getText(),label2.getText(),label5.getText(),Integer.valueOf(label9.getText()),Integer.valueOf(label11.getText())));
//        }
//    }
//
//}
//
//
//
//
//
