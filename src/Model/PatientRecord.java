package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    private String phone;
    private String DOB;
    private ResultSet rset;
    private PatientOperations po;
    private ArrayList<ResultSet> patientList = new ArrayList<ResultSet>();


    public PatientRecord() {
        patientFName = "";
        patientLName = "";
        patientAddress = "";
        gender = "";
        email = "";
        phone = "";
        DOB = "";
    }


    public PatientRecord(String patientFNameIn, String patientLNameIn, String patientAddressIn, String genderIn, String emailIn, String phoneIn, String DOBIn) {
        patientFName = patientFNameIn;
        patientLName = patientLNameIn;
        patientAddress = patientAddressIn;
        gender = genderIn;
        email = emailIn;
        phone = phoneIn;
        DOB = DOBIn;


        rset = po.getPatientAdmin();
        try {
            while (rset.next()) {
                patientList.add(rset);
            }
        } catch (SQLException e1) {
            System.out.println("stupid crap");
        }


    }
    public int getPatientNumber() {
        return patientNumber;
    }

    public void updateAdminRecords(){

    }
}
