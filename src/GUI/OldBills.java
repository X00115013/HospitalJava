package GUI;

import DataBase.StockOperations;
import Model.EquipmentUsed;
import Model.MedicalRecord;
import Model.Prescription;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Thomas Murray on 20/04/2015.
 */
public class OldBills extends JFrame implements ActionListener {
    JButton back,next, cancel,refresh,search;
    JLabel patientNum, label5,enterNum;
    JTextField patientText,enterNumText;
    JTextArea oldBillInformation;
    JScrollPane scroll;
    private int patientNumberIn;
    private String record="\n\n\n\t\t\t\tSelect a Patients Number";
    private ArrayList<String>billList=new ArrayList<>();
    JFrame f;
    private StockOperations so;
    private ResultSet rset;

    public  OldBills() {
        f = new JFrame();
        f.setTitle("Old Bills");
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
        label5 = new JLabel("Old Patient Bills ");
        title.add(label5);
        label5.setFont(new Font("Arial", Font.BOLD, 36));

        JPanel ID = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //labels
        patientNum = new JLabel("\tPatient Number");
        ID.add(patientNum);
        //text field
        patientText = new JTextField(5);
        patientText.setText("");
        patientText.setBorder(loweredBorder);
        patientText.setEditable(false);
        ID.add(patientText);


        topSection.add(clock);
        topSection.add(title);
        topSection.add(ID);
//        f.add(topSection);

        holder.add(topSection);
        JPanel textArea = new JPanel(new FlowLayout(FlowLayout.CENTER));

        oldBillInformation = new JTextArea(40, 70);
        oldBillInformation.setBorder(loweredBorder);
        oldBillInformation.setText(setTextArea());
        scroll = new JScrollPane(oldBillInformation);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textArea.add(scroll);

        JPanel holder2 = new JPanel(new GridLayout(1, 1));
        JPanel test = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel dobs = new JPanel(new GridBagLayout());


        //Search Bar
        enterNum=new JLabel("Enter Patient Number");
        dobs.add(enterNum,getConstraints(0,2,3,1, GridBagConstraints.WEST));

        enterNumText=new JTextField(15);
        enterNumText.setBorder(loweredBorder);
        dobs.add(enterNumText,getConstraints(0,3,5,1, GridBagConstraints.WEST));

        // Search button
        search = new JButton("Search");
        search.addActionListener(this);
        dobs.add(search, getConstraints(4, 3, 1, 1, GridBagConstraints.WEST));

//        // Back button
//        back = new JButton("Back");
//        back.addActionListener(this);
//        dobs.add(back, getConstraints(0, 4, 1, 1, GridBagConstraints.WEST));

        // Next button
        next = new JButton("Next");
        next.addActionListener(this);
        dobs.add(next, getConstraints(0, 4, 1, 1, GridBagConstraints.WEST));

        // Cancel button
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        dobs.add(cancel, getConstraints(1, 4, 1, 1, GridBagConstraints.WEST));

        // Refresh button
        refresh = new JButton("Reset");
        refresh.addActionListener(this);
        dobs.add(refresh, getConstraints(2, 4, 1, 1, GridBagConstraints.WEST));

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
      return record;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cancel)) {
            f.setVisible(false);
        } else if (e.getSource().equals(search)) {
            billList.removeAll(billList);
            oldBillInformation.setText("");
            String textAreaString="";
            so=new StockOperations();
            rset=so.getBill(Integer.parseInt(enterNumText.getText()));
            try{
                while(rset.next()){
                    billList.add("\n   Bill ID\n\n   "+Integer.toString(rset.getInt(1))+"\n\n"+rset.getString(2)+"\n\n   Date Paid\n\n"+rset.getString(3)+"\n\n   Patient who Paid\n\n   "+Integer.toString(rset.getInt(4))+"\n\n" +
                            "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n");
                }
            }catch (SQLException sq){
            }
            so.stockOperationsClose();
            patientText.setText(enterNumText.getText());
            oldBillInformation.setText(billList.get(0));
        }
        else if (e.getSource().equals(next)) {
            try {
                oldBillInformation.setText(billList.get(+1));
            }catch (IndexOutOfBoundsException id){
                JOptionPane.showMessageDialog(null, "No More Records for Patient "+enterNumText.getText());
            }

        }else if (e.getSource().equals(refresh)) {
            oldBillInformation.setText(record);
            enterNumText.setText("");

        }

    }
}