package Model;

import DataBase.TimeTableOperations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Thomas Murray on 09/03/2015.
 */
public class ConsultantTimeTable {
    private int timeIn,appNum;
    private String consultantNameIn;
    private ArrayList<ConsultantTimeTable> consultantTimeTable = new ArrayList<ConsultantTimeTable>();
    private TimeTableOperations to;
    private ResultSet rset;


    public ConsultantTimeTable(){
        refreshTable();

    }

    public ConsultantTimeTable(int num,int timeIn,String consultantNameIn,int appID){
        this.timeIn=timeIn;
        this.consultantNameIn=consultantNameIn;
        appNum=appID;
    }

    public ConsultantTimeTable(int timeIn,String consultantNameIn){
        this.timeIn=timeIn;
        this.consultantNameIn=consultantNameIn;
        setTable();
    }

    public void refreshTable() {
        to=new TimeTableOperations();
        rset = to.getConsultantTT();
        try {
            while (rset.next()) {
                 consultantTimeTable.add(new ConsultantTimeTable(rset.getInt(1),rset.getInt(2),rset.getString(3),rset.getInt(4)));
            }to.TimeTableOperationsClose();
        } catch (SQLException e1) {
            System.out.println(e1);
        }
    }

    public ArrayList<ConsultantTimeTable> getConsultantTimeTable() {
        return consultantTimeTable;
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
