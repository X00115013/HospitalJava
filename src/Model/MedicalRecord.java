package Model;
import DataBase.PatientOperations;
/**
 * Created by College on 04/03/2015.
 */
public class MedicalRecord {

    private int patientNumber,equipNeed,consultantNeed;
    private String blood,symptoms,diagnoses,reqTreatment,allergies,recommendations;
    private  PatientOperations po;

    public MedicalRecord(int patientNumberIn,String recommendationsIn, int requiredEquipment){
        patientNumber=patientNumberIn;
        recommendations=recommendationsIn;
        equipNeed=requiredEquipment;
        po.refMedUpdate(patientNumber,equipNeed,recommendations);

    }
    public MedicalRecord(int patientNumberIn,String newBlood, String newSymptoms, String newDiagnoses, String newReqTreatment, int newEquipNeed, String recommendations,String newAllergies){
        patientNumber=patientNumberIn;
        blood=newBlood;
        symptoms=newSymptoms;
        diagnoses=newDiagnoses;
        reqTreatment=newReqTreatment;
        equipNeed=newEquipNeed;
        allergies=newAllergies;
        po.updatePatientMedical(patientNumber,blood,symptoms,diagnoses,reqTreatment,equipNeed,recommendations,allergies);

    }
}