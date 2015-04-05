package Referrals;

import DataBase.Connect;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;

/**
 * Created by Thomas Murray on 08/03/2015.
 */
public class ReferralOperations {
    private Connection conn;
    private ResultSet rset;
    private PreparedStatement pstmt;
    private Statement stmt;
    private Connect connect;

    public ReferralOperations() {
        connect= new Connect();
        conn = connect.openDB();
    }

    public void referralOperationsClose(){
        connect.closeDB();
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
            String queryString = "SELECT * FROM Referral";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;
    }

    public void setReferral(int gpNumIn,String gpNameIn, String gpLocationIn, String patientFNameIn, String patientSurnameIn,
                            String patientAddressIn, String dobIn,
                            String patientPhoneIn, String reasonForVisitIn,String recommendationsIn,
                            String medicalRequiredIn, String consultantRequiredIn,int checked,String genderIn){

        try {
            String queryString1 = "INSERT INTO Referral(Reference,GPNumber,GPName,GPLocation,Patient_FName," +
                    "Patient_LName,Patient_DOB,Patient_Address,Patient_Phone,ReasonVisit,Recommendation,Med_Equip_Needed," +
                    "Consultant_Type,Checked,gender)VALUES(GPID.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(queryString1);
            pstmt.setInt(1,gpNumIn);
            System.out.println("1");
            pstmt.setString(2,gpNameIn);
            System.out.println("2");
            pstmt.setString(3,gpLocationIn);
            System.out.println("3");
            pstmt.setString(4,patientFNameIn);
            System.out.println("4");
            pstmt.setString(5,patientSurnameIn );
            System.out.println("5");
            pstmt.setString(6,dobIn);
            System.out.println("6");
            pstmt.setString(7,patientAddressIn );
            System.out.println("7");
            pstmt.setString(8,patientPhoneIn);
            System.out.println("8");
            pstmt.setString(9, reasonForVisitIn);
            System.out.println("9");
            pstmt.setString(10,recommendationsIn);
            System.out.println("10");
            pstmt.setString(11, medicalRequiredIn);
            System.out.println("11");
            pstmt.setString(12, consultantRequiredIn);
            System.out.println("12");
            pstmt.setInt(13,checked);
            System.out.println("13");
            pstmt.setString(14,genderIn);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Here in lies the Rub"+e);
        }
    }
    public void setChecked(int refNumIn){
        try {
            String queryString = "UPDATE Referral SET checked = 0 WHERE Reference = "+refNumIn;
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
