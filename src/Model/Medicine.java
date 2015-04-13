package Model;

import DataBase.PatientOperations;
import DataBase.StockOperations;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by David and Thomas Murray on 15/03/2015.
 */
public class Medicine {
    private int medId,dosage,stockLevel;
    private String medName;
    private double price;
    private ResultSet rset;
    private ArrayList<Medicine>medicines=new ArrayList<>();
    StockOperations so;


    public Medicine(){

    }

    public Medicine(String med_Name, int stockLevelIn,double priceIn) {
        so=new StockOperations();
        medName=med_Name;
        stockLevel=stockLevelIn;
        price=priceIn;
        addMedicine(med_Name,stockLevelIn,priceIn);
    }

    public Medicine(int med_ID,String med_Name, int stockLevelIn,double priceIn) {
        medId=med_ID;
        medName=med_Name;
        stockLevel=stockLevelIn;
//        if(stockLevel<=10){
//            JOptionPane.showMessageDialog(null, med_Name+" is low on stock, "+stockLevel+" remain");
//        }else if (stockLevel <=0){
//            JOptionPane.showMessageDialog(null, med_Name+" is out on stock");
//        }
        price=priceIn;
    }

    public void refreshArrays() {
        try {

            so=new StockOperations();
            rset = so.getMedicine();
            while (rset.next()) {
                medicines.add(new Medicine(rset.getInt(1),rset.getString(2),rset.getInt(3),rset.getDouble(4)));
            }so.stockOperationsClose();
        } catch (SQLException e1) {
            System.out.println(e1);
        }
    }

    public void addMedicine(String nameIn,int amount,double price){
        so.addMedicine(nameIn,amount,price);
        so.stockOperationsClose();
    }

    public void updateStock(int id,int stockIn){
        so=new StockOperations();
        so.updateMedStock(id,stockIn);
        so.stockOperationsClose();
    }

    public void deleteMedicine(int medIdIn){
        so=new StockOperations();
        so.deleteMedicine(medIdIn);
        so.stockOperationsClose();
    }

    public ArrayList getMedicines() {
        medicines.removeAll(medicines);
        refreshArrays();
        return medicines;
    }

    public double getPrice() {
        return price;
    }

    public String getMedName() {
        return medName;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public int getMedId() {
        return medId;
    }
}
