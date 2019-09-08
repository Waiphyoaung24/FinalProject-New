package xyz.waiphyoag.shopify.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.adapters.FavoriteItemAdapter;
import xyz.waiphyoag.shopify.components.SmartRecyclerView;
import xyz.waiphyoag.shopify.data.model.ProductModel;
import xyz.waiphyoag.shopify.delegates.FavoriteItemDelegate;
import xyz.waiphyoag.shopify.events.LoadProductListEvent;

public class FavoriteItemListActivity extends AppCompatActivity implements FavoriteItemDelegate {
    @BindView(R.id.rv_favorite)
    SmartRecyclerView rvFavorite;
    private FavoriteItemAdapter mAdapter;
    @BindView(R.id.ivCart)
    ImageView ivCart;
    @BindView(R.id.btnShopNow)
    Button btnShopNow;
    @BindView(R.id.iv_back)
    FrameLayout flBack;
    @BindView(R.id.text)
    TextView tvText;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public static Intent intent(Context context) {
        Intent intent = new Intent(context, FavoriteItemListActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_item);
        ButterKnife.bind(this, this);

        mAdapter = new FavoriteItemAdapter(this, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvFavorite.setAdapter(mAdapter);
        rvFavorite.setLayoutManager(linearLayoutManager);
        ProductModel.getInstance().onLoadingFavoriteItem();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

        flBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ProductListActivity.listIntent(getApplicationContext());
                startActivity(intent);
                finish();
            }
        });


        ivCart.setVisibility(View.VISIBLE);
        btnShopNow.setVisibility(View.VISIBLE);
        tvText.setVisibility(View.VISIBLE);

        btnShopNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ProductMainActivity.mainIntent(getApplicationContext());
                startActivity(intent);
            }
        });

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoadedFavoriteItem(LoadProductListEvent.loadFavoriteItem event) {

        mAdapter.setNewData(event.getLoadFavoriteThings());
        if (mAdapter.getItemCount() > 0) {
            ivCart.setVisibility(View.GONE);
            btnShopNow.setVisibility(View.GONE);
            tvText.setVisibility(View.GONE);
        }

    }


    @Override
    public void onTapBuyNow(String productId) {

        if (productId.startsWith("T")) {
            Intent intent = AddToCartActivity.cartIntentForTopTrends(getApplicationContext(), productId);
            startActivity(intent);

        } else if (productId.startsWith("D")) {
            Intent intent = AddToCartActivity.cartIntentForDesigner(getApplicationContext(), productId);
            startActivity(intent);

        } else if (productId.startsWith("S")) {
            Intent intent = AddToCartActivity.cartIntentForList(getApplicationContext(), productId);
            startActivity(intent);

        }

    }

    @Override
    public void onTapRemoveNow(String productId) {
        ProductModel.getInstance().onRemoveFavoriteItem(productId);

    }
}
