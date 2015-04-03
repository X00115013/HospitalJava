package Model;
import DataBase.AppointmentOperations;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DataBase.TimeTableOperations;

/**
* Created by Thomas Murray on 04/03/2015.
*/
public class TimeTables {

    private int time,appNum;
    private ResultSet rset;
    private ArrayList<ResultSet> consultantList = new ArrayList<ResultSet>();
    private ArrayList<Appointment> appList = new ArrayList<Appointment>();
    private ArrayList<AllTimeTables>allTimeTables=new ArrayList<>();
    private ArrayList<ConsultantTimeTable> consultantTimeTable = new ArrayList<ConsultantTimeTable>();
    private TimeTableOperations to;
    private int req;
    private String free="Free";
    private ConsultantTimeTable consultantT;
    private AppointmentOperations ao;
    private Appointment apt;
    int ind=0;
    private AllTimeTables allT;

    public TimeTables() {
        to=new TimeTableOperations();
        ao=new AppointmentOperations();
        refreshTimeTables();
    }

    public TimeTables(AppointmentOperations ao,int selection, int reqIn) {
        this.ao=ao;
        req=reqIn;
        if(selection==-1) {
            setFromConReq(req);
        }else{
            setFromMedReq(req);
        }
    }

    public void refreshTimeTables() {
        try {
            clearArrays();
            rset = ao.getAppointment();
            System.out.println("\n\n\nAppointment list\n");
            while (rset.next()) {
                appList.add(apt = new Appointment(rset.getInt(1), rset.getString(2), rset.getInt(3), rset.getInt(4), rset.getInt(5)));
            }ao.appointmentOperationsClose();
        } catch (SQLException e1) {
            System.out.println(e1);
        }
    }

    public ArrayList getConsultantTimeTable() {
        return consultantTimeTable;
    }




    public void setFromMedReq(int equipType){
        String taken = "Taken";
        String freeCheck="";
        for (int i = 0; i < allTimeTables.size(); i++){
            freeCheck = allTimeTables.get(i).getTaken();
            if(freeCheck.equals(free)) {
                allT = new AllTimeTables(to,equipType,i, taken, to.getConsultantName(equipType));
                allT.setTable(equipType);
                i = allTimeTables.size() + 1;
            }else{
                System.out.println("All booked out: to screen");
            }
        }
    }


    public void setFromConReq(int conRegIn){
        time=consultantTimeTable.size();
        consultantT=new ConsultantTimeTable(time,to.getConsultantName(conRegIn));
    }



    public void cancelTimeTableEntry(int appointmentNumberIn){
        for (int i = 0; i < appList.size(); i++) {
            if (appList.get(i).getAppNumber() == appointmentNumberIn) {
                if (appList.get(i).getMedicalEquip() == 1) {
                    to.cancelXRayTableEntry(appointmentNumberIn);
                } else if (appList.get(i).getMedicalEquip() == 2) {
                    to.cancelMRITableEntry(appointmentNumberIn);
                } else if (appList.get(i).getMedicalEquip() == 3) {
                    to.cancelCTTableEntry(appointmentNumberIn);
                }
                else{
                    System.out.println("Error processing time table type: log file");
                }
            }else{
                System.out.println("Appointment does not exist dialog box: to screen");
            }
        }
    }

    public void clearArrays(){
        for (int i= 0; i < allTimeTables.size() ; i++) {
            allTimeTables.remove(i);
            System.out.println("Array cleared "+i);

        }
        for (int i= 0; i < consultantTimeTable.size() ; i++) {
            consultantTimeTable.remove(i);
            System.out.println("Array cleared "+i);
        }
    }

    public void setFree(){
//        refreshTimeTables();
        for (int i = 0; i < 1 ; i++) {
            to.setXRayFree(free);
            System.out.println(i+" XRay is free");
        }
        for (int i = 0; i < 1 ; i++) {
            to.setMRIFree(free);
            System.out.println(i+" MRI is free");
        }
        for (int i = 0; i < 1 ; i++) {
            to.setCTFree(free);
            System.out.println(i+" CT is free");
        }
        refreshTimeTables();
    }
}


//    public void printConsulTimeTables() {
//        rset = to.getConsultantTT();
//        System.out.println("\n\n\nConsultants Time Table");
//        try {
//            while (rset.next()) {
//                System.out.println(""+rset.getInt(1)+"\t"+rset.getInt(2)+"\t"+rset.getString(3)+ "\t" + rset.getInt(4));
//            }
//        } catch (SQLException e1) {
//            System.out.println(e1);
//        }
//        rset = to.getConsultant();
//        System.out.println("\n\n\nConsultants list\n");
//        try {
//            while (rset.next()) {
//                System.out.println(""+rset.getInt(1)+"\t"+rset.getString(2));
//            }
//        } catch (SQLException e1) {
//            System.out.println(e1);
//        }
//
//    }