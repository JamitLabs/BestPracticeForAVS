package com.jamitlabs.alexavoiceserviceandroid;

import android.app.Application;
import android.util.Log;

import com.jamitlabs.alexavoiceserviceandroid.utility.SigningKey;

/**
 * Created by dariuschdideban on 07.12.17.
 */

public class AlexaApplication extends Application {

    private static AlexaApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        if (BuildConfig.DEBUG) {
            Log.i("AlexaApplicaiton", SigningKey.getCertificateMD5Fingerprint(this));
        }
    }

    public static AlexaApplication getInstance() {
        return mInstance;
    }
}
