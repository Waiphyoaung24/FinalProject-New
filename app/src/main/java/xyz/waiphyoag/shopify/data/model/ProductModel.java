package xyz.waiphyoag.shopify.data.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import xyz.waiphyoag.shopify.ShopifyApplication;
import xyz.waiphyoag.shopify.activities.PaymentActivity;
import xyz.waiphyoag.shopify.data.vo.DesignerVO;
import xyz.waiphyoag.shopify.data.vo.FavoriteVO;
import xyz.waiphyoag.shopify.data.vo.PromotionVO;
import xyz.waiphyoag.shopify.data.vo.PurchaseVO;
import xyz.waiphyoag.shopify.data.vo.RandomThingsVO;
import xyz.waiphyoag.shopify.data.vo.SharedParent;
import xyz.waiphyoag.shopify.data.vo.ShopNowVO;
import xyz.waiphyoag.shopify.data.vo.ShopifyVO;
import xyz.waiphyoag.shopify.data.vo.TopTrendsVO;
import xyz.waiphyoag.shopify.data.vo.UserVO;
import xyz.waiphyoag.shopify.events.LoadProductListEvent;
import xyz.waiphyoag.shopify.events.LoadShopNowListEvent;

/**
 * Created by WaiPhyoAg on 9/2/19.
 */

public class ProductModel {
    private static final String SHOPIFY = "Shopify";


    public ArrayList<DesignerVO> designerVOS = new ArrayList<>();
    public List<DesignerVO> designerVOList = new ArrayList<>();

    public ArrayList<TopTrendsVO> topTrendsVo = new ArrayList<>();
    public List<TopTrendsVO> topTrendsVOList = new ArrayList<>();

    public List<RandomThingsVO> randomThingsVOList = new ArrayList<>();
    public ArrayList<RandomThingsVO> randomThingsVOS = new ArrayList<>();

    public ArrayList<ShopNowVO> shopNowVOS = new ArrayList<>();
    public List<ShopNowVO> shopNowVOList = new ArrayList<>();

    public ArrayList<PromotionVO> promotionVOS = new ArrayList<>();
    public List<PromotionVO> promotionVOList = new ArrayList<>();

    public ArrayList<FavoriteVO> favoriteVOS = new ArrayList<>();
    public List<FavoriteVO> favoriteVOList = new ArrayList<>();


    public List<SharedParent> mCollectionList;

    public List<SharedParent> getCollectionList() {
        return mCollectionList;
    }

    public UserVO userVO;

    private static ProductModel mInstance;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mFirebaseUser;


    private FirebaseAuth mFirebaseAuth;


    public ProductModel() {

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();


        mCollectionList = new ArrayList<>();


    }

    public static ProductModel getInstance() {
        if (mInstance == null) {
            mInstance = new ProductModel();
        }

        return mInstance;
    }


    public void startLoadingDesignerProduct() {
        DatabaseReference shopifyNodeDBR = mDatabaseReference.child(SHOPIFY).child("Designer");


        shopifyNodeDBR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                designerVOS.clear();

                if (dataSnapshot != null) {
                    for (DataSnapshot products : dataSnapshot.getChildren()) {
                        DesignerVO designerVO = products.getValue(DesignerVO.class);

                        designerVOS.add(designerVO);
                    }

                    designerVOList.clear();
                    designerVOList.addAll(designerVOS);
                    LoadProductListEvent.LoadDesignerProduct loadDesignerProduct = new LoadProductListEvent.LoadDesignerProduct(designerVOList);
                    EventBus.getDefault().post(loadDesignerProduct);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void startLoadingTopTrendsProduct() {
        DatabaseReference shopifyNodeDBR = mDatabaseReference.child(SHOPIFY).child("TopTrend");


        shopifyNodeDBR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                topTrendsVo.clear();
                if (dataSnapshot != null) {
                    for (DataSnapshot products : dataSnapshot.getChildren()) {
                        TopTrendsVO topTrendsVO = products.getValue(TopTrendsVO.class);

                        topTrendsVo.add(topTrendsVO);
                    }

                    topTrendsVOList.clear();
                    topTrendsVOList.addAll(topTrendsVo);

                    LoadProductListEvent.loadTopTrendsProduct loadTopTrendsProduct = new LoadProductListEvent.loadTopTrendsProduct(topTrendsVOList);
                    EventBus.getDefault().post(loadTopTrendsProduct);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void startLoadingShopNowProduct() {
        DatabaseReference shopifyNodeDBR = mDatabaseReference.child(SHOPIFY).child("ShopNow");


        shopifyNodeDBR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                shopNowVOS.clear();

                if (dataSnapshot != null) {
                    for (DataSnapshot products : dataSnapshot.getChildren()) {
                        ShopNowVO shopNowVO = products.getValue(ShopNowVO.class);
                        shopNowVOS.add(shopNowVO);

                    }
                    shopNowVOList.clear();
                    shopNowVOList.addAll(shopNowVOS);

                    LoadShopNowListEvent loadShopNowListEvent = new LoadShopNowListEvent(shopNowVOList);
                    EventBus.getDefault().post(loadShopNowListEvent);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void startLoadingRandomThingsProduct() {
        DatabaseReference shopifyNodeDBR = mDatabaseReference.child(SHOPIFY).child("RandomThings");


        shopifyNodeDBR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                randomThingsVOS.clear();

                if (dataSnapshot != null) {
                    for (DataSnapshot products : dataSnapshot.getChildren()) {
                        RandomThingsVO randomThingsVO = products.getValue(RandomThingsVO.class);
                        randomThingsVOS.add(randomThingsVO);
                    }
                    randomThingsVOList.clear();
                    randomThingsVOList.addAll(randomThingsVOS);

                    LoadProductListEvent.loadRandomThingsProduct randomThingsProduct = new LoadProductListEvent.loadRandomThingsProduct(randomThingsVOList);
                    EventBus.getDefault().post(randomThingsProduct);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void startLoadingPromotionProduct() {
        DatabaseReference shopifyNodeDBR = mDatabaseReference.child(SHOPIFY).child("Promotion");


        shopifyNodeDBR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                promotionVOS.clear();

                if (dataSnapshot != null) {
                    for (DataSnapshot products : dataSnapshot.getChildren()) {
                        PromotionVO promotionVO = products.getValue(PromotionVO.class);
                        promotionVOS.add(promotionVO);
                    }
                    promotionVOList.clear();
                    promotionVOList.addAll(promotionVOS);

                    LoadProductListEvent.loadPromotionThings promotionThings = new LoadProductListEvent.loadPromotionThings(promotionVOList);
                    EventBus.getDefault().post(promotionThings);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public ShopNowVO getProductListDetilById(String productId) {
        if (!shopNowVOList.isEmpty()) {
            for (ShopNowVO shopNowVO : shopNowVOList) {
                if (shopNowVO.getProductId().equals(productId)) {
                    return shopNowVO;
                }
            }
        }
        return null;

    }

    public DesignerVO getProductListDetilByIdForDesigner(String productId) {
        if (!designerVOList.isEmpty()) {
            for (DesignerVO designerVO : designerVOList) {
                if (designerVO.getProductId().equals(productId)) {
                    return designerVO;
                }
            }
        }
        return null;

    }

    public TopTrendsVO getProductListDetilByIdForTopTrends(String productId) {
        if (!topTrendsVOList.isEmpty()) {
            for (TopTrendsVO topTrendsVO : topTrendsVOList) {
                if (topTrendsVO.getProductId().equals(productId)) {
                    return topTrendsVO;
                }
            }
        }
        return null;

    }

    public void addNewUser(String userId, String userName, String email) {

        userVO = new UserVO(userId, userName, email);
        mDatabaseReference.child(SHOPIFY).child("User").child(userId).setValue(userVO);

    }

    public void addFavoriteProductForDesign(DesignerVO designerVO) {
        FavoriteVO favoriteVO = new FavoriteVO(designerVO.getProductId(), designerVO.getProductTitle(), designerVO.getProductPrice(), designerVO.getProductImage());
        mDatabaseReference.child(SHOPIFY).child("User").child(getUserId()).child("Favorite").child(designerVO.getProductId()).setValue(favoriteVO);
        designerVO.setFavorite(true);

    }


    public void purchaseProduct(String userId, String productId, String address, String purchaseId) {

        PurchaseVO purchaseVO = new PurchaseVO(userId, productId, address, purchaseId);

        Long tsLong = System.currentTimeMillis() / 10;
        String ts = tsLong.toString();
        String key = productId + "__" + ts;


        mDatabaseReference.child(SHOPIFY).child("Purchase").child(userVO.getUserId()).child(" Total Purchases").child(key).setValue(purchaseVO);


    }

    public void onLoadingFavoriteItem() {
        DatabaseReference databaseNode = mDatabaseReference.child(SHOPIFY).child("User").child(userVO.getUserId()).child("Favorite");
        databaseNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                favoriteVOS.clear();

                if (dataSnapshot != null) {
                    for (DataSnapshot products : dataSnapshot.getChildren()) {
                        FavoriteVO favoriteVO = products.getValue(FavoriteVO.class);
                        favoriteVOS.add(favoriteVO);
                    }
                    favoriteVOList.clear();
                    favoriteVOList.addAll(favoriteVOS);

                    LoadProductListEvent.loadFavoriteItem favoriteItem = new LoadProductListEvent.loadFavoriteItem(favoriteVOList);
                    EventBus.getDefault().post(favoriteItem);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
    }


    public void onRemoveFavoriteItem(String productId) {

        DatabaseReference databaseNode = mDatabaseReference.child(SHOPIFY).child("User").child(userVO.getUserId()).child("Favorite").child(productId);
        databaseNode.removeValue();


    }


    public void authenticateUserWithGoogleAccount(final GoogleSignInAccount signInAccount, final SignInWithGoogleAccountDelegate delegate) {
        AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                delegate.onSuccessSignIn(signInAccount);
            }
        });
    }

    public interface SignInWithGoogleAccountDelegate {
        void onSuccessSignIn(GoogleSignInAccount signInAccount);

        void onFailureSignIn(String map);
    }

    public String getUserId() {

        String userId = userVO.getUserId();
        return userId;
    }
}
