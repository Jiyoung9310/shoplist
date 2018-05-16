package com.example.zigzag.shoplist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zigzag.shoplist.view.model.ItemRecycler;
import com.example.zigzag.shoplist.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

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
        ItemViewHolder vh = new ItemViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.mTvNum.setText(mItems.get(position).getNum());
        holder.mTvName.setText(mItems.get(position).getName());
        holder.mTvAge.setText(mItems.get(position).getAge());
        holder.mTvStyle.setText(mItems.get(position).getStyle());
        holder.mIvProfile.setImageURI(mItems.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvNum;
        private TextView mTvName;
        private TextView mTvAge;
        private TextView mTvStyle;
        private SimpleDraweeView mIvProfile;

        public ItemViewHolder(View itemView) {
            super(itemView);
            this.mTvNum = (TextView) itemView.findViewById(R.id.tvNumber);
            this.mTvName = (TextView) itemView.findViewById(R.id.tvName);
            this.mTvAge = (TextView) itemView.findViewById(R.id.tvAge);
            this.mTvStyle = (TextView) itemView.findViewById(R.id.tvStyle);
            this.mIvProfile = (SimpleDraweeView) itemView.findViewById(R.id.ivImage);
        }
    }

}
