package com.buzztechnology.paytogether.paytogether.choisePal;

public class PalModel {
    private String name;
    private boolean isPal;

    public PalModel(String name, boolean isPal) {
        this.name = name;
        this.isPal = isPal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPal() {
        return isPal;
    }

    public void setPal(boolean pal) {
        isPal = pal;
    }
}
