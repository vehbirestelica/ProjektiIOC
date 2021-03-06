package com.fiek.projektiioc.Porosite;

import com.google.firebase.firestore.ServerTimestamp;

public class NewOrders{
    private String porosia;
    private String lokacioni;
    private @ServerTimestamp
    String dataLeshimit;
    private String derguesi;
    private String marresi;
    private String sasia;
    private String spinner;
    private String radioGroup;
    private String statusi;
    private String paguarRB;
    private String paPaguarRB;
    private String neProcesRB;
    private String porosiaID;
    private String userID;

    public String getPorosiaID () {
        return porosiaID;
    }

    public void setPorosiaID (String porosiaID) {
        this.porosiaID = porosiaID;
    }

    public String getUserID () {
        return userID;
    }

    public void setUserID (String userID) {
        this.userID = userID;
    }

    public String getPaguarRB () {
        return paguarRB;
    }

    public void setPaguarRB (String paguarRB) {
        this.paguarRB = paguarRB;
    }

    public String getPaPaguarRB () {
        return paPaguarRB;
    }

    public void setPaPaguarRB (String paPaguarRB) {
        this.paPaguarRB = paPaguarRB;
    }

    public String getNeProcesRB () {
        return neProcesRB;
    }

    public void setNeProcesRB (String neProcesRB) {
        this.neProcesRB = neProcesRB;
    }

    public NewOrders() {
    }

    public NewOrders (String porosia, String lokacioni, String dataLeshimit, String derguesi, String marresi, String sasia,
                      String spinner, String radioGroup, String statusi, String paguarRB, String paPaguarRB, String neProcesRB, String userID, String porosiaID) {
        this.porosia = porosia;
        this.lokacioni = lokacioni;
        this.dataLeshimit = dataLeshimit;
        this.derguesi = derguesi;
        this.marresi = marresi;
        this.sasia = sasia;
        this.spinner = spinner;
        this.radioGroup = radioGroup;
        this.statusi = statusi;
        this.paguarRB = paguarRB;
        this.paPaguarRB = paPaguarRB;
        this.neProcesRB = neProcesRB;
        this.userID = userID;
        this.porosiaID = porosiaID;
    }

    public String getStatusi () {
        return statusi;
    }

    public void setStatusi (String statusi) {
        this.statusi = statusi;
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

    public String getDataLeshimit () {
        return dataLeshimit;
    }

    public void setDataLeshimit (String dataLeshimit) {
        this.dataLeshimit = dataLeshimit;
    }


    public String getDerguesi() {
        return derguesi;
    }

    public void setDerguesi(String  derguesi) {
        this.derguesi = derguesi;
    }

    public String getMarresi() {
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
                ", statusi='" + statusi + '\'' +
                ", paguarRB='" + paguarRB + '\'' +
                ", paPaguarRB='" + paPaguarRB + '\'' +
                ", neProcesRB='" + neProcesRB + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }
}
