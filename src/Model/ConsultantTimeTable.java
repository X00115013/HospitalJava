package Model;

/**
 * Created by x00115013 on 09/03/2015.
 */
public class ConsultantTimeTable {
    private int timeIn,appNum;
    private String consultantNameIn;

    public ConsultantTimeTable(int timeIn,String consultantNameIn,int appNumIn){
        this.appNum=appNumIn;
        this.timeIn=timeIn;
        this.consultantNameIn=consultantNameIn;
    }

    public int getTimeIn() {
        return timeIn;
    }

    public String getConsultantNameIn() {
        return consultantNameIn;
    }
}
