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
public class CreditCardGUI extends JFrame implements ActionListener {
    String[] list1 = {"Debit Card :", "VISA :", "MasterCard :"};
    JButton confirm;
    JButton cancel,validate;

    JLabel patientNum;
    JLabel holdersName;
    JLabel cardNum,secCode,expiry,xMonth,xYear,cardType,titleF;

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();

    JTextField pNumText,holdersNameText,cardNumText,secCodeText,xMonthText,xYearText;
    JFrame f;

    JComboBox<String> cardTypeCombo;

    public CreditCardGUI() {
        f = new JFrame();
        f.setTitle("Credit Cards");
        f.setLayout(new FlowLayout(FlowLayout.LEFT));
        f.setSize(520, 520);
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
        titleF = new JLabel("Credit Cards");
        title.add(titleF);
        titleF.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel ID=new JPanel(new FlowLayout(FlowLayout.CENTER));
        //labels
        patientNum = new JLabel("\tPatient Number");
        ID.add(patientNum);
        //text field
        pNumText = new JTextField(5);
        pNumText.setBorder(loweredBorder);
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
        holdersName = new JLabel("Card Holders Full Name ");
        middle.add(holdersName, getConstraints(0, 1, 1, 1, GridBagConstraints.WEST));

        holdersNameText = new JTextField(35);
        holdersNameText.setBorder(loweredBorder);
        middle.add(holdersNameText, getConstraints(0, 2, 4, 1, GridBagConstraints.WEST));

        cardNum = new JLabel("Card Number");
        middle.add(cardNum, getConstraints(0, 3, 3, 1, GridBagConstraints.WEST));

        secCode = new JLabel("Security code");
        middle.add(secCode, getConstraints(3, 3, 1, 1, GridBagConstraints.WEST));

        cardNumText = new JTextField(20);
        cardNumText.setBorder(loweredBorder);
        middle.add(cardNumText, getConstraints(0, 4, 3, 1, GridBagConstraints.WEST));

        secCodeText = new JTextField(10);
        secCodeText.setBorder(loweredBorder);
        middle.add(secCodeText, getConstraints(3, 4, 1, 1, GridBagConstraints.WEST));

        expiry = new JLabel("Expiry Date");
        middle.add(expiry, getConstraints(0, 5, 1, 1, GridBagConstraints.WEST));

        xMonth = new JLabel("Month");
        middle.add(xMonth, getConstraints(0, 6, 2, 1, GridBagConstraints.WEST));

        xYear = new JLabel("Year");
        middle.add(xYear, getConstraints(1, 6, 2, 1, GridBagConstraints.WEST));

        cardType = new JLabel("Card Type");
        middle.add(cardType, getConstraints(3, 6, 1, 1, GridBagConstraints.WEST));

        xMonthText = new JTextField(5);
        xMonthText.setBorder(loweredBorder);
        middle.add(xMonthText, getConstraints(0, 7, 2, 1, GridBagConstraints.WEST));

        xYearText = new JTextField(5);
        xYearText.setBorder(loweredBorder);
        middle.add(xYearText, getConstraints(2, 7, 2, 1, GridBagConstraints.WEST));


        cardTypeCombo = new JComboBox<String>(list1);
        cardTypeCombo.setPreferredSize(new Dimension(120, 20));
        middle.add(cardTypeCombo, getConstraints(3, 7, 3, 1, GridBagConstraints.WEST));
        cardTypeCombo.addActionListener(this);


        validate = new JButton(" Validate ");
        validate.addActionListener(this);
        middle.add(validate, getConstraints(0, 10, 3, 1, GridBagConstraints.WEST));



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
                String medEquip = (String) cardTypeCombo.getSelectedItem();
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