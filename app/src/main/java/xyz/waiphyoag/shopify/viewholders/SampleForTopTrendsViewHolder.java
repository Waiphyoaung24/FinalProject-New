package xyz.waiphyoag.shopify.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.data.vo.SampleVo;
import xyz.waiphyoag.shopify.data.vo.TopTrendsVO;
import xyz.waiphyoag.shopify.delegates.ProductMainScreenDelegate;

/**
 * Created by WaiPhyoAg on 8/31/19.
 */

public class SampleForTopTrendsViewHolder extends BaseViewHolder<TopTrendsVO> {

    @BindView(R.id.iv_image)
    ImageView ivProductImage;
    @BindView(R.id.tv_second_row_name)
    TextView tvProductName;
    @BindView(R.id.tv_second_content_price)
    TextView tvProductPrice;
    private ProductMainScreenDelegate mDelegate;

    private TopTrendsVO topTrendsVO;
    public SampleForTopTrendsViewHolder(View itemView,ProductMainScreenDelegate productMainScreenDelegate) {
        super(itemView);
        mDelegate = productMainScreenDelegate;
    }



    @Override
    public void onClick(View v) {
        mDelegate.onTapTopTrends(topTrendsVO.getProductId());

    }

    @Override
    public void setData(TopTrendsVO data) {
        topTrendsVO = data;

        Glide.with(itemView.getContext())
                .load(data.getProductImage())
                .into(ivProductImage);
        tvProductName.setText(data.getProductTitle());
        tvProductPrice.setText(data.getProductPrice());



    }
}
