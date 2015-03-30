package GUI;

//import DataBase.AppointmentOperations;
//import DataBase.PatientOperations;
//import Model.Appointment;
//import Model.PatientRecord;

        import Model.Appointment;

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
public class MedicalCardGUI extends JFrame implements ActionListener {
    JButton confirm;
    JButton cancel;

    JLabel patientNum;
    JLabel gmsNum,patientName,expiry,xMonth,xYear,dOB,day,month,year,ppsNum,gender,titleF;
    JTextField pNumText,gmsNumText,patientNameText,xMonthText,xYearText,dayText,monthText,yearText,ppsNumText;
    JCheckBox male,female,valid;
    JFrame f;

    JComboBox<String> coverageTypeCombo;

    public MedicalCardGUI(int patientNumIn) {
        f = new JFrame();
        f.setTitle("Health Insurance");
        f.setLayout(new FlowLayout(FlowLayout.LEFT));
        f.setSize(620, 620);
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
        titleF = new JLabel("Health Insurance");
        title.add(titleF);
        titleF.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel ID=new JPanel(new FlowLayout(FlowLayout.CENTER));
        //labels
        patientNum = new JLabel("\tPatient Number");
        ID.add(patientNum);
        //text field
        pNumText = new JTextField(5);
        pNumText.setBorder(loweredBorder);
        pNumText.setText(Integer.toString(patientNumIn));
        pNumText.setEditable(false);
        ID.add(pNumText);


        topSection.add(clock);
        topSection.add(title);
        topSection.add(ID);
//        f.add(topSection);

        holder.add(topSection);



        //middle
        JPanel holder2=new JPanel(new GridLayout(2,1));
        JPanel middle = new JPanel(new GridBagLayout());

        holder2.add(middle, BorderLayout.CENTER);

        //Reason for Visit label
        patientName=new JLabel("Full Name ");
        middle.add(patientName, getConstraints(0, 0, 1, 1, GridBagConstraints.WEST));

        patientNameText = new JTextField(35);
        patientNameText.setBorder(loweredBorder);
        middle.add(patientNameText, getConstraints(0, 1, 6, 1, GridBagConstraints.WEST));

        gmsNum = new JLabel("GMS Number");
        middle.add(gmsNum, getConstraints(0, 2, 1, 1, GridBagConstraints.WEST));

        gmsNumText = new JTextField(35);
        gmsNumText.setBorder(loweredBorder);
        middle.add(gmsNumText, getConstraints(0, 3, 6, 1, GridBagConstraints.WEST));

        ppsNum = new JLabel("PPS Number");
        middle.add(ppsNum, getConstraints(0, 4, 1, 1, GridBagConstraints.WEST));

        ppsNumText = new JTextField(35);
        ppsNumText.setBorder(loweredBorder);
        middle.add(ppsNumText, getConstraints(0, 5, 6, 1, GridBagConstraints.WEST));

        expiry = new JLabel("Expiry Date");
        middle.add(expiry, getConstraints(0, 6, 1, 1, GridBagConstraints.WEST));

        xMonth = new JLabel("Month");
        middle.add(xMonth, getConstraints(0, 7, 1, 1, GridBagConstraints.WEST));

        xYear = new JLabel("Year");
        middle.add(xYear, getConstraints(1, 7, 1, 1, GridBagConstraints.WEST));

        xMonthText = new JTextField(15);
        xMonthText.setBorder(loweredBorder);
        middle.add(xMonthText, getConstraints(0, 8, 1, 1, GridBagConstraints.WEST));

        xYearText = new JTextField(15);
        xYearText.setBorder(loweredBorder);
        middle.add(xYearText, getConstraints(1, 8, 1, 1, GridBagConstraints.WEST));

        gender = new JLabel("Gender");
        middle.add(gender, getConstraints(0, 9, 1, 1, GridBagConstraints.WEST));

        male=new JCheckBox("Male");
        male.addActionListener(this);
        middle.add(male, getConstraints(0, 10, 1, 1, GridBagConstraints.WEST));


        female=new JCheckBox("Female");
        female.addActionListener(this);
        middle.add(female, getConstraints(1, 10, 1, 1, GridBagConstraints.WEST));



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

        //Valid Check
        valid=new JCheckBox("Valid");
        valid.addActionListener(this);
        bottom.add(valid);


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
            int catcher = 0, catcher2 = 0;
            try {
                String medEquip = (String) coverageTypeCombo.getSelectedItem();
                if (medEquip.equals("XRay :")) {
                    catcher = 1;
                } else if (medEquip.equals("MRI Scan :")) {
                    catcher = 2;
                } else if (medEquip.equals("CT Scan :")) {
                    catcher = 3;
                }
            } catch (InputMismatchException im) {
                System.out.println(im);
            }
            f.setVisible(false);
        }
    }
}