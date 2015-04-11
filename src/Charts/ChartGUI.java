package Charts;

import GUI.AddPatientGUI;
import GUI.Clock;
import GUI.DeletePatient;
import Model.Appointment;
import Model.PatientRecord;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Roland on 10/04/2015.
 */
public class ChartGUI extends JFrame implements ActionListener
{
    JButton medicine,equipment,consultant,close;
    JLabel patientNum,label5;
    private int choice=0;
    private ArrayList<PatientRecord> pList=new ArrayList<>();
    private ArrayList<Appointment> appList=new ArrayList<>();
    private PatientRecord patientRecord;
    private Appointment apt;
    private String record="This is meant to be the patient Admin Record";


    JFrame f;

    public ChartGUI(int choice)
    {
        this.choice=choice;
        f = new JFrame();
        f.setTitle("Charts");
        f.setSize(1100, 900);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        Border RaisedBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED);

        JPanel holder=new JPanel(new GridLayout(1,1));
        JPanel topSection=new JPanel(new GridLayout(1,2));

        Clock.DigitalClock clockD=new Clock.DigitalClock();
        JPanel clock = new JPanel(new FlowLayout(FlowLayout.CENTER));
        clock.add(clockD);

        JPanel title=new JPanel(new FlowLayout(FlowLayout.LEFT));
        label5 = new JLabel("Charts");
        title.add(label5);
        label5.setFont(new Font("Arial", Font.BOLD, 46));

        JPanel ID=new JPanel(new FlowLayout(FlowLayout.CENTER));
        //labels
        topSection.add(clock);
        topSection.add(title);
        topSection.add(ID);
//        f.add(topSection);

        holder.add(topSection);
        f.add(holder);



        JPanel chart=new JPanel(new FlowLayout(FlowLayout.CENTER));
        chart.setPreferredSize(new Dimension(900,600));
        if(choice==1) {
            BarChartDemo barChartDemo = new BarChartDemo("Medicine", 1);
            JFreeChart chart1=barChartDemo.createChart(barChartDemo.createDataset());
            ChartPanel chartPanel = new ChartPanel(chart1, false);
            chart.setBorder(RaisedBorder);
            chart.add(chartPanel);
        }else if(choice==2) {
            BarChartDemo barChartDemo2 = new BarChartDemo("Equipment", 2);
            JFreeChart chart1=barChartDemo2.createChart2(barChartDemo2.createDataset2());
            ChartPanel chartPanel2 = new ChartPanel(chart1, false);
            chart.setBorder(RaisedBorder);
            chart.add(chartPanel2);
        }else if(choice==3) {
            BarChartDemo barChartDemo3 = new BarChartDemo("Consultant", 3);
            JFreeChart chart1=barChartDemo3.createChart3(barChartDemo3.createDataset3());
            ChartPanel chartPanel3 = new ChartPanel(chart1, false);
            chart.setBorder(RaisedBorder);
            chart.add(chartPanel3);
        }




        JPanel holder2=new JPanel(new GridLayout(1,1));
        JPanel test =new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel dobs = new JPanel(new GridBagLayout());

        // Update button
        medicine = new JButton("Medicine");
        medicine.addActionListener(this);
        dobs.add(medicine, getConstraints(0, 1, 1, 1, GridBagConstraints.WEST));

        // Delete button
        equipment = new JButton("Equipment");
        equipment.addActionListener(this);
        dobs.add(equipment, getConstraints(1, 1, 1, 1, GridBagConstraints.WEST));

        // Cancel button
        consultant = new JButton("Consultants");
        consultant.addActionListener(this);
        dobs.add(consultant, getConstraints(2, 1, 1, 1, GridBagConstraints.WEST));


        // Refresh button
        close = new JButton("Close");
        close.addActionListener(this);
        dobs.add(close, getConstraints(7, 1, 1, 1, GridBagConstraints.WEST));


        test.add(dobs);
        holder2.add(test);

        f.setLayout(new FlowLayout(FlowLayout.CENTER));

        f.add(chart);
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
        if (e.getSource().equals(close)) {
            f.setVisible(false);
        } else if (e.getSource().equals(medicine)){
            choice=1;
            ChartGUI chartGUI=new ChartGUI(1);
            f.setVisible(false);


        }else if (e.getSource().equals(equipment)) {
            choice=2;
            ChartGUI chartGUI=new ChartGUI(2);
            f.setVisible(false);

        }else if (e.getSource().equals(consultant)) {
            choice=3;
            ChartGUI chartGUI=new ChartGUI(3);
            f.setVisible(false);

        }

    }
}
