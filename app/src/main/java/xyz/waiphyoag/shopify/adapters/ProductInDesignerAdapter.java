package xyz.waiphyoag.shopify.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.waiphyoag.shopify.R;

import xyz.waiphyoag.shopify.data.vo.DesignerVO;
import xyz.waiphyoag.shopify.data.vo.SampleVo;
import xyz.waiphyoag.shopify.delegates.ProductMainScreenDelegate;
import xyz.waiphyoag.shopify.viewholders.BaseViewHolder;
import xyz.waiphyoag.shopify.viewholders.ItemInProductListViewHolder;
import xyz.waiphyoag.shopify.viewholders.SampleForDesignerViewHolder;

/**
 * Created by WaiPhyoAg on 8/30/19.
 */

public class ProductInDesignerAdapter extends BaseRecyclerAdapter<SampleForDesignerViewHolder,DesignerVO> {


    private ProductMainScreenDelegate mDelegate;
    public ProductInDesignerAdapter(Context context, ProductMainScreenDelegate productMainScreenDelegate) {
        super(context);
        mDelegate = productMainScreenDelegate;
    }

    @NonNull
    @Override
    public SampleForDesignerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.item_product_first_row,parent,false);
         SampleForDesignerViewHolder sampleViewHolder = new SampleForDesignerViewHolder(view,mDelegate);
        return  sampleViewHolder;
    }







    @Override
    public int getItemCount() {
        return mData.size();
    }
}
