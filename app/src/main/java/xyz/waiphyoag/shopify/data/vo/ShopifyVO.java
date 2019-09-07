package xyz.waiphyoag.shopify.data.vo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by WaiPhyoAg on 9/2/19.
 */

public class ShopifyVO {

    @SerializedName("Promotion")
    private List<PromotionVO> promotionVOS;

    @SerializedName("Designer")
    private List<DesignerVO> designerVOS;
    @SerializedName("RandomThings")
    private List<RandomThingsVO> randomThingsVOS;
    @SerializedName("TopTrend")
    private List<TopTrendsVO>topTrendsVOS;
    @SerializedName("User")
    private List<UserVO> userVOS;

    public List<PromotionVO> getPromotionVOS() {
        return promotionVOS;
    }

    public void setPromotionVOS(List<PromotionVO> promotionVOS) {
        this.promotionVOS = promotionVOS;
    }

    public List<DesignerVO> getDesignerVOS() {
        return designerVOS;
    }

    public void setDesignerVOS(List<DesignerVO> designerVOS) {
        this.designerVOS = designerVOS;
    }

    public List<RandomThingsVO> getRandomThingsVOS() {
        return randomThingsVOS;
    }

    public void setRandomThingsVOS(List<RandomThingsVO> randomThingsVOS) {
        this.randomThingsVOS = randomThingsVOS;
    }

    public List<TopTrendsVO> getTopTrendsVOS() {
        return topTrendsVOS;
    }

    public void setTopTrendsVOS(List<TopTrendsVO> topTrendsVOS) {
        this.topTrendsVOS = topTrendsVOS;
    }

    public List<UserVO> getUserVOS() {
        return userVOS;
    }

    public void setUserVOS(List<UserVO> userVOS) {
        this.userVOS = userVOS;
    }
}
