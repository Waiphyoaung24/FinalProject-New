package xyz.waiphyoag.shopify.data.vo;

public class FavoriteVO {

    private String productId;
    private String productName;
    private String productPrice;
    private String productImage;

    public FavoriteVO() {

    }

    public FavoriteVO(String productId, String productName, String productPrice, String productImage) {
        this.productId = productId;
        this.productName = productName;

        this.productPrice = productPrice;
        this.productImage = productImage;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
