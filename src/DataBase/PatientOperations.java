package DataBase;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;

/**
 * Created by Roland on 08/03/2015.
 */
public class PatientOperations {
    private Connection conn;
    private ResultSet rset;
    private PreparedStatement pstmt;
    private Statement stmt;
    private Connect connect= new Connect();

    public PatientOperations() {conn = connect.openDB();
    }

    public ResultSet getPatientAdminByNum(int id) {
        try {
            String queryString = "SELECT patientNumber, patientFName ,patientLName, " +
                    "patientAddress, patientPhone, patientEmail FROM Patient where patientNumber = "+id;
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;
    }

    public ResultSet getPatientAdmin() {
        try {
            String queryString = "SELECT * FROM Patient";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;
    }



    public ResultSet getPatientMedical(int id) {
        try {
            String queryString = "SELECT * FROM Patient where patientNumber = "+id;
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;
    }


    public int getNumPatients()
    {
        int num=0;
        try {
            String queryString = "SELECT count(*) FROM Patient";

            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
            if (rset.next()) {
                num = rset.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("This is not working"+e);
        }
        return num;
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
            System.out.println("Is there to many here 2"+se);
        }
    }







    public void updatePatientAdmin(int patientNumber, String newPFname,String newPLname, String newPaddress, String occupationIn, String genderIn,String newPphone, String newEmail, String DOBIn)
    {
        try {
            String sql1 = "UPDATE Patient SET patientFName= '" + newPFname +"',patientLName= '" + newPLname + "', " +
                    "patientAddress= '"+newPaddress+ "', patientPhone= '"+newPaddress+"', patientEmail= " +
                    "'"+newEmail+", occupation = '"+occupationIn+"',where patientNumber=" +patientNumber+"'";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql1);
        } catch (Exception se) {
            System.out.println(se);
        }
    }

    public void updatePatientMedical(int patientNumber,String newBlood, String newSymptoms, String newDiagnoses, String newReqTreatment, int newEquipNeed, String recommendationIn, String newAllergies )
    {
        try {
            String sql1 = "UPDATE Patient SET bloodType= '" + newBlood +"',symptoms= '" + newSymptoms + "', " +
                    "requiredTreatment= '"+newReqTreatment+ "', diagnoses= '"+newDiagnoses+"', equipmentNeeded= " +
                    "'"+newEquipNeed+"', allergies= '"+newAllergies+"', Recommendation ='"+recommendationIn+"' where patientNumber=" +patientNumber+"'";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql1);
        } catch (Exception se) {
            System.out.println(se);
        }
    }

    public void refMedUpdate(int patientNumber, int newEquipNeed, String recommendationIn )
    {
        try {
            String sql1 = "UPDATE Patient SET equipmentNeeded= '"+newEquipNeed+"', Recommendation ='"+recommendationIn+"' where patientNumber=" +patientNumber+"'";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql1);
        } catch (Exception se) {
            System.out.println(se);
        }
    }


    public void deletePatient(int n) {
        try {
            String cmd = "DELETE * FROM patient WHERE patientNumber =" + n;
            stmt = conn.createStatement();
            stmt.executeUpdate(cmd);
            archive(n);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public ResultSet getArchive(int n){
        try {
            String queryString = "SELECT * FROM Patient where patientNumber = "+n;
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;
    }

    public void archive(int n) {
        try {
            String queryString1 = "INSERT INTO archive(archiveId,patientInformation)VALUES(?,?)";
            pstmt = conn.prepareStatement(queryString1);
            pstmt.setString(1, ""+n+"");
            pstmt.setString(2, ""+getArchive(n));
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    public int getPatientNumber(String fName, String lName, int dobIn)
    {
        int id=0;
        try {
            String sql1 = "SELECT patientNumber FROM Patient WHERE patientFName= '"+fName+"' AND patientLName= '"+lName+"' AND patientDOB= '"+dobIn+"'";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(sql1);
            if (rset.next()) {
                id = rset.getInt(1);
            }
        } catch (Exception se) {
            System.out.println(se);
        }
        return id;
    }
}
