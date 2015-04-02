package Model;
import DataBase.*;
import Model.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by David and Thomas Murray on 15/03/2015.
 */
public class Prescription {
    private int pNum,dose,paid;
    private String medName;
    private ResultSet rset;
    private ArrayList<Prescription> presList = new ArrayList<>();
    private StockOperations so;

    public Prescription(int pNumberIn, String medNameIn, int dosageIn,int paidForIn){
        pNum=pNumberIn;
        medName=medNameIn;
        dose=dosageIn;
        paid=paidForIn;
    }

    public void refreshArrays() {
        try {
            so=new StockOperations();
            rset = so.getPrescription();
            while (rset.next()) {
                presList.add(new Prescription(rset.getInt(1), rset.getString(2), rset.getInt(3),rset.getInt(4)));
            }so.stockOperationsClose();
        } catch (SQLException e1) {
            System.out.println(e1);
        }
    }

    public void addPrescription(int pNumIn,String medNameIn,int amount){
        so.addPrescription(pNumIn, medNameIn, amount);
    }

    public void updatePaid(int id){
        so.updatePrescriptionPaid(id);
    }

    public int getpNum() {
        return pNum;
    }

    public int getDose() {
        return dose;
    }

    public int getPaid() {
        return paid;
    }

    public String getMedName() {
        return medName;
    }

    public ArrayList<Prescription> getPresList() {
        refreshArrays();
        return presList;
    }
}


