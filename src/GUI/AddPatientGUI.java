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
 * Created by Thomas Murray on 20/03/2015.
 */
public class AddPatientGUI extends JFrame implements ActionListener {
    JButton confirm, cancel;
    JLabel patientFName, patientSName, patientAddress, patientEmail, patientOccupation, patientPhone, patientNum, titleF, day, month, year, DOB;
    JRadioButton male, female;
    JTextField patientText, patientFNameText, patientSNameText, patientAddressText, patientEmailText, patientOccupationText, patientPhoneText, dayText, monthText, yearText;
    JFrame f;
    int choiceGui;

    public AddPatientGUI(int choiceGUI) {
        choiceGui = choiceGUI;
        f = new JFrame();
        if (choiceGUI == 1) {
            f.setTitle("New Patient Admin Record");
        } else if (choiceGUI == 2) {
            f.setTitle("Update Patient Admin Record");
        }
        f.setSize(790, 975);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        Border loweredBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        f.setLayout(new FlowLayout(FlowLayout.LEFT));


        JPanel holder = new JPanel(new GridLayout(1, 1));
        JPanel topSection = new JPanel(new GridLayout(1, 3));

        Clock.DigitalClock clockD = new Clock.DigitalClock();
        JPanel clock = new JPanel(new FlowLayout(FlowLayout.LEFT));
        clock.add(clockD);

        JPanel title = new JPanel(new FlowLayout(FlowLayout.CENTER));

        if (choiceGUI == 1) {
            titleF = new JLabel("New Admin Records");
            title.add(titleF);
            titleF.setFont(new Font("Arial", Font.BOLD, 24));

        } else if (choiceGUI == 2) {
            titleF = new JLabel("Update Admin Records");
            title.add(titleF);
            titleF.setFont(new Font("Arial", Font.BOLD, 24));
        }


        JPanel ID = new JPanel(new FlowLayout(FlowLayout.CENTER));
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


        JPanel test2 = new JPanel(new GridLayout(1, 1));
        //middle
        JPanel middle = new JPanel(new GridBagLayout());
        patientFName = new JLabel("Patient First Name");
        middle.add(patientFName, getConstraints(0, 0, 1, 1, (GridBagConstraints.WEST)));
        patientFNameText = new JTextField(60);
        patientFNameText.setBorder(loweredBorder);
        middle.add(patientFNameText, getConstraints(0, 1, 1, 2, (GridBagConstraints.WEST)));

        patientSName = new JLabel("Patient Surname");
        middle.add(patientSName, getConstraints(0, 3, 1, 1, (GridBagConstraints.WEST)));
        //text field
        patientSNameText = new JTextField(60);
        patientSNameText.setBorder(loweredBorder);
        middle.add(patientSNameText, getConstraints(0, 4, 1, 2, (GridBagConstraints.WEST)));

        patientAddress = new JLabel("Patient Address");
        middle.add(patientAddress, getConstraints(0, 6, 1, 1, (GridBagConstraints.WEST)));
        //text field
        patientAddressText = new JTextField(60);
        patientAddressText.setBorder(loweredBorder);
        middle.add(patientAddressText, getConstraints(0, 7, 1, 2, (GridBagConstraints.WEST)));

        patientEmail = new JLabel("Patient Email");
        middle.add(patientEmail, getConstraints(0, 9, 1, 1, (GridBagConstraints.WEST)));
        //text field
        patientEmailText = new JTextField(60);
        patientEmailText.setBorder(loweredBorder);
        middle.add(patientEmailText, getConstraints(0, 10, 1, 2, (GridBagConstraints.WEST)));

        patientOccupation = new JLabel("Patient Occupation");
        middle.add(patientOccupation, getConstraints(0, 12, 1, 1, (GridBagConstraints.WEST)));
        //text field
        patientOccupationText = new JTextField(60);
        patientOccupationText.setBorder(loweredBorder);
        middle.add(patientOccupationText, getConstraints(0, 13, 1, 2, (GridBagConstraints.WEST)));

        patientPhone = new JLabel("Patient Phone");
        middle.add(patientPhone, getConstraints(0, 15, 1, 1, (GridBagConstraints.WEST)));
        //text field
        patientPhoneText = new JTextField(60);
        patientPhoneText.setBorder(loweredBorder);
        middle.add(patientPhoneText, getConstraints(0, 16, 1, 2, (GridBagConstraints.WEST)));
        test2.add(middle);
        f.add(test2);


        //DOB labels
        JPanel test = new JPanel(new GridLayout(2, 1));
        JPanel dobs = new JPanel(new GridBagLayout());
        male = new JRadioButton("Male");
        male.addActionListener(this);
        dobs.add(male, getConstraints(0, 11, 1, 1, (GridBagConstraints.WEST)));
        female = new JRadioButton("Female");
        female.addActionListener(this);
        dobs.add(female, getConstraints(1, 11, 1, 1, (GridBagConstraints.WEST)));

        DOB = new JLabel("Patient DOB");
        dobs.add(DOB, getConstraints(0, 13, 1, 1, GridBagConstraints.LINE_START));
        day = new JLabel("Day");
        dobs.add(day, getConstraints(0, 14, 1, 1, GridBagConstraints.LINE_START));
        month = new JLabel("Month");
        dobs.add(month, getConstraints(1, 14, 1, 1, GridBagConstraints.LINE_START));
        year = new JLabel("Year");
        dobs.add(year, getConstraints(2, 14, 1, 1, GridBagConstraints.LINE_START));

        //DOB text fields
        dayText = new JTextField(5);
        dayText.setBorder(loweredBorder);
        dobs.add(dayText, getConstraints(0, 15, 1, 1, GridBagConstraints.LINE_START));
        monthText = new JTextField(5);
        monthText.setBorder(loweredBorder);
        dobs.add(monthText, getConstraints(1, 15, 1, 1, GridBagConstraints.LINE_START));
        yearText = new JTextField(5);
        yearText.setBorder(loweredBorder);
        dobs.add(yearText, getConstraints(2, 15, 1, 1, GridBagConstraints.LINE_START));
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
            String choice = "";
            PatientOperations po = new PatientOperations();
            if (male.isSelected()) {
                choice = "Male";
            }
            if (female.isSelected()) {
                choice = "Female";
            }
            if (choiceGui == 1) {
                PatientRecord patientRecord = new PatientRecord(po, patientFNameText.getText(), patientSNameText.getText(), patientAddressText.getText(),
                        patientEmailText.getText(), choice, patientOccupationText.getText(), patientPhoneText.getText(), (dayText.getText() + monthText.getText() + yearText));
            } else if (choiceGui == 2) {
                PatientRecord patientRecord = new PatientRecord(po,Integer.parseInt(patientText.getText()),patientFNameText.getText(), patientSNameText.getText(), patientAddressText.getText(),
                        patientOccupationText.getText(),choice, patientEmailText.getText(), patientPhoneText.getText(), (dayText.getText() + monthText.getText() + yearText));

            }
        }
    }
}
