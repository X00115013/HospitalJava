package Model;
import DataBase.*;
import Referrals.ReferralOperations;
import Referrals.Referrals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by David and Thomas Murray on 17/03/2015.
 */
public class CardDetails {
    int cardId, securityCode, patientNumIn, cardNum;
    String cardType, cardHolder, expiryDate;
    private ResultSet rset;
    private ArrayList<CardDetails> cardList = new ArrayList<>();
    private AllCardOperations cd;

    public CardDetails() {

    }


    public CardDetails(int patientNumberIn, int cardNumIn, String cardType, int securityCode, String cardHolder, String expiryDate,int fake) {
        cd = new AllCardOperations();
        patientNumIn = patientNumberIn;
        cardNum = cardNumIn;
        this.cardHolder = cardHolder;
        this.cardType = cardType;
        this.expiryDate = expiryDate;
        this.securityCode = securityCode;
        updateCard();
    }


    public CardDetails(int patientNumberIn, int cardNumIn, String cardType, int securityCode, String cardHolder, String expiryDate) {
        cd = new AllCardOperations();
        patientNumIn = patientNumberIn;
        cardNum = cardNumIn;
        this.cardHolder = cardHolder;
        this.cardType = cardType;
        this.expiryDate = expiryDate;
        this.securityCode = securityCode;
        addCard();
    }


    public CardDetails(int cardId, int patientNum, int cardNumIn, String cardType, int securityCode, String cardHolder, String expiryDate) {
        cardNum = cardNumIn;
        this.cardId = cardId;
        this.cardHolder = cardHolder;
        this.cardType = cardType;
        this.expiryDate = expiryDate;
        this.securityCode = securityCode;
        patientNumIn=patientNum;
    }

    public void refreshCardList() {
        cd = new AllCardOperations();
        cardList.removeAll(cardList);
        rset = cd.getCardDetails();
        try {
            while (rset.next()) {
                cardList.add(new CardDetails(rset.getInt(1), rset.getInt(2), rset.getInt(3), rset.getString(4), rset.getInt(5), rset.getString(6), rset.getString(7)));
            }
            cd.allCardOperationsClose();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addCard() {
        cd.addCard(patientNumIn, cardNum, cardType, securityCode, cardHolder, expiryDate);
        cd.allCardOperationsClose();
    }

    public void updateCard() {
        cd.updateCard(patientNumIn, cardNum, cardType, securityCode, cardHolder, expiryDate);
        cd.allCardOperationsClose();
    }


    public int getCardId() {
        return cardId;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public int getPatientNumIn() {
        return patientNumIn;
    }

    public int getCardNum() {
        return cardNum;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public ArrayList getCardList() {
        refreshCardList();
        return cardList;
    }

}
