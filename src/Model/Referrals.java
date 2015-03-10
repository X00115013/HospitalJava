package Model;

import GUI.ReferralGUI;
import DataBase.ReferralOperations;

/**
 * Created by x00115013 on 02/03/2015.
 */
public class Referrals {
    private String gpName;
    private String gpLocation;
    private String patientFName;
    private String patientSurname;
    private int patientNumber;
    private String patientAddress;
    private String dob,gender;
    private String patientPhone;
    private String reasonForVisit;
    private String recommendations;
    private int medicalRequired;
    private int consultantRequired,gpNum;
    private ReferralOperations ro;
    private int checked= 0;

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

    public Referrals(int gpNumber,String gpNameIn,String gpLocationIn,String patientFNameIn,String patientSurnameIn,int patientNumberIn,
                    String patientAddressIn,String dobIn,String patientPhoneIn,String reasonForVisitIn,
                    String recommendationsIn,int medicalRequiredIn,int consultantRequiredIn,int checkedIn,String genderIn){

        gpNum=gpNumber;
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
        checked=checkedIn;
        gender=genderIn;
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
        ro.setReferral(gpName,gpLocation,patientFName,patientSurname,patientNumber,patientAddress,dob,patientPhone,
                reasonForVisit,recommendations,medicalRequired,consultantRequired,checked,gender);

    }

    public static void main(String args[]) {
        Referrals referrals =new Referrals();
        ReferralGUI referralGUI= new ReferralGUI(referrals);
        referralGUI.setVisible(true);

    }
}
