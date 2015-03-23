package Model;
import DataBase.*;
import Model.*;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by David on 15/03/2015.
 */
public class Prescription {
    private double PresDosage;
    private ResultSet rset;
    private ArrayList<Medicine> medList = new ArrayList<Medicine>();
    // private "INSERT OPERATION" op; NOT YET CREATED

    public Prescription(double dosage){
        PresDosage = dosage;
        medList = new ArrayList<Medicine>();
    }
// GETTER & SETTER FOR DOSAGE
    public double getPresDosage() {
        return PresDosage;
    }

    public void setPresDosage(double presDosage) {
        PresDosage = presDosage;
    }

    public Medicine getMed(int i){
        return medList.get(i);
    }

    public int getNumMed(){
        return medList.size();
    }

  /* public void refreshMedList(){
        System.out.println();
        rset = INSERT OPERATION"
        if(medList.size() > 0){  THIS WILL DELETE ALL IN MEDLIST
            for (int i = medList.size() -1; i>=0; i--){
                medList.remove(i);
            }
        }
        try{ //THIS WILL ADD IT BACK IN WITH ANY UPDATES
            while (rset.next()){
                System.out.println("Refresh Method Prescription");
                Medicine med = new Medicine(rset.getInt(1),rset.getString(2));
                medList.add(med);
            }
        }catch (Exception e) {
            System.out.println(e);
        }

    }*/
}


