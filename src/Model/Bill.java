package Model;
import DataBase.*;

import java.util.ArrayList;
import java.util.Scanner;
import Model.*;

/**
 * Created by David on 04/03/2015.
 */
public class Bill {
//    private final double VAT = 1.21;
//    private String type;
//    private double equipCost,medCost=0,cost, amount;
//    private int paid = 0, typeH=0;  // typeH Is THE TYPE OF HEALTH INSURANCE
//    private ArrayList<Equipment> equipment = new ArrayList<Equipment>();
//    private ArrayList<Medicine> medicine = new ArrayList<Medicine>();
//    private Equipment e = new Equipment();
//    private Medicine m = new Medicine();
//
//
//    public Bill(){}
//
////    public void fillEquipCost(Equipment equip){
////        equipment.add(equip);
////    }
//
//    // filling Epuipment for cost per piece of equipment NOT WORKING may be because nothing has been added
////   public void equipmentCost(){
////       double ecost=0;
////       for (int i = 0; i < equipment.size(); i++){
////           ecost = equipment.get(i).getEquipCost1()+equipment.get(i).getEquipCost2()+equipment.get(i).getEquipCost3();
////      }
////       equipCost = ecost;
////      // return equipCost;
////   }
//
//
////public void print(){
////    for (int i = 0; i< equipment.size(); i++){
////        System.out.println(equipment.get(i).getEquipCost1());
////    }
////}
//    public void calcEquipCost(){
//        equipCost = e.getEquipCost1()+e.getEquipCost2()+e.getEquipCost3();
//        //return equipCost;
//    }
//
//    public void calcMedCost(){
//        medCost = m.getMedCost1()+m.getMedCost2()+m.getMedCost3();
//    }
//    public void calBill(String type){
//        if(type.equalsIgnoreCase("MedCard")){
//            amount = 0;
//            paid=1;
//
//        }
//        else if (type.equalsIgnoreCase("CreditCard")|| type.equalsIgnoreCase("DebitCard")){
//            amount = (equipCost + medCost)*VAT;
//            //amount = (e.getEquipCost1()+medCost)*VAT;
//            paid=1;
//        }
//        else if (type.equalsIgnoreCase("Health")){
//            if (typeH == 1){
//                double discount = 0.25;
//                cost = equipCost + medCost;
//                amount = cost -(cost*discount);
//                amount*= VAT;
//                paid=1;
//            }
//            else if (typeH == 2){
//                double discount = 0.50;
//                cost = equipCost + medCost;
//                amount = cost -(cost*discount);
//                amount*= VAT;
//                paid=1;
//            }
//            else if (typeH == 3){
//                double discount = 0.75;
//                cost = equipCost + medCost;
//                amount = cost -(cost*discount);
//                amount*= VAT;
//                paid=1;
//            }
//        }
//    }
//
//    public double getAmount() {
//        return amount;
//    }
//
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int to;
//        String tt;
//        Bill t = new Bill();
//
//        System.out.println("enter the payment type");
//        t.type = in.next();
//       // t.setType(tt);
//        if(t.type.equalsIgnoreCase("Health")){
//            System.out.println("enter the type of hI");
//            //to = in.nextInt();
//            t.typeH= in.nextInt();
//        }
//
//        t.calcEquipCost();
//        t.calcMedCost();
//        t.calBill(t.type);
//        System.out.printf("Test € %.2f%n ", t.getAmount());
//
//    }
}

