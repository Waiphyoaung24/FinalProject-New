package xyz.waiphyoag.shopify.viewholders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import javax.microedition.khronos.opengles.GL;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.data.vo.PromotionVO;

/**
 * Created by WaiPhyoAg on 8/30/19.
 */

public class ImageViewItem extends FrameLayout {
    @BindView(R.id.iv_promotion_item)
    ImageView ivPromotionImage;
    public ImageViewItem(@NonNull Context context) {
        super(context);
    }

    public ImageViewItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    public void onBindDataItemToPromotion(PromotionVO promotionVO){

        Glide.with(ivPromotionImage.getContext())
                .load(promotionVO.getProductImage())
                .into(ivPromotionImage);


    }
}
