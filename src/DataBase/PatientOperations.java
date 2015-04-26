package DataBase;

import oracle.jdbc.pool.OracleDataSource;

import javax.swing.*;
import java.sql.*;

/**
 * Created by Thomas Murray on 08/03/2015.
 */
public class PatientOperations {
    private Connection conn;
    private ResultSet rset;
    private PreparedStatement pstmt;
    private Statement stmt;
    private Connect connect= new Connect();

    public PatientOperations() {
        conn = connect.openDB();
    }

    public void patientOperationsClose(){
        connect.closeDB();
    }

    public ResultSet getPatientAdmin() {
        try {
            String queryString ="SELECT patient_Number, " +
            "patientFName, patientLName,patientAddress " +
                    ", PatientDOB ,PatientGender," +
                    "occupation,PatientEmail," +
                    "patientPhone,AppID,checkedIn "+
                    "FROM Patient";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println("po 1"+e);
        }
        return rset;
    }
    public ResultSet getPatientMedical() {
        try {
            String queryString = "SELECT patient_Number, " +
                    "patientFName, patientLName " +
                    ", PatientDOB ,PatientGender," +
                    " BloodType ,Symptoms, Diagnoses," +
                    " RequiredTreatment ," +
                    " Allergies ," +
                    "Recommendation " +
                    "FROM Patient";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println("po 4"+e);
        }
        return rset;
    }
    public ResultSet getPatientChart(int patientNumIn){
        try {
            String queryString = "SELECT patient_Number, " +
                    "patientFName, patientLName " +
                    ", PatientDOB ,PatientGender," +
                    " BloodType ,Symptoms, Diagnoses," +
                    " RequiredTreatment,Allergies ," +
                    "Recommendation " +
                    "FROM Patient WHERE patient_Number = "+patientNumIn;
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println("po 5"+e);
        }
        return rset;
    }
    public void addPatient(String patientFNameIn, String patientLNameIn, String patientAddressIn,String occupationIn, String genderIn, String emailIn, String phoneIn, String DOBIn) {
        try {
            String sqlQuery = "INSERT INTO Patient(patient_Number,patientFName, patientLName, patientDOB, patientGender, occupation,PatientEmail," +
                    " patientPhone,patientAddress) "+ "values(PatientID.nextVal,?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1,patientFNameIn);
            pstmt.setString(2, patientLNameIn);
            pstmt.setString(3,DOBIn);
            pstmt.setString(4,genderIn);
            pstmt.setString(5,occupationIn);
            pstmt.setString(6,emailIn);
            pstmt.setString(7,phoneIn);
            pstmt.setString(8,patientAddressIn);
            pstmt.executeUpdate();
        } catch (Exception se) {
            System.out.println("po 6"+se);
        }
    }
    public void updatePatientAdmin(int patientNumber, String newPFname,String newPLname, String newPaddress, String occupationIn, String genderIn, String newEmail,String newPphone, String DOBIn)
    {
        try {
            String sql1 = "UPDATE Patient SET patientFName= '" + newPFname +"'," +
                    "patientLName= '" + newPLname + "'," +
                    "patientAddress= '"+newPaddress+ "'," +
                    " patientPhone= '"+newPphone+"'," +
                    "patientDOB ='"+DOBIn+"',"+
                    " patientEmail= '"+newEmail+"'," +
                    "patientGender= '"+genderIn+"',"+
                    " occupation = '"+occupationIn+"'" +
                    "where patient_Number=" +patientNumber;
            stmt = conn.createStatement();
            stmt.executeUpdate(sql1);
        } catch (Exception se) {
            System.out.println("Problem update admin po "+se);
        }
    }
    public void updatePatientCheckIn(int appNumber, String checkedIn)
    {
        try {
            String sql1 = "UPDATE Patient SET checkedIn= '" + checkedIn +"'" +
                    "WHERE AppID=" +appNumber;
            stmt = conn.createStatement();
            stmt.executeUpdate(sql1);
            JOptionPane.showMessageDialog(null, "Patient Checked In");
        } catch (Exception se) {
            System.out.println("po 7"+se);
        }
    }
    public void updatePatientCheckInByNumber( int patientNumber, String checkedIn)
    {
        try {
            String sql1 = "UPDATE Patient SET checkedIn= '" + checkedIn +"'" +
                    "where patient_Number=" +patientNumber;
            stmt = conn.createStatement();
            stmt.executeUpdate(sql1);
            JOptionPane.showMessageDialog(null, "Patient "+patientNumber+" is "+checkedIn);
            if(checkedIn.equals("Checked OUT")){
                updateCheckInfo(patientNumber);
            }
        } catch (Exception se) {
            System.out.println("po 8"+se);
        }
    }
    public void updatePatientMedical(int patientNumber,String newBlood, String newSymptoms, String newDiagnoses, String newReqTreatment, String newAllergies )
    {
        try {
            String sql1 = "UPDATE Patient SET bloodType= '"+newBlood+"'," +
                    "symptoms= '" +newSymptoms+ "'," +
                    "requiredTreatment= '"+newReqTreatment+ "'," +
                    " diagnoses= '"+newDiagnoses+"'," +
                    " allergies= '"+newAllergies+"'" +
                    " where patient_Number=" +patientNumber;
            stmt = conn.createStatement();
            stmt.executeUpdate(sql1);
        } catch (Exception se) {
            System.out.println("The error might be here po update medical "+se);
        }
    }
    public void deletePatient(int n,String reason) {
        try {
            archive(n,reason);
            String sql1 = "UPDATE Patient SET " +
                    "patientAddress= 'Archived'," +
                    "patientPhone= 'Archived'," +
                    "patientEmail= 'Archived'," +
                    "occupation = 'Archived'," +
                    "bloodType= 'Archived'," +
                    "symptoms= 'Archived'," +
                    "requiredTreatment= 'Archived'," +
                    "diagnoses= 'Archived'," +
                    "allergies= 'Archived'," +
                    "Recommendation ='Archived'," +
                    "patientGender= 'Archive'"+
                    "where patient_Number=" +n;
            stmt = conn.createStatement();
            stmt.executeUpdate(sql1);
            JOptionPane.showMessageDialog(null, "Patient "+n+" has been Archived");
        } catch (Exception se) {
            System.out.println("delete patient "+se);
        }
    }
    public void refMedUpdate(int patientNumber, String recommendationIn )
    {
        try {
            String sql1 = "UPDATE Patient SET Recommendation = '"+recommendationIn+"' where patient_Number= " +patientNumber;
            stmt = conn.createStatement();
            stmt.executeUpdate(sql1);
        } catch (Exception se) {
            System.out.println(se);
        }
    }
    public ResultSet getArchive(int n){
        try {
            String queryString = "SELECT * FROM Patient where patient_Number = "+n;
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println("2 delete"+e);
        }
        return rset;
    }
    public ResultSet getCheckInfo(int n){
        try {
            String queryString = "SELECT * FROM checkOUT where thisVisit = 1 AND patient_Num = "+n;
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println("Check OUT info po "+e);
        }
        return rset;
    }

    public void updateCheckInfo(int patientNumber)
    {
        try {
            String sql1 = "UPDATE checkOUT SET thisVisit = 2 where patient_Num = " +patientNumber;
            stmt = conn.createStatement();
            stmt.executeUpdate(sql1);
        } catch (Exception se) {
            System.out.println(se);
        }
    }
    public void addCheckOutInfo(int patientNum ,String extraInfo,String conName) {
        try {


            String queryString1 = "INSERT INTO checkOUT(check_ID, message ,conName ,thisVisit, patient_Num ) VALUES(check_seq.nexTVal,?,?,?,?)";
            pstmt = conn.prepareStatement(queryString1);
            pstmt.setString(1,extraInfo);
            pstmt.setString(2,conName);
            pstmt.setInt(3, 1);
            pstmt.setInt(4, patientNum);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("add check out info po "+e);
        }
    }
    public void archive(int n,String reason) {
        try {
            String queryString1 = "INSERT INTO Archive(archiveID,patient_numIn,reasonForDeletion,archiveFile )VALUES(archive_seq.nexTVal,?,?,?)";
            pstmt = conn.prepareStatement(queryString1);
            pstmt.setString(1, ""+n);
            pstmt.setString(2, reason);
            pstmt.setString(3, ""+getArchive(n));
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("3 delete"+e);
        }
    }
    public int getPatientNumber(String fName, String lName, String dobIn)
    {
        int id=0;
        try {
            String sql1 = "SELECT patient_Number FROM Patient WHERE patientFName= '"+fName+"' AND patientLName= '"+lName+"' AND patientDOB= '"+dobIn+"'";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(sql1);
            if (rset.next()) {
                id = rset.getInt(1);
            }
        } catch (Exception se) {
            System.out.println("po 9"+se);
        }
        return id;
    }
}



//Spare Parts


//    public ResultSet getPatientAdmin(int id) {
//        try {
//            String queryString = "SELECT patient_Number, " +
//                    "patientFName, patientLName " +
//                    ", PatientDOB ,PatientGender," +
//                    "occupation,PatientEmail," +
//                    "patientPhone,patientAddress "+
//                    "FROM Patient WHERE patient_Number= "+id;
//            stmt = conn.createStatement();
//            rset = stmt.executeQuery(queryString);
//        } catch (Exception e) {
//            System.out.println("po 2"+e);
//        }
//        return rset;
//    }
//    public ResultSet getPatientMedical(int id) {
//        try {
//            String queryString = "SELECT patient_Number, " +
//                    "patientFName, patientLName " +
//                    ", PatientDOB ,PatientGender," +
//                    " BloodType ,Symptoms, Diagnoses," +
//                    " RequiredTreatment ," +
//                    " Allergies ," +
//                    "Recommendation " +
//                    "FROM Patient where patient_Number= "+id;
//            stmt = conn.createStatement();
//            rset = stmt.executeQuery(queryString);
//        } catch (Exception e) {
//            System.out.println("po 3"+e);
//        }
//        return rset;
//    }

//    public int getNumPatients()
//    {
//        int num=0;
//        try {
//            String queryString = "SELECT count(*) FROM Patient";
//
//            stmt = conn.createStatement();
//            rset = stmt.executeQuery(queryString);
//            if (rset.next()) {
//                num = rset.getInt(1);
//            }
//        } catch (Exception e) {
//            System.out.println("This is not working"+e);
//        }
//        return num;
//    }