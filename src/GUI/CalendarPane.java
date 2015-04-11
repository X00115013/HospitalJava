package GUI;
    import java.awt.BorderLayout;
    import java.awt.Color;
    import java.awt.Component;
    import java.awt.Container;
    import java.awt.FlowLayout;
    import java.awt.GridLayout;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.util.Calendar;
    import java.util.GregorianCalendar;

    import javax.swing.BorderFactory;
    import javax.swing.JButton;
    import javax.swing.JComboBox;
    import javax.swing.JFrame;
    import javax.swing.JPanel;


    public class CalendarPane extends JPanel {
        protected int yy;
        protected int mm, dd;
        protected JButton labs[][];
        protected int leadGap = 0;
        Calendar calendar = new GregorianCalendar();
        protected final int thisYear = calendar.get(Calendar.YEAR);
        protected final int thisMonth = calendar.get(Calendar.MONTH);
        private JButton b0;
        private JComboBox monthChoice;
        private JComboBox yearChoice;
        CalendarPane() {
            super();
            setYYMMDD(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            buildGUI();
            recompute();
        }
        CalendarPane(int year, int month, int today) {
            super();
            setYYMMDD(year, month, today);
            buildGUI();
            recompute();
        }

        private void setYYMMDD(int year, int month, int today) {
            yy = year;
            mm = month;
            dd = today;
        }

        String[] months = { "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December" };

        private void buildGUI() {
            getAccessibleContext().setAccessibleDescription(
                    "Calendar not accessible yet. Sorry!");
            setBorder(BorderFactory.createEtchedBorder());

            setLayout(new BorderLayout());

            JPanel tp = new JPanel();
            tp.add(monthChoice = new JComboBox());
            for (int i = 0; i < months.length; i++)
                monthChoice.addItem(months[i]);
            monthChoice.setSelectedItem(months[mm]);
            monthChoice.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    int i = monthChoice.getSelectedIndex();
                    if (i >= 0) {
                        mm = i;
                        recompute();
                    }
                }
            });
            monthChoice.getAccessibleContext().setAccessibleName("Months");
            monthChoice.getAccessibleContext().setAccessibleDescription(
                    "Choose a month of the year");

            tp.add(yearChoice = new JComboBox());
            yearChoice.setEditable(true);
            for (int i = yy - 5; i < yy + 5; i++)
                yearChoice.addItem(Integer.toString(i));
            yearChoice.setSelectedItem(Integer.toString(yy));
            yearChoice.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    int i = yearChoice.getSelectedIndex();
                    if (i >= 0) {
                        yy = Integer.parseInt(yearChoice.getSelectedItem()
                                .toString());
                        recompute();
                    }
                }
            });
            add(BorderLayout.CENTER, tp);

            JPanel bp = new JPanel();
            bp.setLayout(new GridLayout(7, 7));
            labs = new JButton[6][7];

            bp.add(b0 = new JButton("Sun"));

            bp.add(new JButton("Mon"));
            bp.add(new JButton("Tues"));
            bp.add(new JButton("Wed"));
            bp.add(new JButton("Thur"));
            bp.add(new JButton("Fri"));
            bp.add(new JButton("Sat"));

            ActionListener dateSetter = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String num = e.getActionCommand();
                    if (!num.equals("")) {
                        setDayActive(Integer.parseInt(num));
                    }
                }
            };
            for (int i = 0; i < 6; i++)
                for (int j = 0; j < 7; j++) {
                    bp.add(labs[i][j] = new JButton(""));
                    labs[i][j].addActionListener(dateSetter);
                }

            add(BorderLayout.SOUTH, bp);
        }

        public final static int dom[] = { 31, 28, 31, 30, /* jan feb mar apr */
                31, 30, 31, 31, /* may jun jul aug */
                30, 31, 30, 31 /* sep oct nov dec */
        };
        protected void recompute() {
            if (mm < 0 || mm > 11)
                throw new IllegalArgumentException("Month " + mm
                        + " bad, must be 0-11");
            clearDayActive();
            calendar = new GregorianCalendar(yy, mm, dd);
            leadGap = new GregorianCalendar(yy, mm, 1).get(Calendar.DAY_OF_WEEK) - 1;

            int daysInMonth = dom[mm];

            if (isLeap(calendar.get(Calendar.YEAR)) && mm == 1)
                ++daysInMonth;

            for (int i = 0; i < leadGap; i++) {
                labs[0][i].setText("");
            }

            for (int i = 1; i <= daysInMonth; i++) {
                JButton b = labs[(leadGap + i - 1) / 7][(leadGap + i - 1) % 7];
                b.setText(Integer.toString(i));
            }

            for (int i = leadGap + 1 + daysInMonth; i < 6 * 7; i++) {
                labs[(i) / 7][(i) % 7].setText("");
            }

            if (thisYear == yy && mm == thisMonth)
                setDayActive(dd);


            repaint();
        }

        public boolean isLeap(int year) {
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
                return true;
            return false;
        }

        public void setDate(int yy, int mm, int dd) {
            this.yy = yy;
            this.mm = mm;
            this.dd = dd;
            recompute();
        }

        private void clearDayActive() {
            JButton b;


            if (activeDay > 0) {
                b = labs[(leadGap + activeDay - 1) / 7][(leadGap + activeDay - 1) % 7];
                b.setBackground(b0.getBackground());
                b.repaint();
                activeDay = -1;
            }
        }

        private int activeDay = -1;

        public void setDayActive(int newDay) {
            clearDayActive();
            if (newDay <= 0)
                dd = new GregorianCalendar().get(Calendar.DAY_OF_MONTH);
            else
                dd = newDay;
            Component square = labs[(leadGap + newDay - 1) / 7][(leadGap + newDay - 1) % 7];
            square.setBackground(Color.LIGHT_GRAY);
            square.repaint();
            activeDay = newDay;
        }

    }
