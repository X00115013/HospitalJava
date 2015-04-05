package Model;

import DataBase.AppointmentOperations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Thomas Murray on 20/03/2015.
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


    public void checkIn(){

    }
}
