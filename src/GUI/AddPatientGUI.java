package GUI;

import DataBase.PatientOperations;
import Model.PatientRecord;

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
    JLabel patientNum;
    JLabel label8;
    JLabel label11;
    JLabel label12;
    JLabel label13;
    JLabel label14;
    JLabel label15;
    JRadioButton male;
    JRadioButton female;
    JTextField patientText;
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

    public AddPatientGUI(int choice)
    {
        f = new JFrame();
        if(choice==1) {
            f.setTitle("New Patient Admin Record");
        }else if(choice==2){
            f.setTitle("Update Patient Admin Record");
        }
        f.setSize(790, 975);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        Border loweredBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        f.setLayout(new FlowLayout(FlowLayout.LEFT));


        JPanel holder=new JPanel(new GridLayout(1,1));
        JPanel topSection=new JPanel(new GridLayout(1,3));

        Clock.DigitalClock clockD=new Clock.DigitalClock();
        JPanel clock = new JPanel(new FlowLayout(FlowLayout.LEFT));
        clock.add(clockD);

        JPanel title=new JPanel(new FlowLayout(FlowLayout.CENTER));

        if(choice==1) {
            label5 = new JLabel("New Admin Records");
            title.add(label5);
            label5.setFont(new Font("Arial", Font.BOLD, 24));

        }else if(choice==2){
            label5 = new JLabel("Update Admin Records");
            title.add(label5);
            label5.setFont(new Font("Arial", Font.BOLD, 24));
        }


        JPanel ID=new JPanel(new FlowLayout(FlowLayout.CENTER));
        //labels
        patientNum = new JLabel("\tPatient Number");
        ID.add(patientNum);
        //text field
        patientText = new JTextField(5);
        patientText.setBorder(loweredBorder);
        ID.add(patientText);


        topSection.add(clock);
        topSection.add(title);
        topSection.add(ID);

        holder.add(topSection);


        f.add(holder);


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

        label12 = new JLabel("Patient DOB");
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cancel)) {
            f.setVisible(false);

        } else if (e.getSource().equals(confirm)) {
            String choice="";
            PatientOperations po=new PatientOperations();
            if(male.isSelected()){
                choice = "Male";
            }
            if(female.isSelected()){
                choice ="Female";
            }
            PatientRecord patientRecord=new PatientRecord(po,field2.getText(), field3.getText(), field4.getText(),field6.getText(),choice, field5.getText(), field7.getText(),(field11.getText()+field12.getText()+field14));
        }
    }
}
