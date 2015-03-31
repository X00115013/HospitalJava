package GUI;

import Model.*;

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
public class TimeTablesGUI extends JFrame implements ActionListener
{
    JButton print,cancel;
    String[] list1 = {"Consultant :","XRay :", "MRI Scan :", "CT Scan :"};
    JLabel patientNum,label5;
    JTextArea timeTableInformation;
    JComboBox<String> combo1;
    JScrollPane scroll;
    JFrame f;
    private ArrayList<XRayTimeTable> xRayTimeTables=new ArrayList<>();
    private ArrayList<CTTimeTables> cTTimeTables=new ArrayList<>();
    private ArrayList<MRITimeTable> mRITimeTables=new ArrayList<>();
    private ArrayList<ConsultantTimeTable> conTimeTables=new ArrayList<>();
    private String contable="This table should be working con Time Tables ";
    private String xraytable="This table should be working xray Time Tables ";
    private String cttable="This table should be working ct Time Tables ";
    private String mritable="This table should be working mri Time Tables ";

    public TimeTablesGUI()
    {
        f = new JFrame();
        f.setTitle("Time Tables");
        f.setSize(805, 1010);
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
        label5 = new JLabel("Time Tables");
        title.add(label5);
        label5.setFont(new Font("Arial", Font.BOLD, 42));

        topSection.add(clock);
        topSection.add(title);
        holder.add(topSection);

        TimeTables timeTables=new TimeTables();
        conTimeTables.addAll(timeTables.getConsultantTimeTable());
        xRayTimeTables.addAll(timeTables.getxRayTimeTable());
        mRITimeTables.addAll(timeTables.getmRITimeTable());
        cTTimeTables.addAll(timeTables.getcTScanTimeTable());

        JPanel textArea=new JPanel(new FlowLayout(FlowLayout.CENTER));

        timeTableInformation = new JTextArea(40,70);
        timeTableInformation.setBorder(loweredBorder);
        timeTableInformation.setEditable(false);
        scroll = new JScrollPane(timeTableInformation);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textArea.add(scroll);


        JPanel holder2=new JPanel(new GridLayout(3,1));
        JPanel test =new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel buttons = new JPanel(new GridBagLayout());
        JPanel selectionCombo=new JPanel(new FlowLayout(FlowLayout.RIGHT));

        combo1 = new JComboBox<String>(list1);
        combo1.setPreferredSize(new Dimension(300, 20));
        selectionCombo.add(combo1);
        combo1.addActionListener(this);

        // Print button
        print = new JButton("Print");
        print.addActionListener(this);
        buttons.add(print, getConstraints(0, 4, 1, 1, GridBagConstraints.WEST));

        // Cancel button
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        buttons.add(cancel, getConstraints(1, 4, 1, 1, GridBagConstraints.WEST));

        test.add(buttons);
        holder2.add(selectionCombo);
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
            System.exit(0);
        } else if (e.getSource().equals(print)){

        }else if (e.getSource().equals(combo1)){
            String tableSelection = (String) combo1.getSelectedItem();
            if(tableSelection.equals("Consultant :")){
                contable="";
                contable="\n\n\tMon\tTues\tWed\tThur\tFri\tSat\tSun\n" +
                        "---------------------------------------------------------------------------------------------------" +
                        "---------------------------------------------------------------------------------------------";
                for (int i = 0; i < conTimeTables.size(); i++) {
                    if (i < 6) {
                        contable += contable = "\n"+ conTimeTables.get(i).getTimeIn() + "\t" + conTimeTables.get(i).getConsultantNameIn() + "\n";
                    }else if(i<12){
                        contable += contable = "\n" +
                                "\t\t" + conTimeTables.get(i).getConsultantNameIn() + "\n";
                    }else if(i<18){
                        contable += contable = "\n" +
                                "\t\t\t" + conTimeTables.get(i).getConsultantNameIn() + "\n";
                    }else if(i<24){
                        contable += contable = "\n" +
                                "\t\t\t\t" + conTimeTables.get(i).getConsultantNameIn() + "\n";
                    }else if(i<30){
                        contable += contable = "\n" +
                                "\t\t\t\t\t" + conTimeTables.get(i).getConsultantNameIn() + "\n";
                    }else if(i<36){
                        contable += contable = "\n" +
                                "\t\t\t\t\t\t" + conTimeTables.get(i).getConsultantNameIn() + "\n";
                    }else if(i<42){
                        contable += contable = "\n" +
                                "\t\t\t\t\t\t\t" + conTimeTables.get(i).getConsultantNameIn() + "\n";
                    }
                }
                timeTableInformation.setText(contable);
                contable="";

            }else if(tableSelection.equals("XRay :")){
                xraytable="";
                xraytable="\n\n\tMon\tTues\tWed\tThur\tFri\tSat\tSun\n" +
                        "---------------------------------------------------------------------------------------------------" +
                        "---------------------------------------------------------------------------------------------";
                for (int i = 0; i < xRayTimeTables.size(); i++) {
                    if (i < 24) {
                        xraytable += xraytable = "\n"+ xRayTimeTables.get(i).getTimeIn() + "\t" + xRayTimeTables.get(i).getConsultantNameIn() + "\n";
                    }else if(i<48){
                        xraytable += xraytable = "\n" +
                                "\t\t" +xRayTimeTables.get(i).getTaken()+"\n\t\t"+ xRayTimeTables.get(i).getConsultantNameIn() + "\n";
                    }else if(i<72){
                        xraytable += xraytable = "\n" +
                                "\t\t\t" +xRayTimeTables.get(i).getTaken()+"\n\t\t\t" + xRayTimeTables.get(i).getConsultantNameIn() + "\n";
                    }else if(i<96){
                        xraytable += xraytable = "\n" +
                                "\t\t\t\t" +xRayTimeTables.get(i).getTaken()+"\n\t\t\t\t"+  xRayTimeTables.get(i).getConsultantNameIn() + "\n";
                    }else if(i<120){
                        xraytable += xraytable = "\n" +
                                "\t\t\t\t\t" + xRayTimeTables.get(i).getTaken()+"\n\t\t\t\t\t"+ xRayTimeTables.get(i).getConsultantNameIn() + "\n";
                    }else if(i<144){
                        xraytable += xraytable = "\n" +
                                "\t\t\t\t\t\t" + xRayTimeTables.get(i).getTaken()+"\n\t\t\t\t\t\t"+ xRayTimeTables.get(i).getConsultantNameIn() + "\n";
                    }else if(i<168){
                        xraytable += xraytable = "\n" +
                                "\t\t\t\t\t\t\t" +xRayTimeTables.get(i).getTaken()+"\n\t\t\t\t\t\t\t"+  xRayTimeTables.get(i).getConsultantNameIn() + "\n";
                    }
                }
                timeTableInformation.setText(xraytable);
                xraytable="";

            }else if(tableSelection.equals("MRI Scan :")){
                mritable="";
                mritable="\n\n\tMon\tTues\tWed\tThur\tFri\tSat\tSun\n" +
                        "---------------------------------------------------------------------------------------------------" +
                        "---------------------------------------------------------------------------------------------";
                for (int i = 0; i < mRITimeTables.size(); i++) {
                    if (i < 24) {
                        mritable += mritable = "\n"+ mRITimeTables.get(i).getTimeIn() + "\t" + mRITimeTables.get(i).getConsultantNameIn() + "\n";
                    }else if(i<48){
                        mritable += mritable = "\n" +
                                "\t\t" +mRITimeTables.get(i).getTaken()+"\n\t\t"+ mRITimeTables.get(i).getConsultantNameIn() + "\n";
                    }else if(i<72){
                        mritable += mritable = "\n" +
                                "\t\t\t" +mRITimeTables.get(i).getTaken()+"\n\t\t\t" + mRITimeTables.get(i).getConsultantNameIn() + "\n";
                    }else if(i<96){
                        mritable += mritable = "\n" +
                                "\t\t\t\t" +mRITimeTables.get(i).getTaken()+"\n\t\t\t\t"+  mRITimeTables.get(i).getConsultantNameIn() + "\n";
                    }else if(i<120){
                        mritable += mritable = "\n" +
                                "\t\t\t\t\t" + mRITimeTables.get(i).getTaken()+"\n\t\t\t\t\t"+ mRITimeTables.get(i).getConsultantNameIn() + "\n";
                    }else if(i<144){
                        mritable += mritable = "\n" +
                                "\t\t\t\t\t\t" + mRITimeTables.get(i).getTaken()+"\n\t\t\t\t\t\t"+ mRITimeTables.get(i).getConsultantNameIn() + "\n";
                    }else if(i<168){
                        mritable += mritable = "\n" +
                                "\t\t\t\t\t\t\t" +mRITimeTables.get(i).getTaken()+"\n\t\t\t\t\t\t\t"+  mRITimeTables.get(i).getConsultantNameIn() + "\n";
                    }
                }
                timeTableInformation.setText(mritable);
                mritable="";

            }else if(tableSelection.equals("CT Scan :")){
                cttable="";
                cttable="\n\n\tMon\tTues\tWed\tThur\tFri\tSat\tSun\n" +
                        "---------------------------------------------------------------------------------------------------" +
                        "---------------------------------------------------------------------------------------------";
                for (int i = 0; i < cTTimeTables.size(); i++) {
                    if (i < 24) {
                        cttable += cttable = "\n"+ cTTimeTables.get(i).getTimeIn() + "\t" + cTTimeTables.get(i).getConsultantNameIn() + "\n";
                    }else if(i<48){
                        cttable += cttable = "\n" +
                                "\t\t" +cTTimeTables.get(i).getTaken()+"\n\t\t"+ cTTimeTables.get(i).getConsultantNameIn() + "\n";
                    }else if(i<72){
                        cttable += cttable = "\n" +
                                "\t\t\t" +cTTimeTables.get(i).getTaken()+"\n\t\t\t" + cTTimeTables.get(i).getConsultantNameIn() + "\n";
                    }else if(i<96){
                        cttable += cttable = "\n" +
                                "\t\t\t\t" +cTTimeTables.get(i).getTaken()+"\n\t\t\t\t"+  cTTimeTables.get(i).getConsultantNameIn() + "\n";
                    }else if(i<120){
                        cttable += cttable = "\n" +
                                "\t\t\t\t\t" + cTTimeTables.get(i).getTaken()+"\n\t\t\t\t\t"+ cTTimeTables.get(i).getConsultantNameIn() + "\n";
                    }else if(i<144){
                        cttable += cttable = "\n" +
                                "\t\t\t\t\t\t" + cTTimeTables.get(i).getTaken()+"\n\t\t\t\t\t\t"+ cTTimeTables.get(i).getConsultantNameIn() + "\n";
                    }else if(i<168){
                        cttable += cttable = "\n" +
                                "\t\t\t\t\t\t\t" +cTTimeTables.get(i).getTaken()+"\n\t\t\t\t\t\t\t"+  cTTimeTables.get(i).getConsultantNameIn() + "\n";
                    }
                }
                timeTableInformation.setText(cttable);
                cttable="";
            }
        }
    }
}
