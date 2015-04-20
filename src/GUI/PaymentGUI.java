
package GUI;

        import DataBase.StockOperations;
        import Model.*;

        import javax.print.PrintException;
        import javax.swing.*;
        import javax.swing.border.BevelBorder;
        import javax.swing.border.Border;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.io.File;
        import java.io.FileWriter;
        import java.io.IOException;
        import java.util.ArrayList;

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
    private ArrayList<PatientRecord>patList=new ArrayList<>();
    private ArrayList<Prescription> presList=new ArrayList<>();
    private ArrayList<EquipmentUsed>eqUsedList=new ArrayList<>();
    private PatientRecord patientRecord;
    private Prescription prescription;
    private EquipmentUsed equipmentUsed;
    private String record="This is meant to be the patient Bill";
    private Bill bill;
    private JFrame f;
    static boolean valid;
    private StockOperations so;


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
        billInformation.setText(setTextArea());
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

    public String setTextArea(){
        bill=new Bill(patientNumberIn);
        patList.removeAll(patList);
        presList.removeAll(presList);
        eqUsedList.removeAll(eqUsedList);
        patientRecord=new PatientRecord();
        patList.addAll(patientRecord.getPatientList());
        prescription=new Prescription();
        presList.addAll(prescription.getPresList());
        equipmentUsed=new EquipmentUsed(patientNumberIn);
        eqUsedList.addAll(equipmentUsed.getUsedList());
        for (int i = 0; i < patList.size(); i++) {
            if(patientNumberIn==patList.get(i).getPatientNumber()){
                record="\n--------------------------------------------------------------------------------------------------------------------\n"+
                        "\n   Patient Number is      \t" + patList.get(i).getPatientNumber() + "\n" +
                        "\n   Patient Name is         \t" + patList.get(i).getPatientFName() + " " + patList.get(i).getPatientLName() + "\n" +
                        "\n   Patient Phone is          \t" + patList.get(i).getPhone() + "\n" +
                        "\n   Patient Email is   \t" + patList.get(i).getEmail() + "\n" +
                        "\n--------------------------------------------------------------------------------------------------------------------\n"+
                        "\n\n   List of Prescriptions this Visit \n\n";
                for (int j = 0; j <presList.size() ; j++) {
                    if(patientNumberIn==presList.get(j).getpNum()&& presList.get(j).getPaid()==1) {
                        record += record = "\n   Drug type:\t" + presList.get(j).getMedName() + "\t  Drug Amount:\t" + presList.get(j).getDose()*10+" mg\n";
                    }

                }
                record+=record=	"\n   Total Medicine Cost:  €"+Bill.df.format(bill.calcMedCost())+"\n";
                record+=record="\n--------------------------------------------------------------------------------------------------------------------\n" +
                        "\n\n   List of Equipment Used this Visit \n\n";
                for (int k = 0; k <eqUsedList.size() ; k++) {
                    if(patientNumberIn==eqUsedList.get(k).getpNum()&& eqUsedList.get(k).getThisVisit()==1) {
                        record += record = "\n   Equip type:\t" + eqUsedList.get(k).getEqName() + "\n";
                    }

                }
                record+=record= "\n   Total Equipment Cost:  € "+Bill.df.format(bill.calcEquipCost())+"\n";
                record+=record= "\n   Standing Daily Charge :  € 150.00\n";
                record+=record="\n--------------------------------------------------------------------------------------------------------------------\n" +
                        "\n\n   Total before VAT\t\t€ "+Bill.df.format(bill.getTotalBeforeVAT())+"\n";
                record+=record="\n--------------------------------------------------------------------------------------------------------------------\n" +
                        "\n\n   Total After VAT \t\t€ "+Bill.df.format(bill.getTotalAfterVAT())+"\n";
                record+=record="\n--------------------------------------------------------------------------------------------------------------------\n" +
                        "\n\n   Total VAT Paid\t\t€ "+Bill.df.format(bill.getTotalVAT())+"\n";
            }

        }

        return record;
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
            f.setVisible(false);
        } else if (e.getSource().equals(confirm)){
            if(valid){
                so=new StockOperations();
                MedPatientRecGUI medPatientRecGUI=new MedPatientRecGUI(patientNumberIn);
                medPatientRecGUI.setVisible(false);
                so.storeOldMedRec(patientNumberIn, medPatientRecGUI.setTextArea(), Prescription.getCurrentTimeStamp());
                Prescription prescription1=new Prescription();
                prescription1.updatePaid(patientNumberIn);
                EquipmentUsed equipmentUsed1=new EquipmentUsed();
                equipmentUsed1.updateEquipPayment(patientNumberIn);
                so.storeAccounts(bill.calcMedCost(),bill.calcEquipCost(),patientNumberIn);
                so.updateAccounts(bill.getTotalAfterVAT());
                so.stockOperationsClose();
                Bill bill1=new Bill();
                bill1.storeBill(patientNumberIn,record);
                f.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(null, "Payment has to be Validated First");
            }

        }else if (e.getSource().equals(print))
        {File Files = new File("files");
            File textFile = new File(Files, ""+patientNumberIn+"_Bill.txt");
            try (FileWriter input = new FileWriter(textFile)) {
                //Input text
                input.write(billInformation.getText());
                JOptionPane.showMessageDialog(null, "Patient "+patientNumberIn+" bill is printing....");
                try {
                    Printing printing = new Printing(patientNumberIn + "_Bill");
                }catch (PrintException pe){

                }
            } catch (IOException io) {
                System.out.println(io);
            }
        }else if (e.getSource().equals(debitCredit))
        {   CreditCardGUI creditCardGUI=new CreditCardGUI(patientNumberIn);
        }else if (e.getSource().equals(insurance))
        {   healthInsuranceGUI hInsuranceGUI=new healthInsuranceGUI(patientNumberIn);
        }else if (e.getSource().equals(medicalCard))
        {   MedicalCardGUI medicalCardGUI=new MedicalCardGUI(patientNumberIn);
        }else if (e.getSource().equals(cash))
        {  valid=true;
        }
    }
}