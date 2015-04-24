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
 * Created by Thomas Murray on 10/04/2015.
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
    private static JPanel chart;
    private static Border RaisedBorder;
    private int replaceTest=0;

    ChartPanel chartPanel,chartPanel2,chartPanel3;

    JFrame f;

    public ChartGUI()
    {
        this.choice=choice;
        f = new JFrame();
        f.setTitle("Charts");
        f.setSize(1100, 900);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        RaisedBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED);

        JPanel holder=new JPanel(new GridLayout(1,1));
        JPanel topSection=new JPanel(new GridLayout(1,2));

        Clock.DigitalClock clockD=new Clock.DigitalClock();
        JPanel clock = new JPanel();
        clock.add(clockD);

        JPanel title=new JPanel(new FlowLayout(FlowLayout.LEFT));
        label5 = new JLabel("Charts");
        title.add(label5);
        label5.setFont(new Font("Arial", Font.BOLD, 52));

        JPanel ID=new JPanel(new FlowLayout(FlowLayout.CENTER));
        //labels
        topSection.add(clock);
        topSection.add(title);
        topSection.add(ID);

        holder.add(topSection);
        f.add(holder);

        chart=new JPanel(new FlowLayout(FlowLayout.CENTER));
        chart.setPreferredSize(new Dimension(900,600));

        JPanel holder2=new JPanel(new GridLayout(1,1));
        JPanel test =new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel dobs = new JPanel(new GridBagLayout());

        // Update button
        medicine = new JButton("Medicine");
        medicine.addActionListener(this);
        dobs.add(medicine, getConstraints(0, 4, 1, 1, GridBagConstraints.WEST));

        // Delete button
        equipment = new JButton("Equipment");
        equipment.addActionListener(this);
        dobs.add(equipment, getConstraints(1, 4, 1, 1, GridBagConstraints.WEST));

        // Cancel button
        consultant = new JButton("Consultants");
        consultant.addActionListener(this);
        dobs.add(consultant, getConstraints(2, 4, 1, 1, GridBagConstraints.WEST));


        // Refresh button
        close = new JButton("Close");
        close.addActionListener(this);
        dobs.add(close, getConstraints(7, 4, 1, 1, GridBagConstraints.WEST));


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
        try {
            if (e.getSource().equals(close)) {
                f.setVisible(false);
            } else if (e.getSource().equals(medicine)) {
                if(replaceTest==1){
                    chart.remove(chartPanel);
                }else{
                    BarChartDemo barChartDemo = new BarChartDemo("Medicine", 1);
                    JFreeChart chart1 = barChartDemo.createChart(barChartDemo.createDataset());
                    chartPanel = new ChartPanel(chart1, false);
                    chart.setBorder(RaisedBorder);
                    chart.add(chartPanel);
                    chart.remove(chartPanel2);
                    chart.remove(chartPanel3);
                    chart.revalidate();
                    chart.repaint();
                    replaceTest=1;

                }

            } else if (e.getSource().equals(equipment)) {
                if(replaceTest==2) {
                    chart.remove(chartPanel2);
                }else{
                    BarChartDemo barChartDemo2 = new BarChartDemo("Equipment", 2);
                    JFreeChart chart1 = barChartDemo2.createChart2(barChartDemo2.createDataset2());
                    chartPanel2 = new ChartPanel(chart1, false);
                    chart.setBorder(RaisedBorder);
                    chart.add(chartPanel2);
                    chart.remove(chartPanel);
                    chart.remove(chartPanel3);
                    chart.revalidate();
                    chart.repaint();
                    replaceTest=2;

                }

            } else if (e.getSource().equals(consultant)) {
                if(replaceTest==3) {
                    chart.remove(chartPanel3);
                }else{
                    BarChartDemo barChartDemo3 = new BarChartDemo("Consultant", 3);
                    JFreeChart chart1 = barChartDemo3.createChart3(barChartDemo3.createDataset3());
                    chartPanel3 = new ChartPanel(chart1, false);
                    chart.setBorder(RaisedBorder);
                    chart.remove(chartPanel2);
                    chart.remove(chartPanel);
                    chart.add(chartPanel3);
                    chart.revalidate();
                    chart.repaint();
                    replaceTest=3;

                }
            }

        } catch (NullPointerException np) {
            JOptionPane.showMessageDialog(null, "Generating Charts");
        }
    }
}
