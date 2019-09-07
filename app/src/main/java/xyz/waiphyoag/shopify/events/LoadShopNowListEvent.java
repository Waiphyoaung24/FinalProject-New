package xyz.waiphyoag.shopify.events;

import java.util.List;

import xyz.waiphyoag.shopify.data.vo.ShopNowVO;

/**
 * Created by WaiPhyoAg on 9/3/19.
 */

public class LoadShopNowListEvent {
    private List<ShopNowVO> shopNowVOList;

    public LoadShopNowListEvent(List<ShopNowVO> shopNowVOList) {
        this.shopNowVOList = shopNowVOList;
    }

    public List<ShopNowVO> getShopNowVOList() {
        return shopNowVOList;
    }
}
