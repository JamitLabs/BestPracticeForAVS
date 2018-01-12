package com.jamitlabs.bestpracticeavs;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

public class AlexaVoiceServiceActivity extends BaseActivity implements FragmentManager.OnBackStackChangedListener {

    private final static String TAG_FRAGMENT = "CurrentFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alexa_voice_service);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            navigate(LoginFragment.class, null);
        }
    }

    @Override
    protected void startListening() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
        if (fragment != null && fragment.isVisible()) {
            if (fragment instanceof BaseListenerFragment) {
                ((BaseListenerFragment) fragment).startListening();
            }
        }
    }

    public void navigate(Class<? extends Fragment> fragmentClass) {
        navigate(fragmentClass, null);
    }

    public void navigate(Class<? extends Fragment> fragmentClass, Bundle arguments) {
        try {
            Fragment fragment = fragmentClass.newInstance();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    protected void stateListening() {

    }

    protected void stateProcessing() {

    }

    protected void stateSpeaking() {

    }

    protected void statePrompting() {

    }

    protected void stateFinished() {

    }

    protected void stateNone() {

    }


    @Override
    public void onBackStackChanged() {
        shouldDisplayHomeUp();
    }

    public void shouldDisplayHomeUp() {
        //Enable Up button only  if there are entries in the back stack
        boolean canback = (getSupportFragmentManager().getBackStackEntryCount() > 0);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(canback);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        //This method is called when the up button is pressed. Just the pop back stack.
        getSupportFragmentManager().popBackStack();
        return true;
    }
}
