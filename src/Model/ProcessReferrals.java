package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataBase.AppointmentOperations;
import DataBase.PatientOperations;
import Referrals.ReferralOperations;
import Referrals.Referrals;

/**
 * Created by Roland on 07/03/2015.
 */
public class ProcessReferrals {
    private String gpName;
    private String gpLocation;
    private int patientNumber;
    private int refNum,isChecked;
    private String reasonForVisit,occupation;
    private String recommendations;
    private int medicalRequired;
    private int consultantRequired, gpNum,k;
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
    private ArrayList<PatientRecord> pRecList;


    public ProcessReferrals() {
        ro=new ReferralOperations();
        po=new PatientOperations();
        process();

    }


    public ProcessReferrals(int refNumIn,int gpNumIn,String gpNameIn,String gpLocationIn,
                            String patientFNameIn,String patientSurnameIn,String patientAddressIn,
                            String dobIn,String patientPhoneIn,String reasonForVisitIn, String recommendationsIn,
                            int medicalRequiredIn,int consultantRequiredIn,int isCheckedIn,String genderIn,int patientNumberIn){
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
        patientNumber=patientNumberIn;
        isChecked=isCheckedIn;
    }


    public void refreshTables() {
        rset = ro.getReferral();
        try {
            while (rset.next()) {
                refList.add(processReferrals = new ProcessReferrals(rset.getInt(1),rset.getInt(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7),
                        rset.getString(8), rset.getString(9), rset.getString(10), rset.getString(11), rset.getInt(12), rset.getInt(13), rset.getInt(14), rset.getString(15), rset.getInt(16)));
            }
        } catch (SQLException e1) {
            System.out.println(e1);
        }
//        try {
//            rset = po.getPatientAdmin();
//            while (rset.next())
//                pRecList.add(patientRecord = new PatientRecord(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4),
//                        rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9)));
//        } catch (SQLException e1) {
//            System.out.println(e1);
//        }
    }

    public void printProcessRefArray(){
        refreshTables();
        System.out.println("\n\n\nReferral List\n");
        for (int i = 0; i < refList.size(); i++) {
            System.out.print("\t" + refList.get(i).refNum);
            System.out.print("\t"+refList.get(i).gpNum);
            System.out.print("\t"+refList.get(i).gpName);
            System.out.print("\t"+refList.get(i).gpLocation);
            System.out.print("\t"+refList.get(i).patientFName);
            System.out.print("\t"+refList.get(i).patientLName);
            System.out.print("\t"+refList.get(i).DOB);
            System.out.print("\t"+refList.get(i).patientAddress);
            System.out.print("\t"+refList.get(i).phoneIn);
            System.out.print("\t"+refList.get(i).reasonForVisit);
            System.out.print("\t"+refList.get(i).gender);
            System.out.print("\t"+refList.get(i).recommendations);
            System.out.print("\t"+refList.get(i).medicalRequired);
            System.out.print("\t"+refList.get(i).consultantRequired);
            System.out.print("\t"+refList.get(i).patientNumber);
            System.out.print("\t" + refList.get(i).isChecked+"\n\n");
        }
    }

    public void printReferrals () {
        System.out.println("\n\n\nReferral List\n");
        rset = ro.getReferral();
        try {
            while (rset.next()) {
                System.out.println("" + rset.getInt(2) + "\t" + rset.getString(3) + "\t" + rset.getString(4) + "\t" + rset.getString(5) + "\t" + rset.getString(6) + "\t" + rset.getString(7) + "\t" + rset.getString(8) + "\t" +
                        "\t" + rset.getString(9) + "\t" + rset.getString(10) + "\t" + rset.getString(11) + "\t" + rset.getInt(12) + "\t" + rset.getInt(13) + "\t" + rset.getInt(14) + "\t" + rset.getString(15) + "\t" + rset.getInt(16));
            }
        } catch (SQLException e1) {
            System.out.println(e1);
        }
    }

    public void process() {
        refreshTables();
        for (int i = 0; i < refList.size(); i++) {
            if (refList.get(i).isChecked == 1) {
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
                ro.setChecked(refList.get(i).refNum);
//                for (int j = 0; j < refList.size(); j++)
//                    if (patientFName.equalsIgnoreCase(pRecList.get(j).getPatientFName()) && patientLName.equalsIgnoreCase(pRecList.get(j).getPatientLName()) && DOB.equalsIgnoreCase(pRecList.get(j).getDOB())) {
//                        referralProcessForExistingPatient(pRecList.get(j).getPatientNumber());
//                    } else {
                        referralProcessForNewPatient();
                    }
//            }
      }
        printProcessRefArray();
        printReferrals();
    }


    public void referralProcessForExistingPatient(int patientNumIn) {
       PatientRecord patientRecord = new PatientRecord(po,patientNumIn,patientFName, patientLName, patientAddress,occupation,gender, emailIn, phoneIn, DOB);
        patientNumber=po.getPatientNumber(patientFName,patientLName,DOB);
        MedicalRecord medicalRecord = new MedicalRecord(po,patientNumber,recommendations, medicalRequired);
        Appointment appointment=new Appointment(reasonForVisit,medicalRequired,consultantRequired);

    }



    public void referralProcessForNewPatient() {
        PatientRecord patientRecord = new PatientRecord(po,patientFName, patientLName, patientAddress,occupation,gender, emailIn, phoneIn, DOB);
        patientNumber=po.getPatientNumber(patientFName,patientLName,DOB);
        MedicalRecord medicalRecord = new MedicalRecord(po,patientNumber,recommendations, medicalRequired);
        Appointment appointment=new Appointment(reasonForVisit,medicalRequired,consultantRequired);

    }
}