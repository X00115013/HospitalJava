package Model;

import DataBase.StockOperations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by David and Thomas Murray on 20/03/2015.
 */
public class Equipment {
    private int eqId;
    private String eqName;
    private double price;
    private ResultSet rset;
    private ArrayList<Equipment> equipments=new ArrayList<>();
    StockOperations so;


    public Equipment(){

    }

    public Equipment(String eq_Name,double priceIn) {
        so=new StockOperations();
        eqName=eq_Name;
        price=priceIn;
        addEquipment(eq_Name,priceIn);
    }


    public Equipment(int eq_ID,String eq_Name,double priceIn) {
        eqId=eq_ID;
        eqName=eq_Name;
        price=priceIn;
    }

    public void refreshArrays() {
        try {
            so=new StockOperations();
            rset = so.getEquipment();
            while (rset.next()) {
                equipments.add(new Equipment(rset.getInt(1), rset.getString(2),rset.getDouble(3)));
            }so.stockOperationsClose();
        } catch (SQLException e1) {
            System.out.println(e1);
        }
    }

    public void addEquipment(String nameIn,double price){
        so.addEquipment(nameIn, price);
        so.stockOperationsClose();
    }


    public void deleteEquipment(int eqIdIn){
        so.deleteEquipment(eqIdIn);
    }

    public int getEqId() {
        return eqId;
    }

    public String getEqName() {
        return eqName;
    }

    public double getPrice() {
        return price;
    }

    public ArrayList getEquipments() {
        refreshArrays();
        return equipments;
    }
}
