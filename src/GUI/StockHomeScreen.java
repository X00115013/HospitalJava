package GUI;

import Charts.ChartGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Thomas Murray on 06/04/2015.
 */

    public class StockHomeScreen extends JFrame implements ActionListener {
    private JButton button1;
    private JButton button2;
    private JButton button3,charts;


    private JLabel label1, calender;
    private JLabel label2;
    JFrame f, g;
    private JTabbedPane tabbedPane = new JTabbedPane(2);

    public StockHomeScreen() {
        f = new JFrame();
        f.setTitle("Appointment");
        f.setLayout(new GridLayout(3, 2));
        f.getContentPane().setBackground(Color.LIGHT_GRAY);

        f.setSize(1200, 800);
        f.setResizable(true);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Clock.DigitalClock clock1 = new Clock.DigitalClock();
        CalendarPane calendarPane=new CalendarPane();

        JPanel top = new JPanel(new GridLayout(1, 3));
        top.setOpaque(true);
        top.add(clock1);
        label1 = new JLabel("Hospital Name");
        label1.setFont(new Font("Arial", Font.BOLD, 50));
        top.add(label1);
        calender = new JLabel("Calender goes here");
        top.add(calendarPane);
        top.setBorder(new EmptyBorder(20, 20, 20, 20));
//        calendar=new JButton("Press for Calendar!!!");
//        top.add(calendar);
        f.add(top);

        //bg image


        try {
            label2 = new JLabel(new ImageIcon(ImageIO.read(new File("files/img.jpg"))));
        } catch (IOException e) {
            System.out.println("image doesn't exist");
        }
        label2.isOpaque();
        label2.setSize(1200, 800);
        f.getContentPane().add(label2);

        //buttons
        JPanel buttons = new JPanel(new GridBagLayout());

        button1 = new JButton("Equipment");
        button1.addActionListener(this);
        button1.setPreferredSize(new Dimension(200, 50));
        buttons.add(button1, getConstraints(0, 0, 1, 1, GridBagConstraints.SOUTH));
        button2 = new JButton("Medicine");
        button2.addActionListener(this);
        button2.setPreferredSize(new Dimension(200, 50));
        buttons.add(button2, getConstraints(1, 0, 1, 1, GridBagConstraints.SOUTH));
        button3 = new JButton("Consultants");
        button3.addActionListener(this);
        button3.setPreferredSize(new Dimension(200, 50));
        buttons.add(button3, getConstraints(2, 0, 1, 1, GridBagConstraints.SOUTH));
        charts = new JButton("Charts");
        charts.addActionListener(this);
        charts.setPreferredSize(new Dimension(200, 50));
        buttons.add(charts, getConstraints(3, 0, 1, 1, GridBagConstraints.SOUTH));

        f.add(buttons, getConstraints(1, 1, 0, 1, GridBagConstraints.SOUTH));
        f.setVisible(true);

//        tabbedPane.add(f);

    }

    private GridBagConstraints getConstraints(int gridx, int gridy, int gridwidth, int gridheight, int anchor) {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
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
        if (e.getSource().equals(button1)) {
            AddEquipmentGUI equipmentGUI=new AddEquipmentGUI();

        } else if (e.getSource().equals(button2)) {
            AddMedicineGUI addMedicineGUI=new AddMedicineGUI();


        } else if (e.getSource().equals(button3)) {
            AddConsultantGUI addConsultantGUI=new AddConsultantGUI();

        } else if (e.getSource().equals(charts)) {
            ChartGUI chartGUI=new ChartGUI();

        }

    }
}
