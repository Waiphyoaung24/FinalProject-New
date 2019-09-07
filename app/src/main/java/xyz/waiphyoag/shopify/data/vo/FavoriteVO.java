package xyz.waiphyoag.shopify.data.vo;

public class FavoriteVO {

    private String productId;
    private String productName;
    private String productColor;
    private String productSize;
    private String productPrice;
    private String productImage;

    public FavoriteVO(String productId, String productName, String productColor, String productSize, String productPrice, String productImage) {
        this.productId = productId;
        this.productName = productName;
        this.productColor = productColor;
        this.productSize = productSize;
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

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
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
