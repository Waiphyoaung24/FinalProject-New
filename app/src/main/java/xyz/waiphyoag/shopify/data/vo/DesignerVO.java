package xyz.waiphyoag.shopify.data.vo;

import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.SerializedName;

/**
 * Created by WaiPhyoAg on 9/2/19.
 */

public class DesignerVO implements SharedParent {

    @SerializedName("productTitle")
    private String productTitle;
    @SerializedName("productPrice")
    private String productPrice;
    @SerializedName("productImage")
    private String productImage;
    @SerializedName("productId")
    private String productId;


    private Boolean Favorite;

    public Boolean isFavorite() {
        return Favorite;
    }

    public void setFavorite(Boolean favorite) {
        Favorite = favorite;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
