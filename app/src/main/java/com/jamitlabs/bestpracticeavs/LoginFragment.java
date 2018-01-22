package com.jamitlabs.bestpracticeavs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.api.Listener;
import com.amazon.identity.auth.device.api.authorization.AuthCancellation;
import com.amazon.identity.auth.device.api.authorization.AuthorizationManager;
import com.amazon.identity.auth.device.api.authorization.AuthorizeListener;
import com.amazon.identity.auth.device.api.authorization.AuthorizeRequest;
import com.amazon.identity.auth.device.api.authorization.AuthorizeResult;
import com.amazon.identity.auth.device.api.authorization.ProfileScope;
import com.amazon.identity.auth.device.api.authorization.Scope;
import com.amazon.identity.auth.device.api.authorization.User;
import com.amazon.identity.auth.device.api.workflow.RequestContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private static final String TAG = LoginFragment.class.getName();

    private RequestContext requestContext;
    private ProgressBar mProgressBar;
    private View mLoginButton;
    private Toolbar mToolbar;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mLoginButton = view.findViewById(R.id.login_with_amazon);
        mProgressBar = view.findViewById(R.id.progress_bar);
        mProgressBar = view.findViewById(R.id.progress_bar);
        mToolbar = view.findViewById(R.id.toolbar_alexa);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mToolbar.setTitle("Login");

        requestContext = RequestContext.create(this);
        requestContext.registerListener(new AuthorizeListener() {
            @Override
            public void onSuccess(AuthorizeResult authorizeResult) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fetchUserProfile();
                    }
                });
            }

            @Override
            public void onError(AuthError authError) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "Error during Authorization. Please try again!", Toast.LENGTH_LONG).show();
                        ((AlexaVoiceServiceActivity) getActivity()).navigate(LoginFragment.class);
                    }
                });
            }

            @Override
            public void onCancel(AuthCancellation authCancellation) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "Authorization cancelled!", Toast.LENGTH_LONG).show();
                        ((AlexaVoiceServiceActivity) getActivity()).navigate(LoginFragment.class);
                    }
                });
            }
        });
        initializeUI();
    }

    @Override
    public void onResume() {
        super.onResume();
        requestContext.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        Scope[] scopes = {ProfileScope.profile(), ProfileScope.postalCode()};
        AuthorizationManager.getToken(getContext(), scopes, new Listener<AuthorizeResult, AuthError>() {
            @Override
            public void onSuccess(final AuthorizeResult authorizeResult) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (authorizeResult.getAccessToken() != null) {
                            fetchUserProfile();
                            Log.i("OnStartMethod", "User is logged in.");
                        } else {
                            Log.i("OnStartMethod", "User is not logged in.");
                        }
                    }
                });
            }

            @Override
            public void onError(final AuthError authError) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity().getApplicationContext(), authError.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void fetchUserProfile() {

        User.fetch(getContext(), new Listener<User, AuthError>() {
            @Override
            public void onSuccess(User user) {
                final String name = user.getUserName();
                final String email = user.getUserEmail();
                final String account = user.getUserId();
                final String zipCode = user.getUserPostalCode();

                updateProfileData(name, email, account, zipCode);
            }

            @Override
            public void onError(AuthError authError) {
                String errorMessage = "Error retrieving profile information. \nPlease log in again!";
                Toast errorToast = Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG);
                errorToast.setGravity(Gravity.CENTER, 0, 0);
                errorToast.show();
            }
        });

    }

    private void updateProfileData(String name, String email, String account, String zipCode) {
        StringBuilder profileBuilder = new StringBuilder();
        profileBuilder.append(String.format("Welcome, %s!\n", name));
        profileBuilder.append(String.format("Your email is %s\n", email));
        profileBuilder.append(String.format("Your zipCode is %s\n", zipCode));
        final String profile = profileBuilder.toString();

        Bundle arguments = new Bundle();
        arguments.putString(AlexaVoiceServiceFragment.ARGUMENT_PROFILE_INFO, profile);
        ((AlexaVoiceServiceActivity) getActivity()).navigate(AlexaVoiceServiceFragment.class, arguments);
    }

    private void initializeUI() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthorizationManager.authorize(new AuthorizeRequest.Builder(requestContext)
                        .addScopes(ProfileScope.profile(), ProfileScope.postalCode()).build());
                mLoginButton.setVisibility(View.GONE);
                mProgressBar.setVisibility(View.VISIBLE);
            }
        });
    }
}
