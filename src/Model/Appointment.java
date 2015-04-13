package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataBase.AppointmentOperations;
/**
 * Created by Thomas Murray on 02/03/2015.
 */
public class Appointment {
    public int time;
    public String reasonForVisit;
    public String consultantType,medicalEquip;
    public int  patientNum, appNumber;
    public String date;
    private Consultants consultants;
    private ResultSet rset = null;
    private ArrayList<Appointment> appList = new ArrayList<Appointment>();
    private ArrayList<Consultants>conList=new ArrayList<>();
    private AppointmentOperations ao;
    private Appointment apt;


    public Appointment(){
    }

    public Appointment(int appNumIn ,String reasonForVisitIn,  String medicalEquipIn,String consultantTypeIn,String dateIn) {
        appNumber=appNumIn;
        reasonForVisit = reasonForVisitIn;
        consultantType = consultantTypeIn;
        medicalEquip = medicalEquipIn;
        date=dateIn;
    }


    public Appointment(String reasonForVisitIn,  String medicalEquipIn,String consultantTypeIn,int patientNumIn) {
        reasonForVisit = reasonForVisitIn;
        consultantType = consultantTypeIn;
        medicalEquip = medicalEquipIn;
        patientNum=patientNumIn;
        setAppointmentExisting(reasonForVisit, medicalEquip, consultantType, patientNum);
//        printAppointment();
    }

    public void appointmentArray() {
        appList.removeAll(appList);
        ao=new AppointmentOperations();
        try {
            rset = ao.getAppointment();
            while (rset.next()) {
                appList.add(apt = new Appointment(rset.getInt(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5)));
            }ao.appointmentOperationsClose();
        } catch (SQLException e1) {
            System.out.println(e1);
        }
    }

//    public void printAppointment() {
//        for (int i = 0; i < appList.size(); i++) {
//            System.out.println("\n\nAppointment number (" + appList.get(i).appNumber + ")");
//            System.out.println("Reason for visit (" + appList.get(i).reasonForVisit + ")");
//            System.out.println("Equipment (" + appList.get(i).consultantType + ")");
//            System.out.println("Consultant (" + appList.get(i).medicalEquip + ")");
//        }
//    }



    public ArrayList appArray(){
        appointmentArray();
        return appList;
    }

    public int getAppNumber() {
        return appNumber;
    }


    public int getSize() {
        return appList.size()+1;
    }

    public String getConsultantType() {
        return consultantType;
    }

    public String getReasonForVisit() {
        return reasonForVisit;
    }

    public String getMedicalEquip() {
        return medicalEquip;
    }

    public String getDate() {
        return date;
    }

    public int getPatientNum() {
        return patientNum;
    }



    public String getConNameFromSkill(String skillIn){
        String swap="";
        conList.removeAll(conList);
        consultants=new Consultants();
        conList.addAll(consultants.getConsultants());
        for (int i = 0; i <conList.size() ; i++) {
            if(conList.get(i).getEquipSill().equals(skillIn)){
                swap=conList.get(i).getConName();
            }
        }return swap;
    }



    public void setAppointmentExisting(String recIn, String equipIn,String conIn,int patientNumIn) {
        ao=new AppointmentOperations();
        ao.addAppointmentExisting(recIn, equipIn, conIn,patientNumIn,Prescription.getCurrentTimeStamp());
        if (equipIn.equals("")) {
            TimeTables timeTableCON = new TimeTables(conIn);
            ao.appointmentOperationsClose();
        } else{
            TimeTables timeTableMED = new TimeTables(equipIn,conIn);
            ao.appointmentOperationsClose();
        }
    }


    public void cancelAppointment(int appointmentNumber) {
        ao=new AppointmentOperations();
        ao.deleteAppointment(appointmentNumber);
        ao.appointmentOperationsClose();
    }
}

