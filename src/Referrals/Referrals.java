package Referrals;

import Referrals.ReferralGUI;
import Referrals.ReferralOperations;
import java.util.Random;

/**
 * Created by x00115013 on 02/03/2015.
 */
public class Referrals {
    private String gpName;
    private String gpLocation;
    private String patientFName;
    private String patientSurname;
    private int patientNumber,referenceNum;
    private String patientAddress;
    private String dob,gender;
    private String patientPhone;
    private String reasonForVisit;
    private String recommendations;
    private int medicalRequired;
    private int consultantRequired,gpNum;
    private ReferralOperations ro;
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
        medicalRequired=0;
        consultantRequired=0;

    }

    public Referrals(int referenceIn,int gpNumIn,String gpNameIn,String gpLocationIn,String patientFNameIn,String patientSurnameIn,String patientAddressIn,String dobIn,String patientPhoneIn,String reasonForVisitIn,
                     String recommendationsIn,int medicalRequiredIn,int consultantRequiredIn,int checkedIn,String genderIn,int patientNumberIn){


        referenceNum=referenceIn;
        gpName=gpNameIn;
        gpLocation=gpLocationIn;
        patientFName=patientFNameIn;
        patientSurname=patientSurnameIn;
        patientNumber=patientNumberIn;
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
                    String recommendationsIn,int medicalRequiredIn,int consultantRequiredIn,String genderIn,int patientNumberIn){

        gpName=gpNameIn;
        gpLocation=gpLocationIn;
        patientFName=patientFNameIn;
        patientSurname=patientSurnameIn;
        patientNumber=patientNumberIn;
        patientAddress=patientAddressIn;
        dob=dobIn;
        patientPhone=patientPhoneIn;
        reasonForVisit=reasonForVisitIn;
        recommendations=recommendationsIn;
        medicalRequired=medicalRequiredIn;
        consultantRequired=consultantRequiredIn;
        gender=genderIn;
        if(gpNum ==0){
            gpNum = random.nextInt(1000000);
        }
        setReferral();

    }

    public String getGpName() {
        return gpName;
    }

    public int isChecked() {
        return checked;
    }

    public ReferralOperations getRo() {
        return ro;
    }

    public int getGpNum() {
        return gpNum;
    }

    public int getReferenceNum() {
        return referenceNum;
    }

    public int getConsultantRequired() {
        return consultantRequired;
    }

    public int getMedicalRequired() {
        return medicalRequired;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public String getDob() {
        return dob;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public int getPatientNumber() {
        return patientNumber;
    }

    public String getPatientFName() {
        return patientFName;
    }

    public String getGpLocation() {
        return gpLocation;
    }

    public String getPatientSurname() {
        return patientSurname;
    }

    public String getReasonForVisit() {
        return reasonForVisit;
    }

    public String getGender() {
        return gender;
    }

    public void setReferral(){
        ro=new ReferralOperations();
        ro.setReferral(gpNum,gpName,gpLocation,patientFName,patientSurname,dob,patientAddress,patientPhone,reasonForVisit,recommendations,medicalRequired,consultantRequired,checked,gender);

    }


    public static void main(String args[]) {
//        Referrals referrals =new Referrals();
        ReferralGUI referralGUI= new ReferralGUI();
        referralGUI.setVisible(true);

    }
}
