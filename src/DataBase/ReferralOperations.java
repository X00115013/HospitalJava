package DataBase;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;

/**
 * Created by Roland on 08/03/2015.
 */
public class ReferralOperations {
    private Connection conn;
    private ResultSet rset;
    private PreparedStatement pstmt;
    private Statement stmt;

    public ReferralOperations() {conn = openDB();
    }

    // This method opens a connection to the Oracle database
    public Connection openDB() {
        try {
            OracleDataSource ods = new OracleDataSource();

//			// Tallaght
//			ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
//			System.out.println("Enter your user name:");
//            String uName = "X00115013";
//            ods.setUser(uName);
//            System.out.println("Enter your password:");
//            String uPass = "db02Dec77";
//            ods.setPassword(uPass);

//			Home Oracle XE
            ods.setURL("jdbc:oracle:thin:hr/hr@localhost:1521/XE");
            String uName = "SYSTEM";
            ods.setUser(uName);
            String uPass = "30905149";
            ods.setPassword(uPass);

            conn = ods.getConnection();
            System.out.println("connected.");
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
    public ResultSet getReferralByID(int id) {
        try {
            String queryString = "SELECT * FROM referral where gpNumber = "+id;
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;
    }

    public ResultSet getReferral() {
        try {
            String queryString = "SELECT * FROM referral";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;
    }

    public void setReferral(String gpNameIn, String gpLocationIn, String patientFNameIn, String patientSurnameIn,
                            int patientNumberIn, String patientAddressIn, String dobIn,
                            String patientPhoneIn, String reasonForVisitIn,String recommendationsIn,
                            int medicalRequiredIn, int consultantRequiredIn,int checked,String genderIn){
        try {
            String queryString1 = "INSERT INTO Referral(GPNumber,GPName,GPLocation,Patient_FName," +
                    "Patient_LName,Patient_DOB,Patient_Address,Patient_Phone,ReasonVisit,Recommendation,Med_Equip_Needed," +
                    "Consultant_Type,Checked,gender,patient_Number)VALUES(GPID.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(queryString1);
            pstmt.setString(1,gpNameIn);
            pstmt.setString(2,gpLocationIn);
            pstmt.setString(3,patientFNameIn);
            pstmt.setString(4,patientSurnameIn );
            pstmt.setString(5,dobIn);
            pstmt.setString(6,patientAddressIn );
            pstmt.setString(7,patientPhoneIn);
            pstmt.setString(8, reasonForVisitIn);
            pstmt.setString(9,recommendationsIn);
            pstmt.setInt(10, medicalRequiredIn);
            pstmt.setInt(11,consultantRequiredIn);
            pstmt.setInt(12,checked);
            pstmt.setString(13,genderIn);
            pstmt.setInt(14,patientNumberIn );
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
