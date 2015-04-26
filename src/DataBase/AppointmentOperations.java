package DataBase;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;
import Model.Appointment;

import javax.swing.*;

/**
 * Created by Thomas Murray on 08/03/2015.
 */
public class AppointmentOperations {
    private Connection conn;
    private ResultSet rset;
    private PreparedStatement pstmt;
    private Statement stmt;
    private Connect connect=new Connect();

    public AppointmentOperations() {
        conn = connect.openDB();
    }

    public void appointmentOperationsClose(){
        connect.closeDB();
    }


    public ResultSet getAppointment() {
        try {
            String queryString = "SELECT * FROM Appointment";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;
    }


    public int getAppointmentTime(int appID) {
        int num=0;
        try {
            String queryString = "SELECT time FROM TimeTable WHERE AppID = "+appID;
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
            while (rset.next()){
                num= rset.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Problem in get app time ao "+e);
        }
        return num;
    }

    public void deleteAppointment(int n) {
        try {
            String cmd = "DELETE FROM Appointment WHERE AppID =" + n;
            stmt = conn.createStatement();
            stmt.executeUpdate(cmd);
            updateTT(n);
            JOptionPane.showMessageDialog(null, "Appointment "+n+" has been deleted");
        } catch (Exception e) {
            System.out.println("Error deleting appointment"+e);
        }
    }


    public void updateTT(int n) {
        try {
            String cmd = "UPDATE TimeTable SET taken = 'Free'," +
                    "con_Name = 'null'" +
                    "WHERE AppID =" + n;
            stmt = conn.createStatement();
            stmt.executeUpdate(cmd);
            deletePatientAppointment(n);
        } catch (Exception e) {
            System.out.println("Error deleting TimeTable Entry "+e);
        }
    }
    public void deletePatientAppointment(int n)
    {
        try {
            String sql1 = "UPDATE Patient SET " +
                    " AppID = 0" +
                    "where AppID=" +n;
            stmt = conn.createStatement();
            stmt.executeUpdate(sql1);
        } catch (Exception se) {
            System.out.println(se);
        }
    }

    public void addAppointmentExisting(String recIn, String equipIn,String conIn,int patientNum,String dateIn) {
        try {

            String sql2 = "INSERT INTO Appointment(AppID, ReasonVisit ,requiredEquipment, requiredConsultant,dateIn) values(APPID.nextVal,?,?,?,?)";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, recIn);
            pstmt.setString(2, equipIn);
            pstmt.setString(3, conIn);
            pstmt.setString(4,dateIn);
            pstmt.executeUpdate();
            updatePatientAppointment(patientNum);
        } catch (Exception se) {
            System.out.println("This is the problem ao update "+se);
        }
    }

    public int getCurrVal(){
        int catchInt=0;
        try {
            String queryString = "select max(AppID) from Appointment";
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

    public void updatePatientAppointment(int patientNumber)
    {
        try {
            String sql1 = "UPDATE Patient SET " +
                    " AppID = "+getCurrVal()+"" +
                    "where patient_Number=" +patientNumber;
            stmt = conn.createStatement();
            stmt.executeUpdate(sql1);
        } catch (Exception se) {
            System.out.println(se);
        }
    }


}

