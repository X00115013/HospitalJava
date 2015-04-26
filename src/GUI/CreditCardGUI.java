package GUI;
        import Model.Appointment;
        import Model.CardDetails;
        import Model.CardValidation;
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
 *
 *This class is used to add and update credit card details
 *
 */
public class CreditCardGUI extends JFrame implements ActionListener {
    private String[] list1 = {"Debit Card", "VISA", "MasterCard"};
    private String[]list2={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    private String[]list3={"2015","2016","2017"};
    private ArrayList<CardDetails>cList=new ArrayList<>();
    private int patientNumber;
    JButton confirm,cancel,validate;
    JLabel patientNum,holdersName,cardNum,secCode,expiry,xMonth,xYear,cardType,titleF;
    JTextField pNumText,holdersNameText,cardNumText,secCodeText;
    JComboBox<String> xMonthTextCombo,xYearTextCombo, cardTypeCombo;
    JFrame f;

    public CreditCardGUI(int patientNumIn) {
        patientNumber=patientNumIn;
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

        //Clock
        Clock.DigitalClock clockD=new Clock.DigitalClock();
        JPanel clock = new JPanel(new FlowLayout(FlowLayout.LEFT));
        clock.add(clockD);

        //Title
        JPanel title=new JPanel(new FlowLayout(FlowLayout.CENTER));
        titleF = new JLabel("Credit Cards");
        title.add(titleF);
        titleF.setFont(new Font("Arial", Font.BOLD, 24));
        JPanel ID=new JPanel(new FlowLayout(FlowLayout.CENTER));

        //patient num labels
        patientNum = new JLabel("\tPatient Number");
        ID.add(patientNum);

        //patient num text field
        pNumText = new JTextField(5);
        pNumText.setBorder(loweredBorder);
        pNumText.setText(Integer.toString(patientNumIn));
        pNumText.setEnabled(false);
        ID.add(pNumText);
        topSection.add(clock);
        topSection.add(title);
        topSection.add(ID);
        holder.add(topSection);

        JPanel holder2=new JPanel(new GridLayout(2,1));
        JPanel middle = new JPanel(new GridBagLayout());
        holder2.add(middle, BorderLayout.CENTER);

        //Name Label
        holdersName = new JLabel("Card Holders Full Name ");
        middle.add(holdersName, getConstraints(0, 0, 2, 1, GridBagConstraints.WEST));

        //Name text
        holdersNameText = new JTextField(35);
        holdersNameText.setBorder(loweredBorder);
        middle.add(holdersNameText, getConstraints(0, 1, 4, 1, GridBagConstraints.WEST));

        //Card Label
        cardNum = new JLabel("Card Number");
        middle.add(cardNum, getConstraints(0, 2, 3, 1, GridBagConstraints.WEST));

        //Sec Code label
        secCode = new JLabel("Security code");
        middle.add(secCode, getConstraints(3, 2, 1, 1, GridBagConstraints.WEST));

        //Card text
        cardNumText = new JTextField(20);
        cardNumText.setBorder(loweredBorder);
        middle.add(cardNumText, getConstraints(0, 3, 3, 1, GridBagConstraints.WEST));

        //Sec code Text
        secCodeText = new JTextField(10);
        secCodeText.setBorder(loweredBorder);
        middle.add(secCodeText, getConstraints(3, 3, 3, 1, GridBagConstraints.WEST));

        //date labels
        expiry = new JLabel("Expiry Date");
        middle.add(expiry, getConstraints(0, 4, 1, 1, GridBagConstraints.WEST));

        xMonth = new JLabel("Month");
        middle.add(xMonth, getConstraints(0, 5, 1, 1, GridBagConstraints.WEST));

        xYear = new JLabel("Year");
        middle.add(xYear, getConstraints(1, 5, 1, 1, GridBagConstraints.WEST));

        cardType = new JLabel("Card Type");
        middle.add(cardType, getConstraints(2, 5, 1, 1, GridBagConstraints.WEST));

        //Date combo boxes
        xMonthTextCombo = new JComboBox<>(list2);
        xMonthTextCombo.setBorder(loweredBorder);
        middle.add(xMonthTextCombo, getConstraints(0, 6, 1, 1, GridBagConstraints.WEST));
        xYearTextCombo = new JComboBox<>(list3);
        middle.add(xYearTextCombo, getConstraints(1, 6, 1, 1, GridBagConstraints.WEST));

        //Card type combo
        cardTypeCombo = new JComboBox<String>(list1);
        cardTypeCombo.setBorder(loweredBorder);
        cardTypeCombo.setPreferredSize(new Dimension(120, 20));
        middle.add(cardTypeCombo, getConstraints(2, 6, 1, 1, GridBagConstraints.WEST));
        cardTypeCombo.addActionListener(this);

        //Validation check box
        validate = new JButton(" Validate ");
        validate.addActionListener(this);
        middle.add(validate, getConstraints(0, 10, 3, 1, GridBagConstraints.WEST));

        //if existing record, retrieve and populate
        cList.removeAll(cList);
        CardDetails cardDetails=new CardDetails();
        cList.addAll(cardDetails.getCardList());
        for (int i = 0; i < cList.size(); i++) {
            if(patientNumber==cList.get(i).getPatientNumIn()){
                holdersNameText.setText(cList.get(i).getCardHolder());
                cardNumText.setText(Integer.toString(cList.get(i).getCardNum()));
                secCodeText.setText(Integer.toString(cList.get(i).getSecurityCode()));
                xMonthTextCombo.setSelectedItem(cList.get(i).getExpiryDate().charAt(0) + "" + cList.get(i).getExpiryDate().charAt(1) + "" + cList.get(i).getExpiryDate().charAt(2));
                xYearTextCombo.setSelectedItem(cList.get(i).getExpiryDate().charAt(4) + "" + cList.get(i).getExpiryDate().charAt(5) + "" +
                        "" + cList.get(i).getExpiryDate().charAt(6) + "" + cList.get(i).getExpiryDate().charAt(7));
                cardTypeCombo.setSelectedItem(cList.get(i).getCardType());
            }
        }
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
            boolean test=false;
            cList.removeAll(cList);
            CardDetails cardDetails=new CardDetails();
            cList.addAll(cardDetails.getCardList());
            for (int i = 0; i <cList.size() ; i++) {
                if(cList.get(i).getPatientNumIn()==patientNumber){
                    test=true;
                }
            }
            String xComboM=(String)xMonthTextCombo.getSelectedItem();
            String xComboY=(String)xYearTextCombo.getSelectedItem();
            String cardCombo=(String)cardTypeCombo.getSelectedItem();
            int catcher = 0, catcher2 = 0;
            if(cardNumText.getText().equals("")||secCode.getText().equals("")||holdersNameText.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Holders Name, Card Number & Security Code are Required Fields");
            }else{
                try {
                    catcher=Integer.parseInt(cardNumText.getText());
                    catcher2=Integer.parseInt(secCodeText.getText());
                    if(test==false) {
                        CardDetails cardDetails2 = new CardDetails(patientNumber, catcher, cardCombo, catcher2, holdersNameText.getText(), (xComboM + "-" + xComboY));
                    }else{
                        CardDetails cardDetails2 = new CardDetails(patientNumber, catcher, cardCombo, catcher2, holdersNameText.getText(), (xComboM + "-" + xComboY),1);
                    }
                    f.setVisible(false);
                } catch (NumberFormatException nf) {
                    JOptionPane.showMessageDialog(null, "Card Number & Security Code must be Numeric");
                    cardNumText.setText("");
                    secCodeText.setText("");
                }
            }
        }else if (e.getSource().equals(validate)) {
            CardValidation cardValidation=new CardValidation(cardNumText.getText());
        }
    }
}