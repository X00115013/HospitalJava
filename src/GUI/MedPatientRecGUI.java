
package GUI;

        import DataBase.PatientOperations;
        import Model.EquipmentUsed;
        import Model.MedicalRecord;
        import Model.PatientRecord;
        import Model.Prescription;

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
public class MedPatientRecGUI extends JFrame implements ActionListener {
    JButton update, cancel,refresh;
    JLabel patientNum, label5;
    JTextField patientText;
    JTextArea medicalInformation;
    JScrollPane scroll;
    private int patientNumberIn;
    private ArrayList<MedicalRecord>mList=new ArrayList<>();
    private ArrayList<Prescription>presList=new ArrayList<>();
    private ArrayList<EquipmentUsed>eqUsedList=new ArrayList<>();
    private MedicalRecord medicalRecord;
    private Prescription prescription;
    private EquipmentUsed equipmentUsed;
    private String record="This is meant to be the patient Medical Record";
    JFrame f;

    public MedPatientRecGUI(int patientNumIn) {
        patientNumberIn=patientNumIn;
        f = new JFrame();
        f.setTitle("Medical Records");
        f.setSize(820, 1000);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        Border loweredBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);

        JPanel holder = new JPanel(new GridLayout(1, 1));
        JPanel topSection = new JPanel(new GridLayout(1, 3));

        Clock.DigitalClock clockD = new Clock.DigitalClock();
        JPanel clock = new JPanel(new FlowLayout(FlowLayout.LEFT));
        clock.add(clockD);

        JPanel title = new JPanel(new FlowLayout(FlowLayout.CENTER));
        label5 = new JLabel("Patient Medical Records");
        title.add(label5);
        label5.setFont(new Font("Arial", Font.BOLD, 24));

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
//        f.add(topSection);

        holder.add(topSection);
        JPanel textArea = new JPanel(new FlowLayout(FlowLayout.CENTER));

        medicalInformation = new JTextArea(40, 70);
        medicalInformation.setBorder(loweredBorder);
        medicalInformation.setFont(new Font("Arial", Font.ITALIC, 14));
        medicalInformation.setText(setTextArea());
        scroll = new JScrollPane(medicalInformation);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textArea.add(scroll);

        JPanel holder2 = new JPanel(new GridLayout(1, 1));
        JPanel test = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel dobs = new JPanel(new GridBagLayout());

        // Update button
        update = new JButton("Update");
        update.addActionListener(this);
        dobs.add(update, getConstraints(0, 4, 1, 1, GridBagConstraints.WEST));


        // Cancel button
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        dobs.add(cancel, getConstraints(1, 4, 1, 1, GridBagConstraints.WEST));

        // Refresh button
        refresh = new JButton("Refresh Record");
        refresh.addActionListener(this);
        dobs.add(refresh, getConstraints(7, 4, 1, 1, GridBagConstraints.WEST));

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


    public String setTextArea(){
        mList.removeAll(mList);
        presList.removeAll(presList);
        eqUsedList.removeAll(eqUsedList);
        medicalRecord=new MedicalRecord();
        mList.addAll(medicalRecord.getMedicalRecordArrayList());
        prescription=new Prescription();
        presList.addAll(prescription.getPresList());
        equipmentUsed=new EquipmentUsed(patientNumberIn);
        eqUsedList.addAll(equipmentUsed.getUsedList());
        for (int i = 0; i < mList.size(); i++) {
            if(patientNumberIn==mList.get(i).getPatientNumber()){
                record="\n--------------------------------------------------------------------------------------------------------------------\n"+
                        "\n   Patient Number is      \t" + mList.get(i).getPatientNumber() + "\n" +
                        "\n   Patient Name is         \t" + mList.get(i).getPatientFName() + " " + mList.get(i).getPatientLName() + "\n" +
                        "\n   Patient DOB is          \t" + mList.get(i).getPatientDOB() + "\n" +
                        "\n   Patient Blood Type is   \t" + mList.get(i).getBlood() + "\n" +
                        "\n   Patient Gender is       \t" + mList.get(i).getPatientGender() + "\n" +
                        "\n   Patient Allergies are   \t" + mList.get(i).getAllergies()+ "\n" +
                        "\n--------------------------------------------------------------------------------------------------------------------\n"+
                        "\n\n   Patient Symptoms          \n\n   " + mList.get(i).getSymptoms() + "\n" +
                        "\n--------------------------------------------------------------------------------------------------------------------\n"+
                        "\n\n   Patient Diagnoses         \n\n   " + mList.get(i).getDiagnoses() + "\n" +
                        "\n--------------------------------------------------------------------------------------------------------------------\n"+
                        "\n\n   GP Recommendations   \n\n   " + mList.get(i).getRecommendations() + "\n"+
                        "\n--------------------------------------------------------------------------------------------------------------------\n"+
                        "\n\n   Patient Required Treatment\n\n   " + mList.get(i).getReqTreatment() + "\n" +
                        "\n--------------------------------------------------------------------------------------------------------------------\n" +
                        "\n\n   List of Prescriptions this Visit \n\n";
                for (int j = 0; j <presList.size() ; j++) {
                    if(patientNumberIn==presList.get(j).getpNum()&& presList.get(j).getPaid()==1) {
                        record += record = "\n   Drug type:\t" + presList.get(j).getMedName() + "\t  Drug Amount:\t" + presList.get(j).getDose()+"\t  Time & Date:   "+presList.get(j).getDate()+"\n";
                    }

                }
                record+=record="\n--------------------------------------------------------------------------------------------------------------------\n" +
                        "\n\n   List of Equipment Used this Visit \n\n";
                for (int k = 0; k <eqUsedList.size() ; k++) {
                    if(patientNumberIn==eqUsedList.get(k).getpNum()&& eqUsedList.get(k).getThisVisit()==1) {
                        record += record = "\n   Equip type:\t" + eqUsedList.get(k).getEqName() + "\n";
                    }

                }
            }

        }

        return record;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cancel)) {
           f.setVisible(false);
        } else if (e.getSource().equals(update)) {
           UpdateMedRec updateMedRec=new UpdateMedRec(patientNumberIn);

        }else if (e.getSource().equals(refresh)) {
            medicalInformation.setText(setTextArea());

        }
    }
}