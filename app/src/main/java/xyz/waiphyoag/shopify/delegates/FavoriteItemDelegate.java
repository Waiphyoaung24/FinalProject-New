package xyz.waiphyoag.shopify.delegates;

public interface FavoriteItemDelegate {

    void onTapBuyNow(String productId);
    void onTapRemoveNow(String productId);
}
