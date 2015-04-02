package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Thomas Murray on 02/04/2015.
 */
public class StockOperations {
    private Connection conn;
    private ResultSet rset;
    private PreparedStatement pstmt;
    private Statement stmt;
    private Connect connect= new Connect();

    public StockOperations() {
        conn = connect.openDB();
    }

    public void stockOperationsClose(){
        connect.closeDB();
    }

    public ResultSet getMedicine(){
        try {
            String queryString = "SELECT * FROM Medicine";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;
    }

    public ResultSet deleteMedicine(int medIDIn){
        try {
            String queryString = "DELETE * FROM Medicine WHERE Med_ID= "+medIDIn;
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;
    }

    public void addMedicine(String nameIn,int dosage,int amount,double price) {
        try {
            String sqlQuery = "INSERT INTO Medicine(Med_ID , Med_Name,Dosage , StockLevel , price ) "+ "values(MedID.nextVal,?,?,?,?)";
            pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1,nameIn);
            pstmt.setInt(2, dosage);
            pstmt.setInt(3, amount);
            pstmt.setDouble(4,price);
            pstmt.executeUpdate();
        } catch (Exception se) {
            System.out.println(se);
        }
    }


    public void updateMedStock(int medNumber,int newStock)
    {
        try {
            String med = "UPDATE Medicine SET StockLevel= " + newStock +" where Med_ID=" +medNumber;
            stmt = conn.createStatement();
            stmt.executeUpdate(med);
        } catch (Exception se) {
            System.out.println(se);
        }
    }


    public ResultSet getEquipment(){
        try {
            String queryString = "SELECT * FROM MedicalEquipment";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;
    }

    public ResultSet deleteEquipment(int eqIDIn){
        try {
            String queryString = "DELETE * FROM MedicalEquipment WHERE Equipment_ID= "+eqIDIn;
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;
    }

    public void addEquipment(String nameIn,double price) {
        try {
            String sqlQuery = "INSERT INTO MedicalEquipment(Equipment_ID , Equipment_name, price ) "+ "values(EquipID.nextVal,?,?)";
            pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1,nameIn);
            pstmt.setDouble(2,price);
            pstmt.executeUpdate();
        } catch (Exception se) {
            System.out.println(se);
        }
    }

    public ResultSet getPrescription(){
        try {
            String queryString = "SELECT * FROM Prescriptions";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;
    }

    public void addPrescription(int pNumIn,String medNameIn,int amount) {
        try {
            String sqlQuery = "INSERT INTO Prescriptions(PrescriptionID, Pat_NumIn, Drug_name , Med_Amount, This_Visit) "+ "values(prescription_seq.nextVal,?,?,?,?)";
            pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setInt(1,pNumIn);
            pstmt.setString(2,medNameIn );
            pstmt.setInt(3, amount);
            pstmt.setInt(4,1);
            pstmt.executeUpdate();
        } catch (Exception se) {
            System.out.println(se);
        }
    }


    public void updatePrescriptionPaid(int patNumber)
    {
        try {
            String med = "UPDATE Prescriptions SET This_Visit= " + 2 +" where Pat_NumIn=" +patNumber;
            stmt = conn.createStatement();
            stmt.executeUpdate(med);
        } catch (Exception se) {
            System.out.println(se);
        }
    }

}
