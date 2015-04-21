package GUI;

        import Model.Appointment;
        import Model.MedicalCard;

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
    private JButton confirm;
    private JButton cancel;
    private JLabel patientNum;
    private JLabel gmsNum,patientName,expiry,xMonth,xYear,ppsNum,gender,titleF;
    private JTextField pNumText,gmsNumText,patientNameText,ppsNumText;
    private JCheckBox male,female,validate;
    private JFrame f;
    private JComboBox<String> coverageTypeCombo;
    private JComboBox<String> xMonthTextCombo;
    private JComboBox<String> xYearTextCombo;
    private MedicalCard medicalCard;
    private ArrayList<MedicalCard>medCList=new ArrayList<>();
    private String[]list={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    private String[]list2={"2015","2016","2017"};
    private int patientNumber;

    public MedicalCardGUI(int patientNumIn) {
        patientNumber=patientNumIn;
        f = new JFrame();
        f.setTitle("Medical Card");
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
        titleF = new JLabel("Medical Card");
        title.add(titleF);
        titleF.setFont(new Font("Arial", Font.BOLD, 31));

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

        xMonthTextCombo = new JComboBox<>(list);
        middle.add(xMonthTextCombo, getConstraints(0, 8, 1, 1, GridBagConstraints.WEST));

        xYearTextCombo = new JComboBox<>(list2);
        middle.add(xYearTextCombo, getConstraints(1, 8, 1, 1, GridBagConstraints.WEST));

        gender = new JLabel("Gender");
        middle.add(gender, getConstraints(0, 9, 1, 1, GridBagConstraints.WEST));

        male=new JCheckBox("Male");
        male.addActionListener(this);
        middle.add(male, getConstraints(0, 10, 1, 1, GridBagConstraints.WEST));


        female=new JCheckBox("Female");
        female.addActionListener(this);
        middle.add(female, getConstraints(1, 10, 1, 1, GridBagConstraints.WEST));


        medCList.removeAll(medCList);
        medicalCard=new MedicalCard();
        medCList.addAll(medicalCard.getMedCardList());
        for (int i = 0; i < medCList.size(); i++) {
            if(patientNumber==medCList.get(i).getPatientNum()){
                System.out.println("Getting to here "+medCList.get(i).getHolderName());
                patientNameText.setText(medCList.get(i).getHolderName());
                gmsNumText.setText(Integer.toString(medCList.get(i).getGMSNumber()));
                ppsNumText.setText(Integer.toString(medCList.get(i).getPPS()));
                xMonthTextCombo.setSelectedItem(medCList.get(i).getValidTo().charAt(0) + "" + medCList.get(i).getValidTo().charAt(1) + "" + medCList.get(i).getValidTo().charAt(2));
                xYearTextCombo.setSelectedItem(medCList.get(i).getValidTo().charAt(4) + "" + medCList.get(i).getValidTo().charAt(5) + "" +
                        "" + medCList.get(i).getValidTo().charAt(6) +""+medCList.get(i).getValidTo().charAt(7));

                if(medCList.get(i).getGender().equals("male")){
                    male.setSelected(true);
                }else {
                    female.setSelected(true);
                }
            }
        }




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
        validate=new JCheckBox("Valid");
        validate.addActionListener(this);
        bottom.add(validate);


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
            boolean test=false;
            medCList.removeAll(medCList);
            medicalCard=new MedicalCard();
            medCList.addAll(medicalCard.getMedCardList());
            for (int i = 0; i < medCList.size(); i++) {
                if(medCList.get(i).getPatientNum()==patientNumber){
                    test=true;
                }

            }
            String xComboM=(String)xMonthTextCombo.getSelectedItem();
            String xComboY=(String)xYearTextCombo.getSelectedItem();
            String catcher="";
            int intCatcher=0,intCatcher2=0;
            if(male.isSelected()){
                catcher="male";
            }else if(female.isSelected()){
                catcher="female";
            }
            if(patientNameText.getText().equals("")||gmsNumText.getText().equals("")||ppsNumText.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Holders Name, GMS Number & PPS Code are Required Fields");
            }else {
                try {
                    intCatcher=Integer.parseInt(gmsNumText.getText());
                    intCatcher2=Integer.parseInt(ppsNumText.getText());
                    if(test==false) {
                        MedicalCard medicalCDAdd = new MedicalCard(patientNumber, intCatcher, intCatcher2, catcher, (xComboM + "-" + xComboY), patientNameText.getText());
                    }else{
                        MedicalCard medicalCDAdd = new MedicalCard(patientNumber, intCatcher, intCatcher2, catcher, (xComboM + "-" + xComboY), patientNameText.getText(),1);
                    }
                    f.setVisible(false);
                }catch (NumberFormatException nf){
                    JOptionPane.showMessageDialog(null, "GMS Number & PPS Code must be Numeric");
                    gmsNumText.setText("");
                    ppsNumText.setText("");
                }
            }
        } else if (e.getSource().equals(male)) {
            female.setSelected(false);

        } else if (e.getSource().equals(female)) {
            male.setSelected(false);


        }else if (e.getSource().equals(validate)) {
            PaymentGUI.valid=true;


        }
    }

}