package Model;
import DataBase.PatientOperations;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by College on 04/03/2015.
 */
public class MedicalRecord {

    private int patientNumber,equipNeed,consultantNeed;
    private String blood,symptoms,diagnoses,reqTreatment,allergies,recommendations;
    private PatientOperations po;
    private ResultSet rset;

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
        updateMedicalRecord();
    }

    public void printMedicalRecord(){
        po=new PatientOperations();
        rset = po.getPatientMedical(patientNumber);
        try {
            while (rset.next()) {
                System.out.println(rset.getInt(1)+"\t"+rset.getString(2)+"\t"+rset.getString(3)+"\t"+rset.getString(4)+"\t"+rset.getString(5)+"" +
                        "\t"+rset.getString(6)+"\t"+rset.getString(7)+"\t"+rset.getString(8)+"\t"+rset.getString(9)+rset.getInt(10)+"" +
                        "\t"+rset.getInt(11)+"\t"+rset.getString(12)+"\t"+rset.getInt(13));
            }
        } catch (SQLException e1) {
            System.out.println("Patient record not working");
        }
    }



    public void updateMedicalRecord(){
        po = new PatientOperations();
        po.updatePatientMedical(patientNumber,blood,symptoms,diagnoses,reqTreatment,equipNeed,recommendations,allergies);
    }
}