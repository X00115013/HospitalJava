package GUI;

        import Model.Appointment;

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
public class AppointmentDetailsGUI extends JFrame implements ActionListener
{
    JButton confirm,print,cancel;
    JLabel patientNum,label5;
    JTextField patientText;
    JTextArea additionalInformation;
    JScrollPane scroll;
    ArrayList<Appointment>appointments=new ArrayList<>();
    private int appNumberIn;
    private Appointment appointment;
    private String appDetails="should be working";


    JFrame f;

    public AppointmentDetailsGUI(Appointment app,int appointmentNumIn)
    {
        appNumberIn=appointmentNumIn;
        appointment=app;
        f = new JFrame();
        f.setTitle("Appointment Details");
        f.setSize(750, 620);
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
        label5 = new JLabel("Appointment Details");
        title.add(label5);
        label5.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel ID=new JPanel(new FlowLayout(FlowLayout.CENTER));
        //labels
        patientNum = new JLabel("\tAppointment Number");
        ID.add(patientNum);
        //text field
        patientText = new JTextField(5);
        patientText.setText(Integer.toString(appointmentNumIn));
        patientText.setBorder(loweredBorder);
        patientText.setEditable(false);
        ID.add(patientText);


        topSection.add(clock);
        topSection.add(title);
        topSection.add(ID);

        holder.add(topSection);



        JPanel textArea=new JPanel(new FlowLayout(FlowLayout.CENTER));


        appointments.addAll(appointment.appArray());
        for (int i = 0; i < appointments.size(); i++) {
            System.out.println("Fink "+appointmentNumIn);
            System.out.println("Fank "+appointments.get(i).appNumber);
            if(appointmentNumIn==appointments.get(i).appNumber){
                appDetails="\tAppointment Number ("+appointments.get(i).appNumber+")\n\n" +
                        "\tReason for Visit ("+appointments.get(i).reasonForVisit+")\n\n" +
                        "\tMedical Equipment ("+appointments.get(i).medicalEquip+")\n\n" +
                        "\tConsultant Required ("+appointments.get(i).patientNum+")";
            }

        }


        additionalInformation = new JTextArea(20,60);
        additionalInformation.setBorder(loweredBorder);
        additionalInformation.setText(appDetails);
        scroll = new JScrollPane(additionalInformation);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textArea.add(scroll);



        JPanel holder2=new JPanel(new GridLayout(1,1));
        JPanel test =new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel dobs = new JPanel(new GridBagLayout());

        // Update button
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        dobs.add(confirm, getConstraints(0, 4, 1, 1, GridBagConstraints.WEST));

        // Delete button
        print = new JButton("Print");
        print.addActionListener(this);
        dobs.add(print, getConstraints(1, 4, 1, 1, GridBagConstraints.WEST));

        // Cancel button
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        dobs.add(cancel, getConstraints(2, 4, 1, 1, GridBagConstraints.WEST));

        test.add(dobs);
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
            f.setVisible(false);
        } else if (e.getSource().equals(confirm)){
            f.setVisible(false);

        }else if (e.getSource().equals(print))
        {
            File Files = new File("files");
            File textFile = new File(Files, ""+appNumberIn+"_APPOINTMENT.txt");
            try (FileWriter input = new FileWriter(textFile)) {
                //Input text
                input.write(additionalInformation.getText());
            } catch (IOException io) {
                System.out.println(io);
            }
        }
    }
}