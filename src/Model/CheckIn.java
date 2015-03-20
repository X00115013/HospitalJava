package Model;

import DataBase.AppointmentOperations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Roland on 20/03/2015.
 */
public class CheckIn {
    private int appointmentNumber;
    private String patientFirstName;
    private String patientLName;
    private String patientDob;
    private ArrayList<Appointment> appList = new ArrayList<Appointment>();
    private AppointmentOperations ao;
    private ResultSet rset;
    private Appointment apt;

    public CheckIn(int appointmentNumberIn, String patientFirstNameIn, String patientLNameIn, String patientDobIn){
        appointmentNumber=appointmentNumberIn;
        patientFirstName=patientFirstNameIn;
        patientLName=patientLNameIn;
        patientDob=patientDobIn;

    }

    public void appointmentArray() {
        ao=new AppointmentOperations();
        try {
            rset = ao.getAppointment();
            System.out.println("\n\n\nAppointment list\n");
            while (rset.next()) {
                appList.add(apt = new Appointment(rset.getInt(1),rset.getString(2),rset.getInt(3),rset.getInt(4),rset.getInt(5)));
            }
        } catch (SQLException e1) {
            System.out.println(e1);
        }
    }

    public void checkIn(){

    }
}
