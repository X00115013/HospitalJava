package Model;
import DataBase.AppointmentOperations;
import DataBase.HospitalOperations;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DataBase.HospitalOperations;
import DataBase.TimeTableOperations;
import Model.*;

/**
* Created by Roland on 04/03/2015.
*/
public class TimeTables {

    private int time,appNum;
    private String mon, tues, wed, thur, fri, sat, sun;
    private String week1, week2, week3, week4;
    private ResultSet rset;
    private ArrayList<ResultSet> consultantList = new ArrayList<ResultSet>();
    private ArrayList<Appointment> appList = new ArrayList<Appointment>();
    private ArrayList<XRayTimeTable> xRayTimeTable = new ArrayList<XRayTimeTable>();
    private ArrayList<MRITimeTable> mRITimeTable = new ArrayList<MRITimeTable>();
    private ArrayList<CTTimeTables> cTScanTimeTable = new ArrayList<CTTimeTables>();
    private ArrayList<ConsultantTimeTable> consultantTimeTable = new ArrayList<ConsultantTimeTable>();
    private TimeTableOperations to = new TimeTableOperations();
    private int medEquip,conReq;
    private String free="Free";
    private XRayTimeTable xRayT;
    private MRITimeTable mriT;
    private CTTimeTables ctT;
    private ConsultantTimeTable consultantT;
    private AppointmentOperations ao;
    private Appointment apt;
    int ind=0;

    public TimeTables() {

    }

    public TimeTables(int fake, int conReqIn) {
        conReq=conReqIn;
        clearArrays();
        refreshTimeTables();
        setFromConReq(conReq);


    }

    public TimeTables(int medEquipIn) {
        medEquip=medEquipIn;
        clearArrays();
        refreshTimeTables();
       setFromMedReq(medEquip);


    }


    public void refreshTimeTables() {
        rset = to.getXRayTT();
        try {
            while (rset.next()) {
                xRayTimeTable.add(xRayT=new XRayTimeTable(rset.getInt(2), rset.getString(3),rset.getString(4)));
                }
            System.out.println("we are getting to xRayTT");
        } catch (SQLException e1) {
            System.out.println(e1);
        }

        rset = to.getMRIScanTT();
        try {
            while (rset.next()) {
                mRITimeTable.add(mriT= new MRITimeTable(rset.getInt(2),rset.getString(3),rset.getString(4)));
            }
            System.out.println("we are getting to MriTT");
        } catch (SQLException e1) {
            System.out.println(e1);
        }

        rset = to.getCTScanTT();
        try {
            while (rset.next()) {
                cTScanTimeTable.add(ctT =new CTTimeTables( rset.getInt(2),rset.getString(3),rset.getString(4)));
            }
            System.out.println("we are getting to cTTT");
        } catch (SQLException e1) {
            System.out.println(e1);
        }

        rset = to.getConsultantTT();
        try {
            while (rset.next()) {
                consultantTimeTable.add(consultantT= new ConsultantTimeTable(rset.getInt(2),rset.getString(3)));
            }
            System.out.println("we are getting to conTT");
        } catch (SQLException e1) {
            System.out.println(e1);
        }
        rset = to.getConsultant();
        try {
            while (rset.next()) {
                System.out.println("Consultants list should be here\t"+rset.getInt(1)+"\t"+rset.getString(2));
            }
            System.out.println("we are getting to Consultant list");
        } catch (SQLException e1) {
            System.out.println(e1);
        }
        ao = new AppointmentOperations();
        try {
            rset = ao.getAppointment();
            System.out.println("we are getting to Appointment list");
            while (rset.next()) {
//                appList.add(apt = new Appointment(rset.getString(2), rset.getInt(3), rset.getInt(4)));
                System.out.println("Whats in Appointment\t " + rset.getInt(1) + " \t" + rset.getString(2) + " \t" + rset.getInt(3) + " \t" + rset.getInt(4)+"\t"+rset.getInt(5));
            }
        } catch (SQLException e1) {
            System.out.println(e1);
        }
        System.out.println("And here");
    }


    public void printXRayTimeTables() {
        rset = to.getXRayTT();
        try {
            while (rset.next()) {
                System.out.println("xray should be here\t" + rset.getInt(1) + "\t" + rset.getInt(2) + "\t" + rset.getString(3) + "\t" + rset.getString(4) + "\t" + rset.getInt(5));
            }
            System.out.println("we are getting to xRayTT");
            printConsulTimeTables();
        } catch (SQLException e1) {
            System.out.println(e1);
        }
    }
    public void printMRITimeTables() {
        rset = to.getMRIScanTT();
        try {
            while (rset.next()) {
                System.out.println("MRI should be here\t" + rset.getInt(1) + "\t" + rset.getInt(2) + "\t" + rset.getString(3) + "\t" + rset.getString(4) + "\t" + rset.getInt(5));
            }
            System.out.println("we are getting to MriTT");
            printConsulTimeTables();
        } catch (SQLException e1) {
            System.out.println(e1);
        }
    }
    public void printCTTimeTables() {
        rset = to.getCTScanTT();
        try {
            while (rset.next()) {
                System.out.println("CT should be here\t" + rset.getInt(1) + "\t" + rset.getInt(2) + "\t" + rset.getString(3) + "\t" + rset.getString(4) + "\t" + rset.getInt(5));
            }
            System.out.println("we are getting to cTTT");
            printConsulTimeTables();
        } catch (SQLException e1) {
            System.out.println(e1);
        }
    }

    public void printConsulTimeTables() {
        rset = to.getConsultantTT();
        try {
            while (rset.next()) {
                System.out.println("ConTT should be here \t"+rset.getInt(1)+"\t"+rset.getInt(2)+"\t"+rset.getString(3)+ "\t" + rset.getInt(4));
            }
            System.out.println("we are getting to conTT");
        } catch (SQLException e1) {
            System.out.println(e1);
        }
        rset = to.getConsultant();
        try {
            while (rset.next()) {
                System.out.println("Consultants list should be here\t"+rset.getInt(1)+"\t"+rset.getString(2));
            }
            System.out.println("we are getting to Consultant list");
        } catch (SQLException e1) {
            System.out.println(e1);
        }

    }


    public void setFromMedReq(int medType){
        ind=0;
        String taken = "Taken";
        String freeCheck="";
            if (medType == 1) {
                for (int i = 0; i < xRayTimeTable.size(); i++){
                    freeCheck = xRayTimeTable.get(i).getTaken();
                    if(freeCheck.equals(free)) {
                        xRayT = new XRayTimeTable(i, taken, to.getConsultantName(1));
                        xRayT.setTable();
                        printXRayTimeTables();
                        i = xRayTimeTable.size() + 1;
                    }else{
                        System.out.println("XRay All booked out: to screen");
                    }
                }
            } else if(medType == 2){
                for (int i = 0; i < mRITimeTable.size(); i++) {
                    freeCheck = mRITimeTable.get(i).getTaken();
                    if (freeCheck.equals(free)) {
                        mriT = new MRITimeTable(i, taken, to.getConsultantName(2));
                        mriT.setTable();
                        printMRITimeTables();
                        i = mRITimeTable.size() + 1;
                    }else{
                        System.out.println("MRI All booked out: to screen");
                    }
                }
            } else if(medType == 3){
                for (int i = 0; i < cTScanTimeTable.size(); i++) {
                    freeCheck = cTScanTimeTable.get(i).getTaken();
                    if (freeCheck.equals(free)) {
                        ctT = new CTTimeTables(i, taken, to.getConsultantName(3));
                        ctT.setTable();
                        printCTTimeTables();
                        i = cTScanTimeTable.size() + 1;
                    }else{
                        System.out.println("CT Scan All booked out: to screen");
                    }
                }
            } else {
                System.out.println("Number did not match");
            }
            System.out.println("You made it this far THE END");
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
        for (int i= 0; i < xRayTimeTable.size() ; i++) {
            xRayTimeTable.remove(i);
            System.out.println("Array cleared "+i);

        }
        for (int i= 0; i < mRITimeTable.size() ; i++) {
            mRITimeTable.remove(i);
            System.out.println("Array cleared "+i);

        }
        for (int i= 0; i < cTScanTimeTable.size() ; i++) {
            cTScanTimeTable.remove(i);
            System.out.println("Array cleared "+i);

        }
        for (int i= 0; i < consultantTimeTable.size() ; i++) {
            consultantTimeTable.remove(i);
            System.out.println("Array cleared "+i);
        }
    }

    public void setFree(){
        to=new TimeTableOperations();
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
