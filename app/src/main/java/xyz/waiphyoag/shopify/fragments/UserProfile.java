package xyz.waiphyoag.shopify.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.waiphyoag.shopify.R;

import xyz.waiphyoag.shopify.activities.ProductMainActivity;
import xyz.waiphyoag.shopify.data.model.ProductModel;
import xyz.waiphyoag.shopify.data.vo.UserVO;

public class UserProfile extends Fragment {

    @BindView(R.id.close)
    ImageButton ibClose;
    @BindView(R.id.imageView1)
    ImageView ivProfile;
    @BindView(R.id.tvProfileName)
    TextView tvProfileName;
    @BindView(R.id.tv_number)
    TextView tvphone;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_userID)
    TextView tvID;


    public static UserProfile newInstance() {
        UserProfile userProfile = new UserProfile();

        return userProfile;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View successView = inflater.inflate(R.layout.fragment_user_profile, container, false);
        ButterKnife.bind(this, successView);


        SharedPreferences prefs = getContext().getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);
        String id = prefs.getString("UserId", "");
        String name = prefs.getString("UserName", "");
        String email = prefs.getString("UserEmail", "");
        String photo = prefs.getString("UserProfile", "");
        String phone = prefs.getString("phone", "");

        tvProfileName.setText(name);

        Glide.with(getContext())
                .load(photo)
                .into(ivProfile);
        tvEmail.setText(email);

        if (phone == "") {
                tvphone.setText("959-97512331");
        } else {
            tvphone.setText(phone);
        }
        tvID.setText(id);


        ibClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().popBackStack();


            }
        });

        return successView;
    }
}


