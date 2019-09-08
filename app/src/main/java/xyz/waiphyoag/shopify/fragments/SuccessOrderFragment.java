package xyz.waiphyoag.shopify.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.activities.ProductMainActivity;

public class SuccessOrderFragment extends Fragment {

    @BindView(R.id.purchase_order)
    TextView tvPurchaseOrder;
    @BindView(R.id.btn_buying)
    Button btnPay;

    public static SuccessOrderFragment newInstance() {
        SuccessOrderFragment successOrderFragment = new SuccessOrderFragment();
        return successOrderFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View successView = inflater.inflate(R.layout.fragment_payment_done, container, false);
        ButterKnife.bind(this, successView);


        SharedPreferences shared = getActivity().getSharedPreferences("purchaseOrder",Context.MODE_PRIVATE);
        String purchaseOrder = shared.getString("key","");

        tvPurchaseOrder.setText(purchaseOrder);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ProductMainActivity.mainIntent(getContext());
                startActivity(intent);
            }
        });


        return successView;
    }

}
