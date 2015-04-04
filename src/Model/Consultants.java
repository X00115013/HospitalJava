package Model;

import DataBase.StockOperations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Roland on 02/04/2015.
 */
public class Consultants {
    private int conId;
    private String conName, conSpeciality,equipSill;
    private ResultSet rset;
    private ArrayList<Consultants> consultants = new ArrayList<>();
    StockOperations so;


    public Consultants() {

    }

    public Consultants(String con_Name, String specialityIn,String equipSkillIn) {
        conName = con_Name;
        conSpeciality = specialityIn;
        equipSill=equipSkillIn;
    }


    public Consultants(int con_ID, String con_Name, String specialityIn,String equipSkillIn) {
        conId = con_ID;
        conName = con_Name;
        conSpeciality = specialityIn;
        equipSill=equipSkillIn;
    }

    public void refreshArrays() {
        try {
            clearArray();
            so = new StockOperations();
            rset = so.getConsultant();
            while (rset.next()) {
                consultants.add(new Consultants(rset.getInt(1), rset.getString(2), rset.getString(3),rset.getString(4)));
            }
            so.stockOperationsClose();
        } catch (SQLException e1) {
            System.out.println(e1);
        }
    }

    public void addConsultant(String nameIn, String conSpecialityIn,int equipSkill) {
        so.addConsultant(nameIn, conSpecialityIn,equipSkill);
    }

    public void deleteMedicine(int conIdIn) {
        so.deleteConsultant(conIdIn);
    }

    public ArrayList<Consultants> getConsultants() {
        refreshArrays();
        return consultants;
    }

    public String getEquipSill() {
        return equipSill;
    }

    public int getConId() {
        return conId;
    }

    public String getConName() {
        return conName;
    }

    public String getConSpeciality() {
        return conSpeciality;
    }
    public void clearArray(){
        for (int i = 0; i < consultants.size(); i++) {
            consultants.remove(i);

        }
    }
}
