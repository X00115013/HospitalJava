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

    /**
     * Created by Roland on 04/04/2015.
     */


    public class AddConsultantGUI extends JFrame implements ActionListener
    {
        JButton confirm,cancel,refresh;
        JLabel patientNum,titleF,reason,conName,conSkill,conEquip;
        JTextField patientText,conNameText, conSkillText,conEquipText;
        JTextArea additionalInformation;
        JScrollPane scroll;
        private ArrayList<Consultants> conList=new ArrayList<>();
        private int patientNumberIn;
        private Consultants consultants;
        private String list="This should work";

        JFrame f;

        public AddConsultantGUI()
        {
            consultants=new Consultants();
            conList.addAll(consultants.getConsultants());
            f = new JFrame();
            f.setTitle("Add Consultant");
            f.setSize(885, 1000);
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
            titleF = new JLabel("Add Consultant");
            title.add(titleF);
            titleF.setFont(new Font("Arial", Font.BOLD, 24));

            JPanel ID=new JPanel(new FlowLayout(FlowLayout.RIGHT));
            //labels
            patientNum = new JLabel("\tConsultant Number");
            ID.add(patientNum);
            //text field
            patientText = new JTextField(5);
            patientText.setText(Integer.toString(conList.size()+1));
            patientText.setBorder(loweredBorder);
            patientText.setEditable(false);
            ID.add(patientText);


            topSection.add(clock);
            topSection.add(title);
            topSection.add(ID);

            holder.add(topSection);

            list ="\t\tLIST AND SKILLS OF CONSULTANTS IN THIS HOSPITAL\n\n" +
                    "_________________________________________________________________________________________" +
                    "\n\n";
            for (int i = 0; i < conList.size(); i++) {
                list +=list="\tCon ID:"+conList.get(i).getConId()+"\t\tName: "+conList.get(i).getConName()+"\t\tSkill: "+conList.get(i).getConSpeciality()+"\t\tEquip Skill: "+conList.get(i).getEquipSill()+"\n\n";

            }


            JPanel textArea=new JPanel(new FlowLayout(FlowLayout.CENTER));
            additionalInformation = new JTextArea(30,75);
            additionalInformation.setBorder(loweredBorder);
            additionalInformation.setText(list);
            scroll = new JScrollPane(additionalInformation);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            textArea.add(scroll);

            JPanel holder2=new JPanel(new GridLayout(1,1));
            JPanel test =new JPanel(new FlowLayout(FlowLayout.LEFT));
            JPanel dobs = new JPanel(new GridBagLayout());

            conName=new JLabel("Consultant Name ");
            dobs.add(conName, getConstraints(0, 3, 2, 1, GridBagConstraints.WEST));

            conNameText=new JTextField(50);
            conNameText.setBorder(loweredBorder);
            conNameText.setEditable(true);
            dobs.add(conNameText, getConstraints(0, 4, 5, 1, GridBagConstraints.WEST));

            conSkill=new JLabel("Consultant Skill ");
            dobs.add(conSkill, getConstraints(0, 5, 2, 1, GridBagConstraints.WEST));

            conSkillText=new JTextField(50);
            conSkillText.setBorder(loweredBorder);
            conSkillText.setEditable(true);
            dobs.add(conSkillText, getConstraints(0, 6, 5, 1, GridBagConstraints.WEST));

            conEquip=new JLabel("Consultant Equip Skill");
            dobs.add(conEquip, getConstraints(0, 7, 2, 1, GridBagConstraints.WEST));

            conEquipText=new JTextField(50);
            conEquipText.setBorder(loweredBorder);
            conEquipText.setEditable(true);
            dobs.add(conEquipText, getConstraints(0, 8, 5, 1, GridBagConstraints.WEST));



            // Confirm button
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


        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource().equals(cancel))
            {
                f.setVisible(false);
            }
            else if (e.getSource().equals(confirm))
            {
                consultants=new Consultants(conNameText.getText(), conSkillText.getText(),conEquipText.getText());
                conNameText.setText("");
                conEquipText.setText("");
                conSkillText.setText("");

            }else if(e.getSource().equals(refresh)){
                conList.removeAll(conList);
                conList.addAll(consultants.getConsultants());
                list ="\t\tLIST AND SKILLS OF CONSULTANTS IN THIS HOSPITAL\n\n" +
                        "___________________________________________________________________________________________________" +
                        "_______________________________\n\n";
                for (int i = 0; i < conList.size(); i++) {
                    list +=list="\tCon ID:"+conList.get(i).getConId()+"\t\tName: "+conList.get(i).getConName()+"\t\tSkill: "+conList.get(i).getConSpeciality()+"\t\tEquip Skill: "+conList.get(i).getEquipSill()+"\n\n";
                    additionalInformation.setText("");
                    additionalInformation.setText(list);
                    conNameText.setText("");
                    conEquipText.setText("");
                    conSkillText.setText("");

                }
            }
        }
    }
