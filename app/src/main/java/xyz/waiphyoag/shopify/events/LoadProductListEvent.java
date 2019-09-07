package xyz.waiphyoag.shopify.events;

import java.util.List;

import xyz.waiphyoag.shopify.data.vo.DesignerVO;
import xyz.waiphyoag.shopify.data.vo.PromotionVO;
import xyz.waiphyoag.shopify.data.vo.RandomThingsVO;
import xyz.waiphyoag.shopify.data.vo.SharedParent;
import xyz.waiphyoag.shopify.data.vo.TopTrendsVO;

/**
 * Created by WaiPhyoAg on 9/3/19.
 */

public class LoadProductListEvent {

    public static class SuccessEvent {
        private List<SharedParent> successData;

        public SuccessEvent(List<SharedParent> successData) {
            this.successData = successData;
        }

        public List<SharedParent> getSuccessData() {
            return successData;
        }
    }

    public static class LoadDesignerProduct {
        private List<DesignerVO> loadDeisngerProduct;

        public LoadDesignerProduct(List<DesignerVO> loadDeisngerProduct) {
            this.loadDeisngerProduct = loadDeisngerProduct;
        }

        public List<DesignerVO> getLoadDeisngerProduct() {
            return loadDeisngerProduct;
        }
    }

    public static class loadTopTrendsProduct {

        private List<TopTrendsVO> loadTopTrendsList;

        public loadTopTrendsProduct(List<TopTrendsVO> loadTopTrendsList) {
            this.loadTopTrendsList = loadTopTrendsList;
        }

        public List<TopTrendsVO> getLoadTopTrendsList() {
            return loadTopTrendsList;
        }
    }

    public static class loadRandomThingsProduct {

        private List<RandomThingsVO> loadRandomThingsList;

        public loadRandomThingsProduct(List<RandomThingsVO> loadRandomThingsList) {
            this.loadRandomThingsList = loadRandomThingsList;
        }

        public List<RandomThingsVO> getLoadRandomThingsList() {
            return loadRandomThingsList;
        }
    }

    public static class loadPromotionThings {
        private List<PromotionVO> loadPromotionThings;

        public loadPromotionThings(List<PromotionVO> loadPromotionThings) {
            this.loadPromotionThings = loadPromotionThings;
        }

        public List<PromotionVO> getLoadPromotionThings() {
            return loadPromotionThings;
        }
    }
}
