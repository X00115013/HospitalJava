package DataBase;

import Model.Prescription;

import javax.swing.*;
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

    public void deleteMedicine(int medIDIn){
        try {
            String queryString = "DELETE FROM Medicine WHERE Med_ID= "+medIDIn;
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
            JOptionPane.showMessageDialog(null, "Medicine number "+medIDIn+" has been Deleted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addMedicine(String nameIn,int amount,double price) {
        try {
            String sqlQuery = "INSERT INTO Medicine(Med_ID , Med_Name , StockLevel , price ) "+ "values(MedID.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1,nameIn);
            pstmt.setInt(2, amount);
            pstmt.setDouble(3,price);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "New medicine has been added");
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

    public ResultSet getBill(int patient_Num) {
        try {
            String queryString = "SELECT * FROM Bill WHERE patient_Num= "+patient_Num;
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;

    }

    public void storeBill(int patientNumIn,String oldBill, String dateIn) {
        try {
            String sqlQuery = "INSERT INTO Bill(bill_Number ,bill ,datePaid, patient_Num ) values(bill_seq.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, oldBill);
            pstmt.setString(2,dateIn);
            pstmt.setInt(3,patientNumIn);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Patient "+patientNumIn+"'s Bill has been Paid");
        } catch (Exception se) {
            System.out.println("Error is here in bill "+se);
        }
    }

    public void updateAccounts(int accountID,double total)
    {
        try {
            String med = "UPDATE Accounts SET runningTotal= runningTotal + " +total +" where accountID=" +accountID;
            stmt = conn.createStatement();
            stmt.executeUpdate(med);
        } catch (Exception se) {
            System.out.println(se);
        }
    }

    public ResultSet getAccounts() {
        try {
            String queryString = "SELECT * FROM Accounts";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;

    }


    public void storeAccounts(double medDeposit, double equipDeposit, int patientNumIn) {
        try {
            String sqlQuery = "INSERT INTO Accounts(accountID, medDeposit, equipDeposit ,dateIn , patient_Num ) values(account_seq.nextVal,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setDouble(1, medDeposit);
            pstmt.setDouble(2, equipDeposit);
            pstmt.setString(3, Prescription.getCurrentTimeStamp());
            pstmt.setInt(4, patientNumIn);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Hospital Accounts Updated");
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

    public void deleteEquipment(int eqIDIn){
        try {
            String queryString = "DELETE FROM MedicalEquipment WHERE Equipment_ID= "+eqIDIn;
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
            JOptionPane.showMessageDialog(null, "Equipment number "+eqIDIn+" has been Deleted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addEquipment(String nameIn,double price) {
        try {
            String sqlQuery = "INSERT INTO MedicalEquipment(Equipment_ID , Equipment_name, price ) "+ "values(EquipID.nextVal,?,?)";
            pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1,nameIn);
            pstmt.setDouble(2,price);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "New Equipment has been added");
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

    public void addPrescription(int pNumIn,String medNameIn,int amount,String conManeIn,String dateIn) {
        try {
            String sqlQuery = "INSERT INTO Prescriptions(PrescriptionID, Pat_NumIn, Drug_name , Med_Amount,conName, This_Visit,dateIn) "+ "values(prescription_seq.nextVal,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setInt(1,pNumIn);
            pstmt.setString(2, medNameIn);
            pstmt.setInt(3, amount);
            pstmt.setString(4,conManeIn);
            pstmt.setInt(5,1);
            pstmt.setString(6,dateIn);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "New Prescription added for patient "+pNumIn);
        } catch (Exception se) {
            System.out.println(se);
        }
    }

    public void deleteConsultant(int conIDIn){
        try {
            String queryString = "DELETE FROM Consultant WHERE con_ID= "+conIDIn;
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
            JOptionPane.showMessageDialog(null, "Consultant Number "+conIDIn+" has been Deleted");
        } catch (Exception e) {
            System.out.println(e);
        }
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

    public void addConsultant(String conNameIn,String conSpecialIn,String equip) {
        try {
            String sqlQuery = "INSERT INTO Consultant(con_ID , con_Name, speciality,machineSkill) "+ "values(conID.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, conNameIn );
            pstmt.setString(2, conSpecialIn);
            pstmt.setString(3,equip);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "New Consultant has been added");
        } catch (Exception se) {
            System.out.println(se);
        }
    }

    public ResultSet getEquipmentUsed(int patientNumIn){
        try {
            String queryString = "SELECT * FROM EquipmentUsed WHERE patient_NumIn = "+patientNumIn+"";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;
    }

    public ResultSet getEquipmentUsedC(){
        try {
            String queryString = "SELECT * FROM EquipmentUsed";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rset;
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


    public void updateMedicineStock(String medName,int amount)
    {
        try {
            String med = "UPDATE Medicine SET StockLevel = StockLevel - " + amount +" WHERE Med_Name= '" +medName+"'";
            stmt = conn.createStatement();
            stmt.executeUpdate(med);
        } catch (Exception se) {
            System.out.println(se);
        }
    }


    public void updateEquipmentPaid(int patNumber)
    {
        try {
            String eq = "UPDATE EquipmentUsed SET this_Visit= " + 2 +" where patient_NumIn=" +patNumber;
            stmt = conn.createStatement();
            stmt.executeUpdate(eq);
        } catch (Exception se) {
            System.out.println(se);
        }
    }

    public void addEquipUsed(int patientNumIn, String medNameIn) {
        try {
            String sqlQuery = "INSERT INTO EquipmentUsed(machineID,patient_NumIn, machine_name , this_Visit) "+ "values(machine_seq.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setInt(1, patientNumIn);
            pstmt.setString(2, medNameIn);
            pstmt.setInt(3, 1);
            pstmt.executeUpdate();
//            JOptionPane.showMessageDialog(null, "New Equipment added for patient "+patientNumIn);
        } catch (Exception se) {
            System.out.println("Error from Here addEuipUsed"+ se);
        }
    }



}
