package Model;
    import DataBase.*;
    import Model.*;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.ArrayList;

    /**
     * Created by Thomas Murray on 15/03/2015.
     */

    public class EquipmentUsed {
        private int pNum,eqNum,thisVisit;
        private String eqName;
        private ResultSet rset;
        private ArrayList<EquipmentUsed> usedList = new ArrayList<>();
        private ArrayList<EquipmentUsed> usedListC = new ArrayList<>();
        private StockOperations so;


        public  EquipmentUsed(){

        }


        public  EquipmentUsed(int pNumber){
            pNum=pNumber;

        }

        public EquipmentUsed(int pNumberIn, String eqNameIn){
            pNum=pNumberIn;
            eqName=eqNameIn;
            addEquipUsed(pNum, eqName);
        }

        public EquipmentUsed(int eqUsedNumIn, int pNumberIn, String eqNameIn, int thisVisitIn){
            eqNum=eqUsedNumIn;
            pNum=pNumberIn;
            eqName=eqNameIn;
            thisVisit=thisVisitIn;
        }

        public void refreshArrays() {
            try {
                usedList.removeAll(usedList);
                so=new StockOperations();
                rset = so.getEquipmentUsed(pNum);
                while (rset.next()) {
                    usedList.add(new EquipmentUsed(rset.getInt(1),rset.getInt(2), rset.getString(3), rset.getInt(4)));
                }so.stockOperationsClose();
            } catch (SQLException e1) {
                System.out.println(e1);
            }
        }

        public void chartArrays() {
            try {
                usedListC.removeAll(usedListC);
                so=new StockOperations();
                rset = so.getEquipmentUsedC();
                while (rset.next()) {
                    usedListC.add(new EquipmentUsed(rset.getInt(1),rset.getInt(2), rset.getString(3), rset.getInt(4)));
                }so.stockOperationsClose();
            } catch (SQLException e1) {
                System.out.println(e1);
            }
        }

        public void addEquipUsed(int pNumIn,String equipNameIn){
            so=new StockOperations();
            so.addEquipUsed(pNumIn, equipNameIn);
            so.stockOperationsClose();
        }

        public int getpNum() {
            return pNum;
        }

        public int getEqNum() {
            return eqNum;
        }

        public int getThisVisit() {
            return thisVisit;
        }

        public String getEqName() {
            return eqName;
        }

        public ArrayList getUsedList() {
            refreshArrays();
            return usedList;
        }

        public ArrayList getUsedListC() {
            chartArrays();
            return usedListC;
        }
    }
