package xyz.waiphyoag.shopify.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.microedition.khronos.opengles.GL;

import butterknife.BindView;
import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.data.vo.DesignerVO;
import xyz.waiphyoag.shopify.data.vo.SampleVo;
import xyz.waiphyoag.shopify.delegates.ProductMainScreenDelegate;

/**
 * Created by WaiPhyoAg on 8/31/19.
 */

public class SampleForDesignerViewHolder extends BaseViewHolder<DesignerVO> {
    @BindView(R.id.tv_first_content_item)
    TextView tvProductName;
    @BindView(R.id.iv_productImage)
    ImageView ivProductImage;
    @BindView(R.id.tv_first_content_price)
    TextView tvProductPrice;

    private DesignerVO designerVO;
    private ProductMainScreenDelegate mDelegate;

    public SampleForDesignerViewHolder(View itemView, ProductMainScreenDelegate productMainScreenDelegate) {
        super(itemView);
        mDelegate = productMainScreenDelegate;
    }


    @Override
    public void onClick(View v) {
        mDelegate.onTapDesignerItem(designerVO.getProductId());

    }

    @Override
    public void setData(DesignerVO data) {
        designerVO = data;
        tvProductName.setText(data.getProductTitle());

        Glide.with(itemView.getContext())
                .load(data.getProductImage())
                .into(ivProductImage);
        tvProductPrice.setText(data.getProductPrice());


    }
}
