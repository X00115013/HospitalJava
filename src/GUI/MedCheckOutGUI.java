package GUI;

import Model.*;

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
 * This class is for the consultants to discharge a patient
 */
public class MedCheckOutGUI extends JFrame implements ActionListener
{
    JButton confirm,cancel;
    JLabel patientNum,label5;
    JTextField patientText;
    JTextArea additionalInformation;
    JComboBox<String>conTextCombo;
    JRadioButton checkOutRadio;
    private int patientNumberIn;
    private PatientRecord patientRecord;
    private ArrayList<PatientRecord>pList=new ArrayList<>();
    private ArrayList<Consultants>conList=new ArrayList<>();
    private Consultants consultants;
    String[] list;
    JFrame f;

    public MedCheckOutGUI(int patientNumIn)
    {
        patientNumberIn=patientNumIn;
        f = new JFrame();
        f.setTitle("Medical Check-Out");
        f.setSize(700, 500);
        f.setResizable(true);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        Border loweredBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        JPanel holder=new JPanel(new GridLayout(3,1));
        JPanel topSection=new JPanel(new GridLayout(1,3));

        //Clock
        Clock.DigitalClock clockD=new Clock.DigitalClock();
        JPanel clock = new JPanel(new FlowLayout(FlowLayout.LEFT));
        clock.add(clockD);

        //Title
        JPanel title=new JPanel(new FlowLayout(FlowLayout.CENTER));
        label5 = new JLabel("Medical Check-Out");
        title.add(label5);
        label5.setFont(new Font("Arial", Font.BOLD, 24));
        JPanel ID=new JPanel(new FlowLayout(FlowLayout.RIGHT));

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

        //Populate consultant combo
        consultants=new Consultants();
        conList.addAll(consultants.getConsultants());
        list = new String[conList.size()];
        for (int i = 0; i < conList.size(); i++) {
            list[i] = conList.get(i).getConName();
        }

        JPanel textArea=new JPanel(new FlowLayout(FlowLayout.CENTER));

        //Central text area
        additionalInformation = new JTextArea(230,60);
        additionalInformation.setBorder(loweredBorder);
        JScrollPane scroll = new JScrollPane(additionalInformation);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textArea.add(scroll);
        holder.add(textArea);
        JPanel test =new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel dobs = new JPanel(new GridBagLayout());

        //Consultant label
        patientNum = new JLabel("\tConsultant");
        dobs.add(patientNum, getConstraints(0, 2, 1, 1, GridBagConstraints.WEST));

        //consultant combo box
        conTextCombo = new JComboBox<>(list);
        conTextCombo.setBorder(loweredBorder);
        dobs.add(conTextCombo, getConstraints(0, 3, 2, 1, GridBagConstraints.WEST));

        // Confirm button
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        dobs.add(confirm, getConstraints(0, 4, 1, 1, GridBagConstraints.WEST));

        // Cancel button
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        dobs.add(cancel, getConstraints(1, 4, 1, 1, GridBagConstraints.WEST));

        //Check out check box
        checkOutRadio = new JRadioButton("Discharge");
        checkOutRadio.addActionListener(this);
        dobs.add(checkOutRadio);
        test.add(dobs);
        holder.add(test);
        f.add(holder);
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
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(cancel))
        {
           f.setVisible(false);
        }
        else if (e.getSource().equals(confirm))
        {
            if(checkOutRadio.isSelected()) {
                String nameCatch=(String)conTextCombo.getSelectedItem();
                patientRecord=new PatientRecord();
                pList.addAll(patientRecord.getPatientList());
                for (int i = 0; i < pList.size(); i++) {
                    if (pList.get(i).getPatientNumber() == patientNumberIn ) {
                        CheckIn checkIn = new CheckIn(patientNumberIn, 2);
                        checkIn.addCheckInfo(patientNumberIn,additionalInformation.getText(),nameCatch);
                        f.setVisible(false);
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "Patient Discharge must be selected!!!");
            }

        }
    }
}
