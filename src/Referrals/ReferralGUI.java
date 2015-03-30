package Referrals;

import DataBase.PatientOperations;
import GUI.Clock;
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
    JLabel gPNum;
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
    JTextField gPNumText;

    JTextArea recommendations;
    JComboBox<String> combo1;
    JComboBox<String> combo2;
    JFrame f;

    public ReferralGUI() {
        f = new JFrame();
        f.setTitle("Referrals");
        f.setLayout(new FlowLayout(FlowLayout.LEFT));
        f.setSize(940, 840);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Border loweredBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);


        //top panel
        JPanel holder=new JPanel(new FlowLayout());
        JPanel topSection=new JPanel(new GridLayout(1,3));

        Clock.DigitalClock clockD=new Clock.DigitalClock();
        JPanel clock = new JPanel(new FlowLayout(FlowLayout.LEFT));
        clock.add(clockD);

        JPanel title=new JPanel(new FlowLayout(FlowLayout.CENTER));
        label5 = new JLabel("        Referrals    ");
        title.add(label5);
        label5.setFont(new Font("Arial", Font.BOLD, 38));


        JPanel ID=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        //labels
        gPNum = new JLabel("\tGP Number");
        ID.add(gPNum);
        //text field
        gPNumText = new JTextField(5);
        gPNumText.setBorder(loweredBorder);
        ID.add(gPNumText);


        topSection.add(clock,BorderLayout.EAST);
        topSection.add(title,BorderLayout.CENTER);
        topSection.add(ID,BorderLayout.EAST);
        holder.add(topSection);





        JPanel body=new JPanel(new GridBagLayout());
        //GP Name label
        label1 = new JLabel("GP Full Name");
        body.add(label1, getConstraints(0, 0, 1, 1, GridBagConstraints.WEST));
        //text field
        gpName = new JTextField(40);
        gpName.setBorder(loweredBorder);
        body.add(gpName, getConstraints(0, 1, 1, 1, GridBagConstraints.WEST));

        //Patient Phone label
        label2 = new JLabel("Patient Phone");
        body.add(label2, getConstraints(1, 0, 1, 1, GridBagConstraints.WEST));
        //text field
        pPhone = new JTextField(40);
        pPhone.setBorder(loweredBorder);
        body.add(pPhone, getConstraints(1, 1, 1, 1, GridBagConstraints.WEST));

        //GO Location label
        label3 = new JLabel("GP Location");
        body.add(label3, getConstraints(0, 3, 1, 1, GridBagConstraints.WEST));
        //text field
        gpLocation = new JTextField(40);
        gpLocation.setBorder(loweredBorder);
        body.add(gpLocation, getConstraints(0, 4, 1, 1, GridBagConstraints.WEST));

        //Reason for Visit label
        label4 = new JLabel("Reason For Visit");
        body.add(label4, getConstraints(1, 3, 1, 1, GridBagConstraints.WEST));
        //text field
        reasonVisit = new JTextField(40);
        reasonVisit.setBorder(loweredBorder);
        body.add(reasonVisit, getConstraints(1, 4, 1, 1, GridBagConstraints.WEST));

        //Patient First Name label
        label5 = new JLabel("Patient First Name");
        body.add(label5, getConstraints(0, 5, 1, 1, GridBagConstraints.WEST));
        //text field
        pFname = new JTextField(40);
        pFname.setBorder(loweredBorder);
        body.add(pFname, getConstraints(0, 6, 1, 1, GridBagConstraints.WEST));

        //Recommendations label
        label6 = new JLabel("Recommendations");
        body.add(label6, getConstraints(1, 5, 1, 1, GridBagConstraints.WEST));
        //text field
        recommendations = new JTextArea(4, 40);
        recommendations.setBorder(loweredBorder);
        body.add(recommendations, getConstraints(1, 6, 3, 3, GridBagConstraints.WEST));

        //Patient Surname label
        label7 = new JLabel("Patient Surname");
        body.add(label7, getConstraints(0, 7, 1, 1, GridBagConstraints.WEST));
        //text field
        pLname = new JTextField(40);
        pLname.setBorder(loweredBorder);
        body.add(pLname, getConstraints(0, 8, 1, 1, GridBagConstraints.WEST));

        //Patient Number label
        label8 = new JLabel("Patient Number");
        body.add(label8, getConstraints(0, 9, 1, 1, GridBagConstraints.WEST));
        //text field
        pNum = new JTextField(40);
        pNum.setBorder(loweredBorder);
        body.add(pNum, getConstraints(0, 10, 1, 1, GridBagConstraints.WEST));

        //Required Equipment label
        label9 = new JLabel("Medical Equipment Required");
        body.add(label9, getConstraints(1, 9, 1, 1, GridBagConstraints.WEST));
        //Combo-box
        combo1 = new JComboBox<String>(list1);
        combo1.setPreferredSize(new Dimension(300,20));
        body.add(combo1, getConstraints(1, 10, 2, 1, GridBagConstraints.WEST));

        //Patient Address label
        label10 = new JLabel("Patient Address");
        body.add(label10, getConstraints(0, 11, 1, 1, GridBagConstraints.WEST));
        //text field
        pAddress = new JTextField(40);
        pAddress.setBorder(loweredBorder);
        body.add(pAddress, getConstraints(0, 12, 1, 1, GridBagConstraints.WEST));

        //Required Consultant label
        label11 = new JLabel("Consultant Type Required");
        body.add(label11, getConstraints(1, 11, 1, 1, GridBagConstraints.WEST));
        //Combo-Box
        combo2 = new JComboBox<String>(list2);
        combo2.setPreferredSize(new Dimension(300,20));
        body.add(combo2, getConstraints(1, 12, 2, 1, GridBagConstraints.WEST));


        JPanel holder2=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel bottom=new JPanel(new GridBagLayout());
        //DOB labels
        label12 = new JLabel("Patient DOB");
        bottom.add(label12, getConstraints(0, 0, 1, 1, GridBagConstraints.WEST));
        label13 = new JLabel("Day");
        bottom.add(label13, getConstraints(0, 1, 1, 1, GridBagConstraints.WEST));
        label14 = new JLabel("Month");
        bottom.add(label14, getConstraints(1, 1, 1, 1, GridBagConstraints.WEST));
        label15 = new JLabel("Year");
        bottom.add(label15, getConstraints(2, 1, 1, 1, GridBagConstraints.WEST));

        //DOB text fields
        day = new JTextField(5);
        day.setBorder(loweredBorder);
        bottom.add(day, getConstraints(0, 2, 1, 1, GridBagConstraints.WEST));
        month = new JTextField(5);
        month.setBorder(loweredBorder);
        bottom.add(month, getConstraints(1, 2, 1, 1, GridBagConstraints.WEST));
        year = new JTextField(5);
        year.setBorder(loweredBorder);
        bottom.add(year, getConstraints(2, 2, 1, 1, GridBagConstraints.WEST));


        // calculate button
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        bottom.add(confirm, getConstraints(0, 3, 1, 1, GridBagConstraints.WEST));


        // exit button
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        bottom.add(cancel, getConstraints(1, 3, 1, 1, GridBagConstraints.WEST));

        f.add(holder,BorderLayout.NORTH);
        f.add(body, BorderLayout.CENTER);
        holder2.add(bottom);
        f.add(holder2, BorderLayout.SOUTH);
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



