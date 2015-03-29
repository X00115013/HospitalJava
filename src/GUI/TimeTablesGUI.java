package GUI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Thomas Murray on 20/03/2015.
 */
public class TimeTablesGUI extends JFrame implements ActionListener
{
    JButton print,cancel;
    String[] list1 = {"Consultant :","XRay :", "MRI Scan :", "CT Scan :"};
    JLabel patientNum,label5;
    JTextField patientText;
    JTextArea additionalInformation;
    JRadioButton checkOutRadio;
    JComboBox<String> combo1;
    JFrame f;

    public TimeTablesGUI()
    {
        f = new JFrame();
        f.setTitle("Time Tables");
        f.setSize(805, 1010);
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
        label5 = new JLabel("Time Tables");
        title.add(label5);
        label5.setFont(new Font("Arial", Font.BOLD, 42));

        topSection.add(clock);
        topSection.add(title);
        holder.add(topSection);

        JPanel textArea=new JPanel(new FlowLayout(FlowLayout.CENTER));

        additionalInformation = new JTextArea(40,70);
        additionalInformation.setBorder(loweredBorder);
        textArea.add(additionalInformation);
//        holder.add(textArea);


        JPanel holder2=new JPanel(new GridLayout(3,1));
        JPanel test =new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel buttons = new JPanel(new GridBagLayout());
        JPanel selectionCombo=new JPanel(new FlowLayout(FlowLayout.RIGHT));

        combo1 = new JComboBox<String>(list1);
        combo1.setSize(30, 1);
        selectionCombo.add(combo1);
        combo1.addActionListener(this);

        // Print button
        print = new JButton("Print");
        print.addActionListener(this);
        buttons.add(print, getConstraints(0, 4, 1, 1, GridBagConstraints.WEST));

        // Cancel button
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        buttons.add(cancel, getConstraints(1, 4, 1, 1, GridBagConstraints.WEST));

        test.add(buttons);
        holder2.add(selectionCombo);
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
        } else if (e.getSource().equals(print)){

        }
    }
}
