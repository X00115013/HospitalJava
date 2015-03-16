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

    public CTTimeTables(int timeIn,String taken,String consultantNameIn){
        this.timeIn=timeIn;
        this.taken=taken;
        this.consultantNameIn=consultantNameIn;
    }


    public CTTimeTables(TimeTableOperations to,int timeIn,String taken,String consultantNameIn){
        this.to=to;
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
        cont= new ConsultantTimeTable(timeIn + 1,to.getConsultantName(3));
        cont.setTable();
        to.setCTTimeTable(timeIn + 1, taken, 3);
    }
}
