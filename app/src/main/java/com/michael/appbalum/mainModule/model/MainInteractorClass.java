package com.michael.appbalum.mainModule.model;

import com.michael.appbalum.common.BasicErrorEventCallback;
import com.michael.appbalum.common.pojo.Vocabulary;
import com.michael.appbalum.mainModule.events.MainEvent;
import com.michael.appbalum.mainModule.model.dataAccess.RealtimeDatabase;
import com.michael.appbalum.mainModule.model.dataAccess.VocabularysEventListener;

import org.greenrobot.eventbus.EventBus;

public class MainInteractorClass implements MainInteractor{
    private RealtimeDatabase mDatabase;

    public MainInteractorClass() {
        mDatabase = new RealtimeDatabase();
    }

    @Override
    public void subscribeToVocabularys() {
        mDatabase.subscribeToVocabularys(new VocabularysEventListener() {
            @Override
            public void onChildAdded(Vocabulary vocabulary) {
                post(vocabulary, MainEvent.SUCCESS_ADD);
            }

            @Override
            public void onChildUpdated(Vocabulary vocabulary) {
                post(vocabulary, MainEvent.SUCCESS_UPDATE);
            }

            @Override
            public void onChildRemoved(Vocabulary vocabulary) {
                post(vocabulary, MainEvent.SUCCESS_REMOVE);
            }

            @Override
            public void onError(int resMsg) {
                post(MainEvent.ERROR_SERVER, resMsg);
            }
        });
    }

    @Override
    public void unsubscribeToVocabularys() {
        mDatabase.unsubscribeToVocabularys();
    }

    @Override
    public void removeVocabulary(Vocabulary vocabulary) {
        mDatabase.removeVocabulary(vocabulary, new BasicErrorEventCallback() {
            @Override
            public void onSuccess() {
                post(MainEvent.SUCCESS_REMOVE);
            }

            @Override
            public void onError(int typeEvent, int resMsg) {
                post(typeEvent, resMsg);
            }
        });
    }

    private void post(int typeEvent){
        post(null, typeEvent, 0);
    }

    private void post(int typeEvent, int resMsg){
        post(null, typeEvent, resMsg);
    }

    private void post(Vocabulary vocabulary, int typeEvent){
        post(vocabulary, typeEvent, 0);
    }

    private void post(Vocabulary vocabulary, int typeEvent, int resMsg) {
        MainEvent event = new MainEvent();
        event.setVocabulary(vocabulary);
        event.setTypeEvent(typeEvent);
        event.setResMsg(resMsg);
        EventBus.getDefault().post(event);
    }
}
