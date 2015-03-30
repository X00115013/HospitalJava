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
public class PrescriptionGUI extends JFrame implements ActionListener {
    String[] list1 = {"XRay :", "MRI Scan :", "CT Scan :"};
    String[] list2 = {"Radiology :", "Pediatrics :", "Surgery :"};
    JButton confirm;
    JButton cancel;

    JLabel patientNum;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();

    JTextField pNumText;
    JTextField field2,conNum;
    JFrame f;

    JComboBox<String> combo1;
    JComboBox<String> combo2;

    public PrescriptionGUI(int patientNumIn) {



        f = new JFrame();
        f.setTitle("Prescription");
        f.setLayout(new FlowLayout(FlowLayout.LEFT));
        f.setSize(630,550);
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
        label5 = new JLabel("Prescription");
        title.add(label5);
        label5.setFont(new Font("Arial", Font.BOLD, 32));

        JPanel ID=new JPanel(new FlowLayout(FlowLayout.CENTER));
        //labels
        patientNum = new JLabel("\tPatient Number");
        ID.add(patientNum);
        //text field
        pNumText = new JTextField(5);
        pNumText.setText(Integer.toString(patientNumIn));
        pNumText.setBorder(loweredBorder);
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

        label3 = new JLabel("Medical Required");
        middle.add(label3, getConstraints(0, 2, 1, 1, GridBagConstraints.WEST));

        combo1 = new JComboBox<String>(list1);
        combo1.setPreferredSize(new Dimension(400, 20));
        middle.add(combo1, getConstraints(0, 3, 3, 1, GridBagConstraints.WEST));
        combo1.addActionListener(this);


        label4 = new JLabel("Dose Required");
        middle.add(label4, getConstraints(0, 4, 1, 1, GridBagConstraints.WEST));


        combo2 = new JComboBox<String>(list2);
        combo2.setPreferredSize(new Dimension(400, 20));
        middle.add(combo2, getConstraints(0, 5, 3, 1, GridBagConstraints.WEST));
        combo2.addActionListener(this);

        //Reason for Visit label
        label2 = new JLabel("Consultant Number");
        middle.add(label2, getConstraints(0, 6, 1, 1, GridBagConstraints.WEST));

        conNum = new JTextField(40);
        conNum.setBorder(loweredBorder);
        middle.add(conNum, getConstraints(0, 7, 2, 1, GridBagConstraints.WEST));

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
                String medEquip = (String) combo1.getSelectedItem();
                if (medEquip.equals("XRay :")) {
                    catcher = 1;
                } else if (medEquip.equals("MRI Scan :")) {
                    catcher = 2;
                } else if (medEquip.equals("CT Scan :")) {
                    catcher = 3;
                }
                String conEquip = (String) combo2.getSelectedItem();
                if (conEquip.equals("Radiology :")) {
                    catcher2 = 1;
                } else if (conEquip.equals("Pediatrics :")) {
                    catcher2 = 2;
                } else if (conEquip.equals("Surgery :")) {
                    catcher2 = 3;
                }
            } catch (InputMismatchException im) {
                System.out.println(im);
            }
        }
    }
}