package xyz.waiphyoag.shopify.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.data.vo.SampleVo;
import xyz.waiphyoag.shopify.data.vo.TopTrendsVO;
import xyz.waiphyoag.shopify.delegates.ProductMainScreenDelegate;

import xyz.waiphyoag.shopify.viewholders.SampleForTopTrendsViewHolder;

/**
 * Created by WaiPhyoAg on 8/30/19.
 */

public class ProductInTopTrendsAdapter extends BaseRecyclerAdapter<SampleForTopTrendsViewHolder,TopTrendsVO> {

    private ProductMainScreenDelegate mDelegate;

    public ProductInTopTrendsAdapter(Context context,ProductMainScreenDelegate productMainScreenDelegate) {
        super(context);
        mDelegate = productMainScreenDelegate;
    }

    @NonNull
    @Override
    public SampleForTopTrendsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_product_second_row,parent,false);
        SampleForTopTrendsViewHolder sampleForTopTrendsViewHolder= new SampleForTopTrendsViewHolder(view,mDelegate);
        return  sampleForTopTrendsViewHolder;
    }





    @Override
    public int getItemCount() {
        return mData.size();
    }
}
