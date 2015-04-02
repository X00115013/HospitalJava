package DataBase;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;

/**
 * Created by Thomas Murray on 08/03/2015.
 */
public class TimeTableOperations {
    private Connection conn;
    private ResultSet rset;
    private PreparedStatement pstmt;
    private Statement stmt;
    private Connect connect= new Connect();

    public TimeTableOperations(){
    conn = connect.openDB();
    }
    public ResultSet getConsultantTT() {
        try {
            String queryString = "SELECT * FROM consultantTimeTable ORDER BY time";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;
    }
    public ResultSet getXRayTT() {
        try {
            String queryString = "SELECT * FROM XRayTimeTable ORDER BY time";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;
    }
    public ResultSet getCTScanTT() {
        try {
            String queryString = "SELECT * FROM CTScanTimeTable ORDER BY time";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;
    }
    public ResultSet getMRIScanTT() {
        try {
            String queryString = "SELECT * FROM MRITimeTable ORDER BY time";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;
    }


    public void setXRayTimeTable(int timeIn, String taken, int consultantNumIn ){
        try {
            String queryString1 = "INSERT INTO XRayTimeTable(xRay_ID, time, taken,con_Name,AppID)VALUES(xRay_seq.nextval,?,?,?,?)";
            pstmt = conn.prepareStatement(queryString1);
            pstmt.setInt(1,timeIn-1);
            pstmt.setString(2, "taken");
            pstmt.setString(3,getConsultantName(consultantNumIn));
            pstmt.setInt(4,getCurrVal());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void setMRITimeTable(int timeIn, String taken,int consultantNumIn){
        try {
            String queryString1 = "INSERT INTO MRITimeTable(mRI_ID, time,taken, con_Name,AppID)VALUES(mRI_seq.nextval,?,?,?,?)";
            pstmt = conn.prepareStatement(queryString1);
            pstmt.setInt(1,timeIn);
            pstmt.setString(2,taken);
            pstmt.setString(3, getConsultantName(consultantNumIn));
            pstmt.setInt(4,getCurrVal());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void setCTTimeTable(int timeIn, String taken, int consultantNumber) {
        try {
            String queryString1 = "INSERT INTO CTScanTimeTable(cT_ID, time,taken, con_Name,AppID)VALUES(cT_seq.nextval,?,?,?,?)";
            pstmt = conn.prepareStatement(queryString1);
            pstmt.setInt(1, timeIn);
            pstmt.setString(2, taken);
            pstmt.setString(3, getConsultantName(consultantNumber));
            pstmt.setInt(4,getCurrVal());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void setConsultantTimeTable(int timeIn, String consultantNumIn){
        try {
            String queryString1 = "INSERT INTO ConsultantTimeTable(conTT_ID,time,con_Name,AppID)VALUES(conTT_seq.nextval,?,?,?)";
            pstmt = conn.prepareStatement(queryString1);
            pstmt.setInt(1,timeIn);
            pstmt.setString(2, consultantNumIn);
            pstmt.setInt(3,getCurrVal());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getCurrVal(){
        int catchInt=0;
        try {
            String queryString = "select max(appID) from appointment";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
            while(rset.next()) {
                catchInt = rset.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return catchInt;
    }




    public ResultSet getConsultant(){
        try {
            String queryString = "SELECT * FROM Consultant";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;
    }

    public String getConsultantName(int iDIn){
        String temp="";
        try {
            String queryString = "SELECT * FROM Consultant WHERE con_ID = "+iDIn;
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
            while (rset.next()){
                temp=rset.getString(2);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return temp;
    }


    public void setXRayFree(String taken){
        try {
            String queryString1 = "INSERT INTO XRayTimeTable(xRay_ID,taken)VALUES(xRay_seq.nextval,?)";
            pstmt = conn.prepareStatement(queryString1);
            pstmt.setString(1,taken);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void setMRIFree(String taken){
        try {
            String queryString1 = "INSERT INTO MRITimeTable(mRI_ID,taken)VALUES(mRI_seq.nextval,?)";
            pstmt = conn.prepareStatement(queryString1);
            pstmt.setString(1,taken);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void setCTFree(String taken){
        try {
            String queryString1 = "INSERT INTO CTScanTimeTable(cT_ID,taken)VALUES(cT_seq.nextval,?)";
            pstmt = conn.prepareStatement(queryString1);
            pstmt.setString(1,taken);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void cancelXRayTableEntry(int appNumIn){
        try {
            String queryString = "UPDATE XRayTimeTable SET taken = 'Free' WHERE AppID = "+appNumIn;
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void cancelMRITableEntry(int appNumIn){
        try {
            String queryString = "UPDATE MRITimeTable SET taken = 'Free' WHERE AppID = "+appNumIn;
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public void cancelCTTableEntry(int appNumIn){
        try {
            String queryString = "UPDATE CTScanTimeTable SET taken = 'Free' WHERE AppID = "+appNumIn;
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
