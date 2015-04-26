package Model;

import DataBase.*;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by David Kiernan and Thomas Murray on 17/03/2015.
 */
public class MedicalCard {
    private AllCardOperations cd;
    private ArrayList<MedicalCard> medCardList = new ArrayList<>();
    private ResultSet rset;
    private int PPS, GMSNumber,patientNum,ID;
    private String gender, validTo, holderName;

    public MedicalCard(){
    }

    public MedicalCard(int patientNumIn,int GMSNumber,int PPS,String gender,String validTo, String holderName,int fake) {
        cd=new AllCardOperations();
        patientNum=patientNumIn;
        this.GMSNumber = GMSNumber;
        this.PPS = PPS;
        this.gender = gender;
        this.validTo = validTo;
        this.holderName = holderName;
        updateMedCard();
    }
    public MedicalCard(int patientNumIn,int GMSNumber,int PPS,String gender,String validTo, String holderName) {
        cd=new AllCardOperations();
        patientNum=patientNumIn;
        this.GMSNumber = GMSNumber;
        this.PPS = PPS;
        this.gender = gender;
        this.validTo = validTo;
        this.holderName = holderName;
        addMedCard();
    }
    public MedicalCard(int ID, int patientNumIn, int GMSNumber,int PPS,String gender,String validTo, String holderName) {
        patientNum=patientNumIn;
        this.ID=ID;
        this.GMSNumber = GMSNumber;
        this.PPS = PPS;
        this.gender = gender;
        this.validTo = validTo;
        this.holderName = holderName;
    }
    public void refreshMedCardList() {
        cd=new AllCardOperations();
       medCardList.removeAll(medCardList);
        rset=cd.getMedCardDetails();
        try {
            while (rset.next()) {
                medCardList.add(new MedicalCard(rset.getInt(1), rset.getInt(2),  rset.getInt(3),rset.getInt(4),rset.getString(5), rset.getString(6), rset.getString(7)));
            }cd.allCardOperationsClose();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void addMedCard(){
        cd.addMedCard(patientNum,GMSNumber,PPS,gender,validTo,holderName);
        cd.allCardOperationsClose();
    }
    public void updateMedCard(){
        cd.updateMedCard(patientNum,GMSNumber,PPS,gender,validTo,holderName);
        cd.allCardOperationsClose();
    }

    public ArrayList getMedCardList() {
        refreshMedCardList();
        return medCardList;
    }
    public int getPPS() {
        return PPS;
    }
    public int getGMSNumber() {
        return GMSNumber;
    }
    public int getPatientNum() {
        return patientNum;
    }
    public String getGender() {
        return gender;
    }
    public String getValidTo() {
        return validTo;
    }
    public String getHolderName() {
        return holderName;
    }
}
