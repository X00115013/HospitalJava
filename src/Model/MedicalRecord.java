package Model;
import DataBase.PatientOperations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by College on 04/03/2015.
 */
public class MedicalRecord {

    private int patientNumber,equipNeed,consultantNeed, getEquipUsed,prescriptionUsed;
    private String blood,symptoms,diagnoses,reqTreatment,allergies,recommendations,patientFName,patientLName,patientGender,patientDOB;
    private MedicalRecord medicalRecord;
    private ArrayList<MedicalRecord> medicalRecordArrayList=new ArrayList<MedicalRecord>();
    private PatientOperations po;
    private ResultSet rset;

    public MedicalRecord(PatientOperations po,int patientNumberIn,String recommendationsIn, int requiredEquipment){
        this.po=po;
        patientNumber=patientNumberIn;
        recommendations=recommendationsIn;
        equipNeed=requiredEquipment;
        po.refMedUpdate(patientNumber,equipNeed,recommendations);
        updateMedicalRecord();

    }
    public MedicalRecord(int patientNumberIn,String newBlood, String newSymptoms, String newDiagnoses, String newReqTreatment, int newEquipNeed, String recommendations,String newAllergies){
        patientNumber=patientNumberIn;
        blood=newBlood;
        symptoms=newSymptoms;
        diagnoses=newDiagnoses;
        reqTreatment=newReqTreatment;
        equipNeed=newEquipNeed;
        allergies=newAllergies;
        updateMedicalRecord();
    }

    public MedicalRecord(int patientNumberIn,String patientFNameIn, String patientLNameIn, String patientDOBIn,
                         String patientGenderIn, String newBlood, String newSymptoms, String newDiagnoses, String newReqTreatment, int newEquipNeed, int newEquipUsedIn,String newAllergies,int prescriptionUsedIn, String recommendationsIn){
        patientNumber=patientNumberIn;
        patientFName=patientFNameIn;
        patientLName=patientLNameIn;
        patientDOB=patientDOBIn;
        patientGender=patientGenderIn;
        blood=newBlood;
        symptoms=newSymptoms;
        diagnoses=newDiagnoses;
        reqTreatment=newReqTreatment;
        getEquipUsed=newEquipUsedIn;
        equipNeed=newEquipNeed;
        prescriptionUsed=prescriptionUsedIn;
        allergies=newAllergies;
        recommendations=recommendationsIn;
    }

    public void refreshArray(){
        rset = po.getPatientMedical();
        try {
            while (rset.next()) {
                medicalRecordArrayList.add(medicalRecord=new MedicalRecord(rset.getInt(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5),
                        rset.getString(6),rset.getString(7),rset.getString(8),rset.getString(9),rset.getInt(10),
                        rset.getInt(11),rset.getString(12),rset.getInt(13),rset.getString(14)));
            }
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
                System.out.print("\nEquip Needed ("+medicalRecordArrayList.get(i).equipNeed+")");
                System.out.print("\nEquip Used ("+medicalRecordArrayList.get(i).getEquipUsed+")");
                System.out.print("\nAllergies ("+medicalRecordArrayList.get(i).allergies+")");
                System.out.print("\nPrescription ("+medicalRecordArrayList.get(i).prescriptionUsed+")");
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
                System.out.print("\nEquip Needed ("+medicalRecordArrayList.get(i).equipNeed+")");
                System.out.print("\nEquip Used ("+medicalRecordArrayList.get(i).getEquipUsed+")");
                System.out.print("\nAllergies ("+medicalRecordArrayList.get(i).allergies+")");
                System.out.print("\nPrescription ("+medicalRecordArrayList.get(i).prescriptionUsed+")");
                System.out.print("\nRecommendations ("+medicalRecordArrayList.get(i).recommendations+")\n");
            }
        }


    public void updateMedicalRecord(){
        po.updatePatientMedical(patientNumber,blood,symptoms,diagnoses,reqTreatment,equipNeed,recommendations,allergies);
        refreshArray();
        printAllArrayMR();
        printFromArrayMR(2);
//        printMedicalRecord();
//        printMedicalRecordByPatientNum();
    }
}



















//    public void printMedicalRecordByPatientNum(){
//        System.out.println("\n\n\nPatient Med Record By Number\n");
//        rset = po.getPatientMedical(patientNumber);
//        try {
//            while (rset.next()) {
//                System.out.println(rset.getInt(1)+"\t"+rset.getString(2)+"\t"+rset.getString(3)+"\t"+rset.getString(4)+"\t"+rset.getString(5)+"" +
//                        "\t"+rset.getString(6)+"\t"+rset.getString(7)+"\t"+rset.getString(8)+"\t"+rset.getString(9)+rset.getInt(10)+"" +
//                        "\t"+rset.getInt(11)+"\t"+rset.getString(12)+"\t"+rset.getInt(13)+"\t"+rset.getString(14));
//            }
//        } catch (SQLException e1) {
//            System.out.println("Patient record not working"+e1);
//        }
//
//    }
//
//    public void printMedicalRecord(){
//        System.out.println("\n\n\nAll Medical Records\n");
//        rset = po.getPatientMedical();
//        try {
//            while (rset.next()) {
//                System.out.println(rset.getInt(1)+"\t"+rset.getString(2)+"\t"+rset.getString(3)+"\t"+rset.getString(4)+"\t"+rset.getString(5)+"" +
//                        "\t"+rset.getString(6)+"\t"+rset.getString(7)+"\t"+rset.getString(8)+"\t"+rset.getString(9)+rset.getInt(10)+"" +
//                        "\t"+rset.getInt(11)+"\t"+rset.getString(12)+"\t"+rset.getInt(13)+"\t"+rset.getString(14));
//            }
//        } catch (SQLException e1) {
//            System.out.println("Patient record not working"+e1);
//        }
//    }

//    public void printMedicalRecord(){
//        System.out.println("\n\n\nPatient Medical Records\n");
//        rset = po.getPatientAdmin();
//        try {
//            while (rset.next()) {
//                System.out.println(rset.getInt(1)+"\t"+rset.getString(2)+"\t"+rset.getString(3)+"\t"+rset.getString(5)+"" +
//                        "\t"+rset.getString(6)+"\t"+rset.getString(8)+"\t"+rset.getString(9)+"\t"+rset.getString(10)+rset.getInt(11)+"" +
//                        "\t"+rset.getInt(12)+"\t"+rset.getInt(13)+"\t"+rset.getString(14)+"\t"+rset.getInt(15)+rset.getString(18));
//            }
//        } catch (SQLException e1) {
//            System.out.println("Patient record not working");
//        }
//    }


















