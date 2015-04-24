package Model;

import GUI.PaymentGUI;

import javax.swing.*;

/**
 * Created by Thomas Murray on 24/04/2015.
 */
public class CardValidation {

    public CardValidation(String ccnum) {

        if (ccnum.length() == 16) {
            char[] c = ccnum.toCharArray();
            int[] cint = new int[16];
            for (int i = 0; i < 16; i++) {
                if (i % 2 == 1) {
                    cint[i] = Integer.parseInt(String.valueOf(c[i])) * 2;
                    if (cint[i] > 9)
                        cint[i] = 1 + cint[i] % 10;
                } else
                    cint[i] = Integer.parseInt(String.valueOf(c[i]));
            }
            int sum = 0;
            for (int i = 0; i < 16; i++) {
                sum += cint[i];
            }
            if (sum % 10 == 0) {
                JOptionPane.showMessageDialog(null, "Card is Valid");
                PaymentGUI.valid=true;

            }else {
                JOptionPane.showMessageDialog(null, "Card is Invalid");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Card is Invalid");
        }
    }
}
