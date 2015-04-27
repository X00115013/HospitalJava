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
    public String consultantType, medicalEquip;
    public int patientNum, appNumber;
    public String date;
    private ResultSet rset = null;
    private ArrayList<Appointment> appList = new ArrayList<Appointment>();
    private AppointmentOperations ao;
    private Appointment apt;

    public Appointment() {
    }

    public Appointment(int appNumIn, String reasonForVisitIn, String medicalEquipIn, String consultantTypeIn, String dateIn) {
        appNumber = appNumIn;
        reasonForVisit = reasonForVisitIn;
        consultantType = consultantTypeIn;
        medicalEquip = medicalEquipIn;
        date = dateIn;
    }

    public Appointment(String reasonForVisitIn, String medicalEquipIn, String consultantTypeIn, int patientNumIn) {
        reasonForVisit = reasonForVisitIn;
        consultantType = consultantTypeIn;
        medicalEquip = medicalEquipIn;
        patientNum = patientNumIn;
        setAppointmentExisting(reasonForVisit, medicalEquip, consultantType, patientNum);
    }

    public void appointmentArray() {
        appList.removeAll(appList);
        ao = new AppointmentOperations();
        try {
            rset = ao.getAppointment();
            while (rset.next()) {
                appList.add(apt = new Appointment(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5)));
            }
            ao.appointmentOperationsClose();
        } catch (SQLException e1) {
            System.out.println(e1);
        }
    }

    public ArrayList appArray() {
        appointmentArray();
        return appList;
    }

    public int getAppNumber() {
        return appNumber;
    }

    public int getSize() {
        return appList.size() + 1;
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

    public void setAppointmentExisting(String recIn, String equipIn, String conIn, int patientNumIn) {
        ao = new AppointmentOperations();
        ao.addAppointmentExisting(recIn, equipIn, conIn, patientNumIn, Prescription.getCurrentTimeStamp());
        if (equipIn.equals("None Booked")) {
            TimeTables timeTableCON = new TimeTables(conIn);
            ao.appointmentOperationsClose();
        } else {
            TimeTables timeTableMED = new TimeTables(equipIn, conIn);
            ao.appointmentOperationsClose();
        }
    }

    public void cancelAppointment(int appointmentNumber) {
        ao = new AppointmentOperations();
        ao.deleteAppointment(appointmentNumber);
        ao.appointmentOperationsClose();
    }

    public String getAppointmentTime(int appTime) {
        String time = "";
        if (appTime % 42 == 1) {
            time += "Monday at 12 AM";
            if(appTime==43){
                time+=" One Week From Date Set";
            }
            if(appTime==85){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 2) {
            time += "Monday at 4 AM";
            if(appTime==44){
                time+=" One Week From Date Set";
            }
            if(appTime==86){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 3) {
            time += "Monday at 8 AM";
            if(appTime==45){
                time+=" One Week From Date Set";
            }
            if(appTime==87){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 4) {
            time += "Monday at 12 PM";
            if(appTime==46){
                time+=" One Week From Date Set";
            }
            if(appTime==88){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 5) {
            time += "Monday at 4 PM";
            if(appTime==47){
                time+=" One Week From Date Set";
            }
            if(appTime==89){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 6) {
            time += "Monday at 8 PM";
            if(appTime==48){
                time+=" One Week From Date Set";
            }
            if(appTime==90){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 7) {
            time += "Tuesday at 12 AM";
            if(appTime==49){
                time+=" One Week From Date Set";
            }
            if(appTime==91){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 8) {
            time += "Tuesday at 4 AM";
            if(appTime==50){
                time+=" One Week From Date Set";
            }
            if(appTime==92){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 9) {
            time += "Tuesday at 8 AM";
            if(appTime==51){
                time+=" One Week From Date Set";
            }
            if(appTime==93){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 10) {
            time += "Tuesday at 12 PM";
            if(appTime==52){
                time+=" One Week From Date Set";
            }
            if(appTime==94){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 11) {
            time += "Tuesday at 4 PM";
            if(appTime==53){
                time+=" One Week From Date Set";
            }
            if(appTime==95){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 12) {
            time += "Tuesday at 8 PM";
            if(appTime==54){
                time+=" One Week From Date Set";
            }
            if(appTime==96){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 13) {
            time += "Wednesday at 12 AM";
            if(appTime==55){
                time+=" One Week From Date Set";
            }
            if(appTime==97){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 14) {
            time += "Wednesday at 4 AM";
            if(appTime==56){
                time+=" One Week From Date Set";
            }
            if(appTime==98){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 15) {
            time += "Wednesday at 8 AM";
            if(appTime==57){
                time+=" One Week From Date Set";
            }
            if(appTime==99){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 16) {
            time += "Wednesday at 12 PM";
            if(appTime==58){
                time+=" One Week From Date Set";
            }
            if(appTime==100){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 17) {
            time += "Wednesday at 4 PM";
            if(appTime==59){
                time+=" One Week From Date Set";
            }
            if(appTime==101){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 18) {
            time += "Wednesday at 8 PM";
            if(appTime==60){
                time+=" One Week From Date Set";
            }
            if(appTime==102){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 19) {
            time += "Thursday at 12 AM";
            if(appTime==61){
                time+=" One Week From Date Set";
            }
            if(appTime==103){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 20) {
            time += "Thursday at 4 AM";
            if(appTime==62){
                time+=" One Week From Date Set";
            }
            if(appTime==104){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 21) {
            time += "Thursday at 8 AM";
            if(appTime==63){
                time+=" One Week From Date Set";
            }
            if(appTime==105){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 22) {
            time += "Thursday at 12 PM";
            if(appTime==64){
                time+=" One Week From Date Set";
            }
            if(appTime==106){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 23) {
            time += "Thursday at 4 PM";
            if(appTime==65){
                time+=" One Week From Date Set";
            }
            if(appTime==107){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 24) {
            time += "Thursday at 8 PM";
            if(appTime==66){
                time+=" One Week From Date Set";
            }
            if(appTime==108){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 25) {
            time += "Friday at 12 AM";
            if(appTime==67){
                time+=" One Week From Date Set";
            }
            if(appTime==109){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 26) {
            time += "Friday at 4 AM";
            if(appTime==68){
                time+=" One Week From Date Set";
            }
            if(appTime==110){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 27) {
            time += "Friday at 8 AM";
            if(appTime==69){
                time+=" One Week From Date Set";
            }
            if(appTime==111){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 28) {
            time += "Friday at 12 PM";
            if(appTime==70){
                time+=" One Week From Date Set";
            }
            if(appTime==112){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 29) {
            time += "Friday at 4 PM";
            if(appTime==71){
                time+=" One Week From Date Set";
            }
            if(appTime==113){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 30) {
            time += "Friday at 8 PM";
            if(appTime==72){
                time+=" One Week From Date Set";
            }
            if(appTime==114){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 31) {
            time += "Saturday at 12 AM";
            if(appTime==73){
                time+=" One Week From Date Set";
            }
            if(appTime==115){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 32) {
            time += "Saturday at 4 AM";
            if(appTime==74){
                time+=" One Week From Date Set";
            }
            if(appTime==116){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 33) {
            time += "Saturday at 8 AM";
            if(appTime==75){
                time+=" One Week From Date Set";
            }
            if(appTime==117){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 34) {
            time += "Saturday at 12 PM";
            if(appTime==76){
                time+=" One Week From Date Set";
            }
            if(appTime==118){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 35) {
            time += "Saturday at 4 PM";
            if(appTime==77){
                time+=" One Week From Date Set";
            }
            if(appTime==119){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 36) {
            time += "Saturday at 8 PM";
            if(appTime==78){
                time+=" One Week From Date Set";
            }
            if(appTime==120){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 37) {
            time += "Sunday at 12 AM";
            if(appTime==79){
                time+=" One Week From Date Set";
            }
            if(appTime==121){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 38) {
            time += "Sunday at 4 AM";
            if(appTime==80){
                time+=" One Week From Date Set";
            }
            if(appTime==122){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 39) {
            time += "Sunday at 8 AM";
            if(appTime==81){
                time+=" One Week From Date Set";
            }
            if(appTime==123){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 40) {
            time += "Sunday at 12 PM";
            if(appTime==82){
                time+=" One Week From Date Set";
            }
            if(appTime==124){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 41) {
            time += "Sunday at 4 PM";
            if(appTime==83){
                time+=" One Week From Date Set";
            }
            if(appTime==125){
                time+=" In a Forth Night From Date Set";
            }
        } else if (appTime % 42 == 0) {
            time += "Sunday at 8 PM";
            if(appTime==84){
                time+=" One Week From Date Set";
            }
            if(appTime==126){
                time+=" In a Forth Night From Date Set";
            }
        }
        return time;
    }
}





