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
    private Connect connect= new Connect();

    public ReferralOperations() {conn = connect.openDB();
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
                            int medicalRequiredIn, int consultantRequiredIn,int checked,String genderIn){

        try {
            String queryString1 = "INSERT INTO Referral(Reference,GPNumber,GPName,GPLocation,Patient_FName," +
                    "Patient_LName,Patient_DOB,Patient_Address,Patient_Phone,ReasonVisit,Recommendation,Med_Equip_Needed," +
                    "Consultant_Type,Checked,gender)VALUES(GPID.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(queryString1);
            pstmt.setInt(1,checked);
            pstmt.setString(2,gpNameIn);
            pstmt.setString(3,gpLocationIn);
            pstmt.setString(4,patientFNameIn);
            pstmt.setString(5,patientSurnameIn );
            pstmt.setString(6,dobIn);
            pstmt.setString(7,patientAddressIn );
            pstmt.setString(8,patientPhoneIn);
            pstmt.setString(9, reasonForVisitIn);
            pstmt.setString(10,recommendationsIn);
            pstmt.setInt(11, medicalRequiredIn);
            pstmt.setInt(12,consultantRequiredIn);
            pstmt.setInt(13,checked);
            pstmt.setString(14,genderIn);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
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
