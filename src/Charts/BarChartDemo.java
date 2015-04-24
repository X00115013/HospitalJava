package Charts;

import java.awt.Color;
import java.awt.Dimension;
import java.lang.reflect.Array;
import java.util.ArrayList;

import Model.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;

/**
 * Created by Thomas Murray on 10/04/2015.
 */

public class BarChartDemo extends JPanel {

    private static final long serialVersionUID = 1L;
    private  Prescription prescription;
    private  ArrayList<Prescription>presList=new ArrayList<>();
    private  EquipmentUsed equipmentUsed;
    private  ArrayList<EquipmentUsed>eqList=new ArrayList<>();
    private Equipment equipment;
    private  ArrayList<Equipment>eList=new ArrayList<>();
    private int choice;
    private Consultants consultants;
    private ArrayList<Consultants>conList=new ArrayList<>();
    private AllTimeTables allTimeTables;
    private ArrayList<AllTimeTables>aTTList=new ArrayList<>();
    private Appointment appointment;
    private ArrayList<Appointment>appList=new ArrayList<>();


    static {
        ChartFactory.setChartTheme(new StandardChartTheme("JFree/Shadow",true));
    }


    public BarChartDemo(String title,int choice) {
        this.choice=choice;
        if (choice == 1) {
            CategoryDataset dataset = createDataset();
            JFreeChart chart = createChart(dataset);
            ChartPanel chartPanel = new ChartPanel(chart, false);
            chartPanel.setBackground(null);
            chartPanel.setFillZoomRectangle(true);
            chartPanel.setMouseWheelEnabled(true);
            chartPanel.setDismissDelay(Integer.MAX_VALUE);
            chartPanel.setPreferredSize(new Dimension(1000, 800));
//            setContentPane(chartPanel);
        } else if (choice == 2) {
            CategoryDataset dataset2 = createDataset2();
            JFreeChart chart2 = createChart2(dataset2);
            ChartPanel chartPanel2 = new ChartPanel(chart2, false);
            chartPanel2.setBackground(null);
            chartPanel2.setFillZoomRectangle(true);
            chartPanel2.setMouseWheelEnabled(true);
            chartPanel2.setDismissDelay(Integer.MAX_VALUE);
            chartPanel2.setPreferredSize(new Dimension(1000, 800));
//            setContentPane(chartPanel2);
        } else if (choice == 3) {
            CategoryDataset dataset3 = createDataset3();
            JFreeChart chart3 = createChart3(dataset3);
            ChartPanel chartPanel3 = new ChartPanel(chart3, false);
            chartPanel3.setBackground(null);
            chartPanel3.setFillZoomRectangle(true);
            chartPanel3.setMouseWheelEnabled(true);
            chartPanel3.setDismissDelay(Integer.MAX_VALUE);
            chartPanel3.setPreferredSize(new Dimension(1000, 800));
//            setContentPane(chartPanel3);
        }
    }

    public CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        JFreeChart chart = createChart(dataset);
        prescription=new Prescription();
        presList.removeAll(presList);
        presList.addAll(prescription.getPresList());
        for (int i = 0; i <presList.size() ; i++) {
            dataset.addValue(presList.get(i).getDose(), presList.get(i).getMedName(),presList.get(i).getConName());
        }
        return dataset;
    }

    public CategoryDataset createDataset2() {
        int use=0;
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        JFreeChart chart2 = createChart2(dataset2);
        equipmentUsed=new EquipmentUsed();
        eqList.removeAll(eqList);
        eqList.addAll(equipmentUsed.getUsedListC());
        equipment=new Equipment();
        eList.removeAll(eList);
        eList.addAll(equipment.getEquipments());
        appointment=new Appointment();
        appList.removeAll(appList);
        appList.addAll(appointment.appArray());
        for (int i = 0; i < eList.size() ; i++) {
            for (int k = 0; k < appList.size(); k++) {
                if (appList.get(k).getMedicalEquip().equals(eList.get(i).getEqName())) {
                    use++;
                }
            }
            for (int j = 0; j < eqList.size(); j++) {
                if (eqList.get(j).getEqName().equals(eList.get(i).getEqName())) {
                    use++;
                }
            }
            dataset2.addValue(use, eList.get(i).getEqName(), "");
            use = 0;
        }
        return dataset2;
    }


    public CategoryDataset createDataset3() {
        int use=0;
        DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
        JFreeChart chart2 = createChart3(dataset3);
        consultants=new Consultants();
        conList.removeAll(conList);
        conList.addAll(consultants.getConsultants());
        aTTList.removeAll(aTTList);
        allTimeTables=new AllTimeTables();
        aTTList.addAll(allTimeTables.getListC());
        for (int i = 0; i < conList.size() ; i++) {
            for (int j = 0; j < aTTList.size(); j++) {
                if(conList.get(i).getConName().equals(aTTList.get(j).getConsultantNameIn())||conList.get(i).getConSpeciality().equals(aTTList.get(j).getConsultantNameIn())){
                    use++;
                }
            }
            dataset3.addValue(use, conList.get(i).getConName(),"");
            use=0;
        }
        return dataset3;
    }

    public JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart("Prescriptions Used", null,"Milligrams", dataset);
        chart.addSubtitle(new TextTitle("Amount prescribed and the prescribers"));
        chart.setBackgroundPaint(null);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(null);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        chart.getLegend().setFrame(BlockBorder.NONE);
        return chart;
    }


    public JFreeChart createChart2(CategoryDataset dataset2) {
        JFreeChart chart = ChartFactory.createBarChart("Equipment Used", null,"Sessions", dataset2);
        chart.addSubtitle(new TextTitle("Amount Equipment and the Users Patient Number"));
        chart.setBackgroundPaint(null);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(null);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        chart.getLegend().setFrame(BlockBorder.NONE);
        return chart;
    }

    public JFreeChart createChart3(CategoryDataset dataset3) {
        JFreeChart chart = ChartFactory.createBarChart("Consultants Used", null,"Bookings", dataset3);
        chart.addSubtitle(new TextTitle("Consultants most regularly booked"));
        chart.setBackgroundPaint(null);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(null);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        chart.getLegend().setFrame(BlockBorder.NONE);
        return chart;
    }
}
