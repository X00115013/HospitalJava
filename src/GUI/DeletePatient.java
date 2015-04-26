package GUI;

        import Model.PatientRecord;

        import javax.swing.*;
        import javax.swing.border.BevelBorder;
        import javax.swing.border.Border;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

/**
 * Created by Thomas Murray on 20/03/2015.
 *
 * Archive a patient through this GUI
 *
 */
public class DeletePatient extends JFrame implements ActionListener
{
    JButton confirm,cancel;
    JLabel patientNum,titleF,reason;
    JTextField patientText;
    JTextArea additionalInformation;
    private int patientNumberIn;
    private PatientRecord pRec;

    JFrame f;

    public DeletePatient(PatientRecord pRecIn,int patientNumIn)
    {
        pRec=pRecIn;
        patientNumberIn=patientNumIn;
        f = new JFrame();
        f.setTitle("Delete Patient");
        f.setSize(700, 370);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        Border loweredBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        JPanel holder=new JPanel(new GridLayout(3,1));
        JPanel topSection=new JPanel(new GridLayout(1,3));

        //Clock
        Clock.DigitalClock clockD=new Clock.DigitalClock();
        JPanel clock = new JPanel(new FlowLayout(FlowLayout.LEFT));
        clock.add(clockD);

        //Title
        JPanel title=new JPanel(new FlowLayout(FlowLayout.CENTER));
        titleF = new JLabel("Record Deletion");
        title.add(titleF);
        titleF.setFont(new Font("Arial", Font.BOLD, 24));
        JPanel ID=new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //patient num lable
        patientNum = new JLabel("\tPatient Number");
        ID.add(patientNum);
        //text field
        patientText = new JTextField(5);
        patientText.setText(Integer.toString(patientNumIn));
        patientText.setBorder(loweredBorder);
        patientText.setEditable(false);
        ID.add(patientText);
        topSection.add(clock);
        topSection.add(title);
        topSection.add(ID);
        holder.add(topSection);

        //Central Text area
        JPanel textArea=new JPanel(new FlowLayout(FlowLayout.CENTER));
        reason=new JLabel("Reason for Deletion");
        textArea.add(reason);
        additionalInformation = new JTextArea(230,60);
        additionalInformation.setBorder(loweredBorder);
        textArea.add(additionalInformation);
        holder.add(textArea);

        JPanel test =new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel dobs = new JPanel(new GridBagLayout());

        // Confirm button
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        dobs.add(confirm, getConstraints(0, 3, 1, 1, GridBagConstraints.WEST));

        // Cancel button
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        dobs.add(cancel, getConstraints(1, 3, 1, 1, GridBagConstraints.WEST));
        test.add(dobs);
        holder.add(test);

        f.add(holder);
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
            f.setVisible(false);
        }
        else if (e.getSource().equals(confirm))
        {
            pRec.deletePatientRecords(patientNumberIn,additionalInformation.getText());

            additionalInformation.setText("");
            f.setVisible(false);
        }
    }
}