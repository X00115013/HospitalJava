package GUI;

import DataBase.PatientOperations;
import Model.PatientRecord;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Thomas Murray on 20/03/2015.
 */
public class AddPatientGUI extends JFrame implements ActionListener {
    JButton confirm, cancel;
    JLabel patientFName, patientSName, patientAddress, patientEmail, patientOccupation, patientPhone, patientNum, titleF, day, month, year, DOB;
    JRadioButton male, female;
    JTextField patientText, patientFNameText, patientSNameText, patientAddressText, patientEmailText, patientOccupationText, patientPhoneText, dayText, monthText, yearText;
    JFrame f;
    private int choiceGui ,setter=1940;
    private ArrayList<PatientRecord> pList=new ArrayList<>();
    private PatientRecord patientRecord;
    private String[] dayArray=new String[31];
    private String[] daysIn={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    private String[] months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    private String[] years=new String[76];
    JComboBox<String> dayCombo,monthCombo,yearCombo;

    public AddPatientGUI(int choiceGUI,int patientNumIn) {
        for (int i = 0; i < 76; i++) {
            years[i]= Integer.toString(setter);
            setter++;
        }
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

        if (choiceGUI == 1) {
            patientNum = new JLabel("\tNew Patient Number");
            ID.add(patientNum);

            //text field
            patientText = new JTextField(5);
            patientText.setBorder(loweredBorder);
            patientText.setText(Integer.toString(patientNumIn));

        } else if (choiceGUI == 2) {
            patientNum = new JLabel("\tPatient Number");
            ID.add(patientNum);
            //text field
            patientText = new JTextField(5);
            patientText.setBorder(loweredBorder);
            patientText.setText(Integer.toString(patientNumIn));
        }
        patientText.setEditable(false);
        //labels

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
        dobs.add(male, getConstraints(0, 1, 1, 1, (GridBagConstraints.WEST)));
        female = new JRadioButton("Female");
        female.addActionListener(this);
        dobs.add(female, getConstraints(1, 1, 1, 1, (GridBagConstraints.WEST)));


        DOB = new JLabel("Patient DOB");
        dobs.add(DOB, getConstraints(0, 3, 1, 1, GridBagConstraints.LINE_START));
        day = new JLabel("Day");
        dobs.add(day, getConstraints(0, 4, 1, 1, GridBagConstraints.LINE_START));
        month = new JLabel("Month");
        dobs.add(month, getConstraints(1, 4, 1, 1, GridBagConstraints.LINE_START));
        year = new JLabel("Year");
        dobs.add(year, getConstraints(2, 4, 1, 1, GridBagConstraints.LINE_START));

        //DOB text fields

        monthCombo = new JComboBox<String>(months);
        monthCombo.addActionListener(this);
        dayCombo = new JComboBox<String>(daysIn);
        dobs.add(dayCombo, getConstraints(0, 6, 1, 1, GridBagConstraints.LINE_START));
        dobs.add(monthCombo, getConstraints(1, 6, 1, 1, GridBagConstraints.LINE_START));
        yearCombo = new JComboBox<String>(years);
        dobs.add(yearCombo, getConstraints(2, 6, 1, 1, GridBagConstraints.LINE_START));
        test.add(dobs);

        if(choiceGUI==2){
            patientRecord=new PatientRecord();
            pList.addAll(patientRecord.getPatientList());
            for (int i = 0; i < pList.size() ; i++) {
                if(patientNumIn==pList.get(i).getPatientNumber()){
                    patientFNameText.setText(pList.get(i).getPatientFName());
                    patientSNameText.setText(pList.get(i).getPatientLName());
                    patientAddressText.setText(pList.get(i).getPatientAddress());
                    patientEmailText.setText(pList.get(i).getEmail());
                    patientOccupationText.setText(pList.get(i).getOccupation());
                    patientPhoneText.setText(pList.get(i).getPhone());
                    dayCombo.setSelectedItem(pList.get(i).getDOB().charAt(0) + "" + pList.get(i).getDOB().charAt(1));
                    monthCombo.setSelectedItem(pList.get(i).getDOB().charAt(3)+""+pList.get(i).getDOB().charAt(4)+""+pList.get(i).getDOB().charAt(5));
                    yearCombo.setSelectedItem(pList.get(i).getDOB().charAt(7)+""+pList.get(i).getDOB().charAt(8)+""+pList.get(i).getDOB().charAt(9)+""+pList.get(i).getDOB().charAt(10));
                    if(pList.get(i).getGender().equalsIgnoreCase("male")){
                        male.setSelected(true);
                    }else if(pList.get(i).getGender().equalsIgnoreCase("female")){
                        female.setSelected(true);
                    }
                }
            }
        }

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

    public String[] setCombo(){
        int setter=1;
        String catcher= (String)monthCombo.getSelectedItem();
        if(catcher.equals("Apr")||catcher.equals("Jun")||catcher.equals("Sep")||catcher.equals("Nov")){
            setter=1;
            for (int i = 0; i < 30; i++) {
                dayArray[i]= Integer.toString(setter);
                setter++;
            }
        }else if(catcher.equals("Feb")){
            setter=1;
            for (int i = 0; i < 31; i++) {
                dayArray[i]= Integer.toString(setter);
                setter++;
            }
        }else {
            setter=1;
            for (int i = 0; i < 31; i++) {
                dayArray[i] = Integer.toString(setter);
                setter++;
            }
        }return dayArray;
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

        } else if (e.getSource() == (male)) {
            female.setSelected(false);

        }else if (e.getSource() == (female)) {
            male.setSelected(false);

        }else if (e.getSource() == (monthCombo)) {
            dayCombo.revalidate();

        }else if (e.getSource().equals(confirm)) {
            String choice = "";
            PatientOperations po = new PatientOperations();
            if ((patientFNameText.getText().equals("")) || (patientSNameText.getText().equals("")) || (patientAddressText.getText().equals("")) ||
                    (dayCombo.getSelectedItem().equals("")) || (monthCombo.getSelectedItem().equals("")) || (yearCombo.getSelectedItem().equals(""))
                    || (patientPhoneText.getText().equals(""))) {
                if (patientFNameText.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "First Name is a required field");
                }if(patientSNameText.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Surname is a required field");
                }if(patientAddressText.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Address is a required field");
                }if(patientPhoneText.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Phone are required field");
                }
            } else {
                if (male.isSelected()) {
                    choice = "Male";
                }
                if (female.isSelected()) {
                    choice = "Female";
                }
                if (choiceGui == 1) {
                    String dayCon=(String)dayCombo.getSelectedItem();
                    String monthCon=(String)monthCombo.getSelectedItem();
                    String yearCon=(String)yearCombo.getSelectedItem();
                    PatientRecord patientRecord = new PatientRecord(po, patientFNameText.getText(), patientSNameText.getText(), patientAddressText.getText(),
                            patientOccupationText.getText(), choice, patientEmailText.getText(), patientPhoneText.getText(), ( dayCon+"-"+ monthCon +"-"+ yearCon));
                    JOptionPane.showMessageDialog(null, "Admin Record has been Added");
                } else if (choiceGui == 2) {
                    String dayCon=(String)dayCombo.getSelectedItem();
                    String monthCon=(String)monthCombo.getSelectedItem();
                    String yearCon=(String)yearCombo.getSelectedItem();
                    PatientRecord patientRecord = new PatientRecord(po, Integer.parseInt(patientText.getText()), patientFNameText.getText(), patientSNameText.getText(), patientAddressText.getText(),
                            patientOccupationText.getText(), choice, patientEmailText.getText(), patientPhoneText.getText(), ( dayCon+"-"+ monthCon +"-"+ yearCon));
                    JOptionPane.showMessageDialog(null, "Admin Record has been Updated");

                }
                f.setVisible(false);
            }
        }
    }
}
