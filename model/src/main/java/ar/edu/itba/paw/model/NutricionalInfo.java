package ar.edu.itba.paw.model;

import ar.edu.itba.paw.model.Enum.NutricionalInfoTypes;

public class NutricionalInfo {
    private NutricionalInfoTypes type;
    private double amount;

    public NutricionalInfo(NutricionalInfoTypes type, double amount){
        this.type = type;
        this.amount = amount;
    }

    public NutricionalInfoTypes getType() {
        return type;
    }

    public void setType(NutricionalInfoTypes type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void addAmount(double amount) {
        this.amount += amount;
    }
}
