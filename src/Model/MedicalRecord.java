package Model;
import DataBase.PatientOperations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Thomas Murray on 04/03/2015.
 */
public class MedicalRecord {

    private int patientNumber;
    private String blood,symptoms,diagnoses,reqTreatment,allergies,recommendations,patientFName,patientLName,patientGender,patientDOB;
    private ArrayList<MedicalRecord> medicalRecordArrayList=new ArrayList<MedicalRecord>();
    private PatientOperations po;
    private ResultSet rset;

    public MedicalRecord(){
        po=new PatientOperations();
        refreshArray();
    }

    public MedicalRecord(PatientOperations po,int patientNumberIn,String recommendationsIn){
        this.po=po;
        patientNumber=patientNumberIn;
        recommendations=recommendationsIn;
        po.refMedUpdate(patientNumber,recommendations);
        updateMedicalRecord();

    }
    public MedicalRecord(int patientNumberIn,String newBlood, String newSymptoms, String newDiagnoses, String newReqTreatment,String newAllergies){
        patientNumber=patientNumberIn;
        blood=newBlood;
        symptoms=newSymptoms;
        diagnoses=newDiagnoses;
        reqTreatment=newReqTreatment;
        allergies=newAllergies;
        updateMedicalRecord();
    }

    public MedicalRecord(int patientNumberIn,String patientFNameIn, String patientLNameIn, String patientDOBIn,
                         String patientGenderIn, String newBlood, String newSymptoms, String newDiagnoses, String newReqTreatment,String newAllergies, String recommendationsIn){
        patientNumber=patientNumberIn;
        patientFName=patientFNameIn;
        patientLName=patientLNameIn;
        patientDOB=patientDOBIn;
        patientGender=patientGenderIn;
        blood=newBlood;
        symptoms=newSymptoms;
        diagnoses=newDiagnoses;
        reqTreatment=newReqTreatment;
        allergies=newAllergies;
        recommendations=recommendationsIn;
    }

    public void refreshArray(){
        rset = po.getPatientMedical();
        try {
            while (rset.next()) {
                medicalRecordArrayList.add(new MedicalRecord(rset.getInt(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5),
                        rset.getString(6),rset.getString(7),rset.getString(8),rset.getString(9),rset.getString(10),rset.getString(11)));
            }po.patientOperationsClose();
        } catch (SQLException e1) {
            System.out.println("Patient record not working"+e1);
        }
    }

    public void printFromArrayMR(int patientNumIn){
        System.out.println("\n\n\nPatient Med Record By Number from Array\n");
        for (int i = 0; i < medicalRecordArrayList.size(); i++) {
            if(medicalRecordArrayList.get(i).patientNumber == patientNumIn){
                System.out.print("\n\nPatient Number ("+medicalRecordArrayList.get(i).patientNumber+")");
                System.out.print("\nPatient First Name ("+medicalRecordArrayList.get(i).patientFName+")");
                System.out.print("\nPatient Last Name ("+medicalRecordArrayList.get(i).patientLName+")");
                System.out.print("\nPatient DOB ("+medicalRecordArrayList.get(i).patientDOB+")");
                System.out.print("\nGender ("+medicalRecordArrayList.get(i).patientGender+")");
                System.out.print("\nBlood ("+medicalRecordArrayList.get(i).blood+")");
                System.out.print("\nSymptoms ("+medicalRecordArrayList.get(i).symptoms+")");
                System.out.print("\nDiagnoses ("+medicalRecordArrayList.get(i).diagnoses+")");
                System.out.print("\nReq Treatment ("+medicalRecordArrayList.get(i).reqTreatment+")");
                System.out.print("\nAllergies ("+medicalRecordArrayList.get(i).allergies+")");
                System.out.print("\nRecommendations ("+medicalRecordArrayList.get(i).recommendations+")\n");
            }
        }
    }
    public void printAllArrayMR(){
        System.out.println("\n\n\nPatient Med Record from Array\n");
        for (int i = 0; i < medicalRecordArrayList.size(); i++) {
                System.out.print("\n\nPatient Number ("+medicalRecordArrayList.get(i).patientNumber+")");
                System.out.print("\nPatient First Name ("+medicalRecordArrayList.get(i).patientFName+")");
                System.out.print("\nPatient Last Name ("+medicalRecordArrayList.get(i).patientLName+")");
                System.out.print("\nPatient DOB ("+medicalRecordArrayList.get(i).patientDOB+")");
                System.out.print("\nGender ("+medicalRecordArrayList.get(i).patientGender+")");
                System.out.print("\nBlood ("+medicalRecordArrayList.get(i).blood+")");
                System.out.print("\nSymptoms ("+medicalRecordArrayList.get(i).symptoms+")");
                System.out.print("\nDiagnoses ("+medicalRecordArrayList.get(i).diagnoses+")");
                System.out.print("\nReq Treatment ("+medicalRecordArrayList.get(i).reqTreatment+")");
                System.out.print("\nAllergies ("+medicalRecordArrayList.get(i).allergies+")");
                System.out.print("\nRecommendations ("+medicalRecordArrayList.get(i).recommendations+")\n");
            }
        }


    public ArrayList getMedicalRecordArrayList() {
        return medicalRecordArrayList;
    }

    public String getPatientDOB() {
        return patientDOB;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public String getPatientLName() {
        return patientLName;
    }

    public String getPatientFName() {
        return patientFName;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public String getAllergies() {
        return allergies;
    }

    public String getReqTreatment() {
        return reqTreatment;
    }

    public String getDiagnoses() {
        return diagnoses;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public String getBlood() {
        return blood;
    }

    public int getPatientNumber() {
        return patientNumber;
    }

    public void updateMedicalRecord(){
        po=new PatientOperations();
        po.updatePatientMedical(patientNumber,blood,symptoms,diagnoses,reqTreatment,recommendations,allergies);
        refreshArray();
        printAllArrayMR();
//        printFromArrayMR(2);
//        printMedicalRecord();
//        printMedicalRecordByPatientNum();
    }
}

