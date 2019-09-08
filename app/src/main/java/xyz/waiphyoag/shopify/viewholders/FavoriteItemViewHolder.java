package xyz.waiphyoag.shopify.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;
import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.data.vo.FavoriteVO;
import xyz.waiphyoag.shopify.delegates.FavoriteItemDelegate;

public class FavoriteItemViewHolder extends BaseViewHolder<FavoriteVO> {
    private FavoriteItemDelegate mDelegate;
    @BindView(R.id.ivProductImage)
    ImageView ivProductImage;
    @BindView(R.id.view_remove)
    LinearLayout llRemove;
    @BindView(R.id.view_buynow)
    LinearLayout llBuyNow;


    private FavoriteVO favoriteVO;

    public FavoriteItemViewHolder(View itemView, FavoriteItemDelegate favoriteItemDelegate) {
        super(itemView);
        this.mDelegate = favoriteItemDelegate;
    }

    @Override
    public void setData(FavoriteVO data) {
        favoriteVO = data;
        Glide.with(itemView.getContext())
                .load(data.getProductImage())
                .into(ivProductImage);


    }

    @Override
    public void onClick(View v) {


    }

    @OnClick(R.id.view_buynow)
    public void onTapBuyNow(View view) {
        mDelegate.onTapBuyNow(favoriteVO.getProductId());
    }

    @OnClick(R.id.view_remove)
    public void onTapRemove(View view) {
        mDelegate.onTapRemoveNow(favoriteVO.getProductId());
    }
}
