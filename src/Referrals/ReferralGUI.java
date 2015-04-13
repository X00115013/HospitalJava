package Referrals;

import DataBase.PatientOperations;
import GUI.Clock;
import Model.Consultants;
import Model.Equipment;
import Model.PatientRecord;
import Referrals.ReferralOperations;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Created by Lionhart and  Thomas Murray on 17/03/2015.
 */
public class ReferralGUI extends JFrame implements ActionListener {
    String[] list1;
    String[] list2;
    private ArrayList<Equipment> eqList=new ArrayList<>();
    private ArrayList<Consultants>conList=new ArrayList<>();
    private Equipment equipment;
    private Consultants consultants;
    JButton confirm;
    JButton cancel;
    JRadioButton male, female;

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

    private int choiceGui ,setter=1940;
    private String[] daysIn={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    private String[] months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    private String[] years=new String[76];
    JComboBox<String> dayCombo,monthCombo,yearCombo;


    public ReferralGUI() {
        for (int i = 0; i < 76; i++) {
            years[i]= Integer.toString(setter);
            setter++;
        }
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(StartWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
//            Logger.getLogger(StartWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
//            Logger.getLogger(StartWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(StartWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

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



        equipment=new Equipment();
        eqList.addAll(equipment.getEquipments());
        list1 = new String[eqList.size()+1];
        list1[0]="";
        for (int i = 0; i < eqList.size(); i++) {
            list1[i+1] = eqList.get(i).getEqName();
        }

        consultants=new Consultants();
        conList.addAll(consultants.getConsultants());
        list2 = new String[conList.size()+1];
        list2[0]="";
        for (int i = 0; i < conList.size(); i++) {
            list2[i+1] = conList.get(i).getConSpeciality();
        }


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
        combo1.setPreferredSize(new Dimension(300,30));
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
        combo2.setPreferredSize(new Dimension(300,30));
        body.add(combo2, getConstraints(1, 12, 2, 1, GridBagConstraints.WEST));




        JPanel holder2=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel bottom=new JPanel(new GridBagLayout());


        male = new JRadioButton("Male");
        male.addActionListener(this);
        bottom.add(male, getConstraints(0, 0, 1, 1, (GridBagConstraints.WEST)));
        female = new JRadioButton("Female");
        female.addActionListener(this);
        bottom.add(female, getConstraints(1, 0, 1, 1, (GridBagConstraints.WEST)));



        //DOB labels
        label12 = new JLabel("Patient DOB");
        bottom.add(label12, getConstraints(0, 1, 1, 1, GridBagConstraints.WEST));
        label13 = new JLabel("Day");
        bottom.add(label13, getConstraints(0, 2, 1, 1, GridBagConstraints.WEST));
        label14 = new JLabel("Month");
        bottom.add(label14, getConstraints(1, 2, 1, 1, GridBagConstraints.WEST));
        label15 = new JLabel("Year");
        bottom.add(label15, getConstraints(2, 2, 1, 1, GridBagConstraints.WEST));

        //DOB text fields
        dayCombo = new JComboBox<String>(daysIn);
        dayCombo.setBorder(loweredBorder);
        bottom.add(dayCombo, getConstraints(0, 3, 1, 1, GridBagConstraints.WEST));
        monthCombo = new JComboBox<String>(months);
        monthCombo.setBorder(loweredBorder);
        monthCombo.addActionListener(this);
        bottom.add(monthCombo, getConstraints(1, 3, 1, 1, GridBagConstraints.WEST));
        yearCombo = new JComboBox<String>(years);
        yearCombo.setBorder(loweredBorder);
        bottom.add(yearCombo, getConstraints(2, 3, 1, 1, GridBagConstraints.WEST));



        // calculate button
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        bottom.add(confirm, getConstraints(0, 5, 1, 1, GridBagConstraints.WEST));


        // exit button
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        bottom.add(cancel, getConstraints(1, 5, 1, 1, GridBagConstraints.WEST));

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
            String choice="";
            if (male.isSelected()) {
                choice = "Male";
            }
            if (female.isSelected()) {
                choice = "Female";
            }
            String catcher = "",catcher2 = "";
            String med = (String) combo1.getSelectedItem();
            for (int i = 0; i < eqList.size(); i++) {
                if (med.equals(eqList.get(i).getEqName())){
                    catcher =eqList.get(i).getEqName();
                }
            }
            String con = (String) combo2.getSelectedItem();
            for (int j = 0; j < conList.size(); j++) {
                if (con.equals(conList.get(j).getConSpeciality())) {
                    catcher2 = conList.get(j).getConName();;
                }
            }
            Referrals referrals=new Referrals(gpName.getText(), gpLocation.getText(), pFname.getText(), pLname.getText(), pAddress.getText(),(day.getText() + "-" + month.getText() + "-" + year.getText()),pPhone.getText(),reasonVisit.getText(),
                    recommendations.getText(),catcher,catcher2,1,choice);

            }else if (e.getSource().equals(male)) {
                female.setSelected(false);

            }else if (e.getSource().equals(female)) {
                male.setSelected(false);
        }
        }
    }



