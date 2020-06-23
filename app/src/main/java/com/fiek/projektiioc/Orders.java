package com.fiek.projektiioc;

public class Orders {
    private String emri;
    private String data;
    private String statusi;
    private String imgURL;

    public Orders () {
    }

    public Orders (String emri, String data, String statusi) {
        this.emri = emri;
        this.data = data;
        this.statusi = statusi;
//        this.imgURL = imgURL;
    }

    public String getEmri () {
        return emri;
    }

    public void setEmri (String emri) {
        this.emri = emri;
    }

    public String getData () {
        return data;
    }

    public void setData (String data) {
        this.data = data;
    }

    public String getStatusi () {
        return statusi;
    }

    public void setStatusi (String statusi) {
        this.statusi = statusi;
    }

    public String getImgURL () {
        return imgURL;
    }

    public void setImgURL (String imgURL) {
        this.imgURL = imgURL;
    }

}
