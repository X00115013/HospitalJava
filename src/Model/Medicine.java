package Model;

/**
 * Created by David on 15/03/2015.
 */
public class Medicine {
    private int medId;
    private String medName1, medName2, medName3;
    private double medCost1=7.50, medCost2=3.99, medCost3=2;

    public Medicine(){}

    public Medicine(double medCost1, double medCost2, double medCost3) {
        this.medCost1 = medCost1;
        this.medCost2 = medCost2;
        this.medCost3 = medCost3;
    }

    public double getMedCost1() {
        return medCost1;
    }

    public void setMedCost1(double medCost1) {
        this.medCost1 = medCost1;
    }

    public double getMedCost2() {
        return medCost2;
    }

    public void setMedCost2(double medCost2) {
        this.medCost2 = medCost2;
    }

    public double getMedCost3() {
        return medCost3;
    }

    public void setMedCost3(double medCost3) {
        this.medCost3 = medCost3;
    }

    public int getMedId() {
        return medId;
    }

    public void setMedId(int medId) {
        this.medId = medId;
    }

    public String getMedName1() {
        return medName1;
    }

    public void setMedName1(String medName1) {
        this.medName1 = medName1;
    }

    public String getMedName2() {
        return medName2;
    }

    public void setMedName2(String medName2) {
        this.medName2 = medName2;
    }

    public String getMedName3() {
        return medName3;
    }

    public void setMedName3(String medName3) {
        this.medName3 = medName3;
    }
}
