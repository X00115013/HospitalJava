package Model;

/**
 * Created by David on 20/03/2015.
 */
public class Equipment {
    private int equipID;
    private String equipName1,equipName2,equipName3, condition;
    private double equipCost1=100, equipCost2=200, equipCost3=300; // This is for the Three option;

    public Equipment(){}

    public Equipment(double equipCost1, double equipCost2, double equipCost3) {
        this.equipCost1 = equipCost1;
        this.equipCost2 = equipCost2;
        this.equipCost3 = equipCost3;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getEquipCost1() {
        return equipCost1;
    }

    public void setEquipCost1(double equipCost1) {
        this.equipCost1 = equipCost1;
    }

    public double getEquipCost2() {
        return equipCost2;
    }

    public void setEquipCost2(double equipCost2) {
        this.equipCost2 = equipCost2;
    }

    public double getEquipCost3() {
        return equipCost3;
    }

    public void setEquipCost3(double equipCost3) {
        this.equipCost3 = equipCost3;
    }

    public int getEquipID() {
        return equipID;
    }

    public void setEquipID(int equipID) {
        this.equipID = equipID;
    }

    public String getEquipName1() {
        return equipName1;
    }

    public void setEquipName1(String equipName1) {
        this.equipName1 = equipName1;
    }

    public String getEquipName2() {
        return equipName2;
    }

    public void setEquipName2(String equipName2) {
        this.equipName2 = equipName2;
    }

    public String getEquipName3() {
        return equipName3;
    }

    public void setEquipName3(String equipName3) {
        this.equipName3 = equipName3;
    }
}
