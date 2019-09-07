package xyz.waiphyoag.shopify.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by WaiPhyoAg on 9/2/19.
 */

public class ShopNowVO {

    @SerializedName("productTitle")
    private String productTitle;
    @SerializedName("productPrice")
    private String productPrice;
    @SerializedName("productImage")
    private String productImage;
    @SerializedName("productId")
    private String productId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
