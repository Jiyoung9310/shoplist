package com.example.zigzag.shoplist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XNOTE on 2018-05-13.
 */

public class AdpRecycler extends RecyclerView.Adapter<AdpRecycler.ItemViewHolder>{

    ArrayList<ItemRecycler> mItems;

    public AdpRecycler(ArrayList<ItemRecycler> mItems) {
        this.mItems = mItems;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position, List<Object> payloads) {
        holder.mIvImage.setImageDrawable(R.);
        holder.mTvName.setText(mItems.get(position).getName());
        holder.mTvAge.setText(mItems.get(position).getAge());
        holder.mTvStyle.setText(mItems.get(position).getStyle());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIvImage;
        private TextView mTvName;
        private TextView mTvAge;
        private TextView mTvStyle;

        public ItemViewHolder(View itemView) {
            super(itemView);
            this.mIvImage = (ImageView) itemView.findViewById(R.id.ivImage);
            this.mTvName = (TextView) itemView.findViewById(R.id.tvName);
            this.mTvAge = (TextView) itemView.findViewById(R.id.tvAge);
            this.mTvStyle = (TextView) itemView.findViewById(R.id.tvStyle);
        }
    }

}
