package GUI;

//import DataBase.AppointmentOperations;
//import DataBase.PatientOperations;
//import Model.Appointment;
//import Model.PatientRecord;

import Model.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by Thomas Murray on 17/03/2015.
 */
public class AppointmentGUI extends JFrame implements ActionListener {
    String[] list1;
    String[] list2;
    JButton confirm;
    JButton cancel,cancelApt;
    private ArrayList<Equipment> eqList=new ArrayList<>();
    private ArrayList<Consultants>conList=new ArrayList<>();
    private Equipment equipment;
    private Consultants consultants;
    private int patientNumberIn;

    JLabel aptNum;
    JLabel reason;
    JLabel medEquip;
    JLabel conType;
    JLabel label5;

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();

    JTextField aptNumText;
    JTextField field2;
    JTextArea textArea;
    JFrame f;

    JComboBox<String> combo1;
    JComboBox<String> combo2;

    public AppointmentGUI(int patientNumIn) {
        patientNumberIn=patientNumIn;
        f = new JFrame();
        f.setTitle("Appointment");
        f.setLayout(new FlowLayout(FlowLayout.LEFT));
        f.setSize(655, 660);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        Border loweredBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);



        JPanel holder=new JPanel(new GridLayout(1,1));
        JPanel topSection=new JPanel(new GridLayout(1,3));

        Clock.DigitalClock clockD=new Clock.DigitalClock();
        JPanel clock = new JPanel(new FlowLayout(FlowLayout.LEFT));
        clock.add(clockD);

        JPanel title=new JPanel(new FlowLayout(FlowLayout.CENTER));
        label5 = new JLabel("Appointment");
        title.add(label5);
        label5.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel ID=new JPanel(new FlowLayout(FlowLayout.CENTER));
        //labels
        aptNum = new JLabel("\tAppointment Number");
        ID.add(aptNum);
        //text field
        aptNumText = new JTextField(5);
        aptNumText.setBorder(loweredBorder);
        Appointment appointment=new Appointment();
        aptNumText.setText(Integer.toString(appointment.getSize()));
        aptNumText.setEditable(false);
        ID.add(aptNumText);


        topSection.add(clock);
        topSection.add(title);
        topSection.add(ID);
//        f.add(topSection);

        holder.add(topSection);


        equipment=new Equipment();
        eqList.addAll(equipment.getEquipments());
        list1 = new String[eqList.size()];
        for (int i = 0; i < eqList.size(); i++) {
            list1[i] = eqList.get(i).getEqName();
        }

        consultants=new Consultants();
        conList.addAll(consultants.getConsultants());
        list2 = new String[conList.size()];
        for (int i = 0; i < conList.size(); i++) {
            list2[i] = conList.get(i).getConSpeciality();
        }

        //middle
        JPanel holder2=new JPanel(new GridLayout(2,1));
        JPanel middle = new JPanel(new GridBagLayout());

        holder2.add(middle, BorderLayout.CENTER);

        //Reason for Visit label
        reason = new JLabel("Reason for Visit");
        middle.add(reason, getConstraints(0, 1, 1, 1, GridBagConstraints.WEST));

        textArea = new JTextArea(10,50);
        textArea.setBorder(loweredBorder);
        middle.add(textArea, getConstraints(0, 2, 2, 1, GridBagConstraints.WEST));

        medEquip = new JLabel("Medical Equipment Required");
        middle.add(medEquip, getConstraints(0, 3, 1, 1, GridBagConstraints.WEST));

        combo1 = new JComboBox<String>(list1);
        combo1.setPreferredSize(new Dimension(300, 20));
        middle.add(combo1, getConstraints(0, 4, 3, 1, GridBagConstraints.WEST));
        combo1.addActionListener(this);


        conType = new JLabel("Consultant Type Required");
        middle.add(conType, getConstraints(0, 5, 1, 1, GridBagConstraints.WEST));


        combo2 = new JComboBox<String>(list2);
        combo2.setPreferredSize(new Dimension(300, 20));
        middle.add(combo2, getConstraints(0, 6, 3, 1, GridBagConstraints.WEST));
        combo2.addActionListener(this);


        //bottom
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        holder2.add(bottom);
        // Confirm button
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        bottom.add(confirm);


        // Cancel button
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        bottom.add(cancel);

        // Cancel Appointment button
        cancelApt = new JButton("Cancel Appointment");
        cancelApt.addActionListener(this);
        bottom.add(cancelApt);

        f.add(holder);
        f.add(holder2);
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
            f.setVisible(false);

        } else if (e.getSource().equals(confirm)) {
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
            Appointment app = new Appointment(textArea.getText(), catcher, catcher2,patientNumberIn);
            reason.setText("");
            f.setVisible(false);
            AppointmentDetailsGUI appointmentDetailsGUI=new AppointmentDetailsGUI(app,Integer.parseInt(aptNumText.getText()));


        } else if (e.getSource().equals(cancelApt)) {
            CancelAppointmentGUI cancelAppointmentGUI=new CancelAppointmentGUI();
        }
    }
}


