package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataBase.AppointmentOperations;
import DataBase.PatientOperations;
/**
 * Created by College on 04/03/2015.
 */
public class PatientRecord {
    private String patientFName;
    private String patientLName;
    private String gender,patientAddress;
    private int patientNumber;
    private String email;
    private String occupation;
    private String phone;
    private String DOB;
    private Appointment appointment;
    private ArrayList<Appointment> appList = new ArrayList<Appointment>();
    private ResultSet rset;
    private PatientOperations po = new PatientOperations();
    private ArrayList<PatientRecord> patientList = new ArrayList<PatientRecord>();
    private AppointmentOperations ao= new AppointmentOperations();


    public void refreshTimeTables() {
        try {
            rset = ao.getAppointment();
            System.out.println("we are getting to Appointment list");
            while (rset.next()) {
                appList.add(appointment = new Appointment(rset.getInt(1),rset.getString(2), rset.getInt(3), rset.getInt(4),rset.getInt(5)));
                System.out.println("Whats in Appointment\t " + rset.getInt(1) + " \t" + rset.getString(2) + " \t" + rset.getInt(3) + " \t" + rset.getInt(4) + "\t" + rset.getInt(5));
            }
        } catch (SQLException e1) {
            System.out.println("Is there to many here 1"+e1);
        }
    }

    public PatientRecord() {
        patientFName = "";
        patientLName = "";
        patientAddress = "";
        gender = "";
        email = "";
        phone = "";
        DOB = "";
    }


    public PatientRecord(String patientFNameIn, String patientLNameIn, String patientAddressIn, String occupationIn,String genderIn, String emailIn, String phoneIn, String DOBIn) {
        patientFName = patientFNameIn;
        patientLName = patientLNameIn;
        patientAddress = patientAddressIn;
        gender = genderIn;
        email = emailIn;
        phone = phoneIn;
        DOB = DOBIn;
        addPatientRecord();
    }

        public void printPatientRecord(){
        rset = po.getPatientAdmin();
        try {
            while (rset.next()) {
                System.out.println(rset.getInt(1)+"\t"+rset.getString(2)+"\t"+rset.getString(3)+"\t"+rset.getString(4)+"\t"+rset.getString(5)+"\t"+rset.getString(6)+"\t"+rset.getString(7)+"\t"+rset.getString(16)+"\t"+rset.getString(17));
            }
        } catch (SQLException e1) {
            System.out.println("Patient record not working");
        }
    }

    public void addPatientRecord(){
        po.addPatient(patientFName,patientLName,patientAddress,occupation,gender,email,phone,DOB);
        printPatientRecord();
    }

    public void updatePatientRecord(){
        po.updatePatientAdmin(patientNumber,patientFName,patientLName,patientAddress,occupation,gender,email,phone,DOB);
        printPatientRecord();
    }



    public int getPatientNumber() {
        return patientNumber;
    }

}
