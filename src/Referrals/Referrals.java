package Referrals;

import Referrals.ReferralGUI;
import Referrals.*;
import Referrals.ReferralOperations;
import java.util.Random;

/**
 * Created by Thomas Murray on 02/03/2015.
 */
public class Referrals {
    private String gpName,medicalRequired, consultantRequired,gpLocation, patientFName,patientSurname;
    private int patientNumber,referenceNum;
    private String patientAddress, dob,gender, patientPhone,reasonForVisit,recommendations;
    private int gpNum;
    private ReferralOperations ro=new ReferralOperations();
    private int checked= 1;
    Random random= new Random();




    public Referrals(){
        gpName="";
        gpLocation="";
        patientFName="";
        patientSurname="";
        patientNumber=0;
        patientAddress="";
        dob="";
        patientPhone="";
        reasonForVisit="";
        recommendations="";
        medicalRequired="";
        consultantRequired="";

    }

    public Referrals(int referenceIn,int gpNumIn,String gpNameIn,String gpLocationIn,String patientFNameIn,String patientSurnameIn,String patientAddressIn,String dobIn,String patientPhoneIn,String reasonForVisitIn,
                     String recommendationsIn,String medicalRequiredIn,String consultantRequiredIn,int checkedIn,String genderIn){


        referenceNum=referenceIn;
        gpName=gpNameIn;
        gpLocation=gpLocationIn;
        patientFName=patientFNameIn;
        patientSurname=patientSurnameIn;
        patientAddress=patientAddressIn;
        dob=dobIn;
        patientPhone=patientPhoneIn;
        reasonForVisit=reasonForVisitIn;
        recommendations=recommendationsIn;
        medicalRequired=medicalRequiredIn;
        consultantRequired=consultantRequiredIn;
        gender=genderIn;
        checked=checkedIn;
        gpNum =gpNumIn;

    }

    public Referrals(String gpNameIn,String gpLocationIn,String patientFNameIn,String patientSurnameIn,String patientAddressIn,String dobIn,String patientPhoneIn,String reasonForVisitIn,
                    String recommendationsIn,String medicalRequiredIn,String consultantRequiredIn,int checkIn,String genderIn){

        gpName=gpNameIn;
        gpLocation=gpLocationIn;
        patientFName=patientFNameIn;
        patientSurname=patientSurnameIn;
        patientAddress=patientAddressIn;
        dob=dobIn;
        patientPhone=patientPhoneIn;
        reasonForVisit=reasonForVisitIn;
        recommendations=recommendationsIn;
        medicalRequired=medicalRequiredIn;
        consultantRequired=consultantRequiredIn;
        gender=genderIn;
        checked=checkIn;
        if(gpNum ==0){
            gpNum = random.nextInt(1000000);
        }
        setReferral();

    }


    public void setReferral(){
        ro=new ReferralOperations();
        ro.setReferral(gpNum,gpName,gpLocation,patientFName,patientSurname,dob,patientAddress,patientPhone,reasonForVisit,recommendations,medicalRequired,consultantRequired,checked,gender);
        ro.referralOperationsClose();
    }


    public static void main(String args[]) {
//        Referrals referrals =new Referrals();
        ReferralGUI referralGUI= new ReferralGUI();
        referralGUI.setVisible(true);

    }
}
