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
public class healthInsuranceGUI extends JFrame implements ActionListener {
    String[] list1 = {"Bronze :", "Silver :", "Gold :"};
    JButton confirm;
    JButton cancel;

    JLabel patientNum;
    JLabel policyNum,companyName,expiry,xMonth,xYear,coverageType,titleF;
    JTextField pNumText,companyNameText,xMonthText,xYearText;
    JCheckBox valid;
    JFrame f;

    JComboBox<String> coverageTypeCombo;

    public healthInsuranceGUI(int patientNumIn) {
        f = new JFrame();
        f.setTitle("Health Insurance");
        f.setLayout(new FlowLayout(FlowLayout.LEFT));
        f.setSize(600, 520);
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
        pNumText.setEditable(false);
        pNumText.setText(Integer.toString(patientNumIn));
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
        companyName=new JLabel("Company Name ");
        middle.add(companyName, getConstraints(0, 0, 1, 1, GridBagConstraints.WEST));

        companyNameText = new JTextField(35);
        companyNameText.setBorder(loweredBorder);
        middle.add(companyNameText, getConstraints(0, 1, 4, 1, GridBagConstraints.WEST));

        policyNum = new JLabel("Policy Number");
        middle.add(policyNum, getConstraints(0, 2, 3, 1, GridBagConstraints.WEST));

        pNumText = new JTextField(35);
        pNumText.setBorder(loweredBorder);
        middle.add(pNumText, getConstraints(0, 3, 3, 1, GridBagConstraints.WEST));

        expiry = new JLabel("Expiry Date");
        middle.add(expiry, getConstraints(0, 5, 1, 1, GridBagConstraints.WEST));

        xMonth = new JLabel("Month");
        middle.add(xMonth, getConstraints(0, 6, 1, 1, GridBagConstraints.WEST));

        xYear = new JLabel("Year");
        middle.add(xYear, getConstraints(1, 6, 1, 1, GridBagConstraints.WEST));

        xMonthText = new JTextField(15);
        xMonthText.setBorder(loweredBorder);
        middle.add(xMonthText, getConstraints(0, 7, 1, 1, GridBagConstraints.WEST));

        xYearText = new JTextField(15);
        xYearText.setBorder(loweredBorder);
        middle.add(xYearText, getConstraints(1, 7, 1, 1, GridBagConstraints.WEST));

        coverageType = new JLabel("Card Type");
        middle.add(coverageType, getConstraints(0, 8, 1, 1, GridBagConstraints.WEST));

        coverageTypeCombo = new JComboBox<String>(list1);
        coverageTypeCombo.setPreferredSize(new Dimension(120, 20));
        middle.add(coverageTypeCombo, getConstraints(0, 9, 3, 1, GridBagConstraints.WEST));
        coverageTypeCombo.addActionListener(this);


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