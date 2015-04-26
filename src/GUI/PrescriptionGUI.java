package GUI;


        import Model.Appointment;
        import Model.Consultants;
        import Model.Medicine;
        import Model.Prescription;

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
 * This class is used to prescribe medicines to a patient one at a time
 */
public class PrescriptionGUI extends JFrame implements ActionListener {

    private String[] list2 = {"10mg", "20mg", "40mg", "50mg", "60mg", "70mg", "80mg", "90mg", "100mg", "110mg", "120mg", "130mg", "140mg", "150mg", "160mg", "170mg", "180mg", "190mg", "200mg"};
    private ArrayList<Medicine> medList = new ArrayList<>();
    private ArrayList<Consultants>conList=new ArrayList<>();
    private String[] list1,list3;
    private Medicine medicine;
    private Consultants consultants;
    private int patientNumberIn;
    JButton confirm,cancel;
    JLabel patientNum;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();

    JTextField pNumText;
    JTextField field2, conNum;
    JFrame f;

    JComboBox<String> combo1,combo2,conCombo;

    public PrescriptionGUI(int patientNumIn) {

        patientNumberIn = patientNumIn;
        f = new JFrame();
        f.setTitle("Prescription");
        f.setLayout(new FlowLayout(FlowLayout.LEFT));
        f.setSize(630, 550);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        Border loweredBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        JPanel holder = new JPanel(new GridLayout(1, 1));
        JPanel topSection = new JPanel(new GridLayout(1, 3));

        //Clock
        Clock.DigitalClock clockD = new Clock.DigitalClock();
        JPanel clock = new JPanel(new FlowLayout(FlowLayout.LEFT));
        clock.add(clockD);

        //Title
        JPanel title = new JPanel(new FlowLayout(FlowLayout.CENTER));
        label5 = new JLabel("Prescription");
        title.add(label5);
        label5.setFont(new Font("Arial", Font.BOLD, 36));
        JPanel ID = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //patient num label
        patientNum = new JLabel("\tPatient Number");
        ID.add(patientNum);

        //patient num text field
        pNumText = new JTextField(5);
        pNumText.setText(Integer.toString(patientNumIn));
        pNumText.setBorder(loweredBorder);
        pNumText.setEditable(false);
        ID.add(pNumText);

        topSection.add(clock);
        topSection.add(title);
        topSection.add(ID);
        holder.add(topSection);


        //Population of medicine and consultant combo
        medicine = new Medicine();
        medList.removeAll(medList);
        medList.addAll(medicine.getMedicines());
        list1 = new String[medList.size()];
        for (int i = 0; i < medList.size(); i++) {
            list1[i] = medList.get(i).getMedName();
        }
        consultants = new Consultants();
        conList.addAll(consultants.getConsultants());
        list3 = new String[conList.size()+1];
        list3[0]="";
        for (int i = 0; i < conList.size(); i++) {
            list3[i+1] = conList.get(i).getConName();
        }

        JPanel holder2 = new JPanel(new GridLayout(2, 1));
        JPanel middle = new JPanel(new GridBagLayout());
        holder2.add(middle, BorderLayout.CENTER);

        //Medicine label
        label3 = new JLabel("Medical Required");
        middle.add(label3, getConstraints(0, 2, 1, 1, GridBagConstraints.WEST));

        //Medicine Combo box
        combo1 = new JComboBox<String>(list1);
        combo1.setPreferredSize(new Dimension(400, 30));
        combo1.setBorder(loweredBorder);
        middle.add(combo1, getConstraints(0, 3, 3, 1, GridBagConstraints.WEST));
        combo1.addActionListener(this);

        //Dose label
        label4 = new JLabel("Dose Required");
        middle.add(label4, getConstraints(0, 4, 1, 1, GridBagConstraints.WEST));

        //dose combo box
        combo2 = new JComboBox<String>(list2);
        combo2.setPreferredSize(new Dimension(400, 30));
        combo2.setBorder(loweredBorder);
        middle.add(combo2, getConstraints(0, 5, 3, 1, GridBagConstraints.WEST));
        combo2.addActionListener(this);

        //Consultant label
        label2 = new JLabel("Consultant Number");
        middle.add(label2, getConstraints(0, 6, 1, 1, GridBagConstraints.WEST));

        //Consultant combo box
        conCombo = new JComboBox<>(list3);
        conCombo.setBorder(loweredBorder);
        middle.add(conCombo, getConstraints(0, 7, 2, 1, GridBagConstraints.WEST));
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
            String catcher = "";
            int catcher2 = 0;
            String conName=(String)conCombo.getSelectedItem();
            if (conName.equals("")) {
                JOptionPane.showMessageDialog(null, "Consultant is a required fields");
            } else {
                    String med = (String) combo1.getSelectedItem();
                    for (int i = 0; i < medList.size(); i++) {
                        if (med.equals(medList.get(i).getMedName())) {
                            catcher = (String) medList.get(i).getMedName();
                        }
                    }
                    String conEquip = (String) combo2.getSelectedItem();
                    for (int j = 0; j < list2.length; j++) {
                        String temp = list2[j];
                        if (conEquip.equals(temp)) {
                            catcher2 = j + 2;
                        }
                    }
                    Prescription prescription = new Prescription(patientNumberIn, catcher, catcher2, conName);
                    prescription.updateStock(catcher,catcher2);
                    f.setVisible(false);
            }
        }
    }
}