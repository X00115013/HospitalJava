package Model;
import DataBase.*;
import Model.*;

import javax.print.attribute.standard.DateTimeAtCreation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by David and Thomas Murray on 15/03/2015.
 */
public class Prescription {
    private int pNum,dose,paid,presNum;
    private String medName,conName,date;
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
    public Prescription(int presNumIn, int pNumberIn, String medNameIn, int dosageIn,String conNameIn, int paidForIn,String dateIn){
        presNum=presNumIn;
        pNum=pNumberIn;
        medName=medNameIn;
        dose=dosageIn;
        conName=conNameIn;
        paid=paidForIn;
        date=dateIn;
    }
    public void refreshArrays() {
        try {
            presList.removeAll(presList);
            so=new StockOperations();
            rset = so.getPrescription();
            while (rset.next()) {
                presList.add(new Prescription(rset.getInt(1),rset.getInt(2), rset.getString(3), rset.getInt(4),rset.getString(5),rset.getInt(6),rset.getString(7)));
            }so.stockOperationsClose();
        } catch (SQLException e1) {
            System.out.println(e1);
        }
    }
    public void addPrescription(int pNumIn,String medNameIn,int amount,String consulNameIn){
        so=new StockOperations();
        so.addPrescription(pNumIn, medNameIn, amount, consulNameIn,getCurrentTimeStamp());
        so.stockOperationsClose();
    }
    public void updatePaid(int id){
        so=new StockOperations();
        so.updatePrescriptionPaid(id);
        so.stockOperationsClose();
    }
    public void updateStock(String medNameIn,int amount){
        so=new StockOperations();
        so.updateMedicineStock(medNameIn,amount);
        so.stockOperationsClose();
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
    public String getDate() {
        return date;
    }
    public ArrayList getPresList() {
        refreshArrays();
        return presList;
    }
    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("  HH:mm:ss   dd-MM-yyyy");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}


