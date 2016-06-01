package com.gosemathraj.pushnotifications;

import android.content.Intent;

import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by iamsparsh on 2/5/16.
 */
public class GCMTokenRefreshListenerService extends InstanceIDListenerService {

    @Override
    public void onTokenRefresh() {

        Intent intent= new Intent(this,GCMRegistrationIntentService.class);
        startActivity(intent);
    }
}
