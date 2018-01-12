package com.jamitlabs.bestpracticeavs;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.api.Listener;
import com.amazon.identity.auth.device.api.authorization.AuthorizationManager;
import com.jamitlabs.alexaconnect.libraries.alexa.requestbody.DataRequestBody;
import com.jamitlabs.alexaconnect.libraries.recorderview.RecorderView;
import com.jamitlabs.alexaconnect.libraries.speechutils.RawAudioRecorder;

import java.io.IOException;
import okio.BufferedSink;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlexaVoiceServiceFragment extends BaseListenerFragment {

    private final static String TAG = "SendAudioActionFragment";

    private final static int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 1;
    private final static int AUDIO_RATE = 16000;
    public final static String ARGUMENT_PROFILE_INFO = "profile_info";

    private RawAudioRecorder rawAudioRecorder;
    private RecorderView recorderView;
    private TextView outText;
    private Toolbar mToolBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_alexa_voice_service, container, false);
        outText = rootView.findViewById(R.id.profile_info);
        mToolBar = rootView.findViewById(R.id.toolbar_alexa);
        if(getArguments() != null && getArguments().containsKey(ARGUMENT_PROFILE_INFO)) {
            outText.setText(getArguments().getString(ARGUMENT_PROFILE_INFO));
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mToolBar.setTitle("Talk to Alexa");
        mToolBar.inflateMenu(R.menu.item_menu);
        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                logout();
                return false;
            }
        });

        recorderView = view.findViewById(R.id.recorder);
        recorderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rawAudioRecorder == null) {
                    startListening();
                } else {
                    stopListening();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.RECORD_AUDIO)) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_RECORD_AUDIO: {
                if (!(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (rawAudioRecorder != null) {
            rawAudioRecorder.stop();
            rawAudioRecorder.release();
            rawAudioRecorder = null;
        }
    }

    @Override
    public void startListening() {
        if (rawAudioRecorder == null) {
            rawAudioRecorder = new RawAudioRecorder(AUDIO_RATE);
        }
        rawAudioRecorder.start();
        alexaManager.sendAudioRequest(requestBody, getRequestCallback());
    }

    private DataRequestBody requestBody = new DataRequestBody() {
        @Override
        public void writeTo(BufferedSink sink) throws IOException {
            while (rawAudioRecorder != null && !rawAudioRecorder.isPausing()) {
                if (rawAudioRecorder != null) {
                    final float rmsdb = rawAudioRecorder.getRmsdb();
                    if (recorderView != null) {
                        recorderView.post(new Runnable() {
                            @Override
                            public void run() {
                                recorderView.setRmsdbLevel(rmsdb);
                            }
                        });
                    }
                    if (sink != null && rawAudioRecorder != null) {
                        sink.write(rawAudioRecorder.consumeRecording());
                    }
                    if (BuildConfig.DEBUG) {
                        Log.i(TAG, "Received Audio");
                        Log.i(TAG, "RMSDB: " + rmsdb);
                    }
                }
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            stopListening();
        }
    };

    private void stopListening() {
        if (rawAudioRecorder != null) {
            rawAudioRecorder.stop();
            rawAudioRecorder.release();
            rawAudioRecorder = null;
        }
    }

    @Override
    protected String getTitle() {
        return getString(R.string.fragment_action_send_audio);
    }

    private void logout() {
        AuthorizationManager.signOut(getContext(), new Listener<Void, AuthError>() {
            @Override
            public void onSuccess(Void aVoid) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "Logout succesfully", Toast.LENGTH_LONG).show();
                        ((AlexaVoiceServiceActivity) getActivity()).navigate(LoginFragment.class);
                    }
                });
            }

            @Override
            public void onError(AuthError authError) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "Logout not possible", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
