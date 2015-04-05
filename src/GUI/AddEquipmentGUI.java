package GUI;

import Model.Equipment;
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


    public class AddEquipmentGUI extends JFrame implements ActionListener
    {
        JButton confirm,cancel,refresh;
        JLabel patientNum,titleF,reason,equipName,equipPrice;
        JTextField patientText,equipNameText, equipPriceText;
        JTextArea additionalInformation;
        JScrollPane scroll;
        private ArrayList<Equipment>eqList=new ArrayList<>();
        private int patientNumberIn;
        private Equipment equipment;
        private String list="This should work";

        JFrame f;

        public AddEquipmentGUI()
        {
            equipment=new Equipment();
            eqList.addAll(equipment.getEquipments());
            f = new JFrame();
            f.setTitle("Add Equipment");
            f.setSize(790, 930);
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
            titleF = new JLabel("Add Equipment");
            title.add(titleF);
            titleF.setFont(new Font("Arial", Font.BOLD, 24));

            JPanel ID=new JPanel(new FlowLayout(FlowLayout.RIGHT));
            //labels
            patientNum = new JLabel("\tEquipment Number");
            ID.add(patientNum);
            //text field
            patientText = new JTextField(5);
            patientText.setText(Integer.toString(eqList.size()+1));
            patientText.setBorder(loweredBorder);
            patientText.setEditable(false);
            ID.add(patientText);


            topSection.add(clock);
            topSection.add(title);
            topSection.add(ID);

            holder.add(topSection);

            list ="\t\tLIST AND PRICE OF EQUIPMENT IN THIS HOSPITAL\n\n" +
                    "_________________________________________________________________________________________" +
                    "\n\n";
            for (int i = 0; i < eqList.size(); i++) {
                list +=list="\tMachine ID:"+eqList.get(i).getEqId()+"\t\tName: "+eqList.get(i).getEqName()+"\t\tCost: "+eqList.get(i).getPrice()+"\n\n";

            }


            JPanel textArea=new JPanel(new FlowLayout(FlowLayout.CENTER));
            additionalInformation = new JTextArea(30,65);
            additionalInformation.setBorder(loweredBorder);
            additionalInformation.setText(list);
            scroll = new JScrollPane(additionalInformation);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            textArea.add(scroll);

            JPanel holder2=new JPanel(new GridLayout(1,1));
            JPanel test =new JPanel(new FlowLayout(FlowLayout.LEFT));
            JPanel dobs = new JPanel(new GridBagLayout());

            equipName=new JLabel("Equipment Name ");
            dobs.add(equipName, getConstraints(0, 3, 2, 1, GridBagConstraints.WEST));

            equipNameText=new JTextField(50);
            equipNameText.setBorder(loweredBorder);
            equipNameText.setEditable(true);
            dobs.add(equipNameText, getConstraints(0, 4, 5, 1, GridBagConstraints.WEST));

            equipPrice=new JLabel("Equipment Cost Per Usage ");
            dobs.add(equipPrice, getConstraints(0, 5, 2, 1, GridBagConstraints.WEST));

            equipPriceText=new JTextField(50);
            equipPriceText.setBorder(loweredBorder);
            equipPriceText.setEditable(true);
            dobs.add(equipPriceText, getConstraints(0, 6, 5, 1, GridBagConstraints.WEST));



            // Confirm button
            confirm = new JButton("ADD");
            confirm.addActionListener(this);
            dobs.add(confirm, getConstraints(0, 7, 1, 1, GridBagConstraints.WEST));

            // Cancel button
            cancel = new JButton("Cancel");
            cancel.addActionListener(this);
            dobs.add(cancel, getConstraints(1, 7, 1, 1, GridBagConstraints.WEST));

            // Refresh button
            refresh = new JButton("Refresh List");
            refresh.addActionListener(this);
            dobs.add(refresh, getConstraints(2, 7, 1, 1, GridBagConstraints.WEST));

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
                equipment=new Equipment(equipNameText.getText(), Double.parseDouble(equipPriceText.getText()));
                equipNameText.setText("");
                equipPriceText.setText("");

            }else if(e.getSource().equals(refresh)){
                eqList.removeAll(eqList);
                eqList.addAll(equipment.getEquipments());
                list ="\t\tLIST AND PRICE OF EQUIPMENT IN THIS HOSPITAL\n\n" +
                        "___________________________________________________________________________________________________" +
                        "_______________________________\n\n";
                for (int i = 0; i < eqList.size(); i++) {
                    list +=list="\tMachine ID:"+eqList.get(i).getEqId()+"\t\tName: "+eqList.get(i).getEqName()+"\t\tCost: "+eqList.get(i).getPrice()+"\n\n";
                    additionalInformation.setText("");
                    additionalInformation.setText(list);
                    equipNameText.setText("");
                    equipPriceText.setText("");

                }
            }
        }
    }