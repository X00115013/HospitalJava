package DataBase;

import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;

/**
 * Created by David and Thomas Murray on 17/03/2015.
 */
public class AllCardOperations {
    private Connection conn;
    private ResultSet rset;
    private PreparedStatement pstmt;
    private Statement stmt;
    private Connect connect= new Connect();

    public AllCardOperations() {
        conn = connect.openDB();
    }

    public void allCardOperationsClose(){
       connect.closeDB();
    }

    public ResultSet getCardDetails() {
        try {
            String queryString = "SELECT * FROM CardDetails";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;
    }


    public void addCard(int patientNumIn, int cardNum, String cardTypeIn,int secCode, String holderIn, String expDate) {
        try {
            String sqlQuery = "INSERT INTO CardDetails(Card_ID, patient_Num, cardNumber, cardType,SecurityCode ,CardHolder,ExpiryDate) values(CardID.nextVal,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setInt(1, patientNumIn);
            pstmt.setInt(2, cardNum);
            pstmt.setString(3,cardTypeIn);
            pstmt.setInt(4,secCode);
            pstmt.setString(5,holderIn);
            pstmt.setString(6,expDate);
            pstmt.executeUpdate();
        } catch (Exception se) {
            System.out.println(se);
        }
    }


    public ResultSet getMedCardDetails() {
        try {
            String queryString = "SELECT * FROM MedicalCard";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;
    }


    public void addMedCard(int patientNumIn, int gmsNum, int pps,String gender,String validTO, String holderIn) {
        try {
            String sqlQuery = "INSERT INTO MedicalCard(MedCard_id, patient_Num,GMSNumber, PPSN, Gender ,ValidTo ,HolderName) values(GMSID.nextVal,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setInt(1,patientNumIn);
            pstmt.setInt(2, gmsNum);
            pstmt.setInt(3, pps);
            pstmt.setString(4, gender);
            pstmt.setString(5,validTO);
            pstmt.setString(6,holderIn);
            pstmt.executeUpdate();
        } catch (Exception se) {
            System.out.println(se);
        }
    }

    public ResultSet getInsuranceDetails() {
        try {
            String queryString = "SELECT * FROM HealthInsurance";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;
    }


    public void addInsurance(int patientNumIn, int polNum, String companyName,String polType,String expDate) {
        try {
            String sqlQuery = "INSERT INTO HealthInsurance(Insu_ID,patient_Num ,Policy_number, Company_name ,Coverage_type ,Expiry_date) values(PolicyID.nextVal,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setInt(1,patientNumIn);
            pstmt.setInt(2, polNum);
            pstmt.setString(3, companyName);
            pstmt.setString(4,polType);
            pstmt.setString(5,expDate);
            pstmt.executeUpdate();
        } catch (Exception se) {
            System.out.println(se);
        }
    }



}
