package com.assignedpro.nj.steam.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.assignedpro.nj.steam.R;
import com.assignedpro.nj.steam.network.Network;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

import cz.msebera.android.httpclient.auth.AUTH;
import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity implements
        View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener {

    Network network;

    private static final String TAG = LoginActivity.class.getSimpleName();
    private GoogleApiClient mGoogleApiClient;
    private EditText userid;
    private static final int RC_SIGN_IN = 007;
    private TextView tvLogin;
    ProgressDialog progress;
    private SpotsDialog spotsDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        spotsDialog = new SpotsDialog(LoginActivity.this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        SignInButton login = (SignInButton) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });



    /*  network=Network.getInstance(this);
        userid = (EditText) findViewById(R.id.userID);
        tvLogin = (TextView) findViewById(R.id.login);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              network.requestWithJsonObject(URLConstants.login,new AppId(
                      ((EditText) findViewById(R.id.userID)).getText().toString()
                      ,String.valueOf(GpsControl.getLatitude()),
                      String.valueOf(GpsControl.getLongitude()),
                      URLConstants.getImeiNo(LoginActivity.this),
                      "login"
                      )

                      ,loginResponse);
            }
        });*/

    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onStart() {
        super.onStart();


        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
             handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            spotsDialog.show();
            spotsDialog.setCancelable(false);
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    spotsDialog.dismiss();
                    handleSignInResult(googleSignInResult);

                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

/*
            String personName = acct.getDisplayName();
            String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();
*/

/*
            Log.e(TAG, "Name: " + personName + ", email: " + email
                    + ", Image: " + personPhotoUrl);
*/
Intent i=new Intent(LoginActivity.this,MainActivity.class);
startActivity(i);
            /*txtName.setText(personName);
            txtEmail.setText(email);
            Glide.with(getApplicationContext()).load(personPhotoUrl)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgProfilePic);

            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }*/
        }
/*
    VolleyResponse loginResponse=new VolleyResponse() {
        @Override
        public void onResponse(JSONObject obj) throws Exception {
                    
        }
    };
*/


    }
}


//        network=Network.getInstance(this);
//
//        network.requestWithJsonObject("",new student(),response);

//    VolleyResponse response=new VolleyResponse() {
//        @Override
//        public void onResponse(JSONObject obj) throws Exception {
//
//        }
//    };
