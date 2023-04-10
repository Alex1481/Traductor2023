package com.michael.appbalum.mainModule.model;

import com.michael.appbalum.common.pojo.Vocabulary;

public interface MainInteractor {
    void subscribeToVocabularys();
    void unsubscribeToVocabularys();

    void removeVocabulary(Vocabulary vocabulary);
}
