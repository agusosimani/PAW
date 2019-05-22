package ar.edu.itba.paw2019a2.model;

import ar.edu.itba.paw2019a2.model.Enum.NutritionalInfoTypes;

public class NutricionalInfo {

    private NutritionalInfoTypes type;
    private double amount;

    public NutricionalInfo(NutritionalInfoTypes type, double amount){
        this.type = type;
        this.amount = amount;
    }

    public NutritionalInfoTypes getType() {
        return type;
    }

    public void setType(NutritionalInfoTypes type) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        NutricionalInfo that = (NutricionalInfo) o;
        return type.equals(that.type);
    }
}
