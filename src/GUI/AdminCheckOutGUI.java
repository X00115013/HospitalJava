package GUI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Lionhart and Thomas Murray on 20/03/2015.
 */
public class AdminCheckOutGUI extends JFrame implements ActionListener
{
    JButton confirm,checkOut,cancel;
    JLabel patientNum,label5;
    JTextField patientText;
    JTextArea additionalInformation;
    JRadioButton checkOutRadio;
    private int patientNumberIn;

    JFrame f;

    public AdminCheckOutGUI(int patientNumIn)
    {
        patientNumberIn=patientNumIn;
        f = new JFrame();
        f.setTitle("Admin Check-Out");
        f.setSize(700, 500);
        f.setResizable(true);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        Border loweredBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);

        JPanel holder=new JPanel(new GridLayout(3,1));
        JPanel topSection=new JPanel(new GridLayout(1,3));

        Clock.DigitalClock clockD=new Clock.DigitalClock();
        JPanel clock = new JPanel(new FlowLayout(FlowLayout.LEFT));
        clock.add(clockD);

        JPanel title=new JPanel(new FlowLayout(FlowLayout.CENTER));
        label5 = new JLabel("Admin Check-Out");
        title.add(label5);
        label5.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel ID=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        //labels
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


        JPanel textArea=new JPanel(new FlowLayout(FlowLayout.CENTER));

        additionalInformation = new JTextArea(230,60);
        additionalInformation.setBorder(loweredBorder);
        textArea.add(additionalInformation);
        holder.add(textArea);

        //DOB labels
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

        // Cancel button
        checkOut = new JButton("Generate Bill");
        checkOut.addActionListener(this);
        dobs.add(checkOut, getConstraints(2, 3, 1, 1, GridBagConstraints.WEST));


        checkOutRadio = new JRadioButton("Check-Out");
        checkOutRadio.addActionListener(this);
        dobs.add(checkOutRadio);
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
            System.exit(0);
        }
        else if (e.getSource().equals(confirm))
        {

        }else if (e.getSource().equals(checkOut))
        {PaymentGUI paymentGUI=new PaymentGUI(patientNumberIn);
        }
    }
}
