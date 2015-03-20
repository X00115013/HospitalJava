package GUI;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.imageio.*;


/**
 * Created by Lionhart on 17/03/2015.
 */
public class HomeScreen extends JFrame implements ActionListener {
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton calendar;

    private JLabel label1,calender;
    private JLabel label2;
    JFrame f;

    public HomeScreen() {
        f = new JFrame();
        f.setTitle("Appointment");
        f.setLayout(new GridLayout(3, 2));

        f.setSize(1200, 800);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Clock.DigitalClock clock1=new Clock.DigitalClock();



        JPanel top = new JPanel(new GridLayout(1,3));
        top.setOpaque(true);
        top.add(clock1);
        label1 = new JLabel("Hospital Name");
        label1.setFont(new Font("Arial", Font.BOLD, 50));
        top.add(label1);
        calender =new JLabel("Calender goes here");
        top.add(calender);
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
        label2.setSize(1200,800);
        f.getContentPane().add(label2);

        //buttons
        JPanel buttons = new JPanel(new GridBagLayout());

        button1 = new JButton("Appointments");
        button1.addActionListener(this);
        button1.setPreferredSize(new Dimension(200, 50));
        buttons.add(button1, getConstraints(0, 0, 1, 1, GridBagConstraints.SOUTH));
        button2 = new JButton("Check-In");
        button2.addActionListener(this);
        button2.setPreferredSize(new Dimension(200, 50));
        buttons.add(button2, getConstraints(1, 0, 1, 1, GridBagConstraints.SOUTH));
        button3 = new JButton("Admin Records");
        button3.addActionListener(this);
        button3.setPreferredSize(new Dimension(200, 50));
        buttons.add(button3, getConstraints(2, 0, 1, 1, GridBagConstraints.SOUTH));
        button4 = new JButton("Medical Records");
        button4.addActionListener(this);
        button4.setPreferredSize(new Dimension(200, 50));
        buttons.add(button4, getConstraints(0, 1, 1, 1, GridBagConstraints.SOUTH));
        button5 = new JButton("Timetables");
        button5.addActionListener(this);
        button5.setPreferredSize(new Dimension(200, 50));
        buttons.add(button5, getConstraints(1, 1, 1, 1, GridBagConstraints.SOUTH));
        button6 = new JButton("Process Referrals");
        button6.addActionListener(this);
        button6.setPreferredSize(new Dimension(200, 50));
        buttons.add(button6, getConstraints(2, 1, 1, 1, GridBagConstraints.SOUTH));
        button7 = new JButton("Patient Charts");
        button7.addActionListener(this);
        button7.setPreferredSize(new Dimension(200, 50));
        buttons.add(button7, getConstraints(0, 2, 1, 1, GridBagConstraints.SOUTH));
        button8 = new JButton("Payment");
        button8.addActionListener(this);
        button8.setPreferredSize(new Dimension(200, 50));
        buttons.add(button8, getConstraints(1, 2, 1, 1, GridBagConstraints.SOUTH));
        button9 = new JButton("Prescriptions");
        button9.addActionListener(this);
        button9.setPreferredSize(new Dimension(200, 50));
        buttons.add(button9, getConstraints(2, 2, 1, 1, GridBagConstraints.SOUTH));

        f.add(buttons, getConstraints(1, 1, 0, 1, GridBagConstraints.SOUTH));
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
        if (e.getSource().equals(button1)) {
            SecurityGUI securityGUI=new SecurityGUI(1);

        } else if (e.getSource().equals(button2)) {
            SecurityGUI securityGUI=new SecurityGUI(2);

        } else if (e.getSource().equals(button3)) {
            SecurityGUI securityGUI=new SecurityGUI(3);

        } else if (e.getSource().equals(button4)) {
            SecurityGUI securityGUI=new SecurityGUI(4);

        } else if (e.getSource().equals(button5)) {
            SecurityGUI securityGUI=new SecurityGUI(5);

        } else if (e.getSource().equals(button6)) {
            SecurityGUI securityGUI=new SecurityGUI(6);

        } else if (e.getSource().equals(button7)) {
            SecurityGUI securityGUI=new SecurityGUI(7);

        } else if (e.getSource().equals(button8)) {
            SecurityGUI securityGUI=new SecurityGUI(8);

        } else if (e.getSource().equals(button9)) {
            SecurityGUI securityGUI=new SecurityGUI(9);

        }else if (e.getSource().equals(calendar)) {


            }

        }
    }
