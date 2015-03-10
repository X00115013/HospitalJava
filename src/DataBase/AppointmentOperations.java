package DataBase;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;
import Model.Appointment;

/**
 * Created by Roland on 08/03/2015.
 */
public class AppointmentOperations {
    private Connection conn;
    private ResultSet rset;
    private PreparedStatement pstmt;
    private Statement stmt;

    public AppointmentOperations() {conn = openDB();
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

        public void addAppointment(String recIn, int equipIn,int conIn) {
            try {
                String sql2 = "INSERT INTO Appointment(AppID, ReasonVisit ,requiredEquipment, requiredConsultant) values(APPID.nextVal,?,?,?)";
                pstmt = conn.prepareStatement(sql2);
                pstmt.setString(1, recIn);
                pstmt.setInt(2, equipIn);
                pstmt.setInt(3, conIn);
                pstmt.executeUpdate();
            } catch (Exception se) {
                System.out.println(se);
            }
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

