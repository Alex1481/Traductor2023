package com.michael.appbalum.common.model.dataAccess;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseRealtimeDatabaseAPI {
    private DatabaseReference mReference;

    private static FirebaseRealtimeDatabaseAPI INSTANCE = null;

    public FirebaseRealtimeDatabaseAPI() {
        mReference = FirebaseDatabase.getInstance().getReference();
    }

    public static FirebaseRealtimeDatabaseAPI getInstance(){
        if (INSTANCE == null){
            INSTANCE = new FirebaseRealtimeDatabaseAPI();
        }
        return INSTANCE;
    }

    //Referencias
    public DatabaseReference getmReference(){
        return mReference;
    }
}
