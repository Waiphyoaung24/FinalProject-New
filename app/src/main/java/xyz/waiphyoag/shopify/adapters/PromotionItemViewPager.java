package xyz.waiphyoag.shopify.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.data.vo.PromotionVO;
import xyz.waiphyoag.shopify.viewholders.ImageViewItem;

/**
 * Created by WaiPhyoAg on 8/30/19.
 */

public class PromotionItemViewPager extends PagerAdapter {

    private List<PromotionVO> promotionVOList;

    public PromotionItemViewPager() {
        promotionVOList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return promotionVOList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Context context = container.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ImageViewItem view = (ImageViewItem) layoutInflater.inflate(R.layout.item_promotion_item, container, false);
        container.addView(view);
        view.onBindDataItemToPromotion(promotionVOList.get(position));
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void setData(List<PromotionVO> promotionVOS) {
        promotionVOList = promotionVOS;
        notifyDataSetChanged();
    }
}
