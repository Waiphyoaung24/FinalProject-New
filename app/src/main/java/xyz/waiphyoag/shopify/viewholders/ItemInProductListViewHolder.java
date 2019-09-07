package xyz.waiphyoag.shopify.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import butterknife.BindView;
import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.data.vo.SampleVo;
import xyz.waiphyoag.shopify.data.vo.ShopNowVO;
import xyz.waiphyoag.shopify.delegates.ProductListScreenDelegate;

/**
 * Created by WaiPhyoAg on 8/31/19.
 */

public class ItemInProductListViewHolder extends BaseViewHolder<ShopNowVO> {

    @BindView(R.id.tv_proudct_name)
    TextView tvProductName;
    @BindView(R.id.iv_product_image)
    ImageView ivProductImage;
    @BindView(R.id.tv_product_price)
    TextView tvProductPrice;

    private ShopNowVO shopNowVO;

    private ProductListScreenDelegate mDelegate;


    public ItemInProductListViewHolder(View itemView,ProductListScreenDelegate productListScreenDelegate) {
        super(itemView);
        mDelegate = productListScreenDelegate;

    }


    @Override
    public void onClick(View v) {
        mDelegate.onTapProductListImage(shopNowVO.getProductId());
    }


    @Override
    public void setData(ShopNowVO data) {

       shopNowVO = data;
        tvProductName.setText(data.getProductTitle());

        Glide.with(itemView.getContext())
                .load(data.getProductImage())
                .into(ivProductImage);
        tvProductPrice.setText(data.getProductPrice());


    }
}
