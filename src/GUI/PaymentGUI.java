
package GUI;

        import javax.swing.*;
        import javax.swing.border.BevelBorder;
        import javax.swing.border.Border;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

/**
 * Created by Thomas Murray on 29/03/2015.
 */



public class PaymentGUI extends JFrame implements ActionListener
{
    JButton confirm,print,cancel,debitCredit,insurance,medicalCard;
    JLabel patientNum,label5;
    JTextField patientText;
    JTextArea billInformation;
    JCheckBox cash;
    JRadioButton checkOutRadio;
    JScrollPane scroll;
    private int patientNumberIn;
    JFrame f;

    public PaymentGUI(int patientNumIn)
    {
        patientNumberIn=patientNumIn;
        f = new JFrame();
        f.setTitle("Payment");
        f.setSize(805, 1000);
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
        label5 = new JLabel("      Payment            ");
        title.add(label5);
        label5.setFont(new Font("Arial", Font.BOLD, 30));

        JPanel ID=new JPanel(new FlowLayout(FlowLayout.CENTER));
        //labels
        patientNum = new JLabel("\tPatient Number");
        ID.add(patientNum);
        //text field
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

        billInformation = new JTextArea(40,70);
        billInformation.setBorder(loweredBorder);
        billInformation.setEditable(false);
        scroll = new JScrollPane(billInformation);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textArea.add(scroll);


        JPanel holder2=new JPanel(new GridLayout(1,1));
        JPanel test =new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel dobs = new JPanel(new GridBagLayout());

        //Cash check box
        cash=new JCheckBox("Cash");
        cash.addActionListener(this);
        dobs.add(cash, getConstraints(6, 0, 1, 1, GridBagConstraints.WEST));

        // Debit/Credit card button
        debitCredit = new JButton("Debit/Credit Card");
        debitCredit.addActionListener(this);
        dobs.add(debitCredit, getConstraints(7, 0, 1, 1, GridBagConstraints.WEST));

        // Insurance button
        insurance = new JButton("Insurance");
        insurance.addActionListener(this);
        dobs.add(insurance, getConstraints(8, 0, 1, 1, GridBagConstraints.WEST));

        // Medical Card button
        medicalCard = new JButton("Medical Card");
        medicalCard.addActionListener(this);
        dobs.add(medicalCard, getConstraints(9, 0, 1, 1, GridBagConstraints.WEST));

        // Confirm button
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        dobs.add(confirm, getConstraints(0, 1, 1, 1, GridBagConstraints.WEST));

        // Print button
        print = new JButton("Print");
        print.addActionListener(this);
        dobs.add(print, getConstraints(1, 1, 1, 1, GridBagConstraints.WEST));

        // Cancel button
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        dobs.add(cancel, getConstraints(2, 1, 1, 1, GridBagConstraints.WEST));

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


    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cancel)) {
            System.exit(0);
        } else if (e.getSource().equals(confirm)){

        }else if (e.getSource().equals(print))
        {
        }else if (e.getSource().equals(debitCredit))
        {   CreditCardGUI creditCardGUI=new CreditCardGUI(patientNumberIn);
        }else if (e.getSource().equals(insurance))
        {   healthInsuranceGUI hInsuranceGUI=new healthInsuranceGUI(patientNumberIn);
        }else if (e.getSource().equals(medicalCard))
        {   MedicalCardGUI medicalCardGUI=new MedicalCardGUI(patientNumberIn);
        }
    }
}