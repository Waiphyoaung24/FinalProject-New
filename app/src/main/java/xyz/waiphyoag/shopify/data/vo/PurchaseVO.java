package xyz.waiphyoag.shopify.data.vo;

/**
 * Created by WaiPhyoAg on 9/4/19.
 */

public class PurchaseVO {
    private String userId;
    private String productId;
    private String address;

    public PurchaseVO(String userId, String productId, String address) {
        this.userId = userId;
        this.productId = productId;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
