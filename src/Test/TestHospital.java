package Test;
import Charts.ChartGUI;
import GUI.*;
import Model.*;
import Referrals.ReferralGUI;
import Referrals.Referrals;

/**
 * Created by Thomas Murray on 01/03/2015.
 */
public class TestHospital {
    public static void main(String[] args) {
//        GUI TESTS
//        AppointmentGUI ag=new AppointmentGUI();
//        ag.setVisible(true);
//        SecurityGUI su=new SecurityGUI();
//        su.setVisible(true);
//        ReferralGUI referralGUI= new ReferralGUI();
//        referralGUI.setVisible(true);
        HomeScreen homeScreen=new HomeScreen();
//        StockHomeScreen stockHomeScreen=new StockHomeScreen();
//        CheckInGUI checkInGUI=new CheckInGUI();
//        AddPatientGUI addPatientGUI=new AddPatientGUI(1,1);
//        AdminCheckOutGUI adminCheckOutGUI=new AdminCheckOutGUI();
//        MedCheckOutGUI medCheckOutGUI=new MedCheckOutGUI();
//        PatientAdminRecGUI patientAdminRecGUI=new PatientAdminRecGUI();
//        TimeTablesGUI timeTablesGUI=new TimeTablesGUI();
//        CancelAppointmentGUI cancelAppointmentGUI=new CancelAppointmentGUI();
//        MedPatientRecGUI medPatientRecGUI=new MedPatientRecGUI();
//        UpdateMedRec updateMedRec=new UpdateMedRec();
//        PatientChartGUI patientChartGUI=new PatientChartGUI();
//        PrescriptionGUI prescriptionGUI=new PrescriptionGUI();
//        PaymentGUI paymentGUI=new PaymentGUI();
//        CreditCardGUI creditCardGUI=new CreditCardGUI(1003);
//         HealthInsuranceGUI healthInsuranceGUI=new HealthInsuranceGUI();
//        MedicalCardGUI medicalCardGUI=new MedicalCardGUI();
//        AppointmentDetailsGUI appointmentDetailsGUI=new AppointmentDetailsGUI(1);
//        DeletePatient deletePatient=new DeletePatient(2);
//        AddEquipmentGUI addEquipmentGUI=new AddEquipmentGUI();
//        AddMedicineGUI addMedicineGUI=new AddMedicineGUI();
//        AddConsultantGUI addConsultantGUI=new AddConsultantGUI();
//        MedicalCardGUI medicalCardGUI=new MedicalCardGUI(1003);
//        ChartGUI chartGUI=new ChartGUI(1);
//        OldBills oldBills=new OldBills();






//        REFERRALS TESTS
//        Referrals referrals= new Referrals( "Jim","dublin","mick","jagger","Carlow","02-Jan-2000","4591662","sick","help him","XRay","Radiology",1,"male");
//        Referrals referrals2= new Referrals( "Joe","cork","paul","Macartey","New York","04-Feb-1967","0870973783","Deader","bury him","MRI","Pediatrics",1,"female");
//        Referrals referrals3= new Referrals( "Hayden","Dudai","John","Lennon","Liverpool","13-Mar-1958","555-555-555","Ghost","Fear him","CT Scan","Neurologist",1,"male");
//
//        Referrals referral4= new Referrals( "Ann","dublin","James","Deane","Ciro","02-Jun-1977","459882","ill","check stomach","XRay","Radiology",1,"male");
//        Referrals referrals5= new Referrals( "Kim","cork","Andrew","Hero","New Deli","04-Feb-1967","0870973783","Deader","bury him","MRI","Neurologist",1,"female");
//        Referrals referrals6= new Referrals( "david","Dudai","John","Lennon","Liverpool","13-Mar-1958","555-555-555","Ghost","Fear him","CT Scan","Dermatologist",1,"male");
//
//        Referrals referral7= new Referrals( "Jimmy","dublin","mick","jagger","Carlow","02-Jan-2000","4591662","sick","help him","XRay","Radiology",1,"male");
//        Referrals referrals8= new Referrals( "Jacob","cork","paul","Macartey","New Deli","24-Feb-1987","087111111783","bumped head","Head Scan","MRI","Dermatologist",1,"female");
//        Referrals referrals9= new Referrals( "letitia","Dudai","King","Kong","Lourdes","23-Mar-1951","555-222255","Back complaints","scan his back","CT Scan","Neurologist",1,"male");
//
//        Referrals referrals10= new Referrals( "carl","dublin","Queen","O'Toole","Hungry","22-Jan-1949","45222232","sick of life","heal him","XRay","Dermatologist",1,"male");
//        Referrals referrals11= new Referrals( "carter","cork","paula","Matey","Brazil","04-Feb-1987","0870898783","Leg pains","Reset","MRI","Pediatrics",1,"female");
//        Referrals referrals12= new Referrals( "bren","Dudai","Bruce","Wayne","Gotham","01-Apr-1999","999 999 999","Batman","I'm Batman!!!","CT Scan","Psychiatrist",1,"male");
//
//        Referrals referrals13= new Referrals( "Bev","dublin","Clark","Kent","Metropolis","22-Jan-2011","4593333","sick in the head","try help him","XRay","Radiology",1,"male");
//        Referrals referrals14= new Referrals( "Sarah","cork","Roland","Deschaine","Gilliade","13-Mar-1977","0877777777","Gunslinger","Give no quarter","MRI","Pediatrics",1,"female");
//        Referrals referrals15= new Referrals( "Andrew","Dudai","Eddie","Deane","New York","13-Mar-1962","555-555-555","Junk head","befriend him","CT Scan","Dermatologist",1,"male");
//
//        Referrals referrals16= new Referrals( "Mick","dublin","Odeta","Holmes","York","02-Jan-1947","44477462","Leg damage","Watch here","XRay","Psychiatrist",1,"male");
//        Referrals referrals17= new Referrals( "Peter","cork","Jake","Chambers","New York","04-Feb-1967","08783","Auto acccident","bury him, blue car","MRI","Dermatologist",1,"female");
//        Referrals referrals18= new Referrals( "Paul","Dudai","Oi","Bumbler","middle world","11-Mar-1988","555-555-555","Spring broken","Feed him","CT Scan","Neurologist",1,"male");
//
//        Referrals referrals19= new Referrals( "Brian","dublin","Red","King","The Tower","01-Jan-2000","66666662","sick to death","Leave him","XRay","Radiology",1,"male");
//        Referrals referrals20= new Referrals( "Abby","cork","Man","Black","South","04-Feb-1967","0870973783","Darker","run from him","MRI","Pediatrics",1,"female");
//        Referrals referrals21= new Referrals( "Ava","Dudai","Captain","Trips","Nevada","01-Mar-1999","555-222-555","Ghost of men","Retreat","CT Scan","Neurologist",1,"male");
//
//        Referrals referrals22= new Referrals( "Denize","dublin","Jacen","Solo","Cadues","31-Jan-2000","4591662","Turned","Save him","XRay","Dermatologist",1,"male");
//        Referrals referrals23= new Referrals( "Cain","cork","Ben","Skywalker","Nabu","14-Sep-1957","0874489883","Vestara","Watch him","MRI","Pediatrics",1,"female");
//        Referrals referrals24= new Referrals( "Dan","Dudai","Jaina","Solo","Imperial space","13-Nov-1958","555-533-555","Sword","of Jedi sort","CT Scan","Dermatologist",1,"male");
//
//        Referrals referrals25= new Referrals( "Eddie","dublin","Han","Solo","Falcon","22-Dec-2010","454452","Slick","Pirate","XRay","Radiology",1,"male");
//        Referrals referrals26= new Referrals( "Carla","cork","Luke","SkyWalker","Tattoien","04-Feb-1957","08704556","Cut","Baca salv","MRI","Psychiatrist",1,"female");
//        Referrals referrals27= new Referrals( "Major","Dudai","Mara","Sky","Lostien","13-Jan-1998","555-555-555","Lost","Find her","CT Scan","Neurologist",1,"male");
//
//        Referrals referrals28= new Referrals( "Borrowed","dublin","Master ","jester","Circus","02-Jan-2016","459443322","Poisoned","Medical care","XRay","Psychiatrist",1,"male");
//        Referrals referrals29= new Referrals( "Kathy","cork","Peter","Pan","Neverland","02-Dec-1957","0870783","To young","laughter","MRI","Pediatrics",1,"female");
//        Referrals referrals30= new Referrals( "Tom","Dudai","Jack","R","White Hall","11-Mar-2000","565-565-565","Head problems","Process him","CT Scan","Neurologist",1,"male");
// ProcessReferrals processReferrals = new ProcessReferrals();


//        APPOINTMENT TESTS
//        Appointment ap=new Appointment("Tom","XRay","Radiology");
//        Appointment ap1=new Appointment("Ava","MRI","Pediatrics");
//        Appointment ap2=new Appointment("Cain","CT Scan","Neurologist");



//        PATIENT RECORDS TESTS
//        PatientRecord patientRecord = new PatientRecord("Tom", "Murray", "23 willow drive", "Programmer","Male", "first.hadron@gmail.com", "0870973783","02-Jan-77");




//        SET TIMETABLES FREE
//        Run the create table first then ful the two below and the any of the appointment above(together or single. system will be single run)
//        TimeTables tt=new TimeTables();
//        tt.setFree();
//
//
    }
}
