package com.michael.appbalum.mainModule;

import com.michael.appbalum.common.pojo.Vocabulary;
import com.michael.appbalum.mainModule.events.MainEvent;
import com.michael.appbalum.mainModule.model.MainInteractor;
import com.michael.appbalum.mainModule.model.MainInteractorClass;
import com.michael.appbalum.mainModule.view.MainView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainPresenterClass implements MainPresenter{
    private MainView mView;
    private MainInteractor mInteractor;

    public MainPresenterClass(MainView mView) {
        this.mView = mView;
        this.mInteractor = new MainInteractorClass();
    }

    @Override
    public void onCreate() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        mInteractor.unsubscribeToVocabularys();
    }

    @Override
    public void onResume() {
        mInteractor.subscribeToVocabularys();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        mView = null;
    }

    @Override
    public void remove(Vocabulary vocabulary) {
        if (setProgress()){
            mInteractor.removeVocabulary(vocabulary);
        }
    }

    private boolean setProgress() {
        if(mView != null){
            mView.showProgress();
            return true;
        }
        return false;
    }

    @Subscribe
    @Override
    public void onEvenListener(MainEvent event) {
        if (mView != null){
            mView.hideProgress();

            switch (event.getTypeEvent()){
                case MainEvent.SUCCESS_ADD:
                    mView.add(event.getVocabulary());
                    break;

                    case MainEvent.SUCCESS_UPDATE:
                        mView.update(event.getVocabulary());
                        break;

                        case MainEvent.SUCCESS_REMOVE:
                            mView.remove(event.getVocabulary());
                            break;

                            case MainEvent.ERROR_SERVER:
                                mView.onShowError(event.getResMsg());
                                break;

                                case MainEvent.ERROR_TO_REMOVE:
                                    mView.removeFail();
                                    break;
            }
        }
    }
}
