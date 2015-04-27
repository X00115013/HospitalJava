package Model;
import DataBase.*;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import Model.*;

/**
 * Created by Thomas Murray and David Kiernan  on 04/03/2015.
 */
public class Bill {
    private final double VAT = 0.27;
    private final double FLAT_CHARGE = 150.00;
    private String type,oldBill,datePaid;
    private double equipCost,medCost=0,cost, totalBeforeVAT=0.00,totalAfterVAT=0.00,totalVAT=0.00;
    private int paid = 0, typeH=0,patientNumberIn;
    private ArrayList<Equipment> equipment = new ArrayList<Equipment>();
    private ArrayList<Medicine> medicine = new ArrayList<Medicine>();
    private ArrayList<Prescription>presList=new ArrayList<>();
    private ArrayList<EquipmentUsed>eqUsedList=new ArrayList<>();
    private ArrayList<Bill>bills=new ArrayList<>();
    private Prescription prescription;
    private EquipmentUsed equipmentUsed;
    private Equipment e;
    private Medicine m;
    private ResultSet rset;
    private StockOperations so;
    public static DecimalFormat df=new DecimalFormat("0.00");

    public Bill(){

    }

    public Bill(int patientNumIn){
        patientNumberIn=patientNumIn;
        arrays();
        totalBeforeVAT=calcMedCost()+calcEquipCost()+FLAT_CHARGE;
        totalVAT=totalBeforeVAT*VAT;
        totalAfterVAT=totalBeforeVAT+totalVAT;

    }


    public Bill(String oldBillIn,String datePaidIn,int patientNumIn){
        oldBill=oldBillIn;
        datePaid=datePaidIn;
        patientNumberIn=patientNumIn;
    }


    public void getOldBill(int patientNumberIn) {
        try {
            bills.removeAll(bills);
            so = new StockOperations();
            rset = so.getBill(patientNumberIn);
            while (rset.next()) {
                bills.add(new Bill(rset.getString(2), rset.getString(3), rset.getInt(4)));
            }
            so.stockOperationsClose();
        } catch (SQLException e1) {
            System.out.println(e1);
        }
    }

    public void storeBill(int patientNumIn,String billIn){
        so=new StockOperations();
        so.storeBill(patientNumIn,billIn,Prescription.getCurrentTimeStamp());
        so.stockOperationsClose();

    }

    public double calcEquipCost(){
        double equipTotal=0.00;
        for (int i = 0; i <eqUsedList.size() ; i++) {
            if((patientNumberIn == eqUsedList.get(i).getpNum())&& (eqUsedList.get(i).getThisVisit()==1)){
                for (int j = 0; j < equipment.size(); j++) {
                    if(eqUsedList.get(i).getEqName().equals(equipment.get(j).getEqName())){
                        equipTotal+=equipment.get(j).getPrice();
                    }
                }
            }
        }return equipTotal;
    }


    public double calcMedCost(){
        double medTotal=0.00;
        for (int i = 0; i <presList.size() ; i++) {
            if((patientNumberIn == presList.get(i).getpNum())&& (presList.get(i).getPaid()==1)){
                for (int j = 0; j < medicine.size(); j++) {
                    if(presList.get(i).getMedName().equals(medicine.get(j).getMedName())){
                        medTotal+=medicine.get(j).getPrice()* presList.get(i).getDose();
                    }
                }
            }
        }return medTotal;
    }

    public void arrays(){
        presList.removeAll(presList);
        eqUsedList.removeAll(eqUsedList);
        equipment.removeAll(eqUsedList);
        medicine.removeAll(medicine);
        prescription=new Prescription();
        presList.addAll(prescription.getPresList());
        equipmentUsed=new EquipmentUsed(patientNumberIn);
        eqUsedList.addAll(equipmentUsed.getUsedList());
        e= new Equipment();
        equipment.addAll(e.getEquipments());
        m= new Medicine();
        medicine.addAll(m.getMedicines());
    }

    public double getTotalBeforeVAT() {
        return totalBeforeVAT;
    }

    public double getTotalAfterVAT() {
        return totalAfterVAT;
    }

    public double getTotalVAT() {
        return totalVAT;
    }

    public int getPatientNumberIn() {
        return patientNumberIn;
    }

    public int getPaid() {
        return paid;
    }

    public String getDatePaid() {
        return datePaid;
    }

    public String getOld() {
        return oldBill;
    }

    public double getFLAT_CHARGE() {
        return FLAT_CHARGE;
    }

    public double getVAT() {
        return VAT;
    }

    public String getType() {
        return type;
    }

    public double getEquipCost() {
        return equipCost;
    }

    public double getMedCost() {
        return medCost;
    }

    public double getCost() {
        return cost;
    }

    public int getTypeH() {
        return typeH;
    }
}


