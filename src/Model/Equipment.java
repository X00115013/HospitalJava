package Model;

import DataBase.StockOperations;
import DataBase.TimeTableOperations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by David and Thomas Murray on 20/03/2015.
 */
public class Equipment {
    private int eqId;
    private String eqName,free="Free";
    private double price;
    private ResultSet rset;
    private ArrayList<Equipment> equipments=new ArrayList<>();
    private StockOperations so;
    private TimeTableOperations to;

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
            equipments.removeAll(equipments);
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
        to=new TimeTableOperations();
        for (int i = 0; i < 200; i++) {
            to.setTTFree(nameIn, free);
            System.out.println(i+" TT is free");
        }to.TimeTableOperationsClose();
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
