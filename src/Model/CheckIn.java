package Model;

import DataBase.PatientOperations;

/**
 * Created by Thomas Murray on 20/03/2015.
 */
public class CheckIn {
    private int appNumber,choice;
    private String state;
    private PatientOperations po;
    public static boolean checkInStatic;

    public CheckIn(int appNumberIn,int choiceIn) {
        po=new PatientOperations();
        appNumber=appNumberIn;
        choice=choiceIn;
        updateCheckedIn();
    }

    public void updateCheckedIn(){
        if(choice==1) {
            po.updatePatientCheckIn(appNumber, "Checked IN");
            po.patientOperationsClose();
        }else if(choice==2){
            po.updatePatientCheckInByNumber(appNumber, "Medically Checked OUT");
            po.patientOperationsClose();
        }else if(choice==3){
            po.updatePatientCheckInByNumber(appNumber, "Checked OUT");
            po.patientOperationsClose();
        }

    }
}
