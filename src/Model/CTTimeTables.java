package Model;

import DataBase.TimeTableOperations;

/**
 * Created by x00115013 on 09/03/2015.
 */
public class CTTimeTables {
    private int timeIn,appNum;
    private String taken;
    private String consultantNameIn;
    private TimeTableOperations to;
    private ConsultantTimeTable cont;

    public CTTimeTables(int timeIn,String taken,String consultantNameIn, int appNumIn){
        this.timeIn=timeIn;
        this.taken=taken;
        this.appNum=appNumIn;
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
        to = new TimeTableOperations();
        cont= new ConsultantTimeTable(timeIn + 1,to.getConsultantName(3), 1);
        cont.setTable();
        to.setCTTimeTable(timeIn + 1, taken, 3);
    }
}
