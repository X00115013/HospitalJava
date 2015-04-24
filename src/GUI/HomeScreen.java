package GUI;



import Charts.ChartGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.imageio.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;


/**
 * Created by David Kiernan and Thomas Murray on 17/03/2015.
 */
public class HomeScreen extends JFrame implements ActionListener {


    private JButton appointments,checkIn,prescriptions,adminRecord,charts,medRecord,timeTables,equipment,medicine,payment,consultant,patientCharts,processR,oldBills,oldMed,accounts;
    private JButton calendar;
    private JLabel pageTitle,pageTitle2;
    private JLabel pageCenter,pageCenter2;
    JFrame f, g;
    private JTabbedPane tabbedPane;


    public HomeScreen() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        }

        f = new JFrame();
        f.setSize(1200, 800);
        f.setResizable(true);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        tabbedPane = new JTabbedPane();
        JPanel firstPage=new JPanel();
        firstPage.setLayout(new GridLayout(3, 1));
        firstPage.setSize(1200,800);
        firstPage.setBackground(Color.LIGHT_GRAY);
        Clock.DigitalClock clock1 = new Clock.DigitalClock();
        CalendarPane calendarPane=new CalendarPane();
        Clock.DigitalClock clock2 = new Clock.DigitalClock();
        CalendarPane calendarPane2=new CalendarPane();

        JPanel top = new JPanel(new GridLayout(1, 3));
        top.setOpaque(true);
        top.add(clock1);
        pageTitle = new JLabel("Hospital Name");
        pageTitle.setFont(new Font("Arial", Font.ITALIC, 50));
        top.add(pageTitle);
        top.add(calendarPane);
        top.setBorder(new EmptyBorder(20, 20, 20, 20));
        firstPage.add(top);


        try {
            pageCenter = new JLabel(new ImageIcon(ImageIO.read(new File("files/img.jpg"))));
        } catch (IOException e) {
            System.out.println("image doesn't exist");
        }

        pageCenter.setSize(2000, 1200);
        firstPage.add(pageCenter);

        //buttons
        JPanel buttons = new JPanel(new GridBagLayout());

        appointments = new JButton("Appointments");
        appointments.addActionListener(this);
        appointments.setPreferredSize(new Dimension(200, 50));
        buttons.add(appointments, getConstraints(0, 0, 1, 1, GridBagConstraints.SOUTH));
        checkIn= new JButton("Check-In");
        checkIn.addActionListener(this);
        checkIn.setPreferredSize(new Dimension(200, 50));
        buttons.add(checkIn, getConstraints(1, 0, 1, 1, GridBagConstraints.SOUTH));
        adminRecord = new JButton("Admin Records");
        adminRecord.addActionListener(this);
        adminRecord.setPreferredSize(new Dimension(200, 50));
        buttons.add(adminRecord, getConstraints(2, 0, 1, 1, GridBagConstraints.SOUTH));
        medRecord = new JButton("Medical Records");
        medRecord.addActionListener(this);
        medRecord.setPreferredSize(new Dimension(200, 50));
        buttons.add(medRecord, getConstraints(0, 1, 1, 1, GridBagConstraints.SOUTH));
        timeTables = new JButton("Timetables");
        timeTables.addActionListener(this);
        timeTables.setPreferredSize(new Dimension(200, 50));
        buttons.add(timeTables, getConstraints(1, 1, 1, 1, GridBagConstraints.SOUTH));
        processR = new JButton("Process Referrals");
        processR.addActionListener(this);
        processR.setPreferredSize(new Dimension(200, 50));
        buttons.add(processR, getConstraints(2, 1, 1, 1, GridBagConstraints.SOUTH));
        patientCharts = new JButton("Patient Charts");
        patientCharts.addActionListener(this);
        patientCharts.setPreferredSize(new Dimension(200, 50));
        buttons.add(patientCharts, getConstraints(0, 2, 1, 1, GridBagConstraints.SOUTH));
        payment = new JButton("Payment");
        payment.addActionListener(this);
        payment.setPreferredSize(new Dimension(200, 50));
        buttons.add(payment, getConstraints(1, 2, 1, 1, GridBagConstraints.SOUTH));
        prescriptions = new JButton("Prescriptions");
        prescriptions.addActionListener(this);
        prescriptions.setPreferredSize(new Dimension(200, 50));
        buttons.add(prescriptions, getConstraints(2, 2, 1, 1, GridBagConstraints.SOUTH));

        firstPage.add(buttons, getConstraints(1, 1, 0, 1, GridBagConstraints.SOUTH));

//        f.setVisible(true);

        tabbedPane.add("Patient Section",firstPage);

        JPanel secondPage=new JPanel();
        secondPage.setLayout(new GridLayout(3, 2));
        secondPage.setBackground(Color.LIGHT_GRAY);
        secondPage.setSize(1200, 800);

        JPanel top2 = new JPanel(new GridLayout(1, 3));
        top2.setOpaque(true);
        top2.add(clock2);
        pageTitle2 = new JLabel("Hospital Name");
        pageTitle2.setFont(new Font("Arial", Font.ITALIC , 50));
        top2.add(pageTitle2);
        top2.add(calendarPane2);
        top2.setBorder(new EmptyBorder(20, 20, 20, 20));
//        calendar=new JButton("Press for Calendar!!!");
//        top.add(calendar);
        secondPage.add(top2);


        //bg image


        try {
            pageCenter2 = new JLabel(new ImageIcon(ImageIO.read(new File("files/img.jpg"))));
        } catch (IOException e) {
            System.out.println("image doesn't exist");
        }
        secondPage.add(pageCenter2);

        //buttons
        JPanel buttons2 = new JPanel(new GridBagLayout());

        equipment = new JButton("Equipment");
        equipment.addActionListener(this);
        equipment.setPreferredSize(new Dimension(200, 50));
        buttons2.add(equipment, getConstraints(0, 0, 1, 1, GridBagConstraints.SOUTH));
        medicine = new JButton("Medicine");
        medicine.addActionListener(this);
        medicine.setPreferredSize(new Dimension(200, 50));
        buttons2.add(medicine, getConstraints(1, 0, 1, 1, GridBagConstraints.SOUTH));
        consultant = new JButton("Consultants");
        consultant.addActionListener(this);
        consultant.setPreferredSize(new Dimension(200, 50));
        buttons2.add(consultant, getConstraints(2, 0, 1, 1, GridBagConstraints.SOUTH));
        charts = new JButton("Charts");
        charts.addActionListener(this);
        charts.setPreferredSize(new Dimension(200, 50));
        buttons2.add(charts, getConstraints(3, 0, 1, 1, GridBagConstraints.SOUTH));
        oldBills = new JButton("Old Bills");
        oldBills.addActionListener(this);
        oldBills.setPreferredSize(new Dimension(200, 50));
        buttons2.add(oldBills, getConstraints(0, 1, 1, 1, GridBagConstraints.SOUTH));
        oldMed = new JButton("Old Med Records");
        oldMed.addActionListener(this);
        oldMed.setPreferredSize(new Dimension(200, 50));
        buttons2.add(oldMed, getConstraints(1, 1, 1, 1, GridBagConstraints.SOUTH));
        accounts = new JButton("Hospital Accounts");
        accounts.addActionListener(this);
        accounts.setPreferredSize(new Dimension(200, 50));
        buttons2.add(accounts, getConstraints(2, 1, 1, 1, GridBagConstraints.SOUTH));


        secondPage.add(buttons2, getConstraints(1, 1, 0, 1, GridBagConstraints.SOUTH));
        tabbedPane.add("Administration Section",secondPage);
        f.add(tabbedPane);

        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                TabSecurity tabSecurity=new TabSecurity();
                System.out.println("In Homepage "+tabSecurity.isTest());
                if(tabSecurity.isTest()==false) {
                    tabbedPane.setSelectedIndex(1);
                    System.out.println("In Homepage index 0 "+tabSecurity.isTest());
                }else{
                    tabbedPane.setSelectedIndex(0);
                    System.out.println("In Homepage index 1 "+tabSecurity.isTest());
                }
            }
        });

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
        if (e.getSource().equals(appointments)) {
            CancelAppointmentSecurity securityGUI = new CancelAppointmentSecurity(1);

        } else if (e.getSource().equals(checkIn)) {
            SingleSecurityGUI securityGUI = new SingleSecurityGUI(2);

        } else if (e.getSource().equals(adminRecord)) {
            AddPatientSecurityGUI addSecurityGUI = new AddPatientSecurityGUI(3);

        } else if (e.getSource().equals(medRecord)) {
            SecurityGUI securityGUI = new SecurityGUI(4);

        } else if (e.getSource().equals(timeTables)) {
            SingleSecurityGUI securityGUI = new SingleSecurityGUI(5);

        } else if (e.getSource().equals(processR)) {
            SingleSecurityGUI securityGUI = new SingleSecurityGUI(6);

        } else if (e.getSource().equals(patientCharts)) {
            SecurityGUI securityGUI = new SecurityGUI(7);

        } else if (e.getSource().equals(payment)) {
            SecurityGUI securityGUI = new SecurityGUI(8);

        } else if (e.getSource().equals(prescriptions)) {
            SecurityGUI securityGUI = new SecurityGUI(9);

        } else if  (e.getSource().equals(equipment)) {
            AddEquipmentGUI equipmentGUI=new AddEquipmentGUI();

        } else if (e.getSource().equals(medicine)) {
            AddMedicineGUI addMedicineGUI=new AddMedicineGUI();


        } else if (e.getSource().equals(consultant)) {
            AddConsultantGUI addConsultantGUI=new AddConsultantGUI();

        } else if (e.getSource().equals(charts)) {
            ChartGUI chartGUI=new ChartGUI();

        }
        else if (e.getSource().equals(oldBills)) {
           OldBills oldBills=new OldBills();

        }else if (e.getSource().equals(oldMed)) {
           OldMedRecords oldMedRecords=new OldMedRecords();

        }else if (e.getSource().equals(accounts)) {
           AccountsGUI accountsGUI=new AccountsGUI();

        }

    }
}

//                tabbedPane.setComponentAt("Patient Section",secondPage);
//                tabbedPane.setIgnoreRepaint(true);
//                tabbedPane.setVerifyInputWhenFocusTarget(true);
//                tabbedPane.setRequestFocusEnabled(false);
//                tabbedPane.getRootPane();
//                tabbedPane.setTabPlacement(1);