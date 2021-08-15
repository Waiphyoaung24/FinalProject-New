package xyz.waiphyoag.shopify.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.data.model.ProductModel;

/**
 * Created by WaiPhyoAg on 9/4/19.
 */

public class LoginActivity extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener {
    @BindView(R.id.btn_login)
    Button btnLogIn;

    private GoogleApiClient mGoogleApiClient;
    private GoogleApiClient mGoogleSignInClient;
    public static final int RC_SIGN_IN = 9001;

    private GoogleSignInAccount mgoogleSignInAccount;
    private SharedPreferences mSharedPreferences;

    private FirebaseUser mFirebaseUser;
    private FirebaseAuth mAuth;

    private String name;
    private String email;
    private String userProfile;
    private String id;
    private String phone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this, this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }


        mSharedPreferences = getSharedPreferences("MySharedPreference", MODE_PRIVATE);
        id = mSharedPreferences.getString("UserId", "");
        if(id!=""){
            Intent intent = ProductMainActivity.mainIntent(getApplicationContext());
            startActivity(intent);
        }


        onTapButton(getApplicationContext());


    }

    public void onTapButton(Context context) {
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setupGoogleApiClient();

            }
        });
    }

    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }
            }
    );

    private void setupGoogleApiClient() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build());

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build();
        signInLauncher.launch(signInIntent);
    }

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            // Successfully signed in
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            Toast.makeText(getApplicationContext(), "Google Sign-In success : "
                    + response.getEmail(), Toast.LENGTH_SHORT).show();

            Intent intent = ProductMainActivity.mainIntent(getApplicationContext());
            startActivity(intent);
            onSuccessFulSignIn();
            // ...
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {



    }

    private void onSuccessFulSignIn(){
        mAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mAuth.getCurrentUser();


        ProductModel.getInstance().addNewUser(mFirebaseUser.getUid(), mFirebaseUser.getDisplayName(), mFirebaseUser.getEmail());


        mSharedPreferences.edit()
                .putString("UserId", mFirebaseUser.getUid())
                .putString("UserEmail", mFirebaseUser.getEmail())
                .putString("UserName", mFirebaseUser.getDisplayName())
                .putString("UserProfile", mFirebaseUser.getPhotoUrl().toString())
                .putString("phone",mFirebaseUser.getPhoneNumber())
                .apply();
    }

}


