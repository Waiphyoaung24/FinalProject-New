package xyz.waiphyoag.shopify.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.waiphyoag.shopify.R;

public class ActivityUserProfile extends BaseActivity {

    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tvUserId)
    TextView tvUserID;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.profile_image)
    ImageView ivProfile;
    @BindView(R.id.iv_back)
    FrameLayout flBack;

    @BindView(R.id.tv_number)
    TextView tvNumber;
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
        tvUserID.setText(id);

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
    }

}
