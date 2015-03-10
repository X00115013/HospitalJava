package Test;

import DataBase.HospitalOperations;
import GUI.AppointmentGUI;
import GUI.ReferralGUI;
import GUI.SecurityGUI;
import Model.Appointment;
import Model.ProcessReferrals;
import Model.Referrals;
import Model.TimeTables;

/**
 * Created by Roland on 01/03/2015.
 */
public class TestHospital {
    public static void main(String[] args) {
        HospitalOperations ho = new HospitalOperations();
//        AppointmentGUI ag=new AppointmentGUI();
//        ag.setVisible(true);
//        SecurityGUI su=new SecurityGUI();
//        su.setVisible(true);
//        Referrals referrals= new Referrals();
//        ReferralGUI referralGUI= new ReferralGUI(referrals);
//        referralGUI.setVisible(true);
        Appointment ap=new Appointment("Tom",3,1);
//        Appointment ap1=new Appointment("Ava",2,-1);
//        Appointment ap2=new Appointment("Cain",1,3);
//        ProcessReferrals processReferrals = new ProcessReferrals();



//        Run the create table first then ful the two below and the any of the appointment above(together or single. system will be single run)

//        TimeTables tt=new TimeTables();
//        tt.setFree();

    }
}
