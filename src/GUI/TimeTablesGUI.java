package GUI;

import Model.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Thomas Murray on 20/03/2015.
 */
public class TimeTablesGUI extends JFrame implements ActionListener {
    JButton print, cancel;
    String[] list1;
    JLabel patientNum, label5;
    JTextArea timeTableInformation;
    JComboBox<String> combo1;
    JScrollPane scroll;
    private Equipment equipment;
    private ArrayList<Equipment> eqList=new ArrayList<>();
    JFrame f;
    private ArrayList<AllTimeTables> allTimeTables = new ArrayList<>();
    private ArrayList<ConsultantTimeTable> conTimeTables = new ArrayList<>();
    private String contable = "This table should be working con Time Tables ";
    private String tTable = "This table should be working Time Tables ";


    public TimeTablesGUI() {
        f = new JFrame();
        f.setTitle("Time Tables");
        f.setSize(805, 1010);
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
        label5 = new JLabel("Time Tables");
        title.add(label5);
        label5.setFont(new Font("Arial", Font.BOLD, 42));

        topSection.add(clock);
        topSection.add(title);
        holder.add(topSection);


        equipment=new Equipment();
        eqList.addAll(equipment.getEquipments());
        list1 = new String[eqList.size()+2];
        list1[0]="";
        list1[1]="Consultant";
        for (int i = 0; i < eqList.size(); i++) {
            list1[i+2] = eqList.get(i).getEqName();
        }


        JPanel textArea = new JPanel(new FlowLayout(FlowLayout.CENTER));
        timeTableInformation = new JTextArea(40, 70);
        timeTableInformation.setBorder(loweredBorder);
        timeTableInformation.setEditable(false);
        scroll = new JScrollPane(timeTableInformation);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textArea.add(scroll);


        JPanel holder2 = new JPanel(new GridLayout(3, 1));
        JPanel test = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel buttons = new JPanel(new GridBagLayout());
        JPanel selectionCombo = new JPanel(new FlowLayout(FlowLayout.RIGHT));

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

    public void clearArrays(){
        allTimeTables.removeAll(allTimeTables);
        conTimeTables.removeAll(conTimeTables);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cancel)) {
            f.setVisible(false);
        }


        else if (e.getSource().equals(print)) {
            File Files = new File("files");
            String tableSelection = (String) combo1.getSelectedItem();
            File textFile = new File(Files, "" + tableSelection + "_timeTables.txt");
            try (FileWriter input = new FileWriter(textFile)) {
                input.write(timeTableInformation.getText());
                JOptionPane.showMessageDialog(null, tableSelection + " Timetable is printing....");
                Printing printing=new Printing(tableSelection + "_timeTables");
            } catch (IOException io) {
                System.out.println(io);
            }
        }


        else if (e.getSource().equals(combo1)) {
            String tableSelection = (String) combo1.getSelectedItem();
            clearArrays();
            AllTimeTables allTimeTables1 = new AllTimeTables();
            allTimeTables.addAll(allTimeTables1.getList(tableSelection));

           ConsultantTimeTable consultantTimeTable=new ConsultantTimeTable();
            conTimeTables.addAll(consultantTimeTable.getConsultantTimeTable());

                if (tableSelection.equals("Consultant")) {
                    contable = "";
                    contable = "\n\n\tMon\tTues\tWed\tThur\tFri\tSat\tSun\n" +
                            "---------------------------------------------------------------------------------------------------" +
                            "----------------------------------------------------------------------------------------------------------------------------" +
                            "------------------------------------------------------";
                    for (int k = 0; k < conTimeTables.size(); k++) {
                        if (k < 6) {
                            contable += contable = "\n" + conTimeTables.get(k).getTimeIn() + "\t" + conTimeTables.get(k).getConsultantNameIn() + "\n";
                        } else if (k < 12) {
                            contable += contable = "\n" +
                                    "\t\t" + conTimeTables.get(k).getConsultantNameIn() + "\n";
                        } else if (k < 18) {
                            contable += contable = "\n" +
                                    "\t\t\t" + conTimeTables.get(k).getConsultantNameIn() + "\n";
                        } else if (k < 24) {
                            contable += contable = "\n" +
                                    "\t\t\t\t" + conTimeTables.get(k).getConsultantNameIn() + "\n";
                        } else if (k < 30) {
                            contable += contable = "\n" +
                                    "\t\t\t\t\t" + conTimeTables.get(k).getConsultantNameIn() + "\n";
                        } else if (k < 36) {
                            contable += contable = "\n" +
                                    "\t\t\t\t\t\t" + conTimeTables.get(k).getConsultantNameIn() + "\n";
                        } else if (k < 42) {
                            contable += contable = "\n" +
                                    "\t\t\t\t\t\t\t" + conTimeTables.get(k).getConsultantNameIn() + "\n";
                        }
                    }
                    timeTableInformation.setText(contable);
                    contable = "";

                }else{
                    int spinner=0,time=0;
                    tTable = "";
                    tTable = "\n\n\t\tMon\t\tTues\t\tWed\t\tThur\t\tFri\t\tSat\t\tSun\n" +
                            "---------------------------------------------------------------------------------------------------" +
                            "---------------------------------------------------------------------------------------------------" +
                            "----------------------------------------------------------------------------------------------------" +
                            "--------------------------------";
                    for (int i = 0; i < allTimeTables.size(); i++) {
                        if (spinner < 6) {
                            tTable += tTable = "\n\n" +
                                    " " + time + "\n";
                            tTable += tTable = " TO\t\t" + allTimeTables.get(i).getConsultantNameIn() + "\t\t" + allTimeTables.get(i+6).getConsultantNameIn()  + "\t\t"+ allTimeTables.get(i+12).getConsultantNameIn() + "\t\t" + allTimeTables.get(i+18).getConsultantNameIn() + "\t\t" + allTimeTables.get(i+24).getConsultantNameIn() + "\t\t" + allTimeTables.get(i+30).getConsultantNameIn() + "\t\t" + allTimeTables.get(i+36).getConsultantNameIn() + "\n";
                            tTable += tTable = " " + (time + 4) + "\t\t" + allTimeTables.get(i).getTaken() + "\t\t" + allTimeTables.get(i+6).getTaken()  + "\t\t"+ allTimeTables.get(i+12).getTaken() + "\t\t" + allTimeTables.get(i+18).getTaken() + "\t\t" + allTimeTables.get(i+24).getTaken() + "\t\t" + allTimeTables.get(i+30).getTaken() + "\t\t" + allTimeTables.get(i+36).getTaken() + "\t\t" + "\n\n\n";
                        } else if (spinner ==6) {
                            i = allTimeTables.size() + 1;
//                            tTable += tTable = "\n\n" +
//                                    " "+i+"\n";
//                            tTable += tTable = " TO\t" + allTimeTables.get(i).getConsultantNameIn()+"\t"+ allTimeTables.get(i).getConsultantNameIn() +allTimeTables.get(i).getConsultantNameIn()+"\t"+ allTimeTables.get(i).getConsultantNameIn()+"\t"+ allTimeTables.get(i).getConsultantNameIn()+"\t"+ allTimeTables.get(i).getConsultantNameIn()+"\t"+ allTimeTables.get(i).getConsultantNameIn()+"\n";
//                            tTable += tTable = " "+(i+4)+"\t" + allTimeTables.get(i).getTaken()+"\t"+ allTimeTables.get(i).getConsultantNameIn() +allTimeTables.get(i).getTaken()+"\t"+ allTimeTables.get(i).getTaken()+"\t"+ allTimeTables.get(i).getTaken()+"\t"+ allTimeTables.get(i).getTaken()+"\t"+ allTimeTables.get(i).getTaken()+"\t"+"\n\n\n";
//                        } else if (i < 18) {
//                            tTable += tTable = "\n\n" +
//                                    " "+i+"\n";
//                            tTable += tTable = " TO\t" + allTimeTables.get(i).getConsultantNameIn()+"\t"+ allTimeTables.get(i).getConsultantNameIn() +allTimeTables.get(i).getConsultantNameIn()+"\t"+ allTimeTables.get(i).getConsultantNameIn()+"\t"+ allTimeTables.get(i).getConsultantNameIn()+"\t"+ allTimeTables.get(i).getConsultantNameIn()+"\t"+ allTimeTables.get(i).getConsultantNameIn()+"\n";
//                            tTable += tTable = " "+(i+4)+"\t" + allTimeTables.get(i).getTaken()+"\t"+ allTimeTables.get(i).getConsultantNameIn() +allTimeTables.get(i).getTaken()+"\t"+ allTimeTables.get(i).getTaken()+"\t"+ allTimeTables.get(i).getTaken()+"\t"+ allTimeTables.get(i).getTaken()+"\t"+ allTimeTables.get(i).getTaken()+"\t"+"\n\n\n";
//                        } else if (i < 24) {
//                            tTable += tTable = "\n\n" +
//                                    " "+i+"\n";
//                            tTable += tTable = " TO\t" + allTimeTables.get(i).getConsultantNameIn()+"\t"+ allTimeTables.get(i).getConsultantNameIn() +allTimeTables.get(i).getConsultantNameIn()+"\t"+ allTimeTables.get(i).getConsultantNameIn()+"\t"+ allTimeTables.get(i).getConsultantNameIn()+"\t"+ allTimeTables.get(i).getConsultantNameIn()+"\t"+ allTimeTables.get(i).getConsultantNameIn()+"\n";
//                            tTable += tTable = " "+(i+4)+"\t" + allTimeTables.get(i).getTaken()+"\t"+ allTimeTables.get(i).getConsultantNameIn() +allTimeTables.get(i).getTaken()+"\t"+ allTimeTables.get(i).getTaken()+"\t"+ allTimeTables.get(i).getTaken()+"\t"+ allTimeTables.get(i).getTaken()+"\t"+ allTimeTables.get(i).getTaken()+"\t"+"\n\n\n";
//                        }
                        }
                            spinner++;
                        time +=4;
                        }
                    timeTableInformation.setText(tTable);
                    tTable = "";
                    }


                }

            }
        }


