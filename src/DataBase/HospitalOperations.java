package DataBase;

import Model.Appointment;
import Model.Referrals;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;


public class HospitalOperations {
    private Connection conn;
    private ResultSet rset;
    private PreparedStatement pstmt;
    private Statement stmt;

    public HospitalOperations() {conn = openDB();
    }

    // This method opens a connection to the Oracle database
    public Connection openDB() {
        try {
            OracleDataSource ods = new OracleDataSource();

//			// Tallaght
//			ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
//            String uName = "X00115013";
//            ods.setUser(uName);
//            String uPass = "db02Dec77";
//            ods.setPassword(uPass);

//			Home Oracle XE
            ods.setURL("jdbc:oracle:thin:hr/hr@localhost:1521/XE");
            String uName = "SYSTEM";
            ods.setUser(uName);
            String uPass = "30905149";
            ods.setPassword(uPass);

            conn = ods.getConnection();
            System.out.println(" Hospital connected.");
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


    public ResultSet getLastRow(String tableIn) {
        String sqlStatement = "SELECT * FROM "+tableIn+" ORDER BY did";
        try {
            pstmt = conn.prepareStatement(sqlStatement,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rset = pstmt.executeQuery();
            rset.last();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        return rset;
    }



}


