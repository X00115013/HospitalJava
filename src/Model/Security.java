package Model;

import GUI.*;

import java.util.ArrayList;

/**
 * Created by Thomas Murray on 14/03/2015.
 */
public class Security {
    private int password;
    private int patientNum;
    private int passed;
    private int answer,selection;
    private PatientRecord patientRecord;
    private ArrayList<PatientRecord> pList=new ArrayList<>();

    public Security(int selectionIn,int patientNumIn, int passWordIn) {
        patientNum=patientNumIn;
        selection=selectionIn;
        password = passWordIn;
        answer=passCheck();
        if(answer != -1 && patientNumCheck()==true){
            setSelection(answer);
        }else if (patientNumCheck()==false){
            System.out.println("Not a patient match");

        }
    }

    public int getAnswer() {
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

    public boolean patientNumCheck(){
        boolean test2=true;
        patientRecord=new PatientRecord();
        pList.addAll(patientRecord.getPatientList());
        for (int i = 0; i < pList.size() ; i++) {
            if(patientNum==pList.get(i).getPatientNumber()||patientNum==-1){
               test2=true;
                i=pList.size()+1;
            }else{
                test2=false;
            }

        }return test2;

    }

    public void setSelection(int answerIn){
        if(selection==1 && (answerIn==1 ||answerIn==2 ||answerIn==3 )){
        AppointmentGUI appointmentGUI = new AppointmentGUI();
        }else if(selection==2 && (answerIn==1 ||answerIn==2 ||answerIn==3 )){
            CheckInGUI checkInGUI=new CheckInGUI();
        }else if(selection==3 && (answerIn==1 ||answerIn==3 )){
            PatientAdminRecGUI patientAdminRecGUI=new PatientAdminRecGUI(patientNum);
        }else if(selection==4&& (answerIn==2 ||answerIn==3 )){
            MedPatientRecGUI medPatientRecGUI=new MedPatientRecGUI(patientNum);
        }else if(selection==5&& (answerIn==1 ||answerIn==2 ||answerIn==3 )){
            TimeTablesGUI timeTablesGUI =new TimeTablesGUI();
        }else if(selection==6&& (answerIn==1 ||answerIn==3 )){
            ProcessReferrals processReferrals=new ProcessReferrals();
        }else if(selection==7&& (answerIn==1 ||answerIn==2 ||answerIn==3 )){
            PatientChartGUI patientChartGUI=new PatientChartGUI(patientNum);
        }else if(selection==8&& (answerIn==1 ||answerIn==3 )){
            PaymentGUI paymentGUI=new PaymentGUI(patientNum);
        }else if(selection==9&& (answerIn==2 ||answerIn==3 )){
            PrescriptionGUI prescriptionGUI=new PrescriptionGUI(patientNum);
        }else if(selection==10){
            if(answerIn ==1 || answerIn==3) {
                AdminCheckOutGUI adminCheckOutGUI = new AdminCheckOutGUI(patientNum);
            }else if(answerIn == 2) {
                MedCheckOutGUI medCheckOutGUI = new MedCheckOutGUI(patientNum);
            }
        }
    }
}
