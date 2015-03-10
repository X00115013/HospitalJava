package Model;
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

    private int time;
    private String mon, tues, wed, thur, fri, sat, sun;
    private String week1, week2, week3, week4;
    private ResultSet rset;
    private ArrayList<ResultSet> xRayList = new ArrayList<ResultSet>();
    private ArrayList<ResultSet> mRIList = new ArrayList<ResultSet>();
    private ArrayList<ResultSet> cTScanList = new ArrayList<ResultSet>();
    private ArrayList<ResultSet> consultantList = new ArrayList<ResultSet>();
    private ArrayList<XRayTimeTable> xRayTimeTable = new ArrayList<XRayTimeTable>();
    private ArrayList<MRITimeTable> mRITimeTable = new ArrayList<MRITimeTable>();
    private ArrayList<CTTimeTables> cTScanTimeTable = new ArrayList<CTTimeTables>();
    private ArrayList<ConsultantTimeTable> consultantTimeTable = new ArrayList<ConsultantTimeTable>();
    private TimeTableOperations to;
    private int medEquip,conReq;
    private String free="Free";
    private XRayTimeTable xRayT;
    private MRITimeTable mriT;
    private CTTimeTables ctT;
    private ConsultantTimeTable consultantT;

    public TimeTables() {
        refreshTimeTables();

    }

    public TimeTables(int fake, int conReqIn) {
        conReq=conReqIn;
        refreshTimeTables();
        setFromConReq(conReq);


    }

    public TimeTables(int medEquipIn) {
        medEquip=medEquipIn;
        refreshTimeTables();
        setFromMedReq(medEquip);


    }


    public void refreshTimeTables() {
        to = new TimeTableOperations();
        rset = to.getXRayTT();
        try {
            while (rset.next()) {
                xRayTimeTable.add(xRayT=new XRayTimeTable(rset.getInt(2), rset.getString(3),rset.getString(4),rset.getInt(5)));
                System.out.println("xray should be here\t" + rset.getInt(1) + "\t" + rset.getInt(2) + "\t" + rset.getString(3)+ "\t" + rset.getString(4) + "\t" + rset.getInt(5));
                }
            System.out.println("we are getting to xRayTT");
        } catch (SQLException e1) {
            System.out.println(e1);
        }

        rset = to.getMRIScanTT();
        try {
            while (rset.next()) {
                mRITimeTable.add(mriT= new MRITimeTable(rset.getInt(2),rset.getString(3),rset.getString(4),rset.getInt(5)));
                System.out.println("MRI should be here\t" + rset.getInt(1) + "\t" + rset.getInt(2) + "\t" + rset.getString(3)+ "\t" + rset.getString(4) + "\t" + rset.getInt(5));
            }
            System.out.println("we are getting to MriTT");
        } catch (SQLException e1) {
            System.out.println(e1);
        }

        rset = to.getCTScanTT();
        try {
            while (rset.next()) {
                cTScanTimeTable.add(ctT =new CTTimeTables( rset.getInt(2),rset.getString(3),rset.getString(4),rset.getInt(5)));
                System.out.println("CT should be here\t" + rset.getInt(1) + "\t" + rset.getInt(2) + "\t" + rset.getString(3) + "\t" + rset.getString(4)+ "\t" + rset.getInt(5));
            }
            System.out.println("we are getting to cTTT");
        } catch (SQLException e1) {
            System.out.println(e1);
        }

        rset = to.getConsultantTT();
        try {
            while (rset.next()) {
                consultantTimeTable.add(consultantT= new ConsultantTimeTable(rset.getInt(2),rset.getString(3),rset.getInt(4)));
                System.out.println("ConTT should be here \t"+rset.getInt(1)+"\t"+rset.getInt(2)+"\t"+rset.getString(3)+ "\t" + rset.getInt(4));
            }
            System.out.println("we are getting to conTT");
        } catch (SQLException e1) {
            System.out.println(e1);
        }
        rset = to.getConsultant();
        try {
            while (rset.next()) {
                consultantList.add(rset);
                System.out.println("Consultants list should be here\t"+rset.getInt(1)+"\t"+rset.getString(2));
            }
            System.out.println("we are getting to Consultant list");
        } catch (SQLException e1) {
            System.out.println(e1);
        }

    }


    public void setFromMedReq(int medType) {
        int time=0,ind=0,conNum=0;
        String taken = "Taken";
        String freeCheck="";
        try {
                if (medType == 1) {
                    for (int i = 0; i < xRayTimeTable.size(); i++) {
                        freeCheck = xRayTimeTable.get(i).getTaken();
                        if(ind==0){
                            if (freeCheck.equals(free)) {
                                to.setConsultantTimeTable(i + 1, 1);
                                to.setXRayTimeTable(i + 1, taken, 1);
                                i = xRayTimeTable.size() + 1;
                                System.out.println("Am I even getting here 1");
                                ind = 1;
                            }
                        }
                    }
                }
                if (medType == 2) {
                    ind=0;
                    for (int i = 0; i < mRITimeTable.size(); i++) {
                        System.out.println("\n\nhas 2");
                        freeCheck = mRITimeTable.get(i).getTaken();
                        if (ind == 0) {
                            if (freeCheck.equals(free)) {
                                to.setConsultantTimeTable(i + 1, 2);
                                to.setMRITimeTable(i + 1, taken, 2);
                                System.out.println("Am I even getting here 2");
                                i = mRITimeTable.size();
                                ind = 1;
                            }
                        }
                    }
                }
                if (medType == 3) {
                    ind=0;
                    for (int i = 0; i < cTScanTimeTable.size(); i++) {
                        System.out.println("\n\nhas 3");
                        freeCheck = cTScanTimeTable.get(i).getTaken();
                        if(ind==0){
                            if (freeCheck.equals(free)) {
                                to.setConsultantTimeTable(i + 1, 3);
                                to.setCTTimeTable(i + 1, taken, 3);
                                System.out.println("Am I even getting here 3");
                                i = cTScanTimeTable.size();
                                ind = 0;
                        }
                        }
                    }
                }
                else{
                    System.out.println("Number did not match");

                }

            System.out.println("You made it this far THE END");

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void setFromConReq(int conRegIn){
        time=consultantTimeTable.size();
        to.setConsultantTimeTable(time,conRegIn);

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
    }

    public void setFree(){
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
    }
}
