package com.example.android.managerparcauto1;

/**
 * Created by Dragos Andrei Olaru on 25.01.2017.
 */

public class Auto {

    private String nr_inm;
    private String marca;
    private String tip;
    private String data;
    private String sofer;

    Auto(String nr_inm, String marca, String tip, String data, String sofer) {
        this.nr_inm = nr_inm;
        this.marca = marca;
        this.tip = tip;
        this.data = data;
        this.sofer = sofer;
    }


    public String getNr_inm() {
        return nr_inm;
    }

    public void setNr_inm(String nr_inm) {
        this.nr_inm = nr_inm;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSofer() {
        return sofer;
    }

    public void setSofer(String sofer) {
        this.sofer = sofer;
    }
}