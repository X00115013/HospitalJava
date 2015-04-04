package Model;

import DataBase.TimeTableOperations;

/**
 * Created by Thomas Murray on 09/03/2015.
 */
public class ConsultantTimeTable {
    private int timeIn,appNum;
    private String consultantNameIn;
    private TimeTableOperations to;


    public ConsultantTimeTable(int timeIn,String consultantNameIn){
        this.timeIn=timeIn;
        this.consultantNameIn=consultantNameIn;
    }

    public ConsultantTimeTable(TimeTableOperations to,int timeIn,String consultantNameIn){
        this.to=to;
        this.timeIn=timeIn;
        this.consultantNameIn=consultantNameIn;
        setTable();
    }

    public int getTimeIn() {
        return timeIn;
    }

    public String getConsultantNameIn() {
        return consultantNameIn;
    }

    public void setTable(){
        to=new TimeTableOperations();
        to.setConsultantTimeTable(timeIn,consultantNameIn);
        to.TimeTableOperationsClose();
    }
}
