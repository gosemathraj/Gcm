package com.gosemathraj.pushnotifications;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

/**
 * Created by iamsparsh on 2/5/16.
 */
public class GCMRegistrationIntentService extends IntentService {

    public static final String REGISTRATION_SUCCESS = "Registration Success";
    public static final String REGISTRATION_FAILURE = "Registration Failure";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public GCMRegistrationIntentService() {
        super("");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        registerAppToGCMServer();
    }

    private void registerAppToGCMServer() {

        Intent registrationComplete;
        String token;

        try{

            InstanceID instanceID = InstanceID.getInstance(getApplicationContext());
            token =instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE,null);
            Log.d("Token",token);

            registrationComplete = new Intent(REGISTRATION_SUCCESS);
            registrationComplete.putExtra("token",token);

        }catch(Exception e){

            registrationComplete = new Intent(REGISTRATION_FAILURE);
            Log.w("Registration Error","error");
        }

        //send broadcast

        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }
}
