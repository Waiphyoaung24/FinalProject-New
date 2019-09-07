package xyz.waiphyoag.shopify.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.data.vo.RandomThingsVO;
import xyz.waiphyoag.shopify.data.vo.SampleVo;
import xyz.waiphyoag.shopify.delegates.ProductMainScreenDelegate;
import xyz.waiphyoag.shopify.viewholders.SampleForRandomThingsViewHolder;

/**
 * Created by WaiPhyoAg on 8/31/19.
 */

public class RandomItemsAdapter extends BaseRecyclerAdapter<SampleForRandomThingsViewHolder,RandomThingsVO> {

    private ProductMainScreenDelegate mDelegate;
    public RandomItemsAdapter(Context context, ProductMainScreenDelegate productMainScreenDelegate) {
        super(context);
        mDelegate = productMainScreenDelegate;
    }

    @NonNull
    @Override
    public SampleForRandomThingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_random_things,parent,false);
        SampleForRandomThingsViewHolder sampleForRandomThingsViewHolder = new SampleForRandomThingsViewHolder(view,mDelegate);
        return sampleForRandomThingsViewHolder;
    }





    @Override
    public int getItemCount() {
        return mData.size();
    }
}
