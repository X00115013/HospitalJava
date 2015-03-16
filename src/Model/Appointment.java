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
    private AppointmentOperations ao=new AppointmentOperations();
    private Appointment apt;


    public Appointment(int appNumIn ,String reasonForVisitIn, int consultantTypeIn, int medicalEquipIn, int patientNumIn) {
        appNumber=appNumIn;
        reasonForVisit = reasonForVisitIn;
        consultantType = consultantTypeIn;
        medicalEquip = medicalEquipIn;
        patientNum=patientNumIn;
    }




    public Appointment(String reasonForVisitIn, int consultantTypeIn, int medicalEquipIn) {
        reasonForVisit = reasonForVisitIn;
        consultantType = consultantTypeIn;
        medicalEquip = medicalEquipIn;
        setAppointment(reasonForVisit, medicalEquip, consultantType);
        printAppointment();
    }


    public void printAppointment(){
        try {
            rset = ao.getAppointment();
            System.out.println("\nAppointment Table\n");
            while (rset.next()) {
                System.out.println("" + rset.getInt(1) + " \t" + rset.getString(2) + " \t" + rset.getInt(3) + " \t" + rset.getInt(4)+"\t"+rset.getInt(5));
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
        TimeTables timeTableMED = new TimeTables(medicalEquip);

    }
    public void setTimeTableCON() {
        TimeTables timeTableCON = new TimeTables(1,consultantType);

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

