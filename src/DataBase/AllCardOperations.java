package DataBase;

import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.*;

/**
 * Created by David Kiernan and Thomas Murray on 17/03/2015.
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
            JOptionPane.showMessageDialog(null, "Your Bank Card Details have been entered");
        } catch (Exception se) {
            System.out.println(se);
        }
    }

    public void updateCard(int patientNumber,int cardNum, String type, int code,String name, String date  )
    {
        try {
            String sql1 = "UPDATE  CardDetails SET cardNumber= " +cardNum+ "," +
                    "cardType= '"+type+ "'," +
                    " SecurityCode= "+code+"," +
                    " ExpiryDate= '"+date+"'," +
                    " CardHolder= '"+name+"'" +
                    " where patient_Num =" +patientNumber;
            stmt = conn.createStatement();
            stmt.executeUpdate(sql1);
            JOptionPane.showMessageDialog(null, "Your Bank Card Details have been Updated");
        } catch (Exception se) {
            System.out.println("The error might be here ac update card "+se);
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
            JOptionPane.showMessageDialog(null, "Your Medical Card details have been added");
        } catch (Exception se) {
            System.out.println(se);
        }
    }

    public void updateMedCard(int patientNumber,int GMS, int PPS, String gender, String validTO,String name )
    {
        try {
            String sql1 = "UPDATE MedicalCard SET GMSNumber= "+GMS+"," +
                    "PPSN= " +PPS+ "," +
                    "Gender= '"+gender+ "'," +
                    " ValidTo= '"+validTO+"'," +
                    " HolderName= '"+name+"'" +
                    " where patient_Num=" +patientNumber;
            stmt = conn.createStatement();
            stmt.executeUpdate(sql1);
            JOptionPane.showMessageDialog(null, "Your Medical Card details have been Update");
        } catch (Exception se) {
            System.out.println("The error might be here ac update medical card "+se);
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
            JOptionPane.showMessageDialog(null, "Your Insurance Details have been added");
        } catch (Exception se) {
            System.out.println(se);
        }
    }

    public void updateInsurance(int patientNumber,int pol_num, String company, String coverType, String expDate)
    {
        try {
            String sql1 = "UPDATE Patient SET Policy_number= "+pol_num+"," +
                    "Company_name= '" +company+ "'," +
                    "Coverage_type= '"+coverType+ "'," +
                    " Expiry_date= '"+expDate+"'" +
                    " where patient_Num=" +patientNumber;
            stmt = conn.createStatement();
            stmt.executeUpdate(sql1);
            JOptionPane.showMessageDialog(null, "Your Insurance Details have been Updated");
        } catch (Exception se) {
            System.out.println("The error might be here ac update insurance "+se);
        }
    }



}
