package com.michael.appbalum.mainModule.view;

import com.michael.appbalum.common.pojo.Vocabulary;

public interface MainView {
    void showProgress();
    void hideProgress();

    void add(Vocabulary vocabulary);
    void update(Vocabulary vocabulary);
    void remove(Vocabulary vocabulary);

    void removeFail();
    void onShowError(int resMsg);
}
