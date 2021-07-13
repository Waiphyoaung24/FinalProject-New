package xyz.waiphyoag.shopify.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.annotation.Nullable;


import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.data.model.ProductModel;
import xyz.waiphyoag.shopify.data.vo.DesignerVO;
import xyz.waiphyoag.shopify.data.vo.ShopNowVO;
import xyz.waiphyoag.shopify.data.vo.TopTrendsVO;

/**
 * Created by WaiPhyoAg on 9/1/19.
 */

public class AddToCartActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    FrameLayout ivBack;
    @BindView(R.id.iv_addToCart)
    ImageView ivAddtoCart;
    @BindView(R.id.tv_productName)
    TextView tvProductName;
    @BindView(R.id.tv_product_price)
    TextView tvProductPrice;
    @BindView(R.id.tv_total_price)
    TextView totalPrice;
    @BindView(R.id.tv_Product_Price_review)
    TextView tvProductPriceReview;
    @BindView(R.id.btn_checkOut)
    Button btnCheckOut;

    @BindView(R.id.rl_product)
    RelativeLayout rlProduct;
    @BindView(R.id.rl_empty_cart)
    RelativeLayout rlEmpty;

    public static Intent cartIntentForList(Context context, String productId) {
        Intent intent = new Intent(context, AddToCartActivity.class);
        intent.putExtra("productId", productId);
        intent.putExtra("detailType", 0);
        return intent;
    }


    public static Intent cartIntentForDesigner(Context context, String productId) {
        Intent intent = new Intent(context, AddToCartActivity.class);
        intent.putExtra("productId", productId);
        intent.putExtra("detailType", 1);
        return intent;
    }

    public static Intent cartIntentForTopTrends(Context context, String productId) {
        Intent intent = new Intent(context, AddToCartActivity.class);
        intent.putExtra("productId", productId);
        intent.putExtra("detailType", 2);
        return intent;

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        ButterKnife.bind(this, this);


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }


        int detailType = getIntent().getIntExtra("detailType", -1);

        if (detailType == 0) {
            String productId = getIntent().getStringExtra("productId");
            ShopNowVO shopNowVO = ProductModel.getInstance().getProductListDetilById(productId);
            onGetAddToCartFromList(shopNowVO);
        } else if (detailType == 1) {
            String productId = getIntent().getStringExtra("productId");
            DesignerVO designerVO = ProductModel.getInstance().getProductListDetilByIdForDesigner(productId);
            onGetAddToCartFromDesigner(designerVO);


        } else if (detailType == 2) {
            String productId = getIntent().getStringExtra("productId");
            TopTrendsVO topTrendsVO = ProductModel.getInstance().getProductListDetilByIdForTopTrends(productId);
            onGetAddToCartFromTopTrends(topTrendsVO);

        }


    }

    public void onGetAddToCartFromList(final ShopNowVO shopNowVO) {

        Glide.with(getApplicationContext())
                .load(shopNowVO.getProductImage())
                .into(ivAddtoCart);
        tvProductName.setText(shopNowVO.getProductTitle());
        tvProductPrice.setText(shopNowVO.getProductPrice());
        totalPrice.setText(shopNowVO.getProductPrice());
        tvProductPriceReview.setText(shopNowVO.getProductPrice());

        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentForPayment = PaymentActivity.paymentIntentForList(getApplicationContext(), shopNowVO.getProductId());
                startActivity(intentForPayment);
                finish();
            }
        });


    }

    public void onGetAddToCartFromDesigner(final DesignerVO designerVO) {

        Glide.with(getApplicationContext())
                .load(designerVO.getProductImage())
                .into(ivAddtoCart);
        tvProductName.setText(designerVO.getProductTitle());
        tvProductPrice.setText(designerVO.getProductPrice());
        totalPrice.setText(designerVO.getProductPrice());
        tvProductPriceReview.setText(designerVO.getProductPrice());


        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentForPayment = PaymentActivity.paymentIntentForDesigner(getApplicationContext(), designerVO.getProductId());
                startActivity(intentForPayment);
                finish();
            }
        });


    }

    public void onGetAddToCartFromTopTrends(final TopTrendsVO topTrendsVO) {

        Glide.with(getApplicationContext())
                .load(topTrendsVO.getProductImage())
                .into(ivAddtoCart);
        tvProductName.setText(topTrendsVO.getProductTitle());
        tvProductPrice.setText(topTrendsVO.getProductPrice());
        totalPrice.setText(topTrendsVO.getProductPrice());
        tvProductPriceReview.setText(topTrendsVO.getProductPrice());


        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentForPayment = PaymentActivity.paymentIntentForTopTrends(getApplicationContext(), topTrendsVO.getProductId());
                startActivity(intentForPayment);
                finish();
            }
        });


    }
}
