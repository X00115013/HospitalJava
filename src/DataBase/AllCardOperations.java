package DataBase;

import java.sql.*;

/**
 * Created by David on 17/03/2015.
 */
public class AllCardOperations {
    private Connection conn;
    private ResultSet rset;
    private PreparedStatement pstmt;
    private Statement stmt;
    private CreateTables connect= new CreateTables();

    public AllCardOperations() {conn = connect.openDB();
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
}
