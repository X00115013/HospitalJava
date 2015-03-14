package Model;

import DataBase.TimeTableOperations;

/**
 * Created by x00115013 on 09/03/2015.
 */
public class MRITimeTable {
    private int timeIn,appNum;
    private String taken;
    private String consultantNameIn;
    private TimeTableOperations to = new TimeTableOperations();;
    private ConsultantTimeTable cont;

    public MRITimeTable(int timeIn,String taken,String consultantNameIn){
        this.timeIn=timeIn;
        this.taken=taken;
        this.consultantNameIn=consultantNameIn;
    }

    public int getTimeIn() {
        return timeIn;
    }

    public String getTaken() {
        return taken;
    }

    public String getConsultantNumIn() {
        return consultantNameIn;
    }

    public void setTable(){
        cont= new ConsultantTimeTable(timeIn + 1,to.getConsultantName(2));
        cont.setTable();
        to.setMRITimeTable(timeIn + 1, taken,2);
    }
}
