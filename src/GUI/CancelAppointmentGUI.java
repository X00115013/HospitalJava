package GUI;

import Model.Appointment;
import Model.PatientRecord;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Thomas Murray on 28/03/2015.
 *
 * This class allows the patient to cancel an appointment and cancelling subsequent tables
 */
public class CancelAppointmentGUI extends JFrame implements ActionListener {
    JButton confirm, cancel;
    JLabel firstName, surname, appNum, day, month, year, dob, title, or;
    JTextField firstNameText, surnameText, appNumText;
    JFrame f;
    private String[] daysIn = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    private String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private String[] years = new String[76];
    private int setter = 1940;
    JComboBox<String> dayCombo, monthCombo, yearCombo;
    private ArrayList<Appointment> appList = new ArrayList<>();
    private ArrayList<PatientRecord>pList=new ArrayList<>();


    public CancelAppointmentGUI() {
        for (int i = 0; i < 76; i++) {
            years[i] = Integer.toString(setter);
            setter++;
        }

        f = new JFrame();
        f.setTitle("Cancel Appointment");
        f.setSize(610, 660);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        Border loweredBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        JPanel holder = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //Clock
        Clock.DigitalClock clockD = new Clock.DigitalClock();
        JPanel clock = new JPanel(new FlowLayout(FlowLayout.CENTER));
        clock.add(clockD);

        //Title
        title = new JLabel("Cancel Appointment");
        clock.add(title);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        holder.add(clock);
        JPanel holder2 = new JPanel(new GridLayout(2, 1));
        JPanel test2 = new JPanel(new GridLayout(1, 1));
        JPanel middle = new JPanel(new GridBagLayout());
        test2.add(middle, BorderLayout.WEST);

        //App num labels
        appNum = new JLabel("Appointment Number");
        middle.add(appNum, getConstraints(0, 0, 1, 1, (GridBagConstraints.WEST)));

        //App num text field
        appNumText = new JTextField(50);
        appNumText.setBorder(loweredBorder);
        or = new JLabel("OR");
        middle.add(or, getConstraints(0, 2, 1, 1, (GridBagConstraints.WEST)));
        middle.add(appNumText, getConstraints(0, 1, 1, 1, (GridBagConstraints.WEST)));

        //first name label
        firstName = new JLabel("First Name");
        middle.add(firstName, getConstraints(0, 3, 1, 1, (GridBagConstraints.WEST)));

        //first name text field
        firstNameText = new JTextField(50);
        firstNameText.setBorder(loweredBorder);
        middle.add(firstNameText, getConstraints(0, 4, 1, 1, (GridBagConstraints.WEST)));

        //surname label
        surname = new JLabel("Surname");
        middle.add(surname, getConstraints(0, 5, 1, 1, (GridBagConstraints.WEST)));

        //surname text field
        surnameText = new JTextField(50);
        surnameText.setBorder(loweredBorder);
        middle.add(surnameText, getConstraints(0, 6, 1, 1, (GridBagConstraints.WEST)));
        holder2.add(test2);


        //DOB labels
        JPanel test = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel dobs = new JPanel(new GridBagLayout());
        test.add(dobs, BorderLayout.SOUTH);
        dob = new JLabel("Patient DOB");
        dobs.add(dob, getConstraints(0, 0, 1, 1, GridBagConstraints.WEST));
        day = new JLabel("Day");
        dobs.add(day, getConstraints(0, 2, 1, 1, GridBagConstraints.WEST));
        month = new JLabel("Month");
        dobs.add(month, getConstraints(1, 2, 1, 1, GridBagConstraints.WEST));
        year = new JLabel("Year");
        dobs.add(year, getConstraints(2, 2, 1, 1, GridBagConstraints.WEST));

        //DOB Combo boxes
        dayCombo = new JComboBox<>(daysIn);
        dayCombo.setBorder(loweredBorder);
        dobs.add(dayCombo, getConstraints(0, 3, 1, 1, GridBagConstraints.WEST));
        monthCombo = new JComboBox<>(months);
        monthCombo.setBorder(loweredBorder);
        dobs.add(monthCombo, getConstraints(1, 3, 1, 1, GridBagConstraints.WEST));
        yearCombo = new JComboBox<>(years);
        yearCombo.setBorder(loweredBorder);
        dobs.add(yearCombo, getConstraints(2, 3, 1, 1, GridBagConstraints.WEST));

        // Confirm button
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        dobs.add(confirm, getConstraints(0, 4, 1, 1, GridBagConstraints.WEST));

        // Cancel button
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        dobs.add(cancel, getConstraints(1, 4, 1, 1, GridBagConstraints.WEST));

        holder2.add(test);
        holder.add(holder2);
        f.add(holder);
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
                appList.removeAll(appList);
                pList.removeAll(pList);
                Appointment appointment = new Appointment();
                PatientRecord patientRecord = new PatientRecord();
                appList.addAll(appointment.appArray());
                pList.addAll(patientRecord.getPatientList());
                boolean test = false, test2 = false;
                String dayC = (String) dayCombo.getSelectedItem();
                String monthC = (String) monthCombo.getSelectedItem();
                String yearC = (String) yearCombo.getSelectedItem();
                int choice = 0;

                if ((firstNameText.getText().equals("")) && (surnameText.getText().equals("")) && (appNumText.getText().equals(""))) {
                    JOptionPane.showMessageDialog(null, "Appointment Number or Patient Info are Required Fields");
                } else {

                    if(appNumText.getText().equals("")) {
                        for (int i = 0; i < pList.size(); i++) {
                            if (firstNameText.getText().equals(pList.get(i).getPatientFName()) && surnameText.getText().equals(pList.get(i).getPatientLName()) &&
                                    dayC.equals(pList.get(i).getDOB().charAt(0) + "" + pList.get(i).getDOB().charAt(1)) && monthC.equals(pList.get(i).getDOB().charAt(3) + "" +
                                    "" + pList.get(i).getDOB().charAt(4) + "" + pList.get(i).getDOB().charAt(5)) && yearC.equals(pList.get(i).getDOB().charAt(7) + "" + pList.get(i).getDOB().charAt(8) + "" +
                                    "" + pList.get(i).getDOB().charAt(9) + "" + pList.get(i).getDOB().charAt(10))) {
                                for (int j = 0; j < appList.size(); j++) {
                                    if (appList.get(j).appNumber == pList.get(i).getAppID()) {
                                        test2 = true;
                                        choice = pList.get(i).getAppID();
                                    }
                                }
                            }
                            }if(test2==false){JOptionPane.showMessageDialog(null, "Appointment does not exist ");
                                appNumText.setText("");
                            }
                        }
                    else if ((!appNumText.getText().equals(""))) {
                        try {
                            for (int i = 0; i < appList.size(); i++) {
                                if (appList.get(i).appNumber == Integer.parseInt(appNumText.getText())) {
                                    choice = Integer.parseInt(appNumText.getText());
                                    test = true;
                                }
                            }
                        } catch (NumberFormatException nf) {
                            JOptionPane.showMessageDialog(null, "Appointment Number must be a Number ");
                            appNumText.setText("");
                        }
                        if (test == false) {
                            JOptionPane.showMessageDialog(null, "Appointment does not exist ");
                            appNumText.setText("");
                        }
                    }
                    if (test == true || test2 == true) {
                        appointment.cancelAppointment(choice);
                        f.setVisible(false);
                    }
                }
            }
        }
    }


