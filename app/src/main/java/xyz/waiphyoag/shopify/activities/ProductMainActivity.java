package xyz.waiphyoag.shopify.activities;

import android.content.Intent;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.waiphyoag.shopify.R;

import xyz.waiphyoag.shopify.adapters.ProductInDesignerAdapter;
import xyz.waiphyoag.shopify.adapters.ProductInTopTrendsAdapter;
import xyz.waiphyoag.shopify.adapters.PromotionItemViewPager;
import xyz.waiphyoag.shopify.adapters.RandomItemsAdapter;
import xyz.waiphyoag.shopify.components.SmartRecyclerView;
import xyz.waiphyoag.shopify.data.model.ProductModel;
import xyz.waiphyoag.shopify.data.vo.SharedParent;
import xyz.waiphyoag.shopify.data.vo.ShopNowVO;
import xyz.waiphyoag.shopify.data.vo.ShopifyVO;
import xyz.waiphyoag.shopify.delegates.ProductMainScreenDelegate;
import xyz.waiphyoag.shopify.events.LoadProductListEvent;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

public class ProductMainActivity extends BaseActivity implements ProductMainScreenDelegate {


    @BindView(R.id.tabDots)
    TabLayout tabLayout;
    @BindView(R.id.vp_promotion_item)
    ViewPager vpPromotionItem;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.btn_explore)
    Button btnExplore;
    @BindView(R.id.rv_designer_product)
    SmartRecyclerView rvDesignerProduct;
    @BindView(R.id.rv_RandomThings)
    SmartRecyclerView rvRandomThings;
    @BindView(R.id.rv_TopTrends)
    SmartRecyclerView rvTopTrends;

    @BindView(R.id.tv_first_show_all)
    TextView tvShowAllFirst;

    @BindView(R.id.tv_second_show_all)
    TextView tvShowAll;


    private PromotionItemViewPager mPromotionItemViewPager;
    private ProductInDesignerAdapter mDesingerAdapter;
    private ProductInTopTrendsAdapter mTopTrendsAdapter;
    private RandomItemsAdapter mRandomAdapter;

    public static Intent mainIntent(Context context) {
        Intent intent = new Intent(context, ProductMainActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_main);
        getSupportActionBar().hide();
        ButterKnife.bind(this, this);


        mPromotionItemViewPager = new PromotionItemViewPager();
        vpPromotionItem.setAdapter(mPromotionItemViewPager);
        tabLayout.setupWithViewPager(vpPromotionItem, true);


        mDesingerAdapter = new ProductInDesignerAdapter(this, this);
        LinearLayoutManager linearLayoutManagerforDesigner = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rvDesignerProduct.setAdapter(mDesingerAdapter);
        rvDesignerProduct.setLayoutManager(linearLayoutManagerforDesigner);

        mTopTrendsAdapter = new ProductInTopTrendsAdapter(this, this);
        LinearLayoutManager linearLayoutManagerforTopTrends = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rvTopTrends.setAdapter(mTopTrendsAdapter);
        rvTopTrends.setLayoutManager(linearLayoutManagerforTopTrends);


        mRandomAdapter = new RandomItemsAdapter(this, this);
        LinearLayoutManager linearLayoutManagerforRandom = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvRandomThings.setAdapter(mRandomAdapter);
        rvRandomThings.setLayoutManager(linearLayoutManagerforRandom);


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
                        Toast.makeText(getApplicationContext(), "This is already a home screen", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.item_shop:
                        Intent intentForShop = ProductListActivity.listIntent(getApplicationContext());
                        startActivity(intentForShop);
                        finish();


                        break;

                    case R.id.item_cart:
                        Intent intentforCart = AddToCartActivity.cartIntentForNoItem(getApplicationContext());
                        startActivity(intentforCart);
                        finish();

                        break;

//                    case R.id.item_profile :
//                        break;
                }

                return true;
            }
        });

        btnExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ProductListActivity.listIntent(getApplicationContext());
                startActivity(intent);
            }
        });


        ProductModel.getInstance().startLoadingTopTrendsProduct();
        ProductModel.getInstance().startLoadingRandomThingsProduct();

        ProductModel.getInstance().startLoadingDesignerProduct();


        ProductModel.getInstance().startLoadingPromotionProduct();


//
    tvShowAllFirst.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = ProductListActivity.listIntent(getApplicationContext());
            startActivity(intent);

        }
    });

    tvShowAll.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = ProductListActivity.listIntent(getApplicationContext());
            startActivity(intent);
        }
    });

    }


    @Override
    public void onTapDesignerItem(String productId) {
        Intent intentForDetail = ProductDetailActivity.detailIntentForDesigner(getApplicationContext(), productId);
        startActivity(intentForDetail);


    }

    @Override
    public void onTapTopTrends(String productId) {
        Intent intentForDetail = ProductDetailActivity.detailIntentForTopTrends(getApplicationContext(), productId);
        startActivity(intentForDetail);

    }

    @Override
    public void onTapSeeMore() {

    }

    @Override
    public void onTapRandom() {
        Intent intent = ProductListActivity.listIntent(getApplicationContext());
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


    @Subscribe
    public void onLoadDesignerProdut(LoadProductListEvent.LoadDesignerProduct designerProduct) {
        mDesingerAdapter.setNewData(designerProduct.getLoadDeisngerProduct());

    }

    @Subscribe
    public void onLoadTopTrends(LoadProductListEvent.loadTopTrendsProduct topTrendsProduct) {
        mTopTrendsAdapter.setNewData(topTrendsProduct.getLoadTopTrendsList());


    }

    @Subscribe
    public void onLoadsRandomthings(LoadProductListEvent.loadRandomThingsProduct randomThingsProduct) {

        mRandomAdapter.setNewData(randomThingsProduct.getLoadRandomThingsList());


    }

    @Subscribe
    public void onLoadPromotionThings(LoadProductListEvent.loadPromotionThings promotionThings) {

        mPromotionItemViewPager.setData(promotionThings.getLoadPromotionThings());
    }
}



