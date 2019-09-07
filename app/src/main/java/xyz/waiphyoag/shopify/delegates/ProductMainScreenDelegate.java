package xyz.waiphyoag.shopify.delegates;

/**
 * Created by WaiPhyoAg on 9/2/19.
 */

public interface ProductMainScreenDelegate {

    void onTapDesignerItem(String productId);
    void onTapTopTrends(String productId);
    void onTapSeeMore();
    void onTapRandom();
}
