package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataBase.AppointmentOperations;
import DataBase.PatientOperations;
/**
 * Created by Thomas Murray on 04/03/2015.
 */
public class PatientRecord {
    private String patientFName;
    private String patientLName;
    private String gender,patientAddress;
    private int patientNumber,appID;
    private String email;
    private String occupation;
    private String phone;
    private String DOB;
    private ResultSet rset;
    private PatientOperations po;
    private ArrayList<PatientRecord> patientList = new ArrayList<PatientRecord>();


    public void refreshArrays() {
        try {
            patientList.removeAll(patientList);
            po=new PatientOperations();
            rset = po.getPatientAdmin();
            while (rset.next()) {
                patientList.add(new PatientRecord(rset.getInt(1),rset.getString(2),rset.getString(3),rset.getString(4),
                        rset.getString(5),rset.getString(6),rset.getString(7),rset.getString(8),rset.getString(9),rset.getInt(10)));
            }po.patientOperationsClose();
        } catch (SQLException e1) {
            System.out.println(e1);
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
    public PatientRecord(int patientNumIn,String patientFNameIn, String patientLNameIn, String patientAddressIn,String DOBIn,String genderIn, String occupationIn, String emailIn, String phoneIn,int appIdIn ) {
        patientNumber=patientNumIn;
        patientFName = patientFNameIn;
        patientLName = patientLNameIn;
        patientAddress = patientAddressIn;
        occupation=occupationIn;
        gender = genderIn;
        email = emailIn;
        phone = phoneIn;
        DOB = DOBIn;
        appID=appIdIn;

    }

    public PatientRecord(PatientOperations po,int patientNumIn,String patientFNameIn, String patientLNameIn, String patientAddressIn, String occupationIn,String genderIn, String emailIn, String phoneIn, String DOBIn) {
        this.po=po;
        patientNumber=patientNumIn;
        patientFName = patientFNameIn;
        patientLName = patientLNameIn;
        patientAddress = patientAddressIn;
        occupation=occupationIn;
        gender = genderIn;
        email = emailIn;
        phone = phoneIn;
        DOB = DOBIn;
        updatePatientRecord(patientNumIn);

    }

    public PatientRecord(PatientOperations po,String patientFNameIn, String patientLNameIn, String patientAddressIn, String occupationIn,String genderIn, String emailIn, String phoneIn, String DOBIn) {
        this.po=po;
        patientFName = patientFNameIn;
        patientLName = patientLNameIn;
        patientAddress = patientAddressIn;
        occupation=occupationIn;
        gender = genderIn;
        email = emailIn;
        phone = phoneIn;
        DOB = DOBIn;
        addPatientRecord();
    }

    public void addPatientRecord(){
        System.out.println("New Patient NEW NEW NEW NEW NEW NEW ");
        po.addPatient(patientFName, patientLName, patientAddress, occupation, gender, email, phone, DOB);
//        printFromArrayAR();
    }

    public void updatePatientRecord(int patientNumIn){
        System.out.println("Updated Patient UPDATED UPDATED UPDATED ");
        po.updatePatientAdmin(patientNumIn, patientFName, patientLName, patientAddress, occupation, gender, email, phone, DOB);
//        printFromArrayAR();
//        printFromArrayARByNumber(2);
    }

    public int getPatientNumber() {
        return patientNumber;
    }

    public void deletePatientRecords(int patientNumIn,String reason){
        po=new PatientOperations();
        po.deletePatient(patientNumIn,reason);
        po.patientOperationsClose();
    }

    public ArrayList getPatientList() {
        refreshArrays();
        return patientList;
    }

    public String getPatientFName() {
        return patientFName;
    }

    public String getPatientLName() {
        return patientLName;
    }

    public String getDOB() {
        return DOB;
    }

    public PatientOperations getPo() {
        return po;
    }

    public ResultSet getRset() {
        return rset;
    }

    public String getPhone() {
        return phone;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getEmail() {
        return email;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public String getGender() {
        return gender;
    }

    public int getAppID() {
        return appID;
    }
}

