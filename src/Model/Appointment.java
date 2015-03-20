package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataBase.AppointmentOperations;
/**
 * Created by x00115013 on 02/03/2015.
 */
public class Appointment {
    public int time;
    public String reasonForVisit;
    public int consultantType, appNumber;
    public int medicalEquip, patientNum;
    public String date;
    private ResultSet rset = null;
    private ArrayList<Appointment> appList = new ArrayList<Appointment>();
    private AppointmentOperations ao;
    private Appointment apt;


    public Appointment(int appNumIn ,String reasonForVisitIn, int consultantTypeIn, int medicalEquipIn, int patientNumIn) {
        appNumber=appNumIn;
        reasonForVisit = reasonForVisitIn;
        consultantType = consultantTypeIn;
        medicalEquip = medicalEquipIn;
        patientNum=patientNumIn;
    }


    public Appointment(String reasonForVisitIn, int consultantTypeIn, int medicalEquipIn,int patientNumIn) {
        reasonForVisit = reasonForVisitIn;
        consultantType = consultantTypeIn;
        medicalEquip = medicalEquipIn;
        patientNum=patientNumIn;
        appointmentArray();
        setAppointmentExisting(reasonForVisit, medicalEquip, consultantType, patientNum);
        printAppointment();
    }


    public Appointment(String reasonForVisitIn, int consultantTypeIn, int medicalEquipIn) {
        reasonForVisit = reasonForVisitIn;
        consultantType = consultantTypeIn;
        medicalEquip = medicalEquipIn;
        appointmentArray();
        setAppointment(reasonForVisit, medicalEquip, consultantType);
        printAppointment();
    }

    public void appointmentArray() {
        ao=new AppointmentOperations();
        try {
            rset = ao.getAppointment();
            System.out.println("\n\n\nAppointment list\n");
            while (rset.next()) {
                appList.add(apt = new Appointment(rset.getInt(1),rset.getString(2),rset.getInt(3),rset.getInt(4),rset.getInt(5)));
            }
        } catch (SQLException e1) {
            System.out.println(e1);
        }
    }

    public void printAppointment(){
        try {
            rset = ao.getAppointment();
            System.out.println("\nAppointment Table\n");
            while (rset.next()) {
                System.out.println("Appointment number (" + rset.getInt(1) + ") \nReason for visit (" + rset.getString(2) + ")\nEquipment (" + rset.getInt(3) + ")\nConsultant (" + rset.getInt(4)+")\nPatient Number ("+rset.getInt(5)+")\n");
            }
        } catch (SQLException e1) {
            System.out.println(e1);
        }
    }

    public int getAppNumber() {
        return appNumber;
    }

    public int getTime() {
        return time;
    }

    public int getConsultantType() {
        return consultantType;
    }

    public String getReasonForVisit() {
        return reasonForVisit;
    }

    public int getMedicalEquip() {
        return medicalEquip;
    }

    public String getDate() {
        return date;
    }

    public int getPatientNum() {
        return patientNum;
    }

    public void setTimeTableMED() {
        TimeTables timeTableMED = new TimeTables(ao,medicalEquip);

    }
    public void setTimeTableCON() {
        TimeTables timeTableCON = new TimeTables(ao,1,consultantType);

    }

    public void setAppointmentExisting(String recIn, int equipIn,int conIn,int patientNumIn) {
        ao.addAppointmentExisting(recIn, equipIn, conIn,patientNumIn);
        if (medicalEquip == -1) {
            setTimeTableCON();
        } else{
            setTimeTableMED();
        }
    }


    public void setAppointment(String recIn, int equipIn,int conIn) {
        ao.addAppointment(recIn, equipIn, conIn);
        if (medicalEquip == -1) {
            setTimeTableCON();
        } else{
            setTimeTableMED();
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

