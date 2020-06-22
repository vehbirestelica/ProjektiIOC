package com.fiek.projektiioc;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class NewOrders {
    private String porosia;
    private String lokacioni;
    private @ServerTimestamp Date dataLeshimit;
    private String derguesi;
    private String marresi;
    private String sasia;
    private String spinner;
    private String radioGroup;

    public NewOrders() {
    }


    public NewOrders (String porosia, String lokacioni, Date dataLeshimit, String sasia, String spinner, String radioGroup, String derguesi, String marresi) {
        this.porosia = porosia;
        this.lokacioni = lokacioni;
        this.dataLeshimit = dataLeshimit;
        this.sasia = sasia;
        this.spinner = spinner;
        this.radioGroup = radioGroup;
        this.derguesi = derguesi;
        this.marresi = marresi;
    }

    public String getPorosia () {
        return porosia;
    }

    public void setPorosia (String porosia) {
        this.porosia= porosia;
    }

    public String getLokacioni () {
        return lokacioni;
    }

    public void setLokacioni (String  lokacioni) {
        this.lokacioni = lokacioni;
    }

    public Date getDataLeshimit () {
        return dataLeshimit;
    }

    public void setDataLeshimit (Date dataLeshimit) {
        this.dataLeshimit = dataLeshimit;
    }


    public String getDerguesi () {
        return derguesi;
    }

    public void setDerguesi (String  derguesi) {
        this.derguesi = derguesi;
    }

    public String getMarresi () {
        return marresi;
    }

    public void setMarresi (String  marresi) {
        this.marresi = marresi;
    }

    public String getSasia () {
        return sasia;
    }

    public void setSasia (String sasia) {
        this.sasia = sasia;
    }

    public String getSpinner () {
        return spinner;
    }

    public void setSpinner (String spinner) {
        this.spinner = spinner;
    }

    public String getRadioGroup () {
        return radioGroup;
    }

    public void setRadioGroup (String radioGroup) {
        this.radioGroup = radioGroup;
    }

    @Override
    public String toString () {
        return "NewOrders{" +
                "porosia='" + porosia + '\'' +
                ", lokacioni='" + lokacioni + '\'' +
                ", dataLeshimit=" + dataLeshimit +
                ", derguesi='" + derguesi + '\'' +
                ", marresi='" + marresi + '\'' +
                ", sasia='" + sasia + '\'' +
                ", spinner='" + spinner + '\'' +
                ", radioGroup='" + radioGroup + '\'' +
                '}';
    }
}
