
package GUI;

        import DataBase.PatientOperations;
        import Model.PatientRecord;

        import javax.swing.*;
        import javax.swing.border.BevelBorder;
        import javax.swing.border.Border;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

/**
 * Created by Thomas Murray on 20/03/2015.
 */
public class MedPatientRecGUI extends JFrame implements ActionListener {
    JButton update, cancel;
    JLabel patientNum, label5;
    JTextField patientText;
    JTextArea additionalInformation;
    JRadioButton checkOutRadio;

    JFrame f;

    public MedPatientRecGUI() {
        f = new JFrame();
        f.setTitle("Medical Records");
        f.setSize(820, 1000);
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
        label5 = new JLabel("Patient Medical Records");
        title.add(label5);
        label5.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel ID = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //labels
        patientNum = new JLabel("\tPatient Number");
        ID.add(patientNum);
        //text field
        patientText = new JTextField(5);
        patientText.setBorder(loweredBorder);
        ID.add(patientText);


        topSection.add(clock);
        topSection.add(title);
        topSection.add(ID);
//        f.add(topSection);

        holder.add(topSection);


        JPanel textArea = new JPanel(new FlowLayout(FlowLayout.CENTER));

        additionalInformation = new JTextArea(40, 70);
        additionalInformation.setBorder(loweredBorder);
        textArea.add(additionalInformation);
//        holder.add(textArea);


        JPanel holder2 = new JPanel(new GridLayout(1, 1));
        JPanel test = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel dobs = new JPanel(new GridBagLayout());

        // Update button
        update = new JButton("Update");
        update.addActionListener(this);
        dobs.add(update, getConstraints(0, 4, 1, 1, GridBagConstraints.WEST));


        // Cancel button
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        dobs.add(cancel, getConstraints(1, 4, 1, 1, GridBagConstraints.WEST));

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
            System.exit(0);
        } else if (e.getSource().equals(update)) {
           UpdateMedRec updateMedRec=new UpdateMedRec();

        }
    }
}