package DataBase;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;
import Model.Appointment;

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

    public ResultSet getAppointmentByNum(int id) {
        try {
            String queryString = "SELECT * FROM Appointment where patientNumber = "+id;
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;
    }



    public int getAppointmentNum(String pFNameIn ,String pLNameIn, String dobIn) {
        int num=0;
        try {
            String queryString = "SELECT AppID FROM Appointment where patient_Number= (" +
                    "SELECT patient_Number" +
                    "FROM Patient" +
                    "WHERE patientFName ='"+pFNameIn+"' AND patientLName = '"+pLNameIn+"' AND patientDOB= "+dobIn+")";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
            while (rset.next()){
                num= rset.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return num;
    }


    public void deleteAppointment(int n) {
        try {
            String cmd = "DELETE * FROM Appointment WHERE AppID =" + n;
            stmt = conn.createStatement();
            stmt.executeUpdate(cmd);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addAppointmentExisting(String recIn, int equipIn,int conIn,int patientNum) {
        try {
            String sql1 = "UPDATE Patient SET " +
                    "AppID= APPID.nextVal," +
                    "ReasonVisit= '" + recIn+ "'," +
                    "requiredEquipment= "+equipIn+ "," +
                    "requiredConsultant= "+conIn+ "" +
                    "where patientNum=" +patientNum;
            stmt = conn.createStatement();
            stmt.executeUpdate(sql1);
        } catch (Exception se) {
            System.out.println(se);
        }
    }

//    public void addAppointmentExisting(String recIn, int equipIn,int conIn,int patientNum) {
//        try {
//            String sql2 = "INSERT INTO Appointment(AppID, ReasonVisit ,requiredEquipment, requiredConsultant,patient_Number) values(APPID.nextVal,?,?,?,?)";
//            pstmt = conn.prepareStatement(sql2);
//            pstmt.setString(1, recIn);
//            pstmt.setInt(2, equipIn);
//            pstmt.setInt(3, conIn);
//            pstmt.setInt(4,getCurrVal());
//            pstmt.executeUpdate();
//        } catch (Exception se) {
//            System.out.println("addAppointment went wrong"+se);
//        }
//    }



        public void addAppointment(String recIn, int equipIn,int conIn) {
            try {
                String sql2 = "INSERT INTO Appointment(AppID, ReasonVisit ,requiredEquipment, requiredConsultant,patient_Number) values(APPID.nextVal,?,?,?,?)";
                pstmt = conn.prepareStatement(sql2);
                pstmt.setString(1, recIn);
                pstmt.setInt(2, equipIn);
                pstmt.setInt(3, conIn);
                pstmt.setInt(4,getCurrVal());
                pstmt.executeUpdate();
            } catch (Exception se) {
                System.out.println("addAppointment went wrong"+se);
            }
        }

    public int getCurrVal(){
        int catchInt=0;
        try {
            String queryString = "select max(patient_Number) from Patient";
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


//    public void addAppointment(Appointment ap) {
//        try {
//            String sql2 = "INSERT INTO Patient(AppID, AppTime ,AppDate ,ReasonVisit, requiredEquipment, requiredConsultant) values(APPID.nextVal,?,?,?,?,?)";
//            pstmt = conn.prepareStatement(sql2);
//            pstmt.setInt(1, ap.getTime());
//            pstmt.setString(2, ap.getDate());
//            pstmt.setString(3,ap.getReasonForVisit());
//            pstmt.setInt(4, ap.getMedicalEquip());
//            pstmt.setInt(5, ap.getConsultantType());
//            pstmt.executeUpdate();
//        } catch (Exception se) {
//            System.out.println(se);
//        }
//    }
}

