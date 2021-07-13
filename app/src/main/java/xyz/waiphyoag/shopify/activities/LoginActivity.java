package xyz.waiphyoag.shopify.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
        name = mSharedPreferences.getString("UserName", "");
        email = mSharedPreferences.getString("UserEmail", "");
        phone = mSharedPreferences.getString("phone","");
        userProfile = mSharedPreferences.getString("UserProfile", "");


        onTapButton(getApplicationContext());

        setupGoogleApiClient();

    }

    public void onTapButton(Context context) {
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentForSignIn = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                onActivityResult(RC_SIGN_IN,0,intentForSignIn);


            }
        });
    }

    private void setupGoogleApiClient() {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("788682889549-9g6p3tlkov7uoh0k9t3e6c9vmp80vg48.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this /*FragmentActivity*/, this /*OnConnectionFailedListener*/)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            onProcessGoogleSignInResult(result);
            if (result.isSuccess()) {


                GoogleSignInAccount account = result.getSignInAccount();


                Toast.makeText(getApplicationContext(), "Google Sign-In success : "
                        + account.getDisplayName(), Toast.LENGTH_SHORT).show();




//                ProductModel.getInstance().addNewUser(id, name, "", email);


                Intent intent = ProductMainActivity.mainIntent(getApplicationContext());
                startActivity(intent);


            } else {
                Toast.makeText(getApplicationContext(), "Google Sign-In failed.", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void onProcessGoogleSignInResult(GoogleSignInResult result) {

        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            ProductModel.getInstance().authenticateUserWithGoogleAccount(account, new ProductModel.SignInWithGoogleAccountDelegate() {
                @Override
                public void onSuccessSignIn(GoogleSignInAccount signInAccount) {
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

                @Override
                public void onFailureSignIn(String map) {

                }
            });


        }
    }


}


