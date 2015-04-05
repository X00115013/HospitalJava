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
    private Consultants consultants;
    private ArrayList<Consultants>conList=new ArrayList<>();
    private TimeTableOperations to;
    private String req,selection;
    private String free="Free";
    private ConsultantTimeTable consultantT;
    private Appointment apt;
    int ind=0;
    private AllTimeTables allT;

    public TimeTables() {
        to=new TimeTableOperations();
        refreshTimeTables();
    }

    public TimeTables(String selectionIn, String reqIn) {
        to=new TimeTableOperations();
        req=reqIn;
        selection=selectionIn;
        refreshTimeTables();
        if(selection.equals("")) {
            setFromConReq(req);
        }else{
            System.out.println("setFrom Constructor tt set");
            setFromMedReq(selection,req);
        }
    }

    public void refreshTimeTables() {
            clearArrays();

            allT=new AllTimeTables();
            allTimeTables.addAll(allT.getList(selection));

            consultants= new Consultants();
            conList.addAll(consultants.getConsultants());
    }


    public void setFromMedReq(String equipType ,String conIn){
        String taken = "Taken";
        String freeCheck="";
        System.out.println("Before for setFrom "+equipType);
        for (int i = 0; i < allTimeTables.size(); i++) {
            System.out.println("after for ");
                freeCheck = (String)allTimeTables.get(i).getTaken();
                if (freeCheck.equals(free)) {
                    System.out.println("In the belly");
                    allT = new AllTimeTables( equipType, i, taken, conIn);
                    i = allTimeTables.size() + 1;
                } else {
                    System.out.println("All booked out: to screen");
                }
            }
        }




    public void setFromConReq(String conRegIn){
        consultantT=new ConsultantTimeTable(time,conRegIn);


    }


    public void cancelTimeTableEntry(int appointmentNumberIn){
//        for (int i = 0; i < appList.size(); i++) {
//            if (appList.get(i).getAppNumber() == appointmentNumberIn) {
//                if (appList.get(i).getMedicalEquip() == 1) {
//                    to.cancelXRayTableEntry(appointmentNumberIn);
//                } else if (appList.get(i).getMedicalEquip() == 2) {
//                    to.cancelMRITableEntry(appointmentNumberIn);
//                } else if (appList.get(i).getMedicalEquip() == 3) {
//                    to.cancelCTTableEntry(appointmentNumberIn);
//                }
//                else{
//                    System.out.println("Error processing time table type: log file");
//                }
//            }else{
//                System.out.println("Appointment does not exist dialog box: to screen");
//            }
//        }
    }

    public void clearArrays(){
        for (int i= 0; i < allTimeTables.size() ; i++) {
            allTimeTables.remove(i);
        }
        for (int i= 0; i < consultantTimeTable.size() ; i++) {
            consultantTimeTable.remove(i);
        }
        for (int i= 0; i < conList.size() ; i++) {
            conList.remove(i);
        }

    }

    public void setFree(){
        for (int i = 0; i < conList.size() ; i++) {
            System.out.println("Con Equip skills "+conList.get(i).getEquipSill());
            to.setTTFree(conList.get(i).getEquipSill(),free);
            System.out.println(i+" TT is free");
        }
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