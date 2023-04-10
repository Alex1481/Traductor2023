package com.michael.appbalum.mainModule;

import com.michael.appbalum.common.pojo.Vocabulary;
import com.michael.appbalum.mainModule.events.MainEvent;

public interface MainPresenter {
    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();

    void remove(Vocabulary vocabulary);

    void onEvenListener(MainEvent event);
}
