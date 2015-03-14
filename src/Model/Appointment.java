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
//    private ArrayList<Appointment> appList = new ArrayList<Appointment>();
    private ResultSet rset = null;
    private AppointmentOperations ao;
    private Appointment apt;


    public Appointment(String reasonForVisitIn, int consultantTypeIn, int medicalEquipIn) {
        reasonForVisit = reasonForVisitIn;
        consultantType = consultantTypeIn;
        medicalEquip = medicalEquipIn;
        setAppointment(reasonForVisit,medicalEquip,consultantType);
        ao = new AppointmentOperations();
        try {
            rset = ao.getAppointment();
            System.out.println("we are getting to here");
            while (rset.next()) {
//                appList.add(apt = new Appointment(rset.getInt(1),rset.getString(2), rset.getInt(3), rset.getInt(4),rset.getInt(5)));
                System.out.println("Whats in Appointment\t " + rset.getInt(1) + " \t" + rset.getString(2) + " \t" + rset.getInt(3) + " \t" + rset.getInt(4)+"\t"+rset.getInt(5));
                appNumber=rset.getInt(1);
            }
        } catch (SQLException e1) {
            System.out.println("stupid crap");
        }
        System.out.println("And here");
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

    public void setTimeTableMED() {
        TimeTables timeTableMED = new TimeTables(medicalEquip);

    }
    public void setTimeTableCON() {
        TimeTables timeTableCON = new TimeTables(1,consultantType);

    }

    public void setAppointment(String recIn, int equipIn,int conIn) {
        ao = new AppointmentOperations();
        ao.addAppointment(recIn, equipIn, conIn);
        if (medicalEquip == -1) {
            setTimeTableCON();
        } else{
            System.out.println("all the way down here");
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

