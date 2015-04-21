package Model;

import DataBase.AllCardOperations;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Thomas Murray on 08/04/2015.
 */
public class HealthInsurance {
    private AllCardOperations cd;
    private ArrayList<HealthInsurance> healthList = new ArrayList<>();
    private ResultSet rset;
    private int polNum, patientNum, ID;

    private String companyName,polType,expDate;

    public HealthInsurance() {

    }

    public HealthInsurance(int patientNumIn,int polNumIn, String companyNameIn, String polTypeIn, String expDateIn,int fake) {
        cd = new AllCardOperations();
        patientNum = patientNumIn;
        polNum=polNumIn;
        companyName=companyNameIn;
        polType=polTypeIn;
        expDate=expDateIn;
        updateInsurance();

    }
    public HealthInsurance(int patientNumIn,int polNumIn, String companyNameIn, String polTypeIn, String expDateIn) {
        cd = new AllCardOperations();
        patientNum = patientNumIn;
        polNum=polNumIn;
        companyName=companyNameIn;
        polType=polTypeIn;
        expDate=expDateIn;
        addInsurance();

    }

    public HealthInsurance(int ID, int patientNumIn, int polNumIn, String companyNameIn, String polTypeIn, String expDateIn) {
        patientNum = patientNumIn;
        this.ID = ID;
        polNum=polNumIn;
        companyName=companyNameIn;
        polNum=polNumIn;
        polType=polTypeIn;
        expDate=expDateIn;

    }

    public void refreshInsuranceList() {
        cd = new AllCardOperations();
        healthList.removeAll(healthList);
        rset = cd.getInsuranceDetails();
        try {
            while (rset.next()) {
                healthList.add(new HealthInsurance(rset.getInt(1), rset.getInt(2), rset.getInt(3), rset.getString(4), rset.getString(5), rset.getString(6)));
            }
            cd.allCardOperationsClose();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addInsurance() {
        cd.addInsurance(patientNum, polNum, companyName, polType, expDate);
        cd.allCardOperationsClose();
    }

    public void updateInsurance() {
        cd.updateInsurance(patientNum, polNum, companyName, polType, expDate);
        cd.allCardOperationsClose();
    }

    public ArrayList getHealthList() {
        refreshInsuranceList();
        return healthList;
    }

    public int getPolNum() {
        return polNum;
    }

    public int getPatientNum() {
        return patientNum;
    }

    public int getID() {
        return ID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPolType() {
        return polType;
    }

    public String getExpDate() {
        return expDate;
    }
}
