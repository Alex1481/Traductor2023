package com.michael.appbalum.common.pojo;

import com.google.firebase.database.Exclude;

import java.util.Objects;

public class Vocabulary {

    public static final String ID = "id";
    public static final String SPANISH = "spanish";
    public static final String TRANSLATE = "translate";
    public static final String PHOTO_URL = "photoUrl";
    public static final String SEN_SPANISH = "senSpanish";
    public static final String SEN_CH = "senCh";

    @Exclude
    private String id;
    private String spanish;
    private String translate;
    private String photoUrl;
    private String senSpanish;
    private String senCh;


    public Vocabulary() {
    }

    @Exclude
    public String getId() {
        return id;
    }

    @Exclude
    public void setId(String id) {
        this.id = id;
    }

    public String getSpanish() {
        return spanish;
    }

    public void setSpanish(String spanish) {
        this.spanish = spanish;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getSenSpanish() {
        return senSpanish;
    }

    public void setSenSpanish(String senSpanish) {
        this.senSpanish = senSpanish;
    }

    public String getSenCh() {
        return senCh;
    }

    public void setSenCh(String senCh) {
        this.senCh = senCh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vocabulary that = (Vocabulary) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
