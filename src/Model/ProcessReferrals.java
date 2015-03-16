package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    private String reasonForVisit,occupation;
    private String recommendations;
    private int medicalRequired;
    private int consultantRequired, gpNum;
    private String patientFNameIn;
    private String patientLNameIn;
    private String patientAddressIn, emailIn, phoneIn, DOBIn;
    private String genderIn;
    private ReferralOperations ro=new ReferralOperations();
    private ArrayList<Referrals> refList = new ArrayList<Referrals>();
    private PatientOperations po;
    private ResultSet rset;
    private Referrals ref;

    public void refreshTables() {
        rset = ro.getReferral();
        try {
            while (rset.next()) {
                refList.add(ref = new Referrals(rset.getInt(1),rset.getInt(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7),
                        rset.getString(8), rset.getString(9), rset.getString(10), rset.getString(11), rset.getInt(12), rset.getInt(13), rset.getInt(14), rset.getString(15), rset.getInt(16)));
            }
        } catch (SQLException e1) {
            System.out.println(e1);


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

    public ProcessReferrals() {
        refreshTables();
            for (int i = 0; i < refList.size(); i++) {
                if (refList.get(i).isChecked() == 1) {
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
//                    if (refList.get(i).getPatientNumber() != 0) {
//                        patientNumber = refList.get(i).getPatientNumber();
//                    }
                    ro.setChecked(refList.get(i).getReferenceNum());
                    referralProcess();
                }
            }printReferrals();
    }


    public void referralProcess() {
        po=new PatientOperations();
        PatientRecord patientRecord = new PatientRecord(patientFNameIn, patientLNameIn, patientAddressIn,occupation,genderIn, emailIn, phoneIn, DOBIn);
        patientNumber=po.getPatientNumber(patientFNameIn,patientLNameIn,DOBIn);
        MedicalRecord medicalRecord = new MedicalRecord(patientNumber,recommendations, medicalRequired);
        Appointment appointment=new Appointment(reasonForVisit,medicalRequired,consultantRequired);

    }
}