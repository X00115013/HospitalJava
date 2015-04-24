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
    public static int clearInt;

    public TimeTableOperations(){
    conn = connect.openDB();
    }

    public void TimeTableOperationsClose(){
        connect.closeDB();
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
    public ResultSet getTT(String machineIn) {
        try {
            String queryString = "SELECT * FROM TimeTable WHERE machineName ='"+machineIn+"' ORDER BY time";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println("getTT error "+e);
        }
        return rset;
    }

    public ResultSet getTT() {
        try {
            String queryString = "SELECT * FROM TimeTable";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println("getTT error "+e);
        }
        return rset;
    }


    public void setTimeTable(String machineNameIn,int timeIn,  String taken, String consultantNameIn ){
        try {
            String queryString1 = "INSERT INTO TimeTable(tt_ID, machineName, time, taken,con_Name,AppID)VALUES(tt_seq.nextval,?,?,?,?,?)";
            pstmt = conn.prepareStatement(queryString1);
            pstmt.setString(1, machineNameIn);
            pstmt.setInt(2, timeIn);
            pstmt.setString(3, taken);
            pstmt.setString(4,consultantNameIn);
            pstmt.setInt(5,getCurrVal());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Insert tt error "+e);
        }
    }

    public void updateTimeTable(){
        for (int i = 0; i <42 ; i++) {
            cancelTableEntry(clearInt);
            clearInt++;
        }
    }


    public void cancelTableEntry(int appNumIn){
        try {
            String queryString = "UPDATE TimeTable SET time = null, con_Name = '', taken = 'Free' WHERE AppID = "+appNumIn;
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void setTTFree(String equipIn,String taken){
        try {
            String queryString1 = "INSERT INTO TimeTable(tt_ID,machineName,taken,con_Name)VALUES(tt_seq.nextval,?,?,?)";
            pstmt = conn.prepareStatement(queryString1);
            pstmt.setString(1,equipIn);
            pstmt.setString(2,taken);
            pstmt.setString(3,"  ");
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

    public int getCurrVal() {
        int catchInt = 0;
        try {
            String queryString = "select max(appID) from appointment";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
            while (rset.next()) {
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
}

