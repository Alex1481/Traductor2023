package com.michael.appbalum.common;

public interface BasicErrorEventCallback {
    void onSuccess();
    void onError(int typeEvent, int resMsg);
}
