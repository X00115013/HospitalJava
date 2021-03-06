package GUI;



import Charts.ChartGUI;
import Model.OpeningWordFile;

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
 * Created by Thomas Murray on 17/03/2015.
 *
 * Both home pages:
 * 1. Patient section
 * 2. Administration session
 */


public class HomeScreen extends JFrame implements ActionListener {


    private JButton appointments,checkIn,prescriptions,adminRecord,charts,medRecord,timeTables,equipment,medicine,payment,consultant,patientCharts,processR,oldBills,oldMed,accounts,manual;
    private JLabel pageTitle,pageTitle2, pageCenter,pageCenter2;
    JFrame f;
    private OpeningWordFile wf = new OpeningWordFile();
    private JTabbedPane tabbedPane;
    private String name="Hospital Name";
    boolean localTest=false;
    int tabbedCheck=1,firstVisit=0;

    public HomeScreen() {
        //This code implements the NIMBUS design throughout the whole system.
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

        //Clock
        Clock.DigitalClock clock1 = new Clock.DigitalClock();
        CalendarPane calendarPane=new CalendarPane();
        Clock.DigitalClock clock2 = new Clock.DigitalClock();

        //Calendar pane
        CalendarPane calendarPane2=new CalendarPane();
        JPanel top = new JPanel(new GridLayout(1, 3));
        top.setOpaque(true);
        top.add(clock1);

        //Title
        pageTitle = new JLabel();
        pageTitle.setFont(new Font("Arial", Font.ITALIC, 56));
        top.add(pageTitle);
        top.add(calendarPane);
        top.setBorder(new EmptyBorder(20, 20, 20, 20));
        firstPage.add(top);

        //Asks for a hospital name the first time a tabbed pane is selected (Start up)
        if(firstVisit==0) {
            name = JOptionPane.showInputDialog("Enter Hospital Name");
            pageTitle.setText(name);
            firstVisit++;
        }

        //Home screen central image
        try {
            pageCenter = new JLabel(new ImageIcon(ImageIO.read(new File("files/img.jpg"))));
        } catch (IOException e) {
            System.out.println("image doesn't exist");
        }

        pageCenter.setSize(2000, 1200);
        firstPage.add(pageCenter);
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
        tabbedPane.add("Patient Section",firstPage);

        JPanel secondPage=new JPanel();
        secondPage.setLayout(new GridLayout(3, 2));
        secondPage.setBackground(Color.LIGHT_GRAY);
        secondPage.setSize(1200, 800);

        JPanel top2 = new JPanel(new GridLayout(1, 3));
        top2.setOpaque(true);
        top2.add(clock2);
        pageTitle2 = new JLabel(name);
        pageTitle2.setFont(new Font("Arial", Font.ITALIC , 56));
        top2.add(pageTitle2);
        top2.add(calendarPane2);
        top2.setBorder(new EmptyBorder(20, 20, 20, 20));
        secondPage.add(top2);

        try {
            pageCenter2 = new JLabel(new ImageIcon(ImageIO.read(new File("files/img.jpg"))));
        } catch (IOException e) {
            System.out.println("image doesn't exist");
        }
        secondPage.add(pageCenter2);

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

        manual = new JButton("Manual");
        manual.addActionListener(new ActionListener() {      // anonymous inner class
            public void actionPerformed(ActionEvent evt) {
                Desktop desktop = Desktop.getDesktop();

                try {

                    File f = new File(wf.getDIR());

                    desktop.open(f);  // opens application (MSWord) associated with .doc file
                } catch (Exception ex) {
                    // HomeScreen.this is to refer to outer class's instance from inner class
                    JOptionPane.showMessageDialog(HomeScreen.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

    });



        manual.setPreferredSize(new Dimension(200, 50));
        buttons2.add(manual, getConstraints(3, 1, 1, 1, GridBagConstraints.SOUTH));

        secondPage.add(buttons2, getConstraints(1, 1, 0, 1, GridBagConstraints.SOUTH));
        tabbedPane.add("Administration Section",secondPage);
        f.add(tabbedPane);

        //Tabbed pane security (High level Admin only)
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    if (tabbedPane.isEnabledAt(0)) {
                        localTest = true;
                        tabbedCheck++;
                    } else if (tabbedPane.isEnabledAt(1)) {
                        localTest = false;
                    }
                    if ((localTest == true) && (tabbedCheck % 2==0)) {
                        String input = JOptionPane.showInputDialog("Input Admin Password");
                        try {
                            if (Integer.parseInt(input) == 789) {
                                JOptionPane.showMessageDialog(null, "Password is Correct");
                                tabbedPane.setSelectedIndex(1);
                                localTest = false;
                            } else {
                                JOptionPane.showMessageDialog(null, "Password is Incorrect");
                                tabbedPane.setSelectedIndex(0);
                                localTest = false;
                            }
                        }catch(NumberFormatException nf){
                            tabbedPane.setSelectedIndex(0);
                            localTest = false;
                        }
                    }
                }catch (NullPointerException np){
                    tabbedPane.setSelectedIndex(0);
                    localTest = false;
                }
            }
        });
        f.setVisible(true);
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


//Spare parts

//                tabbedPane.setComponentAt("Patient Section",secondPage);
//                tabbedPane.setIgnoreRepaint(true);
//                tabbedPane.setVerifyInputWhenFocusTarget(true);
//                tabbedPane.setRequestFocusEnabled(false);
//                tabbedPane.getRootPane();
//                tabbedPane.setTabPlacement(1);