package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataBase.HospitalOperations;
import DataBase.ReferralOperations;

/**
 * Created by Roland on 07/03/2015.
 */
public class ProcessReferrals {
    private String gpName;
    private String gpLocation;
    private int patientNumber;
    private String reasonForVisit;
    private String recommendations;
    private int medicalRequired;
    private int consultantRequired, gpNum;
    private String patientFNameIn;
    private String patientLNameIn;
    private String patientAddressIn, emailIn, phoneIn, DOBIn;
    private String genderIn;
    private ReferralOperations ro=new ReferralOperations();
    private ArrayList<Referrals> refList = new ArrayList<Referrals>();
    private ResultSet rset=null;
    private Referrals ref;

    public ProcessReferrals() {
        rset = ro.getReferral();
        try {
            while (rset.next()) {
                refList.add(ref=new Referrals(rset.getInt(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5),rset.getInt(6),rset.getString(7),
                        rset.getString(8),rset.getString(9),rset.getString(10),rset.getString(11),rset.getInt(12),rset.getInt(13),rset.getInt(14),rset.getString(15)));
            }
        } catch (SQLException e1) {
            System.out.println(e1);
        }
            for (int i = 0; i < refList.size(); i++) {
                if (refList.get(i).isChecked() == 0) {
                    gpName = refList.get(i).getGpName();
                    gpLocation = refList.get(i).getGpLocation();
                    patientFNameIn = refList.get(i).getPatientFName();
                    patientLNameIn = refList.get(i).getPatientSurname();
                    DOBIn = refList.get(i).getDob();
                    patientAddressIn = refList.get(i).getPatientAddress();
                    phoneIn = refList.get(i).getPatientPhone();
                    reasonForVisit = refList.get(i).getReasonForVisit();
                    genderIn =refList.get(i).getGender();
                    recommendations = refList.get(i).getRecommendations();
                    medicalRequired = refList.get(i).getMedicalRequired();
                    consultantRequired = refList.get(i).getConsultantRequired();
                    if (refList.get(i).getPatientNumber() != 0) {
                        patientNumber = refList.get(i).getPatientNumber();
                    }
                    referralProcess();
                }
            }
    }


    public void referralProcess() {
        PatientRecord patientRecord = new PatientRecord(patientFNameIn, patientLNameIn, patientAddressIn, genderIn, emailIn, phoneIn, DOBIn);
        MedicalRecord medicalRecord = new MedicalRecord(patientRecord.getPatientNumber(), recommendations, medicalRequired);
        Appointment appointment=new Appointment(reasonForVisit,medicalRequired,consultantRequired);

    }
}