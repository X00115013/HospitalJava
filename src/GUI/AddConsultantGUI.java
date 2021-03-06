package GUI;

    import Model.Consultants;
    import Model.Equipment;
    import Model.Medicine;
    import Model.PatientRecord;

    import javax.swing.*;
    import javax.swing.border.BevelBorder;
    import javax.swing.border.Border;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.geom.Arc2D;
    import java.util.ArrayList;
    import java.util.InputMismatchException;

/**
     * Created by Thomas Murray on 04/04/2015.
     */


    public class AddConsultantGUI extends JFrame implements ActionListener {
    JButton confirm, cancel, refresh,delete;
    JLabel patientNum, titleF, reason, conName, conSkill, conEquip;
    JTextField patientText, conNameText, conSkillText;
    JTextArea additionalInformation;
    JScrollPane scroll;
    private ArrayList<Consultants> conList = new ArrayList<>();
    private Consultants consultants;
    private String list = "This should work";
    private ArrayList<Equipment> eqList = new ArrayList<>();
    private Equipment equipment;
    private String[] list1;
    private JComboBox<String> equipCombo;

    JFrame f;

    public AddConsultantGUI() {
        consultants = new Consultants();
        conList.addAll(consultants.getConsultants());
        f = new JFrame();
        f.setTitle("Add Consultant");
        f.setSize(885, 1000);
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
        titleF = new JLabel("Add Consultant");
        title.add(titleF);
        titleF.setFont(new Font("Arial", Font.BOLD, 37));

        JPanel ID = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        //Con num labels
        patientNum = new JLabel("\tConsultant Number");
        ID.add(patientNum);
        //con num text field
        patientText = new JTextField(5);
        patientText.setText(Integer.toString(conList.size() + 1));
        patientText.setBorder(loweredBorder);
        patientText.setEditable(false);
        ID.add(patientText);
        topSection.add(clock);
        topSection.add(title);
        topSection.add(ID);

        holder.add(topSection);

        //Populating the combo box list
        equipment = new Equipment();
        eqList.addAll(equipment.getEquipments());
        list1 = new String[eqList.size()];
        for (int i = 0; i < eqList.size(); i++) {
            list1[i] = eqList.get(i).getEqName();
        }


        //Center Text Area
        JPanel textArea = new JPanel(new FlowLayout(FlowLayout.CENTER));
        additionalInformation = new JTextArea(27, 75);
        additionalInformation.setBorder(loweredBorder);
        additionalInformation.setFont(new Font("Arial", Font.ITALIC, 14));
        additionalInformation.setText(setTextArea());
        scroll = new JScrollPane(additionalInformation);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textArea.add(scroll);

        JPanel holder2 = new JPanel(new GridLayout(1, 1));
        JPanel test = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel dobs = new JPanel(new GridBagLayout());

        //Con Label
        conName = new JLabel("Consultant Name ");
        dobs.add(conName, getConstraints(0, 3, 2, 1, GridBagConstraints.WEST));

        //Con Text
        conNameText = new JTextField(50);
        conNameText.setBorder(loweredBorder);
        conNameText.setEditable(true);
        dobs.add(conNameText, getConstraints(0, 4, 5, 1, GridBagConstraints.WEST));

        //Skill Label
        conSkill = new JLabel("Consultant Skill ");
        dobs.add(conSkill, getConstraints(0, 5, 2, 1, GridBagConstraints.WEST));

        //Skill Text
        conSkillText = new JTextField(50);
        conSkillText.setBorder(loweredBorder);
        conSkillText.setEditable(true);
        dobs.add(conSkillText, getConstraints(0, 6, 5, 1, GridBagConstraints.WEST));

        //Combo label
        conEquip = new JLabel("Consultant Equip Skill");
        dobs.add(conEquip, getConstraints(0, 7, 2, 1, GridBagConstraints.WEST));

        //Equipment combo
        equipCombo = new JComboBox<>(list1);
        dobs.add(equipCombo, getConstraints(0, 8, 5, 1, GridBagConstraints.WEST));


        // Add button
        confirm = new JButton("ADD");
        confirm.addActionListener(this);
        dobs.add(confirm, getConstraints(0, 9, 1, 1, GridBagConstraints.WEST));

        // Cancel button
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        dobs.add(cancel, getConstraints(1, 9, 1, 1, GridBagConstraints.WEST));

        // Refresh button
        refresh = new JButton("Refresh List");
        refresh.addActionListener(this);
        dobs.add(refresh, getConstraints(2, 9, 1, 1, GridBagConstraints.WEST));

        // Remove button
        delete = new JButton("Remove Consultant");
        delete.addActionListener(this);
        dobs.add(delete, getConstraints(3, 9, 1, 1, GridBagConstraints.WEST));

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
        c.insets = new Insets(10, 5, 10, 10);
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
        c.anchor = anchor;
        return c;
    }

    //Pulling account information from the database and converting to a string to be displayed
    public String setTextArea() {
        list = "";
        additionalInformation.setText("");
        conList.removeAll(conList);
        conList.addAll(consultants.getConsultants());
        list = "\t\tLIST AND SKILLS OF CONSULTANTS IN THIS HOSPITAL\n\n" +
                "___________________________________________________________________________________________________" +
                "_______________________________\n\n";
        for (int i = 0; i < conList.size(); i++) {
            list += list = "\tCon ID:" + conList.get(i).getConId() + "\t\tName: " + conList.get(i).getConName() + "\t\tSkill: " + conList.get(i).getConSpeciality() + "\t\tEquip Skill: " + conList.get(i).getEquipSill() + "\n\n";

        }
        return list;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cancel)) {
            f.setVisible(false);
        } else if (e.getSource().equals(confirm)) {
            boolean test = true;
            String equipIn = (String) equipCombo.getSelectedItem();
            while (test == true) {
                if (conNameText.getText().equals("") || conSkill.getText().equals("")) {
                    if (conNameText.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Consultant Name is a required fields");
                        test = false;
                    }
                    if (conSkill.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Consultant Skill is a required fields");
                        test = false;
                    }
                } else {
                    consultants = new Consultants(conNameText.getText(), conSkillText.getText(), equipIn);
                    conNameText.setText("");
                    conSkillText.setText("");
                    test=false;
                }
            }

        } else if (e.getSource().equals(refresh)) {
            additionalInformation.setText(setTextArea());

        }else if(e.getSource().equals(delete)) {
            boolean deletion = true;
            while (deletion) {
                String input = JOptionPane.showInputDialog("Please input the Consultant ID to delete");
                Consultants consultants1 = new Consultants();
                conList.removeAll(conList);
                conList.addAll(consultants1.getConsultants());
                try {
                    for (int i = 0; i < eqList.size(); i++) {
                        if (Integer.parseInt(input) == conList.get(i).getConId()) {
                            consultants1.deleteConsultant(Integer.parseInt(input));
                            deletion = false;
                        }
                    }
                    if (deletion == true) {
                        JOptionPane.showMessageDialog(null, "Consultant does not Exist");
                        deletion = false;
                    }
                } catch (NumberFormatException nf) {
                    try {
                        if (!input.equals("")) {
                            JOptionPane.showMessageDialog(null, "Consultant ID must be Numeric");
                        }
                        deletion = false;
                    }catch (NullPointerException np){
                        deletion=false;
                    }

                }
            }
        }

    }
}
