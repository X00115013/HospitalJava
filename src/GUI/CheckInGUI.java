package GUI;

import Model.Appointment;
import Model.CheckIn;
import Model.PatientRecord;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Dylan and Thomas Murray on 20/03/2015.
 */
public class CheckInGUI extends JFrame implements ActionListener
{
    JButton confirm,checkOut,cancel;
    JLabel appNum,firstName,surname,label,or,dob, day,month,year;
    JTextField appNumText,firstNameText, surnameText;
    JComboBox<String> dayCombo,monthCombo,yearCombo;
    JFrame f;
    private int setter=1940;
    private String[] daysIn={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    private String[] months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    private String[] yearsList=new String[76];
    private ArrayList<Appointment>appList=new ArrayList<>();
    private ArrayList<PatientRecord>pList=new ArrayList<>();


    public CheckInGUI()
    {
        for (int i = 0; i < 76; i++) {
            yearsList[i]= Integer.toString(setter);
            setter++;
        }
        f = new JFrame();
        f.setTitle("Check-In");
        f.setSize(660, 700);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        Border loweredBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);

        JPanel mainHolder=new JPanel(new FlowLayout(FlowLayout.LEFT));
        Clock.DigitalClock clockD=new Clock.DigitalClock();
        JPanel clock = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        clock.add(clockD);
        label = new JLabel("Check-In");
        clock.add(label);
        label.setFont(new Font("Arial", Font.BOLD, 46));
//        holder.add(clock);
        JPanel holder=new JPanel(new GridLayout(2,1));
        JPanel test2=new JPanel(new FlowLayout(FlowLayout.LEFT));

        //middle
        JPanel middle = new JPanel(new GridBagLayout());
        test2.add(middle, BorderLayout.WEST);
        //labels
        appNum = new JLabel("Appointment Number");
        middle.add(appNum, getConstraints(0, 0, 1, 1, (GridBagConstraints.WEST)));
        //text field
        appNumText = new JTextField(50);
        appNumText.setBorder(loweredBorder);
        or = new JLabel("OR");
        middle.add(or, getConstraints(0, 2, 1, 1, (GridBagConstraints.WEST)));
        middle.add(appNumText, getConstraints(0, 1, 1, 1, (GridBagConstraints.WEST)));
        firstName = new JLabel("First Name");
        middle.add(firstName, getConstraints(0, 3, 1, 1, (GridBagConstraints.WEST)));
        //text field
        firstNameText = new JTextField(50);
        firstNameText.setBorder(loweredBorder);
        middle.add(firstNameText, getConstraints(0, 4, 1, 1, (GridBagConstraints.WEST)));
        surname = new JLabel("Surname");
        middle.add(surname, getConstraints(0, 5, 1, 1, (GridBagConstraints.WEST)));
        //text field
        surnameText = new JTextField(50);
        surnameText.setBorder(loweredBorder);
        middle.add(surnameText, getConstraints(0, 6, 1, 1, (GridBagConstraints.WEST)));
        holder.add(test2);

        //DOB labels
        JPanel test =new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel dobs = new JPanel(new GridBagLayout());
        test.add(dobs, BorderLayout.SOUTH);
        dob = new JLabel("DOB");
        dobs.add(dob, getConstraints(0, 0, 1, 1, GridBagConstraints.WEST));
        day = new JLabel("Day");
        dobs.add(day, getConstraints(0, 2, 1, 1, GridBagConstraints.WEST));
        month = new JLabel("Month");
        dobs.add(month, getConstraints(1, 2, 1, 1, GridBagConstraints.WEST));
        year = new JLabel("Year");
        dobs.add(year, getConstraints(2, 2, 1, 1, GridBagConstraints.WEST));

        //DOB text fields
        dayCombo = new JComboBox<>(daysIn);
        dayCombo.setBorder(loweredBorder);
        dobs.add(dayCombo, getConstraints(0, 3, 1, 1, GridBagConstraints.WEST));
        monthCombo = new JComboBox<>(months);
        monthCombo.setBorder(loweredBorder);
        dobs.add(monthCombo, getConstraints(1, 3, 1, 1, GridBagConstraints.WEST));
        yearCombo = new JComboBox<>(yearsList);
        yearCombo.setBorder(loweredBorder);
        dobs.add(yearCombo, getConstraints(2, 3, 1, 1, GridBagConstraints.WEST));
        holder.add(test);

        // Confirm button
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        dobs.add(confirm, getConstraints(0, 6, 1, 1, GridBagConstraints.WEST));

        // Cancel button
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        dobs.add(cancel, getConstraints(1, 6, 1, 1, GridBagConstraints.WEST));

        // CheckOut button
        checkOut = new JButton("Check-Out");
        checkOut.addActionListener(this);
        dobs.add(checkOut, getConstraints(2, 6, 1, 1, GridBagConstraints.WEST));
        mainHolder.add(clock);
        mainHolder.add(holder);
        f.add(mainHolder);
        f.setVisible(true);
    }




    private GridBagConstraints getConstraints(int gridx, int gridy, int gridwidth, int gridheight, int anchor) {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 5, 10, 10);
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
        c.anchor = anchor;
        return c;
    }


    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(cancel))
        {
           f.setVisible(true);
        }
        else if (e.getSource().equals(confirm))
        { appList.removeAll(appList);
            pList.removeAll(pList);
            Appointment appointment = new Appointment();
            PatientRecord patientRecord = new PatientRecord();
            appList.addAll(appointment.appArray());
            pList.addAll(patientRecord.getPatientList());
            boolean test = false, test2 = false;
            String dayC = (String) dayCombo.getSelectedItem();
            String monthC = (String) monthCombo.getSelectedItem();
            String yearC = (String) yearCombo.getSelectedItem();
            int choice = 0;

            if ((firstNameText.getText().equals("")) && (surnameText.getText().equals("")) && (appNumText.getText().equals(""))) {
                JOptionPane.showMessageDialog(null, "Appointment Number or Patient Info are Required Fields");
            } else {
                if(appNumText.getText().equals("")) {
                    for (int i = 0; i < pList.size(); i++) {
                        if (firstNameText.getText().equals(pList.get(i).getPatientFName()) && surnameText.getText().equals(pList.get(i).getPatientLName()) &&
                                dayC.equals(pList.get(i).getDOB().charAt(0) + "" + pList.get(i).getDOB().charAt(1)) && monthC.equals(pList.get(i).getDOB().charAt(3) + "" +
                                "" + pList.get(i).getDOB().charAt(4) + "" + pList.get(i).getDOB().charAt(5)) && yearC.equals(pList.get(i).getDOB().charAt(7) + "" + pList.get(i).getDOB().charAt(8) + "" +
                                "" + pList.get(i).getDOB().charAt(9) + "" + pList.get(i).getDOB().charAt(10))) {
                            for (int j = 0; j < appList.size(); j++) {
                                if (appList.get(j).appNumber == pList.get(i).getAppID()) {
                                    test2 = true;
                                    choice = pList.get(i).getAppID();
                                }
                            }
                        }
                    }if(test2==false){
                        JOptionPane.showMessageDialog(null, "Appointment does not exist ");
                        appNumText.setText("");
                    }
                }
                else if ((!appNumText.getText().equals(""))) {
                    try {
                        for (int i = 0; i < appList.size(); i++) {
                            if (appList.get(i).appNumber == Integer.parseInt(appNumText.getText())) {
                                choice = Integer.parseInt(appNumText.getText());
                                test = true;
                            }
                        }
                    } catch (NumberFormatException nf) {
                        JOptionPane.showMessageDialog(null, "Appointment Number must be a Number ");
                        appNumText.setText("");
                    }
                    if (test == false) {
                        JOptionPane.showMessageDialog(null, "Appointment does not exist ");
                        appNumText.setText("");
                    }
                }
                if (test == true || test2 == true) {
                    for (int i = 0; i < pList.size(); i++) {
                        if((pList.get(i).getCheckedIn().equals("Checked IN")|| pList.get(i).getCheckedIn().equals("Medically Checked OUT"))&&pList.get(i).getAppID()==choice){
                            JOptionPane.showMessageDialog(null, "Patient is already Checked-IN");
                        }else {
                            CheckIn checkIn = new CheckIn(choice, 1);
                            f.setVisible(false);
                        }
                    }
                }
            }
        }
        else if (e.getSource().equals(checkOut))
        {
        SecurityGUI securityGUI=new SecurityGUI(10);
        }
    }
}
