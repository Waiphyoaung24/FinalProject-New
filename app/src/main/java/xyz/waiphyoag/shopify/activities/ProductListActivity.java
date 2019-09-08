package xyz.waiphyoag.shopify.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.adapters.ProductListAdapter;
import xyz.waiphyoag.shopify.data.model.ProductModel;
import xyz.waiphyoag.shopify.delegates.ProductListScreenDelegate;
import xyz.waiphyoag.shopify.events.LoadShopNowListEvent;

/**
 * Created by WaiPhyoAg on 8/31/19.
 */

public class ProductListActivity extends BaseActivity implements ProductListScreenDelegate {


    @BindView(R.id.rv_product_list)
    RecyclerView rvProductList;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.ivProfile)
    ImageView ivProfile;
    @BindView(R.id.iv_favorite)
    ImageView ivFavroite;
    @BindView(R.id.indicator)
    AVLoadingIndicatorView indicatorView;


    private ProductListAdapter mAdapter;

    public static Intent listIntent(Context context) {
        Intent intent = new Intent(context, ProductListActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this, this);


        mAdapter = new ProductListAdapter(this, this);
        GridLayoutManager gridLayoutManagerForProductList = new GridLayoutManager(getApplicationContext(), 2);
        rvProductList.setAdapter(mAdapter);
        rvProductList.setLayoutManager(gridLayoutManagerForProductList);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                switch (id) {
                    case R.id.item_home:

                        Intent intentForHome = ProductMainActivity.mainIntent(getApplicationContext());
                        startActivity(intentForHome);
                        finish();


                        break;
                    case R.id.item_shop:

                        Toast.makeText(getApplicationContext(), "This is already a Shop List", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.item_favorite:

                        Intent intent = FavoriteItemListActivity.intent(getApplicationContext());
                        startActivity(intent);
                        finish();

                        break;


                }

                return true;
            }
        });


        ProductModel.getInstance().startLoadingShopNowProduct();


        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ActivityUserProfile.class);
                startActivity(intent);


            }
        });
        ivFavroite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = FavoriteItemListActivity.intent(getApplicationContext());
                startActivity(intent);
                finish();

            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }


    }


    @Override
    public void onStop() {
        super.onStop();

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataLoadedShopNow(LoadShopNowListEvent event) {


        if (event.getShopNowVOList().isEmpty()) {

            rvProductList.setVisibility(View.GONE);

            }else
        {
            rvProductList.setVisibility(View.VISIBLE);
            mAdapter.setNewData(event.getShopNowVOList());
            indicatorView.hide();

        }


    }


    @Override
    public void onTapProductListImage(String productId) {

        Intent intentForDetail = ProductDetailActivity.detailIntentForList(getApplicationContext(), productId);
        startActivity(intentForDetail);

    }
}
