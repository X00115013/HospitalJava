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
    private ResultSet rset = null;
    private ArrayList<Appointment> appList = new ArrayList<Appointment>();
    private AppointmentOperations ao;
    private Appointment apt;


    public Appointment(){
        appointmentArray();
    }

    public Appointment(int appNumIn ,String reasonForVisitIn, String consultantTypeIn, String medicalEquipIn, int patientNumIn) {
        appNumber=appNumIn;
        reasonForVisit = reasonForVisitIn;
        consultantType = consultantTypeIn;
        medicalEquip = medicalEquipIn;
        patientNum=patientNumIn;
    }


    public Appointment(String reasonForVisitIn, String consultantTypeIn, String medicalEquipIn,int patientNumIn) {
        reasonForVisit = reasonForVisitIn;
        consultantType = consultantTypeIn;
        medicalEquip = medicalEquipIn;
        patientNum=patientNumIn;
        setAppointmentExisting(reasonForVisit,consultantType, medicalEquip,  patientNum);
        printAppointment();
    }


    public Appointment(String reasonForVisitIn, String consultantTypeIn, String medicalEquipIn) {
        reasonForVisit = reasonForVisitIn;
        consultantType = consultantTypeIn;
        medicalEquip = medicalEquipIn;
        setAppointment(reasonForVisit, consultantType, medicalEquip);
        printAppointment();
    }

    public void appointmentArray() {
        appList.removeAll(appList);
        ao=new AppointmentOperations();
        try {
            rset = ao.getAppointment();
            System.out.println("\n\n\nAppointment list\n");
            while (rset.next()) {
                appList.add(apt = new Appointment(rset.getInt(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getInt(5)));
            }ao.appointmentOperationsClose();
        } catch (SQLException e1) {
            System.out.println(e1);
        }
    }

    public void printAppointment() {
        for (int i = 0; i < appList.size(); i++) {
            System.out.println("\n\nAppointment number (" + appList.get(i).appNumber + ")");
            System.out.println("Reason for visit (" + appList.get(i).reasonForVisit + ")");
            System.out.println("Equipment (" + appList.get(i).consultantType + ")");
            System.out.println("Consultant (" + appList.get(i).medicalEquip + ")");
        }
    }



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


    public void setAppointmentExisting(String recIn, String equipIn,String conIn,int patientNumIn) {
        ao=new AppointmentOperations();
        ao.addAppointmentExisting(recIn, equipIn, conIn,patientNumIn);
        if (equipIn.equals("")) {
            TimeTables timeTableCON = new TimeTables(conIn,consultantType);
            ao.appointmentOperationsClose();
        } else{
            System.out.println("Existing app:tt set");
            TimeTables timeTableMED = new TimeTables(equipIn,conIn);
            ao.appointmentOperationsClose();
        }
    }


    public void setAppointment(String recIn, String equipIn,String conIn) {
        ao=new AppointmentOperations();
        ao.addAppointment(recIn, equipIn, conIn);
        if (equipIn.equals("")) {
            System.out.println("should not be here con new app");
            TimeTables timeTableCON = new TimeTables(equipIn,conIn);
            ao.appointmentOperationsClose();
        } else{
            System.out.println("New app:tt set eq: "+equipIn+"  con: "+conIn);
            TimeTables timeTableMED = new TimeTables(equipIn,conIn);
            ao.appointmentOperationsClose();
        }
    }


    public void cancelAppointment(int appointmentNumber, String patientFName, String patientSurname, String patientDOB) {
        if (appointmentNumber == -1) {
                ao.deleteAppointment(ao.getAppointmentNum(patientFName, patientSurname,patientDOB));

        }else{
            ao.deleteAppointment(appointmentNumber);
        }
    }
}

