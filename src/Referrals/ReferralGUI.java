package Referrals;

import DataBase.PatientOperations;
import Referrals.ReferralOperations;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

/**
 * Created by Lionhart on 17/03/2015.
 */
public class ReferralGUI extends JFrame implements ActionListener {
    String[] list1 = {"XRay :", "MRI Scan :", "CT Scan :"};
    String[] list2 = {"Radiology :", "Pediatrics :", "Surgery :"};
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


    JTextField gpName;
    JTextField pPhone;
    JTextField gpLocation;
    JTextField reasonVisit;
    JTextField pFname;
    JTextField pLname;
    JTextField pNum;
    JTextField pAddress;
    JTextField year;
    JTextField day;
    JTextField month;

    JTextArea recommendations;
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


//        //Background image
//        contentPane = new JPanel();
//        contentPane.setOpaque(true);
//
//        this.setContentPane(new JLabel(new ImageIcon("/recources/hospital.jpg")));
//        f.setIconImage(new ImageIcon(getClass().getResource("Hospital/recources/hospital.jpg")).getImage());
//
//        bgroundImg = new JLabel(new ImageIcon("Hospital/recources/hospital.jpg"));
//        f.add(bgroundImg);
//        setSize(1100, 1000);


        //top panel


        label16 = new JLabel("Referrals");
        label16.setFont(new Font("Arial", Font.BOLD, 38));
        add(label16, getConstraints(0, 0, 0, 1, GridBagConstraints.NORTH));

        //GP Name label
        label1 = new JLabel("GP Full Name");
        add(label1, getConstraints(1, 1, 1, 1, GridBagConstraints.WEST));
        //text field
        gpName = new JTextField(40);
        gpName.setBorder(loweredBorder);
        add(gpName, getConstraints(1, 2, 1, 1, GridBagConstraints.WEST));

        //Patient Phone label
        label2 = new JLabel("Patient Phone");
        add(label2, getConstraints(2, 1, 1, 1, GridBagConstraints.WEST));
        //text field
        pPhone = new JTextField(40);
        pPhone.setBorder(loweredBorder);
        add(pPhone, getConstraints(2, 2, 1, 1, GridBagConstraints.WEST));

        //GO Location label
        label3 = new JLabel("GP Location");
        add(label3, getConstraints(1, 3, 1, 1, GridBagConstraints.WEST));
        //text field
        gpLocation = new JTextField(40);
        gpLocation.setBorder(loweredBorder);
        add(gpLocation, getConstraints(1, 4, 1, 1, GridBagConstraints.WEST));

        //Reason for Visit label
        label4 = new JLabel("Reason For Visit");
        add(label4, getConstraints(2, 3, 1, 1, GridBagConstraints.WEST));
        //text field
        reasonVisit = new JTextField(40);
        reasonVisit.setBorder(loweredBorder);
        add(reasonVisit, getConstraints(2, 4, 1, 1, GridBagConstraints.WEST));

        //Patient First Name label
        label5 = new JLabel("Patient First Name");
        add(label5, getConstraints(1, 5, 1, 1, GridBagConstraints.WEST));
        //text field
        pFname = new JTextField(40);
        pFname.setBorder(loweredBorder);
        add(pFname, getConstraints(1, 6, 1, 1, GridBagConstraints.WEST));

        //Recommendations label
        label6 = new JLabel("Recommendations");
        add(label6, getConstraints(2, 5, 1, 1, GridBagConstraints.WEST));
        //text field
        recommendations = new JTextArea(3, 40);
        recommendations.setBorder(loweredBorder);
        add(recommendations, getConstraints(2, 6, 1, 1, GridBagConstraints.WEST));

        //Patient Surname label
        label7 = new JLabel("Patient Surname");
        add(label7, getConstraints(1, 7, 1, 1, GridBagConstraints.WEST));
        //text field
        pLname = new JTextField(40);
        pLname.setBorder(loweredBorder);
        add(pLname, getConstraints(1, 8, 1, 1, GridBagConstraints.WEST));

        //Patient Number label
        label8 = new JLabel("Patient Number");
        add(label8, getConstraints(1, 9, 1, 1, GridBagConstraints.WEST));
        //text field
        pNum = new JTextField(40);
        pNum.setBorder(loweredBorder);
        add(pNum, getConstraints(1, 10, 1, 1, GridBagConstraints.WEST));

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
        pAddress = new JTextField(40);
        pAddress.setBorder(loweredBorder);
        add(pAddress, getConstraints(1, 12, 1, 1, GridBagConstraints.WEST));

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
        day = new JTextField(5);
        day.setBorder(loweredBorder);
        add(day, getConstraints(1, 15, 1, 1, GridBagConstraints.WEST));
        month = new JTextField(5);
        month.setBorder(loweredBorder);
        add(month, getConstraints(2, 15, 1, 1, GridBagConstraints.WEST));
        year = new JTextField(5);
        year.setBorder(loweredBorder);
        add(year, getConstraints(3, 15, 1, 1, GridBagConstraints.WEST));


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
            int catcher = 0, catcher2 = 0;
            try {
                String medEquip = (String) combo1.getSelectedItem();
                if (medEquip.equals("XRay :")) {
                    catcher = 1;
                } else if (medEquip.equals("MRI Scan :")) {
                    catcher = 2;
                } else if (medEquip.equals("CT Scan :")) {
                    catcher = 3;
                }
                String conEquip = (String) combo2.getSelectedItem();
                if (conEquip.equals("Radiology :")) {
                    catcher2 = 1;
                } else if (conEquip.equals("Pediatrics :")) {
                    catcher2 = 2;
                } else if (conEquip.equals("Surgery :")) {
                    catcher2 = 3;
                }
            } catch (InputMismatchException im) {
                System.out.println(im);
            }
            Referrals referrals=new Referrals(gpName.getText(), gpLocation.getText(), pFname.getText(), pLname.getText(), pAddress.getText(),(day.getText() + "-" + month.getText() + "-" + year.getText()),pPhone.getText(),reasonVisit.getText(),
                    recommendations.getText(),catcher,catcher2,"male",Integer.valueOf(pNum.getText()));

            }
        }
    }



