package GUI;
    import DataBase.StockOperations;
    import Model.Bill;
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
    public class AccountsGUI extends JFrame implements ActionListener {
        JButton back,next, cancel,refresh,search;
        JLabel patientNum, label5,enterNum;
        JTextField patientText,enterNumText;
        JTextArea accountsInformation;
        JScrollPane scroll;
        private int patientNumberIn;
        private String record="This is meant to be the patient Medical Record";
        JFrame f;
        private StockOperations so;
        private ResultSet rset;

        public  AccountsGUI() {
            f = new JFrame();
            f.setTitle("Hospital Accounts");
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
            label5 = new JLabel("Hospital Accounts");
            title.add(label5);
            label5.setFont(new Font("Arial", Font.BOLD, 32));

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

            accountsInformation= new JTextArea(35, 70);
            accountsInformation.setBorder(loweredBorder);
            accountsInformation.setFont(new Font("Arial", Font.ITALIC, 14));
            accountsInformation.setText(setTextArea());
            scroll = new JScrollPane(accountsInformation);
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

            // Cancel button
            cancel = new JButton("Cancel");
            cancel.addActionListener(this);
            dobs.add(cancel, getConstraints(0, 4, 1, 1, GridBagConstraints.WEST));

            // Refresh button
            refresh = new JButton("Refresh Record");
            refresh.addActionListener(this);
            dobs.add(refresh, getConstraints(1, 4, 1, 1, GridBagConstraints.WEST));

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
            String textAreaString="";
            so=new StockOperations();
            rset=so.getAccounts();
            textAreaString = "\n\tAccount ID\tMedicine\tEquipment\t\tRunning Total\t\tDate\t\tPatient Number\n\n";
            try{
                while(rset.next()){
                    textAreaString+= "\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                            "\t"+Integer.toString(rset.getInt(1))+"\t"+Bill.df.format(rset.getDouble(2))+"\t"+Bill.df.format(rset.getDouble(3))+"\t\t"+Bill.df.format(rset.getDouble(4))+"\t\t"+rset.getString(5)+"\t\t"+Integer.toString(rset.getInt(6))+"\n";

                }
            }catch (SQLException sq){

            }
            accountsInformation.setText(textAreaString);
            so.stockOperationsClose();
            return textAreaString;
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(cancel)) {
                f.setVisible(false);
            } else if (e.getSource().equals(search)) {
                accountsInformation.setText("");
                String textAreaString="";
                so=new StockOperations();
                rset=so.getAccounts(Integer.parseInt(enterNumText.getText()));
                textAreaString = "\n\tAccount ID\tMedicine\tEquipment\t\tRunning Total\t\tDate\t\tPatient Number\n\n";
                try{
                    while(rset.next()){
                        textAreaString+= "\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                                "\t"+Integer.toString(rset.getInt(1))+"\t"+Bill.df.format(rset.getDouble(2))+"\t"+Bill.df.format(rset.getDouble(3))+"\t\t"+Bill.df.format(rset.getDouble(4))+"\t\t"+rset.getString(5)+"\t\t"+Integer.toString(rset.getInt(6))+"\n";

                    }
                }catch (SQLException sq){

                }
                so.stockOperationsClose();
                patientText.setText(enterNumText.getText());
                accountsInformation.setText(textAreaString);
            }else if (e.getSource().equals(refresh)) {
                accountsInformation.setText(setTextArea());
                patientText.setText("");

            }

        }
    }
