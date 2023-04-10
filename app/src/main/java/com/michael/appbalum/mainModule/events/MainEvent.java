package com.michael.appbalum.mainModule.events;

import com.michael.appbalum.common.pojo.Vocabulary;

public class MainEvent {
    public static final int SUCCESS_ADD = 0;
    public static final int SUCCESS_UPDATE = 1;
    public static final int SUCCESS_REMOVE = 2;
    public static final int ERROR_SERVER = 100;
    public static final int ERROR_TO_REMOVE = 101;


    private Vocabulary vocabulary;
    private int typeEvent;
    private int resMsg;

    public MainEvent() {
    }

    public Vocabulary getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }

    public int getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(int typeEvent) {
        this.typeEvent = typeEvent;
    }

    public int getResMsg() {
        return resMsg;
    }

    public void setResMsg(int resMsg) {
        this.resMsg = resMsg;
    }
}
