package Model;

import DataBase.*;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by David on 17/03/2015.
 */
public class MedicalCard {
    private AllCardOperations medicalCard = new AllCardOperations();
    private ArrayList<MedicalCard> medCardList = new ArrayList<>();
    private ResultSet rset;
    private int PPSN, GMSNumber;

    private String gender, DOB, validTo, HolderName;

    public MedicalCard(){

    }

    public MedicalCard(int GMSNumber,int PPSN,String gender,String DOB ,String validTo, String holderName) {
        this.GMSNumber = GMSNumber;
        this.PPSN = PPSN;
        this.gender = gender;
        this.DOB = DOB;
        this.validTo = validTo;
        HolderName = holderName;

    }
    public void refreshMedCardList() {
        System.out.println("This will delete all all in this list");
        rset = medicalCard.getMedCardDetails();
        if (medCardList.size() > 0) {  //THIS WILL DELETE ALL IN CARDLIST
            for (int i = medCardList.size() - 1; i >= 0; i--) {
                medCardList.remove(i);
            }
        }
        try { //THIS WILL ADD IT BACK IN WITH ANY UPDATES
            System.out.println("Start of Try");
            while (rset.next()) {
                System.out.println("Refresh Method Card Details");
                MedicalCard mCard = new MedicalCard(rset.getInt(1), rset.getInt(2), rset.getString(3), rset.getString(4), rset.getString(5),rset.getString(6));
                medCardList.add(mCard);
                System.out.println("ID: "+rset.getInt(1)+" PPSN: " +rset.getInt(2)+" Gender: " + rset.getString(3)+" DOB: " +rset.getString(4)+" Expiry Date " +rset.getString(5) + " Name "+ rset.getString(6));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        MedicalCard cd = new MedicalCard();
        cd.refreshMedCardList();

    }
}
