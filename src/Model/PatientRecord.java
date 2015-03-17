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
    private PatientRecord patientRecord;
    private ResultSet rset;
    private PatientOperations po;
    private ArrayList<PatientRecord> patientList = new ArrayList<PatientRecord>();


    public void refreshArrays() {
        try {
            rset = po.getPatientAdmin();
            System.out.println("Patient Admin Record Array");
            while (rset.next()) {
                patientList.add(patientRecord = new PatientRecord(rset.getInt(1),rset.getString(2),rset.getString(3),rset.getString(4),
                        rset.getString(5),rset.getString(6),rset.getString(7),rset.getString(8),rset.getString(9)));
            }
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
    public PatientRecord(int patientNumIn,String patientFNameIn, String patientLNameIn, String patientAddressIn, String occupationIn,String genderIn, String emailIn, String phoneIn, String DOBIn) {
        patientNumber=patientNumIn;
        patientFName = patientFNameIn;
        patientLName = patientLNameIn;
        patientAddress = patientAddressIn;
        occupation=occupationIn;
        gender = genderIn;
        email = emailIn;
        phone = phoneIn;
        DOB = DOBIn;

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
        patientNumber=po.getPatientNumber(patientFName,patientLName,DOB);

    }


    public void printFromArrayARByNumber(int patientNumIn){
        System.out.println("\n\n\nPatient Admin Record By Number from Array\n");
        for (int i = 0; i < patientList.size(); i++) {
            if(patientList.get(i).patientNumber == patientNumIn){
                System.out.print("\n\n" + patientList.get(i).patientNumber);
                System.out.print("\t" + patientList.get(i).patientFName);
                System.out.print("\t" + patientList.get(i).patientLName);
                System.out.print("\t" + patientList.get(i).patientAddress);
                System.out.print("\t" + patientList.get(i).occupation);
                System.out.print("\t" + patientList.get(i).gender);
                System.out.print("\t" + patientList.get(i).email);
                System.out.print("\t" + patientList.get(i).phone);
                System.out.print("\t" + patientList.get(i).DOB);
            }
        }
    }

    public void printFromArrayMR(){
        System.out.println("\n\n\nPatient Admin Record from Array\n");
        for (int i = 0; i < patientList.size(); i++) {
                System.out.print("\n\n" + patientList.get(i).patientNumber);
                System.out.print("\t" + patientList.get(i).patientFName);
                System.out.print("\t" + patientList.get(i).patientLName);
                System.out.print("\t" + patientList.get(i).patientAddress);
                System.out.print("\t" + patientList.get(i).occupation);
                System.out.print("\t" + patientList.get(i).gender);
                System.out.print("\t" + patientList.get(i).email);
                System.out.print("\t" + patientList.get(i).phone);
                System.out.print("\t" + patientList.get(i).DOB);
            }
        }



    public void addPatientRecord(){
        po.addPatient(patientFName, patientLName, patientAddress, occupation, gender, email, phone, DOB);
        refreshArrays();
        printFromArrayMR();
        printFromArrayARByNumber(2);
    }

    public void updatePatientRecord(int patientNumIn){
        po.updatePatientAdmin(patientNumIn, patientFName, patientLName, patientAddress, occupation, gender, email, phone, DOB);
        printFromArrayMR();
    }

    public int getPatientNumber() {
        return patientNumber;
    }

    public void deletePatientRecords(int patientNumIn){
        po.deletePatient(patientNumIn);
        printFromArrayMR();
    }

    public ArrayList<PatientRecord> getPatientList() {
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
}





//    public void printPatientRecordByPatientNum(int patientNumIn){
//        System.out.println("\n\n\nPatient Admin Record By Number\n");
//        rset = po.getPatientAdmin(patientNumIn);
//        try {
//            while (rset.next()) {
//                System.out.println(rset.getInt(1)+"\t"+rset.getString(2)+"\t"+rset.getString(3)+"\t"+rset.getString(4)+"" +
//                        "\t"+rset.getString(5)+"\t"+rset.getString(6)+"\t"+rset.getString(7)+"\t"+rset.getString(8)+"\t"+rset.getString(9));
//            }
//        } catch (SQLException e1) {
//            System.out.println(e1);
//        }
//    }
//
//    public void printPatientRecord(){
//        System.out.println("\n\n\nPatient Admin Record\n");
//        rset = po.getPatientAdmin();
//        try {
//            while (rset.next()) {
//                System.out.println(rset.getInt(1)+"\t"+rset.getString(2)+"\t"+rset.getString(3)+"\t"+rset.getString(4)+"" +
//                        "\t"+rset.getString(5)+"\t"+rset.getString(6)+"\t"+rset.getString(7)+"\t"+rset.getString(16)+"\t"+rset.getString(17));
//            }
//        } catch (SQLException e1) {
//            System.out.println(e1);
//        }
//    }