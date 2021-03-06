package GUI;

import DataBase.AppointmentOperations;
import Model.Appointment;
import Model.MedicalRecord;
import Model.PatientRecord;
import Model.Prescription;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Thomas Murray on 20/03/2015.
 *
 * This class is used to allow the user to view and update the patients Administration record
 */
public class PatientAdminRecGUI extends JFrame implements ActionListener
{
    JButton update,delete,cancel,refresh;
    JLabel patientNum,label5;
    JTextField patientText;
    JTextArea patientAdminInformation;
    JScrollPane scroll;
    private int patientNumberIn;
    private ArrayList<PatientRecord>pList=new ArrayList<>();
    private ArrayList<Appointment> appList=new ArrayList<>();
    private PatientRecord patientRecord;
    private Appointment apt;
    private String record="This is meant to be the patient Admin Record";
    private AppointmentOperations ao;
    JFrame f;

    public PatientAdminRecGUI(int patientNumIn)
    {
        patientNumberIn=patientNumIn;
        f = new JFrame();
        f.setTitle("Admin Records");
        f.setSize(805, 1000);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        Border loweredBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        JPanel holder=new JPanel(new GridLayout(1,1));
        JPanel topSection=new JPanel(new GridLayout(1,3));

        //Clock
        Clock.DigitalClock clockD=new Clock.DigitalClock();
        JPanel clock = new JPanel(new FlowLayout(FlowLayout.LEFT));
        clock.add(clockD);

        //Title
        JPanel title=new JPanel(new FlowLayout(FlowLayout.CENTER));
        label5 = new JLabel("Patient Admin Records");
        title.add(label5);
        label5.setFont(new Font("Arial", Font.BOLD, 24));
        JPanel ID=new JPanel(new FlowLayout(FlowLayout.CENTER));

        //patient num label
        patientNum = new JLabel("\tPatient Number");
        ID.add(patientNum);

        //patient num text field
        patientText = new JTextField(5);
        patientText.setText(Integer.toString(patientNumIn));
        patientText.setBorder(loweredBorder);
        patientText.setEditable(false);
        ID.add(patientText);
        topSection.add(clock);
        topSection.add(title);
        topSection.add(ID);
        holder.add(topSection);

        JPanel textArea=new JPanel(new FlowLayout(FlowLayout.CENTER));

        //Central text area
        patientAdminInformation = new JTextArea(38,68);
        patientAdminInformation.setBorder(loweredBorder);
        patientAdminInformation.setFont(new Font("Arial", Font.ITALIC, 14));
        patientAdminInformation.setText(setTextArea());
        scroll = new JScrollPane(patientAdminInformation);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textArea.add(scroll);

        JPanel holder2=new JPanel(new GridLayout(1,1));
        JPanel test =new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel dobs = new JPanel(new GridBagLayout());

        // Update button
        update = new JButton("Update");
        update.addActionListener(this);
        dobs.add(update, getConstraints(0, 4, 1, 1, GridBagConstraints.WEST));

        // Delete button
        delete = new JButton("Delete");
        delete.addActionListener(this);
        dobs.add(delete, getConstraints(1, 4, 1, 1, GridBagConstraints.WEST));

        // Cancel button
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        dobs.add(cancel, getConstraints(2, 4, 1, 1, GridBagConstraints.WEST));


        // Refresh button
        refresh = new JButton("Refresh Record");
        refresh.addActionListener(this);
        dobs.add(refresh, getConstraints(7, 4, 1, 1, GridBagConstraints.WEST));
        test.add(dobs);
        holder2.add(test);

        f.setLayout(new FlowLayout(FlowLayout.LEFT));
        f.add(holder);
        f.add(textArea);
        f.add(holder2);
        f.setVisible(true);
    }
    private GridBagConstraints getConstraints(int gridx, int gridy, int gridwidth, int gridheight, int anchor) {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 10, 10);
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
        c.anchor = anchor;
        return c;
    }
    //Pulling patient administration information from the database and converting to a string to be displayed
    public String setTextArea(){
        pList.removeAll(pList);
        appList.removeAll(appList);
        patientRecord=new PatientRecord();
        apt=new Appointment();
        pList.addAll(patientRecord.getPatientList());
        appList.addAll(apt.appArray());
        ao=new AppointmentOperations();
        for (int i = 0; i <pList.size() ; i++) {
            if (pList.get(i).getPatientNumber() == patientNumberIn) {
                record = "\n\n--------------------------------------------------------------------------------------------------------------------\n" +
                        "\n   Patient Number is      \t" + pList.get(i).getPatientNumber() + "\n" +
                        "\n--------------------------------------------------------------------------------------------------------------------\n\n" +
                        "\n   Patient Name is         \t" + pList.get(i).getPatientFName() + " " + pList.get(i).getPatientLName() + "\n" +

                        "\n   Patient Address is     \t" + pList.get(i).getPatientAddress() + "\n" +

                        "\n   Patient Occupation is \t" + pList.get(i).getOccupation() + "\n" +

                        "\n   Patient Gender is       \t" + pList.get(i).getGender() + "\n" +

                        "\n   Patient DOB is          \t" + pList.get(i).getDOB() + "\n" +

                        "\n   Patient Email is        \t" + pList.get(i).getEmail() + "\n" +

                        "\n   Patient Phone is        \t" + pList.get(i).getPhone() + "\n\n" +
                        "\n--------------------------------------------------------------------------------------------------------------------\n"+
                        "\n   Patient is currently    \t" + pList.get(i).getCheckedIn();
            for (int j = 0; j < appList.size(); j++) {
                if (appList.get(j).getAppNumber() == pList.get(i).getAppID()) {
                    record += record = "\n\n--------------------------------------------------------------------------------------------------------------------\n" +
                            "\n   Patient Appointment Number\t" + appList.get(j).getAppNumber() + "\n" +
                            "\n   Booked Medical Equip      \t" + appList.get(j).getMedicalEquip() + "\n" +
                            "\n   Booked Consultant         \t" + appList.get(j).getConsultantType() + "\n" +
                            "\n   Date & Time Booked        \t" + appList.get(j).getDate() + "\n" +
                            "\n   Appointment Time          \t" +apt.getAppointmentTime(ao.getAppointmentTime(appList.get(j).getAppNumber()))+"\n"+
                            "\n--------------------------------------------------------------------------------------------------------------------\n\n";
                    }
                }ao.appointmentOperationsClose();
            }
        }

        return record;
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cancel)) {
           f.setVisible(false);
        } else if (e.getSource().equals(update)){
            AddPatientGUI addPatientGUI=new AddPatientGUI(2,patientNumberIn);
        }else if (e.getSource().equals(delete))
        {DeletePatient deletePatient=new DeletePatient(patientRecord,patientNumberIn);
        }else if (e.getSource().equals(refresh)) {
            patientAdminInformation.setText(setTextArea());
            }
        }
    }
