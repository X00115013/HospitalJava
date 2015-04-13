package Model;

import DataBase.StockOperations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Thomas Murray on 02/04/2015.
 */
public class Consultants {
    private int conId;
    private String conName, conSpeciality,equipSill;
    private ResultSet rset;
    private ArrayList<Consultants> consultants = new ArrayList<>();
    StockOperations so;


    public Consultants() {
        refreshArrays();
    }

    public Consultants(String con_Name, String specialityIn,String equipSkillIn) {
        so=new StockOperations();
        conName = con_Name;
        conSpeciality = specialityIn;
        equipSill=equipSkillIn;
        addConsultant(con_Name,specialityIn,equipSkillIn);
    }


    public Consultants(int con_ID, String con_Name, String specialityIn,String equipSkillIn) {
        conId = con_ID;
        conName = con_Name;
        conSpeciality = specialityIn;
        equipSill=equipSkillIn;
    }

    public void refreshArrays() {
        try {
           consultants.removeAll(consultants);
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

    public void addConsultant(String nameIn, String conSpecialityIn,String equipSkill) {
        so.addConsultant(nameIn, conSpecialityIn,equipSkill);
        so.stockOperationsClose();
    }

    public void deleteConsultant(int conIdIn) {
        so=new StockOperations();
        so.deleteConsultant(conIdIn);
        so.stockOperationsClose();
    }

    public ArrayList getConsultants() {
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
