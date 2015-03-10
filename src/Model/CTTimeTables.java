package Model;

/**
 * Created by x00115013 on 09/03/2015.
 */
public class CTTimeTables {
    private int timeIn,appNum;
    private String taken;
    private String consultantNameIn;

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
}
