package xyz.waiphyoag.shopify.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.waiphyoag.shopify.R;

public class ActivityUserProfile extends BaseActivity {
    @BindView(R.id.btn_logout)
    Button btnLogout;

    @BindView(R.id.tv_email)
    TextView tvEmail;

    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.profile_image)
    ImageView ivProfile;
    @BindView(R.id.iv_back)
    FrameLayout flBack;

    @BindView(R.id.tv_number)
    TextView tvNumber;
    private GoogleSignInAccount mAccount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this, this);


        SharedPreferences prefs = getApplicationContext().getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);
        String id = prefs.getString("UserId", "");
        String name = prefs.getString("UserName", "");
        String email = prefs.getString("UserEmail", "");
        String photo = prefs.getString("UserProfile", "");
        String phone = prefs.getString("phone", "");

        tvName.setText(name);
        tvEmail.setText(email);


        if (phone == "") {
            tvNumber.setText("959-97512331");
        } else {
            tvNumber.setText(phone);
        }
        Glide.with(getApplicationContext())
                .load(photo)
                .into(ivProfile);

        flBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onClickButton();




            }
        });
    }

    private void onClickButton() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.myDialog));
        builder
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation

                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
