package xyz.waiphyoag.shopify.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.data.vo.FavoriteVO;
import xyz.waiphyoag.shopify.delegates.FavoriteItemDelegate;
import xyz.waiphyoag.shopify.viewholders.BaseViewHolder;
import xyz.waiphyoag.shopify.viewholders.FavoriteItemViewHolder;
import xyz.waiphyoag.shopify.viewholders.SampleForDesignerViewHolder;

public class FavoriteItemAdapter extends BaseRecyclerAdapter<BaseViewHolder, FavoriteVO> {

    private FavoriteItemDelegate mDelegate;
    public FavoriteItemAdapter(Context context,FavoriteItemDelegate favoriteItemDelegate) {
        super(context);
        this.mDelegate= favoriteItemDelegate;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.item_favorite_items,parent,false);
        FavoriteItemViewHolder itemViewHolder = new FavoriteItemViewHolder(view,mDelegate);
        return  itemViewHolder;
    }
}
