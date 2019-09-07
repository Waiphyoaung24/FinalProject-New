package xyz.waiphyoag.shopify.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.data.vo.RandomThingsVO;
import xyz.waiphyoag.shopify.data.vo.SampleVo;
import xyz.waiphyoag.shopify.delegates.ProductMainScreenDelegate;

/**
 * Created by WaiPhyoAg on 8/31/19.
 */

public class SampleForRandomThingsViewHolder extends BaseViewHolder<RandomThingsVO> {
    private ProductMainScreenDelegate mDelegate;
    @BindView(R.id.iv_random_profile)
    ImageView ivProdile;
    @BindView(R.id.tv_random_name)
    TextView tvName;

    public SampleForRandomThingsViewHolder(View itemView,ProductMainScreenDelegate productMainScreenDelegate) {
        super(itemView);
        mDelegate = productMainScreenDelegate;
    }



    @Override
    public void onClick(View v) {
        mDelegate.onTapRandom();

    }

    @Override
    public void setData(RandomThingsVO data) {
        Glide.with(itemView.getContext())
                .load(data.getProductImage())
                .into(ivProdile);
        tvName.setText(data.getProductTitle());



    }
}
