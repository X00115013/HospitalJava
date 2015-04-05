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
    private int pNum,dose,paid,presNum;
    private String medName,conName;
    private ResultSet rset;
    private ArrayList<Prescription> presList = new ArrayList<>();
    private StockOperations so;

    public  Prescription(){

    }

    public Prescription(int pNumberIn, String medNameIn, int dosageIn,String conNameIn){
        pNum=pNumberIn;
        medName=medNameIn;
        dose=dosageIn;
        conName=conNameIn;
        addPrescription(pNum,medName,dose,conName);
    }

    public Prescription(int presNumIn, int pNumberIn, String medNameIn, int dosageIn,String conNameIn, int paidForIn){
        presNum=presNumIn;
        pNum=pNumberIn;
        medName=medNameIn;
        dose=dosageIn;
        conName=conNameIn;
        paid=paidForIn;
    }

    public void refreshArrays() {
        try {
            so=new StockOperations();
            rset = so.getPrescription();
            while (rset.next()) {
                presList.add(new Prescription(rset.getInt(1),rset.getInt(2), rset.getString(3), rset.getInt(4),rset.getString(5),rset.getInt(6)));
            }so.stockOperationsClose();
        } catch (SQLException e1) {
            System.out.println(e1);
        }
    }

    public void addPrescription(int pNumIn,String medNameIn,int amount,String consulNameIn){
        so=new StockOperations();
        so.addPrescription(pNumIn, medNameIn, amount,consulNameIn);
        so.stockOperationsClose();
    }

    public void updatePaid(int id){
        so.updatePrescriptionPaid(id);
    }

    public String getConName() {
        return conName;
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
        presList.removeAll(presList);
        refreshArrays();
        return presList;
    }
}


