package GUI;

import DataBase.AppointmentOperations;
import DataBase.PatientOperations;
import Model.Appointment;
import Model.PatientRecord;
import Model.Security;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Created by Thomas Murray on 01/03/2015.
 *
 * This class is used to search for a record using either their name and DOB or by their patient Number.
 * This class is also used to verify access security
 *
 */
public class AddPatientSecurityGUI extends JFrame implements ActionListener{
    private  int answer=0,selection,setter=1940;
    JButton confirm;
    JButton cancel,add;
    JLabel patientNum,password,label5,title,firstName,surname,day, month,year,or,dob;
    JTextField patientNumText, passwordText,firstNameText,surnameText;
    JComboBox<String>dayCombo,monthCombo,yearCombo;
    JFrame f;
    private String[] daysIn={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    private String[] months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    private String[] years=new String[76];
    private ArrayList<PatientRecord>pList=new ArrayList<>();

    public AddPatientSecurityGUI(int selectionIn) {
        selection=selectionIn;
        for (int i = 0; i < 76; i++) {
            years[i]= Integer.toString(setter);
            setter++;
        }
        f = new JFrame("Security");
        f.setLayout(new FlowLayout());
        f.setSize(560, 540);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        Border loweredBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //Clock
        JPanel clock= new JPanel(new FlowLayout(FlowLayout.LEFT));
        f.add(clock,BorderLayout.EAST);
        JPanel offTop= new JPanel(new FlowLayout(FlowLayout.CENTER));
        f.add(offTop,BorderLayout.NORTH);

        //title label
        title = new JLabel("SECURITY");
        offTop.add(title);
        title.setFont(new Font("Arial",Font.BOLD, 44));

        JPanel middle=new JPanel();
        f.add(middle,BorderLayout.CENTER);
        middle.setLayout(new GridBagLayout());

        //first name label
        firstName=new JLabel("First Name");
        middle.add(firstName, getConstraints(0, 0, 4, 1, GridBagConstraints.WEST));

        //first name text
        firstNameText=new JTextField(30);
        firstNameText.setBorder(loweredBorder);
        middle.add(firstNameText, getConstraints(0, 1,4, 1, GridBagConstraints.WEST));

        //surname label
        surname=new JLabel("Surname");
        middle.add(surname, getConstraints(0, 2, 4, 1, GridBagConstraints.WEST));

        //surname text
        surnameText=new JTextField(30);
        surnameText.setBorder(loweredBorder);
        middle.add(surnameText, getConstraints(0, 3, 4, 1, GridBagConstraints.WEST));

        //DOB label
        dob=new JLabel("Date of Birth");
        middle.add(dob, getConstraints(0, 4, 2, 1, GridBagConstraints.WEST));

        //DOB Labels
        day=new JLabel("Day");
        middle.add(day, getConstraints(0, 5, 1, 1, GridBagConstraints.WEST));

        month=new JLabel("Month");
        middle.add(month, getConstraints(1, 5, 1, 1, GridBagConstraints.WEST));

        year=new JLabel("Year");
        middle.add(year, getConstraints(2, 5, 1, 1, GridBagConstraints.WEST));

        //DOB Combo boxes
        dayCombo=new JComboBox<>(daysIn);
        dayCombo.setBorder(loweredBorder);
        middle.add(dayCombo,getConstraints(0, 6, 1, 1, GridBagConstraints.WEST));

        monthCombo=new JComboBox<>(months);
        monthCombo.setBorder(loweredBorder);
        middle.add(monthCombo, getConstraints(1, 6, 1, 1, GridBagConstraints.WEST));

        yearCombo=new JComboBox<>(years);
        yearCombo.setBorder(loweredBorder);
        middle.add(yearCombo,getConstraints(2, 6, 1, 1, GridBagConstraints.WEST));

        //Label
        or=new JLabel("OR");
        middle.add(or,getConstraints(0, 8, 1, 1, GridBagConstraints.WEST));

        //Patient Number
        patientNum = new JLabel("Patient Number");
        middle.add(patientNum, getConstraints(0, 10, 4, 1, GridBagConstraints.WEST));
        //text field
        patientNumText= new JTextField(30);
        patientNumText.setBorder(loweredBorder);
        middle.add(patientNumText, getConstraints(0, 11, 4, 1, GridBagConstraints.WEST));

        //Password
        password = new JLabel("Admin/Medical Password");
        middle.add(password, getConstraints(0, 12, 4, 1, GridBagConstraints.WEST));
        //text field
        passwordText = new JTextField(30);
        passwordText.setBorder(loweredBorder);
        middle.add(passwordText, getConstraints(0, 13, 4, 1, GridBagConstraints.WEST));


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

        // Add new patient button
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

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(cancel))
        {
            f.setVisible(false);

        } else if (e.getSource().equals(confirm)){
            {
                pList.removeAll(pList);
                PatientRecord patientRecord = new PatientRecord();
                pList.addAll(patientRecord.getPatientList());
                boolean test = false;
                String dayC = (String) dayCombo.getSelectedItem();
                String monthC = (String) monthCombo.getSelectedItem();
                String yearC = (String) yearCombo.getSelectedItem();
                int choice = 0, passwordC = 0;
                if ((firstNameText.getText().equals("")) && (surnameText.getText().equals("")) && (patientNumText.getText().equals("")) && (passwordText.getText().equals(""))) {
                    JOptionPane.showMessageDialog(null, "Patient Number or Patient Info are Required Fields");
                } else if(patientNumText.getText().equals("") && passwordText.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Patient Number and Password are Required Fields");
                }else if((firstNameText.getText().equals("")) && (surnameText.getText().equals("")) && (passwordText.getText().equals(""))) {
                    JOptionPane.showMessageDialog(null, "Patient Information and Password are Required Fields");
                }else{
                    if (patientNumText.getText().equals("")) {
                        for (int i = 0; i < pList.size(); i++) {
                            if (firstNameText.getText().equalsIgnoreCase(pList.get(i).getPatientFName()) && surnameText.getText().equalsIgnoreCase(pList.get(i).getPatientLName()) &&
                                    dayC.equals(pList.get(i).getDOB().charAt(0) + "" + pList.get(i).getDOB().charAt(1)) && monthC.equals(pList.get(i).getDOB().charAt(3) + "" +
                                    "" + pList.get(i).getDOB().charAt(4) + "" + pList.get(i).getDOB().charAt(5)) && yearC.equals(pList.get(i).getDOB().charAt(7) + "" + pList.get(i).getDOB().charAt(8) + "" +
                                    "" + pList.get(i).getDOB().charAt(9) + "" + pList.get(i).getDOB().charAt(10))) {
                                choice = pList.get(i).getPatientNumber();
                                try {
                                    passwordC = Integer.parseInt(passwordText.getText());
                                    test=true;
                                } catch (NumberFormatException nf) {
                                    JOptionPane.showMessageDialog(null, "Patient Number & Password must be Numeric");
                                    patientNumText.setText("");
                                    passwordText.setText("");
                                }
                            }
                        }if(test==false){
                            JOptionPane.showMessageDialog(null, "Patient Does Not Exist");
                        }
                    }
                    if (!patientNumText.getText().equals("") ) {
                        try {
                            choice = Integer.parseInt(patientNumText.getText());
                            passwordC = Integer.parseInt(passwordText.getText());
                            test=true;
                        } catch (NumberFormatException nf) {
                            JOptionPane.showMessageDialog(null, "Patient Number & Password must be Numeric");
                            patientNumText.setText("");
                            passwordText.setText("");
                        }
                    }
                    if (test == true) {
                        Security security = new Security(selection, choice, passwordC);
                        firstNameText.setText("");
                        surnameText.setText("");
                        patientNumText.setText("");
                        passwordText.setText("");
                        if (security.passCheck() == -1 || security.passCheck() == 2) {
                            passwordText.setText("");
                            JOptionPane.showMessageDialog(null, "Password is Incorrect");
                        } else if (security.patientNumCheck()) {
                        }
                        if (!security.patientNumCheck()) {
                            patientNumText.setText("");
                            passwordText.setText("");
                            JOptionPane.showMessageDialog(null, "Patient Does Not Exist");
                        } else if (security.patientNumCheck()) {
                        }
                    }
                }
            }
        } else if (e.getSource().equals(add)){
            AppointmentOperations ao=new AppointmentOperations();
            AddPatientGUI addPatientGUI=new AddPatientGUI(1,ao.getCurrVal()+1);
            f.setVisible(false);
        }
    }
}
