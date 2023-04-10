package com.michael.appbalum.mainModule.model.dataAccess;

import com.michael.appbalum.common.pojo.Vocabulary;

public interface VocabularysEventListener {
    void onChildAdded(Vocabulary vocabulary);
    void onChildUpdated(Vocabulary vocabulary);
    void onChildRemoved(Vocabulary vocabulary);

    void onError(int resMsg);

}
