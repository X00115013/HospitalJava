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
    private ArrayList<AllTimeTables>allTimeTables=new ArrayList<>();
    private ArrayList<ConsultantTimeTable> consultantTimeTable = new ArrayList<ConsultantTimeTable>();
    private ArrayList<Equipment>equipments=new ArrayList<>();
    private TimeTableOperations to;
    private String req,selection;
    private String free="Free";
    private ConsultantTimeTable consultantT;
    private Equipment equipment;
    int ind=0;
    private AllTimeTables allT;

    public TimeTables() {
        to=new TimeTableOperations();
        refreshTimeTables();
    }

    public TimeTables(String reqIn) {
        to=new TimeTableOperations();
        req=reqIn;
        refreshTimeTables();
        setFromConReq(req);
        to.TimeTableOperationsClose();
    }

    public TimeTables(String selectionIn, String reqIn) {
        to=new TimeTableOperations();
        req=reqIn;
        selection=selectionIn;
        refreshTimeTables();
        setFromMedReq(selection,req);
        to.TimeTableOperationsClose();
        }

    public void refreshTimeTables() {
        allTimeTables.removeAll(allTimeTables);
        equipments.removeAll(equipments);
        allT=new AllTimeTables();
        allTimeTables.addAll(allT.getList(selection));
        equipment= new Equipment();
        equipments.addAll(equipment.getEquipments());
    }


    public void setFromMedReq(String equipType ,String conIn){
        String taken = "Taken";
        for (int i = 0; i < allTimeTables.size(); i++) {
                if (allTimeTables.get(i).getTaken().equals(free)) {
                    allT = new AllTimeTables( equipType, i, taken, conIn);
                    i = allTimeTables.size() + 1;
                }
            }
        }


    public void setFromConReq(String conRegIn){
        consultantT=new ConsultantTimeTable(time,conRegIn);
    }

    public void setFree(){
        int spinner=0;
        for (int i = 0; i < 297; i++) {
            to.setTTFree(equipments.get(spinner).getEqName(), free);
            spinner++;
            if(spinner==equipments.size()){
                spinner=0;
            }
        }to.TimeTableOperationsClose();
        to=new TimeTableOperations();
        for (int i = 0; i < 297; i++) {
            to.setTTFree(equipments.get(spinner).getEqName(),free);
            spinner++;
            if(spinner==equipments.size()){
                spinner=0;
            }
        }to.TimeTableOperationsClose();
        to=new TimeTableOperations();
        for (int i = 0; i < 297; i++) {
            to.setTTFree(equipments.get(spinner).getEqName(),free);
            spinner++;
            if(spinner==equipments.size()){
                spinner=0;
            }
        }to.TimeTableOperationsClose();
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