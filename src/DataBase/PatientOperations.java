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

    public PatientOperations() {conn = openDB();
    }

    // This method opens a connection to the Oracle database
    public Connection openDB() {
        try {
            OracleDataSource ods = new OracleDataSource();

//			// Tallaght
//			ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
//            String uName = "X00115013";
//            ods.setUser(uName);
//            String uPass = "db02Dec77";
//            ods.setPassword(uPass);

//			Home Oracle XE
            ods.setURL("jdbc:oracle:thin:hr/hr@localhost:1521/XE");
            String uName = "SYSTEM";
            ods.setUser(uName);
            String uPass = "30905149";
            ods.setPassword(uPass);

            conn = ods.getConnection();
            System.out.println(" Patient connected.");
        } catch (Exception e) {
            System.out.print("Unable to load driver " + e);
            System.exit(1);
        }
        return conn;
    }

    // This method closes the connection to the Oracle database
    public void closeDB() {
        try {
            conn.close();
            System.out.print("Connection closed");
        } catch (SQLException e) {
            System.out.print("Could not close connection ");
            e.printStackTrace();
        }
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



//
//    public void addPatient(PatientAdmin pa) {
//        try {
//            String sql2 = "INSERT INTO Patient(patientFName, patientLName, patientDOB, patientGender, occupation," +
//                    " patientPhone) "+ "values(patientNum_seq.nextVal,?,?,?,?,?,?)";
//            pstmt = conn.prepareStatement(sql2);
//            pstmt.setString(1, pa.getPatientFName());
//            pstmt.setString(2, pa.getPatientLName());
//            pstmt.setString(3,pa.getPatientDOB());
//            pstmt.setString(4,pa.getPatientGender());
//            pstmt.setString(5,pa.getPatientPhone());
//            pstmt.setString(6,pa.getPatientEmail());
//            pstmt.executeUpdate();
//        } catch (Exception se) {
//            System.out.println(se);
//        }
//    }
//






    public void updatePatientAdmin(int patientNumber, String newPFname,String newPLname, String newPaddress, String newPphone, String newEmail)
    {
        try {
            String sql1 = "UPDATE Patient SET patientFName= '" + newPFname +"',patientLName= '" + newPLname + "', " +
                    "patientAddress= '"+newPaddress+ "', patientPhone= '"+newPaddress+"', patientEmail= " +
                    "'"+newEmail+", where patientNumber=" +patientNumber+"'";
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
