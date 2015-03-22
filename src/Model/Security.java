package Model;

import GUI.AddPatientGUI;
import GUI.AppointmentGUI;
import GUI.CheckInGUI;

/**
 * Created by Roland on 14/03/2015.
 */
public class Security {
    private int password;
    private int passwordMed;
    private int passwordHiAdmin;
    private int passed;
    private int answer,selection;

    public Security(int selectionIn,int passWordIn) {
        selection=selectionIn;
        password = passWordIn;
        answer=passCheck();
        if(answer != -1){
            setSelection();
        }
    }

    public int getAnswer() {
        System.out.println("security answer "+answer);
        return answer;
    }

    public int passCheck(){
        if(password == 123){
            passed= 1;
        }else if(password== 456){
            passed= 2;
        }else if(password == 789){
            passed= 3;
        }else{
            passed = -1;
        }
        return passed;
    }
    public void setSelection(){
        if(selection==1){
        AppointmentGUI appointmentGUI = new AppointmentGUI();
        }else if(selection==2){
            CheckInGUI checkInGUI=new CheckInGUI();
        }else if(selection==3){

        }else if(selection==4){
//            MedicalRecordGUI medicalRecordGUI =new MedicalRecordGUI();
        }else if(selection==5){
//            TimeTablesGUI timeTablesGUI =new TimeTablesGUI();
        }else if(selection==6){
            ProcessReferrals processReferrals=new ProcessReferrals();
        }else if(selection==7){
//            PatientChartGUI patientChartGUI=new PatientChartGUI();
        }else if(selection==8){
//            BillGUI billGUI=new BillGUI();
        }else if(selection==9){
//            PrescriptionGUI prescriptionGUI=new PrescriptionGUI();
        }
    }
}
