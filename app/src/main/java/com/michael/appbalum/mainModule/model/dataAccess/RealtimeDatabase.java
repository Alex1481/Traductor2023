package com.michael.appbalum.mainModule.model.dataAccess;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.michael.appbalum.R;
import com.michael.appbalum.common.BasicErrorEventCallback;
import com.michael.appbalum.common.model.dataAccess.FirebaseRealtimeDatabaseAPI;
import com.michael.appbalum.common.pojo.Vocabulary;
import com.michael.appbalum.mainModule.events.MainEvent;
import com.michael.appbalum.mainModule.model.dataAccess.VocabularysEventListener;

public class RealtimeDatabase {
    private static final String PATH_VOCABULARYS = "vocabularys";

    private FirebaseRealtimeDatabaseAPI mDatabaseAPI;
    private ChildEventListener mVocabularysChildEventListener;

    public RealtimeDatabase() {
        mDatabaseAPI = FirebaseRealtimeDatabaseAPI.getInstance();
    }

    private DatabaseReference getVocabularysReference(){
        return mDatabaseAPI.getmReference().child(PATH_VOCABULARYS);
    }

    public void subscribeToVocabularys(VocabularysEventListener listener){
        if(mVocabularysChildEventListener == null){
            mVocabularysChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    listener.onChildAdded(getVocabulary(dataSnapshot));
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    listener.onChildUpdated(getVocabulary(dataSnapshot));
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    listener.onChildRemoved(getVocabulary(dataSnapshot));
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    switch (databaseError.getCode()){
                        case DatabaseError.PERMISSION_DENIED:
                            listener.onError(R.string.main_error_permission_denied);
                            break;
                        default:
                            listener.onError(R.string.main_error_server);
                    }
                }
            };
        }
        getVocabularysReference().addChildEventListener(mVocabularysChildEventListener);
    }

    private Vocabulary getVocabulary(DataSnapshot dataSnapshot) {
        Vocabulary vocabulary = dataSnapshot.getValue(Vocabulary.class);
        if (vocabulary != null){
            vocabulary.setId(dataSnapshot.getKey());
        }
        return vocabulary;
    }

    public void unsubscribeToVocabularys(){
        if (mVocabularysChildEventListener != null){
            getVocabularysReference().removeEventListener(mVocabularysChildEventListener);
        }
    }

    public void removeVocabulary(Vocabulary vocabulary, BasicErrorEventCallback callback){
        getVocabularysReference().child(vocabulary.getId())
                .removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if(databaseError == null){
                            callback.onSuccess();
                        } else {
                            switch (databaseError.getCode()){
                                case DatabaseError.PERMISSION_DENIED:
                                    callback.onError(MainEvent.ERROR_TO_REMOVE, R.string.main_error_remove);
                                    break;
                                default:
                                    callback.onError(MainEvent.ERROR_SERVER, R.string.main_error_server);
                            }
                        }
                    }
                });
    }
}
