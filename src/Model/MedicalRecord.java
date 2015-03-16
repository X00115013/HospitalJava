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
    private PatientOperations po = new PatientOperations();
    private ResultSet rset;

    public MedicalRecord(int patientNumberIn,String recommendationsIn, int requiredEquipment){
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

    public void printMedicalRecordByPatientNum(){
        System.out.println("\n\n\nPatient Med Record By Number\n");
        rset = po.getPatientMedical(patientNumber);
        try {
            while (rset.next()) {
                System.out.println(rset.getInt(1)+"\t"+rset.getString(2)+"\t"+rset.getString(3)+"\t"+rset.getString(4)+"\t"+rset.getString(5)+"" +
                        "\t"+rset.getString(6)+"\t"+rset.getString(7)+"\t"+rset.getString(8)+"\t"+rset.getString(9)+rset.getInt(10)+"" +
                        "\t"+rset.getInt(11)+"\t"+rset.getString(12)+"\t"+rset.getInt(13)+"\t"+rset.getString(14));
            }
        } catch (SQLException e1) {
            System.out.println("Patient record not working"+e1);
        }
    }

        public void printMedicalRecord(){
            System.out.println("\n\n\nAll Medical Records\n");
        rset = po.getPatientMedical();
        try {
            while (rset.next()) {
                System.out.println(rset.getInt(1)+"\t"+rset.getString(2)+"\t"+rset.getString(3)+"\t"+rset.getString(4)+"\t"+rset.getString(5)+"" +
                        "\t"+rset.getString(6)+"\t"+rset.getString(7)+"\t"+rset.getString(8)+"\t"+rset.getString(9)+rset.getInt(10)+"" +
                        "\t"+rset.getInt(11)+"\t"+rset.getString(12)+"\t"+rset.getInt(13)+"\t"+rset.getString(14));
            }
        } catch (SQLException e1) {
            System.out.println("Patient record not working"+e1);
        }
    }


    public void updateMedicalRecord(){
        po.updatePatientMedical(patientNumber,blood,symptoms,diagnoses,reqTreatment,equipNeed,recommendations,allergies);
        printMedicalRecord();
        printMedicalRecordByPatientNum();
    }
}

















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


















