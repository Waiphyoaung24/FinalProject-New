package xyz.waiphyoag.shopify.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.data.vo.SampleVo;
import xyz.waiphyoag.shopify.data.vo.ShopNowVO;
import xyz.waiphyoag.shopify.delegates.ProductListScreenDelegate;
import xyz.waiphyoag.shopify.viewholders.BaseViewHolder;
import xyz.waiphyoag.shopify.viewholders.ItemInProductListViewHolder;
import xyz.waiphyoag.shopify.viewholders.SampleForDesignerViewHolder;

/**
 * Created by WaiPhyoAg on 8/31/19.
 */

public class ProductListAdapter extends BaseRecyclerAdapter<ItemInProductListViewHolder, ShopNowVO> {

    private ProductListScreenDelegate mDelegate;

    public ProductListAdapter(Context context, ProductListScreenDelegate productListScreenDelegate) {

        super(context);
        mDelegate = productListScreenDelegate;
    }

    @NonNull
    @Override
    public ItemInProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_product_list, parent, false);
        ItemInProductListViewHolder itemInProductListViewHolder = new ItemInProductListViewHolder(view, mDelegate);
        return itemInProductListViewHolder;
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }
}
