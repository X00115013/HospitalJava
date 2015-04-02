package GUI;

        import DataBase.PatientOperations;
        import Model.MedicalRecord;
        import Model.PatientRecord;

        import javax.swing.*;
        import javax.swing.border.BevelBorder;
        import javax.swing.border.Border;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.util.ArrayList;

/**
 * Created by Thomas Murray on 20/03/2015.
 */
public class UpdateMedRec extends JFrame implements ActionListener {

    JButton confirm, cancel;
    JLabel blood,gender,symptoms,diagnoses,reqTreatment,reqEquip,patientNum, allergies, titleF;
    JRadioButton male, female;
    JTextField patientText, bloodText,patientNumText;
    JComboBox<String> reqEquipCombo;
    JTextArea symptomsText, diagnosesText, requiredTreatText, allergiesText;
    JFrame f;
    String[] list1 = {"XRay :", "MRI Scan :", "CT Scan :"};
    private ArrayList<MedicalRecord> mList=new ArrayList<>();
    private MedicalRecord medicalRecord;
    private int patientNumberIn;

    public UpdateMedRec(int patientNumIn) {
        patientNumberIn=patientNumIn;
        f = new JFrame();
        f.setTitle("Update Patient Med Record");
        f.setSize(830, 975);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        Border loweredBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        f.setLayout(new FlowLayout(FlowLayout.LEFT));


        JPanel holder = new JPanel(new GridLayout(1, 1));
        JPanel topSection = new JPanel(new GridLayout(1, 3));

        Clock.DigitalClock clockD = new Clock.DigitalClock();
        JPanel clock = new JPanel(new FlowLayout(FlowLayout.LEFT));
        clock.add(clockD);

        JPanel title = new JPanel(new FlowLayout(FlowLayout.CENTER));

        titleF = new JLabel("Update Medical Records");
        title.add(titleF);
        titleF.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel ID = new JPanel(new FlowLayout(FlowLayout.CENTER));
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
        f.add(holder);


        JPanel test2 = new JPanel(new GridLayout(1, 1));
        //middle
        JPanel middle = new JPanel(new GridBagLayout());
        blood = new JLabel("Blood Type");
        middle.add(blood, getConstraints(0, 0, 1, 1, (GridBagConstraints.WEST)));
        bloodText = new JTextField(70);
        bloodText.setBorder(loweredBorder);
        middle.add(bloodText, getConstraints(0, 1, 1, 1, (GridBagConstraints.WEST)));

        symptoms= new JLabel("Symptoms");
        middle.add(symptoms, getConstraints(0, 3, 1, 1, (GridBagConstraints.WEST)));
        //text field
        symptomsText = new JTextArea(4,70);
        symptomsText.setBorder(loweredBorder);
        middle.add(symptomsText, getConstraints(0, 4, 1, 1, (GridBagConstraints.WEST)));

        reqTreatment = new JLabel("Required Treatment");
        middle.add(reqTreatment, getConstraints(0, 6, 1, 1, (GridBagConstraints.WEST)));
        //text field
        requiredTreatText = new JTextArea(4,70);
        requiredTreatText.setBorder(loweredBorder);
        middle.add(requiredTreatText,getConstraints(0, 7, 1, 1, (GridBagConstraints.WEST)));

        allergies = new JLabel("Allergies");
        middle.add(allergies, getConstraints(0, 9, 1, 1, (GridBagConstraints.WEST)));
        //text field
        allergiesText = new JTextArea(4,70);
        allergiesText.setBorder(loweredBorder);
        middle.add(allergiesText, getConstraints(0, 10, 1, 1, (GridBagConstraints.WEST)));

        diagnoses = new JLabel("Diagnoses");
        middle.add(diagnoses, getConstraints(0, 12, 1, 1, (GridBagConstraints.WEST)));
        //text field
        diagnosesText = new JTextArea(4,70);
        diagnosesText.setBorder(loweredBorder);
        middle.add(diagnosesText, getConstraints(0, 13, 1, 1, (GridBagConstraints.WEST)));

        reqEquip = new JLabel("Required Equipment");
        middle.add(reqEquip, getConstraints(0, 15, 1, 1, (GridBagConstraints.WEST)));
        //text field
        reqEquipCombo = new JComboBox<String>(list1);
        reqEquipCombo.setPreferredSize(new Dimension(300, 20));
        middle.add(reqEquipCombo, getConstraints(0, 16, 1, 1, GridBagConstraints.WEST));
        reqEquipCombo.addActionListener(this);

        test2.add(middle);
        f.add(test2);

        JPanel test = new JPanel(new GridLayout( 2,1));
        JPanel dobs = new JPanel(new GridBagLayout());
        gender=new JLabel("Gender");
        dobs.add(gender, getConstraints(0, 0, 1, 1, (GridBagConstraints.WEST)));
        male = new JRadioButton("Male");
        male.addActionListener(this);
        dobs.add(male, getConstraints(0, 1, 1, 1, (GridBagConstraints.WEST)));
        female = new JRadioButton("Female");
        female.addActionListener(this);
        dobs.add(female, getConstraints(1, 1, 1, 4, (GridBagConstraints.WEST)));

        test.add(dobs);

        medicalRecord=new MedicalRecord();
        mList.addAll(medicalRecord.getMedicalRecordArrayList());
        for (int i = 0; i < mList.size() ; i++) {
            if(patientNumIn==mList.get(i).getPatientNumber()){
                bloodText.setText(mList.get(i).getBlood());
                symptomsText.setText(mList.get(i).getSymptoms());
                requiredTreatText.setText(mList.get(i).getReqTreatment());
                allergiesText.setText(mList.get(i).getAllergies());
                diagnosesText.setText(mList.get(i).getDiagnoses());
                if(mList.get(i).getPatientGender().equalsIgnoreCase("male")){
                    male.setSelected(true);
                }else if(mList.get(i).getPatientGender().equalsIgnoreCase("female")){
                    female.setSelected(true);
                }
            }
        }

        //bottom


        JPanel buttons=new JPanel(new GridLayout(1,1));
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // Confirm button

        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        bottom.add(confirm);


        // Cancel button
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        bottom.add(cancel);

        buttons.add(bottom);
        test.add(buttons);
        f.add(test, BorderLayout.NORTH);

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

        }else if (e.getSource()==(male)) {
            female.setSelected(false);

        }else if (e.getSource()==(female)) {
           male.setSelected(false);

        } else if (e.getSource().equals(confirm)) {
            String choice = "";
            if (male.isSelected()) {
                choice = "Male";
            }
            if (female.isSelected()) {
                choice = "Female";
            }
            int catcher = 0;
                String medEquip = (String) reqEquipCombo.getSelectedItem();
                if (medEquip.equals("XRay :")) {
                    catcher = 1;
                } else if (medEquip.equals("MRI Scan :")) {
                    catcher = 2;
                } else if (medEquip.equals("CT Scan :")) {
                    catcher = 3;
                }

            MedicalRecord medicalRecord1=new MedicalRecord(patientNumberIn,bloodText.getText(),symptomsText.getText(),diagnosesText.getText(),requiredTreatText.getText(),allergiesText.getText());
            f.setVisible(false);


        }
    }
}