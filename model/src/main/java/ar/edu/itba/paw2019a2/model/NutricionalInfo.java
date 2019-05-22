package ar.edu.itba.paw2019a2.model;

import ar.edu.itba.paw2019a2.model.Enum.NutricionalInfoTypes;

import java.util.Objects;

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
