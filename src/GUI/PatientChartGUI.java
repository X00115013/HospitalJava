package GUI;

import DataBase.PatientOperations;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Thomas Murray on 29/03/2015.
 */



public class PatientChartGUI extends JFrame implements ActionListener
{
    JButton edit,print,cancel;
    JLabel patientNum,label5;
    JTextField patientText;
    JTextArea chartInformation;
    JRadioButton checkOutRadio;
    JScrollPane scroll;
    private ResultSet rset;
    private String chart="This should work chart";
    private int patientNumberIn;

    JFrame f;

    public PatientChartGUI(int patientNumIn)
    {
        patientNumberIn=patientNumIn;
        f = new JFrame();
        f.setTitle("Patient Chart");
        f.setSize(805, 1000);
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
        label5 = new JLabel("      Patient Chart            ");
        title.add(label5);
        label5.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel ID=new JPanel(new FlowLayout(FlowLayout.CENTER));
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

        try {
            PatientOperations po=new PatientOperations();
            rset = po.getPatientChart(patientNumIn);
            while (rset.next()) {
                chart= "\n--------------------------------------------------------------------------------------------------------------------\n"+
                        "\n  Patient \n  Number \t"+rset.getInt(1)+"\n\n" +
                        "\n--------------------------------------------------------------------------------------------------------------------\n"+
                        "  Patient \n  First Name \t"+rset.getString(2)+"\n\n" +
                        "\n--------------------------------------------------------------------------------------------------------------------\n"+
                        "  Patient \n  Surname \t"+rset.getString(3)+"\n\n" +
                        "\n--------------------------------------------------------------------------------------------------------------------\n"+
                        "  Patient \n  DOB \t"+rset.getString(4)+"\n\n" +
                        "\n--------------------------------------------------------------------------------------------------------------------\n"+
                        "  Patient \n  Gender \t"+rset.getString(5)+"\n\n" +
                        "\n--------------------------------------------------------------------------------------------------------------------\n"+
                        "  Patient \n  Blood Type \t"+rset.getString(6)+"\n\n" +
                        "\n--------------------------------------------------------------------------------------------------------------------\n"+
                        "  Patient \n  Symptoms \t"+rset.getString(7)+"\n\n" +
                        "\n--------------------------------------------------------------------------------------------------------------------\n"+
                        "  Patient \n  Diagnoses \t"+rset.getString(8)+"\n\n" +
                        "\n--------------------------------------------------------------------------------------------------------------------\n"+
                        "  Required \n  Treatment \t"+rset.getString(9)+"\n\n" +
                        "\n--------------------------------------------------------------------------------------------------------------------\n"+
                        "  Patient \n  Allergies \t"+rset.getString(10)+"\n\n"+
                        "\n--------------------------------------------------------------------------------------------------------------------\n"+
                        "  GP \n  Recommendations \t"+rset.getString(11)+"\n\n"+
                        "\n--------------------------------------------------------------------------------------------------------------------\n";
            }
        } catch (SQLException e1) {
            System.out.println(e1);
        }


        JPanel textArea=new JPanel(new FlowLayout(FlowLayout.CENTER));

        chartInformation = new JTextArea(40,70);
        chartInformation.setBorder(loweredBorder);
        chartInformation.setText(chart);
        chartInformation.setEditable(false);
        scroll = new JScrollPane(chartInformation);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textArea.add(scroll);

//        holder.add(textArea);


        JPanel holder2=new JPanel(new GridLayout(1,1));
        JPanel test =new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel dobs = new JPanel(new GridBagLayout());

        // Edit button
        edit = new JButton("Edit");
        edit.addActionListener(this);
        dobs.add(edit, getConstraints(0, 4, 1, 1, GridBagConstraints.WEST));

        // Print button
        print = new JButton("Print");
        print.addActionListener(this);
        dobs.add(print, getConstraints(1, 4, 1, 1, GridBagConstraints.WEST));

        // Cancel button
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        dobs.add(cancel, getConstraints(2, 4, 1, 1, GridBagConstraints.WEST));

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


    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cancel)) {
            f.setVisible(false);
        } else if (e.getSource().equals(edit)){
            chartInformation.setEditable(true);

        }else if (e.getSource().equals(print)){
            File Files = new File("files");
            File textFile = new File(Files, ""+patientNumberIn+"_CHART.txt");
            try (FileWriter input = new FileWriter(textFile)) {
                input.write(chartInformation.getText());
                JOptionPane.showMessageDialog(null, "Patient "+patientNumberIn+" Chart is printing....");
            } catch (IOException io) {
                System.out.println(io);
            }
        }
    }
}