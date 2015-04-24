package GUI;

    import Model.*;

    import javax.swing.*;
    import javax.swing.border.BevelBorder;
    import javax.swing.border.Border;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.geom.Arc2D;
    import java.util.ArrayList;

    /**
     * Created by Thomas Murray on 04/04/2015.
     */


    public class AddMedicineGUI extends JFrame implements ActionListener {
        JButton confirm, cancel, refresh, delete, update;
        JLabel patientNum, titleF, reason, medName, medPrice, stock;
        JTextField patientText, medNameText, medPriceText, stockText;
        JTextArea additionalInformation;
        JScrollPane scroll;
        private ArrayList<Medicine> medList = new ArrayList<>();
        private int patientNumberIn;
        private Medicine medicine;
        private String list = "This should work";

        JFrame f;

        public AddMedicineGUI() {
            medicine = new Medicine();
            medList.addAll(medicine.getMedicines());
            f = new JFrame();
            f.setTitle("Add Medicine");
            f.setSize(885, 1000);
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
            titleF = new JLabel("Add Medicine");
            title.add(titleF);
            titleF.setFont(new Font("Arial", Font.BOLD, 43));

            JPanel ID = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            //labels
            patientNum = new JLabel("\tMedicine Number");
            ID.add(patientNum);
            //text field
            patientText = new JTextField(5);
            patientText.setText(Integer.toString(medList.size() + 1));
            patientText.setBorder(loweredBorder);
            patientText.setEditable(false);
            ID.add(patientText);


            topSection.add(clock);
            topSection.add(title);
            topSection.add(ID);

            holder.add(topSection);
            JPanel textArea = new JPanel(new FlowLayout(FlowLayout.CENTER));
            additionalInformation = new JTextArea(30, 75);
            additionalInformation.setBorder(loweredBorder);
            additionalInformation.setText(setTextArea());
            scroll = new JScrollPane(additionalInformation);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            textArea.add(scroll);

            JPanel holder2 = new JPanel(new GridLayout(1, 1));
            JPanel test = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JPanel dobs = new JPanel(new GridBagLayout());

            medName = new JLabel("Medicine Name ");
            dobs.add(medName, getConstraints(0, 3, 2, 1, GridBagConstraints.WEST));

            medNameText = new JTextField(50);
            medNameText.setBorder(loweredBorder);
            medNameText.setEditable(true);
            dobs.add(medNameText, getConstraints(0, 4, 5, 1, GridBagConstraints.WEST));

            medPrice = new JLabel("Medicine Cost ");
            dobs.add(medPrice, getConstraints(0, 5, 2, 1, GridBagConstraints.WEST));

            medPriceText = new JTextField(50);
            medPriceText.setBorder(loweredBorder);
            medPriceText.setEditable(true);
            dobs.add(medPriceText, getConstraints(0, 6, 5, 1, GridBagConstraints.WEST));

            stock = new JLabel("Stock Level ");
            dobs.add(stock, getConstraints(0, 7, 2, 1, GridBagConstraints.WEST));

            stockText = new JTextField(15);
            stockText.setBorder(loweredBorder);
            stockText.setEditable(true);
            dobs.add(stockText, getConstraints(0, 8, 5, 1, GridBagConstraints.WEST));


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

            // delete button
            delete = new JButton("Remove Medicine");
            delete.addActionListener(this);
            dobs.add(delete, getConstraints(3, 9, 1, 1, GridBagConstraints.WEST));

            // Refresh button
            update = new JButton("Update Stock Level");
            update.addActionListener(this);
            dobs.add(update, getConstraints(4, 9, 1, 1, GridBagConstraints.WEST));

            test.add(dobs);
            holder2.add(test);


            f.add(holder);
            f.setLayout(new FlowLayout(FlowLayout.LEFT));
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

        public String setTextArea() {
            additionalInformation.setText("");
            medList.removeAll(medList);
            medList.addAll(medicine.getMedicines());
            list = "\t\tLIST AND PRICE OF MEDICINE IN THIS HOSPITAL\n\n" +
                    "___________________________________________________________________________________________________" +
                    "_______________________________\n\n";
            for (int i = 0; i < medList.size(); i++) {
                list += list = "\tMachine ID:" + medList.get(i).getMedId() + "\t\tName: " + medList.get(i).getMedName() + "\t\tIn Stock: " + medList.get(i).getStockLevel() + "\t\tCost: " + Bill.df.format(medList.get(i).getPrice()) + "\n\n";
                if(medList.get(i).getStockLevel() > 0 && medList.get(i).getStockLevel()<=10){
            JOptionPane.showMessageDialog(null, medList.get(i).getMedName()+" is low on stock, "+medList.get(i).getStockLevel()+" remain");
        }else if (medList.get(i).getStockLevel() <=0){
            JOptionPane.showMessageDialog(null, medList.get(i).getMedName()+" is out on stock");
        }

            }
            return list;
        }


        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(cancel)) {
                f.setVisible(false);
            } else if (e.getSource().equals(confirm)) {
                boolean test = true,test2=true;
                int convert = 0;
                double convert2 = 0.0;
                while (test == true) {
                    try {
                        if (medNameText.getText().equals("") || Integer.parseInt(stockText.getText()) == 0 || Double.parseDouble(medPriceText.getText()) == 0.0) {
                            if (medNameText.getText().equals("")) {
                                JOptionPane.showMessageDialog(null, "Medicine Name is a required field");
                                test = false;
                            }
                            if (Integer.parseInt(stockText.getText()) == 0) {
                                JOptionPane.showMessageDialog(null, "Stock Level is a required field");
                                test = false;
                            }
                            if (Double.parseDouble(medPriceText.getText()) == 0.0) {
                                JOptionPane.showMessageDialog(null, "Medicine Price is a required field");
                                test = false;
                            }
                        } else {
                            convert = Integer.parseInt(stockText.getText());
                            convert2 = Double.parseDouble(medPriceText.getText());
                            for (int i = 0; i <medList.size() ; i++) {
                                if (medNameText.getText().equalsIgnoreCase(medList.get(i).getMedName())) {
                                    medicine = new Medicine();
                                    int total=medList.get(i).getStockLevel()+convert;
                                    medicine.updateStock(medList.get(i).getMedId(),total,convert2 );
                                    JOptionPane.showMessageDialog(null,medList.get(i).getMedName()+" has Been Updated");
                                    medNameText.setText("");
                                    medNameText.setEditable(true);
                                    medPriceText.setText("");
                                    stockText.setText("");
                                    test2=false;
                                    test=false;
                                }
                            }
                            if(test2==true) {
                                medicine = new Medicine(medNameText.getText(), convert, convert2);
                                medNameText.setText("");
                                medPriceText.setText("");
                                stockText.setText("");
                                test = false;
                            }
                        }
                    } catch (NumberFormatException nf) {
                        JOptionPane.showMessageDialog(null, "Price and Amount must be of Number Format");
                        test = false;
                    }
                }


            } else if (e.getSource().equals(refresh)) {
                additionalInformation.setText(setTextArea());


            } else if (e.getSource().equals(delete)) {
                boolean deletion = true;

                    String input = JOptionPane.showInputDialog("Please input the Medicine ID to delete");
                    Medicine medicine1 = new Medicine();
                    medList.removeAll(medList);
                    medList.addAll(medicine1.getMedicines());
                while (deletion) {
                    try {
                        for (Medicine aMedList : medList) {
                            if (Integer.parseInt(input) == aMedList.getMedId()) {
                                medicine1.deleteMedicine(Integer.parseInt(input));
                                deletion = false;
                            }
                        }
                        if (deletion == true) {
                            JOptionPane.showMessageDialog(null, "Medicine does not Exist");
                            deletion = false;
                        }
                    } catch (NumberFormatException nf) {
                        try {
                            if (!input.equals("")) {
                                JOptionPane.showMessageDialog(null, "Medicine ID must be Numeric");
                                deletion = false;
                            }
                        } catch (NullPointerException np) {
                            deletion=false;
                        }
                    }
                }

                }else if(e.getSource().equals(update)){
                boolean deletion = true;
                String input = JOptionPane.showInputDialog("Please input the Medicine ID to Update");
                Medicine medicine1 = new Medicine();
                medList.removeAll(medList);
                medList.addAll(medicine1.getMedicines());
                while (deletion) {
                try {
                    for (int i = 0; i < medList.size(); i++) {
                        if (Integer.parseInt(input) == medList.get(i).getMedId()) {
                            medNameText.setText(medList.get(i).getMedName());
                            medNameText.setEditable(false);
                            medPriceText.setText(Double.toString(medList.get(i).getPrice()));
//                            stockText.setText(Integer.toString(medList.get(i).getStockLevel()));
                            deletion = false;
                        }
                    }
                    if (deletion == true) {
                        JOptionPane.showMessageDialog(null, "Medicine does not Exist");
                        deletion = false;
                    }
                } catch (NumberFormatException nf) {
                    try {
                        if (!input.equals("")) {
                            JOptionPane.showMessageDialog(null, "Medicine ID must be Numeric");
                            deletion = false;
                        }
                    } catch (NullPointerException np) {
                        deletion=false;
                    }
                }
            }
            }
            }
        }
