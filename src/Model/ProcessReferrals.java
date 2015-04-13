package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataBase.AppointmentOperations;
import DataBase.PatientOperations;
import DataBase.StockOperations;
import Referrals.ReferralOperations;
import Referrals.Referrals;

import javax.swing.*;

/**
 * Created by Thomas Murray on 07/03/2015.
 */
public class ProcessReferrals {
    private String gpName;
    private String gpLocation;
    private int patientNumber;
    private int refNum,isChecked;
    private String reasonForVisit,occupation;
    private String recommendations,consultantRequired,medicalRequired;
    private int  gpNum;
    private String patientFName;
    private String patientLName;
    private String patientAddress, emailIn, phoneIn, DOB;
    private String gender;
    private ReferralOperations ro;
    private ArrayList<ProcessReferrals> refList= new ArrayList<>();
    private PatientOperations po;
    private ResultSet rset;
    private ProcessReferrals processReferrals;
    private PatientRecord patientRecord;
    private ArrayList<PatientRecord> pRecList=new ArrayList<>();
    private Consultants consultants;
    private ArrayList<Consultants> conList=new ArrayList<>();
//    private StockOperations so;


    public ProcessReferrals() {
//        so=new StockOperations();
        ro=new ReferralOperations();
        po=new PatientOperations();
        refreshTables();
        process();

    }

//    public PatientRecord(PatientOperations po,int patientNumIn,String patientFNameIn, String patientLNameIn,
//                         String patientAddressIn, String occupationIn,String genderIn, String emailIn, String phoneIn, String DOBIn)


    public ProcessReferrals(int refNumIn,int gpNumIn,String gpNameIn,String gpLocationIn,
                            String patientFNameIn,String patientSurnameIn,String patientAddressIn,
                            String dobIn,String patientPhoneIn,String reasonForVisitIn, String recommendationsIn,
                            String medicalRequiredIn,String consultantRequiredIn,int isCheckedIn,String genderIn){
        refNum=refNumIn;
        gpNum=gpNumIn;
        gpName = gpNameIn;
        gpLocation = gpLocationIn;
        patientFName = patientFNameIn ;
        patientLName = patientSurnameIn;
        DOB = dobIn;
        patientAddress =patientAddressIn;
        phoneIn =patientPhoneIn;
        reasonForVisit =reasonForVisitIn;
        gender =genderIn;
        recommendations = recommendationsIn;
        medicalRequired = medicalRequiredIn;
        consultantRequired= consultantRequiredIn;
        isChecked=isCheckedIn;
    }


    public void refreshTables() {
        refList.removeAll(refList);
        rset = ro.getReferral();
        try {
            while (rset.next()) {
                refList.add(processReferrals = new ProcessReferrals(rset.getInt(1), rset.getInt(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7),
                        rset.getString(8), rset.getString(9), rset.getString(10), rset.getString(11), rset.getString(12), rset.getString(13), rset.getInt(14), rset.getString(15)));
            }
        } catch (SQLException e1) {
            System.out.println("Referral connection trouble process referral " + e1);
        }
        pRecList.removeAll(pRecList);
        patientRecord=new PatientRecord();
        pRecList.addAll(patientRecord.getPatientList());
    }


    public void process() {
        boolean test=false;
        int pNum=0,counter=0;
        for (int i = 0; i < refList.size(); i++) {
            if (refList.get(i).isChecked == 1) {
                counter++;
                this.refNum = refList.get(i).refNum;
                this.gpNum = refList.get(i).gpNum;
                this.gpName = refList.get(i).gpName;
                this.gpLocation = refList.get(i).gpLocation;
                this.patientFName = refList.get(i).patientFName;
                this.patientLName = refList.get(i).patientLName;
                this.DOB = refList.get(i).DOB;
                this.patientAddress = refList.get(i).patientAddress;
                this.phoneIn = refList.get(i).phoneIn;
                this.reasonForVisit = refList.get(i).reasonForVisit;
                this.gender = refList.get(i).gender;
                this.recommendations = refList.get(i).recommendations;
                this.medicalRequired = refList.get(i).medicalRequired;
                this.consultantRequired = refList.get(i).consultantRequired;
                if (pRecList.size()==0) {
                    referralProcessForNewPatient();
                    ro.setChecked(refList.get(i).refNum);
                }
                else{
                for (int j = 0; j < pRecList.size(); j++) {
                    System.out.println("\n\n\t" + patientFName + "\t" + pRecList.get(j).getPatientFName());
                    if (patientFName.equalsIgnoreCase(pRecList.get(j).getPatientFName())) {
                        pNum=pRecList.get(j).getPatientNumber();
                        test = true;
                    }
                }
                    if(test) {
                        referralProcessForExistingPatient(pNum);
                        ro.setChecked(refList.get(i).refNum);
                    }
                    else if(!test) {
                        referralProcessForNewPatient();
                        ro.setChecked(refList.get(i).refNum);
                    }
                }

              }
            }refreshTables();
        JOptionPane.showMessageDialog(null, counter+" Referrals have been processed");

        ro.referralOperationsClose();
    }


    public void referralProcessForExistingPatient(int patientNumIn) {
        String catcher="";
        System.out.println("\n\nExisting Patient\n\n");
        PatientRecord patientRecord = new PatientRecord(po,patientNumIn,patientFName, patientLName, patientAddress,occupation,gender, emailIn, phoneIn, DOB);
        MedicalRecord medicalRecord = new MedicalRecord(po,patientNumIn,recommendations);
        consultants=new Consultants();
        conList.removeAll(conList);
        conList.addAll(consultants.getConsultants());
        for (int j = 0; j < conList.size(); j++) {
            if (consultantRequired.equals(conList.get(j).getConSpeciality())) {
                catcher = conList.get(j).getConName();;
            }
        }
        Appointment appointment=new Appointment(reasonForVisit,medicalRequired,catcher,patientNumIn);
        EquipmentUsed equipmentUsed=new EquipmentUsed(patientNumIn,medicalRequired);
    }


    public void referralProcessForNewPatient() {
        String catcher="";
        System.out.println("\n\nNew Patient Method\n\n");
        PatientRecord patientRecord = new PatientRecord(po,patientFName, patientLName, patientAddress,occupation,gender, emailIn, phoneIn, DOB);
        patientNumber=po.getPatientNumber(patientFName,patientLName,DOB);
        MedicalRecord medicalRecord = new MedicalRecord(po,patientNumber,recommendations);
        consultants=new Consultants();
        conList.removeAll(conList);
        conList.addAll(consultants.getConsultants());
        for (int j = 0; j < conList.size(); j++) {
            if (consultantRequired.equals(conList.get(j).getConSpeciality())) {
                catcher = conList.get(j).getConName();;
            }
        }
        Appointment appointment=new Appointment(reasonForVisit,medicalRequired,catcher,patientNumber);
        EquipmentUsed equipmentUsed=new EquipmentUsed(patientNumber,medicalRequired);

    }

    public void clearArrays(){
        for (int i= 0; i < pRecList.size() ; i++) {
            pRecList.remove(i);
        }
        for (int i= 0; i < refList.size() ; i++) {
            refList.remove(i);
        }
    }
}









//    public void printProcessRefArray(){
//        refreshTables();
//        System.out.println("\n\n\nReferral List\n");
//        for (int i = 0; i < refList.size(); i++) {
//            System.out.print("\nReference Num (" + refList.get(i).refNum+")");
//            System.out.print("\nGP Num ("+refList.get(i).gpNum+")");
//            System.out.print("\nGP Name ("+refList.get(i).gpName+")");
//            System.out.print("\nGP Location ("+refList.get(i).gpLocation+")");
//            System.out.print("\nPatient First Name ("+refList.get(i).patientFName+")");
//            System.out.print("\nPatient Surname ("+refList.get(i).patientLName+")");
//            System.out.print("\nPatient DOB"+refList.get(i).DOB+")");
//            System.out.print("\nPatient Address ("+refList.get(i).patientAddress+")");
//            System.out.print("\nPhone ("+refList.get(i).phoneIn+")");
//            System.out.print("\nReason ("+refList.get(i).reasonForVisit+")");
//            System.out.print("\nGender ("+refList.get(i).gender+")");
//            System.out.print("\nRecommendations ("+refList.get(i).recommendations+")");
//            System.out.print("\nMed Required ("+refList.get(i).medicalRequired+")");
//            System.out.print("\nCon Required ("+refList.get(i).consultantRequired+")");
//            System.out.print("\nPatient Number ("+refList.get(i).patientNumber+")");
//            System.out.print("\nChecked Already Flag (" + refList.get(i).isChecked+")\n\n");
//        }
//    }

//    public void printReferrals () {
//        System.out.println("\n\n\nReferral List\n");
//        rset = ro.getReferral();
//        try {
//            while (rset.next()) {
//                System.out.println("" + rset.getInt(2) + "\t" + rset.getString(3) + "\t" + rset.getString(4) + "\t" + rset.getString(5) + "\t" + rset.getString(6) + "\t" + rset.getString(7) + "\t" + rset.getString(8) + "\t" +
//                        "\t" + rset.getString(9) + "\t" + rset.getString(10) + "\t" + rset.getString(11) + "\t" + rset.getInt(12) + "\t" + rset.getInt(13) + "\t" + rset.getInt(14) + "\t" + rset.getString(15) + "\t" + rset.getInt(16));
//            }
//        } catch (SQLException e1) {
//            System.out.println(e1);
//        }
//    }