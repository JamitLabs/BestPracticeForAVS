package com.jamitlabs.bestpracticeavs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.jamitlabs.alexaconnect.libraries.alexa.AlexaManager;
import com.jamitlabs.alexaconnect.libraries.alexa.callbacks.AsyncCallback;
import com.jamitlabs.alexaconnect.libraries.alexa.interfaces.AvsResponse;

import static com.jamitlabs.bestpracticeavs.global.Constants.PRODUCT_ID;

/**
 * Created by dariuschdideban on 07.12.17.
 */

public abstract class BaseListenerFragment extends Fragment {

    protected AlexaManager alexaManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        alexaManager = AlexaManager.getInstance(getActivity(), PRODUCT_ID);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            getActivity().setTitle(getTitle());
        }
    }

    protected AsyncCallback<AvsResponse, Exception> getRequestCallback() {
        if (getActivity() != null && getActivity() instanceof AvsListenerInterface) {
            return (((AvsListenerInterface) getActivity()).getRequestCallback());
        }
        return null;
    }

    public abstract void startListening();
    protected abstract String getTitle();

    public interface AvsListenerInterface {
        AsyncCallback<AvsResponse, Exception> getRequestCallback();
    }
}
