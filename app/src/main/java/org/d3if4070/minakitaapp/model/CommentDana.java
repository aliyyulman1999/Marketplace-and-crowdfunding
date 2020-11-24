package org.d3if4070.minakitaapp.model;

import com.google.firebase.database.ServerValue;

public class CommentDana {


    private String contentdana,uiddana,uimgdana,unamedana;
    private Object timestampdana;


    public CommentDana() {
    }

    public CommentDana (String contentdana, String uiddana, String uimgdana, String unamedana) {
        this.contentdana = contentdana;
        this.uiddana = uiddana;
        this.uimgdana = uimgdana;
        this.unamedana = unamedana;
        this.timestampdana = ServerValue.TIMESTAMP;

    }

    public CommentDana(String contentdana, String uiddana, String uimgdana, String unamedana, Object timestampdana) {
        this.contentdana = contentdana;
        this.uiddana = uiddana;
        this.uimgdana = uimgdana;
        this.unamedana = unamedana;
        this.timestampdana = timestampdana;
    }

    public String getContentdana() {
        return contentdana;
    }

    public void setContentdana(String contentdana) {
        this.contentdana = contentdana;
    }

    public String getUiddana() {
        return uiddana;
    }

    public void setUiddana(String uiddana) {
        this.uiddana = uiddana;
    }

    public String getUimgdana() {
        return uimgdana;
    }

    public void setUimgdana(String uimgdana) {
        this.uimgdana = uimgdana;
    }

    public String getUnamedana() {
        return unamedana;
    }

    public void setUnamedana(String unamedana) {
        this.unamedana = unamedana;
    }

    public Object getTimestampdana() {
        return timestampdana;
    }

    public void setTimestampdana(Object timestampdana) {
        this.timestampdana = timestampdana;
    }
}
