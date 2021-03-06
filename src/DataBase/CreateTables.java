package DataBase;

/**
 * Created by David Kiernan and Thomas Murray on 25/02/2015.
 */

import java.sql.*;
import java.util.*;

import Model.TimeTables;
import oracle.jdbc.pool.OracleDataSource;

public class CreateTables {
    private Connection conn;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;
    private ResultSet rset;

    // Opening Database
    public Connection openDB() {
        String url;
        Scanner in = new Scanner(System.in);
        try {
            // Load the Oracle JDBC driver
            OracleDataSource ods = new OracleDataSource();
//            System.out.println("Type global1 or home");
//            String val = in.next();
//
//            // Tallaght College
//            if (val.equalsIgnoreCase("global1")) {
//                ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
//                ods.setUser("X00115013");
//                ods.setPassword("db02Dec77");
//                conn = ods.getConnection();
//                System.out.println("connected.");
//            }
//            // At Home
//            else if (val.equalsIgnoreCase("home")) {
                ods.setURL("jdbc:oracle:thin:hr/hr@localhost:1521/XE");
                ods.setUser("system");
                ods.setPassword("30905149");
                conn = ods.getConnection();
                System.out.println("connected.");
//            }

        } catch (Exception e) {
            System.out.println("Unable to load driver. " + e);
        }
        return conn;
    }

    // This method closes the connection to the Oracle database
    public void closeDB() {
        try {
            conn.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            System.out.print("Could not close connection ");
            e.printStackTrace();
        }
    }

    // Dropping tables and sequences.
    public void dropTables() {
        System.out.println("Checking for existing tables & Sequences");

        try {
            // Get a Statement object
            stmt = conn.createStatement();
            try {
                stmt.execute("DROP TABLE PatientChart");
                System.out.println("PatientChart Table dropped");
            } catch (SQLException ex) {
            }
            try{
                stmt.execute("DROP SEQUENCE prescription_seq");
                System.out.println("Prescription Sequence dropped.");
            }catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP TABLE Prescriptions");
                System.out.println("Prescription Table dropped");
            } catch (SQLException ex) {

            }
            try{
                stmt.execute("DROP SEQUENCE bill_seq");
                System.out.println("Bill Sequence dropped.");
            }catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP TABLE Bill");
                System.out.println("Bill Table dropped");
            } catch (SQLException ex) {
            }
            try{
                stmt.execute("DROP SEQUENCE machine_seq");
                System.out.println("Machine Sequence dropped.");
            }catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP TABLE EquipmentUsed");
                System.out.println("EquipmentUsed Table dropped");
            } catch (SQLException ex) {

            }
            try{
                stmt.execute("DROP SEQUENCE tt_seq");
                System.out.println("Time Table Sequence dropped.");
            }catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP TABLE TimeTable");
                System.out.println("Timetable Table dropped");
            } catch (SQLException ex) {
            }
            try{
                stmt.execute("DROP SEQUENCE GPID");
                System.out.println("Referral Sequence dropped.");
            }catch (SQLException ex) {
            }
            try{
                stmt.execute("DROP TABLE Referral");
                System.out.println("Referral Table dropped");
            }catch (SQLException ex){
            }
            try {
                stmt.execute("DROP SEQUENCE xRay_seq");
                System.out.println("xRayTT Sequence dropped.");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP TABLE XRayTimeTable");
                System.out.println("xRayTT Table dropped");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP SEQUENCE mRI_seq");
                System.out.println("MRI Sequence dropped.");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP TABLE MRITimeTable");
                System.out.println("MRI Table dropped");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP SEQUENCE cT_seq");
                System.out.println("cT_seq Sequence dropped.");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP TABLE CTScanTimeTable");
                System.out.println("CT Scan Table dropped");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP SEQUENCE conTT_seq");
                System.out.println("conTT_seq Sequence dropped.");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP TABLE consultantTimetable");
                System.out.println("ConsultantTT Table dropped");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP TABLE Accounts");
                System.out.println("Accounts Table dropped");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP SEQUENCE StaffID");
                System.out.println("Administration Sequence dropped.");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP TABLE Administration");
                System.out.println("Administration Table dropped");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP SEQUENCE archive_seq");
                System.out.println("Archive Sequence dropped.");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP TABLE Archive");
                System.out.println("Archive Table dropped");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP SEQUENCE ConID");
                System.out.println("Consultant Sequence dropped.");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP TABLE Consultant");
                System.out.println("Consultant Table dropped");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP SEQUENCE PatientID");
                System.out.println("Patient Sequence dropped.");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP TABLE Patient");
                System.out.println("Patient Table dropped");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP SEQUENCE account_seq");
                System.out.println("Accounts Sequence dropped.");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP TABLE OldMedicalRecords");
                System.out.println("Old Med Records Table dropped");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP SEQUENCE oldMed_seq");
                System.out.println("Old Med Records Sequence dropped.");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP SEQUENCE APPID");
                System.out.println("Appointment Sequence dropped.");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP TABLE Appointment");
                System.out.println("Appointment Table dropped");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP SEQUENCE MedID");
                System.out.println("Medicine Sequence dropped.");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP TABLE Medicine");
                System.out.println("Medicine Table dropped");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP SEQUENCE CardID");
                System.out.println("Card Details Sequence dropped.");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP TABLE CardDetails");
                System.out.println("CardDetails Table dropped");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP SEQUENCE GMSID");
                System.out.println("Medical Card Sequence dropped.");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP TABLE MedicalCard");
                System.out.println("MedicalCard Table dropped");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP SEQUENCE PolicyID");
                System.out.println("Health Insurance Sequence dropped.");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP TABLE HealthInsurance");
                System.out.println("HealthInsurance Table dropped");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP SEQUENCE BedID");
                System.out.println("Bed Sequence dropped.");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP TABLE Bed");
                System.out.println("Bed Table dropped");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP SEQUENCE EquipID");
                System.out.println("Medical Equipment Sequence dropped.");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP TABLE MedicalEquipment");
                System.out.println("MedicalEquipment Table dropped");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP SEQUENCE check_seq ");
                System.out.println("check out Sequence dropped.");
            } catch (SQLException ex) {
            }
            try {
                stmt.execute("DROP TABLE checkOUT");
                System.out.println("check out Table dropped");
            } catch (SQLException ex) {
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void CreateTable() {
        try {
            //ADMIN START creating table
            System.out.println("Creating Administration");
            String createAdmin = "CREATE TABLE Administration (staff_ID NUMBER PRIMARY KEY, password VARCHAR2(255))";
            pstmt = conn.prepareStatement(createAdmin);
            pstmt.executeUpdate(createAdmin);
            // creating Sequence
            String createAdminSeq = " CREATE SEQUENCE StaffID increment by 1 start with 1";
            pstmt = conn.prepareStatement(createAdminSeq);
            pstmt.executeUpdate(createAdminSeq);


        } catch (SQLException e) {
            System.out.print("SQL Exception " + e);
            System.exit(1);
        } // END ADMIN


        try {
            //CONSULTANT START creating table
            System.out.println("Creating Consultant");
            String createCon = "CREATE TABLE Consultant (con_ID NUMBER PRIMARY KEY, con_Name VARCHAR2(255), speciality VARCHAR2(50),machineSkill VARCHAR2(30))";
            pstmt = conn.prepareStatement(createCon);
            pstmt.executeUpdate(createCon);
            // creating Sequence
            String createConSeq = " CREATE SEQUENCE ConID increment by 1 start with 1";
            pstmt = conn.prepareStatement(createConSeq);
            pstmt.executeUpdate(createConSeq);
            // Insert data into table
            String insertCon = "INSERT INTO Consultant (con_ID,con_Name,speciality,machineSkill) Values(ConID.nextVal,?,?,?)";

            pstmt = conn.prepareStatement(insertCon);
            pstmt.setString(1, "Dr J Doe ");
            pstmt.setString(2, "Radiology");
            pstmt.setString(3, "XRay");
            pstmt.executeUpdate();

            pstmt = conn.prepareStatement(insertCon);
            pstmt.setString(1, "Dr K Door ");
            pstmt.setString(2, "Pediatrics");
            pstmt.setString(3, "Ultrasound");
            pstmt.executeUpdate();

            pstmt = conn.prepareStatement(insertCon);
            pstmt.setString(1, "Dr J Smith ");
            pstmt.setString(2, "Neurologist");
            pstmt.setString(3,"CT Scan");
            pstmt.executeUpdate();

            pstmt = conn.prepareStatement(insertCon);
            pstmt.setString(1, "Dr S Johns ");
            pstmt.setString(2, "Dermatologist");
            pstmt.setString(3,"Laparoscopy");
            pstmt.executeUpdate();

            pstmt = conn.prepareStatement(insertCon);
            pstmt.setString(1, "Dr X Starburn ");
            pstmt.setString(2, "Psychiatrist");
            pstmt.setString(3,"MRI");
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.print("SQL Exception " + e);
            System.exit(1);
        }
        try {
            //APPOINTMENT START creating table
            System.out.println("Creating Appointment");
            String createApp = "CREATE TABLE Appointment (AppID NUMBER PRIMARY KEY,ReasonVisit VARCHAR2(255),requiredEquipment VARCHAR2(50), requiredConsultant VARCHAR2(50),dateIn VARCHAR2(60))";
            pstmt = conn.prepareStatement(createApp);
            pstmt.executeUpdate(createApp);
            // creating Sequence
            String createAppSeq = " CREATE SEQUENCE APPID increment by 1 start with 1";
            pstmt = conn.prepareStatement(createAppSeq);
            pstmt.executeUpdate(createAppSeq);
        } catch (SQLException e) {
            System.out.print("SQL Exception " + e);
            System.exit(1);
        }


        try {
            //Patient START creating table
            System.out.println("Creating Patient");
            String createPatient = "CREATE TABLE Patient (patient_Number NUMBER PRIMARY KEY, patientFName VARCHAR2(60), patientLName VARCHAR2(60),patientAddress VARCHAR2(255)," +
                    "PatientDOB VARCHAR2(255),PatientGender VARCHAR2(10), occupation VARCHAR2(100), BloodType VARCHAR2(30),Symptoms VARCHAR2(255), Diagnoses VARCHAR2(255), RequiredTreatment " +
                    "VARCHAR2(255),Allergies VARCHAR2(255),PatientEmail VARCHAR2(60),PatientPhone VARCHAR2(60),Recommendation " +
                    "VARCHAR2(255),AppID NUMBER,checkedIn VARCHAR2(60))";
            pstmt = conn.prepareStatement(createPatient);
            pstmt.executeUpdate(createPatient);
            // creating Sequence
            String createPatientSeq = " CREATE SEQUENCE PatientID increment by 1 start with 1001";
            pstmt = conn.prepareStatement(createPatientSeq);
            pstmt.executeUpdate(createPatientSeq);
        } catch (SQLException e) {
            System.out.print("SQL Exception Patient" + e);
            System.exit(1);
        }

        try {
            //CARD DETAILS START creating table
            System.out.println("Creating Card Details");
            String createCardDetails = "CREATE TABLE CardDetails (Card_ID NUMBER PRIMARY KEY, patient_Num NUMBER, cardNumber NUMBER, cardType VARCHAR2(30)" +
                    ",SecurityCode NUMBER,CardHolder VARCHAR2(70),ExpiryDate VARCHAR2(30))";
            pstmt = conn.prepareStatement(createCardDetails);
            pstmt.executeUpdate(createCardDetails);
            // creating Sequence
            String createCardSeq = " CREATE SEQUENCE CardID increment by 1 start with 1";
            pstmt = conn.prepareStatement(createCardSeq);
            pstmt.executeUpdate(createCardSeq);
        } catch (SQLException e) {
            System.out.print("SQL Exception " + e);
            System.exit(1);
        }

        try {
            //MEDICAL CARD  START creating table
            System.out.println("Creating Medical Card");
            String createMCardDetails = "CREATE TABLE MedicalCard ( MedCard_id NUMBER PRIMARY KEY, patient_Num NUMBER,GMSNumber NUMBER, PPSN NUMBER, Gender VARCHAR2(20),ValidTo VARCHAR2(30),HolderName VARCHAR2(70))";
            pstmt = conn.prepareStatement(createMCardDetails);
            pstmt.executeUpdate(createMCardDetails);
            // creating Sequence
            String createMCardSeq = " CREATE SEQUENCE GMSID increment by 1 start with 1";
            pstmt = conn.prepareStatement(createMCardSeq);
            pstmt.executeUpdate(createMCardSeq);
        } catch (SQLException e) {
            System.out.print("SQL Exception " + e);
            System.exit(1);
        }

        try {
            //HEALTH INSURANCE  START creating table
            System.out.println("Creating Health Insurance");
            String createInsurDetails = "CREATE TABLE HealthInsurance (Insu_ID NUMBER PRIMARY KEY,patient_Num NUMBER,Policy_number NUMBER, Company_name VARCHAR2(255),Coverage_type VARCHAR2(40),Expiry_date VARCHAR2(30))";
            pstmt = conn.prepareStatement(createInsurDetails);
            pstmt.executeUpdate(createInsurDetails);
            // creating Sequence
            String createInsurSeq = " CREATE SEQUENCE PolicyID increment by 1 start with 1";
            pstmt = conn.prepareStatement(createInsurSeq);
            pstmt.executeUpdate(createInsurSeq);
        } catch (SQLException e) {
            System.out.print("SQL Exception " + e);
            System.exit(1);
        }

        try {
            //REFERRAL START creating table
            System.out.println("Creating Referral");
            String createReferral = "CREATE TABLE Referral (Reference NUMBER PRIMARY KEY,GPNumber NUMBER,GPName VARCHAR2(40),GPLocation VARCHAR2(255),Patient_FName VARCHAR2(30)," +
                    "Patient_LName VARCHAR2(30),Patient_Address VARCHAR2(255),Patient_DOB VARCHAR(225),Patient_Phone VARCHAR2(15)," +
                    "ReasonVisit VARCHAR2(255),Recommendation VARCHAR2(255),Med_Equip_Needed VARCHAR2(50),Consultant_Type VARCHAR2(50),checked NUMBER,gender VARCHAR2(10))";
            pstmt = conn.prepareStatement(createReferral);
            pstmt.executeUpdate(createReferral);
            // creating Sequence
            String createReferralSeq = " CREATE SEQUENCE GPID increment by 1 start with 1";
            pstmt = conn.prepareStatement(createReferralSeq);
            pstmt.executeUpdate(createReferralSeq);
        } catch (SQLException e) {
            System.out.print("SQL Exception " + e);
            System.exit(1);
        }

        try {
            //PATIENT CHART START creating table
            System.out.println("Creating Patient Chart");
            String createPatChart = "CREATE TABLE PatientChart (Patient_form VARCHAR2(255)" +
                    ",patient_Number NUMBER PRIMARY KEY REFERENCES Patient(patient_Number))";
            pstmt = conn.prepareStatement(createPatChart);
            pstmt.executeUpdate(createPatChart);
        } catch (SQLException e) {
            System.out.print("SQL Exception " + e);
            System.exit(1);
        }

        try {
            //MEDICINE START creating table
            System.out.println("Creating Medicine");
            String createMedicine = "CREATE TABLE Medicine (Med_ID NUMBER PRIMARY KEY, Med_Name VARCHAR2(50), StockLevel NUMBER, price NUMBER)";
            pstmt = conn.prepareStatement(createMedicine);
            pstmt.executeUpdate(createMedicine);
            // creating Sequence
            String createMedSeq = " CREATE SEQUENCE MedID increment by 1 start with 1";
            pstmt = conn.prepareStatement(createMedSeq);
            pstmt.executeUpdate(createMedSeq);


            // Insert Medicine into Medicine table

            String insertString1 = "INSERT INTO Medicine(Med_ID , Med_Name , StockLevel, price)values(MedID.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(insertString1);
            pstmt.setString(1, "Acetylsalicylic acid");
            pstmt.setInt(2, 200);
            pstmt.setDouble(3, 1.20);
            pstmt.execute();

            String insertString2 = "INSERT INTO Medicine(Med_ID , Med_Name , StockLevel, price)values(MedID.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(insertString2);
            pstmt.setString(1, "Ibuprofen");
            pstmt.setInt(2, 200);
            pstmt.setDouble(3, 1.50);
            pstmt.execute();


            String insertString3 = "INSERT INTO Medicine(Med_ID , Med_Name , StockLevel, price)values(MedID.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(insertString3);
            pstmt.setString(1, "Codeine");
            pstmt.setInt(2, 200);
            pstmt.setDouble(3, 1.70);
            pstmt.execute();

            String insertString4 = "INSERT INTO Medicine(Med_ID , Med_Name , StockLevel, price)values(MedID.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(insertString4);
            pstmt.setString(1, "Morphine");
            pstmt.setInt(2, 200);
            pstmt.setDouble(3, 2.20);
            pstmt.execute();

            String insertString5 = "INSERT INTO Medicine(Med_ID , Med_Name , StockLevel, price)values(MedID.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(insertString5);
            pstmt.setString(1, "Amitriptyline");
            pstmt.setInt(2, 200);
            pstmt.setDouble(3, 2.30);
            pstmt.execute();

            String insertString6 = "INSERT INTO Medicine(Med_ID , Med_Name , StockLevel, price)values(MedID.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(insertString6);
            pstmt.setString(1, "Docusate sodium");
            pstmt.setInt(2, 200);
            pstmt.setDouble(3, 4.20);
            pstmt.execute();

            String insertString7 = "INSERT INTO Medicine(Med_ID , Med_Name , StockLevel, price)values(MedID.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(insertString7);
            pstmt.setString(1, "Hyoscine butylbromide");
            pstmt.setInt(2, 200);
            pstmt.setDouble(3, 10.20);
            pstmt.execute();


            String insertString8 = "INSERT INTO Medicine(Med_ID , Med_Name , StockLevel, price)values(MedID.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(insertString8);
            pstmt.setString(1, "Metoclopramide");
            pstmt.setInt(2, 200);
            pstmt.setDouble(3, 31.20);
            pstmt.execute();

            String insertString9 = "INSERT INTO Medicine(Med_ID , Med_Name , StockLevel, price)values(MedID.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(insertString9);
            pstmt.setString(1, "Sodium nitrite");
            pstmt.setInt(2, 200);
            pstmt.setDouble(3, 1.80);
            pstmt.execute();

            String insertString10 = "INSERT INTO Medicine(Med_ID , Med_Name , StockLevel, price)values(MedID.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(insertString10);
            pstmt.setString(1, "Deferoxamine");
            pstmt.setInt(2, 200);
            pstmt.setDouble(3, 22.20);
            pstmt.execute();

        } catch (SQLException e) {
            System.out.print("SQL Exception Meds " + e);
            System.exit(1);
        }



        try {
            //PRESCRIPTION START creating table
            System.out.println("Creating PrescriptionsUsed");
            String createMedicine = "CREATE TABLE Prescriptions (PrescriptionID NUMBER PRIMARY KEY, Pat_NumIn NUMBER, Drug_name VARCHAR2(200), Med_Amount NUMBER,conName VARCHAR2(50), This_Visit NUMBER, dateIn VARCHAR2(60))";
            pstmt = conn.prepareStatement(createMedicine);
            pstmt.executeUpdate(createMedicine);
            System.out.println("OR here");
            // creating Sequence
            createMedicine = " CREATE SEQUENCE prescription_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createMedicine);
            pstmt.executeUpdate(createMedicine);
        } catch (SQLException e) {
            System.out.print("SQL Exception prescription " + e);
            System.exit(1);
        }

        try {
            //MEDICAL EQUIPMENT START creating table
            System.out.println("Creating Medical Equipment");
            String createMEquipDetails = "CREATE TABLE MedicalEquipment(Equipment_ID  NUMBER PRIMARY KEY , Equipment_name VARCHAR2(255), price NUMBER)";
            pstmt = conn.prepareStatement(createMEquipDetails);
            pstmt.executeUpdate(createMEquipDetails);
            // creating Sequence
            String createMEquipSeq = " CREATE SEQUENCE EquipID increment by 1 start with 1";
            pstmt = conn.prepareStatement(createMEquipSeq);
            pstmt.executeUpdate(createMEquipSeq);

            // Insert record 1 into University table

            String insertString1 = "INSERT INTO MedicalEquipment(Equipment_ID , Equipment_name, price )values(EquipID.nextVal,?,?)";
            pstmt = conn.prepareStatement(insertString1);
            pstmt.setString(1, "XRay");
            pstmt.setDouble(2, 100.20);
            pstmt.execute();

            String insertString2 = "INSERT INTO MedicalEquipment(Equipment_ID , Equipment_name, price )values(EquipID.nextVal,?,?)";
            pstmt = conn.prepareStatement(insertString2);
            pstmt.setString(1, "MRI");
            pstmt.setDouble(2, 150.50);
            pstmt.execute();


            String insertString3 = "INSERT INTO MedicalEquipment(Equipment_ID , Equipment_name, price )values(EquipID.nextVal,?,?)";
            pstmt = conn.prepareStatement(insertString3);
            pstmt.setString(1, "CT Scan");
            pstmt.setDouble(2, 170.70);
            pstmt.execute();

            String insertString4 = "INSERT INTO MedicalEquipment(Equipment_ID , Equipment_name, price )values(EquipID.nextVal,?,?)";
            pstmt = conn.prepareStatement(insertString4);
            pstmt.setString(1, "Ultrasound");
            pstmt.setDouble(2, 80.20);
            pstmt.execute();

            String insertString5 = "INSERT INTO MedicalEquipment(Equipment_ID , Equipment_name, price )values(EquipID.nextVal,?,?)";
            pstmt = conn.prepareStatement(insertString5);
            pstmt.setString(1, "Laparoscopy");
            pstmt.setDouble(2, 122.30);
            pstmt.execute();


        } catch (SQLException e) {
            System.out.print("SQL Exception med Equip" + e);
            System.exit(1);
        }

        try {
            //MED EQUIP START creating table
            System.out.println("Creating Medical Equipment Used");
            String createMedicine = "CREATE TABLE EquipmentUsed (machineID NUMBER PRIMARY KEY,patient_NumIn NUMBER, machine_name VARCHAR2(255), this_Visit NUMBER)";
            pstmt = conn.prepareStatement(createMedicine);
            pstmt.executeUpdate(createMedicine);
            // creating Sequence
            createMedicine = " CREATE SEQUENCE machine_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createMedicine);
            pstmt.executeUpdate(createMedicine);
        } catch (SQLException e) {
            System.out.print("SQL Exception " + e);
            System.exit(1);
        }


        try {
            //old bills creating table
            System.out.println("Creating Bill");
            String createPayDetails = "CREATE TABLE Bill (bill_Number NUMBER PRIMARY KEY,bill VARCHAR2(2000),datePaid VARCHAR2(60), patient_Num NUMBER )";
            pstmt = conn.prepareStatement(createPayDetails);
            pstmt.executeUpdate(createPayDetails);
            // creating Sequence
            createPayDetails = " CREATE SEQUENCE bill_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createPayDetails);
            pstmt.executeUpdate(createPayDetails);
        } catch (SQLException e) {
            System.out.print("Bill SQL Exception " + e);
            System.exit(1);
        }

        try {
            //creating checkOut table
            System.out.println("Creating check out info Tables");
            String create = "CREATE TABLE checkOUT(check_ID  NUMBER PRIMARY KEY, message VARCHAR2(255),conName VARCHAR2(50), thisVisit NUMBER, patient_Num NUMBER )";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate(create);
            // creating Sequence
            create = " CREATE SEQUENCE check_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate(create);
        } catch (SQLException e) {
            System.out.print("SQL Exception " + e);
            System.exit(1);
        }


        try {
            //TIME TABLE START creating table
            System.out.println("Creating TimeTable");
            String create = "CREATE TABLE TimeTable(tt_ID  NUMBER PRIMARY KEY,machineName VARCHAR2(100), time NUMBER, taken VARCHAR2(10),con_Name VARCHAR2(50), AppID NUMBER)";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate(create);
            // creating Sequence
            create = " CREATE SEQUENCE tt_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate(create);
        } catch (SQLException e) {
            System.out.print("Bloody timetable SQL Exception " + e);
            System.exit(1);
        }

        try {
            //creating accounts table
            System.out.println("Creating Hospital Account");
            String create = "CREATE TABLE Accounts(accountID  NUMBER PRIMARY KEY, medDeposit NUMBER, equipDeposit NUMBER ,runningTotal NUMBER, dateIn VARCHAR2(60),patient_Num NUMBER)";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate(create);
            // creating Sequence
            create = " CREATE SEQUENCE account_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate(create);
        } catch (SQLException e) {
            System.out.print("SQL Exception Accounts" + e);
            System.exit(1);
        }

        try {
            //Creating old med rec table
            System.out.println("Creating Old MedicalRecords");
            String create = "CREATE TABLE OldMedicalRecords(oldMed_ID  NUMBER PRIMARY KEY, dateIn VARCHAR2(60),old_Record VARCHAR2(4000),patient_Num NUMBER)";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate(create);
            // creating Sequence
            create = " CREATE SEQUENCE oldMed_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate(create);
        } catch (SQLException e) {
            System.out.print("Old Medical records SQL Exception " + e);
            System.exit(1);
        }


        try {
            //Consultant TIME TABLE START creating table
            System.out.println("Creating consultantTimetable");
            String create = "CREATE TABLE consultantTimetable(conTT_ID  NUMBER PRIMARY KEY, time NUMBER ,con_Name VARCHAR2(50), AppID NUMBER )";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate(create);
            // creating Sequence
            create = " CREATE SEQUENCE conTT_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate(create);
        } catch (SQLException e) {
            System.out.print("SQL Exception " + e);
            System.exit(1);
        }

        try {
            //Archive TABLE START creating table
            System.out.println("Creating Archive");
            String create = "CREATE TABLE Archive(archiveID  NUMBER PRIMARY KEY, patient_numIn NUMBER,reasonForDeletion VARCHAR2(255),archiveFile VARCHAR2(255))";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate(create);
            // creating Sequence
            create = " CREATE SEQUENCE archive_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate(create);
        } catch (SQLException e) {
            System.out.print("SQL Exception " + e);
            System.exit(1);
        }

        //SET TIMETABLES FREE
        TimeTables tt=new TimeTables();
        tt.setFree();
    }

//    public static void main(String args[]) {
//        CreateTables cp = new CreateTables();
//        cp.openDB();
//        cp.dropTables();
//        cp.CreateTable();
//
//    }
}


